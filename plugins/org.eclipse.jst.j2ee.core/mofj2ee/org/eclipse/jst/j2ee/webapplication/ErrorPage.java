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
 * The error-page element contains a mapping between an error code or exception type to the path of a resource in the web application
 */
public interface ErrorPage extends EObject{

public boolean isErrorCode();
public boolean isExceptionType();
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Location attribute
	 * The location element contains the location of the resource in the web
	 * application
	 */
	String getLocation();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Location attribute
	 */
	void setLocation(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The WebApp reference
	 */
	WebApp getWebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the WebApp reference
	 */
	void setWebApp(WebApp value);

}














