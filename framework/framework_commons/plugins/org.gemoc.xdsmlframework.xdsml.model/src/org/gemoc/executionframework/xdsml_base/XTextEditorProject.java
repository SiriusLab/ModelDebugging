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
package org.gemoc.executionframework.xdsml_base;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XText Editor Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.XTextEditorProject#getGrammarName <em>Grammar Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getXTextEditorProject()
 * @model
 * @generated
 */
public interface XTextEditorProject extends EditorProject {

	/**
	 * Returns the value of the '<em><b>Grammar Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grammar Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grammar Name</em>' attribute.
	 * @see #setGrammarName(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getXTextEditorProject_GrammarName()
	 * @model required="true"
	 * @generated
	 */
	String getGrammarName();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.XTextEditorProject#getGrammarName <em>Grammar Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grammar Name</em>' attribute.
	 * @see #getGrammarName()
	 * @generated
	 */
	void setGrammarName(String value);
} // XTextEditorProject
