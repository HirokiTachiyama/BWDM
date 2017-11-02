package bwdm;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.javafx.tools.packager.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ExtractionInformationTest {
    
    private String[] fileNamesWithoutFileExtension  = {
            "Arg1", 
            "Arg2"
    };
    private String vdmppFilePath;
    private static String decisionTableFilePath;
    private static String filesDicrectory;

    private static int testNunmberCounter;
    
    ExtractInformation ei;
    
    @BeforeAll
    @DisplayName("--- Start ExtractionInformationTest ---")
    static void initAllTests() {
        filesDicrectory = "./vdm_files/";
        testNunmberCounter = 0;
    }
    
    @BeforeEach
    @DisplayName("")
    void initEachTests() {
        Log.debug("TEST CASE No." + String.valueOf(testNunmberCounter));
        String vdmppFileName = fileNamesWithoutFileExtension[testNunmberCounter] + ".vdmpp";
        String decisionTableFileName = fileNamesWithoutFileExtension[testNunmberCounter] + ".csv";
        vdmppFilePath = filesDicrectory + vdmppFileName;
        decisionTableFilePath = filesDicrectory + decisionTableFileName;
        testNunmberCounter++;
    }
    
    @Test
    @DisplayName("ファイル名とパスを正しく取得できているかを確認する。")
    void checkFileNameAndFilePath() {
        
        assertEquals(vdmppFilePath, decisionTableFilePath);
    
    }
    
}
