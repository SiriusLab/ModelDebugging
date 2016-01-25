package org.gemoc.execution.sequential.javaxdsml.ide.ui.commands

import org.gemoc.xdsmlframework.ide.ui.commands.AbstractGemocLanguageProjectHandler
import fr.inria.diverse.melange.metamodel.melange.Language
import fr.inria.diverse.melange.metamodel.melange.Import
import org.eclipse.core.resources.IFile
import org.eclipse.emf.common.util.URI
import org.eclipse.core.runtime.IPath
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path

abstract class AbstractMelangeProjectHandler extends AbstractGemocLanguageProjectHandler {
	
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
}