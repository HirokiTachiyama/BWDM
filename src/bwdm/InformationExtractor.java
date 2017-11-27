package bwdm;

import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.ast.definitions.ASTDefinitionList;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.lex.LexTokenReader;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ParserException;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCExplicitFunctionDefinition;
import com.fujitsu.vdmj.tc.expressions.TCExpression;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import com.fujitsu.vdmj.tc.types.TCTypeList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;




/* information what got from VDM++ specification file by syntax analyse with VDMJ */

public class InformationExtractor {

    //argument  実引数
    //parameter 仮引数
    //今回扱うのは仮引数

    //file name and path
    private String directory;//ok
    private String vdmFileName;//ok
    private String vdmFilePath;//ok
    private String decisionTableFileName;//ok
    private String decisionTableFilePath;//ok
    
    //argument types information
    private String argumentTypeBody; //(int,nat,nat1) ok
    private TCTypeList argumentTypes; //int, nat, nat1 ok
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


    public InformationExtractor(String _vdmFileName, String _decisionTableFileName, String _directory) throws LexException, ParserException {

        /* Initializing fields*/
        vdmFileName = _vdmFileName;
        decisionTableFileName = _decisionTableFileName;
        directory = _directory;
        
        vdmFilePath = directory + _vdmFileName;
        decisionTableFilePath = directory + _decisionTableFileName; //

        /* variableName = init; example */
        argumentTypeBody = new String(); //int*nat*nat1
        argumentTypes = new TCTypeList(); //int, nat, nat1
        parameterBodies = new String(); //a*b*c
        parameters = new ArrayList<String>(); //a, b, c
        intNum = 0; //1
        natNum = 0; //1
        nat1Num = 0; //1

        ifConditionBodies = new ArrayList[3]; //
        ifConditionBodiesInCameForward = new ArrayList<String>();
        //ifConditions = new HashMap[][];
        /*Done initializing fields*/

        LexTokenReader lexer = new LexTokenReader(new File(vdmFilePath), Dialect.VDM_PP);
        DefinitionReader parser = new DefinitionReader(lexer);
        ASTDefinitionList astDefinitions = parser.readDefinitions();

        astDefinitions.forEach((ASTDefinition astDefinition ) -> {
            if(astDefinition.kind().equals("explicit function")) {
                TCExplicitFunctionDefinition tcFunctionDefinition = null;
                try{
                    tcFunctionDefinition = ClassMapper.getInstance(TCDefinition.MAPPINGS).init().convert(astDefinition);
                    //argumentTypeBody = tcFunctionDefinition.
                } catch (Exception e) {
                    e.printStackTrace();
                }

                TCFunctionType tcFunctionType = tcFunctionDefinition.type;
                argumentTypes = tcFunctionType.parameters;
                argumentTypeBody = argumentTypes.toString();
                TCExpression tcExpression = tcFunctionDefinition.body;
                //System.out.println(tcExpression);
            }
        });

    }
    
}
