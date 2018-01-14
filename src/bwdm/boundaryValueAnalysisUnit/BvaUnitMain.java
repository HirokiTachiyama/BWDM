package bwdm.boundaryValueAnalysisUnit;

import bwdm.informationStore.InformationExtractor;

import java.util.ArrayList;
import java.util.HashMap;

public class BvaUnitMain {

	InformationExtractor ie;
	BoundaryValueAnalyzer boundaryValueAnalyzer;
	ExpectedOutputDataGenerator expectedOutputDataGenerator;

	public BoundaryValueAnalyzer getBoundaryValueAnalyzer() { return boundaryValueAnalyzer; }

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

	public String getAllTestcasesByBv() {
		String buf = "";
		ArrayList<String> parameters = ie.getParameters();
		ArrayList<HashMap<String, Long>> inputDataList = boundaryValueAnalyzer.getInputDataList();
		ArrayList<String> expectedOutputDataList = expectedOutputDataGenerator.getExpectedOutputDataList();


		for(int i=0; i<expectedOutputDataList.size(); i++) {
			buf += "No." + (i+1) + " : ";
			HashMap<String, Long> inputData = inputDataList.get(i);
			for(String prm : parameters) {
				buf += inputData.get(prm) + " ";
			}
			buf += "-> " + expectedOutputDataList.get(i) + "\n";
		}

		return buf;
	}



}
