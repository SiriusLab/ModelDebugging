/**
 */
package org.gemoc.executionengine.java.sequential_xdsml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

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
 * @see org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlFactory
 * @model kind="package"
 * @generated
 */
public interface Sequential_xdsmlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sequential_xdsml";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/sequential_xdsml";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sequential_xdsml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Sequential_xdsmlPackage eINSTANCE = org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gemoc.executionengine.java.sequential_xdsml.impl.SequentialLanguageDefinitionImpl <em>Sequential Language Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.SequentialLanguageDefinitionImpl
	 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlPackageImpl#getSequentialLanguageDefinition()
	 * @generated
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Domain Model Project</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT = Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT;

	/**
	 * The feature id for the '<em><b>Editor Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__EDITOR_PROJECTS = Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS;

	/**
	 * The feature id for the '<em><b>Animator Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__ANIMATOR_PROJECTS = Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__NAME = Xdsml_basePackage.LANGUAGE_DEFINITION__NAME;

	/**
	 * The feature id for the '<em><b>Melange URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__MELANGE_URI = Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI;

	/**
	 * The feature id for the '<em><b>Need Melange Synchronization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION = Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION;

	/**
	 * The feature id for the '<em><b>Dsa Project</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT = Xdsml_basePackage.LANGUAGE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sequential Language Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION_FEATURE_COUNT = Xdsml_basePackage.LANGUAGE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get File Extensions</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION___GET_FILE_EXTENSIONS = Xdsml_basePackage.LANGUAGE_DEFINITION___GET_FILE_EXTENSIONS;

	/**
	 * The number of operations of the '<em>Sequential Language Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENTIAL_LANGUAGE_DEFINITION_OPERATION_COUNT = Xdsml_basePackage.LANGUAGE_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionengine.java.sequential_xdsml.impl.DSAProjectImpl <em>DSA Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.DSAProjectImpl
	 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlPackageImpl#getDSAProject()
	 * @generated
	 */
	int DSA_PROJECT = 1;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSA_PROJECT__PROJECT_NAME = Xdsml_basePackage.PROJECT_RESOURCE__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSA_PROJECT__PROJECT_KIND = Xdsml_basePackage.PROJECT_RESOURCE__PROJECT_KIND;

	/**
	 * The feature id for the '<em><b>Code Executor Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSA_PROJECT__CODE_EXECUTOR_CLASS = Xdsml_basePackage.PROJECT_RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entry Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSA_PROJECT__ENTRY_POINT = Xdsml_basePackage.PROJECT_RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DSA Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSA_PROJECT_FEATURE_COUNT = Xdsml_basePackage.PROJECT_RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>DSA Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DSA_PROJECT_OPERATION_COUNT = Xdsml_basePackage.PROJECT_RESOURCE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition <em>Sequential Language Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequential Language Definition</em>'.
	 * @see org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition
	 * @generated
	 */
	EClass getSequentialLanguageDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition#getDsaProject <em>Dsa Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dsa Project</em>'.
	 * @see org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition#getDsaProject()
	 * @see #getSequentialLanguageDefinition()
	 * @generated
	 */
	EReference getSequentialLanguageDefinition_DsaProject();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionengine.java.sequential_xdsml.DSAProject <em>DSA Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DSA Project</em>'.
	 * @see org.gemoc.executionengine.java.sequential_xdsml.DSAProject
	 * @generated
	 */
	EClass getDSAProject();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionengine.java.sequential_xdsml.DSAProject#getCodeExecutorClass <em>Code Executor Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code Executor Class</em>'.
	 * @see org.gemoc.executionengine.java.sequential_xdsml.DSAProject#getCodeExecutorClass()
	 * @see #getDSAProject()
	 * @generated
	 */
	EAttribute getDSAProject_CodeExecutorClass();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionengine.java.sequential_xdsml.DSAProject#getEntryPoint <em>Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Entry Point</em>'.
	 * @see org.gemoc.executionengine.java.sequential_xdsml.DSAProject#getEntryPoint()
	 * @see #getDSAProject()
	 * @generated
	 */
	EAttribute getDSAProject_EntryPoint();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Sequential_xdsmlFactory getSequential_xdsmlFactory();

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
		 * The meta object literal for the '{@link org.gemoc.executionengine.java.sequential_xdsml.impl.SequentialLanguageDefinitionImpl <em>Sequential Language Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.SequentialLanguageDefinitionImpl
		 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlPackageImpl#getSequentialLanguageDefinition()
		 * @generated
		 */
		EClass SEQUENTIAL_LANGUAGE_DEFINITION = eINSTANCE.getSequentialLanguageDefinition();

		/**
		 * The meta object literal for the '<em><b>Dsa Project</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEQUENTIAL_LANGUAGE_DEFINITION__DSA_PROJECT = eINSTANCE.getSequentialLanguageDefinition_DsaProject();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionengine.java.sequential_xdsml.impl.DSAProjectImpl <em>DSA Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.DSAProjectImpl
		 * @see org.gemoc.executionengine.java.sequential_xdsml.impl.Sequential_xdsmlPackageImpl#getDSAProject()
		 * @generated
		 */
		EClass DSA_PROJECT = eINSTANCE.getDSAProject();

		/**
		 * The meta object literal for the '<em><b>Code Executor Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSA_PROJECT__CODE_EXECUTOR_CLASS = eINSTANCE.getDSAProject_CodeExecutorClass();

		/**
		 * The meta object literal for the '<em><b>Entry Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DSA_PROJECT__ENTRY_POINT = eINSTANCE.getDSAProject_EntryPoint();

	}

} //Sequential_xdsmlPackage
