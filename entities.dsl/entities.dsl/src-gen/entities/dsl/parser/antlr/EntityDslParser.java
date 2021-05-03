/*
 * generated by Xtext 2.24.0
 */
package entities.dsl.parser.antlr;

import com.google.inject.Inject;
import entities.dsl.parser.antlr.internal.InternalEntityDslParser;
import entities.dsl.services.EntityDslGrammarAccess;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class EntityDslParser extends AbstractAntlrParser {

	@Inject
	private EntityDslGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalEntityDslParser createParser(XtextTokenStream stream) {
		return new InternalEntityDslParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Entities";
	}

	public EntityDslGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(EntityDslGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}