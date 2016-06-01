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
package fr.inria.diverse.tracemm.xmof.slicer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;

import xmoftracemmslicermodel.XMOFTraceMMSlicerModel;
import fr.inria.diverse.tracemm.xmof.slicer.internal.SlicerInput;
import fr.inria.diverse.tracemm.xmof.slicer.internal.SlicerInputDiscoverer;

public class XMOFTraceMMSlicer {

	private Resource traceMMResource;
	private Resource xmofModelResource;
	private IProfileFacade profileFacade;

	public XMOFTraceMMSlicer(URI traceMMURI, URI xmofModelURI,
			URI tracingProfileApplicationURI) {
		ResourceSet resourceSet = loadResources(traceMMURI, xmofModelURI);
		prepareProfileFacade(resourceSet, tracingProfileApplicationURI);
	}

	private ResourceSet loadResources(URI traceMMURI, URI xmofModelURI) {
		ResourceSet resourceSet = new ResourceSetImpl();
		traceMMResource = resourceSet.getResource(traceMMURI, true);
		xmofModelResource = resourceSet.getResource(xmofModelURI, true);
		return resourceSet;
	}

	private void prepareProfileFacade(ResourceSet resourceSet,
			URI tracingProfileApplicationURI) {
		profileFacade = new ProfileFacadeImpl();
		try {
			profileFacade.loadProfileApplication(tracingProfileApplicationURI,
					resourceSet);
		} catch (IOException e) {
			profileFacade = null;
		}
	}

	public boolean computeSlice() {
		boolean sliceCreated = false;
		if (canComputeSlice()) {
			List<EModelElement> slicerInput = computeInput();
			XMOFTraceMMSlicerModel slicer = new XMOFTraceMMSlicerModel(
					slicerInput, "ecore");
			slicer.slice();
			sliceCreated = true;
		}
		return sliceCreated;
	}

	private boolean canComputeSlice() {
		return traceMMResource != null
				&& traceMMResource.getContents().get(0) instanceof EPackage
				&& profileFacade != null;
	}

	private List<EModelElement> computeInput() {
		SlicerInputDiscoverer xmofSlicerInputDiscoverer = new SlicerInputDiscoverer(
				(EPackage) traceMMResource.getContents().get(0), profileFacade,
				xmofModelResource);
		SlicerInput xmofSlicerInput = xmofSlicerInputDiscoverer
				.computeXMOFSlicerInput();
		Collection<EModelElement> input = xmofSlicerInput.getAllElements();
		return input.parallelStream().collect(Collectors.toList());
	}
}
