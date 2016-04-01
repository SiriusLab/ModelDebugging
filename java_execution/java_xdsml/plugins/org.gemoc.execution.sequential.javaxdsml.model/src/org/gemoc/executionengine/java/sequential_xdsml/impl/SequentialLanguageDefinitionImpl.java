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
package org.gemoc.executionengine.java.sequential_xdsml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.gemoc.executionengine.java.sequential_xdsml.DSAProject;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlPackage;

import org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequential Language Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionengine.java.sequential_xdsml.impl.SequentialLanguageDefinitionImpl#getDsaProject <em>Dsa Project</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequentialLanguageDefinitionImpl extends LanguageDefinitionImpl implements SequentialLanguageDefinition {
	/**
	 * The cached value of the '{@link #getDsaProject() <em>Dsa Project</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsaProject()
	 * @generated
	 * @ordered
	 */
	protected DSAProject dsaProject;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequentialLanguageDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Sequential_xdsmlPackage.Literals.SEQUENTIAL_LANGUAGE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSAProject getDsaProject() {
		return dsaProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDsaProject(DSAProject newDsaProject, NotificationChain msgs) {
		DSAProject oldDsaProject = dsaProject;
		dsaProject = newDsaProject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT, oldDsaProject, newDsaProject);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDsaProject(DSAProject newDsaProject) {
		if (newDsaProject != dsaProject) {
			NotificationChain msgs = null;
			if (dsaProject != null)
				msgs = ((InternalEObject)dsaProject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT, null, msgs);
			if (newDsaProject != null)
				msgs = ((InternalEObject)newDsaProject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT, null, msgs);
			msgs = basicSetDsaProject(newDsaProject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT, newDsaProject, newDsaProject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT:
				return basicSetDsaProject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT:
				return getDsaProject();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT:
				setDsaProject((DSAProject)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT:
				setDsaProject((DSAProject)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Sequential_xdsmlPackage.SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT:
				return dsaProject != null;
		}
		return super.eIsSet(featureID);
	}

} //SequentialLanguageDefinitionImpl
