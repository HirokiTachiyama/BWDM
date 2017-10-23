package past_BWDM_classes.bwdm;

import java.util.ArrayList;
import java.util.HashMap;

public class EvaluationOfConditions {
	/*
	 * Attention!!!!
	 * 20160119現在は引数は一つに限定しているので、
	 * boundaryValueTableをそのまま
	 * 入力値として扱える aについての境界値が並んでるのだけがあればいいから
	 * しかし複数引数になった場合、ちゃんと入力値のテーブルを作って、ここにもそれを
	 * 使うように反映させる必要が有る。
	 */
	//static String[] bValues; //入力値生成部で作った境界値テストの入力値

	//入力値を条件文に当てはめてtrue/false判定したものを入れていく
	// Undefined FT TT Undefined FF TF ...
	private static ArrayList<String> evaluationResult;
	//public static String[] evaluationResult; //bValuesをそれぞれtrue/false判定した結果

	/* EvaluateConditionExE
	 * 概要
	 * if条件文配列(conditions)、境界値テストの入力値配列(bValues)を受け取り、evaluationResultを作る
	 * コンストラクタはもろもろで作れなかったから、リファクタリングよろしく
	 */

	public EvaluationOfConditions(){
		evaluationResult = new ArrayList<String>();
		ArrayList<String> ifConditionsJoinedInCameForward
			= AnalyzedData.getIfConditionsJoinedInCameForward();
		String[][] inputData = BoundaryValueAnalyze.getInputData();
		for(int i=0; i<inputData.length; i++){//各入力値ごとに
			String key = "";
			for(String conditionStr : ifConditionsJoinedInCameForward){//各条件文に対して
				System.out.print("Input of evluated is:");
				for(String str : inputData[i]) System.out.print (str + " ");
				System.out.println("\nCondition of evaluated is:" + conditionStr);
				key = key + evaluate(inputData[i], conditionStr);
			}
			evaluationResult.add(key);
		}

		//Out_of_TypeBoundsOut_of_TypeBounds -> Out_of_TypeBounds
		for(int i=0; i<evaluationResult.size(); i++){
			if(evaluationResult.get(i).contains("Out_of_Bounds")){
				evaluationResult.set(i, "Out_of_Bounds");
			}
			//System.out.println(evaluationResult.get(i));
		}
	}

	private static String evaluate(String[] _inputData, String _condition){
		@SuppressWarnings("rawtypes")
		HashMap[][] ifConditions= AnalyzedData.getIfConditions();

		if(_condition.contains("mod")){ //剰余式だったら
			int line=0, column=0;
			for(int i = 0; i<ifConditions.length; i++){
				for(int j = 0; j<ifConditions[i].length; j++){
					if(ifConditions[i][j] == null){ //剰余式なら次の回へ
						continue;
					}
					try{
						String tmp = ifConditions[i][j].get("Surplus").toString();
						if(ifConditions[i][j] == null){
							continue;
						}
					} catch(NullPointerException n) {
						//普通のif式がifConditions[i][j]に入ってたら
						//Surplusがぬるぽになるから、先にアクセスしてぬるぽならbreak
						n.printStackTrace();
						System.out.println("ガッ！");
						continue;
					}
					String tmp = ifConditions[i][j].get("LeftHand").toString() +
								 ifConditions[i][j].get("Symbol").toString() +
								 ifConditions[i][j].get("RightHand").toString() +
								 "=" +
								 ifConditions[i][j].get("Surplus").toString();
					if(_condition.equals(tmp)){
						line = i;
						column = j;
						System.out.println(_condition + " : " + tmp);
						break;
					}
				}
			}

			String leftHand="", symbol="", rightHand="", surplus = "";
			try{
				leftHand  = ifConditions[line][column].get("LeftHand").toString();
				symbol    = ifConditions[line][column].get("Symbol").toString();
				rightHand = ifConditions[line][column].get("RightHand").toString();
				surplus = ifConditions[line][column].get("Surplus").toString();
			} catch(ArrayIndexOutOfBoundsException e) {
				//System.out.println("EvaluationOfConditions.evaluate内で例外発生:"+e.getMessage());
				//System.out.println("おそらくifConditions内に要素が無いために起こった例外");
			}

			for(int i=0; i < _inputData.length; i++){
				//System.out.println("今のiは"+i+_inputData[i]);
				if(_inputData[i].contains("Min-1") || _inputData[i].contains("Max+1")){
					//入力値が型境界値外の場合
					System.out.println("型外の値 終了");
					return "Out_of_Bounds";
				} else if(_inputData[i].contains("Min") || _inputData[i].contains("Max")){
					//入力値が型境界値上の場合
					//型に応じて値を場合分けして入れる
					long actuallyValueOf_inputData=0;
					switch(_inputData[i]){
					case "intMin" : actuallyValueOf_inputData = Integer.MIN_VALUE; break;
					case "intMax" : actuallyValueOf_inputData = Integer.MAX_VALUE; break;
					case "natMin" : actuallyValueOf_inputData = 0;                 break;
					case "natMax" : actuallyValueOf_inputData = 4294967295L;       break;
					case "nat1Min": actuallyValueOf_inputData = 1;                 break;
					case "nat1Max": actuallyValueOf_inputData = 4294967296L;       break;
					default:
						System.out.println("想定外の入力値 処理終了 at EvaluationOFConditions");
						System.exit(-1);
					}
					System.out.println("actuallyValueOf_inputData:"+actuallyValueOf_inputData);
					if(_condition.contains(AnalyzedData.getFormalArguments().get(i))){
						if(AnalyzedData.getFormalArguments().get(i).equals(leftHand)){
							leftHand = String.valueOf(actuallyValueOf_inputData);
						} else if(AnalyzedData.getFormalArguments().get(i).equals(rightHand)){
							rightHand = String.valueOf(actuallyValueOf_inputData);
						}
					}
				} else {
					//入力値が数値の場合
					if(_condition.contains(AnalyzedData.getFormalArguments().get(i))){
						if(AnalyzedData.getFormalArguments().get(i).equals(leftHand)){
							leftHand = _inputData[i];
						} else if(AnalyzedData.getFormalArguments().get(i).equals(rightHand)){
							rightHand = _inputData[i];
						}
					}
				}
			}
			if( judgeMod(leftHand, symbol, rightHand, surplus) ){
				return "T";
			} else {
				return "F";
			}

		} else {


			int line=0, column=0;
			for(int i = 0; i<ifConditions.length; i++){
				for(int j = 0; j<ifConditions[i].length; j++){
					String tmp = ifConditions[i][j].get("LeftHand").toString() +
								 ifConditions[i][j].get("Symbol").toString() +
								 ifConditions[i][j].get("RightHand").toString();
					if(_condition.equals(tmp)){
						line = i;
						column = j;
						break;
					}
				}
			}
			//System.out.println("line:"+line+", column:"+column);
			//System.out.println(_condition+" == "+ifConditions[line][column].get("LeftHand").toString() +
			//		ifConditions[line][column].get("Symbol").toString() +
			//		ifConditions[line][column].get("RightHand").toString());

			String leftHand="", symbol="", rightHand="";
			try{
				leftHand  = ifConditions[line][column].get("LeftHand").toString();
				symbol    = ifConditions[line][column].get("Symbol").toString();
				rightHand = ifConditions[line][column].get("RightHand").toString();
			}catch(ArrayIndexOutOfBoundsException e){
				//System.out.println("EvaluationOfConditions.evaluate内で例外発生:"+e.getMessage());
				//System.out.println("おそらくifConditions内に要素が無いために起こった例外");
			}

			//System.out.println("BEFORE"+leftHand + symbol + rightHand);
			for(int i=0; i < _inputData.length; i++){
				//System.out.println("今のiは"+i+_inputData[i]);
				if(_inputData[i].contains("Min-1") || _inputData[i].contains("Max+1")){
					//入力値が型境界値外の場合
					return "Out_of_Bounds";
				} else if(_inputData[i].contains("Min") || _inputData[i].contains("Max")){
					//入力値が型境界値上の場合
					//型に応じて値を場合分けして入れる
					long actuallyValueOf_inputData=0;
					switch(_inputData[i]){
						case "intMin" : actuallyValueOf_inputData = Integer.MIN_VALUE; break;
						case "intMax" : actuallyValueOf_inputData = Integer.MAX_VALUE; break;
						case "natMin" : actuallyValueOf_inputData = 0; break;
						case "natMax" : actuallyValueOf_inputData = 4294967295L; break;
						case "nat1Min": actuallyValueOf_inputData = 1; break;
						case "nat1Max": actuallyValueOf_inputData = 4294967296L; break;
						default:
							System.out.println("想定外の入力値 処理終了 at EvaluationOFConditions");
							System.exit(-1);
					}
					if(_condition.contains(AnalyzedData.getFormalArguments().get(i))){
						if(AnalyzedData.getFormalArguments().get(i).equals(leftHand)){
							leftHand = String.valueOf(actuallyValueOf_inputData);
						} else if(AnalyzedData.getFormalArguments().get(i).equals(rightHand)){
							rightHand = String.valueOf(actuallyValueOf_inputData);
						}
					}

				} else {
					//入力値が数値の場合
					if(_condition.contains(AnalyzedData.getFormalArguments().get(i))){
						if(AnalyzedData.getFormalArguments().get(i).equals(leftHand)){
							leftHand = _inputData[i];
						} else if(AnalyzedData.getFormalArguments().get(i).equals(rightHand)){
							rightHand = _inputData[i];
						}
					}
				}
			}
			//System.out.println("AFTER"+leftHand + symbol + rightHand);

			if(judgeCondition(leftHand, symbol, rightHand)){
				return "T";
			} else {
				return "F";
			}
		} // modか普通のif条件式かのif-elseおわり
	}

	public static ArrayList<String> getEvaluationResult(){
		return evaluationResult;
	}

	public static void printEvaluationResult(){
		int i=0;
		for(String element : evaluationResult){
			System.out.println(i++ +":"+element);
		}
	}

	public static boolean judgeCondition(String _leftHand, String _symbol, String _rightHand){
		boolean returnBool=true; //初めがtrueの意味は特には無い
		//System.out.println("式は" + _leftHand + _symbol + _righthand);


		if(_symbol.equals("<")) {
			returnBool = Long.valueOf(_leftHand) < Long.valueOf(_rightHand);
		} else if(_symbol.equals(">")) {
			returnBool = Long.valueOf(_leftHand) > Long.valueOf(_rightHand);
		} else if(_symbol.equals("<=")) {
			returnBool = Long.valueOf(_leftHand) <= Long.valueOf(_rightHand);
		} else if(_symbol.equals(">=")) {
			returnBool = Long.valueOf(_leftHand) >= Long.valueOf(_rightHand);
		}
		return returnBool;
	}

	public static boolean judgeMod(String _leftHand, String _symbol, String _rightHand, String _surplus){
		boolean returnBool = true;
		System.out.print("judgeModなう:"+_leftHand+_symbol+_rightHand+"="+_surplus + " : ");
		returnBool = ( Long.valueOf(_leftHand) % Long.valueOf(_rightHand) == Long.valueOf(_surplus));

		if(returnBool) System.out.println("TRUE");
		else System.out.println("FALSE");
		return returnBool;
	}





}
