/**
 */
package org.eclipse.gemoc.event.commons.model.property.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gemoc.event.commons.model.property.PropertyPackage;
import org.eclipse.gemoc.event.commons.model.property.StepProperty;
import org.eclipse.gemoc.event.commons.model.property.Stepping;

import org.eclipse.gemoc.event.commons.model.scenario.ElementProvider;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.impl.StepPropertyImpl#getStepping <em>Stepping</em>}</li>
 *   <li>{@link org.eclipse.gemoc.event.commons.model.property.impl.StepPropertyImpl#getTargetProvider <em>Target Provider</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StepPropertyImpl<T> extends PropertyImpl implements StepProperty<T> {
	/**
	 * The default value of the '{@link #getStepping() <em>Stepping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepping()
	 * @generated
	 * @ordered
	 */
	protected static final Stepping STEPPING_EDEFAULT = Stepping.ONGOING;

	/**
	 * The cached value of the '{@link #getStepping() <em>Stepping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepping()
	 * @generated
	 * @ordered
	 */
	protected Stepping stepping = STEPPING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTargetProvider() <em>Target Provider</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetProvider()
	 * @generated
	 * @ordered
	 */
	protected ElementProvider<T> targetProvider;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PropertyPackage.Literals.STEP_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stepping getStepping() {
		return stepping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStepping(Stepping newStepping) {
		Stepping oldStepping = stepping;
		stepping = newStepping == null ? STEPPING_EDEFAULT : newStepping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyPackage.STEP_PROPERTY__STEPPING, oldStepping, stepping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementProvider<T> getTargetProvider() {
		return targetProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetProvider(ElementProvider<T> newTargetProvider, NotificationChain msgs) {
		ElementProvider<T> oldTargetProvider = targetProvider;
		targetProvider = newTargetProvider;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER, oldTargetProvider, newTargetProvider);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetProvider(ElementProvider<T> newTargetProvider) {
		if (newTargetProvider != targetProvider) {
			NotificationChain msgs = null;
			if (targetProvider != null)
				msgs = ((InternalEObject)targetProvider).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER, null, msgs);
			if (newTargetProvider != null)
				msgs = ((InternalEObject)newTargetProvider).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER, null, msgs);
			msgs = basicSetTargetProvider(newTargetProvider, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER, newTargetProvider, newTargetProvider));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getOperation() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER:
				return basicSetTargetProvider(null, msgs);
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
			case PropertyPackage.STEP_PROPERTY__STEPPING:
				return getStepping();
			case PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER:
				return getTargetProvider();
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
			case PropertyPackage.STEP_PROPERTY__STEPPING:
				setStepping((Stepping)newValue);
				return;
			case PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER:
				setTargetProvider((ElementProvider<T>)newValue);
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
			case PropertyPackage.STEP_PROPERTY__STEPPING:
				setStepping(STEPPING_EDEFAULT);
				return;
			case PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER:
				setTargetProvider((ElementProvider<T>)null);
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
			case PropertyPackage.STEP_PROPERTY__STEPPING:
				return stepping != STEPPING_EDEFAULT;
			case PropertyPackage.STEP_PROPERTY__TARGET_PROVIDER:
				return targetProvider != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case PropertyPackage.STEP_PROPERTY___GET_OPERATION:
				return getOperation();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (stepping: ");
		result.append(stepping);
		result.append(')');
		return result.toString();
	}

} //StepPropertyImpl
