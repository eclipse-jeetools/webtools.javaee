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
package org.eclipse.jst.j2ee.webservice.internal.wsclient.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Component Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.impl.PortComponentRefImpl#getPortComponentLink <em>Port Component Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.impl.PortComponentRefImpl#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortComponentRefImpl extends EObjectImpl implements PortComponentRef {
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
	 * The cached value of the '{@link #getServiceEndpointInterface() <em>Service Endpoint Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceEndpointInterface()
	 * @generated
	 * @ordered
	 */
	protected JavaClass serviceEndpointInterface = null;

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
		return Webservice_clientPackage.eINSTANCE.getPortComponentRef();
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
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK, oldPortComponentLink, portComponentLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getServiceEndpointInterface() {
		if (serviceEndpointInterface != null && serviceEndpointInterface.eIsProxy()) {
			JavaClass oldServiceEndpointInterface = serviceEndpointInterface;
			serviceEndpointInterface = (JavaClass)eResolveProxy((InternalEObject)serviceEndpointInterface);
			if (serviceEndpointInterface != oldServiceEndpointInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Webservice_clientPackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE, oldServiceEndpointInterface, serviceEndpointInterface));
			}
		}
		return serviceEndpointInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetServiceEndpointInterface() {
		return serviceEndpointInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceEndpointInterface(JavaClass newServiceEndpointInterface) {
		JavaClass oldServiceEndpointInterface = serviceEndpointInterface;
		serviceEndpointInterface = newServiceEndpointInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE, oldServiceEndpointInterface, serviceEndpointInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case Webservice_clientPackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				return getPortComponentLink();
			case Webservice_clientPackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				if (resolve) return getServiceEndpointInterface();
				return basicGetServiceEndpointInterface();
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
			case Webservice_clientPackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				setPortComponentLink((String)newValue);
				return;
			case Webservice_clientPackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface((JavaClass)newValue);
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
			case Webservice_clientPackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				setPortComponentLink(PORT_COMPONENT_LINK_EDEFAULT);
				return;
			case Webservice_clientPackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface((JavaClass)null);
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
			case Webservice_clientPackage.PORT_COMPONENT_REF__PORT_COMPONENT_LINK:
				return PORT_COMPONENT_LINK_EDEFAULT == null ? portComponentLink != null : !PORT_COMPONENT_LINK_EDEFAULT.equals(portComponentLink);
			case Webservice_clientPackage.PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE:
				return serviceEndpointInterface != null;
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
		result.append(" (portComponentLink: ");//$NON-NLS-1$
		result.append(portComponentLink);
		result.append(')');
		return result.toString();
	}

} //PortComponentRefImpl
