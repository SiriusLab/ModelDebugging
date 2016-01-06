package org.gemoc.execution.sequential.javaengine.ui.launcher.tabs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.gemoc.commons.eclipse.emf.URIHelper;
import org.gemoc.commons.eclipse.ui.dialogs.SelectAnyIFileDialog;
import org.gemoc.execution.engine.commons.MelangeHelper;
import org.gemoc.execution.engine.commons.ModelExecutionContext;
import org.gemoc.execution.engine.ui.commons.RunConfiguration;
import org.gemoc.execution.sequential.javaengine.PlainK3ExecutionEngine;
//import org.gemoc.executionengine.java.sequential_modeling_workbench.ui.Activator;
import org.gemoc.execution.sequential.javaengine.ui.launcher.LauncherMessages;
import org.gemoc.xdsmlframework.ui.utils.dialogs.SelectAIRDIFileDialog;
import org.gemoc.xdsmlframework.ui.utils.dialogs.SelectAnyEObjectDialog;
import org.gemoc.xdsmlframework.ui.utils.dialogs.SelectMainMethodDialog;
import org.gemoc.executionframework.ui.utils.ENamedElementQualifiedNameLabelProvider;
import org.osgi.framework.Bundle;

import fr.obeo.dsl.debug.ide.launch.AbstractDSLLaunchConfigurationDelegate;
import fr.obeo.dsl.debug.ide.sirius.ui.launch.AbstractDSLLaunchConfigurationDelegateUI;

public class LaunchConfigurationMainTab extends LaunchConfigurationTab {

	protected Composite _parent;

	protected Text _modelLocationText;
	protected Text _siriusRepresentationLocationText;
	protected Button _animateButton;
	protected Text _delayText;
	protected Text _melangeQueryText;
	protected Button _animationFirstBreak;

	protected Group _k3Area;
	protected Text _entryPointModelElementText;
	protected Text _entryPointMethodText;

	protected Combo _languageCombo;
	protected Combo _modelTypeCombo;

	protected Text modelofexecutionglml_LocationText;

	public int GRID_DEFAULT_WIDTH = 200;
	
	protected IProject _modelProject;

	@Override
	public void createControl(Composite parent) {
		_parent = parent;
		Composite area = new Composite(parent, SWT.NULL);
		GridLayout gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		area.setLayout(gl);
		area.layout();
		setControl(area);

		Group modelArea = createGroup(area, "Model:");
		createModelLayout(modelArea, null);

		Group languageArea = createGroup(area, "Language:");
		createLanguageLayout(languageArea, null);

		Group debugArea = createGroup(area, "Animation:");
		createAnimationLayout(debugArea, null);

		_k3Area = createGroup(area, "Pure K3 execution:");
		createK3Layout(_k3Area, null);

	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(RunConfiguration.LAUNCH_DELAY, 1000);
		configuration.setAttribute(RunConfiguration.LAUNCH_MODEL_ENTRY_POINT, "");
		configuration.setAttribute(RunConfiguration.LAUNCH_METHOD_ENTRY_POINT, "");
		configuration.setAttribute(RunConfiguration.LAUNCH_SELECTED_LANGUAGE, "");
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			RunConfiguration runConfiguration = new RunConfiguration(
					configuration);
			_modelLocationText.setText(URIHelper
					.removePlatformScheme(runConfiguration
							.getExecutedModelURI()));

			if (runConfiguration.getAnimatorURI() != null)
				_siriusRepresentationLocationText
						.setText(URIHelper
								.removePlatformScheme(runConfiguration
										.getAnimatorURI()));
			else
				_siriusRepresentationLocationText.setText("");
			
			_delayText.setText(Integer.toString(runConfiguration
					.getAnimationDelay()));
			_animationFirstBreak.setSelection(runConfiguration.getBreakStart());

			_entryPointModelElementText.setText(runConfiguration
					.getModelEntryPoint());
			_entryPointMethodText.setText(runConfiguration
					.getExecutionEntryPoint());
			_languageCombo.setText(runConfiguration
					.getLanguageName());

		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(
				AbstractDSLLaunchConfigurationDelegate.RESOURCE_URI,
				this._modelLocationText.getText());
		configuration.setAttribute(
				AbstractDSLLaunchConfigurationDelegateUI.SIRIUS_RESOURCE_URI,
				this._siriusRepresentationLocationText.getText());
		configuration.setAttribute(RunConfiguration.LAUNCH_DELAY,
				Integer.parseInt(_delayText.getText()));
		configuration.setAttribute(RunConfiguration.LAUNCH_SELECTED_LANGUAGE,
				_languageCombo.getText());
		configuration.setAttribute(RunConfiguration.LAUNCH_MODEL_ENTRY_POINT,
				_entryPointModelElementText.getText());
		configuration.setAttribute(RunConfiguration.LAUNCH_METHOD_ENTRY_POINT,
				_entryPointMethodText.getText());
		configuration.setAttribute(RunConfiguration.LAUNCH_BREAK_START,
				_animationFirstBreak.getSelection());
	}

	@Override
	public String getName() {
		return "Main";
	}

	// -----------------------------------

	/**
	 * Basic modify listener that can be reused if there is no more precise need
	 */
	private ModifyListener fBasicModifyListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent arg0) {
			updateLaunchConfigurationDialog();
		}
	};

	// -----------------------------------

	/***
	 * Create the Field where user enters model to execute
	 * 
	 * @param parent
	 * @param font
	 * @return
	 */
	public Composite createModelLayout(Composite parent, Font font) {
		createTextLabelLayout(parent, "Model to execute");
		// Model location text
		_modelLocationText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		_modelLocationText.setLayoutData(createStandardLayout());
		_modelLocationText.setFont(font);
		_modelLocationText.addModifyListener(fBasicModifyListener);
		Button modelLocationButton = createPushButton(parent, "Browse", null);
		modelLocationButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent evt) {
				// handleModelLocationButtonSelected();
				// TODO launch the appropriate selector

				SelectAnyIFileDialog dialog = new SelectAnyIFileDialog();
				if (dialog.open() == Dialog.OK) {
					String modelPath = ((IResource) dialog.getResult()[0])
							.getFullPath().toPortableString();
					_modelLocationText.setText(modelPath);
					updateLaunchConfigurationDialog();
					_modelProject = ((IResource) dialog.getResult()[0]).getProject();
				}
			}
		});
		return parent;
	}

	private Composite createAnimationLayout(Composite parent, Font font) {
		createTextLabelLayout(parent, "Animator");

		_siriusRepresentationLocationText = new Text(parent, SWT.SINGLE
				| SWT.BORDER);
		_siriusRepresentationLocationText.setLayoutData(createStandardLayout());
		_siriusRepresentationLocationText.setFont(font);
		_siriusRepresentationLocationText
				.addModifyListener(fBasicModifyListener);
		Button siriusRepresentationLocationButton = createPushButton(parent,
				"Browse", null);
		siriusRepresentationLocationButton
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						// handleModelLocationButtonSelected();
						// TODO launch the appropriate selector

						SelectAIRDIFileDialog dialog = new SelectAIRDIFileDialog();
						if (dialog.open() == Dialog.OK) {
							String modelPath = ((IResource) dialog.getResult()[0])
									.getFullPath().toPortableString();
							_siriusRepresentationLocationText
									.setText(modelPath);
							updateLaunchConfigurationDialog();
						}
					}
				});

		createTextLabelLayout(parent, "Delay");
		_delayText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		_delayText.setLayoutData(createStandardLayout());
		_delayText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				updateLaunchConfigurationDialog();
			}
		});
		createTextLabelLayout(parent, "(in milliseconds)");

		new Label(parent, SWT.NONE).setText("");
		_animationFirstBreak = new Button(parent, SWT.CHECK);
		_animationFirstBreak.setText("Break at start");
		_animationFirstBreak.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				updateLaunchConfigurationDialog();
			}
		}

		);

		return parent;
	}

	private GridData createStandardLayout() {
		return new GridData(SWT.FILL, SWT.CENTER, true, false);
	}

	/***
	 * Create the Field where user enters the language used to execute
	 * 
	 * @param parent
	 * @param font
	 * @return
	 */
	public Composite createLanguageLayout(Composite parent, Font font) {
		// Language
		createTextLabelLayout(parent, "Melange languages");
		_languageCombo = new Combo(parent, SWT.NONE);
		_languageCombo.setLayoutData(createStandardLayout());

		List<String> languagesNames = MelangeHelper.getAllLanguages();
		String[] empty = {};
		_languageCombo.setItems(languagesNames.toArray(empty));
		_languageCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selection = _languageCombo.getText();
				List<String> modelTypeNames = MelangeHelper.getModelTypes(selection);
				_modelTypeCombo.setItems(modelTypeNames.toArray(empty));
				updateLaunchConfigurationDialog();
			}
		});
		createTextLabelLayout(parent, "");

		// ModelType
		createTextLabelLayout(parent, "Available ModelType");
		_modelTypeCombo = new Combo(parent, SWT.NONE);
		_modelTypeCombo.setLayoutData(createStandardLayout());

		return parent;
	}

	private Composite createK3Layout(Composite parent, Font font) {
		createTextLabelLayout(parent, "Main method");
		_entryPointMethodText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		_entryPointMethodText.setLayoutData(createStandardLayout());
		_entryPointMethodText.setFont(font);
		_entryPointMethodText.setEditable(false);
		_entryPointMethodText.addModifyListener(fBasicModifyListener);
		Button mainMethodBrowseButton = createPushButton(parent, "Browse", null);
		mainMethodBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(_languageCombo.getText() == null){
					setErrorMessage("Please select a language.");
				}
				else{
					Set<Class<?>> candidateAspects = MelangeHelper.getAspects(_languageCombo.getText());
					SelectMainMethodDialog dialog = new SelectMainMethodDialog(
							candidateAspects, new ENamedElementQualifiedNameLabelProvider());
					int res = dialog.open();
					if (res == WizardDialog.OK) {
						Method selection = (Method) dialog.getFirstResult();
						_entryPointMethodText.setText(selection.toString());
					}
				}
			}
		});
		
		createTextLabelLayout(parent, "Main model element");
		_entryPointModelElementText = new Text(parent, SWT.SINGLE | SWT.BORDER);
		_entryPointModelElementText.setLayoutData(createStandardLayout());
		_entryPointModelElementText.setFont(font);
		_entryPointModelElementText.setEditable(false);
		_entryPointModelElementText.addModifyListener(fBasicModifyListener);
		Button mainModelElemBrowseButton = createPushButton(parent, "Browse",
				null);
		mainModelElemBrowseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Resource model = getModel();
				if( model == null){
					setErrorMessage("Please select a model to execute.");
				}
				else if(_entryPointMethodText.getText() == null || _entryPointMethodText.getText().equals("")){
					setErrorMessage("Please select a main method.");
				}
				else {
					SelectAnyEObjectDialog dialog = new SelectAnyEObjectDialog(
							PlatformUI.getWorkbench()
									.getActiveWorkbenchWindow().getShell(),
							model.getResourceSet(),
							new ENamedElementQualifiedNameLabelProvider()){
						protected boolean select(EObject obj) {
							String methodSignature = _entryPointMethodText.getText();
							String firstParamType = MelangeHelper.getParametersType(methodSignature)[0];
							String simpleParamType =  MelangeHelper.lastSegment(firstParamType);
							return obj.eClass().getName().equals(simpleParamType);
						}
					};
					int res = dialog.open();
					if (res == WizardDialog.OK) {
						EObject selection = (EObject) dialog.getFirstResult();
						String uriFragment = selection.eResource()
								.getURIFragment(selection);
						_entryPointModelElementText.setText(uriFragment);
					}
				}
			}
		});

		return parent;
	}

	@Override
	protected void updateLaunchConfigurationDialog() {
		super.updateLaunchConfigurationDialog();
		_k3Area.setVisible(true);
	}

	private Resource getModel() {
		URI modelURI = URI.createPlatformResourceURI(
				_modelLocationText.getText(), true);
		return PlainK3ExecutionEngine.loadModel(modelURI);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(ILaunchConfiguration config) {
		setErrorMessage(null);
		setMessage(null);
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		String modelName = _modelLocationText.getText().trim();
		if (modelName.length() > 0) {
			
			IResource modelIResource = workspace.getRoot().findMember(modelName);
			if (modelIResource == null || !modelIResource.exists()) {
				setErrorMessage(NLS.bind(LauncherMessages.SequentialMainTab_model_doesnt_exist, new String[] {modelName})); 
				return false;
			}
			if (modelName.equals("/")) {
				setErrorMessage(LauncherMessages.SequentialMainTab_Model_not_specified); 
				return false;
			}
			if (! (modelIResource instanceof IFile)) {
				setErrorMessage(NLS.bind(LauncherMessages.SequentialMainTab_invalid_model_file, new String[] {modelName})); 
				return false;
			}
		}
		if (modelName.length() == 0) {
			setErrorMessage(LauncherMessages.SequentialMainTab_Model_not_specified); 
			return false;
		}
		
		String languageName = _languageCombo.getText().trim();
		if (languageName.length() == 0) {
			setErrorMessage(LauncherMessages.SequentialMainTab_Language_not_specified); 
			return false;
		}
		else if(MelangeHelper.getEntryPoints(languageName).isEmpty()){
			setErrorMessage(LauncherMessages.SequentialMainTab_Language_main_methods_dont_exist); 
			return false;
		}
		
		String mainMethod = _entryPointMethodText.getText().trim();
		if (mainMethod.length() == 0) {
			setErrorMessage(LauncherMessages.SequentialMainTab_Language_main_method_not_selected); 
			return false;
		}
		
		String rootElement = _entryPointModelElementText.getText().trim();
		if (rootElement.length() == 0) {
			setErrorMessage(LauncherMessages.SequentialMainTab_Language_root_element_not_selected); 
			return false;
		}
		
		String[] params =MelangeHelper.getParametersType(mainMethod);
		String firstParam = MelangeHelper.lastSegment(params[0]);
		String rootEClass = getModel().getEObject(rootElement).eClass().getName();
		if( !(params.length == 1 && firstParam.equals(rootEClass)) ){
			setErrorMessage(LauncherMessages.SequentialMainTab_Language_incompatible_root_and_main); 
			return false;
		}
		
		return true;
	}
}
