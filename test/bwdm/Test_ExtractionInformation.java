package bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import com.sun.javafx.tools.packager.Log;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_ExtractionInformation {

    private int testNunmberCounter;
    ExtractInformation ei;

    /*
     * Please add vdm++ file names to fileNamesWithoutFIleExtension
     */
    private String[] fileNamesWithoutFileExtension  = {
            "Arg1", 
            "Arg2",
            "Arg2_chapter3SampleUTF"
    };

    private String expectedVdmFileName;
    private String expectedVdmFilePath;

    private String expectedDecisionTableFileName;
    private String expectedDecisionTableFilePath;
    private String testCasesDirectory;


    @BeforeAll
    @DisplayName("--- Start Unit Test for ExtractionInformation ---")
    void initAllTests() {
        testCasesDirectory = "./vdm_files/";
        testNunmberCounter = 0;
    }
    
    @BeforeEach
    @DisplayName("Test case initializing")
    void initEachTests() throws LexException, ParserException {
        Log.debug("TEST CASE No." + String.valueOf(testNunmberCounter));

        expectedVdmFileName = fileNamesWithoutFileExtension[testNunmberCounter] + ".vdmpp";
        expectedDecisionTableFileName = fileNamesWithoutFileExtension[testNunmberCounter] + ".csv";

        expectedVdmFilePath = testCasesDirectory + expectedVdmFileName;
        expectedDecisionTableFilePath = testCasesDirectory + expectedDecisionTableFileName;

        ei = new ExtractInformation(expectedVdmFileName, expectedDecisionTableFileName, testCasesDirectory);
        testNunmberCounter++;
    }
    
    @Test
    @DisplayName("Check file names and file paths")
    void checkFileNameAndFilePath() throws NoSuchFieldException, IllegalAccessException {

        String actualVdmFileName = (String)UtilForUnitTest.getPrivateField(ei, "vdmFileName");
        String actualVdmFilePath = (String)UtilForUnitTest.getPrivateField(ei, "vdmFilePath");
        String actualDecisionTableFileName = (String)UtilForUnitTest.getPrivateField(ei, "decisionTableFileName");
        String actualDecisionTableFilePath = (String)UtilForUnitTest.getPrivateField(ei, "decisionTableFilePath");

        assertEquals(expectedDecisionTableFileName, actualDecisionTableFileName);
        assertEquals(expectedDecisionTableFilePath, actualDecisionTableFilePath);
        assertEquals(expectedVdmFileName, actualVdmFileName);
        assertEquals(expectedVdmFilePath, actualVdmFilePath);
    }
    
}
