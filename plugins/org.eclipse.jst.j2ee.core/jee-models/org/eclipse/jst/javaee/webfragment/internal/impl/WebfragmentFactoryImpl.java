/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.webfragment.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.javaee.webfragment.*;
import org.eclipse.jst.javaee.webfragment.internal.metadata.WebfragmentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebfragmentFactoryImpl extends EFactoryImpl implements WebfragmentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WebfragmentFactory init() {
		try {
			WebfragmentFactory theWebfragmentFactory = (WebfragmentFactory)EPackage.Registry.INSTANCE.getEFactory("http://xmlns.jcp.org/xml/ns/javaee/webfragment"); //$NON-NLS-1$ 
			if (theWebfragmentFactory != null) {
				return theWebfragmentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WebfragmentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebfragmentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case WebfragmentPackage.WEB_APP_DEPLOYMENT_DESCRIPTOR: return (EObject)createWebAppDeploymentDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebAppDeploymentDescriptor createWebAppDeploymentDescriptor() {
		WebAppDeploymentDescriptorImpl webAppDeploymentDescriptor = new WebAppDeploymentDescriptorImpl();
		return webAppDeploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WebfragmentPackage getWebfragmentPackage() {
		return (WebfragmentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WebfragmentPackage getPackage() {
		return WebfragmentPackage.eINSTANCE;
	}

} //WebfragmentFactoryImpl
