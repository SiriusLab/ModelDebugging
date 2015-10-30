/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.RichgenerictracePackage;
import fr.inria.diverse.trace.generic.model.richgenerictrace.TracedObject;
import fr.inria.diverse.trace.generic.model.richgenerictrace.ValueSequence;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Traced Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TracedObjectImpl#getValueSequences <em>Value Sequences</em>}</li>
 *   <li>{@link fr.inria.diverse.trace.generic.model.richgenerictrace.impl.TracedObjectImpl#getOriginalObject <em>Original Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TracedObjectImpl extends MinimalEObjectImpl.Container implements TracedObject {
	/**
	 * The cached value of the '{@link #getValueSequences() <em>Value Sequences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueSequences()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueSequence> valueSequences;

	/**
	 * The cached value of the '{@link #getOriginalObject() <em>Original Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalObject()
	 * @generated
	 * @ordered
	 */
	protected EObject originalObject;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TracedObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RichgenerictracePackage.Literals.TRACED_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ValueSequence> getValueSequences() {
		if (valueSequences == null) {
			valueSequences = new EObjectContainmentEList<ValueSequence>(ValueSequence.class, this, RichgenerictracePackage.TRACED_OBJECT__VALUE_SEQUENCES);
		}
		return valueSequences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getOriginalObject() {
		if (originalObject != null && originalObject.eIsProxy()) {
			InternalEObject oldOriginalObject = (InternalEObject)originalObject;
			originalObject = eResolveProxy(oldOriginalObject);
			if (originalObject != oldOriginalObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RichgenerictracePackage.TRACED_OBJECT__ORIGINAL_OBJECT, oldOriginalObject, originalObject));
			}
		}
		return originalObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetOriginalObject() {
		return originalObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginalObject(EObject newOriginalObject) {
		EObject oldOriginalObject = originalObject;
		originalObject = newOriginalObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RichgenerictracePackage.TRACED_OBJECT__ORIGINAL_OBJECT, oldOriginalObject, originalObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RichgenerictracePackage.TRACED_OBJECT__VALUE_SEQUENCES:
				return ((InternalEList<?>)getValueSequences()).basicRemove(otherEnd, msgs);
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
			case RichgenerictracePackage.TRACED_OBJECT__VALUE_SEQUENCES:
				return getValueSequences();
			case RichgenerictracePackage.TRACED_OBJECT__ORIGINAL_OBJECT:
				if (resolve) return getOriginalObject();
				return basicGetOriginalObject();
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
			case RichgenerictracePackage.TRACED_OBJECT__VALUE_SEQUENCES:
				getValueSequences().clear();
				getValueSequences().addAll((Collection<? extends ValueSequence>)newValue);
				return;
			case RichgenerictracePackage.TRACED_OBJECT__ORIGINAL_OBJECT:
				setOriginalObject((EObject)newValue);
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
			case RichgenerictracePackage.TRACED_OBJECT__VALUE_SEQUENCES:
				getValueSequences().clear();
				return;
			case RichgenerictracePackage.TRACED_OBJECT__ORIGINAL_OBJECT:
				setOriginalObject((EObject)null);
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
			case RichgenerictracePackage.TRACED_OBJECT__VALUE_SEQUENCES:
				return valueSequences != null && !valueSequences.isEmpty();
			case RichgenerictracePackage.TRACED_OBJECT__ORIGINAL_OBJECT:
				return originalObject != null;
		}
		return super.eIsSet(featureID);
	}

} //TracedObjectImpl
