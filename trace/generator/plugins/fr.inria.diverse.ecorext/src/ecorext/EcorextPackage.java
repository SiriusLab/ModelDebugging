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
package ecorext;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see ecorext.EcorextFactory
 * @model kind="package"
 * @generated
 */
public interface EcorextPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ecorext";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://ecorext/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ecorext";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EcorextPackage eINSTANCE = ecorext.impl.EcorextPackageImpl.init();

	/**
	 * The meta object id for the '{@link ecorext.impl.EcorextImpl <em>Ecorext</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecorext.impl.EcorextImpl
	 * @see ecorext.impl.EcorextPackageImpl#getEcorext()
	 * @generated
	 */
	int ECOREXT = 0;

	/**
	 * The feature id for the '<em><b>New Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT__NEW_PACKAGES = 0;

	/**
	 * The feature id for the '<em><b>Classes Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT__CLASSES_EXTENSIONS = 1;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT__RULES = 2;

	/**
	 * The number of structural features of the '<em>Ecorext</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Ecorext</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECOREXT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link ecorext.impl.ClassExtensionImpl <em>Class Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecorext.impl.ClassExtensionImpl
	 * @see ecorext.impl.EcorextPackageImpl#getClassExtension()
	 * @generated
	 */
	int CLASS_EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>Extended Existing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION__EXTENDED_EXISTING_CLASS = 0;

	/**
	 * The feature id for the '<em><b>New Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION__NEW_PROPERTIES = 1;

	/**
	 * The number of structural features of the '<em>Class Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Class Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EXTENSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link ecorext.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecorext.impl.RuleImpl
	 * @see ecorext.impl.EcorextPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 2;

	/**
	 * The feature id for the '<em><b>Called Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__CALLED_RULES = 0;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Step Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__STEP_RULE = 2;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OVERRIDEN_BY = 3;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OVERRIDES = 4;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__CONTAINING_CLASS = 5;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ABSTRACT = 6;

	/**
	 * The feature id for the '<em><b>Main</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__MAIN = 7;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link ecorext.Ecorext <em>Ecorext</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ecorext</em>'.
	 * @see ecorext.Ecorext
	 * @generated
	 */
	EClass getEcorext();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.Ecorext#getNewPackages <em>New Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>New Packages</em>'.
	 * @see ecorext.Ecorext#getNewPackages()
	 * @see #getEcorext()
	 * @generated
	 */
	EReference getEcorext_NewPackages();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.Ecorext#getClassesExtensions <em>Classes Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes Extensions</em>'.
	 * @see ecorext.Ecorext#getClassesExtensions()
	 * @see #getEcorext()
	 * @generated
	 */
	EReference getEcorext_ClassesExtensions();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.Ecorext#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see ecorext.Ecorext#getRules()
	 * @see #getEcorext()
	 * @generated
	 */
	EReference getEcorext_Rules();

	/**
	 * Returns the meta object for class '{@link ecorext.ClassExtension <em>Class Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Extension</em>'.
	 * @see ecorext.ClassExtension
	 * @generated
	 */
	EClass getClassExtension();

	/**
	 * Returns the meta object for the reference '{@link ecorext.ClassExtension#getExtendedExistingClass <em>Extended Existing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extended Existing Class</em>'.
	 * @see ecorext.ClassExtension#getExtendedExistingClass()
	 * @see #getClassExtension()
	 * @generated
	 */
	EReference getClassExtension_ExtendedExistingClass();

	/**
	 * Returns the meta object for the containment reference list '{@link ecorext.ClassExtension#getNewProperties <em>New Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>New Properties</em>'.
	 * @see ecorext.ClassExtension#getNewProperties()
	 * @see #getClassExtension()
	 * @generated
	 */
	EReference getClassExtension_NewProperties();

	/**
	 * Returns the meta object for class '{@link ecorext.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see ecorext.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the reference list '{@link ecorext.Rule#getCalledRules <em>Called Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Called Rules</em>'.
	 * @see ecorext.Rule#getCalledRules()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_CalledRules();

	/**
	 * Returns the meta object for the containment reference '{@link ecorext.Rule#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operation</em>'.
	 * @see ecorext.Rule#getOperation()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Operation();

	/**
	 * Returns the meta object for the attribute '{@link ecorext.Rule#isStepRule <em>Step Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Step Rule</em>'.
	 * @see ecorext.Rule#isStepRule()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_StepRule();

	/**
	 * Returns the meta object for the reference list '{@link ecorext.Rule#getOverridenBy <em>Overriden By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Overriden By</em>'.
	 * @see ecorext.Rule#getOverridenBy()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_OverridenBy();

	/**
	 * Returns the meta object for the reference '{@link ecorext.Rule#getOverrides <em>Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Overrides</em>'.
	 * @see ecorext.Rule#getOverrides()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Overrides();

	/**
	 * Returns the meta object for the reference '{@link ecorext.Rule#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Containing Class</em>'.
	 * @see ecorext.Rule#getContainingClass()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_ContainingClass();

	/**
	 * Returns the meta object for the attribute '{@link ecorext.Rule#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see ecorext.Rule#isAbstract()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link ecorext.Rule#isMain <em>Main</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Main</em>'.
	 * @see ecorext.Rule#isMain()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Main();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EcorextFactory getEcorextFactory();

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
		 * The meta object literal for the '{@link ecorext.impl.EcorextImpl <em>Ecorext</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecorext.impl.EcorextImpl
		 * @see ecorext.impl.EcorextPackageImpl#getEcorext()
		 * @generated
		 */
		EClass ECOREXT = eINSTANCE.getEcorext();

		/**
		 * The meta object literal for the '<em><b>New Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECOREXT__NEW_PACKAGES = eINSTANCE.getEcorext_NewPackages();

		/**
		 * The meta object literal for the '<em><b>Classes Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECOREXT__CLASSES_EXTENSIONS = eINSTANCE.getEcorext_ClassesExtensions();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECOREXT__RULES = eINSTANCE.getEcorext_Rules();

		/**
		 * The meta object literal for the '{@link ecorext.impl.ClassExtensionImpl <em>Class Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecorext.impl.ClassExtensionImpl
		 * @see ecorext.impl.EcorextPackageImpl#getClassExtension()
		 * @generated
		 */
		EClass CLASS_EXTENSION = eINSTANCE.getClassExtension();

		/**
		 * The meta object literal for the '<em><b>Extended Existing Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_EXTENSION__EXTENDED_EXISTING_CLASS = eINSTANCE.getClassExtension_ExtendedExistingClass();

		/**
		 * The meta object literal for the '<em><b>New Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_EXTENSION__NEW_PROPERTIES = eINSTANCE.getClassExtension_NewProperties();

		/**
		 * The meta object literal for the '{@link ecorext.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecorext.impl.RuleImpl
		 * @see ecorext.impl.EcorextPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Called Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__CALLED_RULES = eINSTANCE.getRule_CalledRules();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OPERATION = eINSTANCE.getRule_Operation();

		/**
		 * The meta object literal for the '<em><b>Step Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__STEP_RULE = eINSTANCE.getRule_StepRule();

		/**
		 * The meta object literal for the '<em><b>Overriden By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OVERRIDEN_BY = eINSTANCE.getRule_OverridenBy();

		/**
		 * The meta object literal for the '<em><b>Overrides</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OVERRIDES = eINSTANCE.getRule_Overrides();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__CONTAINING_CLASS = eINSTANCE.getRule_ContainingClass();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__ABSTRACT = eINSTANCE.getRule_Abstract();

		/**
		 * The meta object literal for the '<em><b>Main</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__MAIN = eINSTANCE.getRule_Main();

	}

} //EcorextPackage
