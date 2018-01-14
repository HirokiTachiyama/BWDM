package bwdm;

import bwdm.informationStore.InformationExtractor;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import org.junit.jupiter.api.*;


import java.io.IOException;
import java.util.ArrayList;

import static bwdm.Test_InformationExtractor.TestCase.Arg1;
import static bwdm.Util.getPrivateField;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_InformationExtractor {
    /*
     * add vdm++ file names to enum TestCase
     */
    public enum TestCase {
        Arg1(0),
		Arg2(1),
		Arg2_Japanese(2);  /*add here*/

        final public int SIZE = 3; /*add if test case is added*/
        public int No_;
		public TestCase getNext() {
			switch (this) {
				case Arg1: return Arg2;
				case Arg2: return  Arg2_Japanese;
				case Arg2_Japanese: return Arg1;
				default: return Arg2_Japanese;
			}
		}

        TestCase(int value) {
        	this.No_ = value;
		}
		public int getTestCaseNo_() {
			return this.No_;
		}
    }
    final int testCasesNum = 3;
    private TestCase testCase = Arg1;


    InformationExtractor current_ie;
    InformationExtractor[] ies;

    private String testCasesDirectory;
    private String expectedVdmFileName;
    private String expectedVdmFilePath;

    @BeforeAll
    @DisplayName("--- Start Unit Test for ExtractionInformation ---")
    void initAllTests() throws LexException, ParserException, IOException {
        testCasesDirectory = "./vdm_files/";

        //preparing instances of InformationExtractor for each test case file.
        ies = new InformationExtractor[testCase.SIZE];
        TestCase[] testCases = TestCase.values();
        for(int i=0; i<testCase.SIZE; i++) {
        	String testCaseName = testCases[i].toString();
			String tmpVdmFileName = testCaseName + ".vdmpp";
			ies[i] = new InformationExtractor(tmpVdmFileName);
		}

	}
	@BeforeEach
    @DisplayName("Test case initializing")
	void changeCurrentIe() throws LexException, ParserException {

    	current_ie = ies[testCase.getTestCaseNo_()];

		expectedVdmFileName = testCase.toString() + ".vdmpp";
		expectedVdmFilePath = testCasesDirectory + expectedVdmFileName;

		System.out.print("Instance:" + testCase.toString()+" ");
	}

	@AfterEach
	void addTestCaseCounter() {
		testCase = testCase.getNext();
	}



	@RepeatedTest(testCasesNum)
	void checkFileNameAndFilePath() throws NoSuchFieldException, IllegalAccessException {
		System.out.println();
		//getting private fields by Reflection
		String actualDirectory = (String) getPrivateField(current_ie, "directory");
		String actualVdmFileName = (String) getPrivateField(current_ie, "vdmFileName");
        String actualVdmFilePath = (String) getPrivateField(current_ie, "vdmFilePath");
        String actualDecisionTableFileName = (String) getPrivateField(current_ie, "decisionTableFileName");
        String actualDecisionTableFilePath = (String) getPrivateField(current_ie, "decisionTableFilePath");

        assertEquals(testCasesDirectory, actualDirectory, Util.getMethodName());
        System.out.println("directory:");
        Util.printTestResults(testCasesDirectory, actualDirectory);

        assertEquals(expectedVdmFileName, actualVdmFileName, Util.getMethodName());
        System.out.println("vdmFileName");
        Util.printTestResults(expectedVdmFileName, actualVdmFileName);

        assertEquals(expectedVdmFilePath, actualVdmFilePath, Util.getMethodName());
        System.out.println("vdmFilePath");
        Util.printTestResults(expectedVdmFilePath, actualVdmFilePath);

        System.out.println();
    }

    /*
    @ParameterizedTest
    @RepeatedTest(testCasesNum)
    @ValueSource(strings = {"1", "2"})
    void sample() {
        assertEquals(1, 2);
    }
*/

    @RepeatedTest(testCasesNum)
    void checkArgumentTypeBody() throws NoSuchFieldException, IllegalAccessException {
        String expected = null;
        switch(testCase) {
			case Arg1: //Arg1.vdmpp
                expected = "[nat]";
                break;
			case Arg2: //Arg2.vdmpp
                expected = "[int, nat]";
                break;
			case Arg2_Japanese: //Arg2_Japanese.vdmpp
                expected = "[int, nat]";
                break;
        }
        String actual = (String) getPrivateField(current_ie, "argumentTypeBody");

        assertEquals(expected, actual, Util.getMethodName());
        Util.printTestResults(expected, actual);
    }

    @RepeatedTest(testCasesNum)
    void checkArgumentTypes() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<String> tmp = new ArrayList<String>();
        switch(testCase) {
			case Arg1: //Arg1.vdmpp
                tmp.add("nat");
                break;
			case Arg2: //Arg2.vdmpp
                tmp.add("int");
                tmp.add("nat");
                break;
			case Arg2_Japanese: //Arg2_Japanese.vdmpp
                tmp.add("int");
                tmp.add("nat");
                break;
        }
        String[] expected = tmp.toArray(new String[tmp.size()]); //for checking by assertArrayEquals

        tmp = new ArrayList<String>(); //recycle
        ArrayList tmp2 = (ArrayList) getPrivateField(current_ie, "argumentTypes");

        //for using lambda expression
        //Because, I don't know why..., it needs to copy to new instance of variable.
        ArrayList<String> finalTmp = tmp;
        tmp2.forEach(t -> finalTmp.add(t.toString()) );

        String[] actual = finalTmp.toArray(new String[tmp2.size()]);
        assertArrayEquals(expected, actual);

        String expectedUnion="", actualUnion="";
        for(int i=0; i<actual.length; i++) {
            expectedUnion += expected[i]+","+expectedUnion;
            actualUnion += actual[i]+","+actualUnion;
        }
        Util.printTestResults(expectedUnion, actualUnion);
    }

    @RepeatedTest(testCasesNum)
    void checkParametersNum() throws NoSuchFieldException, IllegalAccessException {
        int expectedIntNum;
        int expectedNatNum;
        int expectedNat1Num;
        int actualIntNum;
        int actualNatNum;
        int actualNat1Num;

        switch(testCase) {
			case Arg1:
                expectedIntNum  = 0;
                expectedNatNum  = 1;
                expectedNat1Num = 0;
                break;
			case Arg2: //Arg2
                expectedIntNum  = 1;
                expectedNatNum  = 1;
                expectedNat1Num = 0;
                break;
			case Arg2_Japanese: //Arg2_Japanese
                expectedIntNum  = 1;
                expectedNatNum  = 1;
                expectedNat1Num = 0;
                break;
            default:
                expectedIntNum  = 0;
                expectedNatNum  = 0;
                expectedNat1Num = 0;
                break;
        }

        actualIntNum = (int) getPrivateField(current_ie, "intNum");
        actualNatNum = (int) getPrivateField(current_ie, "natNum");
        actualNat1Num = (int) getPrivateField(current_ie, "nat1Num");

        assertEquals(expectedIntNum, actualIntNum, Util.getMethodName());
        Util.printTestResults(String.valueOf(expectedIntNum), String.valueOf(actualIntNum));

        assertEquals(expectedNatNum, actualNatNum, Util.getMethodName());
        Util.printTestResults(String.valueOf(expectedNatNum), String.valueOf(actualNatNum));

        assertEquals(expectedNat1Num, actualNat1Num, Util.getMethodName());
        Util.printTestResults(String.valueOf(expectedNat1Num), String.valueOf(actualNat1Num));

    }


    @AfterAll
    static void soutAllTestCasesWereCompleted() {
        System.out.println("All test cases were completed. Incredible! YOU ARE GREAT DEVELOPER!!!");
    }


}
