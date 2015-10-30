/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 * Erwan Bousse - separating the states builder common part from a configurable part
 */
package fr.inria.diverse.tracemm.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.modelexecution.fumldebug.core.ExecutionEventListener;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeEvent;
import org.modelexecution.fumldebug.core.event.ActivityNodeExitEvent;
import org.modelexecution.fumldebug.core.event.Event;
import org.modelexecution.fumldebug.core.trace.tracemodel.ActivityExecution;
import org.modelexecution.fumldebug.core.trace.tracemodel.Trace;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;

import fUML.Syntax.Actions.BasicActions.Action;
import fUML.Syntax.Activities.IntermediateActivities.ActivityNode;

public class ConfigurableStatesBuilder extends EContentAdapter implements ExecutionEventListener {

	private static final boolean considerActionEntryEvents = true;

	protected GenericStatesBuilderConfigurationDynamicEObj conf;

	private int rootActivityExecutionID = -1;

	private Trace trace = null;

	private int nbStates = 0;

	public int getNbStates() {
		return nbStates;
	}

	private XMOFVirtualMachine vm;

	public GenericStatesBuilderConfigurationDynamicEObj getConf() {
		return conf;
	}

	private Resource modelResource;

	public Resource getModelResource() {
		return modelResource;
	}

	private List<Event> handledEvents = new ArrayList<Event>();

	private Event currentEvent;

	public ConfigurableStatesBuilder(Resource modelResource, GenericStatesBuilderConfigurationDynamicEObj c) {
		this.modelResource = modelResource;
		this.conf = c;
		c.init(this);
		initialize();
	}

	private void initialize() {
		addResourceAdapter();
		conf.createEmptyTrace();
	}

	private void addResourceAdapter() {
		this.modelResource.eAdapters().add(this);
	}

	private Collection<Throwable> errors = new ArrayList<Throwable>();

	public Collection<Throwable> getErrors() {
		return errors;
	}

	@Override
	public void notify(org.modelexecution.fumldebug.core.event.Event event) {
		try {
			// Initialization case (required to have access to the fUML trace,
			// which is required to
			// find the parameters of the executed activities)
			if (isActivityEntry(event) && !rootActivityExecutionSet()) {
				rootActivityExecutionID = getActivityExecutionID(event);
				retrieveTraceForStateSystem();
			}
			// We only are interested in the state of the exemodel after each
			// activity (of the semantics) exit or entry
			// Each one of these exits or entrys is an event in the trace
			// metamodel
			if (event instanceof ActivityExitEvent) {
				// change the last seen activity exit event
				currentEvent = (ActivityExitEvent) event;
				// add new event to the current state of the trace
				conf.addExitEvent((ActivityExitEvent) event);
			} else if (event instanceof ActivityEntryEvent) {
				// change the last seen activity exit event
				currentEvent = (ActivityEntryEvent) event;
				// add new event to the current state of the trace
				conf.addEntryEvent((ActivityEntryEvent) event);
			} else if (considerActionEntryEvents) {
				if (isActionEntry(event)) {
					currentEvent = getActionEntry(event);
				} else if (isActionExit(event)) {
					currentEvent = null;
				}
			}
			// change the last seen event, but without adding it to the trace

		} catch (Throwable t) {
			errors.add(t);
			t.printStackTrace();
			throw t;
		}
	}

	private boolean isActionEntry(org.modelexecution.fumldebug.core.event.Event event) {
		ActivityNodeEntryEvent actionEntry = getActionEntry(event);
		return actionEntry != null;
	}

	private boolean isActionExit(org.modelexecution.fumldebug.core.event.Event event) {
		ActivityNodeExitEvent actionExit = getActionExit(event);
		return actionExit != null;
	}

	private ActivityNodeEntryEvent getActionEntry(org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityNodeEntryEvent) {
			ActivityNodeEntryEvent activityNodeEntryEvent = (ActivityNodeEntryEvent) event;
			if (isActionEvent(activityNodeEntryEvent))
				return activityNodeEntryEvent;
		}
		return null;
	}

	private boolean isActionEvent(ActivityNodeEvent event) {
		Action action = getAction(event);
		return action != null;
	}

	private Action getAction(ActivityNodeEvent nodeEntryEvent) {
		ActivityNode node = nodeEntryEvent.getNode();
		if (node instanceof Action)
			return (Action) node;
		else
			return null;
	}

	private ActivityNodeExitEvent getActionExit(org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityNodeExitEvent) {
			ActivityNodeExitEvent activityNodeExitEvent = (ActivityNodeExitEvent) event;
			if (isActionEvent(activityNodeExitEvent))
				return activityNodeExitEvent;
		}
		return null;
	}

	private boolean isActivityEntry(org.modelexecution.fumldebug.core.event.Event event) {
		return getActivityExecutionID(event) != -1;
	}

	private int getActivityExecutionID(org.modelexecution.fumldebug.core.event.Event event) {
		if (event instanceof ActivityEntryEvent) {
			ActivityEntryEvent activityEntryEvent = (ActivityEntryEvent) event;
			return activityEntryEvent.getActivityExecutionID();
		}
		return -1;
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

	@Override
	public void notifyChanged(Notification notification) {
		try {
			super.notifyChanged(notification);
			if (isNewStateRequired())
				addNewState();
			else
				conf.updateState();
			adapt(notification);
		} catch (Throwable t) {
			errors.add(t);
			t.printStackTrace();
			throw t;
		}
	}

	public ActivityExecution getActivityExecutionOf(ActivityEvent e) {
		if (trace != null) {
			return trace.getActivityExecutionByID(e.getActivityExecutionID());
		}
		return null;
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
		return !handledEvents.contains(currentEvent);
	}

	private void addNewState() {
		nbStates++;
		handledEvents.add(currentEvent);
		conf.createNewState();
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
