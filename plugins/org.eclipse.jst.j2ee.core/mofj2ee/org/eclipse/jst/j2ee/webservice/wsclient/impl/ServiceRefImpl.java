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
package org.eclipse.jst.j2ee.webservice.wsclient.impl;

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
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getServiceInterface <em>Service Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getServiceRefName <em>Service Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getWsdlFile <em>Wsdl File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getPortComponentRefs <em>Port Component Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.impl.ServiceRefImpl#getServiceQname <em>Service Qname</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceRefImpl extends CompatibilityDescriptionGroupImpl implements ServiceRef {
	/**
	 * The cached value of the '{@link #getServiceInterface() <em>Service Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceInterface()
	 * @generated
	 * @ordered
	 */
	protected JavaClass serviceInterface = null;

	/**
	 * The default value of the '{@link #getServiceRefName() <em>Service Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceRefName() <em>Service Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefName()
	 * @generated
	 * @ordered
	 */
	protected String serviceRefName = SERVICE_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getWsdlFile() <em>Wsdl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsdlFile()
	 * @generated
	 * @ordered
	 */
	protected static final String WSDL_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWsdlFile() <em>Wsdl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWsdlFile()
	 * @generated
	 * @ordered
	 */
	protected String wsdlFile = WSDL_FILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getJaxrpcMappingFile() <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 * @ordered
	 */
	protected static final String JAXRPC_MAPPING_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJaxrpcMappingFile() <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 * @ordered
	 */
	protected String jaxrpcMappingFile = JAXRPC_MAPPING_FILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPortComponentRefs() <em>Port Component Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortComponentRefs()
	 * @generated
	 * @ordered
	 */
	protected EList portComponentRefs = null;

	/**
	 * The cached value of the '{@link #getHandlers() <em>Handlers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlers()
	 * @generated
	 * @ordered
	 */
	protected EList handlers = null;

	/**
	 * The cached value of the '{@link #getServiceQname() <em>Service Qname</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceQname()
	 * @generated
	 * @ordered
	 */
	protected QName serviceQname = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Webservice_clientPackage.eINSTANCE.getServiceRef();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceRefName() {
		return serviceRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceRefName(String newServiceRefName) {
		String oldServiceRefName = serviceRefName;
		serviceRefName = newServiceRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.SERVICE_REF__SERVICE_REF_NAME, oldServiceRefName, serviceRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWsdlFile() {
		return wsdlFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWsdlFile(String newWsdlFile) {
		String oldWsdlFile = wsdlFile;
		wsdlFile = newWsdlFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.SERVICE_REF__WSDL_FILE, oldWsdlFile, wsdlFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJaxrpcMappingFile() {
		return jaxrpcMappingFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJaxrpcMappingFile(String newJaxrpcMappingFile) {
		String oldJaxrpcMappingFile = jaxrpcMappingFile;
		jaxrpcMappingFile = newJaxrpcMappingFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.SERVICE_REF__JAXRPC_MAPPING_FILE, oldJaxrpcMappingFile, jaxrpcMappingFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getServiceInterface() {
		if (serviceInterface != null && serviceInterface.eIsProxy()) {
			JavaClass oldServiceInterface = serviceInterface;
			serviceInterface = (JavaClass)eResolveProxy((InternalEObject)serviceInterface);
			if (serviceInterface != oldServiceInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Webservice_clientPackage.SERVICE_REF__SERVICE_INTERFACE, oldServiceInterface, serviceInterface));
			}
		}
		return serviceInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetServiceInterface() {
		return serviceInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceInterface(JavaClass newServiceInterface) {
		JavaClass oldServiceInterface = serviceInterface;
		serviceInterface = newServiceInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.SERVICE_REF__SERVICE_INTERFACE, oldServiceInterface, serviceInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPortComponentRefs() {
		if (portComponentRefs == null) {
			portComponentRefs = new EObjectContainmentEList(PortComponentRef.class, this, Webservice_clientPackage.SERVICE_REF__PORT_COMPONENT_REFS);
		}
		return portComponentRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getHandlers() {
		if (handlers == null) {
			handlers = new EObjectContainmentEList(Handler.class, this, Webservice_clientPackage.SERVICE_REF__HANDLERS);
		}
		return handlers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getServiceQname() {
		return serviceQname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServiceQname(QName newServiceQname, NotificationChain msgs) {
		QName oldServiceQname = serviceQname;
		serviceQname = newServiceQname;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME, oldServiceQname, newServiceQname);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceQname(QName newServiceQname) {
		if (newServiceQname != serviceQname) {
			NotificationChain msgs = null;
			if (serviceQname != null)
				msgs = ((InternalEObject)serviceQname).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME, null, msgs);
			if (newServiceQname != null)
				msgs = ((InternalEObject)newServiceQname).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME, null, msgs);
			msgs = basicSetServiceQname(newServiceQname, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME, newServiceQname, newServiceQname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case Webservice_clientPackage.SERVICE_REF__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.SERVICE_REF__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.SERVICE_REF__PORT_COMPONENT_REFS:
					return ((InternalEList)getPortComponentRefs()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.SERVICE_REF__HANDLERS:
					return ((InternalEList)getHandlers()).basicRemove(otherEnd, msgs);
				case Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME:
					return basicSetServiceQname(null, msgs);
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
			case Webservice_clientPackage.SERVICE_REF__ICONS:
				return getIcons();
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAMES:
				return getDisplayNames();
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTIONS:
				return getDescriptions();
			case Webservice_clientPackage.SERVICE_REF__SMALL_ICON:
				return getSmallIcon();
			case Webservice_clientPackage.SERVICE_REF__LARGE_ICON:
				return getLargeIcon();
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTION:
				return getDescription();
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAME:
				return getDisplayName();
			case Webservice_clientPackage.SERVICE_REF__SERVICE_INTERFACE:
				if (resolve) return getServiceInterface();
				return basicGetServiceInterface();
			case Webservice_clientPackage.SERVICE_REF__SERVICE_REF_NAME:
				return getServiceRefName();
			case Webservice_clientPackage.SERVICE_REF__WSDL_FILE:
				return getWsdlFile();
			case Webservice_clientPackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				return getJaxrpcMappingFile();
			case Webservice_clientPackage.SERVICE_REF__PORT_COMPONENT_REFS:
				return getPortComponentRefs();
			case Webservice_clientPackage.SERVICE_REF__HANDLERS:
				return getHandlers();
			case Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME:
				return getServiceQname();
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
			case Webservice_clientPackage.SERVICE_REF__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_INTERFACE:
				setServiceInterface((JavaClass)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_REF_NAME:
				setServiceRefName((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__WSDL_FILE:
				setWsdlFile((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				setJaxrpcMappingFile((String)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__PORT_COMPONENT_REFS:
				getPortComponentRefs().clear();
				getPortComponentRefs().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__HANDLERS:
				getHandlers().clear();
				getHandlers().addAll((Collection)newValue);
				return;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME:
				setServiceQname((QName)newValue);
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
			case Webservice_clientPackage.SERVICE_REF__ICONS:
				getIcons().clear();
				return;
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case Webservice_clientPackage.SERVICE_REF__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_INTERFACE:
				setServiceInterface((JavaClass)null);
				return;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_REF_NAME:
				setServiceRefName(SERVICE_REF_NAME_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__WSDL_FILE:
				setWsdlFile(WSDL_FILE_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				setJaxrpcMappingFile(JAXRPC_MAPPING_FILE_EDEFAULT);
				return;
			case Webservice_clientPackage.SERVICE_REF__PORT_COMPONENT_REFS:
				getPortComponentRefs().clear();
				return;
			case Webservice_clientPackage.SERVICE_REF__HANDLERS:
				getHandlers().clear();
				return;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME:
				setServiceQname((QName)null);
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
			case Webservice_clientPackage.SERVICE_REF__ICONS:
				return icons != null && !icons.isEmpty();
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case Webservice_clientPackage.SERVICE_REF__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case Webservice_clientPackage.SERVICE_REF__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case Webservice_clientPackage.SERVICE_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Webservice_clientPackage.SERVICE_REF__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case Webservice_clientPackage.SERVICE_REF__SERVICE_INTERFACE:
				return serviceInterface != null;
			case Webservice_clientPackage.SERVICE_REF__SERVICE_REF_NAME:
				return SERVICE_REF_NAME_EDEFAULT == null ? serviceRefName != null : !SERVICE_REF_NAME_EDEFAULT.equals(serviceRefName);
			case Webservice_clientPackage.SERVICE_REF__WSDL_FILE:
				return WSDL_FILE_EDEFAULT == null ? wsdlFile != null : !WSDL_FILE_EDEFAULT.equals(wsdlFile);
			case Webservice_clientPackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				return JAXRPC_MAPPING_FILE_EDEFAULT == null ? jaxrpcMappingFile != null : !JAXRPC_MAPPING_FILE_EDEFAULT.equals(jaxrpcMappingFile);
			case Webservice_clientPackage.SERVICE_REF__PORT_COMPONENT_REFS:
				return portComponentRefs != null && !portComponentRefs.isEmpty();
			case Webservice_clientPackage.SERVICE_REF__HANDLERS:
				return handlers != null && !handlers.isEmpty();
			case Webservice_clientPackage.SERVICE_REF__SERVICE_QNAME:
				return serviceQname != null;
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
		result.append(" (serviceRefName: ");//$NON-NLS-1$
		result.append(serviceRefName);
		result.append(", wsdlFile: ");//$NON-NLS-1$
		result.append(wsdlFile);
		result.append(", jaxrpcMappingFile: ");//$NON-NLS-1$
		result.append(jaxrpcMappingFile);
		result.append(')');
		return result.toString();
	}

} //ServiceRefImpl
