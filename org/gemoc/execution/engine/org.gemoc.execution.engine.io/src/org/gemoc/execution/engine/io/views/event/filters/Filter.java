package org.gemoc.execution.engine.io.views.event.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.gemoc.commons.eclipse.ui.ViewHelper;
import org.gemoc.execution.engine.io.views.event.ClockWrapper;
import org.gemoc.execution.engine.io.views.event.EventManagerView.ClockStatus;
import org.gemoc.execution.engine.io.views.step.LogicalStepsView;

import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClockExpressionAndRelation.Relation;


public abstract class Filter implements IEventFilterStrategy
{
	
	protected Map<String, ClockWrapper> cache;
	protected Collection<ClockWrapper> wrapperList;
	protected List<Relation> relations;
	
	@Override
	public void setParamFilter(List<Relation> relations,
			Map<String, ClockWrapper> cache) 
	{
		this.relations = relations;
		this.cache = new HashMap<String, ClockWrapper>(cache);
		wrapperList = new ArrayList<ClockWrapper>(cache.values());
	}
	
	/**
	 * Add the free clocks which will tick if the user select a logical step to the wrappers return by the filter
	 * and set them a state.
	 */
	protected void addFutureTickingClocks()
	{
		LogicalStepsView decisionView = ViewHelper.<LogicalStepsView>retrieveView(LogicalStepsView.ID);
		TreeViewer treeViewer = decisionView.getTreeViewer();
		final Tree tree = treeViewer.getTree();
		
		TreeItem[] selection = tree.getSelection();
		List<String> eventNameList = new ArrayList<String>();
		for(TreeItem item : selection)
		{
			// If the item is a parent
			if(item.getExpanded())
			{
				// We check the name of all event children
				for(TreeItem events : item.getItems())
				{
					eventNameList.add(events.getText().substring(4));
				}
			}
			// else its a child
			else
			{
				// We move to the parent and check its children
				for(TreeItem events : item.getParentItem().getItems())
				{
					eventNameList.add(events.getText().substring(4));
				}
				
			}
			for(String event : eventNameList)
			{
				ClockWrapper wrapper = cache.get(event);
				// If the current clock isn't forced to a value.
				if(!wrapper.getState().isForced())
				{
					// We change its state to free with future tick
					wrapper.setState(ClockStatus.NOTFORCED_SET);
				}
				wrapperList.remove(wrapper);
				// We add the clock the the wrappers.
				wrapperList.add(wrapper);
			}
		}
	}
}
