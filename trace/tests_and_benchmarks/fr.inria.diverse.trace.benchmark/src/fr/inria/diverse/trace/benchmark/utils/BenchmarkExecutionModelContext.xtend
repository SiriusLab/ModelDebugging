package fr.inria.diverse.trace.benchmark.utils

import org.gemoc.execution.sequential.javaengine.SequentialModelExecutionContext
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration

class BenchmarkExecutionModelContext extends SequentialModelExecutionContext {
	
	new(IRunConfiguration runConfiguration) throws EngineContextException {
		super(runConfiguration, ExecutionMode.Run)
	}
	
}
