/*
 * generated by Xtext 2.24.0
 */
package entities.dsl.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class EntityDslAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("entities/dsl/parser/antlr/internal/InternalEntityDsl.tokens");
	}
}
