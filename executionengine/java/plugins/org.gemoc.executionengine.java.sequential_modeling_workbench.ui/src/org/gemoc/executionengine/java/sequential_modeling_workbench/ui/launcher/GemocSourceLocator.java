package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.launcher;

import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.emf.ecore.EObject;
import org.gemoc.execution.engine.mse.engine_mse.LogicalStep;
import org.gemoc.execution.engine.mse.engine_mse.MSE;
import org.gemoc.execution.engine.mse.engine_mse.helper.LogicalStepHelper;

import fr.obeo.dsl.debug.ide.DSLSourceLocator;
import fr.obeo.dsl.debug.ide.adapter.DSLStackFrameAdapter;

public class GemocSourceLocator extends DSLSourceLocator {

	@Override
	public Object getSourceElement(IStackFrame stackFrame) {
		final Object res;
		if (stackFrame instanceof DSLStackFrameAdapter) {
			final DSLStackFrameAdapter eStackFrame = (DSLStackFrameAdapter) stackFrame;
			final EObject instruction = eStackFrame.getCurrentInstruction();
			if (instruction instanceof LogicalStep) {
				res = getFirstTarget((LogicalStep) instruction);
			} else if (instruction != null) {
				res = instruction;
			} else {
				res = eStackFrame.getContext();
			}
		} else {
			res = null;
		}
		return res;
	}

	private EObject getFirstTarget(LogicalStep step) {
		EObject res = null;

		for (MSE event : LogicalStepHelper.getMSEs(step)) 
		{
			res = event;
			break;
		}

		return res;
	}

}
