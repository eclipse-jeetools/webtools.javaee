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
package org.eclipse.jst.j2ee.jca.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.internal.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.AdminObject;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanismType;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.InboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.MessageAdapter;
import org.eclipse.jst.j2ee.jca.MessageListener;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;
import org.eclipse.jst.j2ee.jca.TransactionSupportKind;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.internal.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WsddPackageImpl;


public class JcaPackageImpl extends EPackageImpl implements JcaPackage, EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceAdapterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass authenticationMechanismEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityPermissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inboundResourceAdapterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outboundResourceAdapterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageAdapterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adminObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageListenerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activationSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiredConfigPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum authenticationMechanismTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transactionSupportKindEEnum = null;

		   /**
		 * @generated This field/method will be replaced during code generation.
		 */
	private JcaPackageImpl() {
		super(eNS_URI, JcaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static JcaPackage init() {
		if (isInited) return (JcaPackage)EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);

		// Obtain or create and register package
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof JcaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new JcaPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Obtain or create and register interdependencies
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage_1 = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof ApplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		TaglibPackageImpl theTaglibPackage = (TaglibPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) instanceof TaglibPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) : TaglibPackage.eINSTANCE);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof WebapplicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackage.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof Webservice_clientPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof JspPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);
		WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof WscommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackage.eINSTANCE);
		WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof WsddPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackage.eINSTANCE);

		// Create package meta-data objects
		theJcaPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theApplicationPackage_1.createPackageContents();
		theCommonPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theTaglibPackage.createPackageContents();
		theWebapplicationPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theJspPackage.createPackageContents();
		theWscommonPackage.createPackageContents();
		theWsddPackage.createPackageContents();

		// Initialize created meta-data
		theJcaPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theApplicationPackage_1.initializePackageContents();
		theCommonPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theTaglibPackage.initializePackageContents();
		theWebapplicationPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theJspPackage.initializePackageContents();
		theWscommonPackage.initializePackageContents();
		theWsddPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJcaPackage.freeze();

		return theJcaPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getConnector() {
		return connectorEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConnector_VendorName() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConnector_SpecVersion() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConnector_EisType() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConnector_Version() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getConnector_License() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getConnector_ResourceAdapter() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getLicense() {
		return licenseEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLicense_Description() {
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getLicense_Required() {
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLicense_Descriptions() {
		return (EReference)licenseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInboundResourceAdapter() {
		return inboundResourceAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInboundResourceAdapter_MessageAdapter() {
		return (EReference)inboundResourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutboundResourceAdapter() {
		return outboundResourceAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutboundResourceAdapter_ReauthenticationSupport() {
		return (EAttribute)outboundResourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutboundResourceAdapter_TransactionSupport() {
		return (EAttribute)outboundResourceAdapterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutboundResourceAdapter_ConnectionDefinitions() {
		return (EReference)outboundResourceAdapterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutboundResourceAdapter_AuthenticationMechanisms() {
		return (EReference)outboundResourceAdapterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageAdapter() {
		return messageAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageAdapter_MessageListeners() {
		return (EReference)messageAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionDefinition() {
		return connectionDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ManagedConnectionFactoryClass() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionFactoryInterface() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionFactoryImplClass() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionInterface() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionImplClass() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectionDefinition_ConfigProperties() {
		return (EReference)connectionDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdminObject() {
		return adminObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdminObject_AdminObjectInterface() {
		return (EAttribute)adminObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdminObject_AdminObjectClass() {
		return (EAttribute)adminObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdminObject_ConfigProperties() {
		return (EReference)adminObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageListener() {
		return messageListenerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageListener_MessageListenerType() {
		return (EAttribute)messageListenerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageListener_ActivationSpec() {
		return (EReference)messageListenerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivationSpec() {
		return activationSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivationSpec_ActivationSpecClass() {
		return (EAttribute)activationSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivationSpec_RequiredConfigProperties() {
		return (EReference)activationSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredConfigPropertyType() {
		return requiredConfigPropertyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredConfigPropertyType_Name() {
		return (EAttribute)requiredConfigPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiredConfigPropertyType_Descriptions() {
		return (EReference)requiredConfigPropertyTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getResourceAdapter() {
		return resourceAdapterEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_ManagedConnectionFactoryClass() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_ConnectionFactoryInterface() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_ConnectionFactoryImplClass() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_ConnectionInterface() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_ConnectionImplClass() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_TransactionSupport() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceAdapter_ReauthenticationSupport() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceAdapter_ResourceAdapterClass() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getResourceAdapter_SecurityPermissions() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getResourceAdapter_AuthenticationMechanisms() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getResourceAdapter_ConfigProperties() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_OutboundResourceAdapter() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_InboundResourceAdapter() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_AdminObjects() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getSecurityPermission() {
		return securityPermissionEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityPermission_Description() {
		return (EAttribute)securityPermissionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityPermission_Specification() {
		return (EAttribute)securityPermissionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityPermission_Descriptions() {
		return (EReference)securityPermissionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getAuthenticationMechanism() {
		return authenticationMechanismEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getAuthenticationMechanism_Description() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthenticationMechanism_AuthenticationMechanism() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getAuthenticationMechanism_AuthenticationMechanismType() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getAuthenticationMechanism_CredentialInterface() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getConfigProperty() {
		return configPropertyEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConfigProperty_Description() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConfigProperty_Name() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConfigProperty_Type() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getConfigProperty_Value() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfigProperty_Descriptions() {
		return (EReference)configPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getTransactionSupportKind() {
		return transactionSupportKindEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getAuthenticationMechanismType() {
		return authenticationMechanismTypeEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JcaFactory getJcaFactory() {
		return (JcaFactory)getEFactoryInstance();
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
		connectorEClass = createEClass(CONNECTOR);
		createEAttribute(connectorEClass, CONNECTOR__VENDOR_NAME);
		createEAttribute(connectorEClass, CONNECTOR__SPEC_VERSION);
		createEAttribute(connectorEClass, CONNECTOR__EIS_TYPE);
		createEAttribute(connectorEClass, CONNECTOR__VERSION);
		createEReference(connectorEClass, CONNECTOR__LICENSE);
		createEReference(connectorEClass, CONNECTOR__RESOURCE_ADAPTER);

		resourceAdapterEClass = createEClass(RESOURCE_ADAPTER);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__CONNECTION_INTERFACE);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__TRANSACTION_SUPPORT);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__SECURITY_PERMISSIONS);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__CONFIG_PROPERTIES);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__ADMIN_OBJECTS);

		authenticationMechanismEClass = createEClass(AUTHENTICATION_MECHANISM);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__DESCRIPTION);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE);
		createEReference(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__DESCRIPTIONS);

		configPropertyEClass = createEClass(CONFIG_PROPERTY);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__DESCRIPTION);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__NAME);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__TYPE);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__VALUE);
		createEReference(configPropertyEClass, CONFIG_PROPERTY__DESCRIPTIONS);

		securityPermissionEClass = createEClass(SECURITY_PERMISSION);
		createEAttribute(securityPermissionEClass, SECURITY_PERMISSION__DESCRIPTION);
		createEAttribute(securityPermissionEClass, SECURITY_PERMISSION__SPECIFICATION);
		createEReference(securityPermissionEClass, SECURITY_PERMISSION__DESCRIPTIONS);

		licenseEClass = createEClass(LICENSE);
		createEAttribute(licenseEClass, LICENSE__DESCRIPTION);
		createEAttribute(licenseEClass, LICENSE__REQUIRED);
		createEReference(licenseEClass, LICENSE__DESCRIPTIONS);

		inboundResourceAdapterEClass = createEClass(INBOUND_RESOURCE_ADAPTER);
		createEReference(inboundResourceAdapterEClass, INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER);

		outboundResourceAdapterEClass = createEClass(OUTBOUND_RESOURCE_ADAPTER);
		createEAttribute(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT);
		createEAttribute(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT);
		createEReference(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS);
		createEReference(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS);

		messageAdapterEClass = createEClass(MESSAGE_ADAPTER);
		createEReference(messageAdapterEClass, MESSAGE_ADAPTER__MESSAGE_LISTENERS);

		connectionDefinitionEClass = createEClass(CONNECTION_DEFINITION);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTION_INTERFACE);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS);
		createEReference(connectionDefinitionEClass, CONNECTION_DEFINITION__CONFIG_PROPERTIES);

		adminObjectEClass = createEClass(ADMIN_OBJECT);
		createEAttribute(adminObjectEClass, ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE);
		createEAttribute(adminObjectEClass, ADMIN_OBJECT__ADMIN_OBJECT_CLASS);
		createEReference(adminObjectEClass, ADMIN_OBJECT__CONFIG_PROPERTIES);

		messageListenerEClass = createEClass(MESSAGE_LISTENER);
		createEAttribute(messageListenerEClass, MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE);
		createEReference(messageListenerEClass, MESSAGE_LISTENER__ACTIVATION_SPEC);

		activationSpecEClass = createEClass(ACTIVATION_SPEC);
		createEAttribute(activationSpecEClass, ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS);
		createEReference(activationSpecEClass, ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES);

		requiredConfigPropertyTypeEClass = createEClass(REQUIRED_CONFIG_PROPERTY_TYPE);
		createEAttribute(requiredConfigPropertyTypeEClass, REQUIRED_CONFIG_PROPERTY_TYPE__NAME);
		createEReference(requiredConfigPropertyTypeEClass, REQUIRED_CONFIG_PROPERTY_TYPE__DESCRIPTIONS);

		// Create enums
		authenticationMechanismTypeEEnum = createEEnum(AUTHENTICATION_MECHANISM_TYPE);
		transactionSupportKindEEnum = createEEnum(TRANSACTION_SUPPORT_KIND);
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
		connectorEClass.getESuperTypes().add(theCommonPackage.getCompatibilityDescriptionGroup());

		// Initialize classes and features; add operations and parameters
		initEClass(connectorEClass, Connector.class, "Connector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnector_VendorName(), ecorePackage.getEString(), "vendorName", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnector_SpecVersion(), ecorePackage.getEString(), "specVersion", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnector_EisType(), ecorePackage.getEString(), "eisType", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnector_Version(), ecorePackage.getEString(), "version", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnector_License(), this.getLicense(), null, "license", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnector_ResourceAdapter(), this.getResourceAdapter(), null, "resourceAdapter", null, 1, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceAdapterEClass, ResourceAdapter.class, "ResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceAdapter_ManagedConnectionFactoryClass(), ecorePackage.getEString(), "managedConnectionFactoryClass", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_ConnectionFactoryInterface(), ecorePackage.getEString(), "connectionFactoryInterface", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_ConnectionFactoryImplClass(), ecorePackage.getEString(), "connectionFactoryImplClass", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_ConnectionInterface(), ecorePackage.getEString(), "connectionInterface", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_ConnectionImplClass(), ecorePackage.getEString(), "connectionImplClass", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_TransactionSupport(), this.getTransactionSupportKind(), "transactionSupport", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_ReauthenticationSupport(), ecorePackage.getEBoolean(), "reauthenticationSupport", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceAdapter_ResourceAdapterClass(), ecorePackage.getEString(), "resourceAdapterClass", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_SecurityPermissions(), this.getSecurityPermission(), null, "securityPermissions", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_AuthenticationMechanisms(), this.getAuthenticationMechanism(), null, "authenticationMechanisms", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_ConfigProperties(), this.getConfigProperty(), null, "configProperties", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_OutboundResourceAdapter(), this.getOutboundResourceAdapter(), null, "outboundResourceAdapter", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_InboundResourceAdapter(), this.getInboundResourceAdapter(), null, "inboundResourceAdapter", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceAdapter_AdminObjects(), this.getAdminObject(), null, "adminObjects", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(authenticationMechanismEClass, AuthenticationMechanism.class, "AuthenticationMechanism", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAuthenticationMechanism_Description(), ecorePackage.getEString(), "description", null, 0, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthenticationMechanism_AuthenticationMechanism(), ecorePackage.getEString(), "authenticationMechanism", null, 0, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthenticationMechanism_AuthenticationMechanismType(), this.getAuthenticationMechanismType(), "authenticationMechanismType", null, 0, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthenticationMechanism_CredentialInterface(), ecorePackage.getEString(), "credentialInterface", null, 0, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthenticationMechanism_CustomAuthMechType(), ecorePackage.getEString(), "customAuthMechType", null, 0, 1, AuthenticationMechanism.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAuthenticationMechanism_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configPropertyEClass, ConfigProperty.class, "ConfigProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfigProperty_Description(), ecorePackage.getEString(), "description", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfigProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfigProperty_Type(), ecorePackage.getEString(), "type", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfigProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfigProperty_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(securityPermissionEClass, SecurityPermission.class, "SecurityPermission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSecurityPermission_Description(), ecorePackage.getEString(), "description", null, 0, 1, SecurityPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSecurityPermission_Specification(), ecorePackage.getEString(), "specification", null, 0, 1, SecurityPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSecurityPermission_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, SecurityPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licenseEClass, License.class, "License", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicense_Description(), ecorePackage.getEString(), "description", null, 0, 1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicense_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLicense_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inboundResourceAdapterEClass, InboundResourceAdapter.class, "InboundResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInboundResourceAdapter_MessageAdapter(), this.getMessageAdapter(), null, "messageAdapter", null, 0, 1, InboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outboundResourceAdapterEClass, OutboundResourceAdapter.class, "OutboundResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutboundResourceAdapter_ReauthenticationSupport(), ecorePackage.getEBoolean(), "reauthenticationSupport", null, 0, 1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutboundResourceAdapter_TransactionSupport(), this.getTransactionSupportKind(), "transactionSupport", null, 0, 1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutboundResourceAdapter_ConnectionDefinitions(), this.getConnectionDefinition(), null, "connectionDefinitions", null, 0, -1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutboundResourceAdapter_AuthenticationMechanisms(), this.getAuthenticationMechanism(), null, "authenticationMechanisms", null, 0, -1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(messageAdapterEClass, MessageAdapter.class, "MessageAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMessageAdapter_MessageListeners(), this.getMessageListener(), null, "messageListeners", null, 1, -1, MessageAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionDefinitionEClass, ConnectionDefinition.class, "ConnectionDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectionDefinition_ManagedConnectionFactoryClass(), ecorePackage.getEString(), "managedConnectionFactoryClass", null, 0, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionDefinition_ConnectionFactoryInterface(), ecorePackage.getEString(), "connectionFactoryInterface", null, 0, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionDefinition_ConnectionFactoryImplClass(), ecorePackage.getEString(), "connectionFactoryImplClass", null, 0, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionDefinition_ConnectionInterface(), ecorePackage.getEString(), "connectionInterface", null, 0, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionDefinition_ConnectionImplClass(), ecorePackage.getEString(), "connectionImplClass", null, 0, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectionDefinition_ConfigProperties(), this.getConfigProperty(), null, "configProperties", null, 0, -1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adminObjectEClass, AdminObject.class, "AdminObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAdminObject_AdminObjectInterface(), ecorePackage.getEString(), "adminObjectInterface", null, 0, 1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAdminObject_AdminObjectClass(), ecorePackage.getEString(), "adminObjectClass", null, 0, 1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdminObject_ConfigProperties(), this.getConfigProperty(), null, "configProperties", null, 0, -1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(messageListenerEClass, MessageListener.class, "MessageListener", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMessageListener_MessageListenerType(), ecorePackage.getEString(), "messageListenerType", null, 0, 1, MessageListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMessageListener_ActivationSpec(), this.getActivationSpec(), null, "activationSpec", null, 1, 1, MessageListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activationSpecEClass, ActivationSpec.class, "ActivationSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivationSpec_ActivationSpecClass(), ecorePackage.getEString(), "activationSpecClass", null, 0, 1, ActivationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivationSpec_RequiredConfigProperties(), this.getRequiredConfigPropertyType(), null, "requiredConfigProperties", null, 0, -1, ActivationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiredConfigPropertyTypeEClass, RequiredConfigPropertyType.class, "RequiredConfigPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRequiredConfigPropertyType_Name(), ecorePackage.getEString(), "name", null, 0, 1, RequiredConfigPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRequiredConfigPropertyType_Descriptions(), theCommonPackage.getDescription(), null, "descriptions", null, 0, -1, RequiredConfigPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(authenticationMechanismTypeEEnum, AuthenticationMechanismType.class, "AuthenticationMechanismType");
		addEEnumLiteral(authenticationMechanismTypeEEnum, AuthenticationMechanismType.BASIC_PASSWORD_LITERAL);
		addEEnumLiteral(authenticationMechanismTypeEEnum, AuthenticationMechanismType.KERBV5_LITERAL);

		initEEnum(transactionSupportKindEEnum, TransactionSupportKind.class, "TransactionSupportKind");
		addEEnumLiteral(transactionSupportKindEEnum, TransactionSupportKind.NO_TRANSACTION_LITERAL);
		addEEnumLiteral(transactionSupportKindEEnum, TransactionSupportKind.LOCAL_TRANSACTION_LITERAL);
		addEEnumLiteral(transactionSupportKindEEnum, TransactionSupportKind.XA_TRANSACTION_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getAuthenticationMechanism_CustomAuthMechType() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthenticationMechanism_Descriptions() {
		return (EReference)authenticationMechanismEClass.getEStructuralFeatures().get(5);
	}

} //JcaPackageImpl































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































