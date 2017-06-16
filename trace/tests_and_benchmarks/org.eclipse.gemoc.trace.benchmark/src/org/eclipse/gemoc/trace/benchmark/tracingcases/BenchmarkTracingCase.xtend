package org.eclipse.gemoc.trace.benchmark.tracingcases

import org.eclipse.gemoc.trace.benchmark.languages.BenchmarkLanguage
import org.eclipse.gemoc.trace.benchmark.runconf.BenchmarkRunConfiguration
import org.eclipse.gemoc.trace.benchmark.utils.EngineHelper
import java.util.Set
import org.eclipse.emf.common.util.URI

abstract class BenchmarkTracingCase {

	private val engineHelper = new EngineHelper
	private val Set<String> traceAddons
	public val String traceFolder
	
	protected new(Set<String> traceAddons, String traceFolder) {
		this.traceAddons = traceAddons
		this.traceFolder = traceFolder
	}
	
	def long initialize(URI model, BenchmarkLanguage language) {
		val runConf = new BenchmarkRunConfiguration(model, language, traceAddons)
		engineHelper.removeStoppedEngines
		val t = System.nanoTime
		engineHelper.prepareEngine(runConf)
		System.nanoTime - t
	}
	
	def execute() {
		engineHelper.execute
	}
	
	def getSimpleName() {
		"generated_traces"
	}

}
