/**
 */
package fr.inria.diverse.trace.commons.model.trace.impl;

import fr.inria.diverse.trace.commons.model.trace.GenericDimension;
import fr.inria.diverse.trace.commons.model.trace.GenericTracedObject;
import fr.inria.diverse.trace.commons.model.trace.TracePackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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

} //GenericTracedObjectImpl
