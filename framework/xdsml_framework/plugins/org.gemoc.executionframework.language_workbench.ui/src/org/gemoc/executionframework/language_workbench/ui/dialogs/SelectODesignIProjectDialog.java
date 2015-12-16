package org.gemoc.executionframework.language_workbench.ui.dialogs;


import org.eclipse.swt.widgets.Shell;
import org.gemoc.commons.eclipse.ui.dialogs.SelectPluginIProjectWithFileExtensionDialog;

/**
 * Dialog that allow to select an IProject that can be used as a Sirius Viewpoint specification project
 * @author dvojtise
 *
 */
public class SelectODesignIProjectDialog extends SelectPluginIProjectWithFileExtensionDialog {

	public SelectODesignIProjectDialog(Shell parentShell) {
		super(parentShell, "odesign");
	}

}
