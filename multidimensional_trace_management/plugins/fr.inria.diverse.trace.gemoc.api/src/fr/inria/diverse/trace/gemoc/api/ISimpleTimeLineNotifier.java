package fr.inria.diverse.trace.gemoc.api;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.obeo.timeline.view.ITimelineProvider;

public interface ISimpleTimeLineNotifier extends ITimelineProvider {

	void notifyTimeLine();

	void setTraceManager(ITraceManager m);

}
