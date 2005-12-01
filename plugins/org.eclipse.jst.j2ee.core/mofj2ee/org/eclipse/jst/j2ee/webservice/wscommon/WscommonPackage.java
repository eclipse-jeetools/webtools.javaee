/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wscommon;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.common.CommonPackage;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * **
 * <p>
 * <b>Note:</b> This class/interface is part of an interim API that is still under development and expected to
 * change significantly before reaching stability. It is being made available at this early stage to solicit feedback
 * from pioneering adopters on the understanding that any code that uses this API will almost certainly be broken
 * (repeatedly) as the API evolves.
 * </p>
 *	
 *
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory
 * @model kind="package"
 * @generated
 * @since 1.0 */
public interface WscommonPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNAME = "wscommon";//$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNS_URI = "wscommon.xmi";//$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  String eNS_PREFIX = "org.eclipse.jst.j2ee.webservice.wscommon";//$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  WscommonPackage eINSTANCE = org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.InitParamImpl <em>Init Param</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.InitParamImpl
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl#getInitParam()
	 * @generated
	 */
  int INIT_PARAM = 0;

	/**
	 * The feature id for the '<em><b>Param Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int INIT_PARAM__PARAM_NAME = 0;

	/**
	 * The feature id for the '<em><b>Param Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int INIT_PARAM__PARAM_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int INIT_PARAM__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Description Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int INIT_PARAM__DESCRIPTION_TYPES = 3;

	/**
	 * The number of structural features of the the '<em>Init Param</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int INIT_PARAM_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.SOAPHeaderImpl <em>SOAP Header</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.SOAPHeaderImpl
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl#getSOAPHeader()
	 * @generated
	 */
  int SOAP_HEADER = 1;

	/**
	 * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_HEADER__NAMESPACE_URI = CommonPackage.QNAME__NAMESPACE_URI;

	/**
	 * The feature id for the '<em><b>Local Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_HEADER__LOCAL_PART = CommonPackage.QNAME__LOCAL_PART;

	/**
	 * The feature id for the '<em><b>Combined QName</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_HEADER__COMBINED_QNAME = CommonPackage.QNAME__COMBINED_QNAME;

	/**
	 * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_HEADER__INTERNAL_PREFIX_OR_NS_URI = CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI;

	/**
	 * The number of structural features of the the '<em>SOAP Header</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_HEADER_FEATURE_COUNT = CommonPackage.QNAME_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.SOAPRoleImpl <em>SOAP Role</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.SOAPRoleImpl
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl#getSOAPRole()
	 * @generated
	 */
  int SOAP_ROLE = 2;

	/**
	 * The feature id for the '<em><b>Soap Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_ROLE__SOAP_ROLE = 0;

	/**
	 * The number of structural features of the the '<em>SOAP Role</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int SOAP_ROLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.PortNameImpl <em>Port Name</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.PortNameImpl
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl#getPortName()
	 * @generated
	 */
  int PORT_NAME = 3;

	/**
	 * The feature id for the '<em><b>Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PORT_NAME__PORT_NAME = 0;

	/**
	 * The number of structural features of the the '<em>Port Name</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int PORT_NAME_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.DescriptionTypeImpl <em>Description Type</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.DescriptionTypeImpl
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl#getDescriptionType()
	 * @generated
	 */
  int DESCRIPTION_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int DESCRIPTION_TYPE__LANG = CommonPackage.DESCRIPTION__LANG;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int DESCRIPTION_TYPE__VALUE = CommonPackage.DESCRIPTION__VALUE;

	/**
	 * The number of structural features of the the '<em>Description Type</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int DESCRIPTION_TYPE_FEATURE_COUNT = CommonPackage.DESCRIPTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.DisplayNameTypeImpl <em>Display Name Type</em>}' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.DisplayNameTypeImpl
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl#getDisplayNameType()
	 * @generated
	 */
  int DISPLAY_NAME_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int DISPLAY_NAME_TYPE__LANG = CommonPackage.DISPLAY_NAME__LANG;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int DISPLAY_NAME_TYPE__VALUE = CommonPackage.DISPLAY_NAME__VALUE;

	/**
	 * The number of structural features of the the '<em>Display Name Type</em>' class.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int DISPLAY_NAME_TYPE_FEATURE_COUNT = CommonPackage.DISPLAY_NAME_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam <em>Init Param</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Init Param</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.InitParam
	 * @generated
	 */
  EClass getInitParam();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamName <em>Param Name</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Param Name</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamName()
	 * @see #getInitParam()
	 * @generated
	 */
  EAttribute getInitParam_ParamName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamValue <em>Param Value</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Param Value</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamValue()
	 * @see #getInitParam()
	 * @generated
	 */
  EAttribute getInitParam_ParamValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescription()
	 * @see #getInitParam()
	 * @generated
	 */
  EAttribute getInitParam_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescriptionTypes <em>Description Types</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description Types</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescriptionTypes()
	 * @see #getInitParam()
	 * @generated
	 */
  EReference getInitParam_DescriptionTypes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader <em>SOAP Header</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SOAP Header</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader
	 * @generated
	 */
  EClass getSOAPHeader();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole <em>SOAP Role</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SOAP Role</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole
	 * @generated
	 */
  EClass getSOAPRole();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole#getSoapRole <em>Soap Role</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Soap Role</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole#getSoapRole()
	 * @see #getSOAPRole()
	 * @generated
	 */
  EAttribute getSOAPRole_SoapRole();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webservice.wscommon.PortName <em>Port Name</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Name</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.PortName
	 * @generated
	 */
  EClass getPortName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webservice.wscommon.PortName#getPortName <em>Port Name</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port Name</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.PortName#getPortName()
	 * @see #getPortName()
	 * @generated
	 */
  EAttribute getPortName_PortName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType <em>Description Type</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description Type</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType
	 * @generated
	 */
  EClass getDescriptionType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType <em>Display Name Type</em>}'.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Display Name Type</em>'.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType
	 * @generated
	 */
  EClass getDisplayNameType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
  WscommonFactory getWscommonFactory();

} //WscommonPackage
