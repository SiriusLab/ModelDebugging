/**
 */
package fr.inria.diverse.trace.generic.model.richgenerictrace.impl;

import fr.inria.diverse.trace.generic.model.richgenerictrace.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RichgenerictraceFactoryImpl extends EFactoryImpl implements RichgenerictraceFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RichgenerictraceFactory init() {
		try {
			RichgenerictraceFactory theRichgenerictraceFactory = (RichgenerictraceFactory)EPackage.Registry.INSTANCE.getEFactory(RichgenerictracePackage.eNS_URI);
			if (theRichgenerictraceFactory != null) {
				return theRichgenerictraceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RichgenerictraceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RichgenerictraceFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RichgenerictracePackage.TRACE: return createTrace();
			case RichgenerictracePackage.VALUE_SEQUENCE: return createValueSequence();
			case RichgenerictracePackage.VALUE: return createValue();
			case RichgenerictracePackage.EXECUTION_STATE: return createExecutionState();
			case RichgenerictracePackage.TRACED_OBJECT: return createTracedObject();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace createTrace() {
		TraceImpl trace = new TraceImpl();
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSequence createValueSequence() {
		ValueSequenceImpl valueSequence = new ValueSequenceImpl();
		return valueSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Value createValue() {
		ValueImpl value = new ValueImpl();
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState createExecutionState() {
		ExecutionStateImpl executionState = new ExecutionStateImpl();
		return executionState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TracedObject createTracedObject() {
		TracedObjectImpl tracedObject = new TracedObjectImpl();
		return tracedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RichgenerictracePackage getRichgenerictracePackage() {
		return (RichgenerictracePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RichgenerictracePackage getPackage() {
		return RichgenerictracePackage.eINSTANCE;
	}

} //RichgenerictraceFactoryImpl
