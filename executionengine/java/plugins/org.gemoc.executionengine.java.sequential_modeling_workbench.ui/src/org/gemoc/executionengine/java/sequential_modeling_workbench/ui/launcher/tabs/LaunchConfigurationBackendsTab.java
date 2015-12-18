package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.launcher.tabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint;
import org.gemoc.xdsmlframework.api.extensions.engine_addon_group.EngineAddonGroupSpecificationExtension;
import org.gemoc.xdsmlframework.api.extensions.engine_addon_group.EngineAddonGroupSpecificationExtensionPoint;

public class LaunchConfigurationBackendsTab extends LaunchConfigurationDataProcessingTab 
{

	@Override
	public String getName() 
	{
		return "Engine Addons";
	}
	
	@Override
	protected Collection<EngineAddonSpecificationExtension> getExtensionSpecifications() 
	{
		ArrayList<EngineAddonSpecificationExtension> result = new ArrayList<EngineAddonSpecificationExtension>();		
		result.addAll(EngineAddonSpecificationExtensionPoint.getSpecifications());
		return result;
	}
	
	@Override
	protected Collection<EngineAddonGroupSpecificationExtension> getGroupExtensionSpecifications() 
	{
		HashMap<String,EngineAddonGroupSpecificationExtension> result = new HashMap<String,EngineAddonGroupSpecificationExtension>();
		// ensures to get only one group for a given id
		for (Iterator<EngineAddonGroupSpecificationExtension> iterator = EngineAddonGroupSpecificationExtensionPoint.getSpecifications().iterator(); iterator.hasNext();) {
			EngineAddonGroupSpecificationExtension engineAddonGroupSpecificationExtension =  iterator.next();
			result.put(engineAddonGroupSpecificationExtension.getId(), engineAddonGroupSpecificationExtension);
		}
		return result.values();
	}
		
}
