package bwdm.informationStore;


import bwdm.boundaryValueAnalysisUnit.BoundaryValueAnalyzer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConditionAndReturnValueList {

	public int size=0;

	public ArrayList<ConditionAndReturnValue> getConditionAndReturnValues() {
		return conditionAndReturnValues;
	}
	private ArrayList<ConditionAndReturnValue> conditionAndReturnValues;

	InformationExtractor ie;
	BoundaryValueAnalyzer bva;

	public class ConditionAndReturnValue {
		String returnStr;
		//conditions[0]の真偽値がbools[0]
		//conditions[1]の真偽値がbools[1]...
		ArrayList<String>  conditions;
		ArrayList<Boolean> bools;


		public ConditionAndReturnValue() {
			size++;
			conditions = new ArrayList();
			bools = new ArrayList();
		}

		public String getReturnStr() { return returnStr; }
		public ArrayList<String> getConditions() { return conditions; }
		public ArrayList<Boolean> getBools() { return bools; }

	}

	public ConditionAndReturnValueList(IfNode _root, InformationExtractor _ie) {
		conditionAndReturnValues = new ArrayList();
		this.ie = _ie;
		recursiveReturnNodeFind(_root);





		/*20180124 制約に引数型の制限が必要だと気づいてささっと実装*/
		//各制約に、引数型の制限を加える
		/*

		ArrayList<String> arguments = ie.getArgumentTypes();
		ArrayList<String> parameters = ie.getParameters();

		for(int i=0; i<conditionAndReturnValues.size(); i++) {
			ConditionAndReturnValue currentCarv = conditionAndReturnValues.get(i);
			for(int j=0; j< arguments.size(); j++) {
				String currentType = arguments.get(j);
				String currentParm = parameters.get(j);

				switch (currentType) {
					case "int":
						currentCarv.getConditions().add(currentParm+"<="+Integer.MAX_VALUE);
						currentCarv.bools.add(true);
						currentCarv.getConditions().add(Integer.MIN_VALUE + "<="+currentParm);
						currentCarv.bools.add(true);
						break;
					case "nat":
						currentCarv.getConditions().add(currentParm+"<="+Integer.MAX_VALUE * 2);
						currentCarv.bools.add(true);
						currentCarv.getConditions().add("0" + "<=" + currentParm);
						currentCarv.bools.add(true);
						break;

					case "nat1":
						currentCarv.getConditions().add(currentParm+"<="+Integer.MAX_VALUE * 2 + 1);
						currentCarv.bools.add(true);
						currentCarv.getConditions().add("1" + "<=" + currentParm);
						currentCarv.bools.add(true);
						break;

				}
			}


		}
		*/
		/*ささっと実装ここまで*/


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
				conditionAndReturnValue.bools.add( tmpNode.isTrueNode ); //親ノードからみてTrue側かどうか

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
