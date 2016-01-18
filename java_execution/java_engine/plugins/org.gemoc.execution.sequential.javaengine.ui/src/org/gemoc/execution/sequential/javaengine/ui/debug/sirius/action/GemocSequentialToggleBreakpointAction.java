package org.gemoc.execution.sequential.javaengine.ui.debug.sirius.action;

import org.gemoc.execution.sequential.javaengine.ui.Activator;
import org.gemoc.executionframework.engine.ui.debug.sirius.action.GemocToggleBreakpointAction;

/**
 * commons class for all Gemoc based models
 * @author dvojtise
 *
 */
public class GemocSequentialToggleBreakpointAction extends GemocToggleBreakpointAction {

	@Override
	protected String getModelIdentifier() {
		return Activator.PLUGIN_ID+".plainK3debugModel";
	}

	
	
}
