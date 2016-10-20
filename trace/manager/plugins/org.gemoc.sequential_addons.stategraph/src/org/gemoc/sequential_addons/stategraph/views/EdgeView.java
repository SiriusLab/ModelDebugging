package org.gemoc.sequential_addons.stategraph.views;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

public abstract class EdgeView extends Group {

	protected final DoubleBinding a;
	
	public EdgeView(final DoubleProperty sX, final DoubleProperty sY, final DoubleProperty eX,
			final DoubleProperty eY) {
		a = new DoubleBinding() {
			private final DoubleBinding dX = sX.subtract(eX);
			private final DoubleBinding dY = sY.subtract(eY);

			{
				super.bind(dX, dY);
			}

			@Override
			protected double computeValue() {
				return Math.atan2(dY.get(), dX.get());
			}
		};
	}
}
