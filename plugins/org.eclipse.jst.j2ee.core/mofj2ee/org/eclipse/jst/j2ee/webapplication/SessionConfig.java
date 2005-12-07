/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.ecore.EObject;
/**
 * The session-config element defines the session parameters for this web application.
 * @since 1.0
 */
public interface SessionConfig extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the SessionTimeout attribute
	 * The session-timeout element defines the default session timeout interval for
	 * all sessions created in this web application. The specified timeout must be
	 * expressed in a whole number of minutes.
	 */
	int getSessionTimeout();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the SessionTimeout attribute
	 */
	void setSessionTimeout(int value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.webapplication.SessionConfig#getSessionTimeout <em>Session Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isSetSessionTimeout()
	 * @see #getSessionTimeout()
	 * @see #setSessionTimeout(int)
	 * @generated
	 */
  void unsetSessionTimeout();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.webapplication.SessionConfig#getSessionTimeout <em>Session Timeout</em>}' attribute is set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Session Timeout</em>' attribute is set.
	 * @see #unsetSessionTimeout()
	 * @see #getSessionTimeout()
	 * @see #setSessionTimeout(int)
	 * @generated
	 */
  boolean isSetSessionTimeout();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The WebApp reference
	 */
	WebApp getWebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param lvalue The new value of the WebApp reference
	 */
	void setWebApp(WebApp value);

}














