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
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
/**
 * Declaration of the filter mappings in this web application. The container uses the filter-mapping declarations to decide which filters to apply to a request, and in what order. The container matches the request URI to a Servlet in the normal way. To determine which filters to apply it matches filter-mapping declarations either on servlet-name, or on url-pattern for each filter-mapping element, depending on which style is used. The order in which filters are invoked is the order in which filter-mapping declarations that match a request URI for a servlet appear in the list of filter-mapping elements.The filter-name value must be the value of the <filtername> sub-elements of one of the <filter> declarations in the deployment descriptor.
 * 
 * @invariant The filter-name value must be the value of the Filter::name of one of the Filter declarations in the deployment descriptor.

 */
public interface FilterMapping extends EObject{

/**
 * Returns the name of the servlet that this mapping applies to.
 */
public String getServletName();
/**
 * Sets the name of the servlet that this mapping applies to.
 */
public void setServletName(String servletName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the UrlPattern attribute
	 * The url-pattern element contains the url pattern of the mapping. Must follow
	 * the rules specified in Section 10 of the Servlet API Specification.
	 */
	String getUrlPattern();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the UrlPattern attribute
	 */
	void setUrlPattern(String value);

	/**
	 * Returns the value of the '<em><b>Dispatcher Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webapplication.DispatcherType}.
	 * The literals are from the enumeration {@link org.eclipse.jst.j2ee.internal.webapplication.DispatcherType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dispatcher Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dispatcher Type</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.DispatcherType
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getFilterMapping_DispatcherType()
	 * @model type="org.eclipse.jst.j2ee.internal.webapplication.DispatcherType" upper="4"
	 * @generated
	 */
	EList getDispatcherType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Filter reference
	 * The filter being mapped
	 */
	Filter getFilter();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Filter reference
	 */
	void setFilter(Filter value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Servlet reference
	 * An optional servlet that is mapped by a filter mapping.  servletName is used to 
	 * resolve the servlet reference.
	 */
	Servlet getServlet();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Servlet reference
	 */
	void setServlet(Servlet value);

}














