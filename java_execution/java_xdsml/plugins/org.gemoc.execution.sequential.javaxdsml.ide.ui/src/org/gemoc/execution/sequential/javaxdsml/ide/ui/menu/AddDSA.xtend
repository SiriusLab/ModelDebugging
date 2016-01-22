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

class AddDSA extends AbstractHandler {

	override Object execute(ExecutionEvent event) throws ExecutionException {
		addDSA(event)
		return null
	}
	
	def Language addDSA(ExecutionEvent event){
		val aspects = #["asp1","asp.2","asp.asp.3","asp.sap.asp.4"] //FIXME: only to test
		
		val editor = EditorUtils.getActiveXtextEditor(event)
		if (editor != null) {
			val selection = editor.selectionProvider.selection as ITextSelection
			
			editor.document.modify[
				val lang = getSelectedLanguage(it,selection.offset)
				if(lang !== null){
					addDSA(editor.document,lang,aspects)
					return null
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
}