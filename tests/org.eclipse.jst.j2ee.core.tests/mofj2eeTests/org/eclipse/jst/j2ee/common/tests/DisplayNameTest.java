/**
 * <copyright>
 * </copyright>
 *
 * $Id: DisplayNameTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.DisplayName;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Display Name</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class DisplayNameTest extends TestCase {
	/**
	 * The fixture for this Display Name test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DisplayName fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DisplayNameTest.class);
	}

	/**
	 * Constructs a new Display Name test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayNameTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Display Name test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(DisplayName fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Display Name test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DisplayName getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createDisplayName());
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

} //DisplayNameTest
