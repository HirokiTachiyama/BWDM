package bwdm.symbolicExecutionUnit;

import bwdm.informationStore.ConditionAndReturnValueList;
import bwdm.informationStore.InformationExtractor;
import bwdm.informationStore.ConditionAndReturnValueList.ConditionAndReturnValue;
import bwdm.Util;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Solver;

import static bwdm.boundaryValueAnalysisUnit.ExpectedOutputDataGenerator.makeParsedCondition;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolicExecutioner {

	private ArrayList<HashMap<String, Long>> inputDataList;
	private ArrayList<String> expectedOutputDataList;

	Context ctx;
	Solver solver;

	//各条件式は左辺右辺のうち片方のみが変数であるという制約付き
	public SymbolicExecutioner(InformationExtractor _ie) {
		inputDataList = new ArrayList();
		expectedOutputDataList = new ArrayList();
		ctx = new Context();
		solver = ctx.mkSolver();

		ArrayList<String> parameters = _ie.getParameters();
		ConditionAndReturnValueList conditionAndReturnValueList = _ie.getConditionAndReturnValueList();

		doSymbolicExecution(conditionAndReturnValueList.getConditionAndReturnValues().get(0));


		System.out.println();
	}

	private void doSymbolicExecution(ConditionAndReturnValue _conditionAndReturnValue) {
		ArrayList<String> conditions = _conditionAndReturnValue.getConditions();
		ArrayList<Boolean> bools = _conditionAndReturnValue.getBools();

		boolean unitElement = true;
		BoolExpr conditionUnion = ctx.mkBool(unitElement); //単位元
		for(int i=0; i<conditions.size(); i++) {
			HashMap<String, String> parsedCondition = makeParsedCondition(conditions.get(i));
			Boolean bool = bools.get(i);
			String operator = parsedCondition.get("operator");

			BoolExpr expr;
			if (operator.equals("mod")) { //剰余式
				expr = exeMod(
						parsedCondition.get("left"),
						parsedCondition.get("right"),
						parsedCondition.get("surplus"),
						bool
				);
			} else { //不等式
				expr = exeInequality(
						parsedCondition.get("left"),
						operator,
						parsedCondition.get("right"),
						bool
				);
			}

			conditionUnion = ctx.mkAnd(conditionUnion, expr);

		}

		solver.add(conditionUnion);


		inputDataList.add(new HashMap());

	}


	BoolExpr exeMod(String _left, String _right, String _surplus, Boolean _bool) {
		Long surplus = Long.valueOf(_surplus);
		BoolExpr expr = null;

		if(Util.isNumber(_left)) { //右辺が変数
			Long left = Long.valueOf(_left);
			String right = _right;
		}
		else { //左辺が変数
			String left = _left;
			Long right = Long.valueOf(_right);


		}


		return expr;
	}


	BoolExpr exeInequality(String _left, String _operator, String _right, Boolean bool) {
		BoolExpr expr = null;


		return expr;
	}


}
