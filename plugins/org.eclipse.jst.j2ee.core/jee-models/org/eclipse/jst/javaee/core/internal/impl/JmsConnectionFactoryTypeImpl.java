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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JmsConnectionFactoryType;
import org.eclipse.jst.javaee.core.PropertyType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jms Connection Factory Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getResourceAdapter <em>Resource Adapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getClientId <em>Client Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#isTransactional <em>Transactional</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getMaxPoolSize <em>Max Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getMinPoolSize <em>Min Pool Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.JmsConnectionFactoryTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JmsConnectionFactoryTypeImpl extends EObjectImpl implements JmsConnectionFactoryType {
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
	 * The default value of the '{@link #getResourceAdapter() <em>Resource Adapter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_ADAPTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceAdapter() <em>Resource Adapter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected String resourceAdapter = RESOURCE_ADAPTER_EDEFAULT;

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
	 * The default value of the '{@link #getClientId() <em>Client Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientId()
	 * @generated
	 * @ordered
	 */
	protected static final String CLIENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClientId() <em>Client Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientId()
	 * @generated
	 * @ordered
	 */
	protected String clientId = CLIENT_ID_EDEFAULT;

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
	protected JmsConnectionFactoryTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.JMS_CONNECTION_FACTORY_TYPE;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION, oldDescription, newDescription);
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
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION, newDescription, newDescription));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceAdapter() {
		return resourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceAdapter(String newResourceAdapter) {
		String oldResourceAdapter = resourceAdapter;
		resourceAdapter = newResourceAdapter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__RESOURCE_ADAPTER, oldResourceAdapter, resourceAdapter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__USER, oldUser, user));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientId(String newClientId) {
		String oldClientId = clientId;
		clientId = newClientId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLIENT_ID, oldClientId, clientId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PropertyType> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<PropertyType>(PropertyType.class, this, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PROPERTY);
		}
		return property;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__TRANSACTIONAL, oldTransactional, transactional, !oldTransactionalESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__TRANSACTIONAL, oldTransactional, TRANSACTIONAL_EDEFAULT, oldTransactionalESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MAX_POOL_SIZE, oldMaxPoolSize, maxPoolSize));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MIN_POOL_SIZE, oldMinPoolSize, minPoolSize));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PROPERTY:
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
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION:
				return getDescription();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__NAME:
				return getName();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLASS_NAME:
				return getClassName();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__RESOURCE_ADAPTER:
				return getResourceAdapter();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__USER:
				return getUser();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PASSWORD:
				return getPassword();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLIENT_ID:
				return getClientId();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PROPERTY:
				return getProperty();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__TRANSACTIONAL:
				return isTransactional();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MAX_POOL_SIZE:
				return getMaxPoolSize();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MIN_POOL_SIZE:
				return getMinPoolSize();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__ID:
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
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION:
				setDescription((Description)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__NAME:
				setName((String)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__RESOURCE_ADAPTER:
				setResourceAdapter((String)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__USER:
				setUser((String)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PASSWORD:
				setPassword((String)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLIENT_ID:
				setClientId((String)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends PropertyType>)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__TRANSACTIONAL:
				setTransactional((Boolean)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MAX_POOL_SIZE:
				setMaxPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MIN_POOL_SIZE:
				setMinPoolSize((BigInteger)newValue);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__ID:
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
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION:
				setDescription((Description)null);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__RESOURCE_ADAPTER:
				setResourceAdapter(RESOURCE_ADAPTER_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__USER:
				setUser(USER_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLIENT_ID:
				setClientId(CLIENT_ID_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PROPERTY:
				getProperty().clear();
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__TRANSACTIONAL:
				unsetTransactional();
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MAX_POOL_SIZE:
				setMaxPoolSize(MAX_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MIN_POOL_SIZE:
				setMinPoolSize(MIN_POOL_SIZE_EDEFAULT);
				return;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__ID:
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
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__DESCRIPTION:
				return description != null;
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__RESOURCE_ADAPTER:
				return RESOURCE_ADAPTER_EDEFAULT == null ? resourceAdapter != null : !RESOURCE_ADAPTER_EDEFAULT.equals(resourceAdapter);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__CLIENT_ID:
				return CLIENT_ID_EDEFAULT == null ? clientId != null : !CLIENT_ID_EDEFAULT.equals(clientId);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__PROPERTY:
				return property != null && !property.isEmpty();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__TRANSACTIONAL:
				return isSetTransactional();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MAX_POOL_SIZE:
				return MAX_POOL_SIZE_EDEFAULT == null ? maxPoolSize != null : !MAX_POOL_SIZE_EDEFAULT.equals(maxPoolSize);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__MIN_POOL_SIZE:
				return MIN_POOL_SIZE_EDEFAULT == null ? minPoolSize != null : !MIN_POOL_SIZE_EDEFAULT.equals(minPoolSize);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE__ID:
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
		result.append(", resourceAdapter: "); //$NON-NLS-1$
		result.append(resourceAdapter);
		result.append(", user: "); //$NON-NLS-1$
		result.append(user);
		result.append(", password: "); //$NON-NLS-1$
		result.append(password);
		result.append(", clientId: "); //$NON-NLS-1$
		result.append(clientId);
		result.append(", transactional: "); //$NON-NLS-1$
		if (transactionalESet) result.append(transactional); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", maxPoolSize: "); //$NON-NLS-1$
		result.append(maxPoolSize);
		result.append(", minPoolSize: "); //$NON-NLS-1$
		result.append(minPoolSize);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //JmsConnectionFactoryTypeImpl
