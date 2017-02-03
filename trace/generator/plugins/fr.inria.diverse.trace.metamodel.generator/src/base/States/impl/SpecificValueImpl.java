/**
 */
package base.States.impl;

import base.States.SpecificState;
import base.States.SpecificValue;
import base.States.StatesPackage;

import fr.inria.diverse.trace.commons.model.trace.impl.ValueImpl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specific Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class SpecificValueImpl extends ValueImpl<SpecificState> implements SpecificValue {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatesPackage.Literals.SPECIFIC_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecificState> getStates() {
		final EList<SpecificState> result = new org.eclipse.emf.common.util.BasicEList<SpecificState>();
		result.addAll(getStates());
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case StatesPackage.SPECIFIC_VALUE___GET_STATES:
				return getStates();
		}
		return super.eInvoke(operationID, arguments);
	}

} //SpecificValueImpl
