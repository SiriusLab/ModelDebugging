package fr.inria.diverse.tracemm.xmof2tracematerial

import org.eclipse.emf.ecore.EClass
import org.modelexecution.xmof.Syntax.Activities.IntermediateActivities.Activity
import org.modelexecution.xmof.Syntax.Classes.Kernel.DirectedParameter

class ExtractorStringsCreator {

	public static val String ref_ExitToEntry = "correspondingEntryEvent"

	public static val String ref_EventToThis = "thisParam"

	static def String class_createEntryEventClassName(EClass confClass, Activity activity) {
		class_createEntryEventClassName(confClass, activity.name)
	}

	static def String class_createEntryEventClassName(EClass confClass, String activityName) {
		confClass.name.replace("Configuration", "") + "_" + activityName + "EntryEventOccurrence"
	}

	static def String class_createExitEventClassName(EClass confClass, String activityName) {
		confClass.name.replace("Configuration", "") + "_" + activityName + "ExitEventOccurrence"
	}

	static def String class_createExitEventClassName(EClass confClass, Activity activity) {
		class_createExitEventClassName(confClass, activity.name)
	}

	static def String ref_createEntryToParam(DirectedParameter param) { ref_createEntryToParam(param.name) }

	static def String ref_createEntryToParam(String paramName) { paramName + "Param" }

	static def String ref_createExitToReturn(DirectedParameter param) { ref_createExitToReturn(param.name) }

	static def String ref_createExitToReturn(String paramName) { paramName + "Return" }

}
