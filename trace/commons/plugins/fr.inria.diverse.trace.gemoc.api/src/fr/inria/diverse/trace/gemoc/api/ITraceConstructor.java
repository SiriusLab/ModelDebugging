package fr.inria.diverse.trace.gemoc.api;

import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.Step;

public interface ITraceConstructor {

	// From IGemocTraceManager

	void addState(List<ModelChange> modelChanges);

	void addStep(Step step);

	void endStep(Step step);

	// From ITraceManager

	EObject initTrace(LaunchConfiguration launchConfiguration);

	void save();

	void save(URI uri);

	boolean isPartialTraceConstructor();

}
