package bwdm;






import java.io.*;
import java.util.ArrayList;

public class IfElseSyntaxTree {

	private static IfNode root;
	private static BufferedReader if_elseBufferReader;
	//static ArrayList<conditionAndBoolean>[] conditionAndBooleanTable;

	public static ArrayList<ArrayList<ConditionAndBoolean>> conditionAndBooleanTable;
	public static ArrayList<String> returnValues = new ArrayList<String>();

	/*
	 * if_elseファイル作成時、
	 * 現状は戻り値としてseq of char(ダブルクォートを含んでいるもののみ処理しているため)しか
	 * 対応していない
	 */
	/*
	 * TODO
	 * 条件式と真偽値をセットで保持するデータ構造を作る
	 * if条件式を満たす入力データ生成
	 */



	public static void main(String[] args) throws ParserException, LexException, IOException{
		//こいつに満たすべき条件式とその真偽値をいれてく
		conditionAndBooleanTable = new ArrayList<ArrayList<ConditionAndBoolean>>();
		if_elseFileGenerate();
		ifElseSyntaxTreeGenerate("KikkawaToolAndExampleData/data/mod2.if_else");
		//recursivePrintNodes(root);
		recursiveReturnNodeFind(root);
		printConditionAndBooleanTable();
	} //end main


	public IfElseSyntaxTree(String if_elsePath) throws ParserException, LexException, IOException{
		//public static void main(String[] args) throws ParserException, LexException, IOException{

		//こいつに満たすべき条件式とその真偽値をいれてく
		conditionAndBooleanTable = new ArrayList<ArrayList<ConditionAndBoolean>>();

		if_elseFileGenerate();

		ifElseSyntaxTreeGenerate(if_elsePath);

		//recursivePrintNodes(root);

		recursiveReturnNodeFind(root);

		//printConditionAndBooleanTable();


	} //end main

	//if式構文木を作る
	private static void ifElseSyntaxTreeGenerate(String _if_elsePath) throws IOException {
		//FileReader if_elseFileReader = new FileReader(new File("KikkawaToolAndExampleData/data/mod2.if_else"));
		//FileReader if_elseFileReader = new FileReader(new File("KikkawaToolAndExampleData/data/syntaxtree.if_else"));
		FileReader if_elseFileReader = new FileReader(new File(_if_elsePath));
		if_elseBufferReader = new BufferedReader(if_elseFileReader);

		//rootの準備
		String currentLine = if_elseBufferReader.readLine(); //最初はifなので無視
		currentLine = if_elseBufferReader.readLine(); //これは最初のifの条件式
		//多分
		root = ifNodeGenerate(currentLine, null, 0);
		root.isIfNode = true;
		root.isTrueNode = null;
		//これだけでおｋ
		if_elseBufferReader.close();

	}

	private static IfNode ifNodeGenerate(String _condition,
			                                IfNode _parentNode,
			                                int _nodeLevel) throws IOException{
		IfNode ifNode = new IfNode(_condition, _nodeLevel);
		ifNode.parentNode = _parentNode;
		String nextToken;

		//trueNode
		nextToken = if_elseBufferReader.readLine();
		if(nextToken.equals("if")){//ifの場合、次のtokenは条件式なので読み込む
			String conditionStr = if_elseBufferReader.readLine();
			ifNode.conditionTrueNode = ifNodeGenerate(conditionStr, ifNode, _nodeLevel+1);
		} else {//ifじゃない場合、nextTokenにはreturnが入っている
			ifNode.conditionTrueNode = returnNodeGenerate(nextToken, ifNode, _nodeLevel+1);
		}
		ifNode.conditionTrueNode.isTrueNode = true;

		//else 特にすることは無いので無視
		//ということはif_elseファイルからelseを消しても問題無し？
		nextToken = if_elseBufferReader.readLine();

		//elseの次、falseNode
		//falseNode
		nextToken = if_elseBufferReader.readLine();
		if(nextToken.equals("if")){//ifの場合、次のtokenは条件式なので読み込む
			String conditionStr = if_elseBufferReader.readLine();
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
			System.out.println("root,       nodeID:" + node.ID +
								" nodeLevel:" + node.nodeLevel +
								" condition:"+node.getConditionOrReturnStr());
			IfNode ifnode = (IfNode)node;
			recursivePrintNodes(ifnode.conditionTrueNode);
			recursivePrintNodes(ifnode.conditionFalseNode);
			return ;
		}

		if(node.isIfNode) {
			System.out.println("IfNode,     nodeID:" + node.ID +
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

		if(node.isIfNode) { //IfNodeならば
			IfNode ifNode = (IfNode)node;
			recursiveReturnNodeFind(ifNode.conditionTrueNode);
			recursiveReturnNodeFind(ifNode.conditionFalseNode);
		} else { //ReturnNodeならば
			returnValues.add(node.getConditionOrReturnStr());
			ArrayList<ConditionAndBoolean> tmp = new ArrayList<ConditionAndBoolean>();
			//System.out.print(node.getConditionOrReturnStr() + " ");
			Node tmpNode = node;
			while(tmpNode != null){ //下のbreak文が
				//System.out.print(tmpNode.parentNode.getConditionOrReturnStr() + tmpNode.isTrueNode + " ");
				tmp.add(new ConditionAndBoolean(tmpNode.parentNode.getConditionOrReturnStr(), tmpNode.isTrueNode));
				tmpNode = tmpNode.parentNode;
				if(tmpNode.parentNode == null) break;
			}
			//System.out.println();

			/*
			for(ConditionAndBoolean cab : tmp){
				System.out.println(cab.condition + cab.bool);
			}
			*/

			conditionAndBooleanTable.add(tmp);
			//System.out.println("size"+conditionAndBooleanTable.size());
		}

	}

	public static void printConditionAndBooleanTable() {
		int i=0;
		for(ArrayList<ConditionAndBoolean> array: conditionAndBooleanTable){
			System.out.print("No." + i + " " + "returnValue:" + returnValues.get(i));
			for(ConditionAndBoolean cab : array){
				System.out.print(cab.condition + "," + cab.bool + " ");
			}
			System.out.println();
			i++;
		}
	}



	/*
	 * if_elseファイル生成
	 * 最初のifから終わりのセミコロンまでを抜き出す
	 */
	private static void if_elseFileGenerate() throws LexException, ParserException, IOException{
		new AnalyzedData("KikkawaToolAndExampleData/data/problem.vdmpp",
				 "KikkawaToolAndExampleData/data/problem.csv", "");
		//AnalyzedData.printInformation();
		//AnalyzedData.printVdmFileText();

		//analyzed file create
		String ifElseFilePath = AnalyzedData.getVdmFilePath().replace(".vdmpp", "") + ".if_else";
		FileWriter ifElseFile = null;
		try {
			ifElseFile = new FileWriter(new File(ifElseFilePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("making ifelse file failed, return.");
			System.exit(1);
		}

		//if-else式の抜き取り
		//if, condition, then, returnのみにする
		//conditionは()の有る無しに関わらず全てのTokenをくっつけて、
		//最後に()を消去する
		LexTokenReader ltr = new LexTokenReader
				(new File("KikkawaToolAndExampleData/data/problem.vdmpp"),
				Settings.dialect);
		String currentToken = ltr.getLast().toString();

		//if開始箇所まで進める
		while(!currentToken.equals("if")){
			//System.out.println(ltr.getLast().toString());
			currentToken = ltr.nextToken().toString();
		}

		//System.out.println(ltr.getLast().toString());

		String conditionTmp = ""; //条件式のtokenをくっつけていく
		while(!currentToken.equals(";")){
			switch (currentToken) {
			case "if" :
			case "else":
				ifElseFile.write(currentToken + "\n");
				break;

			case "then":
				ifElseFile.write(conditionTmp.replace("(", "").replace(")", "") + "\n");
				conditionTmp = "";
				break;
			default:
				if(currentToken.contains("\"")){ //もしも戻り値だったら
					ifElseFile.write(currentToken + "\n");
				} else {
					conditionTmp = conditionTmp + currentToken;
				}
				break;
			}
			//System.out.println(currentToken);
			currentToken = ltr.nextToken().toString();
		}

		ifElseFile.write(";");


		ltr.close();
		ifElseFile.close();

	}


}
