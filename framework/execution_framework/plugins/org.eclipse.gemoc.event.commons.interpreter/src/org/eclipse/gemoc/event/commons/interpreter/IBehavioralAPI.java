package org.eclipse.gemoc.event.commons.interpreter;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IBehavioralAPI extends IEngineAddon {

	void dispatchEvent(EventInstance event);
	
	boolean canSendEvent(EventInstance event);
	
	Set<EClass> getEventClasses();
}
