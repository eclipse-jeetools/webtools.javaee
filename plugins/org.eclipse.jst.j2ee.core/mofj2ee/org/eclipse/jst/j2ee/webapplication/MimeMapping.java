/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.jst.j2ee.common.J2EEEObject;
/**
 * The mime-mapping element defines a mapping between an extension and a mime type.
 * @generated
 * @since 1.0
 */
public interface MimeMapping extends J2EEEObject {
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
	 * @param value The new value of the WebApp reference
	 */
	void setWebApp(WebApp value);

}














