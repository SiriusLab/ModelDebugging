package org.gemoc.executionframework.ui.xdsml.activefile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

public abstract class ActiveFile {

	IProject gemocLanguageProject;
	
	public ActiveFile(IProject gemocLanguageProject){
		this.gemocLanguageProject = gemocLanguageProject;
	}
	
	public IFile getActiveFile() {
		return null;
	}
	
	protected IProject getProject(IProject gemocLanguageIProject) {
		return null;
	}
}
