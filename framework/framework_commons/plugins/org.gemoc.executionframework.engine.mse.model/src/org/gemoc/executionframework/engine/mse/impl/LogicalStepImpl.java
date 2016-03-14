/**
 */
package org.gemoc.executionframework.engine.mse.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.mse.MsePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.LogicalStepImpl#getMseOccurrences <em>Mse Occurrences</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicalStepImpl extends MinimalEObjectImpl.Container implements LogicalStep {
	/**
	 * The cached value of the '{@link #getMseOccurrences() <em>Mse Occurrences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMseOccurrences()
	 * @generated
	 * @ordered
	 */
	protected EList<MSEOccurrence> mseOccurrences;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogicalStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.LOGICAL_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MSEOccurrence> getMseOccurrences() {
		if (mseOccurrences == null) {
			mseOccurrences = new EObjectContainmentWithInverseEList<MSEOccurrence>(MSEOccurrence.class, this, MsePackage.LOGICAL_STEP__MSE_OCCURRENCES, MsePackage.MSE_OCCURRENCE__LOGICAL_STEP);
		}
		return mseOccurrences;
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
			case MsePackage.LOGICAL_STEP__MSE_OCCURRENCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMseOccurrences()).basicAdd(otherEnd, msgs);
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
			case MsePackage.LOGICAL_STEP__MSE_OCCURRENCES:
				return ((InternalEList<?>)getMseOccurrences()).basicRemove(otherEnd, msgs);
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
			case MsePackage.LOGICAL_STEP__MSE_OCCURRENCES:
				return getMseOccurrences();
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
			case MsePackage.LOGICAL_STEP__MSE_OCCURRENCES:
				getMseOccurrences().clear();
				getMseOccurrences().addAll((Collection<? extends MSEOccurrence>)newValue);
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
			case MsePackage.LOGICAL_STEP__MSE_OCCURRENCES:
				getMseOccurrences().clear();
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
			case MsePackage.LOGICAL_STEP__MSE_OCCURRENCES:
				return mseOccurrences != null && !mseOccurrences.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LogicalStepImpl
