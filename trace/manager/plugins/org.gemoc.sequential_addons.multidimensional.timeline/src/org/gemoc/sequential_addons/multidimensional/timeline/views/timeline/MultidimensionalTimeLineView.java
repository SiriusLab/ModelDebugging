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

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
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

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;
import fr.inria.diverse.trace.gemoc.api.ITraceExplorer;
import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon;

public class MultidimensionalTimeLineView extends EngineSelectionDependentViewPart {

	public static final String ID = "org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView";

	private FXCanvas fxCanvas;

	private FxTraceListener traceListener;

	public MultidimensionalTimeLineView() {
		Activator.getDefault().setMultidimensionalTimeLineViewSupplier(() -> this);
	}

	@Override
	public void dispose() {
		Activator.getDefault().setMultidimensionalTimeLineViewSupplier(null);
		super.dispose();
	}

	public void setTraceExplorer(ITraceExplorer timelineProvider) {
		traceListener.setTraceExplorer(timelineProvider);
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
		MenuItem launchAndBreakAtStateItem = new MenuItem(menu, SWT.CASCADE);
		launchAndBreakAtStateItem.setText("Break at this state");
		final Supplier<Integer> getLastClickedState = traceListener.getLastClickedStateSupplier();
		
		final List<ILaunchConfiguration> launchConfigurations = new ArrayList<>();
		
		try {
			ILaunchConfiguration[] tmp = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations();
			for (int i=0;i<tmp.length;i++) {
				launchConfigurations.add(tmp[i]);
			}
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		
		final Menu launchAndBreakAtStateMenu = new Menu(menu);
		launchAndBreakAtStateItem.setMenu(launchAndBreakAtStateMenu);
		for (ILaunchConfiguration launchConfiguration : launchConfigurations) {
			final MenuItem launchSubMenuItem = new MenuItem(launchAndBreakAtStateMenu, SWT.NONE);
			launchSubMenuItem.setText(launchConfiguration.getName());
			launchSubMenuItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent evt) {
					launchConfig(launchConfiguration, getLastClickedState.get());
				}
			});
		}
		
		final MenuItem launchSubMenuItem = new MenuItem(launchAndBreakAtStateMenu, SWT.NONE);
		launchSubMenuItem.setText("Other configuration");
		launchSubMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent evt) {
				FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.OPEN);
				fileDialog.setFilterExtensions(new String[] { "*.launch" });
				fileDialog.setText("Choose a launch configuration");
				String filePath = fileDialog.open();
				try {
					IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
					IFile[] launchConfigs = workspaceRoot.findFilesForLocationURI(URIUtil.fromString("file:/"
							+ filePath));
					if (launchConfigs.length > 0) {
						launchConfigFromFile(launchConfigs[0], getLastClickedState.get());
					}
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
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

			private FileDialog fileDialog;

			@Override
			protected void init() {
				super.init();
				setText("Compare Traces");
				setToolTipText("compares two traces");
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
				fileDialog.setText("Choose a first trace to load");
				String filePath1 = fileDialog.open();
				fileDialog.setText("Choose a second trace to load");
				String filePath2 = fileDialog.open();

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
				IComparisonScope comparisonScope = new DefaultComparisonScope(traceResource1, traceResource2, null);
				Comparison comparison = compare.compare(comparisonScope);
				List<Diff> differences = comparison.getDifferences();
				System.out.println(differences);
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private FileDialog fileDialog;

			@Override
			protected void init() {
				super.init();
				setText("Load Trace");
				setToolTipText("Load a previously saved trace");
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
				fileDialog.setText("Choose a trace to load");
				String filePath1 = fileDialog.open();

				Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
				Map<String, Object> m = reg.getExtensionToFactoryMap();
				m.put("trace", new XMIResourceFactoryImpl());

				// Obtain a new resource set
				ResourceSet resSet = new ResourceSetImpl();

				// Get the resources
				URI filePath1URI = URI.createFileURI(filePath1);
				Resource traceResource = resSet.getResource(filePath1URI, true);
				EcoreUtil.resolveAll(traceResource);
				AbstractTraceAddon traceAddon = null;
				try {
					IExtensionRegistry extReg = Platform.getExtensionRegistry();
					IExtensionPoint ep = extReg.getExtensionPoint("org.gemoc.gemoc_language_workbench.engine_addon");
					IExtension[] extensions = ep.getExtensions();
					for (int i = 0; i < extensions.length && traceAddon == null; i++) {
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
										traceAddon = obj_cast;
										break;
									}
								}
							}
						}
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}

				if (traceAddon != null) {
					traceAddon.load(null, traceResource);
					traceListener.setTraceExplorer(traceAddon.getTraceExplorer());
				}
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_CHECK_BOX) {
			@Override
			protected void init() {
				super.init();
				setText("Scroll Lock");
				setToolTipText("Toggles Scroll Lock");
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
				setToolTipText("Jumps to the specified state");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/synced.gif");
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

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private FileDialog fileDialog;

			@Override
			protected void init() {
				super.init();
				setText("Flblbl");
				setToolTipText("Blblfl");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/jload_obj.gif");
				setImageDescriptor(id);
				setEnabled(true);

				fileDialog = new FileDialog(shell, SWT.OPEN);
				fileDialog.setFilterExtensions(new String[] { "*.launch" });
			}

			@Override
			public void engineSelectionChanged(IBasicExecutionEngine engine) {
			}

			@Override
			public void run() {
				fileDialog.setText("Choose a launch configuration");
				String filePath = fileDialog.open();

				try {
					IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
					IFile[] launchConfigs = workspaceRoot.findFilesForLocationURI(URIUtil.fromString("file:/"
							+ filePath));
					if (launchConfigs.length > 0) {
						launchConfigFromFile(launchConfigs[0], 15);
					}
				} catch (URISyntaxException e) {
					e.printStackTrace();
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

	@Override
	public void engineSelectionChanged(IBasicExecutionEngine engine) {
		if (engine != null) {
			if (canDisplayTimeline(engine)) {
				Set<IMultiDimensionalTraceAddon> traceAddons = engine
						.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
				if (!traceAddons.isEmpty()) {
					setTraceExplorer(traceAddons.iterator().next().getTraceExplorer());
				}
			} else {
				// TODO
			}
		}
	}

	private void launchConfigFromFile(IFile file, int stateIndexToBreakTo) {
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfiguration launchConfiguration = launchManager.getLaunchConfiguration(file);
		launchConfig(launchConfiguration, stateIndexToBreakTo);
	}
	
	private void launchConfig(ILaunchConfiguration launchConfiguration, int stateIndexToBreakTo) {
		try {
			ILaunchConfigurationWorkingCopy workingCopy = launchConfiguration.getWorkingCopy();
			workingCopy.setAttribute("GEMOC_LAUNCH_BREAK_START", false);
			workingCopy.setAttribute("GEMOC_BREAK_AT_STATE", stateIndexToBreakTo);
			workingCopy.launch("debug", null, false, true);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
