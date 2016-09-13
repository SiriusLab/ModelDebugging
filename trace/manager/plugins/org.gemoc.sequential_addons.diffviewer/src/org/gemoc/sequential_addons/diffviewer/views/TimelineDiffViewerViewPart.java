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
package org.gemoc.sequential_addons.diffviewer.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.gemoc.sequential_addons.diffviewer.Activator;
import org.gemoc.sequential_addons.diffviewer.logic.Diff;
import org.gemoc.sequential_addons.diffviewer.logic.DiffComputer;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;

import fr.inria.diverse.trace.gemoc.api.ITraceExtractor.StateWrapper;
import fr.inria.diverse.trace.gemoc.traceaddon.AbstractTraceAddon;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

public class TimelineDiffViewerViewPart extends ViewPart {

	public static final String ID = "org.gemoc.sequential_addons.diffviewer.views.TimelineDiffViewerRenderer";

	private FXCanvas fxCanvas;
	
	private TimelineDiffViewerRenderer diffViewer;

	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		diffViewer = new TimelineDiffViewerRenderer();
		Scene scene = new Scene(diffViewer);
		fxCanvas.setScene(scene);
		parent.getShell().addListener(SWT.Resize, (e) -> {
			//TODO refresh the renderer
		});
		buildMenu(parent.getShell());
	}

	
	
	private void buildMenu(Shell shell) {
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
			public void engineSelectionChanged(IExecutionEngine engine) {
			}

			@Override
			public void run() {
				fileDialog.setText("Choose a first trace to load");
				String filePath1 = fileDialog.open();
				if (filePath1 != null && !filePath1.equals("")) {
					fileDialog.setText("Choose a second trace to load");
					String filePath2 = fileDialog.open();
					if (filePath2 != null && !filePath2.equals("")) {
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
											if (obj_cast.isAddonForTrace(traceResource1.getContents().get(0)) &&
													obj_cast.isAddonForTrace(traceResource2.getContents().get(0))) {
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
							newTraceAddon.load(traceResource1);
							final List<StateWrapper> wrappers1 = newTraceAddon.getTraceExtractor()
									.getStateWrappers(0, newTraceAddon.getTraceExtractor().getStatesTraceLength()-1);
							final List<EObject> states1 = wrappers1.stream().map(w -> w.state).collect(Collectors.toList());

							newTraceAddon.load(traceResource2);
							final List<StateWrapper> wrappers2 = newTraceAddon.getTraceExtractor()
									.getStateWrappers(0, newTraceAddon.getTraceExtractor().getStatesTraceLength()-1);
							final List<EObject> states2 = wrappers2.stream().map(w -> w.state).collect(Collectors.toList());
							
							final List<EObject> allStates = new ArrayList<>(states1);
							allStates.addAll(states2);
							
							List<Diff> diffs = DiffComputer.computeDiff(states1, states2, newTraceAddon.getTraceExtractor().computeStateEquivalenceClasses(allStates));
							diffViewer.fillStateLines(wrappers1, wrappers2, diffs);
						}
					}
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
}
