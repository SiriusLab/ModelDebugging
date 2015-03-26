package fr.inria.diverse.trace.api;

import org.eclipse.emf.ecore.EObject;

public interface ITraceManager {

	public abstract void save();

	public abstract void addState();

	public abstract void addEvent();

	public abstract void initTrace();
	
	public abstract EObject getTraceRoot();
	
	int getTraceSize();

	EObject getExecutionState(int index);

	String getDescriptionOfExecutionState(int index);

}
