/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

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

import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.ConnectionDefinition;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getManagedconnectionfactoryClass <em>Managedconnectionfactory Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getConnectionfactoryInterface <em>Connectionfactory Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getConnectionfactoryImplClass <em>Connectionfactory Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getConnectionInterface <em>Connection Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getConnectionImplClass <em>Connection Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionDefinitionImpl extends EObjectImpl implements ConnectionDefinition {
	/**
	 * The default value of the '{@link #getManagedconnectionfactoryClass() <em>Managedconnectionfactory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedconnectionfactoryClass()
	 * @generated
	 * @ordered
	 */
	protected static final String MANAGEDCONNECTIONFACTORY_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getManagedconnectionfactoryClass() <em>Managedconnectionfactory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedconnectionfactoryClass()
	 * @generated
	 * @ordered
	 */
	protected String managedconnectionfactoryClass = MANAGEDCONNECTIONFACTORY_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfigProperty() <em>Config Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfigProperty> configProperty;

	/**
	 * The default value of the '{@link #getConnectionfactoryInterface() <em>Connectionfactory Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionfactoryInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTIONFACTORY_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionfactoryInterface() <em>Connectionfactory Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionfactoryInterface()
	 * @generated
	 * @ordered
	 */
	protected String connectionfactoryInterface = CONNECTIONFACTORY_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionfactoryImplClass() <em>Connectionfactory Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionfactoryImplClass()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTIONFACTORY_IMPL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionfactoryImplClass() <em>Connectionfactory Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionfactoryImplClass()
	 * @generated
	 * @ordered
	 */
	protected String connectionfactoryImplClass = CONNECTIONFACTORY_IMPL_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionInterface() <em>Connection Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionInterface() <em>Connection Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionInterface()
	 * @generated
	 * @ordered
	 */
	protected String connectionInterface = CONNECTION_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionImplClass() <em>Connection Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionImplClass()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_IMPL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionImplClass() <em>Connection Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionImplClass()
	 * @generated
	 * @ordered
	 */
	protected String connectionImplClass = CONNECTION_IMPL_CLASS_EDEFAULT;

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
	protected ConnectionDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.CONNECTION_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getManagedconnectionfactoryClass() {
		return managedconnectionfactoryClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setManagedconnectionfactoryClass(String newManagedconnectionfactoryClass) {
		String oldManagedconnectionfactoryClass = managedconnectionfactoryClass;
		managedconnectionfactoryClass = newManagedconnectionfactoryClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS, oldManagedconnectionfactoryClass, managedconnectionfactoryClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ConfigProperty> getConfigProperty() {
		if (configProperty == null) {
			configProperty = new EObjectContainmentEList<ConfigProperty>(ConfigProperty.class, this, JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTY);
		}
		return configProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnectionfactoryInterface() {
		return connectionfactoryInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionfactoryInterface(String newConnectionfactoryInterface) {
		String oldConnectionfactoryInterface = connectionfactoryInterface;
		connectionfactoryInterface = newConnectionfactoryInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE, oldConnectionfactoryInterface, connectionfactoryInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnectionfactoryImplClass() {
		return connectionfactoryImplClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionfactoryImplClass(String newConnectionfactoryImplClass) {
		String oldConnectionfactoryImplClass = connectionfactoryImplClass;
		connectionfactoryImplClass = newConnectionfactoryImplClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS, oldConnectionfactoryImplClass, connectionfactoryImplClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnectionInterface() {
		return connectionInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionInterface(String newConnectionInterface) {
		String oldConnectionInterface = connectionInterface;
		connectionInterface = newConnectionInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE, oldConnectionInterface, connectionInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnectionImplClass() {
		return connectionImplClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionImplClass(String newConnectionImplClass) {
		String oldConnectionImplClass = connectionImplClass;
		connectionImplClass = newConnectionImplClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS, oldConnectionImplClass, connectionImplClass));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTY:
				return ((InternalEList<?>)getConfigProperty()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS:
				return getManagedconnectionfactoryClass();
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTY:
				return getConfigProperty();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE:
				return getConnectionfactoryInterface();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS:
				return getConnectionfactoryImplClass();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				return getConnectionInterface();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				return getConnectionImplClass();
			case JcaPackage.CONNECTION_DEFINITION__ID:
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
			case JcaPackage.CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS:
				setManagedconnectionfactoryClass((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTY:
				getConfigProperty().clear();
				getConfigProperty().addAll((Collection<? extends ConfigProperty>)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE:
				setConnectionfactoryInterface((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS:
				setConnectionfactoryImplClass((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				setConnectionInterface((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				setConnectionImplClass((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__ID:
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
			case JcaPackage.CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS:
				setManagedconnectionfactoryClass(MANAGEDCONNECTIONFACTORY_CLASS_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTY:
				getConfigProperty().clear();
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE:
				setConnectionfactoryInterface(CONNECTIONFACTORY_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS:
				setConnectionfactoryImplClass(CONNECTIONFACTORY_IMPL_CLASS_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				setConnectionInterface(CONNECTION_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				setConnectionImplClass(CONNECTION_IMPL_CLASS_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__ID:
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
			case JcaPackage.CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS:
				return MANAGEDCONNECTIONFACTORY_CLASS_EDEFAULT == null ? managedconnectionfactoryClass != null : !MANAGEDCONNECTIONFACTORY_CLASS_EDEFAULT.equals(managedconnectionfactoryClass);
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTY:
				return configProperty != null && !configProperty.isEmpty();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE:
				return CONNECTIONFACTORY_INTERFACE_EDEFAULT == null ? connectionfactoryInterface != null : !CONNECTIONFACTORY_INTERFACE_EDEFAULT.equals(connectionfactoryInterface);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS:
				return CONNECTIONFACTORY_IMPL_CLASS_EDEFAULT == null ? connectionfactoryImplClass != null : !CONNECTIONFACTORY_IMPL_CLASS_EDEFAULT.equals(connectionfactoryImplClass);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				return CONNECTION_INTERFACE_EDEFAULT == null ? connectionInterface != null : !CONNECTION_INTERFACE_EDEFAULT.equals(connectionInterface);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				return CONNECTION_IMPL_CLASS_EDEFAULT == null ? connectionImplClass != null : !CONNECTION_IMPL_CLASS_EDEFAULT.equals(connectionImplClass);
			case JcaPackage.CONNECTION_DEFINITION__ID:
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
		result.append(" (managedconnectionfactoryClass: "); //$NON-NLS-1$
		result.append(managedconnectionfactoryClass);
		result.append(", connectionfactoryInterface: "); //$NON-NLS-1$
		result.append(connectionfactoryInterface);
		result.append(", connectionfactoryImplClass: "); //$NON-NLS-1$
		result.append(connectionfactoryImplClass);
		result.append(", connectionInterface: "); //$NON-NLS-1$
		result.append(connectionInterface);
		result.append(", connectionImplClass: "); //$NON-NLS-1$
		result.append(connectionImplClass);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ConnectionDefinitionImpl
