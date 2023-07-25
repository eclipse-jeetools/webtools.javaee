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
package org.eclipse.jst.javaee.application;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The webType defines the web-uri and context-root of
 *         a web application module.
 *         
 *         @since Java EE 5, Application 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.Web#getWebUri <em>Web Uri</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Web#getContextRoot <em>Context Root</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Web#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getWeb()
 * @extends JavaEEObject
 * @generated
 */
public interface Web extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Web Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The web-uri element specifies the URI of a web
	 *             application file, relative to the top level of the
	 *             application package.
	 *             
	 *             @since Java EE 5, Application 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Web Uri</em>' attribute.
	 * @see #setWebUri(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getWeb_WebUri()
	 * @generated
	 */
	String getWebUri();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Web#getWebUri <em>Web Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Web Uri</em>' attribute.
	 * @see #getWebUri()
	 * @generated
	 */
	void setWebUri(String value);

	/**
	 * Returns the value of the '<em><b>Context Root</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The context-root element specifies the context root
	 *             of a web application.
	 *             
	 *             @since Java EE 5, Application 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Context Root</em>' attribute.
	 * @see #setContextRoot(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getWeb_ContextRoot()
	 * @generated
	 */
	String getContextRoot();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Web#getContextRoot <em>Context Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context Root</em>' attribute.
	 * @see #getContextRoot()
	 * @generated
	 */
	void setContextRoot(String value);

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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getWeb_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Web#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Web