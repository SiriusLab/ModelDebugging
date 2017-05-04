package fr.inria.diverse.event.commons.model;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IEventManager extends IEngineAddon {
	
	void sendEvent(Object event);
	
	void receiveEvent(Object result, Object caller, String className, String methodName);
	
	void manageEvents();
	
	void waitForEvents();
	
	Set<EClass> getEventClasses();
	
	boolean canSendEvent(Object event);
	
	void loadScenario(URI uri, ResourceSet resourceSet);
	
	void addListener(IEventManagerListener listener);
	
	void removeListener(IEventManagerListener listener);
}
