/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Value;

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
 * An implementation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl#getExecutionStates <em>Execution States</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl#getNextValue <em>Next Value</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueImpl#getPreviousValue <em>Previous Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueImpl extends MinimalEObjectImpl.Container implements Value {
	/**
	 * The cached value of the '{@link #getExecutionStates() <em>Execution States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionStates()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionState> executionStates;

	/**
	 * The cached value of the '{@link #getNextValue() <em>Next Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextValue()
	 * @generated
	 * @ordered
	 */
	protected Value nextValue;

	/**
	 * The cached value of the '{@link #getPreviousValue() <em>Previous Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreviousValue()
	 * @generated
	 * @ordered
	 */
	protected Value previousValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RichgenerictracePackage.Literals.VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionState> getExecutionStates() {
		if (executionStates == null) {
			executionStates = new EObjectWithInverseResolvingEList.ManyInverse<ExecutionState>(ExecutionState.class, this, RichgenerictracePackage.VALUE__EXECUTION_STATES, RichgenerictracePackage.EXECUTION_STATE__VALUES);
		}
		return executionStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value getNextValue() {
		if (nextValue != null && nextValue.eIsProxy()) {
			InternalEObject oldNextValue = (InternalEObject)nextValue;
			nextValue = (Value)eResolveProxy(oldNextValue);
			if (nextValue != oldNextValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.VALUE__NEXT_VALUE, oldNextValue, nextValue));
			}
		}
		return nextValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value basicGetNextValue() {
		return nextValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNextValue(Value newNextValue, NotificationChain msgs) {
		Value oldNextValue = nextValue;
		nextValue = newNextValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.VALUE__NEXT_VALUE, oldNextValue, newNextValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextValue(Value newNextValue) {
		if (newNextValue != nextValue) {
			NotificationChain msgs = null;
			if (nextValue != null)
				msgs = ((InternalEObject)nextValue).eInverseRemove(this, RichgenerictracePackage.VALUE__PREVIOUS_VALUE, Value.class, msgs);
			if (newNextValue != null)
				msgs = ((InternalEObject)newNextValue).eInverseAdd(this, RichgenerictracePackage.VALUE__PREVIOUS_VALUE, Value.class, msgs);
			msgs = basicSetNextValue(newNextValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.VALUE__NEXT_VALUE, newNextValue, newNextValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value getPreviousValue() {
		if (previousValue != null && previousValue.eIsProxy()) {
			InternalEObject oldPreviousValue = (InternalEObject)previousValue;
			previousValue = (Value)eResolveProxy(oldPreviousValue);
			if (previousValue != oldPreviousValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.VALUE__PREVIOUS_VALUE, oldPreviousValue, previousValue));
			}
		}
		return previousValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value basicGetPreviousValue() {
		return previousValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPreviousValue(Value newPreviousValue, NotificationChain msgs) {
		Value oldPreviousValue = previousValue;
		previousValue = newPreviousValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.VALUE__PREVIOUS_VALUE, oldPreviousValue, newPreviousValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreviousValue(Value newPreviousValue) {
		if (newPreviousValue != previousValue) {
			NotificationChain msgs = null;
			if (previousValue != null)
				msgs = ((InternalEObject)previousValue).eInverseRemove(this, RichgenerictracePackage.VALUE__NEXT_VALUE, Value.class, msgs);
			if (newPreviousValue != null)
				msgs = ((InternalEObject)newPreviousValue).eInverseAdd(this, RichgenerictracePackage.VALUE__NEXT_VALUE, Value.class, msgs);
			msgs = basicSetPreviousValue(newPreviousValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.VALUE__PREVIOUS_VALUE, newPreviousValue, newPreviousValue));
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
			case RichgenerictracePackage.VALUE__EXECUTION_STATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutionStates()).basicAdd(otherEnd, msgs);
			case RichgenerictracePackage.VALUE__NEXT_VALUE:
				if (nextValue != null)
					msgs = ((InternalEObject)nextValue).eInverseRemove(this, RichgenerictracePackage.VALUE__PREVIOUS_VALUE, Value.class, msgs);
				return basicSetNextValue((Value)otherEnd, msgs);
			case RichgenerictracePackage.VALUE__PREVIOUS_VALUE:
				if (previousValue != null)
					msgs = ((InternalEObject)previousValue).eInverseRemove(this, RichgenerictracePackage.VALUE__NEXT_VALUE, Value.class, msgs);
				return basicSetPreviousValue((Value)otherEnd, msgs);
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
			case RichgenerictracePackage.VALUE__EXECUTION_STATES:
				return ((InternalEList<?>)getExecutionStates()).basicRemove(otherEnd, msgs);
			case RichgenerictracePackage.VALUE__NEXT_VALUE:
				return basicSetNextValue(null, msgs);
			case RichgenerictracePackage.VALUE__PREVIOUS_VALUE:
				return basicSetPreviousValue(null, msgs);
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
			case RichgenerictracePackage.VALUE__EXECUTION_STATES:
				return getExecutionStates();
			case RichgenerictracePackage.VALUE__NEXT_VALUE:
				if (resolve) return getNextValue();
				return basicGetNextValue();
			case RichgenerictracePackage.VALUE__PREVIOUS_VALUE:
				if (resolve) return getPreviousValue();
				return basicGetPreviousValue();
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
			case RichgenerictracePackage.VALUE__EXECUTION_STATES:
				getExecutionStates().clear();
				getExecutionStates().addAll((Collection<? extends ExecutionState>)newValue);
				return;
			case RichgenerictracePackage.VALUE__NEXT_VALUE:
				setNextValue((Value)newValue);
				return;
			case RichgenerictracePackage.VALUE__PREVIOUS_VALUE:
				setPreviousValue((Value)newValue);
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
			case RichgenerictracePackage.VALUE__EXECUTION_STATES:
				getExecutionStates().clear();
				return;
			case RichgenerictracePackage.VALUE__NEXT_VALUE:
				setNextValue((Value)null);
				return;
			case RichgenerictracePackage.VALUE__PREVIOUS_VALUE:
				setPreviousValue((Value)null);
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
			case RichgenerictracePackage.VALUE__EXECUTION_STATES:
				return executionStates != null && !executionStates.isEmpty();
			case RichgenerictracePackage.VALUE__NEXT_VALUE:
				return nextValue != null;
			case RichgenerictracePackage.VALUE__PREVIOUS_VALUE:
				return previousValue != null;
		}
		return super.eIsSet(featureID);
	}

} //ValueImpl
