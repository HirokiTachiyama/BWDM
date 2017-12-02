package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



/*
 * Attention!
 * とりあえず引数が1つのもののみに限定
 */


public class InputDataGeneratorBySyntaxTree {


	public ArrayList<Integer> inputDataGeneratedFromSyntaxTree = new ArrayList<Integer>();
	


	//public static void main(String args[]) throws ParserException, LexException, IOException{
	public InputDataGeneratorBySyntaxTree(String if_elseFilepath) throws ParserException, LexException, IOException{
		IfElseSyntaxTree syntaxTree =  new IfElseSyntaxTree(if_elseFilepath);
		ArrayList<String> returnValues = IfElseSyntaxTree.returnValues;
		syntaxTree.printConditionAndBooleanTable();

		inputDataGeneratedFromSyntaxTree = new ArrayList<Integer>();


		//整数値　負の数を含めて生成
		Random random = new Random();
		int randomValue = random.nextInt(Integer.MAX_VALUE) - Integer.MAX_VALUE / 2;
		//System.out.println(randomValue);
		boolean allConditionNOTSufficiency = true;

		ArrayList<ArrayList<ConditionAndBoolean>> table = syntaxTree.conditionAndBooleanTable;
		for(ArrayList<ConditionAndBoolean> array : table) {
			while(allConditionNOTSufficiency) {//全ての条件式とその真偽値を満たしたらおしまい
				//randomValue = random.nextInt(Integer.MAX_VALUE) - Integer.MAX_VALUE / 2;
				allConditionNOTSufficiency = false;
				randomValue = random.nextInt(10000);
				//System.out.print(randomValue);
				for(ConditionAndBoolean cab : array){
					if(cab.condition.contains("mod")){//剰余式なら
						if(!judgeMod(cab.condition, cab.bool, randomValue)){
							allConditionNOTSufficiency = true;
							break;
						}
					} else {
						if(!judgeInequality(cab.condition, cab.bool, randomValue)) {
							allConditionNOTSufficiency = true;
							break;
						}
					}
				}
			}
			inputDataGeneratedFromSyntaxTree.add(randomValue);
			allConditionNOTSufficiency = true;
		}



		/*
		 * と　り　あ　え　ず　！　！
		 */
		System.out.println("と　り　あ　え　ず　！　！");
		for(int i=0; i<returnValues.size(); i++){

			System.out.println(returnValues.get(i) + " " + inputDataGeneratedFromSyntaxTree.get(i));
		}

	}


	//条件式の両辺のうち片方は整数値という制限有
	private static boolean judgeMod(String modCondition, boolean targetBool, int inputValue){
		boolean resultBool = false;
		//System.out.println(modCondition + " " + targetBool + " " + inputValue);

		String left, right, surplus;
		int modIndex = modCondition.indexOf("mod");
		int equalIndex = modCondition.indexOf("=");
		left = modCondition.substring(0, modIndex);
		right = modCondition.substring(modIndex + 3, equalIndex);
		surplus = modCondition.substring(equalIndex + 1);

		//System.out.println(left + " " + right + " " + surplus);

		if(isNumber(left)) {//左辺が数字
			resultBool = Integer.parseInt(left) % inputValue == Integer.parseInt(surplus);
		} else {
			resultBool = inputValue % Integer.parseInt(right) == Integer.parseInt(surplus);
		}

		return resultBool == targetBool;
	}



	private static boolean judgeInequality(String inequalityCondition,boolean targetBool, int inputValue){
		boolean resultBool = false;
		System.out.println(inequalityCondition + " " + targetBool + " " + inputValue);

		String left, symbol, right;
		symbol = getSymbol(inequalityCondition);

		int symbolIndex = inequalityCondition.indexOf(symbol);
		left = inequalityCondition.substring(0, symbolIndex);
		right = inequalityCondition.substring(symbolIndex + 1);

		System.out.println(left + " " + symbol + " " + right);

		if(isNumber(left)) {//左辺が数字
			if(symbol.equals("<")) {
				resultBool = Long.valueOf(left) < inputValue;
			} else if(symbol.equals(">")) {
				resultBool = Long.valueOf(left) > inputValue;
			} else if(symbol.equals("<=")) {
				resultBool = Long.valueOf(left) <= inputValue;
			} else if(symbol.equals(">=")) {
				resultBool = Long.valueOf(left) >= inputValue;
			}
		} else { //右辺が数字
			if(symbol.equals("<")) {
				resultBool = inputValue < Integer.parseInt(right);
			} else if(symbol.equals(">")) {
				resultBool = inputValue > Integer.parseInt(right);
			} else if(symbol.equals("<=")) {
				resultBool = inputValue <= Integer.parseInt(right);
			} else if(symbol.equals(">=")) {
				resultBool = inputValue >= Integer.parseInt(right);
			}

		}

		return resultBool == targetBool;

	}


	private static boolean isNumber(String val){
		try{
			Integer.parseInt(val);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}

	private static String getSymbol(String condition){
		if(condition.indexOf("<=") != -1){
			return "<=";
		}else if(condition.indexOf(">=") != -1){
			return ">=";
		}else if(condition.indexOf("<") != -1){
			return "<";
		}else if(condition.indexOf(">") != -1){
			return ">";
		}
		return "other";
	}



}
