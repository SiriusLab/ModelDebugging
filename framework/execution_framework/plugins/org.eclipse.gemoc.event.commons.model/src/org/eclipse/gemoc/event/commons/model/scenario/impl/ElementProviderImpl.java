/**
 */
package org.eclipse.gemoc.event.commons.model.scenario.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.gemoc.event.commons.model.scenario.ElementProvider;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ElementProviderImpl<T> extends MinimalEObjectImpl.Container implements ElementProvider<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.ELEMENT_PROVIDER;
	}

} //ElementProviderImpl
