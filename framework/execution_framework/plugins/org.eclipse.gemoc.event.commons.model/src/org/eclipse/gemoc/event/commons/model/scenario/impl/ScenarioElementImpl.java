/**
 */
package org.eclipse.gemoc.event.commons.model.scenario.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gemoc.event.commons.model.property.Property;

import org.eclipse.gemoc.event.commons.model.scenario.ScenarioElement;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ScenarioElementImpl#getPreviousElements <em>Previous Elements</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ScenarioElementImpl#getNextElements <em>Next Elements</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ScenarioElementImpl#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ScenarioElementImpl<P extends Property> extends MinimalEObjectImpl.Container implements ScenarioElement<P> {
	/**
	 * The cached value of the '{@link #getPreviousElements() <em>Previous Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreviousElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ScenarioElement<P>> previousElements;

	/**
	 * The cached value of the '{@link #getNextElements() <em>Next Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextElements()
	 * @generated
	 * @ordered
	 */
	protected EList<ScenarioElement<P>> nextElements;

	/**
	 * The cached value of the '{@link #getGuard() <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuard()
	 * @generated
	 * @ordered
	 */
	protected P guard;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.SCENARIO_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScenarioElement<P>> getPreviousElements() {
		if (previousElements == null) {
			previousElements = new EObjectWithInverseResolvingEList.ManyInverse<ScenarioElement<P>>(ScenarioElement.class, this, ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS, ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS);
		}
		return previousElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScenarioElement<P>> getNextElements() {
		if (nextElements == null) {
			nextElements = new EObjectWithInverseResolvingEList.ManyInverse<ScenarioElement<P>>(ScenarioElement.class, this, ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS, ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS);
		}
		return nextElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public P getGuard() {
		return guard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGuard(P newGuard, NotificationChain msgs) {
		P oldGuard = guard;
		guard = newGuard;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ELEMENT__GUARD, oldGuard, newGuard);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGuard(P newGuard) {
		if (newGuard != guard) {
			NotificationChain msgs = null;
			if (guard != null)
				msgs = ((InternalEObject)guard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ELEMENT__GUARD, null, msgs);
			if (newGuard != null)
				msgs = ((InternalEObject)newGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ELEMENT__GUARD, null, msgs);
			msgs = basicSetGuard(newGuard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ELEMENT__GUARD, newGuard, newGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPreviousElements()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNextElements()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS:
				return ((InternalEList<?>)getPreviousElements()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS:
				return ((InternalEList<?>)getNextElements()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_ELEMENT__GUARD:
				return basicSetGuard(null, msgs);
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
			case ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS:
				return getPreviousElements();
			case ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS:
				return getNextElements();
			case ScenarioPackage.SCENARIO_ELEMENT__GUARD:
				return getGuard();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS:
				getPreviousElements().clear();
				getPreviousElements().addAll((Collection<? extends ScenarioElement<P>>)newValue);
				return;
			case ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS:
				getNextElements().clear();
				getNextElements().addAll((Collection<? extends ScenarioElement<P>>)newValue);
				return;
			case ScenarioPackage.SCENARIO_ELEMENT__GUARD:
				setGuard((P)newValue);
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
			case ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS:
				getPreviousElements().clear();
				return;
			case ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS:
				getNextElements().clear();
				return;
			case ScenarioPackage.SCENARIO_ELEMENT__GUARD:
				setGuard((P)null);
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
			case ScenarioPackage.SCENARIO_ELEMENT__PREVIOUS_ELEMENTS:
				return previousElements != null && !previousElements.isEmpty();
			case ScenarioPackage.SCENARIO_ELEMENT__NEXT_ELEMENTS:
				return nextElements != null && !nextElements.isEmpty();
			case ScenarioPackage.SCENARIO_ELEMENT__GUARD:
				return guard != null;
		}
		return super.eIsSet(featureID);
	}

} //ScenarioElementImpl
