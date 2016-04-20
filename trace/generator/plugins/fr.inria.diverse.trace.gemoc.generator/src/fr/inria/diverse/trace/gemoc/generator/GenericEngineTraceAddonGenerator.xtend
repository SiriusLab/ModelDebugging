package fr.inria.diverse.trace.gemoc.generator

import ecorext.Ecorext
import ecorext.Rule
import fr.inria.diverse.trace.commons.CodeGenUtil
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.commons.ManifestUtil
import fr.inria.diverse.trace.commons.PluginXMLHelper
import fr.inria.diverse.trace.metamodel.generator.TraceMMGenerationTraceability
import fr.inria.diverse.trace.plugin.generator.GenericTracePluginGenerator
import java.util.List
import java.util.Set
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint
import org.jdom2.Element
import org.jdom2.filter.ElementFilter

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
	private var Set<GenPackage> genPackages

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
	
	private def String getJavaFQN(EClassifier c) {
		return getJavaFQN(c,false)
	}
	
	private def String getJavaFQN(EClassifier c, boolean enforcePrimitiveJavaClass) {
		return EcoreCraftingUtil.getJavaFQN(c,genPackages,enforcePrimitiveJavaClass)
	}

	public def void generateCompleteAddon(IProgressMonitor m) {

		// Generate trace plugin
		val GenericTracePluginGenerator GenericTracePluginGenerator = new GenericTracePluginGenerator(abstractSyntax, executionEcorExt,
			pluginName, true)
		GenericTracePluginGenerator.generate(m)

		// Retrieving some info from the plugin generation
		packageQN = GenericTracePluginGenerator.packageQN
		className = GenericTracePluginGenerator.languageName.replaceAll(" ", "").toFirstUpper + "EngineAddon"
		traceManagerClassName = GenericTracePluginGenerator.traceManagerClassName
		stepFactoryClassName = GenericTracePluginGenerator.languageName.replaceAll(" ", "").toFirstUpper + "StepFactory"
		traceability = GenericTracePluginGenerator.traceability
		genPackages = GenericTracePluginGenerator.referencedGenPackages

		// Add dependency to plugin containing AbstractTraceAddon
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "fr.inria.diverse.trace.gemoc")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "fr.inria.diverse.trace.gemoc.api")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "org.gemoc.xdsmlframework.api")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "org.gemoc.executionframework.engine.mse.model")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "org.gemoc.sequential_addons.multidimensional.timeline")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "fr.obeo.timeline")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "fr.inria.diverse.trace.commons")
		ManifestUtil.addToPluginManifest(GenericTracePluginGenerator.project, m, "org.gemoc.xdsmlframework.api")
		
		
		
		ManifestUtil.setRequiredExecutionEnvironmentToPluginManifest(GenericTracePluginGenerator.project, m, "JavaSE-1.8")
		// Getting java fragment to create classes
		val IPackageFragment fragment = GenericTracePluginGenerator.packageFragment

		// Generate trace engine addon class (same package as the trace manager)
		val String prettyCode = CodeGenUtil.formatJavaCode(generateAddonClassCode())
		fragment.createCompilationUnit(className + ".java", prettyCode, true, m)
		
		// Generate step factory class (same package as the trace manager)
		val String uglyFactoryCode = generateStepFactory 
		val String prettyCodeStepFactory = CodeGenUtil.formatJavaCode(uglyFactoryCode)
		fragment.createCompilationUnit(stepFactoryClassName + ".java", prettyCodeStepFactory, true, m)



		// Add extension point (taken from GemocLanguageDesignerBuilder)
		this.project = GenericTracePluginGenerator.project
		val IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		val PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		val Element extensionPoint = helper.getOrCreateExtensionPoint(
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_CLASS, packageQN + "." + className
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_DEFAULT, "false"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_ID, pluginName
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_NAME, 
			GenericTracePluginGenerator.tracedLanguageName + " MultiDimensional Trace"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_SHORTDESCRIPTION, 
			"MultiDimensional Trace support dedicated to "+GenericTracePluginGenerator.tracedLanguageName+" language"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPENVIEWIDS, "org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView"
		);
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class «className» extends AbstractTraceAddon {
	
	private «stepFactoryClassName» factory = null;

	@Override
	public fr.inria.diverse.trace.gemoc.api.IGemocTraceManager constructTraceManager(Resource exeModel, Resource traceResource) {
		return new «traceManagerClassName»(exeModel,traceResource);
	}

	@Override
	public fr.inria.diverse.trace.gemoc.api.IGemocTraceManager loadTrace(Resource exeModel, Resource traceResource) {
		«traceManagerClassName» manager = new «traceManagerClassName»(exeModel, traceResource);
		manager.loadTrace((«getJavaFQN(traceability.traceMMExplorer.traceClass)»)traceResource.getContents().get(0));
		return manager;
	}
	
	@Override
	public IStepFactory getFactory() {
		if (factory == null)
			factory = new «stepFactoryClassName»();
		return factory;
	}
	
	@Override
	public boolean isAddonForTrace(EObject root) {
		return root instanceof «getJavaFQN(traceability.traceMMExplorer.traceClass)»;
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
	public org.gemoc.executionframework.engine.mse.MSEOccurrence createMSEOccurrence(org.gemoc.executionframework.engine.mse.MSE mse, List<Object> parameters, List<Object> result) {

		String stepRule = fr.inria.diverse.trace.commons.EcoreCraftingUtil.getFQN(mse.getCaller().eClass(),".") + "." + mse.getAction().getName();
		org.gemoc.executionframework.engine.mse.MSEOccurrence mseocc = null;


		«FOR Rule rule : executionEcorExt.rules.sortBy[baseFQN] SEPARATOR "else" AFTER "else"»

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
		mseocc = org.gemoc.executionframework.engine.mse.MseFactory.eINSTANCE.createMSEOccurrence();
		
		
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
