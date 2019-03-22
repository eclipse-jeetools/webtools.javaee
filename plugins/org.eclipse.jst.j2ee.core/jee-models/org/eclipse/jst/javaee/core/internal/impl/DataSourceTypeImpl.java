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
package org.eclipse.jst.javaee.core.internal.impl;

import java.math.BigInteger;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.IsolationLevelType;
import org.eclipse.jst.javaee.core.PropertyType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Source Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getServerName <em>Server Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getPortNumber <em>Port Number</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getDatabaseName <em>Database Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getLoginTimeout <em>Login Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#isTransactional <em>Transactional</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getIsolationLevel <em>Isolation Level</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getInitialPoolSize <em>Initial Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getMaxPoolSize <em>Max Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getMinPoolSize <em>Min Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getMaxIdleTime <em>Max Idle Time</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getMaxStatements <em>Max Statements</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.DataSourceTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSourceTypeImpl extends EObjectImpl implements DataSourceType {
	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected Description description;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected String serverName = SERVER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPortNumber() <em>Port Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNumber()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger PORT_NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortNumber() <em>Port Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNumber()
	 * @generated
	 * @ordered
	 */
	protected BigInteger portNumber = PORT_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getDatabaseName() <em>Database Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabaseName()
	 * @generated
	 * @ordered
	 */
	protected static final String DATABASE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDatabaseName() <em>Database Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabaseName()
	 * @generated
	 * @ordered
	 */
	protected String databaseName = DATABASE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected String user = USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyType> property;

	/**
	 * The default value of the '{@link #getLoginTimeout() <em>Login Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoginTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger LOGIN_TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLoginTimeout() <em>Login Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoginTimeout()
	 * @generated
	 * @ordered
	 */
	protected BigInteger loginTimeout = LOGIN_TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #isTransactional() <em>Transactional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransactional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRANSACTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTransactional() <em>Transactional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransactional()
	 * @generated
	 * @ordered
	 */
	protected boolean transactional = TRANSACTIONAL_EDEFAULT;

	/**
	 * This is true if the Transactional attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionalESet;

	/**
	 * The default value of the '{@link #getIsolationLevel() <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsolationLevel()
	 * @generated
	 * @ordered
	 */
	protected static final IsolationLevelType ISOLATION_LEVEL_EDEFAULT = IsolationLevelType.TRANSACTIONREADUNCOMMITTED;

	/**
	 * The cached value of the '{@link #getIsolationLevel() <em>Isolation Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsolationLevel()
	 * @generated
	 * @ordered
	 */
	protected IsolationLevelType isolationLevel = ISOLATION_LEVEL_EDEFAULT;

	/**
	 * This is true if the Isolation Level attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isolationLevelESet;

	/**
	 * The default value of the '{@link #getInitialPoolSize() <em>Initial Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger INITIAL_POOL_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitialPoolSize() <em>Initial Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialPoolSize()
	 * @generated
	 * @ordered
	 */
	protected BigInteger initialPoolSize = INITIAL_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxPoolSize() <em>Max Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MAX_POOL_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxPoolSize() <em>Max Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxPoolSize()
	 * @generated
	 * @ordered
	 */
	protected BigInteger maxPoolSize = MAX_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinPoolSize() <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinPoolSize()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MIN_POOL_SIZE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinPoolSize() <em>Min Pool Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinPoolSize()
	 * @generated
	 * @ordered
	 */
	protected BigInteger minPoolSize = MIN_POOL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxIdleTime() <em>Max Idle Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdleTime()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MAX_IDLE_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxIdleTime() <em>Max Idle Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxIdleTime()
	 * @generated
	 * @ordered
	 */
	protected BigInteger maxIdleTime = MAX_IDLE_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxStatements() <em>Max Statements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxStatements()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MAX_STATEMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxStatements() <em>Max Statements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxStatements()
	 * @generated
	 * @ordered
	 */
	protected BigInteger maxStatements = MAX_STATEMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataSourceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.DATA_SOURCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescription(Description newDescription, NotificationChain msgs) {
		Description oldDescription = description;
		description = newDescription;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION, oldDescription, newDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(Description newDescription) {
		if (newDescription != description) {
			NotificationChain msgs = null;
			if (description != null)
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerName(String newServerName) {
		String oldServerName = serverName;
		serverName = newServerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__SERVER_NAME, oldServerName, serverName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getPortNumber() {
		return portNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortNumber(BigInteger newPortNumber) {
		BigInteger oldPortNumber = portNumber;
		portNumber = newPortNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__PORT_NUMBER, oldPortNumber, portNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabaseName(String newDatabaseName) {
		String oldDatabaseName = databaseName;
		databaseName = newDatabaseName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__DATABASE_NAME, oldDatabaseName, databaseName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(String newUser) {
		String oldUser = user;
		user = newUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PropertyType> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<PropertyType>(PropertyType.class, this, JavaeePackage.DATA_SOURCE_TYPE__PROPERTY);
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getLoginTimeout() {
		return loginTimeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoginTimeout(BigInteger newLoginTimeout) {
		BigInteger oldLoginTimeout = loginTimeout;
		loginTimeout = newLoginTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__LOGIN_TIMEOUT, oldLoginTimeout, loginTimeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTransactional() {
		return transactional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactional(boolean newTransactional) {
		boolean oldTransactional = transactional;
		transactional = newTransactional;
		boolean oldTransactionalESet = transactionalESet;
		transactionalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__TRANSACTIONAL, oldTransactional, transactional, !oldTransactionalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransactional() {
		boolean oldTransactional = transactional;
		boolean oldTransactionalESet = transactionalESet;
		transactional = TRANSACTIONAL_EDEFAULT;
		transactionalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.DATA_SOURCE_TYPE__TRANSACTIONAL, oldTransactional, TRANSACTIONAL_EDEFAULT, oldTransactionalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransactional() {
		return transactionalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsolationLevelType getIsolationLevel() {
		return isolationLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsolationLevel(IsolationLevelType newIsolationLevel) {
		IsolationLevelType oldIsolationLevel = isolationLevel;
		isolationLevel = newIsolationLevel == null ? ISOLATION_LEVEL_EDEFAULT : newIsolationLevel;
		boolean oldIsolationLevelESet = isolationLevelESet;
		isolationLevelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__ISOLATION_LEVEL, oldIsolationLevel, isolationLevel, !oldIsolationLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsolationLevel() {
		IsolationLevelType oldIsolationLevel = isolationLevel;
		boolean oldIsolationLevelESet = isolationLevelESet;
		isolationLevel = ISOLATION_LEVEL_EDEFAULT;
		isolationLevelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.DATA_SOURCE_TYPE__ISOLATION_LEVEL, oldIsolationLevel, ISOLATION_LEVEL_EDEFAULT, oldIsolationLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsolationLevel() {
		return isolationLevelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getInitialPoolSize() {
		return initialPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialPoolSize(BigInteger newInitialPoolSize) {
		BigInteger oldInitialPoolSize = initialPoolSize;
		initialPoolSize = newInitialPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__INITIAL_POOL_SIZE, oldInitialPoolSize, initialPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxPoolSize(BigInteger newMaxPoolSize) {
		BigInteger oldMaxPoolSize = maxPoolSize;
		maxPoolSize = newMaxPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__MAX_POOL_SIZE, oldMaxPoolSize, maxPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMinPoolSize() {
		return minPoolSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinPoolSize(BigInteger newMinPoolSize) {
		BigInteger oldMinPoolSize = minPoolSize;
		minPoolSize = newMinPoolSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__MIN_POOL_SIZE, oldMinPoolSize, minPoolSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMaxIdleTime() {
		return maxIdleTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxIdleTime(BigInteger newMaxIdleTime) {
		BigInteger oldMaxIdleTime = maxIdleTime;
		maxIdleTime = newMaxIdleTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__MAX_IDLE_TIME, oldMaxIdleTime, maxIdleTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMaxStatements() {
		return maxStatements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxStatements(BigInteger newMaxStatements) {
		BigInteger oldMaxStatements = maxStatements;
		maxStatements = newMaxStatements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__MAX_STATEMENTS, oldMaxStatements, maxStatements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.DATA_SOURCE_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case JavaeePackage.DATA_SOURCE_TYPE__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION:
				return getDescription();
			case JavaeePackage.DATA_SOURCE_TYPE__NAME:
				return getName();
			case JavaeePackage.DATA_SOURCE_TYPE__CLASS_NAME:
				return getClassName();
			case JavaeePackage.DATA_SOURCE_TYPE__SERVER_NAME:
				return getServerName();
			case JavaeePackage.DATA_SOURCE_TYPE__PORT_NUMBER:
				return getPortNumber();
			case JavaeePackage.DATA_SOURCE_TYPE__DATABASE_NAME:
				return getDatabaseName();
			case JavaeePackage.DATA_SOURCE_TYPE__URL:
				return getUrl();
			case JavaeePackage.DATA_SOURCE_TYPE__USER:
				return getUser();
			case JavaeePackage.DATA_SOURCE_TYPE__PASSWORD:
				return getPassword();
			case JavaeePackage.DATA_SOURCE_TYPE__PROPERTY:
				return getProperty();
			case JavaeePackage.DATA_SOURCE_TYPE__LOGIN_TIMEOUT:
				return getLoginTimeout();
			case JavaeePackage.DATA_SOURCE_TYPE__TRANSACTIONAL:
				return isTransactional();
			case JavaeePackage.DATA_SOURCE_TYPE__ISOLATION_LEVEL:
				return getIsolationLevel();
			case JavaeePackage.DATA_SOURCE_TYPE__INITIAL_POOL_SIZE:
				return getInitialPoolSize();
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_POOL_SIZE:
				return getMaxPoolSize();
			case JavaeePackage.DATA_SOURCE_TYPE__MIN_POOL_SIZE:
				return getMinPoolSize();
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_IDLE_TIME:
				return getMaxIdleTime();
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_STATEMENTS:
				return getMaxStatements();
			case JavaeePackage.DATA_SOURCE_TYPE__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION:
				setDescription((Description)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__NAME:
				setName((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__SERVER_NAME:
				setServerName((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__PORT_NUMBER:
				setPortNumber((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__DATABASE_NAME:
				setDatabaseName((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__URL:
				setUrl((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__USER:
				setUser((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__PASSWORD:
				setPassword((String)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends PropertyType>)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__LOGIN_TIMEOUT:
				setLoginTimeout((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__TRANSACTIONAL:
				setTransactional((Boolean)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__ISOLATION_LEVEL:
				setIsolationLevel((IsolationLevelType)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__INITIAL_POOL_SIZE:
				setInitialPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_POOL_SIZE:
				setMaxPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MIN_POOL_SIZE:
				setMinPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_IDLE_TIME:
				setMaxIdleTime((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_STATEMENTS:
				setMaxStatements((BigInteger)newValue);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__ID:
				setId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION:
				setDescription((Description)null);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__SERVER_NAME:
				setServerName(SERVER_NAME_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__PORT_NUMBER:
				setPortNumber(PORT_NUMBER_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__DATABASE_NAME:
				setDatabaseName(DATABASE_NAME_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__URL:
				setUrl(URL_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__USER:
				setUser(USER_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__PROPERTY:
				getProperty().clear();
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__LOGIN_TIMEOUT:
				setLoginTimeout(LOGIN_TIMEOUT_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__TRANSACTIONAL:
				unsetTransactional();
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__ISOLATION_LEVEL:
				unsetIsolationLevel();
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__INITIAL_POOL_SIZE:
				setInitialPoolSize(INITIAL_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_POOL_SIZE:
				setMaxPoolSize(MAX_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MIN_POOL_SIZE:
				setMinPoolSize(MIN_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_IDLE_TIME:
				setMaxIdleTime(MAX_IDLE_TIME_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_STATEMENTS:
				setMaxStatements(MAX_STATEMENTS_EDEFAULT);
				return;
			case JavaeePackage.DATA_SOURCE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaeePackage.DATA_SOURCE_TYPE__DESCRIPTION:
				return description != null;
			case JavaeePackage.DATA_SOURCE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaeePackage.DATA_SOURCE_TYPE__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case JavaeePackage.DATA_SOURCE_TYPE__SERVER_NAME:
				return SERVER_NAME_EDEFAULT == null ? serverName != null : !SERVER_NAME_EDEFAULT.equals(serverName);
			case JavaeePackage.DATA_SOURCE_TYPE__PORT_NUMBER:
				return PORT_NUMBER_EDEFAULT == null ? portNumber != null : !PORT_NUMBER_EDEFAULT.equals(portNumber);
			case JavaeePackage.DATA_SOURCE_TYPE__DATABASE_NAME:
				return DATABASE_NAME_EDEFAULT == null ? databaseName != null : !DATABASE_NAME_EDEFAULT.equals(databaseName);
			case JavaeePackage.DATA_SOURCE_TYPE__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case JavaeePackage.DATA_SOURCE_TYPE__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case JavaeePackage.DATA_SOURCE_TYPE__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case JavaeePackage.DATA_SOURCE_TYPE__PROPERTY:
				return property != null && !property.isEmpty();
			case JavaeePackage.DATA_SOURCE_TYPE__LOGIN_TIMEOUT:
				return LOGIN_TIMEOUT_EDEFAULT == null ? loginTimeout != null : !LOGIN_TIMEOUT_EDEFAULT.equals(loginTimeout);
			case JavaeePackage.DATA_SOURCE_TYPE__TRANSACTIONAL:
				return isSetTransactional();
			case JavaeePackage.DATA_SOURCE_TYPE__ISOLATION_LEVEL:
				return isSetIsolationLevel();
			case JavaeePackage.DATA_SOURCE_TYPE__INITIAL_POOL_SIZE:
				return INITIAL_POOL_SIZE_EDEFAULT == null ? initialPoolSize != null : !INITIAL_POOL_SIZE_EDEFAULT.equals(initialPoolSize);
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_POOL_SIZE:
				return MAX_POOL_SIZE_EDEFAULT == null ? maxPoolSize != null : !MAX_POOL_SIZE_EDEFAULT.equals(maxPoolSize);
			case JavaeePackage.DATA_SOURCE_TYPE__MIN_POOL_SIZE:
				return MIN_POOL_SIZE_EDEFAULT == null ? minPoolSize != null : !MIN_POOL_SIZE_EDEFAULT.equals(minPoolSize);
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_IDLE_TIME:
				return MAX_IDLE_TIME_EDEFAULT == null ? maxIdleTime != null : !MAX_IDLE_TIME_EDEFAULT.equals(maxIdleTime);
			case JavaeePackage.DATA_SOURCE_TYPE__MAX_STATEMENTS:
				return MAX_STATEMENTS_EDEFAULT == null ? maxStatements != null : !MAX_STATEMENTS_EDEFAULT.equals(maxStatements);
			case JavaeePackage.DATA_SOURCE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", className: "); //$NON-NLS-1$
		result.append(className);
		result.append(", serverName: "); //$NON-NLS-1$
		result.append(serverName);
		result.append(", portNumber: "); //$NON-NLS-1$
		result.append(portNumber);
		result.append(", databaseName: "); //$NON-NLS-1$
		result.append(databaseName);
		result.append(", url: "); //$NON-NLS-1$
		result.append(url);
		result.append(", user: "); //$NON-NLS-1$
		result.append(user);
		result.append(", password: "); //$NON-NLS-1$
		result.append(password);
		result.append(", loginTimeout: "); //$NON-NLS-1$
		result.append(loginTimeout);
		result.append(", transactional: "); //$NON-NLS-1$
		if (transactionalESet) result.append(transactional); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", isolationLevel: "); //$NON-NLS-1$
		if (isolationLevelESet) result.append(isolationLevel); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", initialPoolSize: "); //$NON-NLS-1$
		result.append(initialPoolSize);
		result.append(", maxPoolSize: "); //$NON-NLS-1$
		result.append(maxPoolSize);
		result.append(", minPoolSize: "); //$NON-NLS-1$
		result.append(minPoolSize);
		result.append(", maxIdleTime: "); //$NON-NLS-1$
		result.append(maxIdleTime);
		result.append(", maxStatements: "); //$NON-NLS-1$
		result.append(maxStatements);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //DataSourceTypeImpl
