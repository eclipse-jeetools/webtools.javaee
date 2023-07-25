/*******************************************************************************
 * Copyright (c) 2013, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import java.math.BigInteger;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jms Connection Factory Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         Configuration of a JMS Connection Factory.
 *         
 *         @since Java EE 7
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getInterfaceName <em>Interface Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getResourceAdapter <em>Resource Adapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getClientId <em>Client Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#isTransactional <em>Transactional</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getMaxPoolSize <em>Max Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getMinPoolSize <em>Min Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType()
 * @extends JavaEEObject
 * @generated
 */
public interface JmsConnectionFactoryType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Description of this JMS Connection Factory.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Description)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_Description()
	 * @generated
	 */
	Description getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getDescription <em>Description</em>}' containment reference.
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
	 *             JMS connection factory being defined.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_Name()
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Interface Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Fully-qualified name of the JMS connection factory
	 *             interface.  Permitted values are javax.jms.ConnectionFactory,
	 *             javax.jms.QueueConnectionFactory, or 
	 *             javax.jms.TopicConnectionFactory.  If not specified,
	 *             javax.jms.ConnectionFactory will be used.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interface Name</em>' attribute.
	 * @see #setInterfaceName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_InterfaceName()
	 * @generated
	 */
	String getInterfaceName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getInterfaceName <em>Interface Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Name</em>' attribute.
	 * @see #getInterfaceName()
	 * @generated
	 */
	void setInterfaceName(String value);

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Fully-qualified name of the JMS connection factory
	 *             implementation class.  Ignored if a resource adapter  
	 *             is used.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_ClassName()
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Adapter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Resource adapter name.  If not specified, the application
	 *             server will define the default behavior, which may or may
	 *             not involve the use of a resource adapter.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Adapter</em>' attribute.
	 * @see #setResourceAdapter(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_ResourceAdapter()
	 * @generated
	 */
	String getResourceAdapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getResourceAdapter <em>Resource Adapter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Adapter</em>' attribute.
	 * @see #getResourceAdapter()
	 * @generated
	 */
	void setResourceAdapter(String value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             User name to use for connection authentication.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_User()
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getUser <em>User</em>}' attribute.
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
	 *             Password to use for connection authentication.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_Password()
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Client Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Client id to use for connection.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Client Id</em>' attribute.
	 * @see #setClientId(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_ClientId()
	 * @generated
	 */
	String getClientId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getClientId <em>Client Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Client Id</em>' attribute.
	 * @see #getClientId()
	 * @generated
	 */
	void setClientId(String value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             JMS Connection Factory property.  This may be a vendor-specific
	 *             property or a less commonly used ConnectionFactory property.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_Property()
	 * @generated
	 */
	List<PropertyType> getProperty();

	/**
	 * Returns the value of the '<em><b>Transactional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Set to false if connections should not participate in
	 *             transactions.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transactional</em>' attribute.
	 * @see #isSetTransactional()
	 * @see #unsetTransactional()
	 * @see #setTransactional(boolean)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_Transactional()
	 * @generated
	 */
	boolean isTransactional();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#isTransactional <em>Transactional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transactional</em>' attribute.
	 * @see #isSetTransactional()
	 * @see #unsetTransactional()
	 * @see #isTransactional()
	 * @generated
	 */
	void setTransactional(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#isTransactional <em>Transactional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactional()
	 * @see #isTransactional()
	 * @see #setTransactional(boolean)
	 * @generated
	 */
	void unsetTransactional();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#isTransactional <em>Transactional</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transactional</em>' attribute is set.
	 * @see #unsetTransactional()
	 * @see #isTransactional()
	 * @see #setTransactional(boolean)
	 * @generated
	 */
	boolean isSetTransactional();

	/**
	 * Returns the value of the '<em><b>Max Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Maximum number of connections that should be concurrently
	 *             allocated for a connection pool.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Pool Size</em>' attribute.
	 * @see #setMaxPoolSize(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_MaxPoolSize()
	 * @generated
	 */
	BigInteger getMaxPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getMaxPoolSize <em>Max Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Pool Size</em>' attribute.
	 * @see #getMaxPoolSize()
	 * @generated
	 */
	void setMaxPoolSize(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Min Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Minimum number of connections that should be concurrently
	 *             allocated for a connection pool.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min Pool Size</em>' attribute.
	 * @see #setMinPoolSize(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_MinPoolSize()
	 * @generated
	 */
	BigInteger getMinPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getMinPoolSize <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Pool Size</em>' attribute.
	 * @see #getMinPoolSize()
	 * @generated
	 */
	void setMinPoolSize(BigInteger value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getJmsConnectionFactoryType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // JmsConnectionFactoryType
