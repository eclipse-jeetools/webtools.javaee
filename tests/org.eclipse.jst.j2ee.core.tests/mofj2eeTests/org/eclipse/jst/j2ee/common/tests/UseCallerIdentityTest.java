/**
 * <copyright>
 * </copyright>
 *
 * $Id: UseCallerIdentityTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.UseCallerIdentity;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Use Caller Identity</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UseCallerIdentityTest extends SecurityIdentityTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UseCallerIdentityTest.class);
	}

	/**
	 * Constructs a new Use Caller Identity test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCallerIdentityTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Use Caller Identity test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private UseCallerIdentity getFixture() {
		return (UseCallerIdentity)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createUseCallerIdentity());
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

} //UseCallerIdentityTest
