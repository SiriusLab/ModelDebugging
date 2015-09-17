package org.gemoc.executionengine.java.sequential_language_workbench.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.gemoc.commons.eclipse.ui.WizardFinder;
import org.gemoc.executionengine.java.sequential_language_workbench.ui.Activator;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionframework.ui.xdsml.activefile.ActiveFile;
import org.gemoc.executionframework.ui.xdsml.activefile.ActiveFileEcore;
import org.gemoc.executionframework.ui.xdsml.wizards.XDSMLProjectHelper;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;

import fr.inria.diverse.commons.eclipse.pde.wizards.pages.pde.TemplateListSelectionPage;
import fr.inria.diverse.k3.ui.wizards.NewK3ProjectWizard;
import fr.inria.diverse.k3.ui.wizards.pages.NewK3ProjectWizardFields.KindsOfProject;


public class CreateDSAWizardContextActionDSAK3 extends CreateDSAWizardContextBase {

	public CreateDSAWizardContextActionDSAK3(IProject gemocLanguageIProject) {
		super(gemocLanguageIProject);
	}
	
	public CreateDSAWizardContextActionDSAK3(IProject gemocLanguageIProject, SequentialLanguageDefinition rootModelElement) {
		super(gemocLanguageIProject, rootModelElement);
	}

//	@Override
	public void createNewDSAProject() {
		// launch DSA Kermeta New wizard		
		IWizardDescriptor descriptor = WizardFinder.findNewWizardDescriptor("fr.inria.diverse.k3.ui.wizards.WizardNewProjectK3Plugin");
		
		// Then if we have a wizard, open it.
			if(descriptor == null) Activator.error("failled to find wizard descriptor with id = fr.inria.diverse.k3.ui.wizards.WizardNewProjectK3Plugin", null);
			if (descriptor != null) {
			// add a listener to capture the creation of the resulting project
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(workspaceListener);
			try {
				IWizard wizard;
				wizard = descriptor.createWizard();
				// this wizard need some dedicated initialization
				NewK3ProjectWizard k3Wizard = (NewK3ProjectWizard)wizard;
				k3Wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection());
				
				ActiveFile activeFileEcore = new ActiveFileEcore(_gemocLanguageIProject);
				IFile ecoreFile = activeFileEcore.getActiveFile();
				
				k3Wizard.getContext().ecoreIFile = activeFileEcore.getActiveFile();
				
				WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				
				wd.create();

				k3Wizard.getPageProject().setProjectName(XDSMLProjectHelper.baseProjectName(_gemocLanguageIProject)+".k3dsa");
				k3Wizard.getPageProject().setProjectKind(KindsOfProject.PLUGIN);
				// set field as much as possible
				
				if (ecoreFile != null) {
					TemplateListSelectionPage templatePage = k3Wizard.getTemplateListSelectionPage(k3Wizard.getContext());
					templatePage.setUseTemplate(true);
					templatePage.setInitialTemplateId("fr.inria.diverse.k3.ui.templates.projectContent.UserEcoreBasicAspect");
					templatePage.selectTemplate("fr.inria.diverse.k3.ui.templates.projectContent.UserEcoreBasicAspect");
					//((NewK3ProjectWizard)wizard).getPageProject().setEcoreLoaded(ecoreFile);
					
				}
				wd.setTitle("New Kermeta 3 project");
				
				
				int res = wd.open();
				if(res == WizardDialog.OK){
					//((KermetaProjectNewWizard )wizard).performFinish();
					ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
					IProject createdProject = workspaceListener.getLastCreatedProject();
					// update the project configuration model
					if(createdProject != null){
						addDSAProjectToConf(createdProject.getName());
					}
					else{
						addDSAProjectToConf("");
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
			finally{
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
			}
		}
	}

}
