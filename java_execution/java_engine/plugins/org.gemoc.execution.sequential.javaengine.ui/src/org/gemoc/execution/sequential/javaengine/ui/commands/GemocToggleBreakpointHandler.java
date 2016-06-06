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
package org.gemoc.execution.sequential.javaengine.ui.commands;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.gemoc.execution.sequential.javaengine.ui.launcher.Launcher;
import org.gemoc.executionframework.engine.ui.debug.breakpoint.GemocBreakpoint;

import fr.inria.diverse.trace.commons.model.trace.MSE;
import fr.inria.diverse.trace.commons.model.trace.MSEOccurrence;
import fr.obeo.dsl.debug.ide.DSLBreakpoint;
import fr.obeo.dsl.debug.ide.sirius.ui.DSLToggleBreakpointsUtils;

public class GemocToggleBreakpointHandler extends AbstractHandler {

	/**
	 * The {@link DSLToggleBreakpointsUtils}.
	 */
	protected final DSLToggleBreakpointsUtils breakpointUtils;
	 org.eclipse.ui.ide.IGotoMarker f;
	/**
	 * Constructor.
	 */
	public GemocToggleBreakpointHandler() {
		breakpointUtils = new DSLToggleBreakpointsUtils(Launcher.MODEL_ID) {
			
			@Override
			protected EObject getInstruction(Object selected) {
				final EObject res;
				
				if (selected instanceof MSE) 
				{
					res = ((MSE) selected);
				} 
				else if (selected instanceof MSEOccurrence)
				{
					res = ((MSEOccurrence) selected).getMse();				
				} 
				else 
				{
					res = super.getInstruction(selected);
				}

				return res;
			}
			
			@Override
			protected DSLBreakpoint createBreakpoint(Object selected,
					EObject instruction) throws CoreException {
				return new GemocBreakpoint(identifier, instruction, true);
			}
			
		};
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil
				.getCurrentSelectionChecked(event);
		try {
			breakpointUtils.toggleBreakpoints(selection);
		} catch (CoreException e) {
			throw new ExecutionException("Error while toggling breakpoint.", e);
		}

		return null;
	}
	
	@Override
	public boolean isEnabled() {
		final boolean res;
		
		ISelectionService service = (ISelectionService) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ISelectionService.class);
		if (service != null) {
			final ISelection selection = service.getSelection();
			if (selection instanceof IStructuredSelection) {
				boolean allValidMSE = true;
				final Iterator<?> it = ((IStructuredSelection) selection).iterator();
				while (allValidMSE && it.hasNext()) {
					Object current = it.next();
					allValidMSE = current instanceof MSE /*&& ((MSE) current).getAction() != null*/;
				}
				res = allValidMSE;
			} else {
				res = false;
			}
		} else {
			res = false;
		}
		
		return res;
	}
	
}
