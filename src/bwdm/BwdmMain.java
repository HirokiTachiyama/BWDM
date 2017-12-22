package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.util.ArrayList;
import java.util.HashMap;

public class BwdmMain {

	public static void main(String[] args) throws LexException, ParserException {

		InformationExtractor extractInformation = new InformationExtractor(args[0], args[1]);
		IfElseExprSyntaxTreeGenerator ifElseExprSyntaxTreeGenerator = extractInformation.getIfElseExprSyntaxTreeGenerator();
		ifElseExprSyntaxTreeGenerator.printAllNodes();

		BoundaryValueAnalyzer boundaryValueAnalyzer = new BoundaryValueAnalyzer(extractInformation);
		boundaryValueAnalyzer.printAllInputValue();

		ArrayList<String> parameters
				= extractInformation.getParameters();
		ArrayList<HashMap<String, Long>> inputDataList
				= boundaryValueAnalyzer.getInputDataList();



	}
}
