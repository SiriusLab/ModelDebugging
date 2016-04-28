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
package ecorext;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ecorext.ClassExtension#getExtendedExistingClass <em>Extended Existing Class</em>}</li>
 *   <li>{@link ecorext.ClassExtension#getNewProperties <em>New Properties</em>}</li>
 * </ul>
 *
 * @see ecorext.EcorextPackage#getClassExtension()
 * @model
 * @generated
 */
public interface ClassExtension extends EObject {
	/**
	 * Returns the value of the '<em><b>Extended Existing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Existing Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Existing Class</em>' reference.
	 * @see #setExtendedExistingClass(EClass)
	 * @see ecorext.EcorextPackage#getClassExtension_ExtendedExistingClass()
	 * @model
	 * @generated
	 */
	EClass getExtendedExistingClass();

	/**
	 * Sets the value of the '{@link ecorext.ClassExtension#getExtendedExistingClass <em>Extended Existing Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Existing Class</em>' reference.
	 * @see #getExtendedExistingClass()
	 * @generated
	 */
	void setExtendedExistingClass(EClass value);

	/**
	 * Returns the value of the '<em><b>New Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EStructuralFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Properties</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getClassExtension_NewProperties()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<EStructuralFeature> getNewProperties();

} // ClassExtension
