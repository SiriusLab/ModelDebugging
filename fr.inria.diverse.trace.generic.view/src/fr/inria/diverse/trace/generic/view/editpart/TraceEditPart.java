package fr.inria.diverse.trace.generic.view.editpart;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Trace;

public class TraceEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		FreeformLayer layer = new FreeformLayer();
		layer.setLayoutManager(new FreeformLayout());
		layer.setBorder(new LineBorder(1));
		return layer;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<ExecutionState> getModelChildren() {
		List<ExecutionState> retVal = new ArrayList<ExecutionState>();
		Trace t = (Trace) getModel();
		retVal.addAll(t.getStates());
		return retVal;
	}

}
