/**
 */
package org.gemoc.executionframework.xdsml_base;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectKind <em>Project Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getProjectResource()
 * @model abstract="true"
 * @generated
 */
public interface ProjectResource extends EObject {

	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getProjectResource_ProjectName()
	 * @model
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Project Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.gemoc.executionframework.xdsml_base.ProjectKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Kind</em>' attribute.
	 * @see org.gemoc.executionframework.xdsml_base.ProjectKind
	 * @see #setProjectKind(ProjectKind)
	 * @see org.gemoc.executionframework.xdsml_base.Xdsml_basePackage#getProjectResource_ProjectKind()
	 * @model required="true"
	 * @generated
	 */
	ProjectKind getProjectKind();

	/**
	 * Sets the value of the '{@link org.gemoc.executionframework.xdsml_base.ProjectResource#getProjectKind <em>Project Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Kind</em>' attribute.
	 * @see org.gemoc.executionframework.xdsml_base.ProjectKind
	 * @see #getProjectKind()
	 * @generated
	 */
	void setProjectKind(ProjectKind value);
} // ProjectResource
