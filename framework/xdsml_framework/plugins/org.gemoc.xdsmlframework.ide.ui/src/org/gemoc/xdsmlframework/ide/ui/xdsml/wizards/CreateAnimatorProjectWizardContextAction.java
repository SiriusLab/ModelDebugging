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
package org.gemoc.xdsmlframework.ide.ui.xdsml.wizards;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.gemoc.commons.eclipse.ui.WizardFinder;
import org.gemoc.commons.eclipse.ui.dialogs.SelectAnyIProjectDialog;
import org.gemoc.executionframework.xdsml_base.AnimatorProject;
import org.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocDebugRepresentationWizard;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;
import org.gemoc.executionframework.xdsml_base.SiriusAnimatorProject;
import org.gemoc.executionframework.xdsml_base.impl.Xdsml_baseFactoryImpl;
import org.gemoc.xdsmlframework.ide.ui.Activator;
import org.gemoc.xdsmlframework.ui.utils.dialogs.SelectODesignIProjectDialog;

import fr.inria.diverse.melange.metamodel.melange.Language;

//import org.eclipse.emf.ecoretools.design.wizard.EcoreModelerWizard;

/**
 * This class is both a context for the wizard and a Command that will be
 * executed
 * 
 * @author dvojtise
 *
 */
public class CreateAnimatorProjectWizardContextAction {

	public enum CreateAnimatorProjectAction {
		CREATE_NEW_SIRIUS_PROJECT, SELECT_EXISTING_OD_PROJECT
	};

	public CreateAnimatorProjectAction actionToExecute = CreateAnimatorProjectAction.CREATE_NEW_SIRIUS_PROJECT;

	// one of these must be set, depending on it it will work on the file or
	// directly in the model
	protected IProject gemocLanguageIProject = null;
	protected LanguageDefinition gemocLanguageModel = null;
	protected Language melangeLanguage = null;

	public CreateAnimatorProjectWizardContextAction(
			IProject updatedGemocLanguageProject) {
		gemocLanguageIProject = updatedGemocLanguageProject;
	}
	
	public CreateAnimatorProjectWizardContextAction(
			IProject updatedGemocLanguageProject,
			Language language) {
		gemocLanguageIProject = updatedGemocLanguageProject;
		melangeLanguage = language;
	}

	public CreateAnimatorProjectWizardContextAction(
			IProject updatedGemocLanguageProject,
			LanguageDefinition rootModelElement) {
		gemocLanguageIProject = updatedGemocLanguageProject;
		gemocLanguageModel = rootModelElement;
	}

	public void execute() {
		switch (actionToExecute) {

		case CREATE_NEW_SIRIUS_PROJECT:
			createNewODProject();
			break;

		case SELECT_EXISTING_OD_PROJECT:
			selectExistingSiriusProject();
			break;
		default:
			break;
		}

	}

	protected void createNewODProject() {
		final IWizardDescriptor descriptor = WizardFinder
				.findNewWizardDescriptor(org.gemoc.xdsmlframework.extensions.sirius.Activator.PLUGIN_ID
						+ ".wizards.NewGemocDebugRepresentationWizard");
		// Then if we have a wizard, open it.
		if (descriptor != null) {
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					workspaceListener);
			try {
				IWorkbenchWizard wizard;
				wizard = descriptor.createWizard();
				if(melangeLanguage != null){
					((NewGemocDebugRepresentationWizard) wizard)
					.setInitialProjectName(gemocLanguageIProject.getName());
					((NewGemocDebugRepresentationWizard) wizard)
					.setInitialLanguageName(melangeLanguage.getName());
				}
				else{
					((NewGemocDebugRepresentationWizard) wizard)
					.setInitialProjectName(XDSMLProjectHelper
							.baseProjectName(gemocLanguageIProject));
					((NewGemocDebugRepresentationWizard) wizard)
					.setInitialLanguageName(XDSMLProjectHelper
							.getLanguageDefinition(gemocLanguageIProject)
							.getName());
				}
				IWorkbench workbench = PlatformUI.getWorkbench();
				wizard.init(workbench, null);
				WizardDialog wd = new WizardDialog(workbench
						.getActiveWorkbenchWindow().getShell(), wizard);
				wd.create();
				wd.setTitle(wizard.getWindowTitle());
				int res = wd.open();
				if (res == WizardDialog.OK) {
					ResourcesPlugin.getWorkspace()
							.removeResourceChangeListener(workspaceListener);
					IProject createdProject = workspaceListener
							.getLastCreatedProject();
					// update the project configuration model
					if (createdProject != null) {
						SiriusAnimatorProject animatorProject = Xdsml_baseFactoryImpl.eINSTANCE
								.createSiriusAnimatorProject();
						animatorProject
								.setProjectName(createdProject.getName());
						addOrUpdateProjectToConf(animatorProject);
					} else {
						Activator
								.error("not able to detect which project was created by wizard",
										null);
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			} finally {
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
						workspaceListener);
			}
		} else {
			Activator
					.error("wizard with id=org.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocDebugRepresentationWizard not found",
							null);
		}
	}

	protected void selectExistingSiriusProject() {
		// launch the appropriate wizard
		// TODO filter only projects related to the current domain model
		SelectAnyIProjectDialog dialog = new SelectODesignIProjectDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		int res = dialog.open();
		if (res == WizardDialog.OK) {
			// update the project model
			String projectName = ((IResource) dialog.getResult()[0]).getName();
			AnimatorProject animatorProject = Xdsml_baseFactoryImpl.eINSTANCE
					.createSiriusAnimatorProject();
			animatorProject.setProjectName(projectName);
			// TODO detection of the current extension
			addOrUpdateProjectToConf(animatorProject);

		}
	}

	/**
	 * if an animator of the same concrete kind exist, then the new one will
	 * replace it Ie. only one Sirius animator, one XtextEditor, etc
	 * 
	 * @param animatorProject
	 */
	protected void addOrUpdateProjectToConf(AnimatorProject animatorProject) {
		if (gemocLanguageIProject != null) {
			addOrUpdateProjectToConf(animatorProject, gemocLanguageIProject);
		}
		if (gemocLanguageModel != null) {
			addOrUpdateProjectToConf(animatorProject, gemocLanguageModel);
		}
	}

	protected void addOrUpdateProjectToConf(AnimatorProject animatorProject,
			IProject gemocProject) {
		IFile configFile = gemocProject.getFile(new Path(
				Activator.GEMOC_PROJECT_CONFIGURATION_FILE));
		if (configFile.exists()) {
			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = reg.getExtensionToFactoryMap();
			m.put(Activator.GEMOC_PROJECT_CONFIGURATION_FILE_EXTENSION,
					new XMIResourceFactoryImpl());

			// Obtain a new resource set
			ResourceSet resSet = new ResourceSetImpl();

			// get the resource
			Resource resource = resSet
					.getResource(URI.createURI(configFile.getLocationURI()
							.toString()), true);

			LanguageDefinition gemocLanguageWorkbenchConfiguration = (LanguageDefinition) resource
					.getContents().get(0);
			addOrUpdateProjectToConf(animatorProject,
					gemocLanguageWorkbenchConfiguration);

			try {
				resource.save(null);
			} catch (IOException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		try {
			configFile.refreshLocal(IResource.DEPTH_ZERO,
					new NullProgressMonitor());
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
	}

	/**
	 * if an animator of the same concrete kind exist, then the new one will
	 * replace it Ie. only one Sirius animator, one XtextEditor, etc
	 * 
	 * @param animatorProject
	 */
	protected void addOrUpdateProjectToConf(AnimatorProject animatorProject,
			LanguageDefinition language) {

		// add missing data to conf
		AnimatorProject existingEditor = null;
		String searchedClass = animatorProject.getClass().getName();
		// search first existing animator
		for (AnimatorProject possibleExistingEditor : language
				.getAnimatorProjects()) {
			if (possibleExistingEditor.getClass().getName()
					.equals(searchedClass)) {
				existingEditor = possibleExistingEditor;
				break;
			}
		}
		if (existingEditor == null) {
			// simply add the new animator
			language.getAnimatorProjects().add(animatorProject);
		} else {
			// replace the existing animator
			int index = language.getAnimatorProjects().indexOf(existingEditor);
			language.getAnimatorProjects().set(index, animatorProject);
		}
	}

}
