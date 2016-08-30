package org.gemoc.sequential_addons.stategraph.views;

import java.util.Set;
import org.gemoc.sequential_addons.stategraph.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.gemoc.sequential_addons.stategraph.logic.StateGraph;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;

import fr.inria.diverse.trace.gemoc.api.IMultiDimensionalTraceAddon;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class StateGraphViewPart extends EngineSelectionDependentViewPart {

	public static final String ID = "org.gemoc.sequential_addons.multidimensional.timeline.views.timeline.StateGraphViewPart";

	private FXCanvas fxCanvas;

	private StateGraphRenderer renderer;

	private StateGraph stateGraph;

	private Pane root;

	@Override
	public void engineSelectionChanged(IExecutionEngine engine) {
		if (engine != null) {
			Set<IMultiDimensionalTraceAddon> traceAddons = engine.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
			if (!traceAddons.isEmpty()) {
				final IMultiDimensionalTraceAddon traceAddon = traceAddons.iterator().next();
				stateGraph = new StateGraph();
				stateGraph.setTraceExtractor(traceAddon.getTraceExtractor());
				stateGraph.setTraceExplorer(traceAddon.getTraceExplorer());
				renderer.setStateGraph(stateGraph);
			}
		}
	}

	private void setupRoot() {
		stateGraph = new StateGraph();
		renderer = new StateGraphRenderer(stateGraph);
		root.getChildren().add(renderer);
		renderer.minWidthProperty().bind(root.widthProperty());
		renderer.prefWidthProperty().bind(root.widthProperty());
		renderer.maxWidthProperty().bind(root.widthProperty());
		renderer.minHeightProperty().bind(root.heightProperty());
		renderer.prefHeightProperty().bind(root.heightProperty());
		renderer.maxHeightProperty().bind(root.heightProperty());
	}

	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		root = new Pane();
		final Scene scene = new Scene(root);
		fxCanvas.setScene(scene);
		setupRoot();
		buildMenu(parent.getShell());
	}

	private void buildMenu(Shell shell) {
		addActionToToolbar(new AbstractEngineAction(Action.AS_CHECK_BOX) {
			@Override
			protected void init() {
				super.init();
				setText("Toggle Cycle Coloration");
				setToolTipText("Toggle Cycle Coloration");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/cycle_coloration.png");
				setImageDescriptor(id);
				setEnabled(true);
			}

			@Override
			public void engineSelectionChanged(IExecutionEngine engine) {
			}

			@Override
			public void run() {
				renderer.setCycleColorationEnabled(isChecked());
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