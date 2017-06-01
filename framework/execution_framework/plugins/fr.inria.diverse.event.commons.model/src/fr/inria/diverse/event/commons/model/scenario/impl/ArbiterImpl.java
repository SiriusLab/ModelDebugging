/**
 */
package fr.inria.diverse.event.commons.model.scenario.impl;

import fr.inria.diverse.event.commons.model.property.Property;

import fr.inria.diverse.event.commons.model.scenario.Arbiter;
import fr.inria.diverse.event.commons.model.scenario.ArbiterState;
import fr.inria.diverse.event.commons.model.scenario.ArbiterTransition;
import fr.inria.diverse.event.commons.model.scenario.ScenarioPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arbiter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl#getStates <em>States</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl#getAcceptingStates <em>Accepting States</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.scenario.impl.ArbiterImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ArbiterImpl<P extends Property, S extends ArbiterState<P, T>, T extends ArbiterTransition<P, S>> extends MinimalEObjectImpl.Container implements Arbiter<P, S, T> {
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
	 * The cached value of the '{@link #getAcceptingStates() <em>Accepting States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAcceptingStates()
	 * @generated
	 * @ordered
	 */
	protected EList<S> acceptingStates;

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
		return ScenarioPackage.Literals.ARBITER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<S> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<S>(ArbiterState.class, this, ScenarioPackage.ARBITER__STATES);
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
			transitions = new EObjectContainmentEList<T>(ArbiterTransition.class, this, ScenarioPackage.ARBITER__TRANSITIONS);
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.ARBITER__INITIAL_STATE, oldInitialState, initialState));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ARBITER__INITIAL_STATE, oldInitialState, initialState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<S> getAcceptingStates() {
		if (acceptingStates == null) {
			acceptingStates = new EObjectResolvingEList<S>(ArbiterState.class, this, ScenarioPackage.ARBITER__ACCEPTING_STATES);
		}
		return acceptingStates;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ARBITER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.ARBITER__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.ARBITER__TRANSITIONS:
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
			case ScenarioPackage.ARBITER__STATES:
				return getStates();
			case ScenarioPackage.ARBITER__TRANSITIONS:
				return getTransitions();
			case ScenarioPackage.ARBITER__INITIAL_STATE:
				if (resolve) return getInitialState();
				return basicGetInitialState();
			case ScenarioPackage.ARBITER__ACCEPTING_STATES:
				return getAcceptingStates();
			case ScenarioPackage.ARBITER__NAME:
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
			case ScenarioPackage.ARBITER__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends S>)newValue);
				return;
			case ScenarioPackage.ARBITER__TRANSITIONS:
				getTransitions().clear();
				getTransitions().addAll((Collection<? extends T>)newValue);
				return;
			case ScenarioPackage.ARBITER__INITIAL_STATE:
				setInitialState((S)newValue);
				return;
			case ScenarioPackage.ARBITER__ACCEPTING_STATES:
				getAcceptingStates().clear();
				getAcceptingStates().addAll((Collection<? extends S>)newValue);
				return;
			case ScenarioPackage.ARBITER__NAME:
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
			case ScenarioPackage.ARBITER__STATES:
				getStates().clear();
				return;
			case ScenarioPackage.ARBITER__TRANSITIONS:
				getTransitions().clear();
				return;
			case ScenarioPackage.ARBITER__INITIAL_STATE:
				setInitialState((S)null);
				return;
			case ScenarioPackage.ARBITER__ACCEPTING_STATES:
				getAcceptingStates().clear();
				return;
			case ScenarioPackage.ARBITER__NAME:
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
			case ScenarioPackage.ARBITER__STATES:
				return states != null && !states.isEmpty();
			case ScenarioPackage.ARBITER__TRANSITIONS:
				return transitions != null && !transitions.isEmpty();
			case ScenarioPackage.ARBITER__INITIAL_STATE:
				return initialState != null;
			case ScenarioPackage.ARBITER__ACCEPTING_STATES:
				return acceptingStates != null && !acceptingStates.isEmpty();
			case ScenarioPackage.ARBITER__NAME:
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ArbiterImpl
