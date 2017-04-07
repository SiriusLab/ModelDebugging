package fr.inria.diverse.event.commons.interpreter.scenario;

import java.util.List;

import fr.inria.diverse.event.commons.model.scenario.Event;

public interface IScenarioManager {

	List<Event<?>> getEvents();
	
	boolean isScenarioComplete();
	
	void saveScenarioReport();
}
