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
 * The welcome-file element contains file name to use as a default welcome file, such as index.html
 * @since 1.0
 */
public interface WelcomeFile extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the WelcomeFile attribute
	 */
	String getWelcomeFile();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the WelcomeFile attribute
	 */
	void setWelcomeFile(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The FileList reference
	 */
	WelcomeFileList getFileList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the FileList reference
	 */
	void setFileList(WelcomeFileList value);

}














