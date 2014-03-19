/**
 * Copyright (c) 2012-2016 GEMOC consortium.
 * 
 * http://www.gemoc.org
 * 
 * Contributors:
 *   Stephen Creff - ENSTA Bretagne [stephen.creff@ensta-bretagne.fr]
 *   
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * $Id$
 */
package org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension;

import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.BooleanExpression;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.RealExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Real Inf Equal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.RealInfEqual#getLeftValue <em>Left Value</em>}</li>
 *   <li>{@link org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.RealInfEqual#getRightValue <em>Right Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.EditionExtensionPackage#getRealInfEqual()
 * @model
 * @generated
 */
public interface RealInfEqual extends BooleanExpression {
	/**
	 * Returns the value of the '<em><b>Left Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Value</em>' containment reference.
	 * @see #setLeftValue(RealExpression)
	 * @see org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.EditionExtensionPackage#getRealInfEqual_LeftValue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RealExpression getLeftValue();

	/**
	 * Sets the value of the '{@link org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.RealInfEqual#getLeftValue <em>Left Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Value</em>' containment reference.
	 * @see #getLeftValue()
	 * @generated
	 */
	void setLeftValue(RealExpression value);

	/**
	 * Returns the value of the '<em><b>Right Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Value</em>' containment reference.
	 * @see #setRightValue(RealExpression)
	 * @see org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.EditionExtensionPackage#getRealInfEqual_RightValue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	RealExpression getRightValue();

	/**
	 * Sets the value of the '{@link org.gemoc.mocc.fsmkernel.model.FSMModel.editionextension.RealInfEqual#getRightValue <em>Right Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Value</em>' containment reference.
	 * @see #getRightValue()
	 * @generated
	 */
	void setRightValue(RealExpression value);

} // RealInfEqual
