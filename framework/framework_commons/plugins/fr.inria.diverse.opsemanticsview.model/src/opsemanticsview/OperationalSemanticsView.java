/**
 */
package opsemanticsview;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operational Semantics View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getRules <em>Rules</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getDynamicProperties <em>Dynamic Properties</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getDynamicClasses <em>Dynamic Classes</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getExecutionToASmapping <em>Execution To ASmapping</em>}</li>
 * </ul>
 *
 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView()
 * @model
 * @generated
 */
public interface OperationalSemanticsView extends EObject {
	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link opsemanticsview.Rule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<Rule> getRules();

	/**
	 * Returns the value of the '<em><b>Dynamic Properties</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EStructuralFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Properties</em>' reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_DynamicProperties()
	 * @model
	 * @generated
	 */
	EList<EStructuralFeature> getDynamicProperties();

	/**
	 * Returns the value of the '<em><b>Dynamic Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Classes</em>' reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_DynamicClasses()
	 * @model
	 * @generated
	 */
	EList<EClass> getDynamicClasses();

	/**
	 * Returns the value of the '<em><b>Execution To ASmapping</b></em>' containment reference list.
	 * The list contents are of type {@link opsemanticsview.ExecutionToASEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution To ASmapping</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution To ASmapping</em>' containment reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_ExecutionToASmapping()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExecutionToASEntry> getExecutionToASmapping();

} // OperationalSemanticsView
