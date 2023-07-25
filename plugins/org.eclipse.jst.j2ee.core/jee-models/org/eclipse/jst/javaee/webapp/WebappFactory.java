/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.webapp;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.webapp.internal.metadata.WebappPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.webapp.internal.metadata.WebappPackage
 * @generated
 */
public interface WebappFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebappFactory eINSTANCE = org.eclipse.jst.javaee.webapp.internal.impl.WebappFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Web App Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web App Deployment Descriptor</em>'.
	 * @generated
	 */
	WebAppDeploymentDescriptor createWebAppDeploymentDescriptor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WebappPackage getWebappPackage();

} //WebappFactory
