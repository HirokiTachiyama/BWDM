package past_BWDM_classes.bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.io.IOException;
import java.util.ArrayList;


public class BoundaryValueAnalyze {

	@SuppressWarnings("rawtypes")
	private static ArrayList[] boundaryValueTable;
	/*       [0]         [1]     [2]      [3]          [4]   [5]   [6]   [7]   [8]  [9]   [10]  [11]
	 * [0]  intMin-1 intMin intMax intMax+1 4      5      6     7
	 * [1]  intMin-1 intMin intMax intMax+1 -3     -2    100  101  0    1
	 * [2]  intMin-1 intMin intMax intMax+1  9     10    3      2    -29  -30   -39   -40
	 */
	private static String[][] inputData;


	@SuppressWarnings("unchecked")
	public BoundaryValueAnalyze() throws ParserException, LexException, IOException {

		//型境界値、if条件文境界値の登録
		boundaryValueTable = new ArrayList[AnalyzedData.getFormalArguments().size()];
		for(int i=0; i<AnalyzedData.getFormalArguments().size(); i++){
			boundaryValueTable[i] = new ArrayList<String>();
			makeTypeBoundaryValue(i);
			//各変数についての条件数だけ
			for(int j=0; j<AnalyzedData.getIfConditions()[i].length; j++){
				makeIfConditionBoundaryValue(i, j);
			}
		}
		//重複している境界値の除去
		for(ArrayList<String> bvt : boundaryValueTable){
			recursiveRemoverOfOverlappedElement(bvt, 0);
		}

		//for(int i=0; i<AnalyzedData.getFormalArguments().size(); i++){
		//	makeTypeBoundaryValue(i);
		//	//各変数についての条件数だけ
		//	for(int j=0; j<AnalyzedData.getIfConditions()[i].length; j++){
		//		makeIfConditionBoundaryValue(i, j);
		//	}
		//}
		//テストデータ入力値の件数は境界値の数を掛けたもの
		int inputDataNumber = 1;
		for(ArrayList<String> list : boundaryValueTable){
			inputDataNumber *= list.size();
		}
		inputData = new String[inputDataNumber][];


		for(int i=0; i<inputDataNumber; i++){
			inputData[i] = new String[AnalyzedData.getFormalArguments().size()];
		}

		//System.out.println("inputDataNumber:"+inputDataNumber);
		//System.out.println("inputData:"+inputData.length+"line");
		//System.out.println("inputData[0]:"+inputData[0].length+"column");
		makeInputData();


	}

	@SuppressWarnings("unchecked")
	private void makeTypeBoundaryValue(int _lineNumber){
		String type = AnalyzedData.getArgumentTypes().get(_lineNumber); //型を取得
		boundaryValueTable[_lineNumber].add(type+"Min-1");
		boundaryValueTable[_lineNumber].add(type+"Min");
		boundaryValueTable[_lineNumber].add(type+"Max");
		boundaryValueTable[_lineNumber].add(type+"Max+1");
		/*switch(type){ //MinとMaxの値を実際の数値にしていた時期もあった
		case "int": //結局、intMin-1などと表記を統一させるために全てMin,Max表記にした
			boundaryValueTable[_lineNumber].add(String.valueOf(Integer.MIN_VALUE));
			boundaryValueTable[_lineNumber].add(String.valueOf(Integer.MAX_VALUE));
			break;
		case "nat":
			boundaryValueTable[_lineNumber].add("0");
			long hoge = 4294967295L;
			boundaryValueTable[_lineNumber].add(String.valueOf(hoge));
			break;
		case "nat1":
			boundaryValueTable[_lineNumber].add("1");
			long fuga = 4294967296L;
			boundaryValueTable[_lineNumber].add(String.valueOf(fuga));
			break;
		default:
			boundaryValueTable[_lineNumber].add(String.valueOf(Integer.MIN_VALUE));
			boundaryValueTable[_lineNumber].add(String.valueOf(Integer.MAX_VALUE));
			break;
		}*/
	}

	//引数_lineNumber番目の_conditionNumber目の条件文から境界値を出す
	@SuppressWarnings("unchecked")
	private void makeIfConditionBoundaryValue(int _lineNumber, int _conditionNumber){
		String leftHand = AnalyzedData.getIfConditions()[_lineNumber][_conditionNumber].get("LeftHand").toString();
		String symbol = AnalyzedData.getIfConditions()[_lineNumber][_conditionNumber].get("Symbol").toString();
		String rightHand = AnalyzedData.getIfConditions()[_lineNumber][_conditionNumber].get("RightHand").toString();

		if(symbol.equals("<=") || symbol.equals("<")){
			//System.out.println("<= or <");
			if(isNumber(leftHand)){//左辺が数字
				//System.out.print("number is left, so ");
				long Min, MinMinus1;
				if(symbol.equals("<=")){
					Min = (long)Integer.parseInt(leftHand);
					MinMinus1 = Min - 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Min));
					boundaryValueTable[_lineNumber].add(Long.toString(MinMinus1));
				}else{//"<"の場合
					Min = (long)Integer.parseInt(leftHand) + 1; //デフォはInteger
					MinMinus1 = Min - 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Min));
					boundaryValueTable[_lineNumber].add(Long.toString(MinMinus1));
				}
				//System.out.println("Min is "+Min+", Min-1 is "+MinMinus1);

			}else if(isNumber(rightHand)){//右辺が数字
				//System.out.println("number is right, so ");
				long Max, MaxPlus1;
				if(symbol.equals("<=")){
					Max = (long)Integer.parseInt(rightHand);
					MaxPlus1 = Max + 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Max));
					boundaryValueTable[_lineNumber].add(Long.toString(MaxPlus1));
				}else{//"<"の場合
					Max = (long)Integer.parseInt(rightHand) - 1;
					MaxPlus1 = Max + 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Max));
					boundaryValueTable[_lineNumber].add(Long.toString(MaxPlus1));
				}
				//System.out.println("Max is "+Max+", Max+1 is "+MaxPlus1);
			} else {
				//System.out.println("条件文の両辺が変数です。処理を終了。");
				System.exit(-1);
			}

		}else if(symbol.equals(">=") || symbol.equals(">")){
			//System.out.println(">= and > ");
			if(isNumber(leftHand)){//左辺が数字
				//System.out.print("number is left, so ");
				long Max, MaxPlus1;
				if(symbol.equals(">=")){
					Max = (long)Integer.parseInt(leftHand);
					MaxPlus1 = Max + 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Max));
					boundaryValueTable[_lineNumber].add(Long.toString(MaxPlus1));
				}else{//">"の場合
					Max = (long)Integer.parseInt(leftHand) - 1;
					MaxPlus1 = Max + 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Max));
					boundaryValueTable[_lineNumber].add(Long.toString(MaxPlus1));
				}
				//System.out.println("Max is "+Max+", Max+1 is "+MaxPlus1);

			}else if(isNumber(rightHand)){//右辺が数字
				//System.out.println("number is right, so ");
				long Min, MinMinus1;
				if(symbol.equals(">=")){
					Min= (long)Integer.parseInt(rightHand);
					MinMinus1 = Min - 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Min));
					boundaryValueTable[_lineNumber].add(Long.toString(MinMinus1));
				}else{//">"の場合
					Min = (long)Integer.parseInt(rightHand) + 1;
					MinMinus1 = Min - 1;
					boundaryValueTable[_lineNumber].add(Long.toString(Min));
					boundaryValueTable[_lineNumber].add(Long.toString(MinMinus1));
				}
				//System.out.println("Min is "+Min+", Min-1 is "+MinMinus1);
			} else {
				System.out.println("条件文の両辺が変数です。処理を終了。");
				System.exit(-1);
			}
		} else if(symbol.equals("mod")) { //modは数値が右辺にある場合のみに対応する
			long center=0,minus1=0, plus1=0;
			if(isNumber(leftHand)){//左辺が数字
				center = (long)Integer.parseInt(leftHand) +
						 (long)Integer.parseInt(AnalyzedData.getIfConditions()[_lineNumber][_conditionNumber].get("Surplus").toString());
				plus1 = center + 1;
				minus1 = center - 1;
				boundaryValueTable[_lineNumber].add(Long.toString(center));
			}else if(isNumber(rightHand)){//右辺が数字
				center = (long)Integer.parseInt(rightHand) +
						 (long)Integer.parseInt(AnalyzedData.getIfConditions()[_lineNumber][_conditionNumber].get("Surplus").toString());
				plus1 = center + 1;
				minus1 = center - 1;
				boundaryValueTable[_lineNumber].add(Long.toString(center));
			}
			boundaryValueTable[_lineNumber].add(Long.toString(minus1));
			boundaryValueTable[_lineNumber].add(Long.toString(plus1));
		}
	}

	private static boolean isNumber(String num) {
	    try {
	        Integer.parseInt(num);
	        	return true;
	        } catch (NumberFormatException e) {
	        	return false;
	    }
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList[] getBoundaryValueTable() {
		return boundaryValueTable;
	}
	public static String[][] getInputData(){
		return inputData;
	}



	@SuppressWarnings("unchecked")
	private void makeInputData(){
		//System.out.println("AnalyzedData.getFormalArguments().size()"+AnalyzedData.getFormalArguments().size());
		switch(AnalyzedData.getFormalArguments().size()){
			case 0:
				//inputData[0][0] = "NONE";
				//System.out.println("(NONE)");
				System.out.print("makeInputData default action. argNum:");
				System.out.println(AnalyzedData.getFormalArguments().size());
				System.out.println("引数が0個です。境界値テスト要るん？");
				System.exit(-1);
				break;
			case 1:
				nTimesInsert(1, 0, 0, boundaryValueTable[0]);
				//for(int i=0; i<inputData[0].length; i++){
				//	System.out.println("("+inputData[0][i]+")");
				//}
				break;
			case 2:
				nTimesInsert(boundaryValueTable[1].size(), 0, 0, boundaryValueTable[0]);
				for(int i=0; i<inputData.length; i+=boundaryValueTable[1].size()){
					nTimesInsert(1, i,  1, boundaryValueTable[1]);
				}

				//for(int i=0; i<inputData.length; i++){
				//	System.out.println("("+inputData[i][0] +", "+ inputData[i][1]+")");
				//}
				break;
			default:
				System.out.print("makeInputData default action. argNum:");
				System.out.println(AnalyzedData.getFormalArguments().size());
				System.out.println("引数の個数が未対応です。悪しからず！");
				System.exit(-1);
				break;
		}

	}

    //与えられたArrayList<String>を一つの要素につきtimes回ずつ
	//inputDataのlineNumber行目columnNumber列目, lineNumber+1行目columnNumber列目,,,と入れていく
    private void nTimesInsert(int times, int lineNumber, int columnNumber, ArrayList<String> _bv){
    	int currentLineNumber=0;
    	for(int i=0; i<_bv.size(); i++){ //argsの要素一つ一つに対して
    		for(int j=0; j<times; j++){
    			inputData[lineNumber+currentLineNumber][columnNumber] = _bv.get(i);
    			currentLineNumber++;
    		}
    	}
    }

    //bouradaryValueTableの中の重複している要素を再帰的に除去
    private void recursiveRemoverOfOverlappedElement(ArrayList<String> _bvt, int currentPosition){
    	System.out.println("currentPosition:"+currentPosition+", _bvt.size()"+_bvt.size());
    	if(currentPosition == _bvt.size()-1) return;
    	String currentElement = _bvt.get(currentPosition);
    	System.out.println("currentElement"+currentElement);
    	//引数がMinかMaxだったら(Min-1とMax+1は除く)
    	if( (currentElement.contains("Min") || currentElement.contains("Max")) &&
    	   !(currentElement.contains("Min-1") || currentElement.contains("Max+1")) ) {
    		System.out.println("Min or Maxなう");
        	long actuallyValueOf_currentElement=0;
			switch(currentElement){
				case "intMin" : actuallyValueOf_currentElement = Integer.MIN_VALUE; break;
				case "intMax" : actuallyValueOf_currentElement = Integer.MAX_VALUE; break;
				case "natMin" : actuallyValueOf_currentElement = 0; break;
				case "natMax" : actuallyValueOf_currentElement = 4294967295L; break;
				case "nat1Min": actuallyValueOf_currentElement = 1; break;
				case "nat1Max": actuallyValueOf_currentElement = 4294967296L; break;
				default:
					System.out.println("想定外の入力値 処理終了 at BoundaryValueAnalyze");
					System.exit(-1);
			}
    		System.out.println("switchわず"+actuallyValueOf_currentElement);
	    	for(int i=0; i<_bvt.size(); i++){
	    		System.out.println(actuallyValueOf_currentElement + "&&&" + _bvt.get(i));
	    		if( (String.valueOf(actuallyValueOf_currentElement).equals(_bvt.get(i))) && (i != currentPosition) ){
	    			//if( (currentElement.equals(_bvt.get(i))) && (i != currentPosition) ){
	    	    	System.out.println(_bvt.get(i) + " was overlapped. Removed.");
	    			_bvt.remove(i);
	    		}
	    	}
	    	if(++currentPosition < _bvt.size()) {
	    		recursiveRemoverOfOverlappedElement(_bvt, currentPosition);
	    	} else {
	    		return ;
	    	}
    	} else {
    		System.out.println("Min or Max以外なう");
	    	for(int i=0; i<_bvt.size(); i++){
	    		if( (currentElement.equals(_bvt.get(i))) && (i != currentPosition) ){
	    	    	System.out.println(_bvt.get(i) + " was overlapped. Removed.");
	    			_bvt.remove(i);
	    		}
	    	}
	    	if(++currentPosition < _bvt.size()) {
	    		recursiveRemoverOfOverlappedElement(_bvt, currentPosition);
	    	} else {
	    		return ;
	    	}
    	}
    }

	public static void printBoundaryValueTable(){
		for(int i=0; i<AnalyzedData.getFormalArguments().size(); i++){
			for(int j=0; j<boundaryValueTable[i].size(); j++){
				System.out.print(boundaryValueTable[i].get(j)+" ");
			}
			System.out.println();
		}
	}

	public static String getBoundaryValueTableString(){
		String str = "";
		for(int i=0; i<AnalyzedData.getFormalArguments().size(); i++){
			for(int j=0; j<boundaryValueTable[i].size(); j++){
				str += boundaryValueTable[i].get(j) + " ";
			}
			str += "\n";
		}
		return str;
	}


	public static void printInputValue(){
		for(String[] strArray : inputData){
			//System.out.println(strArray.length);
			switch (strArray.length) {
				case 1: System.out.println("("+strArray[0]+")"); break;
				case 2: System.out.println("("+strArray[0]+", "+strArray[1]+")"); break;
				case 3: System.out.println("("+strArray[0]+", "+strArray[1]+", "+strArray[2]+")"); break;

			}
		}
	}

	public static String getInputValue(){
		String str = "";
		for(String[] strArray : inputData){
			//System.out.println(strArray.length);
			switch (strArray.length) {
				case 1: str += "("+strArray[0]+")\n"; break;
				case 2: str += "("+strArray[0]+", "+strArray[1]+")\n"; break;
				case 3: str += "("+strArray[0]+", "+strArray[1]+", "+strArray[2]+")\n"; break;
			}
		}
		return str;

	}


}
