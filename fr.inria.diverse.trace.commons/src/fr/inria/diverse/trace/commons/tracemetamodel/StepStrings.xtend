package fr.inria.diverse.trace.commons.tracemetamodel

import org.eclipse.emf.ecore.EClass
import fr.inria.diverse.trace.commons.EcoreCraftingUtil

class StepStrings {

	public static val String fillEventSuffix = "_FillEvent";

	public static val String abstractSubStepSuffix = "_AbstractSubEvent";

	public static val String globalFillEventName = "FillEvent";

	public static val String package_BigSteps = "BigSteps";

	public static val String ref_BigStepToSub = "subSteps";

	public static def String fillEventClassName(EClass macroEventClass) {
		return macroEventClass.getName() + fillEventSuffix;
	}

	public static def String stepClassName(EClass containingClass, String ruleName) {
		val String prefix = EcoreCraftingUtil.getFQN(containingClass, "_").toFirstUpper + "_"
		return prefix + ruleName.toFirstUpper
	}

	public static def String subStepClassName(EClass containingClass, String ruleName) {
		val String prefix = EcoreCraftingUtil.getFQN(containingClass, "_").toFirstUpper + "_"
		return prefix + ruleName.toFirstUpper
	}

	public static def String abstractSubStepClassName(EClass containingClass, String ruleName) {
		return stepClassName(containingClass, ruleName) + abstractSubStepSuffix
	}

	public static def String fillEventClassName(EClass containingClass, String ruleName) {
		return stepClassName(containingClass, ruleName) + fillEventSuffix
	}

}
