package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.Collection;

import org.eclipse.ui.PlatformUI;
import org.gemoc.commons.eclipse.ui.ViewHelper;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.LogicalStep;
import org.gemoc.execution.engine.trace.gemoc_execution_trace.MSEOccurrence;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.engine_addon.IEngineAddon;

public class MultidimentionalTimeLineOpenViewAddon implements IEngineAddon {

	@Override
	public void engineAboutToStart(IBasicExecutionEngine engine) {
		// when selected in the addon from launch config, make sure to start the view
		final IBasicExecutionEngine _engine = engine;
		// make sure to have the view when starting the engine
		PlatformUI.getWorkbench().getDisplay().syncExec(
				new Runnable()
				{
					@Override
					public void run() {
						MultidimensionalTimeLineView timelineView;
						timelineView = ViewHelper.showView(MultidimensionalTimeLineView.ID);
						timelineView.configure(_engine);
					}			
				});	
	}

	@Override
	public void engineStarted(IBasicExecutionEngine executionEngine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAboutToStop(IBasicExecutionEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineStopped(IBasicExecutionEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAboutToDispose(IBasicExecutionEngine engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine,
			Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine,
			Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicalStepSelected(IBasicExecutionEngine engine,
			LogicalStep selectedLogicalStep) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToExecuteLogicalStep(IBasicExecutionEngine engine,
			LogicalStep logicalStepToExecute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicalStepExecuted(IBasicExecutionEngine engine,
			LogicalStep logicalStepExecuted) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine engine,
			MSEOccurrence mseOccurrence) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine,
			MSEOccurrence mseOccurrence) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineStatusChanged(IBasicExecutionEngine engine,
			RunStatus newStatus) {
		// TODO Auto-generated method stub

	}

}
