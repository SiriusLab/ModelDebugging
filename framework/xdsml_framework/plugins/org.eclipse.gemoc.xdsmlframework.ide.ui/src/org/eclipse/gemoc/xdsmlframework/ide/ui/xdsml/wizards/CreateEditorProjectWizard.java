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
package org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;


public class CreateEditorProjectWizard extends Wizard {

	
	protected CreateEditorProjectWizardContextAction context;
	

	public CreateEditorProjectWizard(IProject updatedGemocLanguageProject) {
		super();
		this.setWindowTitle("Create Editor");
		context = new CreateEditorProjectWizardContextAction(updatedGemocLanguageProject);
		addPage(new CreateEditorProjectWizardPage("Create Editor", context));
	}

	@Override
	public void addPages() {
		
		super.addPages();
	}

	@Override
	public boolean performFinish() {
		// do the selected actions now ...
		context.execute();
		return true;
	}

}
