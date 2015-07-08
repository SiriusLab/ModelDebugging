package fr.inria.diverse.tracemm.xmof2tracematerial

import org.eclipse.emf.ecore.EClass
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter

class ExtractorStringsCreator {

	public static val String ref_ExitToEntry = "correspondingEntryStep"

	public static val String ref_StepToThis = "thisParam"

	static def String class_createEntryStepClassName(EClass confClass, Activity activity) {
		return class_createEntryStepClassName(confClass, activity.name)
	}

	static def String class_createEntryStepClassName(EClass confClass, String activityName) {
		return confClass.name.replace("Configuration", "") + "_" + activityName + "EntryStepOccurrence"
	}

	static def String class_createExitStepClassName(EClass confClass, String activityName) {
		return confClass.name.replace("Configuration", "") + "_" + activityName + "ExitStepOccurrence"
	}

	static def String class_createExitStepClassName(EClass confClass, Activity activity) {
		return class_createExitStepClassName(confClass, activity.name)
	}

	static def String ref_createEntryToParam(DirectedParameter param) { return ref_createEntryToParam(param.name) }

	static def String ref_createEntryToParam(String paramName) { return paramName + "Param" }

	static def String ref_createExitToReturn(DirectedParameter param) { return ref_createExitToReturn(param.name) }

	static def String ref_createExitToReturn(String paramName) { return paramName + "Return" }

}
