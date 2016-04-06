/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.executionframework.engine.mse.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gemoc.executionframework.engine.mse.GenericMSE;
import org.gemoc.executionframework.engine.mse.LogicalStep;
import org.gemoc.executionframework.engine.mse.MSEModel;
import org.gemoc.executionframework.engine.mse.MSEOccurrence;
import org.gemoc.executionframework.engine.mse.MseFactory;
import org.gemoc.executionframework.engine.mse.MsePackage;
import org.gemoc.executionframework.engine.mse.ParrallelLogicalStep;
import org.gemoc.executionframework.engine.mse.SequentialLogicalStep;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MsePackageImpl extends EPackageImpl implements MsePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mseOccurrenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mseModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericMSEEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parrallelLogicalStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequentialLogicalStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iSerializableEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.gemoc.executionframework.engine.mse.MsePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MsePackageImpl() {
		super(eNS_URI, MseFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MsePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MsePackage init() {
		if (isInited) return (MsePackage)EPackage.Registry.INSTANCE.getEPackage(MsePackage.eNS_URI);

		// Obtain or create and register package
		MsePackageImpl theMsePackage = (MsePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MsePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MsePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMsePackage.createPackageContents();

		// Initialize created meta-data
		theMsePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMsePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MsePackage.eNS_URI, theMsePackage);
		return theMsePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMSEOccurrence() {
		return mseOccurrenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMSEOccurrence_Mse() {
		return (EReference)mseOccurrenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMSEOccurrence_Parameters() {
		return (EAttribute)mseOccurrenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMSEOccurrence_Result() {
		return (EAttribute)mseOccurrenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMSEOccurrence__GetLogicalStep() {
		return mseOccurrenceEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMSE() {
		return mseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMSE__GetCaller() {
		return mseEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getMSE__GetAction() {
		return mseEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMSEModel() {
		return mseModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMSEModel_OwnedMSEs() {
		return (EReference)mseModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericMSE() {
		return genericMSEEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericMSE_CallerReference() {
		return (EReference)genericMSEEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGenericMSE_ActionReference() {
		return (EReference)genericMSEEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getGenericMSE__GetCaller() {
		return genericMSEEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getGenericMSE__GetAction() {
		return genericMSEEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalStep() {
		return logicalStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getLogicalStep__GetMseOccurrences() {
		return logicalStepEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParrallelLogicalStep() {
		return parrallelLogicalStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParrallelLogicalStep_MseOccurrences() {
		return (EReference)parrallelLogicalStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequentialLogicalStep() {
		return sequentialLogicalStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getSequentialLogicalStep__GetMseOccurrences() {
		return sequentialLogicalStepEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getSequentialLogicalStep__GetLogicalStep() {
		return sequentialLogicalStepEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getISerializable() {
		return iSerializableEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MseFactory getMseFactory() {
		return (MseFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		mseOccurrenceEClass = createEClass(MSE_OCCURRENCE);
		createEReference(mseOccurrenceEClass, MSE_OCCURRENCE__MSE);
		createEAttribute(mseOccurrenceEClass, MSE_OCCURRENCE__PARAMETERS);
		createEAttribute(mseOccurrenceEClass, MSE_OCCURRENCE__RESULT);
		createEOperation(mseOccurrenceEClass, MSE_OCCURRENCE___GET_LOGICAL_STEP);

		mseEClass = createEClass(MSE);
		createEOperation(mseEClass, MSE___GET_CALLER);
		createEOperation(mseEClass, MSE___GET_ACTION);

		mseModelEClass = createEClass(MSE_MODEL);
		createEReference(mseModelEClass, MSE_MODEL__OWNED_MS_ES);

		genericMSEEClass = createEClass(GENERIC_MSE);
		createEReference(genericMSEEClass, GENERIC_MSE__CALLER_REFERENCE);
		createEReference(genericMSEEClass, GENERIC_MSE__ACTION_REFERENCE);
		createEOperation(genericMSEEClass, GENERIC_MSE___GET_CALLER);
		createEOperation(genericMSEEClass, GENERIC_MSE___GET_ACTION);

		logicalStepEClass = createEClass(LOGICAL_STEP);
		createEOperation(logicalStepEClass, LOGICAL_STEP___GET_MSE_OCCURRENCES);

		parrallelLogicalStepEClass = createEClass(PARRALLEL_LOGICAL_STEP);
		createEReference(parrallelLogicalStepEClass, PARRALLEL_LOGICAL_STEP__MSE_OCCURRENCES);

		sequentialLogicalStepEClass = createEClass(SEQUENTIAL_LOGICAL_STEP);
		createEOperation(sequentialLogicalStepEClass, SEQUENTIAL_LOGICAL_STEP___GET_MSE_OCCURRENCES);
		createEOperation(sequentialLogicalStepEClass, SEQUENTIAL_LOGICAL_STEP___GET_LOGICAL_STEP);

		// Create data types
		iSerializableEDataType = createEDataType(ISERIALIZABLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		mseEClass.getESuperTypes().add(theEcorePackage.getENamedElement());
		genericMSEEClass.getESuperTypes().add(this.getMSE());
		parrallelLogicalStepEClass.getESuperTypes().add(this.getLogicalStep());
		sequentialLogicalStepEClass.getESuperTypes().add(this.getMSEOccurrence());
		sequentialLogicalStepEClass.getESuperTypes().add(this.getLogicalStep());

		// Initialize classes, features, and operations; add parameters
		initEClass(mseOccurrenceEClass, MSEOccurrence.class, "MSEOccurrence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMSEOccurrence_Mse(), this.getMSE(), null, "mse", null, 1, 1, MSEOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMSEOccurrence_Parameters(), theEcorePackage.getEJavaObject(), "parameters", null, 0, -1, MSEOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMSEOccurrence_Result(), ecorePackage.getEJavaObject(), "result", null, 0, -1, MSEOccurrence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getMSEOccurrence__GetLogicalStep(), this.getLogicalStep(), "getLogicalStep", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(mseEClass, org.gemoc.executionframework.engine.mse.MSE.class, "MSE", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getMSE__GetCaller(), theEcorePackage.getEObject(), "getCaller", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getMSE__GetAction(), theEcorePackage.getEOperation(), "getAction", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(mseModelEClass, MSEModel.class, "MSEModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMSEModel_OwnedMSEs(), this.getMSE(), null, "ownedMSEs", null, 0, -1, MSEModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(genericMSEEClass, GenericMSE.class, "GenericMSE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGenericMSE_CallerReference(), theEcorePackage.getEObject(), null, "callerReference", null, 0, 1, GenericMSE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGenericMSE_ActionReference(), theEcorePackage.getEOperation(), null, "actionReference", null, 0, 1, GenericMSE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getGenericMSE__GetCaller(), theEcorePackage.getEObject(), "getCaller", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getGenericMSE__GetAction(), theEcorePackage.getEOperation(), "getAction", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(logicalStepEClass, LogicalStep.class, "LogicalStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getLogicalStep__GetMseOccurrences(), this.getMSEOccurrence(), "getMseOccurrences", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(parrallelLogicalStepEClass, ParrallelLogicalStep.class, "ParrallelLogicalStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParrallelLogicalStep_MseOccurrences(), this.getMSEOccurrence(), null, "mseOccurrences", null, 1, -1, ParrallelLogicalStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sequentialLogicalStepEClass, SequentialLogicalStep.class, "SequentialLogicalStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getSequentialLogicalStep__GetMseOccurrences(), this.getMSEOccurrence(), "getMseOccurrences", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getSequentialLogicalStep__GetLogicalStep(), this.getLogicalStep(), "getLogicalStep", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(iSerializableEDataType, byte[].class, "ISerializable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //MsePackageImpl
