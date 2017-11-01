package bwdm;

import java.util.ArrayList;
import java.util.HashMap;




/* information what got from VDM++ specification file by syntax analyse with VDMJ */

public class ExtractInformation {
    
    //file name and path
    private static String directory = null;
    private static String vdmFileName;
    private static String vdmFIlePath;
    private static String decisionTableFileName;
    private static String decisionTableFilePath;
    
    //type info
    private static String argumentTypesJoined;
    private static ArrayList<String> argumentTypes;
    private static int intNum, natNum, nat1Num;    
    
    //formal args info
    private static String formalArgumentsJoined;
    private static ArrayList<String> formalArguments;

    private static ArrayList[] ifConditionsJoined;
    //ifConditionsJoined = {"4<a", "a<7",
    //                      "-3<b","b>100","0<b",
    //                      "c<10","3<c","c>-29","c<-40"};
    
    private static ArrayList<String> ifConditionsJoiedInCameForward;
    private static HashMap[][] ifConditions;
    //ifConditions = {"4",  "<", "a",  "a", "<", "7"
    //                "-3", "<", "b",  "b", ">", "100", "0", "<", "b",
    //                "c",  "<", "10", "3", "<", "c",   "c", ">", "-29", "c", "<" ,"-40"};
    
    


    public ExtractInformation(String _vdmFileName, String _decisionTableFileName, String _directory) {
        vdmFileName = _vdmFileName;
        decisionTableFileName = _decisionTableFileName;
        directory = _directory;
        
        vdmFIlePath = directory + _vdmFileName;
        decisionTableFilePath = directory + _decisionTableFileName;
        
        argumentTypes = new ArrayList<>();
        formalArguments = new ArrayList<>();
        
        
        
        
    }
    
}
