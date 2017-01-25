/**
 */
package base.States.impl;

import base.States.SpecificDimension;
import base.States.SpecificTracedObject;
import base.States.StatesPackage;

import fr.inria.diverse.trace.commons.model.trace.impl.TracedObjectImpl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specific Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class SpecificTracedObjectImpl<DimensionSubType> extends TracedObjectImpl<SpecificDimension<?>> implements SpecificTracedObject<DimensionSubType> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificTracedObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StatesPackage.Literals.SPECIFIC_TRACED_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<SpecificDimension<?>> getDimensions() {
		if (dimensions == null) {
			dimensions = new EObjectContainmentEList<SpecificDimension<?>>(SpecificDimension.class, this, StatesPackage.SPECIFIC_TRACED_OBJECT__DIMENSIONS);
		}
		return dimensions;
	}

} //SpecificTracedObjectImpl
