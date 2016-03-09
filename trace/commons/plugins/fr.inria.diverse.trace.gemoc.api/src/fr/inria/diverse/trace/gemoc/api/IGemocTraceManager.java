package fr.inria.diverse.trace.gemoc.api;

import java.util.Set;

import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

import fr.inria.diverse.trace.api.ITraceManager;

public interface IGemocTraceManager extends ITraceManager {

	boolean addStep(org.gemoc.executionframework.engine.mse.MSEOccurrence mseOccurrence);
	void addState(Set<ModelChange> modelChanges);
}
