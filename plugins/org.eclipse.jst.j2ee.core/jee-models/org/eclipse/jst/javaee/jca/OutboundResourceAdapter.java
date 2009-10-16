/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outbound Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The outbound-resourceadapterType specifies information about
 *         an outbound resource adapter. The information includes fully
 *         qualified names of classes/interfaces required as part of
 *         the connector architecture specified contracts for
 *         connection management, level of transaction support
 *         provided, one or more authentication mechanisms supported
 *         and additional required security permissions.
 *         
 *         If there is no authentication-mechanism specified as part of
 *         resource adapter element then the resource adapter does not
 *         support any standard security authentication mechanisms as
 *         part of security contract. The application server ignores
 *         the security part of the system contracts in this case.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getConnectionDefinition <em>Connection Definition</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getAuthenticationMechanism <em>Authentication Mechanism</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getOutboundResourceAdapter()
 * @extends JavaEEObject
 * @generated
 */
public interface OutboundResourceAdapter extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Connection Definition</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.ConnectionDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Definition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Definition</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getOutboundResourceAdapter_ConnectionDefinition()
	 * @generated
	 */
	List<ConnectionDefinition> getConnectionDefinition();

	/**
	 * Returns the value of the '<em><b>Transaction Support</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.jca.TransactionSupportType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction Support</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction Support</em>' attribute.
	 * @see org.eclipse.jst.javaee.jca.TransactionSupportType
	 * @see #isSetTransactionSupport()
	 * @see #unsetTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportType)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getOutboundResourceAdapter_TransactionSupport()
	 * @generated
	 */
	TransactionSupportType getTransactionSupport();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Support</em>' attribute.
	 * @see org.eclipse.jst.javaee.jca.TransactionSupportType
	 * @see #isSetTransactionSupport()
	 * @see #unsetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @generated
	 */
	void setTransactionSupport(TransactionSupportType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportType)
	 * @generated
	 */
	void unsetTransactionSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transaction Support</em>' attribute is set.
	 * @see #unsetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportType)
	 * @generated
	 */
	boolean isSetTransactionSupport();

	/**
	 * Returns the value of the '<em><b>Authentication Mechanism</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.AuthenticationMechanism}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authentication Mechanism</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authentication Mechanism</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getOutboundResourceAdapter_AuthenticationMechanism()
	 * @generated
	 */
	List<AuthenticationMechanism> getAuthenticationMechanism();

	/**
	 * Returns the value of the '<em><b>Reauthentication Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element reauthentication-support specifies
	 *             whether the resource adapter implementation supports
	 *             re-authentication of existing Managed- Connection
	 *             instance. Note that this information is for the
	 *             resource adapter implementation and not for the
	 *             underlying EIS instance. This element must have
	 *             either a "true" or "false" value.
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reauthentication Support</em>' attribute.
	 * @see #isSetReauthenticationSupport()
	 * @see #unsetReauthenticationSupport()
	 * @see #setReauthenticationSupport(boolean)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getOutboundResourceAdapter_ReauthenticationSupport()
	 * @generated
	 */
	boolean isReauthenticationSupport();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReauthenticationSupport()
	 * @see #isReauthenticationSupport()
	 * @see #setReauthenticationSupport(boolean)
	 * @generated
	 */
	void unsetReauthenticationSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getOutboundResourceAdapter_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // OutboundResourceAdapter
