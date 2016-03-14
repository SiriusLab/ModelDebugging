/**
 */
package org.gemoc.executionframework.engine.mse.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSE;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.mse.MsePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MSE Occurrence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl#getMse <em>Mse</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl#getResult <em>Result</em>}</li>
 *   <li>{@link org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl#getLogicalStep <em>Logical Step</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MSEOccurrenceImpl extends MinimalEObjectImpl.Container implements MSEOccurrence {
	/**
	 * The cached value of the '{@link #getMse() <em>Mse</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMse()
	 * @generated
	 * @ordered
	 */
	protected MSE mse;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Object> parameters;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected EList<Object> result;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MSEOccurrenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MsePackage.Literals.MSE_OCCURRENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSE getMse() {
		if (mse != null && mse.eIsProxy()) {
			InternalEObject oldMse = (InternalEObject)mse;
			mse = (MSE)eResolveProxy(oldMse);
			if (mse != oldMse) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MsePackage.MSE_OCCURRENCE__MSE, oldMse, mse));
			}
		}
		return mse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSE basicGetMse() {
		return mse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMse(MSE newMse) {
		MSE oldMse = mse;
		mse = newMse;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsePackage.MSE_OCCURRENCE__MSE, oldMse, mse));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Object> getParameters() {
		if (parameters == null) {
			parameters = new EDataTypeUniqueEList<Object>(Object.class, this, MsePackage.MSE_OCCURRENCE__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Object> getResult() {
		if (result == null) {
			result = new EDataTypeUniqueEList<Object>(Object.class, this, MsePackage.MSE_OCCURRENCE__RESULT);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalStep getLogicalStep() {
		if (eContainerFeatureID() != MsePackage.MSE_OCCURRENCE__LOGICAL_STEP) return null;
		return (LogicalStep)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLogicalStep(LogicalStep newLogicalStep, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLogicalStep, MsePackage.MSE_OCCURRENCE__LOGICAL_STEP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogicalStep(LogicalStep newLogicalStep) {
		if (newLogicalStep != eInternalContainer() || (eContainerFeatureID() != MsePackage.MSE_OCCURRENCE__LOGICAL_STEP && newLogicalStep != null)) {
			if (EcoreUtil.isAncestor(this, newLogicalStep))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLogicalStep != null)
				msgs = ((InternalEObject)newLogicalStep).eInverseAdd(this, MsePackage.LOGICAL_STEP__MSE_OCCURRENCES, LogicalStep.class, msgs);
			msgs = basicSetLogicalStep(newLogicalStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MsePackage.MSE_OCCURRENCE__LOGICAL_STEP, newLogicalStep, newLogicalStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLogicalStep((LogicalStep)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				return basicSetLogicalStep(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				return eInternalContainer().eInverseRemove(this, MsePackage.LOGICAL_STEP__MSE_OCCURRENCES, LogicalStep.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MsePackage.MSE_OCCURRENCE__MSE:
				if (resolve) return getMse();
				return basicGetMse();
			case MsePackage.MSE_OCCURRENCE__PARAMETERS:
				return getParameters();
			case MsePackage.MSE_OCCURRENCE__RESULT:
				return getResult();
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				return getLogicalStep();
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
			case MsePackage.MSE_OCCURRENCE__MSE:
				setMse((MSE)newValue);
				return;
			case MsePackage.MSE_OCCURRENCE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Object>)newValue);
				return;
			case MsePackage.MSE_OCCURRENCE__RESULT:
				getResult().clear();
				getResult().addAll((Collection<? extends Object>)newValue);
				return;
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				setLogicalStep((LogicalStep)newValue);
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
			case MsePackage.MSE_OCCURRENCE__MSE:
				setMse((MSE)null);
				return;
			case MsePackage.MSE_OCCURRENCE__PARAMETERS:
				getParameters().clear();
				return;
			case MsePackage.MSE_OCCURRENCE__RESULT:
				getResult().clear();
				return;
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				setLogicalStep((LogicalStep)null);
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
			case MsePackage.MSE_OCCURRENCE__MSE:
				return mse != null;
			case MsePackage.MSE_OCCURRENCE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case MsePackage.MSE_OCCURRENCE__RESULT:
				return result != null && !result.isEmpty();
			case MsePackage.MSE_OCCURRENCE__LOGICAL_STEP:
				return getLogicalStep() != null;
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
		result.append(" (parameters: ");
		result.append(parameters);
		result.append(", result: ");
		result.append(result);
		result.append(')');
		return result.toString();
	}

} //MSEOccurrenceImpl
