/**
 */
package fr.inria.diverse.event.commons.model.property;

import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.StepProperty#getStepping <em>Stepping</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getStepProperty()
 * @model abstract="true"
 * @generated
 */
public interface StepProperty extends Property {

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EOperation getOperation();

	/**
	 * Returns the value of the '<em><b>Stepping</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.diverse.event.commons.model.property.Stepping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stepping</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stepping</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.Stepping
	 * @see #setStepping(Stepping)
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getStepProperty_Stepping()
	 * @model
	 * @generated
	 */
	Stepping getStepping();

	/**
	 * Sets the value of the '{@link fr.inria.diverse.event.commons.model.property.StepProperty#getStepping <em>Stepping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stepping</em>' attribute.
	 * @see fr.inria.diverse.event.commons.model.property.Stepping
	 * @see #getStepping()
	 * @generated
	 */
	void setStepping(Stepping value);
} // StepProperty
