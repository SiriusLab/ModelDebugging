package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.gemoc.xdsmlframework.ide.ui.commands.AbstractGemocLanguageProjectHandler;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateAnimatorProjectWizardContextAction;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateAnimatorProjectWizardContextAction.CreateAnimatorProjectAction;

public class CreateAnimatorProjectHandler extends AbstractGemocLanguageProjectHandler implements
		IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get the optional selection and eventually project data to preset the wizard
		IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

//		// launch the wizard that will select the action and do the job
//		WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
//													 new CreateAnimatorProjectWizard(updatedGemocLanguageProject));
//		wizardDialog.open();
		
		// FIXME we are supposed to know which melange language must be used as parameters for the wizard
		CreateAnimatorProjectWizardContextAction action = new CreateAnimatorProjectWizardContextAction(
				updatedGemocLanguageProject, null);
		action.actionToExecute = CreateAnimatorProjectAction.CREATE_NEW_SIRIUS_PROJECT;
		action.execute();
		return null;
	}

}
