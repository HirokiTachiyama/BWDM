package past_BWDM_classes.bwdm;



import com.fujitsu.vdmj.Settings;
import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ParserException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnalyzedData {

	//ファイル名、パス
	private static String directory=null;
	private static String vdmFileName=null;
	private static String vdmFilePath=null;
	private static String csvFileName=null;
	private static String csvFilePath=null;

	//型情報
	private static String argumentTypesJoined=null;    //引数型のばらす前 int*nat*nat1
	private static ArrayList<String> argumentTypes;    //引数型 int nat nat1...
	private static int intNum=0, natNum=0, nat1Num=0; //それぞれの型の数

	//仮引数情報
	private static String formalArgumentsJoined=null; //仮引数のばらす前 a,b,c
	private static ArrayList<String> formalArguments; //仮引数 a b c...

	//if条件文情報
	//上から仮引数1つ目、2つ目...で格納してる
	//ifConditionsJoined = {"4<a", "a<7",
	//                      "-3<b","b>100","0<b",
	//                      "c<10","3<c","c>-29","c<-40"};
	//という感じ

	@SuppressWarnings("rawtypes")
	private static ArrayList[] ifConditionsJoined;
	//入力値をifConditionsについてtrue/false判定するときに
	//条件文の来た順が必要だった(吉川ツールが来た順に並んでるため)
	private static ArrayList<String> ifConditionsJoinedInCameForward;
	@SuppressWarnings("rawtypes")
	private static HashMap[][] ifConditions; //条件文を演算子と両辺の3つに分解
	//ifConditions = {"4", "<a", "a", "<", "7"
	//                       "-3", "<", "b", "b", ">", "100","0", "<", "b",
	//                       "c", "<", "10","3", "<", "c","c", ">", "-29","c", "<" ,"-40"};

	//事前条件文情報 中身はif条件文情報と似た感じ
	@SuppressWarnings("rawtypes")
	private static ArrayList[] preConditionsJoined; //事前条件文
	/* 使う前に初期化してから使うこと！*/
	@SuppressWarnings("rawtypes")
	private static HashMap[][] preConditions; //条件文を演算子と両辺の3つに分解

	public AnalyzedData(String _vdmFile, String _csvFile, String _directory) throws LexException, ParserException {
		vdmFileName = _vdmFile;
		csvFileName = _csvFile;
		directory   = _directory;
		vdmFilePath = directory + vdmFileName;
		csvFilePath = directory + csvFileName;
		//System.out.println(vdmFilePath);
		//System.out.println(csvFilePath);

		//アトリビュートの初期化
		//但し
		initialize();

		//仮引数情報の取得
		setFormalArgsJoined();
		setFormalArguments();

		//if文＆事前条件文情報の取得
		//if条件文情報の初期化及び取得
		initializeConditionsJoined();
		setConditionsJoined();

		initializeConditions();
		setArgumentTypesJoined();
		setArgumentTypes();

		parseConditions("if");

		ifConditionsDeleteOverlapped();

		//parseConditions("pre"); //事前条件は未実装

	}


	private void initialize(){
		argumentTypes = new ArrayList<String>();    //引数型 int nat nat1...
		formalArguments = new ArrayList<String>(); //仮引数 a b c...
	}

	private void initializeConditionsJoined(){
		//配列の個数は仮引数の分だけある

		ifConditionsJoined = new ArrayList[formalArguments.size()];
		preConditionsJoined =  new ArrayList[formalArguments.size()];
		//System.out.println(formalArguments.size());
		for(int i=0; i<formalArguments.size(); i++){
			ifConditionsJoined[i] = new ArrayList<String>();
			preConditionsJoined[i] = new ArrayList<String>();
		}
	}

	private void initializeConditions(){
		ifConditions = new HashMap[formalArguments.size()][];
		preConditions = new HashMap[formalArguments.size()][];
		for(int i=0; i<ifConditionsJoined.length; i++){
			ifConditions[i] = new HashMap[ifConditionsJoined[i].size()];
			preConditions[i] = new HashMap[preConditionsJoined[i].size()];
			for(int j=0; j<ifConditionsJoined[i].size(); j++){
				ifConditions[i][j] = new HashMap<String, String>();
			}
			for(int j=0; j<preConditionsJoined[i].size(); j++){
				preConditions[i][j] = new HashMap<String, String>();
			}
		}
	}


	private void setArgumentTypesJoined() throws ParserException, LexException{
		LexTokenReader ltr = new LexTokenReader(new File(vdmFilePath), Settings.dialect);
		DefinitionReader dr = new DefinitionReader(ltr);
		Iterator<Definition> it = dr.readDefinitions().iterator();
		it.forEachRemaining(d -> {
			if(d.kind().equals("explicit function")){
				argumentTypesJoined =
						d.getType().toString().substring(1, d.getType().toString().indexOf("->")).replaceAll(" ", "");
			}
		});
	}

	//字句解析を二回行い、仮引数列を取り出す
	private void setFormalArgsJoined() throws LexException {
		String formalArgs = "";
		Settings.dialect = Dialect.VDM_PP;
		//一回目のvdmファイル読み込み
		//関数名を読み込む

		LexTokenReader ltr_first = null;

		ltr_first = new LexTokenReader(new File(vdmFilePath), Settings.dialect);

		String functionName=null;
		while(ltr_first.getLast().toString() != "end" ){ //class定義終了まで
			String currentToken = ltr_first.nextToken().toString();
			if(currentToken.equals("functions")){//関数定義が来たら
				String tmp = ltr_first.nextToken().toString();
				//System.out.println(tmp);
				if( tmp.equals("public") || tmp.equals("private") || tmp.equals("static")){
					tmp = ltr_first.nextToken().toString();
					//System.out.println(tmp);
					if( tmp.equals("public") || tmp.equals("private") || tmp.equals("static")){
						functionName = ltr_first.nextToken().toString();
						//System.out.println("IF"+functionName);
					} else {
						functionName = tmp;
						//System.out.println("ELSE:"+functionName);
					}
				} else {
					functionName = tmp;
				}
			}
		}
		ltr_first.close();
		//二回目のvdmファイル読み込み
		//一回目の読み込みで見つけた関数名が
		//二回目に登場した直後に仮引数が来る



		LexTokenReader ltr_second = new LexTokenReader(new File(vdmFilePath), Settings.dialect);
		int functionAppearTimes = 0;
		while(ltr_second.getLast().toString() != "end" ){ //class定義終了まで
			String currentToken = ltr_second.nextToken().toString();
			if(currentToken.equals(functionName)){//関数定義が来たら
				functionAppearTimes++;
			}
			if(functionAppearTimes == 2){
				while(ltr_second.getLast().toString() != ")"){//thenが来るまでの間
					formalArgs = formalArgs + ltr_second.nextToken().toString();
				}
				functionAppearTimes++;
			}
		}
		ltr_second.close();
		formalArgumentsJoined = formalArgs.replace("(", "").replace(")", "");

	}


	//*Joinedを受け取ってargumentTypesをセットする人
	private void setArgumentTypes(){

		if(argumentTypesJoined.equals("()")) { //()ならすぐおしまい
			//argumentTypes.add(0, "()");
			//return;
			System.out.println("引数0個です。処理を終了します。境界値テスト必要ですか？");
			System.exit(-1);
		} else if (argumentTypesJoined.indexOf("*")  != -1){ //引数が複数個の場合
			//*と,を入れ替えて,で分ける *のままだと正規表現の関係で上手く行かなかった
			String[] splitArgs = argumentTypesJoined.replace("*", ",").split(",");
			for(String splitArg : splitArgs){
				argumentTypes.add(splitArg);
				//System.out.print(splitArg+" ");
			}
		}else{ //引数が１つの場合
			argumentTypes.add(0, argumentTypesJoined.replace("(", "").replace(")", ""));
		}
		intNum  = getArgumentsNumberByKind(argumentTypesJoined, "int");
		natNum  = getArgumentsNumberByKind(argumentTypesJoined, "nat");
		nat1Num = getArgumentsNumberByKind(argumentTypesJoined, "nat1");
		if(natNum != 0){
			natNum -= nat1Num;
		}
	}

	//*Joinedを受け取ってformalArgumentsをセットする人
	private void setFormalArguments(){
		if(formalArgumentsJoined.equals("()")) { //()ならすぐおしまい
			formalArguments.add(0, "NONE");
			return;
		} else if(formalArgumentsJoined.indexOf(",")  != -1){ //引数が複数個の場合
			String[] splitArgs = formalArgumentsJoined.split(",");//,で分ける
			for(String splitArg : splitArgs){
				formalArguments.add(splitArg);
				//System.out.print(splitArg+" ");
			}
		}else{ //引数が１つの場合
			formalArguments.add(0, formalArgumentsJoined.replace("(", "").replace(")", ""));
			return;
		}
	}

	private int getArgumentsNumberByKind(String _argument, String _kind) {
		int num=0, s=0;
		Pattern p = Pattern.compile(_kind);
		Matcher m = p.matcher(_argument);
		while(m.find(s)){
			num++;
			s = m.end();
		}
		return num;
	}

	//if条件文と事前条件文を入れる
	//どちらも条件文が()で囲まれてないといけない
	@SuppressWarnings("unchecked")
	private void setConditionsJoined() throws ParserException, LexException {

		ArrayList<String> ifConditionsJoinedTmp = new ArrayList<String>();
		ArrayList<String> preConditionsjoinedTmp = new ArrayList<String>();

		Settings.dialect = Dialect.VDM_PP;
		@SuppressWarnings("resource")
		LexTokenReader ltr = new LexTokenReader(new File(vdmFilePath), Settings.dialect);

		while( !(ltr.getLast().toString().equals("end")) ){ //class定義終了まで
			String currentToken = ltr.nextToken().toString();
			String tmpStr;
			//System.out.println(currentToken);
			if(currentToken.equals("if") || currentToken.equals("elseif")){ //ifかelseifが来たら
				tmpStr = new String();
				while(ltr.getLast().toString() != ")"){//thenが来るまでの間
					tmpStr = tmpStr + ltr.nextToken().toString();
				}
				if(tmpStr.contains("or") || tmpStr.contains("and")){
					System.out.println("複合if条件式については未対応です。悪しからず。");
					System.exit(-1);
				}
				ifConditionsJoinedTmp.add(tmpStr.replace("(", "").replace(")", ""));
				//System.out.println(tmpStr);
			}
			else if(currentToken.equals("pre")){ //preが来たら
				tmpStr = new String();
				while(ltr.getLast().toString() != ")"){//thenが来るまでの間
					tmpStr = tmpStr + ltr.nextToken().toString();
				}
				if(tmpStr.contains("or") || tmpStr.contains("and")){
					System.out.println("複合if条件式については未対応です。悪しからず。");
					System.exit(-1);
				}
				preConditionsjoinedTmp.add(tmpStr.replace("(", "").replace(")", ""));
			}
		}




		/*
		 *
		 * if条件文が被ってたらそれを消す処理
		 *
		 */

		ifConditionsJoinedInCameForward = ifConditionsJoinedTmp;
		//for(String str : ifConditionsJoinedInCameForward){
		//	for(int i=0; i<ifConditionsJoinedInCameForward.size(); i++){
		//	}
		//}




		//仮引数毎に整理してifConditionJoined[n]に入れる
		ArrayList<String> fa = AnalyzedData.getFormalArguments();
		for(int i=0; i<ifConditionsJoinedTmp.size(); i++){
			for(int j=0; j<fa.size(); j++){
				//System.out.println(ifConditionsJoinedTmp.get(i)+" "+fa.get(j));
				if(ifConditionsJoinedTmp.get(i).contains(fa.get(j))){
					ifConditionsJoined[j].add(ifConditionsJoinedTmp.get(i).toString());
				}
				/*
				 * 事前条件はオミット
				if(preConditionsjoinedTmp.get(i).toString().matches(".*"+fa.get(j)+"*.")){
					System.out.println(preConditionsjoinedTmp.get(i).toString()+" is has "+ fa.get(j));
					ifConditionsJoined[j].add(preConditionsjoinedTmp.get(i).toString());
				}
				*/
			}
		}

	}


	@SuppressWarnings({ "unchecked" })
	private void parseConditions(String ifOrPre) {
		if(ifOrPre.equals("if")){
			for(int i=0; i<ifConditions.length; i++){
				for(int j=0; j<ifConditionsJoined[i].size(); j++){
					if(ifConditionsJoined[i].get(j).toString().contains("mod")){ //mod用の分割処理
						int index = ifConditionsJoined[i].get(j).toString().indexOf("mod");
						int indexOfEqual = ifConditionsJoined[i].get(j).toString().indexOf("=");
						ifConditions[i][j].put("LeftHand", ifConditionsJoined[i].get(j).toString().substring(0, index));
						ifConditions[i][j].put("Symbol", "mod");
						//System.out.println(ifConditions[i][j].get("Symbol").toString() + "  " + indexOfEqual);
						ifConditions[i][j].put("RightHand", ifConditionsJoined[i].get(j).toString().substring(index + ifConditions[i][j].get("Symbol").toString().length(), indexOfEqual));
						ifConditions[i][j].put("Surplus", ifConditionsJoined[i].get(j).toString().substring(indexOfEqual+1));
					} else {

						//System.out.print(getLeftHandString(ifConditionsJoined[i].get(j).toString()));
						//System.out.print(getSymbol(ifConditionsJoined[i].get(j).toString()));
						ifConditions[i][j].put("LeftHand", getLeftHandString(ifConditionsJoined[i].get(j).toString()));
						ifConditions[i][j].put("Symbol", getSymbol(ifConditionsJoined[i].get(j).toString()));
						ifConditions[i][j].put("RightHand", getRightHandString(ifConditionsJoined[i].get(j).toString(), "if", i, j));

						//System.out.println(getRightHandString(ifConditionsJoined[i].get(i).toString(), "if", i, j));
					}
				}
			}

		} else {
			for(int i=0; i<preConditions.length; i++){
				for(int j=0; j<preConditionsJoined[i].size(); j++){
					preConditions[i][j].put("LeftHand", getLeftHandString(preConditionsJoined[i].get(j).toString()));
					preConditions[i][j].put("Symbol", getSymbol(preConditionsJoined[i].get(j).toString()));
					preConditions[i][j].put("RightHand", getRightHandString(preConditionsJoined[i].get(j).toString(), "if", i, j));
				}
			}
		}
	}

	private String getSymbol(String condition){
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

	private String getLeftHandString(String condition){
		int index=0;
		if(condition.indexOf("<=") != -1){
			index = condition.indexOf("<=");
		}else if(condition.indexOf(">=") != -1){
			index = condition.indexOf(">=");
		}else if(condition.indexOf("<") != -1){
			index = condition.indexOf("<");
		}else if(condition.indexOf(">") != -1){
			index = condition.indexOf(">");
		}
		return condition.substring(0, index);
	}

	private String getRightHandString(String condition, String ifOrPre,
			int conditionLinePosition, int conditionColumnPosition){
		int index=0;

		if(condition.indexOf("<=") != -1){
			index = condition.indexOf("<=");
		}else if(condition.indexOf(">=") != -1){
			index = condition.indexOf(">=");
		}else if(condition.indexOf("<") != -1){
			index = condition.indexOf("<");
		}else if(condition.indexOf(">") != -1){
			index = condition.indexOf(">");
		}
		if(ifOrPre.equals("if")){

			return condition.substring(index +
					ifConditions[conditionLinePosition][conditionColumnPosition].get("Symbol").toString().length());
		} else {
			return condition.substring(index +
					preConditions[conditionLinePosition][conditionColumnPosition].get("Symbol").toString().length());
		}
	}


	public static void printInformation(){
		System.out.println("Files information");
		System.out.println("vdmFilePath:" + vdmFilePath);
		System.out.println("csvFilePath:" + csvFilePath+"\n");
		System.out.println("Type information.");
		System.out.println("There are "+argumentTypes.size() +" argumentTypes");
		System.out.println("argumentTypesJoined:" + argumentTypesJoined);
		System.out.print("argumentTypes:");
		for(int i=0; i<argumentTypes.size(); i++){
			System.out.print(argumentTypes.get(i)+", ");
		}
		System.out.println("\nintNum:"+intNum+", natNum:"+natNum+", nat1Num:"+nat1Num);

		System.out.println("\nFormalArgument information.");
		System.out.println("There are "+formalArguments.size() +" formalArguments");
		System.out.println("formalArgumentsJoined:" + formalArgumentsJoined);
		System.out.print("formalArguments:");
		for(int i=0; i<formalArguments.size(); i++){
			System.out.print(formalArguments.get(i)+", ");
		}

		System.out.println("\n\nIfCondition information.");
		System.out.print("ifConditionsJoinedInCameForward:");
		for(String conditionStr : ifConditionsJoinedInCameForward){
			System.out.print(conditionStr+", ");
		}
		System.out.println("\nifConditionsJoined:");
		for(int i=0; i<ifConditionsJoined.length; i++){
			System.out.print("formalArgment:"+formalArguments.get(i)+" -> ");
			for(int j=0; j<ifConditionsJoined[i].size(); j++){
				System.out.print(ifConditionsJoined[i].get(j)+", ");
			}
			System.out.println();
		}
		System.out.print("ifConditions:\n");

		for(int i=0; i<ifConditions.length; i++){
			System.out.print("formalArgment:"+formalArguments.get(i)+" -> ");
			for(int j=0; j<ifConditions[i].length; j++){

				if(ifConditions[i][j].get("Symbol").equals("mod")){
					System.out.print(ifConditions[i][j].get("LeftHand")+", "+ifConditions[i][j].get("Symbol")+
							", "+ifConditions[i][j].get("RightHand")+", "+ifConditions[i][j].get("Surplus"));
				} else {
					System.out.print(ifConditions[i][j].get("LeftHand")+", "+
							ifConditions[i][j].get("Symbol")+", "+ifConditions[i][j].get("RightHand")+", ");
				}
			}
			System.out.println();
		}
		/* 事前条件はまだ封印
		System.out.println("\npreCondition information.");
		System.out.print("preConditionsJoined:");
		for(int i=0; i<preConditions.length; i++){ //ifConditionsはジャグ配列 注意！
			for(int j=0; j<ifConditions[i].length; j++){
				System.out.print(ifConditions[i][j]+" ");
			}
		}
		 */
	}

	private static void ifConditionsDeleteOverlapped(){
		/*
		 * ifConditionsJoined
		 * ifConditionsJoinedInCameForward
		 * ifConditions
		 * から重複しているif条件文を削除する
		 */

	}

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

    public static String getSpecificationAllText() throws FileNotFoundException, IOException{
    	StringBuilder builder = new StringBuilder();

    	try (BufferedReader reader = new BufferedReader(new FileReader(vdmFilePath))) {
    		String string = reader.readLine();
    		while (string != null){
    			builder.append(string + System.getProperty("line.separator"));
    			string = reader.readLine();
    		}
    	}
    	return builder.toString();
    }

	public static String getVdmFilePath(){ return vdmFilePath; }
	public static void printVdmFileText(){
		try {
			System.out.println( Files.lines(Paths.get(vdmFilePath), Charset.forName("UTF-8"))
					.collect(Collectors.joining(System.getProperty("line.separator"))) );
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public static String getCsvFilePath(){ return csvFilePath; }


	public static String getArgumentTypesJoined(){ return argumentTypesJoined; }
	public static ArrayList<String> getArgumentTypes(){ return argumentTypes; }
	public static int getIntNum() { return intNum; }
	public static int getNatNum() { return natNum; }
	public static int getNat1Num() { return nat1Num; }
	public static String getFormalArgumentsJoined() { return formalArgumentsJoined; }
	public static ArrayList<String> getFormalArguments(){ return formalArguments; }
	public static ArrayList<?>[] getIfConditionsJoined(){ return ifConditionsJoined; }
	public static ArrayList<String> getIfConditionsJoinedInCameForward(){
	    return ifConditionsJoinedInCameForward;
	}
	@SuppressWarnings("rawtypes")
	public static HashMap[][] getIfConditions(){ return ifConditions; }
	@SuppressWarnings("rawtypes")
	public static ArrayList[] getPreConditionsJoined() { return preConditionsJoined; }
	@SuppressWarnings("rawtypes")
	public static HashMap[][] getPreConditions(){ return preConditions; }

}
