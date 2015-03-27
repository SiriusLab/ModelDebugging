package fr.inria.diverse.trace.gemoc.generator

import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.emf.common.util.URI
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.ui.PlatformUI
import org.gemoc.gemoc_language_workbench.ui.builder.pde.PluginXMLHelper
import org.jdom2.Element
import org.jdom2.filter.ElementFilter
import fr.inria.diverse.trace.commons.ManifestUtil

class GenericEngineTraceAddonGenerator {

	// Inputs
	private val URI abstractSyntaxEcoreURI
	private val URI executionEcorExtURI
	private val URI eventsMetamodelURI
	private val String pluginName

	// Transient
	private var String packageQN
	private var String className
	private var String traceManagerClassName

	new(URI abstractSyntaxEcoreURI, URI executionEcorExtURI, URI eventsMetamodelURI, String pluginName) {
		this.abstractSyntaxEcoreURI = abstractSyntaxEcoreURI
		this.executionEcorExtURI = executionEcorExtURI
		this.eventsMetamodelURI = eventsMetamodelURI
		this.pluginName = pluginName
	}

	public def void generateCompleteAddon() {

		// Generate trace plugin
		val GenericTracePluginGenerator a = new GenericTracePluginGenerator(abstractSyntaxEcoreURI, executionEcorExtURI,
			eventsMetamodelURI, pluginName)
		a.generate

		// Retrieving some info from the plugin generation
		packageQN = a.packageQN
		className = a.languageName.replaceAll(" ", "").toFirstUpper + "EngineAddon"
		traceManagerClassName = a.traceManagerClassName

		// Need a monitor for few actions
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true,
			[ m |
				// Generate trace engine addon class (same package as the trace manager)
				val String prettyCode = CodeGenUtil.formatJavaCode(generateAddonClassCode())
				val IPackageFragment fragment = a.packageFragment
				fragment.createCompilationUnit(className + ".java", prettyCode, true, m)
				// Add dependency to plugin containing AbstractTraceAddon
				ManifestUtil.addToPluginManifest(a.project, m, "fr.inria.diverse.trace.gemoc")
				ManifestUtil.addToPluginManifest(a.project, m, "org.gemoc.gemoc_language_workbench.api")
				ManifestUtil.addToPluginManifest(a.project, m, "org.gemoc.execution.engine.trace.model")
			])

		// Add extension point :( (taken from GemocLanguageDesignerBuilder)
		val IProject project = a.project
		val IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		val PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		val Element extensionPoint = helper.getOrCreateExtensionPoint("org.gemoc.gemoc_language_workbench.engine_addon");
		updateDefinitionAttributeInExtensionPoint(extensionPoint, "Class", packageQN + "." + className);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, "Default", "true");
		updateDefinitionAttributeInExtensionPoint(extensionPoint, "Name", a.languageName + " Addon");
		helper.saveDocument(pluginfile);
	}

	private def static Element updateDefinitionAttributeInExtensionPoint(Element extensionPoint, String atributeName,
		String value) {
		var Element result;
		val String defName = "Addon"
		val List<Element> elements = extensionPoint.getContent(new ElementFilter(defName));
		if (elements.size() == 0) {

			// create extension point
			result = new Element(defName);
			extensionPoint.addContent(result);
		} else {
			result = elements.get(0);
		}
		result.setAttribute(atributeName, value);
		return result;
	}

	private def String generateAddonClassCode() {
		return '''package «packageQN»;

import fr.inria.diverse.trace.api.ITraceManager;
import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon;

import org.eclipse.emf.ecore.resource.Resource;

public class «className» extends AbstractTraceAddon {

	@Override
	public ITraceManager constructTraceManager(Resource exeModel, Resource traceResource) {
		return new «traceManagerClassName»(exeModel,traceResource);
	}

}'''
	}

}
