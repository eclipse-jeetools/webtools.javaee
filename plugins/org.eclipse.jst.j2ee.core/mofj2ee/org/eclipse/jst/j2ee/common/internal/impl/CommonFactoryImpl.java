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
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
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


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CommonFactoryImpl extends EFactoryImpl implements CommonFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommonFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CommonPackage.EJB_REF: return createEjbRef();
			case CommonPackage.ENV_ENTRY: return createEnvEntry();
			case CommonPackage.RESOURCE_REF: return createResourceRef();
			case CommonPackage.SECURITY_ROLE_REF: return createSecurityRoleRef();
			case CommonPackage.SECURITY_ROLE: return createSecurityRole();
			case CommonPackage.RESOURCE_ENV_REF: return createResourceEnvRef();
			case CommonPackage.EJB_LOCAL_REF: return createEJBLocalRef();
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY: return createRunAsSpecifiedIdentity();
			case CommonPackage.IDENTITY: return createIdentity();
			case CommonPackage.ICON_TYPE: return createIconType();
			case CommonPackage.DISPLAY_NAME: return createDisplayName();
			case CommonPackage.MESSAGE_DESTINATION_REF: return createMessageDestinationRef();
			case CommonPackage.MESSAGE_DESTINATION: return createMessageDestination();
			case CommonPackage.PARAM_VALUE: return createParamValue();
			case CommonPackage.DESCRIPTION_GROUP: return createDescriptionGroup();
			case CommonPackage.SECURITY_IDENTITY: return createSecurityIdentity();
			case CommonPackage.USE_CALLER_IDENTITY: return createUseCallerIdentity();
			case CommonPackage.DESCRIPTION: return createDescription();
			case CommonPackage.QNAME: return createQName();
			case CommonPackage.LISTENER: return createListener();
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP: return createCompatibilityDescriptionGroup();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CommonPackage.ENV_ENTRY_TYPE: {
				EnvEntryType result = EnvEntryType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case CommonPackage.RES_AUTH_TYPE_BASE: {
				ResAuthTypeBase result = ResAuthTypeBase.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case CommonPackage.EJB_REF_TYPE: {
				EjbRefType result = EjbRefType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case CommonPackage.RES_SHARING_SCOPE_TYPE: {
				ResSharingScopeType result = ResSharingScopeType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case CommonPackage.MESSAGE_DESTINATION_USAGE_TYPE: {
				MessageDestinationUsageType result = MessageDestinationUsageType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CommonPackage.ENV_ENTRY_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case CommonPackage.RES_AUTH_TYPE_BASE:
				return instanceValue == null ? null : instanceValue.toString();
			case CommonPackage.EJB_REF_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case CommonPackage.RES_SHARING_SCOPE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case CommonPackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbRef createEjbRef() {
		EjbRefImpl ejbRef = new EjbRefImpl();
		return ejbRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvEntry createEnvEntry() {
		EnvEntryImpl envEntry = new EnvEntryImpl();
		return envEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceRef createResourceRef() {
		ResourceRefImpl resourceRef = new ResourceRefImpl();
		return resourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityRoleRef createSecurityRoleRef() {
		SecurityRoleRefImpl securityRoleRef = new SecurityRoleRefImpl();
		return securityRoleRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityRole createSecurityRole() {
		SecurityRoleImpl securityRole = new SecurityRoleImpl();
		return securityRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceEnvRef createResourceEnvRef() {
		ResourceEnvRefImpl resourceEnvRef = new ResourceEnvRefImpl();
		return resourceEnvRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EJBLocalRef createEJBLocalRef() {
		EJBLocalRefImpl ejbLocalRef = new EJBLocalRefImpl();
		return ejbLocalRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunAsSpecifiedIdentity createRunAsSpecifiedIdentity() {
		RunAsSpecifiedIdentityImpl runAsSpecifiedIdentity = new RunAsSpecifiedIdentityImpl();
		return runAsSpecifiedIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Identity createIdentity() {
		IdentityImpl identity = new IdentityImpl();
		return identity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IconType createIconType() {
		IconTypeImpl iconType = new IconTypeImpl();
		return iconType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayName createDisplayName() {
		DisplayNameImpl displayName = new DisplayNameImpl();
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestinationRef createMessageDestinationRef() {
		MessageDestinationRefImpl messageDestinationRef = new MessageDestinationRefImpl();
		return messageDestinationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestination createMessageDestination() {
		MessageDestinationImpl messageDestination = new MessageDestinationImpl();
		return messageDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParamValue createParamValue() {
		ParamValueImpl paramValue = new ParamValueImpl();
		return paramValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptionGroup createDescriptionGroup() {
		DescriptionGroupImpl descriptionGroup = new DescriptionGroupImpl();
		return descriptionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityIdentity createSecurityIdentity() {
		SecurityIdentityImpl securityIdentity = new SecurityIdentityImpl();
		return securityIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCallerIdentity createUseCallerIdentity() {
		UseCallerIdentityImpl useCallerIdentity = new UseCallerIdentityImpl();
		return useCallerIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Description createDescription() {
		DescriptionImpl description = new DescriptionImpl();
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName createQName() {
		QNameImpl qName = new QNameImpl();
		return qName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Listener createListener() {
		ListenerImpl listener = new ListenerImpl();
		return listener;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompatibilityDescriptionGroup createCompatibilityDescriptionGroup() {
		CompatibilityDescriptionGroupImpl compatibilityDescriptionGroup = new CompatibilityDescriptionGroupImpl();
		return compatibilityDescriptionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommonPackage getCommonPackage() {
		return (CommonPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static CommonPackage getPackage() {
		return CommonPackage.eINSTANCE;
	}

} //CommonFactoryImpl
