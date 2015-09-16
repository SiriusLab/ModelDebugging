/**
 */
package org.gemoc.executionframework.xdsml_base.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.gemoc.executionframework.xdsml_base.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Xdsml_baseFactoryImpl extends EFactoryImpl implements Xdsml_baseFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Xdsml_baseFactory init() {
		try {
			Xdsml_baseFactory theXdsml_baseFactory = (Xdsml_baseFactory)EPackage.Registry.INSTANCE.getEFactory(Xdsml_basePackage.eNS_URI);
			if (theXdsml_baseFactory != null) {
				return theXdsml_baseFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Xdsml_baseFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xdsml_baseFactoryImpl() {
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
			case Xdsml_basePackage.LANGUAGE_DEFINITION: return createLanguageDefinition();
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT: return createDomainModelProject();
			case Xdsml_basePackage.SIRIUS_EDITOR_PROJECT: return createSiriusEditorProject();
			case Xdsml_basePackage.SIRIUS_ANIMATOR_PROJECT: return createSiriusAnimatorProject();
			case Xdsml_basePackage.XTEXT_EDITOR_PROJECT: return createXTextEditorProject();
			case Xdsml_basePackage.TREE_EDITOR_PROJECT: return createTreeEditorProject();
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
			case Xdsml_basePackage.PROJECT_KIND:
				return createProjectKindFromString(eDataType, initialValue);
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
			case Xdsml_basePackage.PROJECT_KIND:
				return convertProjectKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LanguageDefinition createLanguageDefinition() {
		LanguageDefinitionImpl languageDefinition = new LanguageDefinitionImpl();
		return languageDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainModelProject createDomainModelProject() {
		DomainModelProjectImpl domainModelProject = new DomainModelProjectImpl();
		return domainModelProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusEditorProject createSiriusEditorProject() {
		SiriusEditorProjectImpl siriusEditorProject = new SiriusEditorProjectImpl();
		return siriusEditorProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SiriusAnimatorProject createSiriusAnimatorProject() {
		SiriusAnimatorProjectImpl siriusAnimatorProject = new SiriusAnimatorProjectImpl();
		return siriusAnimatorProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XTextEditorProject createXTextEditorProject() {
		XTextEditorProjectImpl xTextEditorProject = new XTextEditorProjectImpl();
		return xTextEditorProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TreeEditorProject createTreeEditorProject() {
		TreeEditorProjectImpl treeEditorProject = new TreeEditorProjectImpl();
		return treeEditorProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectKind createProjectKindFromString(EDataType eDataType, String initialValue) {
		ProjectKind result = ProjectKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProjectKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Xdsml_basePackage getXdsml_basePackage() {
		return (Xdsml_basePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Xdsml_basePackage getPackage() {
		return Xdsml_basePackage.eINSTANCE;
	}

} //Xdsml_baseFactoryImpl
