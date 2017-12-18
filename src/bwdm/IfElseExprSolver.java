package bwdm;

import java.util.HashMap;


import static bwdm.Util.isNumber;

public class IfElseExprSolver {

	public IfElseExprSolver(HashMap<String, String> _condition) {

		solve(_condition);

	}

	public void solve(HashMap<String, String> _condition) {
		String left = _condition.get("left");
		String symbole = _condition.get("symbol");
		String right = _condition.get("right");

		if(isNumber(left)) {

		} else { //right-hand is number

		}

	}





}
