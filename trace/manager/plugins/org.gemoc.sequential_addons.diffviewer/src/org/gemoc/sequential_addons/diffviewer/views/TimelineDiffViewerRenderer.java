package org.gemoc.sequential_addons.diffviewer.views;

import java.util.List;

import org.gemoc.sequential_addons.diffviewer.logic.Diff;

import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StateWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TimelineDiffViewerRenderer extends Pane {

	private final HBox line1 = new HBox();
	private final HBox line2 = new HBox();
	
	private static final int H_MARGIN = 8;
	private static final int V_MARGIN = 2;
	private static final int DIAMETER = 24;
	private static final Insets MARGIN_INSETS = new Insets(V_MARGIN, H_MARGIN, V_MARGIN, H_MARGIN);
	private static final Background BACKGROUND = new Background(new BackgroundFill(Color.WHITE, null, null));

	final private Font stateNumbersFont = Font.font("Arial", FontWeight.BOLD, 9);
	
	public TimelineDiffViewerRenderer() {
		VBox vBox = new VBox();
		ScrollPane scrollPane = new ScrollPane(vBox);
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
		vBox.getChildren().add(line1);
		vBox.getChildren().add(line2);
		setBackground(BACKGROUND);
		scrollPane.setBackground(BACKGROUND);
		vBox.setBackground(BACKGROUND);
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	private String computeStateLabel(int stateNumber) {
		if (stateNumber > 999) {
			return (stateNumber / 1000) + "k" + ((stateNumber % 1000) / 10);
		} else {
			return "" + stateNumber;
		}
	}
	
	private void addState(StateWrapper wrapper, HBox line, Color color) {
		final Rectangle rectangle = new Rectangle(DIAMETER, DIAMETER, color);
		rectangle.setArcHeight(DIAMETER);
		rectangle.setArcWidth(DIAMETER);
		rectangle.setUserData(wrapper.state);
		Label text = new Label(computeStateLabel(wrapper.stateIndex));
		text.setTextOverrun(OverrunStyle.ELLIPSIS);
		text.setAlignment(Pos.CENTER);
		text.setMouseTransparent(true);
		text.setTextFill(Color.WHITE);
		text.setFont(stateNumbersFont);
		text.setMaxWidth(DIAMETER);
		final Tooltip tooltip = new Tooltip(wrapper.description);
		Tooltip.install(rectangle, tooltip);
		StackPane layout = new StackPane();
		StackPane.setMargin(rectangle, MARGIN_INSETS);
		layout.getChildren().addAll(rectangle, text);
		line.getChildren().add(layout);
	}
	
	private void addBlank(HBox line) {
		final Rectangle rectangle = new Rectangle(DIAMETER, DIAMETER, Color.TRANSPARENT);
		line.getChildren().add(rectangle);
		HBox.setMargin(rectangle, MARGIN_INSETS);
	}
	
	public void fillStateLines(List<StateWrapper> stateWrappers1,
			List<StateWrapper> stateWrappers2, List<Diff> diffs) {
		line1.getChildren().clear();
		line2.getChildren().clear();
		for (Diff diff : diffs) {
			int i = diff.idx1;
			int j = diff.idx2;
			switch (diff.kind) {
			case EQ:
				addState(stateWrappers1.get(i), line1, Color.SLATEBLUE);
				addState(stateWrappers2.get(j), line2, Color.SLATEBLUE);
				break;
			case SUBST:
				addState(stateWrappers1.get(i), line1, Color.TOMATO);
				addState(stateWrappers2.get(j), line2, Color.TOMATO);
				break;
			case DEL:
				addState(stateWrappers1.get(i), line1, Color.BROWN);
				addBlank(line2);
				break;
			case IN:
				addBlank(line1);
				addState(stateWrappers2.get(j), line2, Color.BROWN);
				break;
			}
		}
	}
}
