package bwdm;




import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.io.*;
import java.util.ArrayList;

class IfElseSyntaxTree {

	static IfNode root;
	ArrayList<String> ifElses;
	int count = 0;


	public IfElseSyntaxTree(String _ifExpressionBoby) throws ParserException, LexException, IOException{
		extractIfElse(_ifExpressionBoby);
		generateIfElseSyntaxTree();
		//recursivePrintNodes(root);
		recursiveReturnNodeFind(root);
		//printConditionAndBooleanTable();

	} //end constructor

	/*
     * 最初のifから終わりのセミコロンまで抜き出す
     */
	private ArrayList extractIfElse(String _ifElseBody) throws LexException, ParserException, IOException {
		ifElses = new ArrayList<String>();
		String ifElseBody = _ifElseBody;
		System.out.println(ifElseBody);
		ifElseBody = ifElseBody.replace("(", "").
				                replace(")", "");
		System.out.println(ifElseBody);
		return ifElses;
	}


	//if式構文木を作る
	void generateIfElseSyntaxTree()
			throws IOException {

		//rootの準備
		ifElses.get(count++); //最初はifなので無視
		String currentLine = ifElses.get(count++); //これは最初のifの条件式
		//多分
		root = ifNodeGenerate(currentLine, null, 0);
		root.isIfNode = true;
		root.isTrueNode = null;
		//これだけでおｋ
	}

	IfNode ifNodeGenerate(String _condition, IfNode _parentNode, int _nodeLevel)
			throws IOException{
		IfNode ifNode = new IfNode(_condition, _nodeLevel);
		ifNode.parentNode = _parentNode;
		String nextToken;

		//trueNode
		nextToken = ifElses.get(count++);
		if(nextToken.equals("if")){//ifの場合、次のtokenは条件式なので読み込む
			String conditionStr = ifElses.get(count++);
			ifNode.conditionTrueNode = ifNodeGenerate(conditionStr, ifNode, _nodeLevel+1);
		} else {//ifじゃない場合、nextTokenにはreturnが入っている
			ifNode.conditionTrueNode = returnNodeGenerate(nextToken, ifNode, _nodeLevel+1);
		}
		ifNode.conditionTrueNode.isTrueNode = true;

		//else 特にすることは無いので無視
		//ということはif_elseファイルからelseを消しても問題無し？
		nextToken = ifElses.get(count++);

		//elseの次、falseNode
		//falseNode
		nextToken = ifElses.get(count++);
		if(nextToken.equals("if")){//ifの場合、次のtokenは条件式なので読み込む
			String conditionStr = ifElses.get(count++);
			ifNode.conditionFalseNode = ifNodeGenerate(conditionStr, ifNode, _nodeLevel+1);
		} else {//ifじゃない場合、nextTokenにはreturnが入っている
			ifNode.conditionFalseNode = returnNodeGenerate(nextToken, ifNode, _nodeLevel+1);
		}
		ifNode.conditionFalseNode.isTrueNode = false;

		return ifNode;
	}

	private static ReturnNode returnNodeGenerate(String returnStr,
													Node parentNode,
													int _nodeLevel){
		ReturnNode returnNode =  new ReturnNode(returnStr, _nodeLevel);
		returnNode.parentNode = parentNode;
		return returnNode;
	}


	//nodeを末端まで再帰的に情報を表示していく
	private void recursivePrintNodes(Node node){
		//親がいなければroot
		if(node.parentNode == null){
			System.out.println("root,      nodeID:" + node.ID +
								" nodeLevel:" + node.nodeLevel +
								" condition:"+node.getConditionOrReturnStr());
			IfNode ifnode = (IfNode)node;
			recursivePrintNodes(ifnode.conditionTrueNode);
			recursivePrintNodes(ifnode.conditionFalseNode);
			return ;
		}

		if(node.isIfNode) {
			System.out.println("IfNode,    nodeID:" + node.ID +
								" parentNodeID:" + node.parentNode.ID +
								" nodeLevel:" + node.nodeLevel +
								" boolean:" + node.isTrueNode +
					            " condition:"+node.getConditionOrReturnStr());
			IfNode ifnode = (IfNode)node;

			recursivePrintNodes(ifnode.conditionTrueNode);
			recursivePrintNodes(ifnode.conditionFalseNode);

		} else {
			System.out.println("ReturnNode, nodeID:" + node.ID +
								" parentNodeID:" + node.parentNode.ID +
								" nodeLevel:" + node.nodeLevel +
								" boolean:" + node.isTrueNode +
								" Return:"+node.getConditionOrReturnStr());
		}
	}


	//ReturnNodeの発見とそこに至る為に必要な条件式とその真偽値
	public static void recursiveReturnNodeFind(Node node) {
		if(node.isIfNode) {
			IfNode ifNode = (IfNode)node;
			recursiveReturnNodeFind(ifNode.conditionTrueNode);
			recursiveReturnNodeFind(ifNode.conditionFalseNode);
		} else { //ReturnNodeならば
			//returnValues.add(node.getConditionOrReturnStr());
			//ArrayList<ConditionAndBoolean> tmp = new ArrayList<ConditionAndBoolean>();
			//System.out.print(node.getConditionOrReturnStr() + " ");
			Node tmpNode = node;
			while(tmpNode != null){ //下のbreak文が
				//System.out.print(tmpNode.parentNode.getConditionOrReturnStr() + tmpNode.isTrueNode + " ");
				//tmp.add(new ConditionAndBoolean(tmpNode.parentNode.getConditionOrReturnStr(), tmpNode.isTrueNode));
				tmpNode = tmpNode.parentNode;
				if(tmpNode.parentNode == null) break;
			}
			//System.out.println();

			/*
			for(ConditionAndBoolean cab : tmp){
				System.out.println(cab.condition + cab.bool);
			}
			*/

			//conditionAndBooleanTable.add(tmp);
			//System.out.println("size"+conditionAndBooleanTable.size());
		}

	}

	public static void printConditionAndBooleanTable() {
		int i=0;
		//for(ArrayList<ConditionAndBoolean> array: conditionAndBooleanTable){
		//	System.out.print("No." + i + " " + "returnValue:" + returnValues.get(i));
		//	for(ConditionAndBoolean cab : array){
		//		System.out.print(cab.condition + "," + cab.bool + " ");
		//	}
		//	System.out.println();
		//	i++;
		//}
	}

}
