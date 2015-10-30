/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Step;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Value;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl#getValues <em>Values</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl#getEndingSteps <em>Ending Steps</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl#getNextState <em>Next State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl#getPreviousState <em>Previous State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ExecutionStateImpl#getStartingSteps <em>Starting Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionStateImpl extends MinimalEObjectImpl.Container implements ExecutionState {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Value> values;

	/**
	 * The cached value of the '{@link #getEndingSteps() <em>Ending Steps</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndingSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<Step> endingSteps;

	/**
	 * The cached value of the '{@link #getNextState() <em>Next State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextState()
	 * @generated
	 * @ordered
	 */
	protected ExecutionState nextState;

	/**
	 * The cached value of the '{@link #getPreviousState() <em>Previous State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreviousState()
	 * @generated
	 * @ordered
	 */
	protected ExecutionState previousState;

	/**
	 * The cached value of the '{@link #getStartingSteps() <em>Starting Steps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartingSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<Step> startingSteps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RichgenerictracePackage.Literals.EXECUTION_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Value> getValues() {
		if (values == null) {
			values = new EObjectWithInverseResolvingEList.ManyInverse<Value>(Value.class, this, RichgenerictracePackage.EXECUTION_STATE__VALUES, RichgenerictracePackage.VALUE__EXECUTION_STATES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Step> getEndingSteps() {
		if (endingSteps == null) {
			endingSteps = new EObjectWithInverseResolvingEList<Step>(Step.class, this, RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS, RichgenerictracePackage.STEP__ENDING_STATE);
		}
		return endingSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getNextState() {
		if (nextState != null && nextState.eIsProxy()) {
			InternalEObject oldNextState = (InternalEObject)nextState;
			nextState = (ExecutionState)eResolveProxy(oldNextState);
			if (nextState != oldNextState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE, oldNextState, nextState));
			}
		}
		return nextState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState basicGetNextState() {
		return nextState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNextState(ExecutionState newNextState, NotificationChain msgs) {
		ExecutionState oldNextState = nextState;
		nextState = newNextState;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE, oldNextState, newNextState);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextState(ExecutionState newNextState) {
		if (newNextState != nextState) {
			NotificationChain msgs = null;
			if (nextState != null)
				msgs = ((InternalEObject)nextState).eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE, ExecutionState.class, msgs);
			if (newNextState != null)
				msgs = ((InternalEObject)newNextState).eInverseAdd(this, RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE, ExecutionState.class, msgs);
			msgs = basicSetNextState(newNextState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE, newNextState, newNextState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getPreviousState() {
		if (previousState != null && previousState.eIsProxy()) {
			InternalEObject oldPreviousState = (InternalEObject)previousState;
			previousState = (ExecutionState)eResolveProxy(oldPreviousState);
			if (previousState != oldPreviousState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE, oldPreviousState, previousState));
			}
		}
		return previousState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState basicGetPreviousState() {
		return previousState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPreviousState(ExecutionState newPreviousState, NotificationChain msgs) {
		ExecutionState oldPreviousState = previousState;
		previousState = newPreviousState;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE, oldPreviousState, newPreviousState);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreviousState(ExecutionState newPreviousState) {
		if (newPreviousState != previousState) {
			NotificationChain msgs = null;
			if (previousState != null)
				msgs = ((InternalEObject)previousState).eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE, ExecutionState.class, msgs);
			if (newPreviousState != null)
				msgs = ((InternalEObject)newPreviousState).eInverseAdd(this, RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE, ExecutionState.class, msgs);
			msgs = basicSetPreviousState(newPreviousState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE, newPreviousState, newPreviousState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Step> getStartingSteps() {
		if (startingSteps == null) {
			startingSteps = new EObjectContainmentWithInverseEList<Step>(Step.class, this, RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS, RichgenerictracePackage.STEP__STARTING_STATE);
		}
		return startingSteps;
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
			case RichgenerictracePackage.EXECUTION_STATE__VALUES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getValues()).basicAdd(otherEnd, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEndingSteps()).basicAdd(otherEnd, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE:
				if (nextState != null)
					msgs = ((InternalEObject)nextState).eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE, ExecutionState.class, msgs);
				return basicSetNextState((ExecutionState)otherEnd, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE:
				if (previousState != null)
					msgs = ((InternalEObject)previousState).eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE, ExecutionState.class, msgs);
				return basicSetPreviousState((ExecutionState)otherEnd, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStartingSteps()).basicAdd(otherEnd, msgs);
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
			case RichgenerictracePackage.EXECUTION_STATE__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS:
				return ((InternalEList<?>)getEndingSteps()).basicRemove(otherEnd, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE:
				return basicSetNextState(null, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE:
				return basicSetPreviousState(null, msgs);
			case RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS:
				return ((InternalEList<?>)getStartingSteps()).basicRemove(otherEnd, msgs);
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
			case RichgenerictracePackage.EXECUTION_STATE__VALUES:
				return getValues();
			case RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS:
				return getEndingSteps();
			case RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE:
				if (resolve) return getNextState();
				return basicGetNextState();
			case RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE:
				if (resolve) return getPreviousState();
				return basicGetPreviousState();
			case RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS:
				return getStartingSteps();
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
			case RichgenerictracePackage.EXECUTION_STATE__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends Value>)newValue);
				return;
			case RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS:
				getEndingSteps().clear();
				getEndingSteps().addAll((Collection<? extends Step>)newValue);
				return;
			case RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE:
				setNextState((ExecutionState)newValue);
				return;
			case RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE:
				setPreviousState((ExecutionState)newValue);
				return;
			case RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS:
				getStartingSteps().clear();
				getStartingSteps().addAll((Collection<? extends Step>)newValue);
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
			case RichgenerictracePackage.EXECUTION_STATE__VALUES:
				getValues().clear();
				return;
			case RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS:
				getEndingSteps().clear();
				return;
			case RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE:
				setNextState((ExecutionState)null);
				return;
			case RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE:
				setPreviousState((ExecutionState)null);
				return;
			case RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS:
				getStartingSteps().clear();
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
			case RichgenerictracePackage.EXECUTION_STATE__VALUES:
				return values != null && !values.isEmpty();
			case RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS:
				return endingSteps != null && !endingSteps.isEmpty();
			case RichgenerictracePackage.EXECUTION_STATE__NEXT_STATE:
				return nextState != null;
			case RichgenerictracePackage.EXECUTION_STATE__PREVIOUS_STATE:
				return previousState != null;
			case RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS:
				return startingSteps != null && !startingSteps.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExecutionStateImpl
