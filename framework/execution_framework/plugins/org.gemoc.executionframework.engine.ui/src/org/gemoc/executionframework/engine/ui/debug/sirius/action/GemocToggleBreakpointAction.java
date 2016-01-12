package org.gemoc.executionframework.engine.ui.debug.sirius.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.gemoc.executionframework.engine.ui.debug.breakpoint.GemocBreakpoint;
import org.gemoc.executionframework.engine.ui.launcher.AbstractGemocLauncher;

import fr.obeo.dsl.debug.ide.DSLBreakpoint;
import fr.obeo.dsl.debug.ide.sirius.ui.DSLToggleBreakpointsUtils;
import fr.obeo.dsl.debug.ide.sirius.ui.action.AbstractToggleBreakpointAction;

/**
 * commons class for all Gemoc based models
 * @author dvojtise
 *
 */
public class GemocToggleBreakpointAction extends AbstractToggleBreakpointAction {

	@Override
	protected String getModelIdentifier() {
		return AbstractGemocLauncher.MODEL_ID;
	}

	@Override
	protected DSLToggleBreakpointsUtils createToggleBreakpointsUtils() {
		return new DSLToggleBreakpointsUtils(getModelIdentifier()){
			
			@Override
			protected DSLBreakpoint createBreakpoint(Object selected,
					EObject instruction) throws CoreException {
				return new GemocBreakpoint(identifier, instruction, true);
			}
			
		};
	}
	
}
