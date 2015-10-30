package fr.inria.diverse.trace.benchmark

import java.util.ArrayList
import java.util.List
import org.gemoc.gemoc_language_workbench.api.core.IRunConfiguration
import org.gemoc.gemoc_language_workbench.api.extensions.engine_addon.EngineAddonSpecificationExtension
import org.gemoc.gemoc_language_workbench.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint
import org.eclipse.emf.common.util.URI
import java.util.HashSet
import fr.inria.diverse.trace.benchmark.api.IDebuggerHelper
import fr.inria.diverse.trace.benchmark.debuggers.DSTraceDebuggerHelper

class BenchmarkRunConfiguration implements IRunConfiguration {

	val IDebuggerHelper debugger
	val Language language
	val URI model

	new(IDebuggerHelper debugger, Language language, URI model) {
		this.debugger = debugger
		this.language = language
		this.model = model
	}

	override getAnimationDelay() {
		return 0;
	}

	override getAnimatorURI() {
		return null;
	}

	override getDeadlockDetectionDepth() {
		return 0;
	}

	override getDeciderName() {
		return "Random decider"
	}

	override getEngineAddonExtensions() {

		// We find the addons we need
		val addons = new HashSet<String>()

		addons.addAll(debugger.getAddons())
		if(debugger instanceof DSTraceDebuggerHelper)
			addons.add(language.traceAddonName)

		// Then we find all corresponing addon objects and return them
		val List<EngineAddonSpecificationExtension> result = new ArrayList<EngineAddonSpecificationExtension>();
		val loadedAddons = EngineAddonSpecificationExtensionPoint.getSpecifications()
		for (ext : loadedAddons) {
			if(addons.contains(ext.name))
				result.add(ext)
		}
		return result
	}

	override getExecutedModelAsMelangeURI() {
		return getExecutedModelURI();
	}

	override getExecutedModelURI() {
		return model
	}

	override getExecutionEntryPoint() {
		return language.entryPoint
	}

	override getLanguageName() {
		return language.languageName
	}

	override getMelangeQuery() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

}
