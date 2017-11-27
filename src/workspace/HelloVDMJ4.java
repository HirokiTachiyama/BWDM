package workspace;

import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.ast.definitions.ASTDefinitionList;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.lex.LexTokenReader;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ParserException;
import com.fujitsu.vdmj.tc.definitions.*;
import com.fujitsu.vdmj.tc.expressions.TCExpression;
import com.fujitsu.vdmj.tc.expressions.TCIfExpression;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCTypeList;

import java.io.File;

public class HelloVDMJ4 {

    static TCIfExpression ifExpression;


    public static void extractInformationByVDMJ() throws LexException, ParserException {
        LexTokenReader ltr = new LexTokenReader(new File("BWDM/vdm_files/Arg2.vdmpp"), Dialect.VDM_PP);
        DefinitionReader dr = new DefinitionReader(ltr);
        ASTDefinitionList astDefinitionList = dr.readDefinitions();

        astDefinitionList.forEach(_astDefinition -> {
            if(_astDefinition.kind().equals("explicit function")) {
                TCExplicitFunctionDefinition tcExplicitFunctionDefinition = null;
                try {
                    tcExplicitFunctionDefinition = ClassMapper.getInstance(TCExplicitFunctionDefinition.MAPPINGS).init().convert(_astDefinition);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /* koituga if-shiki no kouzou wo motteru! ashita ha kokokara!*/
                ifExpression = (TCIfExpression) tcExplicitFunctionDefinition.body;
                TCFunctionType tcft = tcExplicitFunctionDefinition.type;
                TCTypeList tct = tcft.parameters;
                System.out.println(tct.toString());

            }
        });

    }



    public static void main(String[] args) throws Exception {

        System.setProperty("java.library.path", "/Users/ht/Workspace/Library/:" + System.getProperty("java.library.path"));
        System.out.println(System.getProperty(("java.library.path")));


        //ASTDefinitionListまでは同じ
        LexTokenReader ltr = new LexTokenReader(new File("./BWDM/vdm_files/various_syntax.vdmpp"), Dialect.VDM_PP);
        DefinitionReader dr = new DefinitionReader(ltr);
        ASTDefinitionList astDefinitionList = dr.readDefinitions();


        //ASTDefiniitionList一つ一つに対して、
        astDefinitionList.forEach((ASTDefinition astd) -> {

            //もしも関数定義がきたらTCExplicitFunctionDefinitionに入れる
            if (astd.kind().equals("explicit function")) {
                TCExplicitFunctionDefinition tcefd = null;
                try {
                    tcefd = ClassMapper.getInstance(TCDefinition.MAPPINGS).init().convert(astd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //事前条件と事後条件を取り出して、条件式用のクラスに入れる
                TCExpression tc_pre = tcefd.precondition;
                TCExpression tc_post = tcefd.postcondition;

                System.out.println("関数本体:" + tcefd.body);
                System.out.println("事前条件:" + tc_pre.toString());
                System.out.println("事後条件:" + tc_post.toString());
                System.out.println();
            } else if (astd.kind().equals("type")) { //もしも型定義がきたらTCTypeDefinitionに入れる
                TCTypeDefinition tctd = null;
                try {
                    tctd = ClassMapper.getInstance(TCDefinition.MAPPINGS).init().convert(astd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //型定義を取り出して、型用のクラスに入れる
                TCType type = tctd.type;

                System.out.println("型定義全部" + tctd.toString());
                System.out.println("型" + type.toString());
                System.out.println();
            }
        });

        extractInformationByVDMJ();


    }


}

