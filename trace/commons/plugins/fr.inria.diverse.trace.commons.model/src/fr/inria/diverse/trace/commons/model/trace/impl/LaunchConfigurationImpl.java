/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.LaunchConfiguration;
import fr.inria.diverse.trace.commons.model.trace.LaunchConfigurationParameter;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Launch Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getLaunchconfigurationparameter <em>Launchconfigurationparameter</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.impl.LaunchConfigurationImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LaunchConfigurationImpl extends MinimalEObjectImpl.Container implements LaunchConfiguration {
	/**
	 * The cached value of the '{@link #getLaunchconfigurationparameter() <em>Launchconfigurationparameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaunchconfigurationparameter()
	 * @generated
	 * @ordered
	 */
	protected EList<LaunchConfigurationParameter> launchconfigurationparameter;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LaunchConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.LAUNCH_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LaunchConfigurationParameter> getLaunchconfigurationparameter() {
		if (launchconfigurationparameter == null) {
			launchconfigurationparameter = new EObjectContainmentEList<LaunchConfigurationParameter>(LaunchConfigurationParameter.class, this, TracePackage.LAUNCH_CONFIGURATION__LAUNCHCONFIGURATIONPARAMETER);
		}
		return launchconfigurationparameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LAUNCH_CONFIGURATION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.LAUNCH_CONFIGURATION__LAUNCHCONFIGURATIONPARAMETER:
				return ((InternalEList<?>)getLaunchconfigurationparameter()).basicRemove(otherEnd, msgs);
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
			case TracePackage.LAUNCH_CONFIGURATION__LAUNCHCONFIGURATIONPARAMETER:
				return getLaunchconfigurationparameter();
			case TracePackage.LAUNCH_CONFIGURATION__TYPE:
				return getType();
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
			case TracePackage.LAUNCH_CONFIGURATION__LAUNCHCONFIGURATIONPARAMETER:
				getLaunchconfigurationparameter().clear();
				getLaunchconfigurationparameter().addAll((Collection<? extends LaunchConfigurationParameter>)newValue);
				return;
			case TracePackage.LAUNCH_CONFIGURATION__TYPE:
				setType((String)newValue);
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
			case TracePackage.LAUNCH_CONFIGURATION__LAUNCHCONFIGURATIONPARAMETER:
				getLaunchconfigurationparameter().clear();
				return;
			case TracePackage.LAUNCH_CONFIGURATION__TYPE:
				setType(TYPE_EDEFAULT);
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
			case TracePackage.LAUNCH_CONFIGURATION__LAUNCHCONFIGURATIONPARAMETER:
				return launchconfigurationparameter != null && !launchconfigurationparameter.isEmpty();
			case TracePackage.LAUNCH_CONFIGURATION__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		}
		return super.eIsSet(featureID);
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //LaunchConfigurationImpl
