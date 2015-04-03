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
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate;

import fr.inria.diverse.trace.commons.EMFUtil;
import fr.inria.diverse.trace.gemoc.generator.GenericEngineTraceAddonGenerator;
import fr.inria.diverse.trace.plaink3.tracematerialextractor.EventsMetamodelGenerator;
import fr.inria.diverse.trace.plaink3.tracematerialextractor.ExecutionExtensionGenerator;

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

		// We look for an existing project with this name
		IProject existingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginName);
		if (existingProject.exists()) {
			// If we replace, we delete it
			if (replace) {
				existingProject.delete(true, monitor);
			}
			// Else, error
			else {
				throw new CoreException(new Status(Status.ERROR, "fr.inria.diverse.trace.gemoc.ui",
						"Impossible to create a trace addon: a project already exists with this name."));
			}
		}

		// Then we focus on the "directory" field: we search if it's in
		// workspace or not
		IContainer[] folders = ResourcesPlugin.getWorkspace().getRoot().findContainersForLocationURI(path.toURI());
		Set<EPackage> inputMetamodel = new HashSet<EPackage>();
		ResourceSet rs = new ResourceSetImpl();
		try {
			// Either we did find that the chosen folder matches folders of our
			// workspace
			if (folders != null && folders.length > 0) {
				for (IContainer folder : folders) {
					IResource[] members = folder.members();
					for (IResource r : members) {
						if (r instanceof IFile && r.getName().toLowerCase().endsWith(".ecore")) {
							IFile f = (IFile) r;
							URI uri = URI.createPlatformResourceURI(f.getFullPath().toString(), true);
							Resource model = EMFUtil.loadModelURI(uri, rs);
							inputMetamodel.addAll(model.getContents().stream().filter(o -> o instanceof EPackage)
									.map(o -> (EPackage) o).collect(Collectors.toList()));
						}
					}
				}

			}
			// Or they are located somewhere else on the file system
			else {
				URI uri = URI.createFileURI(path.getAbsolutePath().toString());
				Resource model = EMFUtil.loadModelURI(uri, rs);
				inputMetamodel.addAll(model.getContents().stream().filter(o -> o instanceof EPackage)
						.map(o -> (EPackage) o).collect(Collectors.toList()));
			}

			// Then we call all our business operations
			// TODO handle languages defined with multiple ecores
			EPackage extendedMetamodel = inputMetamodel.iterator().next();
			EventsMetamodelGenerator eventsgen = new EventsMetamodelGenerator(project, mmName, extendedMetamodel);
			eventsgen.generate();
			ExecutionExtensionGenerator extgen = new ExecutionExtensionGenerator(extendedMetamodel);
			extgen.generate();
			GenericEngineTraceAddonGenerator traceaddgen = new GenericEngineTraceAddonGenerator(extendedMetamodel,
					extgen.getResult(), eventsgen.getEventsMM(), pluginName);
			traceaddgen.generateCompleteAddon(monitor);
		} catch (IOException e) {
			// TODO Do real error handling
			e.printStackTrace();
		}
	}

}
