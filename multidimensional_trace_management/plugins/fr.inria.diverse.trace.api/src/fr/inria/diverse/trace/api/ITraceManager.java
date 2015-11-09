package fr.inria.diverse.trace.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

public interface ITraceManager {

	void save();

	void addState();

	boolean addStateIfChanged();

	void addStep(String stepRuleFQN, Map<String, Object> params);

	void retroAddStep(String stepRuleFQN, Map<String, Object> params);

	void endStep(String stepNameFQN, Object returnValue);

	void initTrace();

	int getTraceSize();

	void goTo(int index);

	void goTo(EObject stateOrValue);

	EObject getExecutionState(int index);

	String getDescriptionOfExecutionState(int index);

	boolean isBigStep(String string);

	String currentBigStep();

	int getNumberOfValueTraces();

	List<IValueTrace> getAllValueTraces();

	String getDescriptionOfValue(EObject value);

	Set<EObject> getAllCurrentValues(int stateIndex);

	int getStateIndex(EObject state);
	
	List<fr.inria.diverse.trace.api.IStep> getStackForwardAfterState(int stateIndex);
	
	List<fr.inria.diverse.trace.api.IStep> getStackForwardBeforeState(int stateIndex);

}
