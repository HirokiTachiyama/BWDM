package bwdm;





/* information what got from VDM++ specification file by syntax analyse with VDMJ */


public class ExtractInformation {
    
    private static String directory = null;
    private static String vdmFileName = null;
    private static String vdmFIlePath = null;
    private static String decisionTableFileName = null;
    private static String decisionTableFilePath = null;
    
    public ExtractInformation(String _vdmFileName, String _decisionTableFileName, String _directory) {
        vdmFileName = _vdmFileName;
        decisionTableFileName = _decisionTableFileName;
        vdmFIlePath = directory + _vdmFileName;
        decisionTableFilePath = directory + _decisionTableFileName;
    }
    
}
