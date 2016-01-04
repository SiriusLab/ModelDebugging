package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.executionengine.java.sequential_modeling_workbench.ui.debug.OmniscientGenericSequentialModelDebugger;
import org.gemoc.gemoc_language_workbench.api.core.IBasicExecutionEngine;

import fr.inria.diverse.trace.api.IStep;
import fr.inria.diverse.trace.api.IStep.StepEvent;
import fr.inria.diverse.trace.gemoc.traceaddon.ISequentialTimelineProvider;
import fr.inria.diverse.trace.gemoc.traceaddon.ISequentialTimelineProvider.StateWrapper;
import fr.obeo.timeline.model.ITimelineWindowListener;
import fr.obeo.timeline.view.ITimelineProvider;


public class FxTimeLineListener extends VBox implements ITimelineWindowListener {

	private ISequentialTimelineProvider provider;
	
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
		if (provider instanceof ISequentialTimelineProvider) {
			this.provider = (ISequentialTimelineProvider)provider;
			deepRefresh();
		} else {
			throw new IllegalArgumentException("FxTimeLineListener expects ISequentialTimelineProvider");
		}
	}
	
//	private Background selectedBackground = new Background(new BackgroundFill(Color.BLACK, null, null));
	
	private Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, null, null));
	
	private int currentTrace = -1;
	
	private void selectPane(int branch, Pane toSelect) {
		currentTrace = branch;
		for (Pane r : lines) {
			if (r != null) {
				r.setBackground(Background.EMPTY);
			}
		}
//		toSelect.setBackground(selectedBackground);
		IBasicExecutionEngine engine = multidimensionalTimeLineView.getCurrentEngine();
		for (OmniscientGenericSequentialModelDebugger traceAddon : engine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.setCurrentTrace(branch);
		}
	}
	
	private StackPane stackPane;
	
	private Pane createTracePane(int branch, Pane contentPane) {
		final Label titleLabel = new Label(provider.getTextAt(branch));
		final BorderPane borderPane = new BorderPane();
		borderPane.setTop(titleLabel);
		if (branch == 0) {
			stackPane = new StackPane(contentPane);
			borderPane.setCenter(stackPane);
			titleLabel.minWidthProperty().bind(stackPane.widthProperty());
			contentPane.minWidthProperty().bind(stackPane.widthProperty());
			stackPane.minWidthProperty().bind(borderPane.widthProperty());
		} else {
			borderPane.setCenter(contentPane);
			titleLabel.minWidthProperty().bind(borderPane.widthProperty());
			contentPane.minWidthProperty().bind(borderPane.widthProperty());
		}
		return borderPane;
	}
	
	private HBox createLine(int branch) {
		final HBox hBox = new HBox();
		hBox.setBackground(transparentBackground);
		final Pane pane = createTracePane(branch, hBox);
		pane.setBackground(Background.EMPTY);
		pane.minWidthProperty().bind(widthProperty());
		BorderPane.setMargin(hBox, new Insets(16));
		pane.setFocusTraversable(true);
		getChildren().add(pane);
		lines.add(pane);
		return hBox;
	}

	private static final int DIAMETER = 32;
	private static final int MARGIN = 8;
	private static final int ADDED_WIDTH = DIAMETER + 2 * MARGIN;
	
	private void fillLine(HBox line, int idx, List<StateWrapper> stateWrappers, int maxLength, int selectedState) {
		
		line.getChildren().clear();
		
		int valueIndex = 0;
		int stateIndex = 0;
		
		for (StateWrapper stateWrapper : stateWrappers) {
			
			if (stateWrapper.startIndex > stateIndex) {
				int width = DIAMETER + ADDED_WIDTH * (stateWrapper.startIndex - stateIndex - 1);
				final Rectangle rectangle = new Rectangle(width, DIAMETER, Color.TRANSPARENT);
				line.getChildren().add(rectangle);
				HBox.setMargin(rectangle, new Insets(MARGIN));
			}
			
			final Rectangle rectangle;
			int width = DIAMETER + ADDED_WIDTH * (stateWrapper.endIndex - stateWrapper.startIndex);
			if (selectedState >= stateWrapper.startIndex && selectedState <= stateWrapper.endIndex) {
				rectangle = new Rectangle(width, DIAMETER, Color.DARKORANGE);
			} else {
				rectangle = new Rectangle(width, DIAMETER, Color.DARKBLUE);
			}
			rectangle.setArcHeight(DIAMETER);
			rectangle.setArcWidth(DIAMETER);
			rectangle.setUserData(stateWrapper.value);
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
			Tooltip t = new Tooltip(provider.getTextAt(idx, valueIndex, 0));
			Tooltip.install(rectangle, t);
			line.getChildren().add(rectangle);
			HBox.setMargin(rectangle, new Insets(MARGIN));
			valueIndex++;
			stateIndex = stateWrapper.endIndex + 1;
		}
	}
	
	private int findSelectedState(int start, int end) {
		int half = start + (end - start) / 2;
		int res = provider.getSelectedPossibleStep(0, half);
		if (res == -1) {
			return half;
		} else if (res == 0) {
			return findSelectedState(half+1, end);
		} else if (res == 1) {
			return findSelectedState(start, half);
		}
		return 0;
	}
	
	private void deepRefresh() {
		Platform.runLater(() -> {
			
			getChildren().clear();
			lines.clear();
			
			int traceLength = provider.getEnd(0);
			int selectedStateIndex = 0;
			
			if (provider.getSelectedPossibleStep(0, traceLength - 1) == -1) {
				selectedStateIndex = traceLength - 1;
			} else {
				selectedStateIndex = findSelectedState(0, traceLength);
			}
			for (int i=0;i<traceLength;i++) {
				if (provider.getSelectedPossibleStep(0, i) == -1) {
					selectedStateIndex = i;
					break;
				}
			}
			
			for (int i=0;i<provider.getNumberOfBranches();i++) {
				if (provider.getAt(i, 0) != null) {
					final HBox hBox = createLine(i);
					fillLine(hBox, i, provider.getStatesOrValues(i), traceLength, selectedStateIndex);
				} else {
					lines.add(null);
				}
			}
			
//			List<List<StepEvent>> events = provider.getStepEvents();
//			
//			for (int i=0;i<traceLength;i++) {
//				List<StepEvent> stateEvents = events.get(i);
//				for (StepEvent event : stateEvents) {
//					if (event.start) {
//						IStep step = event.step;
//						double startX = step.getStartingIndex() * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN + stackPane.getBoundsInParent().getMinX();
//						double endX = step.getEndingIndex() * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN + stackPane.getBoundsInParent().getMinX();
//						int y = DIAMETER/2 + MARGIN;
//						double controlX1 = startX + 5 * (step.getEndingIndex() - step.getStartingIndex());
//						double controlX2 = endX - 5 * (step.getEndingIndex() - step.getStartingIndex());
//						double controlY1 = y + 5 * (step.getEndingIndex() - step.getStartingIndex());
//						double controlY2 = y + 5 * (step.getEndingIndex() - step.getStartingIndex());
//						CubicCurve curve = new CubicCurve(startX, y, controlX1, controlY1, controlX2, controlY2, endX, y);
//						curve.setFill(Color.TRANSPARENT);
//						curve.setStroke(Color.DARKBLUE);
//						curve.setStrokeWidth(2);
//						stackPane.getChildren().add(curve);
//					}
//				}
//				stackPane.getChildren();
//			}
			
		});
	}

}
