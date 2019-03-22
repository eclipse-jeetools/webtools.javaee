/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
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

import org.eclipse.jst.javaee.core.ServiceRefHandler;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChain;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Ref Handler Chain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainImpl#getServiceNamePattern <em>Service Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainImpl#getPortNamePattern <em>Port Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainImpl#getProtocolBindings <em>Protocol Bindings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainImpl#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefHandlerChainImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceRefHandlerChainImpl extends EObjectImpl implements ServiceRefHandlerChain {
	/**
	 * The default value of the '{@link #getServiceNamePattern() <em>Service Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceNamePattern() <em>Service Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceNamePattern()
	 * @generated
	 * @ordered
	 */
	protected String serviceNamePattern = SERVICE_NAME_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getPortNamePattern() <em>Port Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNamePattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PORT_NAME_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortNamePattern() <em>Port Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNamePattern()
	 * @generated
	 * @ordered
	 */
	protected String portNamePattern = PORT_NAME_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getProtocolBindings() <em>Protocol Bindings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtocolBindings()
	 * @generated
	 * @ordered
	 */
	protected static final List<String> PROTOCOL_BINDINGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProtocolBindings() <em>Protocol Bindings</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProtocolBindings()
	 * @generated
	 * @ordered
	 */
	protected List<String> protocolBindings = PROTOCOL_BINDINGS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHandlers() <em>Handlers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlers()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceRefHandler> handlers;

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
	protected ServiceRefHandlerChainImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.SERVICE_REF_HANDLER_CHAIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceNamePattern() {
		return serviceNamePattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceNamePattern(String newServiceNamePattern) {
		String oldServiceNamePattern = serviceNamePattern;
		serviceNamePattern = newServiceNamePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER_CHAIN__SERVICE_NAME_PATTERN, oldServiceNamePattern, serviceNamePattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPortNamePattern() {
		return portNamePattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortNamePattern(String newPortNamePattern) {
		String oldPortNamePattern = portNamePattern;
		portNamePattern = newPortNamePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PORT_NAME_PATTERN, oldPortNamePattern, portNamePattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getProtocolBindings() {
		return protocolBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProtocolBindings(List<String> newProtocolBindings) {
		List<String> oldProtocolBindings = protocolBindings;
		protocolBindings = newProtocolBindings;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PROTOCOL_BINDINGS, oldProtocolBindings, protocolBindings));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ServiceRefHandler> getHandlers() {
		if (handlers == null) {
			handlers = new EObjectContainmentEList<ServiceRefHandler>(ServiceRefHandler.class, this, JavaeePackage.SERVICE_REF_HANDLER_CHAIN__HANDLERS);
		}
		return handlers;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF_HANDLER_CHAIN__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__HANDLERS:
				return ((InternalEList<?>)getHandlers()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				return getServiceNamePattern();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PORT_NAME_PATTERN:
				return getPortNamePattern();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PROTOCOL_BINDINGS:
				return getProtocolBindings();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__HANDLERS:
				return getHandlers();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				setServiceNamePattern((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PORT_NAME_PATTERN:
				setPortNamePattern((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PROTOCOL_BINDINGS:
				setProtocolBindings((List<String>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__HANDLERS:
				getHandlers().clear();
				getHandlers().addAll((Collection<? extends ServiceRefHandler>)newValue);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				setServiceNamePattern(SERVICE_NAME_PATTERN_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PORT_NAME_PATTERN:
				setPortNamePattern(PORT_NAME_PATTERN_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PROTOCOL_BINDINGS:
				setProtocolBindings(PROTOCOL_BINDINGS_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__HANDLERS:
				getHandlers().clear();
				return;
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__ID:
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
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__SERVICE_NAME_PATTERN:
				return SERVICE_NAME_PATTERN_EDEFAULT == null ? serviceNamePattern != null : !SERVICE_NAME_PATTERN_EDEFAULT.equals(serviceNamePattern);
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PORT_NAME_PATTERN:
				return PORT_NAME_PATTERN_EDEFAULT == null ? portNamePattern != null : !PORT_NAME_PATTERN_EDEFAULT.equals(portNamePattern);
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__PROTOCOL_BINDINGS:
				return PROTOCOL_BINDINGS_EDEFAULT == null ? protocolBindings != null : !PROTOCOL_BINDINGS_EDEFAULT.equals(protocolBindings);
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__HANDLERS:
				return handlers != null && !handlers.isEmpty();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN__ID:
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
		result.append(" (serviceNamePattern: "); //$NON-NLS-1$
		result.append(serviceNamePattern);
		result.append(", portNamePattern: "); //$NON-NLS-1$
		result.append(portNamePattern);
		result.append(", protocolBindings: "); //$NON-NLS-1$
		result.append(protocolBindings);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ServiceRefHandlerChainImpl