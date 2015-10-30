/*******************************************************************************
 * Copyright (c) 2014 Université de Rennes 1.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erwan Bousse - initial API and implementation
 ******************************************************************************/
package fr.inria.diverse.trace.gemoc.ui.launch;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;

import fr.inria.diverse.trace.gemoc.generator.GenericEngineTraceAddonGeneratorHelper;

public class PlainK3TraceAddonGenerationLaunch extends AbstractJavaLaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {

		// First we retrieve all the information stored in the launch conf
		String mmName = configuration.getAttribute("mmName", "myLanguage");
		String pluginName = configuration.getAttribute("pluginName", "myTraceAddon");
		IJavaProject project = getJavaProject(configuration);
		File path = getWorkingDirectory(configuration);
		boolean replace = configuration.getAttribute("replace", false);

		// Then we call what handles all that
		GenericEngineTraceAddonGeneratorHelper.generateAddon(mmName, pluginName, project, path, replace, monitor);

	}
}
