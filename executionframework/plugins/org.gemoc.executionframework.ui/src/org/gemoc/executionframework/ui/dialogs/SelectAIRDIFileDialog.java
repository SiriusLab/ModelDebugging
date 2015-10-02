package org.gemoc.executionframework.ui.dialogs;

import org.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.gemoc.commons.eclipse.ui.dialogs.SelectSpecificFileDialog;



public class SelectAIRDIFileDialog extends SelectSpecificFileDialog {

	@Override
	protected FileFinderVisitor instanciateFinder() {
		return new FileFinderVisitor("aird");
	}
	
}
