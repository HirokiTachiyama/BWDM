package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.util.ArrayList;
import java.util.HashMap;

public class BwdmMain {

	public static void main(String[] args) throws LexException, ParserException {

		InformationExtractor extractInformation = new InformationExtractor(args[0], args[1]);
		IfElseExprSyntaxTree ifElseExprSyntaxTree = extractInformation.getIfElseExprSyntaxTree();
		ifElseExprSyntaxTree.printAll();

		BoundaryValueAnalyzer boundaryValueAnalyzer = new BoundaryValueAnalyzer(extractInformation);

		ArrayList<String> parameters
				= extractInformation.getParameters();
		ArrayList<HashMap<String, Long>> inputDataList
				= boundaryValueAnalyzer.getInputDataList();

		inputDataList.forEach(inputData -> {
			parameters.forEach(p -> {
				System.out.print(inputData.get(p) + ", ");
			});
			System.out.println();
		});

	}
}
