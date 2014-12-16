/**
 */
package ecorext.impl;

import ecorext.EcorextPackage;
import ecorext.NewClass;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ecorext.impl.NewClassImpl#getNewClass <em>New Class</em>}</li>
 *   <li>{@link ecorext.impl.NewClassImpl#getContainingExistingPackage <em>Containing Existing Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewClassImpl extends MinimalEObjectImpl.Container implements NewClass {
	/**
	 * The cached value of the '{@link #getNewClass() <em>New Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewClass()
	 * @generated
	 * @ordered
	 */
	protected EClass newClass;

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
	protected NewClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcorextPackage.Literals.NEW_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNewClass() {
		return newClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNewClass(EClass newNewClass, NotificationChain msgs) {
		EClass oldNewClass = newClass;
		newClass = newNewClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorextPackage.NEW_CLASS__NEW_CLASS, oldNewClass, newNewClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewClass(EClass newNewClass) {
		if (newNewClass != newClass) {
			NotificationChain msgs = null;
			if (newClass != null)
				msgs = ((InternalEObject)newClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EcorextPackage.NEW_CLASS__NEW_CLASS, null, msgs);
			if (newNewClass != null)
				msgs = ((InternalEObject)newNewClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EcorextPackage.NEW_CLASS__NEW_CLASS, null, msgs);
			msgs = basicSetNewClass(newNewClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcorextPackage.NEW_CLASS__NEW_CLASS, newNewClass, newNewClass));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EcorextPackage.NEW_CLASS__CONTAINING_EXISTING_PACKAGE, oldContainingExistingPackage, containingExistingPackage));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EcorextPackage.NEW_CLASS__CONTAINING_EXISTING_PACKAGE, oldContainingExistingPackage, containingExistingPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EcorextPackage.NEW_CLASS__NEW_CLASS:
				return basicSetNewClass(null, msgs);
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
			case EcorextPackage.NEW_CLASS__NEW_CLASS:
				return getNewClass();
			case EcorextPackage.NEW_CLASS__CONTAINING_EXISTING_PACKAGE:
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
			case EcorextPackage.NEW_CLASS__NEW_CLASS:
				setNewClass((EClass)newValue);
				return;
			case EcorextPackage.NEW_CLASS__CONTAINING_EXISTING_PACKAGE:
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
			case EcorextPackage.NEW_CLASS__NEW_CLASS:
				setNewClass((EClass)null);
				return;
			case EcorextPackage.NEW_CLASS__CONTAINING_EXISTING_PACKAGE:
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
			case EcorextPackage.NEW_CLASS__NEW_CLASS:
				return newClass != null;
			case EcorextPackage.NEW_CLASS__CONTAINING_EXISTING_PACKAGE:
				return containingExistingPackage != null;
		}
		return super.eIsSet(featureID);
	}

} //NewClassImpl
