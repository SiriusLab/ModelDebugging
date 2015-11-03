package fr.inria.diverse.trace.commons.tracemetamodel

import org.eclipse.emf.ecore.EClass
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import org.eclipse.emf.ecore.EOperation

class StepStrings {

	public static val String fillStepSuffix = "_FillStep";

	public static val String abstractSubStepSuffix = "_AbstractSubStep";

	public static val String globalFillStepName = "FillStep";

	public static val String package_BigSteps = "BigSteps";

	public static val String ref_BigStepToSub = "subSteps";

	public static def String fillStepClassName(EClass macroStepClass) {
		return macroStepClass.getName() + fillStepSuffix;
	}

	public static def String stepClassName(EClass containingClass, EOperation rule) {
		val String prefix =
		if (containingClass != null) {
			EcoreCraftingUtil.getFQN(containingClass, "_").toFirstUpper + "_"	
		} else {
			"Root_"
		}
		return prefix + rule.name.toFirstUpper
	}

	public static def String subStepClassName(EClass containingClass, EOperation rule) {
		stepClassName(containingClass,rule)
	}

	public static def String abstractSubStepClassName(EClass containingClass, EOperation rule) {
		return stepClassName(containingClass, rule) + abstractSubStepSuffix
	}

	public static def String fillStepClassName(EClass containingClass, EOperation rule) {
		return stepClassName(containingClass, rule) + fillStepSuffix
	}

}
