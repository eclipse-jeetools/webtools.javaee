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
package org.eclipse.jst.j2ee.internal.common.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.impl.JavaRefPackageImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.EnvEntryType;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.MessageDestinationUsageType;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.ResAuthTypeBase;
import org.eclipse.jst.j2ee.common.ResSharingScopeType;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.UseCallerIdentity;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.application.impl.ApplicationPackageImpl;
import org.eclipse.jst.j2ee.internal.client.ClientPackage;
import org.eclipse.jst.j2ee.internal.client.impl.ClientPackageImpl;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbPackageImpl;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl;
import org.eclipse.jst.j2ee.internal.jsp.JspPackage;
import org.eclipse.jst.j2ee.internal.jsp.impl.JspPackageImpl;
import org.eclipse.jst.j2ee.internal.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.internal.taglib.impl.TaglibPackageImpl;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.internal.webapplication.impl.WebapplicationPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.impl.Webservice_clientPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.impl.WscommonPackageImpl;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.impl.WsddPackageImpl;


public class CommonPackageImpl extends EPackageImpl implements CommonPackage, EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass envEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityRoleRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEnvRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbLocalRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runAsSpecifiedIdentityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityIdentityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass identityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iconTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass displayNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageDestinationRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paramValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptionGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jndiEnvRefsGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useCallerIdentityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listenerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compatibilityDescriptionGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum envEntryTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resAuthTypeBaseEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum ejbRefTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resSharingScopeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum messageDestinationUsageTypeEEnum = null;

		   /**
		 * @generated This field/method will be replaced during code generation.
		 */
	private CommonPackageImpl() {
		super(eNS_URI, CommonFactory.eINSTANCE);
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
	public static CommonPackage init() {
		if (isInited) return (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Obtain or create and register package.
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CommonPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackageImpl.init();
		JavaRefPackageImpl.init();

		// Obtain or create and register interdependencies
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI) : EjbPackage.eINSTANCE);
		TaglibPackageImpl theTaglibPackage = (TaglibPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(TaglibPackage.eNS_URI) : TaglibPackage.eINSTANCE);
		ApplicationPackageImpl theApplicationPackage = (ApplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI) : ApplicationPackage.eINSTANCE);
		WscommonPackageImpl theWscommonPackage = (WscommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WscommonPackage.eNS_URI) : WscommonPackage.eINSTANCE);
		ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI) : ClientPackage.eINSTANCE);
		JspPackageImpl theJspPackage = (JspPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(JspPackage.eNS_URI) : JspPackage.eINSTANCE);
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI) : JcaPackage.eINSTANCE);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI) : Webservice_clientPackage.eINSTANCE);
		WebapplicationPackageImpl theWebapplicationPackage = (WebapplicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI) : WebapplicationPackage.eINSTANCE);
		WsddPackageImpl theWsddPackage = (WsddPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.getEPackage(WsddPackage.eNS_URI) : WsddPackage.eINSTANCE);

		// Step 1: create meta-model objects
		theCommonPackage.createPackageContents();
		theEjbPackage.createPackageContents();
		theTaglibPackage.createPackageContents();
		theApplicationPackage.createPackageContents();
		theWscommonPackage.createPackageContents();
		theClientPackage.createPackageContents();
		theJspPackage.createPackageContents();
		theJcaPackage.createPackageContents();
		theWebservice_clientPackage.createPackageContents();
		theWebapplicationPackage.createPackageContents();
		theWsddPackage.createPackageContents();

		// Step 2: complete initialization
		theCommonPackage.initializePackageContents();
		theEjbPackage.initializePackageContents();
		theTaglibPackage.initializePackageContents();
		theApplicationPackage.initializePackageContents();
		theWscommonPackage.initializePackageContents();
		theClientPackage.initializePackageContents();
		theJspPackage.initializePackageContents();
		theJcaPackage.initializePackageContents();
		theWebservice_clientPackage.initializePackageContents();
		theWebapplicationPackage.initializePackageContents();
		theWsddPackage.initializePackageContents();

		return theCommonPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getSecurityRole() {
		return securityRoleEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityRole_Description() {
		return (EAttribute)securityRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityRole_RoleName() {
		return (EAttribute)securityRoleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityRole_Descriptions() {
		return (EReference)securityRoleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getResourceRef() {
		return resourceRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceRef_Description() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceRef_Name() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceRef_Type() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceRef_Auth() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceRef_Link() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceRef_ResSharingScope() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceRef_Descriptions() {
		return (EReference)resourceRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEjbRef() {
		return ejbRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEjbRef_Name() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEjbRef_Type() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEjbRef_Home() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEjbRef_Remote() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEjbRef_Link() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEjbRef_Description() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEjbRef_Descriptions() {
		return (EReference)ejbRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEJBLocalRef() {
		return ejbLocalRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBLocalRef_LocalHome() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBLocalRef_Local() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getEnvEntry() {
		return envEntryEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEnvEntry_Description() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEnvEntry_Name() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEnvEntry_Value() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getEnvEntry_Type() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvEntry_Descriptions() {
		return (EReference)envEntryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getSecurityRoleRef() {
		return securityRoleRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityRoleRef_Name() {
		return (EAttribute)securityRoleRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityRoleRef_Description() {
		return (EAttribute)securityRoleRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityRoleRef_Link() {
		return (EAttribute)securityRoleRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityRoleRef_Descriptions() {
		return (EReference)securityRoleRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getRunAsSpecifiedIdentity() {
		return runAsSpecifiedIdentityEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getRunAsSpecifiedIdentity_Identity() {
		return (EReference)runAsSpecifiedIdentityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getSecurityIdentity() {
		return securityIdentityEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getSecurityIdentity_Description() {
		return (EAttribute)securityIdentityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityIdentity_Descriptions() {
		return (EReference)securityIdentityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getUseCallerIdentity() {
		return useCallerIdentityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescription() {
		return descriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Lang() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Value() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQName() {
		return qNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQName_NamespaceURI() {
		return (EAttribute)qNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQName_LocalPart() {
		return (EAttribute)qNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQName_CombinedQName() {
		return (EAttribute)qNameEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQName_InternalPrefixOrNsURI() {
		return (EAttribute)qNameEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListener() {
		return listenerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListener_ListenerClass() {
		return (EReference)listenerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompatibilityDescriptionGroup() {
		return compatibilityDescriptionGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompatibilityDescriptionGroup_SmallIcon() {
		return (EAttribute)compatibilityDescriptionGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompatibilityDescriptionGroup_LargeIcon() {
		return (EAttribute)compatibilityDescriptionGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompatibilityDescriptionGroup_Description() {
		return (EAttribute)compatibilityDescriptionGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompatibilityDescriptionGroup_DisplayName() {
		return (EAttribute)compatibilityDescriptionGroupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getIdentity() {
		return identityEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getIdentity_Description() {
		return (EAttribute)identityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getIdentity_RoleName() {
		return (EAttribute)identityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIdentity_Descriptions() {
		return (EReference)identityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIconType() {
		return iconTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIconType_SmallIcon() {
		return (EAttribute)iconTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIconType_LargeIcon() {
		return (EAttribute)iconTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIconType_Lang() {
		return (EAttribute)iconTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisplayName() {
		return displayNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayName_Lang() {
		return (EAttribute)displayNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayName_Value() {
		return (EAttribute)displayNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageDestinationRef() {
		return messageDestinationRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_Name() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_Type() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_Usage() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_Link() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDestinationRef_Descriptions() {
		return (EReference)messageDestinationRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageDestination() {
		return messageDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestination_Name() {
		return (EAttribute)messageDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParamValue() {
		return paramValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamValue_Name() {
		return (EAttribute)paramValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamValue_Value() {
		return (EAttribute)paramValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamValue_Description() {
		return (EAttribute)paramValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParamValue_Descriptions() {
		return (EReference)paramValueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescriptionGroup() {
		return descriptionGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescriptionGroup_Icons() {
		return (EReference)descriptionGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescriptionGroup_DisplayNames() {
		return (EReference)descriptionGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDescriptionGroup_Descriptions() {
		return (EReference)descriptionGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJNDIEnvRefsGroup() {
		return jndiEnvRefsGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_EnvironmentProperties() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_ResourceRefs() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_EjbRefs() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_ResourceEnvRefs() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_EjbLocalRefs() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_MessageDestinationRefs() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJNDIEnvRefsGroup_ServiceRefs() {
		return (EReference)jndiEnvRefsGroupEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EClass getResourceEnvRef() {
		return resourceEnvRefEClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceEnvRef_Description() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EAttribute getResourceEnvRef_Name() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EReference getResourceEnvRef_Type() {
		return (EReference)resourceEnvRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceEnvRef_Descriptions() {
		return (EReference)resourceEnvRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getEjbRefType() {
		return ejbRefTypeEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getEnvEntryType() {
		return envEntryTypeEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getResAuthTypeBase() {
		return resAuthTypeBaseEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EEnum getResSharingScopeType() {
		return resSharingScopeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMessageDestinationUsageType() {
		return messageDestinationUsageTypeEEnum;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public CommonFactory getCommonFactory() {
		return (CommonFactory)getEFactoryInstance();
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
		ejbRefEClass = createEClass(EJB_REF);
		createEAttribute(ejbRefEClass, EJB_REF__NAME);
		createEAttribute(ejbRefEClass, EJB_REF__TYPE);
		createEAttribute(ejbRefEClass, EJB_REF__HOME);
		createEAttribute(ejbRefEClass, EJB_REF__REMOTE);
		createEAttribute(ejbRefEClass, EJB_REF__LINK);
		createEAttribute(ejbRefEClass, EJB_REF__DESCRIPTION);
		createEReference(ejbRefEClass, EJB_REF__DESCRIPTIONS);

		envEntryEClass = createEClass(ENV_ENTRY);
		createEAttribute(envEntryEClass, ENV_ENTRY__DESCRIPTION);
		createEAttribute(envEntryEClass, ENV_ENTRY__NAME);
		createEAttribute(envEntryEClass, ENV_ENTRY__VALUE);
		createEAttribute(envEntryEClass, ENV_ENTRY__TYPE);
		createEReference(envEntryEClass, ENV_ENTRY__DESCRIPTIONS);

		resourceRefEClass = createEClass(RESOURCE_REF);
		createEAttribute(resourceRefEClass, RESOURCE_REF__DESCRIPTION);
		createEAttribute(resourceRefEClass, RESOURCE_REF__NAME);
		createEAttribute(resourceRefEClass, RESOURCE_REF__TYPE);
		createEAttribute(resourceRefEClass, RESOURCE_REF__AUTH);
		createEAttribute(resourceRefEClass, RESOURCE_REF__LINK);
		createEAttribute(resourceRefEClass, RESOURCE_REF__RES_SHARING_SCOPE);
		createEReference(resourceRefEClass, RESOURCE_REF__DESCRIPTIONS);

		securityRoleRefEClass = createEClass(SECURITY_ROLE_REF);
		createEAttribute(securityRoleRefEClass, SECURITY_ROLE_REF__NAME);
		createEAttribute(securityRoleRefEClass, SECURITY_ROLE_REF__DESCRIPTION);
		createEAttribute(securityRoleRefEClass, SECURITY_ROLE_REF__LINK);
		createEReference(securityRoleRefEClass, SECURITY_ROLE_REF__DESCRIPTIONS);

		securityRoleEClass = createEClass(SECURITY_ROLE);
		createEAttribute(securityRoleEClass, SECURITY_ROLE__DESCRIPTION);
		createEAttribute(securityRoleEClass, SECURITY_ROLE__ROLE_NAME);
		createEReference(securityRoleEClass, SECURITY_ROLE__DESCRIPTIONS);

		resourceEnvRefEClass = createEClass(RESOURCE_ENV_REF);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__DESCRIPTION);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__NAME);
		createEReference(resourceEnvRefEClass, RESOURCE_ENV_REF__TYPE);
		createEReference(resourceEnvRefEClass, RESOURCE_ENV_REF__DESCRIPTIONS);

		ejbLocalRefEClass = createEClass(EJB_LOCAL_REF);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__LOCAL_HOME);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__LOCAL);

		runAsSpecifiedIdentityEClass = createEClass(RUN_AS_SPECIFIED_IDENTITY);
		createEReference(runAsSpecifiedIdentityEClass, RUN_AS_SPECIFIED_IDENTITY__IDENTITY);

		identityEClass = createEClass(IDENTITY);
		createEAttribute(identityEClass, IDENTITY__DESCRIPTION);
		createEAttribute(identityEClass, IDENTITY__ROLE_NAME);
		createEReference(identityEClass, IDENTITY__DESCRIPTIONS);

		iconTypeEClass = createEClass(ICON_TYPE);
		createEAttribute(iconTypeEClass, ICON_TYPE__SMALL_ICON);
		createEAttribute(iconTypeEClass, ICON_TYPE__LARGE_ICON);
		createEAttribute(iconTypeEClass, ICON_TYPE__LANG);

		displayNameEClass = createEClass(DISPLAY_NAME);
		createEAttribute(displayNameEClass, DISPLAY_NAME__LANG);
		createEAttribute(displayNameEClass, DISPLAY_NAME__VALUE);

		messageDestinationRefEClass = createEClass(MESSAGE_DESTINATION_REF);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__NAME);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__TYPE);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__USAGE);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__LINK);
		createEReference(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__DESCRIPTIONS);

		messageDestinationEClass = createEClass(MESSAGE_DESTINATION);
		createEAttribute(messageDestinationEClass, MESSAGE_DESTINATION__NAME);

		paramValueEClass = createEClass(PARAM_VALUE);
		createEAttribute(paramValueEClass, PARAM_VALUE__NAME);
		createEAttribute(paramValueEClass, PARAM_VALUE__VALUE);
		createEAttribute(paramValueEClass, PARAM_VALUE__DESCRIPTION);
		createEReference(paramValueEClass, PARAM_VALUE__DESCRIPTIONS);

		descriptionGroupEClass = createEClass(DESCRIPTION_GROUP);
		createEReference(descriptionGroupEClass, DESCRIPTION_GROUP__ICONS);
		createEReference(descriptionGroupEClass, DESCRIPTION_GROUP__DISPLAY_NAMES);
		createEReference(descriptionGroupEClass, DESCRIPTION_GROUP__DESCRIPTIONS);

		jndiEnvRefsGroupEClass = createEClass(JNDI_ENV_REFS_GROUP);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__RESOURCE_REFS);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__EJB_REFS);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS);
		createEReference(jndiEnvRefsGroupEClass, JNDI_ENV_REFS_GROUP__SERVICE_REFS);

		securityIdentityEClass = createEClass(SECURITY_IDENTITY);
		createEAttribute(securityIdentityEClass, SECURITY_IDENTITY__DESCRIPTION);
		createEReference(securityIdentityEClass, SECURITY_IDENTITY__DESCRIPTIONS);

		useCallerIdentityEClass = createEClass(USE_CALLER_IDENTITY);

		descriptionEClass = createEClass(DESCRIPTION);
		createEAttribute(descriptionEClass, DESCRIPTION__LANG);
		createEAttribute(descriptionEClass, DESCRIPTION__VALUE);

		qNameEClass = createEClass(QNAME);
		createEAttribute(qNameEClass, QNAME__NAMESPACE_URI);
		createEAttribute(qNameEClass, QNAME__LOCAL_PART);
		createEAttribute(qNameEClass, QNAME__COMBINED_QNAME);
		createEAttribute(qNameEClass, QNAME__INTERNAL_PREFIX_OR_NS_URI);

		listenerEClass = createEClass(LISTENER);
		createEReference(listenerEClass, LISTENER__LISTENER_CLASS);

		compatibilityDescriptionGroupEClass = createEClass(COMPATIBILITY_DESCRIPTION_GROUP);
		createEAttribute(compatibilityDescriptionGroupEClass, COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON);
		createEAttribute(compatibilityDescriptionGroupEClass, COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON);
		createEAttribute(compatibilityDescriptionGroupEClass, COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION);
		createEAttribute(compatibilityDescriptionGroupEClass, COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME);

		// Create enums
		envEntryTypeEEnum = createEEnum(ENV_ENTRY_TYPE);
		resAuthTypeBaseEEnum = createEEnum(RES_AUTH_TYPE_BASE);
		ejbRefTypeEEnum = createEEnum(EJB_REF_TYPE);
		resSharingScopeTypeEEnum = createEEnum(RES_SHARING_SCOPE_TYPE);
		messageDestinationUsageTypeEEnum = createEEnum(MESSAGE_DESTINATION_USAGE_TYPE);
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
		JavaRefPackageImpl theJavaRefPackage = (JavaRefPackageImpl)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI);
		Webservice_clientPackageImpl theWebservice_clientPackage = (Webservice_clientPackageImpl)EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI);

		// Add supertypes to classes
		ejbLocalRefEClass.getESuperTypes().add(this.getEjbRef());
		runAsSpecifiedIdentityEClass.getESuperTypes().add(this.getSecurityIdentity());
		messageDestinationEClass.getESuperTypes().add(this.getCompatibilityDescriptionGroup());
		jndiEnvRefsGroupEClass.getESuperTypes().add(this.getCompatibilityDescriptionGroup());
		useCallerIdentityEClass.getESuperTypes().add(this.getSecurityIdentity());
		listenerEClass.getESuperTypes().add(this.getCompatibilityDescriptionGroup());
		compatibilityDescriptionGroupEClass.getESuperTypes().add(this.getDescriptionGroup());

		// Initialize classes and features; add operations and parameters
		initEClass(ejbRefEClass, EjbRef.class, "EjbRef", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getEjbRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEjbRef_Type(), this.getEjbRefType(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEjbRef_Home(), ecorePackage.getEString(), "home", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEjbRef_Remote(), ecorePackage.getEString(), "remote", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEjbRef_Link(), ecorePackage.getEString(), "link", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEjbRef_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getEjbRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(envEntryEClass, EnvEntry.class, "EnvEntry", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getEnvEntry_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEnvEntry_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEnvEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEnvEntry_Type(), this.getEnvEntryType(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getEnvEntry_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(resourceRefEClass, ResourceRef.class, "ResourceRef", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getResourceRef_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getResourceRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getResourceRef_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getResourceRef_Auth(), this.getResAuthTypeBase(), "auth", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getResourceRef_Link(), ecorePackage.getEString(), "link", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getResourceRef_ResSharingScope(), this.getResSharingScopeType(), "resSharingScope", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getResourceRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(securityRoleRefEClass, SecurityRoleRef.class, "SecurityRoleRef", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getSecurityRoleRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getSecurityRoleRef_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getSecurityRoleRef_Link(), ecorePackage.getEString(), "link", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getSecurityRoleRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(securityRoleEClass, SecurityRole.class, "SecurityRole", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getSecurityRole_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getSecurityRole_RoleName(), ecorePackage.getEString(), "roleName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getSecurityRole_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(resourceEnvRefEClass, ResourceEnvRef.class, "ResourceEnvRef", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getResourceEnvRef_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getResourceEnvRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getResourceEnvRef_Type(), theJavaRefPackage.getJavaClass(), null, "type", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getResourceEnvRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(ejbLocalRefEClass, EJBLocalRef.class, "EJBLocalRef", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getEJBLocalRef_LocalHome(), ecorePackage.getEString(), "localHome", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getEJBLocalRef_Local(), ecorePackage.getEString(), "local", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(runAsSpecifiedIdentityEClass, RunAsSpecifiedIdentity.class, "RunAsSpecifiedIdentity", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getRunAsSpecifiedIdentity_Identity(), this.getIdentity(), null, "identity", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(identityEClass, Identity.class, "Identity", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getIdentity_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getIdentity_RoleName(), ecorePackage.getEString(), "roleName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getIdentity_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(iconTypeEClass, IconType.class, "IconType", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getIconType_SmallIcon(), ecorePackage.getEString(), "smallIcon", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getIconType_LargeIcon(), ecorePackage.getEString(), "largeIcon", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getIconType_Lang(), ecorePackage.getEString(), "lang", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(displayNameEClass, DisplayName.class, "DisplayName", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getDisplayName_Lang(), ecorePackage.getEString(), "lang", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getDisplayName_Value(), ecorePackage.getEString(), "value", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(messageDestinationRefEClass, MessageDestinationRef.class, "MessageDestinationRef", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getMessageDestinationRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getMessageDestinationRef_Type(), ecorePackage.getEString(), "type", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getMessageDestinationRef_Usage(), this.getMessageDestinationUsageType(), "usage", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getMessageDestinationRef_Link(), ecorePackage.getEString(), "link", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getMessageDestinationRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(messageDestinationEClass, MessageDestination.class, "MessageDestination", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getMessageDestination_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(paramValueEClass, ParamValue.class, "ParamValue", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getParamValue_Name(), ecorePackage.getEString(), "name", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getParamValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getParamValue_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getParamValue_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(descriptionGroupEClass, DescriptionGroup.class, "DescriptionGroup", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getDescriptionGroup_Icons(), this.getIconType(), null, "icons", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getDescriptionGroup_DisplayNames(), this.getDisplayName(), null, "displayNames", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getDescriptionGroup_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(jndiEnvRefsGroupEClass, JNDIEnvRefsGroup.class, "JNDIEnvRefsGroup", IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_EnvironmentProperties(), this.getEnvEntry(), null, "environmentProperties", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_ResourceRefs(), this.getResourceRef(), null, "resourceRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_EjbRefs(), this.getEjbRef(), null, "ejbRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_ResourceEnvRefs(), this.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_EjbLocalRefs(), this.getEJBLocalRef(), null, "ejbLocalRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_MessageDestinationRefs(), this.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getJNDIEnvRefsGroup_ServiceRefs(), theWebservice_clientPackage.getServiceRef(), null, "serviceRefs", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(securityIdentityEClass, SecurityIdentity.class, "SecurityIdentity", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getSecurityIdentity_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEReference(getSecurityIdentity_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(useCallerIdentityEClass, UseCallerIdentity.class, "UseCallerIdentity", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$

		initEClass(descriptionEClass, Description.class, "Description", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getDescription_Lang(), ecorePackage.getEString(), "lang", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getDescription_Value(), ecorePackage.getEString(), "value", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(qNameEClass, QName.class, "QName", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getQName_NamespaceURI(), ecorePackage.getEString(), "namespaceURI", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getQName_LocalPart(), ecorePackage.getEString(), "localPart", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getQName_CombinedQName(), ecorePackage.getEString(), "combinedQName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getQName_InternalPrefixOrNsURI(), ecorePackage.getEString(), "internalPrefixOrNsURI", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(listenerEClass, Listener.class, "Listener", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEReference(getListener_ListenerClass(), theJavaRefPackage.getJavaClass(), null, "listenerClass", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		initEClass(compatibilityDescriptionGroupEClass, CompatibilityDescriptionGroup.class, "CompatibilityDescriptionGroup", !IS_ABSTRACT, !IS_INTERFACE); //$NON-NLS-1$
		initEAttribute(getCompatibilityDescriptionGroup_SmallIcon(), ecorePackage.getEString(), "smallIcon", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getCompatibilityDescriptionGroup_LargeIcon(), ecorePackage.getEString(), "largeIcon", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getCompatibilityDescriptionGroup_Description(), ecorePackage.getEString(), "description", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$
		initEAttribute(getCompatibilityDescriptionGroup_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(envEntryTypeEEnum, EnvEntryType.class, "EnvEntryType"); //$NON-NLS-1$
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.STRING_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.INTEGER_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.BOOLEAN_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.DOUBLE_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.BYTE_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.SHORT_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.LONG_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.FLOAT_LITERAL);
		addEEnumLiteral(envEntryTypeEEnum, EnvEntryType.CHARACTER_LITERAL);

		initEEnum(resAuthTypeBaseEEnum, ResAuthTypeBase.class, "ResAuthTypeBase"); //$NON-NLS-1$
		addEEnumLiteral(resAuthTypeBaseEEnum, ResAuthTypeBase.CONTAINER_LITERAL);
		addEEnumLiteral(resAuthTypeBaseEEnum, ResAuthTypeBase.APPLICATION_LITERAL);
		addEEnumLiteral(resAuthTypeBaseEEnum, ResAuthTypeBase.SERVLET_LITERAL);

		initEEnum(ejbRefTypeEEnum, EjbRefType.class, "EjbRefType"); //$NON-NLS-1$
		addEEnumLiteral(ejbRefTypeEEnum, EjbRefType.SESSION_LITERAL);
		addEEnumLiteral(ejbRefTypeEEnum, EjbRefType.ENTITY_LITERAL);

		initEEnum(resSharingScopeTypeEEnum, ResSharingScopeType.class, "ResSharingScopeType"); //$NON-NLS-1$
		addEEnumLiteral(resSharingScopeTypeEEnum, ResSharingScopeType.SHAREABLE_LITERAL);
		addEEnumLiteral(resSharingScopeTypeEEnum, ResSharingScopeType.UNSHAREABLE_LITERAL);

		initEEnum(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.class, "MessageDestinationUsageType"); //$NON-NLS-1$
		addEEnumLiteral(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.CONSUMES_LITERAL);
		addEEnumLiteral(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.PRODUCES_LITERAL);
		addEEnumLiteral(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}
} //CommonPackageImpl






