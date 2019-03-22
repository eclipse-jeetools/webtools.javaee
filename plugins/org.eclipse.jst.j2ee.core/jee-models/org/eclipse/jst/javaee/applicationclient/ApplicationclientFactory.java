/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.applicationclient;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage
 * @generated
 */
public interface ApplicationclientFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationclientFactory eINSTANCE = org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Application Client</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Client</em>'.
	 * @generated
	 */
	ApplicationClient createApplicationClient();

	/**
	 * Returns a new object of class '<em>Application Client Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Client Deployment Descriptor</em>'.
	 * @generated
	 */
	ApplicationClientDeploymentDescriptor createApplicationClientDeploymentDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ApplicationclientPackage getApplicationclientPackage();

} //ApplicationclientFactory
