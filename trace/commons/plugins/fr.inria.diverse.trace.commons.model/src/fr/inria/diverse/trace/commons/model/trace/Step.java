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
/**
 */
package fr.inria.diverse.trace.commons.model.trace;

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
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Step#getMseoccurrence <em>Mseoccurrence</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Step#getStartingState <em>Starting State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.Step#getEndingState <em>Ending State</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getStep()
 * @model abstract="true"
 * @generated
 */
public interface Step<StateSubType extends State<?, ?>> extends EObject {
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
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getStep_Mseoccurrence()
	 * @model containment="true"
	 * @generated
	 */
	MSEOccurrence getMseoccurrence();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.Step#getMseoccurrence <em>Mseoccurrence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mseoccurrence</em>' containment reference.
	 * @see #getMseoccurrence()
	 * @generated
	 */
	void setMseoccurrence(MSEOccurrence value);

	/**
	 * Returns the value of the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting State</em>' reference.
	 * @see #setStartingState(State)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getStep_StartingState()
	 * @model required="true" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	StateSubType getStartingState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.Step#getStartingState <em>Starting State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting State</em>' reference.
	 * @see #getStartingState()
	 * @generated
	 */
	void setStartingState(StateSubType value);

	/**
	 * Returns the value of the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending State</em>' reference.
	 * @see #setEndingState(State)
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getStep_EndingState()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	StateSubType getEndingState();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.trace.commons.model.trace.Step#getEndingState <em>Ending State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ending State</em>' reference.
	 * @see #getEndingState()
	 * @generated
	 */
	void setEndingState(StateSubType value);

} // Step
