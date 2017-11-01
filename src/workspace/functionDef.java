package workspace;

import com.fujitsu.vdmj.Settings;
import com.fujitsu.vdmj.VDMPP;
import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.ast.definitions.ASTDefinitionList;
import com.fujitsu.vdmj.ast.definitions.ASTTypeDefinition;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.lex.LexTokenReader;
import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ParserException;
import com.fujitsu.vdmj.tc.definitions.TCAccessSpecifier;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCExplicitFunctionDefinition;
import com.fujitsu.vdmj.tc.definitions.TCTypeDefinition;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import java.io.File;

public class functionDef {
    
    public static void main(String[] args) throws LexException, ParserException {


        LexTokenReader ltr = new LexTokenReader(new File("./BWDM/vdm_files/Arg1.vdmpp"), Dialect.VDM_PP);
        DefinitionReader dr = new DefinitionReader(ltr);
        //TCExplicitFunctionDefinition tcefd = new TCExplicitFunctionDefinition(new TCAccessSpecifier());

        ASTTypeDefinition astTypeDefinition = dr.readTypeDefinition();
        ASTDefinitionList astDefinitionList = dr.readDefinitions();

        TCTypeDefinition


        
        
    }
    
}
