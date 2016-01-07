package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.ui.PlatformUI;
import org.gemoc.commons.eclipse.ui.ViewHelper;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;

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
	public void engineStatusChanged(IBasicExecutionEngine engine,
			RunStatus newStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToSelectLogicalStep(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proposedLogicalStepsChanged(IBasicExecutionEngine engine, Collection<LogicalStep> logicalSteps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicalStepSelected(IBasicExecutionEngine engine, LogicalStep selectedLogicalStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aboutToExecuteLogicalStep(IBasicExecutionEngine engine, LogicalStep logicalStepToExecute) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicalStepExecuted(IBasicExecutionEngine engine, LogicalStep logicalStepExecuted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aboutToExecuteMSEOccurrence(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mseOccurrenceExecuted(IBasicExecutionEngine engine, MSEOccurrence mseOccurrence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		
		ArrayList<String> errors = new ArrayList<String>();
		
		boolean found = false;
		for (IEngineAddon iEngineAddon : otherAddons) {
			if( iEngineAddon instanceof IMultiDimensionalTraceAddon){
				found = true;
				break;
			}
		}
		
		if(!found){
			errors.add("MultidimentionalTimeLineOpenViewAddon can't run without IMultiDimensionalTraceAddon");
		}
		
		return errors;
	}
}
