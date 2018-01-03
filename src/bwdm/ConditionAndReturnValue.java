package bwdm;

import java.util.ArrayList;

class ConditionAndReturnValueList {

	int size=0;
	ArrayList<ConditionAndReturnValue> conditionAndReturnValues;

	private class ConditionAndReturnValue {
		String returnStr;
		ArrayList<String>  conditions;
		ArrayList<Boolean> bools;

		public ConditionAndReturnValue() {
			size++;
			conditions = new ArrayList();
			bools = new ArrayList();
		}
	}

	public ConditionAndReturnValueList(IfNode _root) {
		conditionAndReturnValues = new ArrayList();
		recursiveReturnNodeFind(_root);
	}


	//ReturnNodeの発見とそこに至る為に必要な条件式とその真偽値
	public void recursiveReturnNodeFind(Node node) {
		if(node.isIfNode) { //IfNodeならば
			IfNode ifNode = (IfNode)node;
			recursiveReturnNodeFind(ifNode.conditionTrueNode);
			recursiveReturnNodeFind(ifNode.conditionFalseNode);
		} else { //ReturnNodeならば
			ConditionAndReturnValue conditionAndReturnValue =
					new ConditionAndReturnValue();
			conditionAndReturnValue.returnStr = node.getConditionOrReturnStr();

			Node tmpNode = node;
			while(tmpNode != null){ //下のbreak文が
				conditionAndReturnValue.conditions.add(tmpNode.parentNode.getConditionOrReturnStr());
				conditionAndReturnValue.bools.add( !tmpNode.isIfNode );

				tmpNode = tmpNode.parentNode;
				if(tmpNode.parentNode == null) break;
			}
			conditionAndReturnValues.add(conditionAndReturnValue);
		}
	}

	public void printAllConditionAndBoolAndReturn() {

		for(int i=0; i<size; i++) {
			ConditionAndReturnValue current = conditionAndReturnValues.get(i);
			ArrayList<String> conditions = current.conditions;
			ArrayList bools = current.bools;

			for(int j=0; j<conditions.size(); j++) {
				System.out.println( conditions.get(j) + " " + bools.get(j).toString());
			}
			System.out.println(current.returnStr);
			System.out.println();
		}
	}

}
