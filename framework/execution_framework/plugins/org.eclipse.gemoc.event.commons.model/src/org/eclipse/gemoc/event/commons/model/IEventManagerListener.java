package org.eclipse.gemoc.event.commons.model;

import org.eclipse.gemoc.event.commons.model.scenario.Event;

public interface IEventManagerListener {
	
	public void eventReceived(Event event);
}
