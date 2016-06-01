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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gemoc.executionframework.engine.mse.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MseFactoryImpl extends EFactoryImpl implements MseFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MseFactory init() {
		try {
			MseFactory theMseFactory = (MseFactory)EPackage.Registry.INSTANCE.getEFactory(MsePackage.eNS_URI);
			if (theMseFactory != null) {
				return theMseFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MseFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MseFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MsePackage.MSE_OCCURRENCE: return createMSEOccurrence();
			case MsePackage.MSE_MODEL: return createMSEModel();
			case MsePackage.GENERIC_MSE: return createGenericMSE();
			case MsePackage.SEQUENTIAL_STEP: return createSequentialStep();
			case MsePackage.PARALLEL_STEP: return createParallelStep();
			case MsePackage.GENERIC_SEQUENTIAL_STEP: return createGenericSequentialStep();
			case MsePackage.GENERIC_PARALLEL_STEP: return createGenericParallelStep();
			case MsePackage.GENERIC_SMALL_STEP: return createGenericSmallStep();
			case MsePackage.LAUNCH_CONFIGURATION: return createLaunchConfiguration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case MsePackage.ISERIALIZABLE:
				return createISerializableFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case MsePackage.ISERIALIZABLE:
				return convertISerializableToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSEOccurrence createMSEOccurrence() {
		MSEOccurrenceImpl mseOccurrence = new MSEOccurrenceImpl();
		return mseOccurrence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MSEModel createMSEModel() {
		MSEModelImpl mseModel = new MSEModelImpl();
		return mseModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericMSE createGenericMSE() {
		GenericMSEImpl genericMSE = new GenericMSEImpl();
		return genericMSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <StepSubtype extends Step> SequentialStep<StepSubtype> createSequentialStep() {
		SequentialStepImpl<StepSubtype> sequentialStep = new SequentialStepImpl<StepSubtype>();
		return sequentialStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public <StepSubtype extends Step> ParallelStep<StepSubtype> createParallelStep() {
		ParallelStepImpl<StepSubtype> parallelStep = new ParallelStepImpl<StepSubtype>();
		return parallelStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericSequentialStep createGenericSequentialStep() {
		GenericSequentialStepImpl genericSequentialStep = new GenericSequentialStepImpl();
		return genericSequentialStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericParallelStep createGenericParallelStep() {
		GenericParallelStepImpl genericParallelStep = new GenericParallelStepImpl();
		return genericParallelStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericSmallStep createGenericSmallStep() {
		GenericSmallStepImpl genericSmallStep = new GenericSmallStepImpl();
		return genericSmallStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LaunchConfiguration createLaunchConfiguration() {
		LaunchConfigurationImpl launchConfiguration = new LaunchConfigurationImpl();
		return launchConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] createISerializableFromString(EDataType eDataType, String initialValue) {
		return (byte[])super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertISerializableToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MsePackage getMsePackage() {
		return (MsePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MsePackage getPackage() {
		return MsePackage.eINSTANCE;
	}

} //MseFactoryImpl
