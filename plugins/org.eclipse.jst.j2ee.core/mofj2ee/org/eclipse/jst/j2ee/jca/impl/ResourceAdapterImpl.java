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
package org.eclipse.jst.j2ee.jca.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.jca.AdminObject;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.InboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;
import org.eclipse.jst.j2ee.jca.TransactionSupportKind;


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
public class ResourceAdapterImpl extends EObjectImpl implements ResourceAdapter, EObject{

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
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String connectionImplClass = CONNECTION_IMPL_CLASS_EDEFAULT;
	/**
	 * The default value of the '{@link #getTransactionSupport() <em>Transaction Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionSupport()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionSupportKind TRANSACTION_SUPPORT_EDEFAULT = TransactionSupportKind.NO_TRANSACTION_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected TransactionSupportKind transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;
	/**
	 * This is true if the Transaction Support attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionSupportESet = false;

	/**
	 * The default value of the '{@link #isReauthenticationSupport() <em>Reauthentication Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReauthenticationSupport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REAUTHENTICATION_SUPPORT_EDEFAULT = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected boolean reauthenticationSupport = REAUTHENTICATION_SUPPORT_EDEFAULT;
	/**
	 * This is true if the Reauthentication Support attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean reauthenticationSupportESet = false;

	/**
	 * The default value of the '{@link #getResourceAdapterClass() <em>Resource Adapter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAdapterClass()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_ADAPTER_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceAdapterClass() <em>Resource Adapter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAdapterClass()
	 * @generated
	 * @ordered
	 */
	protected String resourceAdapterClass = RESOURCE_ADAPTER_CLASS_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList securityPermissions = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList authenticationMechanisms = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList configProperties = null;
	/**
	 * The cached value of the '{@link #getOutboundResourceAdapter() <em>Outbound Resource Adapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected OutboundResourceAdapter outboundResourceAdapter = null;

	/**
	 * The cached value of the '{@link #getInboundResourceAdapter() <em>Inbound Resource Adapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInboundResourceAdapter()
	 * @generated
	 * @ordered
	 */
	protected InboundResourceAdapter inboundResourceAdapter = null;

	/**
	 * The cached value of the '{@link #getAdminObjects() <em>Admin Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminObjects()
	 * @generated
	 * @ordered
	 */
	protected EList adminObjects = null;

	protected ResourceAdapterImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getResourceAdapter();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the fully qualified name of the Java class that implements the javax.resource.spi.Managed-ConnectionFactory interface. This Java class is provided as part of resource adapter's implementation of connector architecture specified contracts.

	 */
	public String getManagedConnectionFactoryClass() {
		return managedConnectionFactoryClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setManagedConnectionFactoryClass(String newManagedConnectionFactoryClass) {
		String oldManagedConnectionFactoryClass = managedConnectionFactoryClass;
		managedConnectionFactoryClass = newManagedConnectionFactoryClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS, oldManagedConnectionFactoryClass, managedConnectionFactoryClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the fully-qualified name of the ConnectionFactory interface supported by the resource adapter.

	 */
	public String getConnectionFactoryInterface() {
		return connectionFactoryInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setConnectionFactoryInterface(String newConnectionFactoryInterface) {
		String oldConnectionFactoryInterface = connectionFactoryInterface;
		connectionFactoryInterface = newConnectionFactoryInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE, oldConnectionFactoryInterface, connectionFactoryInterface));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the fully-qualified name of the ConnectionFactory class that implements resource adapter specific ConnectionFactory interface.
	 */
	public String getConnectionFactoryImplClass() {
		return connectionFactoryImplClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setConnectionFactoryImplClass(String newConnectionFactoryImplClass) {
		String oldConnectionFactoryImplClass = connectionFactoryImplClass;
		connectionFactoryImplClass = newConnectionFactoryImplClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS, oldConnectionFactoryImplClass, connectionFactoryImplClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getConnectionInterface() {
		return connectionInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setConnectionInterface(String newConnectionInterface) {
		String oldConnectionInterface = connectionInterface;
		connectionInterface = newConnectionInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__CONNECTION_INTERFACE, oldConnectionInterface, connectionInterface));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the fully-qualified name of the Connection interface supported by the resource adapter.
	 */
	public String getConnectionImplClass() {
		return connectionImplClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setConnectionImplClass(String newConnectionImplClass) {
		String oldConnectionImplClass = connectionImplClass;
		connectionImplClass = newConnectionImplClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS, oldConnectionImplClass, connectionImplClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The transaction-support element specifies the level of transaction
	 * support provided by the resource adapter.
	 * The value of transaction-support must be one of the following:
	 *   NoTransaction
	 *   LocalTransaction
	 *   XATransaction
	 */
	public TransactionSupportKind getTransactionSupport() {
		return transactionSupport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionSupport(TransactionSupportKind newTransactionSupport) {
		TransactionSupportKind oldTransactionSupport = transactionSupport;
		transactionSupport = newTransactionSupport == null ? TRANSACTION_SUPPORT_EDEFAULT : newTransactionSupport;
		boolean oldTransactionSupportESet = transactionSupportESet;
		transactionSupportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT, oldTransactionSupport, transactionSupport, !oldTransactionSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransactionSupport() {
		TransactionSupportKind oldTransactionSupport = transactionSupport;
		boolean oldTransactionSupportESet = transactionSupportESet;
		transactionSupport = TRANSACTION_SUPPORT_EDEFAULT;
		transactionSupportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT, oldTransactionSupport, TRANSACTION_SUPPORT_EDEFAULT, oldTransactionSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransactionSupport() {
		return transactionSupportESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies whether the resource adapter implementation supports re-authentication of existing Managed-Connection instance. Note that this information is for the resource adapter implementation and not for the underlying EIS instance.
	 */
	public boolean isReauthenticationSupport() {
		return reauthenticationSupport;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setReauthenticationSupport(boolean newReauthenticationSupport) {
		boolean oldReauthenticationSupport = reauthenticationSupport;
		reauthenticationSupport = newReauthenticationSupport;
		boolean oldReauthenticationSupportESet = reauthenticationSupportESet;
		reauthenticationSupportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT, oldReauthenticationSupport, reauthenticationSupport, !oldReauthenticationSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetReauthenticationSupport() {
		boolean oldReauthenticationSupport = reauthenticationSupport;
		boolean oldReauthenticationSupportESet = reauthenticationSupportESet;
		reauthenticationSupport = REAUTHENTICATION_SUPPORT_EDEFAULT;
		reauthenticationSupportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT, oldReauthenticationSupport, REAUTHENTICATION_SUPPORT_EDEFAULT, oldReauthenticationSupportESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetReauthenticationSupport() {
		return reauthenticationSupportESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceAdapterClass() {
		return resourceAdapterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceAdapterClass(String newResourceAdapterClass) {
		String oldResourceAdapterClass = resourceAdapterClass;
		resourceAdapterClass = newResourceAdapterClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS, oldResourceAdapterClass, resourceAdapterClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getSecurityPermissions() {
		if (securityPermissions == null) {
			securityPermissions = new EObjectContainmentEList(SecurityPermission.class, this, JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS);
		}
		return securityPermissions;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getAuthenticationMechanisms() {
		if (authenticationMechanisms == null) {
			authenticationMechanisms = new EObjectContainmentEList(AuthenticationMechanism.class, this, JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS);
		}
		return authenticationMechanisms;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getConfigProperties() {
		if (configProperties == null) {
			configProperties = new EObjectContainmentEList(ConfigProperty.class, this, JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES);
		}
		return configProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutboundResourceAdapter getOutboundResourceAdapter() {
		return outboundResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutboundResourceAdapter(OutboundResourceAdapter newOutboundResourceAdapter, NotificationChain msgs) {
		OutboundResourceAdapter oldOutboundResourceAdapter = outboundResourceAdapter;
		outboundResourceAdapter = newOutboundResourceAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER, oldOutboundResourceAdapter, newOutboundResourceAdapter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutboundResourceAdapter(OutboundResourceAdapter newOutboundResourceAdapter) {
		if (newOutboundResourceAdapter != outboundResourceAdapter) {
			NotificationChain msgs = null;
			if (outboundResourceAdapter != null)
				msgs = ((InternalEObject)outboundResourceAdapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER, null, msgs);
			if (newOutboundResourceAdapter != null)
				msgs = ((InternalEObject)newOutboundResourceAdapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER, null, msgs);
			msgs = basicSetOutboundResourceAdapter(newOutboundResourceAdapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER, newOutboundResourceAdapter, newOutboundResourceAdapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InboundResourceAdapter getInboundResourceAdapter() {
		return inboundResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInboundResourceAdapter(InboundResourceAdapter newInboundResourceAdapter, NotificationChain msgs) {
		InboundResourceAdapter oldInboundResourceAdapter = inboundResourceAdapter;
		inboundResourceAdapter = newInboundResourceAdapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER, oldInboundResourceAdapter, newInboundResourceAdapter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInboundResourceAdapter(InboundResourceAdapter newInboundResourceAdapter) {
		if (newInboundResourceAdapter != inboundResourceAdapter) {
			NotificationChain msgs = null;
			if (inboundResourceAdapter != null)
				msgs = ((InternalEObject)inboundResourceAdapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER, null, msgs);
			if (newInboundResourceAdapter != null)
				msgs = ((InternalEObject)newInboundResourceAdapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER, null, msgs);
			msgs = basicSetInboundResourceAdapter(newInboundResourceAdapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER, newInboundResourceAdapter, newInboundResourceAdapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAdminObjects() {
		if (adminObjects == null) {
			adminObjects = new EObjectContainmentEList(AdminObject.class, this, JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS);
		}
		return adminObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS:
					return ((InternalEList)getSecurityPermissions()).basicRemove(otherEnd, msgs);
				case JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
					return ((InternalEList)getAuthenticationMechanisms()).basicRemove(otherEnd, msgs);
				case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES:
					return ((InternalEList)getConfigProperties()).basicRemove(otherEnd, msgs);
				case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER:
					return basicSetOutboundResourceAdapter(null, msgs);
				case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER:
					return basicSetInboundResourceAdapter(null, msgs);
				case JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS:
					return ((InternalEList)getAdminObjects()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS:
				return getManagedConnectionFactoryClass();
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE:
				return getConnectionFactoryInterface();
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS:
				return getConnectionFactoryImplClass();
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_INTERFACE:
				return getConnectionInterface();
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS:
				return getConnectionImplClass();
			case JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				return getTransactionSupport();
			case JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				return isReauthenticationSupport() ? Boolean.TRUE : Boolean.FALSE;
			case JcaPackage.RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS:
				return getResourceAdapterClass();
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS:
				return getSecurityPermissions();
			case JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				return getAuthenticationMechanisms();
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES:
				return getConfigProperties();
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER:
				return getOutboundResourceAdapter();
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER:
				return getInboundResourceAdapter();
			case JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS:
				return getAdminObjects();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS:
				return MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT == null ? managedConnectionFactoryClass != null : !MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT.equals(managedConnectionFactoryClass);
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE:
				return CONNECTION_FACTORY_INTERFACE_EDEFAULT == null ? connectionFactoryInterface != null : !CONNECTION_FACTORY_INTERFACE_EDEFAULT.equals(connectionFactoryInterface);
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS:
				return CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT == null ? connectionFactoryImplClass != null : !CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT.equals(connectionFactoryImplClass);
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_INTERFACE:
				return CONNECTION_INTERFACE_EDEFAULT == null ? connectionInterface != null : !CONNECTION_INTERFACE_EDEFAULT.equals(connectionInterface);
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS:
				return CONNECTION_IMPL_CLASS_EDEFAULT == null ? connectionImplClass != null : !CONNECTION_IMPL_CLASS_EDEFAULT.equals(connectionImplClass);
			case JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				return isSetTransactionSupport();
			case JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				return isSetReauthenticationSupport();
			case JcaPackage.RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS:
				return RESOURCE_ADAPTER_CLASS_EDEFAULT == null ? resourceAdapterClass != null : !RESOURCE_ADAPTER_CLASS_EDEFAULT.equals(resourceAdapterClass);
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS:
				return securityPermissions != null && !securityPermissions.isEmpty();
			case JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				return authenticationMechanisms != null && !authenticationMechanisms.isEmpty();
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES:
				return configProperties != null && !configProperties.isEmpty();
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER:
				return outboundResourceAdapter != null;
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER:
				return inboundResourceAdapter != null;
			case JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS:
				return adminObjects != null && !adminObjects.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS:
				setManagedConnectionFactoryClass((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE:
				setConnectionFactoryInterface((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS:
				setConnectionFactoryImplClass((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_INTERFACE:
				setConnectionInterface((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS:
				setConnectionImplClass((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				setTransactionSupport((TransactionSupportKind)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				setReauthenticationSupport(((Boolean)newValue).booleanValue());
				return;
			case JcaPackage.RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS:
				setResourceAdapterClass((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS:
				getSecurityPermissions().clear();
				getSecurityPermissions().addAll((Collection)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				getAuthenticationMechanisms().clear();
				getAuthenticationMechanisms().addAll((Collection)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES:
				getConfigProperties().clear();
				getConfigProperties().addAll((Collection)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER:
				setOutboundResourceAdapter((OutboundResourceAdapter)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER:
				setInboundResourceAdapter((InboundResourceAdapter)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS:
				getAdminObjects().clear();
				getAdminObjects().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS:
				setManagedConnectionFactoryClass(MANAGED_CONNECTION_FACTORY_CLASS_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE:
				setConnectionFactoryInterface(CONNECTION_FACTORY_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS:
				setConnectionFactoryImplClass(CONNECTION_FACTORY_IMPL_CLASS_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_INTERFACE:
				setConnectionInterface(CONNECTION_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS:
				setConnectionImplClass(CONNECTION_IMPL_CLASS_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__TRANSACTION_SUPPORT:
				unsetTransactionSupport();
				return;
			case JcaPackage.RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT:
				unsetReauthenticationSupport();
				return;
			case JcaPackage.RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS:
				setResourceAdapterClass(RESOURCE_ADAPTER_CLASS_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSIONS:
				getSecurityPermissions().clear();
				return;
			case JcaPackage.RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS:
				getAuthenticationMechanisms().clear();
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTIES:
				getConfigProperties().clear();
				return;
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER:
				setOutboundResourceAdapter((OutboundResourceAdapter)null);
				return;
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER:
				setInboundResourceAdapter((InboundResourceAdapter)null);
				return;
			case JcaPackage.RESOURCE_ADAPTER__ADMIN_OBJECTS:
				getAdminObjects().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
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
		result.append(", transactionSupport: ");//$NON-NLS-1$
		if (transactionSupportESet) result.append(transactionSupport); else result.append("<unset>");//$NON-NLS-1$
		result.append(", reauthenticationSupport: ");//$NON-NLS-1$
		if (reauthenticationSupportESet) result.append(reauthenticationSupport); else result.append("<unset>");//$NON-NLS-1$
		result.append(", resourceAdapterClass: ");//$NON-NLS-1$
		result.append(resourceAdapterClass);
		result.append(')');
		return result.toString();
	}

}





