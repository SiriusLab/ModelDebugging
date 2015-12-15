package org.gemoc.execution.engine.core;

import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;

public interface IEngineRegistrationListener {

	void engineRegistered(IBasicExecutionEngine engine);

	void engineUnregistered(IBasicExecutionEngine engine);
	
}
