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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Language Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getDomainModelProject <em>Domain Model Project</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getEditorProjects <em>Editor Projects</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getAnimatorProjects <em>Animator Projects</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getMelangeURI <em>Melange URI</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#isNeedMelangeSynchronization <em>Need Melange Synchronization</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition()
 * @model
 * @generated
 */
public interface LanguageDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Domain Model Project</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Model Project</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Model Project</em>' containment reference.
	 * @see #setDomainModelProject(DomainModelProject)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition_DomainModelProject()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DomainModelProject getDomainModelProject();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getDomainModelProject <em>Domain Model Project</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Model Project</em>' containment reference.
	 * @see #getDomainModelProject()
	 * @generated
	 */
	void setDomainModelProject(DomainModelProject value);

	/**
	 * Returns the value of the '<em><b>Editor Projects</b></em>' containment reference list.
	 * The list contents are of type {@link org.gemoc.executionframework.xdsml_base.EditorProject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor Projects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor Projects</em>' containment reference list.
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition_EditorProjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<EditorProject> getEditorProjects();

	/**
	 * Returns the value of the '<em><b>Animator Projects</b></em>' containment reference list.
	 * The list contents are of type {@link org.gemoc.executionframework.xdsml_base.AnimatorProject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Animator Projects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Animator Projects</em>' containment reference list.
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition_AnimatorProjects()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnimatorProject> getAnimatorProjects();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Melange URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Melange URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Melange URI</em>' attribute.
	 * @see #setMelangeURI(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition_MelangeURI()
	 * @model
	 * @generated
	 */
	String getMelangeURI();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getMelangeURI <em>Melange URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Melange URI</em>' attribute.
	 * @see #getMelangeURI()
	 * @generated
	 */
	void setMelangeURI(String value);

	/**
	 * Returns the value of the '<em><b>Need Melange Synchronization</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Need Melange Synchronization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Need Melange Synchronization</em>' attribute.
	 * @see #setNeedMelangeSynchronization(boolean)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getLanguageDefinition_NeedMelangeSynchronization()
	 * @model default="false"
	 * @generated
	 */
	boolean isNeedMelangeSynchronization();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#isNeedMelangeSynchronization <em>Need Melange Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Need Melange Synchronization</em>' attribute.
	 * @see #isNeedMelangeSynchronization()
	 * @generated
	 */
	void setNeedMelangeSynchronization(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides a convenient access to all file extensions declared. This includes both the files extensions declared in the genmodel of the DomainModelProject and the ones declared in the editors
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<String> getFileExtensions();

} // LanguageDefinition
