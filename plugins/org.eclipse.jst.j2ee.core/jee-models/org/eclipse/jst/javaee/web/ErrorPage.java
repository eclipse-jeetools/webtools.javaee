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

import java.math.BigInteger;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Error Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The error-pageType contains a mapping between an error code
 *         or exception type to the path of a resource in the web
 *         application.
 *         
 *         Error-page declarations using the exception-type element in
 *         the deployment descriptor must be unique up to the class name of
 *         the exception-type. Similarly, error-page declarations using the
 *         error-code element must be unique in the deployment descriptor
 *         up to the status code.
 *         
 *         If an error-page element in the deployment descriptor does not
 *         contain an exception-type or an error-code element, the error
 *         page is a default error page. 
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
 *   <li>{@link org.eclipse.jst.javaee.web.ErrorPage#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.ErrorPage#getExceptionType <em>Exception Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.ErrorPage#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.ErrorPage#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getErrorPage()
 * @extends JavaEEObject
 * @generated
 */
public interface ErrorPage extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Code</em>' attribute.
	 * @see #setErrorCode(BigInteger)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getErrorPage_ErrorCode()
	 * @generated
	 */
	BigInteger getErrorCode();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.ErrorPage#getErrorCode <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Code</em>' attribute.
	 * @see #getErrorCode()
	 * @generated
	 */
	void setErrorCode(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Exception Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               The exception-type contains a fully qualified class
	 *               name of a Java exception type.
	 *               
	 *               @since Java EE 5, Web 2.5
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Exception Type</em>' attribute.
	 * @see #setExceptionType(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getErrorPage_ExceptionType()
	 * @generated
	 */
	String getExceptionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.ErrorPage#getExceptionType <em>Exception Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Type</em>' attribute.
	 * @see #getExceptionType()
	 * @generated
	 */
	void setExceptionType(String value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The location element contains the location of the
	 *             resource in the web application relative to the root of
	 *             the web application. The value of the location must have
	 *             a leading `/'.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getErrorPage_Location()
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.ErrorPage#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getErrorPage_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.ErrorPage#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ErrorPage