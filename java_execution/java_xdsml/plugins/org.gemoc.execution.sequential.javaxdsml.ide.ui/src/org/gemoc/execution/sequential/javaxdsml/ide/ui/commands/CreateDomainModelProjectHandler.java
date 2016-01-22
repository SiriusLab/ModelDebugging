package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateDomainModelWizardContextAction;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateDomainModelWizardContextAction.CreateDomainModelAction;
//import org.eclipse.jface.dialogs.MessageDialog;

public class CreateDomainModelProjectHandler extends AbstractGemocLanguageProjectHandler implements
		IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get the optional selection and eventually project data to preset the wizard
		IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

		// launch the wizard that will select the action and do the job
		//WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
		//											 new CreateDomainModelWizard(updatedGemocLanguageProject));
		//wizardDialog.open();

		// FIXME if the selection is a melange file we should precise which language must be updated
		CreateDomainModelWizardContextAction action = new CreateDomainModelWizardContextAction(
				updatedGemocLanguageProject, null); 
		action.actionToExecute = CreateDomainModelAction.CREATE_NEW_EMF_PROJECT;
		action.execute();
		
		return null;
	}

}
