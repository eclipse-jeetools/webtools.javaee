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
package org.eclipse.jst.j2ee.application.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;

/**
 * @generated
 */
public class ApplicationFactoryImpl extends EFactoryImpl implements ApplicationFactory{
 
	public ApplicationFactoryImpl() {
		super(); 	
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ApplicationPackage.APPLICATION: return createApplication();
			case ApplicationPackage.MODULE: return createModule();
			case ApplicationPackage.WEB_MODULE: return createWebModule();
			case ApplicationPackage.JAVA_CLIENT_MODULE: return createJavaClientModule();
			case ApplicationPackage.EJB_MODULE: return createEjbModule();
			case ApplicationPackage.CONNECTOR_MODULE: return createConnectorModule();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

public static ApplicationFactory getActiveFactory() {
	return (ApplicationFactory) getPackage().getEFactoryInstance();
}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Application createApplication() {
		ApplicationImpl application = new ApplicationImpl();
		return application;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Module createModule() {
		ModuleImpl module = new ModuleImpl();
		return module;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WebModule createWebModule() {
		WebModuleImpl webModule = new WebModuleImpl();
		return webModule;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaClientModule createJavaClientModule() {
		JavaClientModuleImpl javaClientModule = new JavaClientModuleImpl();
		return javaClientModule;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EjbModule createEjbModule() {
		EjbModuleImpl ejbModule = new EjbModuleImpl();
		return ejbModule;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ConnectorModule createConnectorModule() {
		ConnectorModuleImpl connectorModule = new ConnectorModuleImpl();
		return connectorModule;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ApplicationPackage getApplicationPackage() {
		return (ApplicationPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static ApplicationPackage getPackage() {
		return ApplicationPackage.eINSTANCE;
	}
}






