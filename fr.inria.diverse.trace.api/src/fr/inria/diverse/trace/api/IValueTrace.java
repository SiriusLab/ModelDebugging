package fr.inria.diverse.trace.api;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface IValueTrace {
	
	int getSize();
	int getCurrentIndex();
	EObject getValue(int index);

}
