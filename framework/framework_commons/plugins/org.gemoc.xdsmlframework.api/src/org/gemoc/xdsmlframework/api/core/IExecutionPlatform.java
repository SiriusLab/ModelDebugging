package org.gemoc.xdsmlframework.api.core;

import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IExecutionPlatform extends IDisposable
{

	

	/**
	 * @return The model loader used to load the model to be executed.
	 */
	IModelLoader getModelLoader();

	void addEngineAddon(IEngineAddon addon);
	void removeEngineAddon(IEngineAddon addon);
	Iterable<IEngineAddon> getEngineAddons();
	

}
