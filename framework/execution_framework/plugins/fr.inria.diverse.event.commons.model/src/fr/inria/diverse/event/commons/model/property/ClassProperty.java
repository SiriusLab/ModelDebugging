/**
 */
package fr.inria.diverse.event.commons.model.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ClassProperty#getTarget <em>Target</em>}</li>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.ClassProperty#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getClassProperty()
 * @model abstract="true"
 * @generated
 */
public interface ClassProperty<T> extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Object)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getClassProperty_Target()
	 * @model kind="reference"
	 * @generated
	 */
	T getTarget();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.ClassProperty#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(T value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getClassProperty_Feature()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EStructuralFeature getFeature();

} // ClassProperty
