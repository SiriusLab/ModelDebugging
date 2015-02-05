rule MatchStateSystems
	match left : Left!StateSystem
	with right : Right!StateSystem
	{
		compare : compareStateSystems(left, right)
	}

operation compareStateSystems(left : Left!StateSystem, right : Right!StateSystem) : Boolean {
	var leftFiringActionStates : OrderedSet = left.getFiringActionStates();
("leftFiringActionStates size " + leftFiringActionStates.size()).println();
debugPrint(leftFiringActionStates);

	var rightFiringActionStates : OrderedSet = right.getFiringActionStates();
("rightFiringActionStates size " + rightFiringActionStates.size()).println();
debugPrint(rightFiringActionStates);
	
	var i : Integer = 0;
	while (i < leftFiringActionStates.size()) {
		var leftFiringActionState = leftFiringActionStates.at(i);
		var rightFiringActionState = rightFiringActionStates.at(i);
		if(not equals(leftFiringActionState, rightFiringActionState))
			return false;
		i = i + 1;
	}
		
	return true;
}

operation equals(leftState : Any, rightState : Any) {
	var leftFiringActionActivations : OrderedSet = getFiringActionActivations(leftState);
	var rightFiringActionActivations : OrderedSet = getFiringActionActivations(rightState);
	return equals(leftFiringActionActivations, rightFiringActionActivations);
}

operation StateSystem getFiringActionStates() : OrderedSet {
	var firingActionStates : OrderedSet = new OrderedSet();
	
	var statesAfterInitialization : OrderedSet = self.getStatesAfterInitialization();
	var i : Integer = 0;
	while (i < statesAfterInitialization.size()-1) {
		var state : Any = statesAfterInitialization.at(i);
		var previousState : Any = null;
		if(i > 0)
			previousState = statesAfterInitialization.at(i-1);
			
		var firingActionActivations : OrderedSet = getFiringActionActivations(state);
		if(firingActionActivations.size() > 0) {
			if (previousState == null) {
				firingActionStates.add(state);
			} else {
				var firingActionActivationsInPreviousState : OrderedSet = getFiringActionActivations(previousState);
				if(not equals(firingActionActivations, firingActionActivationsInPreviousState)) {
					firingActionStates.add(state);
				}
			}
		}
		i = i + 1;
	}
	
//	firingActionStates.addAll(statesAfterInitialization);
	return firingActionStates;
}

operation equals(actionActivationList1 : OrderedSet, actionActivationList2 : OrderedSet) : Boolean {
	for(activation1 : ActionActivation in actionActivationList1) {
		var found : Boolean = false;
		for(activation2 : ActionActivation in actionActivationList2) {
			if (activation1.equals(activation2))
				found = true;
		}
		if (not found)
			return false;
	}
	return true;
}

operation ActionActivation equals(actionActivation : ActionActivation) : Boolean {
	return (self.runtimeModelElement.name = actionActivation.runtimeModelElement.name) and (self.firing = actionActivation.firing);
}

operation getFiringActionActivations(state : Any) : OrderedSet {
	var firingActionActivations : OrderedSet = new OrderedSet();
	var activityExecution : ActivityExecution = getActivityExecution(state);
	for(nodeActivation : ActivityNodeActivation in activityExecution.activationGroup.nodeActivations) {
		if (nodeActivation.isKindOf(ActionActivation)) {
			var actionActivation : ActionActivation = nodeActivation;
			if (actionActivation.firing) {			
				firingActionActivations.add(actionActivation);
			}
		}
	}
	return firingActionActivations;
}

operation StateSystem getStatesAfterInitialization() : OrderedSet {
	var statesAfterInitialization : OrderedSet = new OrderedSet();
	for (state : Any in self.states) { // type "State" causes collision (trace metamodel vs UML metamodel)
		var activityExecution : ActivityExecution = getActivityExecution(state);
		if (activityExecution <> null and activityExecution.activationGroup <> null) {
			if (activityExecution.activationGroup.nodeActivations.size() > 0) {
				statesAfterInitialization.add(state);
			}
		}
	}
	return statesAfterInitialization;
}

operation getActivityExecution(state : Any) : ActivityExecution {
	var locus : Locus = getLocus(state);
	var activityExecution : ActivityExecution = locus.getActivityExecution();
	return activityExecution;
}

operation getLocus(state : Any) : Locus {
	var locus : Locus = null;
	for (object : Any in state.objects) {
		if (object.isKindOf(Locus)) {
			locus = object;
		}
	}
	return locus;
}

operation Locus getActivityExecution() : ActivityExecution {
	for (extensionalValue : ExtensionalValue in self.extensionalValues) {
		if (extensionalValue.isKindOf(ActivityExecution)) {
			return extensionalValue;
		}
	}	
	return null;
}

operation debugPrint(states : OrderedSet) {
	for(state : Any in states) {
		"State".println();
		var firingActionActivations : OrderedSet = getFiringActionActivations(state);
		for(activation : ActionActivation in firingActionActivations) {
			(activation.runtimeModelElement.name).println();
		}
	}
}