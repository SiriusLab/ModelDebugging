package org.eclipse.gemoc.event.commons.interpreter;

import java.util.HashSet;
import java.util.Set;

public class EventInterpreterRegistry {

	private static EventInterpreterRegistry instance;

	private Set<IEventInterpreter> registeredManagers;
	
	private EventInterpreterRegistry () {
		this.registeredManagers = new HashSet<IEventInterpreter>();
	}

	public static EventInterpreterRegistry getInstance() {
		if(instance == null) {
			instance = new EventInterpreterRegistry();
		}
		return instance;
	}

	public void registerManager(IEventInterpreter manager) {
		if(manager != null) {
			registeredManagers.add(manager);
		}
	}
	
	public void unregisterManager(IEventInterpreter manager) {
		if(manager != null) {
			registeredManagers.remove(manager);
		}
	}
	
	public IEventInterpreter findEventInterpreter() {
		return registeredManagers.stream().findFirst().orElse(null);
	}
}
