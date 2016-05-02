/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.executionframework.engine.mse.Step;
import org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView.Command;

import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer.StateWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer.StepWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer.ValueWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceListener;

public class FxTraceListener extends Pane implements ITraceListener {

	private ITraceExplorer traceExplorer;

	final private IntegerProperty currentState;

	final private IntegerProperty currentStep;

	final private ScrollPane bodyScrollPane;

	final private VBox headerPane;

	final private Pane bodyPane;

	final private VBox valuesLines;

	final private DoubleProperty valueTitleWidth;

	final private DoubleProperty statesPaneHeight;

	final private BooleanProperty displayGrid;

	private BooleanBinding displayGridBinding;

	final private IntegerProperty nbDisplayableStates;

	final private IntegerProperty visibleStatesRange;

	final private IntegerProperty nbStates;

	final private Font statesFont = Font.font("Arial", FontWeight.BOLD, 12);

	final private Font valuesFont = Font.font("Arial", FontWeight.BOLD, 11);

	final private Font stateNumbersFont = Font.font("Arial", FontWeight.BOLD, 9);

	final private Image stepValueGraphic;

	final private Image backValueGraphic;

	public FxTraceListener() {
		headerPane = new VBox();
		valuesLines = new VBox();
		bodyPane = new Pane();
		bodyScrollPane = new ScrollPane(bodyPane);
		backValueGraphic = new Image("/icons/nav_backward.gif");
		stepValueGraphic = new Image("/icons/nav_forward.gif");
		playGraphic = new Image("/icons/start_task.gif");
		replayGraphic = new Image("/icons/restart_task.gif");

		valueTitleWidth = new SimpleDoubleProperty();
		statesPaneHeight = new SimpleDoubleProperty();
		displayGrid = new SimpleBooleanProperty();
		isInReplayMode = new SimpleBooleanProperty();

		nbDisplayableStates = new SimpleIntegerProperty();
		nbDisplayableStates.bind(headerPane.widthProperty().divide(UNIT));
		nbStates = new SimpleIntegerProperty(0);
		currentState = new SimpleIntegerProperty();
		currentStep = new SimpleIntegerProperty(0);
		visibleStatesRange = new SimpleIntegerProperty();
		visibleStatesRange.bind(nbStates.add(1).subtract(nbDisplayableStates));

		nbDisplayableStates.addListener((v, o, n) -> {
			deepRefresh();
		});
		currentState.addListener((v, o, n) -> {
			deepRefresh();
		});
		currentStep.addListener((v, o, n) -> {
			deepRefresh();
		});
		visibleStatesRange.addListener((v, o, n) -> {
			if (currentState.intValue() >= visibleStatesRange.intValue()) {
				currentState.set(visibleStatesRange.intValue() - 1);
			}
		});

		bodyScrollPane.setFitToWidth(true);
		bodyScrollPane.setBorder(Border.EMPTY);
		bodyScrollPane.setBackground(BODY_BACKGROUND);
		bodyScrollPane.setVisible(false);
		bodyPane.setBackground(BODY_BACKGROUND);

		statesPane.minHeightProperty().bind(statesPaneHeight);
		statesPane.heightProperty().addListener((v, o, n) -> {
			if (n.doubleValue() > statesPaneHeight.doubleValue()) {
				statesPaneHeight.set(n.doubleValue());
			}
		});
		headerPane.minWidthProperty().bind(widthProperty());
		headerPane.maxWidthProperty().bind(widthProperty());
		valuesLines.minWidthProperty().bind(widthProperty());
		valuesLines.maxWidthProperty().bind(widthProperty());
		valuesLines.getChildren().addListener(new ListChangeListener<Node>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Node> c) {
				List<? extends Node> l = c.getList();
				int i = 0;
				for (Node n : l) {
					Pane p = (Pane) n;
					if (i % 2 == 1) {
						p.setBackground(LINE_BACKGROUND);
					} else {
						p.setBackground(TRANSPARENT_BACKGROUND);
					}
					i++;
				}
			}
		});

		headerPane.setBackground(HEADER_BACKGROUND);
		valuesLines.setBackground(TRANSPARENT_BACKGROUND);
		setBackground(BODY_BACKGROUND);

		setupStatesPane();

		bodyPane.getChildren().add(valuesLines);
		bodyScrollPane.translateYProperty().bind(headerPane.heightProperty());
		bodyScrollPane.maxHeightProperty().bind(heightProperty().subtract(headerPane.heightProperty()));

		getChildren().add(headerPane);
		getChildren().add(bodyScrollPane);
		minHeightProperty().bind(headerPane.heightProperty().add(bodyScrollPane.heightProperty()));
		prefHeightProperty().bind(headerPane.heightProperty().add(bodyScrollPane.heightProperty()));
		maxHeightProperty().bind(headerPane.heightProperty().add(bodyScrollPane.heightProperty()));
	}

	private void showState(int state, boolean jump) {
		int toShow = Math.min(nbStates.intValue() - 1, Math.max(0, state));
		int effectiveToShow = Math.min(visibleStatesRange.intValue() - 1,
				Math.max(0, toShow - nbDisplayableStates.intValue() / 2));
		if (jump) {
			traceExplorer.jump(toShow);
		}
		currentState.set(effectiveToShow);
	}

	private Path statesGrid = null;
	private Rectangle highlightRectangle = null;

	private boolean scrollLock = false;
	private final Pane statesPane = new Pane();

	private final Image playGraphic;
	private final Image replayGraphic;

	private final BooleanProperty isInReplayMode;

	private Pane setupStatesPane() {
		final Label titleLabel = new Label("All execution states (0)");
		nbStates.addListener((v, o, n) -> {
			String s = "All execution states (" + n.intValue() + ")";
			Platform.runLater(() -> {
				titleLabel.setText(s);
				titleLabel.setContentDisplay(ContentDisplay.RIGHT);
				final ImageView nodeGraphic = new ImageView();
				nodeGraphic.setImage(playGraphic);
				titleLabel.setGraphic(nodeGraphic);
				isInReplayMode.addListener((val, old, neu) -> {
					if (old != neu) {
						if (neu) {
							nodeGraphic.setImage(replayGraphic);
						} else {
							nodeGraphic.setImage(playGraphic);
						}
					}
				});
			});
		});
		titleLabel.setFont(statesFont);
		VBox.setMargin(titleLabel, HALF_MARGIN_INSETS);
		titleLabel.setAlignment(Pos.CENTER);
		final ScrollBar scrollBar = new ScrollBar();
		scrollBar.setVisibleAmount(1);
		scrollBar.setBlockIncrement(10);
		scrollBar.setMin(0);
		final IntegerBinding statesRange = visibleStatesRange.subtract(1);
		scrollBar.disableProperty().bind(statesRange.lessThanOrEqualTo(0));
		scrollBar.maxProperty().bind(statesRange);
		scrollBar.valueProperty().addListener((v, o, n) -> {
			if (o.intValue() != n.intValue() && n.intValue() != currentState.intValue()) {
				currentState.set(n.intValue());
			}
		});
		currentState.addListener((v, o, n) -> {
			if (o.intValue() != n.intValue() && n.intValue() != scrollBar.valueProperty().intValue()) {
				scrollBar.setValue(n.intValue());
			}
		});
		final HBox hBox = new HBox();
		final Polygon arrow = new Polygon(2.5, 10, 10, 5, 2.5, 0);
		HBox.setMargin(arrow, HALF_MARGIN_INSETS);
		final Label toggleValuesLabel = new Label("Timeline for dynamic information	");
		toggleValuesLabel.setFont(statesFont);
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.getChildren().addAll(arrow, toggleValuesLabel);
		hBox.setCursor(Cursor.HAND);
		hBox.setOnMouseClicked((e) -> {
			if (bodyScrollPane.isVisible()) {
				bodyScrollPane.setVisible(false);
				arrow.setRotate(0);
			} else {
				bodyScrollPane.setVisible(true);
				arrow.setRotate(90);
			}

		});
		VBox.setMargin(hBox, HALF_MARGIN_INSETS);
		headerPane.getChildren().addAll(scrollBar, titleLabel, statesPane, hBox);
		VBox.setMargin(statesPane, MARGIN_INSETS);

		return headerPane;
	}

	private final Map<Integer, Boolean> displayLine = new HashMap<>();

	public void openColorPicker() {
		Popup popup = new Popup();
		popup.getContent().add(new ColorPicker());
		popup.show(this, 0, 0);
	}

	private Pane setupValuePane(int line, Label titleLabel, Pane contentPane) {
		final HBox titlePane = new HBox();
		final VBox valueVBox = new VBox();
		final Node backValueGraphicNode = new ImageView(backValueGraphic);
		final double buttonScale = 0.66;
		backValueGraphicNode.setScaleX(1 / buttonScale);
		backValueGraphicNode.setScaleY(1 / buttonScale);
		final Button backValue = new Button("", backValueGraphicNode);
		backValue.setOnAction((e) -> {
			traceExplorer.backValue(line - 1);
		});
		backValue.setScaleX(buttonScale);
		backValue.setScaleY(buttonScale);
		backValue.setDisable(!traceExplorer.canBackValue(line - 1));
		final Node stepValueGraphicNode = new ImageView(stepValueGraphic);
		stepValueGraphicNode.setScaleX(1 / buttonScale);
		stepValueGraphicNode.setScaleY(1 / buttonScale);
		final Button stepValue = new Button("", stepValueGraphicNode);
		stepValue.setOnAction((e) -> {
			traceExplorer.stepValue(line - 1);
		});
		stepValue.setDisable(!traceExplorer.canStepValue(line - 1));
		stepValue.setScaleX(buttonScale);
		stepValue.setScaleY(buttonScale);
		titlePane.setAlignment(Pos.CENTER_LEFT);
		VBox.setMargin(titlePane, HALF_MARGIN_INSETS);
		VBox.setMargin(contentPane, MARGIN_INSETS);

		final CheckBox showValueCheckBox = new CheckBox();
		showValueCheckBox.setScaleX(buttonScale);
		showValueCheckBox.setScaleY(buttonScale);
		boolean hide = displayLine.get(line) != null && !displayLine.get(line);
		if (hide) {
			showValueCheckBox.setSelected(false);
		} else {
			showValueCheckBox.setSelected(true);
		}
		BooleanProperty sel = showValueCheckBox.selectedProperty();
		backValue.visibleProperty().bind(sel);
		stepValue.visibleProperty().bind(sel);
		sel.addListener((v, o, n) -> {
			if (o != n) {
				displayLine.put(line, n);
				if (n) {
					valueVBox.getChildren().add(contentPane);
				} else {
					valueVBox.getChildren().remove(contentPane);
				}
			}
		});
		titlePane.getChildren().addAll(showValueCheckBox, titleLabel, backValue, stepValue);
		valueVBox.getChildren().add(titlePane);
		if (!hide) {
			valueVBox.getChildren().add(contentPane);
		}

		valuesLines.getChildren().add(valueVBox);
		titleLabel.minWidthProperty().bind(valueTitleWidth);
		titleLabel.widthProperty().addListener((v, o, n) -> {
			if (n.doubleValue() > valueTitleWidth.get()) {
				valueTitleWidth.set(n.doubleValue());
			}
		});

		return valueVBox;
	}

	private final Map<Integer, String> valueNames = new HashMap<>();

	private Pane createTracePane(int line, Pane contentPane) {
		Pane result;
		if (line == 0) {
			statesPane.getChildren().add(contentPane);
			result = headerPane;
		} else {
			final String title = valueNames.computeIfAbsent(line, i -> {
				return traceExplorer.getTextAt(i) + "  ";
			});
			final Label titleLabel = new Label(title);
			titleLabel.setFont(valuesFont);
			result = setupValuePane(line, titleLabel, contentPane);
		}
		return result;
	}

	private static final int H_MARGIN = 8;
	private static final int V_MARGIN = 2;
	private static final int DIAMETER = 24;
	private static final int V_HEIGHT = 8;
	private static final int UNIT = DIAMETER + 2 * H_MARGIN;
	private static final Insets MARGIN_INSETS = new Insets(V_MARGIN, H_MARGIN, V_MARGIN, H_MARGIN);
	private static final Insets HALF_MARGIN_INSETS = new Insets(V_MARGIN, H_MARGIN / 2, V_MARGIN, H_MARGIN / 2);
	private static final Background HEADER_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
	private static final Background BODY_BACKGROUND = new Background(new BackgroundFill(Color.WHITE, null, null));
	private static final Background TRANSPARENT_BACKGROUND = new Background(new BackgroundFill(Color.TRANSPARENT, null,
			null));
	private static final Paint LINE_PAINT = new Color(Color.LIGHTGRAY.getRed(), Color.LIGHTGRAY.getGreen(),
			Color.LIGHTGRAY.getBlue(), 0.5);
	private static final Background LINE_BACKGROUND = new Background(new BackgroundFill(LINE_PAINT, null, null));

	private HBox createLine(int branch) {
		final HBox hBox = new HBox();
		final Pane pane = createTracePane(branch, hBox);
		pane.setFocusTraversable(true);
		return hBox;
	}

	private String computeStateLabel(int stateNumber) {
		if (stateNumber > 999) {
			return (stateNumber / 1000) + "k" + ((stateNumber % 1000) / 10);
		} else {
			return "" + stateNumber;
		}
	}

	private void fillStateLine(HBox line, List<StateWrapper> stateWrappers, int selectedState) {
		final Color currentColor = Color.CORAL;
		final Color otherColor = Color.SLATEBLUE;
		final int height = DIAMETER;
		final int width = DIAMETER;

		line.getChildren().clear();

		final int currentStateIndex = Math.max(0, currentState.intValue());

		int diff = stateWrappers.isEmpty() ? 0 : currentStateIndex - stateWrappers.get(0).stateIndex;

		if (diff > 0) {
			line.setTranslateX(-(UNIT * diff));
		}

		for (StateWrapper stateWrapper : stateWrappers) {
			final Rectangle rectangle;
			if (selectedState == stateWrapper.stateIndex) {
				rectangle = new Rectangle(width, height, currentColor);
			} else {
				rectangle = new Rectangle(width, height, otherColor);
			}
			rectangle.setArcHeight(height);
			rectangle.setArcWidth(width);
			rectangle.setUserData(stateWrapper.value);
			rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
				if (e.getClickCount() > 1 && e.getButton() == MouseButton.PRIMARY) {
					Object o = rectangle.getUserData();
					traceExplorer.jump((EObject) o);
				}
				if (e.getClickCount() == 1 && e.getButton() == MouseButton.SECONDARY) {
					lastClickedState = stateWrapper.stateIndex;
					displayMenu.execute();
				}
			});

			displayGridBinding = displayGridBinding.or(rectangle.hoverProperty());

			final Tooltip t = new Tooltip("" + stateWrapper.stateIndex);
			Tooltip.install(rectangle, t);
			Label text = new Label(computeStateLabel(stateWrapper.stateIndex));
			text.setTextOverrun(OverrunStyle.ELLIPSIS);
			text.setAlignment(Pos.CENTER);
			text.setMouseTransparent(true);
			text.setTextFill(Color.WHITE);
			text.setFont(stateNumbersFont);
			text.setMaxWidth(width);
			StackPane layout = new StackPane();
			StackPane.setMargin(rectangle, MARGIN_INSETS);
			layout.getChildren().addAll(rectangle, text);
			line.getChildren().add(layout);
		}
	}
	
	private void fillValueLine(HBox line, int idx, List<ValueWrapper> valueWrappers, int selectedState) {
		final Color currentColor = Color.DARKORANGE;
		final Color otherColor = Color.DARKBLUE;
		final int height = V_HEIGHT;

		line.getChildren().clear();

		int valueIndex = valueWrappers.isEmpty() ? 0 : valueWrappers.get(0).traceIndex;
		final int currentStateIndex = Math.max(0, currentState.intValue());
		int stateIndex = currentStateIndex;

		int diff = valueWrappers.isEmpty() ? 0 : currentStateIndex - valueWrappers.get(0).firstStateIndex;

		if (diff > 0) {
			line.setTranslateX(-(UNIT * diff));
		}

		for (ValueWrapper stateWrapper : valueWrappers) {
			if (stateWrapper.firstStateIndex > stateIndex) {
				// When the first visible value starts after the first state,
				// we fill the space with a transparent rectangle.
				int width = DIAMETER + UNIT * (stateWrapper.firstStateIndex - stateIndex - 1);
				final Rectangle rectangle = new Rectangle(width, height, Color.TRANSPARENT);
				line.getChildren().add(rectangle);
				HBox.setMargin(rectangle, MARGIN_INSETS);
			}

			final Rectangle rectangle;
			final int width = DIAMETER + UNIT * (stateWrapper.lastStateIndex - stateWrapper.firstStateIndex);
			if (selectedState >= stateWrapper.firstStateIndex && selectedState <= stateWrapper.lastStateIndex) {
				rectangle = new Rectangle(width, height, currentColor);
			} else {
				rectangle = new Rectangle(width, height, otherColor);
			}
			rectangle.setArcHeight(height);
			rectangle.setArcWidth(DIAMETER / 2);
			rectangle.setUserData(stateWrapper.value);
			rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
				if (e.getClickCount() > 1 && e.getButton() == MouseButton.PRIMARY) {
					Object o = rectangle.getUserData();
					traceExplorer.jump((EObject) o);
				}
				if (e.getClickCount() == 1 && e.getButton() == MouseButton.SECONDARY) {
					lastClickedState = stateWrapper.firstStateIndex;
					displayMenu.execute();
				}
			});

			displayGridBinding = displayGridBinding.or(rectangle.hoverProperty());

			final Tooltip t = new Tooltip(traceExplorer.getTextAt(idx, valueIndex));
			Tooltip.install(rectangle, t);
			line.getChildren().add(rectangle);
			HBox.setMargin(rectangle, MARGIN_INSETS);

			valueIndex++;
			stateIndex = stateWrapper.lastStateIndex + 1;
		}
	}

	private static final int CURRENT_FORWARD_STEP = 0;
	private static final int CURRENT_BACKWARD_STEP = 1;
	private static final int CURRENT_BIGSTEP = 2;
	
	private NumberExpression createSteps(StepWrapper stepWrapper, int depth, int currentStateStartIndex, int selectedStateIndex,
			List<Path> accumulator, Object[] stepTargets) {

		final boolean endedStep = stepWrapper.endingIndex != -1;
		
		final int stepStartingIndex = stepWrapper.startingIndex;

		final int startingIndex = stepStartingIndex - currentStateStartIndex;
		final int endingIndex = (endedStep ? stepWrapper.endingIndex : nbStates.intValue()) - currentStateStartIndex;
		final Path path = new Path();
		path.setStrokeWidth(2);

		final double x1 = startingIndex * UNIT + UNIT / 2;
		final double x4 = endingIndex * UNIT + UNIT / 2;
		final double x2 = x1 + UNIT / 4;
		final double x3 = x4 - UNIT / 4;
		final double baseLineY = DIAMETER / 2 + V_MARGIN;
		final MoveTo moveTo = new MoveTo(x1, baseLineY);
		final LineTo lineTo = new LineTo(x2, baseLineY);
		final HLineTo hLineTo = new HLineTo(x3);
		path.getElements().addAll(moveTo, lineTo, hLineTo);
		if (endedStep) {
			final LineTo lastLineTo = new LineTo(x4, baseLineY);
			path.getElements().add(lastLineTo);
		}

		accumulator.add(path);

		final List<Step> subSteps = stepWrapper.subSteps;
		NumberExpression yOffset = new SimpleDoubleProperty(0);
		if (subSteps != null && !subSteps.isEmpty()) {
			for (Step subStep : subSteps) {
				final StepWrapper subStepWrapper = traceExplorer.getStepWrapper(subStep);
				if (subStepWrapper.startingIndex != subStepWrapper.endingIndex) {
					yOffset = Bindings.max(
							yOffset,
							createSteps(subStepWrapper, depth + 1, currentStateStartIndex, selectedStateIndex, accumulator,
									stepTargets));
				}
			}
		}
		
		lineTo.yProperty().bind(yOffset.add(DIAMETER / 2 + V_MARGIN));

		final Step step = stepWrapper.step;
		
		if (stepTargets[CURRENT_FORWARD_STEP] == step) {
			path.setStroke(Color.DARKORANGE);
		} else if (stepTargets[CURRENT_BACKWARD_STEP] == step) {
			path.setStroke(Color.DARKGREEN);
		} else if (stepTargets[CURRENT_BIGSTEP] == step) {
			path.setStroke(Color.DARKRED);
		} else {
			path.setStroke(Color.DARKBLUE);
			if (!traceExplorer.getCallStack().contains(step)
					&& (stepStartingIndex > selectedStateIndex
							|| (stepStartingIndex == selectedStateIndex && endedStep))) {
				path.getStrokeDashArray().addAll(5., 5.);
				path.setStrokeLineCap(StrokeLineCap.ROUND);
			}
		}

		return lineTo.yProperty();
	}

	public void deepRefresh() {
		Platform.runLater(() -> {
			
			valuesLines.getChildren().clear();
			statesPane.getChildren().clear();
			displayGrid.unbind();
			
			if (traceExplorer == null) {
				return;
			}

			isInReplayMode.set(traceExplorer.isInReplayMode());

			final int currentStateStartIndex = Math.max(0, currentState.intValue());
			final int currentStateEndIndex = currentStateStartIndex + nbDisplayableStates.intValue();

			final int selectedStateIndex = traceExplorer.getCurrentStateIndex();

			displayGridBinding = new BooleanBinding() {
				@Override
				protected boolean computeValue() {
					return false;
				}
			};
			
			{
				final HBox hBox = createLine(0);
				fillStateLine(hBox,
						traceExplorer.getStatesWrappers(currentStateStartIndex - 1, currentStateEndIndex + 1),
						selectedStateIndex);
			}
			for (int i = 0; i < traceExplorer.getNumberOfTraces(); i++) {
				if (traceExplorer.getAt(i, 0) != null) {
					final HBox hBox = createLine(i+1);
					fillValueLine(hBox, i,
							traceExplorer.getValuesWrappers(i, currentStateStartIndex - 1, currentStateEndIndex + 1),
							selectedStateIndex);
				}
			}

			displayGrid.bind(displayGridBinding);

			// ---------------- Steps creation

			final List<? extends Step> rootSteps = traceExplorer.getStepsForStates(currentStateStartIndex - 1, currentStateEndIndex + 1);

			final List<Path> steps = new ArrayList<>();

			final Object[] stepTargets = new Object[3];
			
			Step tmp = traceExplorer.getCurrentForwardStep();
			if (tmp != null) {
				stepTargets[CURRENT_FORWARD_STEP] = tmp;
			}
			tmp = traceExplorer.getCurrentBackwardStep();
			if (tmp != null) {
				stepTargets[CURRENT_BACKWARD_STEP] = tmp;
			}
			tmp = traceExplorer.getCurrentBigStep();
			if (tmp != null) {
				stepTargets[CURRENT_BIGSTEP] = tmp;
			}

			for (Step rootStep : rootSteps) {
				final StepWrapper stepWrapper = traceExplorer.getStepWrapper(rootStep);
				if (stepWrapper.startingIndex != stepWrapper.endingIndex) {
					createSteps(stepWrapper, 0, currentStateStartIndex, selectedStateIndex, steps, stepTargets);
				}
			}

			statesPane.getChildren().addAll(0, steps);

			// ---------------- Adding grid and highlight rectangle

			if (statesGrid != null) {
				bodyPane.getChildren().remove(statesGrid);
			}
			if (highlightRectangle != null) {
				bodyPane.getChildren().remove(highlightRectangle);
			}

			statesGrid = new Path();
			final VLineTo vLineTo = new VLineTo();
			vLineTo.yProperty().bind(valuesLines.heightProperty());
			displayGrid.addListener((v, o, n) -> {
				if (n) {
					statesGrid.setStroke(Color.GRAY);
				} else {
					statesGrid.setStroke(Color.LIGHTGRAY);
				}
			});
			highlightRectangle = new Rectangle();
			for (int i = currentStateStartIndex; i <= currentStateEndIndex; i++) {
				if (i == selectedStateIndex) {
					highlightRectangle.setX(H_MARGIN + (i - currentStateStartIndex) * (2 * H_MARGIN + DIAMETER));
					highlightRectangle.setWidth(2 * H_MARGIN + DIAMETER);
					highlightRectangle.heightProperty().bind(valuesLines.heightProperty());
				}
				statesGrid.getElements().addAll(
						new MoveTo(H_MARGIN + (i - currentStateStartIndex) * (2 * H_MARGIN + DIAMETER), 0), vLineTo);
			}
			statesGrid.getStrokeDashArray().addAll(10., 10.);
			statesGrid.setStrokeWidth(1);
			statesGrid.setStroke(Color.LIGHTGRAY);
			statesGrid.setStrokeLineCap(StrokeLineCap.ROUND);
			bodyPane.getChildren().add(0, statesGrid);
			highlightRectangle.setFill(Color.LIGHTGRAY);
			bodyPane.getChildren().add(0, highlightRectangle);
		});
	}

	public void setScrollLock(boolean value) {
		scrollLock = value;
	}

	public Consumer<Integer> getJumpConsumer() {
		return (i) -> traceExplorer.jump(i);
	}

	public void setTraceExplorer(ITraceExplorer traceExplorer) {
		valueNames.clear();
		if (this.traceExplorer != null) {
			this.traceExplorer.removeListener(this);
		}
		this.traceExplorer = traceExplorer;
		if (this.traceExplorer != null) {
			this.traceExplorer.addListener(this);
		}
		update();
	}

	@Override
	public void update() {
		if (traceExplorer != null) {
			nbStates.set(traceExplorer.getStatesTraceLength());
			if (!scrollLock) {
				showState(traceExplorer.getCurrentStateIndex(), false);
			}
		} else {
			nbStates.set(0);
		}
		deepRefresh();
	}

	private Command displayMenu = null;

	public void setMenuDisplayer(Command displayMenu) {
		this.displayMenu = displayMenu;
	}

	private int lastClickedState = -1;

	public Supplier<Integer> getLastClickedStateSupplier() {
		return () -> lastClickedState;
	}
}
