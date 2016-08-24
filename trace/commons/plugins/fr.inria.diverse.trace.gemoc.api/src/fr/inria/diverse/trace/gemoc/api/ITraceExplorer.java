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

import fr.inria.diverse.trace.commons.model.trace.Step;

public interface ITraceExplorer extends ITraceNotifier, ITraceListener {
	
	/**
	 * @return The current step
	 */
	Step getCurrentForwardStep();

	/**
	 * @return The step (big or small) preceding the current step
	 */
	Step getCurrentBackwardStep();

	/**
	 * @return The big step containing the current step
	 */
	Step getCurrentBigStep();

	/**
	 * @return The index of the current state in the trace
	 */
	int getCurrentStateIndex();

	/**
	 * If <code>o</code> is a state, updates the explorer so that <code>o</code> becomes the current state.
	 * If <code>o</code> is a value, updates the explorer so that the first state to contain <code>o</code>
	 * becomes the current state.
	 * @param o The EObject to jump to : must be either a specific state or a specific value
	 */
	void jump(EObject o);

	/**
	 * Updates the explorer so that the state located at the provided index becomes the current state.
	 * @param i The index of the state
	 */
	void jump(int i);

	/**
	 * Updates the explorer so that the last recorded state and step in the trace become the current state and step.
	 */
	void loadLastState();

	/**
	 * If the current step is a big step, updates the state of the explorer so that its current step
	 * becomes the first step contained by the current step.
	 * If the current step is a small step, updates the state of the explorer so that its current step
	 * becomes the step following the current step.
	 * @return Whether the operation succeeded or not
	 */
	boolean stepInto();

	/**
	 * Updates the state of the explorer so that its current step becomes the step following the current step.
	 * @return Whether the operation succeeded or not
	 */
	boolean stepOver();

	/**
	 * Updates the state of the explorer so that its current step becomes the step following the big step
	 * containing the current step.
	 * @return Whether the operation succeeded or not
	 */
	boolean stepReturn();

	/**
	 * @return Whether the stepBackInto method can be called
	 */
	boolean canStepBackInto();

	/**
	 * @return Whether the stepBackOver method can be called
	 */
	boolean canStepBackOver();

	/**
	 * @return Whether the stepBackOut method can be called
	 */
	boolean canStepBackOut();

	/**
	 * Updates the state of the explorer so that its current step becomes the small step preceding the current step.
	 * This is the backward equivalent of the step into operation.
	 * @return Whether the operation succeeded or not
	 */
	boolean stepBackInto();

	/**
	 * Updates the state of the explorer so that its current step becomes the step (big or small)
	 * preceding the current step.
	 * This is the backward equivalent of the step over operation.
	 * @return Whether the operation succeeded or not
	 */
	boolean stepBackOver();

	/**
	 * Updates the state of the explorer so that its current step becomes the big step containing the current step.
	 * This is the backward equivalent of the step return operation.
	 * @return Whether the operation succeeded or not
	 */
	boolean stepBackOut();

	/**
	 * Updates the state of the explorer so that its current state is the first one to contain
	 * the value following the current one in the value trace located at the provided index.
	 * @param traceIndex
	 */
	void stepValue(int traceIndex);

	/**
	 * Updates the state of the explorer so that its current state is the first one to contain
	 * the value preceding the current one in the value trace located at the provided index.
	 * @param traceIndex The index of the value trace
	 */
	void backValue(int traceIndex);

	/**
	 * Returns whether the value trace located at the provided index has at least one value following the current one.
	 * @param traceIndex The index of the value trace
	 * @return Whether the value trace has a value following the current one
	 */
	boolean canStepValue(int traceIndex);

	/**
	 * Returns whether the value trace located at the provided index has at least one value preceding the current one.
	 * @param traceIndex The index of the value trace
	 * @return Whether the value trace has a value preceding the current one
	 */
	boolean canBackValue(int traceIndex);

	/**
	 * @return Whether the explorer is in replay mode or not
	 */
	boolean isInReplayMode();

	/**
	 * Returns the current call stack of the explorer. The current step is at the end of the list.
	 * @return The current call stack
	 */
	List<Step> getCallStack();

	/**
	 * Updates the call stack of the explorer so that the provided step becomes the current step.
	 * The state of the explorer is then recomputed accordingly. 
	 * @param step The step that will become the current step
	 */
	void updateCallStack(Step step);
}
