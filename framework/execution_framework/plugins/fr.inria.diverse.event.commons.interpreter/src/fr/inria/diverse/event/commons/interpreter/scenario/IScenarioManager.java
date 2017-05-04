package fr.inria.diverse.event.commons.interpreter.scenario;

import java.util.List;

import fr.inria.diverse.event.commons.model.EventInstance;

public interface IScenarioManager {

//	List<Event<?>> getEvents();
	List<EventInstance> getEvents();
	
	boolean isScenarioComplete();
	
	void saveScenarioReport();
}
