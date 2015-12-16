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
	 * @param stateIndex the index of the current state in the global trace
	 * @return the index of the current value in the value trace
	 */
	int getCurrentIndex(int stateIndex);
	
	/**
	 * 
	 * @param index the index of the desired value in the value trace
	 * @return the value in the value trace contained at the given index
	 */
	EObject getValue(int index);

}
