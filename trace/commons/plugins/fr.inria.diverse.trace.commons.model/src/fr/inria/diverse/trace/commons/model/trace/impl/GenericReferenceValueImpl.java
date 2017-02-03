/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericReferenceValue;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Reference Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericReferenceValueImpl#getReferenceValue <em>Reference Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericReferenceValueImpl extends GenericValueImpl implements GenericReferenceValue {
	/**
	 * The cached value of the '{@link #getReferenceValue() <em>Reference Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceValue()
	 * @generated
	 * @ordered
	 */
	protected EObject referenceValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericReferenceValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.GENERIC_REFERENCE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getReferenceValue() {
		if (referenceValue != null && referenceValue.eIsProxy()) {
			InternalEObject oldReferenceValue = (InternalEObject)referenceValue;
			referenceValue = eResolveProxy(oldReferenceValue);
			if (referenceValue != oldReferenceValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.GENERIC_REFERENCE_VALUE__REFERENCE_VALUE, oldReferenceValue, referenceValue));
			}
		}
		return referenceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetReferenceValue() {
		return referenceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceValue(EObject newReferenceValue) {
		EObject oldReferenceValue = referenceValue;
		referenceValue = newReferenceValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.GENERIC_REFERENCE_VALUE__REFERENCE_VALUE, oldReferenceValue, referenceValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracePackage.GENERIC_REFERENCE_VALUE__REFERENCE_VALUE:
				if (resolve) return getReferenceValue();
				return basicGetReferenceValue();
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
			case TracePackage.GENERIC_REFERENCE_VALUE__REFERENCE_VALUE:
				setReferenceValue((EObject)newValue);
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
			case TracePackage.GENERIC_REFERENCE_VALUE__REFERENCE_VALUE:
				setReferenceValue((EObject)null);
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
			case TracePackage.GENERIC_REFERENCE_VALUE__REFERENCE_VALUE:
				return referenceValue != null;
		}
		return super.eIsSet(featureID);
	}

} //GenericReferenceValueImpl
