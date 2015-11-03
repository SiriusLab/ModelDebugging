package fr.inria.diverse.trace.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

public interface ITraceManager {

	void save();

	void addState(); 
	
	boolean addStateIfChanged();

	void addEvent(String eventName, Map<String, Object> params);

	void retroAddEvent(String eventName, Map<String, Object> params);
	
	void endEvent(String eventName, Object returnValue);
	
	void initTrace();

	int getTraceSize();
	
	void goTo(int index);
	
	void goTo(EObject stateOrValue);
	
	EObject getExecutionState(int index);

	String getDescriptionOfExecutionState(int index);

	boolean isMacro(String string);

	String currentMacro();

	int getNumberOfValueTraces();

	List<IValueTrace> getAllValueTraces();

	String getDescriptionOfValue(EObject value);

	Set<EObject> getAllCurrentValues(int stateIndex);
	
	int getStateIndex(EObject state);

}
