package org.gemoc.sequential_addons.diffviewer.views;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ValueView extends HBox {

	private static final int HEIGHT = 8;
	private static final int H_MARGIN = 8;
	private static final int WIDTH = 24;
	
	private Shape getHatching(int length, int offset) {
		final Path p = new Path();
		for (int i = 0; i < (length / 2.5) + 1; i++) {
			final double x1 = (i - 1) * 2.5 + offset;
			final double y1 = 8;
			final double x2 = (i + 1) * 2.5 + offset;
			final double y2 = 0;
			final MoveTo move = new MoveTo(x1, y1);
			final LineTo line = new LineTo(x2, y2);
			p.getElements().addAll(move, line);
			p.setStrokeWidth(1);
		}
		return p;
	}

	public void setSegments(int... segmentParts) {
		if (segmentParts.length > 0) {
			int totalLength = (WIDTH + H_MARGIN * 2) * segmentParts.length - H_MARGIN * 2;
			final List<Shape> rectangles = new ArrayList<>();
			final boolean firstSegmentIsGap = segmentParts[0] == -1;
			int j = 0;
			final List<Integer> segmentLengths = new ArrayList<>();
			segmentLengths.add(1);
			for (int i = 1; i < segmentParts.length; i++) {
				if (segmentParts[i] != segmentParts[i - 1]) {
					j++;
					segmentLengths.add(1);
				} else {
					segmentLengths.add(1 + segmentLengths.remove(j));
				}
			}

			if (segmentLengths.size() > 1) {
				final int segmentCount = segmentLengths.size();
				for (int i = 1; i < segmentCount - 1; i++) {
					final Shape s;
					final int length = (WIDTH + H_MARGIN * 2) * segmentLengths.get(i);
					if (i % 2 == 0 && !firstSegmentIsGap) {
						s = new Rectangle(length, HEIGHT, Color.BLUE);
					} else {
						final Rectangle r = new Rectangle(length, HEIGHT);
						s = Shape.subtract(r, getHatching(length, 0));
						s.setFill(Color.BLUE);
					}
					rectangles.add(s);
				}

				final Shape firstR;
				final int l1 = (WIDTH + H_MARGIN * 2) * segmentLengths.get(0) - H_MARGIN;
				if (!firstSegmentIsGap) {
					firstR = new Rectangle(l1, HEIGHT);
				} else {
					final Rectangle r = new Rectangle(l1, HEIGHT);
					firstR = Shape.subtract(r, getHatching(l1, 0));
				}

				final Shape lastR;
				final int l2 = (WIDTH + H_MARGIN * 2) * segmentLengths.get(segmentCount - 1) - H_MARGIN;
				if ((segmentCount - 1) % 2 == 0 && !firstSegmentIsGap) {
					lastR = new Rectangle(totalLength - l2, 0, l2, HEIGHT);
				} else {
					final Rectangle r = new Rectangle(totalLength - l2, 0, l2, HEIGHT);
					lastR = Shape.subtract(r, getHatching(l2, totalLength - l2));
				}
				
				final Rectangle r = new Rectangle(totalLength, HEIGHT);
				r.setArcHeight(HEIGHT);
				r.setArcWidth(12);
				final Shape s1 = Shape.intersect(firstR, r);
				final Shape s2 = Shape.intersect(lastR, r);
				s1.setFill(Color.BLUE);
				s2.setFill(Color.BLUE);
				rectangles.add(0, s1);
				rectangles.add(s2);
			} else {
				final int length = (WIDTH + H_MARGIN * 2) * segmentLengths.get(0) - H_MARGIN * 2;
				final Rectangle r = new Rectangle(length, HEIGHT, Color.BLUE);
				r.setArcHeight(HEIGHT);
				r.setArcWidth(12);
				rectangles.add(r);
			}
			getChildren().addAll(rectangles);
		}
	}

	public ValueView(String description, int... segmentParts) {
		setSegments(segmentParts);
		final Tooltip tooltip = new Tooltip(description);
		Tooltip.install(this, tooltip);
	}
}
