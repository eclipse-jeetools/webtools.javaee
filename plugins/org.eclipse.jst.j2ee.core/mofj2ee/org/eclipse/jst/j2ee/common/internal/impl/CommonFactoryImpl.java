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
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.j2ee.common.*;

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
import org.eclipse.jst.j2ee.common.J2EEEObject;
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
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CommonFactory init() {
		try {
			CommonFactory theCommonFactory = (CommonFactory)EPackage.Registry.INSTANCE.getEFactory("common.xmi");  //$NON-NLS-1$
			if (theCommonFactory != null) {
				return theCommonFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CommonFactoryImpl();
	}

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
	@Override
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
			case CommonPackage.J2EEE_OBJECT: return createJ2EEEObject();
			case CommonPackage.J2EEE_ATTRIBUTE: return createJ2EEEAttribute();
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
			case CommonPackage.ENV_ENTRY_TYPE:
				return createEnvEntryTypeFromString(eDataType, initialValue);
			case CommonPackage.RES_AUTH_TYPE_BASE:
				return createResAuthTypeBaseFromString(eDataType, initialValue);
			case CommonPackage.EJB_REF_TYPE:
				return createEjbRefTypeFromString(eDataType, initialValue);
			case CommonPackage.RES_SHARING_SCOPE_TYPE:
				return createResSharingScopeTypeFromString(eDataType, initialValue);
			case CommonPackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return createMessageDestinationUsageTypeFromString(eDataType, initialValue);
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
			case CommonPackage.ENV_ENTRY_TYPE:
				return convertEnvEntryTypeToString(eDataType, instanceValue);
			case CommonPackage.RES_AUTH_TYPE_BASE:
				return convertResAuthTypeBaseToString(eDataType, instanceValue);
			case CommonPackage.EJB_REF_TYPE:
				return convertEjbRefTypeToString(eDataType, instanceValue);
			case CommonPackage.RES_SHARING_SCOPE_TYPE:
				return convertResSharingScopeTypeToString(eDataType, instanceValue);
			case CommonPackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return convertMessageDestinationUsageTypeToString(eDataType, instanceValue);
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
	public EjbRef createEjbRef() {
		EjbRefImpl ejbRef = new EjbRefImpl();
		return ejbRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnvEntry createEnvEntry() {
		EnvEntryImpl envEntry = new EnvEntryImpl();
		return envEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceRef createResourceRef() {
		ResourceRefImpl resourceRef = new ResourceRefImpl();
		return resourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityRoleRef createSecurityRoleRef() {
		SecurityRoleRefImpl securityRoleRef = new SecurityRoleRefImpl();
		return securityRoleRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityRole createSecurityRole() {
		SecurityRoleImpl securityRole = new SecurityRoleImpl();
		return securityRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceEnvRef createResourceEnvRef() {
		ResourceEnvRefImpl resourceEnvRef = new ResourceEnvRefImpl();
		return resourceEnvRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EJBLocalRef createEJBLocalRef() {
		EJBLocalRefImpl ejbLocalRef = new EJBLocalRefImpl();
		return ejbLocalRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RunAsSpecifiedIdentity createRunAsSpecifiedIdentity() {
		RunAsSpecifiedIdentityImpl runAsSpecifiedIdentity = new RunAsSpecifiedIdentityImpl();
		return runAsSpecifiedIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Identity createIdentity() {
		IdentityImpl identity = new IdentityImpl();
		return identity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IconType createIconType() {
		IconTypeImpl iconType = new IconTypeImpl();
		return iconType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DisplayName createDisplayName() {
		DisplayNameImpl displayName = new DisplayNameImpl();
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageDestinationRef createMessageDestinationRef() {
		MessageDestinationRefImpl messageDestinationRef = new MessageDestinationRefImpl();
		return messageDestinationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageDestination createMessageDestination() {
		MessageDestinationImpl messageDestination = new MessageDestinationImpl();
		return messageDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ParamValue createParamValue() {
		ParamValueImpl paramValue = new ParamValueImpl();
		return paramValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DescriptionGroup createDescriptionGroup() {
		DescriptionGroupImpl descriptionGroup = new DescriptionGroupImpl();
		return descriptionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityIdentity createSecurityIdentity() {
		SecurityIdentityImpl securityIdentity = new SecurityIdentityImpl();
		return securityIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UseCallerIdentity createUseCallerIdentity() {
		UseCallerIdentityImpl useCallerIdentity = new UseCallerIdentityImpl();
		return useCallerIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Description createDescription() {
		DescriptionImpl description = new DescriptionImpl();
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QName createQName() {
		QNameImpl qName = new QNameImpl();
		return qName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Listener createListener() {
		ListenerImpl listener = new ListenerImpl();
		return listener;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompatibilityDescriptionGroup createCompatibilityDescriptionGroup() {
		CompatibilityDescriptionGroupImpl compatibilityDescriptionGroup = new CompatibilityDescriptionGroupImpl();
		return compatibilityDescriptionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public J2EEEObject createJ2EEEObject() {
		J2EEEObjectImpl j2EEEObject = new J2EEEObjectImpl();
		return j2EEEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public J2EEEAttribute createJ2EEEAttribute() {
		J2EEEAttributeImpl j2EEEAttribute = new J2EEEAttributeImpl();
		return j2EEEAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvEntryType createEnvEntryTypeFromString(EDataType eDataType, String initialValue) {
		EnvEntryType result = EnvEntryType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnvEntryTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResAuthTypeBase createResAuthTypeBaseFromString(EDataType eDataType, String initialValue) {
		ResAuthTypeBase result = ResAuthTypeBase.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResAuthTypeBaseToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbRefType createEjbRefTypeFromString(EDataType eDataType, String initialValue) {
		EjbRefType result = EjbRefType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEjbRefTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResSharingScopeType createResSharingScopeTypeFromString(EDataType eDataType, String initialValue) {
		ResSharingScopeType result = ResSharingScopeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResSharingScopeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestinationUsageType createMessageDestinationUsageTypeFromString(EDataType eDataType, String initialValue) {
		MessageDestinationUsageType result = MessageDestinationUsageType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageDestinationUsageTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
