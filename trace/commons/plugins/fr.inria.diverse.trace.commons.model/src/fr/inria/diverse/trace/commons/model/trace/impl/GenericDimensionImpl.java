/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericDimension;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import fr.inria.diverse.trace.commons.model.trace.Value;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Dimension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.GenericDimensionImpl#getDynamicProperty <em>Dynamic Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenericDimensionImpl extends DimensionImpl<Value> implements GenericDimension {
	/**
	 * The cached value of the '{@link #getDynamicProperty() <em>Dynamic Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicProperty()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature dynamicProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericDimensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.GENERIC_DIMENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getDynamicProperty() {
		if (dynamicProperty != null && dynamicProperty.eIsProxy()) {
			InternalEObject oldDynamicProperty = (InternalEObject)dynamicProperty;
			dynamicProperty = (EStructuralFeature)eResolveProxy(oldDynamicProperty);
			if (dynamicProperty != oldDynamicProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TracePackage.GENERIC_DIMENSION__DYNAMIC_PROPERTY, oldDynamicProperty, dynamicProperty));
			}
		}
		return dynamicProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetDynamicProperty() {
		return dynamicProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicProperty(EStructuralFeature newDynamicProperty) {
		EStructuralFeature oldDynamicProperty = dynamicProperty;
		dynamicProperty = newDynamicProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.GENERIC_DIMENSION__DYNAMIC_PROPERTY, oldDynamicProperty, dynamicProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracePackage.GENERIC_DIMENSION__DYNAMIC_PROPERTY:
				if (resolve) return getDynamicProperty();
				return basicGetDynamicProperty();
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
			case TracePackage.GENERIC_DIMENSION__DYNAMIC_PROPERTY:
				setDynamicProperty((EStructuralFeature)newValue);
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
			case TracePackage.GENERIC_DIMENSION__DYNAMIC_PROPERTY:
				setDynamicProperty((EStructuralFeature)null);
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
			case TracePackage.GENERIC_DIMENSION__DYNAMIC_PROPERTY:
				return dynamicProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //GenericDimensionImpl
