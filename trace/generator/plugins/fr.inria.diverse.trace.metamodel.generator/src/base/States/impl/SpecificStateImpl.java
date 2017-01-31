/**
 */
package base.States.impl;

import base.States.SpecificState;
import base.States.SpecificValue;
import base.States.StatesPackage;

import base.Steps.SpecificStep;

import fr.inria.diverse.trace.commons.model.trace.impl.StateImpl;

import org.eclipse.emf.ecore.EClass;

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

} //SpecificStateImpl
