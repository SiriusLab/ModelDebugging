package org.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.gemoc.commons.eclipse.ui.dialogs.SelectSpecificFileDialog;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;
import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class SelectEcoreProposal implements IProposal{

	private IProject ecoreProject;
	
	class SelectEcoreIFileDialog extends SelectSpecificFileDialog {
		public FileFinderVisitor instanciateFinder() {
			return new FileFinderVisitor("ecore");
		}
	}

	@Override
	public String getDisplayText() {
		return "-- Select an existing Ecore file --";
	}

	@Override
	public String getReplacementText() {
		SelectEcoreIFileDialog dialog = new SelectEcoreIFileDialog();
		if (dialog.open() == Dialog.OK) {
			Object[] selections = dialog.getResult();
			if(selections != null 
				&& selections.length != 0
				&& selections[0] instanceof IResource 
			){
				IResource ecoreFile = (IResource) selections[0];
				ecoreProject = ecoreFile.getProject();
				String path = "/"+ecoreFile.getProject().getName() +"/"+ecoreFile.getProjectRelativePath();
				URI uri = URI.createPlatformResourceURI(path,true);
				String replacementText = "\""+uri.toString()+"\"";
				return replacementText;
			}
		}
		return "";
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

	@Override
	public void configureProposal(EObject context) {
		// TODO Auto-generated method stub
	}
}
