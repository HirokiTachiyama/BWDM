package bwdm;

import bwdm.boundaryValueAnalysisUnit.BvaUnitMain;
import bwdm.informationStore.InformationExtractor;
import bwdm.symbolicExecutionUnit.SeUnitMain;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import java.io.IOException;


public class BwdmMain {

	public static void main(String[] args) throws LexException, ParserException, IOException {

		InformationExtractor extractInformation = new InformationExtractor(args[0], args[1]);

		BvaUnitMain bvaUnitMain = new BvaUnitMain(extractInformation);
		bvaUnitMain.printAllTestcasesByBv();

		SeUnitMain seUnitMain = new SeUnitMain(extractInformation);
		seUnitMain.printAllTestcasesBySe();



	}


	void outputTestcases() {
		//todo テストケース出力処理！
	}

}
