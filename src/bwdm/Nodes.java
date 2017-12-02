package bwdm;

class IfNode extends Node {
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
		return conditionStr;
	}

}

class ReturnNode extends Node{

	public String returnStr;


	public ReturnNode(String str, int _nodeLevel) {
		returnStr = str;
		nodeLevel = _nodeLevel;
		isIfNode = false;
		ID = staticID++;
	}

	@Override
	public String getConditionOrReturnStr() {
		return returnStr;
	}
}


abstract class Node {

    /*
     *parentNodeは絶対IfNodeだからIfNodeにしてもいいかも
     */
    public Node parentNode = null;

    
	public int nodeLevel; //このノードのいる階層 rootが0
	public static int staticID=0; //ノードを作ったら1つずつ数字が増える
	public int ID; //このノードのID
	public Boolean isIfNode; //自身がIfNodeなのか
	public Boolean isTrueNode; //自身がconditionTrueNodeなのか

	//抽象メソッド
	//IfNode or ReturnNodeかで conditionStr or returnStrを返す
	abstract public String getConditionOrReturnStr();



}
