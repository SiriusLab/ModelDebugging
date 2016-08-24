/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.trace.gemoc.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.Step;

public interface ITraceExtractor {
	
	/**
	 * Tells the extractor to ignore or not the value trace located at the provided index.
	 * @param trace The index of the value trace
	 * @param ignore Wether to ignore or not the value trace
	 */
	void ignoreValueTrace(int trace, boolean ignore);
	
	/**
	 * Returns whether the value trace located at the provided index is ignored or not.
	 * @param trace The index of the value trace
	 * @return <code>true</code> if the value trace is ignored, <code>false</code> otherwise
	 */
	boolean isValueTraceIgnored(int trace);
	
	/**
	 * Compares two states and returns <code>true</code> if their values vector are the same, <code>false</code> otherwise.
	 * @param state1 The first state
	 * @param state2 The second state
	 * @param respectIgnored Whether to include ignored values in the comparison or not
	 * @return <code>true</code> if the values vectors of the states are the same, <code>false</code> otherwise
	 */
	boolean compareStates(EObject state1, EObject state2, boolean respectIgnored);
	
	/**
	 * Computes the lists of states that have the same values vectors, for a given list of states.
	 * @param states The list of states to process
	 * @return The lists of states that have the same values vectors
	 */
	Collection<List<EObject>> computeStateEquivalenceClasses(List<? extends EObject> states);
	
	/**
	 * Computes the lists of states that have the same values vectors, for all the states of the trace.
	 * @return The lists of states that have the same values vectors
	 */
	Collection<List<EObject>> computeStateEquivalenceClasses();
	
	/**
	 * @return the launch configuration that was used to generate the trace
	 */
	LaunchConfiguration getLaunchConfiguration();
	
	/**
	 * @return The number of value traces in the trace
	 */
	int getNumberOfTraces();
	
	/**
	 * Returns a description of the state located at the provided index.
	 * @param stateIndex The index of the state in the trace
	 * @return A string listing all values of the state
	 */
	String getStateDescription(int stateIndex);
	
	/**
	 * @return The number of states in the trace
	 */
	int getStatesTraceLength();

	/**
	 * Creates and returns a generic wrapper of the specific state located at the provided index.
	 * @param stateIndex The index of the state in the trace
	 * @return A generic wrapper of the state
	 */
	StateWrapper getStateWrapper(int stateIndex);
	
	/**
	 * Returns a list of generic wrappers of the specific states located between
	 * the <code>start</code> and the <code>end</code> index, both included.
	 * @param start The index of the first desired state
	 * @param end The index of the last desired state
	 * @return A list of generic wrapper of the states
	 */
	List<StateWrapper> getStateWrappers(int start, int end);
	
	/**
	 * Creates and returns a generic wrapper of the provided specific step.
	 * @param step The step to create a wrapper for
	 * @return A generic wrapper of the step
	 */
	StepWrapper getStepWrapper(Step step);

	/**
	 * Returns a list of generic wrappers of the specific steps located between
	 * the <code>start</code> and the <code>end</code> index, both included.
	 * @param start The index of the first state
	 * @param end The index of the last state
	 * @return A list of generic wrapper of the steps
	 */
	List<StepWrapper> getStepWrappers(int start, int end);
	
	/**
	 * Returns a description of the value located on the provided value trace index and at the provided state index.
	 * @param traceIndex The index of the value trace
	 * @param stateIndex The index of the state
	 * @return A string describing the value
	 */
	String getValueDescription(int traceIndex, int stateIndex);
	
	/**
	 * Returns a label for the value trace located at the provided index
	 * @param traceIndex The index of the value trace
	 * @return A label for the value trace
	 */
	String getValueLabel(int traceIndex);
	
	/**
	 * @param traceIndex The index of the value trace
	 * @return The length of the value trace
	 */
	int getValuesTraceLength(int traceIndex);
	
	/**
	 * Creates and returns a generic wrapper of the provided specific value.
	 * @param traceIndex The index of the value trace
	 * @param stateIndex The index of the state
	 * @return A generic wrapper of the value
	 */
	ValueWrapper getValueWrapper(int traceIndex, int stateIndex);
	
	/**
	 * Returns a list of generic wrappers of the specific values of the value trace
	 * located at the provided index, between the <code>start</code> and the <code>end</code>
	 * index, both included.
	 * @param valueTraceIndex The index of the value trace
	 * @param start The index of the first desired state
	 * @param end The index of the last desired state
	 * @return A list of generic wrappers of the values
	 */
	List<ValueWrapper> getValueWrappers(int valueTraceIndex, int start, int end);
	
	/**
	 * Updates the state of the extractor.
	 * To be called when the trace has changed.
	 */
	void update();
	
	class ValueWrapper {

		public EObject value;
		public int firstStateIndex;
		public int traceIndex;
		public int lastStateIndex;

		public ValueWrapper() {
			value = null;
			firstStateIndex = -1;
			lastStateIndex = -1;
			traceIndex = -1;
		}

		public ValueWrapper(EObject value, int firstStateIndex, int lastStateIndex, int traceIndex) {
			this.value = value;
			this.firstStateIndex = firstStateIndex;
			this.lastStateIndex = lastStateIndex;
			this.traceIndex = traceIndex;
		}
	}
	
	class StateWrapper {
		public EObject state;
		public int stateIndex;
		public boolean breakable;
		
		public StateWrapper() {
			state = null;
			stateIndex = -1;
			breakable = false;
		}

		public StateWrapper(EObject value, int stateIndex, boolean breakable) {
			this.state = value;
			this.stateIndex = stateIndex;
			this.breakable = breakable;
		}
	}
	
	class StepWrapper {
		public Step step = null;
		public int startingIndex = -1;
		public int endingIndex = -1;
		public List<Step> subSteps = new ArrayList<>();

		public StepWrapper(Step value, int startingIndex, int endingIndex, List<Step> subSteps) {
			this.step = value;
			this.startingIndex = startingIndex;
			this.endingIndex = endingIndex;
			this.subSteps.addAll(subSteps);
		}
	}
}
