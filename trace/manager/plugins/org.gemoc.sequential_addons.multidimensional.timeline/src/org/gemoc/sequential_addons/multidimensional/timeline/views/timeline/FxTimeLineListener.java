package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
	
	final private IntegerProperty currentState;
	
	final private MultidimensionalTimeLineView multidimensionalTimeLineView;
	
	final private BorderPane statesLine;
	
	final private ScrollPane scrollPane;
	
	final private VBox valuesLines;

	final private DoubleProperty xOffset;
	
	final private DoubleProperty yOffset;
	
	final private DoubleProperty valueTitleWidth;
	
	final private DoubleProperty statesPaneHeight;
	
	final private BooleanProperty displayGrid;
	
	private BooleanBinding displayGridBinding;
	
	final private IntegerProperty nbDisplayableStates;
	
	final private IntegerProperty visibleStatesRange;
	
	final private IntegerProperty nbStates;
	
	final private DoubleBinding gridHeightBinding;

	final private List<Double> scrollVvalues;
	
	final private Font font = Font.font("Arial", FontWeight.BOLD, 12);

	final private Image stepValueGraphic;
	
	final private Image backValueGraphic;
	
	public FxTimeLineListener(MultidimensionalTimeLineView multidimensionalTimeLineView, ScrollPane scrollPane) {
		this.multidimensionalTimeLineView = multidimensionalTimeLineView;
		this.scrollPane = scrollPane;
		statesLine = new BorderPane();
		valuesLines = new VBox();
		backValueGraphic = new Image("/icons/backvalue.png");
		stepValueGraphic = new Image("/icons/stepvalue.png");
		scrollVvalues = new ArrayList<>();
		
		xOffset = new SimpleDoubleProperty();
		yOffset = new SimpleDoubleProperty();
		valueTitleWidth = new SimpleDoubleProperty();
		statesPaneHeight = new SimpleDoubleProperty();
		displayGrid = new SimpleBooleanProperty();
		
		nbDisplayableStates = new SimpleIntegerProperty();
		nbDisplayableStates.bind(scrollPane.widthProperty().divide(UNIT));
		nbStates = new SimpleIntegerProperty(0);
		currentState = new SimpleIntegerProperty();
		visibleStatesRange = new SimpleIntegerProperty();
		visibleStatesRange.bind(nbStates.add(1).subtract(nbDisplayableStates));
		
		nbDisplayableStates.addListener((v,o,n)->{
			deepRefresh();
		});
		currentState.addListener((v,o,n)->{
			deepRefresh();
		});
		
		scrollPane.setFitToWidth(true);
		
		scrollPane.vvalueProperty().addListener((v,o,n) -> {
			if (!scrollVvalues.isEmpty()) {
				Double val = scrollVvalues.remove(0);
				scrollPane.setVvalue(val);
			}
			Bounds bounds = localToScene(getBoundsInLocal());
			yOffset.set(-bounds.getMinY());
		});
		
		getChildren().addListener(new ListChangeListener<Node>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Node> c) {
				List<Node> addedNodes = new ArrayList<>();
				while(c.next()) {
					addedNodes.addAll(c.getAddedSubList());
				}
				addedNodes.stream().forEach((n)->{
					n.translateXProperty().bind(xOffset);
				});
			}
		});
		
//		statesLine.minHeightProperty().bind(statesPaneHeight);
		statesPane.minHeightProperty().bind(statesPaneHeight);
		statesLine.minWidthProperty().bind(widthProperty());
		statesLine.maxWidthProperty().bind(widthProperty());
		valuesLines.minWidthProperty().bind(widthProperty());
		valuesLines.maxWidthProperty().bind(widthProperty());
		statesLine.translateYProperty().bind(yOffset);
		valuesLines.layoutYProperty().bind(statesLine.heightProperty());
		gridHeightBinding = statesLine.heightProperty().add(valuesLines.heightProperty());
		
		statesLine.setBackground(HEADER_BACKGROUND);
		valuesLines.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		setupStatesPane();
		
		getChildren().add(valuesLines);
		getChildren().add(statesLine);
		minHeightProperty().bind(statesLine.heightProperty().add(valuesLines.heightProperty()));
		prefHeightProperty().bind(statesLine.heightProperty().add(valuesLines.heightProperty()));
		maxHeightProperty().bind(statesLine.heightProperty().add(valuesLines.heightProperty()));
		
		bback.setOnAction((e)->{
			currentState.set(Math.max(0,currentState.get() - 10));
		});
		back.setOnAction((e)->{
			currentState.set(Math.max(0,currentState.get() - 1));
		});
		bback.disableProperty().bind(currentState.greaterThan(0).not());
		back.disableProperty().bind(bback.disabledProperty());
		
		next.setOnAction((e)->{
			currentState.set(Math.min(visibleStatesRange.intValue()-1,currentState.get() + 1));
		});
		nnext.setOnAction((e)->{
			currentState.set(Math.min(visibleStatesRange.intValue()-1,currentState.get() + 10));
		});
		nnext.disableProperty().bind(currentState.lessThan(visibleStatesRange.subtract(1)).not());
		next.disableProperty().bind(nnext.disabledProperty());
		
		goToTextField.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
			@Override
			public Change apply(Change t) {
				String text = t.getText();
				if (!text.matches("[0-9]")) {
					t.setText("");
				}
				return t;
			}
		}));
		jump.setOnAction((e)->{
			showState(Integer.valueOf(goToTextField.getText()),true);
		});
		goTo.setOnAction((e)->{
			showState(Integer.valueOf(goToTextField.getText()),false);
		});
		goToTextField.maxWidthProperty().bind(goTo.widthProperty());
		goToTextField.minWidthProperty().bind(goTo.widthProperty());

		bback.setBorder(Border.EMPTY);
		back.setBorder(Border.EMPTY);
		next.setBorder(Border.EMPTY);
		nnext.setBorder(Border.EMPTY);
		goTo.setBorder(Border.EMPTY);
		jump.setBorder(Border.EMPTY);
		goToTextField.setBorder(Border.EMPTY);
		followCurrent.setBorder(Border.EMPTY);

		Tooltip.install(goTo, goToTooltip);
		Tooltip.install(jump, jumpTooltip);
		Tooltip.install(followCurrent, followTooltip);
		
	}
	
	private void showState(int state, boolean jump) {
		int toShow = Math.min(nbStates.intValue()-1,Math.max(0, state));
		int effectiveToShow = Math.min(visibleStatesRange.intValue()-1,
				Math.max(0, toShow - nbDisplayableStates.intValue() / 2));
		if (jump) {
			IBasicExecutionEngine engine = multidimensionalTimeLineView.getCurrentEngine();
			for (OmniscientGenericSequentialModelDebugger traceAddon : engine
					.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
				traceAddon.jump(toShow);
			}
		}
		currentState.set(effectiveToShow);
	}
	
	private void initialize() {
		
	}
	
	
	private Path statesGrid = null;
	private Rectangle highlightRectangle = null;
	
	private final Button bback = new Button("<<");
	private final Button back = new Button("<");
	private final Button next = new Button(">");
	private final Button nnext = new Button(">>");
	private final Button goTo = new Button("Go");
	private final Button jump = new Button("Jump");
	private final TextField goToTextField = new TextField();
	private final CheckBox followCurrent = new CheckBox("Follow");
	private final Pane statesPane = new Pane();
	private final Tooltip goToTooltip = new Tooltip("Shows the specified state in the timeline");
	private final Tooltip jumpTooltip = new Tooltip("Shows the specified state in the timeline\n"
			+ "and jumps to that state");
	private final Tooltip followTooltip = new Tooltip("Check to automatically show the current state\n"
			+ "when it changes");
	
	private Pane setupStatesPane() {
		final Label titleLabel = new Label("All execution states (0)");
		nbStates.addListener((v,o,n)->{
			String s = "All execution states (" + n.intValue() + ")";
			Platform.runLater(() -> {
				titleLabel.setText(s);
			});
		});
		titleLabel.setFont(font);
		BorderPane.setMargin(titleLabel, HALF_MARGIN_INSETS);
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		final HBox leftBox = new HBox(bback,back);
		final HBox rightBox = new HBox(next,nnext);
		final HBox centerPane = new HBox(goToTextField,goTo,jump,followCurrent);
		HBox.setMargin(goToTextField,HALF_MARGIN_INSETS);
		HBox.setMargin(goTo,HALF_MARGIN_INSETS);
		HBox.setMargin(jump,HALF_MARGIN_INSETS);
		HBox.setMargin(followCurrent,HALF_MARGIN_INSETS);
		centerPane.setAlignment(Pos.CENTER);
		final BorderPane buttons = new BorderPane();
		buttons.setLeft(leftBox);
		buttons.setRight(rightBox);
		buttons.setCenter(centerPane);
		statesLine.setTop(titleLabel);
		statesLine.setCenter(buttons);
		BorderPane.setMargin(statesPane, MARGIN_INSETS);
		statesLine.setBottom(statesPane);
		statesLine.minWidthProperty().bind(valuesLines.widthProperty());
		return statesLine;
	}
	
	private Pane setupValuePane(int line, Label titleLabel, Pane contentPane) {
		final HBox titlePane = new HBox();
		final BorderPane borderPane = new BorderPane();
		final Node backValueGraphicNode = new ImageView(backValueGraphic);
		backValueGraphicNode.setScaleX(1.33);
		backValueGraphicNode.setScaleY(1.33);
		final Button backValue = new Button("", backValueGraphicNode);
		backValue.setOnAction((e)->{
			multidimensionalTimeLineView.handleBackValue(line);
		});
		backValue.setScaleX(0.75);
		backValue.setScaleY(0.75);
		backValue.setDisable(!multidimensionalTimeLineView.canBackValue(line));
		final Node stepValueGraphicNode = new ImageView(stepValueGraphic);
		stepValueGraphicNode.setScaleX(1.33);
		stepValueGraphicNode.setScaleY(1.33);
		final Button stepValue = new Button("", stepValueGraphicNode);
		stepValue.setOnAction((e)->{
			multidimensionalTimeLineView.handleStepValue(line);
		});
		stepValue.setDisable(!multidimensionalTimeLineView.canStepValue(line));
		stepValue.setScaleX(0.75);
		stepValue.setScaleY(0.75);
		titlePane.setAlignment(Pos.CENTER_LEFT);
		titlePane.getChildren().addAll(titleLabel,backValue,stepValue);
		BorderPane.setMargin(titlePane, HALF_MARGIN_INSETS);
		borderPane.setTop(titlePane);
		BorderPane.setMargin(contentPane, MARGIN_INSETS);
		borderPane.setCenter(contentPane);
		valuesLines.getChildren().add(borderPane);
		titleLabel.minWidthProperty().bind(valueTitleWidth);
		titleLabel.widthProperty().addListener((v,o,n)->{
			if (n.doubleValue() > valueTitleWidth.get()) {
				valueTitleWidth.set(n.doubleValue());
			}
		});
		return borderPane;
	}
	
	private Pane createTracePane(int line, Pane contentPane) {
		Pane result;
		if (line == 0) {
			statesPane.getChildren().add(contentPane);
			result = statesLine;
		} else {
			final Label titleLabel = new Label(provider.getTextAt(line) + "  ");
			titleLabel.setFont(font);
			result = setupValuePane(line, titleLabel, contentPane);
		}
		return result;
	}

	private static final int MARGIN = 8;
	private static final int DIAMETER = 24;
	private static final int UNIT = DIAMETER + 2 * MARGIN;
	private static final Insets MARGIN_INSETS = new Insets(MARGIN);
	private static final Insets HALF_MARGIN_INSETS = new Insets(MARGIN/2);
	private static final Background HEADER_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	
	private HBox createLine(int branch) {
		final HBox hBox = new HBox();
		final Pane pane = createTracePane(branch, hBox);
		pane.setFocusTraversable(true);
		return hBox;
	}

	private void fillLine(HBox line, int idx, List<StateWrapper> stateWrappers, int selectedState) {

		final boolean isStatesLine = idx == 0;
		
		final Color currentColor = isStatesLine ? Color.CORAL : Color.DARKORANGE;
		final Color otherColor = isStatesLine ? Color.SLATEBLUE : Color.DARKBLUE;
		
		line.getChildren().clear();
		
		int valueIndex = 0;
		final int currentStateIndex = Math.max(0,currentState.intValue());
		int stateIndex = currentStateIndex;
		
		int diff = stateWrappers.isEmpty() ? 0 : currentStateIndex - stateWrappers.get(0).startIndex;
		
		if (diff > 0) {
			line.setTranslateX(-(UNIT*diff));
		}		
		
		for (StateWrapper stateWrapper : stateWrappers) {
			if (stateWrapper.startIndex > stateIndex) {
				int width = DIAMETER + UNIT * (stateWrapper.startIndex - stateIndex - 1);
				final Rectangle rectangle = new Rectangle(width, DIAMETER, Color.TRANSPARENT);
				line.getChildren().add(rectangle);
				HBox.setMargin(rectangle, new Insets(MARGIN));
			}
			
			final Rectangle rectangle;
			final int width = DIAMETER + UNIT * (stateWrapper.endIndex - stateWrapper.startIndex);
			if (selectedState >= stateWrapper.startIndex && selectedState <= stateWrapper.endIndex) {
				rectangle = new Rectangle(width, DIAMETER, currentColor);
			} else {
				rectangle = new Rectangle(width, DIAMETER, otherColor);
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
			
			displayGridBinding = displayGridBinding.or(rectangle.hoverProperty());
			
			final Tooltip t = new Tooltip(provider.getTextAt(idx, valueIndex, 0));
			Tooltip.install(rectangle, t);
			if (isStatesLine) {
				Label text = new Label("" + stateWrapper.endIndex);
				text.setTextOverrun(OverrunStyle.ELLIPSIS);
				text.setAlignment(Pos.CENTER);
				text.setMouseTransparent(true);
				text.setTextFill(Color.WHITE);
				text.setFont(font);
				text.setMaxWidth(width);
				StackPane layout = new StackPane();
				StackPane.setMargin(rectangle, MARGIN_INSETS);
				layout.getChildren().addAll(rectangle,text);
				line.getChildren().add(layout);
			} else {
				line.getChildren().add(rectangle);
				HBox.setMargin(rectangle, MARGIN_INSETS);
			}
			valueIndex++;
			stateIndex = stateWrapper.endIndex + 1;
		}
	}
	
	public void deepRefresh() {
		Platform.runLater(() -> {

			if (provider == null) {
				return;
			}

			final int currentStateStartIndex = Math.max(0,currentState.intValue());
			final int currentStateEndIndex = currentStateStartIndex+nbDisplayableStates.intValue();

			scrollVvalues.add(scrollPane.getVvalue());
			
			if (statesPane.getHeight() > statesPaneHeight.doubleValue()) {
				statesPaneHeight.set(statesPane.getHeight());
			}
			
			valuesLines.getChildren().clear();
			statesPane.getChildren().clear();
			
			final int selectedStateIndex = provider.getSelectedPossibleStep(0,0);
			
			displayGrid.unbind();
			displayGridBinding = new BooleanBinding() {
				@Override
				protected boolean computeValue() {
					return false;
				}
			};
			
			for (int i=0;i<provider.getNumberOfBranches();i++) {
				if (provider.getAt(i, 0) != null) {
					final HBox hBox = createLine(i);
					fillLine(hBox, i,
							provider.getStatesOrValues(i,currentStateStartIndex,currentStateEndIndex),
							selectedStateIndex);
				}
			}
			
			displayGrid.bind(displayGridBinding);
			
			if (selectedStateIndex >= currentStateStartIndex && selectedStateIndex <= currentStateEndIndex) {
			
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
					int startingIndex = step.getStartingIndex();
					int endingIndex = step.getEndingIndex();
					int effectiveStartingIndex = startingIndex - currentStateStartIndex;
					int effectiveEndingIndex = endingIndex - currentStateStartIndex;
					Path path = new Path();
	
					if (startingIndex < selectedStateIndex) {
						// Incoming Step
						if (nbIncoming == 0 && startingIndex + 1 == selectedStateIndex) {
							// Straight line
							double x1 = effectiveStartingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
							double x2 = effectiveEndingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
							double y = DIAMETER/2 + MARGIN;
							MoveTo moveTo = new MoveTo(x1,y);
							HLineTo hLineTo = new HLineTo(x2);
							path.getElements().addAll(moveTo,hLineTo);
						} else {
							double x1 = effectiveStartingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
							double x2 = effectiveEndingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
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
							double x1 = effectiveStartingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
							double x2 = effectiveEndingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * tmpIdx;
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
						effectiveEndingIndex = endingIndex == -1 ? nbStates.intValue() - currentStateStartIndex : effectiveEndingIndex;
						if (eventIndex == events.size() - 1) {
							// Straight line
							double x1 = effectiveStartingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
							double x2 = effectiveEndingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
							double y1 = DIAMETER/2 + MARGIN;
							MoveTo moveTo = new MoveTo(x1,y1);
							HLineTo hLineTo = new HLineTo(x2);
							path.getElements().addAll(moveTo,hLineTo);
						} else {
							double x1 = effectiveStartingIndex * (2*MARGIN+DIAMETER) + DIAMETER/8 + MARGIN + space * eventIndex;
							double x2 = effectiveEndingIndex * (2*MARGIN+DIAMETER) + DIAMETER/2 + MARGIN;
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
			}
			
			if (statesGrid != null) {
				getChildren().remove(statesGrid);
			}
			if (highlightRectangle != null) {
				getChildren().remove(highlightRectangle);
			}
			
			statesGrid = new Path();
			final VLineTo vLineTo = new VLineTo();
			vLineTo.yProperty().bind(gridHeightBinding);
			statesGrid.visibleProperty().bind(displayGrid);
			highlightRectangle = new Rectangle();
			for (int i=currentStateStartIndex;i<=currentStateEndIndex;i++) {
				if (i == selectedStateIndex) {
					highlightRectangle.setX(MARGIN+(i-currentStateStartIndex)*(2*MARGIN+DIAMETER));
					highlightRectangle.setWidth(2*MARGIN+DIAMETER);
					highlightRectangle.heightProperty().bind(gridHeightBinding);
				}
				statesGrid.getElements().addAll(new MoveTo(MARGIN+(i-currentStateStartIndex)*(2*MARGIN+DIAMETER),0),vLineTo);
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
	public void endChanged(int current, int end) {
		nbStates.set(end);
		if (followCurrent.isSelected()) {
			showState(current, false);
		}
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
