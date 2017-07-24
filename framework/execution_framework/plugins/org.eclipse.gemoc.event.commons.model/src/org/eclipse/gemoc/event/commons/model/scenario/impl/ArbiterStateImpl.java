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

import org.eclipse.gemoc.event.commons.model.scenario.ArbiterState;
import org.eclipse.gemoc.event.commons.model.scenario.ArbiterTransition;
import org.eclipse.gemoc.event.commons.model.scenario.ScenarioPackage;
import org.eclipse.gemoc.event.commons.model.scenario.TruthValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arbiter State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ArbiterStateImpl#getIncomingTransitions <em>Incoming Transitions</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ArbiterStateImpl#getOutgoingTransitions <em>Outgoing Transitions</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ArbiterStateImpl#getTruthValue <em>Truth Value</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.scenario.impl.ArbiterStateImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArbiterStateImpl<P extends Property, T extends ArbiterTransition<P, ?>> extends MinimalEObjectImpl.Container implements ArbiterState<P, T> {
	/**
	 * The cached value of the '{@link #getIncomingTransitions() <em>Incoming Transitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<T> incomingTransitions;

	/**
	 * The cached value of the '{@link #getOutgoingTransitions() <em>Outgoing Transitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<T> outgoingTransitions;

	/**
	 * The default value of the '{@link #getTruthValue() <em>Truth Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTruthValue()
	 * @generated
	 * @ordered
	 */
	protected static final TruthValue TRUTH_VALUE_EDEFAULT = TruthValue.INCONCLUSIVE;

	/**
	 * The cached value of the '{@link #getTruthValue() <em>Truth Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTruthValue()
	 * @generated
	 * @ordered
	 */
	protected TruthValue truthValue = TRUTH_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArbiterStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.ARBITER_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<T> getIncomingTransitions() {
		if (incomingTransitions == null) {
			incomingTransitions = new EObjectWithInverseResolvingEList<T>(ArbiterTransition.class, this, ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS, ScenarioPackage.ARBITER_TRANSITION__TARGET);
		}
		return incomingTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<T> getOutgoingTransitions() {
		if (outgoingTransitions == null) {
			outgoingTransitions = new EObjectWithInverseResolvingEList<T>(ArbiterTransition.class, this, ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS, ScenarioPackage.ARBITER_TRANSITION__SOURCE);
		}
		return outgoingTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TruthValue getTruthValue() {
		return truthValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTruthValue(TruthValue newTruthValue) {
		TruthValue oldTruthValue = truthValue;
		truthValue = newTruthValue == null ? TRUTH_VALUE_EDEFAULT : newTruthValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ARBITER_STATE__TRUTH_VALUE, oldTruthValue, truthValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ARBITER_STATE__NAME, oldName, name));
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
			case ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
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
			case ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
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
			case ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS:
				return getIncomingTransitions();
			case ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS:
				return getOutgoingTransitions();
			case ScenarioPackage.ARBITER_STATE__TRUTH_VALUE:
				return getTruthValue();
			case ScenarioPackage.ARBITER_STATE__NAME:
				return getName();
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
			case ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS:
				getIncomingTransitions().clear();
				getIncomingTransitions().addAll((Collection<? extends T>)newValue);
				return;
			case ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS:
				getOutgoingTransitions().clear();
				getOutgoingTransitions().addAll((Collection<? extends T>)newValue);
				return;
			case ScenarioPackage.ARBITER_STATE__TRUTH_VALUE:
				setTruthValue((TruthValue)newValue);
				return;
			case ScenarioPackage.ARBITER_STATE__NAME:
				setName((String)newValue);
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
			case ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS:
				getIncomingTransitions().clear();
				return;
			case ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS:
				getOutgoingTransitions().clear();
				return;
			case ScenarioPackage.ARBITER_STATE__TRUTH_VALUE:
				setTruthValue(TRUTH_VALUE_EDEFAULT);
				return;
			case ScenarioPackage.ARBITER_STATE__NAME:
				setName(NAME_EDEFAULT);
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
			case ScenarioPackage.ARBITER_STATE__INCOMING_TRANSITIONS:
				return incomingTransitions != null && !incomingTransitions.isEmpty();
			case ScenarioPackage.ARBITER_STATE__OUTGOING_TRANSITIONS:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case ScenarioPackage.ARBITER_STATE__TRUTH_VALUE:
				return truthValue != TRUTH_VALUE_EDEFAULT;
			case ScenarioPackage.ARBITER_STATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (truthValue: ");
		result.append(truthValue);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ArbiterStateImpl
