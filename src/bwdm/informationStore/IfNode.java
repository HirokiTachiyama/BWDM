package bwdm.informationStore;

public class IfNode extends Node {
	public String conditionStr;
	public Node conditionTrueNode;

	public Node conditionFalseNode; //Ifノードには子ノードが2つ

	public IfNode(String _conditionStr, int _nodeLevel){
		conditionStr = _conditionStr;
		nodeLevel = _nodeLevel;
		isIfNode = true;
		ID = staticID++;
	}


	@Override
	public String getConditionOrReturnStr() {
		return conditionStr;
	}

}
