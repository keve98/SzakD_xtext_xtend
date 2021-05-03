/*
 * generated by Xtext 2.24.0
 */
package entities.dsl.ui;

import com.google.inject.Injector;
import entities.dsl.ui.internal.DslActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class EntityDslExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(DslActivator.class);
	}
	
	@Override
	protected Injector getInjector() {
		DslActivator activator = DslActivator.getInstance();
		return activator != null ? activator.getInjector(DslActivator.ENTITIES_DSL_ENTITYDSL) : null;
	}

}