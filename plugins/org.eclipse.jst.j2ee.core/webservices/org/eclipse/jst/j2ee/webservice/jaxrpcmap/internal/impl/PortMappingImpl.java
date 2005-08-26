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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.PortMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.PortMappingImpl#getPortName <em>Port Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.PortMappingImpl#getJavaPortName <em>Java Port Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortMappingImpl extends EObjectImpl implements PortMapping
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
	 * The default value of the '{@link #getPortName() <em>Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getPortName()
	 * @generated
	 * @ordered
	 */
  protected static final String PORT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortName() <em>Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getPortName()
	 * @generated
	 * @ordered
	 */
  protected String portName = PORT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaPortName() <em>Java Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getJavaPortName()
	 * @generated
	 * @ordered
	 */
  protected static final String JAVA_PORT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaPortName() <em>Java Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getJavaPortName()
	 * @generated
	 * @ordered
	 */
  protected String javaPortName = JAVA_PORT_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected PortMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return JaxrpcmapPackage.eINSTANCE.getPortMapping();
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PORT_MAPPING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getPortName() {
		return portName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setPortName(String newPortName) {
		String oldPortName = portName;
		portName = newPortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PORT_MAPPING__PORT_NAME, oldPortName, portName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getJavaPortName() {
		return javaPortName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setJavaPortName(String newJavaPortName) {
		String oldJavaPortName = javaPortName;
		javaPortName = newJavaPortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PORT_MAPPING__JAVA_PORT_NAME, oldJavaPortName, javaPortName));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JaxrpcmapPackage.PORT_MAPPING__ID:
				return getId();
			case JaxrpcmapPackage.PORT_MAPPING__PORT_NAME:
				return getPortName();
			case JaxrpcmapPackage.PORT_MAPPING__JAVA_PORT_NAME:
				return getJavaPortName();
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
			case JaxrpcmapPackage.PORT_MAPPING__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.PORT_MAPPING__PORT_NAME:
				setPortName((String)newValue);
				return;
			case JaxrpcmapPackage.PORT_MAPPING__JAVA_PORT_NAME:
				setJavaPortName((String)newValue);
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
			case JaxrpcmapPackage.PORT_MAPPING__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.PORT_MAPPING__PORT_NAME:
				setPortName(PORT_NAME_EDEFAULT);
				return;
			case JaxrpcmapPackage.PORT_MAPPING__JAVA_PORT_NAME:
				setJavaPortName(JAVA_PORT_NAME_EDEFAULT);
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
			case JaxrpcmapPackage.PORT_MAPPING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.PORT_MAPPING__PORT_NAME:
				return PORT_NAME_EDEFAULT == null ? portName != null : !PORT_NAME_EDEFAULT.equals(portName);
			case JaxrpcmapPackage.PORT_MAPPING__JAVA_PORT_NAME:
				return JAVA_PORT_NAME_EDEFAULT == null ? javaPortName != null : !JAVA_PORT_NAME_EDEFAULT.equals(javaPortName);
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
		result.append(", portName: ");
		result.append(portName);
		result.append(", javaPortName: ");
		result.append(javaPortName);
		result.append(')');
		return result.toString();
	}

} //PortMappingImpl
