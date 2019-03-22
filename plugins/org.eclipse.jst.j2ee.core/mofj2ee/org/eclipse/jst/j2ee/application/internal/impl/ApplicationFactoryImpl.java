/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
public class ApplicationFactoryImpl extends EFactoryImpl implements ApplicationFactory {
 
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ApplicationFactory init() {
		try {
			ApplicationFactory theApplicationFactory = (ApplicationFactory)EPackage.Registry.INSTANCE.getEFactory("application.xmi");  //$NON-NLS-1$
			if (theApplicationFactory != null) {
				return theApplicationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ApplicationFactoryImpl();
	}

	public ApplicationFactoryImpl() {
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






