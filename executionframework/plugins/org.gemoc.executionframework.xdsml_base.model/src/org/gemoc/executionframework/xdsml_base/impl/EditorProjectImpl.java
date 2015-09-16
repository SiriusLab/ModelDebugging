/**
 */
package org.gemoc.executionframework.xdsml_base.impl;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.gemoc.executionframework.xdsml_base.EditorProject;
import org.gemoc.executionframework.xdsml_base.Xdsml_basePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Editor Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.impl.EditorProjectImpl#getFileExtension <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EditorProjectImpl extends ProjectResourceImpl implements EditorProject {
	/**
	 * The cached value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileExtension()
	 * @generated
	 * @ordered
	 */
	protected EList<String> fileExtension;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditorProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xdsml_basePackage.Literals.EDITOR_PROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getFileExtension() {
		if (fileExtension == null) {
			fileExtension = new EDataTypeUniqueEList<String>(String.class, this, Xdsml_basePackage.EDITOR_PROJECT__FILE_EXTENSION);
		}
		return fileExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xdsml_basePackage.EDITOR_PROJECT__FILE_EXTENSION:
				return getFileExtension();
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
			case Xdsml_basePackage.EDITOR_PROJECT__FILE_EXTENSION:
				getFileExtension().clear();
				getFileExtension().addAll((Collection<? extends String>)newValue);
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
			case Xdsml_basePackage.EDITOR_PROJECT__FILE_EXTENSION:
				getFileExtension().clear();
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
			case Xdsml_basePackage.EDITOR_PROJECT__FILE_EXTENSION:
				return fileExtension != null && !fileExtension.isEmpty();
		}
		return super.eIsSet(featureID);
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
		result.append(" (fileExtension: ");
		result.append(fileExtension);
		result.append(')');
		return result.toString();
	}

} //EditorProjectImpl
