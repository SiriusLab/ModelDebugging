package org.gemoc.xdsmlframework.api.core;

import java.util.Deque;

import org.gemoc.executionframework.engine.mse.MSEOccurrence;

public interface ISequentialExecutionEngine extends IBasicExecutionEngine {

	public Runnable getEntryPoint();

	public Deque<MSEOccurrence> getCurrentStack();

	public MSEOccurrence getCurrentMSEOccurrence();

}
