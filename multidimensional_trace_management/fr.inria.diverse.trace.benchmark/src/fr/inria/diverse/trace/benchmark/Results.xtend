package fr.inria.diverse.trace.benchmark

import java.util.HashSet
import java.util.List
import java.util.Set
import java.util.ArrayList

class Results {

	private List<Result> results = new ArrayList<Result>

	new() {
	}

	def void addResult(Result r) {
		results.add(r)
	}

	static def Results merge(Set<Results> manyResults) {
		val Results someResults = manyResults.get(0);
		val ret = new Results()
		for (var int i = 0; i < someResults.results.size; i++) {
			val Set<Result> iLines = new HashSet()
			for (results : manyResults) {
				iLines.add(results.results.get(i))
			}
			ret.addResult(Result.merge(iLines))
		}
		return ret
	}

	override public String toString() {
		return '''«Result.getColumnNames»
«FOR r : results»
«r.toString»
«ENDFOR»'''
	}

}
