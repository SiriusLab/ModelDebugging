/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.execution.sequential.javaengine;

import org.gemoc.execution.sequential.javaxdsml.api.extensions.languages.SequentialLanguageDefinitionExtension;
import org.gemoc.execution.sequential.javaxdsml.api.extensions.languages.SequentialLanguageDefinitionExtensionPoint;
import org.gemoc.executionframework.engine.commons.EngineContextException;
import org.gemoc.executionframework.engine.commons.ModelExecutionContext;
import org.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

import fr.inria.diverse.trace.commons.model.trace.MSEModel;

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
