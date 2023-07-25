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
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.j2ee.webservice.wsdd.*;
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
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WsddFactory init() {
		try {
			WsddFactory theWsddFactory = (WsddFactory)EPackage.Registry.INSTANCE.getEFactory("wsdd.xmi"); //$NON-NLS-1$ 
			if (theWsddFactory != null) {
				return theWsddFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WsddFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
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
  @Override
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
			case WsddPackage.WSDL_SERVICE: return createWSDLService();
			case WsddPackage.HANDLER_CHAIN: return createHandlerChain();
			case WsddPackage.HANDLERS_CHAINS: return createHandlersChains();
			case WsddPackage.RESPECT_BINDING_TYPE: return createRespectBindingType();
			case WsddPackage.ADDRESSING_TYPE: return createAddressingType();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case WsddPackage.ADDRESSING_RESPONSES_TYPE:
				return createAddressingResponsesTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case WsddPackage.ADDRESSING_RESPONSES_TYPE:
				return convertAddressingResponsesTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public WebServices createWebServices() {
		WebServicesImpl webServices = new WebServicesImpl();
		return webServices;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public WebServiceDescription createWebServiceDescription() {
		WebServiceDescriptionImpl webServiceDescription = new WebServiceDescriptionImpl();
		return webServiceDescription;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public PortComponent createPortComponent() {
		PortComponentImpl portComponent = new PortComponentImpl();
		return portComponent;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public WSDLPort createWSDLPort() {
		WSDLPortImpl wsdlPort = new WSDLPortImpl();
		return wsdlPort;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public ServiceImplBean createServiceImplBean() {
		ServiceImplBeanImpl serviceImplBean = new ServiceImplBeanImpl();
		return serviceImplBean;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public ServletLink createServletLink() {
		ServletLinkImpl servletLink = new ServletLinkImpl();
		return servletLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EJBLink createEJBLink() {
		EJBLinkImpl ejbLink = new EJBLinkImpl();
		return ejbLink;
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
	public BeanLink createBeanLink() {
		BeanLinkImpl beanLink = new BeanLinkImpl();
		return beanLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WSDLService createWSDLService() {
		WSDLServiceImpl wsdlService = new WSDLServiceImpl();
		return wsdlService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HandlerChain createHandlerChain() {
		HandlerChainImpl handlerChain = new HandlerChainImpl();
		return handlerChain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HandlersChains createHandlersChains() {
		HandlersChainsImpl handlersChains = new HandlersChainsImpl();
		return handlersChains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RespectBindingType createRespectBindingType() {
		RespectBindingTypeImpl respectBindingType = new RespectBindingTypeImpl();
		return respectBindingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AddressingType createAddressingType() {
		AddressingTypeImpl addressingType = new AddressingTypeImpl();
		return addressingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressingResponsesType createAddressingResponsesTypeFromString(EDataType eDataType, String initialValue) {
		AddressingResponsesType result = AddressingResponsesType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAddressingResponsesTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
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
