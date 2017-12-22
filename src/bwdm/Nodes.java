package bwdm;

abstract class Node {
	/*
	 *parentNodeは絶対IfNodeだからIfNodeにしてもいいかも
	 */
	public Node parentNode = null;

	public int nodeLevel; //このノードのいる階層 rootが0
	public static int staticID=0; //ノードを作ったら1つずつ数字が増える ノードの総数
	public int ID; //このノードのID
	public Boolean isIfNode; //自身がIfNodeなのか
	public Boolean isTrueNode; //自身がconditionTrueNodeなのか

	//抽象メソッド
	//IfNode or ReturnNodeかで conditionStr or returnStrを返す
	abstract public String getConditionOrReturnStr();

}

class IfNode extends Node {
	public String conditionStr;
	public Node conditionTrueNode, conditionFalseNode; //Ifノードには子ノードが2つ

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

class ReturnNode extends Node{
	public String returnStr;

	public ReturnNode(String _returnStr, int _nodeLevel) {
		returnStr = _returnStr;
		nodeLevel = _nodeLevel;
		isIfNode = false;
		ID = staticID++;
	}

	@Override
	public String getConditionOrReturnStr() {
		return returnStr;
	}
}


