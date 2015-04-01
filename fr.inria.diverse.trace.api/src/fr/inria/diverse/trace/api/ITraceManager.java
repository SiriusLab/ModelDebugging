package fr.inria.diverse.trace.api;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public interface ITraceManager {

	public abstract void save();

	public abstract void addState();

	public abstract void addEvent(String eventName, Map<String, Object> params);

	public abstract void initTrace();
	
	public abstract EObject getTraceRoot();
	
	int getTraceSize();

	EObject getExecutionState(int index);

	String getDescriptionOfExecutionState(int index);

}
