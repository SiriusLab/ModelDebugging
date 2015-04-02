package fr.inria.diverse.trace.gemoc.ui.wizards;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchConfigurationsMessages;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.debug.ui.StringVariableSelectionDialog;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;
import org.eclipse.jdt.internal.debug.ui.actions.ControlAccessibleListener;
import org.eclipse.jdt.internal.debug.ui.launcher.AbstractJavaMainTab;
import org.eclipse.jdt.internal.debug.ui.launcher.LauncherMessages;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.internal.ide.dialogs.ResourceComparator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * Lots of code taken from
 * org.eclipse.jdt.internal.debug.ui.launcher.AbstractJavaMainTab, eg. to be
 * able to choose a java project easily. Maybe we choose filter and only show
 * xtext projects, but it's already nice like this...
 * 
 * @author ebousse
 *
 */
public class PlainK3WizardPage extends WizardPage {

	protected Text fProjText;

	private Button fProjButton;

	// Local/shared UI widgets
	private Button fEcoreFolderBrowse;
	private Text fEcoreFolderText;
	private Button fVariables;
	private Button fWorkspaceBrowse;
	private Text fMetamodelNameText;
	private Button fEMFCheckButton;
	
	private WidgetListener fListener = new WidgetListener();

	private class WidgetListener implements ModifyListener, SelectionListener {

		public void modifyText(ModifyEvent e) {
			// updateLaunchConfigurationDialog();
		}

		public void widgetDefaultSelected(SelectionEvent e) {/* do nothing */
		}

		public void widgetSelected(SelectionEvent e) {
			Object source = e.getSource();
			if (source == fProjButton) {
				// System.out.println("clic!");
				handleProjectButtonSelected();
			} else {
				// updateLaunchConfigurationDialog();
			}
		}
	}

	protected PlainK3WizardPage(String pageName) {
		super(pageName);
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		Composite projComp = SWTFactory.createComposite(parent, parent.getFont(), 1, 1, GridData.FILL_BOTH);
		createProjectEditor(projComp);
		createEcoreFolderComponent(projComp);
	}

	/**
	 * Show a dialog that lets the user select a project. This in turn provides
	 * context for the main type, allowing the user to key a main type name, or
	 * constraining the search for main types to the specified project.
	 */
	protected void handleProjectButtonSelected() {
		IJavaProject project = chooseJavaProject();
		if (project == null) {
			return;
		}
		String projectName = project.getElementName();
		fProjText.setText(projectName);
	}

	/**
	 * Convenience method to get the workspace root.
	 */
	protected IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	/**
	 * chooses a project for the type of java launch config that it is
	 * 
	 * @return the selected project or <code>null</code> if none
	 */
	private IJavaProject chooseJavaProject() {
		ILabelProvider labelProvider = new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT);
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
		dialog.setTitle(LauncherMessages.AbstractJavaMainTab_4);
		dialog.setMessage(LauncherMessages.AbstractJavaMainTab_3);
		try {
			dialog.setElements(JavaCore.create(getWorkspaceRoot()).getJavaProjects());
		} catch (JavaModelException jme) {
			JDIDebugUIPlugin.log(jme);
		}
		IJavaProject javaProject = getJavaProject();
		if (javaProject != null) {
			dialog.setInitialSelections(new Object[] { javaProject });
		}
		if (dialog.open() == Window.OK) {
			return (IJavaProject) dialog.getFirstResult();
		}
		return null;
	}

	/**
	 * Return the IJavaProject corresponding to the project name in the project
	 * name text field, or null if the text does not match a project name.
	 */
	protected IJavaProject getJavaProject() {
		String projectName = fProjText.getText().trim();
		if (projectName.length() < 1) {
			return null;
		}
		return getJavaModel().getJavaProject(projectName);
	}

	/**
	 * Convenience method to get access to the java model.
	 */
	private IJavaModel getJavaModel() {
		return JavaCore.create(getWorkspaceRoot());
	}

	protected Button createPushButton(Composite parent, String label, Image image) {
		return SWTFactory.createPushButton(parent, label, image);
	}

	/**
	 * Modify listener that simply updates the owning launch configuration dialog.
	 */
	private ModifyListener fBasicModifyListener = new ModifyListener() {
		public void modifyText(ModifyEvent evt) {
			//scheduleUpdateJob();
		}
	};
	
	/**
	 * Creates the widgets for specifying a main type.
	 * 
	 * @param parent
	 *            the parent composite
	 */
	protected void createProjectEditor(Composite parent) {
		Group group = SWTFactory.createGroup(parent, LauncherMessages.AbstractJavaMainTab_0, 2, 1,
				GridData.FILL_HORIZONTAL);
		fProjText = SWTFactory.createSingleText(group, 1);
		fProjText.addModifyListener(fListener);
		ControlAccessibleListener.addListener(fProjText, group.getText());
		fProjButton = createPushButton(group, LauncherMessages.AbstractJavaMainTab_1, null);
		fProjButton.addSelectionListener(fListener);
		setControl(group);
	}
	

	/**
	 * Constant representing the id of the {@link IDialogSettings} location for the {@link ContainerSelectionDialog}
	 * used on this tab
	 * 
	 * @since 3.6
	 */
	private final String WORKSPACE_SELECTION_DIALOG = IDebugUIConstants.PLUGIN_ID + ".WORKSPACE_SELECTION_DIALOG"; //$NON-NLS-1$


	/**
	 * Returns the {@link IDialogSettings} for the given id
	 * 
	 * @param id
	 *            the id of the dialog settings to get
	 * @return the {@link IDialogSettings} to pass into the {@link ContainerSelectionDialog}
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
		Group group = SWTFactory.createGroup(parent, "Folder with ecore models defining the metamodel", 5, 2,
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
				dialog.setMessage("Select a folder containing all the ecore models defining the metamodel.");
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


}
