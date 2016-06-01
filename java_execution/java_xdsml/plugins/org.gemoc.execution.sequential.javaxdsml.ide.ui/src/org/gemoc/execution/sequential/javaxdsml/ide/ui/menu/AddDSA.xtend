package org.gemoc.execution.sequential.javaxdsml.ide.ui.menu

import fr.inria.diverse.melange.metamodel.melange.Language
import java.util.List
import java.util.Set
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.ui.editor.utils.EditorUtils
import org.gemoc.execution.sequential.javaxdsml.ide.ui.commands.CreateDSAProjectHandler

class AddDSA extends CreateDSAProjectHandler {
	
	override protected updateMelange(ExecutionEvent event, Language language, Set<String> aspects) {
		val editor = EditorUtils.getActiveXtextEditor(event)
		if (editor != null) {
			val document = editor.document
			document.modify[
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
					return null // no computed value
				}
			]
		}
	}
}