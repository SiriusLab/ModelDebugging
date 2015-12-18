package org.gemoc.xdsmlframework.api.engine_addon.modelchangelistener;

import java.util.List;

import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IModelChangeListenerAddon {

	public List<FieldChange> getChanges(IEngineAddon addon);
		
	public boolean registerAddon(IEngineAddon addon);
}
