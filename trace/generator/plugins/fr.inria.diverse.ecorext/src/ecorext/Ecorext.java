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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ecorext</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ecorext.Ecorext#getNewPackages <em>New Packages</em>}</li>
 *   <li>{@link ecorext.Ecorext#getClassesExtensions <em>Classes Extensions</em>}</li>
 *   <li>{@link ecorext.Ecorext#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see ecorext.EcorextPackage#getEcorext()
 * @model
 * @generated
 */
public interface Ecorext extends EObject {
	/**
	 * Returns the value of the '<em><b>New Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Packages</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getEcorext_NewPackages()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<EPackage> getNewPackages();

	/**
	 * Returns the value of the '<em><b>Classes Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link ecorext.ClassExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes Extensions</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getEcorext_ClassesExtensions()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ClassExtension> getClassesExtensions();

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link ecorext.Rule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see ecorext.EcorextPackage#getEcorext_Rules()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Rule> getRules();

} // Ecorext
