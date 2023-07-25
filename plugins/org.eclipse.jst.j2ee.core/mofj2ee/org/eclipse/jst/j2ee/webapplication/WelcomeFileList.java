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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.J2EEEObject;
/**
 * The welcome-file-list contains an ordered list of welcome files elements.
 * @generated
 * @since 1.0
 */
public interface WelcomeFileList extends J2EEEObject {

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














