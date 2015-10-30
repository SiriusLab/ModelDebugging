package fr.inria.diverse.trace.benchmark

import java.util.Set

//model_size,nbMut,timeStep,traceSize,traceMemoryFootprint,meanJumpTime,allJumpTimes
class Result {

	public String languageName = ""
	public String debuggerName = ""
	public String modelName = ""
	public Integer modelNbElements = 0
	public Integer modelNbMutFields = 0
	public Long timeExe = new Long(0)
	public Long timeInit = new Long(0)
	public Long timeJumpMean = new Long(0)
	public Integer traceNbStates = 0
	public Integer traceMemoryFootprint = 0
	public Integer nbRetries = 0
	

	/**
 * TODO
 */
	def static Result merge(Set<Result> results) {
		
//		if (results.size < 2)
//			throw new Exception ("Cannot merge less than two results!")
		
		val ret = new Result

		for (p : Result.declaredFields) {
			switch (p.type) {
				Class<String>: {
					p.set(ret, p.get(results.get(0)))
				}
				Class<Long>: {
					var long sum = 0
					for (r : results) {
						sum = sum + p.getLong(r)
					}
					val long mean = sum / results.size
					p.setLong(ret, mean)
				}
				Class<Integer>: {
					var int sum = 0
					for (r : results) {
						sum = sum + p.getInt(r)
					}
					val int mean = sum / results.size
					p.setInt(ret, mean)
				}
			}
		}

		return ret
	}

	def static String getColumnNames() {
		val allNames = Result.declaredFields.map[f|f.name]
		return allNames.join(",")
	}

	override toString() {
		return Result.declaredFields.map[f|
			try {
				f.get(this)
			} catch(IllegalAccessException exc) {
				throw new RuntimeException("auto-generated try/catch", exc)
			}].join(",")

	}

}
