package org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.dialogs.SelectDSAIProjectDialog;

public class CreateDSAWizardContextActionDSA extends CreateDSAWizardContextBase {

	public CreateDSAWizardContextActionDSA(IProject gemocLanguageIProject) {
		super(gemocLanguageIProject);
	}

	public void createNewDSAProject() {
	}
	
	public void selectExistingDSAProject(){
		// launch the appropriate wizard
		SelectDSAIProjectDialog dialog = new SelectDSAIProjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		int res = dialog.open();
		if(res == WizardDialog.OK){
			// update the project model
			addDSAProjectToConf(((IResource)dialog.getResult()[0]).getName());
		}
	}	
	
}
