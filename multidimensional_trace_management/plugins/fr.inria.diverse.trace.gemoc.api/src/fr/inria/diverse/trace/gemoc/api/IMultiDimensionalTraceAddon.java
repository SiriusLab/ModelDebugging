package fr.inria.diverse.trace.gemoc.api;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.obeo.timeline.view.ITimelineProvider;

public interface IMultiDimensionalTraceAddon extends IEngineAddon {

	void goTo(EObject state);

	void goToNoTimelineNotification(int i);

	void goTo(int i);

	ITimelineProvider getTimeLineProvider();

	void setTimeLineNotifier(ISimpleTimeLineNotifier prov);
	
	ITraceManager getTraceManager();

	IStepFactory getFactory();

}
