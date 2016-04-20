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
package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.gemoc.sequential_addons.multidimensional.timeline.Activator;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;
import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon;
import fr.obeo.dsl.debug.ide.launch.AbstractDSLLaunchConfigurationDelegate;

public class MultidimensionalTimeLineView extends EngineSelectionDependentViewPart {

	public static final String ID = "org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView";

	private FXCanvas fxCanvas;

	private FxTraceListener traceListener;

	private IMultiDimensionalTraceAddon traceAddon;

	final private List<EObject> statesToBreakTo = new ArrayList<>();

	public List<EObject> getStatesToBreakTo() {
		return Collections.unmodifiableList(statesToBreakTo);
	}

	public MultidimensionalTimeLineView() {
		Activator.getDefault().setMultidimensionalTimeLineViewSupplier(() -> this);
	}

	@Override
	public void dispose() {
		Activator.getDefault().setMultidimensionalTimeLineViewSupplier(null);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		traceListener = new FxTraceListener();
		Scene scene = new Scene(traceListener);
		fxCanvas.setScene(scene);
		parent.getShell().addListener(SWT.Resize, (e) -> {
			traceListener.deepRefresh();
		});
		buildMenu(parent.getShell());

		final Menu menu = new Menu(fxCanvas);
		MenuItem launchAndBreakAtStateItem = new MenuItem(menu, SWT.NONE);
		launchAndBreakAtStateItem.setText("Relaunch and break at this state");
		final Supplier<Integer> getLastClickedState = traceListener.getLastClickedStateSupplier();
		launchAndBreakAtStateItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				launchConfigFromTrace(getLastClickedState.get());
			}
		});

		menu.addListener(SWT.Show, (event) -> {
			menu.setVisible(true);
		});

		Command displayMenu = () -> {
			Event event = new Event();
			event.type = SWT.Show;
			event.button = SWT.BUTTON2;
			menu.notifyListeners(SWT.Show, event);
		};

		traceListener.setMenuDisplayer(displayMenu);
	}

	interface Command {
		void execute();
	}

	private void buildMenu(Shell shell) {

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private FileDialog saveAsDialog;

			@Override
			protected void init() {
				super.init();
				setText("Save Trace");
				setToolTipText("Save Trace");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/save.gif");
				setImageDescriptor(id);
				setEnabled(true);

				saveAsDialog = new FileDialog(shell, SWT.SAVE);
				saveAsDialog.setFilterNames(new String[] { "Trace Files", "All Files (*.*)" });
				saveAsDialog.setFilterExtensions(new String[] { "*.trace", "*.*" });
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				saveAsDialog.setText("Save As");
				String filePath = saveAsDialog.open();
				URI uri = URI.createFileURI(filePath);

				traceAddon.getTraceManager().save(uri);
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			@Override
			protected void init() {
				super.init();
				setText("Remove Trace");
				setToolTipText("Remove Trace");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/remove.gif");
				setImageDescriptor(id);
				setEnabled(true);
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				traceAddon = null;
				traceListener.setTraceExplorer(null);
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private FileDialog fileDialog;

			@Override
			protected void init() {
				super.init();
				setText("Compare Traces");
				setToolTipText("Compare Traces");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/insp_sbook.gif");
				setImageDescriptor(id);
				setEnabled(true);

				fileDialog = new FileDialog(shell, SWT.OPEN);
				fileDialog.setFilterExtensions(new String[] { "*.trace" });
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				fileDialog.setText("Choose a first trace to load");
				String filePath1 = fileDialog.open();
				if (!filePath1.equals("")) {
					fileDialog.setText("Choose a second trace to load");
					String filePath2 = fileDialog.open();
					if (!filePath2.equals("")) {
						Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
						Map<String, Object> m = reg.getExtensionToFactoryMap();
						m.put("trace", new XMIResourceFactoryImpl());

						// Obtain a new resource set
						ResourceSet resSet = new ResourceSetImpl();

						// Get the resources
						URI filePath1URI = URI.createFileURI(filePath1);
						Resource traceResource1 = resSet.getResource(filePath1URI, true);
						EcoreUtil.resolveAll(traceResource1);
						URI filePath2URI = URI.createFileURI(filePath2);
						Resource traceResource2 = resSet.getResource(filePath2URI, true);
						EcoreUtil.resolveAll(traceResource2);

						EMFCompare compare = EMFCompare.builder().build();
						IComparisonScope comparisonScope = new DefaultComparisonScope(traceResource1, traceResource2,
								null);
						Comparison comparison = compare.compare(comparisonScope);
						List<Diff> differences = comparison.getDifferences();
						System.out.println(differences);
					}
				}
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private FileDialog fileDialog;

			@Override
			protected void init() {
				super.init();
				setText("Open Trace");
				setToolTipText("Open Trace");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/jload_obj.gif");
				setImageDescriptor(id);
				setEnabled(true);

				fileDialog = new FileDialog(shell, SWT.OPEN);
				fileDialog.setFilterExtensions(new String[] { "*.trace" });
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				fileDialog.setText("Open Trace");
				String filePath = fileDialog.open();

				if (!filePath.equals("")) {
					Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
					Map<String, Object> m = reg.getExtensionToFactoryMap();
					m.put("trace", new XMIResourceFactoryImpl());

					// Obtain a new resource set
					ResourceSet resSet = new ResourceSetImpl();

					// Get the resources
					URI filePath1URI = URI.createFileURI(filePath);
					Resource traceResource = resSet.getResource(filePath1URI, true);
					EcoreUtil.resolveAll(traceResource);
					AbstractTraceAddon newTraceAddon = null;
					try {
						IExtensionRegistry extReg = Platform.getExtensionRegistry();
						IExtensionPoint ep = extReg
								.getExtensionPoint("org.gemoc.gemoc_language_workbench.engine_addon");
						IExtension[] extensions = ep.getExtensions();
						for (int i = 0; i < extensions.length && newTraceAddon == null; i++) {
							IExtension ext = extensions[i];
							IConfigurationElement[] confElements = ext.getConfigurationElements();
							for (int j = 0; j < confElements.length; j++) {
								IConfigurationElement confElement = confElements[j];
								String attr = confElement.getAttribute("Class");
								if (attr != null) {
									Object obj = confElement.createExecutableExtension("Class");
									if (obj instanceof AbstractTraceAddon) {
										AbstractTraceAddon obj_cast = (AbstractTraceAddon) obj;
										if (obj_cast.isAddonForTrace(traceResource.getContents().get(0))) {
											newTraceAddon = obj_cast;
											break;
										}
									}
								}
							}
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}

					if (newTraceAddon != null) {
						traceAddon = newTraceAddon;
						newTraceAddon.load(null, traceResource);
						traceListener.setTraceExplorer(traceAddon.getTraceExplorer());
						List<Resource> otherResources = resSet.getResources().stream()
								.filter((r) -> r != traceResource).collect(Collectors.toList());
						URI uri = otherResources
								.get(0)
								.getURI()
								.deresolve(
										URI.createURI(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString()));
						System.out.println(uri);
					}
				}
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_CHECK_BOX) {
			@Override
			protected void init() {
				super.init();
				setText("Scroll Lock");
				setToolTipText("Scroll Lock");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/lock_co.gif");
				setImageDescriptor(id);
				setEnabled(true);
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				traceListener.setScrollLock(isChecked());
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private InputDialog dialog;

			@Override
			protected void init() {
				super.init();
				setText("Jump to state");
				setToolTipText("Jump To State");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/runtoline_co.gif");
				setImageDescriptor(id);
				setEnabled(true);

				dialog = new InputDialog(shell, "Jump to state", "Enter the desired state", "0", s -> {
					try {
						Integer.parseInt(s);
						return null;
					} catch (NumberFormatException e) {
						return "Not a valid state";
					}
				});
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				dialog.open();
				if (dialog.getReturnCode() == Window.OK) {
					int state = Integer.parseInt(dialog.getValue());
					traceListener.getJumpConsumer().accept(state);
				}
			}
		});
	}

	private void addActionToToolbar(Action action) {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(action);
	}

	@Override
	public void setFocus() {
		if (fxCanvas != null) {
			fxCanvas.setFocus();
		}
	}

	private boolean canDisplayTimeline(IBasicExecutionEngine engine) {
		if (engine.getExecutionContext().getExecutionMode().equals(ExecutionMode.Run)
				&& engine.getRunningStatus().equals(RunStatus.Stopped)) {
			return true;
		}
		if (engine.getExecutionContext().getExecutionMode().equals(ExecutionMode.Animation)) {
			return true;
		}
		return false;
	}

	private Map<String, Object> runConfigurationAttributes = null;

	private void setupRunConfigurationAttributes(IRunConfiguration configuration) {
		Map<String, Object> attributes = new HashMap<>();
		if (configuration.getLanguageName() != "") {
			attributes.put(IRunConfiguration.LAUNCH_SELECTED_LANGUAGE, configuration.getLanguageName());
		}
		URI modelURI = configuration.getExecutedModelURI();
		if (modelURI != null) {
			String scheme = modelURI.scheme() + ":/resource";
			attributes.put(AbstractDSLLaunchConfigurationDelegate.RESOURCE_URI,
					modelURI.toString().substring(scheme.length()));
		}
		if (configuration.getAnimationDelay() != 0) {
			attributes.put(IRunConfiguration.LAUNCH_DELAY, configuration.getAnimationDelay());
		}
		URI animatorURI = configuration.getAnimatorURI();
		if (configuration.getAnimatorURI() != null) {
			String scheme = animatorURI.scheme() + ":/resource";
			attributes.put("airdResource", animatorURI.toString().substring(scheme.length()));
		}
		if (configuration.getExecutionEntryPoint() != null) {
			attributes.put(IRunConfiguration.LAUNCH_METHOD_ENTRY_POINT, configuration.getExecutionEntryPoint());
		}
		if (configuration.getModelEntryPoint() != null) {
			attributes.put(IRunConfiguration.LAUNCH_MODEL_ENTRY_POINT, configuration.getModelEntryPoint());
		}
		if (configuration.getModelInitializationMethod() != null) {
			attributes
					.put(IRunConfiguration.LAUNCH_INITIALIZATION_METHOD, configuration.getModelInitializationMethod());
		}
		if (configuration.getModelInitializationArguments() != null) {
			attributes.put(IRunConfiguration.LAUNCH_INITIALIZATION_ARGUMENTS,
					configuration.getModelInitializationArguments());
		}
		for (EngineAddonSpecificationExtension extension : configuration.getEngineAddonExtensions()) {
			attributes.put(extension.getName(), true);
		}
		attributes.put(IRunConfiguration.LAUNCH_DEADLOCK_DETECTION_DEPTH, configuration.getDeadlockDetectionDepth());
		if (!attributes.isEmpty()) {
			runConfigurationAttributes = attributes;
		}
	}

	@Override
	public void engineSelectionChanged(IBasicExecutionEngine engine) {
		if (engine != null) {
			setupRunConfigurationAttributes(engine.getExecutionContext().getRunConfiguration());
			if (canDisplayTimeline(engine)) {
				Set<IMultiDimensionalTraceAddon> traceAddons = engine
						.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
				if (!traceAddons.isEmpty()) {
					traceAddon = traceAddons.iterator().next();
					traceListener.setTraceExplorer(traceAddon.getTraceExplorer());
				}
			} else {
				// TODO
			}
		}
	}

	private void launchConfigFromTrace(int stateToBreakTo) {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		String launchName = manager.generateLaunchConfigurationName((String) runConfigurationAttributes
				.get(IRunConfiguration.LAUNCH_SELECTED_LANGUAGE));
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType("org.gemoc.execution.sequential.javaengine.ui.launcher");
		try {
			ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, launchName);
			workingCopy.setAttributes(runConfigurationAttributes);
			workingCopy.setAttribute("GEMOC_BREAK_AT_STATE", stateToBreakTo);
			workingCopy.launch("debug", null, false, true);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
