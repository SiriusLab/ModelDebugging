package org.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.gemoc.commons.eclipse.ui.WizardFinder;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialTemplate;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;
import fr.inria.diverse.commons.eclipse.pde.wizards.pages.pde.TemplateListSelectionPage;
import fr.inria.diverse.k3.ui.wizards.NewK3ProjectWizard;
import fr.inria.diverse.k3.ui.wizards.pages.NewK3ProjectWizardFields.KindsOfProject;
import fr.inria.diverse.melange.metamodel.melange.Language;
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace;
import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class CreateDSAProposal implements IProposal{

	private IProject dsaProject;
	private String packageName = "packageName";
	private String languageName = "languageName";
	private IFile ecoreFile;
	
	@Override
	public String getDisplayText() {
		return "-- Create a DSA Project --";
	}

	@Override
	public String getReplacementText() {
		
		IWizardDescriptor descriptor = WizardFinder.findNewWizardDescriptor("fr.inria.diverse.k3.ui.wizards.WizardNewProjectK3Plugin");
		
		if (descriptor != null) {
			// add a listener to capture the creation of the resulting project
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(workspaceListener);
			try {
				IWizard wizard;
				wizard = descriptor.createWizard();
				// this wizard need some dedicated initialization
				NewK3ProjectWizard k3Wizard = (NewK3ProjectWizard)wizard;
				
				k3Wizard.getContext().ecoreIFile = ecoreFile;
				
				WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				
				wd.create();

				k3Wizard.getPageProject().setProjectName(packageName+"."+languageName+".k3dsa");
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
					dsaProject = workspaceListener.getLastCreatedProject();
					waitForAutoBuild();
					Set<String> aspects = SequentialTemplate.getAspectClassesList(dsaProject);
					final StringBuilder insertion = new StringBuilder();
					for (String asp : aspects) {
						insertion.append("\twith " + asp + "\n");
					}
					return insertion.toString();
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
			finally{
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
			}
		}
		return "";
	}

	@Override
	public void configureProject(IProject project) {
		ManifestChanger manifestChanger = new ManifestChanger(project);
		try {
			manifestChanger.addPluginDependency(dsaProject.getName());
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void configureProposal(EObject context) {
		if(context instanceof Language){
			Language lang = (Language) context;
			this.packageName = ((ModelTypingSpace)lang.eContainer()).getName();
			this.languageName = lang.getName();
			this.ecoreFile = MelangeXDSMLProjectHelper.getFirstEcore(lang);
		}
	}
	
	private void waitForAutoBuild() {
		boolean wasInterrupted = false;
		do {
			try {
				Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD,	null);
				wasInterrupted = false;
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}
}
