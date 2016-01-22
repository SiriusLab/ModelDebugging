package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction.CreateEditorProjectAction;

public class CreateEditorProjectHandler extends AbstractGemocLanguageProjectHandler implements
		IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get the optional selection and eventually project data to preset the wizard
		IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

//		// launch the wizard that will select the action and do the job
//		WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
//													 new CreateEditorProjectWizard(updatedGemocLanguageProject));
//		wizardDialog.open();
		
		CreateEditorProjectWizardContextAction action = new CreateEditorProjectWizardContextAction(
				updatedGemocLanguageProject, null);
		action.actionToExecute = CreateEditorProjectAction.CREATE_NEW_SIRIUS_PROJECT;
		action.execute();
		return null;
	}

}
