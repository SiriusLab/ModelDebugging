/**
 */
package fr.inria.diverse.trace.commons.model.trace;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.inria.diverse.trace.commons.model.trace.TracePackage
 * @generated
 */
public interface TraceFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TraceFactory eINSTANCE = fr.inria.diverse.trace.commons.model.trace.impl.TraceFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>MSE Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MSE Occurrence</em>'.
	 * @generated
	 */
	MSEOccurrence createMSEOccurrence();

	/**
	 * Returns a new object of class '<em>MSE Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>MSE Model</em>'.
	 * @generated
	 */
	MSEModel createMSEModel();

	/**
	 * Returns a new object of class '<em>Generic MSE</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic MSE</em>'.
	 * @generated
	 */
	GenericMSE createGenericMSE();

	/**
	 * Returns a new object of class '<em>Sequential Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequential Step</em>'.
	 * @generated
	 */
	<StepSubtype extends Step> SequentialStep<StepSubtype> createSequentialStep();

	/**
	 * Returns a new object of class '<em>Parallel Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parallel Step</em>'.
	 * @generated
	 */
	<StepSubtype extends Step> ParallelStep<StepSubtype> createParallelStep();

	/**
	 * Returns a new object of class '<em>Generic Sequential Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Sequential Step</em>'.
	 * @generated
	 */
	GenericSequentialStep createGenericSequentialStep();

	/**
	 * Returns a new object of class '<em>Generic Parallel Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Parallel Step</em>'.
	 * @generated
	 */
	GenericParallelStep createGenericParallelStep();

	/**
	 * Returns a new object of class '<em>Generic Small Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Small Step</em>'.
	 * @generated
	 */
	GenericSmallStep createGenericSmallStep();

	/**
	 * Returns a new object of class '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trace</em>'.
	 * @generated
	 */
	<StepSubType> Trace<StepSubType> createTrace();

	/**
	 * Returns a new object of class '<em>Launch Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Launch Configuration</em>'.
	 * @generated
	 */
	LaunchConfiguration createLaunchConfiguration();

	/**
	 * Returns a new object of class '<em>Language Name Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Language Name Parameter</em>'.
	 * @generated
	 */
	LanguageNameParameter createLanguageNameParameter();

	/**
	 * Returns a new object of class '<em>Model URI Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model URI Parameter</em>'.
	 * @generated
	 */
	ModelURIParameter createModelURIParameter();

	/**
	 * Returns a new object of class '<em>Animator URI Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Animator URI Parameter</em>'.
	 * @generated
	 */
	AnimatorURIParameter createAnimatorURIParameter();

	/**
	 * Returns a new object of class '<em>Entry Point Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entry Point Parameter</em>'.
	 * @generated
	 */
	EntryPointParameter createEntryPointParameter();

	/**
	 * Returns a new object of class '<em>Model Root Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Root Parameter</em>'.
	 * @generated
	 */
	ModelRootParameter createModelRootParameter();

	/**
	 * Returns a new object of class '<em>Initialization Method Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Initialization Method Parameter</em>'.
	 * @generated
	 */
	InitializationMethodParameter createInitializationMethodParameter();

	/**
	 * Returns a new object of class '<em>Initialization Arguments Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Initialization Arguments Parameter</em>'.
	 * @generated
	 */
	InitializationArgumentsParameter createInitializationArgumentsParameter();

	/**
	 * Returns a new object of class '<em>Addon Extension Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Addon Extension Parameter</em>'.
	 * @generated
	 */
	AddonExtensionParameter createAddonExtensionParameter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TracePackage getTracePackage();

} //TraceFactory
