/**
 */
package org.gemoc.executionframework.xdsml_base.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.gemoc.executionframework.xdsml_base.AnimatorProject;
import org.gemoc.executionframework.xdsml_base.DomainModelProject;
import org.gemoc.executionframework.xdsml_base.EditorProject;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;
import org.gemoc.executionframework.xdsml_base.ProjectKind;
import org.gemoc.executionframework.xdsml_base.ProjectResource;
import org.gemoc.executionframework.xdsml_base.SiriusAnimatorProject;
import org.gemoc.executionframework.xdsml_base.SiriusEditorProject;
import org.gemoc.executionframework.xdsml_base.TreeEditorProject;
import org.gemoc.executionframework.xdsml_base.XTextEditorProject;
import org.gemoc.executionframework.xdsml_base.Xdsml_baseFactory;
import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Xdsml_basePackageImpl extends EPackageImpl implements Xdsml_basePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass languageDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass projectResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainModelProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass animatorProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusEditorProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass siriusAnimatorProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xTextEditorProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeEditorProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum projectKindEEnum = null;

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
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Xdsml_basePackageImpl() {
		super(eNS_URI, Xdsml_baseFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Xdsml_basePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Xdsml_basePackage init() {
		if (isInited) return (Xdsml_basePackage)EPackage.Registry.INSTANCE.getEPackage(Xdsml_basePackage.eNS_URI);

		// Obtain or create and register package
		Xdsml_basePackageImpl theXdsml_basePackage = (Xdsml_basePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Xdsml_basePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Xdsml_basePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theXdsml_basePackage.createPackageContents();

		// Initialize created meta-data
		theXdsml_basePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theXdsml_basePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Xdsml_basePackage.eNS_URI, theXdsml_basePackage);
		return theXdsml_basePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLanguageDefinition() {
		return languageDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLanguageDefinition_DomainModelProject() {
		return (EReference)languageDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLanguageDefinition_EditorProjects() {
		return (EReference)languageDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLanguageDefinition_AnimatorProjects() {
		return (EReference)languageDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLanguageDefinition_Name() {
		return (EAttribute)languageDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLanguageDefinition_MelangeURI() {
		return (EAttribute)languageDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLanguageDefinition_NeedMelangeSynchronization() {
		return (EAttribute)languageDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getLanguageDefinition__GetFileExtensions() {
		return languageDefinitionEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProjectResource() {
		return projectResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProjectResource_ProjectName() {
		return (EAttribute)projectResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProjectResource_ProjectKind() {
		return (EAttribute)projectResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditorProject() {
		return editorProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEditorProject_FileExtension() {
		return (EAttribute)editorProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainModelProject() {
		return domainModelProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainModelProject_DefaultRootEObjectQualifiedName() {
		return (EAttribute)domainModelProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainModelProject_Genmodeluri() {
		return (EAttribute)domainModelProjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainModelProject_ModelLoaderClass() {
		return (EAttribute)domainModelProjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDomainModelProject__GetEcoreURI() {
		return domainModelProjectEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDomainModelProject__GetGenmodel() {
		return domainModelProjectEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnimatorProject() {
		return animatorProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusEditorProject() {
		return siriusEditorProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSiriusAnimatorProject() {
		return siriusAnimatorProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXTextEditorProject() {
		return xTextEditorProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getXTextEditorProject_GrammarName() {
		return (EAttribute)xTextEditorProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTreeEditorProject() {
		return treeEditorProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getProjectKind() {
		return projectKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xdsml_baseFactory getXdsml_baseFactory() {
		return (Xdsml_baseFactory)getEFactoryInstance();
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
		languageDefinitionEClass = createEClass(LANGUAGE_DEFINITION);
		createEReference(languageDefinitionEClass, LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT);
		createEReference(languageDefinitionEClass, LANGUAGE_DEFINITION__EDITOR_PROJECTS);
		createEReference(languageDefinitionEClass, LANGUAGE_DEFINITION__ANIMATOR_PROJECTS);
		createEAttribute(languageDefinitionEClass, LANGUAGE_DEFINITION__NAME);
		createEAttribute(languageDefinitionEClass, LANGUAGE_DEFINITION__MELANGE_URI);
		createEAttribute(languageDefinitionEClass, LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION);
		createEOperation(languageDefinitionEClass, LANGUAGE_DEFINITION___GET_FILE_EXTENSIONS);

		projectResourceEClass = createEClass(PROJECT_RESOURCE);
		createEAttribute(projectResourceEClass, PROJECT_RESOURCE__PROJECT_NAME);
		createEAttribute(projectResourceEClass, PROJECT_RESOURCE__PROJECT_KIND);

		editorProjectEClass = createEClass(EDITOR_PROJECT);
		createEAttribute(editorProjectEClass, EDITOR_PROJECT__FILE_EXTENSION);

		domainModelProjectEClass = createEClass(DOMAIN_MODEL_PROJECT);
		createEAttribute(domainModelProjectEClass, DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME);
		createEAttribute(domainModelProjectEClass, DOMAIN_MODEL_PROJECT__GENMODELURI);
		createEAttribute(domainModelProjectEClass, DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS);
		createEOperation(domainModelProjectEClass, DOMAIN_MODEL_PROJECT___GET_ECORE_URI);
		createEOperation(domainModelProjectEClass, DOMAIN_MODEL_PROJECT___GET_GENMODEL);

		animatorProjectEClass = createEClass(ANIMATOR_PROJECT);

		siriusEditorProjectEClass = createEClass(SIRIUS_EDITOR_PROJECT);

		siriusAnimatorProjectEClass = createEClass(SIRIUS_ANIMATOR_PROJECT);

		xTextEditorProjectEClass = createEClass(XTEXT_EDITOR_PROJECT);
		createEAttribute(xTextEditorProjectEClass, XTEXT_EDITOR_PROJECT__GRAMMAR_NAME);

		treeEditorProjectEClass = createEClass(TREE_EDITOR_PROJECT);

		// Create enums
		projectKindEEnum = createEEnum(PROJECT_KIND);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		editorProjectEClass.getESuperTypes().add(this.getProjectResource());
		domainModelProjectEClass.getESuperTypes().add(this.getProjectResource());
		animatorProjectEClass.getESuperTypes().add(this.getProjectResource());
		siriusEditorProjectEClass.getESuperTypes().add(this.getEditorProject());
		siriusAnimatorProjectEClass.getESuperTypes().add(this.getAnimatorProject());
		xTextEditorProjectEClass.getESuperTypes().add(this.getEditorProject());
		treeEditorProjectEClass.getESuperTypes().add(this.getEditorProject());

		// Initialize classes, features, and operations; add parameters
		initEClass(languageDefinitionEClass, LanguageDefinition.class, "LanguageDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLanguageDefinition_DomainModelProject(), this.getDomainModelProject(), null, "domainModelProject", null, 1, 1, LanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLanguageDefinition_EditorProjects(), this.getEditorProject(), null, "editorProjects", null, 0, -1, LanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLanguageDefinition_AnimatorProjects(), this.getAnimatorProject(), null, "animatorProjects", null, 0, -1, LanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLanguageDefinition_Name(), ecorePackage.getEString(), "name", null, 1, 1, LanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLanguageDefinition_MelangeURI(), ecorePackage.getEString(), "melangeURI", null, 0, 1, LanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLanguageDefinition_NeedMelangeSynchronization(), ecorePackage.getEBoolean(), "needMelangeSynchronization", "false", 0, 1, LanguageDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getLanguageDefinition__GetFileExtensions(), ecorePackage.getEString(), "getFileExtensions", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(projectResourceEClass, ProjectResource.class, "ProjectResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProjectResource_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, ProjectResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProjectResource_ProjectKind(), this.getProjectKind(), "projectKind", null, 1, 1, ProjectResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editorProjectEClass, EditorProject.class, "EditorProject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEditorProject_FileExtension(), ecorePackage.getEString(), "fileExtension", null, 0, -1, EditorProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainModelProjectEClass, DomainModelProject.class, "DomainModelProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDomainModelProject_DefaultRootEObjectQualifiedName(), ecorePackage.getEString(), "defaultRootEObjectQualifiedName", null, 0, 1, DomainModelProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainModelProject_Genmodeluri(), ecorePackage.getEString(), "genmodeluri", null, 0, 1, DomainModelProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainModelProject_ModelLoaderClass(), ecorePackage.getEString(), "modelLoaderClass", null, 0, 1, DomainModelProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getDomainModelProject__GetEcoreURI(), ecorePackage.getEString(), "getEcoreURI", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDomainModelProject__GetGenmodel(), ecorePackage.getEObject(), "getGenmodel", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(animatorProjectEClass, AnimatorProject.class, "AnimatorProject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(siriusEditorProjectEClass, SiriusEditorProject.class, "SiriusEditorProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(siriusAnimatorProjectEClass, SiriusAnimatorProject.class, "SiriusAnimatorProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(xTextEditorProjectEClass, XTextEditorProject.class, "XTextEditorProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getXTextEditorProject_GrammarName(), ecorePackage.getEString(), "grammarName", null, 1, 1, XTextEditorProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(treeEditorProjectEClass, TreeEditorProject.class, "TreeEditorProject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(projectKindEEnum, ProjectKind.class, "ProjectKind");
		addEEnumLiteral(projectKindEEnum, ProjectKind.ECLIPSE_PLUGIN);
		addEEnumLiteral(projectKindEEnum, ProjectKind.MAVEN_JAR);

		// Create resource
		createResource(eNS_URI);
	}

} //Xdsml_basePackageImpl
