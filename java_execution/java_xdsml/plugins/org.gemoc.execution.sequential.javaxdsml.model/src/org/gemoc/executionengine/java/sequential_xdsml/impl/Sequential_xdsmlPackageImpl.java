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
package org.gemoc.executionengine.java.sequential_xdsml.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gemoc.executionengine.java.sequential_xdsml.DSAProject;
import org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition;
import org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlFactory;
import org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlPackage;

import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Sequential_xdsmlPackageImpl extends EPackageImpl implements Sequential_xdsmlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequentialLanguageDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dsaProjectEClass = null;

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
	 * @see org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Sequential_xdsmlPackageImpl() {
		super(eNS_URI, Sequential_xdsmlFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Sequential_xdsmlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Sequential_xdsmlPackage init() {
		if (isInited) return (Sequential_xdsmlPackage)EPackage.Registry.INSTANCE.getEPackage(Sequential_xdsmlPackage.eNS_URI);

		// Obtain or create and register package
		Sequential_xdsmlPackageImpl theSequential_xdsmlPackage = (Sequential_xdsmlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Sequential_xdsmlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Sequential_xdsmlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		Xdsml_basePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSequential_xdsmlPackage.createPackageContents();

		// Initialize created meta-data
		theSequential_xdsmlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSequential_xdsmlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Sequential_xdsmlPackage.eNS_URI, theSequential_xdsmlPackage);
		return theSequential_xdsmlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequentialLanguageDefinition() {
		return sequentialLanguageDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSequentialLanguageDefinition_DsaProject() {
		return (EReference)sequentialLanguageDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDSAProject() {
		return dsaProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDSAProject_CodeExecutorClass() {
		return (EAttribute)dsaProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDSAProject_EntryPoint() {
		return (EAttribute)dsaProjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequential_xdsmlFactory getSequential_xdsmlFactory() {
		return (Sequential_xdsmlFactory)getEFactoryInstance();
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
		sequentialLanguageDefinitionEClass = createEClass(SEQUENTIAL_LANGUAGE_DEFINITION);
		createEReference(sequentialLanguageDefinitionEClass, SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT);

		dsaProjectEClass = createEClass(DSA_PROJECT);
		createEAttribute(dsaProjectEClass, DSA_PROJECT__CODE_EXECUTOR_CLASS);
		createEAttribute(dsaProjectEClass, DSA_PROJECT__ENTRY_POINT);
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
		Xdsml_basePackage theXdsml_basePackage = (Xdsml_basePackage)EPackage.Registry.INSTANCE.getEPackage(Xdsml_basePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		sequentialLanguageDefinitionEClass.getESuperTypes().add(theXdsml_basePackage.getLanguageDefinition());
		dsaProjectEClass.getESuperTypes().add(theXdsml_basePackage.getProjectResource());

		// Initialize classes, features, and operations; add parameters
		initEClass(sequentialLanguageDefinitionEClass, SequentialLanguageDefinition.class, "SequentialLanguageDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSequentialLanguageDefinition_DsaProject(), this.getDSAProject(), null, "dsaProject", null, 0, 1, SequentialLanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dsaProjectEClass, DSAProject.class, "DSAProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDSAProject_CodeExecutorClass(), ecorePackage.getEString(), "codeExecutorClass", null, 0, 1, DSAProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDSAProject_EntryPoint(), ecorePackage.getEString(), "entryPoint", null, 0, 1, DSAProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //Sequential_xdsmlPackageImpl
