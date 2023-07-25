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
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * The servlet element contains the declarative data of a servlet. If a jsp-file is specified and the load-on-startup element is present, then the JSP should be precompiled and loaded.
 */
public class ServletImpl extends CompatibilityDescriptionGroupImpl implements Servlet {

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
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String servletName = SERVLET_NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getLoadOnStartup() <em>Load On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadOnStartup()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LOAD_ON_STARTUP_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Integer loadOnStartup = LOAD_ON_STARTUP_EDEFAULT;
	/**
	 * This is true if the Load On Startup attribute has been set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  protected boolean loadOnStartupESet = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected WebType webType = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList params = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList securityRoleRefs = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected RunAsSpecifiedIdentity runAs = null;
	/**
	 * The cached value of the '{@link #getInitParams() <em>Init Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitParams()
	 * @generated
	 * @ordered
	 */
	protected EList initParams = null;

	public ServletImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.SERVLET;
	}

/**
 * Returns an unmodifiable list of the mappings that this
 * servlet participates in.  Since servletMappings is not
 * a bi-directional attribute, if you want to add or delete a
 * mapping, this must be done in the web-app.
 * @return java.util.List
 */
@Override
public List<ServletMapping> getMappings() {
	WebApp webApp = (WebApp) eContainer();
	if (webApp == null) return new BasicEList(0);
	
	EList allMappings = webApp.getServletMappings();
	Iterator i = allMappings.iterator();
	List<ServletMapping> mappings = new ArrayList<ServletMapping>();
	while (i.hasNext()) {
		ServletMapping mapping = (ServletMapping) i.next();
		if (mapping.getServlet() == this)
			mappings.add(mapping);
	}
	return mappings;
}
/**
 * getParamsAsProperties method comment.
 */
@Override
public Properties getParamsAsProperties() {
	Properties props = new Properties();
	List lparams = getParams();
	InitParam param;
	for (int i = 0; i < lparams.size(); i++) {
		param = (InitParam) lparams.get(i);
		props.setProperty(param.getParamName(), param.getParamValue());
	}
	return props;
}
/**
 * @see Servlet
 */
@Override
public void reSyncSecurityRoleRef(java.lang.String existingRoleName, java.lang.String newRoleName) {
	List roleRefs = getSecurityRoleRefs();
	for (int i = 0; i < roleRefs.size(); i++) {
		SecurityRoleRef roleRef = (SecurityRoleRef) roleRefs.get(i);
		if (roleRef.getLink() != null && roleRef.getLink().equals(existingRoleName))
			roleRef.setLink(newRoleName);
	}
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The servlet-name element contains the canonical name of the servlet.
	 */
	@Override
	public String getServletName() {
		return servletName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setServletName(String newServletName) {
		String oldServletName = servletName;
		servletName = newServletName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__SERVLET_NAME, oldServletName, servletName));
	}

	/**
	 *
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
	}
	
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The load-on-startup element indicates that this servlet should be loaded on the startup of the web application. The optional contents of these element must be a positive integer indicating the order in which the servlet should be loaded. Lower integers are loaded before higher integers. If no value is specified, or if the value specified is not a positive integer, the container is free to load it at any time in the startup sequence.

	 */
	@Override
	public Integer getLoadOnStartup() {
		return loadOnStartup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLoadOnStartup(Integer newLoadOnStartup) {
		Integer oldLoadOnStartup = loadOnStartup;
		loadOnStartup = newLoadOnStartup;
		boolean oldLoadOnStartupESet = loadOnStartupESet;
		loadOnStartupESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__LOAD_ON_STARTUP, oldLoadOnStartup, loadOnStartup, !oldLoadOnStartupESet));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void unsetLoadOnStartup() {
		Integer oldLoadOnStartup = loadOnStartup;
		boolean oldLoadOnStartupESet = loadOnStartupESet;
		loadOnStartup = LOAD_ON_STARTUP_EDEFAULT;
		loadOnStartupESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebapplicationPackage.SERVLET__LOAD_ON_STARTUP, oldLoadOnStartup, LOAD_ON_STARTUP_EDEFAULT, oldLoadOnStartupESet));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public boolean isSetLoadOnStartup() {
		return loadOnStartupESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public WebApp getWebApp() {
		if (eContainerFeatureID != WebapplicationPackage.SERVLET__WEB_APP) return null;
		return (WebApp)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebApp(WebApp newWebApp, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWebApp, WebapplicationPackage.SERVLET__WEB_APP, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setWebApp(WebApp newWebApp) {
		if (newWebApp != eInternalContainer() || (eContainerFeatureID != WebapplicationPackage.SERVLET__WEB_APP && newWebApp != null)) {
			if (EcoreUtil.isAncestor(this, newWebApp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWebApp != null)
				msgs = ((InternalEObject)newWebApp).eInverseAdd(this, WebapplicationPackage.WEB_APP__SERVLETS, WebApp.class, msgs);
			msgs = basicSetWebApp(newWebApp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__WEB_APP, newWebApp, newWebApp));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public WebType getWebType() {
		return webType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWebType(WebType newWebType, NotificationChain msgs) {
		WebType oldWebType = webType;
		webType = newWebType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__WEB_TYPE, oldWebType, newWebType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setWebType(WebType newWebType) {
		if (newWebType != webType) {
			NotificationChain msgs = null;
			if (webType != null)
				msgs = ((InternalEObject)webType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.SERVLET__WEB_TYPE, null, msgs);
			if (newWebType != null)
				msgs = ((InternalEObject)newWebType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.SERVLET__WEB_TYPE, null, msgs);
			msgs = basicSetWebType(newWebType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__WEB_TYPE, newWebType, newWebType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * getParams() is used for J2EE1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getParams() {
		if (params == null) {
			params = new EObjectContainmentEList(InitParam.class, this, WebapplicationPackage.SERVLET__PARAMS);
		}
		return params;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getSecurityRoleRefs() {
		if (securityRoleRefs == null) {
			securityRoleRefs = new EObjectContainmentEList(SecurityRoleRef.class, this, WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS);
		}
		return securityRoleRefs;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * If defined for a servlet, overrides the security identity used to call an EJB by that servlet in this web application. The role-name is one of the security roles already defined for this web application.
	 */
	@Override
	public RunAsSpecifiedIdentity getRunAs() {
		return runAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRunAs(RunAsSpecifiedIdentity newRunAs, NotificationChain msgs) {
		RunAsSpecifiedIdentity oldRunAs = runAs;
		runAs = newRunAs;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__RUN_AS, oldRunAs, newRunAs);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setRunAs(RunAsSpecifiedIdentity newRunAs) {
		if (newRunAs != runAs) {
			NotificationChain msgs = null;
			if (runAs != null)
				msgs = ((InternalEObject)runAs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.SERVLET__RUN_AS, null, msgs);
			if (newRunAs != null)
				msgs = ((InternalEObject)newRunAs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.SERVLET__RUN_AS, null, msgs);
			msgs = basicSetRunAs(newRunAs, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.SERVLET__RUN_AS, newRunAs, newRunAs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * getInitParams() is used for J2EE1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList(ParamValue.class, this, WebapplicationPackage.SERVLET__INIT_PARAMS);
		}
		return initParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.SERVLET__WEB_APP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWebApp((WebApp)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.SERVLET__WEB_APP:
				return basicSetWebApp(null, msgs);
			case WebapplicationPackage.SERVLET__WEB_TYPE:
				return basicSetWebType(null, msgs);
			case WebapplicationPackage.SERVLET__PARAMS:
				return ((InternalEList)getParams()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS:
				return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.SERVLET__RUN_AS:
				return basicSetRunAs(null, msgs);
			case WebapplicationPackage.SERVLET__INIT_PARAMS:
				return ((InternalEList)getInitParams()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case WebapplicationPackage.SERVLET__WEB_APP:
				return eInternalContainer().eInverseRemove(this, WebapplicationPackage.WEB_APP__SERVLETS, WebApp.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.SERVLET__SERVLET_NAME:
				return getServletName();
			case WebapplicationPackage.SERVLET__LOAD_ON_STARTUP:
				return getLoadOnStartup();
			case WebapplicationPackage.SERVLET__WEB_APP:
				return getWebApp();
			case WebapplicationPackage.SERVLET__WEB_TYPE:
				return getWebType();
			case WebapplicationPackage.SERVLET__PARAMS:
				return getParams();
			case WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case WebapplicationPackage.SERVLET__RUN_AS:
				return getRunAs();
			case WebapplicationPackage.SERVLET__INIT_PARAMS:
				return getInitParams();
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
			case WebapplicationPackage.SERVLET__SERVLET_NAME:
				setServletName((String)newValue);
				return;
			case WebapplicationPackage.SERVLET__LOAD_ON_STARTUP:
				setLoadOnStartup((Integer)newValue);
				return;
			case WebapplicationPackage.SERVLET__WEB_APP:
				setWebApp((WebApp)newValue);
				return;
			case WebapplicationPackage.SERVLET__WEB_TYPE:
				setWebType((WebType)newValue);
				return;
			case WebapplicationPackage.SERVLET__PARAMS:
				getParams().clear();
				getParams().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.SERVLET__RUN_AS:
				setRunAs((RunAsSpecifiedIdentity)newValue);
				return;
			case WebapplicationPackage.SERVLET__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection)newValue);
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
			case WebapplicationPackage.SERVLET__SERVLET_NAME:
				setServletName(SERVLET_NAME_EDEFAULT);
				return;
			case WebapplicationPackage.SERVLET__LOAD_ON_STARTUP:
				unsetLoadOnStartup();
				return;
			case WebapplicationPackage.SERVLET__WEB_APP:
				setWebApp((WebApp)null);
				return;
			case WebapplicationPackage.SERVLET__WEB_TYPE:
				setWebType((WebType)null);
				return;
			case WebapplicationPackage.SERVLET__PARAMS:
				getParams().clear();
				return;
			case WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case WebapplicationPackage.SERVLET__RUN_AS:
				setRunAs((RunAsSpecifiedIdentity)null);
				return;
			case WebapplicationPackage.SERVLET__INIT_PARAMS:
				getInitParams().clear();
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
			case WebapplicationPackage.SERVLET__SERVLET_NAME:
				return SERVLET_NAME_EDEFAULT == null ? servletName != null : !SERVLET_NAME_EDEFAULT.equals(servletName);
			case WebapplicationPackage.SERVLET__LOAD_ON_STARTUP:
				return isSetLoadOnStartup();
			case WebapplicationPackage.SERVLET__WEB_APP:
				return getWebApp() != null;
			case WebapplicationPackage.SERVLET__WEB_TYPE:
				return webType != null;
			case WebapplicationPackage.SERVLET__PARAMS:
				return params != null && !params.isEmpty();
			case WebapplicationPackage.SERVLET__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case WebapplicationPackage.SERVLET__RUN_AS:
				return runAs != null;
			case WebapplicationPackage.SERVLET__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (servletName: "); //$NON-NLS-1$
		result.append(servletName);
		result.append(", loadOnStartup: "); //$NON-NLS-1$
		if (loadOnStartupESet) result.append(loadOnStartup); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

	/** 
	 * return the java servlet class for this servlet
	 */
	@Override
	public JavaClass getServletClass() {
		JavaClass javaClass = null;
		String javaClassName = null;
		// Handle servlet case
		if (getWebType().isServletType())
			javaClassName = ((ServletType)getWebType()).getClassName();
		//Handle the JSP case
		else {
			String relPath = ((JSPType)getWebType()).getJspFile();
			URI webAppURI = getWebApp().eResource().getURI();
			javaClassName = webAppURI.segment(1)+"/"+webAppURI.segment(2)+relPath;//$NON-NLS-1$
		}
		javaClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType(javaClassName,this);
		return javaClass;
	}

}















