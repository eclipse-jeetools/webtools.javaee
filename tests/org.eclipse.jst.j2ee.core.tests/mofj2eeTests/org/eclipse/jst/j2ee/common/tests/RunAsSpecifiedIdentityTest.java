/**
 * <copyright>
 * </copyright>
 *
 * $Id: RunAsSpecifiedIdentityTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Run As Specified Identity</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RunAsSpecifiedIdentityTest extends SecurityIdentityTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RunAsSpecifiedIdentityTest.class);
	}

	/**
	 * Constructs a new Run As Specified Identity test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunAsSpecifiedIdentityTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Run As Specified Identity test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private RunAsSpecifiedIdentity getFixture() {
		return (RunAsSpecifiedIdentity)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createRunAsSpecifiedIdentity());
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

} //RunAsSpecifiedIdentityTest
