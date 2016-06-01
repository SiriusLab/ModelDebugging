/**
 *  Copyright (c) 2016 Inria and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Inria - initial API and implementation
 * 
 */
package org.gemoc.executionframework.engine.mse;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.Step#getMseoccurrence <em>Mseoccurrence</em>}</li>
 * </ul>
 *
 * @see org.gemoc.executionframework.engine.mse.MsePackage#getStep()
 * @model abstract="true"
 * @generated
 */
public interface Step extends EObject {
	/**
	 * Returns the value of the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mseoccurrence</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mseoccurrence</em>' containment reference.
	 * @see #setMseoccurrence(MSEOccurrence)
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#getStep_Mseoccurrence()
	 * @model containment="true"
	 * @generated
	 */
	MSEOccurrence getMseoccurrence();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.engine.mse.Step#getMseoccurrence <em>Mseoccurrence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mseoccurrence</em>' containment reference.
	 * @see #getMseoccurrence()
	 * @generated
	 */
	void setMseoccurrence(MSEOccurrence value);

} // Step
