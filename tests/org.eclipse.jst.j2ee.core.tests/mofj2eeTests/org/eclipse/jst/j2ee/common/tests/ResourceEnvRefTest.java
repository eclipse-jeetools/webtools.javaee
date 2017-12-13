/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResourceEnvRefTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Resource Env Ref</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceEnvRefTest extends TestCase {
	/**
	 * The fixture for this Resource Env Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceEnvRef fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ResourceEnvRefTest.class);
	}

	/**
	 * Constructs a new Resource Env Ref test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceEnvRefTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Resource Env Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ResourceEnvRef fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Resource Env Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResourceEnvRef getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createResourceEnvRef());
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

} //ResourceEnvRefTest
