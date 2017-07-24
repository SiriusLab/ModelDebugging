/**
 */
package fr.inria.diverse.event.commons.model.property.impl;

import fr.inria.diverse.event.commons.model.property.NegationTemporalProperty;
import fr.inria.diverse.event.commons.model.property.PropertyPackage;
import fr.inria.diverse.event.commons.model.property.TemporalProperty;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Negation Temporal Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.impl.NegationTemporalPropertyImpl#getFormula <em>Formula</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NegationTemporalPropertyImpl<P extends TemporalProperty> extends TemporalPropertyImpl implements NegationTemporalProperty<P> {
	/**
	 * The cached value of the '{@link #getFormula() <em>Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected P formula;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NegationTemporalPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PropertyPackage.Literals.NEGATION_TEMPORAL_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public P getFormula() {
		return formula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormula(P newFormula, NotificationChain msgs) {
		P oldFormula = formula;
		formula = newFormula;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA, oldFormula, newFormula);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormula(P newFormula) {
		if (newFormula != formula) {
			NotificationChain msgs = null;
			if (formula != null)
				msgs = ((InternalEObject)formula).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA, null, msgs);
			if (newFormula != null)
				msgs = ((InternalEObject)newFormula).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA, null, msgs);
			msgs = basicSetFormula(newFormula, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA, newFormula, newFormula));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA:
				return basicSetFormula(null, msgs);
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
			case PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA:
				return getFormula();
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
			case PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA:
				setFormula((P)newValue);
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
			case PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA:
				setFormula((P)null);
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
			case PropertyPackage.NEGATION_TEMPORAL_PROPERTY__FORMULA:
				return formula != null;
		}
		return super.eIsSet(featureID);
	}

} //NegationTemporalPropertyImpl
