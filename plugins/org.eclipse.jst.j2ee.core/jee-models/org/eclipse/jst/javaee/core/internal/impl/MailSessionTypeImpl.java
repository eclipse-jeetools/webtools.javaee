/*******************************************************************************
 * Copyright (c) 2013, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

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
import org.eclipse.jst.javaee.core.MailSessionType;
import org.eclipse.jst.javaee.core.PropertyType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mail Session Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getStoreProtocol <em>Store Protocol</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getStoreProtocolClass <em>Store Protocol Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getTransportProtocol <em>Transport Protocol</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getTransportProtocolClass <em>Transport Protocol Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getHost <em>Host</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getUser <em>User</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.MailSessionTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MailSessionTypeImpl extends EObjectImpl implements MailSessionType {
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
	 * The default value of the '{@link #getStoreProtocol() <em>Store Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoreProtocol()
	 * @generated
	 * @ordered
	 */
	protected static final String STORE_PROTOCOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStoreProtocol() <em>Store Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoreProtocol()
	 * @generated
	 * @ordered
	 */
	protected String storeProtocol = STORE_PROTOCOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getStoreProtocolClass() <em>Store Protocol Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoreProtocolClass()
	 * @generated
	 * @ordered
	 */
	protected static final String STORE_PROTOCOL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStoreProtocolClass() <em>Store Protocol Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoreProtocolClass()
	 * @generated
	 * @ordered
	 */
	protected String storeProtocolClass = STORE_PROTOCOL_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransportProtocol() <em>Transport Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportProtocol()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSPORT_PROTOCOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransportProtocol() <em>Transport Protocol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportProtocol()
	 * @generated
	 * @ordered
	 */
	protected String transportProtocol = TRANSPORT_PROTOCOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransportProtocolClass() <em>Transport Protocol Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportProtocolClass()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSPORT_PROTOCOL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransportProtocolClass() <em>Transport Protocol Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransportProtocolClass()
	 * @generated
	 * @ordered
	 */
	protected String transportProtocolClass = TRANSPORT_PROTOCOL_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getHost() <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected String host = HOST_EDEFAULT;

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
	 * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected String from = FROM_EDEFAULT;

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
	protected MailSessionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.MAIL_SESSION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION, oldDescription, newDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(Description newDescription) {
		if (newDescription != description) {
			NotificationChain msgs = null;
			if (description != null)
				msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION, null, msgs);
			if (newDescription != null)
				msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION, null, msgs);
			msgs = basicSetDescription(newDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION, newDescription, newDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStoreProtocol() {
		return storeProtocol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStoreProtocol(String newStoreProtocol) {
		String oldStoreProtocol = storeProtocol;
		storeProtocol = newStoreProtocol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL, oldStoreProtocol, storeProtocol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStoreProtocolClass() {
		return storeProtocolClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStoreProtocolClass(String newStoreProtocolClass) {
		String oldStoreProtocolClass = storeProtocolClass;
		storeProtocolClass = newStoreProtocolClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL_CLASS, oldStoreProtocolClass, storeProtocolClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTransportProtocol() {
		return transportProtocol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransportProtocol(String newTransportProtocol) {
		String oldTransportProtocol = transportProtocol;
		transportProtocol = newTransportProtocol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL, oldTransportProtocol, transportProtocol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTransportProtocolClass() {
		return transportProtocolClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransportProtocolClass(String newTransportProtocolClass) {
		String oldTransportProtocolClass = transportProtocolClass;
		transportProtocolClass = newTransportProtocolClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL_CLASS, oldTransportProtocolClass, transportProtocolClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHost() {
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHost(String newHost) {
		String oldHost = host;
		host = newHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__HOST, oldHost, host));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUser(String newUser) {
		String oldUser = user;
		user = newUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFrom(String newFrom) {
		String oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<PropertyType> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<PropertyType>(PropertyType.class, this, JavaeePackage.MAIL_SESSION_TYPE__PROPERTY);
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.MAIL_SESSION_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION:
				return basicSetDescription(null, msgs);
			case JavaeePackage.MAIL_SESSION_TYPE__PROPERTY:
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
			case JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION:
				return getDescription();
			case JavaeePackage.MAIL_SESSION_TYPE__NAME:
				return getName();
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL:
				return getStoreProtocol();
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL_CLASS:
				return getStoreProtocolClass();
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL:
				return getTransportProtocol();
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL_CLASS:
				return getTransportProtocolClass();
			case JavaeePackage.MAIL_SESSION_TYPE__HOST:
				return getHost();
			case JavaeePackage.MAIL_SESSION_TYPE__USER:
				return getUser();
			case JavaeePackage.MAIL_SESSION_TYPE__PASSWORD:
				return getPassword();
			case JavaeePackage.MAIL_SESSION_TYPE__FROM:
				return getFrom();
			case JavaeePackage.MAIL_SESSION_TYPE__PROPERTY:
				return getProperty();
			case JavaeePackage.MAIL_SESSION_TYPE__ID:
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
			case JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION:
				setDescription((Description)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__NAME:
				setName((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL:
				setStoreProtocol((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL_CLASS:
				setStoreProtocolClass((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL:
				setTransportProtocol((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL_CLASS:
				setTransportProtocolClass((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__HOST:
				setHost((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__USER:
				setUser((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__PASSWORD:
				setPassword((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__FROM:
				setFrom((String)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends PropertyType>)newValue);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__ID:
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
			case JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION:
				setDescription((Description)null);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL:
				setStoreProtocol(STORE_PROTOCOL_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL_CLASS:
				setStoreProtocolClass(STORE_PROTOCOL_CLASS_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL:
				setTransportProtocol(TRANSPORT_PROTOCOL_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL_CLASS:
				setTransportProtocolClass(TRANSPORT_PROTOCOL_CLASS_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__HOST:
				setHost(HOST_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__USER:
				setUser(USER_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__FROM:
				setFrom(FROM_EDEFAULT);
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__PROPERTY:
				getProperty().clear();
				return;
			case JavaeePackage.MAIL_SESSION_TYPE__ID:
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
			case JavaeePackage.MAIL_SESSION_TYPE__DESCRIPTION:
				return description != null;
			case JavaeePackage.MAIL_SESSION_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL:
				return STORE_PROTOCOL_EDEFAULT == null ? storeProtocol != null : !STORE_PROTOCOL_EDEFAULT.equals(storeProtocol);
			case JavaeePackage.MAIL_SESSION_TYPE__STORE_PROTOCOL_CLASS:
				return STORE_PROTOCOL_CLASS_EDEFAULT == null ? storeProtocolClass != null : !STORE_PROTOCOL_CLASS_EDEFAULT.equals(storeProtocolClass);
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL:
				return TRANSPORT_PROTOCOL_EDEFAULT == null ? transportProtocol != null : !TRANSPORT_PROTOCOL_EDEFAULT.equals(transportProtocol);
			case JavaeePackage.MAIL_SESSION_TYPE__TRANSPORT_PROTOCOL_CLASS:
				return TRANSPORT_PROTOCOL_CLASS_EDEFAULT == null ? transportProtocolClass != null : !TRANSPORT_PROTOCOL_CLASS_EDEFAULT.equals(transportProtocolClass);
			case JavaeePackage.MAIL_SESSION_TYPE__HOST:
				return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
			case JavaeePackage.MAIL_SESSION_TYPE__USER:
				return USER_EDEFAULT == null ? user != null : !USER_EDEFAULT.equals(user);
			case JavaeePackage.MAIL_SESSION_TYPE__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case JavaeePackage.MAIL_SESSION_TYPE__FROM:
				return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
			case JavaeePackage.MAIL_SESSION_TYPE__PROPERTY:
				return property != null && !property.isEmpty();
			case JavaeePackage.MAIL_SESSION_TYPE__ID:
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
		result.append(", storeProtocol: "); //$NON-NLS-1$
		result.append(storeProtocol);
		result.append(", storeProtocolClass: "); //$NON-NLS-1$
		result.append(storeProtocolClass);
		result.append(", transportProtocol: "); //$NON-NLS-1$
		result.append(transportProtocol);
		result.append(", transportProtocolClass: "); //$NON-NLS-1$
		result.append(transportProtocolClass);
		result.append(", host: "); //$NON-NLS-1$
		result.append(host);
		result.append(", user: "); //$NON-NLS-1$
		result.append(user);
		result.append(", password: "); //$NON-NLS-1$
		result.append(password);
		result.append(", from: "); //$NON-NLS-1$
		result.append(from);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //MailSessionTypeImpl
