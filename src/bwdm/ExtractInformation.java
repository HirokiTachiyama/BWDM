package bwdm;

import java.util.ArrayList;
import java.util.HashMap;




/* information what got from VDM++ specification file by syntax analyse with VDMJ */

public class ExtractInformation {

    //argument  実引数
    //parameter 仮引数
    //今回扱うのは仮引数

    //file name and path
    private String directory;
    private String vdmFileName;
    private String vdmFIlePath;
    private String decisionTableFileName;
    private String decisionTableFilePath;
    
    //argument types information
    private String argumentTypeBody; //int*nat*nat1
    private ArrayList<String> argumentTypes; //int, nat, nat1
    private int intNum;
    private int natNum;
    private int nat1Num;
    
    //parameter information
    private String parameterBodies; //a*b*c
    private ArrayList<String> parameters; //a, b, c

    private ArrayList[] ifConditionBodies;
    //ifConditionsJoined = {"4<a", "a<7",
    //                      "-3<b","b>100","0<b",
    //                      "c<10","3<c","c>-29","c<-40"};
    private ArrayList<String> ifConditionBodiesInCameForward;

    private HashMap[][] ifConditions;
    //ifConditions = {"4",  "<", "a",  "a", "<", "7"
    //                "-3", "<", "b",  "b", ">", "100", "0", "<", "b",
    //                "c",  "<", "10", "3", "<", "c",   "c", ">", "-29", "c", "<" ,"-40"};

    //事前条件文情報 中身はif条件文情報と似た感じ
    @SuppressWarnings("rawtypes")
    private ArrayList[] preConditionBodies; //事前条件文
    /* 使う前に初期化してから使うこと！*/
    @SuppressWarnings("rawtypes")
    private HashMap[][] preConditions; //条件文を演算子と両辺の3つに分解


    public ExtractInformation(String _vdmFileName, String _decisionTableFileName, String _directory) {

        vdmFileName = _vdmFileName;
        decisionTableFileName = _decisionTableFileName;
        directory = _directory;
        
        vdmFIlePath = directory + _vdmFileName;
        decisionTableFilePath = directory + _decisionTableFileName;

        argumentTypeBody = new String();
        argumentTypes = new ArrayList<>();
        parameters = new ArrayList<>();
        intNum = 0;
        natNum = 0;
        nat1Num = 0;
        parameterBodies = new String();
        parameters = new ArrayList<String>();

        ifConditionBodies = new ArrayList[3];
        ifConditionBodiesInCameForward = new ArrayList<String>();
        ifConditions = new HashMap[][];



    }
    
}
