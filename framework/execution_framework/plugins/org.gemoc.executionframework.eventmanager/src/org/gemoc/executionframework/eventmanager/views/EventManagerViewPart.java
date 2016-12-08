package org.gemoc.executionframework.eventmanager.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.gemoc.xdsmlframework.api.core.IExecutionEngine;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

public class EventManagerViewPart extends EngineSelectionDependentViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.gemoc.executionframework.eventmanager.views.EventManager";

	private FXCanvas fxCanvas;
	
	private EventManagerRenderer eventManagerRenderer;

	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		eventManagerRenderer = new EventManagerRenderer();
		Scene scene = new Scene(eventManagerRenderer);
		fxCanvas.setScene(scene);
		
		parent.getShell().addListener(SWT.Resize, (e) -> {
			
		});
	}

	public void setFocus() {
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine engine) {
		eventManagerRenderer.setExecutedModel(engine.getExecutionContext().getResourceModel());
		engine.getExecutionContext().getExecutionPlatform().addEngineAddon(eventManagerRenderer);
	}
}
