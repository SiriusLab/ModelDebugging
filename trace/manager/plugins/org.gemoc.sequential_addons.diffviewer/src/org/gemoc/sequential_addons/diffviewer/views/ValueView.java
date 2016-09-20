package org.gemoc.sequential_addons.diffviewer.views;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ValueView extends HBox {

	private static final int HEIGHT = 8;
	private static final int H_MARGIN = 8;
	private static final int DIAMETER = 24;
	
	public void setSegments(int... segmentParts) {
		if (segmentParts.length > 0) {
			int totalLength = (DIAMETER + H_MARGIN * 2) * segmentParts.length - H_MARGIN * 2;
			final List<Shape> rectangles = new ArrayList<>();
			if (segmentParts.length > 1) {
				for (int i = 1; i < segmentParts.length - 1; i++) {
					final Rectangle r = new Rectangle(DIAMETER + H_MARGIN * 2, HEIGHT,
							segmentParts[i] > 0 ? Color.BLUE : Color.LIGHTBLUE);
					rectangles.add(r);
				}
				final Rectangle firstR = new Rectangle(DIAMETER + H_MARGIN, HEIGHT);
				final Rectangle lastR = new Rectangle(totalLength - (DIAMETER + H_MARGIN), 0, DIAMETER + H_MARGIN, HEIGHT);
				final Rectangle r = new Rectangle(totalLength, HEIGHT);
				r.setArcHeight(HEIGHT);
				r.setArcWidth(12);
				final Shape s1 = Shape.intersect(firstR, r);
				final Shape s2 = Shape.intersect(lastR, r);
				s1.setFill(Color.BLUE);
				s2.setFill(segmentParts[segmentParts.length - 1] > 0 ? Color.BLUE : Color.LIGHTBLUE);
				rectangles.add(0, s1);
				rectangles.add(s2);
			} else {
				final Rectangle r = new Rectangle(DIAMETER, HEIGHT, Color.BLUE);
				r.setArcHeight(HEIGHT);
				r.setArcWidth(12);
				rectangles.add(r);
			}
			getChildren().addAll(rectangles);
		}
	}
	
	public ValueView(String description, int... segmentLengths) {
		setSegments(segmentLengths);
		final Tooltip tooltip = new Tooltip(description);
		Tooltip.install(this, tooltip);
	}
}
