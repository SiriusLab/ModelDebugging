/**
 */
package fr.inria.diverse.event.commons.model.property.impl;

import fr.inria.diverse.event.commons.model.property.AbstractProperty;
import fr.inria.diverse.event.commons.model.property.PropertyPackage;
import fr.inria.diverse.event.commons.model.property.ReleaseProperty;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Release Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.impl.ReleasePropertyImpl#getLeftFormula <em>Left Formula</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.impl.ReleasePropertyImpl#getRightFormula <em>Right Formula</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ReleasePropertyImpl<P extends AbstractProperty> extends TemporalPropertyImpl implements ReleaseProperty<P> {
	/**
	 * The cached value of the '{@link #getLeftFormula() <em>Left Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftFormula()
	 * @generated
	 * @ordered
	 */
	protected P leftFormula;

	/**
	 * The cached value of the '{@link #getRightFormula() <em>Right Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightFormula()
	 * @generated
	 * @ordered
	 */
	protected P rightFormula;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReleasePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PropertyPackage.Literals.RELEASE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public P getLeftFormula() {
		return leftFormula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftFormula(P newLeftFormula, NotificationChain msgs) {
		P oldLeftFormula = leftFormula;
		leftFormula = newLeftFormula;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA, oldLeftFormula, newLeftFormula);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftFormula(P newLeftFormula) {
		if (newLeftFormula != leftFormula) {
			NotificationChain msgs = null;
			if (leftFormula != null)
				msgs = ((InternalEObject)leftFormula).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA, null, msgs);
			if (newLeftFormula != null)
				msgs = ((InternalEObject)newLeftFormula).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA, null, msgs);
			msgs = basicSetLeftFormula(newLeftFormula, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA, newLeftFormula, newLeftFormula));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public P getRightFormula() {
		return rightFormula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightFormula(P newRightFormula, NotificationChain msgs) {
		P oldRightFormula = rightFormula;
		rightFormula = newRightFormula;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA, oldRightFormula, newRightFormula);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightFormula(P newRightFormula) {
		if (newRightFormula != rightFormula) {
			NotificationChain msgs = null;
			if (rightFormula != null)
				msgs = ((InternalEObject)rightFormula).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA, null, msgs);
			if (newRightFormula != null)
				msgs = ((InternalEObject)newRightFormula).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA, null, msgs);
			msgs = basicSetRightFormula(newRightFormula, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA, newRightFormula, newRightFormula));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA:
				return basicSetLeftFormula(null, msgs);
			case PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA:
				return basicSetRightFormula(null, msgs);
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
			case PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA:
				return getLeftFormula();
			case PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA:
				return getRightFormula();
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
			case PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA:
				setLeftFormula((P)newValue);
				return;
			case PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA:
				setRightFormula((P)newValue);
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
			case PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA:
				setLeftFormula((P)null);
				return;
			case PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA:
				setRightFormula((P)null);
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
			case PropertyPackage.RELEASE_PROPERTY__LEFT_FORMULA:
				return leftFormula != null;
			case PropertyPackage.RELEASE_PROPERTY__RIGHT_FORMULA:
				return rightFormula != null;
		}
		return super.eIsSet(featureID);
	}

} //ReleasePropertyImpl
