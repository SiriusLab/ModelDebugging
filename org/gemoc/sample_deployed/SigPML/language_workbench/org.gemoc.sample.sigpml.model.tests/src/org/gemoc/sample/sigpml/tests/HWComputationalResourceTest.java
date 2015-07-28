/**
 */
package org.gemoc.sample.sigpml.tests;

import junit.textui.TestRunner;

import org.gemoc.sample.sigpml.HWComputationalResource;
import org.gemoc.sample.sigpml.SigpmlFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>HW Computational Resource</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class HWComputationalResourceTest extends HWRessourceTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(HWComputationalResourceTest.class);
	}

	/**
	 * Constructs a new HW Computational Resource test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HWComputationalResourceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this HW Computational Resource test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected HWComputationalResource getFixture() {
		return (HWComputationalResource)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SigpmlFactory.eINSTANCE.createHWComputationalResource());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //HWComputationalResourceTest
