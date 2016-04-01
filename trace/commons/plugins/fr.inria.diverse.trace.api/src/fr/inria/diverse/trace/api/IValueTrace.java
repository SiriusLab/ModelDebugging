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
package fr.inria.diverse.trace.api;

import org.eclipse.emf.ecore.EObject;

public interface IValueTrace {
	
	/**
	 * 
	 * @return the number of stored values within the trace
	 */
	int getSize();
	
	/**
	 * 
	 * @param index the index of the desired value in the value trace
	 * @return the value in this trace contained at the given index
	 */
	EObject getValue(int index);
	
	/**
	 * 
	 * @param stateIndex the index of the desired state in the global trace
	 * @return the active value of the given state in this trace
	 */
	EObject getActiveValue(int stateIndex);
	
	/**
	 * 
	 * @param stateIndex the index of the desired state in the global trace
	 * @return the index of the active value of the given state in this trace
	 */
	int getActiveValueIndex(int stateIndex);
	
	/**
	 * 
	 * @param stateIndex the index of the desired state in the global trace
	 * @return the index of the first state of the active value of the given state in this trace
	 */
	int getActiveValueStartingState(int stateIndex);

}
