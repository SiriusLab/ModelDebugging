package org.gemoc.execution.sequential.javaxdsml.ide.ui.menu

import fr.inria.diverse.melange.metamodel.melange.Language
import java.util.List
import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.jface.text.ITextSelection
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.resource.EObjectAtOffsetHelper
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.model.IXtextDocument
import org.eclipse.xtext.ui.editor.utils.EditorUtils
import org.eclipse.core.resources.IProject
import org.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.CreateDSAWizardContextActionDSAK3
import org.gemoc.execution.sequential.javaxdsml.ide.ui.commands.CreateDSAProjectHandler
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.IStructuredSelection
import fr.inria.diverse.melange.metamodel.melange.Import
import org.eclipse.emf.common.util.URI
import org.eclipse.ui.PlatformUI
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.Path
import org.eclipse.core.resources.ResourcesPlugin
import org.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialTemplate
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.core.runtime.OperationCanceledException

class AddDSA extends AbstractHandler {

	override Object execute(ExecutionEvent event) throws ExecutionException {
		addDSA(event)
		return null
	}
	
	def IProject createProject(ExecutionEvent event, IFile ecore){
		val editor = EditorUtils.getActiveXtextEditor(event)
		val IProject updatedGemocLanguageProject = editor.resource.project
		
		val CreateDSAWizardContextActionDSAK3 action = new CreateDSAWizardContextActionDSAK3(updatedGemocLanguageProject, null);
		action.createNewDSAProject(ecore);
		return action.lastCreatedProject
	}
	
	
	def Language addDSA(ExecutionEvent event){
//		val aspects = #["asp1","asp.2","asp.asp.3","asp.sap.asp.4"] //FIXME: only to test
		
		val editor = EditorUtils.getActiveXtextEditor(event)
		if (editor != null) {
			val selection = editor.selectionProvider.selection as ITextSelection
			
			editor.document.modify[
				val lang = getSelectedLanguage(it,selection.offset)
				if(lang !== null){
					
					val file = getFirstEcore(lang)
					val dsaProject = createProject(event,file)
					// FIXME: Need to wait the build complete
					waitForAutoBuild
					val aspects = SequentialTemplate.getAspectClassesList(dsaProject).toList
					
					addDSA(editor.document,lang,aspects)
					return lang
				}
			]
		}
		return null
	}
	
	def Language getSelectedLanguage(XtextResource resource, int offset){
		val EObjectAtOffsetHelper eObjectAtOffsetHelper =
			resource.resourceServiceProvider.get(typeof(EObjectAtOffsetHelper))
		val selectedElement = eObjectAtOffsetHelper.resolveContainedElementAt(resource, offset)
		if (selectedElement != null) {
			var currentElem = selectedElement
			while(currentElem !== null){
				if(currentElem instanceof Language){
					return currentElem
				}
				currentElem = currentElem.eContainer
			}
		}
		return null
	}
	
	def void addDSA(IXtextDocument document, Language language, List<String> aspects){
		//insert after operators
		val EStructuralFeature operators = language.eClass().getEStructuralFeature("operators");
		val List<INode> nodesOp = NodeModelUtils.findNodesForFeature(language, operators);
		var int lastOffset = -1
		for(node : nodesOp){
			if(node.endOffset > lastOffset) lastOffset = node.endOffset
		}
		if(lastOffset != -1){
			
			val StringBuilder insertion = new StringBuilder
			aspects.forEach[asp |
				insertion.append("\twith "+ asp + "\n")
			] 
			
			document.replace(lastOffset,0, "\n\n"+insertion.toString)
		}
	}
	
	def String getFirstEcorePath(Language language){
		val firstImport = language.operators.filter(Import).head
		if(firstImport !== null){
			return firstImport.ecoreUri
		}
		return null
	}
	
	def IFile getFirstEcore(Language lang){
		val String ecoreURI = getFirstEcorePath(lang)
		if(ecoreURI !== null){
			val URI uri = org.eclipse.emf.common.util.URI.createURI(ecoreURI)
			val filePath = uri.toPlatformString(true)
			val IPath path = new Path(filePath);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}
		return null
	}
	
	private def void waitForAutoBuild() {
		var wasInterrupted = false
		do {
			try {
				Job.jobManager.join(ResourcesPlugin::FAMILY_AUTO_BUILD,	null)
				wasInterrupted = false
			} catch (OperationCanceledException e) {
				e.printStackTrace
			} catch (InterruptedException e) {
				wasInterrupted = true
			}
		} while (wasInterrupted)
	}
}