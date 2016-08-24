package org.gemoc.sequential_addons.stategraph.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StateWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StepWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceListener;

public class StateGraph extends DirectedGraph<StateVertex> implements ITraceListener {
	
	private ITraceExtractor traceExtractor;
	
	private ITraceExplorer traceExplorer;
	
	private final Map<EObject, StateVertex> stateToNode = new HashMap<>();
	
	private final Map<EObject, EObject> stateToEquivalentState = new HashMap<>();
	
	private final List<EObject> equivalentStates = new ArrayList<>();
	
	private BiConsumer<Boolean, StateVertex> renderCommand = null;
	
	public void setTraceExtractor(ITraceExtractor traceExtractor) {
		this.traceExtractor = traceExtractor;
	}

	public void setTraceExplorer(ITraceExplorer traceExplorer) {
		if (this.traceExplorer != null) {
			this.traceExplorer.removeListener(this);
		}
		this.traceExplorer = traceExplorer;
		if (this.traceExplorer != null) {
			this.traceExplorer.addListener(this);
		}
		update();
	}
	
	public void setUpdateCommand(BiConsumer<Boolean, StateVertex> command) {
		renderCommand = command;
	}
	
	@Override
	public void update() {
		stateToEquivalentState.clear();
		traceExtractor.computeStateEquivalenceClasses().stream().forEach(l->{
			EObject equivalentState = null;
			for (EObject state : l) {
				if (equivalentStates.contains(state)) {
					equivalentState = state;
					break;
				}
			}
			if (equivalentState == null) {
				equivalentState = l.remove(0);
				equivalentStates.add(equivalentState);
			} else {
				l.remove(equivalentState);
				l.forEach(s->{
					equivalentStates.remove(s);
					removeVertex(stateToNode.remove(s));
				});
			}
			stateToEquivalentState.put(equivalentState,equivalentState);
			for (EObject otherState : l) {
				stateToEquivalentState.put(otherState,equivalentState);
			}
		});
		final List<StateWrapper> stateWrappers = traceExtractor.getStateWrappers(0, traceExtractor.getStatesTraceLength());
		final List<StepWrapper> stepWrappers = traceExtractor.getStepWrappers(0, traceExtractor.getStatesTraceLength());
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
			stepWrappers.addAll(0,stepWrapper.subSteps.stream().map(s->traceExtractor.getStepWrapper(s)).collect(Collectors.toList()));
		}
		
		final List<Edge<StateVertex>> edgesToRemove = new ArrayList<>(getEdges());
		edgesToRemove.removeAll(addedEdges);
		edgesToRemove.forEach(e -> removeEdge(e));
		
		if (renderCommand != null) {
			StateVertex currentNode = null;
			final StateWrapper currentStateWrapper = traceExtractor.getStateWrapper(traceExplorer.getCurrentStateIndex());
			if (currentStateWrapper != null) {
				final EObject equivalentState = stateToEquivalentState.get(currentStateWrapper.state);
				currentNode = stateToNode.get(equivalentState);
			}
			renderCommand.accept(false, currentNode);
		}
	}
	
	public Edge<StateVertex> addEdge(StateWrapper startStateWrapper, StateWrapper endStateWrapper, EObject step) {
		final EObject startState = startStateWrapper.state;
		final EObject endState = endStateWrapper.state;
		final EObject equivalentStartState = stateToEquivalentState.get(startState);
		final EObject equivalentEndState = stateToEquivalentState.get(endState);
		if (equivalentEndState == equivalentStartState) {
			return null;
		} else {
			StateVertex startNode = null;
			StateVertex endNode = null;
			for (Entry<EObject,StateVertex> entry : stateToNode.entrySet()) {
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
				startNode = addVertex(new StateVertex(traceExtractor.getStateDescription(startStateWrapper.stateIndex)));
				stateToNode.put(equivalentStartState, startNode);
			} else {
				startNode.setTooltip(traceExtractor.getStateDescription(startStateWrapper.stateIndex));
			}
			if (endNode == null) {
				endNode = addVertex(new StateVertex(traceExtractor.getStateDescription(endStateWrapper.stateIndex)));
				stateToNode.put(equivalentEndState, endNode);
			} else {
				endNode.setTooltip(traceExtractor.getStateDescription(endStateWrapper.stateIndex));
			}
			
			Edge<StateVertex> result = getEdge(startNode, endNode);
			
			if (result == null) {
				result = addEdge(startNode, endNode);
			}
			return result;
		}
	}
	
	public void clear() {
		stateToNode.clear();
		if (renderCommand != null) {
			renderCommand.accept(true, null);
		}
		update();
	}
}
