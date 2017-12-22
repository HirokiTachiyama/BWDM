package bwdm;

import java.util.ArrayList;

class ConditionAndReturnValueList {

	public ConditionAndReturnValueList(IfNode _root) {

	}

	private class ConditionAndReturnValue {
		public ConditionAndReturnValue() {

		}

		ArrayList<String> conditionLexes;
		String returnStr;
	}

	public void printConditionLexes() {
/*
		conditionLexes.forEach(lex -> {
			System.out.print(lex + " ");
		});
*/
		System.out.println();
	}

}
