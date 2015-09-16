/**
 */
package org.gemoc.executionframework.xdsml_base;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
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
 * @see org.gemoc.executionframework.xdsml_base.Xdsml_baseFactory
 * @model kind="package"
 * @generated
 */
public interface Xdsml_basePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "xdsml_base";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/xdsml_base";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xdsml_base";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Xdsml_basePackage eINSTANCE = org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl <em>Language Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getLanguageDefinition()
	 * @generated
	 */
	int LANGUAGE_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Domain Model Project</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT = 0;

	/**
	 * The feature id for the '<em><b>Editor Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION__EDITOR_PROJECTS = 1;

	/**
	 * The feature id for the '<em><b>Animator Projects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION__ANIMATOR_PROJECTS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION__NAME = 3;

	/**
	 * The feature id for the '<em><b>Melange URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION__MELANGE_URI = 4;

	/**
	 * The feature id for the '<em><b>Need Melange Synchronization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION = 5;

	/**
	 * The number of structural features of the '<em>Language Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION_FEATURE_COUNT = 6;

	/**
	 * The operation id for the '<em>Get File Extensions</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION___GET_FILE_EXTENSIONS = 0;

	/**
	 * The number of operations of the '<em>Language Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_DEFINITION_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.ProjectResourceImpl <em>Project Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.ProjectResourceImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getProjectResource()
	 * @generated
	 */
	int PROJECT_RESOURCE = 1;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_RESOURCE__PROJECT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_RESOURCE__PROJECT_KIND = 1;

	/**
	 * The number of structural features of the '<em>Project Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_RESOURCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Project Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.EditorProjectImpl <em>Editor Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.EditorProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getEditorProject()
	 * @generated
	 */
	int EDITOR_PROJECT = 2;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PROJECT__PROJECT_NAME = PROJECT_RESOURCE__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PROJECT__PROJECT_KIND = PROJECT_RESOURCE__PROJECT_KIND;

	/**
	 * The feature id for the '<em><b>File Extension</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PROJECT__FILE_EXTENSION = PROJECT_RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PROJECT_FEATURE_COUNT = PROJECT_RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PROJECT_OPERATION_COUNT = PROJECT_RESOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl <em>Domain Model Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getDomainModelProject()
	 * @generated
	 */
	int DOMAIN_MODEL_PROJECT = 3;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT__PROJECT_NAME = PROJECT_RESOURCE__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT__PROJECT_KIND = PROJECT_RESOURCE__PROJECT_KIND;

	/**
	 * The feature id for the '<em><b>Default Root EObject Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME = PROJECT_RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Genmodeluri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT__GENMODELURI = PROJECT_RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Model Loader Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS = PROJECT_RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Domain Model Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT_FEATURE_COUNT = PROJECT_RESOURCE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Ecore URI</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT___GET_ECORE_URI = PROJECT_RESOURCE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Genmodel</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT___GET_GENMODEL = PROJECT_RESOURCE_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Domain Model Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_PROJECT_OPERATION_COUNT = PROJECT_RESOURCE_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.AnimatorProjectImpl <em>Animator Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.AnimatorProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getAnimatorProject()
	 * @generated
	 */
	int ANIMATOR_PROJECT = 4;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_PROJECT__PROJECT_NAME = PROJECT_RESOURCE__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_PROJECT__PROJECT_KIND = PROJECT_RESOURCE__PROJECT_KIND;

	/**
	 * The number of structural features of the '<em>Animator Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_PROJECT_FEATURE_COUNT = PROJECT_RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Animator Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANIMATOR_PROJECT_OPERATION_COUNT = PROJECT_RESOURCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.SiriusEditorProjectImpl <em>Sirius Editor Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.SiriusEditorProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getSiriusEditorProject()
	 * @generated
	 */
	int SIRIUS_EDITOR_PROJECT = 5;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EDITOR_PROJECT__PROJECT_NAME = EDITOR_PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EDITOR_PROJECT__PROJECT_KIND = EDITOR_PROJECT__PROJECT_KIND;

	/**
	 * The feature id for the '<em><b>File Extension</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EDITOR_PROJECT__FILE_EXTENSION = EDITOR_PROJECT__FILE_EXTENSION;

	/**
	 * The number of structural features of the '<em>Sirius Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EDITOR_PROJECT_FEATURE_COUNT = EDITOR_PROJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sirius Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_EDITOR_PROJECT_OPERATION_COUNT = EDITOR_PROJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.SiriusAnimatorProjectImpl <em>Sirius Animator Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.SiriusAnimatorProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getSiriusAnimatorProject()
	 * @generated
	 */
	int SIRIUS_ANIMATOR_PROJECT = 6;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_ANIMATOR_PROJECT__PROJECT_NAME = ANIMATOR_PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_ANIMATOR_PROJECT__PROJECT_KIND = ANIMATOR_PROJECT__PROJECT_KIND;

	/**
	 * The number of structural features of the '<em>Sirius Animator Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_ANIMATOR_PROJECT_FEATURE_COUNT = ANIMATOR_PROJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sirius Animator Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIRIUS_ANIMATOR_PROJECT_OPERATION_COUNT = ANIMATOR_PROJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.XTextEditorProjectImpl <em>XText Editor Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.XTextEditorProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getXTextEditorProject()
	 * @generated
	 */
	int XTEXT_EDITOR_PROJECT = 7;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XTEXT_EDITOR_PROJECT__PROJECT_NAME = EDITOR_PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XTEXT_EDITOR_PROJECT__PROJECT_KIND = EDITOR_PROJECT__PROJECT_KIND;

	/**
	 * The feature id for the '<em><b>File Extension</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XTEXT_EDITOR_PROJECT__FILE_EXTENSION = EDITOR_PROJECT__FILE_EXTENSION;

	/**
	 * The feature id for the '<em><b>Grammar Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XTEXT_EDITOR_PROJECT__GRAMMAR_NAME = EDITOR_PROJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>XText Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XTEXT_EDITOR_PROJECT_FEATURE_COUNT = EDITOR_PROJECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>XText Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XTEXT_EDITOR_PROJECT_OPERATION_COUNT = EDITOR_PROJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.impl.TreeEditorProjectImpl <em>Tree Editor Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.impl.TreeEditorProjectImpl
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getTreeEditorProject()
	 * @generated
	 */
	int TREE_EDITOR_PROJECT = 8;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_EDITOR_PROJECT__PROJECT_NAME = EDITOR_PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Project Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_EDITOR_PROJECT__PROJECT_KIND = EDITOR_PROJECT__PROJECT_KIND;

	/**
	 * The feature id for the '<em><b>File Extension</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_EDITOR_PROJECT__FILE_EXTENSION = EDITOR_PROJECT__FILE_EXTENSION;

	/**
	 * The number of structural features of the '<em>Tree Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_EDITOR_PROJECT_FEATURE_COUNT = EDITOR_PROJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Tree Editor Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_EDITOR_PROJECT_OPERATION_COUNT = EDITOR_PROJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.gemoc.executionframework.xdsml_base.ProjectKind <em>Project Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.gemoc.executionframework.xdsml_base.ProjectKind
	 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getProjectKind()
	 * @generated
	 */
	int PROJECT_KIND = 9;


	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition <em>Language Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Language Definition</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition
	 * @generated
	 */
	EClass getLanguageDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getDomainModelProject <em>Domain Model Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Domain Model Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#getDomainModelProject()
	 * @see #getLanguageDefinition()
	 * @generated
	 */
	EReference getLanguageDefinition_DomainModelProject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getEditorProjects <em>Editor Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Editor Projects</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#getEditorProjects()
	 * @see #getLanguageDefinition()
	 * @generated
	 */
	EReference getLanguageDefinition_EditorProjects();

	/**
	 * Returns the meta object for the containment reference list '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getAnimatorProjects <em>Animator Projects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Animator Projects</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#getAnimatorProjects()
	 * @see #getLanguageDefinition()
	 * @generated
	 */
	EReference getLanguageDefinition_AnimatorProjects();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#getName()
	 * @see #getLanguageDefinition()
	 * @generated
	 */
	EAttribute getLanguageDefinition_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getMelangeURI <em>Melange URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Melange URI</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#getMelangeURI()
	 * @see #getLanguageDefinition()
	 * @generated
	 */
	EAttribute getLanguageDefinition_MelangeURI();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#isNeedMelangeSynchronization <em>Need Melange Synchronization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Need Melange Synchronization</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#isNeedMelangeSynchronization()
	 * @see #getLanguageDefinition()
	 * @generated
	 */
	EAttribute getLanguageDefinition_NeedMelangeSynchronization();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.xdsml_base.LanguageDefinition#getFileExtensions() <em>Get File Extensions</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get File Extensions</em>' operation.
	 * @see org.gemoc.executionframework.xdsml_base.LanguageDefinition#getFileExtensions()
	 * @generated
	 */
	EOperation getLanguageDefinition__GetFileExtensions();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.ProjectResource <em>Project Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project Resource</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.ProjectResource
	 * @generated
	 */
	EClass getProjectResource();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectName()
	 * @see #getProjectResource()
	 * @generated
	 */
	EAttribute getProjectResource_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectKind <em>Project Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Kind</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectKind()
	 * @see #getProjectResource()
	 * @generated
	 */
	EAttribute getProjectResource_ProjectKind();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.EditorProject <em>Editor Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editor Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.EditorProject
	 * @generated
	 */
	EClass getEditorProject();

	/**
	 * Returns the meta object for the attribute list '{@link org.gemoc.executionframework.xdsml_base.EditorProject#getFileExtension <em>File Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>File Extension</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.EditorProject#getFileExtension()
	 * @see #getEditorProject()
	 * @generated
	 */
	EAttribute getEditorProject_FileExtension();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject <em>Domain Model Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Model Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.DomainModelProject
	 * @generated
	 */
	EClass getDomainModelProject();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getDefaultRootEObjectQualifiedName <em>Default Root EObject Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Root EObject Qualified Name</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.DomainModelProject#getDefaultRootEObjectQualifiedName()
	 * @see #getDomainModelProject()
	 * @generated
	 */
	EAttribute getDomainModelProject_DefaultRootEObjectQualifiedName();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getGenmodeluri <em>Genmodeluri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Genmodeluri</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.DomainModelProject#getGenmodeluri()
	 * @see #getDomainModelProject()
	 * @generated
	 */
	EAttribute getDomainModelProject_Genmodeluri();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getModelLoaderClass <em>Model Loader Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Loader Class</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.DomainModelProject#getModelLoaderClass()
	 * @see #getDomainModelProject()
	 * @generated
	 */
	EAttribute getDomainModelProject_ModelLoaderClass();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getEcoreURI() <em>Get Ecore URI</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Ecore URI</em>' operation.
	 * @see org.gemoc.executionframework.xdsml_base.DomainModelProject#getEcoreURI()
	 * @generated
	 */
	EOperation getDomainModelProject__GetEcoreURI();

	/**
	 * Returns the meta object for the '{@link org.gemoc.executionframework.xdsml_base.DomainModelProject#getGenmodel() <em>Get Genmodel</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Genmodel</em>' operation.
	 * @see org.gemoc.executionframework.xdsml_base.DomainModelProject#getGenmodel()
	 * @generated
	 */
	EOperation getDomainModelProject__GetGenmodel();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.AnimatorProject <em>Animator Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Animator Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.AnimatorProject
	 * @generated
	 */
	EClass getAnimatorProject();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.SiriusEditorProject <em>Sirius Editor Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Editor Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.SiriusEditorProject
	 * @generated
	 */
	EClass getSiriusEditorProject();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.SiriusAnimatorProject <em>Sirius Animator Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sirius Animator Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.SiriusAnimatorProject
	 * @generated
	 */
	EClass getSiriusAnimatorProject();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.XTextEditorProject <em>XText Editor Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XText Editor Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.XTextEditorProject
	 * @generated
	 */
	EClass getXTextEditorProject();

	/**
	 * Returns the meta object for the attribute '{@link org.gemoc.executionframework.xdsml_base.XTextEditorProject#getGrammarName <em>Grammar Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grammar Name</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.XTextEditorProject#getGrammarName()
	 * @see #getXTextEditorProject()
	 * @generated
	 */
	EAttribute getXTextEditorProject_GrammarName();

	/**
	 * Returns the meta object for class '{@link org.gemoc.executionframework.xdsml_base.TreeEditorProject <em>Tree Editor Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Editor Project</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.TreeEditorProject
	 * @generated
	 */
	EClass getTreeEditorProject();

	/**
	 * Returns the meta object for enum '{@link org.gemoc.executionframework.xdsml_base.ProjectKind <em>Project Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Project Kind</em>'.
	 * @see org.gemoc.executionframework.xdsml_base.ProjectKind
	 * @generated
	 */
	EEnum getProjectKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Xdsml_baseFactory getXdsml_baseFactory();

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
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl <em>Language Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getLanguageDefinition()
		 * @generated
		 */
		EClass LANGUAGE_DEFINITION = eINSTANCE.getLanguageDefinition();

		/**
		 * The meta object literal for the '<em><b>Domain Model Project</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT = eINSTANCE.getLanguageDefinition_DomainModelProject();

		/**
		 * The meta object literal for the '<em><b>Editor Projects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LANGUAGE_DEFINITION__EDITOR_PROJECTS = eINSTANCE.getLanguageDefinition_EditorProjects();

		/**
		 * The meta object literal for the '<em><b>Animator Projects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LANGUAGE_DEFINITION__ANIMATOR_PROJECTS = eINSTANCE.getLanguageDefinition_AnimatorProjects();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LANGUAGE_DEFINITION__NAME = eINSTANCE.getLanguageDefinition_Name();

		/**
		 * The meta object literal for the '<em><b>Melange URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LANGUAGE_DEFINITION__MELANGE_URI = eINSTANCE.getLanguageDefinition_MelangeURI();

		/**
		 * The meta object literal for the '<em><b>Need Melange Synchronization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION = eINSTANCE.getLanguageDefinition_NeedMelangeSynchronization();

		/**
		 * The meta object literal for the '<em><b>Get File Extensions</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation LANGUAGE_DEFINITION___GET_FILE_EXTENSIONS = eINSTANCE.getLanguageDefinition__GetFileExtensions();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.ProjectResourceImpl <em>Project Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.ProjectResourceImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getProjectResource()
		 * @generated
		 */
		EClass PROJECT_RESOURCE = eINSTANCE.getProjectResource();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT_RESOURCE__PROJECT_NAME = eINSTANCE.getProjectResource_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Project Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT_RESOURCE__PROJECT_KIND = eINSTANCE.getProjectResource_ProjectKind();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.EditorProjectImpl <em>Editor Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.EditorProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getEditorProject()
		 * @generated
		 */
		EClass EDITOR_PROJECT = eINSTANCE.getEditorProject();

		/**
		 * The meta object literal for the '<em><b>File Extension</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDITOR_PROJECT__FILE_EXTENSION = eINSTANCE.getEditorProject_FileExtension();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl <em>Domain Model Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getDomainModelProject()
		 * @generated
		 */
		EClass DOMAIN_MODEL_PROJECT = eINSTANCE.getDomainModelProject();

		/**
		 * The meta object literal for the '<em><b>Default Root EObject Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME = eINSTANCE.getDomainModelProject_DefaultRootEObjectQualifiedName();

		/**
		 * The meta object literal for the '<em><b>Genmodeluri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_MODEL_PROJECT__GENMODELURI = eINSTANCE.getDomainModelProject_Genmodeluri();

		/**
		 * The meta object literal for the '<em><b>Model Loader Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS = eINSTANCE.getDomainModelProject_ModelLoaderClass();

		/**
		 * The meta object literal for the '<em><b>Get Ecore URI</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOMAIN_MODEL_PROJECT___GET_ECORE_URI = eINSTANCE.getDomainModelProject__GetEcoreURI();

		/**
		 * The meta object literal for the '<em><b>Get Genmodel</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOMAIN_MODEL_PROJECT___GET_GENMODEL = eINSTANCE.getDomainModelProject__GetGenmodel();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.AnimatorProjectImpl <em>Animator Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.AnimatorProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getAnimatorProject()
		 * @generated
		 */
		EClass ANIMATOR_PROJECT = eINSTANCE.getAnimatorProject();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.SiriusEditorProjectImpl <em>Sirius Editor Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.SiriusEditorProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getSiriusEditorProject()
		 * @generated
		 */
		EClass SIRIUS_EDITOR_PROJECT = eINSTANCE.getSiriusEditorProject();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.SiriusAnimatorProjectImpl <em>Sirius Animator Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.SiriusAnimatorProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getSiriusAnimatorProject()
		 * @generated
		 */
		EClass SIRIUS_ANIMATOR_PROJECT = eINSTANCE.getSiriusAnimatorProject();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.XTextEditorProjectImpl <em>XText Editor Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.XTextEditorProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getXTextEditorProject()
		 * @generated
		 */
		EClass XTEXT_EDITOR_PROJECT = eINSTANCE.getXTextEditorProject();

		/**
		 * The meta object literal for the '<em><b>Grammar Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XTEXT_EDITOR_PROJECT__GRAMMAR_NAME = eINSTANCE.getXTextEditorProject_GrammarName();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.impl.TreeEditorProjectImpl <em>Tree Editor Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.impl.TreeEditorProjectImpl
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getTreeEditorProject()
		 * @generated
		 */
		EClass TREE_EDITOR_PROJECT = eINSTANCE.getTreeEditorProject();

		/**
		 * The meta object literal for the '{@link org.gemoc.executionframework.xdsml_base.ProjectKind <em>Project Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.gemoc.executionframework.xdsml_base.ProjectKind
		 * @see org.gemoc.executionframework.xdsml_base.impl.Xdsml_basePackageImpl#getProjectKind()
		 * @generated
		 */
		EEnum PROJECT_KIND = eINSTANCE.getProjectKind();

	}

} //Xdsml_basePackage
