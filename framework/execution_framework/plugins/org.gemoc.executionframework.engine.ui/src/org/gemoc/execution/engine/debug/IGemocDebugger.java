package org.gemoc.execution.engine.debug;

import java.util.function.BiPredicate;

import org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IGemocDebugger extends IEngineAddon {

	public abstract void addPredicateBreak(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate);

	public abstract void addPredicateBreakpoint(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate);

}
