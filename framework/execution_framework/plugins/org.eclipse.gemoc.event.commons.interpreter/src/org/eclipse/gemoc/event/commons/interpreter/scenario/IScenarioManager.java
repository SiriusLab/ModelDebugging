package org.eclipse.gemoc.event.commons.interpreter.scenario;

import org.eclipse.gemoc.event.commons.model.scenario.Scenario;

public interface IScenarioManager {

	boolean isScenarioComplete();
	
	void loadScenario(Scenario<?> scenario);
	
	void saveScenarioReport();
}
