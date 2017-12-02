package bwdm;

import com.fujitsu.vdmj.Settings;
import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.ast.definitions.ASTDefinitionList;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.lex.LexTokenReader;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ParserException;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCExplicitFunctionDefinition;
import com.fujitsu.vdmj.tc.expressions.TCExpression;
import com.fujitsu.vdmj.tc.patterns.TCPatternList;
import com.fujitsu.vdmj.tc.patterns.TCPatternListList;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import com.fujitsu.vdmj.tc.types.TCTypeList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/* information what got from VDM++ specification file by syntax analyse with VDMJ */

class InformationExtractor {

    //argument  実引数
    //parameter 仮引数
    //今回扱うのは仮引数

    //file name and path
    private String directory;//ok
    private String vdmFileName;//ok
    private String vdmFilePath;//ok
    private String decisionTableFileName;//ok
    private String decisionTableFilePath;//ok
    
    //argument types information
    private String argumentTypeBody; //(int,nat,nat1) ok
    private ArrayList<String> argumentTypes; //int, nat, nat1 ok
    private int intNum; //ok
    private int natNum;  //ok
    private int nat1Num;  //ok
    
    //parameter information
    private String parameterBodies; //a*b*c ok
    private ArrayList<String> parameters; //a, b, c ok



    private HashMap ifConditionBodies; //a parameter to ArrayList of if-expressions
	//ArrayList of ifConditions of each parameter
	//ifConditionBodies.get("a") : "4<a", "a<7"
	//ifConditionBodies.get("b") : "-3<b","b>100","0<b"
	//ifConditionBodies.get("c") : "c<10","3<c","c>-29"

	private ArrayList<String> ifConditionBodiesInCameForward;

    private HashMap ifConditions; //a parameter to ArrayList of HashMaps that is parsed each if-expression
	//ArrayList of HashMap of parsed if-expr.
	//ifConditions.get("a") : 'HashMap of 4<a', 'HashMap of a<7'
	//ifConditions.get("b") : 'HashMap of -3<b', 'HashMap of b>100', 'HashMap of 0<b'
	//ifConditions.get("c") : 'HashMap of c<10', 'HashMap of 3<c', 'HashMap of c>-29'

    //事前条件文情報 中身はif条件文情報と似た感じ
    @SuppressWarnings("rawtypes")
    private ArrayList[] preConditionBodies; //事前条件文
    /* 使う前に初期化してから使うこと！*/
    @SuppressWarnings("rawtypes")
    private HashMap[][] preConditions; //条件文を演算子と両辺の3つに分解


    public InformationExtractor(String _vdmFileName, String _decisionTableFileName, String _directory) throws LexException, ParserException {

        /* Initializing fields*/
        vdmFileName = _vdmFileName;
        decisionTableFileName = _decisionTableFileName;
        directory = _directory;
        
        vdmFilePath = directory + vdmFileName;
        decisionTableFilePath = directory + decisionTableFileName; //

		/* variableName = init; example */
		argumentTypeBody = new String(); //int*nat*nat1
		argumentTypes = new ArrayList<String>(); //int, nat, nat1

		parameterBodies = new String(); //a*b*c
		parameters = new ArrayList<String>(); //a, b, c

		ifConditionBodies = new HashMap<String, ArrayList<String>>();
		ifConditionBodiesInCameForward = new ArrayList<String>();
		ifConditions = new HashMap<String, ArrayList<HashMap<String, String>>>();
        /*Done initializing fields*/

		LexTokenReader lexer = new LexTokenReader(new File(vdmFilePath), Dialect.VDM_PP);
		DefinitionReader parser = new DefinitionReader(lexer);
		ASTDefinitionList astDefinitions = parser.readDefinitions();

		astDefinitions.forEach((ASTDefinition astDefinition ) -> {
			if(astDefinition.kind().equals("explicit function")) {
				TCExplicitFunctionDefinition tcFunctionDefinition = null;
				try{
					tcFunctionDefinition = ClassMapper.getInstance(TCDefinition.MAPPINGS).init().convert(astDefinition);
				} catch (Exception e) {
					e.printStackTrace();
				}

				TCFunctionType tcFunctionType = tcFunctionDefinition.type;
				TCTypeList tmp_argumentTypes = tcFunctionType.parameters;
				tmp_argumentTypes.forEach(e -> argumentTypes.add(e.toString()));
				argumentTypeBody = argumentTypes.toString(); //set argumentTypes

				countArgumentTypeNumByKind();


				TCPatternListList tcPatternListList = tcFunctionDefinition.paramPatternList;
				TCPatternList tcPatternList = tcPatternListList.firstElement();
				TCExpression tcExpression = tcFunctionDefinition.body;

				parseIfConditions(tcExpression);
			}
        });

    }

    private void countArgumentTypeNumByKind() {
    	argumentTypes.forEach(at -> {
    		if(at.toString().equals("int")) {
    			intNum++;
			}
    		else if(at.toString().equals("nat")) {
    			natNum++;
			}
			else if(at.toString().equals("nat1")) {
    			nat1Num++;
			}
		});
	}

	//ArrayList of ifConditions of each parameter
	//ifConditionBodies.get("a") : "4<a", "a<7"
	//ifConditionBodies.get("b") : "-3<b","b>100","0<b"
	//ifConditionBodies.get("c") : "c<10","3<c","c>-29"
	//ArrayList of HashMap of parsed if-expr.
	//ifConditions.get("a") : 'HashMap of 4<a', 'HashMap of a<7'
	//ifConditions.get("b") : 'HashMap of -3<b', 'HashMap of b>100', 'HashMap of 0<b'
	//ifConditions.get("c") : 'HashMap of c<10', 'HashMap of 3<c', 'HashMap of c>-29'
	private void parseIfConditions(TCExpression _tcExpression) {
		System.out.println("parsing of if-expr.");
		//ifConditionBodies : HashMap< String, ArrayList<String> >
		//ifConditionBodiesInCameForward : ArrayList<String, String>
		//ifConditions : HashMap< String, ArrayList< HashMap< String, String > > >

//		(if ((a < 5) and (-4 < a))
//		then (if (a > 0)
//		        then "0 < a < 5"
//		      else "a < 0")
//else (if (12 <= a)
//			then (if (a < 20)
//			then "12 <= a < 20"
//else "20 <= a")
//else "others"))

		System.out.println(_tcExpression.toString());


	}


	/*
 * if_elseファイル生成
 * 最初のifから終わりのセミコロンまでを抜き出す
 */
	private static void if_elseFileGenerate() throws LexException, ParserException, IOException {
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
