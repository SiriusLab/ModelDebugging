package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.CreateDSAWizardContextActionDSAK3;
import org.gemoc.xdsmlframework.ide.ui.Activator;
import org.gemoc.xdsmlframework.ui.utils.dialogs.SelectAnyMelangeLanguageDialog;

import fr.inria.diverse.melange.metamodel.melange.Language;

public class CreateDSAProjectHandler extends AbstractMelangeProjectHandler implements IHandler  {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get the optional selection and eventually project data to preset the wizard
		final IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

		// launch the wizard that will select the action and do the job
//		WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
//													 new CreateDSAWizard(new CreateDSAWizardContextAction(updatedGemocLanguageProject)));
//		wizardDialog.open();
		// FIXME we are supposed to know the melange language
		
		
		final IFile melangeFile = getMelangeFileFromSelection(event);
		final ResourceSet rs = new ResourceSetImpl();
		if(melangeFile != null){
			final URI uri = URI.createPlatformResourceURI(melangeFile.getFullPath().toString(), true);
			rs.getResource(uri, true);
		}
		else{
			
			FileFinderVisitor melangeProjectVisitor = new FileFinderVisitor(
					"melange");
			try {
				updatedGemocLanguageProject.accept(melangeProjectVisitor);
				for(IFile projectMelangeIFile: melangeProjectVisitor.getFiles()){
					// consider all melange files in the project, get them in the ResourceSet
					if(!(projectMelangeIFile.getFullPath().toString().contains("/bin/")|projectMelangeIFile.getFullPath().toString().contains("/target/"))){ // stupid check to remove some typical duplicates, a beter impl should look into java output
						
						final URI uri = URI.createPlatformResourceURI(projectMelangeIFile.getFullPath().toString(), true);
						rs.getResource(uri, true);
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}	
			
		final LabelProvider labelProvider = new LabelProvider(){								
			public String getText(Object element) {
				if(element instanceof Language){
					return ((Language)element).getName();
				} else	return super.getText(element);
			}
		};
		final SelectAnyMelangeLanguageDialog dialog = new SelectAnyMelangeLanguageDialog(rs, labelProvider);
		dialog.setTitle("Select Melange language");
		dialog.setMessage("Select Melange language that will be used to initialize the new DSA project ");
		final int res = dialog.open();
		if (res == WizardDialog.OK) {
			final Language lang = (Language)dialog.getFirstResult();
			CreateDSAWizardContextActionDSAK3 action = new CreateDSAWizardContextActionDSAK3(
					updatedGemocLanguageProject, null);
			action.createNewDSAProject(getFirstEcore(lang));
		}
			
		return null;
	}

	
}
