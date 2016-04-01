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

import java.util.Set;
import java.util.WeakHashMap;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.gemoc.execution.sequential.javaengine.ui.debug.OmniscientGenericSequentialModelDebugger;
import org.gemoc.executionframework.ui.views.engine.IEngineSelectionListener;
import org.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.gemoc.sequential_addons.multidimensional.timeline.Activator;
import org.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.gemoc.xdsmlframework.api.core.IBasicExecutionEngine;
import org.gemoc.xdsmlframework.api.core.IDisposable;

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;
import fr.obeo.timeline.editpart.TimelineEditPartFactory;
import fr.obeo.timeline.view.AbstractTimelineView;
import fr.obeo.timeline.view.ITimelineProvider;

public class MultidimensionalTimeLineView extends AbstractTimelineView implements IEngineSelectionListener {

	public static final String ID = "org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView";

	public static final String FOLLOW_COMMAND_ID = "org.gemoc.executionframework.engine.io.views.timeline.Follow";

	/**
	 * The {@link AdapterFactory} created from the EMF registry.
	 */
	private final AdapterFactory adapterFactory = new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	private IContentProvider _contentProvider;
	private ILabelProvider _labelProvider;

	private IBasicExecutionEngine _currentEngine;
	
	private FXCanvas fxCanvas;

	private WeakHashMap<IBasicExecutionEngine, Integer> _positions = new WeakHashMap<IBasicExecutionEngine, Integer>();

	private FxTimeLineListener timelineWindowListener;

	public MultidimensionalTimeLineView() {
		_contentProvider = new AdapterFactoryContentProvider(adapterFactory);
		_labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		Activator.getDefault().setMultidimensionalTimeLineViewSupplier(() -> this);
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		startListeningToMotorSelectionChange();
	}

	@Override
	public void dispose() {
		disposeTimeLineProvider();
		stopListeningToMotorSelectionChange();
		Activator.getDefault().setMultidimensionalTimeLineViewSupplier(null);
		super.dispose();
		_contentProvider.dispose();
		_labelProvider.dispose();
	}

	private ITimelineProvider provider;
	
	@Override
	public void setTimelineProvider(ITimelineProvider timelineProvider, int start) {
		timelineWindowListener.setProvider(timelineProvider);
		if (this.provider != null) {
			this.provider.removeTimelineListener(timelineWindowListener);
		}
		this.provider = timelineProvider;
		if (timelineProvider != null) {
			this.provider.addTimelineListener(timelineWindowListener);
		}
	}
	
	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		timelineWindowListener = new FxTimeLineListener(this);
		if (provider != null) {
			provider.addTimelineListener(timelineWindowListener);
		}
		Scene scene = new Scene(timelineWindowListener);
		fxCanvas.setScene(scene);

		parent.getShell().addListener(SWT.Resize, (e) -> {
			timelineWindowListener.deepRefresh();
		});
		
		buildMenu(parent.getShell());
	}
	
	private void buildMenu(Shell shell) {
		addActionToToolbar(new AbstractEngineAction(Action.AS_CHECK_BOX) {
			@Override
			protected void init() {
				super.init();
				setText("Scroll Lock");
				setToolTipText("Toggles Scroll Lock");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/lock_co.gif");
				setImageDescriptor(id);
			}
			
			@Override
			public void run() {
				timelineWindowListener.setScrollLock(isChecked());
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
				
				dialog = new InputDialog(shell, "Jump to state", "Enter the desired state", "0",
						s -> {
							try {
								Integer.parseInt(s);
								return null;
							} catch (NumberFormatException e) {
								return "Not a valid state";
							}
						});
			}
			
			@Override
			public void run() {
				dialog.open();
				if (dialog.getReturnCode() == Window.OK) {
					int state = Integer.parseInt(dialog.getValue());
					for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
							.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
						traceAddon.jump(state);
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

	private void startListeningToMotorSelectionChange() {
		org.gemoc.executionframework.ui.Activator.getDefault().getEngineSelectionManager()
				.addEngineSelectionListener(this);
	}

	private void stopListeningToMotorSelectionChange() {
		org.gemoc.executionframework.ui.Activator.getDefault().getEngineSelectionManager()
				.removeEngineSelectionListener(this);
	}

	private ITimelineProvider _timelineProvider;

	public void configure(IBasicExecutionEngine engine) {
		if (_currentEngine != engine || _timelineProvider == null) {
			saveStartIndex();
			_currentEngine = engine;
			disposeTimeLineProvider();
			if (engine != null) {
				int start = getStartIndex(engine);

				// We first look for trace addons
				Set<IMultiDimensionalTraceAddon> traceAddons = engine
						.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
				if (!traceAddons.isEmpty()) {
					_timelineProvider = traceAddons.iterator().next().getTimeLineProvider();
					setTimelineProvider(_timelineProvider, start);
				}
			}
		}
	}

	private int getStartIndex(IBasicExecutionEngine engine) {
		int start = 0;
		if (_positions.containsKey(engine)) {
			start = _positions.get(engine);
		}
		return start;
	}

	private void saveStartIndex() {
		if (_currentEngine != null) {
			_positions.put(_currentEngine, getStart());
		}
	}

	private void disposeTimeLineProvider() {
		if (_timelineProvider != null) {
			((IDisposable) _timelineProvider).dispose();
			_timelineProvider = null;
			setTimelineProvider(_timelineProvider, 0);
		}
	}

	@Override
	public void engineSelectionChanged(IBasicExecutionEngine engine) {
		update(engine);
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
	public boolean hasDetailViewer() {
		return false;
	}

	@Override
	public String getFollowCommandID() {
		return FOLLOW_COMMAND_ID;
	}
	
	public IBasicExecutionEngine getCurrentEngine() {
		return _currentEngine;
	}

	public void handleStepValue(int traceIndex) {
		for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.stepValue(traceIndex-1);
		}
	}
	
	public void handleBackValue(int traceIndex) {
		for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.backValue(traceIndex-1);
		}
	}
	
	public boolean canStepValue(int traceIndex) {
		Set<OmniscientGenericSequentialModelDebugger> addons = _currentEngine.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class);
		if (!addons.isEmpty()) {
			return addons.iterator().next().canStepValue(traceIndex-1);
		}
		return false;
	}
	
	public boolean canBackValue(int traceIndex) {
		Set<OmniscientGenericSequentialModelDebugger> addons = _currentEngine.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class);
		if (!addons.isEmpty()) {
			return addons.iterator().next().canBackValue(traceIndex-1);
		}
		return false;
	}

	@Override
	protected TimelineEditPartFactory getTimelineEditPartFactory() {
		return new TimelineEditPartFactory(false);
	}

	public void update(IBasicExecutionEngine engine) {
		if (engine != null) {
			if (canDisplayTimeline(engine)) {
				configure(engine);
			} else {
				disposeTimeLineProvider();
			}
		}
	}
}
