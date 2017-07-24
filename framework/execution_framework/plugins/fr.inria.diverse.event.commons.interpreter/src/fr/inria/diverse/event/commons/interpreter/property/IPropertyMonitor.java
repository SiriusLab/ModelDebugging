package fr.inria.diverse.event.commons.interpreter.property;

import fr.inria.diverse.event.commons.model.property.Property;

public interface IPropertyMonitor {
	
	void monitorProperty(Property property, IPropertyListener listener);
	
	void unmonitorProperty(Property property, IPropertyListener listener);
}
