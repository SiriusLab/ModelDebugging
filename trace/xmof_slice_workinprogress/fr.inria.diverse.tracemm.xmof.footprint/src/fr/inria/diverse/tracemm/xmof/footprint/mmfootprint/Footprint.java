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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Footprint</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getMetamodelEPackage
 * <em>Metamodel EPackage</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getAnalyzedObjectURI
 * <em>Analyzed Object URI</em>}</li>
 * <li>
 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getEModelElementAccesses
 * <em>EModel Element Accesses</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getFootprint()
 * @model
 * @generated
 */
public interface Footprint extends EObject {
	/**
	 * Returns the value of the '<em><b>Metamodel EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metamodel EPackage</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Metamodel EPackage</em>' reference.
	 * @see #setMetamodelEPackage(EPackage)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getFootprint_MetamodelEPackage()
	 * @model required="true"
	 * @generated
	 */
	EPackage getMetamodelEPackage();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getMetamodelEPackage
	 * <em>Metamodel EPackage</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Metamodel EPackage</em>' reference.
	 * @see #getMetamodelEPackage()
	 * @generated
	 */
	void setMetamodelEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Analyzed Object URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Analyzed Object URI</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Analyzed Object URI</em>' attribute.
	 * @see #setAnalyzedObjectURI(String)
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getFootprint_AnalyzedObjectURI()
	 * @model required="true"
	 * @generated
	 */
	String getAnalyzedObjectURI();

	/**
	 * Sets the value of the '
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.Footprint#getAnalyzedObjectURI
	 * <em>Analyzed Object URI</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Analyzed Object URI</em>' attribute.
	 * @see #getAnalyzedObjectURI()
	 * @generated
	 */
	void setAnalyzedObjectURI(String value);

	/**
	 * Returns the value of the '<em><b>EModel Element Accesses</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.EModelElementAccess}
	 * . <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EModel Element Accesses</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>EModel Element Accesses</em>' containment
	 *         reference list.
	 * @see fr.inria.diverse.tracemm.xmof.footprint.mmfootprint.MmfootprintPackage#getFootprint_EModelElementAccesses()
	 * @model containment="true"
	 * @generated
	 */
	EList<EModelElementAccess> getEModelElementAccesses();

} // Footprint
