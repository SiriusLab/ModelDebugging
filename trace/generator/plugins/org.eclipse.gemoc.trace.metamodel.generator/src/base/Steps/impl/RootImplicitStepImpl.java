/**
 */
package base.Steps.impl;

import base.States.SpecificState;

import base.Steps.RootImplicitStep;
import base.Steps.StepsPackage;

import org.eclipse.gemoc.trace.commons.model.trace.impl.SmallStepImpl;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root Implicit Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class RootImplicitStepImpl extends SmallStepImpl<SpecificState> implements RootImplicitStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootImplicitStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StepsPackage.Literals.ROOT_IMPLICIT_STEP;
	}

} //RootImplicitStepImpl
