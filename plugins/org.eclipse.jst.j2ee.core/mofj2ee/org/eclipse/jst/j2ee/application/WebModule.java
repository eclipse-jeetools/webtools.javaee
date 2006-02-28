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
package org.eclipse.jst.j2ee.application;


/**
 * Contains the web-uri and context-root of a web application module.

 * @since 1.0 */
public interface WebModule extends Module{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ContextRoot attribute
	 * The context-root element specifies the context root of a web application

	 */
	public String getContextRoot();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ContextRoot attribute
	 */
	public void setContextRoot(String value);

}





