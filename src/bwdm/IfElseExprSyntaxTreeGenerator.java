package bwdm;




import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IfElseExprSyntaxTreeGenerator {

	IfNode root;
	List<String> ifElses;
	int count = 0;


	public IfElseExprSyntaxTreeGenerator(String _ifExpressionBoby) throws ParserException, LexException, IOException{
		shapeIfElseBody(_ifExpressionBoby);
		generateIfElseSyntaxTree();
		//recursiveReturnNodeFind(root);
	} //end constructor

	public IfNode getRoot() { return root; }
	public void printAllNodes() { printNodesRecursively(root); }

	/*
     * shaping of passed ifElseBody
     */
	private void shapeIfElseBody(String _ifElseBody) throws LexException, ParserException, IOException {
		ifElses = new ArrayList<String>();
		String ifElseBody = _ifElseBody;
		ifElseBody = ifElseBody.replace("(", "").
				                replace(")", "").
				                replace("if", "if\n").
				                replace("else", "else\n").
				                replace("then", "").
				                replace(" ", "");
		ifElseBody = ifElseBody + "\n;";
		ifElses = Arrays.asList(ifElseBody.split("\n"));
	}


	//if式構文木を作る
	private void generateIfElseSyntaxTree()
			throws IOException {

		//rootの準備
		ifElses.get(count++); //最初はifなので無視
		String currentLine = ifElses.get(count++); //これは最初のifの条件式
		//多分
		root = generateIfNode(currentLine, null, 0);
		root.isIfNode = true;
		root.isTrueNode = null;
		//これだけでおｋ
	}

	private IfNode generateIfNode(String _condition, IfNode _parentNode, int _nodeLevel)
			throws IOException {
		IfNode ifNode = new IfNode(_condition, _nodeLevel);
		ifNode.parentNode = _parentNode;
		String nextToken;

		//trueNode
		nextToken = ifElses.get(count++);
		if(nextToken.equals("if")){//ifの場合、次のtokenは条件式なので読み込む
			String conditionStr = ifElses.get(count++);
			ifNode.conditionTrueNode = generateIfNode(conditionStr, ifNode, _nodeLevel+1);
		} else {//ifじゃない場合、nextTokenにはreturnが入っている
			ifNode.conditionTrueNode = generateReturnNode(nextToken, ifNode, _nodeLevel+1);
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
			ifNode.conditionFalseNode = generateIfNode(conditionStr, ifNode, _nodeLevel+1);
		} else {//ifじゃない場合、nextTokenにはreturnが入っている
			ifNode.conditionFalseNode = generateReturnNode(nextToken, ifNode, _nodeLevel+1);
		}
		ifNode.conditionFalseNode.isTrueNode = false;

		return ifNode;
	}

	private ReturnNode generateReturnNode(String returnStr,
												 Node parentNode,
												 int _nodeLevel){
		ReturnNode returnNode =  new ReturnNode(returnStr, _nodeLevel);
		returnNode.parentNode = parentNode;
		return returnNode;
	}


	//ReturnNodeの発見とそこに至る為に必要な条件式とその真偽値
	private void recursiveReturnNodeFind(Node node) {
		if(node.isIfNode) {
			IfNode ifNode = (IfNode)node;
			recursiveReturnNodeFind(ifNode.conditionTrueNode);
			recursiveReturnNodeFind(ifNode.conditionFalseNode);
		} else { //ReturnNodeならば
			//returnValues.add(node.getConditionOrReturnStr());
			//ArrayList<ConditionAndBoolean> tmp = new ArrayList<ConditionAndBoolean>();
			Node tmpNode = node;
			while(tmpNode != null){ //下のbreak文が
				//tmp.add(new ConditionAndBoolean(tmpNode.parentNode.getConditionOrReturnStr(), tmpNode.isTrueNode));
				tmpNode = tmpNode.parentNode;
				if(tmpNode.parentNode == null) break;
			}
		}
	}

	//nodeを末端まで再帰的に情報を表示していく
	private void printNodesRecursively(Node node) {
		//親がいなければroot
		if(node.parentNode == null){
			System.out.println(
					"root,      nodeID:" + node.ID +
					" nodeLevel:" + node.nodeLevel +
					" condition:"+node.getConditionOrReturnStr());
			IfNode ifnode = (IfNode)node;
			printNodesRecursively(ifnode.conditionTrueNode);
			printNodesRecursively(ifnode.conditionFalseNode);
			return ;
		}

		if(node.isIfNode) {
			System.out.println(
					"IfNode,    nodeID:" + node.ID +
					" parentNodeID:" + node.parentNode.ID +
					" nodeLevel:" + node.nodeLevel +
					" boolean:" + node.isTrueNode +
					" condition:"+node.getConditionOrReturnStr());
			IfNode ifnode = (IfNode)node;

			printNodesRecursively(ifnode.conditionTrueNode);
			printNodesRecursively(ifnode.conditionFalseNode);

		} else {
			System.out.println(
					"ReturnNode, nodeID:" + node.ID +
					" parentNodeID:" + node.parentNode.ID +
					" nodeLevel:" + node.nodeLevel +
					" boolean:" + node.isTrueNode +
					" Return:"+node.getConditionOrReturnStr());
		}
	}


}
