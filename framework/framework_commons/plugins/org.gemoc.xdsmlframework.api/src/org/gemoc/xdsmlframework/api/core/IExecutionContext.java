package org.gemoc.xdsmlframework.api.core;

import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.executionframework.engine.mse.MSEModel;
import org.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public interface IExecutionContext extends IDisposable
{

	void initializeResourceModel();
	
	LanguageDefinitionExtension getLanguageDefinitionExtension();
	
	IExecutionWorkspace getWorkspace();

	IExecutionPlatform getExecutionPlatform();
	
	IRunConfiguration getRunConfiguration();

	Resource getResourceModel();

	ExecutionMode getExecutionMode();

	MSEModel getMSEModel();
	
	 
}
