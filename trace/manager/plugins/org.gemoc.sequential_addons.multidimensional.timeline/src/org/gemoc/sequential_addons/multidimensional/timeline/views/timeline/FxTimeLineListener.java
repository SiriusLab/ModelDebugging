package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class FxTimeLineListener extends Pane implements ITimelineWindowListener {

	private ISequentialTimelineProvider provider;
	
	final private MultidimensionalTimeLineView multidimensionalTimeLineView;
	
	final private BorderPane statesLine;
	
	final private ScrollPane scrollPane;
	
	final private VBox valuesLines;

	final private DoubleProperty xOffset;
	
	final private DoubleProperty yOffset;
	
	final private DoubleProperty valueTitleWidth;
	
	final private BooleanProperty displayGrid;
	
	private BooleanBinding displayGridBinding;
	
	final private List<Double> scrollValues;
	
	public FxTimeLineListener(MultidimensionalTimeLineView multidimensionalTimeLineView, ScrollPane scrollPane) {
		this.multidimensionalTimeLineView = multidimensionalTimeLineView;
		this.scrollPane = scrollPane;
		statesLine = new BorderPane();
		valuesLines = new VBox();
		scrollValues = new ArrayList<>();
		
		xOffset = new SimpleDoubleProperty();
		yOffset = new SimpleDoubleProperty();
		valueTitleWidth = new SimpleDoubleProperty();
		displayGrid = new SimpleBooleanProperty();
		
		scrollPane.hvalueProperty().addListener((v,o,n) -> {
			Bounds bounds = localToScene(getBoundsInLocal());
			xOffset.set(-bounds.getMinX());
		});
		
		scrollPane.vvalueProperty().addListener((v,o,n) -> {
			if (!scrollValues.isEmpty()) {
				Double val = scrollValues.remove(0);
				scrollPane.setVvalue(val);
			} else {
				Bounds bounds = localToScene(getBoundsInLocal());
				yOffset.set(-bounds.getMinY());
			}
		});
				
		statesLine.translateYProperty().bind(yOffset);
		valuesLines.layoutYProperty().bind(statesLine.heightProperty());
		
		statesLine.setBackground(HEADER_BACKGROUND);
		valuesLines.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		getChildren().add(valuesLines);
		getChildren().add(statesLine);
		minHeightProperty().bind(statesLine.heightProperty().add(valuesLines.heightProperty()));
		prefHeightProperty().bind(statesLine.heightProperty().add(valuesLines.heightProperty()));
		maxHeightProperty().bind(statesLine.heightProperty().add(valuesLines.heightProperty()));
	}
	
	private Pane statesPane;
	private Path statesGrid = null;
	private Rectangle highlightRectangle = null;
	
	private Pane createTracePane(int branch, Pane contentPane) {
		final Label titleLabel = new Label(provider.getTextAt(branch) + "  ");
		Pane result;
		if (branch == 0) {
			titleLabel.translateXProperty().bind(xOffset);
			BorderPane.setMargin(titleLabel, new Insets(MARGIN/2));
			statesLine.setTop(titleLabel);
			statesPane = new Pane(contentPane);
			BorderPane.setMargin(statesPane, MARGIN_INSETS);
			statesLine.setCenter(statesPane);
			contentPane.minWidthProperty().bind(statesPane.widthProperty());
			statesPane.minWidthProperty().bind(statesLine.widthProperty());
			result = statesLine;
		} else {
			titleLabel.minWidthProperty().bind(valueTitleWidth);
			final HBox titlePane = new HBox();
			titlePane.translateXProperty().bind(xOffset);
			final BorderPane borderPane = new BorderPane();
			final Button backValue = new Button("", new ImageView(new Image("/icons/backvalue.png")));
			backValue.setOnAction((e)->{
				multidimensionalTimeLineView.handleBackValue(branch);
			});
			backValue.setDisable(!multidimensionalTimeLineView.canBackValue(branch));
			final Button stepValue = new Button("", new ImageView(new Image("/icons/stepvalue.png")));
			stepValue.setOnAction((e)->{
				multidimensionalTimeLineView.handleStepValue(branch);
			});
			stepValue.setDisable(!multidimensionalTimeLineView.canStepValue(branch));
			titlePane.setAlignment(Pos.CENTER_LEFT);
			titlePane.getChildren().addAll(titleLabel,backValue,stepValue);
			BorderPane.setMargin(titlePane, new Insets(MARGIN/2));
			borderPane.setTop(titlePane);
			BorderPane.setMargin(contentPane, MARGIN_INSETS);
			borderPane.setCenter(contentPane);
			contentPane.minWidthProperty().bind(borderPane.widthProperty());
			valuesLines.getChildren().add(borderPane);
			titleLabel.minWidthProperty().bind(valueTitleWidth);
			titleLabel.widthProperty().addListener((v,o,n)->{
				if (n.doubleValue() > valueTitleWidth.get()) {
					valueTitleWidth.set(n.doubleValue());
				}
			});
			result = borderPane;
		}
		return result;
	}

	private static final int MARGIN = 6;
	private static final int DIAMETER = 24;
	private static final int ADDED_WIDTH = DIAMETER + 2 * MARGIN;
	private static final Insets MARGIN_INSETS = new Insets(MARGIN);
	private static final Background HEADER_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	
	private HBox createLine(int branch) {
		final HBox hBox = new HBox();
		final Pane pane = createTracePane(branch, hBox);
		pane.minWidthProperty().bind(widthProperty());
		pane.setFocusTraversable(true);
		return hBox;
	}

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
			
			if (displayGridBinding == null) {
				displayGridBinding = new BooleanBinding() {
					@Override
					protected boolean computeValue() {
						return rectangle.hoverProperty().get();
					}
				};
			} else {
				displayGridBinding = displayGridBinding.or(rectangle.hoverProperty());
			}
			
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
			
			scrollValues.add(scrollPane.getVvalue());
			
			valuesLines.getChildren().clear();
			
			int traceLength = provider.getEnd(0);
			final int selectedStateIndex = provider.getSelectedPossibleStep(0,0);
			
			displayGrid.unbind();
			displayGridBinding = null;
			
			for (int i=0;i<provider.getNumberOfBranches();i++) {
				if (provider.getAt(i, 0) != null) {
					final HBox hBox = createLine(i);
					fillLine(hBox, i, provider.getStatesOrValues(i), traceLength, selectedStateIndex);
				}
			}
			
			displayGrid.bind(displayGridBinding);
			
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
			
			if (statesGrid != null) {
				getChildren().remove(statesGrid);
			}
			if (highlightRectangle != null) {
				getChildren().remove(highlightRectangle);
			}
			double rectHeight = getHeight();
			statesGrid = new Path();
			statesGrid.visibleProperty().bind(displayGrid);
			highlightRectangle = new Rectangle();
			for (int i=0;i<=traceLength;i++) {
				if (i == selectedStateIndex) {
					highlightRectangle.setX(i*(2*MARGIN+DIAMETER));
					highlightRectangle.setWidth(2*MARGIN+DIAMETER);
					highlightRectangle.setHeight(rectHeight);
				}
				statesGrid.getElements().addAll(new MoveTo(i*(2*MARGIN+DIAMETER),0),new VLineTo(rectHeight));
			}
			statesGrid.getStrokeDashArray().addAll(10., 10.);
			statesGrid.setStrokeWidth(1);
			statesGrid.setStroke(Color.GRAY);
			statesGrid.setStrokeLineCap(StrokeLineCap.ROUND);
			getChildren().add(0,statesGrid);
			highlightRectangle.setFill(Color.LIGHTGRAY);
			getChildren().add(0,highlightRectangle);
		});
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
		if (provider == null) {
			this.provider = null;
		} else if (provider instanceof ISequentialTimelineProvider) {
			this.provider = (ISequentialTimelineProvider)provider;
			deepRefresh();
		} else {
			throw new IllegalArgumentException("FxTimeLineListener expects an instance of ISequentialTimelineProvider");
		}
	}
}
