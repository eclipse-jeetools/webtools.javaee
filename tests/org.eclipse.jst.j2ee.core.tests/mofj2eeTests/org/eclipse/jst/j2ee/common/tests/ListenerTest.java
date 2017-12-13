/**
 * <copyright>
 * </copyright>
 *
 * $Id: ListenerTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Listener;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Listener</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ListenerTest extends CompatibilityDescriptionGroupTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ListenerTest.class);
	}

	/**
	 * Constructs a new Listener test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListenerTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Listener test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Listener getFixture() {
		return (Listener)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createListener());
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

} //ListenerTest
