package fr.inria.diverse.event.commons.interpreter.scenario;

import fr.inria.diverse.event.commons.model.scenario.Scenario;

public interface IScenarioManager {

	boolean isScenarioComplete();
	
	void loadScenario(Scenario<?> scenario);
	
	void saveScenarioReport();
}
