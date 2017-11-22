package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_InformationExtractor {

    private int testNumberCounter;
    InformationExtractor ei;

    /*
     * add vdm++ file names, and add testCasesNum.
     */
    private String[] fileNamesWithoutFileExtension  = {
            "Arg1", 
            "Arg2",
            "Arg2_Japanese"
    };
    final int testCasesNum = 3;

    private String testCasesDirectory;
    private String expectedVdmFileName;
    private String expectedVdmFilePath;
    private String expectedDecisionTableFileName;
    private String expectedDecisionTableFilePath;

    @BeforeAll
    @DisplayName("--- Start Unit Test for ExtractionInformation ---")
    void initAllTests() {
        testCasesDirectory = "./vdm_files/";
        testNumberCounter = 0;
    }
    
    @BeforeEach
    @DisplayName("Test case initializing")
    void initEachTests() throws LexException, ParserException {
        System.out.println("TEST CASE No." + String.valueOf(testNumberCounter) + ":" +
                fileNamesWithoutFileExtension[testNumberCounter]);
        expectedVdmFileName = fileNamesWithoutFileExtension[testNumberCounter] + ".vdmpp";
        expectedDecisionTableFileName = fileNamesWithoutFileExtension[testNumberCounter] + ".csv";
        expectedVdmFilePath = testCasesDirectory + expectedVdmFileName;
        expectedDecisionTableFilePath = testCasesDirectory + expectedDecisionTableFileName;

        ei = new InformationExtractor(expectedVdmFileName, expectedDecisionTableFileName, testCasesDirectory);

        System.out.println("Initialize Done");
    }

    @RepeatedTest(testCasesNum)
    @DisplayName("Check file names and file paths")
    void checkFileNameAndFilePath() throws NoSuchFieldException, IllegalAccessException {

        //getting private fields by Reflection
        String actualDirectory = (String)UtilForUnitTest.getPrivateField(ei, "directory");
        String actualVdmFileName = (String)UtilForUnitTest.getPrivateField(ei, "vdmFileName");
        String actualVdmFilePath = (String)UtilForUnitTest.getPrivateField(ei, "vdmFilePath");
        String actualDecisionTableFileName = (String)UtilForUnitTest.getPrivateField(ei, "decisionTableFileName");
        String actualDecisionTableFilePath = (String)UtilForUnitTest.getPrivateField(ei, "decisionTableFilePath");

        assertEquals(testCasesDirectory, actualDirectory);
        assertEquals(expectedDecisionTableFileName, actualDecisionTableFileName);
        assertEquals(expectedDecisionTableFilePath, actualDecisionTableFilePath);
        assertEquals(expectedVdmFileName, actualVdmFileName);
        assertEquals(expectedVdmFilePath, actualVdmFilePath);
        System.out.println("file names and paths OK");
    }

    @AfterEach
    void soutAllTestsWereCompleted() {
        System.out.println("All tests for " + fileNamesWithoutFileExtension[testNumberCounter] + " were completed." +"\n");
        testNumberCounter++;
    }

    @AfterAll
    static void soutAllTestCasesWereCompleted() {
        System.out.println("All test cases were completed. Incredible! YOU ARE GREAT DEVELOPER!!!");
    }


}
