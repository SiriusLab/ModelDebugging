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
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.Trace#getRootStep <em>Root Step</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.Trace#getLaunchconfiguration <em>Launchconfiguration</em>}</li>
 * </ul>
 *
 * @see org.gemoc.executionframework.engine.mse.MsePackage#getTrace()
 * @model abstract="true"
 * @generated
 */
public interface Trace<StepSubType> extends EObject {
	/**
	 * Returns the value of the '<em><b>Root Step</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Step</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Step</em>' containment reference.
	 * @see #setRootStep(Object)
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#getTrace_RootStep()
	 * @model kind="reference" containment="true" required="true"
	 * @generated
	 */
	StepSubType getRootStep();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.engine.mse.Trace#getRootStep <em>Root Step</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Step</em>' containment reference.
	 * @see #getRootStep()
	 * @generated
	 */
	void setRootStep(StepSubType value);

	/**
	 * Returns the value of the '<em><b>Launchconfiguration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Launchconfiguration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Launchconfiguration</em>' containment reference.
	 * @see #setLaunchconfiguration(LaunchConfiguration)
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#getTrace_Launchconfiguration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	LaunchConfiguration getLaunchconfiguration();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.engine.mse.Trace#getLaunchconfiguration <em>Launchconfiguration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Launchconfiguration</em>' containment reference.
	 * @see #getLaunchconfiguration()
	 * @generated
	 */
	void setLaunchconfiguration(LaunchConfiguration value);

} // Trace
