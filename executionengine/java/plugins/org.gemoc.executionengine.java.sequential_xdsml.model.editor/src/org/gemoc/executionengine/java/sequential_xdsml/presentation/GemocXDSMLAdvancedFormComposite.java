package org.gemoc.executionengine.java.sequential_xdsml.presentation;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.wizards.NewClassCreationWizard;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.pde.internal.ui.PDEPlugin;
import org.eclipse.pde.internal.ui.util.PDEJavaHelperUI;
import org.eclipse.pde.internal.ui.util.SWTUtil;
import org.eclipse.pde.internal.ui.util.TextUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;

/*
 * IMPORTANT : this file has been edited using Windows builder.
 * This is why the structure is quite "unstructured" and use long methods.
 * The data binding is also managed via Windows Builder.
 */

public class GemocXDSMLAdvancedFormComposite extends AbstractGemocFormComposite {
	private DataBindingContext m_bindingContext;
	

	protected SequentialXDSMLModelWrapper xdsmlWrappedObject = new SequentialXDSMLModelWrapper();

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text txtModelLoaderClass;
	

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public GemocXDSMLAdvancedFormComposite(Composite parent, int style) {
		super(parent, SWT.NONE);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new ColumnLayout());

		Group grpDomainModelDefinition = new Group(this, SWT.NONE);
		grpDomainModelDefinition.setText("Domain Model");
		toolkit.adapt(grpDomainModelDefinition);
		toolkit.paintBordersFor(grpDomainModelDefinition);
		grpDomainModelDefinition.setLayout(new GridLayout(3, false));

		Label lblDomainCustomIntroduction = new Label(grpDomainModelDefinition, SWT.NONE);
		lblDomainCustomIntroduction.setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 3, 1));
		lblDomainCustomIntroduction
				.setText("Optionnaly provide you own custom model loader.");
		lblDomainCustomIntroduction.setBounds(0, 0, 397, 15);
		toolkit.adapt(lblDomainCustomIntroduction, true, true);
		
		Link linkModelLoaderClass = new Link(grpDomainModelDefinition, SWT.NONE);
		linkModelLoaderClass.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		linkModelLoaderClass.setBounds(0, 0, 49, 15);
		toolkit.adapt(linkModelLoaderClass, true, true);
		linkModelLoaderClass.setText("<a>Model loader class name</a>");

		txtModelLoaderClass = new Text(grpDomainModelDefinition, SWT.BORDER);
		GridData gd_txtEMFProject = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_txtEMFProject.widthHint = 226;
		txtModelLoaderClass.setLayoutData(gd_txtEMFProject);
		txtModelLoaderClass.setBounds(0, 0, 244, 21);
		toolkit.adapt(txtModelLoaderClass, true, true);

		Button btnBrowseModelLoaderClass = new Button(grpDomainModelDefinition,
				SWT.NONE);

		btnBrowseModelLoaderClass.setBounds(0, 0, 50, 25);
		toolkit.adapt(btnBrowseModelLoaderClass, true, true);
		btnBrowseModelLoaderClass.setText("Browse");

		
		Group grpBehaviorDefinition = new Group(this, SWT.NONE);
		grpBehaviorDefinition.setText("Behavior definition");
		toolkit.adapt(grpBehaviorDefinition);
		toolkit.paintBordersFor(grpBehaviorDefinition);
		grpBehaviorDefinition.setLayout(new GridLayout(1, false));

		
		
		initLinkListeners(linkModelLoaderClass);

		initButtonListeners( btnBrowseModelLoaderClass);
		m_bindingContext = initDataBindings();

	}

	public void initControl(AdapterFactoryEditingDomain editingDomain) {
		if (editingDomain != null) {
			this.editingDomain = editingDomain;
			editingDomain.toString();

			if (editingDomain.getResourceSet().getResources().size() > 0) {
				if (editingDomain.getResourceSet().getResources().get(0)
						.getContents().size() > 0) {
					EObject eObject = editingDomain.getResourceSet()
							.getResources().get(0).getContents().get(0);
					if (eObject instanceof SequentialLanguageDefinition) {
						rootModelElement = (SequentialLanguageDefinition) eObject;
						// txtLanguageName.setText(confModelElement.getLanguageDefinition().getName());
						SequentialXDSMLModelWrapperHelper.init(xdsmlWrappedObject,
								(SequentialLanguageDefinition)rootModelElement);

					}
				}
			}
		}

		m_bindingContext = initDataBindings();
		
		initControlFromWrappedObject();

		initTxtListeners();

	}

	/**
	 * Sets the initial values of the fields when opening the view
	 */
	public void initControlFromWrappedObject() {
		
		
	}

	/**
	 * Initialize the modifyListener for the txt field They are in charge of
	 * reflecting the change to the underlying model via the bean Note that they
	 * must act in a TransactionalEditingDomain in order to be correctly handled
	 */
	protected void initTxtListeners() {
		// all the listeners that will really edit the model
		txtModelLoaderClass.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// Get the widget whose text was modified
				final Text text = (Text) e.widget;
				TransactionalEditingDomain teditingDomain = TransactionalEditingDomain.Factory.INSTANCE
						.createEditingDomain();
				editingDomain.getCommandStack().execute(
						new RecordingCommand(teditingDomain) {
							public void doExecute() {
								xdsmlWrappedObject.setModelLoaderClass(text
										.getText());
							}
						});
			}
		});

	}

	/**
	 * Creates the listeners in charge of the behavior for the links
	 */
	protected void initLinkListeners(Link linkModelLoader) {
		
		linkModelLoader.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			//	if (!txtCodeExecutorClass.getText().isEmpty()) {
					
					//String value = txtCodeExecutorClass.getText();
					IProject project = getCurrentIFile().getProject();
					/*BundlePluginModel bmodel = new BundlePluginModel();
					//bmodel.setBundleDescription(Activator.);
					JavaAttributeValue javaAttributeValue = new JavaAttributeValue(project, bmodel, null, value);
					javaAttributeValue.
					value = PDEJavaHelperUI.createClass(value, project, javaAttributeValue, false);
					if (value != null)
						txtCodeExecutorClass.setText(value);
						*/
					String name = TextUtil.trimNonAlphaChars(txtModelLoaderClass.getText()).replace('$', '.');
					try {
						if (project.hasNature(JavaCore.NATURE_ID)) {
							IJavaProject javaProject = JavaCore.create(project);
							IJavaElement result = null;
							if (name.length() > 0)
								result = javaProject.findType(name);
							if (result != null)
								JavaUI.openInEditor(result);
							else {
								NewClassCreationWizard wizard = new NewClassCreationWizard();
								//wizard.init(Plugin., currentSelection);
								WizardDialog dialog = new WizardDialog(PDEPlugin.getActiveWorkbenchShell(), wizard);
								dialog.create();
								SWTUtil.setDialogSize(dialog, 400, 500);
								int dResult = dialog.open();
								if (dResult == Window.OK)
									txtModelLoaderClass.setText(wizard.getCreatedElement().getElementName());
							}
						}
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JavaModelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CoreException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});

	}

	/**
	 * Creates the listeners in charge of the behavior for the buttons
	 */
	protected void initButtonListeners(	Button btnModelLoaderClass) {
		btnModelLoaderClass.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					String className = txtModelLoaderClass.getText();
					IResource resource = getCurrentIFile();
					String type = PDEJavaHelperUI.selectType(resource, IJavaElementSearchConstants.CONSIDER_CLASSES, className, null);
					if (type != null)
						txtModelLoaderClass.setText(type);
									
					break;
				}
			}
		});
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtModelLoaderClassObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtModelLoaderClass);
		IObservableValue modelLoaderClassXdsmlWrappedObjectObserveValue = BeanProperties.value("modelLoaderClass").observe(xdsmlWrappedObject);
		bindingContext.bindValue(observeTextTxtModelLoaderClassObserveWidget, modelLoaderClassXdsmlWrappedObjectObserveValue, null, null);
		//
		return bindingContext;
	}
}
