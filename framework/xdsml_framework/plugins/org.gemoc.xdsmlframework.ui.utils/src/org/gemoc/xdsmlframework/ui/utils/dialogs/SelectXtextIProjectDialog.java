package org.gemoc.xdsmlframework.ui.utils.dialogs;


import org.eclipse.swt.widgets.Shell;
import org.gemoc.commons.eclipse.ui.dialogs.SelectPluginIProjectWithFileExtensionDialog;

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
