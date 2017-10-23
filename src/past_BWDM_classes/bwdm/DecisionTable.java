package past_BWDM_classes.bwdm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DecisionTable {
	//+1はそれぞれcondition/actionの文そのものを入れるスペース
	static String[][] conditionBoolean;
	static String[][] actionBoolean;
	private static HashMap<String, String> booleanSequenceToAction;
	static String[] keyArray;

	public DecisionTable(String fileName){
		readDecisionTable(fileName);
		
		makeBooleanSequenceToAction();
	}

	public static HashMap<String, String> getBooleanSequenceToAction(){
		return booleanSequenceToAction;
	}

	private static void makeBooleanSequenceToAction(){
		keyArray = new String[conditionBoolean[0].length-1];
		booleanSequenceToAction = new HashMap<String, String>();

		for(int i=0; i<conditionBoolean[0].length-1; i++){ //conditionの組み合わせ数2, 4, 8, 16...それぞれに対して
			String key = new String();
			for(int j=0; j<conditionBoolean.length; j++){//conditionの数だけ
				key = key + conditionBoolean[j][i]; //hashmapのキーとなるtrue/falseの並びを作る
			}
			//System.out.println(key);
			keyArray[i] = key;
			//System.out.println(actionTrueFalse.length);
			for(int k=0; k<actionBoolean.length; k++){
				//System.out.println(actionTrueFalse[k][i]);
				//System.out.println(actionTrueFalse[k][(int)Math.pow(2,  conditionTrueFalse.length)]);
				if(actionBoolean[k][i].equals("T")){//actionがtrueだったら
					//上で作ったキーと見つけたactionで登録
					booleanSequenceToAction.put(key, actionBoolean[k][(int)Math.pow(2,  conditionBoolean.length)]);
				}
			}
			//System.out.println(key);
		}
		booleanSequenceToAction.put("Out_of_Bounds", "Undefined Action");
		for(int i=0; i<keyArray.length; i++){
			System.out.println(booleanSequenceToAction.get(keyArray[i]));
		}
	}


	private static void readDecisionTable(String fileName) {
		//conditionとactionをアトリビュートに入れる
		int conditionNum=0; //条件数
		int actionNum=0; //action数

		try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            //読み込んだファイルを１行ずつ処理する
            String line;
            while ((line = br.readLine()) != null) { //条件数の数え上げ
            	//System.out.println(line);
            	if(line.contains("Condition")){
            		conditionNum++;
            	}
            	else if(line.contains("Action")){
            		actionNum++;
            	}
            }
            br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//System.out.println("con:"+conditionNum+", act:"+actionNum);

		//+1はそれぞれcondition/actionの文そのものを入れるスペース
		conditionBoolean = new String[conditionNum][(int) Math.pow(2, conditionNum)+1];
		actionBoolean = new String[actionNum][(int) Math.pow(2, conditionNum)+1];

		//System.out.println("conditionLine:"+conditionNum+", column:"+(int)Math.pow(2, conditionNum));
		//System.out.println("actionLine:"+actionNum+", column:"+(int)Math.pow(2, conditionNum));

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            //読み込んだファイルを１行ずつ処理する
            String line;
            StringTokenizer token;
            int currentConditionLine = 0;
            int currentActionLine=0;
            while ((line = br.readLine()) != null) {
            	System.out.println("currentConditionsLine"+line);
            	line = line.replace("\"", "");
                //区切り文字","で分割する
        		int comma=0;
             	if(line.contains("Condition")){ //condition行true/falseの抜き出し
            		comma = line.indexOf(",");
            		//System.out.println(comma); //1つめの,
            		//System.out.println(line.substring(comma+1, line.indexOf(",", comma+1)));
            		conditionBoolean[currentConditionLine][(int) Math.pow(2, conditionNum)] =
            				line.substring(comma+1, line.indexOf(",", comma+1)); //条件文を最後のスペースに入れとく
            		comma = line.indexOf(",", comma+1);
            		//System.out.println(comma); //2つめの,

                 	line = line.substring(comma);
                 	//System.out.println(line);
                 	int strIndex=1;
                 	for(int i=0; i<(int)Math.pow(2,  conditionNum); i++){
                 		conditionBoolean[currentConditionLine][i] = String.valueOf(line.charAt(strIndex));
                 		//System.out.print(conditionTrueFalse[currentConditionLine][i]+" ");
                 		strIndex+=2;
                 	}
                 	//System.out.println();
                 	currentConditionLine++;
            	}
             	else if(line.contains("Action")){ //action行true/falseの抜き出し
            		comma = line.indexOf(",");
            		actionBoolean[currentActionLine][(int) Math.pow(2, conditionNum)] =
            				line.substring(comma+1, line.indexOf(",", comma+1)); //条件文を最後のスペースに入れとく
            		comma = line.indexOf(",", comma+1);
                 	line = line.substring(comma);
                 	int strIndex=1;
                 	for(int i=0; i<(int)Math.pow(2,  conditionNum); i++){
                 		actionBoolean[currentActionLine][i] = String.valueOf(line.charAt(strIndex));
                 		//System.out.print(actionTrueFalse[currentActionLine][i]+" ");
                 		strIndex+=2;
                 	}
                 	//System.out.println();
                 	currentActionLine++;
            	}

                token = new StringTokenizer(line, ",");
                //分割した文字を画面出力する
                while (token.hasMoreTokens()) {
                	//System.out.println(token.nextToken());
                	token.nextToken();
                }
            }
            //終了処理
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
