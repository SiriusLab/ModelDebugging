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
package org.gemoc.executionframework.engine.ui.debug.sirius.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.gemoc.executionframework.engine.ui.debug.breakpoint.GemocBreakpoint;
import org.gemoc.executionframework.engine.ui.launcher.AbstractGemocLauncher;

import fr.inria.diverse.melange.resource.MelangeResource;
import fr.obeo.dsl.debug.ide.DSLBreakpoint;
import fr.obeo.dsl.debug.ide.sirius.ui.DSLToggleBreakpointsUtils;
import fr.obeo.dsl.debug.ide.sirius.ui.action.AbstractToggleBreakpointAction;

/**
 * commons class for all Gemoc based models
 * 
 * @author dleroy
 *
 */
public class GemocToggleConditionalBreakpointAction extends AbstractToggleBreakpointAction {

	@Override
	protected String getModelIdentifier() {
		return AbstractGemocLauncher.MODEL_ID;
	}

	@Override
	protected DSLToggleBreakpointsUtils createToggleBreakpointsUtils() {
		return new DSLToggleBreakpointsUtils(getModelIdentifier()) {
			@Override
			protected void toggleBreakpoint(Object selected, EObject instruction) throws CoreException {
				DSLBreakpoint breakpoint = getBreakpoint(instruction);
				if (breakpoint != null) {
					breakpoint.delete();
				} else {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							try {
								DSLBreakpoint breakpoint = createBreakpoint(selected, instruction);
								DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(breakpoint);
							} catch (CoreException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
			
			@Override
			protected DSLBreakpoint createBreakpoint(Object selected, EObject instruction) throws CoreException {
				final Resource res = ((EObject) instruction).eResource();
				final ResourceSet resSet = res.getResourceSet();
				final Resource originalResource = resSet.getResources().stream().filter(r -> {
					return r instanceof MelangeResource;
				}).map(r -> (MelangeResource) r).findFirst().map(mr -> mr.getWrappedResource()).orElse(null);

				if (originalResource != null) {
					final String fragmentURI = res.getURIFragment(instruction);
					instruction = originalResource.getEObject(fragmentURI);
				}
				
				final GemocBreakpoint breakpoint = new GemocBreakpoint(identifier, instruction, true);
				
				Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				FileDialog dialog = new FileDialog(activeShell, SWT.OPEN);
				try {
					breakpoint.getMarker().setAttribute(GemocBreakpoint.PROPERTY, dialog.open());
				} catch (CoreException e) {
					e.printStackTrace();
				}
				
				return breakpoint;
			}
		};
	}
}
