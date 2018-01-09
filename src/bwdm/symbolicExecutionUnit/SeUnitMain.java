package bwdm.symbolicExecutionUnit;

import bwdm.informationStore.InformationExtractor;

import java.util.ArrayList;
import java.util.HashMap;

public class SeUnitMain {
	InformationExtractor ie;
	SymbolicExecutioner se;
	public SeUnitMain(InformationExtractor _ie) {
		this.ie = _ie;
		se = new SymbolicExecutioner(ie);
	}

	public void printAllTestcasesBySe() {
		ArrayList<String> parameters = ie.getParameters();
		ArrayList<HashMap<String, String>> inputDataList = se.getInputDataList();
		ArrayList<String> expectedOutputDataList = se.getExpectedOutputDataList();

		System.out.print("parameters:");
		for(String prm : parameters) {
			System.out.print(prm + " ");
		}
		System.out.println();

		for(int i=0; i<expectedOutputDataList.size(); i++) {
			System.out.println("Testcase No." + i);
			HashMap<String, String> inputData = inputDataList.get(i);
			for(String prm : parameters) {
				System.out.print(inputData.get(prm) + " ");
			}
			System.out.println("-> " + expectedOutputDataList.get(i));
		}

	}
}
