/**
 */
package org.gemoc.executionengine.java.sequential_xdsml;

import org.gemoc.executionframework.xdsml_base.LanguageDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequential Language Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition#getDsaProject <em>Dsa Project</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlPackage#getSequentialLanguageDefinition()
 * @model
 * @generated
 */
public interface SequentialLanguageDefinition extends LanguageDefinition {
	/**
	 * Returns the value of the '<em><b>Dsa Project</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dsa Project</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dsa Project</em>' containment reference.
	 * @see #setDsaProject(DSAProject)
	 * @see org.gemoc.executionengine.java.sequential_xdsml.Sequential_xdsmlPackage#getSequentialLanguageDefinition_DsaProject()
	 * @model containment="true"
	 * @generated
	 */
	DSAProject getDsaProject();

	/**
	 * Sets the value of the '{@link org.gemoc.executionengine.java.sequential_xdsml.SequentialLanguageDefinition#getDsaProject <em>Dsa Project</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dsa Project</em>' containment reference.
	 * @see #getDsaProject()
	 * @generated
	 */
	void setDsaProject(DSAProject value);

} // SequentialLanguageDefinition
