package org.gemoc.execution.engine.io.views.event.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.gemoc.commons.eclipse.ui.ViewHelper;
import org.gemoc.execution.engine.io.Activator;
import org.gemoc.execution.engine.io.views.event.EventManagerView;
import org.gemoc.execution.engine.io.views.event.SelectScenarioFileDialog;
import org.gemoc.execution.engine.io.views.event.scenario.ScenarioException;

public class PlayScenarioAction extends Action 
{

	public PlayScenarioAction() 
	{
		setText("Play");
		setToolTipText("Play scenario");
		ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/play-4-16.png");
		setImageDescriptor(id);
	}
	
	@Override
	public void run() 
	{
		EventManagerView eventView = ViewHelper.retrieveView(EventManagerView.ID);
		if (eventView.getEngine() != null)
		{
			try {
				eventView.playScenario(selectScenario());
			} catch (ScenarioException e) {
				e.printStackTrace();
				eventView.informationMsg("Load Scenario", e.getMessage());
			}				
		}
		else
		{
			eventView.informationMsg("Load Scenario", "Please Select one engine");
		}
	}
	
	private IPath selectScenario()
	{
		SelectScenarioFileDialog selector = new SelectScenarioFileDialog();
		selector.open();
		Object[] selection = selector.getResult();
		IPath path = ((IFile)selection[0]).getLocation();
		return path;
	}
	
}
