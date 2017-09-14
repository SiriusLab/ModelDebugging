package org.eclipse.gemoc.event.commons.interpreter;

import org.eclipse.gemoc.event.commons.model.scenario.Event;

public interface IEventInterpreterListener {
	
	public void eventReceived(Event event);
}
