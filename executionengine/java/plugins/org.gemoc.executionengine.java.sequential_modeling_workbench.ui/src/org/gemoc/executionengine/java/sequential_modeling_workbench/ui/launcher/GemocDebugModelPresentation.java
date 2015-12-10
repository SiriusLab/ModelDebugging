package org.gemoc.executionengine.java.sequential_modeling_workbench.ui.launcher;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.gemoc.execution.engine.mse.engine_mse.LogicalStep;
import org.gemoc.execution.engine.mse.engine_mse.MSE;
import org.gemoc.execution.engine.mse.engine_mse.helper.LogicalStepHelper;
import org.gemoc.executionframework.ui.IMSEPresenter;

import fr.obeo.dsl.debug.ide.adapter.DSLStackFrameAdapter;
import fr.obeo.dsl.debug.ide.sirius.ui.DSLDebugModelPresentation;
import fr.obeo.dsl.debug.ide.sirius.ui.SiriusEditorUtils;

public class GemocDebugModelPresentation extends DSLDebugModelPresentation {

	@Override
	public IEditorInput getEditorInput(Object element) {
//		final IEditorInput res;
//
//		if (element instanceof ModelSpecificEvent
//				&& ((ModelSpecificEvent) element).getCaller() != null) {
//			res = super.getEditorInput(((ModelSpecificEvent) element).getCaller());
//		} else {
//			res = super.getEditorInput(element);
//		}
//
//		return res;
		return null;
	}

	@Override
	public String getEditorId(IEditorInput input, Object element) {
		final String res;

		if (element instanceof MSE
				&& ((MSE) element).getCaller() != null) {
			res = super.getEditorId(input, ((MSE) element).getCaller());
		} else {
			res = super.getEditorId(input, element);
		}


		return res;
	}

	@Override
	public boolean addAnnotations(IEditorPart editorPart, IStackFrame frame) {
		if (frame instanceof DSLStackFrameAdapter) {
			changeCurrentStackFrame(frame);
			if (editorPart instanceof DialectEditor) {
				EObject instruction = ((DSLStackFrameAdapter) frame)
						.getCurrentInstruction();
				if (instruction instanceof LogicalStep) {
					final List<MSE> tickedEvents = LogicalStepHelper.getMSEs((LogicalStep) instruction);
					showEvents(tickedEvents);
					final Set<EObject> callers = new LinkedHashSet<EObject>();
					for (MSE event : tickedEvents) {
						if (event.getCaller() != null) {
							callers.add(event.getCaller());
						}
					}
					SiriusEditorUtils.showInstructions((DialectEditor) editorPart, new ArrayList<EObject>(callers));
				} else {
//					SiriusEditorUtils.showInstruction((DialectEditor) editorPart, instruction);
				}
			} else {
				super.addAnnotations(editorPart, frame);
			}
		}

		return true;
	}

	private void showEvents(List<MSE> events) {
		final List<URI> uris = new ArrayList<URI>();
		for (MSE event : events) {
			uris.add(EcoreUtil.getURI(event));
		}
		for (IMSEPresenter presenter : org.gemoc.executionframework.ui.Activator
				.getDefault().getEventPresenters()) {
			presenter.present(uris);
		}
	}
	
	

}
