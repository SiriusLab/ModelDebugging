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
package fr.inria.diverse.trace.gemoc.traceaddon;

import java.util.List;
import java.util.Map;

import fr.inria.diverse.trace.api.IStep;
import fr.obeo.timeline.view.ITimelineProvider;

public interface ISequentialTimelineProvider extends ITimelineProvider {

	/**
	 * 
	 * @param line
	 * @param startStateIndex
	 * @param endStateIndex
	 * @return
	 */
	List<StateWrapper> getStatesOrValues(int line, int startStateIndex, int endStateIndex);
	
//	/**
//	 * 
//	 * @param startStateIndex
//	 * @param endStateIndex
//	 * @return the map associating a list of state or value wrappers to each recorded trace
//	 */
//	Map<Integer,List<StateWrapper>> getAllStatesOrValues(int startStateIndex, int endStateIndex);
	
	List<IStep> getStepsForStates(int startingState, int endingState);
	
	class StateWrapper {
		
		public Object value;
		public int stateIndex;
		public int traceIndex;
		public int length;
//		public Map<Integer,Integer> lengthByState;
		
		public StateWrapper() {
//			lengthByState = new HashMap<>();
			value = null;
			stateIndex = -1;
			traceIndex = -1;
			length = -1;
		}
		
		public StateWrapper(Object value, int stateIndex, int traceIndex, int length) {
//			lengthByState = new HashMap<>();
			this.value = value;
			this.stateIndex = stateIndex;
			this.traceIndex = traceIndex;
			this.length = length;
		}
		
	}

}
