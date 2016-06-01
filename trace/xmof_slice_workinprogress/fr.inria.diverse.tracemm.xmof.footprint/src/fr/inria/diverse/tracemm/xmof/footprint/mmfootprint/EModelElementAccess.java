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
package fr.inria.diverse.tracemm.xmof.footprint.mmfootprint;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>EModel Element Access</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getEModelElement
 * <em>EModel Element</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getAccessLocations
 * <em>Access Locations</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getEModelElementAccess()
 * @model
 * @generated
 */
public interface EModelElementAccess extends EObject {
	/**
	 * Returns the value of the '<em><b>EModel Element</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EModel Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>EModel Element</em>' reference.
	 * @see #setEModelElement(EModelElement)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getEModelElementAccess_EModelElement()
	 * @model required="true"
	 * @generated
	 */
	EModelElement getEModelElement();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess#getEModelElement
	 * <em>EModel Element</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>EModel Element</em>' reference.
	 * @see #getEModelElement()
	 * @generated
	 */
	void setEModelElement(EModelElement value);

	/**
	 * Returns the value of the '<em><b>Access Locations</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Location}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Locations</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Access Locations</em>' containment
	 *         reference list.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getEModelElementAccess_AccessLocations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Location> getAccessLocations();

} // EModelElementAccess
