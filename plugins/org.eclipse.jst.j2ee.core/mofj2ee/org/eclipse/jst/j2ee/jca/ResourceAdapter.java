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
 * Specifies information about the resource adapter. The information includes fully-qualified names of class//interfaces required as part of the connector architecture specified contracts, level of transaction support provided, configurable properties for ManagedConnectionFactory instances, one or more authentication mechanisms supported and additional
 * required security permissions.
 * 
 * If there is no authentication-mechanism specified as part of
 * resource adapter element then the resource adapter does not
 * support any standard security authentication mechanisms as part
 * of security contract. The application server ignores the security
 * part of the system contracts in this case.

 */
public interface ResourceAdapter extends EObject{


	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ManagedConnectionFactoryClass attribute
	 * Specifies the fully qualified name of the Java class that implements the
	 * javax.resource.spi.Managed-ConnectionFactory interface. This Java class is
	 * provided as part of resource adapter's implementation of connector architecture 
	 * specified contracts.

	 */
	String getManagedConnectionFactoryClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ManagedConnectionFactoryClass attribute
	 */
	void setManagedConnectionFactoryClass(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ConnectionFactoryInterface attribute
	 * Specifies the fully-qualified name of the ConnectionFactory interface supported 
	 * by the resource adapter.

	 */
	String getConnectionFactoryInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ConnectionFactoryInterface attribute
	 */
	void setConnectionFactoryInterface(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ConnectionFactoryImplClass attribute
	 * Specifies the fully-qualified name of the ConnectionFactory class that
	 * implements resource adapter specific ConnectionFactory interface.
	 */
	String getConnectionFactoryImplClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ConnectionFactoryImplClass attribute
	 */
	void setConnectionFactoryImplClass(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ConnectionInterface attribute
	 */
	String getConnectionInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ConnectionInterface attribute
	 */
	void setConnectionInterface(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ConnectionImplClass attribute
	 * Specifies the fully-qualified name of the Connection interface supported by the 
	 * resource adapter.
	 */
	String getConnectionImplClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ConnectionImplClass attribute
	 */
	void setConnectionImplClass(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TransactionSupport attribute
	 * The transaction-support element specifies the level of transaction
	 * support provided by the resource adapter.
	 * The value of transaction-support must be one of the following:
	 *   NoTransaction
	 *   LocalTransaction
	 *   XATransaction
	 */
	TransactionSupportKind getTransactionSupport();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupportKind)
	 * @generated
	 */
	void unsetTransactionSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getTransactionSupport <em>Transaction Support</em>}' attribute is set.
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
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ReauthenticationSupport attribute
	 * Specifies whether the resource adapter implementation supports
	 * re-authentication of existing Managed-Connection instance. Note that this
	 * information is for the resource adapter implementation and not for the
	 * underlying EIS instance.
	 */
	boolean isReauthenticationSupport();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ReauthenticationSupport attribute
	 */
	void setReauthenticationSupport(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReauthenticationSupport()
	 * @see #isReauthenticationSupport()
	 * @see #setReauthenticationSupport(boolean)
	 * @generated
	 */
	void unsetReauthenticationSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Resource Adapter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Adapter Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element resourceadapter-class specifies the
	 *         fully qualified name of a Java class that implements
	 *         the javax.resource.spi.ResourceAdapter
	 *         interface. This Java class is provided as part of
	 *         resource adapter's implementation of connector
	 *         architecture specified contracts. The implementation
	 *         of this class is required to be a JavaBean.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Adapter Class</em>' attribute.
	 * @see #setResourceAdapterClass(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getResourceAdapter_ResourceAdapterClass()
	 * @model 
	 * @generated
	 */
	String getResourceAdapterClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getResourceAdapterClass <em>Resource Adapter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Adapter Class</em>' attribute.
	 * @see #getResourceAdapterClass()
	 * @generated
	 */
	void setResourceAdapterClass(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of SecurityPermissions references
	 */
	EList getSecurityPermissions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of AuthenticationMechanisms references
	 */
	EList getAuthenticationMechanisms();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ConfigProperties references
	 */
	EList getConfigProperties();

	/**
	 * Returns the value of the '<em><b>Outbound Resource Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outbound Resource Adapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * The connectionfactory-interface element content must be
	 *         unique in the outbound-resourceadapter. Multiple connection-definitions
	 *         can not use the same connectionfactory-type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outbound Resource Adapter</em>' containment reference.
	 * @see #setOutboundResourceAdapter(OutboundResourceAdapter)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getResourceAdapter_OutboundResourceAdapter()
	 * @model containment="true"
	 * @generated
	 */
	OutboundResourceAdapter getOutboundResourceAdapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getOutboundResourceAdapter <em>Outbound Resource Adapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outbound Resource Adapter</em>' containment reference.
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	void setOutboundResourceAdapter(OutboundResourceAdapter value);

	/**
	 * Returns the value of the '<em><b>Inbound Resource Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inbound Resource Adapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inbound Resource Adapter</em>' containment reference.
	 * @see #setInboundResourceAdapter(InboundResourceAdapter)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getResourceAdapter_InboundResourceAdapter()
	 * @model containment="true"
	 * @generated
	 */
	InboundResourceAdapter getInboundResourceAdapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getInboundResourceAdapter <em>Inbound Resource Adapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inbound Resource Adapter</em>' containment reference.
	 * @see #getInboundResourceAdapter()
	 * @generated
	 */
	void setInboundResourceAdapter(InboundResourceAdapter value);

	/**
	 * Returns the value of the '<em><b>Admin Objects</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.jca.AdminObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Admin Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Admin Objects</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getResourceAdapter_AdminObjects()
	 * @model type="org.eclipse.jst.j2ee.internal.jca.AdminObject" containment="true"
	 * @generated
	 */
	EList getAdminObjects();

}





