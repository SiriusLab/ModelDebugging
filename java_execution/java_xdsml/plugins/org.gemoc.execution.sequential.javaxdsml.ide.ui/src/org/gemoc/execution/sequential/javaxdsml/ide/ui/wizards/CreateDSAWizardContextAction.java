package org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards;

import org.eclipse.core.resources.IProject;

public class CreateDSAWizardContextAction {

	public enum CreateDSAAction {CREATE_NEW_K2_DSA_PROJECT, CREATE_NEW_K3_DSA_PROJECT, SELECT_EXISTING_DSA_PROJECT};
	
	public CreateDSAAction actionToExecute = CreateDSAAction.CREATE_NEW_K3_DSA_PROJECT;
	
	protected IProject gemocLanguageIProject; 
	
	public CreateDSAWizardContextAction(IProject updatedGemocLanguageProject) {
		gemocLanguageIProject = updatedGemocLanguageProject;
	}

	public void execute() {
		switch (actionToExecute) {
		/*case CREATE_NEW_K2_DSA_PROJECT:
			new CreateDSAWizardContextActionDSAK2(gemocLanguageIProject).createNewDSAProject(); 
			break;*/
		case CREATE_NEW_K3_DSA_PROJECT:
			new CreateDSAWizardContextActionDSAK3(gemocLanguageIProject).createNewDSAProject();
			break;
		case SELECT_EXISTING_DSA_PROJECT:
			new CreateDSAWizardContextActionDSA(gemocLanguageIProject).selectExistingDSAProject();
			break;

		default:
			break;
		}
	}
}
