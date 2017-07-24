/**
 */
package fr.inria.diverse.event.commons.model.arbiter.impl;

import fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage;
import fr.inria.diverse.event.commons.model.arbiter.State;
import fr.inria.diverse.event.commons.model.arbiter.Transition;
import fr.inria.diverse.event.commons.model.arbiter.TruthValue;

import fr.inria.diverse.event.commons.model.property.Property;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.impl.StateImpl#getTruthValue <em>Truth Value</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.impl.StateImpl#getOutgoingTransitions <em>Outgoing Transitions</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.impl.StateImpl#getIncomingTransitions <em>Incoming Transitions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StateImpl<P extends Property, T extends Transition<P, ?>> extends MinimalEObjectImpl.Container implements State<P, T> {
	/**
	 * The default value of the '{@link #getTruthValue() <em>Truth Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTruthValue()
	 * @generated
	 * @ordered
	 */
	protected static final TruthValue TRUTH_VALUE_EDEFAULT = TruthValue.TRUE;

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
	 * The cached value of the '{@link #getOutgoingTransitions() <em>Outgoing Transitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<T> outgoingTransitions;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArbiterPackage.Literals.STATE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ArbiterPackage.STATE__TRUTH_VALUE, oldTruthValue, truthValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<T> getOutgoingTransitions() {
		if (outgoingTransitions == null) {
			outgoingTransitions = new EObjectWithInverseResolvingEList<T>(Transition.class, this, ArbiterPackage.STATE__OUTGOING_TRANSITIONS, ArbiterPackage.TRANSITION__SOURCE);
		}
		return outgoingTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<T> getIncomingTransitions() {
		if (incomingTransitions == null) {
			incomingTransitions = new EObjectWithInverseResolvingEList<T>(Transition.class, this, ArbiterPackage.STATE__INCOMING_TRANSITIONS, ArbiterPackage.TRANSITION__TARGET);
		}
		return incomingTransitions;
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
			case ArbiterPackage.STATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
			case ArbiterPackage.STATE__INCOMING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
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
			case ArbiterPackage.STATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
			case ArbiterPackage.STATE__INCOMING_TRANSITIONS:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
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
			case ArbiterPackage.STATE__TRUTH_VALUE:
				return getTruthValue();
			case ArbiterPackage.STATE__OUTGOING_TRANSITIONS:
				return getOutgoingTransitions();
			case ArbiterPackage.STATE__INCOMING_TRANSITIONS:
				return getIncomingTransitions();
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
			case ArbiterPackage.STATE__TRUTH_VALUE:
				setTruthValue((TruthValue)newValue);
				return;
			case ArbiterPackage.STATE__OUTGOING_TRANSITIONS:
				getOutgoingTransitions().clear();
				getOutgoingTransitions().addAll((Collection<? extends T>)newValue);
				return;
			case ArbiterPackage.STATE__INCOMING_TRANSITIONS:
				getIncomingTransitions().clear();
				getIncomingTransitions().addAll((Collection<? extends T>)newValue);
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
			case ArbiterPackage.STATE__TRUTH_VALUE:
				setTruthValue(TRUTH_VALUE_EDEFAULT);
				return;
			case ArbiterPackage.STATE__OUTGOING_TRANSITIONS:
				getOutgoingTransitions().clear();
				return;
			case ArbiterPackage.STATE__INCOMING_TRANSITIONS:
				getIncomingTransitions().clear();
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
			case ArbiterPackage.STATE__TRUTH_VALUE:
				return truthValue != TRUTH_VALUE_EDEFAULT;
			case ArbiterPackage.STATE__OUTGOING_TRANSITIONS:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case ArbiterPackage.STATE__INCOMING_TRANSITIONS:
				return incomingTransitions != null && !incomingTransitions.isEmpty();
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
		result.append(')');
		return result.toString();
	}

} //StateImpl
