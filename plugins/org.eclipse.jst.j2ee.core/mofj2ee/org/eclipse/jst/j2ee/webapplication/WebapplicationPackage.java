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
package org.eclipse.jst.j2ee.webapplication;

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
public interface WebapplicationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "webapplication";//$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP = 0;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ICONS = CommonPackage.JNDI_ENV_REFS_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DISPLAY_NAMES = CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DESCRIPTIONS = CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__SMALL_ICON = CommonPackage.JNDI_ENV_REFS_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__LARGE_ICON = CommonPackage.JNDI_ENV_REFS_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__DESCRIPTION = CommonPackage.JNDI_ENV_REFS_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__DISPLAY_NAME = CommonPackage.JNDI_ENV_REFS_GROUP__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Environment Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ENVIRONMENT_PROPERTIES = CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__RESOURCE_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__EJB_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__RESOURCE_ENV_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__EJB_LOCAL_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS;
	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MESSAGE_DESTINATION_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SERVICE_REFS = CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__DISTRIBUTABLE = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__VERSION = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__CONTEXTS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__ERROR_PAGES = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__FILE_LIST = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__TAG_LIBS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__CONSTRAINTS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__LOGIN_CONFIG = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__MIME_MAPPINGS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__SESSION_CONFIG = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__SERVLET_MAPPINGS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__SERVLETS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 11;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__SECURITY_ROLES = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 12;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__FILTERS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 13;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__FILTER_MAPPINGS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 14;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_APP__LISTENERS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 15;
	/**
	 * The feature id for the '<em><b>Context Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__CONTEXT_PARAMS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Jsp Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__JSP_CONFIG = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MESSAGE_DESTINATIONS = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Local Encoding Mapping List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__LOCAL_ENCODING_MAPPING_LIST = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 19;

	/**
	 * The number of structural features of the '<em>Web App</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_FEATURE_COUNT = CommonPackage.JNDI_ENV_REFS_GROUP_FEATURE_COUNT + 20;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTEXT_PARAM = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTEXT_PARAM__PARAM_NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTEXT_PARAM__PARAM_VALUE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTEXT_PARAM__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int CONTEXT_PARAM__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Context Param</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_PARAM_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_PAGE = 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCEPTION_TYPE_ERROR_PAGE = 22;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_CODE_ERROR_PAGE = 23;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WELCOME_FILE_LIST = 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WELCOME_FILE = 21;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB_REF = 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_CONSTRAINT = 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_RESOURCE_COLLECTION = 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int URL_PATTERN_TYPE = 19;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int HTTP_METHOD_TYPE = 28;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTH_CONSTRAINT = 11;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int USER_DATA_CONSTRAINT = 12;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOGIN_CONFIG = 13;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FORM_LOGIN_CONFIG = 14;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MIME_MAPPING = 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION_CONFIG = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET_MAPPING = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET = 2;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__SERVLET_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__LOAD_ON_STARTUP = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__WEB_APP = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__WEB_TYPE = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__PARAMS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__SECURITY_ROLE_REFS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET__RUN_AS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Init Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__INIT_PARAMS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Servlet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 8;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET_MAPPING__URL_PATTERN = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_MAPPING__NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET_MAPPING__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET_MAPPING__SERVLET = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Servlet Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_MAPPING_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION_CONFIG__SESSION_TIMEOUT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SESSION_CONFIG__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Session Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_CONFIG_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MIME_MAPPING__EXTENSION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MIME_MAPPING__MIME_TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int MIME_MAPPING__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Mime Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MIME_MAPPING_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WELCOME_FILE_LIST__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WELCOME_FILE_LIST__FILE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Welcome File List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WELCOME_FILE_LIST_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_PAGE__LOCATION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_PAGE__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Error Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PAGE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB_REF__TAGLIB_URI = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB_REF__TAGLIB_LOCATION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Web App</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_REF__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tag Lib Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_REF_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__DISPLAY_NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_CONSTRAINT__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_CONSTRAINT__AUTH_CONSTRAINT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__DISPLAY_NAMES = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_RESOURCE_COLLECTION__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Url Pattern</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__URL_PATTERN = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Http Method</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__HTTP_METHOD = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_RESOURCE_COLLECTION__UR_LS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>HTT Ps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__HTT_PS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Web Resource Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 8;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTH_CONSTRAINT__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTH_CONSTRAINT__ROLES = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int AUTH_CONSTRAINT__SEC_CONSTRAINT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTH_CONSTRAINT__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Auth Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTH_CONSTRAINT_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int USER_DATA_CONSTRAINT__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int USER_DATA_CONSTRAINT__SEC_CONSTRAINT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_CONSTRAINT__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>User Data Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_CONSTRAINT_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOGIN_CONFIG__AUTH_METHOD = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOGIN_CONFIG__REALM_NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Authorization Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG__AUTHORIZATION_METHOD = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOGIN_CONFIG__WEB_APP = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int LOGIN_CONFIG__FORM_LOGIN_CONFIG = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Login Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FORM_LOGIN_CONFIG__FORM_ERROR_PAGE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FORM_LOGIN_CONFIG__LOGIN_CONFIG = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Form Login Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_LOGIN_CONFIG_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WEB_TYPE = 16;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET_TYPE = 17;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TYPE = 18;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INIT_PARAM = 15;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INIT_PARAM__PARAM_NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INIT_PARAM__PARAM_VALUE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int INIT_PARAM__DESCRIPTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Init Param</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_PARAM_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Web Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_TYPE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SERVLET_TYPE__CLASS_NAME = WEB_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Servlet Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_TYPE_FEATURE_COUNT = WEB_TYPE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TYPE__JSP_FILE = WEB_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>JSP Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TYPE_FEATURE_COUNT = WEB_TYPE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int URL_PATTERN_TYPE__URL_PATTERN = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int URL_PATTERN_TYPE__RES_COLLECTION = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>URL Pattern Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URL_PATTERN_TYPE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER = 25;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER_MAPPING = 24;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ROLE_NAME_TYPE = 20;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ROLE_NAME_TYPE__ROLE_NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Role Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_NAME_TYPE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WELCOME_FILE__WELCOME_FILE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int WELCOME_FILE__FILE_LIST = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Welcome File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WELCOME_FILE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCEPTION_TYPE_ERROR_PAGE__LOCATION = ERROR_PAGE__LOCATION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCEPTION_TYPE_ERROR_PAGE__WEB_APP = ERROR_PAGE__WEB_APP;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE = ERROR_PAGE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Exception Type Error Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_TYPE_ERROR_PAGE_FEATURE_COUNT = ERROR_PAGE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_CODE_ERROR_PAGE__LOCATION = ERROR_PAGE__LOCATION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_CODE_ERROR_PAGE__WEB_APP = ERROR_PAGE__WEB_APP;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ERROR_CODE_ERROR_PAGE__ERROR_CODE = ERROR_PAGE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Error Code Error Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_CODE_ERROR_PAGE_FEATURE_COUNT = ERROR_PAGE_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER_MAPPING__URL_PATTERN = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Dispatcher Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__DISPATCHER_TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER_MAPPING__FILTER = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER_MAPPING__SERVLET = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Filter Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__INIT_PARAMS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int FILTER__FILTER_CLASS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Init Param Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__INIT_PARAM_VALUES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingListImpl <em>Local Encoding Mapping List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingListImpl
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getLocalEncodingMappingList()
	 * @generated
	 */
	int LOCAL_ENCODING_MAPPING_LIST = 26;

	/**
	 * The feature id for the '<em><b>Local Encoding Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Local Encoding Mapping List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ENCODING_MAPPING_LIST_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingImpl <em>Local Encoding Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingImpl
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getLocalEncodingMapping()
	 * @generated
	 */
	int LOCAL_ENCODING_MAPPING = 27;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ENCODING_MAPPING__LOCALE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ENCODING_MAPPING__ENCODING = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Local Encoding Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_ENCODING_MAPPING_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Http Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_METHOD_TYPE__HTTP_METHOD = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>HTTP Method Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_METHOD_TYPE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webapplication.ResAuthServletType <em>Res Auth Servlet Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webapplication.ResAuthServletType
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getResAuthServletType()
	 * @generated
	 */
	int RES_AUTH_SERVLET_TYPE = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum <em>HTTP Method Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getHTTPMethodTypeEnum()
	 * @generated
	 */
	int HTTP_METHOD_TYPE_ENUM = 30;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TRANSPORT_GUARANTEE_TYPE = 31;
	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webapplication.DispatcherType <em>Dispatcher Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webapplication.DispatcherType
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getDispatcherType()
	 * @generated
	 */
	int DISPATCHER_TYPE = 32;


	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.webapplication.AuthMethodKind <em>Auth Method Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.webapplication.AuthMethodKind
	 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getAuthMethodKind()
	 * @generated
	 */
	int AUTH_METHOD_KIND = 33;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "webapplication.xmi";//$NON-NLS-1$
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.webapplication";//$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebapplicationPackage eINSTANCE = org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WebApp object
	 */
	EClass getWebApp();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EAttribute getWebApp_Distributable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebApp#getVersion()
	 * @see #getWebApp()
	 * @generated
	 */
	EAttribute getWebApp_Version();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_Contexts();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_ErrorPages();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_FileList();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_TagLibs();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_Constraints();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_LoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_MimeMappings();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_SessionConfig();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_ServletMappings();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_Servlets();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_SecurityRoles();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_Filters();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_FilterMappings();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return
	 */
	EReference getWebApp_Listeners();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getContextParams <em>Context Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context Params</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebApp#getContextParams()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ContextParams();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getJspConfig <em>Jsp Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Jsp Config</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebApp#getJspConfig()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_JspConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getMessageDestinations <em>Message Destinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destinations</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebApp#getMessageDestinations()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_MessageDestinations();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.webapplication.WebApp#getLocalEncodingMappingList <em>Local Encoding Mapping List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Local Encoding Mapping List</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebApp#getLocalEncodingMappingList()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_LocalEncodingMappingList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ContextParam object
	 */
	EClass getContextParam();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return Name
	 */
	EAttribute getContextParam_ParamName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return Value
	 */
	EAttribute getContextParam_ParamValue();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return Description
	 */
	EAttribute getContextParam_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return WebApp
	 */
	EReference getContextParam_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ErrorPage object
	 */
	EClass getErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return Location
	 */
	EAttribute getErrorPage_Location();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return WebApp
	 */
	EReference getErrorPage_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ExceptionTypeErrorPage object
	 */
	EClass getExceptionTypeErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getExceptionTypeErrorPage_ExceptionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ErrorCodeErrorPage object
	 */
	/**
	 * @return ErrorCodeErrorPage object
	 */
	EClass getErrorCodeErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return ErrorCode
	 */
	EAttribute getErrorCodeErrorPage_ErrorCode();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WelcomeFileList object
	 */
	EClass getWelcomeFileList();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getWelcomeFileList_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getWelcomeFileList_File();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WelcomeFile object
	 */
	EClass getWelcomeFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getWelcomeFile_WelcomeFile();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getWelcomeFile_FileList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TagLibRef object
	 */
	EClass getTagLibRef();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getTagLibRef_TaglibURI();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getTagLibRef_TaglibLocation();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.j2ee.webapplication.TagLibRef#getWebApp <em>Web App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Web App</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.TagLibRef#getWebApp()
	 * @see #getTagLibRef()
	 * @generated
	 */
	EReference getTagLibRef_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityConstraint object
	 */
	EClass getSecurityConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.SecurityConstraint#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.SecurityConstraint#getDisplayName()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EAttribute getSecurityConstraint_DisplayName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getSecurityConstraint_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getSecurityConstraint_WebResourceCollections();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getSecurityConstraint_AuthConstraint();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getSecurityConstraint_UserDataConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.SecurityConstraint#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.SecurityConstraint#getDisplayNames()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EReference getSecurityConstraint_DisplayNames();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WebResourceCollection object
	 */
	EClass getWebResourceCollection();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getWebResourceCollection_WebResourceName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getWebResourceCollection_Description();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getUrlPattern <em>Url Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Url Pattern</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getUrlPattern()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EAttribute getWebResourceCollection_UrlPattern();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getHttpMethod <em>Http Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Http Method</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getHttpMethod()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EAttribute getWebResourceCollection_HttpMethod();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getWebResourceCollection_SecConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getURLs <em>UR Ls</em>}'.
	 * <!-- begin-user-doc -->
	 * @deprecated since 4/25/2003 use {@link #getWebResourceCollection_UrlPattern()}
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>UR Ls</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getURLs()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EReference getWebResourceCollection_URLs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getDescriptions()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EReference getWebResourceCollection_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getHTTPs <em>HTT Ps</em>}'.
	 * <!-- begin-user-doc -->
	 * @deprecated since 4/25/2003 use {@link #getWebResourceCollection_HttpMethod()}
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>HTT Ps</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.WebResourceCollection#getHTTPs()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EReference getWebResourceCollection_HTTPs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return URLPatternType object
	 */
	EClass getURLPatternType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getURLPatternType_UrlPattern();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getURLPatternType_ResCollection();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return HTTPMethodType object
	 */
	EClass getHTTPMethodType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.HTTPMethodType#getHttpMethod <em>Http Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Method</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.HTTPMethodType#getHttpMethod()
	 * @see #getHTTPMethodType()
	 * @generated
	 */
	EAttribute getHTTPMethodType_HttpMethod();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AuthConstraint object
	 */
	EClass getAuthConstraint();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return Description
	 */
	EAttribute getAuthConstraint_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return Roles
	 */
	EAttribute getAuthConstraint_Roles();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @return SecConstraint object
	 */
	EReference getAuthConstraint_SecConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.AuthConstraint#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.AuthConstraint#getDescriptions()
	 * @see #getAuthConstraint()
	 * @generated
	 */
	EReference getAuthConstraint_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return UserDataConstraint object
	 */
	EClass getUserDataConstraint();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getUserDataConstraint_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getUserDataConstraint_TransportGuarantee();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getUserDataConstraint_SecConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.UserDataConstraint#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.UserDataConstraint#getDescriptions()
	 * @see #getUserDataConstraint()
	 * @generated
	 */
	EReference getUserDataConstraint_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return LoginConfig object
	 */
	EClass getLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLoginConfig_AuthMethod();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getLoginConfig_RealmName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.LoginConfig#getAuthorizationMethod <em>Authorization Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authorization Method</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.LoginConfig#getAuthorizationMethod()
	 * @see #getLoginConfig()
	 * @generated
	 */
	EAttribute getLoginConfig_AuthorizationMethod();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLoginConfig_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getLoginConfig_FormLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return FormLoginConfig object
	 */
	EClass getFormLoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFormLoginConfig_FormLoginPage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFormLoginConfig_FormErrorPage();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFormLoginConfig_LoginConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MimeMapping object
	 */
	EClass getMimeMapping();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMimeMapping_Extension();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getMimeMapping_MimeType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getMimeMapping_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SessionConfig object
	 */
	EClass getSessionConfig();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSessionConfig_SessionTimeout();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getSessionConfig_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ServletMapping object
	 */
	EClass getServletMapping();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getServletMapping_UrlPattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.ServletMapping#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.ServletMapping#getName()
	 * @see #getServletMapping()
	 * @generated
	 */
	EAttribute getServletMapping_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServletMapping_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServletMapping_Servlet();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Servlet object
	 */
	EClass getServlet();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getServlet_ServletName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getServlet_LoadOnStartup();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServlet_WebApp();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServlet_WebType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServlet_Params();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServlet_SecurityRoleRefs();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getServlet_RunAs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.Servlet#getInitParams <em>Init Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Init Params</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.Servlet#getInitParams()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_InitParams();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return WebType object
	 */
	EClass getWebType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ServletType object
	 */
	EClass getServletType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getServletType_ClassName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPType object
	 */
	EClass getJSPType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPType_JspFile();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return InitParam object
	 */
	EClass getInitParam();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getInitParam_ParamName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getInitParam_ParamValue();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getInitParam_Description();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Filter object
	 */
	EClass getFilter();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFilter_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFilter_InitParams();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFilter_FilterClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.Filter#getInitParamValues <em>Init Param Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Init Param Values</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.Filter#getInitParamValues()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_InitParamValues();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return FilterMapping object
	 */
	EClass getFilterMapping();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getFilterMapping_UrlPattern();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.j2ee.webapplication.FilterMapping#getDispatcherType <em>Dispatcher Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Dispatcher Type</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.FilterMapping#getDispatcherType()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EAttribute getFilterMapping_DispatcherType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFilterMapping_Filter();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getFilterMapping_Servlet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList <em>Local Encoding Mapping List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Encoding Mapping List</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList
	 * @generated
	 */
	EClass getLocalEncodingMappingList();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList#getLocalEncodingMappings <em>Local Encoding Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Local Encoding Mappings</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList#getLocalEncodingMappings()
	 * @see #getLocalEncodingMappingList()
	 * @generated
	 */
	EReference getLocalEncodingMappingList_LocalEncodingMappings();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping <em>Local Encoding Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local Encoding Mapping</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping
	 * @generated
	 */
	EClass getLocalEncodingMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping#getLocale <em>Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Locale</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping#getLocale()
	 * @see #getLocalEncodingMapping()
	 * @generated
	 */
	EAttribute getLocalEncodingMapping_Locale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping#getEncoding()
	 * @see #getLocalEncodingMapping()
	 * @generated
	 */
	EAttribute getLocalEncodingMapping_Encoding();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.webapplication.ResAuthServletType <em>Res Auth Servlet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Res Auth Servlet Type</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.ResAuthServletType
	 * @generated
	 */
	EEnum getResAuthServletType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum <em>HTTP Method Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>HTTP Method Type Enum</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum
	 * @generated
	 */
	EEnum getHTTPMethodTypeEnum();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return RoleNameType object
	 */
	EClass getRoleNameType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getRoleNameType_RoleName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TransportGuaranteeType object
	 */
	EEnum getTransportGuaranteeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.webapplication.DispatcherType <em>Dispatcher Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Dispatcher Type</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.DispatcherType
	 * @generated
	 */
	EEnum getDispatcherType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.webapplication.AuthMethodKind <em>Auth Method Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Auth Method Kind</em>'.
	 * @see org.eclipse.jst.j2ee.webapplication.AuthMethodKind
	 * @generated
	 */
	EEnum getAuthMethodKind();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	WebapplicationFactory getWebapplicationFactory();

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
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.WebAppImpl <em>Web App</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebAppImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getWebApp()
		 * @generated
		 */
		EClass WEB_APP = eINSTANCE.getWebApp();

		/**
		 * The meta object literal for the '<em><b>Distributable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__DISTRIBUTABLE = eINSTANCE.getWebApp_Distributable();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__VERSION = eINSTANCE.getWebApp_Version();

		/**
		 * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__CONTEXTS = eINSTANCE.getWebApp_Contexts();

		/**
		 * The meta object literal for the '<em><b>Error Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__ERROR_PAGES = eINSTANCE.getWebApp_ErrorPages();

		/**
		 * The meta object literal for the '<em><b>File List</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__FILE_LIST = eINSTANCE.getWebApp_FileList();

		/**
		 * The meta object literal for the '<em><b>Tag Libs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__TAG_LIBS = eINSTANCE.getWebApp_TagLibs();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__CONSTRAINTS = eINSTANCE.getWebApp_Constraints();

		/**
		 * The meta object literal for the '<em><b>Login Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__LOGIN_CONFIG = eINSTANCE.getWebApp_LoginConfig();

		/**
		 * The meta object literal for the '<em><b>Mime Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__MIME_MAPPINGS = eINSTANCE.getWebApp_MimeMappings();

		/**
		 * The meta object literal for the '<em><b>Session Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SESSION_CONFIG = eINSTANCE.getWebApp_SessionConfig();

		/**
		 * The meta object literal for the '<em><b>Servlet Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SERVLET_MAPPINGS = eINSTANCE.getWebApp_ServletMappings();

		/**
		 * The meta object literal for the '<em><b>Servlets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SERVLETS = eINSTANCE.getWebApp_Servlets();

		/**
		 * The meta object literal for the '<em><b>Security Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SECURITY_ROLES = eINSTANCE.getWebApp_SecurityRoles();

		/**
		 * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__FILTERS = eINSTANCE.getWebApp_Filters();

		/**
		 * The meta object literal for the '<em><b>Filter Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__FILTER_MAPPINGS = eINSTANCE.getWebApp_FilterMappings();

		/**
		 * The meta object literal for the '<em><b>Listeners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__LISTENERS = eINSTANCE.getWebApp_Listeners();

		/**
		 * The meta object literal for the '<em><b>Context Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__CONTEXT_PARAMS = eINSTANCE.getWebApp_ContextParams();

		/**
		 * The meta object literal for the '<em><b>Jsp Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__JSP_CONFIG = eINSTANCE.getWebApp_JspConfig();

		/**
		 * The meta object literal for the '<em><b>Message Destinations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__MESSAGE_DESTINATIONS = eINSTANCE.getWebApp_MessageDestinations();

		/**
		 * The meta object literal for the '<em><b>Local Encoding Mapping List</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__LOCAL_ENCODING_MAPPING_LIST = eINSTANCE.getWebApp_LocalEncodingMappingList();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ContextParamImpl <em>Context Param</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ContextParamImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getContextParam()
		 * @generated
		 */
		EClass CONTEXT_PARAM = eINSTANCE.getContextParam();

		/**
		 * The meta object literal for the '<em><b>Param Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTEXT_PARAM__PARAM_NAME = eINSTANCE.getContextParam_ParamName();

		/**
		 * The meta object literal for the '<em><b>Param Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTEXT_PARAM__PARAM_VALUE = eINSTANCE.getContextParam_ParamValue();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTEXT_PARAM__DESCRIPTION = eINSTANCE.getContextParam_Description();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_PARAM__WEB_APP = eINSTANCE.getContextParam_WebApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ServletImpl <em>Servlet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ServletImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getServlet()
		 * @generated
		 */
		EClass SERVLET = eINSTANCE.getServlet();

		/**
		 * The meta object literal for the '<em><b>Servlet Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__SERVLET_NAME = eINSTANCE.getServlet_ServletName();

		/**
		 * The meta object literal for the '<em><b>Load On Startup</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__LOAD_ON_STARTUP = eINSTANCE.getServlet_LoadOnStartup();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__WEB_APP = eINSTANCE.getServlet_WebApp();

		/**
		 * The meta object literal for the '<em><b>Web Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__WEB_TYPE = eINSTANCE.getServlet_WebType();

		/**
		 * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__PARAMS = eINSTANCE.getServlet_Params();

		/**
		 * The meta object literal for the '<em><b>Security Role Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__SECURITY_ROLE_REFS = eINSTANCE.getServlet_SecurityRoleRefs();

		/**
		 * The meta object literal for the '<em><b>Run As</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__RUN_AS = eINSTANCE.getServlet_RunAs();

		/**
		 * The meta object literal for the '<em><b>Init Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__INIT_PARAMS = eINSTANCE.getServlet_InitParams();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ServletMappingImpl <em>Servlet Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ServletMappingImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getServletMapping()
		 * @generated
		 */
		EClass SERVLET_MAPPING = eINSTANCE.getServletMapping();

		/**
		 * The meta object literal for the '<em><b>Url Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET_MAPPING__URL_PATTERN = eINSTANCE.getServletMapping_UrlPattern();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET_MAPPING__NAME = eINSTANCE.getServletMapping_Name();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET_MAPPING__WEB_APP = eINSTANCE.getServletMapping_WebApp();

		/**
		 * The meta object literal for the '<em><b>Servlet</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET_MAPPING__SERVLET = eINSTANCE.getServletMapping_Servlet();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.SessionConfigImpl <em>Session Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.SessionConfigImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getSessionConfig()
		 * @generated
		 */
		EClass SESSION_CONFIG = eINSTANCE.getSessionConfig();

		/**
		 * The meta object literal for the '<em><b>Session Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION_CONFIG__SESSION_TIMEOUT = eINSTANCE.getSessionConfig_SessionTimeout();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION_CONFIG__WEB_APP = eINSTANCE.getSessionConfig_WebApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.MimeMappingImpl <em>Mime Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.MimeMappingImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getMimeMapping()
		 * @generated
		 */
		EClass MIME_MAPPING = eINSTANCE.getMimeMapping();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MIME_MAPPING__EXTENSION = eINSTANCE.getMimeMapping_Extension();

		/**
		 * The meta object literal for the '<em><b>Mime Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MIME_MAPPING__MIME_TYPE = eINSTANCE.getMimeMapping_MimeType();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MIME_MAPPING__WEB_APP = eINSTANCE.getMimeMapping_WebApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.WelcomeFileListImpl <em>Welcome File List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WelcomeFileListImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getWelcomeFileList()
		 * @generated
		 */
		EClass WELCOME_FILE_LIST = eINSTANCE.getWelcomeFileList();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WELCOME_FILE_LIST__WEB_APP = eINSTANCE.getWelcomeFileList_WebApp();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WELCOME_FILE_LIST__FILE = eINSTANCE.getWelcomeFileList_File();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ErrorPageImpl <em>Error Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ErrorPageImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getErrorPage()
		 * @generated
		 */
		EClass ERROR_PAGE = eINSTANCE.getErrorPage();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PAGE__LOCATION = eINSTANCE.getErrorPage_Location();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ERROR_PAGE__WEB_APP = eINSTANCE.getErrorPage_WebApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.TagLibRefImpl <em>Tag Lib Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.TagLibRefImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getTagLibRef()
		 * @generated
		 */
		EClass TAG_LIB_REF = eINSTANCE.getTagLibRef();

		/**
		 * The meta object literal for the '<em><b>Taglib URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB_REF__TAGLIB_URI = eINSTANCE.getTagLibRef_TaglibURI();

		/**
		 * The meta object literal for the '<em><b>Taglib Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB_REF__TAGLIB_LOCATION = eINSTANCE.getTagLibRef_TaglibLocation();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_LIB_REF__WEB_APP = eINSTANCE.getTagLibRef_WebApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.SecurityConstraintImpl <em>Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.SecurityConstraintImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getSecurityConstraint()
		 * @generated
		 */
		EClass SECURITY_CONSTRAINT = eINSTANCE.getSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_CONSTRAINT__DISPLAY_NAME = eINSTANCE.getSecurityConstraint_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__WEB_APP = eINSTANCE.getSecurityConstraint_WebApp();

		/**
		 * The meta object literal for the '<em><b>Web Resource Collections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS = eINSTANCE.getSecurityConstraint_WebResourceCollections();

		/**
		 * The meta object literal for the '<em><b>Auth Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__AUTH_CONSTRAINT = eINSTANCE.getSecurityConstraint_AuthConstraint();

		/**
		 * The meta object literal for the '<em><b>User Data Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT = eINSTANCE.getSecurityConstraint_UserDataConstraint();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__DISPLAY_NAMES = eINSTANCE.getSecurityConstraint_DisplayNames();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.WebResourceCollectionImpl <em>Web Resource Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebResourceCollectionImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getWebResourceCollection()
		 * @generated
		 */
		EClass WEB_RESOURCE_COLLECTION = eINSTANCE.getWebResourceCollection();

		/**
		 * The meta object literal for the '<em><b>Web Resource Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME = eINSTANCE.getWebResourceCollection_WebResourceName();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__DESCRIPTION = eINSTANCE.getWebResourceCollection_Description();

		/**
		 * The meta object literal for the '<em><b>Url Pattern</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__URL_PATTERN = eINSTANCE.getWebResourceCollection_UrlPattern();

		/**
		 * The meta object literal for the '<em><b>Http Method</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__HTTP_METHOD = eINSTANCE.getWebResourceCollection_HttpMethod();

		/**
		 * The meta object literal for the '<em><b>Sec Constraint</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT = eINSTANCE.getWebResourceCollection_SecConstraint();

		/**
		 * The meta object literal for the '<em><b>UR Ls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_RESOURCE_COLLECTION__UR_LS = eINSTANCE.getWebResourceCollection_URLs();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_RESOURCE_COLLECTION__DESCRIPTIONS = eINSTANCE.getWebResourceCollection_Descriptions();

		/**
		 * The meta object literal for the '<em><b>HTT Ps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_RESOURCE_COLLECTION__HTT_PS = eINSTANCE.getWebResourceCollection_HTTPs();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.AuthConstraintImpl <em>Auth Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.AuthConstraintImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getAuthConstraint()
		 * @generated
		 */
		EClass AUTH_CONSTRAINT = eINSTANCE.getAuthConstraint();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTH_CONSTRAINT__DESCRIPTION = eINSTANCE.getAuthConstraint_Description();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTH_CONSTRAINT__ROLES = eINSTANCE.getAuthConstraint_Roles();

		/**
		 * The meta object literal for the '<em><b>Sec Constraint</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTH_CONSTRAINT__SEC_CONSTRAINT = eINSTANCE.getAuthConstraint_SecConstraint();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTH_CONSTRAINT__DESCRIPTIONS = eINSTANCE.getAuthConstraint_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.UserDataConstraintImpl <em>User Data Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.UserDataConstraintImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getUserDataConstraint()
		 * @generated
		 */
		EClass USER_DATA_CONSTRAINT = eINSTANCE.getUserDataConstraint();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA_CONSTRAINT__DESCRIPTION = eINSTANCE.getUserDataConstraint_Description();

		/**
		 * The meta object literal for the '<em><b>Transport Guarantee</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE = eINSTANCE.getUserDataConstraint_TransportGuarantee();

		/**
		 * The meta object literal for the '<em><b>Sec Constraint</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DATA_CONSTRAINT__SEC_CONSTRAINT = eINSTANCE.getUserDataConstraint_SecConstraint();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DATA_CONSTRAINT__DESCRIPTIONS = eINSTANCE.getUserDataConstraint_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.LoginConfigImpl <em>Login Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.LoginConfigImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getLoginConfig()
		 * @generated
		 */
		EClass LOGIN_CONFIG = eINSTANCE.getLoginConfig();

		/**
		 * The meta object literal for the '<em><b>Auth Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_CONFIG__AUTH_METHOD = eINSTANCE.getLoginConfig_AuthMethod();

		/**
		 * The meta object literal for the '<em><b>Realm Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_CONFIG__REALM_NAME = eINSTANCE.getLoginConfig_RealmName();

		/**
		 * The meta object literal for the '<em><b>Authorization Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_CONFIG__AUTHORIZATION_METHOD = eINSTANCE.getLoginConfig_AuthorizationMethod();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGIN_CONFIG__WEB_APP = eINSTANCE.getLoginConfig_WebApp();

		/**
		 * The meta object literal for the '<em><b>Form Login Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGIN_CONFIG__FORM_LOGIN_CONFIG = eINSTANCE.getLoginConfig_FormLoginConfig();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.FormLoginConfigImpl <em>Form Login Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.FormLoginConfigImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getFormLoginConfig()
		 * @generated
		 */
		EClass FORM_LOGIN_CONFIG = eINSTANCE.getFormLoginConfig();

		/**
		 * The meta object literal for the '<em><b>Form Login Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE = eINSTANCE.getFormLoginConfig_FormLoginPage();

		/**
		 * The meta object literal for the '<em><b>Form Error Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_LOGIN_CONFIG__FORM_ERROR_PAGE = eINSTANCE.getFormLoginConfig_FormErrorPage();

		/**
		 * The meta object literal for the '<em><b>Login Config</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_LOGIN_CONFIG__LOGIN_CONFIG = eINSTANCE.getFormLoginConfig_LoginConfig();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.InitParamImpl <em>Init Param</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.InitParamImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getInitParam()
		 * @generated
		 */
		EClass INIT_PARAM = eINSTANCE.getInitParam();

		/**
		 * The meta object literal for the '<em><b>Param Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INIT_PARAM__PARAM_NAME = eINSTANCE.getInitParam_ParamName();

		/**
		 * The meta object literal for the '<em><b>Param Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INIT_PARAM__PARAM_VALUE = eINSTANCE.getInitParam_ParamValue();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INIT_PARAM__DESCRIPTION = eINSTANCE.getInitParam_Description();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.WebTypeImpl <em>Web Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebTypeImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getWebType()
		 * @generated
		 */
		EClass WEB_TYPE = eINSTANCE.getWebType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ServletTypeImpl <em>Servlet Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ServletTypeImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getServletType()
		 * @generated
		 */
		EClass SERVLET_TYPE = eINSTANCE.getServletType();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET_TYPE__CLASS_NAME = eINSTANCE.getServletType_ClassName();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.JSPTypeImpl <em>JSP Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.JSPTypeImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getJSPType()
		 * @generated
		 */
		EClass JSP_TYPE = eINSTANCE.getJSPType();

		/**
		 * The meta object literal for the '<em><b>Jsp File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TYPE__JSP_FILE = eINSTANCE.getJSPType_JspFile();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.URLPatternTypeImpl <em>URL Pattern Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.URLPatternTypeImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getURLPatternType()
		 * @generated
		 */
		EClass URL_PATTERN_TYPE = eINSTANCE.getURLPatternType();

		/**
		 * The meta object literal for the '<em><b>Url Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute URL_PATTERN_TYPE__URL_PATTERN = eINSTANCE.getURLPatternType_UrlPattern();

		/**
		 * The meta object literal for the '<em><b>Res Collection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference URL_PATTERN_TYPE__RES_COLLECTION = eINSTANCE.getURLPatternType_ResCollection();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.RoleNameTypeImpl <em>Role Name Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.RoleNameTypeImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getRoleNameType()
		 * @generated
		 */
		EClass ROLE_NAME_TYPE = eINSTANCE.getRoleNameType();

		/**
		 * The meta object literal for the '<em><b>Role Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_NAME_TYPE__ROLE_NAME = eINSTANCE.getRoleNameType_RoleName();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.WelcomeFileImpl <em>Welcome File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WelcomeFileImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getWelcomeFile()
		 * @generated
		 */
		EClass WELCOME_FILE = eINSTANCE.getWelcomeFile();

		/**
		 * The meta object literal for the '<em><b>Welcome File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WELCOME_FILE__WELCOME_FILE = eINSTANCE.getWelcomeFile_WelcomeFile();

		/**
		 * The meta object literal for the '<em><b>File List</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WELCOME_FILE__FILE_LIST = eINSTANCE.getWelcomeFile_FileList();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ExceptionTypeErrorPageImpl <em>Exception Type Error Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ExceptionTypeErrorPageImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getExceptionTypeErrorPage()
		 * @generated
		 */
		EClass EXCEPTION_TYPE_ERROR_PAGE = eINSTANCE.getExceptionTypeErrorPage();

		/**
		 * The meta object literal for the '<em><b>Exception Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTION_TYPE_ERROR_PAGE__EXCEPTION_TYPE = eINSTANCE.getExceptionTypeErrorPage_ExceptionType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.ErrorCodeErrorPageImpl <em>Error Code Error Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.ErrorCodeErrorPageImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getErrorCodeErrorPage()
		 * @generated
		 */
		EClass ERROR_CODE_ERROR_PAGE = eINSTANCE.getErrorCodeErrorPage();

		/**
		 * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_CODE_ERROR_PAGE__ERROR_CODE = eINSTANCE.getErrorCodeErrorPage_ErrorCode();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.FilterMappingImpl <em>Filter Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.FilterMappingImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getFilterMapping()
		 * @generated
		 */
		EClass FILTER_MAPPING = eINSTANCE.getFilterMapping();

		/**
		 * The meta object literal for the '<em><b>Url Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__URL_PATTERN = eINSTANCE.getFilterMapping_UrlPattern();

		/**
		 * The meta object literal for the '<em><b>Dispatcher Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__DISPATCHER_TYPE = eINSTANCE.getFilterMapping_DispatcherType();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_MAPPING__FILTER = eINSTANCE.getFilterMapping_Filter();

		/**
		 * The meta object literal for the '<em><b>Servlet</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_MAPPING__SERVLET = eINSTANCE.getFilterMapping_Servlet();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.FilterImpl <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.FilterImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getFilter()
		 * @generated
		 */
		EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__NAME = eINSTANCE.getFilter_Name();

		/**
		 * The meta object literal for the '<em><b>Init Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__INIT_PARAMS = eINSTANCE.getFilter_InitParams();

		/**
		 * The meta object literal for the '<em><b>Filter Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__FILTER_CLASS = eINSTANCE.getFilter_FilterClass();

		/**
		 * The meta object literal for the '<em><b>Init Param Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__INIT_PARAM_VALUES = eINSTANCE.getFilter_InitParamValues();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingListImpl <em>Local Encoding Mapping List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingListImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getLocalEncodingMappingList()
		 * @generated
		 */
		EClass LOCAL_ENCODING_MAPPING_LIST = eINSTANCE.getLocalEncodingMappingList();

		/**
		 * The meta object literal for the '<em><b>Local Encoding Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCAL_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS = eINSTANCE.getLocalEncodingMappingList_LocalEncodingMappings();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingImpl <em>Local Encoding Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.LocalEncodingMappingImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getLocalEncodingMapping()
		 * @generated
		 */
		EClass LOCAL_ENCODING_MAPPING = eINSTANCE.getLocalEncodingMapping();

		/**
		 * The meta object literal for the '<em><b>Locale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ENCODING_MAPPING__LOCALE = eINSTANCE.getLocalEncodingMapping_Locale();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_ENCODING_MAPPING__ENCODING = eINSTANCE.getLocalEncodingMapping_Encoding();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.internal.impl.HTTPMethodTypeImpl <em>HTTP Method Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.HTTPMethodTypeImpl
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getHTTPMethodType()
		 * @generated
		 */
		EClass HTTP_METHOD_TYPE = eINSTANCE.getHTTPMethodType();

		/**
		 * The meta object literal for the '<em><b>Http Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_METHOD_TYPE__HTTP_METHOD = eINSTANCE.getHTTPMethodType_HttpMethod();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.ResAuthServletType <em>Res Auth Servlet Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.ResAuthServletType
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getResAuthServletType()
		 * @generated
		 */
		EEnum RES_AUTH_SERVLET_TYPE = eINSTANCE.getResAuthServletType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum <em>HTTP Method Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getHTTPMethodTypeEnum()
		 * @generated
		 */
		EEnum HTTP_METHOD_TYPE_ENUM = eINSTANCE.getHTTPMethodTypeEnum();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.TransportGuaranteeType <em>Transport Guarantee Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.TransportGuaranteeType
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getTransportGuaranteeType()
		 * @generated
		 */
		EEnum TRANSPORT_GUARANTEE_TYPE = eINSTANCE.getTransportGuaranteeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.DispatcherType <em>Dispatcher Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.DispatcherType
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getDispatcherType()
		 * @generated
		 */
		EEnum DISPATCHER_TYPE = eINSTANCE.getDispatcherType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.webapplication.AuthMethodKind <em>Auth Method Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.webapplication.AuthMethodKind
		 * @see org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationPackageImpl#getAuthMethodKind()
		 * @generated
		 */
		EEnum AUTH_METHOD_KIND = eINSTANCE.getAuthMethodKind();

	}

} //WebapplicationPackage















