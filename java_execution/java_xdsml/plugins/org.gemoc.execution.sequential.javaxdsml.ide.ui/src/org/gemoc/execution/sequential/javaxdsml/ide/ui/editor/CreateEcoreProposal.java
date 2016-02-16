package org.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecoretools.design.wizard.EcoreModelerWizard;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.gemoc.commons.eclipse.ui.WizardFinder;
import org.gemoc.xdsmlframework.ide.ui.Activator;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.XDSMLProjectHelper;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;
import fr.inria.diverse.melange.metamodel.melange.Language;
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace;
import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class CreateEcoreProposal implements IProposal{

	private IProject ecoreProject;
	private String packageName = "packageName";
	private String languageName = "languageName";
	
	@Override
	public String getDisplayText() {
		return "-- Create a Domain Model Project --";
	}

	@Override
	public String getReplacementText() {
		
		IWizardDescriptor descriptor = WizardFinder.findNewWizardDescriptor("org.eclipse.ecoretools.emf.design.wizardID");

		// Then if we have a wizard, open it.
		if (descriptor != null) {
			// add a listener to capture the creation of the resulting project
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					workspaceListener);
			try {
				IWizard wizard = descriptor.createWizard();
				// this wizard need some dedicated initialization
				((EcoreModelerWizard) wizard).setInitialProjectName(packageName+"."+languageName+".model");
				((EcoreModelerWizard) wizard).init(PlatformUI.getWorkbench(),null);
				WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), wizard);
				wd.create();
				wd.setTitle(wizard.getWindowTitle());

				int res = wd.open();
				if (res == WizardDialog.OK) {
					ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
					ecoreProject = workspaceListener.getLastCreatedProject();
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			} finally {
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
						workspaceListener);
				if(ecoreProject != null){
					return getCreatedEcoreUri();
				}
			}
		}
		
		return "\tsyntax \"platform/resource/project/file.ecore\""; 
	}

	@Override
	public void configureProject(IProject project) {
		ManifestChanger manifestChanger = new ManifestChanger(project);
		try {
			manifestChanger.addPluginDependency(ecoreProject.getName());
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getCreatedEcoreUri(){
		FileFinderVisitor ecoreProjectVisitor = new FileFinderVisitor(
				"ecore");
		try {
			ecoreProject.accept(ecoreProjectVisitor);
			IFile ecoreIFile = ecoreProjectVisitor.getFile();
			if (ecoreIFile != null) {
				return "\tsyntax \"platform:/resource"+ecoreIFile.getFullPath().toString()+"\"";
			}
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
		return "";
	}

	@Override
	public void configureProposal(EObject context) {
		if(context instanceof Language){
			Language lang = (Language) context;
			this.packageName = ((ModelTypingSpace)lang.eContainer()).getName();
			this.languageName = lang.getName();
		}
	}
}
