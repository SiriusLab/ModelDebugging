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

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Model Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getDefaultRootEObjectQualifiedName <em>Default Root EObject Qualified Name</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getGenmodeluri <em>Genmodeluri</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getModelLoaderClass <em>Model Loader Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getDomainModelProject()
 * @model
 * @generated
 */
public interface DomainModelProject extends ProjectResource {

	/**
	 * Returns the value of the '<em><b>Default Root EObject Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Root EObject Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Root EObject Qualified Name</em>' attribute.
	 * @see #setDefaultRootEObjectQualifiedName(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getDomainModelProject_DefaultRootEObjectQualifiedName()
	 * @model
	 * @generated
	 */
	String getDefaultRootEObjectQualifiedName();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getDefaultRootEObjectQualifiedName <em>Default Root EObject Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Root EObject Qualified Name</em>' attribute.
	 * @see #getDefaultRootEObjectQualifiedName()
	 * @generated
	 */
	void setDefaultRootEObjectQualifiedName(String value);

	/**
	 * Returns the value of the '<em><b>Genmodeluri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Genmodeluri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Genmodeluri</em>' attribute.
	 * @see #setGenmodeluri(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getDomainModelProject_Genmodeluri()
	 * @model
	 * @generated
	 */
	String getGenmodeluri();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getGenmodeluri <em>Genmodeluri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Genmodeluri</em>' attribute.
	 * @see #getGenmodeluri()
	 * @generated
	 */
	void setGenmodeluri(String value);

	/**
	 * Returns the value of the '<em><b>Model Loader Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Loader Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Loader Class</em>' attribute.
	 * @see #setModelLoaderClass(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getDomainModelProject_ModelLoaderClass()
	 * @model
	 * @generated
	 */
	String getModelLoaderClass();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getModelLoaderClass <em>Model Loader Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Loader Class</em>' attribute.
	 * @see #getModelLoaderClass()
	 * @generated
	 */
	void setModelLoaderClass(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getEcoreURI();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EObject getGenmodel();
} // DomainModelProject
