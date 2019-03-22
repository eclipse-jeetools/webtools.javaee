/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;
import org.eclipse.jst.javaee.jca.ActivationSpec;
import org.eclipse.jst.javaee.jca.AdminObject;
import org.eclipse.jst.javaee.jca.AuthenticationMechanism;
import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.ConfigPropertyTypeType;
import org.eclipse.jst.javaee.jca.ConnectionDefinition;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor;
import org.eclipse.jst.javaee.jca.CredentialInterfaceType;
import org.eclipse.jst.javaee.jca.InboundResourceAdapter;
import org.eclipse.jst.javaee.jca.JcaFactory;
import org.eclipse.jst.javaee.jca.License;
import org.eclipse.jst.javaee.jca.MessageAdapter;
import org.eclipse.jst.javaee.jca.MessageListener;
import org.eclipse.jst.javaee.jca.OutboundResourceAdapter;
import org.eclipse.jst.javaee.jca.RequiredConfigProperty;
import org.eclipse.jst.javaee.jca.ResourceAdapter;
import org.eclipse.jst.javaee.jca.SecurityPermission;
import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JcaPackageImpl extends EPackageImpl implements JcaPackage {
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
	private EClass adminObjectEClass = null;

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
	private EClass connectionDefinitionEClass = null;

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
	private EClass connectorDeploymentDescriptorEClass = null;

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
	private EClass licenseEClass = null;

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
	private EClass messageListenerEClass = null;

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
	private EClass requiredConfigPropertyEClass = null;

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
	private EClass securityPermissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum configPropertyTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum credentialInterfaceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType configPropertyNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType configPropertyTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType credentialInterfaceTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * { @link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JcaPackageImpl() {
		super(eNS_URI, JcaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link JcaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JcaPackage init() {
		if (isInited) return (JcaPackage)EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);

		// Obtain or create and register package
		JcaPackageImpl theJcaPackage = (JcaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JcaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JcaPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLNamespacePackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJcaPackage.createPackageContents();

		// Initialize created meta-data
		theJcaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJcaPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JcaPackage.eNS_URI, theJcaPackage);
		EPackage.Registry.INSTANCE.put(JcaPackage.eNS_URI2, theJcaPackage);

		J2EEInit.initEMFModels();
		return theJcaPackage;
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
	public EAttribute getActivationSpec_ActivationspecClass() {
		return (EAttribute)activationSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivationSpec_RequiredConfigProperty() {
		return (EReference)activationSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivationSpec_ConfigProperty() {
		return (EReference)activationSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivationSpec_Id() {
		return (EAttribute)activationSpecEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getAdminObject_AdminobjectInterface() {
		return (EAttribute)adminObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdminObject_AdminobjectClass() {
		return (EAttribute)adminObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdminObject_ConfigProperty() {
		return (EReference)adminObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAdminObject_Id() {
		return (EAttribute)adminObjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAuthenticationMechanism() {
		return authenticationMechanismEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthenticationMechanism_Description() {
		return (EReference)authenticationMechanismEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthenticationMechanism_AuthenticationMechanismType() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthenticationMechanism_CredentialInterface() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthenticationMechanism_Id() {
		return (EAttribute)authenticationMechanismEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfigProperty() {
		return configPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfigProperty_Description() {
		return (EReference)configPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_ConfigPropertyName() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_ConfigPropertyType() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_ConfigPropertyValue() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_ConfigPropertyIgnore() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_ConfigPropertySupportsDynamicUpdates() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_ConfigPropertyConfidential() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfigProperty_Id() {
		return (EAttribute)configPropertyEClass.getEStructuralFeatures().get(7);
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
	public EAttribute getConnectionDefinition_ManagedconnectionfactoryClass() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectionDefinition_ConfigProperty() {
		return (EReference)connectionDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionfactoryInterface() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionfactoryImplClass() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionInterface() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_ConnectionImplClass() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionDefinition_Id() {
		return (EAttribute)connectionDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnector() {
		return connectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_ModuleName() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnector_Descriptions() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnector_DisplayNames() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnector_Icons() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_VendorName() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_EisType() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_ResourceadapterVersion() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnector_License() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnector_Resourceadapter() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_RequiredWorkContext() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_Id() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_MetadataComplete() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnector_Version() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectorDeploymentDescriptor() {
		return connectorDeploymentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectorDeploymentDescriptor_Mixed() {
		return (EAttribute)connectorDeploymentDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorDeploymentDescriptor_XMLNSPrefixMap() {
		return (EReference)connectorDeploymentDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorDeploymentDescriptor_XSISchemaLocation() {
		return (EReference)connectorDeploymentDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorDeploymentDescriptor_Connector() {
		return (EReference)connectorDeploymentDescriptorEClass.getEStructuralFeatures().get(3);
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
	public EReference getInboundResourceAdapter_Messageadapter() {
		return (EReference)inboundResourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInboundResourceAdapter_Id() {
		return (EAttribute)inboundResourceAdapterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLicense() {
		return licenseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLicense_Description() {
		return (EReference)licenseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLicense_LicenseRequired() {
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLicense_Id() {
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(2);
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
	public EReference getMessageAdapter_Messagelistener() {
		return (EReference)messageAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageAdapter_Id() {
		return (EAttribute)messageAdapterEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getMessageListener_MessagelistenerType() {
		return (EAttribute)messageListenerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageListener_Activationspec() {
		return (EReference)messageListenerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageListener_Id() {
		return (EAttribute)messageListenerEClass.getEStructuralFeatures().get(2);
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
	public EReference getOutboundResourceAdapter_ConnectionDefinition() {
		return (EReference)outboundResourceAdapterEClass.getEStructuralFeatures().get(0);
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
	public EReference getOutboundResourceAdapter_AuthenticationMechanism() {
		return (EReference)outboundResourceAdapterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutboundResourceAdapter_ReauthenticationSupport() {
		return (EAttribute)outboundResourceAdapterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOutboundResourceAdapter_Id() {
		return (EAttribute)outboundResourceAdapterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredConfigProperty() {
		return requiredConfigPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequiredConfigProperty_Description() {
		return (EReference)requiredConfigPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredConfigProperty_ConfigPropertyName() {
		return (EAttribute)requiredConfigPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequiredConfigProperty_Id() {
		return (EAttribute)requiredConfigPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceAdapter() {
		return resourceAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceAdapter_ResourceadapterClass() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_ConfigProperty() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_OutboundResourceadapter() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_InboundResourceadapter() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_Adminobject() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceAdapter_SecurityPermission() {
		return (EReference)resourceAdapterEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceAdapter_Id() {
		return (EAttribute)resourceAdapterEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityPermission() {
		return securityPermissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityPermission_Description() {
		return (EReference)securityPermissionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityPermission_SecurityPermissionSpec() {
		return (EAttribute)securityPermissionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityPermission_Id() {
		return (EAttribute)securityPermissionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConfigPropertyTypeType() {
		return configPropertyTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCredentialInterfaceType() {
		return credentialInterfaceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getConfigPropertyNameType() {
		return configPropertyNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getConfigPropertyTypeTypeObject() {
		return configPropertyTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCredentialInterfaceTypeObject() {
		return credentialInterfaceTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JcaFactory getJcaFactory() {
		return (JcaFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isCreated = false;

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
		activationSpecEClass = createEClass(ACTIVATION_SPEC);
		createEAttribute(activationSpecEClass, ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS);
		createEReference(activationSpecEClass, ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY);
		createEReference(activationSpecEClass, ACTIVATION_SPEC__CONFIG_PROPERTY);
		createEAttribute(activationSpecEClass, ACTIVATION_SPEC__ID);

		adminObjectEClass = createEClass(ADMIN_OBJECT);
		createEAttribute(adminObjectEClass, ADMIN_OBJECT__ADMINOBJECT_INTERFACE);
		createEAttribute(adminObjectEClass, ADMIN_OBJECT__ADMINOBJECT_CLASS);
		createEReference(adminObjectEClass, ADMIN_OBJECT__CONFIG_PROPERTY);
		createEAttribute(adminObjectEClass, ADMIN_OBJECT__ID);

		authenticationMechanismEClass = createEClass(AUTHENTICATION_MECHANISM);
		createEReference(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__DESCRIPTION);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE);
		createEAttribute(authenticationMechanismEClass, AUTHENTICATION_MECHANISM__ID);

		configPropertyEClass = createEClass(CONFIG_PROPERTY);
		createEReference(configPropertyEClass, CONFIG_PROPERTY__DESCRIPTION);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__CONFIG_PROPERTY_NAME);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL);
		createEAttribute(configPropertyEClass, CONFIG_PROPERTY__ID);

		connectionDefinitionEClass = createEClass(CONNECTION_DEFINITION);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS);
		createEReference(connectionDefinitionEClass, CONNECTION_DEFINITION__CONFIG_PROPERTY);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTION_INTERFACE);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS);
		createEAttribute(connectionDefinitionEClass, CONNECTION_DEFINITION__ID);

		connectorEClass = createEClass(CONNECTOR);
		createEAttribute(connectorEClass, CONNECTOR__MODULE_NAME);
		createEReference(connectorEClass, CONNECTOR__DESCRIPTIONS);
		createEReference(connectorEClass, CONNECTOR__DISPLAY_NAMES);
		createEReference(connectorEClass, CONNECTOR__ICONS);
		createEAttribute(connectorEClass, CONNECTOR__VENDOR_NAME);
		createEAttribute(connectorEClass, CONNECTOR__EIS_TYPE);
		createEAttribute(connectorEClass, CONNECTOR__RESOURCEADAPTER_VERSION);
		createEReference(connectorEClass, CONNECTOR__LICENSE);
		createEReference(connectorEClass, CONNECTOR__RESOURCEADAPTER);
		createEAttribute(connectorEClass, CONNECTOR__REQUIRED_WORK_CONTEXT);
		createEAttribute(connectorEClass, CONNECTOR__ID);
		createEAttribute(connectorEClass, CONNECTOR__METADATA_COMPLETE);
		createEAttribute(connectorEClass, CONNECTOR__VERSION);

		connectorDeploymentDescriptorEClass = createEClass(CONNECTOR_DEPLOYMENT_DESCRIPTOR);
		createEAttribute(connectorDeploymentDescriptorEClass, CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED);
		createEReference(connectorDeploymentDescriptorEClass, CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP);
		createEReference(connectorDeploymentDescriptorEClass, CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION);
		createEReference(connectorDeploymentDescriptorEClass, CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR);

		inboundResourceAdapterEClass = createEClass(INBOUND_RESOURCE_ADAPTER);
		createEReference(inboundResourceAdapterEClass, INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER);
		createEAttribute(inboundResourceAdapterEClass, INBOUND_RESOURCE_ADAPTER__ID);

		licenseEClass = createEClass(LICENSE);
		createEReference(licenseEClass, LICENSE__DESCRIPTION);
		createEAttribute(licenseEClass, LICENSE__LICENSE_REQUIRED);
		createEAttribute(licenseEClass, LICENSE__ID);

		messageAdapterEClass = createEClass(MESSAGE_ADAPTER);
		createEReference(messageAdapterEClass, MESSAGE_ADAPTER__MESSAGELISTENER);
		createEAttribute(messageAdapterEClass, MESSAGE_ADAPTER__ID);

		messageListenerEClass = createEClass(MESSAGE_LISTENER);
		createEAttribute(messageListenerEClass, MESSAGE_LISTENER__MESSAGELISTENER_TYPE);
		createEReference(messageListenerEClass, MESSAGE_LISTENER__ACTIVATIONSPEC);
		createEAttribute(messageListenerEClass, MESSAGE_LISTENER__ID);

		outboundResourceAdapterEClass = createEClass(OUTBOUND_RESOURCE_ADAPTER);
		createEReference(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION);
		createEAttribute(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT);
		createEReference(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM);
		createEAttribute(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT);
		createEAttribute(outboundResourceAdapterEClass, OUTBOUND_RESOURCE_ADAPTER__ID);

		requiredConfigPropertyEClass = createEClass(REQUIRED_CONFIG_PROPERTY);
		createEReference(requiredConfigPropertyEClass, REQUIRED_CONFIG_PROPERTY__DESCRIPTION);
		createEAttribute(requiredConfigPropertyEClass, REQUIRED_CONFIG_PROPERTY__CONFIG_PROPERTY_NAME);
		createEAttribute(requiredConfigPropertyEClass, REQUIRED_CONFIG_PROPERTY__ID);

		resourceAdapterEClass = createEClass(RESOURCE_ADAPTER);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__CONFIG_PROPERTY);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__ADMINOBJECT);
		createEReference(resourceAdapterEClass, RESOURCE_ADAPTER__SECURITY_PERMISSION);
		createEAttribute(resourceAdapterEClass, RESOURCE_ADAPTER__ID);

		securityPermissionEClass = createEClass(SECURITY_PERMISSION);
		createEReference(securityPermissionEClass, SECURITY_PERMISSION__DESCRIPTION);
		createEAttribute(securityPermissionEClass, SECURITY_PERMISSION__SECURITY_PERMISSION_SPEC);
		createEAttribute(securityPermissionEClass, SECURITY_PERMISSION__ID);

		// Create enums
		configPropertyTypeTypeEEnum = createEEnum(CONFIG_PROPERTY_TYPE_TYPE);
		credentialInterfaceTypeEEnum = createEEnum(CREDENTIAL_INTERFACE_TYPE);

		// Create data types
		configPropertyNameTypeEDataType = createEDataType(CONFIG_PROPERTY_NAME_TYPE);
		configPropertyTypeTypeObjectEDataType = createEDataType(CONFIG_PROPERTY_TYPE_TYPE_OBJECT);
		credentialInterfaceTypeObjectEDataType = createEDataType(CREDENTIAL_INTERFACE_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isInitialized = false;

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
		JavaeePackage theJavaeePackage = (JavaeePackage)(EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) instanceof JavaeePackage ? EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) : JavaeePackage.eINSTANCE);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(activationSpecEClass, ActivationSpec.class, "ActivationSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getActivationSpec_ActivationspecClass(), theJavaeePackage.getFullyQualifiedClassType(), "activationspecClass", null, 1, 1, ActivationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActivationSpec_RequiredConfigProperty(), this.getRequiredConfigProperty(), null, "requiredConfigProperty", null, 0, -1, ActivationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActivationSpec_ConfigProperty(), this.getConfigProperty(), null, "configProperty", null, 0, -1, ActivationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getActivationSpec_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ActivationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(adminObjectEClass, AdminObject.class, "AdminObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAdminObject_AdminobjectInterface(), theJavaeePackage.getFullyQualifiedClassType(), "adminobjectInterface", null, 1, 1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAdminObject_AdminobjectClass(), theJavaeePackage.getFullyQualifiedClassType(), "adminobjectClass", null, 1, 1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAdminObject_ConfigProperty(), this.getConfigProperty(), null, "configProperty", null, 0, -1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAdminObject_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AdminObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(authenticationMechanismEClass, AuthenticationMechanism.class, "AuthenticationMechanism", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAuthenticationMechanism_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, -1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAuthenticationMechanism_AuthenticationMechanismType(), theXMLTypePackage.getString(), "authenticationMechanismType", null, 1, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAuthenticationMechanism_CredentialInterface(), this.getCredentialInterfaceType(), "credentialInterface", null, 1, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAuthenticationMechanism_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AuthenticationMechanism.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(configPropertyEClass, ConfigProperty.class, "ConfigProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConfigProperty_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, -1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_ConfigPropertyName(), this.getConfigPropertyNameType(), "configPropertyName", null, 1, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_ConfigPropertyType(), this.getConfigPropertyTypeType(), "configPropertyType", null, 1, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_ConfigPropertyValue(), theXMLTypePackage.getString(), "configPropertyValue", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_ConfigPropertyIgnore(), theJavaeePackage.getTrueFalseType(), "configPropertyIgnore", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_ConfigPropertySupportsDynamicUpdates(), theJavaeePackage.getTrueFalseType(), "configPropertySupportsDynamicUpdates", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_ConfigPropertyConfidential(), theJavaeePackage.getTrueFalseType(), "configPropertyConfidential", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConfigProperty_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(connectionDefinitionEClass, ConnectionDefinition.class, "ConnectionDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getConnectionDefinition_ManagedconnectionfactoryClass(), theJavaeePackage.getFullyQualifiedClassType(), "managedconnectionfactoryClass", null, 1, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnectionDefinition_ConfigProperty(), this.getConfigProperty(), null, "configProperty", null, 0, -1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnectionDefinition_ConnectionfactoryInterface(), theJavaeePackage.getFullyQualifiedClassType(), "connectionfactoryInterface", null, 1, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnectionDefinition_ConnectionfactoryImplClass(), theJavaeePackage.getFullyQualifiedClassType(), "connectionfactoryImplClass", null, 1, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnectionDefinition_ConnectionInterface(), theJavaeePackage.getFullyQualifiedClassType(), "connectionInterface", null, 1, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnectionDefinition_ConnectionImplClass(), theJavaeePackage.getFullyQualifiedClassType(), "connectionImplClass", null, 1, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnectionDefinition_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConnectionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(connectorEClass, Connector.class, "Connector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getConnector_ModuleName(), theXMLTypePackage.getString(), "moduleName", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_VendorName(), theXMLTypePackage.getString(), "vendorName", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_EisType(), theXMLTypePackage.getString(), "eisType", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_ResourceadapterVersion(), theXMLTypePackage.getString(), "resourceadapterVersion", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_License(), this.getLicense(), null, "license", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_Resourceadapter(), this.getResourceAdapter(), null, "resourceadapter", null, 1, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_RequiredWorkContext(), theJavaeePackage.getFullyQualifiedClassType(), "requiredWorkContext", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_MetadataComplete(), theXMLTypePackage.getBoolean(), "metadataComplete", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConnector_Version(), theJavaeePackage.getDeweyVersionType(), "version", "1.7", 1, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(connectorDeploymentDescriptorEClass, ConnectorDeploymentDescriptor.class, "ConnectorDeploymentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getConnectorDeploymentDescriptor_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnectorDeploymentDescriptor_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnectorDeploymentDescriptor_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnectorDeploymentDescriptor_Connector(), this.getConnector(), null, "connector", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(inboundResourceAdapterEClass, InboundResourceAdapter.class, "InboundResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInboundResourceAdapter_Messageadapter(), this.getMessageAdapter(), null, "messageadapter", null, 0, 1, InboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInboundResourceAdapter_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, InboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(licenseEClass, License.class, "License", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getLicense_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, -1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicense_LicenseRequired(), theJavaeePackage.getTrueFalseType(), "licenseRequired", null, 1, 1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLicense_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(messageAdapterEClass, MessageAdapter.class, "MessageAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMessageAdapter_Messagelistener(), this.getMessageListener(), null, "messagelistener", null, 1, -1, MessageAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageAdapter_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MessageAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(messageListenerEClass, MessageListener.class, "MessageListener", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMessageListener_MessagelistenerType(), theJavaeePackage.getFullyQualifiedClassType(), "messagelistenerType", null, 1, 1, MessageListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageListener_Activationspec(), this.getActivationSpec(), null, "activationspec", null, 1, 1, MessageListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageListener_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MessageListener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(outboundResourceAdapterEClass, OutboundResourceAdapter.class, "OutboundResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOutboundResourceAdapter_ConnectionDefinition(), this.getConnectionDefinition(), null, "connectionDefinition", null, 0, -1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getOutboundResourceAdapter_TransactionSupport(), theJavaeePackage.getTransactionSupport(), "transactionSupport", null, 0, 1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOutboundResourceAdapter_AuthenticationMechanism(), this.getAuthenticationMechanism(), null, "authenticationMechanism", null, 0, -1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getOutboundResourceAdapter_ReauthenticationSupport(), theJavaeePackage.getTrueFalseType(), "reauthenticationSupport", null, 0, 1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getOutboundResourceAdapter_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, OutboundResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(requiredConfigPropertyEClass, RequiredConfigProperty.class, "RequiredConfigProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRequiredConfigProperty_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, -1, RequiredConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequiredConfigProperty_ConfigPropertyName(), this.getConfigPropertyNameType(), "configPropertyName", null, 1, 1, RequiredConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequiredConfigProperty_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RequiredConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(resourceAdapterEClass, ResourceAdapter.class, "ResourceAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getResourceAdapter_ResourceadapterClass(), theJavaeePackage.getFullyQualifiedClassType(), "resourceadapterClass", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getResourceAdapter_ConfigProperty(), this.getConfigProperty(), null, "configProperty", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getResourceAdapter_OutboundResourceadapter(), this.getOutboundResourceAdapter(), null, "outboundResourceadapter", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getResourceAdapter_InboundResourceadapter(), this.getInboundResourceAdapter(), null, "inboundResourceadapter", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getResourceAdapter_Adminobject(), this.getAdminObject(), null, "adminobject", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getResourceAdapter_SecurityPermission(), this.getSecurityPermission(), null, "securityPermission", null, 0, -1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getResourceAdapter_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ResourceAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(securityPermissionEClass, SecurityPermission.class, "SecurityPermission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSecurityPermission_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, -1, SecurityPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSecurityPermission_SecurityPermissionSpec(), theXMLTypePackage.getString(), "securityPermissionSpec", null, 1, 1, SecurityPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSecurityPermission_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SecurityPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.class, "ConfigPropertyTypeType"); //$NON-NLS-1$
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_BOOLEAN);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_STRING);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_INTEGER);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_DOUBLE);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_BYTE);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_SHORT);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_LONG);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_FLOAT);
		addEEnumLiteral(configPropertyTypeTypeEEnum, ConfigPropertyTypeType.JAVA_LANG_CHARACTER);

		initEEnum(credentialInterfaceTypeEEnum, CredentialInterfaceType.class, "CredentialInterfaceType"); //$NON-NLS-1$
		addEEnumLiteral(credentialInterfaceTypeEEnum, CredentialInterfaceType.JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL);
		addEEnumLiteral(credentialInterfaceTypeEEnum, CredentialInterfaceType.ORG_IETF_JGSS_GSS_CREDENTIAL);
		addEEnumLiteral(credentialInterfaceTypeEEnum, CredentialInterfaceType.JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL);

		// Initialize data types
		initEDataType(configPropertyNameTypeEDataType, String.class, "ConfigPropertyNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(configPropertyTypeTypeObjectEDataType, ConfigPropertyTypeType.class, "ConfigPropertyTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(credentialInterfaceTypeObjectEDataType, CredentialInterfaceType.class, "CredentialInterfaceTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
		}finally{
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$				
		addAnnotation
		  (activationSpecEClass, 
		   source, 
		   new String[] {
			 "name", "activationspecType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getActivationSpec_ActivationspecClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "activationspec-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getActivationSpec_RequiredConfigProperty(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "required-config-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getActivationSpec_ConfigProperty(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getActivationSpec_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (adminObjectEClass, 
		   source, 
		   new String[] {
			 "name", "adminobjectType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getAdminObject_AdminobjectInterface(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "adminobject-interface", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getAdminObject_AdminobjectClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "adminobject-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAdminObject_ConfigProperty(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAdminObject_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (authenticationMechanismEClass, 
		   source, 
		   new String[] {
			 "name", "authentication-mechanismType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAuthenticationMechanism_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getAuthenticationMechanism_AuthenticationMechanismType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "authentication-mechanism-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAuthenticationMechanism_CredentialInterface(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "credential-interface", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAuthenticationMechanism_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (configPropertyEClass, 
		   source, 
		   new String[] {
			 "name", "config-propertyType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConfigProperty_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConfigProperty_ConfigPropertyName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConfigProperty_ConfigPropertyType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConfigProperty_ConfigPropertyValue(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-value", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConfigProperty_ConfigPropertyIgnore(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-ignore", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConfigProperty_ConfigPropertySupportsDynamicUpdates(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-supports-dynamic-updates", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConfigProperty_ConfigPropertyConfidential(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-confidential", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConfigProperty_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (configPropertyNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "config-property-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#string" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (configPropertyTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "config-property-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (configPropertyTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "config-property-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "config-property-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (connectionDefinitionEClass, 
		   source, 
		   new String[] {
			 "name", "connection-definitionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnectionDefinition_ManagedconnectionfactoryClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "managedconnectionfactory-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnectionDefinition_ConfigProperty(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnectionDefinition_ConnectionfactoryInterface(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connectionfactory-interface", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnectionDefinition_ConnectionfactoryImplClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connectionfactory-impl-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnectionDefinition_ConnectionInterface(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-interface", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnectionDefinition_ConnectionImplClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-impl-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnectionDefinition_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (connectorEClass, 
		   source, 
		   new String[] {
			 "name", "connectorType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_ModuleName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "module-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnector_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnector_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnector_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_VendorName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "vendor-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_EisType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "eis-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_ResourceadapterVersion(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resourceadapter-version", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnector_License(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "license", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnector_Resourceadapter(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resourceadapter", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_RequiredWorkContext(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "required-work-context", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnector_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_MetadataComplete(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "metadata-complete" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnector_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "version" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (connectorDeploymentDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnectorDeploymentDescriptor_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnectorDeploymentDescriptor_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConnectorDeploymentDescriptor_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getConnectorDeploymentDescriptor_Connector(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connector", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (credentialInterfaceTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "credential-interfaceType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (credentialInterfaceTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "credential-interfaceType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "credential-interfaceType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (inboundResourceAdapterEClass, 
		   source, 
		   new String[] {
			 "name", "inbound-resourceadapterType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInboundResourceAdapter_Messageadapter(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "messageadapter", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInboundResourceAdapter_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (licenseEClass, 
		   source, 
		   new String[] {
			 "name", "licenseType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLicense_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getLicense_LicenseRequired(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "license-required", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLicense_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageAdapterEClass, 
		   source, 
		   new String[] {
			 "name", "messageadapterType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageAdapter_Messagelistener(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "messagelistener", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageAdapter_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageListenerEClass, 
		   source, 
		   new String[] {
			 "name", "messagelistenerType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageListener_MessagelistenerType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "messagelistener-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageListener_Activationspec(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "activationspec", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageListener_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (outboundResourceAdapterEClass, 
		   source, 
		   new String[] {
			 "name", "outbound-resourceadapterType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getOutboundResourceAdapter_ConnectionDefinition(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-definition", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getOutboundResourceAdapter_TransactionSupport(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "transaction-support", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getOutboundResourceAdapter_AuthenticationMechanism(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "authentication-mechanism", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getOutboundResourceAdapter_ReauthenticationSupport(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "reauthentication-support", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getOutboundResourceAdapter_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (requiredConfigPropertyEClass, 
		   source, 
		   new String[] {
			 "name", "required-config-propertyType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRequiredConfigProperty_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRequiredConfigProperty_ConfigPropertyName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRequiredConfigProperty_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (resourceAdapterEClass, 
		   source, 
		   new String[] {
			 "name", "resourceadapterType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceAdapter_ResourceadapterClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resourceadapter-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceAdapter_ConfigProperty(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "config-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceAdapter_OutboundResourceadapter(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "outbound-resourceadapter", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceAdapter_InboundResourceadapter(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "inbound-resourceadapter", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceAdapter_Adminobject(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "adminobject", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceAdapter_SecurityPermission(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-permission", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceAdapter_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (securityPermissionEClass, 
		   source, 
		   new String[] {
			 "name", "security-permissionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityPermission_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSecurityPermission_SecurityPermissionSpec(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-permission-spec", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityPermission_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

	@Override
	public void freeze()
	{
		// since EClassImpl.freeze() does a clear() on all of the subClasses, we need to protect initializePackageContents() against it.
		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}
		finally {
			super.freeze();
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

} //JcaPackageImpl
