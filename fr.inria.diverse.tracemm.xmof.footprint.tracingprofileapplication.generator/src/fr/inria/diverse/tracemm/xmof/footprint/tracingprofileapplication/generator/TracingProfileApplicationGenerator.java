package fr.inria.diverse.tracemm.xmof.footprint.tracingprofileapplication.generator;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.impl.ProfileFacadeImpl;
import org.modelversioning.emfprofile.registry.IProfileRegistry;

import fr.inria.diverse.trace.commons.ExecutionMetamodelTraceability;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess;
import fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint;

public class TracingProfileApplicationGenerator {

	private static final String TRACING_PROFILE = "http://xmof/tracingprofile/1.0";
	private static final String STEREOTYPE_TRACED_ECLASS = "TracedEClass";
	private static final String STEREOTYPE_TRACED_ESTRUCTURALFEATURE = "TracedEStructuralFeature";

	private ResourceSet resourceSet;
	private Resource xmofModelResource;

	private Footprint footprint;

	private IProfileFacade profileFacade;
	private Stereotype stereotypeTracedEStructuralFeature;
	private Stereotype stereotypeTracedEClass;

	public TracingProfileApplicationGenerator(URI footprintURI, URI xmofModelURI) {
		loadResources(footprintURI, xmofModelURI);
	}

	private void loadResources(URI footprintURI, URI xmofModelURI) {
		resourceSet = new ResourceSetImpl();

		Resource footprintResource = resourceSet.getResource(footprintURI, true);
		footprint = (Footprint) footprintResource.getContents().get(0);

		xmofModelResource = resourceSet.getResource(xmofModelURI, true);
	}

	public void generateProfileApplication(URI profileApplicationURI) throws IOException {
		profileFacade = prepareProfileFacade(profileApplicationURI);
		footprint.getEModelElementAccesses().stream()
				.filter(elementAccess -> ExecutionMetamodelTraceability
						.getTraceabilityAnnotation(elementAccess.getEModelElement()) != null)
				.forEach(elementAccess -> applyStereotype(elementAccess));
		profileFacade.save();
	}

	private void applyStereotype(EModelElementAccess elementAccess) {
		EModelElement accessedTraceElement = elementAccess.getEModelElement();
		EObject executionMetamodelElement = getExecutionMetamodelElement(accessedTraceElement);
		Stereotype stereotypeToApply = getTracingprofileStereotpye(executionMetamodelElement);
		applyStereotype(stereotypeToApply, executionMetamodelElement);
	}

	private EObject getExecutionMetamodelElement(EModelElement traceElement) {
		String executionMetamodelElementURIFragment = ExecutionMetamodelTraceability
				.getTraceabilityAnnotationValue(traceElement);
		EObject executionMetamodelElement = xmofModelResource.getEObject(executionMetamodelElementURIFragment);
		return executionMetamodelElement;
	}

	private Stereotype getTracingprofileStereotpye(EObject executionMetamodelElement) {
		Stereotype tracingprofileStereotype = null;
		if (executionMetamodelElement instanceof EClass) {
			tracingprofileStereotype = stereotypeTracedEClass;
		} else if (executionMetamodelElement instanceof EStructuralFeature) {
			tracingprofileStereotype = stereotypeTracedEStructuralFeature;
		}
		return tracingprofileStereotype;
	}

	private void applyStereotype(Stereotype stereotype, EObject eObject) {
		if (stereotype != null && eObject != null) {
			if (profileFacade.isApplicable(stereotype, eObject)) {
				profileFacade.apply(stereotype, eObject);
			}
		}
	}

	private IProfileFacade prepareProfileFacade(URI profileApplicationURI) throws IOException {
		Profile tracingProfile = IProfileRegistry.INSTANCE.getRegisteredProfiles().stream()
				.filter(p -> p.getNsURI().equals(TRACING_PROFILE)).findFirst().orElse(null);
		stereotypeTracedEClass = tracingProfile.getStereotype(STEREOTYPE_TRACED_ECLASS);
		stereotypeTracedEStructuralFeature = tracingProfile.getStereotype(STEREOTYPE_TRACED_ESTRUCTURALFEATURE);

		IProfileFacade facade = new ProfileFacadeImpl();
		facade.loadProfileApplication(profileApplicationURI, resourceSet);
		facade.getStereotypeApplications().stream().forEach(sa -> facade.removeStereotypeApplication(sa));
		facade.makeApplicable(tracingProfile);
		facade.loadProfile(tracingProfile);
		return facade;
	}

}
