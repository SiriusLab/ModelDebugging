package org.gemoc.gemoc_language_workbench.api.core;

import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.execution.engine.mse.engine_mse.MSEModel;
import org.gemoc.gemoc_language_workbench.api.extensions.languages.LanguageDefinitionExtension;

public interface IExecutionContext extends IDisposable
{

	LanguageDefinitionExtension getLanguageDefinitionExtension();
	
	IExecutionWorkspace getWorkspace();

	IExecutionPlatform getExecutionPlatform();
	
	IRunConfiguration getRunConfiguration();

	Resource getResourceModel();

	ExecutionMode getExecutionMode();

	MSEModel getMSEModel();
	
	 
}
