package org.gemoc.executionframework.ui.views.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.gemoc.executionframework.engine.core.GemocRunningEnginesRegistry;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;

class ViewContentProvider implements ITreeContentProvider 
{

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}
	
	public Object[] getElements(Object parent) {
		if (parent instanceof GemocRunningEnginesRegistry)
		{
			GemocRunningEnginesRegistry registry = org.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry;
			List<IBasicExecutionEngine> engines = new ArrayList<IBasicExecutionEngine>(registry.getRunningEngines().values());
			Collections.sort(engines, getComparator()); 
			return engines.toArray();
		}
		return null;
	}
	public Object getParent(Object child) 
	{
		return null;
	}
	
	public Object [] getChildren(Object parent) 
	{
		return new Object[0];
	}
	
	public boolean hasChildren(Object parent) 
	{
		return false;
	}
	
	private Comparator<IBasicExecutionEngine> getComparator()
	{
		Comparator<IBasicExecutionEngine> comparator = new Comparator<IBasicExecutionEngine>() {
		    public int compare(IBasicExecutionEngine c1, IBasicExecutionEngine c2) 
		    {
		    	int c1Value = c1.getRunningStatus().ordinal();
		    	int c2Value = c2.getRunningStatus().ordinal();
		        if (c1Value < c2Value) 
		        {
		        	return -1;
		        } 
		        else if (c1Value > c2Value) 
		        {
		        	return 1;
		        }  
		        return 0;
		    }
		};
		return comparator;
	}
	
}
