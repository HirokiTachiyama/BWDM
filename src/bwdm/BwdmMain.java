package bwdm;

import bwdm.boundaryValueAnalysisUnit.BvaUnitMain;
import bwdm.informationStore.InformationExtractor;
import bwdm.symbolicExecutionUnit.SeUnitMain;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import java.io.IOException;


public class BwdmMain {

	public static void main(String[] args) throws LexException, ParserException, IOException {

		exeBWDM(args);

	}


	static void exeBWDM(String[] args) throws IOException, LexException, ParserException {

		switch(args.length) {
			case 2:
				String vdmPath = args[0];
				String vdmDir = args[1];
				InformationExtractor extractInformation = new InformationExtractor(vdmPath, vdmDir);
				BvaUnitMain bvaUnitMain = new BvaUnitMain(extractInformation);
				SeUnitMain seUnitMain = new SeUnitMain(extractInformation);
				//bvaUnitMain.printAllTestcasesByBv();
				//seUnitMain.printAllTestcasesBySe();
				break;


			case 1:
				switch (args[0]) {
					case "--help":
					case "-h":
						System.out.println("コマンドオプションについて明記");

						break;
					case "--only-BVA":
						System.out.println("境界値分析のみを行う事を明記");

						break;
					case "--only-SE":
						System.out.println("");


					default:
						System.out.println("Usage: bwdm [fileNameOfVdmFile] [/path/to/vdmFile]");
				}


			case 0:
			default:
				System.out.println("Usage: bwdm [fileNameOfVdmFile] [/path/to/vdmFile]");
		}

	}

}
