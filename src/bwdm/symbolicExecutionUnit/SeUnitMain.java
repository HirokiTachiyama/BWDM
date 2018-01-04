package bwdm.symbolicExecutionUnit;

import bwdm.informationStore.InformationExtractor;

public class SeUnitMain {
	InformationExtractor ie;

	public SeUnitMain(InformationExtractor _ie) {
		this.ie = _ie;
		SymbolicExecutioner se = new SymbolicExecutioner(ie);
	}

	public void printAllTestcasesBySe() {

	}
}
