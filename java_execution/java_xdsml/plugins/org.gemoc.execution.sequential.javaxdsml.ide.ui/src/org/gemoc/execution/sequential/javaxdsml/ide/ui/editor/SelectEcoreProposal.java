package org.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import java.util.function.Supplier;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.gemoc.commons.eclipse.ui.dialogs.SelectSpecificFileDialog;

import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class SelectEcoreProposal implements IProposal{

	class SelectEcoreIFileDialog extends SelectSpecificFileDialog {
		public FileFinderVisitor instanciateFinder() {
			return new FileFinderVisitor("ecore");
		}
	}

	@Override
	public String getDisplayText() {
		return "Select an existing Ecore file";
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
				String path = "/"+ecoreFile.getProject().getName() +"/"+ecoreFile.getProjectRelativePath();
				URI uri = URI.createPlatformResourceURI(path,true);
				String replacementText = "\""+uri.toString()+"\"";
				return replacementText;
			}
		}
		return "platform/resource/project/file.ecore";
	}
}
