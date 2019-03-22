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

import org.eclipse.jst.javaee.jca.AdminObject;
import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.InboundResourceAdapter;
import org.eclipse.jst.javaee.jca.OutboundResourceAdapter;
import org.eclipse.jst.javaee.jca.ResourceAdapter;
import org.eclipse.jst.javaee.jca.SecurityPermission;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getResourceadapterClass <em>Resourceadapter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getOutboundResourceadapter <em>Outbound Resourceadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getInboundResourceadapter <em>Inbound Resourceadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getAdminobject <em>Adminobject</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getSecurityPermission <em>Security Permission</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceAdapterImpl extends EObjectImpl implements ResourceAdapter {
	/**
	 * The default value of the '{@link #getResourceadapterClass() <em>Resourceadapter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceadapterClass()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCEADAPTER_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceadapterClass() <em>Resourceadapter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceadapterClass()
	 * @generated
	 * @ordered
	 */
	protected String resourceadapterClass = RESOURCEADAPTER_CLASS_EDEFAULT;

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
	 * The cached value of the '{@link #getOutboundResourceadapter() <em>Outbound Resourceadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutboundResourceadapter()
	 * @generated
	 * @ordered
	 */
	protected OutboundResourceAdapter outboundResourceadapter;

	/**
	 * The cached value of the '{@link #getInboundResourceadapter() <em>Inbound Resourceadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInboundResourceadapter()
	 * @generated
	 * @ordered
	 */
	protected InboundResourceAdapter inboundResourceadapter;

	/**
	 * The cached value of the '{@link #getAdminobject() <em>Adminobject</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminobject()
	 * @generated
	 * @ordered
	 */
	protected EList<AdminObject> adminobject;

	/**
	 * The cached value of the '{@link #getSecurityPermission() <em>Security Permission</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityPermission()
	 * @generated
	 * @ordered
	 */
	protected EList<SecurityPermission> securityPermission;

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
	protected ResourceAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JcaPackage.Literals.RESOURCE_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceadapterClass() {
		return resourceadapterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceadapterClass(String newResourceadapterClass) {
		String oldResourceadapterClass = resourceadapterClass;
		resourceadapterClass = newResourceadapterClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS, oldResourceadapterClass, resourceadapterClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ConfigProperty> getConfigProperty() {
		if (configProperty == null) {
			configProperty = new EObjectContainmentEList<ConfigProperty>(ConfigProperty.class, this, JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTY);
		}
		return configProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutboundResourceAdapter getOutboundResourceadapter() {
		return outboundResourceadapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutboundResourceadapter(OutboundResourceAdapter newOutboundResourceadapter, NotificationChain msgs) {
		OutboundResourceAdapter oldOutboundResourceadapter = outboundResourceadapter;
		outboundResourceadapter = newOutboundResourceadapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER, oldOutboundResourceadapter, newOutboundResourceadapter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutboundResourceadapter(OutboundResourceAdapter newOutboundResourceadapter) {
		if (newOutboundResourceadapter != outboundResourceadapter) {
			NotificationChain msgs = null;
			if (outboundResourceadapter != null)
				msgs = ((InternalEObject)outboundResourceadapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER, null, msgs);
			if (newOutboundResourceadapter != null)
				msgs = ((InternalEObject)newOutboundResourceadapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER, null, msgs);
			msgs = basicSetOutboundResourceadapter(newOutboundResourceadapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER, newOutboundResourceadapter, newOutboundResourceadapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InboundResourceAdapter getInboundResourceadapter() {
		return inboundResourceadapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInboundResourceadapter(InboundResourceAdapter newInboundResourceadapter, NotificationChain msgs) {
		InboundResourceAdapter oldInboundResourceadapter = inboundResourceadapter;
		inboundResourceadapter = newInboundResourceadapter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER, oldInboundResourceadapter, newInboundResourceadapter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInboundResourceadapter(InboundResourceAdapter newInboundResourceadapter) {
		if (newInboundResourceadapter != inboundResourceadapter) {
			NotificationChain msgs = null;
			if (inboundResourceadapter != null)
				msgs = ((InternalEObject)inboundResourceadapter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER, null, msgs);
			if (newInboundResourceadapter != null)
				msgs = ((InternalEObject)newInboundResourceadapter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER, null, msgs);
			msgs = basicSetInboundResourceadapter(newInboundResourceadapter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER, newInboundResourceadapter, newInboundResourceadapter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<AdminObject> getAdminobject() {
		if (adminobject == null) {
			adminobject = new EObjectContainmentEList<AdminObject>(AdminObject.class, this, JcaPackage.RESOURCE_ADAPTER__ADMINOBJECT);
		}
		return adminobject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SecurityPermission> getSecurityPermission() {
		if (securityPermission == null) {
			securityPermission = new EObjectContainmentEList<SecurityPermission>(SecurityPermission.class, this, JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSION);
		}
		return securityPermission;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.RESOURCE_ADAPTER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTY:
				return ((InternalEList<?>)getConfigProperty()).basicRemove(otherEnd, msgs);
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER:
				return basicSetOutboundResourceadapter(null, msgs);
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER:
				return basicSetInboundResourceadapter(null, msgs);
			case JcaPackage.RESOURCE_ADAPTER__ADMINOBJECT:
				return ((InternalEList<?>)getAdminobject()).basicRemove(otherEnd, msgs);
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSION:
				return ((InternalEList<?>)getSecurityPermission()).basicRemove(otherEnd, msgs);
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
			case JcaPackage.RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS:
				return getResourceadapterClass();
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTY:
				return getConfigProperty();
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER:
				return getOutboundResourceadapter();
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER:
				return getInboundResourceadapter();
			case JcaPackage.RESOURCE_ADAPTER__ADMINOBJECT:
				return getAdminobject();
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSION:
				return getSecurityPermission();
			case JcaPackage.RESOURCE_ADAPTER__ID:
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
			case JcaPackage.RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS:
				setResourceadapterClass((String)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTY:
				getConfigProperty().clear();
				getConfigProperty().addAll((Collection<? extends ConfigProperty>)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER:
				setOutboundResourceadapter((OutboundResourceAdapter)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER:
				setInboundResourceadapter((InboundResourceAdapter)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__ADMINOBJECT:
				getAdminobject().clear();
				getAdminobject().addAll((Collection<? extends AdminObject>)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSION:
				getSecurityPermission().clear();
				getSecurityPermission().addAll((Collection<? extends SecurityPermission>)newValue);
				return;
			case JcaPackage.RESOURCE_ADAPTER__ID:
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
			case JcaPackage.RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS:
				setResourceadapterClass(RESOURCEADAPTER_CLASS_EDEFAULT);
				return;
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTY:
				getConfigProperty().clear();
				return;
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER:
				setOutboundResourceadapter((OutboundResourceAdapter)null);
				return;
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER:
				setInboundResourceadapter((InboundResourceAdapter)null);
				return;
			case JcaPackage.RESOURCE_ADAPTER__ADMINOBJECT:
				getAdminobject().clear();
				return;
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSION:
				getSecurityPermission().clear();
				return;
			case JcaPackage.RESOURCE_ADAPTER__ID:
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
			case JcaPackage.RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS:
				return RESOURCEADAPTER_CLASS_EDEFAULT == null ? resourceadapterClass != null : !RESOURCEADAPTER_CLASS_EDEFAULT.equals(resourceadapterClass);
			case JcaPackage.RESOURCE_ADAPTER__CONFIG_PROPERTY:
				return configProperty != null && !configProperty.isEmpty();
			case JcaPackage.RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER:
				return outboundResourceadapter != null;
			case JcaPackage.RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER:
				return inboundResourceadapter != null;
			case JcaPackage.RESOURCE_ADAPTER__ADMINOBJECT:
				return adminobject != null && !adminobject.isEmpty();
			case JcaPackage.RESOURCE_ADAPTER__SECURITY_PERMISSION:
				return securityPermission != null && !securityPermission.isEmpty();
			case JcaPackage.RESOURCE_ADAPTER__ID:
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
		result.append(" (resourceadapterClass: "); //$NON-NLS-1$
		result.append(resourceadapterClass);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ResourceAdapterImpl
