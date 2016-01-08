package org.gemoc.xdsmlframework.api.core;

import org.gemoc.executionframework.engine.mse.LogicalStep;

public class  EngineStatus {
	long nbLogicalStepRun = 0;
	
	LogicalStep chosenLogicalStep;


	public enum RunStatus { Initializing, Running, WaitingLogicalStepSelection, Stopped}

	
	public long getNbLogicalStepRun() {
		return nbLogicalStepRun;
	}

	public void setNbLogicalStepRun(long nbLogicalStepRun) {
		this.nbLogicalStepRun = nbLogicalStepRun;
	}
	public void incrementNbLogicalStepRun() {
		this.nbLogicalStepRun +=1;
	}
		
	
}
