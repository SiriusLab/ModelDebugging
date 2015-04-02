package fr.inria.diverse.trace.gemoc.ui.wizards;

import org.eclipse.core.resources.IProject; 

import org.eclipse.jdt.internal.debug.ui.launcher.AbstractJavaMainTab;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
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

public class PlainK3Wizard extends Wizard implements INewWizard {

	private WizardNewProjectCreationPage _askProjectNamePage;
	private PlainK3WizardPage plaink3page;
	
	private IProject createdProject = null; 
	
	

	public PlainK3Wizard() {
		super();
		this.setWindowTitle("Create Domain Model");
		_askProjectNamePage = new WizardNewProjectCreationPage("NewGemocLanguageProjectName");
		_askProjectNamePage.setTitle("Project");
		_askProjectNamePage.setDescription("Create a new Gemoc Language Project");
		addPage(_askProjectNamePage);
		plaink3page = new PlainK3WizardPage("Yay!");
		plaink3page.setDescription("Blarg!");
		plaink3page.setWizard(this);
		addPage(plaink3page);

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
		return true;
		//return getContainer().getCurrentPage() == _askLanguageNamePage && _askLanguageNamePage.isPageComplete();
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
//		if (page == _askProjectNamePage) {
//			_askLanguageNamePage.setLanguageName(_askProjectNamePage.getProjectName());
//		}
		return super.getNextPage(page);
	}
		
	@Override
	public boolean performFinish() {
		
		try {
			createdProject = _askProjectNamePage.getProjectHandle();
			//final String languageName = _askLanguageNamePage.getLanguageName();

			IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
			final IProjectDescription description = workspace.newProjectDescription(createdProject.getName());
			if (!_askProjectNamePage.getLocationPath().equals(workspace.getRoot().getLocation()))
				description.setLocation(_askProjectNamePage.getLocationPath());
			//description.setLocationURI(_askProjectNamePage.getLocationURI());
			
			IWorkspaceRunnable operation = new IWorkspaceRunnable() {
				 public void run(IProgressMonitor monitor) throws CoreException {
					 createdProject.create(description, monitor);
					 createdProject.open(monitor);
					 //addGemocLanguageProjectNature(createdProject, languageName);
					 createdProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					 createdProject.touch(new NullProgressMonitor()); // [FT] One touch to force eclipse to serialize the project properties that will update accordingly the gemoc actions in the menu.
					 //createdProject.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
				 }
			};
			ResourcesPlugin.getWorkspace().run(operation, null);
		} catch (CoreException exception) {
			//Activator.error(exception.getMessage(), exception);
			return false;
		}
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}


	public IProject getCreatedProject() {
		return createdProject;
	}
}
