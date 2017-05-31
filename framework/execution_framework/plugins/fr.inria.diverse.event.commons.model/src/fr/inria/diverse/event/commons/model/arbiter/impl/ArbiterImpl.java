/**
 */
package fr.inria.diverse.event.commons.model.arbiter.impl;

import fr.inria.diverse.event.commons.model.arbiter.Arbiter;
import fr.inria.diverse.event.commons.model.arbiter.ArbiterPackage;
import fr.inria.diverse.event.commons.model.arbiter.State;
import fr.inria.diverse.event.commons.model.arbiter.Transition;

import fr.inria.diverse.event.commons.model.property.Property;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arbiter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.impl.ArbiterImpl#getStates <em>States</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.impl.ArbiterImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.arbiter.impl.ArbiterImpl#getInitialState <em>Initial State</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ArbiterImpl<P extends Property, S extends State<P, T>, T extends Transition<P, S>> extends MinimalEObjectImpl.Container implements Arbiter<P, S, T> {
	/**
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<S> states;

	/**
	 * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<T> transitions;

	/**
	 * The cached value of the '{@link #getInitialState() <em>Initial State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialState()
	 * @generated
	 * @ordered
	 */
	protected S initialState;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArbiterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArbiterPackage.Literals.ARBITER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<S> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<S>(State.class, this, ArbiterPackage.ARBITER__STATES);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<T> getTransitions() {
		if (transitions == null) {
			transitions = new EObjectContainmentEList<T>(Transition.class, this, ArbiterPackage.ARBITER__TRANSITIONS);
		}
		return transitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public S getInitialState() {
		if (initialState != null && initialState.eIsProxy()) {
			InternalEObject oldInitialState = (InternalEObject)initialState;
			initialState = (S)eResolveProxy(oldInitialState);
			if (initialState != oldInitialState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ArbiterPackage.ARBITER__INITIAL_STATE, oldInitialState, initialState));
			}
		}
		return initialState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public S basicGetInitialState() {
		return initialState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialState(S newInitialState) {
		S oldInitialState = initialState;
		initialState = newInitialState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArbiterPackage.ARBITER__INITIAL_STATE, oldInitialState, initialState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ArbiterPackage.ARBITER__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case ArbiterPackage.ARBITER__TRANSITIONS:
				return ((InternalEList<?>)getTransitions()).basicRemove(otherEnd, msgs);
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
			case ArbiterPackage.ARBITER__STATES:
				return getStates();
			case ArbiterPackage.ARBITER__TRANSITIONS:
				return getTransitions();
			case ArbiterPackage.ARBITER__INITIAL_STATE:
				if (resolve) return getInitialState();
				return basicGetInitialState();
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
			case ArbiterPackage.ARBITER__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends S>)newValue);
				return;
			case ArbiterPackage.ARBITER__TRANSITIONS:
				getTransitions().clear();
				getTransitions().addAll((Collection<? extends T>)newValue);
				return;
			case ArbiterPackage.ARBITER__INITIAL_STATE:
				setInitialState((S)newValue);
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
			case ArbiterPackage.ARBITER__STATES:
				getStates().clear();
				return;
			case ArbiterPackage.ARBITER__TRANSITIONS:
				getTransitions().clear();
				return;
			case ArbiterPackage.ARBITER__INITIAL_STATE:
				setInitialState((S)null);
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
			case ArbiterPackage.ARBITER__STATES:
				return states != null && !states.isEmpty();
			case ArbiterPackage.ARBITER__TRANSITIONS:
				return transitions != null && !transitions.isEmpty();
			case ArbiterPackage.ARBITER__INITIAL_STATE:
				return initialState != null;
		}
		return super.eIsSet(featureID);
	}

} //ArbiterImpl
