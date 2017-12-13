/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResourceRefTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ResourceRef;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Resource Ref</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceRefTest extends TestCase {
	/**
	 * The fixture for this Resource Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceRef fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ResourceRefTest.class);
	}

	/**
	 * Constructs a new Resource Ref test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceRefTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Resource Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ResourceRef fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Resource Ref test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResourceRef getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createResourceRef());
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

} //ResourceRefTest
