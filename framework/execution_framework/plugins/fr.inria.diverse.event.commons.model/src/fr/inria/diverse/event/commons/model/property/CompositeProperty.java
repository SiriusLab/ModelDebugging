/**
 */
package fr.inria.diverse.event.commons.model.property;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.event.commons.model.property.CompositeProperty#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getCompositeProperty()
 * @model
 * @generated
 */
public interface CompositeProperty extends Property {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link fr.inria.diverse.event.commons.model.property.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage#getCompositeProperty_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getProperties();

} // CompositeProperty
