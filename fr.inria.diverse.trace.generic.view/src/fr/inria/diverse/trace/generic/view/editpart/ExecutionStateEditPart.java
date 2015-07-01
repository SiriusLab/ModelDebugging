package fr.inria.diverse.trace.generic.view.editpart;

import java.util.Random;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.view.figure.ExecutionStateFigure;

public class ExecutionStateEditPart extends AbstractGraphicalEditPart {

	Random rand = new Random();
	
	@Override
	protected IFigure createFigure() {
		return new ExecutionStateFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refreshVisuals() {
		ExecutionStateFigure figure = (ExecutionStateFigure) getFigure();
		//ExecutionState model = (ExecutionState) getModel();
		TraceEditPart parent = (TraceEditPart) getParent();

		figure.getLabel().setText("BLAAAH");
		Rectangle layout = new Rectangle(rand.nextInt(300), rand.nextInt(300), 50, 50);
		parent.setLayoutConstraint(this, figure, layout);
	}

}
