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
package org.eclipse.jst.j2ee.internal.jca;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.jca.JcaFactory;



public interface JcaPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jca";//$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR = 0;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__VENDOR_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__SPEC_VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__EIS_TYPE = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__LICENSE = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONNECTOR__RESOURCE_ADAPTER = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;
	/**
	 * The number of structural features of the the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LICENSE = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_INTERFACE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__TRANSACTION_SUPPORT = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = 6;
	/**
	 * The feature id for the '<em><b>Resource Adapter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS = 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__SECURITY_PERMISSIONS = 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS = 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONFIG_PROPERTIES = 10;
	/**
	 * The feature id for the '<em><b>Outbound Resource Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER = 11;

	/**
	 * The feature id for the '<em><b>Inbound Resource Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER = 12;

	/**
	 * The feature id for the '<em><b>Admin Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__ADMIN_OBJECTS = 13;

	/**
	 * The number of structural features of the the '<em>Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER_FEATURE_COUNT = 14;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_PERMISSION = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__DESCRIPTION = 0;
	/**
	 * The feature id for the '<em><b>Authentication Mechanism</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM = 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TRANSACTION_SUPPORT_KIND = 15;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM_TYPE = 14;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "jca.xmi";//$NON-NLS-1$
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.internal.jca";//$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JcaPackage eINSTANCE = org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE = 4;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__DESCRIPTIONS = 5;

	/**
	 * The number of structural features of the the '<em>Authentication Mechanism</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM_FEATURE_COUNT = 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__TYPE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__VALUE = 3;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__DESCRIPTIONS = 4;

	/**
	 * The number of structural features of the the '<em>Config Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY_FEATURE_COUNT = 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_PERMISSION__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_PERMISSION__SPECIFICATION = 1;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>Security Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION_FEATURE_COUNT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LICENSE__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LICENSE__REQUIRED = 1;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.InboundResourceAdapterImpl <em>Inbound Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.InboundResourceAdapterImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getInboundResourceAdapter()
	 * @generated
	 */
	int INBOUND_RESOURCE_ADAPTER = 6;

	/**
	 * The feature id for the '<em><b>Message Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER = 0;

	/**
	 * The number of structural features of the the '<em>Inbound Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOUND_RESOURCE_ADAPTER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.OutboundResourceAdapterImpl <em>Outbound Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.OutboundResourceAdapterImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getOutboundResourceAdapter()
	 * @generated
	 */
	int OUTBOUND_RESOURCE_ADAPTER = 7;

	/**
	 * The feature id for the '<em><b>Reauthentication Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = 0;

	/**
	 * The feature id for the '<em><b>Transaction Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT = 1;

	/**
	 * The feature id for the '<em><b>Connection Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS = 2;

	/**
	 * The feature id for the '<em><b>Authentication Mechanisms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS = 3;

	/**
	 * The number of structural features of the the '<em>Outbound Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.MessageAdapterImpl <em>Message Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.MessageAdapterImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getMessageAdapter()
	 * @generated
	 */
	int MESSAGE_ADAPTER = 8;

	/**
	 * The feature id for the '<em><b>Message Listeners</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_ADAPTER__MESSAGE_LISTENERS = 0;

	/**
	 * The number of structural features of the the '<em>Message Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_ADAPTER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl <em>Connection Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.ConnectionDefinitionImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getConnectionDefinition()
	 * @generated
	 */
	int CONNECTION_DEFINITION = 9;

	/**
	 * The feature id for the '<em><b>Managed Connection Factory Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Connection Factory Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE = 1;

	/**
	 * The feature id for the '<em><b>Connection Factory Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS = 2;

	/**
	 * The feature id for the '<em><b>Connection Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_INTERFACE = 3;

	/**
	 * The feature id for the '<em><b>Connection Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS = 4;

	/**
	 * The feature id for the '<em><b>Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONFIG_PROPERTIES = 5;

	/**
	 * The number of structural features of the the '<em>Connection Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.AdminObjectImpl <em>Admin Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.AdminObjectImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getAdminObject()
	 * @generated
	 */
	int ADMIN_OBJECT = 10;

	/**
	 * The feature id for the '<em><b>Admin Object Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE = 0;

	/**
	 * The feature id for the '<em><b>Admin Object Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__ADMIN_OBJECT_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__CONFIG_PROPERTIES = 2;

	/**
	 * The number of structural features of the the '<em>Admin Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.MessageListenerImpl <em>Message Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.MessageListenerImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getMessageListener()
	 * @generated
	 */
	int MESSAGE_LISTENER = 11;

	/**
	 * The feature id for the '<em><b>Message Listener Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Activation Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER__ACTIVATION_SPEC = 1;

	/**
	 * The number of structural features of the the '<em>Message Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.ActivationSpecImpl <em>Activation Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.ActivationSpecImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getActivationSpec()
	 * @generated
	 */
	int ACTIVATION_SPEC = 12;

	/**
	 * The feature id for the '<em><b>Activation Spec Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Required Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES = 1;

	/**
	 * The number of structural features of the the '<em>Activation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.jca.impl.RequiredConfigPropertyTypeImpl <em>Required Config Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.RequiredConfigPropertyTypeImpl
	 * @see org.eclipse.jst.j2ee.internal.jca.impl.JcaPackageImpl#getRequiredConfigPropertyType()
	 * @generated
	 */
	int REQUIRED_CONFIG_PROPERTY_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY_TYPE__DESCRIPTIONS = 1;

	/**
	 * The number of structural features of the the '<em>Required Config Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY_TYPE_FEATURE_COUNT = 2;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Connector object
	 */
	EClass getConnector();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConnector_VendorName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConnector_SpecVersion();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConnector_EisType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConnector_Version();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getConnector_License();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getConnector_ResourceAdapter();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return License object
	 */
	EClass getLicense();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLicense_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLicense_Required();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.License#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.License#getDescriptions()
	 * @see #getLicense()
	 * @generated
	 */
	EReference getLicense_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.InboundResourceAdapter <em>Inbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.InboundResourceAdapter
	 * @generated
	 */
	EClass getInboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.jca.InboundResourceAdapter#getMessageAdapter <em>Message Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.InboundResourceAdapter#getMessageAdapter()
	 * @see #getInboundResourceAdapter()
	 * @generated
	 */
	EReference getInboundResourceAdapter_MessageAdapter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter <em>Outbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter
	 * @generated
	 */
	EClass getOutboundResourceAdapter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reauthentication Support</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#isReauthenticationSupport()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_ReauthenticationSupport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Support</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getTransactionSupport()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_TransactionSupport();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getConnectionDefinitions <em>Connection Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Definitions</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getConnectionDefinitions()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EReference getOutboundResourceAdapter_ConnectionDefinitions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getAuthenticationMechanisms <em>Authentication Mechanisms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Authentication Mechanisms</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.OutboundResourceAdapter#getAuthenticationMechanisms()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EReference getOutboundResourceAdapter_AuthenticationMechanisms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.MessageAdapter <em>Message Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.MessageAdapter
	 * @generated
	 */
	EClass getMessageAdapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.MessageAdapter#getMessageListeners <em>Message Listeners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Listeners</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.MessageAdapter#getMessageListeners()
	 * @see #getMessageAdapter()
	 * @generated
	 */
	EReference getMessageAdapter_MessageListeners();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition <em>Connection Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Definition</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition
	 * @generated
	 */
	EClass getConnectionDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getManagedConnectionFactoryClass <em>Managed Connection Factory Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Managed Connection Factory Class</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getManagedConnectionFactoryClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ManagedConnectionFactoryClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryInterface <em>Connection Factory Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Factory Interface</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryInterface()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionFactoryInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryImplClass <em>Connection Factory Impl Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Factory Impl Class</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryImplClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionFactoryImplClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Interface</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionInterface()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Impl Class</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionImplClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionImplClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConfigProperties <em>Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConfigProperties()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EReference getConnectionDefinition_ConfigProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.AdminObject <em>Admin Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Admin Object</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.AdminObject
	 * @generated
	 */
	EClass getAdminObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.AdminObject#getAdminObjectInterface <em>Admin Object Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Admin Object Interface</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.AdminObject#getAdminObjectInterface()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_AdminObjectInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.AdminObject#getAdminObjectClass <em>Admin Object Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Admin Object Class</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.AdminObject#getAdminObjectClass()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_AdminObjectClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.AdminObject#getConfigProperties <em>Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.AdminObject#getConfigProperties()
	 * @see #getAdminObject()
	 * @generated
	 */
	EReference getAdminObject_ConfigProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.MessageListener <em>Message Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Listener</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.MessageListener
	 * @generated
	 */
	EClass getMessageListener();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.MessageListener#getMessageListenerType <em>Message Listener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Listener Type</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.MessageListener#getMessageListenerType()
	 * @see #getMessageListener()
	 * @generated
	 */
	EAttribute getMessageListener_MessageListenerType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.jca.MessageListener#getActivationSpec <em>Activation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activation Spec</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.MessageListener#getActivationSpec()
	 * @see #getMessageListener()
	 * @generated
	 */
	EReference getMessageListener_ActivationSpec();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.ActivationSpec <em>Activation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activation Spec</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ActivationSpec
	 * @generated
	 */
	EClass getActivationSpec();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ActivationSpec#getActivationSpecClass <em>Activation Spec Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activation Spec Class</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ActivationSpec#getActivationSpecClass()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EAttribute getActivationSpec_ActivationSpecClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.ActivationSpec#getRequiredConfigProperties <em>Required Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ActivationSpec#getRequiredConfigProperties()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EReference getActivationSpec_RequiredConfigProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.internal.jca.RequiredConfigPropertyType <em>Required Config Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Config Property Type</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.RequiredConfigPropertyType
	 * @generated
	 */
	EClass getRequiredConfigPropertyType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.RequiredConfigPropertyType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.RequiredConfigPropertyType#getName()
	 * @see #getRequiredConfigPropertyType()
	 * @generated
	 */
	EAttribute getRequiredConfigPropertyType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.RequiredConfigPropertyType#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.RequiredConfigPropertyType#getDescriptions()
	 * @see #getRequiredConfigPropertyType()
	 * @generated
	 */
	EReference getRequiredConfigPropertyType_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ResourceAdapter object
	 */
	EClass getResourceAdapter();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_ManagedConnectionFactoryClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_ConnectionFactoryInterface();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_ConnectionFactoryImplClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_ConnectionInterface();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_ConnectionImplClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_TransactionSupport();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceAdapter_ReauthenticationSupport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getResourceAdapterClass <em>Resource Adapter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Adapter Class</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getResourceAdapterClass()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EAttribute getResourceAdapter_ResourceAdapterClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getResourceAdapter_SecurityPermissions();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getResourceAdapter_AuthenticationMechanisms();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getResourceAdapter_ConfigProperties();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getOutboundResourceAdapter <em>Outbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getOutboundResourceAdapter()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_OutboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getInboundResourceAdapter <em>Inbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getInboundResourceAdapter()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_InboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getAdminObjects <em>Admin Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Admin Objects</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ResourceAdapter#getAdminObjects()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_AdminObjects();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityPermission object
	 */
	EClass getSecurityPermission();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityPermission_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityPermission_Specification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.SecurityPermission#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.SecurityPermission#getDescriptions()
	 * @see #getSecurityPermission()
	 * @generated
	 */
	EReference getSecurityPermission_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AuthenticationMechanism object
	 */
	EClass getAuthenticationMechanism();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getAuthenticationMechanism_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.jca.AuthenticationMechanism#getAuthenticationMechanism <em>Authentication Mechanism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authentication Mechanism</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.AuthenticationMechanism#getAuthenticationMechanism()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EAttribute getAuthenticationMechanism_AuthenticationMechanism();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getAuthenticationMechanism_AuthenticationMechanismType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getAuthenticationMechanism_CredentialInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ConfigProperty object
	 */
	EClass getConfigProperty();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConfigProperty_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConfigProperty_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConfigProperty_Type();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getConfigProperty_Value();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.ConfigProperty#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.ConfigProperty#getDescriptions()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EReference getConfigProperty_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TransactionSupportKind object
	 */
	EEnum getTransactionSupportKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AuthenticationMechanismType object
	 */
	EEnum getAuthenticationMechanismType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	JcaFactory getJcaFactory();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getAuthenticationMechanism_CustomAuthMechType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.jca.AuthenticationMechanism#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.internal.jca.AuthenticationMechanism#getDescriptions()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EReference getAuthenticationMechanism_Descriptions();

} //JcaPackage





























































































































































































































































































































































































































































































































































































































































































































































































