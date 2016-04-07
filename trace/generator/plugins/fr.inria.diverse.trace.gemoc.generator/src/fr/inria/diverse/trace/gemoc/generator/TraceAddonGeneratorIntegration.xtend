package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace
import fr.inria.diverse.melange.ui.internal.MelangeActivator
import fr.inria.diverse.trace.commons.EMFUtil
import java.util.HashSet
import java.util.Set
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.xtext.ui.resource.IResourceSetProvider
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper

/**
 * Plenty of ways to call the generator in an eclipse context.
 * Must be provided a TraceAddonGeneratorIntegrationConfiguration, specific to the type of xdsml (K3, XMOF, etc)
 */
class TraceAddonGeneratorIntegration {

	private val TraceAddonGeneratorIntegrationConfiguration traceAddonGeneratorIntegrationConfiguration;

	new(TraceAddonGeneratorIntegrationConfiguration traceAddonGeneratorIntegrationConfiguration) {
		this.traceAddonGeneratorIntegrationConfiguration = traceAddonGeneratorIntegrationConfiguration
	}


	def void generateAddon(IFile melangeFile, String selectedLanguage, boolean replace, IProgressMonitor monitor) {
		// Computing output plugin name
		val pluginName = MelangeXDSMLProjectHelper.baseProjectName(melangeFile.project) + "." +
			selectedLanguage.toLowerCase + ".trace"
		generateAddon(melangeFile, selectedLanguage, pluginName, replace, monitor)
	}

	def void generateAddon(IFile melangeFile, String selectedLanguage, String pluginName, boolean replace,
		IProgressMonitor monitor) {

		// Loading Melange model
		val URI uri = URI.createPlatformResourceURI(melangeFile.getFullPath().toString(), true);
		val injector = MelangeActivator.getInstance().getInjector(MelangeActivator.FR_INRIA_DIVERSE_MELANGE_MELANGE)
		val IResourceSetProvider provider = injector.getInstance(typeof(IResourceSetProvider))
		val ResourceSet resSet = provider.get(melangeFile.getProject())
		val Resource resource = resSet.getResource(uri, true)
		val ModelTypingSpace root = resource.getContents().get(0) as ModelTypingSpace
		val Language selection = root.elements.filter(Language).findFirst[name == selectedLanguage]

		// Get syntax
		val rs = new ResourceSetImpl
		val inputMetamodel = new HashSet
		val URI mmUri = URI.createURI(selection.syntax.ecoreUri)
		val Resource model = EMFUtil.loadModelURI(mmUri, rs);
		val Set<EPackage> result = new HashSet<EPackage>();
		for (EObject c : model.getContents()) {
			if (c instanceof EPackage)
				result.add(c as EPackage);
		}
		inputMetamodel.addAll(result);

		val Ecorext mmextension = traceAddonGeneratorIntegrationConfiguration.getExecutionExtension(selection,
			selectedLanguage, melangeFile.project, inputMetamodel, rs);

		// Calling operation that calls business stuff
		TraceAddonGeneratorIntegrationHelper.generateAddon(selectedLanguage, pluginName, inputMetamodel, replace, monitor, mmextension)

	}
}