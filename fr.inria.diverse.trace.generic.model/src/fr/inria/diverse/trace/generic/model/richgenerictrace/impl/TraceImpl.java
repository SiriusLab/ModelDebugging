/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Step;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Trace;
import fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject;

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
 * An implementation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl#getStates <em>States</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl#getTracedObjects <em>Traced Objects</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl#getCurrentState <em>Current State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TraceImpl#getCurrentStep <em>Current Step</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraceImpl extends MinimalEObjectImpl.Container implements Trace {
	/**
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionState> states;

	/**
	 * The cached value of the '{@link #getTracedObjects() <em>Traced Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracedObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<TracedObject> tracedObjects;

	/**
	 * The cached value of the '{@link #getCurrentState() <em>Current State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentState()
	 * @generated
	 * @ordered
	 */
	protected ExecutionState currentState;

	/**
	 * The cached value of the '{@link #getCurrentStep() <em>Current Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentStep()
	 * @generated
	 * @ordered
	 */
	protected Step currentStep;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RichgenerictracePackage.Literals.TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionState> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<ExecutionState>(ExecutionState.class, this, RichgenerictracePackage.TRACE__STATES);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TracedObject> getTracedObjects() {
		if (tracedObjects == null) {
			tracedObjects = new EObjectContainmentEList<TracedObject>(TracedObject.class, this, RichgenerictracePackage.TRACE__TRACED_OBJECTS);
		}
		return tracedObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getCurrentState() {
		if (currentState != null && currentState.eIsProxy()) {
			InternalEObject oldCurrentState = (InternalEObject)currentState;
			currentState = (ExecutionState)eResolveProxy(oldCurrentState);
			if (currentState != oldCurrentState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.TRACE__CURRENT_STATE, oldCurrentState, currentState));
			}
		}
		return currentState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState basicGetCurrentState() {
		return currentState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentState(ExecutionState newCurrentState) {
		ExecutionState oldCurrentState = currentState;
		currentState = newCurrentState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.TRACE__CURRENT_STATE, oldCurrentState, currentState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step getCurrentStep() {
		if (currentStep != null && currentStep.eIsProxy()) {
			InternalEObject oldCurrentStep = (InternalEObject)currentStep;
			currentStep = (Step)eResolveProxy(oldCurrentStep);
			if (currentStep != oldCurrentStep) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.TRACE__CURRENT_STEP, oldCurrentStep, currentStep));
			}
		}
		return currentStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step basicGetCurrentStep() {
		return currentStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentStep(Step newCurrentStep) {
		Step oldCurrentStep = currentStep;
		currentStep = newCurrentStep;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.TRACE__CURRENT_STEP, oldCurrentStep, currentStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RichgenerictracePackage.TRACE__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case RichgenerictracePackage.TRACE__TRACED_OBJECTS:
				return ((InternalEList<?>)getTracedObjects()).basicRemove(otherEnd, msgs);
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
			case RichgenerictracePackage.TRACE__STATES:
				return getStates();
			case RichgenerictracePackage.TRACE__TRACED_OBJECTS:
				return getTracedObjects();
			case RichgenerictracePackage.TRACE__CURRENT_STATE:
				if (resolve) return getCurrentState();
				return basicGetCurrentState();
			case RichgenerictracePackage.TRACE__CURRENT_STEP:
				if (resolve) return getCurrentStep();
				return basicGetCurrentStep();
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
			case RichgenerictracePackage.TRACE__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends ExecutionState>)newValue);
				return;
			case RichgenerictracePackage.TRACE__TRACED_OBJECTS:
				getTracedObjects().clear();
				getTracedObjects().addAll((Collection<? extends TracedObject>)newValue);
				return;
			case RichgenerictracePackage.TRACE__CURRENT_STATE:
				setCurrentState((ExecutionState)newValue);
				return;
			case RichgenerictracePackage.TRACE__CURRENT_STEP:
				setCurrentStep((Step)newValue);
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
			case RichgenerictracePackage.TRACE__STATES:
				getStates().clear();
				return;
			case RichgenerictracePackage.TRACE__TRACED_OBJECTS:
				getTracedObjects().clear();
				return;
			case RichgenerictracePackage.TRACE__CURRENT_STATE:
				setCurrentState((ExecutionState)null);
				return;
			case RichgenerictracePackage.TRACE__CURRENT_STEP:
				setCurrentStep((Step)null);
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
			case RichgenerictracePackage.TRACE__STATES:
				return states != null && !states.isEmpty();
			case RichgenerictracePackage.TRACE__TRACED_OBJECTS:
				return tracedObjects != null && !tracedObjects.isEmpty();
			case RichgenerictracePackage.TRACE__CURRENT_STATE:
				return currentState != null;
			case RichgenerictracePackage.TRACE__CURRENT_STEP:
				return currentStep != null;
		}
		return super.eIsSet(featureID);
	}

} //TraceImpl
