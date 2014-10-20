package org.gemoc.execution.engine.io.views.event;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.gemoc.execution.engine.io.views.event.filters.IEventFilterStrategy;

public class ViewContentProvider implements IStructuredContentProvider 
{
	private IEventFilterStrategy filterStrategy;

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {}

	public void dispose() {}

	public Object[] getElements(Object parent) 
	{
		if(parent instanceof ModelSpecificEventSet)
		{
			ModelSpecificEventSet cache = (ModelSpecificEventSet) parent;
			ArrayList<ModelSpecificEventWrapper> listeClockWrapper = 
					new ArrayList<ModelSpecificEventWrapper>(cache.getFilteredMSEs(filterStrategy));
			return listeClockWrapper.toArray();
		}
		return new Object[0];
	}

	public void setFilterStrategy(IEventFilterStrategy filterStrategy) 
	{
		this.filterStrategy = filterStrategy;
	}
}

