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

import org.eclipse.emf.ecore.EObject;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.State;
import fr.inria.diverse.trace.commons.model.trace.Value;

public interface ITraceExtractor extends ITraceViewNotifier, ITraceListener {
	
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
	List<List<EObject>> computeStateEquivalenceClasses(List<? extends EObject> states);
	
	/**
	 * Computes the lists of states that have the same values vectors, for all the states of the trace.
	 * @return The lists of states that have the same values vectors
	 */
	List<List<EObject>> computeStateEquivalenceClasses();
	
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
	 * @param stateIndex The index of the state in the trace
	 * @return The state
	 */
	State getState(int stateIndex);
	
	/**
	 * @param state The state
	 * @return The index of the state in the trace
	 */
	int getStateIndex(State state);
	
	/**
	 * @param value the value
	 * @return The index of the first state in which the value is present
	 */
	int getValueFirstStateIndex(Value value);
	
	/**
	 * @param value the value
	 * @return The index of the last state in which the value is present
	 */
	int getValueLastStateIndex(Value value);
	
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
	
	int 
}
