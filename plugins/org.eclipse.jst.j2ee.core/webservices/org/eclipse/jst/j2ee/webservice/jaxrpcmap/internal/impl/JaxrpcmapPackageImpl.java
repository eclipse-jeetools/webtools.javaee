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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.client.internal.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ElementName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLBinding;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessageMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLOperation;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLPortType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLServiceName;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WsddPackageImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JaxrpcmapPackageImpl extends EPackageImpl implements JaxrpcmapPackage
{
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass javaWSDLMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass packageMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass javaXMLTypeMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass exceptionMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass serviceInterfaceMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass serviceEndpointInterfaceMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass rootTypeQnameEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass variableMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass constructorParameterOrderEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass elementNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlServiceNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass portMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlPortTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass serviceEndpointMethodMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass methodParamPartsMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlReturnValueMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlMessageMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass wsdlMessagePartNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private EClass interfaceMappingEClass = null;

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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
  private JaxrpcmapPackageImpl() {
		super(eNS_URI, JaxrpcmapFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
  public static JaxrpcmapPackage init() {
		if (isInited) return (JaxrpcmapPackage)EPackage.Registry.INSTANCE.getEPackage(JaxrpcmapPackage.eNS_URI);

		// Obtain or create and register package
		JaxrpcmapPackageImpl theJaxrpcmapPackage = (JaxrpcmapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof JaxrpcmapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new JaxrpcmapPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ClientPackageImpl.init();
		ApplicationPackageImpl.init();
		CommonPackageImpl.init();
		EjbPackageImpl.init();
		WebapplicationPackageImpl.init();
		Webservice_clientPackageImpl.init();
		JspPackageImpl.init();
		WscommonPackageImpl.init();
		WsddPackageImpl.init();
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Create package meta-data objects
		theJaxrpcmapPackage.createPackageContents();

		// Initialize created meta-data
		theJaxrpcmapPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJaxrpcmapPackage.freeze();

		return theJaxrpcmapPackage;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getJavaWSDLMapping() {
		return javaWSDLMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getJavaWSDLMapping_Id() {
		return (EAttribute)javaWSDLMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getJavaWSDLMapping_PackageMappings() {
		return (EReference)javaWSDLMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getJavaWSDLMapping_JavaXMLTypeMappings() {
		return (EReference)javaWSDLMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getJavaWSDLMapping_ExceptionMappings() {
		return (EReference)javaWSDLMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getJavaWSDLMapping_InterfaceMappings() {
		return (EReference)javaWSDLMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getPackageMapping() {
		return packageMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPackageMapping_Id() {
		return (EAttribute)packageMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPackageMapping_PackageType() {
		return (EAttribute)packageMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPackageMapping_NamespaceURI() {
		return (EAttribute)packageMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getJavaXMLTypeMapping() {
		return javaXMLTypeMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getJavaXMLTypeMapping_Id() {
		return (EAttribute)javaXMLTypeMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getJavaXMLTypeMapping_ClassType() {
		return (EAttribute)javaXMLTypeMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getJavaXMLTypeMapping_QnameScope() {
		return (EAttribute)javaXMLTypeMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getJavaXMLTypeMapping_AnonymousTypeQname() {
		return (EAttribute)javaXMLTypeMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getJavaXMLTypeMapping_RootTypeQname() {
		return (EReference)javaXMLTypeMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getJavaXMLTypeMapping_VariableMappings() {
		return (EReference)javaXMLTypeMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getExceptionMapping() {
		return exceptionMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getExceptionMapping_Id() {
		return (EAttribute)exceptionMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getExceptionMapping_ExceptionType() {
		return (EAttribute)exceptionMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getExceptionMapping_WsdlMessagePartName() {
		return (EAttribute)exceptionMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getExceptionMapping_WsdlMessage() {
		return (EReference)exceptionMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getExceptionMapping_ConstructorParameterOrder() {
		return (EReference)exceptionMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getServiceInterfaceMapping() {
		return serviceInterfaceMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceInterfaceMapping_Id() {
		return (EAttribute)serviceInterfaceMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceInterfaceMapping_ServiceInterface() {
		return (EAttribute)serviceInterfaceMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceInterfaceMapping_WsdlServiceName() {
		return (EReference)serviceInterfaceMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceInterfaceMapping_PortMappings() {
		return (EReference)serviceInterfaceMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getServiceEndpointInterfaceMapping() {
		return serviceEndpointInterfaceMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceEndpointInterfaceMapping_Id() {
		return (EAttribute)serviceEndpointInterfaceMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceEndpointInterfaceMapping_ServiceEndpointInterface() {
		return (EAttribute)serviceEndpointInterfaceMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceEndpointInterfaceMapping_WsdlPortType() {
		return (EReference)serviceEndpointInterfaceMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceEndpointInterfaceMapping_WsdlBinding() {
		return (EReference)serviceEndpointInterfaceMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings() {
		return (EReference)serviceEndpointInterfaceMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getRootTypeQname() {
		return rootTypeQnameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getVariableMapping() {
		return variableMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getVariableMapping_Id() {
		return (EAttribute)variableMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getVariableMapping_JavaVariableName() {
		return (EAttribute)variableMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getVariableMapping_XmlElementName() {
		return (EAttribute)variableMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getVariableMapping_DataMember() {
		return (EAttribute)variableMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getVariableMapping_XmlAttributeName() {
		return (EAttribute)variableMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getVariableMapping_XmlWildcard() {
		return (EAttribute)variableMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLMessage() {
		return wsdlMessageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getConstructorParameterOrder() {
		return constructorParameterOrderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getConstructorParameterOrder_Id() {
		return (EAttribute)constructorParameterOrderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getConstructorParameterOrder_ElementNames() {
		return (EReference)constructorParameterOrderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getElementName() {
		return elementNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getElementName_Id() {
		return (EAttribute)elementNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getElementName_Text() {
		return (EAttribute)elementNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLServiceName() {
		return wsdlServiceNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getPortMapping() {
		return portMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortMapping_Id() {
		return (EAttribute)portMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortMapping_PortName() {
		return (EAttribute)portMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getPortMapping_JavaPortName() {
		return (EAttribute)portMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLPortType() {
		return wsdlPortTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLBinding() {
		return wsdlBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getServiceEndpointMethodMapping() {
		return serviceEndpointMethodMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceEndpointMethodMapping_Id() {
		return (EAttribute)serviceEndpointMethodMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceEndpointMethodMapping_JavaMethodName() {
		return (EAttribute)serviceEndpointMethodMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceEndpointMethodMapping_WsdlOperation() {
		return (EAttribute)serviceEndpointMethodMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getServiceEndpointMethodMapping_WrappedElement() {
		return (EAttribute)serviceEndpointMethodMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceEndpointMethodMapping_MethodParamPartsMappings() {
		return (EReference)serviceEndpointMethodMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getServiceEndpointMethodMapping_WsdlReturnValueMapping() {
		return (EReference)serviceEndpointMethodMappingEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLOperation() {
		return wsdlOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLOperation_Id() {
		return (EAttribute)wsdlOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLOperation_WsdlOperation() {
		return (EAttribute)wsdlOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getMethodParamPartsMapping() {
		return methodParamPartsMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getMethodParamPartsMapping_Id() {
		return (EAttribute)methodParamPartsMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getMethodParamPartsMapping_ParamPosition() {
		return (EAttribute)methodParamPartsMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getMethodParamPartsMapping_ParamType() {
		return (EAttribute)methodParamPartsMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getMethodParamPartsMapping_WsdlMessageMapping() {
		return (EReference)methodParamPartsMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLReturnValueMapping() {
		return wsdlReturnValueMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLReturnValueMapping_Id() {
		return (EAttribute)wsdlReturnValueMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLReturnValueMapping_MethodReturnValue() {
		return (EAttribute)wsdlReturnValueMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLReturnValueMapping_WsdlMessagePartName() {
		return (EAttribute)wsdlReturnValueMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWSDLReturnValueMapping_WsdlMessage() {
		return (EReference)wsdlReturnValueMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLMessageMapping() {
		return wsdlMessageMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLMessageMapping_Id() {
		return (EAttribute)wsdlMessageMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLMessageMapping_WsdlMessagePartName() {
		return (EAttribute)wsdlMessageMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLMessageMapping_ParameterMode() {
		return (EAttribute)wsdlMessageMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLMessageMapping_SoapHeader() {
		return (EAttribute)wsdlMessageMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EReference getWSDLMessageMapping_WsdlMessage() {
		return (EReference)wsdlMessageMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getWSDLMessagePartName() {
		return wsdlMessagePartNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLMessagePartName_Id() {
		return (EAttribute)wsdlMessagePartNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EAttribute getWSDLMessagePartName_WsdlMessagePartName() {
		return (EAttribute)wsdlMessagePartNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EClass getInterfaceMapping() {
		return interfaceMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public JaxrpcmapFactory getJaxrpcmapFactory() {
		return (JaxrpcmapFactory)getEFactoryInstance();
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
		javaWSDLMappingEClass = createEClass(JAVA_WSDL_MAPPING);
		createEAttribute(javaWSDLMappingEClass, JAVA_WSDL_MAPPING__ID);
		createEReference(javaWSDLMappingEClass, JAVA_WSDL_MAPPING__PACKAGE_MAPPINGS);
		createEReference(javaWSDLMappingEClass, JAVA_WSDL_MAPPING__JAVA_XML_TYPE_MAPPINGS);
		createEReference(javaWSDLMappingEClass, JAVA_WSDL_MAPPING__EXCEPTION_MAPPINGS);
		createEReference(javaWSDLMappingEClass, JAVA_WSDL_MAPPING__INTERFACE_MAPPINGS);

		packageMappingEClass = createEClass(PACKAGE_MAPPING);
		createEAttribute(packageMappingEClass, PACKAGE_MAPPING__ID);
		createEAttribute(packageMappingEClass, PACKAGE_MAPPING__PACKAGE_TYPE);
		createEAttribute(packageMappingEClass, PACKAGE_MAPPING__NAMESPACE_URI);

		javaXMLTypeMappingEClass = createEClass(JAVA_XML_TYPE_MAPPING);
		createEAttribute(javaXMLTypeMappingEClass, JAVA_XML_TYPE_MAPPING__ID);
		createEAttribute(javaXMLTypeMappingEClass, JAVA_XML_TYPE_MAPPING__CLASS_TYPE);
		createEAttribute(javaXMLTypeMappingEClass, JAVA_XML_TYPE_MAPPING__QNAME_SCOPE);
		createEAttribute(javaXMLTypeMappingEClass, JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME);
		createEReference(javaXMLTypeMappingEClass, JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME);
		createEReference(javaXMLTypeMappingEClass, JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS);

		exceptionMappingEClass = createEClass(EXCEPTION_MAPPING);
		createEAttribute(exceptionMappingEClass, EXCEPTION_MAPPING__ID);
		createEAttribute(exceptionMappingEClass, EXCEPTION_MAPPING__EXCEPTION_TYPE);
		createEAttribute(exceptionMappingEClass, EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME);
		createEReference(exceptionMappingEClass, EXCEPTION_MAPPING__WSDL_MESSAGE);
		createEReference(exceptionMappingEClass, EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER);

		serviceInterfaceMappingEClass = createEClass(SERVICE_INTERFACE_MAPPING);
		createEAttribute(serviceInterfaceMappingEClass, SERVICE_INTERFACE_MAPPING__ID);
		createEAttribute(serviceInterfaceMappingEClass, SERVICE_INTERFACE_MAPPING__SERVICE_INTERFACE);
		createEReference(serviceInterfaceMappingEClass, SERVICE_INTERFACE_MAPPING__WSDL_SERVICE_NAME);
		createEReference(serviceInterfaceMappingEClass, SERVICE_INTERFACE_MAPPING__PORT_MAPPINGS);

		serviceEndpointInterfaceMappingEClass = createEClass(SERVICE_ENDPOINT_INTERFACE_MAPPING);
		createEAttribute(serviceEndpointInterfaceMappingEClass, SERVICE_ENDPOINT_INTERFACE_MAPPING__ID);
		createEAttribute(serviceEndpointInterfaceMappingEClass, SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_INTERFACE);
		createEReference(serviceEndpointInterfaceMappingEClass, SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_PORT_TYPE);
		createEReference(serviceEndpointInterfaceMappingEClass, SERVICE_ENDPOINT_INTERFACE_MAPPING__WSDL_BINDING);
		createEReference(serviceEndpointInterfaceMappingEClass, SERVICE_ENDPOINT_INTERFACE_MAPPING__SERVICE_ENDPOINT_METHOD_MAPPINGS);

		rootTypeQnameEClass = createEClass(ROOT_TYPE_QNAME);

		variableMappingEClass = createEClass(VARIABLE_MAPPING);
		createEAttribute(variableMappingEClass, VARIABLE_MAPPING__ID);
		createEAttribute(variableMappingEClass, VARIABLE_MAPPING__JAVA_VARIABLE_NAME);
		createEAttribute(variableMappingEClass, VARIABLE_MAPPING__XML_ELEMENT_NAME);
		createEAttribute(variableMappingEClass, VARIABLE_MAPPING__DATA_MEMBER);
		createEAttribute(variableMappingEClass, VARIABLE_MAPPING__XML_ATTRIBUTE_NAME);
		createEAttribute(variableMappingEClass, VARIABLE_MAPPING__XML_WILDCARD);

		wsdlMessageEClass = createEClass(WSDL_MESSAGE);

		constructorParameterOrderEClass = createEClass(CONSTRUCTOR_PARAMETER_ORDER);
		createEAttribute(constructorParameterOrderEClass, CONSTRUCTOR_PARAMETER_ORDER__ID);
		createEReference(constructorParameterOrderEClass, CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES);

		elementNameEClass = createEClass(ELEMENT_NAME);
		createEAttribute(elementNameEClass, ELEMENT_NAME__ID);
		createEAttribute(elementNameEClass, ELEMENT_NAME__TEXT);

		wsdlServiceNameEClass = createEClass(WSDL_SERVICE_NAME);

		portMappingEClass = createEClass(PORT_MAPPING);
		createEAttribute(portMappingEClass, PORT_MAPPING__ID);
		createEAttribute(portMappingEClass, PORT_MAPPING__PORT_NAME);
		createEAttribute(portMappingEClass, PORT_MAPPING__JAVA_PORT_NAME);

		wsdlPortTypeEClass = createEClass(WSDL_PORT_TYPE);

		wsdlBindingEClass = createEClass(WSDL_BINDING);

		serviceEndpointMethodMappingEClass = createEClass(SERVICE_ENDPOINT_METHOD_MAPPING);
		createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__ID);
		createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME);
		createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION);
		createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT);
		createEReference(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS);
		createEReference(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING);

		wsdlOperationEClass = createEClass(WSDL_OPERATION);
		createEAttribute(wsdlOperationEClass, WSDL_OPERATION__ID);
		createEAttribute(wsdlOperationEClass, WSDL_OPERATION__WSDL_OPERATION);

		methodParamPartsMappingEClass = createEClass(METHOD_PARAM_PARTS_MAPPING);
		createEAttribute(methodParamPartsMappingEClass, METHOD_PARAM_PARTS_MAPPING__ID);
		createEAttribute(methodParamPartsMappingEClass, METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION);
		createEAttribute(methodParamPartsMappingEClass, METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE);
		createEReference(methodParamPartsMappingEClass, METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING);

		wsdlReturnValueMappingEClass = createEClass(WSDL_RETURN_VALUE_MAPPING);
		createEAttribute(wsdlReturnValueMappingEClass, WSDL_RETURN_VALUE_MAPPING__ID);
		createEAttribute(wsdlReturnValueMappingEClass, WSDL_RETURN_VALUE_MAPPING__METHOD_RETURN_VALUE);
		createEAttribute(wsdlReturnValueMappingEClass, WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE_PART_NAME);
		createEReference(wsdlReturnValueMappingEClass, WSDL_RETURN_VALUE_MAPPING__WSDL_MESSAGE);

		wsdlMessageMappingEClass = createEClass(WSDL_MESSAGE_MAPPING);
		createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__ID);
		createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__WSDL_MESSAGE_PART_NAME);
		createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__PARAMETER_MODE);
		createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__SOAP_HEADER);
		createEReference(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__WSDL_MESSAGE);

		wsdlMessagePartNameEClass = createEClass(WSDL_MESSAGE_PART_NAME);
		createEAttribute(wsdlMessagePartNameEClass, WSDL_MESSAGE_PART_NAME__ID);
		createEAttribute(wsdlMessagePartNameEClass, WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME);

		interfaceMappingEClass = createEClass(INTERFACE_MAPPING);
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
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Add supertypes to classes
		serviceInterfaceMappingEClass.getESuperTypes().add(this.getInterfaceMapping());
		serviceEndpointInterfaceMappingEClass.getESuperTypes().add(this.getInterfaceMapping());
		rootTypeQnameEClass.getESuperTypes().add(theCommonPackage.getQName());
		wsdlMessageEClass.getESuperTypes().add(theCommonPackage.getQName());
		wsdlServiceNameEClass.getESuperTypes().add(theCommonPackage.getQName());
		wsdlPortTypeEClass.getESuperTypes().add(theCommonPackage.getQName());
		wsdlBindingEClass.getESuperTypes().add(theCommonPackage.getQName());

		// Initialize classes and features; add operations and parameters
		initEClass(javaWSDLMappingEClass, JavaWSDLMapping.class, "JavaWSDLMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaWSDLMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, JavaWSDLMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaWSDLMapping_PackageMappings(), this.getPackageMapping(), null, "packageMappings", null, 1, -1, JavaWSDLMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaWSDLMapping_JavaXMLTypeMappings(), this.getJavaXMLTypeMapping(), null, "javaXMLTypeMappings", null, 0, -1, JavaWSDLMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaWSDLMapping_ExceptionMappings(), this.getExceptionMapping(), null, "exceptionMappings", null, 0, -1, JavaWSDLMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaWSDLMapping_InterfaceMappings(), this.getInterfaceMapping(), null, "interfaceMappings", null, 0, -1, JavaWSDLMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageMappingEClass, PackageMapping.class, "PackageMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, PackageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageMapping_PackageType(), ecorePackage.getEString(), "packageType", null, 0, 1, PackageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageMapping_NamespaceURI(), ecorePackage.getEString(), "namespaceURI", null, 0, 1, PackageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaXMLTypeMappingEClass, JavaXMLTypeMapping.class, "JavaXMLTypeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaXMLTypeMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, JavaXMLTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaXMLTypeMapping_ClassType(), ecorePackage.getEString(), "classType", null, 0, 1, JavaXMLTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaXMLTypeMapping_QnameScope(), ecorePackage.getEString(), "qnameScope", null, 0, 1, JavaXMLTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaXMLTypeMapping_AnonymousTypeQname(), ecorePackage.getEString(), "anonymousTypeQname", null, 0, 1, JavaXMLTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaXMLTypeMapping_RootTypeQname(), this.getRootTypeQname(), null, "rootTypeQname", null, 1, 1, JavaXMLTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaXMLTypeMapping_VariableMappings(), this.getVariableMapping(), null, "variableMappings", null, 0, -1, JavaXMLTypeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exceptionMappingEClass, ExceptionMapping.class, "ExceptionMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExceptionMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, ExceptionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionMapping_ExceptionType(), ecorePackage.getEString(), "exceptionType", null, 0, 1, ExceptionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExceptionMapping_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, ExceptionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExceptionMapping_WsdlMessage(), this.getWSDLMessage(), null, "wsdlMessage", null, 1, 1, ExceptionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExceptionMapping_ConstructorParameterOrder(), this.getConstructorParameterOrder(), null, "constructorParameterOrder", null, 0, 1, ExceptionMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceInterfaceMappingEClass, ServiceInterfaceMapping.class, "ServiceInterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceInterfaceMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, ServiceInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceInterfaceMapping_ServiceInterface(), ecorePackage.getEString(), "serviceInterface", null, 0, 1, ServiceInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceInterfaceMapping_WsdlServiceName(), this.getWSDLServiceName(), null, "wsdlServiceName", null, 1, 1, ServiceInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceInterfaceMapping_PortMappings(), this.getPortMapping(), null, "portMappings", null, 0, -1, ServiceInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceEndpointInterfaceMappingEClass, ServiceEndpointInterfaceMapping.class, "ServiceEndpointInterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceEndpointInterfaceMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, ServiceEndpointInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceEndpointInterfaceMapping_ServiceEndpointInterface(), ecorePackage.getEString(), "serviceEndpointInterface", null, 0, 1, ServiceEndpointInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceEndpointInterfaceMapping_WsdlPortType(), this.getWSDLPortType(), null, "wsdlPortType", null, 1, 1, ServiceEndpointInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceEndpointInterfaceMapping_WsdlBinding(), this.getWSDLBinding(), null, "wsdlBinding", null, 1, 1, ServiceEndpointInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings(), this.getServiceEndpointMethodMapping(), null, "serviceEndpointMethodMappings", null, 0, -1, ServiceEndpointInterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rootTypeQnameEClass, RootTypeQname.class, "RootTypeQname", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableMappingEClass, VariableMapping.class, "VariableMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, VariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableMapping_JavaVariableName(), ecorePackage.getEString(), "javaVariableName", null, 0, 1, VariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableMapping_XmlElementName(), ecorePackage.getEString(), "xmlElementName", null, 0, 1, VariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableMapping_DataMember(), ecorePackage.getEBoolean(), "dataMember", null, 0, 1, VariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableMapping_XmlAttributeName(), ecorePackage.getEString(), "xmlAttributeName", null, 0, 1, VariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableMapping_XmlWildcard(), ecorePackage.getEBoolean(), "xmlWildcard", null, 0, 1, VariableMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlMessageEClass, WSDLMessage.class, "WSDLMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constructorParameterOrderEClass, ConstructorParameterOrder.class, "ConstructorParameterOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstructorParameterOrder_Id(), ecorePackage.getEString(), "id", null, 0, 1, ConstructorParameterOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstructorParameterOrder_ElementNames(), this.getElementName(), null, "elementNames", null, 1, -1, ConstructorParameterOrder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementNameEClass, ElementName.class, "ElementName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElementName_Id(), ecorePackage.getEString(), "id", null, 0, 1, ElementName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementName_Text(), ecorePackage.getEString(), "text", null, 0, 1, ElementName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlServiceNameEClass, WSDLServiceName.class, "WSDLServiceName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(portMappingEClass, PortMapping.class, "PortMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, PortMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortMapping_PortName(), ecorePackage.getEString(), "portName", null, 0, 1, PortMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortMapping_JavaPortName(), ecorePackage.getEString(), "javaPortName", null, 0, 1, PortMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlPortTypeEClass, WSDLPortType.class, "WSDLPortType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(wsdlBindingEClass, WSDLBinding.class, "WSDLBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(serviceEndpointMethodMappingEClass, ServiceEndpointMethodMapping.class, "ServiceEndpointMethodMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceEndpointMethodMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, ServiceEndpointMethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceEndpointMethodMapping_JavaMethodName(), ecorePackage.getEString(), "javaMethodName", null, 0, 1, ServiceEndpointMethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceEndpointMethodMapping_WsdlOperation(), ecorePackage.getEString(), "wsdlOperation", null, 0, 1, ServiceEndpointMethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceEndpointMethodMapping_WrappedElement(), ecorePackage.getEBoolean(), "wrappedElement", null, 0, 1, ServiceEndpointMethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceEndpointMethodMapping_MethodParamPartsMappings(), this.getMethodParamPartsMapping(), null, "methodParamPartsMappings", null, 0, -1, ServiceEndpointMethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getServiceEndpointMethodMapping_WsdlReturnValueMapping(), this.getWSDLReturnValueMapping(), null, "wsdlReturnValueMapping", null, 0, 1, ServiceEndpointMethodMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlOperationEClass, WSDLOperation.class, "WSDLOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWSDLOperation_Id(), ecorePackage.getEString(), "id", null, 0, 1, WSDLOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLOperation_WsdlOperation(), ecorePackage.getEString(), "wsdlOperation", null, 0, 1, WSDLOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodParamPartsMappingEClass, MethodParamPartsMapping.class, "MethodParamPartsMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodParamPartsMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, MethodParamPartsMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodParamPartsMapping_ParamPosition(), ecorePackage.getEString(), "paramPosition", null, 0, 1, MethodParamPartsMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMethodParamPartsMapping_ParamType(), ecorePackage.getEString(), "paramType", null, 0, 1, MethodParamPartsMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMethodParamPartsMapping_WsdlMessageMapping(), this.getWSDLMessageMapping(), null, "wsdlMessageMapping", null, 1, 1, MethodParamPartsMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlReturnValueMappingEClass, WSDLReturnValueMapping.class, "WSDLReturnValueMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWSDLReturnValueMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, WSDLReturnValueMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLReturnValueMapping_MethodReturnValue(), ecorePackage.getEString(), "methodReturnValue", null, 0, 1, WSDLReturnValueMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLReturnValueMapping_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, WSDLReturnValueMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWSDLReturnValueMapping_WsdlMessage(), this.getWSDLMessage(), null, "wsdlMessage", null, 1, 1, WSDLReturnValueMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlMessageMappingEClass, WSDLMessageMapping.class, "WSDLMessageMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWSDLMessageMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, WSDLMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLMessageMapping_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, WSDLMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLMessageMapping_ParameterMode(), ecorePackage.getEString(), "parameterMode", null, 0, 1, WSDLMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLMessageMapping_SoapHeader(), ecorePackage.getEBoolean(), "soapHeader", null, 0, 1, WSDLMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWSDLMessageMapping_WsdlMessage(), this.getWSDLMessage(), null, "wsdlMessage", null, 1, 1, WSDLMessageMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wsdlMessagePartNameEClass, WSDLMessagePartName.class, "WSDLMessagePartName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWSDLMessagePartName_Id(), ecorePackage.getEString(), "id", null, 0, 1, WSDLMessagePartName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWSDLMessagePartName_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, WSDLMessagePartName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interfaceMappingEClass, InterfaceMapping.class, "InterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //JaxrpcmapPackageImpl
