/**
 */
package org.gemoc.executionframework.xdsml_base.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.gemoc.executionframework.xdsml_base.AnimatorProject;
import org.gemoc.executionframework.xdsml_base.DomainModelProject;
import org.gemoc.executionframework.xdsml_base.EditorProject;
import org.gemoc.executionframework.xdsml_base.LanguageDefinition;
import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Language Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl#getDomainModelProject <em>Domain Model Project</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl#getEditorProjects <em>Editor Projects</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl#getAnimatorProjects <em>Animator Projects</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl#getMelangeURI <em>Melange URI</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.LanguageDefinitionImpl#isNeedMelangeSynchronization <em>Need Melange Synchronization</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LanguageDefinitionImpl extends MinimalEObjectImpl.Container implements LanguageDefinition {
	/**
	 * The cached value of the '{@link #getDomainModelProject() <em>Domain Model Project</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainModelProject()
	 * @generated
	 * @ordered
	 */
	protected DomainModelProject domainModelProject;

	/**
	 * The cached value of the '{@link #getEditorProjects() <em>Editor Projects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<EditorProject> editorProjects;

	/**
	 * The cached value of the '{@link #getAnimatorProjects() <em>Animator Projects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnimatorProjects()
	 * @generated
	 * @ordered
	 */
	protected EList<AnimatorProject> animatorProjects;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMelangeURI() <em>Melange URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMelangeURI()
	 * @generated
	 * @ordered
	 */
	protected static final String MELANGE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMelangeURI() <em>Melange URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMelangeURI()
	 * @generated
	 * @ordered
	 */
	protected String melangeURI = MELANGE_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #isNeedMelangeSynchronization() <em>Need Melange Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedMelangeSynchronization()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEED_MELANGE_SYNCHRONIZATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedMelangeSynchronization() <em>Need Melange Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedMelangeSynchronization()
	 * @generated
	 * @ordered
	 */
	protected boolean needMelangeSynchronization = NEED_MELANGE_SYNCHRONIZATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LanguageDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xdsml_basePackage.Literals.LANGUAGE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainModelProject getDomainModelProject() {
		return domainModelProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomainModelProject(DomainModelProject newDomainModelProject, NotificationChain msgs) {
		DomainModelProject oldDomainModelProject = domainModelProject;
		domainModelProject = newDomainModelProject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT, oldDomainModelProject, newDomainModelProject);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainModelProject(DomainModelProject newDomainModelProject) {
		if (newDomainModelProject != domainModelProject) {
			NotificationChain msgs = null;
			if (domainModelProject != null)
				msgs = ((InternalEObject)domainModelProject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT, null, msgs);
			if (newDomainModelProject != null)
				msgs = ((InternalEObject)newDomainModelProject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT, null, msgs);
			msgs = basicSetDomainModelProject(newDomainModelProject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT, newDomainModelProject, newDomainModelProject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EditorProject> getEditorProjects() {
		if (editorProjects == null) {
			editorProjects = new EObjectContainmentEList<EditorProject>(EditorProject.class, this, Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS);
		}
		return editorProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AnimatorProject> getAnimatorProjects() {
		if (animatorProjects == null) {
			animatorProjects = new EObjectContainmentEList<AnimatorProject>(AnimatorProject.class, this, Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS);
		}
		return animatorProjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.LANGUAGE_DEFINITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMelangeURI() {
		return melangeURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMelangeURI(String newMelangeURI) {
		String oldMelangeURI = melangeURI;
		melangeURI = newMelangeURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI, oldMelangeURI, melangeURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeedMelangeSynchronization() {
		return needMelangeSynchronization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeedMelangeSynchronization(boolean newNeedMelangeSynchronization) {
		boolean oldNeedMelangeSynchronization = needMelangeSynchronization;
		needMelangeSynchronization = newNeedMelangeSynchronization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION, oldNeedMelangeSynchronization, needMelangeSynchronization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<String> getFileExtensions() {
		EList<String> result = new BasicEList<String>();
		
		// search for a genmodel and possibly file extension in it
		try{
			DomainModelProject eep = this.getDomainModelProject();
			if (eep != null) {
				GenModel genModel = (GenModel)eep.getGenmodel();
				if (genModel != null) {
					// search extension in direct packages
					for(GenPackage genPackage : genModel.getGenPackages()){
						for(String fileExtension : genPackage.getFileExtensionList()){
							if(!result.contains(fileExtension)){
								result.add(fileExtension);
							}
						}
					}
					// search extension in used packages
					for(GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()){
						for(String fileExtension : genPackage.getFileExtensionList()){
							if(!result.contains(fileExtension)){
								result.add(fileExtension);
							}
						}
					}
				}
			}
		} catch (Throwable e){	}
		// aggregate with the other declared files extensions for the editors
		for(EditorProject editorProject : getEditorProjects()){
			for(String fileExtension : editorProject.getFileExtension()){
				if(!result.contains(fileExtension)){
					result.add(fileExtension);
				}
			}
		}		
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT:
				return basicSetDomainModelProject(null, msgs);
			case Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS:
				return ((InternalEList<?>)getEditorProjects()).basicRemove(otherEnd, msgs);
			case Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS:
				return ((InternalEList<?>)getAnimatorProjects()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT:
				return getDomainModelProject();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS:
				return getEditorProjects();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS:
				return getAnimatorProjects();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NAME:
				return getName();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI:
				return getMelangeURI();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION:
				return isNeedMelangeSynchronization();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT:
				setDomainModelProject((DomainModelProject)newValue);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS:
				getEditorProjects().clear();
				getEditorProjects().addAll((Collection<? extends EditorProject>)newValue);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS:
				getAnimatorProjects().clear();
				getAnimatorProjects().addAll((Collection<? extends AnimatorProject>)newValue);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NAME:
				setName((String)newValue);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI:
				setMelangeURI((String)newValue);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION:
				setNeedMelangeSynchronization((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT:
				setDomainModelProject((DomainModelProject)null);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS:
				getEditorProjects().clear();
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS:
				getAnimatorProjects().clear();
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI:
				setMelangeURI(MELANGE_URI_EDEFAULT);
				return;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION:
				setNeedMelangeSynchronization(NEED_MELANGE_SYNCHRONIZATION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION__DOMAIN_MODEL_PROJECT:
				return domainModelProject != null;
			case Xdsml_basePackage.LANGUAGE_DEFINITION__EDITOR_PROJECTS:
				return editorProjects != null && !editorProjects.isEmpty();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__ANIMATOR_PROJECTS:
				return animatorProjects != null && !animatorProjects.isEmpty();
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Xdsml_basePackage.LANGUAGE_DEFINITION__MELANGE_URI:
				return MELANGE_URI_EDEFAULT == null ? melangeURI != null : !MELANGE_URI_EDEFAULT.equals(melangeURI);
			case Xdsml_basePackage.LANGUAGE_DEFINITION__NEED_MELANGE_SYNCHRONIZATION:
				return needMelangeSynchronization != NEED_MELANGE_SYNCHRONIZATION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case Xdsml_basePackage.LANGUAGE_DEFINITION___GET_FILE_EXTENSIONS:
				return getFileExtensions();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", melangeURI: ");
		result.append(melangeURI);
		result.append(", needMelangeSynchronization: ");
		result.append(needMelangeSynchronization);
		result.append(')');
		return result.toString();
	}

} //LanguageDefinitionImpl
