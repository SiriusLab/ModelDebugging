/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.executionframework.ui.xdsml.activefile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.gemoc.executionframework.ui.Activator;

public class ActiveFileEcore extends ActiveFile {

	public ActiveFileEcore(IProject gemocLanguageProject) {
		super(gemocLanguageProject);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public IFile getActiveFile() {
		IProject projectWithEcore = this.getProject(this.gemocLanguageProject);
		FileFinderVisitor ecoreFinder = new FileFinderVisitor("ecore");
		
		try {

			projectWithEcore.accept(ecoreFinder);
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}

		if(ecoreFinder.getFiles().size() > 0){
			return ecoreFinder.getFiles().get(0);
		} else {
			return null;
		}
	}
	
	

}
