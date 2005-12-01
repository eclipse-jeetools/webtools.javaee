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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal;



import org.eclipse.emf.ecore.EObject;


public interface LooseArchive extends EObject {
	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the Uri attribute
	 */
	String getUri();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param value
	 *            The new value of the Uri attribute
	 */
	void setUri(String value);

	boolean isEAR();

	boolean isWAR();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the BinariesPath attribute
	 */
	String getBinariesPath();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param value
	 *            The new value of the BinariesPath attribute
	 */
	void setBinariesPath(String value);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the ResourcesPath attribute
	 */
	String getResourcesPath();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param value
	 *            The new value of the ResourcesPath attribute
	 */
	void setResourcesPath(String value);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The LooseApp reference
	 */
	LooseApplication getLooseApp();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the LooseApp reference
	 */
	void setLooseApp(LooseApplication value);

	boolean isModule();

} //LooseArchive



