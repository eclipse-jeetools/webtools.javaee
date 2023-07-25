/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.common.CommonPackage;


/**
 * <p>
 * <b>Note:</b> This class/interface is part of an interim API that is still under development and expected to
 * change significantly before reaching stability. It is being made available at this early stage to solicit feedback
 * from pioneering adopters on the understanding that any code that uses this API will almost certainly be broken
 * (repeatedly) as the API evolves.
 * </p>
 *	@since 1.0
 */
public interface JcaPackage extends EPackage {
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
	 * The number of structural features of the '<em>Connector</em>' class.
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
	int RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_INTERFACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__TRANSACTION_SUPPORT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;
	/**
	 * The feature id for the '<em><b>Resource Adapter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__SECURITY_PERMISSIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ADAPTER__CONFIG_PROPERTIES = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 10;
	/**
	 * The feature id for the '<em><b>Outbound Resource Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Inbound Resource Adapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Admin Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__ADMIN_OBJECTS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 14;

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
	int AUTHENTICATION_MECHANISM__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Authentication Mechanism</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;
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
	String eNS_PREFIX = "org.eclipse.jst.j2ee.jca";//$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JcaPackage eINSTANCE = org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Authentication Mechanism</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONFIG_PROPERTY__VALUE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Config Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_PERMISSION__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_PERMISSION__SPECIFICATION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Security Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LICENSE__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LICENSE__REQUIRED = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.InboundResourceAdapterImpl <em>Inbound Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.InboundResourceAdapterImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getInboundResourceAdapter()
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
	int INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Inbound Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOUND_RESOURCE_ADAPTER_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl <em>Outbound Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getOutboundResourceAdapter()
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
	int OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transaction Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Connection Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Authentication Mechanisms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Outbound Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.MessageAdapterImpl <em>Message Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.MessageAdapterImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getMessageAdapter()
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
	int MESSAGE_ADAPTER__MESSAGE_LISTENERS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Message Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_ADAPTER_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ConnectionDefinitionImpl <em>Connection Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.ConnectionDefinitionImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getConnectionDefinition()
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
	int CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connection Factory Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Connection Factory Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Connection Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_INTERFACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Connection Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONFIG_PROPERTIES = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Connection Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl <em>Admin Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getAdminObject()
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
	int ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Admin Object Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__ADMIN_OBJECT_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__CONFIG_PROPERTIES = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Admin Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.MessageListenerImpl <em>Message Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.MessageListenerImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getMessageListener()
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
	int MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activation Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER__ACTIVATION_SPEC = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Message Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ActivationSpecImpl <em>Activation Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.ActivationSpecImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getActivationSpec()
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
	int ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Activation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.RequiredConfigPropertyTypeImpl <em>Required Config Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.RequiredConfigPropertyTypeImpl
	 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getRequiredConfigPropertyType()
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
	int REQUIRED_CONFIG_PROPERTY_TYPE__NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY_TYPE__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Required Config Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY_TYPE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

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
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.License#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.jca.License#getDescriptions()
	 * @see #getLicense()
	 * @generated
	 */
	EReference getLicense_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.InboundResourceAdapter <em>Inbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.jca.InboundResourceAdapter
	 * @generated
	 */
	EClass getInboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.jca.InboundResourceAdapter#getMessageAdapter <em>Message Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.jca.InboundResourceAdapter#getMessageAdapter()
	 * @see #getInboundResourceAdapter()
	 * @generated
	 */
	EReference getInboundResourceAdapter_MessageAdapter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.OutboundResourceAdapter <em>Outbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.jca.OutboundResourceAdapter
	 * @generated
	 */
	EClass getOutboundResourceAdapter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reauthentication Support</em>'.
	 * @see org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#isReauthenticationSupport()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_ReauthenticationSupport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Support</em>'.
	 * @see org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#getTransactionSupport()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_TransactionSupport();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#getConnectionDefinitions <em>Connection Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Definitions</em>'.
	 * @see org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#getConnectionDefinitions()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EReference getOutboundResourceAdapter_ConnectionDefinitions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#getAuthenticationMechanisms <em>Authentication Mechanisms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Authentication Mechanisms</em>'.
	 * @see org.eclipse.jst.j2ee.jca.OutboundResourceAdapter#getAuthenticationMechanisms()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EReference getOutboundResourceAdapter_AuthenticationMechanisms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.MessageAdapter <em>Message Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.jca.MessageAdapter
	 * @generated
	 */
	EClass getMessageAdapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.MessageAdapter#getMessageListeners <em>Message Listeners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Listeners</em>'.
	 * @see org.eclipse.jst.j2ee.jca.MessageAdapter#getMessageListeners()
	 * @see #getMessageAdapter()
	 * @generated
	 */
	EReference getMessageAdapter_MessageListeners();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition <em>Connection Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Definition</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition
	 * @generated
	 */
	EClass getConnectionDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition#getManagedConnectionFactoryClass <em>Managed Connection Factory Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Managed Connection Factory Class</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition#getManagedConnectionFactoryClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ManagedConnectionFactoryClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionFactoryInterface <em>Connection Factory Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Factory Interface</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionFactoryInterface()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionFactoryInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionFactoryImplClass <em>Connection Factory Impl Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Factory Impl Class</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionFactoryImplClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionFactoryImplClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Interface</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionInterface()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Impl Class</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConnectionImplClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionImplClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConfigProperties <em>Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition#getConfigProperties()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EReference getConnectionDefinition_ConfigProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.AdminObject <em>Admin Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Admin Object</em>'.
	 * @see org.eclipse.jst.j2ee.jca.AdminObject
	 * @generated
	 */
	EClass getAdminObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectInterface <em>Admin Object Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Admin Object Interface</em>'.
	 * @see org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectInterface()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_AdminObjectInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectClass <em>Admin Object Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Admin Object Class</em>'.
	 * @see org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectClass()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_AdminObjectClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.AdminObject#getConfigProperties <em>Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.jca.AdminObject#getConfigProperties()
	 * @see #getAdminObject()
	 * @generated
	 */
	EReference getAdminObject_ConfigProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.MessageListener <em>Message Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Listener</em>'.
	 * @see org.eclipse.jst.j2ee.jca.MessageListener
	 * @generated
	 */
	EClass getMessageListener();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.MessageListener#getMessageListenerType <em>Message Listener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Listener Type</em>'.
	 * @see org.eclipse.jst.j2ee.jca.MessageListener#getMessageListenerType()
	 * @see #getMessageListener()
	 * @generated
	 */
	EAttribute getMessageListener_MessageListenerType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.jca.MessageListener#getActivationSpec <em>Activation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activation Spec</em>'.
	 * @see org.eclipse.jst.j2ee.jca.MessageListener#getActivationSpec()
	 * @see #getMessageListener()
	 * @generated
	 */
	EReference getMessageListener_ActivationSpec();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.ActivationSpec <em>Activation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activation Spec</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ActivationSpec
	 * @generated
	 */
	EClass getActivationSpec();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ActivationSpec#getActivationSpecClass <em>Activation Spec Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activation Spec Class</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ActivationSpec#getActivationSpecClass()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EAttribute getActivationSpec_ActivationSpecClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.ActivationSpec#getRequiredConfigProperties <em>Required Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ActivationSpec#getRequiredConfigProperties()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EReference getActivationSpec_RequiredConfigProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType <em>Required Config Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Config Property Type</em>'.
	 * @see org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType
	 * @generated
	 */
	EClass getRequiredConfigPropertyType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType#getName()
	 * @see #getRequiredConfigPropertyType()
	 * @generated
	 */
	EAttribute getRequiredConfigPropertyType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType#getDescriptions()
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
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.ResourceAdapter#getResourceAdapterClass <em>Resource Adapter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Adapter Class</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ResourceAdapter#getResourceAdapterClass()
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
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.jca.ResourceAdapter#getOutboundResourceAdapter <em>Outbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ResourceAdapter#getOutboundResourceAdapter()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_OutboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.jca.ResourceAdapter#getInboundResourceAdapter <em>Inbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ResourceAdapter#getInboundResourceAdapter()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_InboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.ResourceAdapter#getAdminObjects <em>Admin Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Admin Objects</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ResourceAdapter#getAdminObjects()
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
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.SecurityPermission#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.jca.SecurityPermission#getDescriptions()
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
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getAuthenticationMechanism <em>Authentication Mechanism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authentication Mechanism</em>'.
	 * @see org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getAuthenticationMechanism()
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
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.ConfigProperty#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.jca.ConfigProperty#getDescriptions()
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
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.ConnectorImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Vendor Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__VENDOR_NAME = eINSTANCE.getConnector_VendorName();

		/**
		 * The meta object literal for the '<em><b>Spec Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__SPEC_VERSION = eINSTANCE.getConnector_SpecVersion();

		/**
		 * The meta object literal for the '<em><b>Eis Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__EIS_TYPE = eINSTANCE.getConnector_EisType();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__VERSION = eINSTANCE.getConnector_Version();

		/**
		 * The meta object literal for the '<em><b>License</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__LICENSE = eINSTANCE.getConnector_License();

		/**
		 * The meta object literal for the '<em><b>Resource Adapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__RESOURCE_ADAPTER = eINSTANCE.getConnector_ResourceAdapter();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ResourceAdapterImpl <em>Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.ResourceAdapterImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getResourceAdapter()
		 * @generated
		 */
		EClass RESOURCE_ADAPTER = eINSTANCE.getResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Managed Connection Factory Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__MANAGED_CONNECTION_FACTORY_CLASS = eINSTANCE.getResourceAdapter_ManagedConnectionFactoryClass();

		/**
		 * The meta object literal for the '<em><b>Connection Factory Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__CONNECTION_FACTORY_INTERFACE = eINSTANCE.getResourceAdapter_ConnectionFactoryInterface();

		/**
		 * The meta object literal for the '<em><b>Connection Factory Impl Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__CONNECTION_FACTORY_IMPL_CLASS = eINSTANCE.getResourceAdapter_ConnectionFactoryImplClass();

		/**
		 * The meta object literal for the '<em><b>Connection Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__CONNECTION_INTERFACE = eINSTANCE.getResourceAdapter_ConnectionInterface();

		/**
		 * The meta object literal for the '<em><b>Connection Impl Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__CONNECTION_IMPL_CLASS = eINSTANCE.getResourceAdapter_ConnectionImplClass();

		/**
		 * The meta object literal for the '<em><b>Transaction Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__TRANSACTION_SUPPORT = eINSTANCE.getResourceAdapter_TransactionSupport();

		/**
		 * The meta object literal for the '<em><b>Reauthentication Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = eINSTANCE.getResourceAdapter_ReauthenticationSupport();

		/**
		 * The meta object literal for the '<em><b>Resource Adapter Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__RESOURCE_ADAPTER_CLASS = eINSTANCE.getResourceAdapter_ResourceAdapterClass();

		/**
		 * The meta object literal for the '<em><b>Security Permissions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__SECURITY_PERMISSIONS = eINSTANCE.getResourceAdapter_SecurityPermissions();

		/**
		 * The meta object literal for the '<em><b>Authentication Mechanisms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS = eINSTANCE.getResourceAdapter_AuthenticationMechanisms();

		/**
		 * The meta object literal for the '<em><b>Config Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__CONFIG_PROPERTIES = eINSTANCE.getResourceAdapter_ConfigProperties();

		/**
		 * The meta object literal for the '<em><b>Outbound Resource Adapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__OUTBOUND_RESOURCE_ADAPTER = eINSTANCE.getResourceAdapter_OutboundResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Inbound Resource Adapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__INBOUND_RESOURCE_ADAPTER = eINSTANCE.getResourceAdapter_InboundResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Admin Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__ADMIN_OBJECTS = eINSTANCE.getResourceAdapter_AdminObjects();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.AuthenticationMechanismImpl <em>Authentication Mechanism</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.AuthenticationMechanismImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getAuthenticationMechanism()
		 * @generated
		 */
		EClass AUTHENTICATION_MECHANISM = eINSTANCE.getAuthenticationMechanism();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__DESCRIPTION = eINSTANCE.getAuthenticationMechanism_Description();

		/**
		 * The meta object literal for the '<em><b>Authentication Mechanism</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM = eINSTANCE.getAuthenticationMechanism_AuthenticationMechanism();

		/**
		 * The meta object literal for the '<em><b>Authentication Mechanism Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE = eINSTANCE.getAuthenticationMechanism_AuthenticationMechanismType();

		/**
		 * The meta object literal for the '<em><b>Credential Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE = eINSTANCE.getAuthenticationMechanism_CredentialInterface();

		/**
		 * The meta object literal for the '<em><b>Custom Auth Mech Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE = eINSTANCE.getAuthenticationMechanism_CustomAuthMechType();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHENTICATION_MECHANISM__DESCRIPTIONS = eINSTANCE.getAuthenticationMechanism_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ConfigPropertyImpl <em>Config Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.ConfigPropertyImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getConfigProperty()
		 * @generated
		 */
		EClass CONFIG_PROPERTY = eINSTANCE.getConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__DESCRIPTION = eINSTANCE.getConfigProperty_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__NAME = eINSTANCE.getConfigProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__TYPE = eINSTANCE.getConfigProperty_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__VALUE = eINSTANCE.getConfigProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIG_PROPERTY__DESCRIPTIONS = eINSTANCE.getConfigProperty_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.SecurityPermissionImpl <em>Security Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.SecurityPermissionImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getSecurityPermission()
		 * @generated
		 */
		EClass SECURITY_PERMISSION = eINSTANCE.getSecurityPermission();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_PERMISSION__DESCRIPTION = eINSTANCE.getSecurityPermission_Description();

		/**
		 * The meta object literal for the '<em><b>Specification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_PERMISSION__SPECIFICATION = eINSTANCE.getSecurityPermission_Specification();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_PERMISSION__DESCRIPTIONS = eINSTANCE.getSecurityPermission_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.LicenseImpl <em>License</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.LicenseImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getLicense()
		 * @generated
		 */
		EClass LICENSE = eINSTANCE.getLicense();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE__DESCRIPTION = eINSTANCE.getLicense_Description();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE__REQUIRED = eINSTANCE.getLicense_Required();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LICENSE__DESCRIPTIONS = eINSTANCE.getLicense_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.InboundResourceAdapterImpl <em>Inbound Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.InboundResourceAdapterImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getInboundResourceAdapter()
		 * @generated
		 */
		EClass INBOUND_RESOURCE_ADAPTER = eINSTANCE.getInboundResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Message Adapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INBOUND_RESOURCE_ADAPTER__MESSAGE_ADAPTER = eINSTANCE.getInboundResourceAdapter_MessageAdapter();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl <em>Outbound Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.OutboundResourceAdapterImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getOutboundResourceAdapter()
		 * @generated
		 */
		EClass OUTBOUND_RESOURCE_ADAPTER = eINSTANCE.getOutboundResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Reauthentication Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = eINSTANCE.getOutboundResourceAdapter_ReauthenticationSupport();

		/**
		 * The meta object literal for the '<em><b>Transaction Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT = eINSTANCE.getOutboundResourceAdapter_TransactionSupport();

		/**
		 * The meta object literal for the '<em><b>Connection Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITIONS = eINSTANCE.getOutboundResourceAdapter_ConnectionDefinitions();

		/**
		 * The meta object literal for the '<em><b>Authentication Mechanisms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISMS = eINSTANCE.getOutboundResourceAdapter_AuthenticationMechanisms();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.MessageAdapterImpl <em>Message Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.MessageAdapterImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getMessageAdapter()
		 * @generated
		 */
		EClass MESSAGE_ADAPTER = eINSTANCE.getMessageAdapter();

		/**
		 * The meta object literal for the '<em><b>Message Listeners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_ADAPTER__MESSAGE_LISTENERS = eINSTANCE.getMessageAdapter_MessageListeners();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ConnectionDefinitionImpl <em>Connection Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.ConnectionDefinitionImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getConnectionDefinition()
		 * @generated
		 */
		EClass CONNECTION_DEFINITION = eINSTANCE.getConnectionDefinition();

		/**
		 * The meta object literal for the '<em><b>Managed Connection Factory Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__MANAGED_CONNECTION_FACTORY_CLASS = eINSTANCE.getConnectionDefinition_ManagedConnectionFactoryClass();

		/**
		 * The meta object literal for the '<em><b>Connection Factory Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTION_FACTORY_INTERFACE = eINSTANCE.getConnectionDefinition_ConnectionFactoryInterface();

		/**
		 * The meta object literal for the '<em><b>Connection Factory Impl Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTION_FACTORY_IMPL_CLASS = eINSTANCE.getConnectionDefinition_ConnectionFactoryImplClass();

		/**
		 * The meta object literal for the '<em><b>Connection Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTION_INTERFACE = eINSTANCE.getConnectionDefinition_ConnectionInterface();

		/**
		 * The meta object literal for the '<em><b>Connection Impl Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS = eINSTANCE.getConnectionDefinition_ConnectionImplClass();

		/**
		 * The meta object literal for the '<em><b>Config Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_DEFINITION__CONFIG_PROPERTIES = eINSTANCE.getConnectionDefinition_ConfigProperties();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl <em>Admin Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.AdminObjectImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getAdminObject()
		 * @generated
		 */
		EClass ADMIN_OBJECT = eINSTANCE.getAdminObject();

		/**
		 * The meta object literal for the '<em><b>Admin Object Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN_OBJECT__ADMIN_OBJECT_INTERFACE = eINSTANCE.getAdminObject_AdminObjectInterface();

		/**
		 * The meta object literal for the '<em><b>Admin Object Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN_OBJECT__ADMIN_OBJECT_CLASS = eINSTANCE.getAdminObject_AdminObjectClass();

		/**
		 * The meta object literal for the '<em><b>Config Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADMIN_OBJECT__CONFIG_PROPERTIES = eINSTANCE.getAdminObject_ConfigProperties();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.MessageListenerImpl <em>Message Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.MessageListenerImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getMessageListener()
		 * @generated
		 */
		EClass MESSAGE_LISTENER = eINSTANCE.getMessageListener();

		/**
		 * The meta object literal for the '<em><b>Message Listener Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_LISTENER__MESSAGE_LISTENER_TYPE = eINSTANCE.getMessageListener_MessageListenerType();

		/**
		 * The meta object literal for the '<em><b>Activation Spec</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_LISTENER__ACTIVATION_SPEC = eINSTANCE.getMessageListener_ActivationSpec();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.ActivationSpecImpl <em>Activation Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.ActivationSpecImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getActivationSpec()
		 * @generated
		 */
		EClass ACTIVATION_SPEC = eINSTANCE.getActivationSpec();

		/**
		 * The meta object literal for the '<em><b>Activation Spec Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVATION_SPEC__ACTIVATION_SPEC_CLASS = eINSTANCE.getActivationSpec_ActivationSpecClass();

		/**
		 * The meta object literal for the '<em><b>Required Config Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTIES = eINSTANCE.getActivationSpec_RequiredConfigProperties();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.internal.impl.RequiredConfigPropertyTypeImpl <em>Required Config Property Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.RequiredConfigPropertyTypeImpl
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getRequiredConfigPropertyType()
		 * @generated
		 */
		EClass REQUIRED_CONFIG_PROPERTY_TYPE = eINSTANCE.getRequiredConfigPropertyType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_CONFIG_PROPERTY_TYPE__NAME = eINSTANCE.getRequiredConfigPropertyType_Name();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIRED_CONFIG_PROPERTY_TYPE__DESCRIPTIONS = eINSTANCE.getRequiredConfigPropertyType_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanismType <em>Authentication Mechanism Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.AuthenticationMechanismType
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getAuthenticationMechanismType()
		 * @generated
		 */
		EEnum AUTHENTICATION_MECHANISM_TYPE = eINSTANCE.getAuthenticationMechanismType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.jca.TransactionSupportKind <em>Transaction Support Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.jca.TransactionSupportKind
		 * @see org.eclipse.jst.j2ee.jca.internal.impl.JcaPackageImpl#getTransactionSupportKind()
		 * @generated
		 */
		EEnum TRANSACTION_SUPPORT_KIND = eINSTANCE.getTransactionSupportKind();

	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getAuthenticationMechanism_CustomAuthMechType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getDescriptions()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EReference getAuthenticationMechanism_Descriptions();

} //JcaPackage
