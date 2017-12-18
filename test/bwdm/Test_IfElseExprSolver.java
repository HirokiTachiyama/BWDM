package bwdm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Test_IfElseExprSolver {

	IfElseExprSolver solver;
	HashMap<String, String> condition;


	@BeforeAll
	void beforeAll() {
		condition = new HashMap<String, String>();
		condition.put("left", "a");
		condition.put("symbol", "<");
		condition.put("right", "5");

		solver = new IfElseExprSolver();

	}


	@Test
	void Test() {

	}
}
