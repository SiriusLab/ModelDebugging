package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.commons.PluginXMLHelper
import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.List
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.ecore.EPackage
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors
import org.jdom2.Element
import org.jdom2.filter.ElementFilter
import ecorext.Rule
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EClass

class GenericEngineTraceAddonGenerator {

	// Inputs
	private val EPackage abstractSyntax // URI
	private val Ecorext executionEcorExt // URI
	private val String pluginName
	
	// Transient
	private var String packageQN
	private var String className
	private var String traceManagerClassName
	private var String stepFactoryClassName
	private var TraceMMGenerationTraceability traceability

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	IProject project
	
	

	new(EPackage abstractSyntax, Ecorext executionEcorExt, String pluginName) {
		this.abstractSyntax = abstractSyntax
		this.executionEcorExt = executionEcorExt
		this.pluginName = pluginName
	}

	public def void generateCompleteAddon() {
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true, [ m |
			generateCompleteAddon(m)
		])
	}

	public static def String getBaseFQN(Rule r) {
		val EOperation o = r.operation
		val EClass c = r.containingClass
		return EcoreCraftingUtil.getBaseFQN(c) + "." + o.name
	}

	public def void generateCompleteAddon(IProgressMonitor m) {

		// Generate trace plugin
		val GenericTracePluginGenerator a = new GenericTracePluginGenerator(abstractSyntax, executionEcorExt,
			pluginName, true)
		a.generate(m)

		// Retrieving some info from the plugin generation
		packageQN = a.packageQN
		className = a.languageName.replaceAll(" ", "").toFirstUpper + "EngineAddon"
		traceManagerClassName = a.traceManagerClassName
		stepFactoryClassName = a.languageName.replaceAll(" ", "").toFirstUpper + "StepFactory"
		traceability = a.traceability

		// Add dependency to plugin containing AbstractTraceAddon
		ManifestUtil.addToPluginManifest(a.project, m, "fr.inria.diverse.trace.gemoc")
		ManifestUtil.addToPluginManifest(a.project, m, "fr.inria.diverse.trace.gemoc.api")
		ManifestUtil.addToPluginManifest(a.project, m, "org.gemoc.xdsmlframework.api")
		ManifestUtil.addToPluginManifest(a.project, m, "org.gemoc.executionframework.engine.mse.model")
		ManifestUtil.addToPluginManifest(a.project, m, "org.gemoc.sequential_addons.multidimensional.timeline")
		ManifestUtil.addToPluginManifest(a.project, m, "fr.obeo.timeline")
		ManifestUtil.addToPluginManifest(a.project, m, "fr.inria.diverse.trace.commons")

		// Getting java fragment to create classes
		val IPackageFragment fragment = a.packageFragment

		// Generate trace engine addon class (same package as the trace manager)
		val String prettyCode = CodeGenUtil.formatJavaCode(generateAddonClassCode())
		fragment.createCompilationUnit(className + ".java", prettyCode, true, m)
		
		// Generate step factory class (same package as the trace manager)
		val String uglyFactoryCode = generateStepFactory 
		val String prettyCodeStepFactory = CodeGenUtil.formatJavaCode(uglyFactoryCode)
		fragment.createCompilationUnit(stepFactoryClassName + ".java", prettyCodeStepFactory, true, m)



		// Add extension point (taken from GemocLanguageDesignerBuilder)
		this.project = a.project
		val IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		val PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		val Element extensionPoint = helper.getOrCreateExtensionPoint(
			"org.gemoc.gemoc_language_workbench.engine_addon");
		updateDefinitionAttributeInExtensionPoint(extensionPoint, "Class", packageQN + "." + className);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, "Default", "false");
		updateDefinitionAttributeInExtensionPoint(extensionPoint, "id", pluginName);
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

import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon;
import fr.inria.diverse.trace.gemoc.api.IStepFactory;

import org.eclipse.emf.ecore.resource.Resource;

public class «className» extends AbstractTraceAddon {

	@Override
	public fr.inria.diverse.trace.gemoc.api.IGemocTraceManager constructTraceManager(Resource exeModel, Resource traceResource) {
		return new «traceManagerClassName»(exeModel,traceResource);
	}
	
	private «stepFactoryClassName» factory = null;
	
	@Override
	public IStepFactory getFactory() {
		if (factory == null)
			factory = new «stepFactoryClassName»();
		return factory;
	}

}'''
	}
	
	private def String generateStepFactory() {
		return '''
		
	package «packageQN»;
		
	import java.util.List;
	import fr.inria.diverse.trace.gemoc.api.IStepFactory;

	public class «stepFactoryClassName» implements IStepFactory {	
		
	@Override
	public org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence createMSEOccurrence(org.gemoc.execution.engine.mse.engine_mse.MSE mse, List<Object> parameters, List<Object> result) {

		String stepRule = fr.inria.diverse.trace.commons.EcoreCraftingUtil.getFQN(mse.getCaller().eClass(),".") + "." + mse.getAction().getName();
		org.gemoc.execution.engine.mse.engine_mse.MSEOccurrence mseocc = null;


		«FOR Rule rule : executionEcorExt.rules.sortBy[baseFQN] SEPARATOR "else"»

			«val stepCallerClass = rule.containingClass»
			«val possibleCallerClasses = abstractSyntax.EClassifiers
				.filter[c|c instanceof EClass]
				.map[c|c as EClass]
				.filter[c|c.equals(stepCallerClass)||c.EAllSuperTypes.contains(stepCallerClass)]
				.sortBy[name]»
			«IF possibleCallerClasses.empty»
			if (stepRule.equalsIgnoreCase("«getBaseFQN(rule)»")) {
			«ELSE»
			if (
			«FOR possibleCallerClass: possibleCallerClasses SEPARATOR " || "»
				stepRule.equalsIgnoreCase("«EcoreCraftingUtil.getFQN(possibleCallerClass, ".") + "." + rule.operation.name»")
			«ENDFOR»
			) {
			«ENDIF»
			mseocc = «EcoreCraftingUtil.stringCreate(traceability.getStepClassFromStepRule(rule))»;
			} 
			
		«ENDFOR»

		else {
			mseocc = org.gemoc.execution.engine.mse.engine_mse.Engine_mseFactory.eINSTANCE.createMSEOccurrence();
		}
		
		if (mseocc != null) {
			mseocc.setMse(mse);
			mseocc.getParameters().addAll(parameters);
			mseocc.getResult().addAll(result);
		}
		return mseocc;
	}
	}
		'''
	}
		

}
