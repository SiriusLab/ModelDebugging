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
import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ecorext.Rule#getCalledRules <em>Called Rules</em>}</li>
 *   <li>{@link ecorext.Rule#getOperation <em>Operation</em>}</li>
 *   <li>{@link ecorext.Rule#isStepRule <em>Step Rule</em>}</li>
 *   <li>{@link ecorext.Rule#getOverridenBy <em>Overriden By</em>}</li>
 *   <li>{@link ecorext.Rule#getOverrides <em>Overrides</em>}</li>
 *   <li>{@link ecorext.Rule#getContainingClass <em>Containing Class</em>}</li>
 *   <li>{@link ecorext.Rule#isAbstract <em>Abstract</em>}</li>
 * </ul>
 * </p>
 *
 * @see ecorext.EcorextPackage#getRule()
 * @model
 * @generated
 */
public interface Rule extends EObject {
	/**
	 * Returns the value of the '<em><b>Called Rules</b></em>' reference list.
	 * The list contents are of type {@link ecorext.Rule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Rules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Rules</em>' reference list.
	 * @see ecorext.EcorextPackage#getRule_CalledRules()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Rule> getCalledRules();

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' containment reference.
	 * @see #setOperation(EOperation)
	 * @see ecorext.EcorextPackage#getRule_Operation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EOperation getOperation();

	/**
	 * Sets the value of the '{@link ecorext.Rule#getOperation <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' containment reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(EOperation value);

	/**
	 * Returns the value of the '<em><b>Step Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step Rule</em>' attribute.
	 * @see #setStepRule(boolean)
	 * @see ecorext.EcorextPackage#getRule_StepRule()
	 * @model required="true"
	 * @generated
	 */
	boolean isStepRule();

	/**
	 * Sets the value of the '{@link ecorext.Rule#isStepRule <em>Step Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Step Rule</em>' attribute.
	 * @see #isStepRule()
	 * @generated
	 */
	void setStepRule(boolean value);

	/**
	 * Returns the value of the '<em><b>Overriden By</b></em>' reference list.
	 * The list contents are of type {@link ecorext.Rule}.
	 * It is bidirectional and its opposite is '{@link ecorext.Rule#getOverrides <em>Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overriden By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overriden By</em>' reference list.
	 * @see ecorext.EcorextPackage#getRule_OverridenBy()
	 * @see ecorext.Rule#getOverrides
	 * @model opposite="overrides" ordered="false"
	 * @generated
	 */
	EList<Rule> getOverridenBy();

	/**
	 * Returns the value of the '<em><b>Overrides</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ecorext.Rule#getOverridenBy <em>Overriden By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overrides</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overrides</em>' reference.
	 * @see #setOverrides(Rule)
	 * @see ecorext.EcorextPackage#getRule_Overrides()
	 * @see ecorext.Rule#getOverridenBy
	 * @model opposite="overridenBy"
	 * @generated
	 */
	Rule getOverrides();

	/**
	 * Sets the value of the '{@link ecorext.Rule#getOverrides <em>Overrides</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overrides</em>' reference.
	 * @see #getOverrides()
	 * @generated
	 */
	void setOverrides(Rule value);

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' reference.
	 * @see #setContainingClass(EClass)
	 * @see ecorext.EcorextPackage#getRule_ContainingClass()
	 * @model
	 * @generated
	 */
	EClass getContainingClass();

	/**
	 * Sets the value of the '{@link ecorext.Rule#getContainingClass <em>Containing Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see ecorext.EcorextPackage#getRule_Abstract()
	 * @model required="true"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link ecorext.Rule#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

} // Rule
