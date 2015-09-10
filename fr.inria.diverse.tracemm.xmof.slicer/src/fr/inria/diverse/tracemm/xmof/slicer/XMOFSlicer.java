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

import xmofslicermodel.XMOFSlicerModel;
import fr.inria.diverse.tracemm.xmof.slicer.internal.XMOFSlicerInput;
import fr.inria.diverse.tracemm.xmof.slicer.internal.XMOFSlicerInputDiscoverer;

public class XMOFSlicer {

	private Resource traceMMResource;
	private Resource xmofModelResource;
	private IProfileFacade profileFacade;

	public XMOFSlicer(URI traceMMURI, URI xmofModelURI,
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
			XMOFSlicerModel slicer = new XMOFSlicerModel(slicerInput, "ecore");
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
		XMOFSlicerInputDiscoverer xmofSlicerInputDiscoverer = new XMOFSlicerInputDiscoverer(
				(EPackage) traceMMResource.getContents().get(0), profileFacade,
				xmofModelResource);
		XMOFSlicerInput xmofSlicerInput = xmofSlicerInputDiscoverer
				.computeXMOFSlicerInput();
		Collection<EModelElement> input = xmofSlicerInput.getAllElements();
		return input.parallelStream().collect(Collectors.toList());
	}
}
