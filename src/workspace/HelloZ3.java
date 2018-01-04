package workspace;

import com.microsoft.z3.*;

import java.util.HashMap;

public class HelloZ3 {


    public static void solve1() {
        //application example of z3
        //reference https://qiita.com/quentin-maisonneuve/items/4f32cf52293dc44ffc3d

        System.out.println("(a || b) && c");
        Context context = new Context(new HashMap<>());
        BoolExpr a = context.mkBoolConst("a");
        BoolExpr b = context.mkBoolConst("b");
        BoolExpr c = context.mkBoolConst("c");
        BoolExpr formula = context.mkAnd(context.mkOr(a, b), c); // (a || b) && c

        Solver solver = context.mkSolver();
        solver.add(formula);

        if(solver.check() == Status.SATISFIABLE) {
            Model model = solver.getModel();
            Expr resultVa = model.eval(a, false);
            Expr resultVb = model.eval(b, false);
            Expr resultVc = model.eval(c, false);

            System.out.println("a:"+resultVa);   // Result is true
            System.out.println("b:"+resultVb);   // Result is false
            System.out.println("c:"+resultVc);   // Result is true
        }

        context.close();
    }

    public static void solve2() {
		System.out.println("a < b + 5, 10 > a");

		Context ctx = new Context();

		IntExpr a = ctx.mkIntConst("a");
		IntExpr b = ctx.mkIntConst("b");
		IntExpr five = ctx.mkInt(5);
		IntExpr ten = ctx.mkInt(10);

		ArithExpr b_plus_five = ctx.mkAdd(b, five);

		BoolExpr c1 = ctx.mkLt(a, b_plus_five);
		BoolExpr c2 = ctx.mkGt(a, ten);

		BoolExpr q = ctx.mkAnd(c1, c2);

		Solver s = ctx.mkSolver();
		s.add(q);
		if(s.check() == Status.SATISFIABLE) {
			System.out.println("SAT");
			Model model = s.getModel();
			System.out.println("a=" + model.evaluate(a, false) +
					"b=" + model.evaluate(b, false));
		} else {
			System.out.println("UNSAT");
		}

		ctx.close();
	}

	public static void solve3() {
		System.out.println("a < b + 5 and a % 10 = 0");

		Context ctx = new Context();

		IntExpr a = ctx.mkIntConst("a");
		IntExpr b = ctx.mkIntConst("b");
		IntExpr five = ctx.mkInt(5);
		IntExpr ten = ctx.mkInt(10);
		IntExpr zero = ctx.mkInt(0);

		ArithExpr b_plus_five = ctx.mkAdd(b, five);

		BoolExpr c1 = ctx.mkLt(a, b_plus_five);
		//BoolExpr c2 = ctx.mkGt(a, ten);
		IntExpr c2 = ctx.mkMod(a, ten);
		BoolExpr c3 = ctx.mkEq(c2, zero);

		BoolExpr q = ctx.mkAnd(c1, c3);

		Solver s = ctx.mkSolver();
		s.add(q);
		if(s.check() == Status.SATISFIABLE) {
			System.out.println("SAT");
			Model model = s.getModel();
			System.out.println("a=" + model.evaluate(a, false) +
					"b=" + model.evaluate(b, false));
		} else {
			System.out.println("UNSAT");
		}

		ctx.close();
	}

	static void solve4() {
    	Context ctx = new Context();
		IntExpr a = ctx.mkIntConst("a");
		IntExpr b = ctx.mkIntConst("b");
		IntExpr five = ctx.mkInt(5);
		ArithExpr a_plus_b = ctx.mkAdd(a, b);
		//BoolExpr q = ctx.mkEq(a_plus_b, five);
		BoolExpr q = ctx.mkEq(a_plus_b, five);
		BoolExpr nq = ctx.mkNot(q);
		Solver s = ctx.mkSolver();
		s.add(nq);
		if(s.check() == Status.SATISFIABLE) {
			System.out.println("SAT");
			Model model = s.getModel();
			System.out.println("a=" + model.evaluate(a, false) +
					"b=" + model.evaluate(b, false));

			System.out.println(model.toString());

		} else {
			System.out.println("UNSAT");
		}


	}


	public static void main(String[] args) {
		solve1();
		solve2();
		solve3();
		solve4();
	}

}
