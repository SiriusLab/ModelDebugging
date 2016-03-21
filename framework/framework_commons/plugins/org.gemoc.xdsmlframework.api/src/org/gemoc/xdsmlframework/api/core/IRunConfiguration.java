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
	
	String getModelEntryPoint();

	String getModelInitializationMethod();
	
	String getModelInitializationArguments();
	
}
