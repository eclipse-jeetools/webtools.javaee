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
package org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.common.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.impl.WsddPackageImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ClassType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ElementName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaMethodName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaPortName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaVariableName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodReturnValue;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ParamPosition;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ParamType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ParameterMode;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.QnameScope;
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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.XMLElementName;


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
  private EClass packageTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass classTypeEClass = null;

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
  private EClass qnameScopeEClass = null;

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
  private EClass exceptionTypeEClass = null;

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
  private EClass javaVariableNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xmlElementNameEClass = null;

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
  private EClass javaPortNameEClass = null;

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
  private EClass javaMethodNameEClass = null;

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
  private EClass paramPositionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass paramTypeEClass = null;

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
  private EClass parameterModeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass methodReturnValueEClass = null;

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
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private JaxrpcmapPackageImpl()
  {
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
  public static JaxrpcmapPackage init()
  {
    if (isInited) return (JaxrpcmapPackage)EPackage.Registry.INSTANCE.getEPackage(JaxrpcmapPackage.eNS_URI);

    // Obtain or create and register package.
    JaxrpcmapPackageImpl theJaxrpcmapPackage = (JaxrpcmapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new JaxrpcmapPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    CommonPackageImpl.init();

    // Obtain or create and register interdependencies
    WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackage.eINSTANCE);
    WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackage.eINSTANCE);

    // Step 1: create meta-model objects
    theJaxrpcmapPackage.createPackageContents();
    theWscommonPackage.createPackageContents();
    theWsddPackage.createPackageContents();

    // Step 2: complete initialization
    theJaxrpcmapPackage.initializePackageContents();
    theWscommonPackage.initializePackageContents();
    theWsddPackage.initializePackageContents();

    return theJaxrpcmapPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJavaWSDLMapping()
  {
    return javaWSDLMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaWSDLMapping_Id()
  {
    return (EAttribute)javaWSDLMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJavaWSDLMapping_PackageMappings()
  {
    return (EReference)javaWSDLMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJavaWSDLMapping_JavaXMLTypeMappings()
  {
    return (EReference)javaWSDLMappingEClass.getEReferences().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJavaWSDLMapping_ExceptionMappings()
  {
    return (EReference)javaWSDLMappingEClass.getEReferences().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJavaWSDLMapping_InterfaceMappings()
  {
    return (EReference)javaWSDLMappingEClass.getEReferences().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackageMapping()
  {
    return packageMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageMapping_Id()
  {
    return (EAttribute)packageMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageMapping_PackageType()
  {
    return (EAttribute)packageMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageMapping_NamespaceURI()
  {
    return (EAttribute)packageMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJavaXMLTypeMapping()
  {
    return javaXMLTypeMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaXMLTypeMapping_Id()
  {
    return (EAttribute)javaXMLTypeMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaXMLTypeMapping_ClassType()
  {
    return (EAttribute)javaXMLTypeMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaXMLTypeMapping_QnameScope()
  {
    return (EAttribute)javaXMLTypeMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaXMLTypeMapping_AnonymousTypeQname()
  {
    return (EAttribute)javaXMLTypeMappingEClass.getEAttributes().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJavaXMLTypeMapping_RootTypeQname()
  {
    return (EReference)javaXMLTypeMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getJavaXMLTypeMapping_VariableMappings()
  {
    return (EReference)javaXMLTypeMappingEClass.getEReferences().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExceptionMapping()
  {
    return exceptionMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExceptionMapping_Id()
  {
    return (EAttribute)exceptionMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExceptionMapping_ExceptionType()
  {
    return (EAttribute)exceptionMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExceptionMapping_WsdlMessagePartName()
  {
    return (EAttribute)exceptionMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExceptionMapping_WsdlMessage()
  {
    return (EReference)exceptionMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExceptionMapping_ConstructorParameterOrder()
  {
    return (EReference)exceptionMappingEClass.getEReferences().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getServiceInterfaceMapping()
  {
    return serviceInterfaceMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceInterfaceMapping_Id()
  {
    return (EAttribute)serviceInterfaceMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceInterfaceMapping_ServiceInterface()
  {
    return (EAttribute)serviceInterfaceMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceInterfaceMapping_WsdlServiceName()
  {
    return (EReference)serviceInterfaceMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceInterfaceMapping_PortMappings()
  {
    return (EReference)serviceInterfaceMappingEClass.getEReferences().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getServiceEndpointInterfaceMapping()
  {
    return serviceEndpointInterfaceMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceEndpointInterfaceMapping_Id()
  {
    return (EAttribute)serviceEndpointInterfaceMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceEndpointInterfaceMapping_ServiceEndpointInterface()
  {
    return (EAttribute)serviceEndpointInterfaceMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceEndpointInterfaceMapping_WsdlPortType()
  {
    return (EReference)serviceEndpointInterfaceMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceEndpointInterfaceMapping_WsdlBinding()
  {
    return (EReference)serviceEndpointInterfaceMappingEClass.getEReferences().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings()
  {
    return (EReference)serviceEndpointInterfaceMappingEClass.getEReferences().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackageType()
  {
    return packageTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageType_Id()
  {
    return (EAttribute)packageTypeEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageType_PackageType()
  {
    return (EAttribute)packageTypeEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClassType()
  {
    return classTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassType_Id()
  {
    return (EAttribute)classTypeEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassType_ClassType()
  {
    return (EAttribute)classTypeEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRootTypeQname()
  {
    return rootTypeQnameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQnameScope()
  {
    return qnameScopeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQnameScope_Id()
  {
    return (EAttribute)qnameScopeEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getQnameScope_QnameScope()
  {
    return (EAttribute)qnameScopeEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableMapping()
  {
    return variableMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableMapping_Id()
  {
    return (EAttribute)variableMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableMapping_JavaVariableName()
  {
    return (EAttribute)variableMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableMapping_XmlElementName()
  {
    return (EAttribute)variableMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableMapping_DataMember()
  {
    return (EAttribute)variableMappingEClass.getEAttributes().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableMapping_XmlAttributeName()
  {
    return (EAttribute)variableMappingEClass.getEAttributes().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableMapping_XmlWildcard()
  {
    return (EAttribute)variableMappingEClass.getEAttributes().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExceptionType()
  {
    return exceptionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExceptionType_Id()
  {
    return (EAttribute)exceptionTypeEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExceptionType_Name()
  {
    return (EAttribute)exceptionTypeEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLMessage()
  {
    return wsdlMessageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstructorParameterOrder()
  {
    return constructorParameterOrderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstructorParameterOrder_Id()
  {
    return (EAttribute)constructorParameterOrderEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstructorParameterOrder_ElementNames()
  {
    return (EReference)constructorParameterOrderEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJavaVariableName()
  {
    return javaVariableNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaVariableName_Id()
  {
    return (EAttribute)javaVariableNameEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaVariableName_JavaVariableName()
  {
    return (EAttribute)javaVariableNameEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXMLElementName()
  {
    return xmlElementNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLElementName_Id()
  {
    return (EAttribute)xmlElementNameEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLElementName_XmlElementName()
  {
    return (EAttribute)xmlElementNameEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementName()
  {
    return elementNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementName_Id()
  {
    return (EAttribute)elementNameEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementName_Text()
  {
    return (EAttribute)elementNameEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLServiceName()
  {
    return wsdlServiceNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPortMapping()
  {
    return portMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPortMapping_Id()
  {
    return (EAttribute)portMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPortMapping_PortName()
  {
    return (EAttribute)portMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPortMapping_JavaPortName()
  {
    return (EAttribute)portMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJavaPortName()
  {
    return javaPortNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaPortName_Id()
  {
    return (EAttribute)javaPortNameEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaPortName_JavaPortName()
  {
    return (EAttribute)javaPortNameEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLPortType()
  {
    return wsdlPortTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLBinding()
  {
    return wsdlBindingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getServiceEndpointMethodMapping()
  {
    return serviceEndpointMethodMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceEndpointMethodMapping_Id()
  {
    return (EAttribute)serviceEndpointMethodMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceEndpointMethodMapping_JavaMethodName()
  {
    return (EAttribute)serviceEndpointMethodMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceEndpointMethodMapping_WsdlOperation()
  {
    return (EAttribute)serviceEndpointMethodMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getServiceEndpointMethodMapping_WrappedElement()
  {
    return (EAttribute)serviceEndpointMethodMappingEClass.getEAttributes().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceEndpointMethodMapping_MethodParamPartsMappings()
  {
    return (EReference)serviceEndpointMethodMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getServiceEndpointMethodMapping_WsdlReturnValueMapping()
  {
    return (EReference)serviceEndpointMethodMappingEClass.getEReferences().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJavaMethodName()
  {
    return javaMethodNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaMethodName_Id()
  {
    return (EAttribute)javaMethodNameEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJavaMethodName_JavaMethodName()
  {
    return (EAttribute)javaMethodNameEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLOperation()
  {
    return wsdlOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLOperation_Id()
  {
    return (EAttribute)wsdlOperationEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLOperation_WsdlOperation()
  {
    return (EAttribute)wsdlOperationEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMethodParamPartsMapping()
  {
    return methodParamPartsMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMethodParamPartsMapping_Id()
  {
    return (EAttribute)methodParamPartsMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMethodParamPartsMapping_ParamPosition()
  {
    return (EAttribute)methodParamPartsMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMethodParamPartsMapping_ParamType()
  {
    return (EAttribute)methodParamPartsMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMethodParamPartsMapping_WsdlMessageMapping()
  {
    return (EReference)methodParamPartsMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLReturnValueMapping()
  {
    return wsdlReturnValueMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLReturnValueMapping_Id()
  {
    return (EAttribute)wsdlReturnValueMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLReturnValueMapping_MethodReturnValue()
  {
    return (EAttribute)wsdlReturnValueMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLReturnValueMapping_WsdlMessagePartName()
  {
    return (EAttribute)wsdlReturnValueMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWSDLReturnValueMapping_WsdlMessage()
  {
    return (EReference)wsdlReturnValueMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParamPosition()
  {
    return paramPositionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParamPosition_Id()
  {
    return (EAttribute)paramPositionEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParamPosition_ParamPosition()
  {
    return (EAttribute)paramPositionEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParamType()
  {
    return paramTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParamType_Id()
  {
    return (EAttribute)paramTypeEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParamType_ParamType()
  {
    return (EAttribute)paramTypeEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLMessageMapping()
  {
    return wsdlMessageMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLMessageMapping_Id()
  {
    return (EAttribute)wsdlMessageMappingEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLMessageMapping_WsdlMessagePartName()
  {
    return (EAttribute)wsdlMessageMappingEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLMessageMapping_ParameterMode()
  {
    return (EAttribute)wsdlMessageMappingEClass.getEAttributes().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLMessageMapping_SoapHeader()
  {
    return (EAttribute)wsdlMessageMappingEClass.getEAttributes().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWSDLMessageMapping_WsdlMessage()
  {
    return (EReference)wsdlMessageMappingEClass.getEReferences().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWSDLMessagePartName()
  {
    return wsdlMessagePartNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLMessagePartName_Id()
  {
    return (EAttribute)wsdlMessagePartNameEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWSDLMessagePartName_WsdlMessagePartName()
  {
    return (EAttribute)wsdlMessagePartNameEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterMode()
  {
    return parameterModeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameterMode_Id()
  {
    return (EAttribute)parameterModeEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameterMode_ParameterMode()
  {
    return (EAttribute)parameterModeEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMethodReturnValue()
  {
    return methodReturnValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMethodReturnValue_Id()
  {
    return (EAttribute)methodReturnValueEClass.getEAttributes().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMethodReturnValue_MethodReturnValue()
  {
    return (EAttribute)methodReturnValueEClass.getEAttributes().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInterfaceMapping()
  {
    return interfaceMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JaxrpcmapFactory getJaxrpcmapFactory()
  {
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
  public void createPackageContents()
  {
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

    packageTypeEClass = createEClass(PACKAGE_TYPE);
    createEAttribute(packageTypeEClass, PACKAGE_TYPE__ID);
    createEAttribute(packageTypeEClass, PACKAGE_TYPE__PACKAGE_TYPE);

    classTypeEClass = createEClass(CLASS_TYPE);
    createEAttribute(classTypeEClass, CLASS_TYPE__ID);
    createEAttribute(classTypeEClass, CLASS_TYPE__CLASS_TYPE);

    rootTypeQnameEClass = createEClass(ROOT_TYPE_QNAME);

    qnameScopeEClass = createEClass(QNAME_SCOPE);
    createEAttribute(qnameScopeEClass, QNAME_SCOPE__ID);
    createEAttribute(qnameScopeEClass, QNAME_SCOPE__QNAME_SCOPE);

    variableMappingEClass = createEClass(VARIABLE_MAPPING);
    createEAttribute(variableMappingEClass, VARIABLE_MAPPING__ID);
    createEAttribute(variableMappingEClass, VARIABLE_MAPPING__JAVA_VARIABLE_NAME);
    createEAttribute(variableMappingEClass, VARIABLE_MAPPING__XML_ELEMENT_NAME);
    createEAttribute(variableMappingEClass, VARIABLE_MAPPING__DATA_MEMBER);
    createEAttribute(variableMappingEClass, VARIABLE_MAPPING__XML_ATTRIBUTE_NAME);
    createEAttribute(variableMappingEClass, VARIABLE_MAPPING__XML_WILDCARD);

    exceptionTypeEClass = createEClass(EXCEPTION_TYPE);
    createEAttribute(exceptionTypeEClass, EXCEPTION_TYPE__ID);
    createEAttribute(exceptionTypeEClass, EXCEPTION_TYPE__NAME);

    wsdlMessageEClass = createEClass(WSDL_MESSAGE);

    constructorParameterOrderEClass = createEClass(CONSTRUCTOR_PARAMETER_ORDER);
    createEAttribute(constructorParameterOrderEClass, CONSTRUCTOR_PARAMETER_ORDER__ID);
    createEReference(constructorParameterOrderEClass, CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES);

    javaVariableNameEClass = createEClass(JAVA_VARIABLE_NAME);
    createEAttribute(javaVariableNameEClass, JAVA_VARIABLE_NAME__ID);
    createEAttribute(javaVariableNameEClass, JAVA_VARIABLE_NAME__JAVA_VARIABLE_NAME);

    xmlElementNameEClass = createEClass(XML_ELEMENT_NAME);
    createEAttribute(xmlElementNameEClass, XML_ELEMENT_NAME__ID);
    createEAttribute(xmlElementNameEClass, XML_ELEMENT_NAME__XML_ELEMENT_NAME);

    elementNameEClass = createEClass(ELEMENT_NAME);
    createEAttribute(elementNameEClass, ELEMENT_NAME__ID);
    createEAttribute(elementNameEClass, ELEMENT_NAME__TEXT);

    wsdlServiceNameEClass = createEClass(WSDL_SERVICE_NAME);

    portMappingEClass = createEClass(PORT_MAPPING);
    createEAttribute(portMappingEClass, PORT_MAPPING__ID);
    createEAttribute(portMappingEClass, PORT_MAPPING__PORT_NAME);
    createEAttribute(portMappingEClass, PORT_MAPPING__JAVA_PORT_NAME);

    javaPortNameEClass = createEClass(JAVA_PORT_NAME);
    createEAttribute(javaPortNameEClass, JAVA_PORT_NAME__ID);
    createEAttribute(javaPortNameEClass, JAVA_PORT_NAME__JAVA_PORT_NAME);

    wsdlPortTypeEClass = createEClass(WSDL_PORT_TYPE);

    wsdlBindingEClass = createEClass(WSDL_BINDING);

    serviceEndpointMethodMappingEClass = createEClass(SERVICE_ENDPOINT_METHOD_MAPPING);
    createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__ID);
    createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME);
    createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION);
    createEAttribute(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT);
    createEReference(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS);
    createEReference(serviceEndpointMethodMappingEClass, SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING);

    javaMethodNameEClass = createEClass(JAVA_METHOD_NAME);
    createEAttribute(javaMethodNameEClass, JAVA_METHOD_NAME__ID);
    createEAttribute(javaMethodNameEClass, JAVA_METHOD_NAME__JAVA_METHOD_NAME);

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

    paramPositionEClass = createEClass(PARAM_POSITION);
    createEAttribute(paramPositionEClass, PARAM_POSITION__ID);
    createEAttribute(paramPositionEClass, PARAM_POSITION__PARAM_POSITION);

    paramTypeEClass = createEClass(PARAM_TYPE);
    createEAttribute(paramTypeEClass, PARAM_TYPE__ID);
    createEAttribute(paramTypeEClass, PARAM_TYPE__PARAM_TYPE);

    wsdlMessageMappingEClass = createEClass(WSDL_MESSAGE_MAPPING);
    createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__ID);
    createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__WSDL_MESSAGE_PART_NAME);
    createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__PARAMETER_MODE);
    createEAttribute(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__SOAP_HEADER);
    createEReference(wsdlMessageMappingEClass, WSDL_MESSAGE_MAPPING__WSDL_MESSAGE);

    wsdlMessagePartNameEClass = createEClass(WSDL_MESSAGE_PART_NAME);
    createEAttribute(wsdlMessagePartNameEClass, WSDL_MESSAGE_PART_NAME__ID);
    createEAttribute(wsdlMessagePartNameEClass, WSDL_MESSAGE_PART_NAME__WSDL_MESSAGE_PART_NAME);

    parameterModeEClass = createEClass(PARAMETER_MODE);
    createEAttribute(parameterModeEClass, PARAMETER_MODE__ID);
    createEAttribute(parameterModeEClass, PARAMETER_MODE__PARAMETER_MODE);

    methodReturnValueEClass = createEClass(METHOD_RETURN_VALUE);
    createEAttribute(methodReturnValueEClass, METHOD_RETURN_VALUE__ID);
    createEAttribute(methodReturnValueEClass, METHOD_RETURN_VALUE__METHOD_RETURN_VALUE);

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
  public void initializePackageContents()
  {
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
    initEClass(javaWSDLMappingEClass, JavaWSDLMapping.class, "JavaWSDLMapping", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
    initEAttribute(getJavaWSDLMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getJavaWSDLMapping_PackageMappings(), this.getPackageMapping(), null, "packageMappings", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getJavaWSDLMapping_JavaXMLTypeMappings(), this.getJavaXMLTypeMapping(), null, "javaXMLTypeMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getJavaWSDLMapping_ExceptionMappings(), this.getExceptionMapping(), null, "exceptionMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getJavaWSDLMapping_InterfaceMappings(), this.getInterfaceMapping(), null, "interfaceMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(packageMappingEClass, PackageMapping.class, "PackageMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getPackageMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getPackageMapping_PackageType(), ecorePackage.getEString(), "packageType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getPackageMapping_NamespaceURI(), ecorePackage.getEString(), "namespaceURI", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(javaXMLTypeMappingEClass, JavaXMLTypeMapping.class, "JavaXMLTypeMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getJavaXMLTypeMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getJavaXMLTypeMapping_ClassType(), ecorePackage.getEString(), "classType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getJavaXMLTypeMapping_QnameScope(), ecorePackage.getEString(), "qnameScope", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getJavaXMLTypeMapping_AnonymousTypeQname(), ecorePackage.getEString(), "anonymousTypeQname", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getJavaXMLTypeMapping_RootTypeQname(), this.getRootTypeQname(), null, "rootTypeQname", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getJavaXMLTypeMapping_VariableMappings(), this.getVariableMapping(), null, "variableMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(exceptionMappingEClass, ExceptionMapping.class, "ExceptionMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getExceptionMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getExceptionMapping_ExceptionType(), ecorePackage.getEString(), "exceptionType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getExceptionMapping_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getExceptionMapping_WsdlMessage(), this.getWSDLMessage(), null, "wsdlMessage", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getExceptionMapping_ConstructorParameterOrder(), this.getConstructorParameterOrder(), null, "constructorParameterOrder", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(serviceInterfaceMappingEClass, ServiceInterfaceMapping.class, "ServiceInterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getServiceInterfaceMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getServiceInterfaceMapping_ServiceInterface(), ecorePackage.getEString(), "serviceInterface", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceInterfaceMapping_WsdlServiceName(), this.getWSDLServiceName(), null, "wsdlServiceName", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceInterfaceMapping_PortMappings(), this.getPortMapping(), null, "portMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(serviceEndpointInterfaceMappingEClass, ServiceEndpointInterfaceMapping.class, "ServiceEndpointInterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getServiceEndpointInterfaceMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getServiceEndpointInterfaceMapping_ServiceEndpointInterface(), ecorePackage.getEString(), "serviceEndpointInterface", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceEndpointInterfaceMapping_WsdlPortType(), this.getWSDLPortType(), null, "wsdlPortType", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceEndpointInterfaceMapping_WsdlBinding(), this.getWSDLBinding(), null, "wsdlBinding", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings(), this.getServiceEndpointMethodMapping(), null, "serviceEndpointMethodMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(packageTypeEClass, PackageType.class, "PackageType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getPackageType_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getPackageType_PackageType(), ecorePackage.getEString(), "packageType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(classTypeEClass, ClassType.class, "ClassType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getClassType_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getClassType_ClassType(), ecorePackage.getEString(), "classType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(rootTypeQnameEClass, RootTypeQname.class, "RootTypeQname", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

    initEClass(qnameScopeEClass, QnameScope.class, "QnameScope", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getQnameScope_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getQnameScope_QnameScope(), ecorePackage.getEString(), "qnameScope", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(variableMappingEClass, VariableMapping.class, "VariableMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getVariableMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getVariableMapping_JavaVariableName(), ecorePackage.getEString(), "javaVariableName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getVariableMapping_XmlElementName(), ecorePackage.getEString(), "xmlElementName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getVariableMapping_DataMember(), ecorePackage.getEBoolean(), "dataMember", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getVariableMapping_XmlAttributeName(), ecorePackage.getEString(), "xmlAttributeName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getVariableMapping_XmlWildcard(), ecorePackage.getEBoolean(), "xmlWildcard", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(exceptionTypeEClass, ExceptionType.class, "ExceptionType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getExceptionType_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getExceptionType_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlMessageEClass, WSDLMessage.class, "WSDLMessage", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

    initEClass(constructorParameterOrderEClass, ConstructorParameterOrder.class, "ConstructorParameterOrder", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getConstructorParameterOrder_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getConstructorParameterOrder_ElementNames(), this.getElementName(), null, "elementNames", null, 1, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(javaVariableNameEClass, JavaVariableName.class, "JavaVariableName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getJavaVariableName_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getJavaVariableName_JavaVariableName(), ecorePackage.getEString(), "javaVariableName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(xmlElementNameEClass, XMLElementName.class, "XMLElementName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getXMLElementName_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getXMLElementName_XmlElementName(), ecorePackage.getEString(), "xmlElementName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(elementNameEClass, ElementName.class, "ElementName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getElementName_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getElementName_Text(), ecorePackage.getEString(), "text", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlServiceNameEClass, WSDLServiceName.class, "WSDLServiceName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

    initEClass(portMappingEClass, PortMapping.class, "PortMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getPortMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getPortMapping_PortName(), ecorePackage.getEString(), "portName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getPortMapping_JavaPortName(), ecorePackage.getEString(), "javaPortName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(javaPortNameEClass, JavaPortName.class, "JavaPortName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getJavaPortName_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getJavaPortName_JavaPortName(), ecorePackage.getEString(), "javaPortName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlPortTypeEClass, WSDLPortType.class, "WSDLPortType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

    initEClass(wsdlBindingEClass, WSDLBinding.class, "WSDLBinding", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

    initEClass(serviceEndpointMethodMappingEClass, ServiceEndpointMethodMapping.class, "ServiceEndpointMethodMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getServiceEndpointMethodMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getServiceEndpointMethodMapping_JavaMethodName(), ecorePackage.getEString(), "javaMethodName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getServiceEndpointMethodMapping_WsdlOperation(), ecorePackage.getEString(), "wsdlOperation", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getServiceEndpointMethodMapping_WrappedElement(), ecorePackage.getEBoolean(), "wrappedElement", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceEndpointMethodMapping_MethodParamPartsMappings(), this.getMethodParamPartsMapping(), null, "methodParamPartsMappings", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getServiceEndpointMethodMapping_WsdlReturnValueMapping(), this.getWSDLReturnValueMapping(), null, "wsdlReturnValueMapping", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(javaMethodNameEClass, JavaMethodName.class, "JavaMethodName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getJavaMethodName_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getJavaMethodName_JavaMethodName(), ecorePackage.getEString(), "javaMethodName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlOperationEClass, WSDLOperation.class, "WSDLOperation", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getWSDLOperation_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLOperation_WsdlOperation(), ecorePackage.getEString(), "wsdlOperation", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(methodParamPartsMappingEClass, MethodParamPartsMapping.class, "MethodParamPartsMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getMethodParamPartsMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getMethodParamPartsMapping_ParamPosition(), ecorePackage.getEString(), "paramPosition", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getMethodParamPartsMapping_ParamType(), ecorePackage.getEString(), "paramType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getMethodParamPartsMapping_WsdlMessageMapping(), this.getWSDLMessageMapping(), null, "wsdlMessageMapping", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlReturnValueMappingEClass, WSDLReturnValueMapping.class, "WSDLReturnValueMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getWSDLReturnValueMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLReturnValueMapping_MethodReturnValue(), ecorePackage.getEString(), "methodReturnValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLReturnValueMapping_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getWSDLReturnValueMapping_WsdlMessage(), this.getWSDLMessage(), null, "wsdlMessage", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(paramPositionEClass, ParamPosition.class, "ParamPosition", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getParamPosition_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getParamPosition_ParamPosition(), ecorePackage.getEString(), "paramPosition", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(paramTypeEClass, ParamType.class, "ParamType", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getParamType_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getParamType_ParamType(), ecorePackage.getEString(), "paramType", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlMessageMappingEClass, WSDLMessageMapping.class, "WSDLMessageMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getWSDLMessageMapping_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLMessageMapping_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLMessageMapping_ParameterMode(), ecorePackage.getEString(), "parameterMode", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLMessageMapping_SoapHeader(), ecorePackage.getEBoolean(), "soapHeader", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEReference(getWSDLMessageMapping_WsdlMessage(), this.getWSDLMessage(), null, "wsdlMessage", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(wsdlMessagePartNameEClass, WSDLMessagePartName.class, "WSDLMessagePartName", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getWSDLMessagePartName_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getWSDLMessagePartName_WsdlMessagePartName(), ecorePackage.getEString(), "wsdlMessagePartName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(parameterModeEClass, ParameterMode.class, "ParameterMode", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getParameterMode_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getParameterMode_ParameterMode(), ecorePackage.getEString(), "parameterMode", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(methodReturnValueEClass, MethodReturnValue.class, "MethodReturnValue", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$
    initEAttribute(getMethodReturnValue_Id(), ecorePackage.getEString(), "id", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$
    initEAttribute(getMethodReturnValue_MethodReturnValue(), ecorePackage.getEString(), "methodReturnValue", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, false);//$NON-NLS-1$

    initEClass(interfaceMappingEClass, InterfaceMapping.class, "InterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE);//$NON-NLS-1$

    // Create resource
    createResource(eNS_URI);
  }
} //JaxrpcmapPackageImpl
