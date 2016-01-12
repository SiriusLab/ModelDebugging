package org.gemoc.executionframework.engine.ui.launcher;


abstract public class AbstractGemocLauncher extends fr.obeo.dsl.debug.ide.sirius.ui.launch.AbstractDSLLaunchConfigurationDelegateUI {

	// warning this MODEL_ID must be the same as teh one in the ModelLoader in order to enable correctly the breakpoints
	public final static String MODEL_ID = org.gemoc.execution.engine.ui.Activator.PLUGIN_ID+".debugModel";
}
