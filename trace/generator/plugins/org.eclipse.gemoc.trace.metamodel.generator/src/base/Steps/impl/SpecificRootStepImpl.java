/**
 */
package base.Steps.impl;

import base.States.SpecificState;
import base.Steps.SpecificRootStep;
import base.Steps.SpecificStep;
import base.Steps.StepsPackage;

import org.eclipse.gemoc.trace.commons.model.trace.impl.SequentialStepImpl;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specific Root Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SpecificRootStepImpl extends SequentialStepImpl<SpecificStep, SpecificState> implements SpecificRootStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificRootStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StepsPackage.Literals.SPECIFIC_ROOT_STEP;
	}

} //SpecificRootStepImpl
