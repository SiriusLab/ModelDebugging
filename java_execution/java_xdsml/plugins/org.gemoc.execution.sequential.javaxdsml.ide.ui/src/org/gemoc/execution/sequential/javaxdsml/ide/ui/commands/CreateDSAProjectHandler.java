package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.CreateDSAWizardContextActionDSAK3;
import org.gemoc.xdsmlframework.ide.ui.commands.AbstractGemocLanguageProjectHandler;

public class CreateDSAProjectHandler extends AbstractGemocLanguageProjectHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get the optional selection and eventually project data to preset the wizard
		IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

		// launch the wizard that will select the action and do the job
//		WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
//													 new CreateDSAWizard(new CreateDSAWizardContextAction(updatedGemocLanguageProject)));
//		wizardDialog.open();
		// FIXME we are supposed to know the melange language
		CreateDSAWizardContextActionDSAK3 action = new CreateDSAWizardContextActionDSAK3(
				updatedGemocLanguageProject, null);
		action.createNewDSAProject();
		return null;
	}

}
