package org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.builder.AddRemoveGemocSequentialLanguageNatureHandler;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.AbstractCreateNewGemocLanguageProject;

import fr.inria.diverse.melange.ui.wizards.NewMelangeProjectWizard;
import fr.inria.diverse.melange.ui.wizards.pages.NewMelangeProjectWizardPage;

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
}
