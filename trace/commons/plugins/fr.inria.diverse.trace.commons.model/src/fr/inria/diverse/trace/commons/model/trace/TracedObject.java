/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.inria.diverse.trace.commons.model.trace.TracedObject#getDimensions <em>Dimensions</em>}</li>
 * </ul>
 *
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getTracedObject()
 * @model abstract="true"
 * @generated
 */
public interface TracedObject<DimensionSubType extends Dimension<?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dimensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimensions</em>' containment reference list.
	 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage#getTracedObject_Dimensions()
	 * @model containment="true"
	 * @generated
	 */
	EList<DimensionSubType> getDimensions();

} // TracedObject
