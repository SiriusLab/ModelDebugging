package org.gemoc.executionframework.ui.views.engine.actions;

import java.util.Set;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.gemoc.commons.eclipse.ui.ViewHelper;
import org.gemoc.executionframework.ui.Activator;
import org.gemoc.executionframework.ui.views.engine.EnginesStatusView;
import org.gemoc.execution.engine.debug.AbstractGemocDebugger;
import org.gemoc.gemoc_language_workbench.api.core.ExecutionMode;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;
import org.gemoc.gemoc_language_workbench.api.core.EngineStatus.RunStatus;

public class StopEngineAction extends AbstractEngineAction 
{

	public StopEngineAction()
	{
		super();
	}
	
	
	@Override
	protected void init() {
		super.init();
		setText("Stop");
		setToolTipText("Stop selected engines");
		ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, ISharedImages.IMG_ELCL_STOP);
		setImageDescriptor(id);
	}


	@Override
	protected void updateButton() {
		super.updateButton();
	}


	@Override
	public void run()
	{
		EnginesStatusView view = ViewHelper.retrieveView(EnginesStatusView.ID);
		if(view.getSelectedEngine() != null)
		{
			Set<AbstractGemocDebugger> debuggers = view.getSelectedEngine().getAddonsTypedBy(AbstractGemocDebugger.class);
			view.getSelectedEngine().stop();
			for(AbstractGemocDebugger debugger : debuggers){
				debugger.resume();
			}
		}
		else
		{
			showMessage(view.getSite(), "please select an engine to stop");		
		}
	}

	
	@Override
	public void engineSelectionChanged(IBasicExecutionEngine engine) 
	{
		_currentSelectedEngine = engine;
		
		if (_currentSelectedEngine == null)
		{
			setEnabled(false);			
		}
		else
		{
			setEnabled(!_currentSelectedEngine.getRunningStatus().equals(RunStatus.Stopped));
						
		}
	}
	
}
