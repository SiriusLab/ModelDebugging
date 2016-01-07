package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.List;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.VLineTo;


import org.eclipse.emf.ecore.EObject;
import org.gemoc.execution.sequential.javaengine.ui.debug.OmniscientGenericSequentialModelDebugger;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;


import fr.inria.diverse.trace.api.IStep;
import fr.inria.diverse.trace.api.IStep.StepEvent;
import fr.inria.diverse.trace.gemoc.traceaddon.ISequentialTimelineProvider;
import fr.inria.diverse.trace.gemoc.traceaddon.ISequentialTimelineProvider.StateWrapper;
import fr.obeo.timeline.model.ITimelineWindowListener;
import fr.obeo.timeline.view.ITimelineProvider;

public class FxTimeLineListener extends VBox implements ITimelineWindowListener {

	private ISequentialTimelineProvider provider;
	
	final private MultidimensionalTimeLineView multidimensionalTimeLineView;
	
	final Pane parent;
	
	final private List<Pane> lines = new ArrayList<>();
	
	public FxTimeLineListener(MultidimensionalTimeLineView multidimensionalTimeLineView, Pane parent) {
		this.multidimensionalTimeLineView = multidimensionalTimeLineView;
		this.parent = parent;
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
			throw new IllegalArgumentException("FxTimeLineListener expects an instance of ISequentialTimelineProvider");
		}
	}
	
	private Pane statesPane;
	private Path currentStateHighlight = null;
	
	private Pane createTracePane(int branch, Pane contentPane) {
		final Label titleLabel = new Label(provider.getTextAt(branch));
		final BorderPane borderPane = new BorderPane();
		titleLabel.setBackground(Background.EMPTY);
		borderPane.setBackground(Background.EMPTY);
		borderPane.setTop(titleLabel);
		if (branch == 0) {
			statesPane = new Pane(contentPane);
			statesPane.setBackground(Background.EMPTY);
			borderPane.setCenter(statesPane);
			titleLabel.minWidthProperty().bind(statesPane.widthProperty());
			contentPane.minWidthProperty().bind(statesPane.widthProperty());
			statesPane.minWidthProperty().bind(borderPane.widthProperty());
		} else {
			borderPane.setCenter(contentPane);
			titleLabel.minWidthProperty().bind(borderPane.widthProperty());
			contentPane.minWidthProperty().bind(borderPane.widthProperty());
			if (multidimensionalTimeLineView.getCurrentTrace() == branch) {
				borderPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
			}
			borderPane.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
				multidimensionalTimeLineView.handleTraceSelected(branch);
			});
		}
		return borderPane;
	}
	
	private HBox createLine(int branch) {
		final HBox hBox = new HBox();
		hBox.setBackground(Background.EMPTY);
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
	
	private void deepRefresh() {
		Platform.runLater(() -> {
			
			getChildren().clear();
			lines.clear();
			
			int traceLength = provider.getEnd(0);
			final int selectedStateIndex = provider.getSelectedPossibleStep(0,0);
			
			for (int i=0;i<provider.getNumberOfBranches();i++) {
				if (provider.getAt(i, 0) != null) {
					final HBox hBox = createLine(i);
					fillLine(hBox, i, provider.getStatesOrValues(i), traceLength, selectedStateIndex);
				} else {
					lines.add(null);
				}
			}
			
			List<StepEvent> events = provider.getStepEventsForState(selectedStateIndex);
			
			int nbIncoming = 0;
			int nbSelf = (int) events.stream()
					.filter(event-> {
						int startIdx = event.step.getStartingIndex();
						int endIdx = event.step.getEndingIndex();
						return event.start && startIdx == endIdx;
					})
					.count();
			int nbOutgoing = (int) events.stream()
					.filter(event-> {
						int idx = event.step.getEndingIndex();
						return event.start && (idx > selectedStateIndex || idx == -1);
					})
					.count() - 1;
			int eventIndex = 0;
			int nbEvents = events.size();
			
			double space = nbEvents == 0 ? 0 : ((DIAMETER * 0.75) / nbEvents);	
			if (currentStateHighlight != null) {
				parent.getChildren().remove(currentStateHighlight);
			}
			double rectX = selectedStateIndex * (2*MARGIN+DIAMETER);
			double rectWidth = DIAMETER + 2 * MARGIN;
			double rectHeight = parent.getHeight();
			currentStateHighlight = new Path();
			currentStateHighlight.getElements().addAll(
					new MoveTo(rectX,0),
					new VLineTo(rectHeight),
					new MoveTo(rectX+rectWidth,0),
					new VLineTo(rectHeight));
			currentStateHighlight.getStrokeDashArray().addAll(10., 10.);
			currentStateHighlight.setStrokeWidth(2.5);
			currentStateHighlight.setStroke(Color.GRAY);
			currentStateHighlight.setStrokeLineCap(StrokeLineCap.ROUND);
			parent.getChildren().add(0,currentStateHighlight);
			
			for (StepEvent event : events) {
				
				IStep step = event.step;
				int endingIndex = step.getEndingIndex();
				int startingIndex = step.getStartingIndex();
				Path path = new Path();

				if (startingIndex < selectedStateIndex) {
					// Incoming Step
					if (nbIncoming == 0 && startingIndex + 1 == selectedStateIndex) {
						// Straight line
						double x1 = startingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
						double x2 = endingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
						double y = DIAMETER/2 + MARGIN;
						MoveTo moveTo = new MoveTo(x1,y);
						HLineTo hLineTo = new HLineTo(x2);
						path.getElements().addAll(moveTo,hLineTo);
					} else {
						double x1 = startingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
						double x2 = endingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
						double y1 = DIAMETER/2 + MARGIN;
						double y2 = y1 + DIAMETER/2 + MARGIN * nbIncoming;
						MoveTo moveTo = new MoveTo(x1,y1);
						VLineTo vLineTo1 = new VLineTo(y2);
						HLineTo hLineTo = new HLineTo(x2);
						VLineTo vLineTo2 = new VLineTo(y1);
						path.getElements().addAll(moveTo,vLineTo1,hLineTo,vLineTo2);
					}
					nbIncoming++;
				} else if (startingIndex == endingIndex) {
					// Self step
					if (event.start) {
						// Treat only start events to avoid treating the same step twice
						int tmpIdx = eventIndex+1;
						StepEvent tmp = events.get(tmpIdx);
						int nb = 0;
						while (tmp.start || nb > 0) {
							if (tmp.start) {
								nb++;
							} else {
								nb--;
							}
							tmpIdx++;
							tmp = events.get(tmpIdx);
						}
						double x1 = startingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
						double x2 = endingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * tmpIdx;
						double y1 = DIAMETER/2 + MARGIN;
						double y2 = y1 + DIAMETER/2 + MARGIN * nbSelf;
						MoveTo moveTo = new MoveTo(x1,y1);
						VLineTo vLineTo1 = new VLineTo(y2);
						HLineTo hLineTo = new HLineTo(x2);
						path.getElements().addAll(moveTo,vLineTo1,hLineTo);
						VLineTo vLineTo2 = new VLineTo(y1);
						path.getElements().add(vLineTo2);
					}
					nbSelf--;
				} else {
					// Outgoing step
					endingIndex = endingIndex == -1 ? provider.getEnd(0) : endingIndex; 
					if (eventIndex == events.size() - 1) {
						// Straight line
						double x1 = startingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
						double x2 = endingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
						double y1 = DIAMETER/2 + MARGIN;
						MoveTo moveTo = new MoveTo(x1,y1);
						HLineTo hLineTo = new HLineTo(x2);
						path.getElements().addAll(moveTo,hLineTo);
					} else {
						double x1 = startingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
						double x2 = endingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
						double y1 = DIAMETER/2 + MARGIN;
						double y2 = y1 + DIAMETER/2 + MARGIN * nbOutgoing;
						MoveTo moveTo = new MoveTo(x1,y1);
						VLineTo vLineTo1 = new VLineTo(y2);
						HLineTo hLineTo = new HLineTo(x2);
						path.getElements().addAll(moveTo,vLineTo1,hLineTo);
						if (step.getEndingIndex() != -1) {
							VLineTo vLineTo2 = new VLineTo(y1);
							path.getElements().add(vLineTo2);
						}
					}
					nbOutgoing--;
				}
				
				eventIndex++;
				path.setStroke(Color.DARKBLUE);
				path.setStrokeWidth(2);
				statesPane.getChildren().add(0,path);
			}
		});
	}
}
