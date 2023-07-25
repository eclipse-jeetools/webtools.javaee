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

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.UrlPatternType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Servlet Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The servlet-mappingType defines a mapping between a
 *         servlet and a url pattern. 
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
 *   <li>{@link org.eclipse.jst.javaee.web.ServletMapping#getServletName <em>Servlet Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.ServletMapping#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.ServletMapping#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServletMapping()
 * @extends JavaEEObject
 * @generated
 */
public interface ServletMapping extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Servlet Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servlet Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servlet Name</em>' attribute.
	 * @see #setServletName(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServletMapping_ServletName()
	 * @generated
	 */
	String getServletName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.ServletMapping#getServletName <em>Servlet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Servlet Name</em>' attribute.
	 * @see #getServletName()
	 * @generated
	 */
	void setServletName(String value);

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServletMapping_UrlPatterns()
	 * @generated
	 */
	List<UrlPatternType> getUrlPatterns();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getServletMapping_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.ServletMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ServletMapping