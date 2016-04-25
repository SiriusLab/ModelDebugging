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
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.StepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getStep()
	 * @generated
	 */
	int STEP = 4;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__MSEOCCURRENCE = 0;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.BigStepImpl <em>Big Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.BigStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getBigStep()
	 * @generated
	 */
	int BIG_STEP = 5;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP__MSEOCCURRENCE = STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP__SUB_STEPS = STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Big Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Big Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIG_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.SmallStepImpl <em>Small Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.SmallStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getSmallStep()
	 * @generated
	 */
	int SMALL_STEP = 6;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP__MSEOCCURRENCE = STEP__MSEOCCURRENCE;

	/**
	 * The number of structural features of the '<em>Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.SequentialStepImpl <em>Sequential Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.SequentialStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getSequentialStep()
	 * @generated
	 */
	int SEQUENTIAL_STEP = 7;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP__MSEOCCURRENCE = BIG_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP__SUB_STEPS = BIG_STEP__SUB_STEPS;

	/**
	 * The number of structural features of the '<em>Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP_FEATURE_COUNT = BIG_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_STEP_OPERATION_COUNT = BIG_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.ParallelStepImpl <em>Parallel Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.ParallelStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getParallelStep()
	 * @generated
	 */
	int PARALLEL_STEP = 8;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP__MSEOCCURRENCE = BIG_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP__SUB_STEPS = BIG_STEP__SUB_STEPS;

	/**
	 * The number of structural features of the '<em>Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP_FEATURE_COUNT = BIG_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_STEP_OPERATION_COUNT = BIG_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericSequentialStepImpl <em>Generic Sequential Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.GenericSequentialStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericSequentialStep()
	 * @generated
	 */
	int GENERIC_SEQUENTIAL_STEP = 9;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__MSEOCCURRENCE = SEQUENTIAL_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__SUB_STEPS = SEQUENTIAL_STEP__SUB_STEPS;

	/**
	 * The number of structural features of the '<em>Generic Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP_FEATURE_COUNT = SEQUENTIAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP_OPERATION_COUNT = SEQUENTIAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericParallelStepImpl <em>Generic Parallel Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.GenericParallelStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericParallelStep()
	 * @generated
	 */
	int GENERIC_PARALLEL_STEP = 10;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__MSEOCCURRENCE = PARALLEL_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__SUB_STEPS = PARALLEL_STEP__SUB_STEPS;

	/**
	 * The number of structural features of the '<em>Generic Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP_FEATURE_COUNT = PARALLEL_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP_OPERATION_COUNT = PARALLEL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericSmallStepImpl <em>Generic Small Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.GenericSmallStepImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericSmallStep()
	 * @generated
	 */
	int GENERIC_SMALL_STEP = 11;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__MSEOCCURRENCE = SMALL_STEP__MSEOCCURRENCE;

	/**
	 * The number of structural features of the '<em>Generic Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP_FEATURE_COUNT = SMALL_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP_OPERATION_COUNT = SMALL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.TraceImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 12;

	/**
	 * The feature id for the '<em><b>Root Step</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__ROOT_STEP = 0;

	/**
	 * The feature id for the '<em><b>Launchconfiguration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__LAUNCHCONFIGURATION = 1;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.engine.mse.impl.LaunchConfigurationImpl <em>Launch Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.LaunchConfigurationImpl
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getLaunchConfiguration()
	 * @generated
	 */
	int LAUNCH_CONFIGURATION = 13;

	/**
	 * The number of structural features of the '<em>Launch Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Launch Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAUNCH_CONFIGURATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>ISerializable</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getISerializable()
	 * @generated
	 */
	int ISERIALIZABLE = 14;


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
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.executionframework.engine.mse.Step#getMseoccurrence <em>Mseoccurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mseoccurrence</em>'.
	 * @see org.gemoc.executionframework.engine.mse.Step#getMseoccurrence()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_Mseoccurrence();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.BigStep <em>Big Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Big Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.BigStep
	 * @generated
	 */
	EClass getBigStep();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.executionframework.engine.mse.BigStep#getSubSteps <em>Sub Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Steps</em>'.
	 * @see org.gemoc.executionframework.engine.mse.BigStep#getSubSteps()
	 * @see #getBigStep()
	 * @generated
	 */
	EReference getBigStep_SubSteps();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.SmallStep <em>Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Small Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.SmallStep
	 * @generated
	 */
	EClass getSmallStep();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.SequentialStep <em>Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequential Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.SequentialStep
	 * @generated
	 */
	EClass getSequentialStep();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.ParallelStep <em>Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parallel Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.ParallelStep
	 * @generated
	 */
	EClass getParallelStep();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.GenericSequentialStep <em>Generic Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Sequential Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.GenericSequentialStep
	 * @generated
	 */
	EClass getGenericSequentialStep();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.GenericParallelStep <em>Generic Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Parallel Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.GenericParallelStep
	 * @generated
	 */
	EClass getGenericParallelStep();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.GenericSmallStep <em>Generic Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Small Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.GenericSmallStep
	 * @generated
	 */
	EClass getGenericSmallStep();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see org.gemoc.executionframework.engine.mse.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.executionframework.engine.mse.Trace#getRootStep <em>Root Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Step</em>'.
	 * @see org.gemoc.executionframework.engine.mse.Trace#getRootStep()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_RootStep();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.executionframework.engine.mse.Trace#getLaunchconfiguration <em>Launchconfiguration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Launchconfiguration</em>'.
	 * @see org.gemoc.executionframework.engine.mse.Trace#getLaunchconfiguration()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Launchconfiguration();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.engine.mse.LaunchConfiguration <em>Launch Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Launch Configuration</em>'.
	 * @see org.gemoc.executionframework.engine.mse.LaunchConfiguration
	 * @generated
	 */
	EClass getLaunchConfiguration();

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
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.StepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '<em><b>Mseoccurrence</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__MSEOCCURRENCE = eINSTANCE.getStep_Mseoccurrence();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.BigStepImpl <em>Big Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.BigStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getBigStep()
		 * @generated
		 */
		EClass BIG_STEP = eINSTANCE.getBigStep();

		/**
		 * The meta object literal for the '<em><b>Sub Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BIG_STEP__SUB_STEPS = eINSTANCE.getBigStep_SubSteps();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.SmallStepImpl <em>Small Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.SmallStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getSmallStep()
		 * @generated
		 */
		EClass SMALL_STEP = eINSTANCE.getSmallStep();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.SequentialStepImpl <em>Sequential Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.SequentialStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getSequentialStep()
		 * @generated
		 */
		EClass SEQUENTIAL_STEP = eINSTANCE.getSequentialStep();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.ParallelStepImpl <em>Parallel Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.ParallelStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getParallelStep()
		 * @generated
		 */
		EClass PARALLEL_STEP = eINSTANCE.getParallelStep();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericSequentialStepImpl <em>Generic Sequential Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.GenericSequentialStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericSequentialStep()
		 * @generated
		 */
		EClass GENERIC_SEQUENTIAL_STEP = eINSTANCE.getGenericSequentialStep();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericParallelStepImpl <em>Generic Parallel Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.GenericParallelStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericParallelStep()
		 * @generated
		 */
		EClass GENERIC_PARALLEL_STEP = eINSTANCE.getGenericParallelStep();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.GenericSmallStepImpl <em>Generic Small Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.GenericSmallStepImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getGenericSmallStep()
		 * @generated
		 */
		EClass GENERIC_SMALL_STEP = eINSTANCE.getGenericSmallStep();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.TraceImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Root Step</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__ROOT_STEP = eINSTANCE.getTrace_RootStep();

		/**
		 * The meta object literal for the '<em><b>Launchconfiguration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__LAUNCHCONFIGURATION = eINSTANCE.getTrace_Launchconfiguration();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.engine.mse.impl.LaunchConfigurationImpl <em>Launch Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.engine.mse.impl.LaunchConfigurationImpl
		 * @see org.gemoc.executionframework.engine.mse.impl.MsePackageImpl#getLaunchConfiguration()
		 * @generated
		 */
		EClass LAUNCH_CONFIGURATION = eINSTANCE.getLaunchConfiguration();

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
