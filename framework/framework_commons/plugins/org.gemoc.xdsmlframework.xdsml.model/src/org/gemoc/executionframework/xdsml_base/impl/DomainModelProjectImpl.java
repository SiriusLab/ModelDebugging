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
package org.gemoc.executionframework.xdsml_base.impl;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.gemoc.executionframework.xdsml_base.DomainModelProject;
import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Model Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl#getDefaultRootEObjectQualifiedName <em>Default Root EObject Qualified Name</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl#getGenmodeluri <em>Genmodeluri</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.DomainModelProjectImpl#getModelLoaderClass <em>Model Loader Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainModelProjectImpl extends ProjectResourceImpl implements DomainModelProject {
	/**
	 * The default value of the '{@link #getDefaultRootEObjectQualifiedName() <em>Default Root EObject Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultRootEObjectQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getDefaultRootEObjectQualifiedName() <em>Default Root EObject Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultRootEObjectQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected String defaultRootEObjectQualifiedName = DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getGenmodeluri() <em>Genmodeluri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenmodeluri()
	 * @generated
	 * @ordered
	 */
	protected static final String GENMODELURI_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getGenmodeluri() <em>Genmodeluri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenmodeluri()
	 * @generated
	 * @ordered
	 */
	protected String genmodeluri = GENMODELURI_EDEFAULT;

	/**
	 * The default value of the '{@link #getModelLoaderClass() <em>Model Loader Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelLoaderClass()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_LOADER_CLASS_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getModelLoaderClass() <em>Model Loader Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelLoaderClass()
	 * @generated
	 * @ordered
	 */
	protected String modelLoaderClass = MODEL_LOADER_CLASS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainModelProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xdsml_basePackage.Literals.DOMAIN_MODEL_PROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultRootEObjectQualifiedName() {
		return defaultRootEObjectQualifiedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultRootEObjectQualifiedName(String newDefaultRootEObjectQualifiedName) {
		String oldDefaultRootEObjectQualifiedName = defaultRootEObjectQualifiedName;
		defaultRootEObjectQualifiedName = newDefaultRootEObjectQualifiedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME, oldDefaultRootEObjectQualifiedName, defaultRootEObjectQualifiedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGenmodeluri() {
		return genmodeluri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenmodeluri(String newGenmodeluri) {
		String oldGenmodeluri = genmodeluri;
		genmodeluri = newGenmodeluri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.DOMAIN_MODEL_PROJECT__GENMODELURI, oldGenmodeluri, genmodeluri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModelLoaderClass() {
		return modelLoaderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelLoaderClass(String newModelLoaderClass) {
		String oldModelLoaderClass = modelLoaderClass;
		modelLoaderClass = newModelLoaderClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xdsml_basePackage.DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS, oldModelLoaderClass, modelLoaderClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEcoreURI() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EObject getGenmodel() {
		try{
			if (getGenmodeluri() != null || !getGenmodeluri().isEmpty()) {
				String genModeluri = getGenmodeluri().replace("platform:/plugin", "platform:/resource");
				final ResourceSet resourceSet = new ResourceSetImpl();
				
				Resource resource = null;
				try{
					// try first in workspace
					resource = resourceSet.getResource(URI.createURI(genModeluri), true);
				}catch(Exception e){
					// if fail then try as platform:/plugin
					genModeluri = genModeluri.replace("platform:/resource", "platform:/plugin");
					resource = resourceSet.getResource(URI.createURI(genModeluri), true);
				}
				if (resource.getContents().size() > 0) {
					Object firstContent = resource.getContents().get(0);
					if (firstContent instanceof GenModel){
						GenModel genModel = (GenModel)firstContent;	
						return genModel;
					}
				}
			}		
		} catch (Throwable e){	}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME:
				return getDefaultRootEObjectQualifiedName();
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__GENMODELURI:
				return getGenmodeluri();
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS:
				return getModelLoaderClass();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME:
				setDefaultRootEObjectQualifiedName((String)newValue);
				return;
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__GENMODELURI:
				setGenmodeluri((String)newValue);
				return;
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS:
				setModelLoaderClass((String)newValue);
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
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME:
				setDefaultRootEObjectQualifiedName(DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME_EDEFAULT);
				return;
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__GENMODELURI:
				setGenmodeluri(GENMODELURI_EDEFAULT);
				return;
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS:
				setModelLoaderClass(MODEL_LOADER_CLASS_EDEFAULT);
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
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME:
				return DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME_EDEFAULT == null ? defaultRootEObjectQualifiedName != null : !DEFAULT_ROOT_EOBJECT_QUALIFIED_NAME_EDEFAULT.equals(defaultRootEObjectQualifiedName);
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__GENMODELURI:
				return GENMODELURI_EDEFAULT == null ? genmodeluri != null : !GENMODELURI_EDEFAULT.equals(genmodeluri);
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT__MODEL_LOADER_CLASS:
				return MODEL_LOADER_CLASS_EDEFAULT == null ? modelLoaderClass != null : !MODEL_LOADER_CLASS_EDEFAULT.equals(modelLoaderClass);
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
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT___GET_ECORE_URI:
				return getEcoreURI();
			case Xdsml_basePackage.DOMAIN_MODEL_PROJECT___GET_GENMODEL:
				return getGenmodel();
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
		result.append(" (defaultRootEObjectQualifiedName: ");
		result.append(defaultRootEObjectQualifiedName);
		result.append(", genmodeluri: ");
		result.append(genmodeluri);
		result.append(", modelLoaderClass: ");
		result.append(modelLoaderClass);
		result.append(')');
		return result.toString();
	}

} //DomainModelProjectImpl
