/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.ExecutionState;
import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Step;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getNextStep <em>Next Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getPreviousStep <em>Previous Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getEndingState <em>Ending State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getStartingState <em>Starting State</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getParentStep <em>Parent Step</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getAppliedRule <em>Applied Rule</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.StepImpl#getChildrenSteps <em>Children Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StepImpl extends MinimalEObjectImpl.Container implements Step {
	/**
	 * The cached value of the '{@link #getNextStep() <em>Next Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextStep()
	 * @generated
	 * @ordered
	 */
	protected Step nextStep;

	/**
	 * The cached value of the '{@link #getPreviousStep() <em>Previous Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreviousStep()
	 * @generated
	 * @ordered
	 */
	protected Step previousStep;

	/**
	 * The cached value of the '{@link #getEndingState() <em>Ending State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndingState()
	 * @generated
	 * @ordered
	 */
	protected ExecutionState endingState;

	/**
	 * The cached value of the '{@link #getParentStep() <em>Parent Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentStep()
	 * @generated
	 * @ordered
	 */
	protected Step parentStep;

	/**
	 * The cached value of the '{@link #getAppliedRule() <em>Applied Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppliedRule()
	 * @generated
	 * @ordered
	 */
	protected EOperation appliedRule;

	/**
	 * The cached value of the '{@link #getChildrenSteps() <em>Children Steps</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildrenSteps()
	 * @generated
	 * @ordered
	 */
	protected Step childrenSteps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RichgenerictracePackage.Literals.STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step getNextStep() {
		if (nextStep != null && nextStep.eIsProxy()) {
			InternalEObject oldNextStep = (InternalEObject)nextStep;
			nextStep = (Step)eResolveProxy(oldNextStep);
			if (nextStep != oldNextStep) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.STEP__NEXT_STEP, oldNextStep, nextStep));
			}
		}
		return nextStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step basicGetNextStep() {
		return nextStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNextStep(Step newNextStep, NotificationChain msgs) {
		Step oldNextStep = nextStep;
		nextStep = newNextStep;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__NEXT_STEP, oldNextStep, newNextStep);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextStep(Step newNextStep) {
		if (newNextStep != nextStep) {
			NotificationChain msgs = null;
			if (nextStep != null)
				msgs = ((InternalEObject)nextStep).eInverseRemove(this, RichgenerictracePackage.STEP__PREVIOUS_STEP, Step.class, msgs);
			if (newNextStep != null)
				msgs = ((InternalEObject)newNextStep).eInverseAdd(this, RichgenerictracePackage.STEP__PREVIOUS_STEP, Step.class, msgs);
			msgs = basicSetNextStep(newNextStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__NEXT_STEP, newNextStep, newNextStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step getPreviousStep() {
		if (previousStep != null && previousStep.eIsProxy()) {
			InternalEObject oldPreviousStep = (InternalEObject)previousStep;
			previousStep = (Step)eResolveProxy(oldPreviousStep);
			if (previousStep != oldPreviousStep) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.STEP__PREVIOUS_STEP, oldPreviousStep, previousStep));
			}
		}
		return previousStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step basicGetPreviousStep() {
		return previousStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPreviousStep(Step newPreviousStep, NotificationChain msgs) {
		Step oldPreviousStep = previousStep;
		previousStep = newPreviousStep;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__PREVIOUS_STEP, oldPreviousStep, newPreviousStep);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreviousStep(Step newPreviousStep) {
		if (newPreviousStep != previousStep) {
			NotificationChain msgs = null;
			if (previousStep != null)
				msgs = ((InternalEObject)previousStep).eInverseRemove(this, RichgenerictracePackage.STEP__NEXT_STEP, Step.class, msgs);
			if (newPreviousStep != null)
				msgs = ((InternalEObject)newPreviousStep).eInverseAdd(this, RichgenerictracePackage.STEP__NEXT_STEP, Step.class, msgs);
			msgs = basicSetPreviousStep(newPreviousStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__PREVIOUS_STEP, newPreviousStep, newPreviousStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getEndingState() {
		if (endingState != null && endingState.eIsProxy()) {
			InternalEObject oldEndingState = (InternalEObject)endingState;
			endingState = (ExecutionState)eResolveProxy(oldEndingState);
			if (endingState != oldEndingState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.STEP__ENDING_STATE, oldEndingState, endingState));
			}
		}
		return endingState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState basicGetEndingState() {
		return endingState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndingState(ExecutionState newEndingState, NotificationChain msgs) {
		ExecutionState oldEndingState = endingState;
		endingState = newEndingState;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__ENDING_STATE, oldEndingState, newEndingState);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndingState(ExecutionState newEndingState) {
		if (newEndingState != endingState) {
			NotificationChain msgs = null;
			if (endingState != null)
				msgs = ((InternalEObject)endingState).eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS, ExecutionState.class, msgs);
			if (newEndingState != null)
				msgs = ((InternalEObject)newEndingState).eInverseAdd(this, RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS, ExecutionState.class, msgs);
			msgs = basicSetEndingState(newEndingState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__ENDING_STATE, newEndingState, newEndingState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getStartingState() {
		if (eContainerFeatureID() != RichgenerictracePackage.STEP__STARTING_STATE) return null;
		return (ExecutionState)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartingState(ExecutionState newStartingState, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStartingState, RichgenerictracePackage.STEP__STARTING_STATE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartingState(ExecutionState newStartingState) {
		if (newStartingState != eInternalContainer() || (eContainerFeatureID() != RichgenerictracePackage.STEP__STARTING_STATE && newStartingState != null)) {
			if (EcoreUtil.isAncestor(this, newStartingState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStartingState != null)
				msgs = ((InternalEObject)newStartingState).eInverseAdd(this, RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS, ExecutionState.class, msgs);
			msgs = basicSetStartingState(newStartingState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__STARTING_STATE, newStartingState, newStartingState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step getParentStep() {
		if (parentStep != null && parentStep.eIsProxy()) {
			InternalEObject oldParentStep = (InternalEObject)parentStep;
			parentStep = (Step)eResolveProxy(oldParentStep);
			if (parentStep != oldParentStep) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.STEP__PARENT_STEP, oldParentStep, parentStep));
			}
		}
		return parentStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step basicGetParentStep() {
		return parentStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentStep(Step newParentStep, NotificationChain msgs) {
		Step oldParentStep = parentStep;
		parentStep = newParentStep;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__PARENT_STEP, oldParentStep, newParentStep);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentStep(Step newParentStep) {
		if (newParentStep != parentStep) {
			NotificationChain msgs = null;
			if (parentStep != null)
				msgs = ((InternalEObject)parentStep).eInverseRemove(this, RichgenerictracePackage.STEP__CHILDREN_STEPS, Step.class, msgs);
			if (newParentStep != null)
				msgs = ((InternalEObject)newParentStep).eInverseAdd(this, RichgenerictracePackage.STEP__CHILDREN_STEPS, Step.class, msgs);
			msgs = basicSetParentStep(newParentStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__PARENT_STEP, newParentStep, newParentStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAppliedRule() {
		if (appliedRule != null && appliedRule.eIsProxy()) {
			InternalEObject oldAppliedRule = (InternalEObject)appliedRule;
			appliedRule = (EOperation)eResolveProxy(oldAppliedRule);
			if (appliedRule != oldAppliedRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.STEP__APPLIED_RULE, oldAppliedRule, appliedRule));
			}
		}
		return appliedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation basicGetAppliedRule() {
		return appliedRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppliedRule(EOperation newAppliedRule) {
		EOperation oldAppliedRule = appliedRule;
		appliedRule = newAppliedRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__APPLIED_RULE, oldAppliedRule, appliedRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step getChildrenSteps() {
		if (childrenSteps != null && childrenSteps.eIsProxy()) {
			InternalEObject oldChildrenSteps = (InternalEObject)childrenSteps;
			childrenSteps = (Step)eResolveProxy(oldChildrenSteps);
			if (childrenSteps != oldChildrenSteps) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.STEP__CHILDREN_STEPS, oldChildrenSteps, childrenSteps));
			}
		}
		return childrenSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Step basicGetChildrenSteps() {
		return childrenSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChildrenSteps(Step newChildrenSteps, NotificationChain msgs) {
		Step oldChildrenSteps = childrenSteps;
		childrenSteps = newChildrenSteps;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__CHILDREN_STEPS, oldChildrenSteps, newChildrenSteps);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChildrenSteps(Step newChildrenSteps) {
		if (newChildrenSteps != childrenSteps) {
			NotificationChain msgs = null;
			if (childrenSteps != null)
				msgs = ((InternalEObject)childrenSteps).eInverseRemove(this, RichgenerictracePackage.STEP__PARENT_STEP, Step.class, msgs);
			if (newChildrenSteps != null)
				msgs = ((InternalEObject)newChildrenSteps).eInverseAdd(this, RichgenerictracePackage.STEP__PARENT_STEP, Step.class, msgs);
			msgs = basicSetChildrenSteps(newChildrenSteps, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.STEP__CHILDREN_STEPS, newChildrenSteps, newChildrenSteps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RichgenerictracePackage.STEP__NEXT_STEP:
				if (nextStep != null)
					msgs = ((InternalEObject)nextStep).eInverseRemove(this, RichgenerictracePackage.STEP__PREVIOUS_STEP, Step.class, msgs);
				return basicSetNextStep((Step)otherEnd, msgs);
			case RichgenerictracePackage.STEP__PREVIOUS_STEP:
				if (previousStep != null)
					msgs = ((InternalEObject)previousStep).eInverseRemove(this, RichgenerictracePackage.STEP__NEXT_STEP, Step.class, msgs);
				return basicSetPreviousStep((Step)otherEnd, msgs);
			case RichgenerictracePackage.STEP__ENDING_STATE:
				if (endingState != null)
					msgs = ((InternalEObject)endingState).eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__ENDING_STEPS, ExecutionState.class, msgs);
				return basicSetEndingState((ExecutionState)otherEnd, msgs);
			case RichgenerictracePackage.STEP__STARTING_STATE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStartingState((ExecutionState)otherEnd, msgs);
			case RichgenerictracePackage.STEP__PARENT_STEP:
				if (parentStep != null)
					msgs = ((InternalEObject)parentStep).eInverseRemove(this, RichgenerictracePackage.STEP__CHILDREN_STEPS, Step.class, msgs);
				return basicSetParentStep((Step)otherEnd, msgs);
			case RichgenerictracePackage.STEP__CHILDREN_STEPS:
				if (childrenSteps != null)
					msgs = ((InternalEObject)childrenSteps).eInverseRemove(this, RichgenerictracePackage.STEP__PARENT_STEP, Step.class, msgs);
				return basicSetChildrenSteps((Step)otherEnd, msgs);
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
			case RichgenerictracePackage.STEP__NEXT_STEP:
				return basicSetNextStep(null, msgs);
			case RichgenerictracePackage.STEP__PREVIOUS_STEP:
				return basicSetPreviousStep(null, msgs);
			case RichgenerictracePackage.STEP__ENDING_STATE:
				return basicSetEndingState(null, msgs);
			case RichgenerictracePackage.STEP__STARTING_STATE:
				return basicSetStartingState(null, msgs);
			case RichgenerictracePackage.STEP__PARENT_STEP:
				return basicSetParentStep(null, msgs);
			case RichgenerictracePackage.STEP__CHILDREN_STEPS:
				return basicSetChildrenSteps(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RichgenerictracePackage.STEP__STARTING_STATE:
				return eInternalContainer().eInverseRemove(this, RichgenerictracePackage.EXECUTION_STATE__STARTING_STEPS, ExecutionState.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RichgenerictracePackage.STEP__NEXT_STEP:
				if (resolve) return getNextStep();
				return basicGetNextStep();
			case RichgenerictracePackage.STEP__PREVIOUS_STEP:
				if (resolve) return getPreviousStep();
				return basicGetPreviousStep();
			case RichgenerictracePackage.STEP__ENDING_STATE:
				if (resolve) return getEndingState();
				return basicGetEndingState();
			case RichgenerictracePackage.STEP__STARTING_STATE:
				return getStartingState();
			case RichgenerictracePackage.STEP__PARENT_STEP:
				if (resolve) return getParentStep();
				return basicGetParentStep();
			case RichgenerictracePackage.STEP__APPLIED_RULE:
				if (resolve) return getAppliedRule();
				return basicGetAppliedRule();
			case RichgenerictracePackage.STEP__CHILDREN_STEPS:
				if (resolve) return getChildrenSteps();
				return basicGetChildrenSteps();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RichgenerictracePackage.STEP__NEXT_STEP:
				setNextStep((Step)newValue);
				return;
			case RichgenerictracePackage.STEP__PREVIOUS_STEP:
				setPreviousStep((Step)newValue);
				return;
			case RichgenerictracePackage.STEP__ENDING_STATE:
				setEndingState((ExecutionState)newValue);
				return;
			case RichgenerictracePackage.STEP__STARTING_STATE:
				setStartingState((ExecutionState)newValue);
				return;
			case RichgenerictracePackage.STEP__PARENT_STEP:
				setParentStep((Step)newValue);
				return;
			case RichgenerictracePackage.STEP__APPLIED_RULE:
				setAppliedRule((EOperation)newValue);
				return;
			case RichgenerictracePackage.STEP__CHILDREN_STEPS:
				setChildrenSteps((Step)newValue);
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
			case RichgenerictracePackage.STEP__NEXT_STEP:
				setNextStep((Step)null);
				return;
			case RichgenerictracePackage.STEP__PREVIOUS_STEP:
				setPreviousStep((Step)null);
				return;
			case RichgenerictracePackage.STEP__ENDING_STATE:
				setEndingState((ExecutionState)null);
				return;
			case RichgenerictracePackage.STEP__STARTING_STATE:
				setStartingState((ExecutionState)null);
				return;
			case RichgenerictracePackage.STEP__PARENT_STEP:
				setParentStep((Step)null);
				return;
			case RichgenerictracePackage.STEP__APPLIED_RULE:
				setAppliedRule((EOperation)null);
				return;
			case RichgenerictracePackage.STEP__CHILDREN_STEPS:
				setChildrenSteps((Step)null);
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
			case RichgenerictracePackage.STEP__NEXT_STEP:
				return nextStep != null;
			case RichgenerictracePackage.STEP__PREVIOUS_STEP:
				return previousStep != null;
			case RichgenerictracePackage.STEP__ENDING_STATE:
				return endingState != null;
			case RichgenerictracePackage.STEP__STARTING_STATE:
				return getStartingState() != null;
			case RichgenerictracePackage.STEP__PARENT_STEP:
				return parentStep != null;
			case RichgenerictracePackage.STEP__APPLIED_RULE:
				return appliedRule != null;
			case RichgenerictracePackage.STEP__CHILDREN_STEPS:
				return childrenSteps != null;
		}
		return super.eIsSet(featureID);
	}

} //StepImpl
