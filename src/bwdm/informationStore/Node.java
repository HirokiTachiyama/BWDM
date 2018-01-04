package bwdm.informationStore;

public abstract class Node {
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


