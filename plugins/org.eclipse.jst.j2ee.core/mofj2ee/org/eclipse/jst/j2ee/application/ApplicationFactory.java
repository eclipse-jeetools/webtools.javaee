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

import org.eclipse.emf.ecore.EFactory;
/**
 * @generated
 * @since 1.0 */
public interface ApplicationFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationFactory eINSTANCE = new org.eclipse.jst.j2ee.application.internal.impl.ApplicationFactoryImpl();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Application value
	 */
	Application createApplication();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Module value
	 */
	Module createModule();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WebModule value
	 */
	WebModule createWebModule();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JavaClientModule value
	 */
	JavaClientModule createJavaClientModule();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EjbModule value
	 */
	EjbModule createEjbModule();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ConnectorModule value
	 */
	ConnectorModule createConnectorModule();

	/**
	 * <!-- begin-user-doc -->
	 * This method is NOT api, and may change in future releases.  
	 * EMF package classes should be access via their "eINSTANCE" static fields
	 * <!-- end-user-doc -->
	 * @generated This field/method will be replaced during code generation.
	 *
	 * @return EMF package class
	 */
	ApplicationPackage getApplicationPackage();

}






