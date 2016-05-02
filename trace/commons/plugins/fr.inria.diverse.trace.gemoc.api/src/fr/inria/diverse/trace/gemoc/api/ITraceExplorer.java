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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.executionframework.engine.mse.Step;

public interface ITraceExplorer extends ITraceNotifier, ITraceListener {

	List<StateWrapper> getStatesWrappers(int startStateIndex, int endStateIndex);

	List<ValueWrapper> getValuesWrappers(int valueTraceIndex, int startStateIndex, int endStateIndex);

	StepWrapper getStepWrapper(Step step);

	List<? extends Step> getStepsForStates(int startingState, int endingState);

	Step getCurrentForwardStep();

	Step getCurrentBackwardStep();

	Step getCurrentBigStep();

	int getNumberOfTraces();

	int getStatesTraceLength();

	int getValuesTraceLength(int traceIndex);

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

	List<Step> getCallStack();

	void updateCallStack(Step step);

	class ValueWrapper {

		public Object value;
		public int firstStateIndex;
		public int traceIndex;
		public int lastStateIndex;

		public ValueWrapper() {
			value = null;
			firstStateIndex = -1;
			lastStateIndex = -1;
			traceIndex = -1;
		}

		public ValueWrapper(Object value, int firstStateIndex, int lastStateIndex, int traceIndex) {
			this.value = value;
			this.firstStateIndex = firstStateIndex;
			this.lastStateIndex = lastStateIndex;
			this.traceIndex = traceIndex;
		}
	}
	
	class StateWrapper {
		public Object value;
		public int stateIndex;
		
		public StateWrapper() {
			value = null;
			stateIndex = -1;
		}

		public StateWrapper(Object value, int stateIndex) {
			this.value = value;
			this.stateIndex = stateIndex;
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
