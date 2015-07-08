package fr.inria.diverse.tracemm.xmof2tracematerial

import org.eclipse.emf.ecore.EClass
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter

class ExtractorStringsCreator {

	public static val String ref_StepToEntry = "correspondingEntryStep"

	public static val String ref_StepToThis = "thisParam"

	static def String class_createStepClassName(EClass confClass, Activity activity) {
		return fr.inria.diverse.tracemm.xmof2tracematerial.ExtractorStringsCreator.class_createStepClassName(confClass, activity.name)
	}

	static def String class_createStepClassName(EClass confClass, String activityName) {
		return confClass.name.replace("Configuration", "") + "_" + activityName + "EntryStepOccurrence"
	}

	static def String ref_createStepToParam(DirectedParameter param) { return fr.inria.diverse.tracemm.xmof2tracematerial.ExtractorStringsCreator.ref_createStepToParam(param.name) }

	static def String ref_createStepToParam(String paramName) { return paramName + "Param" }

	static def String ref_createStepToReturn(DirectedParameter param) { return fr.inria.diverse.tracemm.xmof2tracematerial.ExtractorStringsCreator.ref_createStepToReturn(param.name) }

	static def String ref_createStepToReturn(String paramName) { return paramName + "Return" }

}
