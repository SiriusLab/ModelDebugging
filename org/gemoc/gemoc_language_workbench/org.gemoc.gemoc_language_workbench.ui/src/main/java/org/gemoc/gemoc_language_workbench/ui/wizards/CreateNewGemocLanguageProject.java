package org.gemoc.gemoc_language_workbench.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.gemoc.gemoc_language_workbench.ui.Activator;
import org.gemoc.gemoc_language_workbench.ui.builder.ToggleNatureAction;
import org.gemoc.gemoc_language_workbench.ui.wizards.pages.AskLanguageNameWizardPage;

public class CreateNewGemocLanguageProject extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage _askProjectNamePage;
	private AskLanguageNameWizardPage _askLanguageNamePage;
	private ToggleNatureAction nature;
	
	public CreateNewGemocLanguageProject() {
		super();
		this.nature = new ToggleNatureAction();
		this.setWindowTitle("Create Domain Model");
		_askProjectNamePage = new WizardNewProjectCreationPage("NewGemocLanguageProjectName");
		_askProjectNamePage.setTitle("Project");
		_askProjectNamePage.setDescription("Create a new Gemoc Language Project");
		addPage(_askProjectNamePage);

		_askLanguageNamePage = new AskLanguageNameWizardPage("NewGemocLanguageName");
		_askLanguageNamePage.setTitle("Language");
		_askLanguageNamePage.setDescription("Specify the language name");
		addPage(_askLanguageNamePage);	
	}

	@Override
	public void addPages() {
		super.addPages();
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return true;
	}
	
	@Override
	public boolean canFinish() {
		return getContainer().getCurrentPage() == _askLanguageNamePage
				&& _askLanguageNamePage.isPageComplete();
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == _askProjectNamePage) {
			_askLanguageNamePage.setLanguageName(_askProjectNamePage.getProjectName());
		}
		return super.getNextPage(page);
	}
		
	@Override
	public boolean performFinish() {
		
		try {
			final IProject createdProject = _askProjectNamePage.getProjectHandle();
			final String languageName = _askLanguageNamePage.getLanguageName();
			IWorkspaceRunnable operation = new IWorkspaceRunnable() {
				 public void run(IProgressMonitor monitor) throws CoreException {
					 createdProject.create(monitor);
					 createdProject.open(monitor);
					 addGemocLanguageProjectNature(createdProject, languageName);
					 createdProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				 }
			};
			ResourcesPlugin.getWorkspace().run(operation, null);
		} catch (CoreException exception) {
			Activator.error(exception.getMessage(), exception);
			return false;
		}
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	private void addGemocLanguageProjectNature(IProject project, String languageName) {
		 this.nature.toggleNature(project, languageName);
	}
}
