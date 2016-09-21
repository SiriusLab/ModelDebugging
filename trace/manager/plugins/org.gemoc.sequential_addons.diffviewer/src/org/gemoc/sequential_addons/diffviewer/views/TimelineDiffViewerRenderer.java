package org.gemoc.sequential_addons.diffviewer.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.gemoc.sequential_addons.diffviewer.logic.Diff;
import org.gemoc.sequential_addons.diffviewer.logic.DiffComputer;

import fr.inria.diverse.trace.gemoc.api.ITraceExtractor;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StateWrapper;
import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.ValueWrapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

public class TimelineDiffViewerRenderer extends Pane {

	private static final int H_MARGIN = 8;
	private static final int V_MARGIN = 2;
	private static final int WIDTH = 24;
	private static final int UNIT = WIDTH + H_MARGIN * 2;
	
	private static final Insets HALF_MARGIN_INSETS = new Insets(V_MARGIN, H_MARGIN / 2, V_MARGIN, H_MARGIN / 2);
	private static final Insets MARGIN_INSETS = new Insets(V_MARGIN, H_MARGIN, V_MARGIN, H_MARGIN);
	
	private static final Paint LINE_PAINT = new Color(Color.LIGHTGRAY.getRed(), Color.LIGHTGRAY.getGreen(),
			Color.LIGHTGRAY.getBlue(), 0.5);
	
	private static final Background LINE_BACKGROUND = new Background(new BackgroundFill(LINE_PAINT, null, null));
	private static final Background HEADER_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
	private static final Background WHITE_BACKGROUND = new Background(new BackgroundFill(Color.WHITE, null, null));
	private static final Background TRANSPARENT_BACKGROUND = new Background(new BackgroundFill(Color.TRANSPARENT, null, null));

	private static final Font STATE_FONT = Font.font("Arial", FontWeight.BOLD, 9);
	private static final Font VALUE_FONT = Font.font("Arial", FontWeight.BOLD, 11);
	private static final Font GROUP_FONT = Font.font("Arial", FontWeight.BOLD, 12);
	
	private final VBox rootVBox = new VBox();
	private final HBox line1 = new HBox();
	private final HBox line2 = new HBox();
	private final VBox eqLines = new VBox();
	private final VBox eqBox = new VBox();
	private final VBox substLines = new VBox();
	private final VBox substBox = new VBox();
	private final VBox inLines = new VBox();
	private final VBox inBox = new VBox();
	private final VBox delLines = new VBox();
	private final VBox delBox = new VBox();

	private final IntegerProperty nbDisplayableStates;
	private final IntegerProperty statesRange;
	private final IntegerProperty nbStates;

	private int currentState = 0;
	
	final Map<HBox, List<List<Integer>>> lineToSegments = new HashMap<>();
	final Map<HBox, List<String>> segmentToDescription = new HashMap<>();

	private ITraceExtractor extractor1 = null;
	private ITraceExtractor extractor2 = null;
	
	private final List<List<EObject>> valueTraces1 = new ArrayList<>();
	private final List<List<EObject>> valueTraces2 = new ArrayList<>();

	private final List<StateWrapper> wrappers1 = new ArrayList<>();
	private final List<StateWrapper> wrappers2 = new ArrayList<>();
	
	private DiffComputer diffComputer;
	
	private final Map<EObject, ValueWrapper> valueToWrapper = new HashMap<>();
	
	public TimelineDiffViewerRenderer() {

		nbStates = new SimpleIntegerProperty();
		statesRange = new SimpleIntegerProperty();
		nbDisplayableStates = new SimpleIntegerProperty();
		nbDisplayableStates.bind(widthProperty().divide(UNIT));
		statesRange.bind(nbStates.subtract(nbDisplayableStates));
		
		nbDisplayableStates.addListener((v, o, n) -> {
			refresh();
		});
		
		setupBox(eqBox, "Toggle identical traces", eqLines);
		setupBox(substBox, "Toggle similar traces", substLines);
		setupBox(inBox, "Toggle inserted traces", inLines);
		setupBox(delBox, "Toggle deleted traces", delLines);
		
		ScrollPane scrollPane = new ScrollPane(rootVBox);
		scrollPane.minWidthProperty().bind(widthProperty());
		scrollPane.maxWidthProperty().bind(widthProperty());
		scrollPane.prefWidthProperty().bind(widthProperty());
		scrollPane.minHeightProperty().bind(heightProperty());
		scrollPane.maxHeightProperty().bind(heightProperty());
		scrollPane.prefHeightProperty().bind(heightProperty());
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setBorder(Border.EMPTY);
		getChildren().add(scrollPane);
		
		final ScrollBar scrollBar = new ScrollBar();
		scrollBar.setVisibleAmount(1);
		scrollBar.setBlockIncrement(10);
		scrollBar.setMin(0);
		scrollBar.disableProperty().bind(statesRange.lessThanOrEqualTo(0));
		scrollBar.maxProperty().bind(statesRange);
		scrollBar.valueProperty().addListener((v, o, n) -> {
			if (o.intValue() != n.intValue() && n.intValue() != currentState) {
				currentState = n.intValue();
				refresh();
			}
		});
		
		rootVBox.getChildren().add(scrollBar);
		rootVBox.getChildren().add(line1);
		rootVBox.getChildren().add(line2);
		line1.setBackground(HEADER_BACKGROUND);
		line2.setBackground(HEADER_BACKGROUND);
		setBackground(WHITE_BACKGROUND);
		scrollPane.setBackground(WHITE_BACKGROUND);
		rootVBox.setBackground(WHITE_BACKGROUND);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	private void setupBox(VBox box, String labelString, VBox content) {
		final HBox boxLabel = new HBox();
		final Polygon arrow = new Polygon(2.5, 10, 10, 5, 2.5, 0);
		final Label label = new Label(labelString);
		boxLabel.setBackground(HEADER_BACKGROUND);
		label.setFont(GROUP_FONT);
		HBox.setMargin(arrow, HALF_MARGIN_INSETS);
		boxLabel.setAlignment(Pos.CENTER_LEFT);
		boxLabel.getChildren().addAll(arrow, label);
		boxLabel.setCursor(Cursor.HAND);
		box.getChildren().add(boxLabel);
		boxLabel.setOnMouseClicked(e -> {
			if (box.getChildren().size() > 1) {
				box.getChildren().remove(content);
				arrow.setRotate(0);
			} else {
				box.getChildren().add(content);
				arrow.setRotate(90);
			}
		});
	}
	
	private String computeStateLabel(int stateNumber) {
		if (stateNumber > 999) {
			return (stateNumber / 1000) + "k" + ((stateNumber % 1000) / 10);
		} else {
			return "" + stateNumber;
		}
	}
	
	private void addState(StateWrapper wrapper, HBox line, Color color) {
		final Rectangle rectangle = new Rectangle(WIDTH, WIDTH, color);
		rectangle.setArcHeight(WIDTH);
		rectangle.setArcWidth(WIDTH);
		rectangle.setUserData(wrapper.state);
		Label text = new Label(computeStateLabel(wrapper.stateIndex));
		text.setTextOverrun(OverrunStyle.ELLIPSIS);
		text.setAlignment(Pos.CENTER);
		text.setMouseTransparent(true);
		text.setTextFill(Color.WHITE);
		text.setFont(STATE_FONT);
		text.setMaxWidth(WIDTH);
		final Tooltip tooltip = new Tooltip(wrapper.description);
		Tooltip.install(rectangle, tooltip);
		StackPane layout = new StackPane();
		StackPane.setMargin(rectangle, MARGIN_INSETS);
		layout.getChildren().addAll(rectangle, text);
		line.getChildren().add(layout);
	}
	
	private void addBlankState(HBox line) {
		final Rectangle rectangle = new Rectangle(WIDTH, WIDTH, Color.TRANSPARENT);
		HBox.setMargin(rectangle, MARGIN_INSETS);
		line.getChildren().add(rectangle);
	}
	
	private void addValue(EObject value, HBox line, String description, boolean newValue) {
		final List<List<Integer>> segments = lineToSegments.get(line);
		List<Integer> segment;
		boolean addDescription = false;
		if (segments.isEmpty()) {
			segment = new ArrayList<>();
			addDescription = true;
			segments.add(segment);
		} else {
			if (newValue) {
				segment = new ArrayList<>();
				addDescription = true;
				segments.add(segment);
			} else {
				segment = segments.get(segments.size() - 1);
				if (segment == null) {
					segment = new ArrayList<>();
					addDescription = true;
					segments.add(segment);
				}
			}
			
		}
		if (addDescription) {
			List<String> descriptions = segmentToDescription.get(line);
			if (descriptions == null) {
				descriptions = new ArrayList<>();
				segmentToDescription.put(line, descriptions);
			}
			descriptions.add(description);
		}
		segment.add(1);
	}
	
	private void addDelayedValue(HBox line, String description) {
		final List<List<Integer>> segments = lineToSegments.get(line);
		final List<Integer> segment;
		boolean addDescription = false;
		if (segments.isEmpty()) {
			segment = new ArrayList<>();
			addDescription = true;
			segments.add(segment);
		} else {
			segment = segments.get(segments.size() - 1);
		}
		if (addDescription) {
			List<String> descriptions = segmentToDescription.get(line);
			if (descriptions == null) {
				descriptions = new ArrayList<>();
				segmentToDescription.put(line, descriptions);
			}
			descriptions.add(description);
		}
		segment.add(-1);
	}
	
	private void addBlankValue(HBox line) {
		lineToSegments.get(line).add(null);
	}

	private List<EObject> normalizeValueTrace(List<ValueWrapper> trace) {
		final List<EObject> result = new ArrayList<>();
		trace.stream().forEach(w -> {
			while (result.size() < w.firstStateIndex) {
				result.add(null);
			}
			while (result.size() < w.lastStateIndex + 1) {
				result.add(w.value);
				valueToWrapper.putIfAbsent(w.value, w);
			}
		});
		return result;
	}
	
	public void loadTraces(final ITraceExtractor extractor1, final ITraceExtractor extractor2) {
		this.extractor1 = extractor1;
		this.extractor2 = extractor2;
		valueToWrapper.clear();
		valueTraces1.clear();
		valueTraces2.clear();
		wrappers1.clear();
		wrappers2.clear();
		
		wrappers1.addAll(extractor1.getStateWrappers(0, extractor1.getStatesTraceLength()-1));
		final List<EObject> states1 = wrappers1.stream().map(w -> w.state).collect(Collectors.toList());
		final List<List<ValueWrapper>> valueWrappers1 = new ArrayList<>();
		for (int i = 0; i < extractor1.getNumberOfTraces(); i++) {
			final List<ValueWrapper> valueWrappers = extractor1.getValueWrappers(i, 0, extractor1.getStatesTraceLength()-1);
			valueWrappers1.add(valueWrappers);
			final List<EObject> valueTrace = normalizeValueTrace(valueWrappers);
			while (valueTrace.size() < states1.size()) {
				valueTrace.add(null);
			}
			valueTraces1.add(valueTrace);
		}
		
		wrappers2.addAll(extractor2.getStateWrappers(0, extractor2.getStatesTraceLength()-1));
		final List<EObject> states2 = wrappers2.stream().map(w -> w.state).collect(Collectors.toList());
		final List<List<ValueWrapper>> valueWrappers2 = new ArrayList<>();
		for (int i = 0; i < extractor2.getNumberOfTraces(); i++) {
			final List<ValueWrapper> valueWrappers = extractor2.getValueWrappers(i, 0, extractor2.getStatesTraceLength()-1);
			valueWrappers2.add(valueWrappers);
			final List<EObject> valueTrace = normalizeValueTrace(valueWrappers);
			while (valueTrace.size() < states2.size()) {
				valueTrace.add(null);
			}
			valueTraces2.add(valueTrace);
		}
		
		diffComputer = new DiffComputer();
		diffComputer.loadTraces(valueTraces1, valueTraces2);
		nbStates.set(diffComputer.getDiffs().size());
		
		refresh();
	}
	
	private boolean isNewValue(int idx, List<EObject> list) {
		return idx == 0 || (idx < list.size() && idx > 0 && list.get(idx - 1) != list.get(idx));
	}
	
	private void fillGap(HBox line, List<EObject> trace, int idx, String description) {
		if (idx > 0 && idx < trace.size()) {
			if (trace.get(idx - 1) != null || (idx < trace.size() - 1 && trace.get(idx + 1) != null)) {
				addDelayedValue(line, description);
			} else {
				addBlankValue(line);
			}
		} else {
			addBlankValue(line);
		}
	}
	
	public void refresh() {
		if (diffComputer != null) {
		line1.getChildren().clear();
		line2.getChildren().clear();
		eqLines.getChildren().clear();
		substLines.getChildren().clear();
		inLines.getChildren().clear();
		delLines.getChildren().clear();
		lineToSegments.clear();
		while (rootVBox.getChildren().size() > 3) {
			rootVBox.getChildren().remove(3);
		}

		final List<Pair<List<EObject>, List<EObject>>> eqGroup = diffComputer.getEqGroup();
		final List<Pair<List<EObject>, List<EObject>>> substGroup = diffComputer.getSubstGroup();
		final List<List<EObject>> inGroup = diffComputer.getInGroup();
		final List<List<EObject>> delGroup = diffComputer.getDelGroup();

		if (!eqGroup.isEmpty()) {
			rootVBox.getChildren().add(eqBox);
		}
		
		if (!substGroup.isEmpty()) {
			rootVBox.getChildren().add(substBox);
		}
		
		if (!inGroup.isEmpty()) {
			rootVBox.getChildren().add(inBox);
		}
		
		if (!delGroup.isEmpty()) {
			rootVBox.getChildren().add(delBox);
		}
		
		final Map<List<EObject>, HBox> traceToLine = new HashMap<>();

		int c = 0;
		
		for (Pair<List<EObject>, List<EObject>> e : eqGroup) {
			final VBox pairBox = new VBox();
			final HBox trace1Box = new HBox();
			final HBox trace2Box = new HBox();
			traceToLine.put(e.getKey(), trace1Box);
			traceToLine.put(e.getValue(), trace2Box);
			lineToSegments.put(trace1Box, new ArrayList<>());
			lineToSegments.put(trace2Box, new ArrayList<>());
			Label l1 = new Label(extractor1.getValueLabel(valueTraces1.indexOf(e.getKey())));
			Label l2 = new Label(extractor2.getValueLabel(valueTraces2.indexOf(e.getValue())));
			VBox.setMargin(l1, HALF_MARGIN_INSETS);
			VBox.setMargin(l2, HALF_MARGIN_INSETS);
			l1.setFont(VALUE_FONT);
			l2.setFont(VALUE_FONT);
			pairBox.getChildren().addAll(l1, trace1Box, l2, trace2Box);
			eqLines.getChildren().add(pairBox);
			pairBox.setBackground(c % 2 == 0 ? LINE_BACKGROUND : WHITE_BACKGROUND);
			trace1Box.setBackground(TRANSPARENT_BACKGROUND);
			trace2Box.setBackground(TRANSPARENT_BACKGROUND);
			c++;
		}
		
		for (Pair<List<EObject>, List<EObject>> e : substGroup) {
			final VBox pairBox = new VBox();
			final HBox trace1Box = new HBox();
			final HBox trace2Box = new HBox();
			traceToLine.put(e.getKey(), trace1Box);
			traceToLine.put(e.getValue(), trace2Box);
			lineToSegments.put(trace1Box, new ArrayList<>());
			lineToSegments.put(trace2Box, new ArrayList<>());
			Label l1 = new Label(extractor1.getValueLabel(valueTraces1.indexOf(e.getKey())));
			Label l2 = new Label(extractor2.getValueLabel(valueTraces2.indexOf(e.getValue())));
			VBox.setMargin(l1, HALF_MARGIN_INSETS);
			VBox.setMargin(l2, HALF_MARGIN_INSETS);
			l1.setFont(VALUE_FONT);
			l2.setFont(VALUE_FONT);
			pairBox.getChildren().addAll(l1, trace1Box, l2, trace2Box);
			substLines.getChildren().add(pairBox);
			pairBox.setBackground(c % 2 == 0 ? LINE_BACKGROUND : WHITE_BACKGROUND);
			trace1Box.setBackground(TRANSPARENT_BACKGROUND);
			trace2Box.setBackground(TRANSPARENT_BACKGROUND);
			c++;
		}
		
		for (List<EObject> in : inGroup) {
			final VBox inVBox = new VBox();
			final HBox traceBox = new HBox();
			traceToLine.put(in, traceBox);
			lineToSegments.put(traceBox, new ArrayList<>());
			Label l = new Label(extractor2.getValueLabel(valueTraces2.indexOf(in)));
			VBox.setMargin(l, HALF_MARGIN_INSETS);
			l.setFont(VALUE_FONT);
			inVBox.getChildren().addAll(l, traceBox);
			inLines.getChildren().add(inVBox);
			traceBox.setBackground(c % 2 == 0 ? LINE_BACKGROUND : WHITE_BACKGROUND);
			c++;
		}
		
		for (List<EObject> del : delGroup) {
			final VBox delVBox = new VBox();
			final HBox traceBox = new HBox();
			traceToLine.put(del, traceBox);
			lineToSegments.put(traceBox, new ArrayList<>());
			Label l = new Label(extractor1.getValueLabel(valueTraces1.indexOf(del)));
			VBox.setMargin(l, HALF_MARGIN_INSETS);
			l.setFont(VALUE_FONT);
			delVBox.getChildren().addAll(l, traceBox);
			delLines.getChildren().add(delVBox);
			traceBox.setBackground(c % 2 == 0 ? LINE_BACKGROUND : WHITE_BACKGROUND);
			c++;
		}
		
		for (Diff diff : diffComputer.getDiffs().subList(currentState, currentState + nbDisplayableStates.intValue())) {
			int i = diff.idx1;
			int j = diff.idx2;
			switch (diff.kind) {
			case EQ:
				addState(wrappers1.get(i), line1, Color.SLATEBLUE);
				addState(wrappers2.get(j), line2, Color.SLATEBLUE);
				for (Pair<List<EObject>, List<EObject>> e : eqGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					addValue(t1.get(i), traceToLine.get(t1), d1, isNewValue(i, t1));
					addValue(t2.get(j), traceToLine.get(t2), d2, isNewValue(j, t2));
				}
				for (Pair<List<EObject>, List<EObject>> e : substGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					addValue(t1.get(i), traceToLine.get(t1), d1, isNewValue(i, t1));
					addValue(t2.get(j), traceToLine.get(t2), d2, isNewValue(j, t2));
				}
				for (List<EObject> del : delGroup) {
					String d = extractor1.getValueDescription(valueTraces1.indexOf(del), i);
					addValue(del.get(i), traceToLine.get(del), d, isNewValue(i, del));
				}
				for (List<EObject> in : inGroup) {
					String d = extractor2.getValueDescription(valueTraces2.indexOf(in), j);
					addValue(in.get(j), traceToLine.get(in), d, isNewValue(j, in));
				}
				break;
			case SUBST:
				addState(wrappers1.get(i), line1, Color.TOMATO);
				addState(wrappers2.get(j), line2, Color.TOMATO);
				for (Pair<List<EObject>, List<EObject>> e : eqGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					addValue(t1.get(i), traceToLine.get(t1), d1, isNewValue(i, t1));
					addValue(t2.get(j), traceToLine.get(t2), d2, isNewValue(j, t2));
				}
				for (Pair<List<EObject>, List<EObject>> e : substGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					addValue(t1.get(i), traceToLine.get(t1), d1, isNewValue(i, t1));
					addValue(t2.get(j), traceToLine.get(t2), d2, isNewValue(j, t2));
				}
				for (List<EObject> del : delGroup) {
					String d = extractor1.getValueDescription(valueTraces1.indexOf(del), i);
					addValue(del.get(i), traceToLine.get(del), d, isNewValue(i, del));
				}
				for (List<EObject> in : inGroup) {
					String d = extractor2.getValueDescription(valueTraces2.indexOf(in), j);
					addValue(in.get(j), traceToLine.get(in), d, isNewValue(j, in));
				}
				break;
			case DEL:
				addState(wrappers1.get(i), line1, Color.BROWN);
				addBlankState(line2);
				for (Pair<List<EObject>, List<EObject>> e : eqGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					addValue(t1.get(i), traceToLine.get(t1), d1, isNewValue(i, t1));
					fillGap(traceToLine.get(t2), t2, j, d2);
				}
				for (Pair<List<EObject>, List<EObject>> e : substGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					addValue(t1.get(i), traceToLine.get(t1), d1, isNewValue(i, t1));
					fillGap(traceToLine.get(t2), t2, j, d2);
				}
				for (List<EObject> del : delGroup) {
					String d = extractor1.getValueDescription(valueTraces1.indexOf(del), i);
					addValue(del.get(i), traceToLine.get(del), d, isNewValue(i, del));
				}
				for (List<EObject> in : inGroup) {
					String d = extractor2.getValueDescription(valueTraces2.indexOf(in), j);
					fillGap(traceToLine.get(in), in, j, d);
				}
				break;
			case IN:
				addBlankState(line1);
				addState(wrappers2.get(j), line2, Color.BROWN);
				for (Pair<List<EObject>, List<EObject>> e : eqGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					fillGap(traceToLine.get(t1), t1, i, d1);
					addValue(t2.get(j), traceToLine.get(t2), d2, isNewValue(j, t2));
				}
				for (Pair<List<EObject>, List<EObject>> e : substGroup) {
					final List<EObject> t1 = e.getKey();
					final List<EObject> t2 = e.getValue();
					String d1 = extractor1.getValueDescription(valueTraces1.indexOf(t1), i);
					String d2 = extractor2.getValueDescription(valueTraces2.indexOf(t2), j);
					fillGap(traceToLine.get(t1), t1, i, d1);
					addValue(t2.get(j), traceToLine.get(t2), d2, isNewValue(j, t2));
				}
				for (List<EObject> del : delGroup) {
					String d = extractor1.getValueDescription(valueTraces1.indexOf(del), i);
					fillGap(traceToLine.get(del), del, i, d);
				}
				for (List<EObject> in : inGroup) {
					String d = extractor2.getValueDescription(valueTraces2.indexOf(in), j);
					addValue(in.get(j), traceToLine.get(in), d, isNewValue(j, in));
				}
				break;
			}
		}
		processSegments();
		}
	}
	
	private void processSegments() {
		for (Map.Entry<HBox, List<List<Integer>>> e : lineToSegments.entrySet()) {
			final HBox line = e.getKey();
			final List<String> descriptions = segmentToDescription.get(line);
			final List<Node> children = line.getChildren();
			final List<List<Integer>> segments = e.getValue();
			int idx = 0;
			for (List<Integer> segment : segments) {
				if (segment == null) {
					final Rectangle rectangle = new Rectangle(WIDTH, 8, Color.TRANSPARENT);
					HBox.setMargin(rectangle, MARGIN_INSETS);
					children.add(rectangle);
				} else {
					int[] seg = new int[segment.size()];
					for (int i = 0; i < seg.length; i++) {
						seg[i] = segment.get(i);
					}
					final String description = descriptions.get(idx);
					final ValueView view = new ValueView(description, seg);
					HBox.setMargin(view, MARGIN_INSETS);
					children.add(view);
					idx++;
				}
			}
		}
	}
}
