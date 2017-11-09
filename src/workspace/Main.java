package workspace;

import com.fujitsu.vdmj.Settings;
import com.fujitsu.vdmj.ast.definitions.*;
import com.fujitsu.vdmj.ast.expressions.ASTElementsExpression;
import com.fujitsu.vdmj.ast.expressions.ASTExpression;
import com.fujitsu.vdmj.ast.expressions.ASTExpressionList;
import com.fujitsu.vdmj.ast.lex.LexNameToken;
import com.fujitsu.vdmj.ast.patterns.ASTPattern;
import com.fujitsu.vdmj.ast.patterns.ASTPatternList;
import com.fujitsu.vdmj.ast.statements.ASTForPatternBindStatement;
import com.fujitsu.vdmj.ast.types.ASTOperationType;
import com.fujitsu.vdmj.ast.types.ASTType;
import com.fujitsu.vdmj.ast.types.ASTTypeList;
import com.fujitsu.vdmj.commands.ClassCommandReader;
import com.fujitsu.vdmj.commands.CommandReader;
import com.fujitsu.vdmj.in.INNode;
import com.fujitsu.vdmj.in.definitions.INClassList;
import com.fujitsu.vdmj.lex.*;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.mapper.MappingReader;
import com.fujitsu.vdmj.runtime.ClassInterpreter;
import com.fujitsu.vdmj.runtime.Interpreter;
import com.fujitsu.vdmj.syntax.*;
import com.fujitsu.vdmj.tc.TCNode;
import com.fujitsu.vdmj.tc.definitions.*;
import com.fujitsu.vdmj.tc.expressions.TCExpressionList;
import com.fujitsu.vdmj.tc.lex.TCNameList;
import com.fujitsu.vdmj.tc.modules.TCModuleList;
import com.fujitsu.vdmj.tc.patterns.TCPatternList;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.typechecker.ClassTypeChecker;
import com.fujitsu.vdmj.typechecker.Environment;
import com.fujitsu.vdmj.typechecker.NameScope;
import com.fujitsu.vdmj.typechecker.TypeChecker;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;


public class Main {



    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\kat-lab\\Documents\\Overture\\workspace\\STACK\\stack.vdmpp");


//        Settings.dialect = Dialect.VDM_PP;
//        LexTokenReader ltr = new LexTokenReader(file, Dialect.VDM_PP);
//        ClassReader cr = new ClassReader(ltr);
//        ASTClassList cl = cr.readClasses();
//        System.out.println(cl);


//
//        LexTokenReader ltr2 = new LexTokenReader(file, Dialect.VDM_PP);
//        DefinitionReader dr = new DefinitionReader(ltr2);
//        ListIterator<ASTDefinition> ast = dr.readDefinitions().listIterator();
//
//        ast.forEachRemaining(d -> {
//                    System.out.println("＃access\r\n" + d.accessSpecifier);
//                    //System.out.println("＃location\r\n" + d.location);
//                    System.out.println("＃name\r\n" + d.name);
//                    System.out.println("＃kind\r\n" + d.kind());
//                    System.out.println("＃body\r\n" + d.toString());
//
//                    System.out.println("----------------------------------------------------------------");
//
//                }
//        );


//        TCImplicitFunctionDefinition tcfunction = new TCImplicitFunctionDefinition();


//        LexTokenReader ltr3 = new LexTokenReader(file,Dialect.VDM_PP);
//        ClassReader cr = new ClassReader(ltr3);
//        ASTClassList astcl = cr.readClasses();
//
//        TCClassList tccl = ClassMapper.getInstance(TCNode.MAPPINGS).init().convert(astcl);


        LexTokenReader ltr4 = new LexTokenReader(file, Dialect.VDM_PP);
        DefinitionReader dr = new DefinitionReader(ltr4);
        ASTDefinitionList astdl = dr.readDefinitions();

        TCDefinitionList tcdl = ClassMapper.getInstance(TCNode.MAPPINGS).init().convert(astdl);


        tcdl.forEach(d -> {
            System.out.println("#location\r\n" + d.location + "\r\n");
            System.out.println("#kind\r\n" + d.kind() + "\r\n");
            System.out.println("#access\r\n" + d.accessSpecifier + "\r\n");
            System.out.println("#name\r\n" + d.name + "\r\n");
            System.out.println("#type\r\n" + d.getType() + "\r\n");
            System.out.println("#body\r\n" + d.deref() + "\r\n");

            TCFunctionType tcft = d.getType().getFunction();

//            System.out.println(tcft);

            if( tcft != null){
                System.out.println("#pretype\r\n" + tcft.getPreType() + "\r\n");
                System.out.println("#posttype\r\n" + tcft.getPostType() + "\r\n");
            }

            System.out.println("------------------------------------------------");

                }
        );




    }
}
