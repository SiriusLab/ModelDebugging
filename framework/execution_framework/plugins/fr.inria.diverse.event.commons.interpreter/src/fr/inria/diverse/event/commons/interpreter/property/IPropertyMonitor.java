package fr.inria.diverse.event.commons.interpreter.property;

import fr.inria.diverse.event.commons.model.property.Property;

public interface IPropertyMonitor {

	boolean monitor(Property property);
	
	void unmonitor(Property property);
	
	void addListener(Property property, IPropertyListener listener);
	
	void removeListener(Property property, IPropertyListener listener);
}
