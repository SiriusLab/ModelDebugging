package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.SequentialStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.Trace;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
import fr.inria.diverse.trace.gemoc.api.ITraceListener;

public class GenericTraceExplorer implements ITraceExplorer {

	private Trace<SequentialStep<Step>> traceRoot;

	final private List<Step> callStack = new ArrayList<>();

	private Step stepIntoResult;
	private Step stepOverResult;
	private Step stepReturnResult;

	private Step stepBackIntoResult;
	private Step stepBackOverResult;
	private Step stepBackOutResult;

	final private List<ITraceListener> listeners = new ArrayList<>();

	@SuppressWarnings("unchecked")
	private Step computeBackInto(List<Step> stepPath) {
		final List<Step> rootSteps = traceRoot.getRootStep().getSubSteps();
		final int depth = stepPath.size();
		Step result = null;
		if (depth > 1) {
			final Step currentStep = stepPath.get(depth - 1);
			final Step parentStep = stepPath.get(depth - 2);
			final SequentialStep<Step> parentStep_cast = (SequentialStep<Step>) parentStep;
			final List<? extends Step> parentSubSteps = parentStep_cast.getSubSteps();
			final int idx = parentSubSteps.indexOf(currentStep);
			if (idx == 0) {
				// If the current step is the first in its parents substeps,
				// return parent step
				result = parentStep;
			} else if (idx > 0) {
				// Otherwise, return the deepest substep in the previous sibling
				// step
				final Step previousSiblingStep = parentSubSteps.get(idx - 1);
				Step tmpStep = previousSiblingStep;
				final List<Step> tmpSubSteps = new ArrayList<>();
				tmpSubSteps.clear();
				if (tmpStep instanceof SequentialStep<?>) {
					SequentialStep<Step> tmpStep_cast = (SequentialStep<Step>) tmpStep;
					tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
				}
				while (!tmpSubSteps.isEmpty()) {
					tmpStep = tmpSubSteps.get(tmpSubSteps.size() - 1);
					tmpSubSteps.clear();
					if (tmpStep instanceof SequentialStep<?>) {
						SequentialStep<Step> tmpStep_cast = (SequentialStep<Step>) tmpStep;
						tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
					}
				}
				result = tmpStep;
			}
		} else if (depth == 1) {
			final Step currentStep = stepPath.get(0);
			final int idx = rootSteps.indexOf(currentStep);
			if (idx > 0) {
				Step tmpStep = rootSteps.get(idx - 1);
				final List<Step> tmpSubSteps = new ArrayList<>();
				tmpSubSteps.clear();
				if (tmpStep instanceof SequentialStep<?>) {
					SequentialStep<Step> tmpStep_cast = (SequentialStep<Step>) tmpStep;
					tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
				}
				while (!tmpSubSteps.isEmpty()) {
					tmpStep = tmpSubSteps.get(tmpSubSteps.size() - 1);
					tmpSubSteps.clear();
					if (tmpStep instanceof SequentialStep<?>) {
						SequentialStep<Step> tmpStep_cast = (SequentialStep<Step>) tmpStep;
						tmpSubSteps.addAll(tmpStep_cast.getSubSteps());
					}
				}
				result = tmpStep;
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private Step computeBackOver(List<Step> stepPath) {
		final List<Step> rootSteps = traceRoot.getRootStep().getSubSteps();
		final int depth = stepPath.size();
		Step result = null;
		if (depth > 1) {
			final Step currentStep = stepPath.get(depth - 1);
			final Step parentStep = stepPath.get(depth - 2);
			final SequentialStep<Step> parentStep_cast = (SequentialStep<Step>) parentStep;
			final List<Step> parentSubSteps = parentStep_cast.getSubSteps();
			final int idx = parentSubSteps.indexOf(currentStep);
			if (idx == 0) {
				// If the current step is the first in its parents substeps,
				// return parent step
				result = parentStep;
			} else {
				// Otherwise, return the previous sibling step
				result = parentSubSteps.get(idx - 1);
			}
		} else if (depth == 1) {
			final Step currentStep = stepPath.get(0);
			final int idx = rootSteps.indexOf(currentStep);
			if (idx > 0) {
				result = rootSteps.get(idx - 1);
			}
		}
		return result;
	}

	private Step computeBackOut(List<Step> stepPath) {
		if (stepPath.size() > 1) {
			return stepPath.get(stepPath.size() - 2);
		}
		return null;
	}

	private Step computeStepInto(List<Step> stepPath, List<Step> rootSteps) {
		return findNextStep(stepPath, null, 0);
	}

	private Step computeStepOver(List<Step> stepPath, List<Step> rootSteps) {
		if (!stepPath.isEmpty()) {
			return findNextStep(stepPath, stepPath.get(stepPath.size() - 1), 1);
		}
		return null;
	}

	private Step computeStepReturn(List<Step> stepPath, List<Step> rootSteps) {
		if (stepPath.size() > 1) {
			return findNextStep(stepPath, stepPath.get(stepPath.size() - 2), 2);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Step findNextStep(final List<Step> stepPath, final Step previousStep, final int start) {
		final List<Step> rootSteps = traceRoot.getRootStep().getSubSteps();
		Step result = null;
		int i = start;
		int depth = stepPath.size();
		Step previous = previousStep;
		while (result == null && i < depth) {
			final Step currentStep = stepPath.get(depth - i - 1);
			final List<Step> currentSubSteps = new ArrayList<>();
			if (currentStep instanceof SequentialStep<?>) {
				currentSubSteps.addAll(((SequentialStep<Step>) currentStep).getSubSteps());
			}
			if (currentSubSteps.isEmpty()) {
				// No substep to step into, we thus have to explore the substeps
				// of the parent step
				previous = currentStep;
			} else {
				if (previous == null) {
					// First time we step into
					result = currentSubSteps.get(0);
				} else {
					final int idx = currentSubSteps.indexOf(previous) + 1;
					if (idx < currentSubSteps.size()) {
						// We step into the next step
						result = currentSubSteps.get(idx);
					} else {
						previous = currentStep;
					}
				}
			}
			i++;
		}
		if (result == null) {
			final int idx = rootSteps.indexOf(previous) + 1;
			if (idx < rootSteps.size()) {
				result = rootSteps.get(idx);
			}
		}
		return result;
	}

	private void computeExplorerState(List<Step> stepPath) {
		final List<Step> rootSteps = traceRoot.getRootStep().getSubSteps();

		final List<Step> stepPathUnmodifiable = Collections.unmodifiableList(stepPath);

		stepIntoResult = computeStepInto(stepPathUnmodifiable, rootSteps);
		stepOverResult = computeStepOver(stepPathUnmodifiable, rootSteps);
		stepReturnResult = computeStepReturn(stepPathUnmodifiable, rootSteps);

		stepBackIntoResult = computeBackInto(stepPathUnmodifiable);
		stepBackOverResult = computeBackOver(stepPathUnmodifiable);
		stepBackOutResult = computeBackOut(stepPathUnmodifiable);

		callStack.clear();
		callStack.addAll(stepPathUnmodifiable.stream().map(s -> (Step) s).collect(Collectors.toList()));
	}

	private void jumpBeforeStep(Step step) {
		updateCallStack(step);
	}

	public void loadTrace(Trace<SequentialStep<Step>> root) {
		traceRoot = root;
	}

	@Override
	public void notifyListeners() {
		for (ITraceListener listener : listeners) {
			listener.update();
		}
	}

	@Override
	public void addListener(ITraceListener listener) {
		if (listener != null) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeListener(ITraceListener listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	@Override
	public void update() {
		notifyListeners();
	}

	@Override
	public Step getCurrentForwardStep() {
		if (!callStack.isEmpty()) {
			return callStack.get(callStack.size() - 1);
		}
		return null;
	}

	@Override
	public Step getCurrentBackwardStep() {
		return stepBackOverResult;
	}

	@Override
	public Step getCurrentBigStep() {
		return stepBackOutResult;
	}

	@Override
	public int getCurrentStateIndex() {
		return -1;
	}

	@Override
	public void jump(EObject o) {
	}

	@Override
	public void jump(int i) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadLastState() {
		final List<Step> steps = traceRoot.getRootStep().getSubSteps();
		Step lastStep = null;
		while (!steps.isEmpty()) {
			lastStep = steps.get(steps.size() - 1);
			steps.clear();
			if (lastStep instanceof SequentialStep<?>) {
				steps.addAll(((SequentialStep<Step>) lastStep).getSubSteps());
			}
		}
		jumpBeforeStep(lastStep);
	}

	@Override
	public boolean stepInto() {
		if (stepIntoResult != null) {
			jumpBeforeStep(stepIntoResult);
			return true;
		}
		return false;
	}

	@Override
	public boolean stepOver() {
		if (stepOverResult != null) {
			jumpBeforeStep(stepOverResult);
			return true;
		}
		return false;
	}

	@Override
	public boolean stepReturn() {
		if (stepReturnResult != null) {
			jumpBeforeStep(stepReturnResult);
			return true;
		}
		return false;
	}

	@Override
	public boolean canStepBackInto() {
		return stepBackIntoResult != null;
	}

	@Override
	public boolean canStepBackOver() {
		return stepBackOverResult != null;
	}

	@Override
	public boolean canStepBackOut() {
		return stepBackOutResult != null;
	}

	@Override
	public boolean stepBackInto() {
		if (stepBackIntoResult != null) {
			jumpBeforeStep(stepBackIntoResult);
			return true;
		}
		return false;
	}

	@Override
	public boolean stepBackOver() {
		if (stepBackOverResult != null) {
			jumpBeforeStep(stepBackOverResult);
			return true;
		}
		return false;
	}

	@Override
	public boolean stepBackOut() {
		if (stepBackOutResult != null) {
			jumpBeforeStep(stepBackOutResult);
			return true;
		}
		return false;
	}

	@Override
	public void stepValue(int traceIndex) {
	}

	@Override
	public void backValue(int traceIndex) {
	}

	@Override
	public boolean canStepValue(int traceIndex) {
		return false;
	}

	@Override
	public boolean canBackValue(int traceIndex) {
		return false;
	}

	@Override
	public boolean isInReplayMode() {
		return stepIntoResult != null;
	}

	@Override
	public List<Step> getCallStack() {
		return callStack;
	}

	@Override
	public void updateCallStack(Step step) {
		Step step_cast = (Step) step;
		final List<Step> newPath = new ArrayList<>();
		newPath.add(step_cast);
		EObject container = step.eContainer();
		while (container != null && container instanceof Step) {
			newPath.add(0, (Step) container);
			container = container.eContainer();
		}
		computeExplorerState(newPath);
		update();
	}

}
