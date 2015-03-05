rule MatchTrace
	match left : Trace
	with right : Trace
	{
		compare 
		{
//("left states size " + left.globalTrace.size()).println();
//("right states size " + right.globalTrace.size()).println();

//var leftTracedOpaqueActions : Set = left.tracedObjects.basicActions_tracedOpaqueActionActivations;
//("left traced opaque actions " + leftTracedOpaqueActions.size()).println();

			var leftFiringActions : Sequence = left.collectFiringActions();
//("left firing actions " + leftFiringActions.size()).println();

//var rightTracedOpaqueActions : Set = left.tracedObjects.basicActions_tracedOpaqueActionActivations;
//("right traced opaque actions " + rightTracedOpaqueActions.size()).println();

			var rightFiringActions : Sequence = right.collectFiringActions();
//("right firing actions " + rightFiringActions.size()).println();

			return leftFiringActions.matches(rightFiringActions);
		}
	}

operation Trace collectFiringActions() : Sequence {
	var firingActionsMap : Map = new Map();
	for (tracedAction : TracedOpaqueActionActivation in self.tracedObjects.basicActions_tracedOpaqueActionActivations){
		var action : TracedAction = tracedAction.getAction();
		for (firingTrace : ActionActivation_firing_State in tracedAction.firingTrace) {
			if (firingTrace.firing) {
				var state : GlobalState = firingTrace.globalStates.at(0); 
				var stateIndex : Integer = self.globalTrace.indexOf(state);
				firingActionsMap.put(stateIndex, action);
//("action " + action.name + " firing at global state #" + stateIndex).println();
			}
		}
	}

	var firingActions : Sequence = new Sequence();
	var sortedStateIndexes : Sequence = firingActionsMap.keySet().sortBy(f | f);
	for (index : Integer in sortedStateIndexes) {
		firingActions.add(firingActionsMap.get(index));		
	}
//"sorted".println();
//	for(action : TracedAction in firingActions) {
//(action.name).println();
//	}
	
	return firingActions;
}

operation TracedOpaqueActionActivation getAction() : TracedAction{
	var action : TracedOpaqueAction = null; 
	for (runtimeModelElementState : SemanticVisitor_runtimeModelElement_State in self.runtimeModelElementTrace) {
		var runtimeModelElement : TracedElement = runtimeModelElementState.runtimeModelElement;
		if (runtimeModelElement <> null) {
			action = runtimeModelElement;
		}
	}
	return action;
}


@Lazy
rule MatchAction
	match left : TracedOpaqueAction
	with right : TracedOpaqueAction
	{
		compare 
		{ 
//("MatchAction " + left.name + " vs " + right.name).println();
			return left.name = right.name;
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
operation Trace printFiringTrace() {
	for (tracedAction : TracedOpaqueActionActivation in self.tracedObjects.basicActions_tracedOpaqueActionActivations){
("tracedAction ").println();
		for (firingTrace : ActionActivation_firing_State in tracedAction.firingTrace) {
("firingTrace " + firingTrace.firing).println();
		}
	}
}
	