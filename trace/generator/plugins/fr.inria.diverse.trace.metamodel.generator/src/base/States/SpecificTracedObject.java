/**
 */
package base.States;

import fr.inria.diverse.trace.commons.model.trace.TracedObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see base.States.StatesPackage#getSpecificTracedObject()
 * @model abstract="true"
 * @generated
 */
public interface SpecificTracedObject<DimensionSubType> extends TracedObject<SpecificDimension<?>> {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<SpecificDimension<?>> getDimensionsInternal();
} // SpecificTracedObject
