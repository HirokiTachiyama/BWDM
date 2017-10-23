package past_BWDM_classes.workspace;

public class IfNode extends Node {
	public Node conditionTrueNode, conditionFalseNode;
	public String conditionStr;

	public IfNode(String _conditionStr, int _nodeLevel){
		conditionStr = _conditionStr;
		nodeLevel = _nodeLevel;
		isIfNode = true;
		ID = staticID++;
	}

	@Override
	public String getConditionOrReturnStr() {
		// TODO 自動生成されたメソッド・スタブ
		return conditionStr;
	}

}
