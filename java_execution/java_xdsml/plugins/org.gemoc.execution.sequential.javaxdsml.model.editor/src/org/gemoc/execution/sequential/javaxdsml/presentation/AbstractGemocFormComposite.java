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
package org.gemoc.execution.sequential.javaxdsml.presentation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;

public abstract class AbstractGemocFormComposite extends Composite {


	protected LanguageDefinition rootModelElement;
	protected AdapterFactoryEditingDomain editingDomain;

	
	public AbstractGemocFormComposite(Composite parent, int style) {
		super(parent, style);
	}

	abstract public void initControl(AdapterFactoryEditingDomain editingDomain);
	abstract public void initControlFromWrappedObject();
	
	protected IFile getCurrentIFile() {
		String platformString;
		if(rootModelElement.eResource() != null){
			platformString = rootModelElement.eResource().getURI()
				.toPlatformString(true);
		} else {
			// maybe freshly created and the element doesn't have its eResource set
			// trying using the editingDomain
			platformString = editingDomain.getResourceSet().getResources().get(0).getURI().toPlatformString(true);
		}
		return ResourcesPlugin.getWorkspace().getRoot()
				.getFile(new Path(platformString));

	}
}
