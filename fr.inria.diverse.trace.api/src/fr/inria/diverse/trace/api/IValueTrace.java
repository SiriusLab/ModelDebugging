package fr.inria.diverse.trace.api;

import org.eclipse.emf.ecore.EObject;

public interface IValueTrace {
	
	int getSize();
	int getCurrentIndex();
	EObject getValue(int index);

}
