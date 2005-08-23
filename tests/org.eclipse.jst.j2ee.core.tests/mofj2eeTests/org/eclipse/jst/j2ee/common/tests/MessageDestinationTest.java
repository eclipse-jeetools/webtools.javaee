/**
 * <copyright>
 * </copyright>
 *
 * $Id: MessageDestinationTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.MessageDestination;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Message Destination</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MessageDestinationTest extends CompatibilityDescriptionGroupTest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MessageDestinationTest.class);
	}

	/**
	 * Constructs a new Message Destination test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestinationTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Message Destination test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MessageDestination getFixture() {
		return (MessageDestination)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createMessageDestination());
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

} //MessageDestinationTest
