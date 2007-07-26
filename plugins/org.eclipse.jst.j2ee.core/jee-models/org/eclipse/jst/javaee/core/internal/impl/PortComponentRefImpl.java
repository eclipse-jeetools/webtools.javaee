/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.core.PortComponentRef;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Component Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#isEnableMtom <em>Enable Mtom</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getPortComponentLink <em>Port Component Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.PortComponentRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortComponentRefImpl extends EObjectImpl implements PortComponentRef {
	/**
	 * The default value of the '{@link #getServiceEndpointInterface() <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpointInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_ENDPOINT_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceEndpointInterface() <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpointInterface()
	 * @generated
	 * @ordered
	 */
	protected String serviceEndpointInterface = SERVICE_ENDPOINT_INTERFACE_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnableMtom() <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableMtom()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_MTOM_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnableMtom() <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnableMtom()
	 * @generated
	 * @ordered
	 */
	protected boolean enableMtom = ENABLE_MTOM_EDEFAULT;

	/**
	 * This is true if the Enable Mtom attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enableMtomESet = false;

	/**
	 * The default value of the '{@link #getPortComponentLink() <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortComponentLink()
	 * @generated
	 * @ordered
	 */
	protected static final String PORT_COMPONENT_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortComponentLink() <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortComponentLink()
	 * @generated
	 * @ordered
	 */
	protected String portComponentLink = PORT_COMPONENT_LINK_EDEFAULT;

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
	protected PortComponentRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.PORT_COMPONENT_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceEndpointInterface() {
		return serviceEndpointInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceEndpointInterface(String newServiceEndpointInterface) {
		String oldServiceEndpointInterface = serviceEndpointInterface;
		serviceEndpointInterface = newServiceEndpointInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE, oldServiceEndpointInterface, serviceEndpointInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnableMtom() {
		return enableMtom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnableMtom(boolean newEnableMtom) {
		boolean oldEnableMtom = enableMtom;
		enableMtom = newEnableMtom;
		boolean oldEnableMtomESet = enableMtomESet;
		enableMtomESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM, oldEnableMtom, enableMtom, !oldEnableMtomESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnableMtom() {
		boolean oldEnableMtom = enableMtom;
		boolean oldEnableMtomESet = enableMtomESet;
		enableMtom = ENABLE_MTOM_EDEFAULT;
		enableMtomESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM, oldEnableMtom, ENABLE_MTOM_EDEFAULT, oldEnableMtomESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnableMtom() {
		return enableMtomESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPortComponentLink() {
		return portComponentLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortComponentLink(String newPortComponentLink) {
		String oldPortComponentLink = portComponentLink;
		portComponentLink = newPortComponentLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK, oldPortComponentLink, portComponentLink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.PORT_COMPONENT_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				return getServiceEndpointInterface();
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				return isEnableMtom() ? Boolean.TRUE : Boolean.FALSE;
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				return getPortComponentLink();
			case JavaeePackage.PORT_COMPONENT_REF__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface((String)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				setEnableMtom(((Boolean)newValue).booleanValue());
				return;
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				setPortComponentLink((String)newValue);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ID:
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface(SERVICE_ENDPOINT_INTERFACE_EDEFAULT);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				unsetEnableMtom();
				return;
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				setPortComponentLink(PORT_COMPONENT_LINK_EDEFAULT);
				return;
			case JavaeePackage.PORT_COMPONENT_REF__ID:
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaeePackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				return SERVICE_ENDPOINT_INTERFACE_EDEFAULT == null ? serviceEndpointInterface != null : !SERVICE_ENDPOINT_INTERFACE_EDEFAULT.equals(serviceEndpointInterface);
			case JavaeePackage.PORT_COMPONENT_REF__ENABLE_MTOM:
				return isSetEnableMtom();
			case JavaeePackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				return PORT_COMPONENT_LINK_EDEFAULT == null ? portComponentLink != null : !PORT_COMPONENT_LINK_EDEFAULT.equals(portComponentLink);
			case JavaeePackage.PORT_COMPONENT_REF__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (serviceEndpointInterface: "); //$NON-NLS-1$
		result.append(serviceEndpointInterface);
		result.append(", enableMtom: "); //$NON-NLS-1$
		if (enableMtomESet) result.append(enableMtom); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", portComponentLink: "); //$NON-NLS-1$
		result.append(portComponentLink);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //PortComponentRefImpl