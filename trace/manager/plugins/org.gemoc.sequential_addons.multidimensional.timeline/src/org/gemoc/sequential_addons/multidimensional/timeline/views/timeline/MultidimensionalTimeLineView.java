package org.gemoc.sequential_addons.multidimensional.timeline.views.timeline;

import java.util.Set;
import java.util.WeakHashMap;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.gemoc.execution.sequential.javaengine.ui.debug.OmniscientGenericSequentialModelDebugger;
import org.gemoc.executionframework.ui.views.engine.IEngineSelectionListener;
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

	public static final String FOLLOW_COMMAND_ID = "org.gemoc.execution.engine.io.views.timeline.Follow";

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
		removeDoubleClickListener();
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
		ScrollPane scrollPane = new ScrollPane();
		timelineWindowListener = new FxTimeLineListener(this, scrollPane);
		scrollPane.setContent(timelineWindowListener);
		if (provider != null) {
			provider.addTimelineListener(timelineWindowListener);
		}
		scrollPane.setBackground(Background.EMPTY);
		scrollPane.setBorder(Border.EMPTY);
		scrollPane.setPannable(true);
		Scene scene = new Scene(scrollPane);
		fxCanvas.setScene(scene);
		
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
	private MouseListener _mouseListener = null;

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

	private void removeDoubleClickListener() {
		if (_mouseListener != null && getTimelineViewer() != null && getTimelineViewer().getControl() != null) {
			getTimelineViewer().getControl().removeMouseListener(_mouseListener);
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
	
	public int getCurrentTrace() {
		for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			return traceAddon.getCurrentTrace();
		}
		return -1;
	}

	public void handleStepValue() {
		for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.stepValue();
		}
		fxCanvas.redraw();
	}
	
	public void handleBackValue() {
		for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.backValue();
		}
		fxCanvas.redraw();
	}
	
	public void handleTraceSelected(int trace) {
		for (OmniscientGenericSequentialModelDebugger traceAddon : _currentEngine
				.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class)) {
			traceAddon.setCurrentTrace(trace);
		}
		fxCanvas.redraw();
	}
	
	public boolean canStepValue() {
		Set<OmniscientGenericSequentialModelDebugger> addons = _currentEngine.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class);
		if (!addons.isEmpty()) {
			return addons.iterator().next().canStepValue();
		}
		return false;
	}
	
	public boolean canBackValue() {
		Set<OmniscientGenericSequentialModelDebugger> addons = _currentEngine.getAddonsTypedBy(OmniscientGenericSequentialModelDebugger.class);
		if (!addons.isEmpty()) {
			return addons.iterator().next().canBackValue();
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
