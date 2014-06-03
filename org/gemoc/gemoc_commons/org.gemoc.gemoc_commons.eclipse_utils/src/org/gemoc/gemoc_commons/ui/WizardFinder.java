package org.gemoc.gemoc_commons.ui;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

public class WizardFinder {

	static public IWizardDescriptor findNewWizardDescriptor(String wizardId)
	{
		return PlatformUI
				.getWorkbench()
				.getNewWizardRegistry()
				.findWizard(wizardId);
	}
	
}
