package org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.internal.ui.elements.ElementList;
import org.eclipse.swt.widgets.Composite;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.builder.AddRemoveGemocSequentialLanguageNatureHandler;

import fr.inria.diverse.commons.eclipse.pde.wizards.pages.pde.TemplateListSelectionPage;
import fr.inria.diverse.commons.eclipse.pde.wizards.pages.pde.WizardElement;
import fr.inria.diverse.melange.ui.wizards.NewMelangeProjectWizard;
import fr.inria.diverse.melange.ui.wizards.pages.NewMelangeProjectWizardPage;

@SuppressWarnings("restriction")
public class CreateNewGemocSequentialLanguageProject extends NewMelangeProjectWizard {

	
	public CreateNewGemocSequentialLanguageProject() {
		super();	
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		NewMelangeProjectWizardPage firstPage = (NewMelangeProjectWizardPage) getPage("wizardPage"); 
		firstPage.setTitle("Project");
		firstPage.setDescription("Create a new Gemoc Sequential Language Project");
		firstPage.updateNameProject("org.company.my_sequential_language.xdsml");
	}

	@Override
	public void configureProject(IProject project, IProgressMonitor monitor) {
		super.configureProject(project, monitor);
		new AddRemoveGemocSequentialLanguageNatureHandler().configureNature(project);
	}
	
	/**
	 * Look for extension point="fr.inria.diverse.commons.eclipse.pde.projectContent"
	 * and filter wizards
	 */
	@Override
	public ElementList getAvailableCodegenWizards() {
		ElementList superRes = super.getAvailableCodegenWizards();
		ElementList newRes = new ElementList("CodegenWizards"); //$NON-NLS-1$
		
		for (Object element : superRes.getChildren()) {
			if(element instanceof WizardElement){
				WizardElement wizardElem = (WizardElement) element;
				if(wizardElem.getID().equals("fr.inria.diverse.melange.ui.templates.projectContent.Sequential")){
					newRes.add(wizardElem);
				}
			}
		}
		
		return newRes;
	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		TemplateListSelectionPage templatesPage = getTemplateListSelectionPage(context);
		templatesPage.setUseTemplate(true);
		templatesPage.selectTemplate("fr.inria.diverse.melange.ui.templates.projectContent.Sequential");
	}
}
