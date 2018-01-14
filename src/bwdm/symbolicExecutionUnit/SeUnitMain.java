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

	public String getAllTestcasesBySe() {
		String buf = "";

		ArrayList<String> parameters = ie.getParameters();
		ArrayList<HashMap<String, String>> inputDataList = se.getInputDataList();
		ArrayList<String> expectedOutputDataList = se.getExpectedOutputDataList();

		for(int i=0; i<expectedOutputDataList.size(); i++) {
			buf += "No." + (i+1) + " : ";
			HashMap<String, String> inputData = inputDataList.get(i);
			for(String prm : parameters) {
				buf += inputData.get(prm) + " ";
			}
			 buf += "-> " + expectedOutputDataList.get(i) + "\n";
		}
		return buf;
	}

	public SymbolicExecutioner getSe() { return se; }
}
