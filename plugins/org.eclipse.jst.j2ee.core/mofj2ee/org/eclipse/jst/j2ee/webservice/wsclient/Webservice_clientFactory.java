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
package org.eclipse.jst.j2ee.webservice.wsclient;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage
 * @generated
 * @since 1.0 */
public interface Webservice_clientFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Webservice_clientFactory eINSTANCE = new org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.Webservice_clientFactoryImpl();

	/**
	 * Returns a new object of class '<em>Service Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref</em>'.
	 * @generated
	 */
	ServiceRef createServiceRef();

	/**
	 * Returns a new object of class '<em>Port Component Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Component Ref</em>'.
	 * @generated
	 */
	PortComponentRef createPortComponentRef();

	/**
	 * Returns a new object of class '<em>Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Handler</em>'.
	 * @generated
	 */
	Handler createHandler();

	/**
	 * Returns a new object of class '<em>Web Services Client</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web Services Client</em>'.
	 * @generated
	 */
    WebServicesClient createWebServicesClient();

	/**
	 * Returns a new object of class '<em>Component Scoped Refs</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Scoped Refs</em>'.
	 * @generated
	 */
    ComponentScopedRefs createComponentScopedRefs();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * This method is NOT api, and may change in future releases.  
	 * EMF package classes should be access via their "eINSTANCE" static fields
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Webservice_clientPackage getWebservice_clientPackage();

} //Webservice_clientFactory
