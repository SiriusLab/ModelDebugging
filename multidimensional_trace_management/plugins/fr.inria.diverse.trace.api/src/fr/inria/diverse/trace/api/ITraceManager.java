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

	void addStep(EOperation stepRule, Map<String, Object> params);

	void retroAddStep(EOperation stepRule, Map<String, Object> params);

	void endStep(EOperation stepName, Object returnValue);

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

}
