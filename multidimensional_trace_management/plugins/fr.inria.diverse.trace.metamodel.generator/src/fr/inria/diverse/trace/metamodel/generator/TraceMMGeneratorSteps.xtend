package fr.inria.diverse.trace.metamodel.generator

import ecorext.Ecorext
import ecorext.Rule
import fr.inria.diverse.trace.commons.EcoreCraftingUtil
import fr.inria.diverse.trace.commons.tracemetamodel.StepStrings
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory

import static fr.inria.diverse.trace.commons.EcoreCraftingUtil.*

class TraceMMGeneratorSteps {

	// Inputs
	private val Ecorext mmext
	private val TraceMMExplorer traceMMExplorer

	// Inputs/Outputs
	private val EPackage tracemmresult
	private val TraceMMGenerationTraceability traceability

	new(Ecorext mmext, EPackage tracemmresult, TraceMMGenerationTraceability traceability,
		TraceMMExplorer traceMMExplorer) {
		this.traceability = traceability
		this.tracemmresult = tracemmresult
		this.traceMMExplorer = traceMMExplorer
		this.mmext = mmext

	}

	def Set<Rule> gatherOverrides(Rule rule) {
		val Set<Rule> result = new HashSet
		result.add(rule)
		result.addAll(rule.overridenBy)
		for (ov : rule.overridenBy) {
			result.addAll(gatherOverrides(ov))
		}
		return result
	}

	private def Set<Rule> gatherStepCalls(Rule rule, Set<Rule> inProgress) {
		val Set<Rule> result = new HashSet
		// To avoid cycles
		if (!inProgress.contains(rule)) {
			inProgress.add(rule)
			// Case step rule: stop
			if (rule.isStepRule) {
				result.add(rule)
			} // Case non step, recursive
			else {
				for (called : rule.calledRules) {
					val gathered = gatherStepCalls(called, inProgress)
					result.addAll(gathered)
				}
			}
		}

		return result
	}

	private Map<Rule, EClass> stepRuleToClass = new HashMap

	private def getStepClass(Rule stepRule) {
		if (stepRuleToClass.containsKey(stepRule)) {
			return stepRuleToClass.get(stepRule)
		} else {
			val stepClass = EcoreFactory.eINSTANCE.createEClass
			traceMMExplorer.eventsPackage.EClassifiers.add(stepClass)
			stepRuleToClass.put(stepRule, stepClass)
			return stepClass
		}
	}

	public def process() {

		val Set<Rule> allRules = new HashSet
		allRules.addAll(mmext.rules)
		val stepRules = allRules.filter[r|r.isStepRule].toSet

		// Flatten the rule graph regarding function overrides
		for (rule : allRules) {
			rule.calledRules.addAll(rule.calledRules.map[cr|gatherOverrides(cr)].flatten.toSet)
		}

		// "Merge" normal rules into step rules (ie. inlining)
		for (rule : stepRules) {
			val calledNonStepRules = rule.calledRules.filter[r|!r.isStepRule].toSet
			// For each called non step rule
			for (called : calledNonStepRules) {
				val Set<Rule> inProgress = new HashSet
				// We gather all step rules transitively called
				val gathered = gatherStepCalls(called, inProgress)
				rule.calledRules.addAll(gathered)
			}
			// And we remove the calls to the non step rules
			rule.calledRules.removeAll(calledNonStepRules)
		}
		// Now "stepRules" contains a set of step rules that only call other step rules
		// We directly have the information for the big/small steps creation
		// -----------------------------------------
		for (stepRule : stepRules) {

			// Creation of the step class (or reuse)
			val stepClass = getStepClass(stepRule)

			// Default basic name
			stepClass.name = stepRule.operation.name

			// We put a single "this" parameter
			EcoreCraftingUtil.addReferenceToClass(stepClass, "this", stepRule.containingClass)

			// And a FQN name
			stepClass.name = StepStrings.stepClassName(stepRule.containingClass, stepRule.operation)

			// Link EventsTraces -> Event class
			val ref = addReferenceToClass(traceMMExplorer.eventsClass,
				TraceMMStrings.ref_createEventsTracesToEvent(stepClass), stepClass)

			ref.lowerBound = 0
			ref.upperBound = -1
			ref.containment = true
			traceability.addEventTrace(stepClass, ref)
			traceability.addEventClass(stepClass)

			// Case Small Step
			if (stepRule.calledRules.isEmpty) {

				// Adding inheritance to Event abstract class
				stepClass.ESuperTypes.add(traceMMExplorer.eventOccClass)

			} // Case Big Step
			else {

				traceability.addMacroEventClass(stepClass)

				// Adding inheritance to MacroEvent abstract class
				stepClass.ESuperTypes.add(traceMMExplorer.macroEventClass)

				// SubStepSuperClass
				val EClass subStepSuperClass = EcoreFactory.eINSTANCE.createEClass
				traceMMExplorer.eventsPackage.EClassifiers.add(subStepSuperClass)
				subStepSuperClass.name = StepStrings.abstractSubStepClassName(stepRule.containingClass,
					stepRule.operation)
				subStepSuperClass.abstract = true

				// Link StepClass -> SubStepSuperClass
				val ref2 = EcoreCraftingUtil.addReferenceToClass(stepClass, StepStrings.ref_BigStepToSub,
					subStepSuperClass)
				ref2.ordered = true
				ref2.containment = false
				ref2.lowerBound = 0
				ref2.upperBound = -1

				// Fill step class
				val EClass fillStepClass = EcoreFactory.eINSTANCE.createEClass
				traceMMExplorer.eventsPackage.EClassifiers.add(fillStepClass)
				fillStepClass.name = StepStrings.fillStepClassName(stepRule.containingClass, stepRule.operation)

				// Inheritance Fill > SubStepSuper
				fillStepClass.ESuperTypes.add(subStepSuperClass)

				for (calledStepRule : stepRule.calledRules) {

					// For each called step rule, we create an step class (if not created already)
					val EClass subStepClass = getStepClass(calledStepRule)

					// Inheritance SubStep -> SubStepSuper
					subStepClass.ESuperTypes.add(subStepSuperClass)

				}

			}

		}

	}
}