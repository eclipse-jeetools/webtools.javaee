/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsclient.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Webservice_clientFactoryImpl extends EFactoryImpl implements Webservice_clientFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Webservice_clientFactory init() {
		try {
			Webservice_clientFactory theWebservice_clientFactory = (Webservice_clientFactory)EPackage.Registry.INSTANCE.getEFactory("webservice_client.xmi");  //$NON-NLS-1$
			if (theWebservice_clientFactory != null) {
				return theWebservice_clientFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Webservice_clientFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Webservice_clientFactoryImpl() {
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
			case Webservice_clientPackage.SERVICE_REF: return createServiceRef();
			case Webservice_clientPackage.PORT_COMPONENT_REF: return createPortComponentRef();
			case Webservice_clientPackage.HANDLER: return createHandler();
			case Webservice_clientPackage.WEB_SERVICES_CLIENT: return createWebServicesClient();
			case Webservice_clientPackage.COMPONENT_SCOPED_REFS: return createComponentScopedRefs();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServiceRef createServiceRef() {
		ServiceRefImpl serviceRef = new ServiceRefImpl();
		return serviceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PortComponentRef createPortComponentRef() {
		PortComponentRefImpl portComponentRef = new PortComponentRefImpl();
		return portComponentRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Handler createHandler() {
		HandlerImpl handler = new HandlerImpl();
		return handler;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public WebServicesClient createWebServicesClient() {
		WebServicesClientImpl webServicesClient = new WebServicesClientImpl();
		return webServicesClient;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public ComponentScopedRefs createComponentScopedRefs() {
		ComponentScopedRefsImpl componentScopedRefs = new ComponentScopedRefsImpl();
		return componentScopedRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Webservice_clientPackage getWebservice_clientPackage() {
		return (Webservice_clientPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static Webservice_clientPackage getPackage() {
		return Webservice_clientPackage.eINSTANCE;
	}

} //Webservice_clientFactoryImpl
