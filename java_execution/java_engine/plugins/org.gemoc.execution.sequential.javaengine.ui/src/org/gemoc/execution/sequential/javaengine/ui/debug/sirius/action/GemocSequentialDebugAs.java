package org.gemoc.execution.sequential.javaengine.ui.debug.sirius.action;


import org.gemoc.execution.sequential.javaengine.ui.launcher.Launcher;

import fr.obeo.dsl.debug.ide.sirius.ui.action.AbstractDebugAsAction;

public class GemocSequentialDebugAs extends AbstractDebugAsAction {

	@Override
	protected String getLaunchConfigurationTypeID() {
		return Launcher.TYPE_ID;
	}
	
}
