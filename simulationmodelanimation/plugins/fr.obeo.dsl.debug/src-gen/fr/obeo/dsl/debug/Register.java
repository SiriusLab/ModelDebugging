/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.debug;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Register</b></em>'. <!-- end-user-doc
 * --> <!-- begin-model-doc --> A {@link Variable} representing a register. <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link fr.obeo.dsl.debug.Register#getRegisterGroup <em>Register Group</em>}</li>
 * </ul>
 * </p>
 * 
 * @see fr.obeo.dsl.debug.DebugPackage#getRegister()
 * @model
 * @generated
 */
public interface Register extends Variable {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "Copyright (c) 2013 Obeo. All Rights Reserved.\n\nThis software and the attached documentation are the exclusive ownership\nof its authors and was conceded to the profit of Obeo SARL.\nThis software and the attached documentation are protected under the rights\nof intellectual ownership, including the section \"Titre II  Droits des auteurs (Articles L121-1 L123-12)\"\nBy installing this software, you acknowledge being aware of this rights and\naccept them, and as a consequence you must:\n- be in possession of a valid license of use conceded by Obeo only.\n- agree that you have read, understood, and will comply with the license terms and conditions.\n- agree not to do anything that could conflict with intellectual ownership owned by Obeo or its beneficiaries\nor the authors of this software\n\nShould you not agree with these terms, you must stop to use this software and give it back to its legitimate owner.";

	/**
	 * Returns the value of the '<em><b>Register Group</b></em>' container reference. It is bidirectional and
	 * its opposite is '{@link fr.obeo.dsl.debug.RegisterGroup#getRegisters <em>Registers</em>}'. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Register Group</em>' container reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Register Group</em>' container reference.
	 * @see #setRegisterGroup(RegisterGroup)
	 * @see fr.obeo.dsl.debug.DebugPackage#getRegister_RegisterGroup()
	 * @see fr.obeo.dsl.debug.RegisterGroup#getRegisters
	 * @model opposite="registers" transient="false"
	 * @generated
	 */
	RegisterGroup getRegisterGroup();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.debug.Register#getRegisterGroup <em>Register Group</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Register Group</em>' container reference.
	 * @see #getRegisterGroup()
	 * @generated
	 */
	void setRegisterGroup(RegisterGroup value);

} // Register
