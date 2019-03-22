/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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

/**
 * @generated
 */
public class JcaFactoryImpl extends EFactoryImpl implements JcaFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JcaFactory init() {
		try {
			JcaFactory theJcaFactory = (JcaFactory)EPackage.Registry.INSTANCE.getEFactory("jca.xmi");  //$NON-NLS-1$
			if (theJcaFactory != null) {
				return theJcaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JcaFactoryImpl();
	}

	public JcaFactoryImpl() {
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
			case JcaPackage.CONNECTOR: return createConnector();
			case JcaPackage.RESOURCE_ADAPTER: return createResourceAdapter();
			case JcaPackage.AUTHENTICATION_MECHANISM: return createAuthenticationMechanism();
			case JcaPackage.CONFIG_PROPERTY: return createConfigProperty();
			case JcaPackage.SECURITY_PERMISSION: return createSecurityPermission();
			case JcaPackage.LICENSE: return createLicense();
			case JcaPackage.INBOUND_RESOURCE_ADAPTER: return createInboundResourceAdapter();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER: return createOutboundResourceAdapter();
			case JcaPackage.MESSAGE_ADAPTER: return createMessageAdapter();
			case JcaPackage.CONNECTION_DEFINITION: return createConnectionDefinition();
			case JcaPackage.ADMIN_OBJECT: return createAdminObject();
			case JcaPackage.MESSAGE_LISTENER: return createMessageListener();
			case JcaPackage.ACTIVATION_SPEC: return createActivationSpec();
			case JcaPackage.REQUIRED_CONFIG_PROPERTY_TYPE: return createRequiredConfigPropertyType();
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
			case JcaPackage.AUTHENTICATION_MECHANISM_TYPE:
				return createAuthenticationMechanismTypeFromString(eDataType, initialValue);
			case JcaPackage.TRANSACTION_SUPPORT_KIND:
				return createTransactionSupportKindFromString(eDataType, initialValue);
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
			case JcaPackage.AUTHENTICATION_MECHANISM_TYPE:
				return convertAuthenticationMechanismTypeToString(eDataType, instanceValue);
			case JcaPackage.TRANSACTION_SUPPORT_KIND:
				return convertTransactionSupportKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Connector createConnector() {
		ConnectorImpl connector = new ConnectorImpl();
		return connector;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public License createLicense() {
		LicenseImpl license = new LicenseImpl();
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InboundResourceAdapter createInboundResourceAdapter() {
		InboundResourceAdapterImpl inboundResourceAdapter = new InboundResourceAdapterImpl();
		return inboundResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutboundResourceAdapter createOutboundResourceAdapter() {
		OutboundResourceAdapterImpl outboundResourceAdapter = new OutboundResourceAdapterImpl();
		return outboundResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageAdapter createMessageAdapter() {
		MessageAdapterImpl messageAdapter = new MessageAdapterImpl();
		return messageAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionDefinition createConnectionDefinition() {
		ConnectionDefinitionImpl connectionDefinition = new ConnectionDefinitionImpl();
		return connectionDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdminObject createAdminObject() {
		AdminObjectImpl adminObject = new AdminObjectImpl();
		return adminObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageListener createMessageListener() {
		MessageListenerImpl messageListener = new MessageListenerImpl();
		return messageListener;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivationSpec createActivationSpec() {
		ActivationSpecImpl activationSpec = new ActivationSpecImpl();
		return activationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredConfigPropertyType createRequiredConfigPropertyType() {
		RequiredConfigPropertyTypeImpl requiredConfigPropertyType = new RequiredConfigPropertyTypeImpl();
		return requiredConfigPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthenticationMechanismType createAuthenticationMechanismTypeFromString(EDataType eDataType, String initialValue) {
		AuthenticationMechanismType result = AuthenticationMechanismType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAuthenticationMechanismTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionSupportKind createTransactionSupportKindFromString(EDataType eDataType, String initialValue) {
		TransactionSupportKind result = TransactionSupportKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionSupportKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ResourceAdapter createResourceAdapter() {
		ResourceAdapterImpl resourceAdapter = new ResourceAdapterImpl();
		return resourceAdapter;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public SecurityPermission createSecurityPermission() {
		SecurityPermissionImpl securityPermission = new SecurityPermissionImpl();
		return securityPermission;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public AuthenticationMechanism createAuthenticationMechanism() {
		AuthenticationMechanismImpl authenticationMechanism = new AuthenticationMechanismImpl();
		return authenticationMechanism;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ConfigProperty createConfigProperty() {
		ConfigPropertyImpl configProperty = new ConfigPropertyImpl();
		return configProperty;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JcaPackage getJcaPackage() {
		return (JcaPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static JcaPackage getPackage() {
		return JcaPackage.eINSTANCE;
	}

}
