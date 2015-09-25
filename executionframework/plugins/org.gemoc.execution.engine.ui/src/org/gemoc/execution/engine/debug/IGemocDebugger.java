package org.gemoc.execution.engine.debug;

import java.util.function.BiPredicate;

import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;

public interface IGemocDebugger extends IEngineAddon {

	public abstract void addPredicateBreak(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate);

	public abstract void addPredicateBreakpoint(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate);

}
