/**
 */
package base.Steps.impl;

import base.States.SpecificState;
import base.Steps.SpecificStep;
import base.Steps.StepsPackage;

import org.eclipse.gemoc.trace.commons.model.trace.impl.StepImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specific Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class SpecificStepImpl extends StepImpl<SpecificState> implements SpecificStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StepsPackage.Literals.SPECIFIC_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public NotificationChain basicSetStartingState(SpecificState newStartingState, NotificationChain msgs) {
		return super.basicSetStartingState(newStartingState, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public NotificationChain basicSetEndingState(SpecificState newEndingState, NotificationChain msgs) {
		return super.basicSetEndingState(newEndingState, msgs);
	}

} //SpecificStepImpl
