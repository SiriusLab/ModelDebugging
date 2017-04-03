/**
 */
package opsemanticsview.impl;

import java.util.Collection;

import opsemanticsview.ExecutionToASEntry;
import opsemanticsview.OperationalSemanticsView;
import opsemanticsview.OpsemanticsviewPackage;
import opsemanticsview.Rule;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Semantics View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.impl.OperationalSemanticsViewImpl#getRules <em>Rules</em>}</li>
 *   <li>{@link opsemanticsview.impl.OperationalSemanticsViewImpl#getDynamicProperties <em>Dynamic Properties</em>}</li>
 *   <li>{@link opsemanticsview.impl.OperationalSemanticsViewImpl#getDynamicClasses <em>Dynamic Classes</em>}</li>
 *   <li>{@link opsemanticsview.impl.OperationalSemanticsViewImpl#getExecutionToASmapping <em>Execution To ASmapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationalSemanticsViewImpl extends MinimalEObjectImpl.Container implements OperationalSemanticsView {
	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> rules;

	/**
	 * The cached value of the '{@link #getDynamicProperties() <em>Dynamic Properties</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<EStructuralFeature> dynamicProperties;

	/**
	 * The cached value of the '{@link #getDynamicClasses() <em>Dynamic Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> dynamicClasses;

	/**
	 * The cached value of the '{@link #getExecutionToASmapping() <em>Execution To ASmapping</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionToASmapping()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionToASEntry> executionToASmapping;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationalSemanticsViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OpsemanticsviewPackage.Literals.OPERATIONAL_SEMANTICS_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rule> getRules() {
		if (rules == null) {
			rules = new EObjectContainmentEList<Rule>(Rule.class, this, OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EStructuralFeature> getDynamicProperties() {
		if (dynamicProperties == null) {
			dynamicProperties = new EObjectResolvingEList<EStructuralFeature>(EStructuralFeature.class, this, OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES);
		}
		return dynamicProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getDynamicClasses() {
		if (dynamicClasses == null) {
			dynamicClasses = new EObjectResolvingEList<EClass>(EClass.class, this, OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES);
		}
		return dynamicClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionToASEntry> getExecutionToASmapping() {
		if (executionToASmapping == null) {
			executionToASmapping = new EObjectContainmentEList<ExecutionToASEntry>(ExecutionToASEntry.class, this, OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING);
		}
		return executionToASmapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__RULES:
				return ((InternalEList<?>)getRules()).basicRemove(otherEnd, msgs);
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING:
				return ((InternalEList<?>)getExecutionToASmapping()).basicRemove(otherEnd, msgs);
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
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__RULES:
				return getRules();
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES:
				return getDynamicProperties();
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES:
				return getDynamicClasses();
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING:
				return getExecutionToASmapping();
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
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends Rule>)newValue);
				return;
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES:
				getDynamicProperties().clear();
				getDynamicProperties().addAll((Collection<? extends EStructuralFeature>)newValue);
				return;
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES:
				getDynamicClasses().clear();
				getDynamicClasses().addAll((Collection<? extends EClass>)newValue);
				return;
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING:
				getExecutionToASmapping().clear();
				getExecutionToASmapping().addAll((Collection<? extends ExecutionToASEntry>)newValue);
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
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__RULES:
				getRules().clear();
				return;
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES:
				getDynamicProperties().clear();
				return;
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES:
				getDynamicClasses().clear();
				return;
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING:
				getExecutionToASmapping().clear();
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
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__RULES:
				return rules != null && !rules.isEmpty();
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES:
				return dynamicProperties != null && !dynamicProperties.isEmpty();
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES:
				return dynamicClasses != null && !dynamicClasses.isEmpty();
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING:
				return executionToASmapping != null && !executionToASmapping.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationalSemanticsViewImpl
