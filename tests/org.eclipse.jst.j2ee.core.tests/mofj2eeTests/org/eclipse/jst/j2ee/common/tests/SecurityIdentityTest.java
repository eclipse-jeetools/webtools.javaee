/**
 * <copyright>
 * </copyright>
 *
 * $Id: SecurityIdentityTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.SecurityIdentity;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Security Identity</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SecurityIdentityTest extends TestCase {
	/**
	 * The fixture for this Security Identity test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SecurityIdentity fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SecurityIdentityTest.class);
	}

	/**
	 * Constructs a new Security Identity test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityIdentityTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Security Identity test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SecurityIdentity fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Security Identity test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SecurityIdentity getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createSecurityIdentity());
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

} //SecurityIdentityTest
