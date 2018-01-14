package bwdm.boundaryValueAnalysisUnit;

import bwdm.informationStore.InformationExtractor;

import java.util.ArrayList;
import java.util.HashMap;

public class BvaUnitMain {

	InformationExtractor ie;
	BoundaryValueAnalyzer boundaryValueAnalyzer;
	ExpectedOutputDataGenerator expectedOutputDataGenerator;


	public BvaUnitMain(InformationExtractor _ie) {
		this.ie = _ie;
		boundaryValueAnalyzer = new BoundaryValueAnalyzer(_ie);
		expectedOutputDataGenerator =
				new ExpectedOutputDataGenerator(
						_ie,
						_ie.getIfElseExprSyntaxTree().getRoot(),
						boundaryValueAnalyzer.getInputDataList()
				);

	}

	public void printAllTestcasesByBv() {
		System.out.println("Testcases by Boundary Value Analysis");
		ArrayList<String> parameters = ie.getParameters();
		ArrayList<HashMap<String, Long>> inputDataList = boundaryValueAnalyzer.getInputDataList();
		ArrayList<String> expectedOutputDataList = expectedOutputDataGenerator.getExpectedOutputDataList();

		System.out.print("parameters:");
		for(String prm : parameters) {
			System.out.print(prm + " ");
		}
		System.out.println();

		for(int i=0; i<expectedOutputDataList.size(); i++) {
			System.out.print("Testcase No." + i + " : ");
			HashMap<String, Long> inputData = inputDataList.get(i);
			for(String prm : parameters) {
				System.out.print(inputData.get(prm) + " ");
			}
			System.out.println("-> " + expectedOutputDataList.get(i));
		}
	}



}
