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
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

public class Test_IfElseSyntaxTree {

	@BeforeAll
	static void initAll()
			throws LexException, ParserException {


		LexTokenReader lexer = new LexTokenReader(new File("./vdm_files/Arg1.vdmpp"), Dialect.VDM_PP);
		DefinitionReader parser = new DefinitionReader(lexer);
		ASTDefinitionList astDefinitions = parser.readDefinitions();

		astDefinitions.forEach((ASTDefinition astDefinition ) -> {
			if(astDefinition.kind().equals("explicit function")) {
				TCExplicitFunctionDefinition tcFunctionDefinition = null;
				try{
					tcFunctionDefinition = ClassMapper.getInstance(TCDefinition.MAPPINGS).init().convert(astDefinition);
				} catch (Exception e) {
					e.printStackTrace();
				}
				TCExpression tcExpression = tcFunctionDefinition.body;
				String ifExpressionBody = tcExpression.toString();

				IfElseSyntaxTree ifElseSyntaxTree = null;
				try {
					ifElseSyntaxTree = new IfElseSyntaxTree(ifExpressionBody);
				} catch (ParserException e) {
					e.printStackTrace();
				} catch (LexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}


			}
		});




	}

	@BeforeEach
	void init() {

	}


	@Test
	void ほげ() {

	}

	@AfterEach
	void tearDown() {

	}

	@AfterAll
	static void tearDownAll() {

	}


}
