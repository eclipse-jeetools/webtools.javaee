/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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
 * A representation of the model object '<em><b>Data Source Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         Configuration of a DataSource.
 *         
 *         @since Java EE 6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getServerName <em>Server Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getPortNumber <em>Port Number</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getDatabaseName <em>Database Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getUrl <em>Url</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getLoginTimeout <em>Login Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#isTransactional <em>Transactional</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getIsolationLevel <em>Isolation Level</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getInitialPoolSize <em>Initial Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getMaxPoolSize <em>Max Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getMinPoolSize <em>Min Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getMaxIdleTime <em>Max Idle Time</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getMaxStatements <em>Max Statements</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.DataSourceType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType()
 * @extends JavaEEObject
 * @generated
 */
public interface DataSourceType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Description of this DataSource.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Description)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Description()
	 * @generated
	 */
	Description getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getDescription <em>Description</em>}' containment reference.
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
	 *             data source being defined.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Name()
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             DataSource, XADataSource or ConnectionPoolDataSource
	 *             implementation class.
	 * 
	 *             @since Java EE 6            
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_ClassName()
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Database server name.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Server Name</em>' attribute.
	 * @see #setServerName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_ServerName()
	 * @generated
	 */
	String getServerName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getServerName <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Name</em>' attribute.
	 * @see #getServerName()
	 * @generated
	 */
	void setServerName(String value);

	/**
	 * Returns the value of the '<em><b>Port Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Port number where a server is listening for requests.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Number</em>' attribute.
	 * @see #setPortNumber(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_PortNumber()
	 * @generated
	 */
	BigInteger getPortNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getPortNumber <em>Port Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Number</em>' attribute.
	 * @see #getPortNumber()
	 * @generated
	 */
	void setPortNumber(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Database Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Name of a database on a server.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Database Name</em>' attribute.
	 * @see #setDatabaseName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_DatabaseName()
	 * @generated
	 */
	String getDatabaseName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getDatabaseName <em>Database Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database Name</em>' attribute.
	 * @see #getDatabaseName()
	 * @generated
	 */
	void setDatabaseName(String value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             A JDBC URL. If the <code>url</code> property is specified
	 *             along with other standard <code>DataSource</code> properties
	 *             such as <code>serverName</code>, <code>databaseName</code>
	 *             and <code>portNumber</code>, the more specific properties will
	 *             take precedence and <code>url</code> will be ignored.
	 *             
	 * 
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Url()
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             User name to use for connection authentication.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_User()
	 * @generated
	 */
	String getUser();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getUser <em>User</em>}' attribute.
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
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Password()
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             JDBC DataSource property.  This may be a vendor-specific
	 *             property or a less commonly used DataSource property.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Property()
	 * @generated
	 */
	List<PropertyType> getProperty();

	/**
	 * Returns the value of the '<em><b>Login Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Sets the maximum time in seconds that this data source
	 *             will wait while attempting to connect to a database.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Login Timeout</em>' attribute.
	 * @see #setLoginTimeout(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_LoginTimeout()
	 * @generated
	 */
	BigInteger getLoginTimeout();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getLoginTimeout <em>Login Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Login Timeout</em>' attribute.
	 * @see #getLoginTimeout()
	 * @generated
	 */
	void setLoginTimeout(BigInteger value);

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
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transactional</em>' attribute.
	 * @see #isSetTransactional()
	 * @see #unsetTransactional()
	 * @see #setTransactional(boolean)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Transactional()
	 * @generated
	 */
	boolean isTransactional();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#isTransactional <em>Transactional</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#isTransactional <em>Transactional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactional()
	 * @see #isTransactional()
	 * @see #setTransactional(boolean)
	 * @generated
	 */
	void unsetTransactional();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#isTransactional <em>Transactional</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Isolation Level</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.IsolationLevelType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Isolation level for connections.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Isolation Level</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.IsolationLevelType
	 * @see #isSetIsolationLevel()
	 * @see #unsetIsolationLevel()
	 * @see #setIsolationLevel(IsolationLevelType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_IsolationLevel()
	 * @generated
	 */
	IsolationLevelType getIsolationLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getIsolationLevel <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isolation Level</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.IsolationLevelType
	 * @see #isSetIsolationLevel()
	 * @see #unsetIsolationLevel()
	 * @see #getIsolationLevel()
	 * @generated
	 */
	void setIsolationLevel(IsolationLevelType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getIsolationLevel <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsolationLevel()
	 * @see #getIsolationLevel()
	 * @see #setIsolationLevel(IsolationLevelType)
	 * @generated
	 */
	void unsetIsolationLevel();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getIsolationLevel <em>Isolation Level</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Isolation Level</em>' attribute is set.
	 * @see #unsetIsolationLevel()
	 * @see #getIsolationLevel()
	 * @see #setIsolationLevel(IsolationLevelType)
	 * @generated
	 */
	boolean isSetIsolationLevel();

	/**
	 * Returns the value of the '<em><b>Initial Pool Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Number of connections that should be created when a
	 *             connection pool is initialized.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initial Pool Size</em>' attribute.
	 * @see #setInitialPoolSize(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_InitialPoolSize()
	 * @generated
	 */
	BigInteger getInitialPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getInitialPoolSize <em>Initial Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Pool Size</em>' attribute.
	 * @see #getInitialPoolSize()
	 * @generated
	 */
	void setInitialPoolSize(BigInteger value);

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
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Pool Size</em>' attribute.
	 * @see #setMaxPoolSize(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_MaxPoolSize()
	 * @generated
	 */
	BigInteger getMaxPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getMaxPoolSize <em>Max Pool Size</em>}' attribute.
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
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Min Pool Size</em>' attribute.
	 * @see #setMinPoolSize(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_MinPoolSize()
	 * @generated
	 */
	BigInteger getMinPoolSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getMinPoolSize <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Pool Size</em>' attribute.
	 * @see #getMinPoolSize()
	 * @generated
	 */
	void setMinPoolSize(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Max Idle Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The number of seconds that a physical connection should
	 *             remain unused in the pool before the connection is
	 *             closed for a connection pool.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Idle Time</em>' attribute.
	 * @see #setMaxIdleTime(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_MaxIdleTime()
	 * @generated
	 */
	BigInteger getMaxIdleTime();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getMaxIdleTime <em>Max Idle Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Idle Time</em>' attribute.
	 * @see #getMaxIdleTime()
	 * @generated
	 */
	void setMaxIdleTime(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Max Statements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The total number of statements that a connection pool
	 *             should keep open.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Statements</em>' attribute.
	 * @see #setMaxStatements(BigInteger)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_MaxStatements()
	 * @generated
	 */
	BigInteger getMaxStatements();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getMaxStatements <em>Max Statements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Statements</em>' attribute.
	 * @see #getMaxStatements()
	 * @generated
	 */
	void setMaxStatements(BigInteger value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getDataSourceType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.DataSourceType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // DataSourceType
