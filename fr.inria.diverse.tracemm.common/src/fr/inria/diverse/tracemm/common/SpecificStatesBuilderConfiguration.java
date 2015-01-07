package fr.inria.diverse.tracemm.common;

import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;

public abstract class SpecificStatesBuilderConfiguration {

	protected ConfigurableStatesBuilder statesBuilder;
	private boolean initDone = false;

	public void init(ConfigurableStatesBuilder s) {
		if (!initDone) {
			this.statesBuilder = s;
			initDone = true;
		}
	}

	public abstract void createStateSystem();

	public abstract void updateState();

	public abstract void createNewState();

	public abstract void addExitEvent(ActivityExitEvent event) ;

	public abstract void addEntryEvent(ActivityEntryEvent event) ;



}
