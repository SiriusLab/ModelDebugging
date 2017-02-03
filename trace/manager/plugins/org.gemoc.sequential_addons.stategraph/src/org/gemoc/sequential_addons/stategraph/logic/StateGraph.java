package org.gemoc.sequential_addons.stategraph.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StateWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StepWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceListener;
import fr.inria.diverse.trace.gemoc.api.ITraceViewListener;

public class StateGraph extends DirectedGraph<StateVertex>implements ITraceViewListener, ITraceListener {

	private ITraceExtractor traceExtractor;

	private ITraceExplorer traceExplorer;

	private final Map<EObject, StateVertex> stateToNode = new HashMap<>();

	private final Map<EObject, EObject> stateToEquivalentState = new HashMap<>();

	private final List<EObject> equivalentStates = new ArrayList<>();

	private BiConsumer<Boolean, StateVertex> renderCommand = null;

	public void setTraceExtractor(ITraceExtractor traceExtractor) {
		if (this.traceExtractor != null) {
			this.traceExtractor.removeListener(this);
		}
		this.traceExtractor = traceExtractor;
		if (this.traceExtractor != null) {
			this.traceExtractor.registerCommand(this, () -> updateGraph());
		}
	}

	public void setTraceExplorer(ITraceExplorer traceExplorer) {
		if (this.traceExplorer != null) {
			this.traceExplorer.removeListener(this);
		}
		this.traceExplorer = traceExplorer;
		if (this.traceExplorer != null) {
			this.traceExplorer.registerCommand(this, () -> updateCurrentState());
		}
	}

	public void setUpdateCommand(BiConsumer<Boolean, StateVertex> command) {
		renderCommand = command;
	}

	private final List<Boolean> ignoredValueTraces = new ArrayList<>();

	private void updateGraph() {
		// Finding out whether the list of ignored values has changed
		final List<Boolean> newIgnoredValueTraces = new ArrayList<>();
		for (int i = 0; i < traceExtractor.getNumberOfDimensions(); i++) {
			newIgnoredValueTraces.add(traceExtractor.isDimensionIgnored(i));
		}
		// If it did we have to recompute the graph
		if (!newIgnoredValueTraces.equals(ignoredValueTraces)) {
			ignoredValueTraces.clear();
			ignoredValueTraces.addAll(newIgnoredValueTraces);
			computeStateSpace();
			render(false, null);
		}
	}

	private void updateCurrentState() {
		StateVertex currentNode = null;
		if (traceExplorer != null) {
			final StateWrapper currentStateWrapper = traceExtractor
					.getStateWrapper(traceExplorer.getCurrentStateIndex());
			if (currentStateWrapper != null) {
				final EObject equivalentState = stateToEquivalentState.get(currentStateWrapper.state);
				currentNode = stateToNode.get(equivalentState);
			}
		}
		render(false, currentNode);
	}

	@Override
	public void update() {
	}

	private void updateEquivalentStates(Collection<List<EObject>> equivalenceClasses) {
		equivalenceClasses.forEach(l -> {
			EObject equivalentState = null;
			for (EObject state : l) {
				if (equivalentStates.contains(state)) {
					equivalentState = state;
					break;
				}
			}
			if (equivalentState == null) {
				if (l.isEmpty()) {
					return;
				}
				// The current equivalence class does not yet have
				// a dedicated vertex, we thus add its first state
				// to the list of equivalent states.
				equivalentState = l.remove(0);
				equivalentStates.add(equivalentState);
			} else {
				// The current equivalence class does have a
				// dedicated vertex.
				l.remove(equivalentState);
				l.forEach(s -> {
					equivalentStates.remove(s);
					// Cleaning up old vertice in case the equivalence class of
					// the state changed.
					removeVertex(stateToNode.remove(s));
				});
			}
			stateToEquivalentState.put(equivalentState, equivalentState);
			for (EObject otherState : l) {
				stateToEquivalentState.put(otherState, equivalentState);
			}
		});
	}

	private void computeStateSpace() {
		stateToEquivalentState.clear();
		List<List<EObject>> equivalenceClasses = traceExtractor.computeStateEquivalenceClasses();
		updateEquivalentStates(equivalenceClasses);

		final int n = traceExtractor.getStatesTraceLength();
		final List<StateWrapper> stateWrappers = traceExtractor.getStateWrappers(0, n);
		final List<StepWrapper> stepWrappers = traceExtractor.getStepWrappers(0, n);
		final List<Edge<StateVertex>> addedEdges = new ArrayList<>();

		while (!stepWrappers.isEmpty()) {
			final StepWrapper stepWrapper = stepWrappers.remove(0);
			if (stepWrapper.endingIndex != -1 && stepWrapper.subSteps.isEmpty()) {
				final StateWrapper startingStateWrapper = stateWrappers.get(stepWrapper.startingIndex);
				final StateWrapper endingStateWrapper = stateWrappers.get(stepWrapper.endingIndex);
				final Edge<StateVertex> addedEdge = addEdge(startingStateWrapper, endingStateWrapper, stepWrapper.step);
				if (addedEdge != null) {
					addedEdges.add(addedEdge);
				}
			}
			stepWrappers.addAll(0, stepWrapper.subSteps.stream().map(s -> traceExtractor.getStepWrapper(s))
					.collect(Collectors.toList()));
		}

		final List<Edge<StateVertex>> edgesToRemove = new ArrayList<>(getEdges());
		edgesToRemove.removeAll(addedEdges);
		edgesToRemove.forEach(e -> removeEdge(e));
	}

	public Edge<StateVertex> addEdge(StateWrapper startStateWrapper, StateWrapper endStateWrapper, EObject step) {
		final EObject startState = startStateWrapper.state;
		final EObject endState = endStateWrapper.state;
		final EObject equivalentStartState = stateToEquivalentState.get(startState);
		final EObject equivalentEndState = stateToEquivalentState.get(endState);
		if (equivalentEndState == equivalentStartState || equivalentStartState == null || equivalentEndState == null) {
			return null;
		}
		StateVertex startNode = null;
		StateVertex endNode = null;
		for (Entry<EObject, StateVertex> entry : stateToNode.entrySet()) {
			final EObject entryState = entry.getKey();
			if (startNode == null) {
				if (equivalentStartState == entryState) {
					startNode = entry.getValue();
					continue;
				}
			}

			if (endNode == null) {
				if (equivalentEndState == entryState) {
					endNode = entry.getValue();
				}
			}

			if (startNode != null && endNode != null) {
				break;
			}
		}

		if (startNode == null) {
			startNode = addVertex(new StateVertex(traceExtractor.getStateDescription(startStateWrapper.stateIndex),
					startStateWrapper.stateIndex));
			stateToNode.put(equivalentStartState, startNode);
		} else {
			final int startIndex = traceExtractor.getStateWrapper(equivalentStartState).stateIndex;
			final String description = traceExtractor.getStateDescription(startIndex);
			startNode.setTooltip(description);
			startNode.setIndex(startIndex);
		}
		if (endNode == null) {
			endNode = addVertex(new StateVertex(traceExtractor.getStateDescription(endStateWrapper.stateIndex),
					endStateWrapper.stateIndex));
			stateToNode.put(equivalentEndState, endNode);
		} else {
			final int endIndex = traceExtractor.getStateWrapper(equivalentEndState).stateIndex;
			final String description = traceExtractor.getStateDescription(endIndex);
			endNode.setTooltip(description);
			endNode.setIndex(endIndex);
		}

		Edge<StateVertex> result = getEdge(startNode, endNode);

		if (result == null) {
			result = addEdge(startNode, endNode);
		}
		return result;
	}

	public void clear() {
		stateToNode.clear();
		if (renderCommand != null) {
			renderCommand.accept(true, null);
		}
		update();
	}

	private void render(boolean clear, StateVertex currentVertex) {
		if (renderCommand != null) {
			renderCommand.accept(clear, currentVertex);
		}
	}

	@Override
	public void statesAdded(List<EObject> states) {
		List<List<EObject>> equivalenceClasses = traceExtractor.computeStateEquivalenceClasses();
		updateEquivalentStates(equivalenceClasses);
		render(false, null);
	}

	@Override
	public void stepsEnded(List<EObject> steps) {
		for (EObject step : steps) {
			final StepWrapper stepWrapper = traceExtractor.getStepWrapper((Step) step);
			final StateWrapper startingStateWrapper = traceExtractor.getStateWrapper(stepWrapper.startingIndex);
			final StateWrapper endingStateWrapper = traceExtractor.getStateWrapper(stepWrapper.endingIndex);
			addEdge(startingStateWrapper, endingStateWrapper, step);
		}
		render(false, null);
	}

	@Override
	public void stepsStarted(java.util.List<EObject> steps) {
		// Nothing to do here.
	};

	@Override
	public void valuesAdded(List<EObject> values) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dimensionsAdded(List<List<? extends EObject>> dimensions) {
		// TODO Auto-generated method stub
	}
}
