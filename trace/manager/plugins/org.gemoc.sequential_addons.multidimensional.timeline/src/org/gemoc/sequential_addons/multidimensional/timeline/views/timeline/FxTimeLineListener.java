package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug.OmniscientGenericSequentialModelDebugger;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;

import fr.obeo.timeline.model.ITimelineWindowListener;
import fr.obeo.timeline.view.ITimelineProvider;

public class FxTimeLineListener extends VBox implements ITimelineWindowListener {

	private ITimelineProvider provider;
	
	final private MultidimensionalTimeLineView multidimensionalTimeLineView;
	
	final private List<Pane> lines = new ArrayList<>();
	
	public FxTimeLineListener(MultidimensionalTimeLineView multidimensionalTimeLineView) {
		this.multidimensionalTimeLineView = multidimensionalTimeLineView;
		setBackground(Background.EMPTY);
	}
	
	@Override
	public void startChanged(int branch, int start) {
	}

	@Override
	public void endChanged(int branch, int end) {
		deepRefresh();
	}

	@Override
	public void textAtChanged(int branch, String text) {
		deepRefresh();
	}

	@Override
	public void numberOfPossibleStepsAtChanged(int branch, int index, int numberOfpossibleStep) {
		deepRefresh();
	}

	@Override
	public void textAtChanged(int branch, int index, String text) {
		deepRefresh();
	}

	@Override
	public void atChanged(int branch, int index, int possibleStep, Object object) {
		deepRefresh();
	}

	@Override
	public void isSelectedChanged(int branch, int index, int possibleStep, boolean selected) {
		deepRefresh();
	}

	@Override
	public void textAtChanged(int branch, int index, int possibleStep, String text) {
		deepRefresh();
	}

	@Override
	public void followingsChanged(int branch, int index, int possibleStep, int[][] followings) {
		deepRefresh();
	}

	@Override
	public void precedingsChanged(int branch, int index, int possibleStep, int[][] precedings) {
		deepRefresh();
	}

	@Override
	public void startChanged(int start) {
		deepRefresh();
	}

	@Override
	public void lengthChanged(int length) {
		deepRefresh();
	}

	@Override
	public void providerChanged(ITimelineProvider provider) {
		this.setProvider(provider);
	}

	public ITimelineProvider getProvider() {
		return provider;
	}

	public void setProvider(ITimelineProvider provider) {
		this.provider = provider;
		deepRefresh();
	}
	
	private Background selectedBackground = new Background(new BackgroundFill(Color.BLACK, null, null));
	
	private Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, null, null));
	
	private int currentTrace = -1;
	
	private void selectPane(int branch, Pane toSelect) {
		currentTrace = branch;
		for (Pane r : lines) {
			if (r != null) {
				r.setBackground(Background.EMPTY);
			}
		}
		toSelect.setBackground(selectedBackground);
		IBasicExecutionEngine engine = multidimensionalTimeLineView.getCurrentEngine();
		for (OmniscientGenericSequentialModelDebugger traceAddon : engine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.setCurrentTrace(branch);
		}
	}
	
	private Pane createTracePane(int branch, Pane contentPane) {
		final Label titleLabel = new Label(provider.getTextAt(branch));
		final StackPane pane = new StackPane();
		pane.getChildren().addAll(titleLabel, contentPane);
		titleLabel.minWidthProperty().bind(pane.widthProperty());
		contentPane.minWidthProperty().bind(pane.widthProperty());
		return pane;
	}
	
	private HBox createLine(int branch) {
		final HBox hBox = new HBox();
		hBox.setBackground(transparentBackground);
		final Pane pane = createTracePane(branch, hBox);
		pane.setBackground(Background.EMPTY);
		pane.minWidthProperty().bind(widthProperty());
		StackPane.setMargin(hBox, new Insets(16));
		pane.setFocusTraversable(true);
		getChildren().add(pane);
		EventHandler<MouseEvent> handler = (e) -> {
			if (e.getClickCount() == 1) {
				selectPane(branch, pane);
			}
		};
		hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
		pane.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
		if (currentTrace == branch) {
			hBox.setBackground(selectedBackground);
			pane.setBackground(selectedBackground);
		}
		lines.add(pane);
		return hBox;
	}
	
	private void fillLine(HBox line, int idx, int nb) {
		line.getChildren().clear();
		for (int i=0;i<nb;i++) {
			final Rectangle rectangle;
			if (provider.getSelectedPossibleStep(idx, i) == -1) {
				rectangle = new Rectangle(32, 32, Color.DARKORANGE);
			} else {
				rectangle = new Rectangle(32, 32, Color.DARKBLUE);
			}
			rectangle.setArcHeight(8);
			rectangle.setArcWidth(8);
			rectangle.setUserData(provider.getAt(idx, i));
			rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
				if (e.getClickCount() > 1) {
					Object o = rectangle.getUserData();
					IBasicExecutionEngine engine = multidimensionalTimeLineView.getCurrentEngine();
					for (OmniscientGenericSequentialModelDebugger traceAddon : engine
							.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
						if (o instanceof EObject)
							traceAddon.jump((EObject) o);
					}
				}
			});
			Tooltip t = new Tooltip(provider.getTextAt(idx, i, 0));
			Tooltip.install(rectangle, t);
			line.getChildren().add(rectangle);
			HBox.setMargin(rectangle, new Insets(8));
		}
	}
	
	private void deepRefresh() {
		Platform.runLater(() -> {
			getChildren().clear();
			lines.clear();
			for (int i=0;i<provider.getNumberOfBranches();i++) {
				if (provider.getAt(i, 0) != null) {
					final HBox hBox = createLine(i);
					fillLine(hBox, i, provider.getEnd(i));
					getChildren().add(hBox);
				} else {
					lines.add(null);
				}
			}
		});
	}

}
