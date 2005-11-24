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
package org.eclipse.jst.j2ee.application;

import org.eclipse.emf.ecore.EObject;
/**
 * The module element represents a single J2EE module and contains an ejb, java, or web element, which indicates the module type and contains a path to the module file, and an optional alt-dd element, which specifies an optional URI to the post-assembly version of the deployment descriptor. The application deployment descriptor must have one module element for each J2EE module in the application package.

 * @since 1.0 */
public interface Module extends EObject{

public boolean isConnectorModule();
public boolean isEjbModule();
public boolean isJavaModule();
public boolean isWebModule();
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Uri attribute
	 * Specifies the URI of a module, relative to the top level of the application
	 * package.

	 */
	String getUri();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Uri attribute
	 */
	void setUri(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the AltDD attribute
	 * The alt-dd element specifies an optional URI to the post-assembly version of
	 * the deployment descriptor file for a particular J2EE module. The URI must
	 * specify the full pathname of the deployment descriptor file relative to the
	 * application's root directory. If alt-dd is not specified, the deployer must
	 * read the deployment descriptor from the default location and file name required 
	 * by the respective component specification.

	 */
	String getAltDD();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the AltDD attribute
	 */
	void setAltDD(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Application reference
	 */
	Application getApplication();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Application reference
	 */
	void setApplication(Application value);

}





