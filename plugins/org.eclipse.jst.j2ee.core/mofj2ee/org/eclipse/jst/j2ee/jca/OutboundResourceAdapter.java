/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since J2EE1.4
 * The outbound-resourceadapterType specifies information about
 * an outbound resource adapter. The information includes fully
 * qualified names of classes/interfaces required as part of
 * the connector architecture specified contracts for
 * connection management, level of transaction support
 * provided, one or more authentication mechanisms supported
 * and additional required security permissions.
 * 
 * If there is no authentication-mechanism specified as part of
 * resource adapter element then the resource adapter does not
 * support any standard security authentication mechanisms as
 * part of security contract. The application server ignores
 * the security part of the system contracts in this case.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getConnectionDefinitions <em>Connection Definitions</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getAuthenticationMechanisms <em>Authentication Mechanisms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getOutboundResourceAdapter()
 * @model 
 * @generated
 */
public interface OutboundResourceAdapter extends EObject{
	/**
	 * Returns the value of the '<em><b>Reauthentication Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reauthentication Support</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element reauthentication-support specifies
	 *         whether the resource adapter implementation supports
	 *         re-authentication of existing Managed- Connection
	 *         instance. Note that this information is for the
	 *         resource adapter implementation and not for the
	 *         underlying EIS instance. This element must have
	 *         either a "true" or "false" value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reauthentication Support</em>' attribute.
	 * @see #isSetReauthenticationSupport()
	 * @see #unsetReauthenticationSupport()
	 * @see #setReauthenticationSupport(boolean)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getOutboundResourceAdapter_ReauthenticationSupport()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isReauthenticationSupport();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reauthentication Support</em>' attribute.
	 * @see #isSetReauthenticationSupport()
	 * @see #unsetReauthenticationSupport()
	 * @see #isReauthenticationSupport()
	 * @generated
	 */
	void setReauthenticationSupport(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReauthenticationSupport()
	 * @see #isReauthenticationSupport()
	 * @see #setReauthenticationSupport(boolean)
	 * @generated
	 */
	void unsetReauthenticationSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Reauthentication Support</em>' attribute is set.
	 * @see #unsetReauthenticationSupport()
	 * @see #isReauthenticationSupport()
	 * @see #setReauthenticationSupport(boolean)
	 * @generated
	 */
	boolean isSetReauthenticationSupport();

	/**
	 * Returns the value of the '<em><b>Transaction Support</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.j2ee.internal.jca.TransactionSupportKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction Support</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction Support</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.jca.TransactionSupportKind
	 * @see #isSetTransactionSupport()
	 * @see #unsetTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportKind)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getOutboundResourceAdapter_TransactionSupport()
	 * @model unsettable="true"
	 * @generated
	 */
	TransactionSupportKind getTransactionSupport();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Support</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.jca.TransactionSupportKind
	 * @see #isSetTransactionSupport()
	 * @see #unsetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @generated
	 */
	void setTransactionSupport(TransactionSupportKind value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportKind)
	 * @generated
	 */
	void unsetTransactionSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transaction Support</em>' attribute is set.
	 * @see #unsetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportKind)
	 * @generated
	 */
	boolean isSetTransactionSupport();

	/**
	 * Returns the value of the '<em><b>Connection Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Definitions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getOutboundResourceAdapter_ConnectionDefinitions()
	 * @model type="org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition" containment="true"
	 * @generated
	 */
	EList getConnectionDefinitions();

	/**
	 * Returns the value of the '<em><b>Authentication Mechanisms</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.jca.AuthenticationMechanism}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authentication Mechanisms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authentication Mechanisms</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getOutboundResourceAdapter_AuthenticationMechanisms()
	 * @model type="org.eclipse.jst.j2ee.internal.jca.AuthenticationMechanism" containment="true"
	 * @generated
	 */
	EList getAuthenticationMechanisms();

} // OutboundResourceAdapter
