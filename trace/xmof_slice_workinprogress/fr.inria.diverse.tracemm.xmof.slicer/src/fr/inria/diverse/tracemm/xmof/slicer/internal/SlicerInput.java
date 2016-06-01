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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SlicerInput {

	private Set<EClass> tracedObjectClasses = new HashSet<EClass>();
	private Set<EClass> valueClasses = new HashSet<EClass>();
	private Set<EStructuralFeature> valueFeatures = new HashSet<EStructuralFeature>();
	private Set<EReference> valueTraceContainments = new HashSet<EReference>();
	private Set<EClass> baseClasses = new HashSet<EClass>();
	private Set<EStructuralFeature> baseClassFeatures = new HashSet<EStructuralFeature>();

	public void addTracedObjectClasses(
			final Collection<EClass> tracedObjectClasses) {
		this.tracedObjectClasses.addAll(tracedObjectClasses);
	}

	public void addValueClasses(final Collection<EClass> valueClasses) {
		this.valueClasses.addAll(valueClasses);
	}

	public void addValueFeatures(
			final Collection<EStructuralFeature> valueFeatures) {
		this.valueFeatures.addAll(valueFeatures);
	}

	public void addValueTraceContainments(
			final Collection<EReference> valueTraceContainments) {
		this.valueTraceContainments.addAll(valueTraceContainments);
	}

	public void addBaseClasses(final Collection<EClass> baseClasses) {
		this.baseClasses.addAll(baseClasses);
	}

	public void addBaseClassFeatures(
			final Collection<EStructuralFeature> baseClassFeatures) {
		this.baseClassFeatures.addAll(baseClassFeatures);
	}

	public Collection<EClass> getTracedObjectClasses() {
		return Collections.unmodifiableSet(tracedObjectClasses);
	}

	public Collection<EClass> getValueClasses() {
		return Collections.unmodifiableSet(valueClasses);
	}

	public Collection<EStructuralFeature> getValueFeatures() {
		return Collections.unmodifiableSet(valueFeatures);
	}

	public Collection<EReference> getValueTraceContainments() {
		return Collections.unmodifiableSet(valueTraceContainments);
	}

	public Collection<EClass> getBaseClasses() {
		return Collections.unmodifiableSet(baseClasses);
	}

	public Collection<EStructuralFeature> getBaseClassFeatures() {
		return Collections.unmodifiableCollection(baseClassFeatures);
	}

	public Collection<EModelElement> getAllElements() {
		Set<EModelElement> allElements = new HashSet<EModelElement>();
		allElements.addAll(tracedObjectClasses);
		allElements.addAll(valueClasses);
		allElements.addAll(valueFeatures);
		allElements.addAll(valueTraceContainments);
		allElements.addAll(baseClasses);
		allElements.addAll(baseClassFeatures);
		return Collections.unmodifiableSet(allElements);
	}
}
