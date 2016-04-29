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
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.mse.Step;

import fr.inria.diverse.trace.api.IStep;
import fr.inria.diverse.trace.api.ITraceManager;

public interface ITraceExplorer extends ITraceNotifier, ITraceListener {

	List<StateWrapper> getStatesOrValues(int line, int startStateIndex, int endStateIndex);

	List<IStep> getStepsForStates(int startingState, int endingState);

	IStep getCurrentForwardStep();

	IStep getCurrentBackwardStep();

	IStep getCurrentBigStep();
	
	void setTraceManager(ITraceManager traceManager);

	int getNumberOfTraces();

	int getTraceLength(int traceIndex);

	int getCurrentStateIndex();

	String getTextAt(int traceIndex);

	String getTextAt(int traceIndex, int indexInTrace);

	Object getAt(int traceIndex, int indexInTrace);

	void jump(EObject o);

	void jump(int i);

	void loadLastState();

	boolean stepInto();

	boolean stepOver();

	boolean stepReturn();

	boolean canStepBackInto();

	boolean canStepBackOver();

	boolean canStepBackOut();

	boolean stepBackInto();

	boolean stepBackOver();

	boolean stepBackOut();

	void stepValue(int traceIndex);

	void backValue(int traceIndex);

	boolean canStepValue(int traceIndex);

	boolean canBackValue(int traceIndex);

	boolean isInReplayMode();

	class StateWrapper {

		public Object value;
		public int stateIndex;
		public int traceIndex;
		public int length;

		public StateWrapper() {
			value = null;
			stateIndex = -1;
			traceIndex = -1;
			length = -1;
		}

		public StateWrapper(Object value, int stateIndex, int traceIndex, int length) {
			this.value = value;
			this.stateIndex = stateIndex;
			this.traceIndex = traceIndex;
			this.length = length;
		}
	}

	List<IStep> getCallStack();

	void updateCallStack(Step step);
}
