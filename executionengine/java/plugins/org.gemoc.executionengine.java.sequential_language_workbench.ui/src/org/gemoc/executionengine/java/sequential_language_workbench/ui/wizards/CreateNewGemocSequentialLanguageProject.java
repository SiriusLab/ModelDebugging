package org.gemoc.executionengine.java.sequential_language_workbench.ui.wizards;

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
import org.gemoc.executionengine.java.sequential_language_workbench.ui.builder.AddRemoveGemocSequentialLanguageNatureHandler;
import org.gemoc.executionframework.ui.xdsml.wizards.AbstractCreateNewGemocLanguageProject;

public class CreateNewGemocSequentialLanguageProject extends AbstractCreateNewGemocLanguageProject {

	
	public CreateNewGemocSequentialLanguageProject() {
		super();	
		_askProjectNamePage.setDescription("Create a new Gemoc Sequential Language Project");
		_askProjectNamePage.setInitialProjectName("org.company.my_sequential_language.xdsml");	
	}


	@Override
	protected void initializeProject(IProject project, String languageName) {
		new AddRemoveGemocSequentialLanguageNatureHandler().toggleNature(project, languageName);
		
	}

}
