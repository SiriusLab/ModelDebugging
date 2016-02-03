package org.gemoc.xdsmlframework.api.extensions.engine_addon_group;

import org.gemoc.xdsmlframework.api.extensions.Extension;

public class EngineAddonGroupSpecificationExtension extends Extension
{

	public String getId()
	{
		return getAttribute(EngineAddonGroupSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_GROUP_EXTENSION_POINT_ID);
	}

	public String getName()
	{
		return getAttribute(EngineAddonGroupSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_GROUP_EXTENSION_POINT_NAME);
	}
	
}
