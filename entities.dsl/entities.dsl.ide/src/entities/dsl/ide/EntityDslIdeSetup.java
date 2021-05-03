/*
 * generated by Xtext 2.24.0
 */
package entities.dsl.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import entities.dsl.EntityDslRuntimeModule;
import entities.dsl.EntityDslStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class EntityDslIdeSetup extends EntityDslStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new EntityDslRuntimeModule(), new EntityDslIdeModule()));
	}
	
}