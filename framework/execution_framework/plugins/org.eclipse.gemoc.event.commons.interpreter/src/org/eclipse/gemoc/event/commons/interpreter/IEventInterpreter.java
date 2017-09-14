package org.eclipse.gemoc.event.commons.interpreter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IEventInterpreter extends IEngineAddon {

	void queueEvent(EventInstance event);

	void processEvents();

	void waitForEvents();

	void loadScenario(URI uri, ResourceSet resourceSet);

	void loadArbiter(URI arbiterURI, ResourceSet resourceSet);

	void addListener(IEventInterpreterListener listener);

	void removeListener(IEventInterpreterListener listener);

	boolean canSendEvent(EventInstance event);
}
