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
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.ParamValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The filterType is used to declare a filter in the web
 *         application. The filter is mapped to either a servlet or a
 *         URL pattern in the filter-mapping element, using the
 *         filter-name value to reference. Filters can access the
 *         initialization parameters declared in the deployment
 *         descriptor at runtime via the FilterConfig interface.
 *         
 *         Used in: web-app
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getFilterName <em>Filter Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getFilterClass <em>Filter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#isAsyncSupported <em>Async Supported</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getInitParams <em>Init Params</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.Filter#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter()
 * @extends JavaEEObject
 * @generated
 */
public interface Filter extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_FilterName()
	 * @generated
	 */
	String getFilterName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Filter#getFilterName <em>Filter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Name</em>' attribute.
	 * @see #getFilterName()
	 * @generated
	 */
	void setFilterName(String value);

	/**
	 * Returns the value of the '<em><b>Filter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The fully qualified classname of the filter.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Filter Class</em>' attribute.
	 * @see #setFilterClass(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_FilterClass()
	 * @generated
	 */
	String getFilterClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Filter#getFilterClass <em>Filter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Class</em>' attribute.
	 * @see #getFilterClass()
	 * @generated
	 */
	void setFilterClass(String value);

	/**
	 * Returns the value of the '<em><b>Async Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Async Supported</em>' attribute.
	 * @see #isSetAsyncSupported()
	 * @see #unsetAsyncSupported()
	 * @see #setAsyncSupported(boolean)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_AsyncSupported()
	 * @generated
	 */
	boolean isAsyncSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Filter#isAsyncSupported <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Async Supported</em>' attribute.
	 * @see #isSetAsyncSupported()
	 * @see #unsetAsyncSupported()
	 * @see #isAsyncSupported()
	 * @generated
	 */
	void setAsyncSupported(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.Filter#isAsyncSupported <em>Async Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAsyncSupported()
	 * @see #isAsyncSupported()
	 * @see #setAsyncSupported(boolean)
	 * @generated
	 */
	void unsetAsyncSupported();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.Filter#isAsyncSupported <em>Async Supported</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Async Supported</em>' attribute is set.
	 * @see #unsetAsyncSupported()
	 * @see #isAsyncSupported()
	 * @see #setAsyncSupported(boolean)
	 * @generated
	 */
	boolean isSetAsyncSupported();

	/**
	 * Returns the value of the '<em><b>Init Params</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The init-param element contains a name/value pair as
	 *             an initialization param of a servlet filter
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init Params</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_InitParams()
	 * @generated
	 */
	List<ParamValue> getInitParams();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getFilter_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.Filter#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Filter