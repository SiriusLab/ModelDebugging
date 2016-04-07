package fr.inria.diverse.trace.gemoc.generator

import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace
import fr.inria.diverse.melange.ui.internal.MelangeActivator
import fr.inria.diverse.trace.commons.EMFUtil
import fr.inria.diverse.trace.plaink3.tracematerialextractor.K3ExecutionExtensionGenerator
import fr.inria.diverse.trace.plaink3.tracematerialextractor.K3StepExtractor
import java.io.IOException
import java.util.HashSet
import java.util.Set
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.resources.WorkspaceJob
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IType
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtext.ui.resource.IResourceSetProvider
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper

/**
 * Plenty of ways to call the generator in an eclipse context
 */
class K3TraceAddonGeneratorHelper {


	/**
	 * Central operation of the class, that calls business operations
	 */
	public def static void generateAddon(String mmName, String pluginName, Set<IType> aspects,
		Set<EPackage> inputMetamodel, boolean replace, IProgressMonitor monitor) throws CoreException {

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
					new Status(Status.ERROR, "fr.inria.diverse.trace.gemoc.ui",
						"Impossible to create a trace addon: a project already exists with this name."));
			}
		}

		try {

			// Then we call all our business operations
			// TODO handle languages defined with multiple ecores
			val EPackage extendedMetamodel = inputMetamodel.iterator().next();

			val K3ExecutionExtensionGenerator extgen = new K3ExecutionExtensionGenerator(extendedMetamodel);
			extgen.generate();

			val mmextension = extgen.mmextensionResult

			val K3StepExtractor eventsgen = new K3StepExtractor(aspects, mmName, extendedMetamodel, mmextension);
			eventsgen.generate();

			val GenericEngineTraceAddonGenerator traceaddgen = new GenericEngineTraceAddonGenerator(extendedMetamodel,
				mmextension, pluginName);
			traceaddgen.generateCompleteAddon(monitor);
		} catch (IOException e) {

			// TODO Do real error handling
			e.printStackTrace();
		}
	}

	def static void generateAddon(IFile melangeFile, String selectedLanguage, boolean replace,
		IProgressMonitor monitor) {
			// Computing output plugin name
		val pluginName = MelangeXDSMLProjectHelper.baseProjectName(melangeFile.project) + "." +
			selectedLanguage.toLowerCase + ".trace"
		generateAddon(melangeFile, selectedLanguage, pluginName, replace, monitor)
	}
	def static void generateAddon(IFile melangeFile, String selectedLanguage, String pluginName, boolean replace,
		IProgressMonitor monitor) {

		// Loading Melange model
		val URI uri = URI.createPlatformResourceURI(melangeFile.getFullPath().toString(), true);
		val injector = MelangeActivator.getInstance().getInjector(MelangeActivator.FR_INRIA_DIVERSE_MELANGE_MELANGE)
		val IResourceSetProvider provider = injector.getInstance(typeof(IResourceSetProvider))
		val ResourceSet resSet = provider.get(melangeFile.getProject())
		val Resource resource = resSet.getResource(uri, true)
		val ModelTypingSpace root = resource.getContents().get(0) as ModelTypingSpace

		// Get Aspects
		val Language selection = root.elements.filter(Language).findFirst[name == selectedLanguage]
		val aspectNames = selection.semantics.map[aspectTypeRef.type.qualifiedName].toList
		val IJavaProject javaProject = JavaCore.create(melangeFile.project);
		val aspectClasses = aspectNames.map[it|javaProject.findType(it)].toSet

		// Get syntax
		val inputMetamodel = new HashSet
		val URI mmUri = URI.createURI(selection.syntax.ecoreUri)
		val Resource model = EMFUtil.loadModelURI(mmUri, new ResourceSetImpl);
		val Set<EPackage> result = new HashSet<EPackage>();
		for (EObject c : model.getContents()) {
			if (c instanceof EPackage)
				result.add(c as EPackage);
		}
		inputMetamodel.addAll(result);

		// Calling operation that calls business stuff
		generateAddon(selectedLanguage, pluginName, aspectClasses, inputMetamodel, replace, monitor)

	}
}