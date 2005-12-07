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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
/**
 * The welcome-file-list contains an ordered list of welcome files elements.
 * @since 1.0
 */
public interface WelcomeFileList extends EObject{

	/**
	 * Adds the file whose name is specified in the parameter
	 * @param name the name of the file to be added
	 */
	public void addFileNamed(String name);
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

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of File references
	 */
	EList getFile();

}














