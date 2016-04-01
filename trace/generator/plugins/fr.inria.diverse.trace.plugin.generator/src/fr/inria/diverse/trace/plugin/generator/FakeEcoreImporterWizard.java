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
package fr.inria.diverse.trace.plugin.generator;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.importer.ecore.EcoreImporterPlugin;
import org.eclipse.emf.importer.ecore.ui.EcoreImporterWizard;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.swt.widgets.Composite;

public class FakeEcoreImporterWizard extends EcoreImporterWizard {

	FakeEcoreImporterWizard(Composite rootParent) {
		detailPage = new FakeModelImporterDetailPage(getModelImporter(),
				"EcoreModel");
		detailPage.setTitle(EcoreImporterPlugin.INSTANCE
				.getString("_UI_EcoreImport_title"));
		detailPage
				.setDescription(EcoreImporterPlugin.INSTANCE.getString(detailPage
						.showGenModel() ? "_UI_EcoreImportNewProject_description"
						: "_UI_EcoreImportFile_description"));
		addPage(detailPage);
		detailPage.fakeSetParent(rootParent);
 
		packagePage = new FakeModelImporterPackagePage(getModelImporter(),
				"EcorePackages");
		packagePage.setShowReferencedGenModels(true);
		addPage(packagePage);
		packagePage.createControl(rootParent);
	}

	private FakeModelImporterDetailPage detailPage;

	private FakeModelImporterPackagePage packagePage;

	public FakeModelImporterDetailPage getFakeDetailPage() {
		return detailPage;
	}

	public FakeModelImporterPackagePage getFakePackagePage() {
		return packagePage;
	}

	@Override
	protected void selectFile(IFile file) {
	}

	@Override
	protected void openEditor(IFile file) throws PartInitException {
	}

	@Override
	public IWizardContainer getContainer() {
		// TODO Auto-generated method stub
		return new IWizardContainer() {

			@Override
			public void run(boolean fork, boolean cancelable,
					IRunnableWithProgress runnable)
					throws InvocationTargetException, InterruptedException {
				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
				win.run(false, true, runnable);

			}

			@Override
			public void updateWindowTitle() {
				// TODO Auto-generated method stub

			}

			@Override
			public void updateTitleBar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void updateMessage() {
				// TODO Auto-generated method stub

			}

			@Override
			public void updateButtons() {
				// TODO Auto-generated method stub

			}

			@Override
			public void showPage(IWizardPage page) {
				// TODO Auto-generated method stub

			}

			@Override
			public Shell getShell() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IWizardPage getCurrentPage() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
