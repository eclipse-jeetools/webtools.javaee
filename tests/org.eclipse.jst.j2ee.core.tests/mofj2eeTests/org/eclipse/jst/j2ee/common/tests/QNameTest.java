/**
 * <copyright>
 * </copyright>
 *
 * $Id: QNameTest.java,v 1.1 2005/08/23 03:31:00 cbridgha Exp $
 */
package org.eclipse.jst.j2ee.common.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.QName;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>QName</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class QNameTest extends TestCase {
	/**
	 * The fixture for this QName test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QName fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(QNameTest.class);
	}

	/**
	 * Constructs a new QName test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QNameTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this QName test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(QName fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this QName test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private QName getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(CommonFactory.eINSTANCE.createQName());
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

} //QNameTest
