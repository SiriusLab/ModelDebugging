package fr.inria.diverse.trace.gemoc.api;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.api.IStep;
import fr.inria.diverse.trace.api.IValueTrace;

public interface ITraceExtractor {

	EObject getExecutionState(int index);

	int getNumberOfValueTraces();

	List<IValueTrace> getAllValueTraces();

	int getTraceSize();

	Object getContainedValue(EObject value);

	Set<EObject> getAllCurrentValues(int stateIndex);

	int getStateOrValueIndex(EObject stateOrValue);

	List<IStep> getStepsForStates(int startingState, int endingState);

}
