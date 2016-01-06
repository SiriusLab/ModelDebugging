package org.gemoc.execution.sequential.javaxdsml.ide.ui.dialogs;

import java.util.Arrays;

import org.eclipse.swt.widgets.Shell;
import org.gemoc.commons.eclipse.ui.dialogs.SelectPluginIProjectWithFileExtensionDialog;

public class SelectDSAIProjectDialog extends SelectPluginIProjectWithFileExtensionDialog {

	public SelectDSAIProjectDialog(Shell parentShell) {
		super(parentShell, Arrays.asList("kp","xtend","k3sle"));
	}

}
