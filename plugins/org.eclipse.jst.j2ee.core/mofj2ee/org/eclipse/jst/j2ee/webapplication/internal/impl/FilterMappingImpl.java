/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * Declaration of the filter mappings in this web application. The container uses the filter-mapping declarations to decide which filters to apply to a request, and in what order. The container matches the request URI to a Servlet in the normal way. To determine which filters to apply it matches filter-mapping declarations either on servlet-name, or on url-pattern for each filter-mapping element, depending on which style is used. The order in which filters are invoked is the order in which filter-mapping declarations that match a request URI for a servlet appear in the list of filter-mapping elements.The filter-name value must be the value of the <filtername> sub-elements of one of the <filter> declarations in the deployment descriptor.
 * 
 * @invariant The filter-name value must be the value of the Filter::name of one of the Filter declarations in the deployment descriptor.
 * @generated
 */
public class FilterMappingImpl extends J2EEEObjectImpl implements FilterMapping {

	/**
	 * The default value of the '{@link #getUrlPattern() <em>Url Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_PATTERN_EDEFAULT = null;

	protected transient String servletName;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String urlPattern = URL_PATTERN_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDispatcherType() <em>Dispatcher Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDispatcherType()
	 * @generated
	 * @ordered
	 */
	protected EList dispatcherType = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Filter filter = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Servlet servlet = null;
	public FilterMappingImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebapplicationPackage.Literals.FILTER_MAPPING;
	}

/**
 * Returns the name of the servlet that this mapping applies to.
 */
	public Servlet getServlet(){
	Servlet lservlet = this.getServletGen();
	if (lservlet == null && servletName != null){
		WebApp webapp = (WebApp)eContainer();
		if (webapp != null){
			lservlet=webapp.getServletNamed(servletName);
			if (lservlet != null) 
				setServlet(lservlet);
		}
	}
	return lservlet;
}
/**
 * Returns the name of the servlet that this mapping applies to.
 */
public String getServletName(){
	String ret = null;
	if (servletName != null)
		ret =  servletName;

	Servlet lservlet = getServlet();
	if (lservlet != null)
		ret = lservlet.getServletName();
	return ret;
}
/**
 * Sets the name of the servlet that this mapping applies to.
 */
public void setServletName(String servletName){
	this.servletName = servletName;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The url-pattern element contains the url pattern of the mapping. Must follow
	 * the rules specified in Section 10 of the Servlet API Specification.
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUrlPattern(String newUrlPattern) {
		String oldUrlPattern = urlPattern;
		urlPattern = newUrlPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FILTER_MAPPING__URL_PATTERN, oldUrlPattern, urlPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDispatcherType() {
		if (dispatcherType == null) {
			dispatcherType = new EDataTypeUniqueEList(DispatcherType.class, this, WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE);
		}
		return dispatcherType;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The filter being mapped
	 */
	public Filter getFilter() {
		if (filter != null && filter.eIsProxy()) {
			InternalEObject oldFilter = (InternalEObject)filter;
			filter = (Filter)eResolveProxy(oldFilter);
			if (filter != oldFilter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebapplicationPackage.FILTER_MAPPING__FILTER, oldFilter, filter));
			}
		}
		return filter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Filter basicGetFilter() {
		return filter;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setFilter(Filter newFilter) {
		Filter oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FILTER_MAPPING__FILTER, oldFilter, filter));
	}

	public void setServlet(Servlet l) {
		servletName = null;
		setServletGen(l);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setServletGen(Servlet newServlet) {
		Servlet oldServlet = servlet;
		servlet = newServlet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.FILTER_MAPPING__SERVLET, oldServlet, servlet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebapplicationPackage.FILTER_MAPPING__URL_PATTERN:
				return getUrlPattern();
			case WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE:
				return getDispatcherType();
			case WebapplicationPackage.FILTER_MAPPING__FILTER:
				if (resolve) return getFilter();
				return basicGetFilter();
			case WebapplicationPackage.FILTER_MAPPING__SERVLET:
				if (resolve) return getServlet();
				return basicGetServlet();
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
			case WebapplicationPackage.FILTER_MAPPING__URL_PATTERN:
				setUrlPattern((String)newValue);
				return;
			case WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE:
				getDispatcherType().clear();
				getDispatcherType().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.FILTER_MAPPING__FILTER:
				setFilter((Filter)newValue);
				return;
			case WebapplicationPackage.FILTER_MAPPING__SERVLET:
				setServlet((Servlet)newValue);
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
			case WebapplicationPackage.FILTER_MAPPING__URL_PATTERN:
				setUrlPattern(URL_PATTERN_EDEFAULT);
				return;
			case WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE:
				getDispatcherType().clear();
				return;
			case WebapplicationPackage.FILTER_MAPPING__FILTER:
				setFilter((Filter)null);
				return;
			case WebapplicationPackage.FILTER_MAPPING__SERVLET:
				setServlet((Servlet)null);
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
			case WebapplicationPackage.FILTER_MAPPING__URL_PATTERN:
				return URL_PATTERN_EDEFAULT == null ? urlPattern != null : !URL_PATTERN_EDEFAULT.equals(urlPattern);
			case WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE:
				return dispatcherType != null && !dispatcherType.isEmpty();
			case WebapplicationPackage.FILTER_MAPPING__FILTER:
				return filter != null;
			case WebapplicationPackage.FILTER_MAPPING__SERVLET:
				return servlet != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * (Removed Generated flag to preserve override This field/method will NOT be replaced during code generation.
	 */
	@Override
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.FILTER_MAPPING__URL_PATTERN:
				setUrlPattern((String)newValue);
				return;
			case WebapplicationPackage.FILTER_MAPPING__DISPATCHER_TYPE:
				getDispatcherType().clear();
				getDispatcherType().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.FILTER_MAPPING__FILTER:
				setFilter((Filter)newValue);
				return;
			case WebapplicationPackage.FILTER_MAPPING__SERVLET:
				if(newValue instanceof String)
	                setServletName((String)newValue);
	            else
	                setServlet((Servlet)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (urlPattern: "); //$NON-NLS-1$
		result.append(urlPattern);
		result.append(", dispatcherType: "); //$NON-NLS-1$
		result.append(dispatcherType);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * An optional servlet that is mapped by a filter mapping.  servletName is used to resolve the servlet reference.
	 */
	public Servlet getServletGen() {
		if (servlet != null && servlet.eIsProxy()) {
			InternalEObject oldServlet = (InternalEObject)servlet;
			servlet = (Servlet)eResolveProxy(oldServlet);
			if (servlet != oldServlet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WebapplicationPackage.FILTER_MAPPING__SERVLET, oldServlet, servlet));
			}
		}
		return servlet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Servlet basicGetServlet() {
		return servlet;
	}

}














