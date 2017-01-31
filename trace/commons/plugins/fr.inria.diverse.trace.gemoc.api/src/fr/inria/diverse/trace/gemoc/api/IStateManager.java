package fr.inria.diverse.trace.gemoc.api;

import fr.inria.diverse.trace.commons.model.trace.State;

public interface IStateManager<StateSubType extends State<?,?>> {
	
	boolean restoreState(StateSubType state);
	
}
