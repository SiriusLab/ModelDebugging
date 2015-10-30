/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage;
import fr.inria.diverse.trace.generic.model.richgenerictrace.Value;
import fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Sequence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl#getValues <em>Values</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl#getCurrent <em>Current</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.ValueSequenceImpl#getTracedProperty <em>Traced Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueSequenceImpl extends MinimalEObjectImpl.Container implements ValueSequence {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Value> values;

	/**
	 * The cached value of the '{@link #getCurrent() <em>Current</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrent()
	 * @generated
	 * @ordered
	 */
	protected Value current;

	/**
	 * The cached value of the '{@link #getTracedProperty() <em>Traced Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracedProperty()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature tracedProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueSequenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RichgenerictracePackage.Literals.VALUE_SEQUENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Value> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<Value>(Value.class, this, RichgenerictracePackage.VALUE_SEQUENCE__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value getCurrent() {
		if (current != null && current.eIsProxy()) {
			InternalEObject oldCurrent = (InternalEObject)current;
			current = (Value)eResolveProxy(oldCurrent);
			if (current != oldCurrent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.VALUE_SEQUENCE__CURRENT, oldCurrent, current));
			}
		}
		return current;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value basicGetCurrent() {
		return current;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrent(Value newCurrent) {
		Value oldCurrent = current;
		current = newCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.VALUE_SEQUENCE__CURRENT, oldCurrent, current));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature getTracedProperty() {
		if (tracedProperty != null && tracedProperty.eIsProxy()) {
			InternalEObject oldTracedProperty = (InternalEObject)tracedProperty;
			tracedProperty = (EStructuralFeature)eResolveProxy(oldTracedProperty);
			if (tracedProperty != oldTracedProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.VALUE_SEQUENCE__TRACED_PROPERTY, oldTracedProperty, tracedProperty));
			}
		}
		return tracedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetTracedProperty() {
		return tracedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTracedProperty(EStructuralFeature newTracedProperty) {
		EStructuralFeature oldTracedProperty = tracedProperty;
		tracedProperty = newTracedProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.VALUE_SEQUENCE__TRACED_PROPERTY, oldTracedProperty, tracedProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RichgenerictracePackage.VALUE_SEQUENCE__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
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
			case RichgenerictracePackage.VALUE_SEQUENCE__VALUES:
				return getValues();
			case RichgenerictracePackage.VALUE_SEQUENCE__CURRENT:
				if (resolve) return getCurrent();
				return basicGetCurrent();
			case RichgenerictracePackage.VALUE_SEQUENCE__TRACED_PROPERTY:
				if (resolve) return getTracedProperty();
				return basicGetTracedProperty();
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
			case RichgenerictracePackage.VALUE_SEQUENCE__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends Value>)newValue);
				return;
			case RichgenerictracePackage.VALUE_SEQUENCE__CURRENT:
				setCurrent((Value)newValue);
				return;
			case RichgenerictracePackage.VALUE_SEQUENCE__TRACED_PROPERTY:
				setTracedProperty((EStructuralFeature)newValue);
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
			case RichgenerictracePackage.VALUE_SEQUENCE__VALUES:
				getValues().clear();
				return;
			case RichgenerictracePackage.VALUE_SEQUENCE__CURRENT:
				setCurrent((Value)null);
				return;
			case RichgenerictracePackage.VALUE_SEQUENCE__TRACED_PROPERTY:
				setTracedProperty((EStructuralFeature)null);
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
			case RichgenerictracePackage.VALUE_SEQUENCE__VALUES:
				return values != null && !values.isEmpty();
			case RichgenerictracePackage.VALUE_SEQUENCE__CURRENT:
				return current != null;
			case RichgenerictracePackage.VALUE_SEQUENCE__TRACED_PROPERTY:
				return tracedProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //ValueSequenceImpl
