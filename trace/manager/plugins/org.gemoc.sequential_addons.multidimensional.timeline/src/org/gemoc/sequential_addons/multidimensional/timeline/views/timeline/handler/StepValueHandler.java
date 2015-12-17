package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.handler;

import java.util.function.Supplier;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.gemoc.sequential_addons.multidimensional.timeline.Activator;
import org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView;

public class StepValueHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final MultidimensionalTimeLineView timelineView = (MultidimensionalTimeLineView)HandlerUtil.getActivePart(event);
		timelineView.handleStepValue();
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		boolean result = false;
		Activator activator = Activator.getDefault();
		final Supplier<MultidimensionalTimeLineView> timelineViewSupplier = activator.getMultidimensionalTimeLineViewSupplier();
		if (timelineViewSupplier != null) {
			MultidimensionalTimeLineView timeLineView = timelineViewSupplier.get();
			result = timeLineView.canStepValue();
		}
		return result;
	}
}