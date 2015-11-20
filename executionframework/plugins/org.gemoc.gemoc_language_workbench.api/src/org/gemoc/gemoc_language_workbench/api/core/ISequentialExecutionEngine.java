package org.gemoc.gemoc_language_workbench.api.core;

import java.util.Deque;

import org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence;

public interface ISequentialExecutionEngine extends IBasicExecutionEngine {

	public Runnable getEntryPoint();

	public Deque<MSEOccurrence> getCurrentStack();

	public MSEOccurrence getCurrentMSEOccurrence();

}
