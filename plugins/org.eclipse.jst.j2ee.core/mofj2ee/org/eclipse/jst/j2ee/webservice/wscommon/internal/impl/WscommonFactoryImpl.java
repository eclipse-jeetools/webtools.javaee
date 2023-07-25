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
package org.eclipse.jst.j2ee.webservice.wscommon.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;
import org.eclipse.jst.j2ee.webservice.wscommon.PortName;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPRole;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WscommonFactoryImpl extends EFactoryImpl implements WscommonFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WscommonFactory init() {
		try {
			WscommonFactory theWscommonFactory = (WscommonFactory)EPackage.Registry.INSTANCE.getEFactory("wscommon.xmi");  //$NON-NLS-1$
			if (theWscommonFactory != null) {
				return theWscommonFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WscommonFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WscommonFactoryImpl() {
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
			case WscommonPackage.INIT_PARAM: return createInitParam();
			case WscommonPackage.SOAP_HEADER: return createSOAPHeader();
			case WscommonPackage.SOAP_ROLE: return createSOAPRole();
			case WscommonPackage.PORT_NAME: return createPortName();
			case WscommonPackage.DESCRIPTION_TYPE: return createDescriptionType();
			case WscommonPackage.DISPLAY_NAME_TYPE: return createDisplayNameType();
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
public InitParam createInitParam() {
		InitParamImpl initParam = new InitParamImpl();
		return initParam;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public SOAPHeader createSOAPHeader() {
		SOAPHeaderImpl soapHeader = new SOAPHeaderImpl();
		return soapHeader;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public SOAPRole createSOAPRole() {
		SOAPRoleImpl soapRole = new SOAPRoleImpl();
		return soapRole;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public PortName createPortName() {
		PortNameImpl portName = new PortNameImpl();
		return portName;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public DescriptionType createDescriptionType() {
		DescriptionTypeImpl descriptionType = new DescriptionTypeImpl();
		return descriptionType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public DisplayNameType createDisplayNameType() {
		DisplayNameTypeImpl displayNameType = new DisplayNameTypeImpl();
		return displayNameType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public WscommonPackage getWscommonPackage() {
		return (WscommonPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
  public static WscommonPackage getPackage() {
		return WscommonPackage.eINSTANCE;
	}

} //WscommonFactoryImpl
