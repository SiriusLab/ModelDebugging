package fr.inria.diverse.trace.commons.tracemetamodel

import org.eclipse.emf.ecore.EClass
import fr.inria.diverse.trace.commons.EcoreCraftingUtil

class StepStrings {

	public static val String fillStepSuffix = "_FillStep";

	public static val String abstractSubStepSuffix = "_AbstractSubStep";

	public static val String globalFillStepName = "FillStep";

	public static val String package_BigSteps = "BigSteps";

	public static val String ref_BigStepToSub = "subSteps";

	public static def String fillStepClassName(EClass macroStepClass) {
		return macroStepClass.getName() + fillStepSuffix;
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

	public static def String fillStepClassName(EClass containingClass, String ruleName) {
		return stepClassName(containingClass, ruleName) + fillStepSuffix
	}

}
