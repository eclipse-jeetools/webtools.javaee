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

import java.util.List;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The session-configType defines the session parameters
 *         for this web application.
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
 *   <li>{@link org.eclipse.jst.javaee.web.SessionConfig#getSessionTimeout <em>Session Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SessionConfig#getCookieConfig <em>Cookie Config</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SessionConfig#getTrackingMode <em>Tracking Mode</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SessionConfig#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSessionConfig()
 * @extends JavaEEObject
 * @generated
 */
public interface SessionConfig extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Session Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The session-timeout element defines the default
	 *             session timeout interval for all sessions created
	 *             in this web application. The specified timeout
	 *             must be expressed in a whole number of minutes.
	 *             If the timeout is 0 or less, the container ensures
	 *             the default behaviour of sessions is never to time
	 *             out. If this element is not specified, the container
	 *             must set its default timeout period.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Session Timeout</em>' attribute.
	 * @see #setSessionTimeout(BigInteger)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSessionConfig_SessionTimeout()
	 * @generated
	 */
	BigInteger getSessionTimeout();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.SessionConfig#getSessionTimeout <em>Session Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Session Timeout</em>' attribute.
	 * @see #getSessionTimeout()
	 * @generated
	 */
	void setSessionTimeout(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cookie Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The cookie-config element defines the configuration of the
	 *             session tracking cookies created by this web application.
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cookie Config</em>' containment reference.
	 * @see #setCookieConfig(CookieConfigType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSessionConfig_CookieConfig()
	 * @generated
	 */
	CookieConfigType getCookieConfig();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.SessionConfig#getCookieConfig <em>Cookie Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cookie Config</em>' containment reference.
	 * @see #getCookieConfig()
	 * @generated
	 */
	void setCookieConfig(CookieConfigType value);

	/**
	 * Returns the value of the '<em><b>Tracking Mode</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.TrackingModeType}.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.web.TrackingModeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The tracking-mode element defines the tracking modes
	 *             for sessions created by this web application
	 *             
	 *             @since Java EE 6, Web 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tracking Mode</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.TrackingModeType
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSessionConfig_TrackingMode()
	 * @generated
	 */
	List<TrackingModeType> getTrackingMode();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSessionConfig_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.SessionConfig#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SessionConfig