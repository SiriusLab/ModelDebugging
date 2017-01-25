/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericDimension;
import fr.inria.diverse.trace.commons.model.trace.GenericTracedObject;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GenericTracedObjectImpl<T extends EObject> extends TracedObjectImpl<GenericDimension> implements GenericTracedObject<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericTracedObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.GENERIC_TRACED_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific element type known in this context.
	 * @generated
	 */
	@Override
	public EList<GenericDimension> getDimensions() {
		if (dimensions == null) {
			dimensions = new EObjectContainmentEList<GenericDimension>(GenericDimension.class, this, TracePackage.GENERIC_TRACED_OBJECT__DIMENSIONS);
		}
		return dimensions;
	}

} //GenericTracedObjectImpl
