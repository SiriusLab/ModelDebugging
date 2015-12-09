package org.gemoc.executionengine.java.engine;

import org.gemoc.execution.engine.commons.EngineContextException;
import org.gemoc.execution.engine.commons.ModelExecutionContext;
import org.gemoc.execution.engine.mse.engine_mse.MSEModel;
import org.gemoc.executionengine.java.api.extensions.languages.SequentialLanguageDefinitionExtension;
import org.gemoc.executionengine.java.api.extensions.languages.SequentialLanguageDefinitionExtensionPoint;
import org.gemoc.gemoc_language_workbench.api.core.ExecutionMode;
import org.gemoc.gemoc_language_workbench.api.core.IRunConfiguration;
import org.gemoc.gemoc_language_workbench.api.extensions.languages.LanguageDefinitionExtension;

public class SequentialModelExecutionContext extends ModelExecutionContext 
{

	
	public SequentialModelExecutionContext(IRunConfiguration runConfiguration, ExecutionMode executionMode)
			throws EngineContextException
	{
		super(runConfiguration, executionMode);
		
	}
	
	@Override
	protected LanguageDefinitionExtension getLanguageDefinition(String languageName) throws EngineContextException
	{
		SequentialLanguageDefinitionExtension languageDefinition = SequentialLanguageDefinitionExtensionPoint
				.findDefinition(_runConfiguration.getLanguageName());
		if (languageDefinition == null)
		{
			String message = "Cannot find sequential xdsml definition for the language " + _runConfiguration.getLanguageName()
					+ ", please verify that is is correctly deployed.";
			EngineContextException exception = new EngineContextException(message);
			throw exception;
		}
		return languageDefinition;
	}

	@Override
	public MSEModel getMSEModel() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
