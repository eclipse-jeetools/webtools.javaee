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
package org.eclipse.jst.j2ee.internal.jca.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.JcaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl#getManagedConnectionFactoryClass <em>Managed Connection Factory Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl#getConnectionFactoryInterface <em>Connection Factory Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl#getConnectionFactoryImplClass <em>Connection Factory Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl#getConnectionInterface <em>Connection Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl#getConnectionImplClass <em>Connection Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl#getConfigProperties <em>Config Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionDefinitionImpl extends EObjectImpl implements ConnectionDefinition {
	/**
	 * The default value of the '{@link #getManagedConnectionFactoryClass() <em>Managed Connection Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedConnectionFactoryClass()
	 * @generated
	 * @ordered
	 */
	protected static final String MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getManagedConnectionFactoryClass() <em>Managed Connection Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedConnectionFactoryClass()
	 * @generated
	 * @ordered
	 */
	protected String managedConnectionFactoryClass = MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionFactoryInterface() <em>Connection Factory Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionFactoryInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_FACTORY_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionFactoryInterface() <em>Connection Factory Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionFactoryInterface()
	 * @generated
	 * @ordered
	 */
	protected String connectionFactoryInterface = CONNECTION_FACTORY_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionFactoryImplClass() <em>Connection Factory Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionFactoryImplClass()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionFactoryImplClass() <em>Connection Factory Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionFactoryImplClass()
	 * @generated
	 * @ordered
	 */
	protected String connectionFactoryImplClass = CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT;

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
	 * The cached value of the '{@link #getConfigProperties() <em>Config Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigProperties()
	 * @generated
	 * @ordered
	 */
	protected EList configProperties = null;

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
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getConnectionDefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getManagedConnectionFactoryClass() {
		return managedConnectionFactoryClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedConnectionFactoryClass(String newManagedConnectionFactoryClass) {
		String oldManagedConnectionFactoryClass = managedConnectionFactoryClass;
		managedConnectionFactoryClass = newManagedConnectionFactoryClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS, oldManagedConnectionFactoryClass, managedConnectionFactoryClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectionFactoryInterface() {
		return connectionFactoryInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionFactoryInterface(String newConnectionFactoryInterface) {
		String oldConnectionFactoryInterface = connectionFactoryInterface;
		connectionFactoryInterface = newConnectionFactoryInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE, oldConnectionFactoryInterface, connectionFactoryInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectionFactoryImplClass() {
		return connectionFactoryImplClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionFactoryImplClass(String newConnectionFactoryImplClass) {
		String oldConnectionFactoryImplClass = connectionFactoryImplClass;
		connectionFactoryImplClass = newConnectionFactoryImplClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS, oldConnectionFactoryImplClass, connectionFactoryImplClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectionInterface() {
		return connectionInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public String getConnectionImplClass() {
		return connectionImplClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public EList getConfigProperties() {
		if (configProperties == null) {
			configProperties = new EObjectContainmentEList(ConfigProperty.class, this, JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES);
		}
		return configProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES:
					return ((InternalEList)getConfigProperties()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS:
				return getManagedConnectionFactoryClass();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE:
				return getConnectionFactoryInterface();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS:
				return getConnectionFactoryImplClass();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				return getConnectionInterface();
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				return getConnectionImplClass();
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES:
				return getConfigProperties();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS:
				setManagedConnectionFactoryClass((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE:
				setConnectionFactoryInterface((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS:
				setConnectionFactoryImplClass((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				setConnectionInterface((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				setConnectionImplClass((String)newValue);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES:
				getConfigProperties().clear();
				getConfigProperties().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS:
				setManagedConnectionFactoryClass(MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE:
				setConnectionFactoryInterface(CONNECTION_FACTORY_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS:
				setConnectionFactoryImplClass(CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				setConnectionInterface(CONNECTION_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				setConnectionImplClass(CONNECTION_IMPL_CLASS_EDEFAULT);
				return;
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES:
				getConfigProperties().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS:
				return MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT == null ? managedConnectionFactoryClass != null : !MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT.equals(managedConnectionFactoryClass);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE:
				return CONNECTION_FACTORY_INTERFACE_EDEFAULT == null ? connectionFactoryInterface != null : !CONNECTION_FACTORY_INTERFACE_EDEFAULT.equals(connectionFactoryInterface);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS:
				return CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT == null ? connectionFactoryImplClass != null : !CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT.equals(connectionFactoryImplClass);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_INTERFACE:
				return CONNECTION_INTERFACE_EDEFAULT == null ? connectionInterface != null : !CONNECTION_INTERFACE_EDEFAULT.equals(connectionInterface);
			case JcaPackage.CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS:
				return CONNECTION_IMPL_CLASS_EDEFAULT == null ? connectionImplClass != null : !CONNECTION_IMPL_CLASS_EDEFAULT.equals(connectionImplClass);
			case JcaPackage.CONNECTION_DEFINITION__CONFIG_PROPERTIES:
				return configProperties != null && !configProperties.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (managedConnectionFactoryClass: ");//$NON-NLS-1$
		result.append(managedConnectionFactoryClass);
		result.append(", connectionFactoryInterface: ");//$NON-NLS-1$
		result.append(connectionFactoryInterface);
		result.append(", connectionFactoryImplClass: ");//$NON-NLS-1$
		result.append(connectionFactoryImplClass);
		result.append(", connectionInterface: ");//$NON-NLS-1$
		result.append(connectionInterface);
		result.append(", connectionImplClass: ");//$NON-NLS-1$
		result.append(connectionImplClass);
		result.append(')');
		return result.toString();
	}

} //ConnectionDefinitionImpl
