/**
 */
package base.States.impl;

import base.States.SpecificState;
import base.States.SpecificValue;
import base.States.StatesPackage;

import base.Steps.SpecificStep;
import fr.inria.diverse.trace.commons.model.trace.Step;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;
import fr.inria.diverse.trace.commons.model.trace.Value;
import fr.inria.diverse.trace.commons.model.trace.impl.StateImpl;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specific State</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SpecificStateImpl extends StateImpl<SpecificStep, SpecificValue> implements SpecificState {
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
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<SpecificStep> getStartedSteps() {
		if (startedSteps == null) {
			startedSteps = new EObjectWithInverseResolvingEList<SpecificStep>(SpecificStep.class, this, StatesPackage.SPECIFIC_STATE__STARTED_STEPS, TracePackage.STEP__STARTING_STATE) { private static final long serialVersionUID = 1L; @Override public Class<?> getInverseFeatureClass() { return Step.class; } };
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
	public EList<SpecificStep> getEndedSteps() {
		if (endedSteps == null) {
			endedSteps = new EObjectWithInverseResolvingEList<SpecificStep>(SpecificStep.class, this, StatesPackage.SPECIFIC_STATE__ENDED_STEPS, TracePackage.STEP__ENDING_STATE) { private static final long serialVersionUID = 1L; @Override public Class<?> getInverseFeatureClass() { return Step.class; } };
		}
		return endedSteps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<SpecificValue> getValues() {
		if (values == null) {
			values = new EObjectWithInverseResolvingEList.ManyInverse<SpecificValue>(SpecificValue.class, this, StatesPackage.SPECIFIC_STATE__VALUES, TracePackage.VALUE__STATES) { private static final long serialVersionUID = 1L; @Override public Class<?> getInverseFeatureClass() { return Value.class; } };
		}
		return values;
	}

} //SpecificStateImpl
