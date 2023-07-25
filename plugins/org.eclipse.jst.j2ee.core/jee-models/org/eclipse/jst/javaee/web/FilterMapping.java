/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.UrlPatternType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         Declaration of the filter mappings in this web
 *         application is done by using filter-mappingType. 
 *         The container uses the filter-mapping
 *         declarations to decide which filters to apply to a request,
 *         and in what order. The container matches the request URI to
 *         a Servlet in the normal way. To determine which filters to
 *         apply it matches filter-mapping declarations either on
 *         servlet-name, or on url-pattern for each filter-mapping
 *         element, depending on which style is used. The order in
 *         which filters are invoked is the order in which
 *         filter-mapping declarations that match a request URI for a
 *         servlet appear in the list of filter-mapping elements.The
 *         filter-name value must be the value of the filter-name
 *         sub-elements of one of the filter declarations in the
 *         deployment descriptor.
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.FilterMapping#getFilterName <em>Filter Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FilterMapping#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FilterMapping#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FilterMapping#getServletNames <em>Servlet Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FilterMapping#getDispatchers <em>Dispatchers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.FilterMapping#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping()
 * @extends JavaEEObject
 * @generated
 */
public interface FilterMapping extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Filter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Name</em>' attribute.
	 * @see #setFilterName(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping_FilterName()
	 * @generated
	 */
	String getFilterName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.FilterMapping#getFilterName <em>Filter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Name</em>' attribute.
	 * @see #getFilterName()
	 * @generated
	 */
	void setFilterName(String value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping_Group()
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Url Patterns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.UrlPatternType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url Patterns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url Patterns</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping_UrlPatterns()
	 * @generated
	 */
	List<UrlPatternType> getUrlPatterns();

	/**
	 * Returns the value of the '<em><b>Servlet Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servlet Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servlet Names</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping_ServletNames()
	 * @generated
	 */
	List<String> getServletNames();

	/**
	 * Returns the value of the '<em><b>Dispatchers</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.DispatcherType}.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.web.DispatcherType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dispatchers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dispatchers</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.DispatcherType
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping_Dispatchers()
	 * @generated
	 */
	List<DispatcherType> getDispatchers();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilterMapping_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.FilterMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // FilterMapping