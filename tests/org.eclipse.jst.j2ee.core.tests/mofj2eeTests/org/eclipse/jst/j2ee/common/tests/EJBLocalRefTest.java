/**
 * <copyright>
 * </copyright>
 *
 * $Id: EJBLocalRefTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.EJBLocalRef;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>EJB Local Ref</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EJBLocalRefTest extends EjbRefTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EJBLocalRefTest.class);
	}

	/**
	 * Constructs a new EJB Local Ref test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EJBLocalRefTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this EJB Local Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EJBLocalRef getFixture() {
		return (EJBLocalRef)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createEJBLocalRef());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //EJBLocalRefTest
