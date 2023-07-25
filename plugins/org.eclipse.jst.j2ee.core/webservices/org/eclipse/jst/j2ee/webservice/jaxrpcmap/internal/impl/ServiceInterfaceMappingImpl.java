/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLServiceName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Interface Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceInterfaceMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceInterfaceMappingImpl#getServiceInterface <em>Service Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceInterfaceMappingImpl#getWsdlServiceName <em>Wsdl Service Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceInterfaceMappingImpl#getPortMappings <em>Port Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceInterfaceMappingImpl extends InterfaceMappingImpl implements ServiceInterfaceMapping
{
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
	 * The default value of the '{@link #getServiceInterface() <em>Service Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getServiceInterface()
	 * @generated
	 * @ordered
	 */
  protected static final String SERVICE_INTERFACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceInterface() <em>Service Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getServiceInterface()
	 * @generated
	 * @ordered
	 */
  protected String serviceInterface = SERVICE_INTERFACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWsdlServiceName() <em>Wsdl Service Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlServiceName()
	 * @generated
	 * @ordered
	 */
  protected WSDLServiceName wsdlServiceName = null;

	/**
	 * The cached value of the '{@link #getPortMappings() <em>Port Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getPortMappings()
	 * @generated
	 * @ordered
	 */
  protected EList portMappings = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ServiceInterfaceMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return JaxrpcmapPackage.Literals.SERVICE_INTERFACE_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getServiceInterface() {
		return serviceInterface;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setServiceInterface(String newServiceInterface) {
		String oldServiceInterface = serviceInterface;
		serviceInterface = newServiceInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE, oldServiceInterface, serviceInterface));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public WSDLServiceName getWsdlServiceName() {
		return wsdlServiceName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetWsdlServiceName(WSDLServiceName newWsdlServiceName, NotificationChain msgs) {
		WSDLServiceName oldWsdlServiceName = wsdlServiceName;
		wsdlServiceName = newWsdlServiceName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME, oldWsdlServiceName, newWsdlServiceName);
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
public void setWsdlServiceName(WSDLServiceName newWsdlServiceName) {
		if (newWsdlServiceName != wsdlServiceName) {
			NotificationChain msgs = null;
			if (wsdlServiceName != null)
				msgs = ((InternalEObject)wsdlServiceName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME, null, msgs);
			if (newWsdlServiceName != null)
				msgs = ((InternalEObject)newWsdlServiceName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME, null, msgs);
			msgs = basicSetWsdlServiceName(newWsdlServiceName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME, newWsdlServiceName, newWsdlServiceName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EList getPortMappings() {
		if (portMappings == null) {
			portMappings = new EObjectContainmentEList(PortMapping.class, this, JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS);
		}
		return portMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME:
				return basicSetWsdlServiceName(null, msgs);
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS:
				return ((InternalEList)getPortMappings()).basicRemove(otherEnd, msgs);
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
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__ID:
				return getId();
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE:
				return getServiceInterface();
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME:
				return getWsdlServiceName();
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS:
				return getPortMappings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE:
				setServiceInterface((String)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME:
				setWsdlServiceName((WSDLServiceName)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS:
				getPortMappings().clear();
				getPortMappings().addAll((Collection)newValue);
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
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE:
				setServiceInterface(SERVICE_INTERFACE_EDEFAULT);
				return;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME:
				setWsdlServiceName((WSDLServiceName)null);
				return;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS:
				getPortMappings().clear();
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
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE:
				return SERVICE_INTERFACE_EDEFAULT == null ? serviceInterface != null : !SERVICE_INTERFACE_EDEFAULT.equals(serviceInterface);
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME:
				return wsdlServiceName != null;
			case JaxrpcmapPackage.SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS:
				return portMappings != null && !portMappings.isEmpty();
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", serviceInterface: "); //$NON-NLS-1$
		result.append(serviceInterface);
		result.append(')');
		return result.toString();
	}

} //ServiceInterfaceMappingImpl
