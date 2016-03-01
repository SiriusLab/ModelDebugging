package org.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;
import org.gemoc.commons.eclipse.ui.dialogs.SelectAnyIProjectDialog;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialTemplate;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;
import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class SelectDsaProposal implements IProposal{
	
	private IProject dsaProject;

	@Override
	public String getDisplayText() {
			return "-- Import existing DSA project --";
	}

	@Override
	public String getReplacementText() {
		SelectAnyIProjectDialog dialog = new SelectAnyIProjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		if (dialog.open() == Dialog.OK) {
			Object[] selections = dialog.getResult();
			if(selections != null 
				&& selections.length != 0
				&& selections[0] instanceof IProject 
			){
				dsaProject = (IProject) selections[0];
				Set<String> aspects = SequentialTemplate.getAspectClassesList(dsaProject);
				final StringBuilder insertion = new StringBuilder();
				for (String asp : aspects) {
					insertion.append("\twith " + asp + "\n");
				}
				insertion.replace(0, 1, "");//remove the first \t
				return insertion.toString();
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
		// TODO Auto-generated method stub
	}
}
