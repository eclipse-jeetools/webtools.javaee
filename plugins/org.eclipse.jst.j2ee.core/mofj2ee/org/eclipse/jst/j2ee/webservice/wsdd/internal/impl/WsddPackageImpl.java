/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.AddressingResponsesType;
import org.eclipse.jst.j2ee.webservice.wsdd.AddressingType;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain;
import org.eclipse.jst.j2ee.webservice.wsdd.HandlersChains;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.RespectBindingType;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLService;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WsddPackageImpl extends EPackageImpl implements WsddPackage
{
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass webServicesEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass webServiceDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass portComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlPortEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass serviceImplBeanEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass servletLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass ejbLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass handlerEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass beanLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wsdlServiceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlerChainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlersChainsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass respectBindingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addressingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum addressingResponsesTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
  private WsddPackageImpl() {
		super(eNS_URI, WsddFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link WsddPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
  public static WsddPackage init() {
		if (isInited) return (WsddPackage)EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI);

		// Obtain or create and register package
		WsddPackageImpl theWsddPackage = 
			(WsddPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof
					WsddPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new
							WsddPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		JavaRefPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theWsddPackage.createPackageContents();

		// Initialize created meta-data
		theWsddPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWsddPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WsddPackage.eNS_URI, theWsddPackage);

		J2EEInit.initEMFModels();

		return theWsddPackage;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWebServices() {
		return webServicesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWebServices_WebServiceDescriptions() {
		return (EReference)webServicesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWebServiceDescription() {
		return webServiceDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_JaxrpcMappingFile() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_WebServiceDescriptionName() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_WsdlFile() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_SmallIcon() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_LargeIcon() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_Description() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWebServiceDescription_DisplayName() {
		return (EAttribute)webServiceDescriptionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWebServiceDescription_PortComponents() {
		return (EReference)webServiceDescriptionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWebServiceDescription_DescriptionType() {
		return (EReference)webServiceDescriptionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWebServiceDescription_DisplayNameType() {
		return (EReference)webServiceDescriptionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWebServiceDescription_IconType() {
		return (EReference)webServiceDescriptionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getPortComponent() {
		return portComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortComponent_PortComponentName() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortComponent_ServiceEndpointInterface() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortComponent_SmallIcon() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortComponent_LargeIcon() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortComponent_Description() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortComponent_DisplayName() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getPortComponent_WsdlPort() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getPortComponent_ServiceImplBean() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getPortComponent_Handlers() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getPortComponent_DescriptionType() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getPortComponent_DisplayNameType() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getPortComponent_IconType() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortComponent_WsdlService() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponent_EnableMtom() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponent_ProtocolBinding() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortComponent_HandlerChains() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponent_MtomThreshold() {
		return (EAttribute)portComponentEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortComponent_RespectBinding() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortComponent_Addressing() {
		return (EReference)portComponentEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLPort() {
		return wsdlPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getServiceImplBean() {
		return serviceImplBeanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceImplBean_EEJBLink() {
		return (EReference)serviceImplBeanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceImplBean_EServletLink() {
		return (EReference)serviceImplBeanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceImplBean_BeanLink() {
		return (EReference)serviceImplBeanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getServletLink() {
		return servletLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServletLink_ServletLink() {
		return (EAttribute)servletLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getEJBLink() {
		return ejbLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getEJBLink_EjbLink() {
		return (EAttribute)ejbLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getHandler() {
		return handlerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getHandler_HandlerName() {
		return (EAttribute)handlerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getHandler_HandlerClass() {
		return (EAttribute)handlerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getHandler_InitParams() {
		return (EReference)handlerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getHandler_SoapHeaders() {
		return (EReference)handlerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getHandler_SoapRoles() {
		return (EReference)handlerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getBeanLink() {
		return beanLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWSDLService() {
		return wsdlServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandlerChain() {
		return handlerChainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHandlerChain_ServiceNamePattern() {
		return (EAttribute)handlerChainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHandlerChain_PortNamePattern() {
		return (EAttribute)handlerChainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHandlerChain_ProtocolBindings() {
		return (EAttribute)handlerChainEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHandlerChain_Handlers() {
		return (EReference)handlerChainEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandlersChains() {
		return handlersChainsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHandlersChains_HandlerChain() {
		return (EReference)handlersChainsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRespectBindingType() {
		return respectBindingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRespectBindingType_Enabled() {
		return (EAttribute)respectBindingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddressingType() {
		return addressingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddressingType_Enabled() {
		return (EAttribute)addressingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddressingType_Required() {
		return (EAttribute)addressingTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddressingType_Responses() {
		return (EAttribute)addressingTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAddressingResponsesType() {
		return addressingResponsesTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WsddFactory getWsddFactory() {
		return (WsddFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		webServicesEClass = createEClass(WEB_SERVICES);
		createEReference(webServicesEClass, WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS);

		webServiceDescriptionEClass = createEClass(WEB_SERVICE_DESCRIPTION);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__JAXRPC_MAPPING_FILE);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__WEB_SERVICE_DESCRIPTION_NAME);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__WSDL_FILE);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__SMALL_ICON);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__LARGE_ICON);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__DESCRIPTION);
		createEAttribute(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__DISPLAY_NAME);
		createEReference(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__PORT_COMPONENTS);
		createEReference(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__DESCRIPTION_TYPE);
		createEReference(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__DISPLAY_NAME_TYPE);
		createEReference(webServiceDescriptionEClass, WEB_SERVICE_DESCRIPTION__ICON_TYPE);

		portComponentEClass = createEClass(PORT_COMPONENT);
		createEAttribute(portComponentEClass, PORT_COMPONENT__PORT_COMPONENT_NAME);
		createEAttribute(portComponentEClass, PORT_COMPONENT__SERVICE_ENDPOINT_INTERFACE);
		createEAttribute(portComponentEClass, PORT_COMPONENT__SMALL_ICON);
		createEAttribute(portComponentEClass, PORT_COMPONENT__LARGE_ICON);
		createEAttribute(portComponentEClass, PORT_COMPONENT__DESCRIPTION);
		createEAttribute(portComponentEClass, PORT_COMPONENT__DISPLAY_NAME);
		createEReference(portComponentEClass, PORT_COMPONENT__WSDL_PORT);
		createEReference(portComponentEClass, PORT_COMPONENT__SERVICE_IMPL_BEAN);
		createEReference(portComponentEClass, PORT_COMPONENT__HANDLERS);
		createEReference(portComponentEClass, PORT_COMPONENT__DESCRIPTION_TYPE);
		createEReference(portComponentEClass, PORT_COMPONENT__DISPLAY_NAME_TYPE);
		createEReference(portComponentEClass, PORT_COMPONENT__ICON_TYPE);
		createEReference(portComponentEClass, PORT_COMPONENT__WSDL_SERVICE);
		createEAttribute(portComponentEClass, PORT_COMPONENT__ENABLE_MTOM);
		createEAttribute(portComponentEClass, PORT_COMPONENT__PROTOCOL_BINDING);
		createEReference(portComponentEClass, PORT_COMPONENT__HANDLER_CHAINS);
		createEAttribute(portComponentEClass, PORT_COMPONENT__MTOM_THRESHOLD);
		createEReference(portComponentEClass, PORT_COMPONENT__RESPECT_BINDING);
		createEReference(portComponentEClass, PORT_COMPONENT__ADDRESSING);

		wsdlPortEClass = createEClass(WSDL_PORT);

		serviceImplBeanEClass = createEClass(SERVICE_IMPL_BEAN);
		createEReference(serviceImplBeanEClass, SERVICE_IMPL_BEAN__EEJB_LINK);
		createEReference(serviceImplBeanEClass, SERVICE_IMPL_BEAN__ESERVLET_LINK);
		createEReference(serviceImplBeanEClass, SERVICE_IMPL_BEAN__BEAN_LINK);

		servletLinkEClass = createEClass(SERVLET_LINK);
		createEAttribute(servletLinkEClass, SERVLET_LINK__SERVLET_LINK);

		ejbLinkEClass = createEClass(EJB_LINK);
		createEAttribute(ejbLinkEClass, EJB_LINK__EJB_LINK);

		handlerEClass = createEClass(HANDLER);
		createEAttribute(handlerEClass, HANDLER__HANDLER_NAME);
		createEAttribute(handlerEClass, HANDLER__HANDLER_CLASS);
		createEReference(handlerEClass, HANDLER__INIT_PARAMS);
		createEReference(handlerEClass, HANDLER__SOAP_HEADERS);
		createEReference(handlerEClass, HANDLER__SOAP_ROLES);

		beanLinkEClass = createEClass(BEAN_LINK);

		wsdlServiceEClass = createEClass(WSDL_SERVICE);

		handlerChainEClass = createEClass(HANDLER_CHAIN);
		createEAttribute(handlerChainEClass, HANDLER_CHAIN__SERVICE_NAME_PATTERN);
		createEAttribute(handlerChainEClass, HANDLER_CHAIN__PORT_NAME_PATTERN);
		createEAttribute(handlerChainEClass, HANDLER_CHAIN__PROTOCOL_BINDINGS);
		createEReference(handlerChainEClass, HANDLER_CHAIN__HANDLERS);

		handlersChainsEClass = createEClass(HANDLERS_CHAINS);
		createEReference(handlersChainsEClass, HANDLERS_CHAINS__HANDLER_CHAIN);

		respectBindingTypeEClass = createEClass(RESPECT_BINDING_TYPE);
		createEAttribute(respectBindingTypeEClass, RESPECT_BINDING_TYPE__ENABLED);

		addressingTypeEClass = createEClass(ADDRESSING_TYPE);
		createEAttribute(addressingTypeEClass, ADDRESSING_TYPE__ENABLED);
		createEAttribute(addressingTypeEClass, ADDRESSING_TYPE__REQUIRED);
		createEAttribute(addressingTypeEClass, ADDRESSING_TYPE__RESPONSES);

		// Create enums
		addressingResponsesTypeEEnum = createEEnum(ADDRESSING_RESPONSES_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
		WscommonPackage theWscommonPackage = (WscommonPackage)EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
			// Add supertypes to classes
			webServicesEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
			webServiceDescriptionEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			portComponentEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			wsdlPortEClass.getESuperTypes().add(theCommonPackage.getQName());
			serviceImplBeanEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			servletLinkEClass.getESuperTypes().add(this.getBeanLink());
			ejbLinkEClass.getESuperTypes().add(this.getBeanLink());
			handlerEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());
			beanLinkEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			wsdlServiceEClass.getESuperTypes().add(theCommonPackage.getQName());
			handlerChainEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			handlersChainsEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			respectBindingTypeEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
			addressingTypeEClass.getESuperTypes().add(theCommonPackage.getJ2EEEObject());
	
			// Initialize classes and features; add operations and parameters
			initEClass(webServicesEClass, WebServices.class, "WebServices", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getWebServices_WebServiceDescriptions(), this.getWebServiceDescription(), null, "webServiceDescriptions", null, 1, -1, WebServices.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(webServiceDescriptionEClass, WebServiceDescription.class, "WebServiceDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_JaxrpcMappingFile(), ecorePackage.getEString(), "jaxrpcMappingFile", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_WebServiceDescriptionName(), ecorePackage.getEString(), "webServiceDescriptionName", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_WsdlFile(), ecorePackage.getEString(), "wsdlFile", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_SmallIcon(), ecorePackage.getEString(), "smallIcon", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_LargeIcon(), ecorePackage.getEString(), "largeIcon", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_Description(), ecorePackage.getEString(), "description", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getWebServiceDescription_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getWebServiceDescription_PortComponents(), this.getPortComponent(), null, "portComponents", null, 1, -1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getWebServiceDescription_DescriptionType(), theWscommonPackage.getDescriptionType(), null, "descriptionType", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getWebServiceDescription_DisplayNameType(), theWscommonPackage.getDisplayNameType(), null, "displayNameType", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getWebServiceDescription_IconType(), theCommonPackage.getIconType(), null, "iconType", null, 0, 1, WebServiceDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(portComponentEClass, PortComponent.class, "PortComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getPortComponent_PortComponentName(), ecorePackage.getEString(), "portComponentName", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_ServiceEndpointInterface(), ecorePackage.getEString(), "serviceEndpointInterface", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_SmallIcon(), ecorePackage.getEString(), "smallIcon", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_LargeIcon(), ecorePackage.getEString(), "largeIcon", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_Description(), ecorePackage.getEString(), "description", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_WsdlPort(), this.getWSDLPort(), null, "wsdlPort", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_ServiceImplBean(), this.getServiceImplBean(), null, "serviceImplBean", null, 1, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_Handlers(), this.getHandler(), null, "handlers", null, 0, -1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_DescriptionType(), theWscommonPackage.getDescriptionType(), null, "descriptionType", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_DisplayNameType(), theWscommonPackage.getDisplayNameType(), null, "displayNameType", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_IconType(), theCommonPackage.getIconType(), null, "iconType", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_WsdlService(), this.getWSDLService(), null, "wsdlService", null, 1, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_EnableMtom(), theEcorePackage.getEBoolean(), "enableMtom", "", 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
			initEAttribute(getPortComponent_ProtocolBinding(), ecorePackage.getEString(), "protocolBinding", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_HandlerChains(), this.getHandlersChains(), null, "handlerChains", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponent_MtomThreshold(), theEcorePackage.getEBigInteger(), "mtomThreshold", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_RespectBinding(), this.getRespectBindingType(), null, "respectBinding", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponent_Addressing(), this.getAddressingType(), null, "addressing", null, 0, 1, PortComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(wsdlPortEClass, WSDLPort.class, "WSDLPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
			initEClass(serviceImplBeanEClass, ServiceImplBean.class, "ServiceImplBean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getServiceImplBean_EEJBLink(), this.getEJBLink(), null, "eEJBLink", null, 0, 1, ServiceImplBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceImplBean_EServletLink(), this.getServletLink(), null, "eServletLink", null, 0, 1, ServiceImplBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceImplBean_BeanLink(), this.getBeanLink(), null, "beanLink", null, 0, 1, ServiceImplBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(servletLinkEClass, ServletLink.class, "ServletLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getServletLink_ServletLink(), ecorePackage.getEString(), "servletLink", null, 0, 1, ServletLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(ejbLinkEClass, EJBLink.class, "EJBLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getEJBLink_EjbLink(), ecorePackage.getEString(), "ejbLink", null, 0, 1, EJBLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(handlerEClass, Handler.class, "Handler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getHandler_HandlerName(), ecorePackage.getEString(), "handlerName", null, 0, 1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getHandler_HandlerClass(), ecorePackage.getEString(), "handlerClass", null, 0, 1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandler_InitParams(), theWscommonPackage.getInitParam(), null, "initParams", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandler_SoapHeaders(), theWscommonPackage.getSOAPHeader(), null, "soapHeaders", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandler_SoapRoles(), theWscommonPackage.getSOAPRole(), null, "soapRoles", null, 0, -1, Handler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(beanLinkEClass, BeanLink.class, "BeanLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
			initEClass(wsdlServiceEClass, WSDLService.class, "WSDLService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
			initEClass(handlerChainEClass, HandlerChain.class, "HandlerChain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getHandlerChain_ServiceNamePattern(), ecorePackage.getEString(), "serviceNamePattern", null, 0, 1, HandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getHandlerChain_PortNamePattern(), ecorePackage.getEString(), "portNamePattern", null, 0, 1, HandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getHandlerChain_ProtocolBindings(), ecorePackage.getEString(), "protocolBindings", null, 0, -1, HandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getHandlerChain_Handlers(), this.getHandler(), null, "handlers", null, 1, -1, HandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(handlersChainsEClass, HandlersChains.class, "HandlersChains", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getHandlersChains_HandlerChain(), this.getHandlerChain(), null, "handlerChain", null, 0, -1, HandlersChains.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(respectBindingTypeEClass, RespectBindingType.class, "RespectBindingType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getRespectBindingType_Enabled(), theEcorePackage.getEBoolean(), "enabled", null, 0, 1, RespectBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(addressingTypeEClass, AddressingType.class, "AddressingType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getAddressingType_Enabled(), theEcorePackage.getEBoolean(), "enabled", null, 0, 1, AddressingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getAddressingType_Required(), theEcorePackage.getEBoolean(), "required", null, 0, 1, AddressingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getAddressingType_Responses(), this.getAddressingResponsesType(), "responses", null, 0, 1, AddressingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			// Initialize enums and add enum literals
			initEEnum(addressingResponsesTypeEEnum, AddressingResponsesType.class, "AddressingResponsesType"); //$NON-NLS-1$
			addEEnumLiteral(addressingResponsesTypeEEnum, AddressingResponsesType.ANONYMOUS_LITERAL);
			addEEnumLiteral(addressingResponsesTypeEEnum, AddressingResponsesType.NONANONYMOUS_LITERAL);
			addEEnumLiteral(addressingResponsesTypeEEnum, AddressingResponsesType.ALL_LITERAL);
	
			// Create resource
			createResource(eNS_URI);
		}finally{
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

} //WsddPackageImpl
