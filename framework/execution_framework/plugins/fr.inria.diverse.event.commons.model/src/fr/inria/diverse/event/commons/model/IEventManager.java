package fr.inria.diverse.event.commons.model;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IEventManager extends IEngineAddon {
	
	void sendEvent(Object event);
	
	void manageEvents();
	
	void waitForEvents();
	
	Set<EClass> getEventClasses();
	
	boolean canSendEvent(Object event);
	
	void loadScenario(String path, ResourceSet resourceSet);
}
