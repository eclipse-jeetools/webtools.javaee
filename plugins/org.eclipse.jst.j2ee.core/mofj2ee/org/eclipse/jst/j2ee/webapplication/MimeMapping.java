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
 * The mime-mapping element defines a mapping between an extension and a mime type.

 */
public interface MimeMapping extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Extension attribute
	 * The extension element contains a string describing an extension. example: "txt"
	 */
	String getExtension();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Extension attribute
	 */
	void setExtension(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the MimeType attribute
	 * The mime-type element contains a defined mime type. example: "text//plain"
	 */
	String getMimeType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the MimeType attribute
	 */
	void setMimeType(String value);

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














