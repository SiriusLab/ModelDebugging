/**
 */
package ecorext.impl;

import ecorext.EcorextPackage;
import ecorext.NewPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ecorext.impl.NewPackageImpl#getNewPackage <em>New Package</em>}</li>
 *   <li>{@link ecorext.impl.NewPackageImpl#getContainingExistingPackage <em>Containing Existing Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewPackageImpl extends MinimalEObjectImpl.Container implements NewPackage {
	/**
	 * The cached value of the '{@link #getNewPackage() <em>New Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage newPackage;

	/**
	 * The cached value of the '{@link #getContainingExistingPackage() <em>Containing Existing Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingExistingPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage containingExistingPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcorextPackage.Literals.NEW_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getNewPackage() {
		return newPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewPackage(EPackage newNewPackage, NotificationChain msgs) {
		EPackage oldNewPackage = newPackage;
		newPackage = newNewPackage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorextPackage.NEW_PACKAGE__NEW_PACKAGE, oldNewPackage, newNewPackage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewPackage(EPackage newNewPackage) {
		if (newNewPackage != newPackage) {
			NotificationChain msgs = null;
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EcorextPackage.NEW_PACKAGE__NEW_PACKAGE, null, msgs);
			if (newNewPackage != null)
				msgs = ((InternalEObject)newNewPackage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EcorextPackage.NEW_PACKAGE__NEW_PACKAGE, null, msgs);
			msgs = basicSetNewPackage(newNewPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcorextPackage.NEW_PACKAGE__NEW_PACKAGE, newNewPackage, newNewPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getContainingExistingPackage() {
		if (containingExistingPackage != null && containingExistingPackage.eIsProxy()) {
			InternalEObject oldContainingExistingPackage = (InternalEObject)containingExistingPackage;
			containingExistingPackage = (EPackage)eResolveProxy(oldContainingExistingPackage);
			if (containingExistingPackage != oldContainingExistingPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorextPackage.NEW_PACKAGE__CONTAINING_EXISTING_PACKAGE, oldContainingExistingPackage, containingExistingPackage));
			}
		}
		return containingExistingPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetContainingExistingPackage() {
		return containingExistingPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingExistingPackage(EPackage newContainingExistingPackage) {
		EPackage oldContainingExistingPackage = containingExistingPackage;
		containingExistingPackage = newContainingExistingPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcorextPackage.NEW_PACKAGE__CONTAINING_EXISTING_PACKAGE, oldContainingExistingPackage, containingExistingPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EcorextPackage.NEW_PACKAGE__NEW_PACKAGE:
				return basicSetNewPackage(null, msgs);
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
			case EcorextPackage.NEW_PACKAGE__NEW_PACKAGE:
				return getNewPackage();
			case EcorextPackage.NEW_PACKAGE__CONTAINING_EXISTING_PACKAGE:
				if (resolve) return getContainingExistingPackage();
				return basicGetContainingExistingPackage();
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
			case EcorextPackage.NEW_PACKAGE__NEW_PACKAGE:
				setNewPackage((EPackage)newValue);
				return;
			case EcorextPackage.NEW_PACKAGE__CONTAINING_EXISTING_PACKAGE:
				setContainingExistingPackage((EPackage)newValue);
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
			case EcorextPackage.NEW_PACKAGE__NEW_PACKAGE:
				setNewPackage((EPackage)null);
				return;
			case EcorextPackage.NEW_PACKAGE__CONTAINING_EXISTING_PACKAGE:
				setContainingExistingPackage((EPackage)null);
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
			case EcorextPackage.NEW_PACKAGE__NEW_PACKAGE:
				return newPackage != null;
			case EcorextPackage.NEW_PACKAGE__CONTAINING_EXISTING_PACKAGE:
				return containingExistingPackage != null;
		}
		return super.eIsSet(featureID);
	}

} //NewPackageImpl
