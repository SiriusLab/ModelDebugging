package org.gemoc.executionframework.ui.wizards.xdsml;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;


public class CreateDomainModelWizard extends Wizard {

	
	protected CreateDomainModelWizardContextAction context;
	

	public CreateDomainModelWizard(IProject updatedGemocLanguageProject) {
		super();
		this.setWindowTitle("Create Domain Model");
		context = new CreateDomainModelWizardContextAction(updatedGemocLanguageProject);
		addPage(new CreateDomainModelWizardPage("Create Domain Model", context));
	}

	@Override
	public void addPages() {
		
		super.addPages();
	}

	@Override
	public boolean performFinish() {
		// do the selected actions now ...
		context.execute();
		return true;
	}

}
