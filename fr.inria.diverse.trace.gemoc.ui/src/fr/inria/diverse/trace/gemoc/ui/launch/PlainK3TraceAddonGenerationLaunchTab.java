/*******************************************************************************
 * Copyright (c) 2014 Université de Rennes 1.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erwan Bousse - initial API and implementation
 ******************************************************************************/
package fr.inria.diverse.trace.gemoc.ui.launch;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationsMessages;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.StringVariableSelectionDialog;
import org.eclipse.jdt.internal.debug.ui.IJavaDebugHelpContextIds;
import org.eclipse.jdt.internal.debug.ui.launcher.AbstractJavaMainTab;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

@SuppressWarnings("restriction")
public class PlainK3TraceAddonGenerationLaunchTab extends AbstractJavaMainTab {

	// Local/shared UI widgets
	private Button fEcoreFolderBrowse;
	private Text fEcoreFolderText;
	private Button fVariables;
	private Button fWorkspaceBrowse;
	private Text fMetamodelNameText;
	private Text fPluginNameText;
	private Button fReplaceCheckButton;

	@Override
	public void createControl(Composite parent) {
		Composite comp = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_BOTH);
		((GridLayout) comp.getLayout()).verticalSpacing = 0;
		createEcoreFolderComponent(comp);
		createVerticalSpacer(comp, 1);
		createProjectEditor(comp);
		((Group) fProjText.getParent()).setText("Java project containing K3AL Xtend code defining the semantics");
		createVerticalSpacer(comp, 1);
		createMetamodelNameComponent(comp);
		createPluginNameComponent(comp);
		createReplaceCheckbox(comp);
		setControl(comp);
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(getControl(), IJavaDebugHelpContextIds.LAUNCH_CONFIGURATION_DIALOG_MAIN_TAB);
	}
	
	private void createReplaceCheckbox(Composite parent) {
		fReplaceCheckButton = SWTFactory.createCheckButton(parent, "Replace existing project with the same name (note: the whole content of the former project will be deleted)",
				null, false, 1);
		fReplaceCheckButton.addSelectionListener(getDefaultListener());
	}

	/**
	 * Modify listener that simply updates the owning launch configuration
	 * dialog.
	 */
	private ModifyListener fBasicModifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent evt) {
			scheduleUpdateJob();
		}
	};

	/**
	 * Constant representing the id of the {@link IDialogSettings} location for
	 * the {@link ContainerSelectionDialog} used on this tab
	 * 
	 * @since 3.6
	 */
	private final String WORKSPACE_SELECTION_DIALOG = IDebugUIConstants.PLUGIN_ID + ".WORKSPACE_SELECTION_DIALOG"; //$NON-NLS-1$

	/**
	 * Returns the {@link IDialogSettings} for the given id
	 * 
	 * @param id
	 *            the id of the dialog settings to get
	 * @return the {@link IDialogSettings} to pass into the
	 *         {@link ContainerSelectionDialog}
	 * @since 3.6
	 */
	IDialogSettings getDialogBoundsSettings(String id) {
		IDialogSettings settings = DebugUIPlugin.getDefault().getDialogSettings();
		IDialogSettings section = settings.getSection(id);
		if (section == null) {
			section = settings.addNewSection(id);
		}
		return section;
	}

	/**
	 * Creates the component set for the ecore folder
	 * 
	 * @param parent
	 *            the parent to add this component to
	 */
	private void createEcoreFolderComponent(Composite parent) {
		Group group = SWTFactory.createGroup(parent,
				"Folder with Melange-generated ecore models defining the execution metamodel", 5, 2,
				GridData.FILL_HORIZONTAL);
		Composite comp = SWTFactory.createComposite(group, 5, 5, GridData.FILL_BOTH);
		GridLayout ld = (GridLayout) comp.getLayout();
		ld.marginWidth = 1;
		ld.marginHeight = 1;
		GridData gd = new GridData(SWT.BEGINNING, SWT.NORMAL, true, false);
		gd.horizontalSpan = 5;

		fEcoreFolderText = SWTFactory.createSingleText(comp, 4);
		fEcoreFolderText.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = LaunchConfigurationsMessages.CommonTab_6;
			}
		});
		fEcoreFolderText.addModifyListener(fBasicModifyListener);

		Composite bcomp = SWTFactory.createComposite(comp, 3, 5, GridData.HORIZONTAL_ALIGN_END);
		ld = (GridLayout) bcomp.getLayout();
		ld.marginHeight = 1;
		ld.marginWidth = 0;
		fWorkspaceBrowse = createPushButton(bcomp, LaunchConfigurationsMessages.CommonTab_12, null);
		fWorkspaceBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(),
						new WorkbenchLabelProvider(), new WorkbenchContentProvider());
				dialog.setTitle(LaunchConfigurationsMessages.CommonTab_13);
				dialog.setMessage("Select a folder containing all the ecore models.");
				dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
				dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));
				dialog.setDialogBoundsSettings(getDialogBoundsSettings(WORKSPACE_SELECTION_DIALOG),
						Dialog.DIALOG_PERSISTSIZE);
				if (dialog.open() == IDialogConstants.OK_ID) {
					IResource resource = (IResource) dialog.getFirstResult();
					if (resource != null) {
						String arg = resource.getFullPath().toString();
						String fileLoc = VariablesPlugin.getDefault().getStringVariableManager()
								.generateVariableExpression("workspace_loc", arg); //$NON-NLS-1$
						fEcoreFolderText.setText(fileLoc);
					}
				}
			}
		});
		fEcoreFolderBrowse = createPushButton(bcomp, LaunchConfigurationsMessages.CommonTab_7, null);
		fEcoreFolderBrowse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String filePath = fEcoreFolderText.getText();
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				filePath = dialog.open();
				if (filePath != null) {
					fEcoreFolderText.setText(filePath);
				}
			}
		});
		fVariables = createPushButton(bcomp, LaunchConfigurationsMessages.CommonTab_9, null);
		fVariables.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				StringVariableSelectionDialog dialog = new StringVariableSelectionDialog(getShell());
				dialog.open();
				String variable = dialog.getVariableExpression();
				if (variable != null) {
					fEcoreFolderText.insert(variable);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

	/**
	 * Creates the component set for the ecore folder
	 * 
	 * @param parent
	 *            the parent to add this component to
	 */
	private void createMetamodelNameComponent(Composite parent) {
		Group group = SWTFactory.createGroup(parent, "Language name", 5, 2, GridData.FILL_HORIZONTAL);
		Composite comp = SWTFactory.createComposite(group, 5, 5, GridData.FILL_BOTH);
		GridLayout ld = (GridLayout) comp.getLayout();
		ld.marginWidth = 1;
		ld.marginHeight = 1;
		GridData gd = new GridData(SWT.BEGINNING, SWT.NORMAL, true, false);
		gd.horizontalSpan = 5;

		fMetamodelNameText = SWTFactory.createSingleText(comp, 4);
		fMetamodelNameText.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = LaunchConfigurationsMessages.CommonTab_6;
			}
		});
		fMetamodelNameText.addModifyListener(fBasicModifyListener);

	}

	/**
	 * Creates the component set for the plugin name/id
	 * 
	 * @param parent
	 *            the parent to add this component to
	 */
	private void createPluginNameComponent(Composite parent) {
		Group group = SWTFactory.createGroup(parent, "Plugin name", 5, 2, GridData.FILL_HORIZONTAL);
		Composite comp = SWTFactory.createComposite(group, 5, 5, GridData.FILL_BOTH);
		GridLayout ld = (GridLayout) comp.getLayout();
		ld.marginWidth = 1;
		ld.marginHeight = 1;
		GridData gd = new GridData(SWT.BEGINNING, SWT.NORMAL, true, false);
		gd.horizontalSpan = 5;

		fPluginNameText = SWTFactory.createSingleText(comp, 4);
		fPluginNameText.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = LaunchConfigurationsMessages.CommonTab_6;
			}
		});
		fPluginNameText.addModifyListener(fBasicModifyListener);

	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, fProjText.getText().trim());
		configuration
				.setAttribute(IJavaLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY, fEcoreFolderText.getText());
		configuration.setAttribute("mmName", fMetamodelNameText.getText());
		configuration.setAttribute("pluginName", fPluginNameText.getText());
		configuration.setAttribute("replace", fReplaceCheckButton.getSelection());
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		super.initializeFrom(configuration);
		try {
			
			String ecoreFolder = configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_WORKING_DIRECTORY,
					"");
			fEcoreFolderText.setText(ecoreFolder);
			String mmName = configuration.getAttribute("mmName", "");
			fMetamodelNameText.setText(mmName);
			String pluginName = configuration.getAttribute("pluginName", "");
			fPluginNameText.setText(pluginName);
			boolean replace = configuration.getAttribute("replace", false);
			fReplaceCheckButton.setSelection(replace);

		} catch (CoreException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		// TODO Stub de la méthode généré automatiquement
		return "Configuration";
	}

}
