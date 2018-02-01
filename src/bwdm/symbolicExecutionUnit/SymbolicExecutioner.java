package bwdm.symbolicExecutionUnit;

import bwdm.informationStore.ConditionAndReturnValueList;
import bwdm.informationStore.InformationExtractor;
import bwdm.informationStore.ConditionAndReturnValueList.ConditionAndReturnValue;
import bwdm.Util;
import com.microsoft.z3.*;

import static bwdm.boundaryValueAnalysisUnit.ExpectedOutputDataGenerator.makeParsedCondition;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolicExecutioner {

	//複数の変数があっても、条件式次第で一つの変数の値次第で、
	//残りの変数の値はどうでもいい場合がある
	//その場合、z3はevaluateした場合、変数名をそのまま返すので、
	//<String, String>としてある
	private ArrayList<HashMap<String, String>> inputDataList;

	private ArrayList<String> expectedOutputDataList;

	InformationExtractor ie;
	Context ctx;

	//各条件式は左辺右辺のうち片方のみが変数であるという制約付き
	public SymbolicExecutioner(InformationExtractor _ie) {
		this.ie = _ie;
		inputDataList = new ArrayList();
		expectedOutputDataList = new ArrayList();
		ctx = new Context();

		ConditionAndReturnValueList conditionAndReturnValueList = _ie.getConditionAndReturnValueList();

		conditionAndReturnValueList.getConditionAndReturnValues().forEach(c -> {
			doSymbolicExecution(c);
		});
	}

	private void doSymbolicExecution(ConditionAndReturnValue _conditionAndReturnValue) {
		ArrayList<String> conditions = _conditionAndReturnValue.getConditions();
		ArrayList<Boolean> bools = _conditionAndReturnValue.getBools();

		BoolExpr conditionUnion = ctx.mkBool(Boolean.TRUE); //単位元としてTRUEの式を一つくっつけとく
		BoolExpr expr;
		for(int i=0; i<conditions.size(); i++) {
			HashMap<String, String> parsedCondition = makeParsedCondition(conditions.get(i));
			Boolean bool = bools.get(i);
			String operator = parsedCondition.get("operator");

			if (operator.equals("mod")) { //剰余式
				expr = makeModExpr(
						parsedCondition.get("left"),
						parsedCondition.get("right"),
						parsedCondition.get("surplus"),
						bool
				);
			} else { //不等式
				expr = makeInequalityExpr(
						parsedCondition.get("left"),
						operator,
						parsedCondition.get("right"),
						bool
				);
			}

			conditionUnion = ctx.mkAnd(conditionUnion, expr);
		}
		expr = null;
		ArrayList<String> parameters = ie.getParameters();
		ArrayList<String> argumentTypes = ie.getArgumentTypes();

		for(int i=0; i<parameters.size(); i++) {
			if (!argumentTypes.get(i).equals("int")) { //int型なら0以上の制限はいらない
				expr = makeInequalityExpr(parameters.get(i), ">", "0", true);
			}
		}
		if(expr != null) {
			conditionUnion = ctx.mkAnd(conditionUnion, expr);
		}

		Solver solver = ctx.mkSolver();
		solver.add(conditionUnion);
		if(solver.check() == Status.SATISFIABLE) {
			Model m = solver.getModel();

			parameters = ie.getParameters();
			HashMap<String, String> hm = new HashMap();
			parameters.forEach(p -> {
				hm.put(p, m.evaluate(ctx.mkIntConst(p), false).toString());
			});

			inputDataList.add(hm);
			expectedOutputDataList.add(_conditionAndReturnValue.getReturnStr());
		}

	}


	BoolExpr makeModExpr(String _left, String _right, String _surplus, Boolean _bool) {
		BoolExpr expr;
		Long surplus = Long.valueOf(_surplus);

		IntExpr modExpr;
		if(Util.isNumber(_left)) { //右辺が変数
			Long left = Long.valueOf(_left);
			String right = _right;
			modExpr = ctx.mkMod(ctx.mkInt(left), ctx.mkIntConst(right));
		}
		else { //左辺が変数
			String left = _left;
			Long right = Long.valueOf(_right);
			modExpr = ctx.mkMod(ctx.mkIntConst(left), ctx.mkInt(right));
		}
		expr = ctx.mkEq(modExpr, ctx.mkInt(surplus));

		if(_bool) return expr; //満たすべき真偽(_bool)次第で、notをつける
		else     return ctx.mkNot(expr);
	}


	BoolExpr makeInequalityExpr(String _left, String _operator, String _right, Boolean _bool) {
		BoolExpr expr;

		if(Util.isNumber(_left)) { //右辺が変数
			Long left = Long.valueOf(_left);
			String right = _right;

			switch (_operator) {
				case "<":  expr = ctx.mkLt( ctx.mkInt(left), ctx.mkIntConst(right) ); break;
				case "<=": expr = ctx.mkLe( ctx.mkInt(left), ctx.mkIntConst(right) ); break;
				case ">":  expr = ctx.mkGt( ctx.mkInt(left), ctx.mkIntConst(right) ); break;
				case ">=": expr = ctx.mkGe( ctx.mkInt(left), ctx.mkIntConst(right) ); break;
				default :  expr = null;
			}

		}
		else { //左辺が変数
			String left = _left;
			Long right = Long.valueOf(_right);
			switch (_operator) {
				case "<":  expr = ctx.mkLt( ctx.mkIntConst(left), ctx.mkInt(right) ); break;
				case "<=": expr = ctx.mkLe( ctx.mkIntConst(left), ctx.mkInt(right) ); break;
				case ">":  expr = ctx.mkGt( ctx.mkIntConst(left), ctx.mkInt(right) ); break;
				case ">=": expr = ctx.mkGe( ctx.mkIntConst(left), ctx.mkInt(right) ); break;
				default :  expr = null;
			}
		}

		if(_bool) return expr; //満たすべき真偽(_bool)次第で、notをつける
		else     return ctx.mkNot(expr);
	}

	public ArrayList<HashMap<String, String>> getInputDataList() {
		return inputDataList;
	}
	public ArrayList<String> getExpectedOutputDataList() {
		return expectedOutputDataList;
	}


}
