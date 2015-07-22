import 'http://www.gemoc.org/sample/tfsm'
--import 'platform:/resource/org.gemoc.sample.tfsm.model/model/tfsm.ecore'
--import 'platform:/resource/org.gemoc.sample.tfsm.tfsmextended.model/model/TfsmExtended.ecore'
import _'http://www.eclipse.org/emf/2002/Ecore'


ECLimport "platform:/plugin/fr.inria.aoste.timesquare.ccslkernel.model/ccsllibrary/kernel.ccslLib"
ECLimport "platform:/plugin/fr.inria.aoste.timesquare.ccslkernel.model/ccsllibrary/CCSL.ccslLib"
--ECLimport "platform:/plugin/org.gemoc.sample.tfsm.moc.lib/ccsl/TFSMMoC.ccslLib"
ECLimport "platform:/resource/org.gemoc.sample.tfsm.moc.lib/ccsl/TFSMMoC.ccslLib"
ECLimport "platform:/resource/org.gemoc.sample.tfsm.moc.lib/ccsl/TFSMMoCC.moccml"

package tfsm
/**
 * @Public DSE
 */
	/**
	 * DSE linked to specific DSA
	 */ 
  	context FSMClock
     def: ticks : Event = self.ticks()

  	context Transition
     def: fire : Event = self.fire()
    
  	-- Mapped to the evaluation of the guard
  	context EvaluateGuard
     def : evaluate : Event = self.evaluate()--[result] switch case (true) force evaluatedTrue  ;
     											--			 case (false) force evaluatedFalse;
     														 
    
	/**
	 * DSE with no associated DSA
	 */ 
	-- The user should be allowed to inject events whenever needed.
	context FSMEvent
     def: occurs : Event = self.occurs()
    context State
     -- these events are tracked by the debugger thanks to the reference to self
     def : entering : Event = self.onEnter()
     def : leaving : Event = self
    
/**
 * @Private DSE
 */
	context EvaluateGuard
	 def : evaluatedTrue : Event  = self
	 def : evaluatedFalse : Event = self
	context TFSM
	 def: start : Event = self.init()
	context TimedSystem
	 def: start : Event = self.init() 
	

/**
 * MoC Constraints to AS association
 */ 
	context FSMEvent
		inv occursWhenSolicitate:
			(self.sollicitingTransitions->size() >0) implies  
			(let AllTriggeringOccurrences : Event = Expression Union(self.sollicitingTransitions.fire) in
			Relation FSMEventSendReceive(AllTriggeringOccurrences, self.occurs))
	
	context Transition
	    inv fireWhenRestrueOccursTransition: 
	    	(self.ownedGuard.oclIsKindOf(EvaluateGuard) and self.source.outgoingTransitions->select(t | (t) <> self)->size() = 0) implies
	    	let restrueOccursAfterOrWhileStateEntering :Event = Expression SampledOn(self.source.entering,self.ownedGuard.oclAsType(EvaluateGuard).evaluatedTrue) in
	    	Relation Coincides(restrueOccursAfterOrWhileStateEntering, self.fire) 
	    
	    inv fireWhenRestrueOccursVariousTransition:
			(self.ownedGuard.oclIsKindOf(EvaluateGuard) and self.source.outgoingTransitions->select(t | (t) <> self)->size() > 0) implies
			let otherFireFromTheSameState3: Event = Expression Union (self.source.outgoingTransitions->select(t | (t) <> self).fire) in
		 	Relation EventGuardedTransition(self.source.entering,
		 							self.ownedGuard.oclAsType(EvaluateGuard).evaluatedTrue,
		 							otherFireFromTheSameState3,
		 							self.fire
		 	) 
	    
	    -- there is non rule when ocurrs various evaluate transitions
		inv fireWhenEventOccursOneTransition:
			(self.ownedGuard.oclIsKindOf(EventGuard) and self.source.outgoingTransitions->select(t| (t) <> self)->size() = 0) implies
			let eventOccursAfterOrWhileStateEntering :Event = Expression SampledOn(self.source.entering,self.ownedGuard.oclAsType(EventGuard).triggeringEvent.occurs) in
			Relation Coincides(eventOccursAfterOrWhileStateEntering, self.fire) 
		
		inv fireWhenEventOccursVariousTransition:
			(self.ownedGuard.oclIsKindOf(EventGuard) and self.source.outgoingTransitions->select(t| (t) <> self)->size() > 0) implies
			let otherFireFromTheSameState2: Event = Expression Union (self.source.outgoingTransitions->select(t| (t) <> self).fire) in
		 	Relation EventGuardedTransition(self.source.entering,
		 							self.ownedGuard.oclAsType(EventGuard).triggeringEvent.occurs,
		 							otherFireFromTheSameState2,
		 							self.fire
		 	) 
		
		inv fireWhenTemporalGuardHoldsVariousTransition:
			(self.ownedGuard.oclIsKindOf(TemporalGuard) and self.source.outgoingTransitions->select(t| (t) <> self)->size() > 0) implies
			let guardDelay : Integer = self.ownedGuard.oclAsType(TemporalGuard).afterDuration in
			let otherFireFromTheSameState: Event = Expression Union (self.source.outgoingTransitions->select(t| (t) <> self).fire) in
			Relation TemporalGuardedTransition(self.source.entering,
									   self.ownedGuard.oclAsType(TemporalGuard).onClock.ticks,
									   otherFireFromTheSameState,
									   guardDelay,
									   self.fire
			) 

		inv fireWhenTemporalGuardHoldsOneTransition:
			(self.ownedGuard.oclIsKindOf(TemporalGuard) and self.source.outgoingTransitions->select(t| (t) <> self)->size() = 0) implies
			let delay : Integer = self.ownedGuard.oclAsType(TemporalGuard).afterDuration in
			let delayIsExpired_wrt_StateEntering :Event = Expression DelayFor(
															self.source.entering,
															self.ownedGuard.oclAsType(TemporalGuard).onClock.ticks,
															delay
			) in
			Relation Coincides(delayIsExpired_wrt_StateEntering, self.fire) 
		
		-- Evaluate guards is checked at the entering of the state 
		inv EvaluateGuardWhenEnteringState:
			(self.ownedGuard.oclIsKindOf(EvaluateGuard)) implies
			(Relation Coincides(self.ownedGuard.oclAsType(EvaluateGuard).evaluate, self.source.entering)) 
			
	context State
		inv enterOnceBeforeToLeave:
			Relation WeakAlternates(self.entering, self.leaving)  
		
		inv firingATransitionAlternatesWithLeavingState:
			(self.outgoingTransitions->size() > 0) implies
			let allFiredoutgoingTransition : Event = Expression Union(self.outgoingTransitions.fire) in
			Relation Coincides(allFiredoutgoingTransition, self.leaving)
		
		inv stateEntering1:
			(not (self = self.owningFSM.initialState)) implies
			let allInputTransition : Event = Expression Union(self.incomingTransitions.fire) in
			Relation Precedes(allInputTransition,self.entering)
			
		--no time elapsed between the fire and the entering (micro step)
		inv stateEntering2:
			(not (self = self.owningFSM.initialState)) and (self.owningFSM.localClock = null) implies --case of no local time
			let allInputTransition2 : Event = Expression Union(self.incomingTransitions.fire) in
			let allInputsSampledOnMinutes : Event = Expression SampledOn(allInputTransition2, self.owningFSM.oclAsType(ecore::EObject).eContainer().oclAsType(TimedSystem).globalClocks->first().ticks) in
			Relation Precedes(self.entering, allInputsSampledOnMinutes)

		
	context EvaluateGuard
		inv fireEvaluationAndResult:
			Relation BooleanGuardedTransitionRule (self.evaluate, self.evaluatedTrue, self.evaluatedFalse)	
			
	context TFSM
		inv oneStateAtATime:
			Relation Exclusion(self.ownedStates.entering)
			
		inv oneTransitionAtATime:
			(self.ownedStates.outgoingTransitions->size() > 1) implies
			(Relation Exclusion(self.ownedStates.outgoingTransitions.fire))
			
		inv firstIsInitialState:
			Relation Coincides(self.start, self.initialState.entering)
			
		inv firstOnlyOnce:
			let onlyOneFirst : Event = Expression OneTickAndNoMore(self.start) in
			Relation Coincides(self.start,onlyOneFirst)	
		
		
	
	context TimedSystem	
		
		inv startTimedSystemBeforeAllStartTFSM:
			let allStartTFSM : Event = Expression Union(self.tfsms.start) in
			Relation Precedes(self.start, allStartTFSM)
		
		inv firstOnlyOnce:
			let onlyOneFirst : Event = Expression OneTickAndNoMore(self.start) in
			Relation Coincides(self.start,onlyOneFirst)
endpackage