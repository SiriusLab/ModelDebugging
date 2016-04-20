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
package org.gemoc.executionframework.engine.mse;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.gemoc.executionframework.engine.mse.MseFactory
 * @model kind="package"
 * @generated
 */
public interface MsePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "mse";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/gemoc_execution_engine_mse";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mse";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MsePackage eINSTANCE = org.gemoc.executionframework.engine.mse.impl.MsePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl <em>MSE Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getMSEOccurrence()
	 * @generated
	 */
	int MSE_OCCURRENCE = 0;

	/**
	 * The feature id for the '<em><b>Mse</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE__MSE = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE__PARAMETERS = 1;

	/**
	 * The feature id for the '<em><b>Result</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE__RESULT = 2;

	/**
	 * The number of structural features of the '<em>MSE Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>MSE Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OCCURRENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.MSEImpl <em>MSE</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.MSEImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getMSE()
	 * @generated
	 */
	int MSE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE__EANNOTATIONS = EcorePackage.ENAMED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE__NAME = EcorePackage.ENAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_FEATURE_COUNT = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Get EAnnotation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE___GET_EANNOTATION__STRING = EcorePackage.ENAMED_ELEMENT___GET_EANNOTATION__STRING;

	/**
	 * The operation id for the '<em>Get Caller</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE___GET_CALLER = EcorePackage.ENAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE___GET_ACTION = EcorePackage.ENAMED_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_OPERATION_COUNT = EcorePackage.ENAMED_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.MSEModelImpl <em>MSE Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.MSEModelImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getMSEModel()
	 * @generated
	 */
	int MSE_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Owned MS Es</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_MODEL__OWNED_MS_ES = 0;

	/**
	 * The number of structural features of the '<em>MSE Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>MSE Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MSE_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericMSEImpl <em>Generic MSE</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.GenericMSEImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericMSE()
	 * @generated
	 */
	int GENERIC_MSE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__EANNOTATIONS = MSE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__NAME = MSE__NAME;

	/**
	 * The feature id for the '<em><b>Caller Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__CALLER_REFERENCE = MSE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE__ACTION_REFERENCE = MSE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE_FEATURE_COUNT = MSE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get EAnnotation</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE___GET_EANNOTATION__STRING = MSE___GET_EANNOTATION__STRING;

	/**
	 * The operation id for the '<em>Get Caller</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE___GET_CALLER = MSE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE___GET_ACTION = MSE_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic MSE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_MSE_OPERATION_COUNT = MSE_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.LogicalStepImpl <em>Logical Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.LogicalStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getLogicalStep()
	 * @generated
	 */
	int LOGICAL_STEP = 4;

	/**
	 * The feature id for the '<em><b>Mse Occurrences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_STEP__MSE_OCCURRENCES = 0;

	/**
	 * The number of structural features of the '<em>Logical Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_STEP_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Logical Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>ISerializable</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getISerializable()
	 * @generated
	 */
	int ISERIALIZABLE = 5;


	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.MSEOccurrence <em>MSE Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MSE Occurrence</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSEOccurrence
	 * @generated
	 */
	EClass getMSEOccurrence();

	/**
	 * Returns the meta object for the reference '{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getMse <em>Mse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mse</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSEOccurrence#getMse()
	 * @see #getMSEOccurrence()
	 * @generated
	 */
	EReference getMSEOccurrence_Mse();

	/**
	 * Returns the meta object for the attribute list '{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parameters</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSEOccurrence#getParameters()
	 * @see #getMSEOccurrence()
	 * @generated
	 */
	EAttribute getMSEOccurrence_Parameters();

	/**
	 * Returns the meta object for the attribute list '{@link org.gemoc.executionframework.engine.mse.MSEOccurrence#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Result</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSEOccurrence#getResult()
	 * @see #getMSEOccurrence()
	 * @generated
	 */
	EAttribute getMSEOccurrence_Result();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.MSE <em>MSE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MSE</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSE
	 * @generated
	 */
	EClass getMSE();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.engine.mse.MSE#getCaller() <em>Get Caller</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Caller</em>' operation.
	 * @see org.gemoc.executionframework.engine.mse.MSE#getCaller()
	 * @generated
	 */
	EOperation getMSE__GetCaller();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.engine.mse.MSE#getAction() <em>Get Action</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Action</em>' operation.
	 * @see org.gemoc.executionframework.engine.mse.MSE#getAction()
	 * @generated
	 */
	EOperation getMSE__GetAction();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.MSEModel <em>MSE Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MSE Model</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSEModel
	 * @generated
	 */
	EClass getMSEModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.executionframework.engine.mse.MSEModel#getOwnedMSEs <em>Owned MS Es</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned MS Es</em>'.
	 * @see org.gemoc.executionframework.engine.mse.MSEModel#getOwnedMSEs()
	 * @see #getMSEModel()
	 * @generated
	 */
	EReference getMSEModel_OwnedMSEs();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.GenericMSE <em>Generic MSE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic MSE</em>'.
	 * @see org.gemoc.executionframework.engine.mse.GenericMSE
	 * @generated
	 */
	EClass getGenericMSE();

	/**
	 * Returns the meta object for the reference '{@link org.gemoc.executionframework.engine.mse.GenericMSE#getCallerReference <em>Caller Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Caller Reference</em>'.
	 * @see org.gemoc.executionframework.engine.mse.GenericMSE#getCallerReference()
	 * @see #getGenericMSE()
	 * @generated
	 */
	EReference getGenericMSE_CallerReference();

	/**
	 * Returns the meta object for the reference '{@link org.gemoc.executionframework.engine.mse.GenericMSE#getActionReference <em>Action Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Action Reference</em>'.
	 * @see org.gemoc.executionframework.engine.mse.GenericMSE#getActionReference()
	 * @see #getGenericMSE()
	 * @generated
	 */
	EReference getGenericMSE_ActionReference();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.engine.mse.GenericMSE#getCaller() <em>Get Caller</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Caller</em>' operation.
	 * @see org.gemoc.executionframework.engine.mse.GenericMSE#getCaller()
	 * @generated
	 */
	EOperation getGenericMSE__GetCaller();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.engine.mse.GenericMSE#getAction() <em>Get Action</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Action</em>' operation.
	 * @see org.gemoc.executionframework.engine.mse.GenericMSE#getAction()
	 * @generated
	 */
	EOperation getGenericMSE__GetAction();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.LogicalStep <em>Logical Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.LogicalStep
	 * @generated
	 */
	EClass getLogicalStep();

	/**
	 * Returns the meta object for the reference list '{@link org.gemoc.executionframework.engine.mse.LogicalStep#getMseOccurrences <em>Mse Occurrences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mse Occurrences</em>'.
	 * @see org.gemoc.executionframework.engine.mse.LogicalStep#getMseOccurrences()
	 * @see #getLogicalStep()
	 * @generated
	 */
	EReference getLogicalStep_MseOccurrences();

	/**
	 * Returns the meta object for data type '<em>ISerializable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ISerializable</em>'.
	 * @model instanceClass="byte[]"
	 * @generated
	 */
	EDataType getISerializable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MseFactory getMseFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl <em>MSE Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.MSEOccurrenceImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getMSEOccurrence()
		 * @generated
		 */
		EClass MSE_OCCURRENCE = eINSTANCE.getMSEOccurrence();

		/**
		 * The meta object literal for the '<em><b>Mse</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MSE_OCCURRENCE__MSE = eINSTANCE.getMSEOccurrence_Mse();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MSE_OCCURRENCE__PARAMETERS = eINSTANCE.getMSEOccurrence_Parameters();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MSE_OCCURRENCE__RESULT = eINSTANCE.getMSEOccurrence_Result();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.MSEImpl <em>MSE</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.MSEImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getMSE()
		 * @generated
		 */
		EClass MSE = eINSTANCE.getMSE();

		/**
		 * The meta object literal for the '<em><b>Get Caller</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MSE___GET_CALLER = eINSTANCE.getMSE__GetCaller();

		/**
		 * The meta object literal for the '<em><b>Get Action</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MSE___GET_ACTION = eINSTANCE.getMSE__GetAction();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.MSEModelImpl <em>MSE Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.MSEModelImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getMSEModel()
		 * @generated
		 */
		EClass MSE_MODEL = eINSTANCE.getMSEModel();

		/**
		 * The meta object literal for the '<em><b>Owned MS Es</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MSE_MODEL__OWNED_MS_ES = eINSTANCE.getMSEModel_OwnedMSEs();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericMSEImpl <em>Generic MSE</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.GenericMSEImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericMSE()
		 * @generated
		 */
		EClass GENERIC_MSE = eINSTANCE.getGenericMSE();

		/**
		 * The meta object literal for the '<em><b>Caller Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_MSE__CALLER_REFERENCE = eINSTANCE.getGenericMSE_CallerReference();

		/**
		 * The meta object literal for the '<em><b>Action Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_MSE__ACTION_REFERENCE = eINSTANCE.getGenericMSE_ActionReference();

		/**
		 * The meta object literal for the '<em><b>Get Caller</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_MSE___GET_CALLER = eINSTANCE.getGenericMSE__GetCaller();

		/**
		 * The meta object literal for the '<em><b>Get Action</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_MSE___GET_ACTION = eINSTANCE.getGenericMSE__GetAction();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.LogicalStepImpl <em>Logical Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.LogicalStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getLogicalStep()
		 * @generated
		 */
		EClass LOGICAL_STEP = eINSTANCE.getLogicalStep();

		/**
		 * The meta object literal for the '<em><b>Mse Occurrences</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_STEP__MSE_OCCURRENCES = eINSTANCE.getLogicalStep_MseOccurrences();

		/**
		 * The meta object literal for the '<em>ISerializable</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getISerializable()
		 * @generated
		 */
		EDataType ISERIALIZABLE = eINSTANCE.getISerializable();

	}

} //MsePackage
