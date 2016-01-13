package org.gemoc.executionframework.engine.core;

import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;

public interface IEngineRegistrationListener {

	void engineRegistered(IBasicExecutionEngine engine);

	void engineUnregistered(IBasicExecutionEngine engine);
	
}
