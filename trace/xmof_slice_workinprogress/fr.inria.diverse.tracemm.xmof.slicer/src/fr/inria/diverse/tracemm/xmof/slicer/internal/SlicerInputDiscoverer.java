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
package fr.inria.diverse.tracemm.xmof.slicer.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelversioning.emfprofile.IProfileFacade;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import fr.inria.diverse.trace.commons.ExecutionMetamodelTraceability;
import fr.inria.diverse.tracemm.xmof.tracingprofile.TracingProfile;

public class SlicerInputDiscoverer {

	private EPackage traceMMRootEPackage;
	private IProfileFacade tracingProfileFacade;
	private Resource xmofModelResource;

	private TraceModelStructure traceModelStructure;

	public SlicerInputDiscoverer(EPackage traceMMRootPackage,
			IProfileFacade tracingProfileFacade, Resource xmofModelResource) {
		this.traceMMRootEPackage = traceMMRootPackage;
		this.tracingProfileFacade = tracingProfileFacade;
		this.xmofModelResource = xmofModelResource;

		this.traceModelStructure = new TraceModelStructureDiscoverer(
				traceMMRootEPackage).computeTraceModelStructure();
	}

	public SlicerInput computeXMOFSlicerInput() {
		SlicerInput slicerInput = new SlicerInput();

		addTracedObjectClassesForTracingAnnotations(slicerInput);
		addValueClassesForTracingAnnotations(slicerInput);
		addValueClassFeaturesForTracingAnnotations(slicerInput);

		addValueTraceContainments(slicerInput);
		addTracedObjectClassesOwningValueTraceContainments(slicerInput);

		addTracedObjectClassesInInheritanceHierarchy(slicerInput);

		addTraceBaseClasses(slicerInput);
		addReferencesAmongTraceBaseClass(slicerInput);
		addTraceBaseClassContainmentsToTracedObjectClasses(slicerInput);
		addTraceBaseClassContainmentsToValueClasses(slicerInput);

		return slicerInput;
	}

	private void addTracedObjectClassesForTracingAnnotations(
			SlicerInput slicerInput) {
		Set<EClass> tracedObjectClasses = traceModelStructure
				.getTracedObjectClasses()
				.stream()
				.filter(tracedObjectClass -> isTraceMMElementForTracedExecutionMMElement(tracedObjectClass))
				.collect(Collectors.toSet());
		slicerInput.addTracedObjectClasses(tracedObjectClasses);
	}

	private void addValueClassesForTracingAnnotations(
			SlicerInput slicerInput) {
		Set<EClass> valueClasses = traceModelStructure
				.getValueClasses()
				.stream()
				.filter(tracedValueClass -> isTraceMMElementForTracedExecutionMMElement(tracedValueClass))
				.collect(Collectors.toSet());
		slicerInput.addValueClasses(valueClasses);
	}

	private void addValueClassFeaturesForTracingAnnotations(
			SlicerInput slicerInput) {
		Set<EStructuralFeature> valueClassFeatures = slicerInput
				.getValueClasses()
				.stream()
				.flatMap(
						valueClass -> valueClass.getEStructuralFeatures()
								.stream())
				.filter(valueFeature -> isTraceMMElementForTracedExecutionMMElement(valueFeature))
				.collect(Collectors.toSet());
		slicerInput.addValueFeatures(valueClassFeatures);
	}

	private void addValueTraceContainments(SlicerInput slicerInput) {
		Set<EReference> valueTraceContainments = slicerInput
				.getValueClasses()
				.stream()
				.map(valueClass -> traceModelStructure
						.getValueTraceContainment(valueClass))
				.collect(Collectors.toSet());
		slicerInput.addValueTraceContainments(valueTraceContainments);
	}

	private void addTracedObjectClassesOwningValueTraceContainments(
			SlicerInput slicerInput) {
		Set<EClass> tracedObjectClasses = slicerInput
				.getValueTraceContainments()
				.stream()
				.map(valueTraceContainment -> (EClass) valueTraceContainment
						.eContainer()).collect(Collectors.toSet());
		slicerInput.addTracedObjectClasses(tracedObjectClasses);
	}

	private void addTracedObjectClassesInInheritanceHierarchy(
			SlicerInput slicerInput) {
		Set<EClass> tracedObjectClasses = slicerInput
				.getTracedObjectClasses()
				.stream()
				.flatMap(
						tracedObjectClass -> traceModelStructure
								.getAllSuperclassesAndSubclassesOfTracedObjectClass(
										tracedObjectClass).stream())
				.collect(Collectors.toSet());
		slicerInput.addTracedObjectClasses(tracedObjectClasses);
	}

	private void addTraceBaseClasses(SlicerInput slicerInput) {
		List<EClass> baseClasses = Arrays.asList(
				traceModelStructure.getBaseClassTrace(),
				traceModelStructure.getBaseClassState(),
				traceModelStructure.getBaseClassTracedObjects());
		slicerInput.addBaseClasses(baseClasses);

	}

	private void addReferencesAmongTraceBaseClass(SlicerInput slicerInput) {
		Collection<EClass> baseClasses = slicerInput.getBaseClasses();
		slicerInput.addBaseClassFeatures(baseClasses
				.stream()
				.flatMap(baseClass -> baseClass.getEReferences().stream())
				.filter(baseClassReference -> baseClasses
						.contains(baseClassReference.getEType()))
				.collect(Collectors.toSet()));
	}

	private void addTraceBaseClassContainmentsToTracedObjectClasses(
			SlicerInput slicerInput) {
		slicerInput.addBaseClassFeatures(traceModelStructure
				.getBaseClassTracedObjects()
				.getEReferences()
				.stream()
				.filter(eReference -> slicerInput.getTracedObjectClasses()
						.contains(eReference.getEType()))
				.collect(Collectors.toSet()));
	}

	private void addTraceBaseClassContainmentsToValueClasses(
			SlicerInput slicerInput) {
		slicerInput.addBaseClassFeatures(traceModelStructure
				.getBaseClassState()
				.getEReferences()
				.stream()
				.filter(eReference -> slicerInput.getValueClasses().contains(
						eReference.getEType())).collect(Collectors.toSet()));
	}

	private boolean isTraceMMElementForTracedExecutionMMElement(
			EObject traceMMObject) {
		boolean isTraceMMElementForTracedExecutionMMElement = false;
		EModelElement tracedExecutionMMElement = getTracedExecutionMMElement(traceMMObject);
		if (tracedExecutionMMElement != null) {
			if (tracingProfileFacade
					.getAppliedStereotypes(tracedExecutionMMElement)
					.stream()
					.map(StereotypeApplication::getStereotype)
					.map(Stereotype::getName)
					.filter(n -> n
							.equals(TracingProfile.STEREOTYPE_TRACED_ECLASS)
							|| n.equals(TracingProfile.STEREOTYPE_TRACED_ESTRUCTURALFEATURE))
					.count() > 0)
				isTraceMMElementForTracedExecutionMMElement = true;

		}
		return isTraceMMElementForTracedExecutionMMElement;
	}

	private EModelElement getTracedExecutionMMElement(EObject traceMMObject) {
		EModelElement tracedExecutionMMElement = null;
		if (traceMMObject instanceof EModelElement) {
			EModelElement traceMMElement = (EModelElement) traceMMObject;
			String traceabilityAnnotationValue = ExecutionMetamodelTraceability
					.getTraceabilityAnnotationValue(traceMMElement);
			if (traceabilityAnnotationValue != null) {
				EObject executionMMElement = xmofModelResource
						.getEObject(traceabilityAnnotationValue);
				if (executionMMElement instanceof EModelElement) {
					tracedExecutionMMElement = (EModelElement) executionMMElement;
				}
			}
		}
		return tracedExecutionMMElement;
	}

}
