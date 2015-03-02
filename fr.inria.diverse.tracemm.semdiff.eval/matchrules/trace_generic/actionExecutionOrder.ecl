rule MatchStateSystems
	match left : StateSystem
	with right : StateSystem
	{
		compare 
		{
("left states size " + left.states.size()).println();
("right states size " + right.states.size()).println();

			var leftFiringActionStates : OrderedSet = left.getStatesWithFiringAction();
("leftFiringActionStates size " + leftFiringActionStates.size()).println();
debugPrint(leftFiringActionStates);

			var rightFiringActionStates : OrderedSet = right.getStatesWithFiringAction();
("rightFiringActionStates size " + rightFiringActionStates.size()).println();
debugPrint(rightFiringActionStates);
	
			return leftFiringActionStates.matches(rightFiringActionStates);
		}
	}

operation StateSystem getStatesWithFiringAction() : OrderedSet {
	var statesWithFiringAction : OrderedSet = new OrderedSet();
	var i : Integer = 0;
	while (i < self.states.size() - 1) {
		var state : states::State = self.states.at(i);

//state.debugPrintOpaqueActions();
//("hasFiringAction = " + state.hasFiringAction()).println();

		if (state.hasFiringAction()) {
			if (statesWithFiringAction.size() == 0) {
				statesWithFiringAction.add(state);
			} else {
				var previousState : states::State = self.states.at(i-1);
				if (not compareStates(state, previousState)) {				
//					if (not matches(state, previousState)) // this should work, but does not because Set.matches() is called for some reason
					statesWithFiringAction.add(state);
				}
			}
		}
		i = i + 1;
	}
	return statesWithFiringAction;
}

operation states::State hasFiringAction() : Boolean {
	var firingActions : OrderedSet = self.getFiringActions();
	return firingActions.size() > 0;
}

operation states::State getFiringActions() : OrderedSet {
	var firingActionActivations : OrderedSet = new OrderedSet();
	var activityExecution : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution = self.getActivityExecution();
	if (activityExecution <> null) {
		firingActionActivations = activityExecution.getFiringActions();
	} 
	return firingActionActivations;
}


operation fumlConfiguration::Activities::IntermediateActivities::ActivityExecution getFiringActions() : OrderedSet {
	var firingActions : OrderedSet = new OrderedSet();
	if (self.activationGroup <> null) {
		for(nodeActivation : ActivityNodeActivation in self.activationGroup.nodeActivations) {
			if (nodeActivation.isKindOf(OpaqueActionActivation)) {
				var actionActivation : ActionActivation = nodeActivation;
				if (actionActivation.firing) {			
					firingActions.add(actionActivation);
				}
			}
		}
	}
	return firingActions;
}

operation states::State getActivityExecution() : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution {
	var locus : Locus = self.getLocus();
	var activityExecution : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution = locus.getActivityExecution();
	return activityExecution;
}

operation states::State getLocus() : Locus {
	var locus : Locus = null;
	for (object : Any in self.objects) {
		if (object.isKindOf(Locus)) {
			locus = object;
		}
	}
	return locus;
}

operation Locus getActivityExecution() : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution {
	for (extensionalValue : ExtensionalValue in self.extensionalValues) {
		if (extensionalValue.isKindOf(fumlConfiguration::Activities::IntermediateActivities::ActivityExecution)) {
			return extensionalValue;
		}
	}	
	return null;
}

@Lazy
rule MatchStates
	match left : states::State
	with right : states::State
	{
		compare : compareStates(left, right)
	}
	
operation compareStates(leftState : states::State, rightState : states::State) : Boolean {
	var leftFiringActions : OrderedSet = leftState.getFiringActions();
	var rightFiringActions : OrderedSet = rightState.getFiringActions();
	return compareActionActivations(leftFiringActions, rightFiringActions);
}

operation compareActionActivations(actionActivationList1 : OrderedSet, actionActivationList2 : OrderedSet) : Boolean {
	var actionActivationSet1 : Set = actionActivationList1.asSet();
	var actionActivationSet2 : Set = actionActivationList2.asSet();
	return actionActivationSet1.matches(actionActivationSet2);
}

@Lazy
rule MatchOpaqueActionActivations
	match left : OpaqueActionActivation
	with right : OpaqueActionActivation
	{
		compare 
		{
			var firingMatches : Boolean = left.firing = right.firing;
			
			var leftOpaqueAction : OpaqueAction = left.runtimeModelElement;
			var rightOpaqueAction : OpaqueAction = right.runtimeModelElement;	
			
//			var actionNameMatches : Boolean = false;
//			if(leftOpaqueAction = null and rightOpaqueAction = null) {
//				actionNameMatches = true;
//			} else {
//				actionNameMatches = (leftOpaqueAction.name = rightOpaqueAction.name);
//			}
			
//if (left = null) "left null".println();
//if (right = null) "right null".println();	
//if (leftOpaqueAction = null) "left runtime element null".println();
//if (rightOpaqueAction = null) "right runtime element null".println();
//if (leftOpaqueAction.name = null) "left name null".println();
//if (rightOpaqueAction.name = null) "right name null".println();
			return (leftOpaqueAction.name = rightOpaqueAction.name) and (left.firing = right.firing);
//			return firingMatches and actionNameMatches;
		}
	}

operation Set matches(targetSet : Set) : Boolean {
//"set matches called".println();
	var matches : Boolean = true;
	for (source : Any in self) {
		var sourceTargetMatchFound : Boolean = false;
		for (target : Any in targetSet) {
			if (source.matches(target)) 
				sourceTargetMatchFound = true;
		}
		if (not sourceTargetMatchFound) {
			matches = false;
		}
	}
	return matches;
}

operation OrderedSet matches(targetSet : OrderedSet) : Boolean {
	if (self.size() <> targetSet.size()) {
		return false;
	}
	
	var matches : Boolean = true;
	var i : Integer = 0;
	while (i < self.size()) {
		var source = self.at(i);
		var target = targetSet.at(i);
		if (not source.matches(target)) {
			matches = false;
		}
		i = i + 1;
	}
	return matches;
}

operation debugPrint(states : OrderedSet) {
	for(state : states::State in states) {
		"State".println();
		var firingActionActivations : OrderedSet = state.getFiringActions();
		for(activation : ActionActivation in firingActionActivations) {
//			if (activation.runtimeModelElement <> null) {
			(activation.runtimeModelElement.name).println();
//			} 
//			else {
//				"null".println();
//			}
		}
	}
}

operation debugPrintOpaqueActions(states : OrderedSet) {
	for(state : states::State in states) {
		state.debugPrintOpaqueActions();
	}
}

operation states::State debugPrintOpaqueActions() {
	"state opaque actions: ".println();
	var activityExecution = self.getActivityExecution();
	if (activityExecution <> null) {
		if(activityExecution.activationGroup <> null) {
			for(node in activityExecution.activationGroup.nodeActivations) {
				if(node.isKindOf(ActionActivation)) {
					(node.runtimeModelElement.name + " firing = " + node.firing).println();
				}
			}
		}
	}
}