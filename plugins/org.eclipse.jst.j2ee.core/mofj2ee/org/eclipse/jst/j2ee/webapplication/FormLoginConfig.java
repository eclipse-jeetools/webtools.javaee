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

import org.eclipse.emf.ecore.EObject;
/**
 * The form-login-config element specifies the login and error pages that should be used in form based login. If form based authentication is not used, these elements are ignored.
 */
public interface FormLoginConfig extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the FormLoginPage attribute
	 * The form-login-page element defines the location in the web app where the page
	 * that can be used for login can be found
	 */
	String getFormLoginPage();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the FormLoginPage attribute
	 */
	void setFormLoginPage(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the FormErrorPage attribute
	 * The form-error-page element defines the location in the web app where the error 
	 * page that is displayed when login is not successful can be found
	 */
	String getFormErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the FormErrorPage attribute
	 */
	void setFormErrorPage(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The LoginConfig reference
	 */
	LoginConfig getLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the LoginConfig reference
	 */
	void setLoginConfig(LoginConfig value);

}














