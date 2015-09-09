package org.gemoc.gemoc_language_workbench.api.core;

import java.util.Deque;

import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence;

public interface IDeterministicExecutionEngine extends IBasicExecutionEngine {

	public Runnable getEntryPoint();

	public Deque<MSEOccurrence> getCurrentStack();

	public MSEOccurrence getCurrentMSEOccurrence();

}
