/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package fr.inria.diverse.tracemm.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

public class ConfigurableStatesBuilder extends EContentAdapter implements ExecutionEventListener {
	
	protected SpecificStatesBuilderConfiguration conf;
	public SpecificStatesBuilderConfiguration getConf() {
		return conf;
	}


	private Resource modelResource;
	
	public Resource getModelResource() {
		return modelResource;
	}


	private List<Event> handledEvents = new ArrayList<Event>();

	private ActivityExitEvent currentActivityExitEvent;

	private XMOFVirtualMachine vm;

	private int rootActivityExecutionID = -1;
	private Trace trace = null;

	public ConfigurableStatesBuilder(Resource modelResource, SpecificStatesBuilderConfiguration c) {
		this.modelResource = modelResource;
		this.conf = c;
		c.init(this);
		initialize();
	}

	private void initialize() {
		addResourceAdapter();
		conf.createStateSystem();
	}

	private void addResourceAdapter() {
		this.modelResource.eAdapters().add(this);
	}



	@Override
	public void notify(org.modelexecution.fumldebug.core.event.Event event) {
		// We only are interested in the state of the exemodel after each
		// activity (of the semantics) exit
		// Each one of these exits is an event in the trace metamodel
		if (event instanceof ActivityExitEvent) {
			// change the last seen activity exit event
			currentActivityExitEvent = (ActivityExitEvent) event;
			// add new event to the current state of the trace
			conf.addEvent(getCurrentActivityExecution());
		}
		// Initialization case (still useful?)
		else if (isActivityEntry(event) && !rootActivityExecutionSet()) {
			rootActivityExecutionID = getActivityExecutionID(event);
			retrieveTraceForStateSystem();
		}
	}

	private void retrieveTraceForStateSystem() {
		if (traceExists()) {
			this.trace = getTrace();
		}
	}

	private boolean traceExists() {
		if (isVMSet())
			return getTrace() != null;
		else
			return false;
	}

	private Trace getTrace() {
		Trace trace = vm.getRawExecutionContext().getTrace(rootActivityExecutionID);
		return trace;
	}

	private boolean isVMSet() {
		return vm != null;
	}

	private boolean rootActivityExecutionSet() {
		return rootActivityExecutionID != -1;
	}

	private int getActivityExecutionID(org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			return activityEntryEvent.getActivityExecutionID();
		}
		return -1;
	}

	private boolean isActivityEntry(org.modelexecution.fumldebug.core.event.Event event) {
		return getActivityExecutionID(event) != -1;
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (isNewStateRequired())
			addNewState();
		else
			conf.updateState();
		adapt(notification);
	}

	private void adapt(Notification notification) {
		switch (notification.getEventType()) {
		case Notification.REMOVE_MANY:
		case Notification.REMOVE:
		case Notification.UNSET:
			EObject eObject = getEObject(notification);
			adapt(eObject);
		}

	}

	private void adapt(EObject eObject) {
		if (eObject == null)
			return;
		if (!eObject.eAdapters().contains(this))
			eObject.eAdapters().add(this);
	}

	private EObject getEObject(Notification notification) {
		if (notification.getOldValue() instanceof EObject)
			return (EObject) notification.getOldValue();
		return null;
	}


	private boolean isNewStateRequired() {
		return !handledEvents.contains(currentActivityExitEvent);
	}

	public static String uglyGetActionNameFromActivityExecution(ActivityExecution actExe) {
		String toStringOutput = actExe.getContext().getValue().toString();
		String splitName = toStringOutput.split("name\\[0\\]  = ")[1];
		String splitNewline = splitName.split("\n")[0];
		return splitNewline;
	}

	private void addNewState() {
		handledEvents.add(currentActivityExitEvent);
		conf.createNewState();
	}

	private ActivityExecution getCurrentActivityExecution() {
		if (currentActivityExitEvent != null && trace != null) {
			int activityExecutionID = currentActivityExitEvent.getActivityExecutionID();
			ActivityExecution activityExecution = trace.getActivityExecutionByID(activityExecutionID);
			return activityExecution;
		}
		return null;
	}



	public static Object eGetFromName(EObject object, String feature) {
		return object.eGet(object.eClass().getEStructuralFeature(feature));
	}




	public void setVM(XMOFVirtualMachine vm) {
		this.vm = vm;
	}

	public XMOFVirtualMachine getVM() {
		return vm;
	}
}
