package org.eclipse.gemoc.event.commons.interpreter.scenario;

import org.eclipse.gemoc.event.commons.model.scenario.Arbiter;
import org.eclipse.gemoc.event.commons.model.scenario.TruthValue;

public interface IArbiterManager {

	TruthValue getTruthValue();

	void loadArbiter(Arbiter<?, ?, ?> arbiter);

}