package fr.inria.diverse.trace.gemoc.generator

import fr.inria.diverse.trace.commons.EMFUtil
import fr.inria.diverse.trace.plaink3.tracematerialextractor.EventsMetamodelGenerator
import fr.inria.diverse.trace.plaink3.tracematerialextractor.ExecutionExtensionGenerator
import java.io.File
import java.io.IOException
import java.util.HashSet
import java.util.Set
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.gemoc.gemoc_language_workbench.conf.LanguageDefinition
import org.gemoc.gemoc_language_workbench.ui.wizards.XDSMLProjectHelper

/**
 * Plenty of ways to call the generator in an eclipse context
 */
class GenericEngineTraceAddonGeneratorHelper {
	
	private def static Set<EPackage> findAllEPackagesIn(Set<IContainer> containers) {

		val Set<EPackage> inputMetamodel = new HashSet<EPackage>();
		val rs = new ResourceSetImpl

		for (container : containers) {
			container.accept(
				[ r |
					if(r instanceof IFile) {
						if(r.getName().toLowerCase().endsWith(".ecore")) {
							val URI uri = URI.createPlatformResourceURI(r.getFullPath().toString(), true);
							val Resource model = EMFUtil.loadModelURI(uri, rs);

							val Set<EPackage> result = new HashSet<EPackage>();
							for (EObject c : model.getContents()) {
								if(c instanceof EPackage)
									result.add(c as EPackage);
							}
							inputMetamodel.addAll(result);
						}
					}
					return true
				])
		}
		return inputMetamodel
	}

	public def static void generateAddon(String mmName, String pluginName, IJavaProject project, File path,
		boolean replace, IProgressMonitor monitor) throws CoreException {

		val Set<IContainer> folders = ResourcesPlugin.getWorkspace().getRoot().
			findContainersForLocationURI(path.toURI()).toSet;

		// Either we did find that the chosen folder matches folders of our
		// workspace
		val Set<EPackage> inputMetamodel = if(folders != null && folders.length > 0) {
				findAllEPackagesIn(folders)
			}
			// Or they are located somewhere else on the file system
			else {
				val rs = new ResourceSetImpl
				val URI uri = URI.createFileURI(path.getAbsolutePath().toString());
				val Resource model = EMFUtil.loadModelURI(uri, rs);

				val Set<EPackage> result = new HashSet<EPackage>();
				for (EObject c : model.getContents()) {
					if(c instanceof EPackage)
						result.add(c as EPackage);
				}
			result;
			}

		generateAddon(mmName, pluginName, project, inputMetamodel, replace, monitor)
	}

	/**
	 * Central operation of the class, that calls business operations
	 */
	public def static void generateAddon(String mmName, String pluginName, IJavaProject project,
		Set<EPackage> inputMetamodel, boolean replace, IProgressMonitor monitor) throws CoreException {

		// We look for an existing project with this name
		val IProject existingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginName);
		if(existingProject.exists()) {

			// If we replace, we delete it
			if(replace) {
				existingProject.delete(true, monitor);
			}
			// Else, error
			else {
				throw new CoreException(
					new Status(Status.ERROR, "fr.inria.diverse.trace.gemoc.ui",
						"Impossible to create a trace addon: a project already exists with this name."));
			}
		}

		try {

			// Then we call all our business operations
			// TODO handle languages defined with multiple ecores
			val EPackage extendedMetamodel = inputMetamodel.iterator().next();
			val EventsMetamodelGenerator eventsgen = new EventsMetamodelGenerator(project, mmName, extendedMetamodel);
			eventsgen.generate();
			val ExecutionExtensionGenerator extgen = new ExecutionExtensionGenerator(extendedMetamodel);
			extgen.generate();
			val GenericEngineTraceAddonGenerator traceaddgen = new GenericEngineTraceAddonGenerator(extendedMetamodel,
				extgen.getMmextensionResult(), eventsgen.getEventsMM(), pluginName);
			traceaddgen.generateCompleteAddon(monitor);
		} catch(IOException e) {

			// TODO Do real error handling
			e.printStackTrace();
		}
	}

	def static void generateAddon(IFile languageDefinitionFile, IProgressMonitor monitor) {

		// Loading languagedef model
		val ResourceSet rs = new ResourceSetImpl();
		val URI uri = URI.createPlatformResourceURI(languageDefinitionFile.getFullPath().toString(), true);
		val Resource model = EMFUtil.loadModelURI(uri, rs);
		val EObject languageDefinition = model.contents.get(0)

		// Follow-up in other operation...
		if(languageDefinition instanceof LanguageDefinition) {
			generateAddon(languageDefinition, languageDefinitionFile.project, monitor, rs)
		}

	}

	def static void generateAddon(LanguageDefinition languageDefinition, IProject xDSMLProject, IProgressMonitor monitor,
		ResourceSet rs) {

		// Getting DSA project
		val IProject k3DSAIProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
			languageDefinition.getDsaProject().getProjectName());
		val IJavaProject k3DSAIJavaProject = JavaCore.create(k3DSAIProject);

		// Getting languagename
		val String languageName = languageDefinition.name

		// Getting input ecore
		val IProject emfProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
			languageDefinition.domainModelProject.projectName)
		val Set<EPackage> inputMetamodel = findAllEPackagesIn(#{emfProject as IContainer})

		// Computing output plugin name
		val pluginName = XDSMLProjectHelper.baseProjectName(xDSMLProject) + ".trace"

		// Calling operation that calls business stuff
		generateAddon(languageName, pluginName, k3DSAIJavaProject, inputMetamodel, true, monitor)

	}
}