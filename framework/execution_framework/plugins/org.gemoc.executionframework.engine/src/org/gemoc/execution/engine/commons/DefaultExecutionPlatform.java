package org.gemoc.execution.engine.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.gemoc.xdsmlframework.api.core.IModelLoader;
import org.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public class DefaultExecutionPlatform implements IExecutionPlatform {
	
	protected IModelLoader _modelLoader;
//	protected ICodeExecutor _codeExecutor;
	protected Collection<IEngineAddon> _addons;
	
	public DefaultExecutionPlatform(LanguageDefinitionExtension _languageDefinition, IRunConfiguration runConfiguration) throws CoreException 
	{
		_modelLoader = _languageDefinition.instanciateModelLoader();
//		_codeExecutor = _languageDefinition.instanciateCodeExecutor();		
		_addons = _languageDefinition.instanciateEngineAddons();
		
		for (EngineAddonSpecificationExtension extension : runConfiguration.getEngineAddonExtensions())
		{
			addEngineAddon(extension.instanciateComponent());
		}
		for (IEngineAddon addon : _languageDefinition.instanciateEngineAddons())
		{
			addEngineAddon(addon);			
		}
	}

	@Override
	public IModelLoader getModelLoader() 
	{
		return _modelLoader;
	}

//	@Override
//	public ICodeExecutor getCodeExecutor() 
//	{
//		return _codeExecutor;
//	}

	@Override
	public Iterable<IEngineAddon> getEngineAddons() 
	{
		synchronized(_addonLock)
		{
			return Collections.unmodifiableCollection(new ArrayList<IEngineAddon>(_addons));
		}
	}

	

	@Override
	public void dispose() 
	{
		_addons.clear();
	}

	private Object _addonLock = new Object();
	
	@Override
	public void addEngineAddon(IEngineAddon addon) 
	{
		synchronized (_addonLock) 
		{
			_addons.add(addon);
		}
	}

	@Override
	public void removeEngineAddon(IEngineAddon addon) 
	{
		synchronized (_addonLock) 
		{
			_addons.remove(addon);
		}
	}
	
}
