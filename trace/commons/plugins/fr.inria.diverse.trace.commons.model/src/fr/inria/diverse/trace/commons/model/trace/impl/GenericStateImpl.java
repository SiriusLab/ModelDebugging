/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericState;
import fr.inria.diverse.trace.commons.model.trace.GenericStep;
import fr.inria.diverse.trace.commons.model.trace.GenericValue;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl#getValuesRef <em>Values Ref</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl#getStartedStepsRef <em>Started Steps Ref</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericStateImpl#getEndedStepsRef <em>Ended Steps Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericStateImpl extends StateImpl<GenericStep, GenericValue> implements GenericState {
	/**
	 * The cached value of the '{@link #getValuesRef() <em>Values Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValuesRef()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericValue> valuesRef;

	/**
	 * The cached value of the '{@link #getStartedStepsRef() <em>Started Steps Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartedStepsRef()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericStep> startedStepsRef;

	/**
	 * The cached value of the '{@link #getEndedStepsRef() <em>Ended Steps Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndedStepsRef()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericStep> endedStepsRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.GENERIC_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<GenericStep> getStartedSteps() {
		if (startedSteps == null) {
			startedSteps = new EObjectWithInverseResolvingEList<GenericStep>(GenericStep.class, this, TracePackage.GENERIC_STATE__STARTED_STEPS, TracePackage.STEP__STARTING_STATE) { private static final long serialVersionUID = 1L; @Override public Class<?> getInverseFeatureClass() { return Step.class; } };
		}
		return startedSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<GenericStep> getEndedSteps() {
		if (endedSteps == null) {
			endedSteps = new EObjectWithInverseResolvingEList<GenericStep>(GenericStep.class, this, TracePackage.GENERIC_STATE__ENDED_STEPS, TracePackage.STEP__ENDING_STATE) { private static final long serialVersionUID = 1L; @Override public Class<?> getInverseFeatureClass() { return Step.class; } };
		}
		return endedSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericValue> getValuesRef() {
		if (valuesRef == null) {
			valuesRef = new EObjectWithInverseResolvingEList.ManyInverse<GenericValue>(GenericValue.class, this, TracePackage.GENERIC_STATE__VALUES_REF, TracePackage.GENERIC_VALUE__STATES_REF);
		}
		return valuesRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericStep> getStartedStepsRef() {
		if (startedStepsRef == null) {
			startedStepsRef = new EObjectWithInverseResolvingEList<GenericStep>(GenericStep.class, this, TracePackage.GENERIC_STATE__STARTED_STEPS_REF, TracePackage.GENERIC_STEP__STARTING_STATE_REF);
		}
		return startedStepsRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericStep> getEndedStepsRef() {
		if (endedStepsRef == null) {
			endedStepsRef = new EObjectWithInverseResolvingEList<GenericStep>(GenericStep.class, this, TracePackage.GENERIC_STATE__ENDED_STEPS_REF, TracePackage.GENERIC_STEP__ENDING_STATE_REF);
		}
		return endedStepsRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericValue> getValues() {
		return getValuesRef();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericStep> getStartedSteps() {
		return getStartedStepsRef();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericStep> getEndedSteps() {
		return getEndedStepsRef();
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
			case TracePackage.GENERIC_STATE__VALUES_REF:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getValuesRef()).basicAdd(otherEnd, msgs);
			case TracePackage.GENERIC_STATE__STARTED_STEPS_REF:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStartedStepsRef()).basicAdd(otherEnd, msgs);
			case TracePackage.GENERIC_STATE__ENDED_STEPS_REF:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEndedStepsRef()).basicAdd(otherEnd, msgs);
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
			case TracePackage.GENERIC_STATE__VALUES_REF:
				return ((InternalEList<?>)getValuesRef()).basicRemove(otherEnd, msgs);
			case TracePackage.GENERIC_STATE__STARTED_STEPS_REF:
				return ((InternalEList<?>)getStartedStepsRef()).basicRemove(otherEnd, msgs);
			case TracePackage.GENERIC_STATE__ENDED_STEPS_REF:
				return ((InternalEList<?>)getEndedStepsRef()).basicRemove(otherEnd, msgs);
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
			case TracePackage.GENERIC_STATE__VALUES_REF:
				return getValuesRef();
			case TracePackage.GENERIC_STATE__STARTED_STEPS_REF:
				return getStartedStepsRef();
			case TracePackage.GENERIC_STATE__ENDED_STEPS_REF:
				return getEndedStepsRef();
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
			case TracePackage.GENERIC_STATE__VALUES_REF:
				getValuesRef().clear();
				getValuesRef().addAll((Collection<? extends GenericValue>)newValue);
				return;
			case TracePackage.GENERIC_STATE__STARTED_STEPS_REF:
				getStartedStepsRef().clear();
				getStartedStepsRef().addAll((Collection<? extends GenericStep>)newValue);
				return;
			case TracePackage.GENERIC_STATE__ENDED_STEPS_REF:
				getEndedStepsRef().clear();
				getEndedStepsRef().addAll((Collection<? extends GenericStep>)newValue);
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
			case TracePackage.GENERIC_STATE__VALUES_REF:
				getValuesRef().clear();
				return;
			case TracePackage.GENERIC_STATE__STARTED_STEPS_REF:
				getStartedStepsRef().clear();
				return;
			case TracePackage.GENERIC_STATE__ENDED_STEPS_REF:
				getEndedStepsRef().clear();
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
			case TracePackage.GENERIC_STATE__VALUES_REF:
				return valuesRef != null && !valuesRef.isEmpty();
			case TracePackage.GENERIC_STATE__STARTED_STEPS_REF:
				return startedStepsRef != null && !startedStepsRef.isEmpty();
			case TracePackage.GENERIC_STATE__ENDED_STEPS_REF:
				return endedStepsRef != null && !endedStepsRef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case TracePackage.GENERIC_STATE___GET_VALUES:
				return getValues();
			case TracePackage.GENERIC_STATE___GET_STARTED_STEPS:
				return getStartedSteps();
			case TracePackage.GENERIC_STATE___GET_ENDED_STEPS:
				return getEndedSteps();
		}
		return super.eInvoke(operationID, arguments);
	}

} //GenericStateImpl
