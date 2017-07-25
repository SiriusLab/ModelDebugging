package org.eclipse.gemoc.event.commons.model;

import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.gemoc.event.commons.model.scenario.Event;

public class EventInstance {

	private final Event originalEvent;

	private final Map<EStructuralFeature, Object> parameters;

	public EventInstance(Event originalEvent, Map<EStructuralFeature, Object> parameters) {
		this.originalEvent = originalEvent;
		this.parameters = parameters;
	}

	public Event getOriginalEvent() {
		return originalEvent;
	}

	public Map<EStructuralFeature, Object> getParameters() {
		return parameters;
	}
}
