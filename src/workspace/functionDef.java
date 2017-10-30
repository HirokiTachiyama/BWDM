package workspace;

import com.fujitsu.vdmj.Settings;
import com.fujitsu.vdmj.VDMPP;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.lex.LexTokenReader;
import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.tc.definitions.TCAccessSpecifier;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCExplicitFunctionDefinition;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import java.io.File;

public class functionDef {
    
    public static void main(String[] args) {
        
        LexTokenReader ltr = new LexTokenReader(new File("/Users/ht/Workspace/IntelliJ/BWDM/DTGenerationSupportTool/data/problem.vdmpp"), Dialect.VDM_PP);
        DefinitionReader dr = new DefinitionReader(ltr);
        //TCExplicitFunctionDefinition tcefd = new TCExplicitFunctionDefinition(new TCAccessSpecifier());
        
        
        
        
    }
    
}
