package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.Trace;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;

public class GenericTraceExtractor implements ITraceExtractor {

	private Trace<SequentialStep<Step>> traceRoot;
	
	@Override
	public void update() {
	}

	@Override
	public boolean compareStates(EObject e1, EObject e2, boolean respectIgnored) {
		return false;
	}

	@Override
	public ValueWrapper getValueWrapper(int traceIndex, int stateIndex) {
		return null;
	}

	@Override
	public StateWrapper getStateWrapper(int stateIndex) {
		return null;
	}

	@Override
	public List<StateWrapper> getStateWrappers(int startStateIndex, int endStateIndex) {
		return Collections.emptyList();
	}

	@Override
	public List<ValueWrapper> getValueWrappers(int valueTraceIndex, int startStateIndex, int endStateIndex) {
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public StepWrapper getStepWrapper(Step step) {
		final List<Step> subSteps = new ArrayList<>();
		if (step instanceof SequentialStep<?>) {
			subSteps.addAll(((SequentialStep<Step>) step).getSubSteps());
		}
		return new StepWrapper(step, -1, -1, subSteps);
	}

	@Override
	public List<StepWrapper> getStepWrappers(int start, int end) {
		return traceRoot.getRootStep().getSubSteps().stream().map(s -> getStepWrapper(s)).collect(Collectors.toList());
	}

	@Override
	public int getNumberOfTraces() {
		return 0;
	}

	@Override
	public int getStatesTraceLength() {
		return 0;
	}

	@Override
	public int getValuesTraceLength(int traceIndex) {
		return 0;
	}

	@Override
	public String getValueLabel(int traceIndex) {
		return null;
	}

	@Override
	public String getStateDescription(int stateIndex) {
		return null;
	}

	@Override
	public String getValueDescription(int traceIndex, int stateIndex) {
		return null;
	}

	@Override
	public LaunchConfiguration getLaunchConfiguration() {
		return traceRoot.getLaunchconfiguration();
	}
	
	public void loadTrace(Trace<SequentialStep<Step>> root) {
		traceRoot = root;
	}

	@Override
	public void ignoreValueTrace(int trace, boolean ignore) {
	}

	@Override
	public boolean isValueTraceIgnored(int trace) {
		return false;
	}

	@Override
	public Collection<List<EObject>> computeStateEquivalenceClasses(List<? extends EObject> states) {
		return null;
	}

	@Override
	public Collection<List<EObject>> computeStateEquivalenceClasses() {
		return null;
	}

}
