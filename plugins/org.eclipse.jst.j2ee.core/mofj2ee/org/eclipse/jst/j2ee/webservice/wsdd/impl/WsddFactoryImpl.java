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
package org.eclipse.jst.j2ee.webservice.wsdd.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WsddFactoryImpl extends EFactoryImpl implements WsddFactory
{
	/**
	 * Creates and instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WsddFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case WsddPackage.WEB_SERVICES: return createWebServices();
			case WsddPackage.WEB_SERVICE_DESCRIPTION: return createWebServiceDescription();
			case WsddPackage.PORT_COMPONENT: return createPortComponent();
			case WsddPackage.WSDL_PORT: return createWSDLPort();
			case WsddPackage.SERVICE_IMPL_BEAN: return createServiceImplBean();
			case WsddPackage.SERVLET_LINK: return createServletLink();
			case WsddPackage.EJB_LINK: return createEJBLink();
			case WsddPackage.HANDLER: return createHandler();
			case WsddPackage.BEAN_LINK: return createBeanLink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");//$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WebServices createWebServices() {
		WebServicesImpl webServices = new WebServicesImpl();
		return webServices;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WebServiceDescription createWebServiceDescription() {
		WebServiceDescriptionImpl webServiceDescription = new WebServiceDescriptionImpl();
		return webServiceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public PortComponent createPortComponent() {
		PortComponentImpl portComponent = new PortComponentImpl();
		return portComponent;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WSDLPort createWSDLPort() {
		WSDLPortImpl wsdlPort = new WSDLPortImpl();
		return wsdlPort;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ServiceImplBean createServiceImplBean() {
		ServiceImplBeanImpl serviceImplBean = new ServiceImplBeanImpl();
		return serviceImplBean;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ServletLink createServletLink() {
		ServletLinkImpl servletLink = new ServletLinkImpl();
		return servletLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EJBLink createEJBLink() {
		EJBLinkImpl ejbLink = new EJBLinkImpl();
		return ejbLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Handler createHandler() {
		HandlerImpl handler = new HandlerImpl();
		return handler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BeanLink createBeanLink() {
		BeanLinkImpl beanLink = new BeanLinkImpl();
		return beanLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WsddPackage getWsddPackage() {
		return (WsddPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
  public static WsddPackage getPackage() {
		return WsddPackage.eINSTANCE;
	}
} //WsddFactoryImpl
