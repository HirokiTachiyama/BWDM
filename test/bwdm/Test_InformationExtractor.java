package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import com.fujitsu.vdmj.tc.types.TCTypeList;
import org.junit.jupiter.api.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_InformationExtractor {

    /*
     * add vdm++ file names for the test case
     */

    private enum TESTCASE {
        Arg1, Arg2, Arg2_Japanese;
    }

    private String[] fileNamesWithoutFileExtension  = {
            "Arg1",
            "Arg2",
            "Arg2_Japanese"
    };
    private final int testCasesNum = 3; //equals the number of vdm++ files
    private int testCaseCounter;
    private final int testsNum = 3; //the number of executed tests in this file
    private int testCounter;
    InformationExtractor current_ie;
    InformationExtractor[] ies;

    private String testCasesDirectory;
    private String expectedVdmFileName;
    private String expectedVdmFilePath;
    private String expectedDecisionTableFileName;
    private String expectedDecisionTableFilePath;

    @BeforeAll
    @DisplayName("--- Start Unit Test for ExtractionInformation ---")
    void initAllTests() throws LexException, ParserException {
        testCasesDirectory = "./vdm_files/";
        testCaseCounter = 0;
        testCounter = 0;

        //preparing instances of InformationExtractor for each test case file.
        ies = new InformationExtractor[testCasesNum];
        for(int i=0; i<testCasesNum; i++) {
            String tmpVdmFileName = fileNamesWithoutFileExtension[i] + ".vdmpp";
            String tmpDecisionTableFileName = fileNamesWithoutFileExtension[i] + ".csv";
            String tmpVdmFilePath = testCasesDirectory + tmpVdmFileName;
            String tmpDecisionTableFilePath = testCasesDirectory + tmpDecisionTableFileName;
            ies[i] = new InformationExtractor(tmpVdmFileName, tmpDecisionTableFileName, testCasesDirectory);
        }
    }
    
    @BeforeEach
    @DisplayName("Test case initializing")
    void changeCurrentIe() throws LexException, ParserException {
        switch(testCaseCounter) {
            case testsNum:
                current_ie = ies[testCaseCounter];
                testCaseCounter = 0;
                break;
            default:
                current_ie = ies[testCaseCounter];
                break;
        }

        expectedVdmFileName = fileNamesWithoutFileExtension[testCaseCounter] + ".vdmpp";
        expectedDecisionTableFileName = fileNamesWithoutFileExtension[testCaseCounter] + ".csv";
        expectedVdmFilePath = testCasesDirectory + expectedVdmFileName;
        expectedDecisionTableFilePath = testCasesDirectory + expectedDecisionTableFileName;

        current_ie = new InformationExtractor(expectedVdmFileName, expectedDecisionTableFileName, testCasesDirectory);

        System.out.print("Instance:" + fileNamesWithoutFileExtension[testCaseCounter]+" ");
    }

    @AfterEach
    void addTestCaseCounter() {
        switch (testCaseCounter) {
            case testCasesNum - 1:
                testCaseCounter = 0;
                break;
            default:
                testCaseCounter++;
                break;
        }
    }

    @RepeatedTest(testCasesNum)
    void checkFileNameAndFilePath() throws NoSuchFieldException, IllegalAccessException {
        System.out.println();
        //getting private fields by Reflection
        String actualDirectory = (String) Util.getPrivateField(current_ie, "directory");
        String actualVdmFileName = (String) Util.getPrivateField(current_ie, "vdmFileName");
        String actualVdmFilePath = (String) Util.getPrivateField(current_ie, "vdmFilePath");
        String actualDecisionTableFileName = (String) Util.getPrivateField(current_ie, "decisionTableFileName");
        String actualDecisionTableFilePath = (String) Util.getPrivateField(current_ie, "decisionTableFilePath");

        assertEquals(testCasesDirectory, actualDirectory, Util.getMethodName());
        System.out.println("directory:");
        Util.printTestResults(testCasesDirectory, actualDirectory);

        assertEquals(expectedVdmFileName, actualVdmFileName, Util.getMethodName());
        System.out.println("vdmFileName");
        Util.printTestResults(expectedVdmFileName, actualVdmFileName);

        assertEquals(expectedVdmFilePath, actualVdmFilePath, Util.getMethodName());
        System.out.println("vdmFilePath");
        Util.printTestResults(expectedVdmFilePath, actualVdmFilePath);

        assertEquals(expectedDecisionTableFileName, actualDecisionTableFileName, Util.getMethodName());
        System.out.println("decisionTableFileName:");
        Util.printTestResults(expectedDecisionTableFileName, actualDecisionTableFileName);

        assertEquals(expectedDecisionTableFilePath, actualDecisionTableFilePath, Util.getMethodName());
        System.out.println("decisionTableFilePath:");
        Util.printTestResults(expectedDecisionTableFilePath, actualDecisionTableFilePath);
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
        switch(testCaseCounter) {
            case TESTCASE.Arg1: //Arg1.vdmpp
                expected = "(nat)";
                break;
            case 1: //Arg2.vdmpp
                expected = "(int, nat)";
                break;
            case 2: //Arg2_Japanese.vdmpp
                expected = "(int, nat)";
                break;
        }
        String actual = (String) Util.getPrivateField(current_ie, "argumentTypeBody");

        assertEquals(expected, actual, Util.getMethodName());
        Util.printTestResults(expected, actual);
    }

    @RepeatedTest(testCasesNum)
    void checkArgumentTypes() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<String> tmp = new ArrayList<String>();
        switch(testCaseCounter) {
            case 0: //Arg1.vdmpp
                tmp.add("nat");
                break;
            case 1: //Arg2.vdmpp
                tmp.add("int");
                tmp.add("nat");
                break;
            case 2: //Arg2_Japanese.vdmpp
                tmp.add("int");
                tmp.add("nat");
                break;
        }
        String[] expected = tmp.toArray(new String[tmp.size()]); //for checking by assertArrayEquals

        tmp = new ArrayList<String>();
        TCTypeList tmp2 = (TCTypeList) Util.getPrivateField(current_ie, "argumentTypes");

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

        switch(testCaseCounter) {
            case 0: //Arg1
                expectedIntNum  = 0;
                expectedNatNum  = 1;
                expectedNat1Num = 0;
                break;
            case 1: //Arg2
                expectedIntNum  = 1;
                expectedNatNum  = 1;
                expectedNat1Num = 0;
                break;
            case 2: //Arg2_Japanese
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

        actualIntNum = (int) Util.getPrivateField(current_ie, "intNum");
        actualNatNum = (int) Util.getPrivateField(current_ie, "natNum");
        actualNat1Num = (int) Util.getPrivateField(current_ie, "nat1Num");

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
