/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
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
 * A representation of the model object '<em><b>Connection Factory Resource Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         Configuration of a Connector Connection Factory resource.
 *         
 *         @since Java EE 7
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getInterfaceName <em>Interface Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getResourceAdapter <em>Resource Adapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getMaxPoolSize <em>Max Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getMinPoolSize <em>Min Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getTransactionSupport <em>Transaction Support</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType()
 * @extends JavaEEObject
 * @generated
 */
public interface ConnectionFactoryResourceType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Description of this resource.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Description)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_Description()
	 * @generated
	 */
	Description getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getDescription <em>Description</em>}' containment reference.
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
	 *             resource being defined.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_Name()
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getName <em>Name</em>}' attribute.
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
	 *             The fully qualified class name of the connection factory 
	 *             interface.  
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interface Name</em>' attribute.
	 * @see #setInterfaceName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_InterfaceName()
	 * @generated
	 */
	String getInterfaceName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getInterfaceName <em>Interface Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Name</em>' attribute.
	 * @see #getInterfaceName()
	 * @generated
	 */
	void setInterfaceName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Adapter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Resource adapter name.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Adapter</em>' attribute.
	 * @see #setResourceAdapter(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_ResourceAdapter()
	 * @generated
	 */
	String getResourceAdapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getResourceAdapter <em>Resource Adapter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Adapter</em>' attribute.
	 * @see #getResourceAdapter()
	 * @generated
	 */
	void setResourceAdapter(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_MaxPoolSize()
	 * @generated
	 */
	BigInteger getMaxPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getMaxPoolSize <em>Max Pool Size</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_MinPoolSize()
	 * @generated
	 */
	BigInteger getMinPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getMinPoolSize <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Pool Size</em>' attribute.
	 * @see #getMinPoolSize()
	 * @generated
	 */
	void setMinPoolSize(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Transaction Support</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.TransactionSupport}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The level of transaction support the connection factory 
	 *             needs to support. 
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transaction Support</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.TransactionSupport
	 * @see #isSetTransactionSupport()
	 * @see #unsetTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupport)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_TransactionSupport()
	 * @generated
	 */
	TransactionSupport getTransactionSupport();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Support</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.TransactionSupport
	 * @see #isSetTransactionSupport()
	 * @see #unsetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @generated
	 */
	void setTransactionSupport(TransactionSupport value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getTransactionSupport <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupport)
	 * @generated
	 */
	void unsetTransactionSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getTransactionSupport <em>Transaction Support</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transaction Support</em>' attribute is set.
	 * @see #unsetTransactionSupport()
	 * @see #getTransactionSupport()
	 * @see #setTransactionSupport(TransactionSupport)
	 * @generated
	 */
	boolean isSetTransactionSupport();

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Resource property.  This may be a vendor-specific
	 *             property.
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_Property()
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getConnectionFactoryResourceType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ConnectionFactoryResourceType
