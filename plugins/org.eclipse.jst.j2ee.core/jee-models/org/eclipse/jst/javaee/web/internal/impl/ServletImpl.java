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
package org.eclipse.jst.javaee.web.internal.impl;

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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRoleRef;

import org.eclipse.jst.javaee.web.MultipartConfigType;
import org.eclipse.jst.javaee.web.Servlet;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Servlet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getServletName <em>Servlet Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getServletClass <em>Servlet Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getJspFile <em>Jsp File</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getLoadOnStartup <em>Load On Startup</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#isAsyncSupported <em>Async Supported</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getSecurityRoleRefs <em>Security Role Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getMultipartConfig <em>Multipart Config</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServletImpl extends EObjectImpl implements Servlet {
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
	 * The default value of the '{@link #getServletName() <em>Servlet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServletName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVLET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServletName() <em>Servlet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServletName()
	 * @generated
	 * @ordered
	 */
	protected String servletName = SERVLET_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getServletClass() <em>Servlet Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServletClass()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVLET_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServletClass() <em>Servlet Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServletClass()
	 * @generated
	 * @ordered
	 */
	protected String servletClass = SERVLET_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getJspFile() <em>Jsp File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJspFile()
	 * @generated
	 * @ordered
	 */
	protected static final String JSP_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJspFile() <em>Jsp File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJspFile()
	 * @generated
	 * @ordered
	 */
	protected String jspFile = JSP_FILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInitParams() <em>Init Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitParams()
	 * @generated
	 * @ordered
	 */
	protected EList<ParamValue> initParams;

	/**
	 * The default value of the '{@link #getLoadOnStartup() <em>Load On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadOnStartup()
	 * @generated
	 * @ordered
	 */
	protected static final Object LOAD_ON_STARTUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLoadOnStartup() <em>Load On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadOnStartup()
	 * @generated
	 * @ordered
	 */
	protected Object loadOnStartup = LOAD_ON_STARTUP_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * This is true if the Enabled attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enabledESet;

	/**
	 * The default value of the '{@link #isAsyncSupported() <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsyncSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ASYNC_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAsyncSupported() <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsyncSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean asyncSupported = ASYNC_SUPPORTED_EDEFAULT;

	/**
	 * This is true if the Async Supported attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean asyncSupportedESet;

	/**
	 * The cached value of the '{@link #getRunAs() <em>Run As</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunAs()
	 * @generated
	 * @ordered
	 */
	protected RunAs runAs;

	/**
	 * The cached value of the '{@link #getSecurityRoleRefs() <em>Security Role Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoleRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<SecurityRoleRef> securityRoleRefs;

	/**
	 * The cached value of the '{@link #getMultipartConfig() <em>Multipart Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultipartConfig()
	 * @generated
	 * @ordered
	 */
	protected MultipartConfigType multipartConfig;

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
	protected ServletImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.SERVLET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, WebPackage.SERVLET__DESCRIPTIONS);
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
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, WebPackage.SERVLET__DISPLAY_NAMES);
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
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, WebPackage.SERVLET__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServletName() {
		return servletName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServletName(String newServletName) {
		String oldServletName = servletName;
		servletName = newServletName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__SERVLET_NAME, oldServletName, servletName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServletClass() {
		return servletClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServletClass(String newServletClass) {
		String oldServletClass = servletClass;
		servletClass = newServletClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__SERVLET_CLASS, oldServletClass, servletClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJspFile() {
		return jspFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJspFile(String newJspFile) {
		String oldJspFile = jspFile;
		jspFile = newJspFile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__JSP_FILE, oldJspFile, jspFile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<ParamValue> getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList<ParamValue>(ParamValue.class, this, WebPackage.SERVLET__INIT_PARAMS);
		}
		return initParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getLoadOnStartup() {
		return loadOnStartup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadOnStartup(Object newLoadOnStartup) {
		Object oldLoadOnStartup = loadOnStartup;
		loadOnStartup = newLoadOnStartup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__LOAD_ON_STARTUP, oldLoadOnStartup, loadOnStartup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		boolean oldEnabledESet = enabledESet;
		enabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__ENABLED, oldEnabled, enabled, !oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnabled() {
		boolean oldEnabled = enabled;
		boolean oldEnabledESet = enabledESet;
		enabled = ENABLED_EDEFAULT;
		enabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.SERVLET__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnabled() {
		return enabledESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAsyncSupported() {
		return asyncSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsyncSupported(boolean newAsyncSupported) {
		boolean oldAsyncSupported = asyncSupported;
		asyncSupported = newAsyncSupported;
		boolean oldAsyncSupportedESet = asyncSupportedESet;
		asyncSupportedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__ASYNC_SUPPORTED, oldAsyncSupported, asyncSupported, !oldAsyncSupportedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAsyncSupported() {
		boolean oldAsyncSupported = asyncSupported;
		boolean oldAsyncSupportedESet = asyncSupportedESet;
		asyncSupported = ASYNC_SUPPORTED_EDEFAULT;
		asyncSupportedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.SERVLET__ASYNC_SUPPORTED, oldAsyncSupported, ASYNC_SUPPORTED_EDEFAULT, oldAsyncSupportedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAsyncSupported() {
		return asyncSupportedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunAs getRunAs() {
		return runAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRunAs(RunAs newRunAs, NotificationChain msgs) {
		RunAs oldRunAs = runAs;
		runAs = newRunAs;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__RUN_AS, oldRunAs, newRunAs);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRunAs(RunAs newRunAs) {
		if (newRunAs != runAs) {
			NotificationChain msgs = null;
			if (runAs != null)
				msgs = ((InternalEObject)runAs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.SERVLET__RUN_AS, null, msgs);
			if (newRunAs != null)
				msgs = ((InternalEObject)newRunAs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.SERVLET__RUN_AS, null, msgs);
			msgs = basicSetRunAs(newRunAs, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__RUN_AS, newRunAs, newRunAs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<SecurityRoleRef> getSecurityRoleRefs() {
		if (securityRoleRefs == null) {
			securityRoleRefs = new EObjectContainmentEList<SecurityRoleRef>(SecurityRoleRef.class, this, WebPackage.SERVLET__SECURITY_ROLE_REFS);
		}
		return securityRoleRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipartConfigType getMultipartConfig() {
		return multipartConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMultipartConfig(MultipartConfigType newMultipartConfig, NotificationChain msgs) {
		MultipartConfigType oldMultipartConfig = multipartConfig;
		multipartConfig = newMultipartConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__MULTIPART_CONFIG, oldMultipartConfig, newMultipartConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultipartConfig(MultipartConfigType newMultipartConfig) {
		if (newMultipartConfig != multipartConfig) {
			NotificationChain msgs = null;
			if (multipartConfig != null)
				msgs = ((InternalEObject)multipartConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.SERVLET__MULTIPART_CONFIG, null, msgs);
			if (newMultipartConfig != null)
				msgs = ((InternalEObject)newMultipartConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.SERVLET__MULTIPART_CONFIG, null, msgs);
			msgs = basicSetMultipartConfig(newMultipartConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__MULTIPART_CONFIG, newMultipartConfig, newMultipartConfig));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SERVLET__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.SERVLET__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case WebPackage.SERVLET__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case WebPackage.SERVLET__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case WebPackage.SERVLET__INIT_PARAMS:
				return ((InternalEList<?>)getInitParams()).basicRemove(otherEnd, msgs);
			case WebPackage.SERVLET__RUN_AS:
				return basicSetRunAs(null, msgs);
			case WebPackage.SERVLET__SECURITY_ROLE_REFS:
				return ((InternalEList<?>)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
			case WebPackage.SERVLET__MULTIPART_CONFIG:
				return basicSetMultipartConfig(null, msgs);
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
			case WebPackage.SERVLET__DESCRIPTIONS:
				return getDescriptions();
			case WebPackage.SERVLET__DISPLAY_NAMES:
				return getDisplayNames();
			case WebPackage.SERVLET__ICONS:
				return getIcons();
			case WebPackage.SERVLET__SERVLET_NAME:
				return getServletName();
			case WebPackage.SERVLET__SERVLET_CLASS:
				return getServletClass();
			case WebPackage.SERVLET__JSP_FILE:
				return getJspFile();
			case WebPackage.SERVLET__INIT_PARAMS:
				return getInitParams();
			case WebPackage.SERVLET__LOAD_ON_STARTUP:
				return getLoadOnStartup();
			case WebPackage.SERVLET__ENABLED:
				return isEnabled();
			case WebPackage.SERVLET__ASYNC_SUPPORTED:
				return isAsyncSupported();
			case WebPackage.SERVLET__RUN_AS:
				return getRunAs();
			case WebPackage.SERVLET__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case WebPackage.SERVLET__MULTIPART_CONFIG:
				return getMultipartConfig();
			case WebPackage.SERVLET__ID:
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
			case WebPackage.SERVLET__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case WebPackage.SERVLET__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case WebPackage.SERVLET__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case WebPackage.SERVLET__SERVLET_NAME:
				setServletName((String)newValue);
				return;
			case WebPackage.SERVLET__SERVLET_CLASS:
				setServletClass((String)newValue);
				return;
			case WebPackage.SERVLET__JSP_FILE:
				setJspFile((String)newValue);
				return;
			case WebPackage.SERVLET__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection<? extends ParamValue>)newValue);
				return;
			case WebPackage.SERVLET__LOAD_ON_STARTUP:
				setLoadOnStartup(newValue);
				return;
			case WebPackage.SERVLET__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case WebPackage.SERVLET__ASYNC_SUPPORTED:
				setAsyncSupported((Boolean)newValue);
				return;
			case WebPackage.SERVLET__RUN_AS:
				setRunAs((RunAs)newValue);
				return;
			case WebPackage.SERVLET__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection<? extends SecurityRoleRef>)newValue);
				return;
			case WebPackage.SERVLET__MULTIPART_CONFIG:
				setMultipartConfig((MultipartConfigType)newValue);
				return;
			case WebPackage.SERVLET__ID:
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
			case WebPackage.SERVLET__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WebPackage.SERVLET__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case WebPackage.SERVLET__ICONS:
				getIcons().clear();
				return;
			case WebPackage.SERVLET__SERVLET_NAME:
				setServletName(SERVLET_NAME_EDEFAULT);
				return;
			case WebPackage.SERVLET__SERVLET_CLASS:
				setServletClass(SERVLET_CLASS_EDEFAULT);
				return;
			case WebPackage.SERVLET__JSP_FILE:
				setJspFile(JSP_FILE_EDEFAULT);
				return;
			case WebPackage.SERVLET__INIT_PARAMS:
				getInitParams().clear();
				return;
			case WebPackage.SERVLET__LOAD_ON_STARTUP:
				setLoadOnStartup(LOAD_ON_STARTUP_EDEFAULT);
				return;
			case WebPackage.SERVLET__ENABLED:
				unsetEnabled();
				return;
			case WebPackage.SERVLET__ASYNC_SUPPORTED:
				unsetAsyncSupported();
				return;
			case WebPackage.SERVLET__RUN_AS:
				setRunAs((RunAs)null);
				return;
			case WebPackage.SERVLET__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case WebPackage.SERVLET__MULTIPART_CONFIG:
				setMultipartConfig((MultipartConfigType)null);
				return;
			case WebPackage.SERVLET__ID:
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
			case WebPackage.SERVLET__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case WebPackage.SERVLET__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case WebPackage.SERVLET__ICONS:
				return icons != null && !icons.isEmpty();
			case WebPackage.SERVLET__SERVLET_NAME:
				return SERVLET_NAME_EDEFAULT == null ? servletName != null : !SERVLET_NAME_EDEFAULT.equals(servletName);
			case WebPackage.SERVLET__SERVLET_CLASS:
				return SERVLET_CLASS_EDEFAULT == null ? servletClass != null : !SERVLET_CLASS_EDEFAULT.equals(servletClass);
			case WebPackage.SERVLET__JSP_FILE:
				return JSP_FILE_EDEFAULT == null ? jspFile != null : !JSP_FILE_EDEFAULT.equals(jspFile);
			case WebPackage.SERVLET__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case WebPackage.SERVLET__LOAD_ON_STARTUP:
				return LOAD_ON_STARTUP_EDEFAULT == null ? loadOnStartup != null : !LOAD_ON_STARTUP_EDEFAULT.equals(loadOnStartup);
			case WebPackage.SERVLET__ENABLED:
				return isSetEnabled();
			case WebPackage.SERVLET__ASYNC_SUPPORTED:
				return isSetAsyncSupported();
			case WebPackage.SERVLET__RUN_AS:
				return runAs != null;
			case WebPackage.SERVLET__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case WebPackage.SERVLET__MULTIPART_CONFIG:
				return multipartConfig != null;
			case WebPackage.SERVLET__ID:
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
		result.append(" (servletName: "); //$NON-NLS-1$
		result.append(servletName);
		result.append(", servletClass: "); //$NON-NLS-1$
		result.append(servletClass);
		result.append(", jspFile: "); //$NON-NLS-1$
		result.append(jspFile);
		result.append(", loadOnStartup: "); //$NON-NLS-1$
		result.append(loadOnStartup);
		result.append(", enabled: "); //$NON-NLS-1$
		if (enabledESet) result.append(enabled); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", asyncSupported: "); //$NON-NLS-1$
		if (asyncSupportedESet) result.append(asyncSupported); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ServletImpl