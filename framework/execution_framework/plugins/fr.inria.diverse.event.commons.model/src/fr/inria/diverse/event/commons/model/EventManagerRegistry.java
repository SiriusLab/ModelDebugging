package fr.inria.diverse.event.commons.model;

import java.util.HashSet;
import java.util.Set;

public class EventManagerRegistry {

	private static EventManagerRegistry instance;

	private Set<IEventManager> registeredManagers;
	
	private EventManagerRegistry () {
		this.registeredManagers = new HashSet<IEventManager>();
	}

	public static EventManagerRegistry getInstance() {
		if(instance == null) {
			instance = new EventManagerRegistry();
		}
		return instance;
	}

	public void registerManager(IEventManager manager) {
		if(manager != null) {
			registeredManagers.add(manager);
		}
	}
	
	public void unregisterManager(IEventManager manager) {
		if(manager != null) {
			registeredManagers.remove(manager);
		}
	}
	
	public IEventManager findEventManager() {
		return registeredManagers.stream().findFirst().orElse(null);
	}
}
