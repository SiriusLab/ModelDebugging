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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

public class TraceModelStructureDiscoverer {

	private static final String TRACED_OBJECT_PACKAGE_NAME = "Traced";
	private static final String TRACED_OBJECTS_BASECLASS_NAME = "TracedObjects";
	private static final String TRACE_BASECLASS_NAME = "Trace";
	private static final String STATE_BASECLASS_NAME = "State";

	private EPackage traceMMRootEPackage;

	public TraceModelStructureDiscoverer(EPackage traceMMRootEPackage) {
		this.traceMMRootEPackage = traceMMRootEPackage;
	}

	public TraceModelStructure computeTraceModelStructure() {
		TraceModelStructure traceModelStructure = new TraceModelStructure();

		EPackage tracedObjectRootPackage = discoverBasePackage(TRACED_OBJECT_PACKAGE_NAME);
		Set<EClass> tracedObjectClasses = discoverTracedObjectClasses(tracedObjectRootPackage);
		tracedObjectClasses.stream().forEach(
				tracedObjectClass -> traceModelStructure
						.addTracedObjectClass(tracedObjectClass));

		traceModelStructure
				.setBaseClassTracedObjects((EClass) tracedObjectRootPackage
						.getEClassifier(TRACED_OBJECTS_BASECLASS_NAME));
		traceModelStructure.setBaseClassTrace((EClass) traceMMRootEPackage
				.getEClassifier(TRACE_BASECLASS_NAME));
		traceModelStructure.setBaseClassState((EClass) traceMMRootEPackage
				.getEClassifier(STATE_BASECLASS_NAME));
		return traceModelStructure;
	}

	private Set<EClass> discoverTracedObjectClasses(
			EPackage tracedObjectRootPackage) {
		Set<EPackage> tracedObjectPackages = getAllSubpackages(tracedObjectRootPackage);
		Stream<EClassifier> tracedObjectClassifiers = tracedObjectPackages
				.stream().flatMap(
						tracedObjectPackage -> tracedObjectPackage
								.getEClassifiers().stream());
		Set<EClass> tracedObjectClasses = tracedObjectClassifiers
				.filter(eClassifier -> eClassifier instanceof EClass)
				.map(eClassifier -> (EClass) eClassifier)
				.collect(Collectors.toSet());
		return tracedObjectClasses;
	}

	private Set<EPackage> getAllSubpackages(EPackage ePackage) {
		Set<EPackage> allSubpackages = new HashSet<EPackage>();
		allSubpackages.addAll(ePackage.getESubpackages());
		allSubpackages.addAll(allSubpackages.stream()
				.flatMap(subpackage -> getAllSubpackages(subpackage).stream())
				.collect(Collectors.toSet()));
		return allSubpackages;
	}

	private EPackage discoverBasePackage(String basePackageName) {
		if (basePackageName == null) {
			return null;
		}
		EPackage basePackage = traceMMRootEPackage.getESubpackages().stream()
				.filter(ePackage -> basePackageName.equals(ePackage.getName()))
				.findFirst().orElse(null);
		return basePackage;
	}
}
