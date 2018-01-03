package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.util.ArrayList;
import java.util.HashMap;

public class BwdmMain {

	public static void main(String[] args) throws LexException, ParserException {

		InformationExtractor extractInformation = new InformationExtractor(args[0], args[1]);
		IfElseExprSyntaxTreeGenerator ifElseExprSyntaxTreeGenerator = extractInformation.getIfElseExprSyntaxTreeGenerator();
		ArrayList<String> parameters = extractInformation.getParameters();
		IfNode root = ifElseExprSyntaxTreeGenerator.getRoot();
		//ifElseExprSyntaxTreeGenerator.printAllNodes();

		BoundaryValueAnalyzer boundaryValueAnalyzer = new BoundaryValueAnalyzer(extractInformation);
		//boundaryValueAnalyzer.printAllInputValue();


		ArrayList<HashMap<String, Long>> inputDataList = boundaryValueAnalyzer.getInputDataList();

		ConditionAndReturnValueList conditionAndReturnValueList = new ConditionAndReturnValueList(root);
		conditionAndReturnValueList.printAllConditionAndBoolAndReturn();

		ExpectedOutputDataGenerator expectedOutputDataGenerator =
				new ExpectedOutputDataGenerator(root, parameters, inputDataList);

		System.out.println();

	}
}
