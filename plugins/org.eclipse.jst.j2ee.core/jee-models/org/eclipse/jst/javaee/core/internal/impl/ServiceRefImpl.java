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

import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.PortComponentRef;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.javaee.core.ServiceRefHandler;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChains;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getServiceRefName <em>Service Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getServiceInterface <em>Service Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getServiceRefType <em>Service Ref Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getWsdlFile <em>Wsdl File</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getServiceQname <em>Service Qname</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getPortComponentRefs <em>Port Component Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getHandlerChains <em>Handler Chains</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.ServiceRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceRefImpl extends EObjectImpl implements ServiceRef {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

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
	 * The default value of the '{@link #getServiceRefType() <em>Service Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefType()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVICE_REF_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceRefType() <em>Service Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefType()
	 * @generated
	 * @ordered
	 */
	protected String serviceRefType = SERVICE_REF_TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getServiceQname() <em>Service Qname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceQname()
	 * @generated
	 * @ordered
	 */
	protected static final QName SERVICE_QNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServiceQname() <em>Service Qname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceQname()
	 * @generated
	 * @ordered
	 */
	protected QName serviceQname = SERVICE_QNAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPortComponentRefs() <em>Port Component Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortComponentRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<PortComponentRef> portComponentRefs;

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
	 * The cached value of the '{@link #getHandlerChains() <em>Handler Chains</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHandlerChains()
	 * @generated
	 * @ordered
	 */
	protected ServiceRefHandlerChains handlerChains;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInjectionTargets() <em>Injection Targets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargets()
	 * @generated
	 * @ordered
	 */
	protected EList<InjectionTarget> injectionTargets;

	/**
	 * The default value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected static final String LOOKUP_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected String lookupName = LOOKUP_NAME_EDEFAULT;

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
	protected ServiceRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.SERVICE_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.SERVICE_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, JavaeePackage.SERVICE_REF__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, JavaeePackage.SERVICE_REF__ICONS);
		}
		return icons;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__SERVICE_REF_NAME, oldServiceRefName, serviceRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceInterface() {
		return serviceInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceInterface(String newServiceInterface) {
		String oldServiceInterface = serviceInterface;
		serviceInterface = newServiceInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__SERVICE_INTERFACE, oldServiceInterface, serviceInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServiceRefType() {
		return serviceRefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServiceRefType(String newServiceRefType) {
		String oldServiceRefType = serviceRefType;
		serviceRefType = newServiceRefType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__SERVICE_REF_TYPE, oldServiceRefType, serviceRefType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__WSDL_FILE, oldWsdlFile, wsdlFile));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__JAXRPC_MAPPING_FILE, oldJaxrpcMappingFile, jaxrpcMappingFile));
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
	public void setServiceQname(QName newServiceQname) {
		QName oldServiceQname = serviceQname;
		serviceQname = newServiceQname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__SERVICE_QNAME, oldServiceQname, serviceQname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<PortComponentRef> getPortComponentRefs() {
		if (portComponentRefs == null) {
			portComponentRefs = new EObjectContainmentEList<PortComponentRef>(PortComponentRef.class, this, JavaeePackage.SERVICE_REF__PORT_COMPONENT_REFS);
		}
		return portComponentRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ServiceRefHandler> getHandlers() {
		if (handlers == null) {
			handlers = new EObjectContainmentEList<ServiceRefHandler>(ServiceRefHandler.class, this, JavaeePackage.SERVICE_REF__HANDLERS);
		}
		return handlers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceRefHandlerChains getHandlerChains() {
		return handlerChains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHandlerChains(ServiceRefHandlerChains newHandlerChains, NotificationChain msgs) {
		ServiceRefHandlerChains oldHandlerChains = handlerChains;
		handlerChains = newHandlerChains;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__HANDLER_CHAINS, oldHandlerChains, newHandlerChains);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHandlerChains(ServiceRefHandlerChains newHandlerChains) {
		if (newHandlerChains != handlerChains) {
			NotificationChain msgs = null;
			if (handlerChains != null)
				msgs = ((InternalEObject)handlerChains).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.SERVICE_REF__HANDLER_CHAINS, null, msgs);
			if (newHandlerChains != null)
				msgs = ((InternalEObject)newHandlerChains).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaeePackage.SERVICE_REF__HANDLER_CHAINS, null, msgs);
			msgs = basicSetHandlerChains(newHandlerChains, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__HANDLER_CHAINS, newHandlerChains, newHandlerChains));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.SERVICE_REF__INJECTION_TARGETS);
		}
		return injectionTargets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLookupName() {
		return lookupName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookupName(String newLookupName) {
		String oldLookupName = lookupName;
		lookupName = newLookupName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__LOOKUP_NAME, oldLookupName, lookupName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.SERVICE_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.SERVICE_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF__PORT_COMPONENT_REFS:
				return ((InternalEList<?>)getPortComponentRefs()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF__HANDLERS:
				return ((InternalEList<?>)getHandlers()).basicRemove(otherEnd, msgs);
			case JavaeePackage.SERVICE_REF__HANDLER_CHAINS:
				return basicSetHandlerChains(null, msgs);
			case JavaeePackage.SERVICE_REF__INJECTION_TARGETS:
				return ((InternalEList<?>)getInjectionTargets()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.SERVICE_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.SERVICE_REF__DISPLAY_NAMES:
				return getDisplayNames();
			case JavaeePackage.SERVICE_REF__ICONS:
				return getIcons();
			case JavaeePackage.SERVICE_REF__SERVICE_REF_NAME:
				return getServiceRefName();
			case JavaeePackage.SERVICE_REF__SERVICE_INTERFACE:
				return getServiceInterface();
			case JavaeePackage.SERVICE_REF__SERVICE_REF_TYPE:
				return getServiceRefType();
			case JavaeePackage.SERVICE_REF__WSDL_FILE:
				return getWsdlFile();
			case JavaeePackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				return getJaxrpcMappingFile();
			case JavaeePackage.SERVICE_REF__SERVICE_QNAME:
				return getServiceQname();
			case JavaeePackage.SERVICE_REF__PORT_COMPONENT_REFS:
				return getPortComponentRefs();
			case JavaeePackage.SERVICE_REF__HANDLERS:
				return getHandlers();
			case JavaeePackage.SERVICE_REF__HANDLER_CHAINS:
				return getHandlerChains();
			case JavaeePackage.SERVICE_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.SERVICE_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.SERVICE_REF__LOOKUP_NAME:
				return getLookupName();
			case JavaeePackage.SERVICE_REF__ID:
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
			case JavaeePackage.SERVICE_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.SERVICE_REF__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case JavaeePackage.SERVICE_REF__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_REF_NAME:
				setServiceRefName((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_INTERFACE:
				setServiceInterface((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_REF_TYPE:
				setServiceRefType((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__WSDL_FILE:
				setWsdlFile((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				setJaxrpcMappingFile((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_QNAME:
				setServiceQname((QName)newValue);
				return;
			case JavaeePackage.SERVICE_REF__PORT_COMPONENT_REFS:
				getPortComponentRefs().clear();
				getPortComponentRefs().addAll((Collection<? extends PortComponentRef>)newValue);
				return;
			case JavaeePackage.SERVICE_REF__HANDLERS:
				getHandlers().clear();
				getHandlers().addAll((Collection<? extends ServiceRefHandler>)newValue);
				return;
			case JavaeePackage.SERVICE_REF__HANDLER_CHAINS:
				setHandlerChains((ServiceRefHandlerChains)newValue);
				return;
			case JavaeePackage.SERVICE_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.SERVICE_REF__LOOKUP_NAME:
				setLookupName((String)newValue);
				return;
			case JavaeePackage.SERVICE_REF__ID:
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
			case JavaeePackage.SERVICE_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.SERVICE_REF__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JavaeePackage.SERVICE_REF__ICONS:
				getIcons().clear();
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_REF_NAME:
				setServiceRefName(SERVICE_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_INTERFACE:
				setServiceInterface(SERVICE_INTERFACE_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_REF_TYPE:
				setServiceRefType(SERVICE_REF_TYPE_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__WSDL_FILE:
				setWsdlFile(WSDL_FILE_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				setJaxrpcMappingFile(JAXRPC_MAPPING_FILE_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__SERVICE_QNAME:
				setServiceQname(SERVICE_QNAME_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__PORT_COMPONENT_REFS:
				getPortComponentRefs().clear();
				return;
			case JavaeePackage.SERVICE_REF__HANDLERS:
				getHandlers().clear();
				return;
			case JavaeePackage.SERVICE_REF__HANDLER_CHAINS:
				setHandlerChains((ServiceRefHandlerChains)null);
				return;
			case JavaeePackage.SERVICE_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.SERVICE_REF__LOOKUP_NAME:
				setLookupName(LOOKUP_NAME_EDEFAULT);
				return;
			case JavaeePackage.SERVICE_REF__ID:
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
			case JavaeePackage.SERVICE_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.SERVICE_REF__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JavaeePackage.SERVICE_REF__ICONS:
				return icons != null && !icons.isEmpty();
			case JavaeePackage.SERVICE_REF__SERVICE_REF_NAME:
				return SERVICE_REF_NAME_EDEFAULT == null ? serviceRefName != null : !SERVICE_REF_NAME_EDEFAULT.equals(serviceRefName);
			case JavaeePackage.SERVICE_REF__SERVICE_INTERFACE:
				return SERVICE_INTERFACE_EDEFAULT == null ? serviceInterface != null : !SERVICE_INTERFACE_EDEFAULT.equals(serviceInterface);
			case JavaeePackage.SERVICE_REF__SERVICE_REF_TYPE:
				return SERVICE_REF_TYPE_EDEFAULT == null ? serviceRefType != null : !SERVICE_REF_TYPE_EDEFAULT.equals(serviceRefType);
			case JavaeePackage.SERVICE_REF__WSDL_FILE:
				return WSDL_FILE_EDEFAULT == null ? wsdlFile != null : !WSDL_FILE_EDEFAULT.equals(wsdlFile);
			case JavaeePackage.SERVICE_REF__JAXRPC_MAPPING_FILE:
				return JAXRPC_MAPPING_FILE_EDEFAULT == null ? jaxrpcMappingFile != null : !JAXRPC_MAPPING_FILE_EDEFAULT.equals(jaxrpcMappingFile);
			case JavaeePackage.SERVICE_REF__SERVICE_QNAME:
				return SERVICE_QNAME_EDEFAULT == null ? serviceQname != null : !SERVICE_QNAME_EDEFAULT.equals(serviceQname);
			case JavaeePackage.SERVICE_REF__PORT_COMPONENT_REFS:
				return portComponentRefs != null && !portComponentRefs.isEmpty();
			case JavaeePackage.SERVICE_REF__HANDLERS:
				return handlers != null && !handlers.isEmpty();
			case JavaeePackage.SERVICE_REF__HANDLER_CHAINS:
				return handlerChains != null;
			case JavaeePackage.SERVICE_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.SERVICE_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.SERVICE_REF__LOOKUP_NAME:
				return LOOKUP_NAME_EDEFAULT == null ? lookupName != null : !LOOKUP_NAME_EDEFAULT.equals(lookupName);
			case JavaeePackage.SERVICE_REF__ID:
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
		result.append(" (serviceRefName: "); //$NON-NLS-1$
		result.append(serviceRefName);
		result.append(", serviceInterface: "); //$NON-NLS-1$
		result.append(serviceInterface);
		result.append(", serviceRefType: "); //$NON-NLS-1$
		result.append(serviceRefType);
		result.append(", wsdlFile: "); //$NON-NLS-1$
		result.append(wsdlFile);
		result.append(", jaxrpcMappingFile: "); //$NON-NLS-1$
		result.append(jaxrpcMappingFile);
		result.append(", serviceQname: "); //$NON-NLS-1$
		result.append(serviceQname);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", lookupName: "); //$NON-NLS-1$
		result.append(lookupName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

	public void setServiceQname(Object newServiceQname) {
		if (newServiceQname == null || newServiceQname instanceof QName)
		{
			setServiceQname((QName)newServiceQname);
		}
		else
		{
			QName newQName = QName.valueOf(newServiceQname.toString());
			setServiceQname(newQName);
		}
	}

} //ServiceRefImpl