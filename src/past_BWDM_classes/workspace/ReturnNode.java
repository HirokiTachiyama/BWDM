package past_BWDM_classes.workspace;

public class ReturnNode extends Node{

	public String returnStr;


	public ReturnNode(String str, int _nodeLevel) {
		returnStr = str;
		nodeLevel = _nodeLevel;
		isIfNode = false;
		ID = staticID++;
	}

	@Override
	public String getConditionOrReturnStr() {
		// TODO 自動生成されたメソッド・スタブ
		return returnStr;
	}
}
