package fr.inria.diverse.trace.generic.view.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Trace;

public class TraceEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {

		EditPart part = null;

		if (model instanceof Trace) {
			part = new TraceEditPart();
		} else if (model instanceof ExecutionState) {
			part = new ExecutionStateEditPart();
		}

		if (part != null) {
			part.setModel(model);
		}

		return part;

	}

}