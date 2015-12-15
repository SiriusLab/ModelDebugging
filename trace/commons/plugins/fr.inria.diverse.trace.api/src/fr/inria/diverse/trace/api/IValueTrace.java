package fr.inria.diverse.trace.api;

import org.eclipse.emf.ecore.EObject;

public interface IValueTrace {
	
	int getSize();
	int getCurrentIndex(int stateIndex);
	EObject getValue(int index);

}
