package bwdm.informationStore;

public class ReturnNode extends Node{
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
