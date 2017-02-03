/**
 */
package base.States.impl;

import base.States.SpecificState;
import base.States.SpecificValue;
import base.States.StatesPackage;

import base.Steps.SpecificStep;
import base.Steps.StepsPackage;

import fr.inria.diverse.trace.commons.model.trace.impl.StateImpl;

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
 * An implementation of the model object '<em><b>Specific State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link base.States.impl.SpecificStateImpl#getStartedStepsRef <em>Started Steps Ref</em>}</li>
 *   <li>{@link base.States.impl.SpecificStateImpl#getEndedStepsRef <em>Ended Steps Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpecificStateImpl extends StateImpl<SpecificStep, SpecificValue> implements SpecificState {
	/**
	 * The cached value of the '{@link #getStartedStepsRef() <em>Started Steps Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartedStepsRef()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecificStep> startedStepsRef;

	/**
	 * The cached value of the '{@link #getEndedStepsRef() <em>Ended Steps Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndedStepsRef()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecificStep> endedStepsRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatesPackage.Literals.SPECIFIC_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecificStep> getStartedStepsRef() {
		if (startedStepsRef == null) {
			startedStepsRef = new EObjectWithInverseResolvingEList<SpecificStep>(SpecificStep.class, this, StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF, StepsPackage.SPECIFIC_STEP__STARTING_STATE_REF);
		}
		return startedStepsRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecificStep> getEndedStepsRef() {
		if (endedStepsRef == null) {
			endedStepsRef = new EObjectWithInverseResolvingEList<SpecificStep>(SpecificStep.class, this, StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF, StepsPackage.SPECIFIC_STEP__ENDING_STATE_REF);
		}
		return endedStepsRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecificStep> getStartedSteps() {
		return getStartedStepsRef();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecificStep> getEndedSteps() {
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
			case StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStartedStepsRef()).basicAdd(otherEnd, msgs);
			case StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF:
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
			case StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF:
				return ((InternalEList<?>)getStartedStepsRef()).basicRemove(otherEnd, msgs);
			case StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF:
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
			case StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF:
				return getStartedStepsRef();
			case StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF:
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
			case StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF:
				getStartedStepsRef().clear();
				getStartedStepsRef().addAll((Collection<? extends SpecificStep>)newValue);
				return;
			case StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF:
				getEndedStepsRef().clear();
				getEndedStepsRef().addAll((Collection<? extends SpecificStep>)newValue);
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
			case StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF:
				getStartedStepsRef().clear();
				return;
			case StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF:
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
			case StatesPackage.SPECIFIC_STATE__STARTED_STEPS_REF:
				return startedStepsRef != null && !startedStepsRef.isEmpty();
			case StatesPackage.SPECIFIC_STATE__ENDED_STEPS_REF:
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
			case StatesPackage.SPECIFIC_STATE___GET_STARTED_STEPS:
				return getStartedSteps();
			case StatesPackage.SPECIFIC_STATE___GET_ENDED_STEPS:
				return getEndedSteps();
		}
		return super.eInvoke(operationID, arguments);
	}

} //SpecificStateImpl
