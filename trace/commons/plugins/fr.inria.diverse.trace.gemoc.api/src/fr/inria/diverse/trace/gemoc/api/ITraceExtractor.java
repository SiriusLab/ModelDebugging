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

import java.util.List;

import fr.inria.diverse.trace.commons.model.trace.Dimension;
import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.State;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TracedObject;
import fr.inria.diverse.trace.commons.model.trace.Value;

public interface ITraceExtractor<StepSubType extends Step<?>, StateSubType extends State<?,?>, TracedObjectSubType extends TracedObject<?>, DimensionSubType extends Dimension<?>, ValueSubType extends Value<?>> extends ITraceViewNotifier, ITraceListener {
	
	/**
	 * Tells the extractor to ignore or not the value trace located at the provided index.
	 * @param trace The index of the value trace
	 * @param ignore Wether to ignore or not the value trace
	 */
	void ignoreDimension(DimensionSubType dimension, boolean ignore);
	
	/**
	 * Returns whether the value trace located at the provided index is ignored or not.
	 * @param trace The index of the value trace
	 * @return <code>true</code> if the value trace is ignored, <code>false</code> otherwise
	 */
	boolean isDimensionIgnored(DimensionSubType dimension);
	
	/**
	 * Compares two states and returns <code>true</code> if their values vector are the same, <code>false</code> otherwise.
	 * @param state1 The first state
	 * @param state2 The second state
	 * @param respectIgnored Whether to include ignored values in the comparison or not
	 * @return <code>true</code> if the values vectors of the states are the same, <code>false</code> otherwise
	 */
	boolean compareStates(StateSubType state1, StateSubType state2, boolean respectIgnored);
	
	/**
	 * Computes the lists of states that have the same values vectors, for a given list of states.
	 * @param states The list of states to process
	 * @return The lists of states that have the same values vectors
	 */
	List<List<StateSubType>> computeStateEquivalenceClasses(List<? extends StateSubType> states);
	
	/**
	 * Computes the lists of states that have the same values vectors, for all the states of the trace.
	 * @return The lists of states that have the same values vectors
	 */
	List<List<StateSubType>> computeStateEquivalenceClasses();
	
	/**
	 * @return the launch configuration that was used to generate the trace
	 */
	LaunchConfiguration getLaunchConfiguration();
	
	/**
	 * @return The number of value traces in the trace
	 */
	int getNumberOfDimensions();
	
	/**
	 * Returns a description of the state located at the provided index.
	 * @param stateIndex The index of the state in the trace
	 * @return A string listing all values of the state
	 */
	String getStateDescription(int stateIndex);
	
	String getStateDescription(StateSubType state);
	
	/**
	 * @return The number of states in the trace
	 */
	int getStatesTraceLength();
	
	/**
	 * @param stateIndex The index of the state in the trace
	 * @return The state
	 */
	StateSubType getState(int stateIndex);
	
	/**
	 * @param state The state
	 * @return The index of the state in the trace
	 */
	int getStateIndex(StateSubType state);
	
	/**
	 * @param value the value
	 * @return The index of the first state in which the value is present
	 */
	int getValueFirstStateIndex(ValueSubType value);
	
	/**
	 * @param value the value
	 * @return The index of the last state in which the value is present
	 */
	int getValueLastStateIndex(ValueSubType value);
	
	/**
	 * Returns a description of the value located on the provided value trace index and at the provided state index.
	 * @param traceIndex The index of the value trace
	 * @param stateIndex The index of the state
	 * @return A string describing the value
	 */
	String getValueDescription(int traceIndex, int stateIndex);
	
	String getValueDescription(ValueSubType value);
	
	/**
	 * Returns a label for the value trace located at the provided index
	 * @param traceIndex The index of the value trace
	 * @return A label for the value trace
	 */
	String getDimensionLabel(int traceIndex);
	
	String getDimensionLabel(DimensionSubType dimension);
	
	/**
	 * @param traceIndex The index of the value trace
	 * @return The length of the value trace
	 */
	int getValuesTraceLength(int traceIndex);
	
	int getValuesTraceLength(DimensionSubType dimension);
}
