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
package org.gemoc.xdsmlframework.ui.utils.dialogs;


import org.eclipse.swt.widgets.Shell;
import org.eclipse.gemoc.commons.eclipse.ui.dialogs.SelectPluginIProjectWithFileExtensionDialog;

/**
 * Dialog that allow to select an IProject that can be used as an Xtext project
 * @author dvojtise
 *
 */
public class SelectXtextIProjectDialog extends SelectPluginIProjectWithFileExtensionDialog {

	public SelectXtextIProjectDialog(Shell parentShell) {
		super(parentShell, "xtext");
	}

	

}
