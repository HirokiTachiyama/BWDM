package bwdm.informationStore;

import bwdm.Util;
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
import com.fujitsu.vdmj.tc.patterns.TCIdentifierPattern;
import com.fujitsu.vdmj.tc.patterns.TCPatternList;
import com.fujitsu.vdmj.tc.patterns.TCPatternListList;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import com.fujitsu.vdmj.tc.types.TCTypeList;

import java.io.File;
import java.io.IOException;
import java.util.*;


/* information what got from VDM++ specification file by syntax analyse with VDMJ */

public class InformationExtractor {

	private ConditionAndReturnValueList conditionAndReturnValueList;
	private IfElseExprSyntaxTree ifElseExprSyntaxTree;

    //argument  実引数
    //parameter 仮引数
    //今回扱うのは仮引数

    //file name and path
	private String vdmFileName;
	private String vdmFilePath;

	//function name
	private String functionName;

    //argument types information
    private String argumentTypeBody; //(int,nat,nat1)
    private ArrayList<String> argumentTypes; //int, nat, nat1
    private int intNum;
    private int natNum;
    private int nat1Num;
    
    //parameter information
    private String parameterBodies; //a*b*c
	private ArrayList<String> parameters; //a, b, c

	//type of return value
	private String returnValue;


	private String ifExpressionBody;

    private HashMap<String, ArrayList<String>> ifConditionBodies;
    //a parameter to ArrayList of if-conditions
	//ArrayList of ifConditions of each parameter
	//ifConditionBodies.get("a") : "4<a", "a<7"
	//ifConditionBodies.get("b") : "-3<b","b>100","0<b"
	//ifConditionBodies.get("c") : "c<10","3<c","c>-29"

	private ArrayList<String> ifConditionBodiesInCameForward;


	private HashMap<String, ArrayList<HashMap<String, String>>> ifConditions;
	//a parameter to ArrayList of HashMaps that is parsed each if-expression
	//ArrayList of HashMap of parsed if-expr.
	//ifConditions.get("a") : 'HashMap of 4<a', 'HashMap of a<7'
	//ifConditions.get("b") : 'HashMap of -3<b', 'HashMap of b>100', 'HashMap of 0<b'
	//ifConditions.get("c") : 'HashMap of c<10', 'HashMap of 3<c', 'HashMap of c>-29'


	public InformationExtractor(String _vdmFilePath)
			throws LexException, ParserException, IOException {

        /* Initializing fields*/
		vdmFilePath = _vdmFilePath;
		File vdmFile = new File(vdmFilePath);
		vdmFileName = vdmFile.getName();

		/* variableName = init; example */
		argumentTypeBody = new String(); //int*nat*nat1
		argumentTypes = new ArrayList<String>(); //int, nat, nat1

		parameterBodies = new String(); //a*b*c
		parameters = new ArrayList<String>(); //a, b, c

		ifExpressionBody = new String();
		ifConditionBodies = new HashMap();
		ifConditionBodiesInCameForward = new ArrayList<String>();
		ifConditions = new HashMap();
        /*Done initializing fields*/

		LexTokenReader lexer = new LexTokenReader(vdmFile, Dialect.VDM_PP);
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
				functionName = tcFunctionDefinition.name.getName();
				returnValue = tcFunctionDefinition.type.result.toString();
				//returnValue.replaceAll((,"").replaceAll(")","");
				TCFunctionType tcFunctionType = tcFunctionDefinition.type;
				TCTypeList tmp_argumentTypes = tcFunctionType.parameters;
				TCExpression tcExpression = tcFunctionDefinition.body;
				ifExpressionBody = tcExpression.toString();
				tmp_argumentTypes.forEach(e -> argumentTypes.add(e.toString()));
				argumentTypeBody = argumentTypes.toString(); //set argumentTypes

				countArgumentTypeNumByKind();

				try {
					ifElseExprSyntaxTree = new IfElseExprSyntaxTree(ifExpressionBody);
				} catch (ParserException e) {
					e.printStackTrace();
				} catch (LexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				//parsing for parameters
				TCPatternListList tcPatternListList = tcFunctionDefinition.paramPatternList;
				TCPatternList tcPatternList = tcPatternListList.firstElement();
				for(int i=0; i<tcPatternList.size(); i++) {
					TCIdentifierPattern tcIdentifierPattern =
							(TCIdentifierPattern) tcPatternList.get(i);
					String parameter = tcIdentifierPattern.toString();
					parameters.add(parameter);
				}

				parseIfConditions();

			}
        });

		ifElseExprSyntaxTree =
				new IfElseExprSyntaxTree(ifExpressionBody);

		conditionAndReturnValueList =
				new ConditionAndReturnValueList(ifElseExprSyntaxTree.root, this);

	}

	private void countArgumentTypeNumByKind() {
		argumentTypes.forEach(at -> {
			if(at.toString().equals("int"))       intNum++;
			else if(at.toString().equals("nat"))  natNum++;
			else if(at.toString().equals("nat1")) nat1Num++;
		});
	}


	private void parseIfConditions() {
		List<String> ifElses = ifElseExprSyntaxTree.ifElses;

		for(int i=0; i<ifElses.size(); i++) {
			String element = ifElses.get(i);
			if(element.equals("if")) {
				element = ifElses.get(i + 1);
				ifConditionBodiesInCameForward.add(element);
			}
		}

		//initializing of collection instances of each parameter
		parameters.forEach(s -> {
			ifConditionBodies.put(s, new ArrayList<String>());
			ifConditions.put(s, new ArrayList<HashMap<String, String>>());
		});

		//parsing of each if-condition, and store in ifConditions
		ifConditionBodiesInCameForward.forEach(condition -> {
			parameters.forEach(parameter -> {
				if (condition.contains(parameter)) {
					parse(condition, parameter);
				}
			});
		});
	}

	private void parse(String condition, String parameter) {
		ArrayList al = ifConditionBodies.get(parameter);
		al.add(condition);

		String operator = Util.getOperator(condition);
		int indexOfoperator = condition.indexOf(operator);
		HashMap hm = new HashMap<String, String>();
		hm.put("left", condition.substring(0, indexOfoperator));
		hm.put("operator", operator);

		//right-hand and surplus need branch depending on mod or other.
		if(operator.equals("mod")) {
			int indexOfEqual = condition.indexOf("=");
			hm.put("right", condition.substring(indexOfoperator+3, indexOfEqual));
			hm.put("surplus", condition.substring(indexOfEqual+1));
		} else {
			hm.put("right", condition.substring(indexOfoperator + operator.length()));
		}

		al = ifConditions.get(parameter);
		al.add(hm);
    }

	public IfElseExprSyntaxTree getIfElseExprSyntaxTree() {
    	return ifElseExprSyntaxTree;
	}
	public ArrayList<String> getParameters() {
		return parameters;
	}
	public ArrayList<String> getArgumentTypes() {
		return argumentTypes;
	}
	public HashMap getIfConditions() {
		return ifConditions;
	}
	public ConditionAndReturnValueList getConditionAndReturnValueList() {
		return conditionAndReturnValueList;
	}
	public String getFunctionName() { return functionName; }
	public String getVdmFileName() { return vdmFileName; }
	public String getVdmFilePath() { return vdmFilePath; }
	public String getReturnValue() { return returnValue; }
}
