/**
 */
package fr.inria.diverse.event.commons.model.property.util;

import fr.inria.diverse.event.commons.model.property.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.event.commons.model.property.PropertyPackage
 * @generated
 */
public class PropertySwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PropertyPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertySwitch() {
		if (modelPackage == null) {
			modelPackage = PropertyPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case PropertyPackage.STATE_PROPERTY: {
				StateProperty<?> stateProperty = (StateProperty<?>)theEObject;
				T1 result = caseStateProperty(stateProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.BINARY_PROPERTY: {
				BinaryProperty<?, ?> binaryProperty = (BinaryProperty<?, ?>)theEObject;
				T1 result = caseBinaryProperty(binaryProperty);
				if (result == null) result = caseStateProperty(binaryProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.MANY_REFERENCE_PROPERTY: {
				ManyReferenceProperty<?, ?> manyReferenceProperty = (ManyReferenceProperty<?, ?>)theEObject;
				T1 result = caseManyReferenceProperty(manyReferenceProperty);
				if (result == null) result = caseStateProperty(manyReferenceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.SINGLE_REFERENCE_PROPERTY: {
				SingleReferenceProperty<?, ?> singleReferenceProperty = (SingleReferenceProperty<?, ?>)theEObject;
				T1 result = caseSingleReferenceProperty(singleReferenceProperty);
				if (result == null) result = caseStateProperty(singleReferenceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.CONTAINER_REFERENCE_PROPERTY: {
				ContainerReferenceProperty<?, ?> containerReferenceProperty = (ContainerReferenceProperty<?, ?>)theEObject;
				T1 result = caseContainerReferenceProperty(containerReferenceProperty);
				if (result == null) result = caseStateProperty(containerReferenceProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.MANY_BOOLEAN_ATTRIBUTE_PROPERTY: {
				ManyBooleanAttributeProperty<?> manyBooleanAttributeProperty = (ManyBooleanAttributeProperty<?>)theEObject;
				T1 result = caseManyBooleanAttributeProperty(manyBooleanAttributeProperty);
				if (result == null) result = caseStateProperty(manyBooleanAttributeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.MANY_INTEGER_ATTRIBUTE_PROPERTY: {
				ManyIntegerAttributeProperty<?> manyIntegerAttributeProperty = (ManyIntegerAttributeProperty<?>)theEObject;
				T1 result = caseManyIntegerAttributeProperty(manyIntegerAttributeProperty);
				if (result == null) result = caseStateProperty(manyIntegerAttributeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.MANY_STRING_ATTRIBUTE_PROPERTY: {
				ManyStringAttributeProperty<?> manyStringAttributeProperty = (ManyStringAttributeProperty<?>)theEObject;
				T1 result = caseManyStringAttributeProperty(manyStringAttributeProperty);
				if (result == null) result = caseStateProperty(manyStringAttributeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.BOOLEAN_ATTRIBUTE_PROPERTY: {
				BooleanAttributeProperty<?> booleanAttributeProperty = (BooleanAttributeProperty<?>)theEObject;
				T1 result = caseBooleanAttributeProperty(booleanAttributeProperty);
				if (result == null) result = caseStateProperty(booleanAttributeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.INTEGER_ATTRIBUTE_PROPERTY: {
				IntegerAttributeProperty<?> integerAttributeProperty = (IntegerAttributeProperty<?>)theEObject;
				T1 result = caseIntegerAttributeProperty(integerAttributeProperty);
				if (result == null) result = caseStateProperty(integerAttributeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.STRING_ATTRIBUTE_PROPERTY: {
				StringAttributeProperty<?> stringAttributeProperty = (StringAttributeProperty<?>)theEObject;
				T1 result = caseStringAttributeProperty(stringAttributeProperty);
				if (result == null) result = caseStateProperty(stringAttributeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PropertyPackage.STEP_PROPERTY: {
				StepProperty stepProperty = (StepProperty)theEObject;
				T1 result = caseStepProperty(stepProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseStateProperty(StateProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binary Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binary Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends StateProperty<?>, T> T1 caseBinaryProperty(BinaryProperty<P, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Reference Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Reference Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends StateProperty<?>, T> T1 caseManyReferenceProperty(ManyReferenceProperty<P, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Reference Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Reference Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends StateProperty<?>, T> T1 caseSingleReferenceProperty(SingleReferenceProperty<P, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Reference Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Reference Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <P extends StateProperty<?>, T> T1 caseContainerReferenceProperty(ContainerReferenceProperty<P, T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Boolean Attribute Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Boolean Attribute Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseManyBooleanAttributeProperty(ManyBooleanAttributeProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Integer Attribute Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Integer Attribute Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseManyIntegerAttributeProperty(ManyIntegerAttributeProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many String Attribute Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many String Attribute Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseManyStringAttributeProperty(ManyStringAttributeProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Attribute Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Attribute Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseBooleanAttributeProperty(BooleanAttributeProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Attribute Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Attribute Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseIntegerAttributeProperty(IntegerAttributeProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Attribute Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Attribute Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseStringAttributeProperty(StringAttributeProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Step Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStepProperty(StepProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} //PropertySwitch
