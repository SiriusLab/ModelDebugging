package org.gemoc.xdsmlframework.api.core;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;

public interface IRunConfiguration {

	String getLanguageName();

	URI getExecutedModelURI();
	
	URI getExecutedModelAsMelangeURI();
	
	String getMelangeQuery();
	
	URI getAnimatorURI();

	int getAnimationDelay();
	
	int getDeadlockDetectionDepth();

	Collection<EngineAddonSpecificationExtension> getEngineAddonExtensions();
		
	String getExecutionEntryPoint();
	
	String getModelInitializationMethod();
	
	String getModelInitializationArguments();
	
}
