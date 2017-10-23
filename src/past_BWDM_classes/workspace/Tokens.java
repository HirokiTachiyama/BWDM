package past_BWDM_classes.workspace;

import bwdm.AnalyzedData;
import org.overturetool.vdmj.lex.LexException;
import org.overturetool.vdmj.syntax.ParserException;

public class Tokens {

	public static void main(String[] args) throws LexException, ParserException {
		// TODO 自動生成されたメソッド・スタブ
		String kikkawa = "KikkawaToolAndExampleData/";
		new AnalyzedData(kikkawa+"data/mod2.vdmpp",
				kikkawa+"data/mod2.csv", "");
		AnalyzedData.printInformation();
		AnalyzedData.printVdmFileText();

		
		
	}

}
