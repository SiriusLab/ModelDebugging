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
package fr.inria.diverse.trace.gemoc.generator

import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace
import fr.inria.diverse.melange.ui.internal.MelangeActivator
import fr.inria.diverse.opsemanticsview.gen.OperationalSemanticsViewGenerator
import java.io.IOException
import opsemanticsview.OperationalSemanticsView
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.resources.WorkspaceJob
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.Status
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.ui.resource.IResourceSetProvider
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper

/**
 * Plenty of ways to call the generator in an eclipse context.
 * Must be provided a TraceAddonGeneratorIntegrationConfiguration, specific to the type of xdsml (K3, XMOF, etc)
 */
class TraceAddonGeneratorIntegration {

	static def void generateAddon(IFile melangeFile, String selectedLanguage, boolean replace,
		IProgressMonitor monitor) {
		// Computing output plugin name
		val pluginName = MelangeXDSMLProjectHelper.baseProjectName(melangeFile.project) + "." +
			selectedLanguage.toLowerCase + ".trace"
		generateAddon(melangeFile, selectedLanguage, pluginName, replace, monitor)
	}

	static def void generateAddon(IFile melangeFile, String selectedLanguage, String pluginName, boolean replace,
		IProgressMonitor monitor) {

		// Loading Melange model
		val URI uri = URI.createPlatformResourceURI(melangeFile.getFullPath().toString(), true);
		val injector = MelangeActivator.getInstance().getInjector(MelangeActivator.FR_INRIA_DIVERSE_MELANGE_MELANGE)
		val IResourceSetProvider provider = injector.getInstance(typeof(IResourceSetProvider))
		val ResourceSet resSet = provider.get(melangeFile.getProject())
		val Resource resource = resSet.getResource(uri, true)
		val ModelTypingSpace root = resource.getContents().get(0) as ModelTypingSpace
		val Language selection = root.elements.filter(Language).findFirst[name == selectedLanguage]

//		// Get syntax
//		val ResourceSet rs = new ResourceSetImpl
//		val URI mmUri = URI.createURI(selection.syntax.ecoreUri)
//		//val URI mmUri = URI.createURI("platform:/resource/"+ root.name + "." + selection.name.toLowerCase + "/model/" + selection.name + ".ecore")
//		val Resource syntaxResource = EMFUtil.loadModelURI(mmUri, rs);
//		val Set<EPackage> syntax = syntaxResource.getContents().filter(EPackage).toSet

		// Register all packages in registry
		// TODO remove them afterwards?
//		for (EPackage p : syntaxResource.allContents.filter(EPackage).toSet)
//			EPackage.Registry.INSTANCE.put(p.getNsURI(), p);


		//	We find all extension points providing fr.inria.diverse.trace.gemoc.generator.integration
		val configNew = Platform.getExtensionRegistry().getConfigurationElementsFor(
			"fr.inria.diverse.opsemanticsview.gen");

		// Using them, we instantiate TraceAddonGeneratorIntegrationConfiguration objects and look for one that can work with the current selected language 
		val OperationalSemanticsViewGenerator validViewGenerator = configNew.map [ e |
			e.createExecutableExtension("class")
		].filter(OperationalSemanticsViewGenerator).findFirst [ conf |
			conf.canHandle(selection, melangeFile.project)
		]
		
			// If we find one, we generate
		if (validViewGenerator != null) {
			val OperationalSemanticsView mmextension = validViewGenerator.generate(selection, melangeFile.project);
			generateAddon(selectedLanguage, pluginName, replace, monitor, mmextension)

		} // Otherwise, we error

		// TODO remove what is after
//
//		// We find all extension points providing fr.inria.diverse.trace.gemoc.generator.integration
//		val config = Platform.getExtensionRegistry().getConfigurationElementsFor(
//			"fr.inria.diverse.trace.gemoc.generator.integration");
//
//		// Using them, we instantiate TraceAddonGeneratorIntegrationConfiguration objects and look for one that can work with the current selected language 
//		val TraceAddonGeneratorIntegrationConfiguration validIntegration = config.map [ e |
//			e.createExecutableExtension("class")
//		].filter(TraceAddonGeneratorIntegrationConfiguration).findFirst [ conf |
//			conf.canWorkWith(selection, melangeFile.project)
//		]
//
//		// If we find one, we generate
//		if (validIntegration != null) {
//			validIntegration.compute(selection, selectedLanguage, melangeFile.project, syntax, rs);
//			val Set<EPackage> executionMetamodel = validIntegration.executionMetamodel
//			val OperationalSemanticsView mmextension = validIntegration.getExecutionExtension();
//			generateAddon(selectedLanguage, pluginName, executionMetamodel, replace, monitor, mmextension)
//
//		} // Otherwise, we error
		else {
			throw new CoreException(
				new Status(
					Status.
						ERROR,
					"fr.inria.diverse.trace.gemoc.generator",
					"Impossible to create a trace addon: couldn't find a trace addon integration extension that can manage the chosen melange language."
				));
		}

	}

	/**
	 * Central operation of the class, that calls business operations
	 */
	public static def void generateAddon(String mmName, String pluginName,
		boolean replace, IProgressMonitor monitor, OperationalSemanticsView executionExtension) throws CoreException {

		// We look for an existing project with this name
		val IProject existingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginName);
		if (existingProject.exists()) {

			// If we replace, we delete most of its content 
			// (we keep the original project in order to be able to replace the project even if it was imported in the workspace)
			if (replace) {
				// existingProject.delete(true, monitor);
				val WorkspaceJob job = new WorkspaceJob("deleting project " + existingProject.name + " content") {
					override public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
						for (IResource iRes : existingProject.members) {
							if (!(iRes.name.equals(".project") || iRes.name.equals(".classpath"))) {
								iRes.delete(true, monitor);
							}
						}
						return Status.OK_STATUS;
					}
				};
				job.setRule(existingProject);
				job.schedule();
			} // Else, error
			else {
				throw new CoreException(
					new Status(Status.ERROR, "fr.inria.diverse.trace.gemoc.generator",
						"Impossible to create a trace addon: a project already exists with this name."));
			}
		}

		try {

			// Then we call all our business operations
			// TODO handle languages defined with multiple ecores
			val GenericEngineTraceAddonGenerator traceaddgen = new GenericEngineTraceAddonGenerator(executionExtension, pluginName);
			traceaddgen.generateCompleteAddon(monitor);
		} catch (IOException e) {

			// TODO Do real error handling
			e.printStackTrace();
		}
	}

}
