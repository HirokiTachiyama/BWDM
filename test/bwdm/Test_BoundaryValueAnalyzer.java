package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Test_BoundaryValueAnalyzer {


	@Test
	void test() throws LexException, ParserException {

		InformationExtractor information = new InformationExtractor(
				"Arg2_Japanese.vdmpp",
				"Arg2_Japanse.vdmpp",
				"./vdm_files/");
		BoundaryValueAnalyzer bvAnalyzer = new BoundaryValueAnalyzer(information);




	}

}
