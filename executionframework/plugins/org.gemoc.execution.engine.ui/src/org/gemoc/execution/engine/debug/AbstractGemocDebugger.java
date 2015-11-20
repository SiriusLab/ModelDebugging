package org.gemoc.execution.engine.debug;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;

import fr.obeo.dsl.debug.ide.AbstractDSLDebugger;
import fr.obeo.dsl.debug.ide.event.IDSLDebugEventProcessor;

public abstract class AbstractGemocDebugger extends AbstractDSLDebugger implements IGemocDebugger, IEngineAddon {

	public AbstractGemocDebugger(IDSLDebugEventProcessor target) {
		super(target);
	}

	private Set<BiPredicate<IBasicExecutionEngine, MSEOccurrence>> predicateBreakPoints = new HashSet<BiPredicate<IBasicExecutionEngine, MSEOccurrence>>();
	private Set<BiPredicate<IBasicExecutionEngine, MSEOccurrence>> predicateBreaks = new HashSet<BiPredicate<IBasicExecutionEngine, MSEOccurrence>>();

	@Override
	/**
	 * Breakpoints are persistent, and can trigger pauses as long as they are not removed.
	 */
	public void addPredicateBreakpoint(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate) {
		predicateBreakPoints.add(predicate);
	}

	@Override
	/**
	 * A Break only trigger a single pause, then is removed.
	 */
	public void addPredicateBreak(BiPredicate<IBasicExecutionEngine, MSEOccurrence> predicate) {
		predicateBreaks.add(predicate);
	}

	protected boolean shouldBreakPredicates(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {

		// We look at predicate breaks to remove the ones that are true
		boolean shouldBreak2 = false;
		Set<BiPredicate<IBasicExecutionEngine, MSEOccurrence>> toRemove = new HashSet<BiPredicate<IBasicExecutionEngine, MSEOccurrence>>();
		for (BiPredicate<IBasicExecutionEngine, MSEOccurrence> pred : predicateBreaks) {
			if (pred.test(engine, mseOccurrence)) {
				shouldBreak2 = true;
				toRemove.add(pred);
			}
		}
		predicateBreaks.removeAll(toRemove);
		if (shouldBreak2)
			return true;

		// If no break yet, we look at predicate breakpoints
		for (BiPredicate<IBasicExecutionEngine, MSEOccurrence> pred : predicateBreakPoints) {
			if (pred.test(engine, mseOccurrence)) {
				return true;
			}
		}

		return false;

	}

}
