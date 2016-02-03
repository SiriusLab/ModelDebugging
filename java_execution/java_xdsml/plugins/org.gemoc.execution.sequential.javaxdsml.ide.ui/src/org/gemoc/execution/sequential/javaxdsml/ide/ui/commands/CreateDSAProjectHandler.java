package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialTemplate;
import org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.CreateDSAWizardContextActionDSAK3;
import org.gemoc.xdsmlframework.ide.ui.commands.AbstractMelangeSelectHandler;
import org.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper;

import fr.inria.diverse.melange.metamodel.melange.Language;

public class CreateDSAProjectHandler extends AbstractMelangeSelectHandler implements IHandler  {

	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, Language language)
			throws ExecutionException {
		CreateDSAWizardContextActionDSAK3 action = new CreateDSAWizardContextActionDSAK3(
				updatedGemocLanguageProject, null);
		action.createNewDSAProject(MelangeXDSMLProjectHelper.getFirstEcore(language));
		
		if(action.getLastCreatedProject() != null){
			waitForAutoBuild();
			
			Set<String> aspects = SequentialTemplate.getAspectClassesList(action.getLastCreatedProject());
			
			updateMelange(event,language,aspects);
		}
		
		return null;
	}

	@Override
	public String getSelectionMessage() {
		return "Select Melange language that will be used to initialize the new DSA project";
	}

	protected void updateMelange(ExecutionEvent event, Language language, Set<String> aspects){
		try {
			XtextEditor editor = EditorUtils.getActiveXtextEditor();
			if (editor != null) { //Update the editor content
				
				IXtextDocument document = editor.getDocument();
				document.modify((XtextResource it) -> {
					EStructuralFeature operators = language.eClass().getEStructuralFeature("operators");
					List<INode> nodesOp = NodeModelUtils.findNodesForFeature(language, operators);
					int lastOffset = -1;
					for (INode node : nodesOp) {
						if (node.getEndOffset() > lastOffset) lastOffset = node.getEndOffset();
					}
					if (lastOffset != -1) {
						final StringBuilder insertion = new StringBuilder();
						for (String asp : aspects) {
							insertion.append("\twith " + asp + "\n");
						}
						document.replace(lastOffset, 0,	"\n\n" + insertion.toString());
					}
					return null; // no computed value
				});
			}
			else{ //Update the Melange file content
				
				//Load Melange file
				String melangeWSLocation = language.eResource().getURI().toPlatformString(true);
				URI uri = language.eResource().getURI();
				String melangeLocation =ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+melangeWSLocation;
				List<String> lines = Files.readAllLines(Paths.get(melangeLocation));
				StringBuffer newContent = new StringBuffer();
				lines.forEach(
						line -> newContent.append(line+"\n")
						);
				
				//insert after operators
				EStructuralFeature operators = language.eClass().getEStructuralFeature("operators");
				List<INode> nodesOp = NodeModelUtils.findNodesForFeature(language, operators);
				int lastOffset = -1;
				for(INode node : nodesOp){
					if(node.getEndOffset() > lastOffset) lastOffset = node.getEndOffset();
				}
				if(lastOffset != -1){
					
					StringBuilder insertion = new StringBuilder();
					aspects.stream().forEach(
							asp -> insertion.append("\twith "+ asp + "\n")
							);
					
					newContent.replace(lastOffset,lastOffset+1, "\n\n"+insertion.toString());
				}
				
				//Write new content
				Files.write(Paths.get(melangeLocation), newContent.toString().getBytes());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void waitForAutoBuild() {
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
