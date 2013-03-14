/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mail Session Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         Configuration of a Mail Session resource.
 *         
 *         @since Java EE 7
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getStoreProtocol <em>Store Protocol</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getStoreProtocolClass <em>Store Protocol Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getTransportProtocol <em>Transport Protocol</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getTransportProtocolClass <em>Transport Protocol Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getHost <em>Host</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.MailSessionType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType()
 * @extends JavaEEObject
 * @generated
 */
public interface MailSessionType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Description of this Mail Session resource.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Description)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_Description()
	 * @generated
	 */
	Description getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getDescription <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' containment reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(Description value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The name element specifies the JNDI name of the
	 *             Mail Session resource being defined.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_Name()
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Store Protocol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Storage protocol.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Store Protocol</em>' attribute.
	 * @see #setStoreProtocol(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_StoreProtocol()
	 * @generated
	 */
	String getStoreProtocol();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getStoreProtocol <em>Store Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Store Protocol</em>' attribute.
	 * @see #getStoreProtocol()
	 * @generated
	 */
	void setStoreProtocol(String value);

	/**
	 * Returns the value of the '<em><b>Store Protocol Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Service provider store protocol implementation class
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Store Protocol Class</em>' attribute.
	 * @see #setStoreProtocolClass(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_StoreProtocolClass()
	 * @generated
	 */
	String getStoreProtocolClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getStoreProtocolClass <em>Store Protocol Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Store Protocol Class</em>' attribute.
	 * @see #getStoreProtocolClass()
	 * @generated
	 */
	void setStoreProtocolClass(String value);

	/**
	 * Returns the value of the '<em><b>Transport Protocol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Transport protocol.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transport Protocol</em>' attribute.
	 * @see #setTransportProtocol(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_TransportProtocol()
	 * @generated
	 */
	String getTransportProtocol();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getTransportProtocol <em>Transport Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transport Protocol</em>' attribute.
	 * @see #getTransportProtocol()
	 * @generated
	 */
	void setTransportProtocol(String value);

	/**
	 * Returns the value of the '<em><b>Transport Protocol Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Service provider transport protocol implementation class
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transport Protocol Class</em>' attribute.
	 * @see #setTransportProtocolClass(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_TransportProtocolClass()
	 * @generated
	 */
	String getTransportProtocolClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getTransportProtocolClass <em>Transport Protocol Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transport Protocol Class</em>' attribute.
	 * @see #getTransportProtocolClass()
	 * @generated
	 */
	void setTransportProtocolClass(String value);

	/**
	 * Returns the value of the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Mail server host name.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Host</em>' attribute.
	 * @see #setHost(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_Host()
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' attribute.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(String value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Mail server user name.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_User()
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(String value);

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Password.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_Password()
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Email address to indicate the message sender.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_From()
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Mail server property.  This may be a vendor-specific
	 *             property.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_Property()
	 * @generated
	 */
	List<PropertyType> getProperty();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getMailSessionType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.MailSessionType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // MailSessionType
