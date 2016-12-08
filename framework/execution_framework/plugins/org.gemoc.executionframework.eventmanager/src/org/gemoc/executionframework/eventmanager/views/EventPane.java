package org.gemoc.executionframework.eventmanager.views;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IEventManager;
import javafx.scene.layout.BorderPane;

public class EventPane extends BorderPane {

	private final EventTableView tableView;
	
	public EventPane(EClass eventClass, final Resource executedModel, final IEventManager eventManager) {
		tableView = new EventTableView(eventClass, executedModel, eventManager);
		setCenter(tableView);
		tableView.refreshEvents();
	}
}
