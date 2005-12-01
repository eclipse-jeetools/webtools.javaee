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
package org.eclipse.jst.j2ee.ejb;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
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
public interface EjbPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ejb"; //$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_PERMISSION = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ASSEMBLY_DESCRIPTOR = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_TRANSACTION = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN = 6;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTERPRISE_BEAN__ICONS = CommonPackage.JNDI_ENV_REFS_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTERPRISE_BEAN__DISPLAY_NAMES = CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTERPRISE_BEAN__DESCRIPTIONS = CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__SMALL_ICON = CommonPackage.JNDI_ENV_REFS_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__LARGE_ICON = CommonPackage.JNDI_ENV_REFS_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__DESCRIPTION = CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__DISPLAY_NAME = CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES = CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__RESOURCE_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__EJB_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__RESOURCE_ENV_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__EJB_LOCAL_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS;
	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTERPRISE_BEAN__SERVICE_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__NAME = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__SECURITY_ROLE_REFS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__EJB_CLASS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__HOME_INTERFACE = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__REMOTE_INTERFACE = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__EJB_JAR = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__SECURITY_IDENTITY = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTERPRISE_BEAN__LOCAL_INTERFACE = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 8;
	/**
	 * The number of structural features of the the '<em>Enterprise Bean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTERPRISE_BEAN_FEATURE_COUNT = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 9;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY = 5;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ICONS = ENTERPRISE_BEAN__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DISPLAY_NAMES = ENTERPRISE_BEAN__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTIONS = ENTERPRISE_BEAN__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__SMALL_ICON = ENTERPRISE_BEAN__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__LARGE_ICON = ENTERPRISE_BEAN__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__DESCRIPTION = ENTERPRISE_BEAN__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__DISPLAY_NAME = ENTERPRISE_BEAN__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__ENVIRONMENT_PROPERTIES = ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__RESOURCE_REFS = ENTERPRISE_BEAN__RESOURCE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__EJB_REFS = ENTERPRISE_BEAN__EJB_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__RESOURCE_ENV_REFS = ENTERPRISE_BEAN__RESOURCE_ENV_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__EJB_LOCAL_REFS = ENTERPRISE_BEAN__EJB_LOCAL_REFS;
	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__MESSAGE_DESTINATION_REFS = ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__SERVICE_REFS = ENTERPRISE_BEAN__SERVICE_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__NAME = ENTERPRISE_BEAN__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__SECURITY_ROLE_REFS = ENTERPRISE_BEAN__SECURITY_ROLE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__EJB_CLASS = ENTERPRISE_BEAN__EJB_CLASS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__HOME_INTERFACE = ENTERPRISE_BEAN__HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__REMOTE_INTERFACE = ENTERPRISE_BEAN__REMOTE_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__EJB_JAR = ENTERPRISE_BEAN__EJB_JAR;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__SECURITY_IDENTITY = ENTERPRISE_BEAN__SECURITY_IDENTITY;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__LOCAL_HOME_INTERFACE = ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__LOCAL_INTERFACE = ENTERPRISE_BEAN__LOCAL_INTERFACE;
	/**
	 * The feature id for the '<em><b>Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__REENTRANT = ENTERPRISE_BEAN_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENTITY__PRIMARY_KEY = ENTERPRISE_BEAN_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = ENTERPRISE_BEAN_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY = 0;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__ICONS = ENTITY__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__DISPLAY_NAMES = ENTITY__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__DESCRIPTIONS = ENTITY__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__SMALL_ICON = ENTITY__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__LARGE_ICON = ENTITY__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__DESCRIPTION = ENTITY__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__DISPLAY_NAME = ENTITY__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__ENVIRONMENT_PROPERTIES = ENTITY__ENVIRONMENT_PROPERTIES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__RESOURCE_REFS = ENTITY__RESOURCE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__EJB_REFS = ENTITY__EJB_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__RESOURCE_ENV_REFS = ENTITY__RESOURCE_ENV_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__EJB_LOCAL_REFS = ENTITY__EJB_LOCAL_REFS;
	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__MESSAGE_DESTINATION_REFS = ENTITY__MESSAGE_DESTINATION_REFS;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__SERVICE_REFS = ENTITY__SERVICE_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__NAME = ENTITY__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__SECURITY_ROLE_REFS = ENTITY__SECURITY_ROLE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__EJB_CLASS = ENTITY__EJB_CLASS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__HOME_INTERFACE = ENTITY__HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__REMOTE_INTERFACE = ENTITY__REMOTE_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__EJB_JAR = ENTITY__EJB_JAR;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__SECURITY_IDENTITY = ENTITY__SECURITY_IDENTITY;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__LOCAL_HOME_INTERFACE = ENTITY__LOCAL_HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__LOCAL_INTERFACE = ENTITY__LOCAL_INTERFACE;
	/**
	 * The feature id for the '<em><b>Reentrant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__REENTRANT = ENTITY__REENTRANT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__PRIMARY_KEY = ENTITY__PRIMARY_KEY;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__VERSION = ENTITY_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME = ENTITY_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES = ENTITY_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES = ENTITY_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTAINER_MANAGED_ENTITY__QUERIES = ENTITY_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>CMP Attribute</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE = ENTITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Prim Key Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD = ENTITY_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the the '<em>Container Managed Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_MANAGED_ENTITY_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_PERMISSION__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_PERMISSION__UNCHECKED = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_PERMISSION__ROLES = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_PERMISSION__METHOD_ELEMENTS = 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PERMISSION__DESCRIPTIONS = 5;

	/**
	 * The number of structural features of the the '<em>Method Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_PERMISSION_FEATURE_COUNT = 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ASSEMBLY_DESCRIPTOR__EJB_JAR = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ASSEMBLY_DESCRIPTOR__SECURITY_ROLES = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST = 4;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS = 5;

	/**
	 * The number of structural features of the the '<em>Assembly Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_DESCRIPTOR_FEATURE_COUNT = 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_TRANSACTION__DESCRIPTION = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_TRANSACTION__METHOD_ELEMENTS = 3;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_TRANSACTION__DESCRIPTIONS = 4;

	/**
	 * The number of structural features of the the '<em>Method Transaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_TRANSACTION_FEATURE_COUNT = 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMP_ATTRIBUTE = 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMR_FIELD = 16;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE = 14;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATION = 13;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RELATIONSHIPS = 11;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR = 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ROLE_SOURCE = 15;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY = 12;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD = 20;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT = 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCLUDE_LIST = 19;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION = 4;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__ICONS = ENTERPRISE_BEAN__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__DISPLAY_NAMES = ENTERPRISE_BEAN__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__DESCRIPTIONS = ENTERPRISE_BEAN__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__SMALL_ICON = ENTERPRISE_BEAN__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__LARGE_ICON = ENTERPRISE_BEAN__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__DESCRIPTION = ENTERPRISE_BEAN__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__DISPLAY_NAME = ENTERPRISE_BEAN__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__ENVIRONMENT_PROPERTIES = ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__RESOURCE_REFS = ENTERPRISE_BEAN__RESOURCE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__EJB_REFS = ENTERPRISE_BEAN__EJB_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__RESOURCE_ENV_REFS = ENTERPRISE_BEAN__RESOURCE_ENV_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__EJB_LOCAL_REFS = ENTERPRISE_BEAN__EJB_LOCAL_REFS;
	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__MESSAGE_DESTINATION_REFS = ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__SERVICE_REFS = ENTERPRISE_BEAN__SERVICE_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__NAME = ENTERPRISE_BEAN__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__SECURITY_ROLE_REFS = ENTERPRISE_BEAN__SECURITY_ROLE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__EJB_CLASS = ENTERPRISE_BEAN__EJB_CLASS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__HOME_INTERFACE = ENTERPRISE_BEAN__HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__REMOTE_INTERFACE = ENTERPRISE_BEAN__REMOTE_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__EJB_JAR = ENTERPRISE_BEAN__EJB_JAR;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__SECURITY_IDENTITY = ENTERPRISE_BEAN__SECURITY_IDENTITY;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__LOCAL_HOME_INTERFACE = ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__LOCAL_INTERFACE = ENTERPRISE_BEAN__LOCAL_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__TRANSACTION_TYPE = ENTERPRISE_BEAN_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION__SESSION_TYPE = ENTERPRISE_BEAN_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Service Endpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__SERVICE_ENDPOINT = ENTERPRISE_BEAN_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Session</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_FEATURE_COUNT = ENTERPRISE_BEAN_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN = 17;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN_DESTINATION = 18;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_METHOD_CATEGORY = 7;
	/**
	 * The number of structural features of the the '<em>EJB Method Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_METHOD_CATEGORY_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_JAR__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_JAR__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_JAR__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__EJB_CLIENT_JAR = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_JAR__VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__ASSEMBLY_DESCRIPTOR = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__ENTERPRISE_BEANS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_JAR__RELATIONSHIP_LIST = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the the '<em>EJB Jar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_JAR_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT__NAME = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT__PARMS = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT__TYPE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT__DESCRIPTION = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT__ENTERPRISE_BEAN = 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT__DESCRIPTIONS = 5;

	/**
	 * The number of structural features of the the '<em>Method Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_ELEMENT_FEATURE_COUNT = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__EANNOTATIONS = EcorePackage.EATTRIBUTE__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMP_ATTRIBUTE__NAME = EcorePackage.EATTRIBUTE__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__ORDERED = EcorePackage.EATTRIBUTE__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__UNIQUE = EcorePackage.EATTRIBUTE__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__LOWER_BOUND = EcorePackage.EATTRIBUTE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__UPPER_BOUND = EcorePackage.EATTRIBUTE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__MANY = EcorePackage.EATTRIBUTE__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__REQUIRED = EcorePackage.EATTRIBUTE__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__ETYPE = EcorePackage.EATTRIBUTE__ETYPE;

	/**
	 * The feature id for the '<em><b>Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__CHANGEABLE = EcorePackage.EATTRIBUTE__CHANGEABLE;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__VOLATILE = EcorePackage.EATTRIBUTE__VOLATILE;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__TRANSIENT = EcorePackage.EATTRIBUTE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__DEFAULT_VALUE_LITERAL = EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__DEFAULT_VALUE = EcorePackage.EATTRIBUTE__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Unsettable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__UNSETTABLE = EcorePackage.EATTRIBUTE__UNSETTABLE;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__DERIVED = EcorePackage.EATTRIBUTE__DERIVED;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__ECONTAINING_CLASS = EcorePackage.EATTRIBUTE__ECONTAINING_CLASS;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int CMP_ATTRIBUTE__ID = EcorePackage.EATTRIBUTE__ID;

	/**
	 * The feature id for the '<em><b>EAttribute Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__EATTRIBUTE_TYPE = EcorePackage.EATTRIBUTE__EATTRIBUTE_TYPE;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMP_ATTRIBUTE__DESCRIPTION = EcorePackage.EATTRIBUTE_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE__DESCRIPTIONS = EcorePackage.EATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>CMP Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMP_ATTRIBUTE_FEATURE_COUNT = EcorePackage.EATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RELATIONSHIPS__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RELATIONSHIPS__EJB_JAR = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RELATIONSHIPS__EJB_RELATIONS = 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIPS__DESCRIPTIONS = 3;

	/**
	 * The number of structural features of the the '<em>Relationships</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIPS_FEATURE_COUNT = 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY__EJB_QL = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY__RETURN_TYPE_MAPPING = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY__QUERY_METHOD = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY__ENTITY = 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY__DESCRIPTIONS = 5;

	/**
	 * The number of structural features of the the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_FEATURE_COUNT = 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATION__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATION__NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATION__RELATIONSHIP_LIST = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATION__RELATIONSHIP_ROLES = 3;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_RELATION__DESCRIPTIONS = 4;

	/**
	 * The number of structural features of the the '<em>EJB Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_RELATION_FEATURE_COUNT = 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__ROLE_NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__MULTIPLICITY = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__CASCADE_DELETE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__RELATIONSHIP = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__SOURCE = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_RELATIONSHIP_ROLE__CMR_FIELD = 6;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_RELATIONSHIP_ROLE__DESCRIPTIONS = 7;

	/**
	 * The number of structural features of the the '<em>EJB Relationship Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_RELATIONSHIP_ROLE_FEATURE_COUNT = 8;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ROLE_SOURCE__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ROLE_SOURCE__ROLE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ROLE_SOURCE__ENTITY_BEAN = 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SOURCE__DESCRIPTIONS = 3;

	/**
	 * The number of structural features of the the '<em>Role Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_SOURCE_FEATURE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__EANNOTATIONS = CMP_ATTRIBUTE__EANNOTATIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMR_FIELD__NAME = CMP_ATTRIBUTE__NAME;
	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__ORDERED = CMP_ATTRIBUTE__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__UNIQUE = CMP_ATTRIBUTE__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__LOWER_BOUND = CMP_ATTRIBUTE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__UPPER_BOUND = CMP_ATTRIBUTE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__MANY = CMP_ATTRIBUTE__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__REQUIRED = CMP_ATTRIBUTE__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__ETYPE = CMP_ATTRIBUTE__ETYPE;

	/**
	 * The feature id for the '<em><b>Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__CHANGEABLE = CMP_ATTRIBUTE__CHANGEABLE;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__VOLATILE = CMP_ATTRIBUTE__VOLATILE;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__TRANSIENT = CMP_ATTRIBUTE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__DEFAULT_VALUE_LITERAL = CMP_ATTRIBUTE__DEFAULT_VALUE_LITERAL;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__DEFAULT_VALUE = CMP_ATTRIBUTE__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Unsettable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__UNSETTABLE = CMP_ATTRIBUTE__UNSETTABLE;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__DERIVED = CMP_ATTRIBUTE__DERIVED;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__ECONTAINING_CLASS = CMP_ATTRIBUTE__ECONTAINING_CLASS;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  int CMR_FIELD__ID = CMP_ATTRIBUTE__ID;

	/**
	 * The feature id for the '<em><b>EAttribute Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__EATTRIBUTE_TYPE = CMP_ATTRIBUTE__EATTRIBUTE_TYPE;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMR_FIELD__DESCRIPTION = CMP_ATTRIBUTE__DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD__DESCRIPTIONS = CMP_ATTRIBUTE__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMR_FIELD__ROLE = CMP_ATTRIBUTE_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CMR_FIELD__COLLECTION_TYPE = CMP_ATTRIBUTE_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the the '<em>CMR Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CMR_FIELD_FEATURE_COUNT = CMP_ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__ICONS = ENTERPRISE_BEAN__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__DISPLAY_NAMES = ENTERPRISE_BEAN__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__DESCRIPTIONS = ENTERPRISE_BEAN__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__SMALL_ICON = ENTERPRISE_BEAN__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__LARGE_ICON = ENTERPRISE_BEAN__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__DESCRIPTION = ENTERPRISE_BEAN__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__DISPLAY_NAME = ENTERPRISE_BEAN__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__ENVIRONMENT_PROPERTIES = ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__RESOURCE_REFS = ENTERPRISE_BEAN__RESOURCE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__EJB_REFS = ENTERPRISE_BEAN__EJB_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__RESOURCE_ENV_REFS = ENTERPRISE_BEAN__RESOURCE_ENV_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__EJB_LOCAL_REFS = ENTERPRISE_BEAN__EJB_LOCAL_REFS;
	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__MESSAGE_DESTINATION_REFS = ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__SERVICE_REFS = ENTERPRISE_BEAN__SERVICE_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__NAME = ENTERPRISE_BEAN__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__SECURITY_ROLE_REFS = ENTERPRISE_BEAN__SECURITY_ROLE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__EJB_CLASS = ENTERPRISE_BEAN__EJB_CLASS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__HOME_INTERFACE = ENTERPRISE_BEAN__HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__REMOTE_INTERFACE = ENTERPRISE_BEAN__REMOTE_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__EJB_JAR = ENTERPRISE_BEAN__EJB_JAR;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__SECURITY_IDENTITY = ENTERPRISE_BEAN__SECURITY_IDENTITY;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__LOCAL_HOME_INTERFACE = ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__LOCAL_INTERFACE = ENTERPRISE_BEAN__LOCAL_INTERFACE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__TRANSACTION_TYPE = ENTERPRISE_BEAN_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__MESSAGE_SELECTOR = ENTERPRISE_BEAN_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__ACKNOWLEDGE_MODE = ENTERPRISE_BEAN_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__LINK = ENTERPRISE_BEAN_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN__DESTINATION = ENTERPRISE_BEAN_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Activation Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__ACTIVATION_CONFIG = ENTERPRISE_BEAN_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Message Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__MESSAGE_DESTINATION = ENTERPRISE_BEAN_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Messaging Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN__MESSAGING_TYPE = ENTERPRISE_BEAN_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the the '<em>Message Driven</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN_FEATURE_COUNT = ENTERPRISE_BEAN_FEATURE_COUNT + 8;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN_DESTINATION__TYPE = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN_DESTINATION__SUBSCRIPTION_DURABILITY = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MESSAGE_DRIVEN_DESTINATION__BEAN = 2;

	/**
	 * The number of structural features of the the '<em>Message Driven Destination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DRIVEN_DESTINATION_FEATURE_COUNT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCLUDE_LIST__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCLUDE_LIST__METHOD_ELEMENTS = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDE_LIST__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>Exclude List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDE_LIST_FEATURE_COUNT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD__NAME = METHOD_ELEMENT__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD__PARMS = METHOD_ELEMENT__PARMS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD__TYPE = METHOD_ELEMENT__TYPE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD__DESCRIPTION = METHOD_ELEMENT__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD__ENTERPRISE_BEAN = METHOD_ELEMENT__ENTERPRISE_BEAN;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_METHOD__DESCRIPTIONS = METHOD_ELEMENT__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int QUERY_METHOD__QUERY = METHOD_ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the the '<em>Query Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_METHOD_FEATURE_COUNT = METHOD_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.ejb.internal.impl.ActivationConfigPropertyImpl <em>Activation Config Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.ejb.internal.impl.ActivationConfigPropertyImpl
	 * @see org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl#getActivationConfigProperty()
	 * @generated
	 */
	int ACTIVATION_CONFIG_PROPERTY = 21;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_CONFIG_PROPERTY__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_CONFIG_PROPERTY__NAME = 1;

	/**
	 * The number of structural features of the the '<em>Activation Config Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_CONFIG_PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.ejb.internal.impl.ActivationConfigImpl <em>Activation Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.ejb.internal.impl.ActivationConfigImpl
	 * @see org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl#getActivationConfig()
	 * @generated
	 */
	int ACTIVATION_CONFIG = 22;

	/**
	 * The feature id for the '<em><b>Config Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_CONFIG__CONFIG_PROPERTIES = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_CONFIG__DESCRIPTIONS = 1;

	/**
	 * The number of structural features of the the '<em>Activation Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_CONFIG_FEATURE_COUNT = 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TRANSACTION_ATTRIBUTE_TYPE = 23;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MULTIPLICITY_KIND = 31;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RETURN_TYPE_MAPPING = 29;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int METHOD_ELEMENT_KIND = 26;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TRANSACTION_TYPE = 24;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ACKNOWLEDGE_MODE = 27;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int DESTINATION_TYPE = 30;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SUBSCRIPTION_DURABILITY_KIND = 28;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION_TYPE = 25;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "ejb.xmi"; //$NON-NLS-1$
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.ejb"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EjbPackage eINSTANCE = org.eclipse.jst.j2ee.ejb.internal.impl.EjbPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodPermission object
	 */
	EClass getMethodPermission();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodPermission_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodPermission_Unchecked();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethodPermission_AssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethodPermission_Roles();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethodPermission_MethodElements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.MethodPermission#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MethodPermission#getDescriptions()
	 * @see #getMethodPermission()
	 * @generated
	 */
	EReference getMethodPermission_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AssemblyDescriptor object
	 */
	EClass getAssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getAssemblyDescriptor_MethodPermissions();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getAssemblyDescriptor_MethodTransactions();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getAssemblyDescriptor_EjbJar();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getAssemblyDescriptor_SecurityRoles();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getAssemblyDescriptor_ExcludeList();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.AssemblyDescriptor#getMessageDestinations <em>Message Destinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destinations</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.AssemblyDescriptor#getMessageDestinations()
	 * @see #getAssemblyDescriptor()
	 * @generated
	 */
	EReference getAssemblyDescriptor_MessageDestinations();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodTransaction object
	 */
	EClass getMethodTransaction();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodTransaction_TransactionAttribute();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodTransaction_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethodTransaction_AssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethodTransaction_MethodElements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.MethodTransaction#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MethodTransaction#getDescriptions()
	 * @see #getMethodTransaction()
	 * @generated
	 */
	EReference getMethodTransaction_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EnterpriseBean object
	 */
	EClass getEnterpriseBean();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.ejb.EnterpriseBean#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.EnterpriseBean#getName()
	 * @see #getEnterpriseBean()
	 * @generated
	 */
	EAttribute getEnterpriseBean_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_SecurityRoleRefs();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_EjbClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_HomeInterface();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_RemoteInterface();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_EjbJar();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_SecurityIdentity();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_LocalHomeInterface();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEnterpriseBean_LocalInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Entity object
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.ejb.Entity#isReentrant <em>Reentrant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reentrant</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.Entity#isReentrant()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Reentrant();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEntity_PrimaryKey();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ContainerManagedEntity object
	 */
	EClass getContainerManagedEntity();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getContainerManagedEntity_Version();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getContainerManagedEntity_AbstractSchemaName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getContainerManagedEntity_PersistentAttributes();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getContainerManagedEntity_KeyAttributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getQueries <em>Queries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Queries</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getQueries()
	 * @see #getContainerManagedEntity()
	 * @generated
	 */
	EReference getContainerManagedEntity_Queries();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getCMPAttribute <em>CMP Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>CMP Attribute</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getCMPAttribute()
	 * @see #getContainerManagedEntity()
	 * @generated
	 */
	EReference getContainerManagedEntity_CMPAttribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getPrimKeyField <em>Prim Key Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prim Key Field</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getPrimKeyField()
	 * @see #getContainerManagedEntity()
	 * @generated
	 */
	EReference getContainerManagedEntity_PrimKeyField();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return CMPAttribute object
	 */
	EClass getCMPAttribute();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getCMPAttribute_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.CMPAttribute#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.CMPAttribute#getDescriptions()
	 * @see #getCMPAttribute()
	 * @generated
	 */
	EReference getCMPAttribute_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return CMRField object
	 */
	EClass getCMRField();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getCMRField_Role();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getCMRField_CollectionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBRelationshipRole object
	 */
	EClass getEJBRelationshipRole();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBRelationshipRole_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBRelationshipRole_RoleName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBRelationshipRole_Multiplicity();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBRelationshipRole_CascadeDelete();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBRelationshipRole_Relationship();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBRelationshipRole_Source();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBRelationshipRole_CmrField();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#getDescriptions()
	 * @see #getEJBRelationshipRole()
	 * @generated
	 */
	EReference getEJBRelationshipRole_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBRelation object
	 */
	EClass getEJBRelation();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBRelation_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBRelation_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBRelation_RelationshipList();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBRelation_RelationshipRoles();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.EJBRelation#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.EJBRelation#getDescriptions()
	 * @see #getEJBRelation()
	 * @generated
	 */
	EReference getEJBRelation_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Relationships object
	 */
	EClass getRelationships();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getRelationships_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getRelationships_EjbJar();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getRelationships_EjbRelations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.Relationships#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.Relationships#getDescriptions()
	 * @see #getRelationships()
	 * @generated
	 */
	EReference getRelationships_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBJar object
	 */
	EClass getEJBJar();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEJBJar_EjbClientJar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.ejb.EJBJar#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.EJBJar#getVersion()
	 * @see #getEJBJar()
	 * @generated
	 */
	EAttribute getEJBJar_Version();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBJar_AssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBJar_EnterpriseBeans();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getEJBJar_RelationshipList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return RoleSource object
	 */
	EClass getRoleSource();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getRoleSource_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getRoleSource_Role();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getRoleSource_EntityBean();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.RoleSource#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.RoleSource#getDescriptions()
	 * @see #getRoleSource()
	 * @generated
	 */
	EReference getRoleSource_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Query object
	 */
	EClass getQuery();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getQuery_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getQuery_EjbQL();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getQuery_ReturnTypeMapping();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getQuery_QueryMethod();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getQuery_Entity();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.Query#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.Query#getDescriptions()
	 * @see #getQuery()
	 * @generated
	 */
	EReference getQuery_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return QueryMethod object
	 */
	EClass getQueryMethod();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getQueryMethod_Query();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.ejb.ActivationConfigProperty <em>Activation Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activation Config Property</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfigProperty
	 * @generated
	 */
	EClass getActivationConfigProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.ejb.ActivationConfigProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfigProperty#getValue()
	 * @see #getActivationConfigProperty()
	 * @generated
	 */
	EAttribute getActivationConfigProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.ejb.ActivationConfigProperty#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfigProperty#getName()
	 * @see #getActivationConfigProperty()
	 * @generated
	 */
	EAttribute getActivationConfigProperty_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.ejb.ActivationConfig <em>Activation Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activation Config</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfig
	 * @generated
	 */
	EClass getActivationConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.ActivationConfig#getConfigProperties <em>Config Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Properties</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfig#getConfigProperties()
	 * @see #getActivationConfig()
	 * @generated
	 */
	EReference getActivationConfig_ConfigProperties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.ActivationConfig#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfig#getDescriptions()
	 * @see #getActivationConfig()
	 * @generated
	 */
	EReference getActivationConfig_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodElement object
	 */
	EClass getMethodElement();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodElement_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodElement_Parms();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodElement_Type();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMethodElement_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMethodElement_EnterpriseBean();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.MethodElement#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MethodElement#getDescriptions()
	 * @see #getMethodElement()
	 * @generated
	 */
	EReference getMethodElement_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ExcludeList object
	 */
	EClass getExcludeList();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getExcludeList_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getExcludeList_MethodElements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.ejb.ExcludeList#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.ExcludeList#getDescriptions()
	 * @see #getExcludeList()
	 * @generated
	 */
	EReference getExcludeList_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Session object
	 */
	EClass getSession();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSession_TransactionType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSession_SessionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.ejb.Session#getServiceEndpoint <em>Service Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Service Endpoint</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.Session#getServiceEndpoint()
	 * @see #getSession()
	 * @generated
	 */
	EReference getSession_ServiceEndpoint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MessageDriven object
	 */
	EClass getMessageDriven();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMessageDriven_TransactionType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMessageDriven_MessageSelector();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMessageDriven_AcknowledgeMode();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMessageDriven_Destination();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.ejb.MessageDriven#getActivationConfig <em>Activation Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activation Config</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MessageDriven#getActivationConfig()
	 * @see #getMessageDriven()
	 * @generated
	 */
	EReference getMessageDriven_ActivationConfig();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.ejb.MessageDriven#getMessageDestination <em>Message Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Message Destination</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MessageDriven#getMessageDestination()
	 * @see #getMessageDriven()
	 * @generated
	 */
	EReference getMessageDriven_MessageDestination();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.ejb.MessageDriven#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MessageDriven#getLink()
	 * @see #getMessageDriven()
	 * @generated
	 */
	EAttribute getMessageDriven_Link();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.ejb.MessageDriven#getMessagingType <em>Messaging Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Messaging Type</em>'.
	 * @see org.eclipse.jst.j2ee.ejb.MessageDriven#getMessagingType()
	 * @see #getMessageDriven()
	 * @generated
	 */
	EReference getMessageDriven_MessagingType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MessageDrivenDestination object
	 */
	EClass getMessageDrivenDestination();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMessageDrivenDestination_Type();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMessageDrivenDestination_SubscriptionDurability();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMessageDrivenDestination_Bean();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBMethodCategory object
	 */
	EClass getEJBMethodCategory();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TransactionAttributeType object
	 */
	EEnum getTransactionAttributeType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MultiplicityKind object
	 */
	EEnum getMultiplicityKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ReturnTypeMapping object
	 */
	EEnum getReturnTypeMapping();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodElementKind object
	 */
	EEnum getMethodElementKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TransactionType object
	 */
	EEnum getTransactionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AcknowledgeMode object
	 */
	EEnum getAcknowledgeMode();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return DestinationType object
	 */
	EEnum getDestinationType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SubscriptionDurabilityKind object
	 */
	EEnum getSubscriptionDurabilityKind();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SessionType object
	 */
	EEnum getSessionType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EjbFactory getEjbFactory();

} //EjbPackage








