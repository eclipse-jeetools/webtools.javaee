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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl;
import org.eclipse.jst.j2ee.common.internal.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.ErrorCodeErrorPage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.SessionConfig;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;


/**
 * The web-app element is the root of the deployment descriptor for
 * a web application.
 */
public class WebAppImpl extends JNDIEnvRefsGroupImpl implements WebApp {

	/**
	 * The default value of the '{@link #isDistributable() <em>Distributable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDistributable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISTRIBUTABLE_EDEFAULT = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected boolean distributable = DISTRIBUTABLE_EDEFAULT;
	/**
	 * This is true if the Distributable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean distributableESet = false;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList contexts = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList errorPages = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected WelcomeFileList fileList = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList<TagLibRef> tagLibs = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList constraints = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LoginConfig loginConfig = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList mimeMappings = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected SessionConfig sessionConfig = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList servletMappings = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList<Servlet> servlets = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList securityRoles = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList filters = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList filterMappings = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList listeners = null;
	/**
	 * The cached value of the '{@link #getContextParams() <em>Context Params</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextParams()
	 * @generated
	 * @ordered
	 */
	protected EList contextParams = null;

	/**
	 * The cached value of the '{@link #getJspConfig() <em>Jsp Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJspConfig()
	 * @generated
	 * @ordered
	 */
	protected JSPConfig jspConfig = null;

	/**
	 * The cached value of the '{@link #getMessageDestinations() <em>Message Destinations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinations()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinations = null;

	/**
	 * The cached value of the '{@link #getLocalEncodingMappingList() <em>Local Encoding Mapping List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalEncodingMappingList()
	 * @generated
	 * @ordered
	 */
	protected LocalEncodingMappingList localEncodingMappingList = null;

	public WebAppImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.WEB_APP;
	}

/**
 * @see org.eclipse.jst.j2ee.internal.webapplication.WebApp
 */
@Override
public boolean containsSecurityRole(java.lang.String name) {
	return getSecurityRoleNamed(name) != null;
}
@Override
public ErrorPage getErrorPageByCode(Integer code) {
	List lerrorPages = getErrorPages();
	String codeString = code.toString();
	Iterator ie = lerrorPages.iterator();
	while (ie.hasNext()) {
		ErrorPage ep = (ErrorPage) ie.next();
		if (ep.isErrorCode()) {
			ErrorCodeErrorPage ecep = (ErrorCodeErrorPage) ep;
			if (codeString.equals(ecep.getErrorCode()))
				return ecep;
		}
	}
	return null;
}
@Override
public ErrorPage getErrorPageByExceptionType(String exceptionType) {
	if (exceptionType != null) {
		List lerrorPages = getErrorPages();
		Iterator ie = lerrorPages.iterator();
		while (ie.hasNext()) {
			ErrorPage ep = (ErrorPage) ie.next();
			if (ep.isExceptionType()) {
				ExceptionTypeErrorPage etep = (ExceptionTypeErrorPage) ep;
				if (exceptionType.equals(etep.getExceptionTypeName()))
					return etep;
			}
		}
	}
	return null;
}
/**
 * Finds a filter mapping for a specified filter.
 * @param filter The filter to find the mapping for.
 * @return The first filter mapping found for the specified filter, or null if no
 * mapping exists for the filter.
 */
@Override
public FilterMapping getFilterMapping(
	Filter aFilter) {
	if (aFilter != null) {
		List mappings = getFilterMappings();
		FilterMapping mapping;
		for (int i = 0; i < mappings.size(); i++) {
			mapping = (FilterMapping) mappings.get(i);
			if (aFilter.equals(mapping.getFilter())) 
				return mapping;
		}
	}
	return null;
}
/**
 * getFilterNamed method comment.
 */
@Override
public Filter getFilterNamed(java.lang.String name) {
	List lfilters = getFilters();
	if (lfilters == null || lfilters.size() == 0) {
		return null;
	}
	Filter next;
	for (int i = 0; i < lfilters.size(); i++) {
		next = (Filter) lfilters.get(i);
		if (next.getName().equals(name)) {
			return next;
		}
	}
	return null;
}
@Override
public List getFilterNames() {
	List names, lfilters = getFilters();
	names = new ArrayList(lfilters.size());
	Filter next;
	for (int i = 0; i < lfilters.size(); i++) {
		next = (Filter) lfilters.get(i);
		names.add(next.getName());
	}
	return names;
}
/**
 * Returns the mime type for the specified extension
 * @param An extension to find the mime type for.
 */
@Override
public java.lang.String getMimeType(java.lang.String extension) {
	if (extension != null) {
		List mappings = getMimeMappings();
		MimeMapping mapping;
		for (int i = 0; i < mappings.size(); i++) {
			mapping = (MimeMapping) mappings.get(i);
			if (extension.equals(mapping.getExtension()))
				return mapping.getMimeType();
		}
	}
	return null;
}
@Override
public SecurityRole getSecurityRoleNamed(String roleName) {
	java.util.List tempRoles = getSecurityRoles();
	SecurityRole role;
	for (int i = 0; i < tempRoles.size(); i++) {
		role = (SecurityRole) tempRoles.get(i);
		if (role.getRoleName().equals(roleName))
			return role;
	}
	return null;
}
/**
 * Finds a servlet mapping for a specified servlet.
 * @param servlet The servlet to find the mappings for.
 * @return The servlet mapping for the specified servlet, or null if no
 * mapping exists for the servlet.
 */
@Override
public ServletMapping getServletMapping(
	Servlet aServlet) {
	if (aServlet != null) {
		List mappings = getServletMappings();
		ServletMapping mapping;
		for (int i = 0; i < mappings.size(); i++) {
			mapping = (ServletMapping) mappings.get(i);
			if (aServlet.equals(mapping.getServlet())) 
				return mapping;
		}
	}
	return null;
}
/**
 * getServletNamed method comment.
 */
@Override
public Servlet getServletNamed(java.lang.String name) {
	List lservlets = getServlets();
	if (lservlets == null || lservlets.size() == 0) {
		return null;
	}
	Servlet next;
	String nextName;
	for (int i = 0; i < lservlets.size(); i++) {
		next = (Servlet) lservlets.get(i);
		nextName = next.getServletName();
		if (nextName != null && nextName.equals(name)) {
			return next;
		}
	}
	return null;
}
@Override
public List getServletNames() {
	List names, lservlets = getServlets();
	names = new ArrayList(lservlets.size());
	Servlet next;
	for (int i = 0; i < lservlets.size(); i++) {
		next = (Servlet) lservlets.get(i);
		names.add(next.getServletName());
	}
	return names;
}
/**
 * Return boolean indicating if this Web App was populated from an Servlet2.2 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
@Override
public boolean isVersion2_2Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.WEBAPP_SYSTEMID_2_2);
}
/**
 * Return boolean indicating if this Web App was populated from an Servlet2.3 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
@Override
public boolean isVersion2_3Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.WEBAPP_SYSTEMID_2_3);
}
/**
 * @see org.eclipse.jst.j2ee.internal.webapplication.WebApp
 */
@Override
public void renameSecurityRole(java.lang.String existingRoleName, java.lang.String newRoleName) {
	SecurityRole role = getSecurityRoleNamed(existingRoleName);
	if (role != null)
		role.setRoleName(newRoleName);
	List theServlets = getServlets();
	for (int i = 0; i < theServlets.size(); i++){
		Servlet servlet = (Servlet)theServlets.get(i);
		servlet.reSyncSecurityRoleRef(existingRoleName, newRoleName);
	}
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The distributable element, by its presence in a web application deployment
	 * descriptor, indicates that this web application is programmed appropriately to
	 * be deployed into a distributed servlet container
	 */
	@Override
	public boolean isDistributable() {
		return distributable;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setDistributable(boolean newDistributable) {
		boolean oldDistributable = distributable;
		distributable = newDistributable;
		boolean oldDistributableESet = distributableESet;
		distributableESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__DISTRIBUTABLE, oldDistributable, distributable, !oldDistributableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDistributable() {
		boolean oldDistributable = distributable;
		boolean oldDistributableESet = distributableESet;
		distributable = DISTRIBUTABLE_EDEFAULT;
		distributableESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebapplicationPackage.WEB_APP__DISTRIBUTABLE, oldDistributable, DISTRIBUTABLE_EDEFAULT, oldDistributableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDistributable() {
		return distributableESet;
	}

	/**
	 *
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__VERSION, oldVersion, version));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getContexts() {
		if (contexts == null) {
			contexts = new EObjectContainmentWithInverseEList(ContextParam.class, this, WebapplicationPackage.WEB_APP__CONTEXTS, WebapplicationPackage.CONTEXT_PARAM__WEB_APP);
		}
		return contexts;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getErrorPages() {
		if (errorPages == null) {
			errorPages = new EObjectContainmentWithInverseEList(ErrorPage.class, this, WebapplicationPackage.WEB_APP__ERROR_PAGES, WebapplicationPackage.ERROR_PAGE__WEB_APP);
		}
		return errorPages;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public WelcomeFileList getFileList() {
		return fileList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFileList(WelcomeFileList newFileList, NotificationChain msgs) {
		WelcomeFileList oldFileList = fileList;
		fileList = newFileList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__FILE_LIST, oldFileList, newFileList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setFileList(WelcomeFileList newFileList) {
		if (newFileList != fileList) {
			NotificationChain msgs = null;
			if (fileList != null)
				msgs = ((InternalEObject)fileList).eInverseRemove(this, WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP, WelcomeFileList.class, msgs);
			if (newFileList != null)
				msgs = ((InternalEObject)newFileList).eInverseAdd(this, WebapplicationPackage.WELCOME_FILE_LIST__WEB_APP, WelcomeFileList.class, msgs);
			msgs = basicSetFileList(newFileList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__FILE_LIST, newFileList, newFileList));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList<TagLibRef> getTagLibs() {
		if (tagLibs == null) {
			tagLibs = new EObjectContainmentWithInverseEList(TagLibRef.class, this, WebapplicationPackage.WEB_APP__TAG_LIBS, WebapplicationPackage.TAG_LIB_REF__WEB_APP);
		}
		return tagLibs;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentWithInverseEList(SecurityConstraint.class, this, WebapplicationPackage.WEB_APP__CONSTRAINTS, WebapplicationPackage.SECURITY_CONSTRAINT__WEB_APP);
		}
		return constraints;
	}

	/**
	* This returns the module version id. Compare with J2EEVersionConstants to determine module level
	*/
	
	@Override
	public int getVersionID() throws IllegalStateException {
		J2EEVersionResource res = (J2EEVersionResource) eResource();
		if (res == null) {
			// fix defect 3276, when resource is unloaded
			if (version == null) 
				throw new IllegalStateException();
			
			if (version.equals("2.4"))//$NON-NLS-1$
				return J2EEVersionConstants.WEB_2_4_ID;
			if (version.equals("2.3"))//$NON-NLS-1$
				return J2EEVersionConstants.WEB_2_3_ID;
			if (version.equals("2.2"))//$NON-NLS-1$
				return J2EEVersionConstants.WEB_2_2_ID;
			throw new IllegalStateException();
		}
		return res.getModuleVersionID();
	}

	/**
	 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
	 */
	@Override
	public int getJ2EEVersionID() throws IllegalStateException {
		J2EEVersionResource res = (J2EEVersionResource) eResource();
		if (res == null) throw new IllegalStateException();
		return res.getJ2EEVersionID();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public LoginConfig getLoginConfig() {
		return loginConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoginConfig(LoginConfig newLoginConfig, NotificationChain msgs) {
		LoginConfig oldLoginConfig = loginConfig;
		loginConfig = newLoginConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__LOGIN_CONFIG, oldLoginConfig, newLoginConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setLoginConfig(LoginConfig newLoginConfig) {
		if (newLoginConfig != loginConfig) {
			NotificationChain msgs = null;
			if (loginConfig != null)
				msgs = ((InternalEObject)loginConfig).eInverseRemove(this, WebapplicationPackage.LOGIN_CONFIG__WEB_APP, LoginConfig.class, msgs);
			if (newLoginConfig != null)
				msgs = ((InternalEObject)newLoginConfig).eInverseAdd(this, WebapplicationPackage.LOGIN_CONFIG__WEB_APP, LoginConfig.class, msgs);
			msgs = basicSetLoginConfig(newLoginConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__LOGIN_CONFIG, newLoginConfig, newLoginConfig));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getMimeMappings() {
		if (mimeMappings == null) {
			mimeMappings = new EObjectContainmentWithInverseEList(MimeMapping.class, this, WebapplicationPackage.WEB_APP__MIME_MAPPINGS, WebapplicationPackage.MIME_MAPPING__WEB_APP);
		}
		return mimeMappings;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public SessionConfig getSessionConfig() {
		return sessionConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSessionConfig(SessionConfig newSessionConfig, NotificationChain msgs) {
		SessionConfig oldSessionConfig = sessionConfig;
		sessionConfig = newSessionConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__SESSION_CONFIG, oldSessionConfig, newSessionConfig);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setSessionConfig(SessionConfig newSessionConfig) {
		if (newSessionConfig != sessionConfig) {
			NotificationChain msgs = null;
			if (sessionConfig != null)
				msgs = ((InternalEObject)sessionConfig).eInverseRemove(this, WebapplicationPackage.SESSION_CONFIG__WEB_APP, SessionConfig.class, msgs);
			if (newSessionConfig != null)
				msgs = ((InternalEObject)newSessionConfig).eInverseAdd(this, WebapplicationPackage.SESSION_CONFIG__WEB_APP, SessionConfig.class, msgs);
			msgs = basicSetSessionConfig(newSessionConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__SESSION_CONFIG, newSessionConfig, newSessionConfig));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getServletMappings() {
		if (servletMappings == null) {
			servletMappings = new EObjectContainmentWithInverseEList(ServletMapping.class, this, WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS, WebapplicationPackage.SERVLET_MAPPING__WEB_APP);
		}
		return servletMappings;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList<Servlet> getServlets() {
		if (servlets == null) {
			servlets = new EObjectContainmentWithInverseEList(Servlet.class, this, WebapplicationPackage.WEB_APP__SERVLETS, WebapplicationPackage.SERVLET__WEB_APP);
		}
		return servlets;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList(SecurityRole.class, this, WebapplicationPackage.WEB_APP__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public EList getFilters() {
		if (filters == null) {
			filters = new EObjectContainmentEList(Filter.class, this, WebapplicationPackage.WEB_APP__FILTERS);
		}
		return filters;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Filter mappings defined for the web app
	 */
	@Override
	public EList getFilterMappings() {
		if (filterMappings == null) {
			filterMappings = new EObjectContainmentEList(FilterMapping.class, this, WebapplicationPackage.WEB_APP__FILTER_MAPPINGS);
		}
		return filterMappings;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The listeners collection contains deployment properties for a web application
	 * listener beans in the web app.
	 */
	@Override
	public EList getListeners() {
		if (listeners == null) {
			listeners = new EObjectContainmentEList(Listener.class, this, WebapplicationPackage.WEB_APP__LISTENERS);
		}
		return listeners;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getContextParams() {
		if (contextParams == null) {
			contextParams = new EObjectContainmentEList(ParamValue.class, this, WebapplicationPackage.WEB_APP__CONTEXT_PARAMS);
		}
		return contextParams;
	}

	/**
	 * @deprecated: Use getEnvironmentProperties()
	 */
	@Override
	public EList getEnvEntries() {
		
		return getEnvironmentProperties();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JSPConfig getJspConfig() {
		return jspConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJspConfig(JSPConfig newJspConfig, NotificationChain msgs) {
		JSPConfig oldJspConfig = jspConfig;
		jspConfig = newJspConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__JSP_CONFIG, oldJspConfig, newJspConfig);
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
	public void setJspConfig(JSPConfig newJspConfig) {
		if (newJspConfig != jspConfig) {
			NotificationChain msgs = null;
			if (jspConfig != null)
				msgs = ((InternalEObject)jspConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__JSP_CONFIG, null, msgs);
			if (newJspConfig != null)
				msgs = ((InternalEObject)newJspConfig).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__JSP_CONFIG, null, msgs);
			msgs = basicSetJspConfig(newJspConfig, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__JSP_CONFIG, newJspConfig, newJspConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getMessageDestinations() {
		if (messageDestinations == null) {
			messageDestinations = new EObjectContainmentEList(MessageDestination.class, this, WebapplicationPackage.WEB_APP__MESSAGE_DESTINATIONS);
		}
		return messageDestinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocalEncodingMappingList getLocalEncodingMappingList() {
		return localEncodingMappingList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLocalEncodingMappingList(LocalEncodingMappingList newLocalEncodingMappingList, NotificationChain msgs) {
		LocalEncodingMappingList oldLocalEncodingMappingList = localEncodingMappingList;
		localEncodingMappingList = newLocalEncodingMappingList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST, oldLocalEncodingMappingList, newLocalEncodingMappingList);
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
	public void setLocalEncodingMappingList(LocalEncodingMappingList newLocalEncodingMappingList) {
		if (newLocalEncodingMappingList != localEncodingMappingList) {
			NotificationChain msgs = null;
			if (localEncodingMappingList != null)
				msgs = ((InternalEObject)localEncodingMappingList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST, null, msgs);
			if (newLocalEncodingMappingList != null)
				msgs = ((InternalEObject)newLocalEncodingMappingList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST, null, msgs);
			msgs = basicSetLocalEncodingMappingList(newLocalEncodingMappingList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST, newLocalEncodingMappingList, newLocalEncodingMappingList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebapplicationPackage.WEB_APP__CONTEXTS:
				return ((InternalEList)getContexts()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__ERROR_PAGES:
				return ((InternalEList)getErrorPages()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__FILE_LIST:
				if (fileList != null)
					msgs = ((InternalEObject)fileList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__FILE_LIST, null, msgs);
				return basicSetFileList((WelcomeFileList)otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__TAG_LIBS:
				return ((InternalEList)getTagLibs()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__CONSTRAINTS:
				return ((InternalEList)getConstraints()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__LOGIN_CONFIG:
				if (loginConfig != null)
					msgs = ((InternalEObject)loginConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__LOGIN_CONFIG, null, msgs);
				return basicSetLoginConfig((LoginConfig)otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__MIME_MAPPINGS:
				return ((InternalEList)getMimeMappings()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__SESSION_CONFIG:
				if (sessionConfig != null)
					msgs = ((InternalEObject)sessionConfig).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebapplicationPackage.WEB_APP__SESSION_CONFIG, null, msgs);
				return basicSetSessionConfig((SessionConfig)otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS:
				return ((InternalEList)getServletMappings()).basicAdd(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__SERVLETS:
				return ((InternalEList)getServlets()).basicAdd(otherEnd, msgs);
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
			case WebapplicationPackage.WEB_APP__CONTEXTS:
				return ((InternalEList)getContexts()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__ERROR_PAGES:
				return ((InternalEList)getErrorPages()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__FILE_LIST:
				return basicSetFileList(null, msgs);
			case WebapplicationPackage.WEB_APP__TAG_LIBS:
				return ((InternalEList)getTagLibs()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__CONSTRAINTS:
				return ((InternalEList)getConstraints()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__LOGIN_CONFIG:
				return basicSetLoginConfig(null, msgs);
			case WebapplicationPackage.WEB_APP__MIME_MAPPINGS:
				return ((InternalEList)getMimeMappings()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__SESSION_CONFIG:
				return basicSetSessionConfig(null, msgs);
			case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS:
				return ((InternalEList)getServletMappings()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__SERVLETS:
				return ((InternalEList)getServlets()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__SECURITY_ROLES:
				return ((InternalEList)getSecurityRoles()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__FILTERS:
				return ((InternalEList)getFilters()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__FILTER_MAPPINGS:
				return ((InternalEList)getFilterMappings()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__LISTENERS:
				return ((InternalEList)getListeners()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__CONTEXT_PARAMS:
				return ((InternalEList)getContextParams()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__JSP_CONFIG:
				return basicSetJspConfig(null, msgs);
			case WebapplicationPackage.WEB_APP__MESSAGE_DESTINATIONS:
				return ((InternalEList)getMessageDestinations()).basicRemove(otherEnd, msgs);
			case WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST:
				return basicSetLocalEncodingMappingList(null, msgs);
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
			case WebapplicationPackage.WEB_APP__DISTRIBUTABLE:
				return isDistributable() ? Boolean.TRUE : Boolean.FALSE;
			case WebapplicationPackage.WEB_APP__VERSION:
				return getVersion();
			case WebapplicationPackage.WEB_APP__CONTEXTS:
				return getContexts();
			case WebapplicationPackage.WEB_APP__ERROR_PAGES:
				return getErrorPages();
			case WebapplicationPackage.WEB_APP__FILE_LIST:
				return getFileList();
			case WebapplicationPackage.WEB_APP__TAG_LIBS:
				return getTagLibs();
			case WebapplicationPackage.WEB_APP__CONSTRAINTS:
				return getConstraints();
			case WebapplicationPackage.WEB_APP__LOGIN_CONFIG:
				return getLoginConfig();
			case WebapplicationPackage.WEB_APP__MIME_MAPPINGS:
				return getMimeMappings();
			case WebapplicationPackage.WEB_APP__SESSION_CONFIG:
				return getSessionConfig();
			case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS:
				return getServletMappings();
			case WebapplicationPackage.WEB_APP__SERVLETS:
				return getServlets();
			case WebapplicationPackage.WEB_APP__SECURITY_ROLES:
				return getSecurityRoles();
			case WebapplicationPackage.WEB_APP__FILTERS:
				return getFilters();
			case WebapplicationPackage.WEB_APP__FILTER_MAPPINGS:
				return getFilterMappings();
			case WebapplicationPackage.WEB_APP__LISTENERS:
				return getListeners();
			case WebapplicationPackage.WEB_APP__CONTEXT_PARAMS:
				return getContextParams();
			case WebapplicationPackage.WEB_APP__JSP_CONFIG:
				return getJspConfig();
			case WebapplicationPackage.WEB_APP__MESSAGE_DESTINATIONS:
				return getMessageDestinations();
			case WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST:
				return getLocalEncodingMappingList();
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
			case WebapplicationPackage.WEB_APP__DISTRIBUTABLE:
				setDistributable(((Boolean)newValue).booleanValue());
				return;
			case WebapplicationPackage.WEB_APP__VERSION:
				setVersion((String)newValue);
				return;
			case WebapplicationPackage.WEB_APP__CONTEXTS:
				getContexts().clear();
				getContexts().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__ERROR_PAGES:
				getErrorPages().clear();
				getErrorPages().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__FILE_LIST:
				setFileList((WelcomeFileList)newValue);
				return;
			case WebapplicationPackage.WEB_APP__TAG_LIBS:
				getTagLibs().clear();
				getTagLibs().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__LOGIN_CONFIG:
				setLoginConfig((LoginConfig)newValue);
				return;
			case WebapplicationPackage.WEB_APP__MIME_MAPPINGS:
				getMimeMappings().clear();
				getMimeMappings().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__SESSION_CONFIG:
				setSessionConfig((SessionConfig)newValue);
				return;
			case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS:
				getServletMappings().clear();
				getServletMappings().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__SERVLETS:
				getServlets().clear();
				getServlets().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__FILTERS:
				getFilters().clear();
				getFilters().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__FILTER_MAPPINGS:
				getFilterMappings().clear();
				getFilterMappings().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__LISTENERS:
				getListeners().clear();
				getListeners().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__CONTEXT_PARAMS:
				getContextParams().clear();
				getContextParams().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__JSP_CONFIG:
				setJspConfig((JSPConfig)newValue);
				return;
			case WebapplicationPackage.WEB_APP__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				getMessageDestinations().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST:
				setLocalEncodingMappingList((LocalEncodingMappingList)newValue);
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
			case WebapplicationPackage.WEB_APP__DISTRIBUTABLE:
				unsetDistributable();
				return;
			case WebapplicationPackage.WEB_APP__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case WebapplicationPackage.WEB_APP__CONTEXTS:
				getContexts().clear();
				return;
			case WebapplicationPackage.WEB_APP__ERROR_PAGES:
				getErrorPages().clear();
				return;
			case WebapplicationPackage.WEB_APP__FILE_LIST:
				setFileList((WelcomeFileList)null);
				return;
			case WebapplicationPackage.WEB_APP__TAG_LIBS:
				getTagLibs().clear();
				return;
			case WebapplicationPackage.WEB_APP__CONSTRAINTS:
				getConstraints().clear();
				return;
			case WebapplicationPackage.WEB_APP__LOGIN_CONFIG:
				setLoginConfig((LoginConfig)null);
				return;
			case WebapplicationPackage.WEB_APP__MIME_MAPPINGS:
				getMimeMappings().clear();
				return;
			case WebapplicationPackage.WEB_APP__SESSION_CONFIG:
				setSessionConfig((SessionConfig)null);
				return;
			case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS:
				getServletMappings().clear();
				return;
			case WebapplicationPackage.WEB_APP__SERVLETS:
				getServlets().clear();
				return;
			case WebapplicationPackage.WEB_APP__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case WebapplicationPackage.WEB_APP__FILTERS:
				getFilters().clear();
				return;
			case WebapplicationPackage.WEB_APP__FILTER_MAPPINGS:
				getFilterMappings().clear();
				return;
			case WebapplicationPackage.WEB_APP__LISTENERS:
				getListeners().clear();
				return;
			case WebapplicationPackage.WEB_APP__CONTEXT_PARAMS:
				getContextParams().clear();
				return;
			case WebapplicationPackage.WEB_APP__JSP_CONFIG:
				setJspConfig((JSPConfig)null);
				return;
			case WebapplicationPackage.WEB_APP__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				return;
			case WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST:
				setLocalEncodingMappingList((LocalEncodingMappingList)null);
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
			case WebapplicationPackage.WEB_APP__DISTRIBUTABLE:
				return isSetDistributable();
			case WebapplicationPackage.WEB_APP__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case WebapplicationPackage.WEB_APP__CONTEXTS:
				return contexts != null && !contexts.isEmpty();
			case WebapplicationPackage.WEB_APP__ERROR_PAGES:
				return errorPages != null && !errorPages.isEmpty();
			case WebapplicationPackage.WEB_APP__FILE_LIST:
				return fileList != null;
			case WebapplicationPackage.WEB_APP__TAG_LIBS:
				return tagLibs != null && !tagLibs.isEmpty();
			case WebapplicationPackage.WEB_APP__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case WebapplicationPackage.WEB_APP__LOGIN_CONFIG:
				return loginConfig != null;
			case WebapplicationPackage.WEB_APP__MIME_MAPPINGS:
				return mimeMappings != null && !mimeMappings.isEmpty();
			case WebapplicationPackage.WEB_APP__SESSION_CONFIG:
				return sessionConfig != null;
			case WebapplicationPackage.WEB_APP__SERVLET_MAPPINGS:
				return servletMappings != null && !servletMappings.isEmpty();
			case WebapplicationPackage.WEB_APP__SERVLETS:
				return servlets != null && !servlets.isEmpty();
			case WebapplicationPackage.WEB_APP__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case WebapplicationPackage.WEB_APP__FILTERS:
				return filters != null && !filters.isEmpty();
			case WebapplicationPackage.WEB_APP__FILTER_MAPPINGS:
				return filterMappings != null && !filterMappings.isEmpty();
			case WebapplicationPackage.WEB_APP__LISTENERS:
				return listeners != null && !listeners.isEmpty();
			case WebapplicationPackage.WEB_APP__CONTEXT_PARAMS:
				return contextParams != null && !contextParams.isEmpty();
			case WebapplicationPackage.WEB_APP__JSP_CONFIG:
				return jspConfig != null;
			case WebapplicationPackage.WEB_APP__MESSAGE_DESTINATIONS:
				return messageDestinations != null && !messageDestinations.isEmpty();
			case WebapplicationPackage.WEB_APP__LOCAL_ENCODING_MAPPING_LIST:
				return localEncodingMappingList != null;
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
		result.append(" (distributable: "); //$NON-NLS-1$
		if (distributableESet) result.append(distributable); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

}














