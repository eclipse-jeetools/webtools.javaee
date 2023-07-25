/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.javaee.jca.*;
import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
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
			JcaFactory theJcaFactory = (JcaFactory)EPackage.Registry.INSTANCE.getEFactory("http://xmlns.jcp.org/xml/ns/javaee/jca"); //$NON-NLS-1$ 
			if (theJcaFactory != null) {
				return theJcaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JcaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
			case JcaPackage.ACTIVATION_SPEC: return (EObject)createActivationSpec();
			case JcaPackage.ADMIN_OBJECT: return (EObject)createAdminObject();
			case JcaPackage.AUTHENTICATION_MECHANISM: return (EObject)createAuthenticationMechanism();
			case JcaPackage.CONFIG_PROPERTY: return (EObject)createConfigProperty();
			case JcaPackage.CONNECTION_DEFINITION: return (EObject)createConnectionDefinition();
			case JcaPackage.CONNECTOR: return (EObject)createConnector();
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR: return (EObject)createConnectorDeploymentDescriptor();
			case JcaPackage.INBOUND_RESOURCE_ADAPTER: return (EObject)createInboundResourceAdapter();
			case JcaPackage.LICENSE: return (EObject)createLicense();
			case JcaPackage.MESSAGE_ADAPTER: return (EObject)createMessageAdapter();
			case JcaPackage.MESSAGE_LISTENER: return (EObject)createMessageListener();
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER: return (EObject)createOutboundResourceAdapter();
			case JcaPackage.REQUIRED_CONFIG_PROPERTY: return (EObject)createRequiredConfigProperty();
			case JcaPackage.RESOURCE_ADAPTER: return (EObject)createResourceAdapter();
			case JcaPackage.SECURITY_PERMISSION: return (EObject)createSecurityPermission();
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
			case JcaPackage.CONFIG_PROPERTY_TYPE_TYPE:
				return createConfigPropertyTypeTypeFromString(eDataType, initialValue);
			case JcaPackage.CREDENTIAL_INTERFACE_TYPE:
				return createCredentialInterfaceTypeFromString(eDataType, initialValue);
			case JcaPackage.CONFIG_PROPERTY_NAME_TYPE:
				return createConfigPropertyNameTypeFromString(eDataType, initialValue);
			case JcaPackage.CONFIG_PROPERTY_TYPE_TYPE_OBJECT:
				return createConfigPropertyTypeTypeObjectFromString(eDataType, initialValue);
			case JcaPackage.CREDENTIAL_INTERFACE_TYPE_OBJECT:
				return createCredentialInterfaceTypeObjectFromString(eDataType, initialValue);
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
			case JcaPackage.CONFIG_PROPERTY_TYPE_TYPE:
				return convertConfigPropertyTypeTypeToString(eDataType, instanceValue);
			case JcaPackage.CREDENTIAL_INTERFACE_TYPE:
				return convertCredentialInterfaceTypeToString(eDataType, instanceValue);
			case JcaPackage.CONFIG_PROPERTY_NAME_TYPE:
				return convertConfigPropertyNameTypeToString(eDataType, instanceValue);
			case JcaPackage.CONFIG_PROPERTY_TYPE_TYPE_OBJECT:
				return convertConfigPropertyTypeTypeObjectToString(eDataType, instanceValue);
			case JcaPackage.CREDENTIAL_INTERFACE_TYPE_OBJECT:
				return convertCredentialInterfaceTypeObjectToString(eDataType, instanceValue);
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
	public ActivationSpec createActivationSpec() {
		ActivationSpecImpl activationSpec = new ActivationSpecImpl();
		return activationSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdminObject createAdminObject() {
		AdminObjectImpl adminObject = new AdminObjectImpl();
		return adminObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AuthenticationMechanism createAuthenticationMechanism() {
		AuthenticationMechanismImpl authenticationMechanism = new AuthenticationMechanismImpl();
		return authenticationMechanism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConfigProperty createConfigProperty() {
		ConfigPropertyImpl configProperty = new ConfigPropertyImpl();
		return configProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectionDefinition createConnectionDefinition() {
		ConnectionDefinitionImpl connectionDefinition = new ConnectionDefinitionImpl();
		return connectionDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connector createConnector() {
		ConnectorImpl connector = new ConnectorImpl();
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectorDeploymentDescriptor createConnectorDeploymentDescriptor() {
		ConnectorDeploymentDescriptorImpl connectorDeploymentDescriptor = new ConnectorDeploymentDescriptorImpl();
		return connectorDeploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InboundResourceAdapter createInboundResourceAdapter() {
		InboundResourceAdapterImpl inboundResourceAdapter = new InboundResourceAdapterImpl();
		return inboundResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public License createLicense() {
		LicenseImpl license = new LicenseImpl();
		return license;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageAdapter createMessageAdapter() {
		MessageAdapterImpl messageAdapter = new MessageAdapterImpl();
		return messageAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageListener createMessageListener() {
		MessageListenerImpl messageListener = new MessageListenerImpl();
		return messageListener;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutboundResourceAdapter createOutboundResourceAdapter() {
		OutboundResourceAdapterImpl outboundResourceAdapter = new OutboundResourceAdapterImpl();
		return outboundResourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequiredConfigProperty createRequiredConfigProperty() {
		RequiredConfigPropertyImpl requiredConfigProperty = new RequiredConfigPropertyImpl();
		return requiredConfigProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceAdapter createResourceAdapter() {
		ResourceAdapterImpl resourceAdapter = new ResourceAdapterImpl();
		return resourceAdapter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityPermission createSecurityPermission() {
		SecurityPermissionImpl securityPermission = new SecurityPermissionImpl();
		return securityPermission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigPropertyTypeType createConfigPropertyTypeTypeFromString(EDataType eDataType, String initialValue) {
		ConfigPropertyTypeType result = ConfigPropertyTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigPropertyTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CredentialInterfaceType createCredentialInterfaceTypeFromString(EDataType eDataType, String initialValue) {
		CredentialInterfaceType result = CredentialInterfaceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCredentialInterfaceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createConfigPropertyNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigPropertyNameTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigPropertyTypeType createConfigPropertyTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createConfigPropertyTypeTypeFromString(JcaPackage.Literals.CONFIG_PROPERTY_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConfigPropertyTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertConfigPropertyTypeTypeToString(JcaPackage.Literals.CONFIG_PROPERTY_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CredentialInterfaceType createCredentialInterfaceTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createCredentialInterfaceTypeFromString(JcaPackage.Literals.CREDENTIAL_INTERFACE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCredentialInterfaceTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertCredentialInterfaceTypeToString(JcaPackage.Literals.CREDENTIAL_INTERFACE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JcaPackage getJcaPackage() {
		return (JcaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static JcaPackage getPackage() {
		return JcaPackage.eINSTANCE;
	}

} //JcaFactoryImpl
