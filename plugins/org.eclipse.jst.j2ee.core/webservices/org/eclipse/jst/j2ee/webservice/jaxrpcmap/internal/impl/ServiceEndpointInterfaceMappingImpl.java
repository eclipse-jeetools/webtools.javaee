/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLBinding;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLPortType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Endpoint Interface Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceEndpointInterfaceMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceEndpointInterfaceMappingImpl#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceEndpointInterfaceMappingImpl#getWsdlPortType <em>Wsdl Port Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceEndpointInterfaceMappingImpl#getWsdlBinding <em>Wsdl Binding</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ServiceEndpointInterfaceMappingImpl#getServiceEndpointMethodMappings <em>Service Endpoint Method Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceEndpointInterfaceMappingImpl extends InterfaceMappingImpl implements ServiceEndpointInterfaceMapping
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
	 * The cached value of the '{@link #getWsdlPortType() <em>Wsdl Port Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlPortType()
	 * @generated
	 * @ordered
	 */
  protected WSDLPortType wsdlPortType = null;

	/**
	 * The cached value of the '{@link #getWsdlBinding() <em>Wsdl Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWsdlBinding()
	 * @generated
	 * @ordered
	 */
  protected WSDLBinding wsdlBinding = null;

	/**
	 * The cached value of the '{@link #getServiceEndpointMethodMappings() <em>Service Endpoint Method Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getServiceEndpointMethodMappings()
	 * @generated
	 * @ordered
	 */
  protected EList serviceEndpointMethodMappings = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ServiceEndpointInterfaceMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return JaxrpcmapPackage.eINSTANCE.getServiceEndpointInterfaceMapping();
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE, oldServiceEndpointInterface, serviceEndpointInterface));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WSDLPortType getWsdlPortType() {
		return wsdlPortType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetWsdlPortType(WSDLPortType newWsdlPortType, NotificationChain msgs) {
		WSDLPortType oldWsdlPortType = wsdlPortType;
		wsdlPortType = newWsdlPortType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE, oldWsdlPortType, newWsdlPortType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setWsdlPortType(WSDLPortType newWsdlPortType) {
		if (newWsdlPortType != wsdlPortType) {
			NotificationChain msgs = null;
			if (wsdlPortType != null)
				msgs = ((InternalEObject)wsdlPortType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE, null, msgs);
			if (newWsdlPortType != null)
				msgs = ((InternalEObject)newWsdlPortType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE, null, msgs);
			msgs = basicSetWsdlPortType(newWsdlPortType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE, newWsdlPortType, newWsdlPortType));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WSDLBinding getWsdlBinding() {
		return wsdlBinding;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetWsdlBinding(WSDLBinding newWsdlBinding, NotificationChain msgs) {
		WSDLBinding oldWsdlBinding = wsdlBinding;
		wsdlBinding = newWsdlBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING, oldWsdlBinding, newWsdlBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setWsdlBinding(WSDLBinding newWsdlBinding) {
		if (newWsdlBinding != wsdlBinding) {
			NotificationChain msgs = null;
			if (wsdlBinding != null)
				msgs = ((InternalEObject)wsdlBinding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING, null, msgs);
			if (newWsdlBinding != null)
				msgs = ((InternalEObject)newWsdlBinding).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING, null, msgs);
			msgs = basicSetWsdlBinding(newWsdlBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING, newWsdlBinding, newWsdlBinding));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getServiceEndpointMethodMappings() {
		if (serviceEndpointMethodMappings == null) {
			serviceEndpointMethodMappings = new EObjectContainmentEList(ServiceEndpointMethodMapping.class, this, JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS);
		}
		return serviceEndpointMethodMappings;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE:
					return basicSetWsdlPortType(null, msgs);
				case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING:
					return basicSetWsdlBinding(null, msgs);
				case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS:
					return ((InternalEList)getServiceEndpointMethodMappings()).basicRemove(otherEnd, msgs);
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
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__ID:
				return getId();
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE:
				return getServiceEndpointInterface();
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE:
				return getWsdlPortType();
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING:
				return getWsdlBinding();
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS:
				return getServiceEndpointMethodMappings();
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
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface((String)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE:
				setWsdlPortType((WSDLPortType)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING:
				setWsdlBinding((WSDLBinding)newValue);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS:
				getServiceEndpointMethodMappings().clear();
				getServiceEndpointMethodMappings().addAll((Collection)newValue);
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
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE:
				setServiceEndpointInterface(SERVICE_ENDPOINT_INTERFACE_EDEFAULT);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE:
				setWsdlPortType((WSDLPortType)null);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING:
				setWsdlBinding((WSDLBinding)null);
				return;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS:
				getServiceEndpointMethodMappings().clear();
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
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE:
				return SERVICE_ENDPOINT_INTERFACE_EDEFAULT == null ? serviceEndpointInterface != null : !SERVICE_ENDPOINT_INTERFACE_EDEFAULT.equals(serviceEndpointInterface);
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE:
				return wsdlPortType != null;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING:
				return wsdlBinding != null;
			case JaxrpcmapPackage.SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS:
				return serviceEndpointMethodMappings != null && !serviceEndpointMethodMappings.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", serviceEndpointInterface: ");
		result.append(serviceEndpointInterface);
		result.append(')');
		return result.toString();
	}

} //ServiceEndpointInterfaceMappingImpl
