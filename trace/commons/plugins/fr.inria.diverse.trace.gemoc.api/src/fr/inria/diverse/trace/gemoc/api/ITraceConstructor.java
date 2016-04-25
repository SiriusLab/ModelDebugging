package fr.inria.diverse.trace.gemoc.api;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

public interface ITraceConstructor {

	// From IGemocTraceManager

	void addState(Set<ModelChange> modelChanges);

	boolean addStep(MSEOccurrence mseOccurrence);

	void endStep();

	// From ITraceManager

	EObject initTrace();

	void save();

	void save(URI uri);

}
