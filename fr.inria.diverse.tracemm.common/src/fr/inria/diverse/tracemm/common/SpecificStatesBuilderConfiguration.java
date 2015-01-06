package fr.inria.diverse.tracemm.common;

import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;

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

	public abstract void addEvent(ActivityExecution currentActivityExecution);

	public abstract void createNewState();

}
