package org.gemoc.executionframework.eventmanager.views;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.EventManagerRegistry;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IEventManager;
import fr.inria.diverse.trace.commons.model.trace.Step;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class EventManagerRenderer extends Pane implements IEngineAddon {

	private IEventManager eventManager;
	
	private Resource executedModel;

	private final ObservableList<EClass> eventList = FXCollections.observableArrayList();
	
	private final Map<EClass, EventTableView> eventTypeToEventTableView = new HashMap<>();
	
	private final ScrollPane scrollPane = new ScrollPane();
	
	private final ListView<EClass> eventListView = new ListView<>(eventList);
	
	private final BorderPane borderPane = new BorderPane();
	
	private final Button button1 = new Button("Push");
	
	private final Button button2 = new Button("Send");
	
	private final HBox header = new HBox();

	public EventManagerRenderer() {
		getChildren().add(borderPane);
		
		borderPane.minWidthProperty().bind(widthProperty());
		borderPane.maxWidthProperty().bind(widthProperty());
		borderPane.minHeightProperty().bind(heightProperty());
		borderPane.maxHeightProperty().bind(heightProperty());
		
		header.getChildren().addAll(button1, button2);
		
		eventListView.setCellFactory((l) -> new ComboBoxListCell<EClass>(new StringConverter<EClass>() {
			@Override
			public String toString(EClass object) {
				return object.getName();
			}
			@Override
			public EClass fromString(String string) {
				return null;
			}
		}));
		
		eventListView.getSelectionModel().selectedItemProperty().addListener((c, o, n) -> {
			scrollPane.setContent(eventTypeToEventTableView.get(n));
		});
		
		borderPane.setTop(header);
		borderPane.setLeft(eventListView);
		borderPane.setCenter(scrollPane);
		
		scrollPane.setFitToWidth(true);
		scrollPane.setBorder(Border.EMPTY);
		scrollPane.minHeightProperty().bind(eventListView.heightProperty());
		scrollPane.maxHeightProperty().bind(eventListView.heightProperty());
		final ListChangeListener<EClass> eventTypesChangeListener = c -> {
			while(c.next()) {
				c.getRemoved().stream().forEach(e -> eventTypeToEventTableView.remove(e));
				c.getAddedSubList().stream().forEach(e -> {
					final EventTableView tableView = new EventTableView(e, executedModel, eventManager);
					eventTypeToEventTableView.put(e, tableView);
					tableView.refreshEvents();
					tableView.minHeightProperty().bind(scrollPane.heightProperty().subtract(2));
				});
			}
		};
		eventList.addListener(eventTypesChangeListener);
	}
	
	public void setEventManager(IEventManager eventManager) {
		Runnable runnable = () -> {
			this.eventManager = eventManager;
			eventList.clear();
			if (eventManager != null) {
				eventList.addAll(this.eventManager.getEventClasses());
			}
		};
		if (!Platform.isFxApplicationThread()) {
			Platform.runLater(runnable);
		} else {
			runnable.run();
		}
	}
	
	public void setExecutedModel(Resource executedModel) {
		this.executedModel = executedModel;
	}
	
	private void refreshEvents() {
		eventTypeToEventTableView.entrySet().forEach(e -> {
			final EventTableView tableView = e.getValue();
			tableView.refreshEvents();
		});
		final EClass selectedItem = eventListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			final EventTableView selectedTableView = eventTypeToEventTableView.get(selectedItem);
			if (selectedTableView != null) {
				scrollPane.setContent(selectedTableView);
			} else {
				scrollPane.setContent(null);
			}
		}
	}

	@Override
	public void engineAboutToStart(IExecutionEngine engine) {
	}

	@Override
	public void engineStarted(IExecutionEngine executionEngine) {
		executedModel = executionEngine.getExecutionContext().getResourceModel();
	}
	
	@Override
	public void engineInitialized(IExecutionEngine executionEngine) {
		setEventManager(EventManagerRegistry.getInstance().findEventManager(null));
	}

	@Override
	public void engineAboutToStop(IExecutionEngine engine) {
	}

	@Override
	public void engineStopped(IExecutionEngine engine) {
		executedModel = null;
		eventList.clear();
		eventTypeToEventTableView.clear();
		scrollPane.setContent(null);
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine engine) {
	}

	@Override
	public void stepSelected(IExecutionEngine engine, Step selectedStep) {
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine engine, Step stepToExecute) {
		refreshEvents();
	}

	@Override
	public void stepExecuted(IExecutionEngine engine, Step stepExecuted) {
		refreshEvents();
	}

	@Override
	public void engineStatusChanged(IExecutionEngine engine, RunStatus newStatus) {
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aboutToSelectStep(IExecutionEngine engine, Collection<Step> steps) {
	}

	@Override
	public void proposedStepsChanged(IExecutionEngine engine, Collection<Step> steps) {
	}
}
