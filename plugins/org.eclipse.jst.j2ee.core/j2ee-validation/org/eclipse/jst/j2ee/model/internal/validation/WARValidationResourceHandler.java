/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

import org.eclipse.osgi.util.NLS;

public final class WARValidationResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "warvalidation";//$NON-NLS-1$

	private WARValidationResourceHandler() {
		// Do not instantiate
	}

	public static String ERROR_WAR_VALIDATION_FAILED;
	public static String ERROR_EAR_DUPLICATE_ROLES;
	public static String MESSAGE_WAR_VALIDATION_MISSING_JSP;
	public static String MESSAGE_WAR_VALIDATION_SMALL_ICON;
	public static String MESSAGE_WAR_VALIDATION_LARGE_ICON;
	public static String MESSAGE_WAR_VALIDATION_BROKEN_SERVLET_MAPPING;
	public static String MESSAGE_WAR_VALIDATION_NO_WELCOME_FILE;
	public static String MESSAGE_WAR_VALIDATION_MISSING_WELCOME_FILE;
	public static String MESSAGE_WAR_VALIDATION_MISSING_TLD;
	public static String ERROR_INVALID_WAR_FILE;
	public static String MESSAGE_WAR_VALIDATION_BROKEN_FILTER_MAPPING;
	public static String MESSAGE_WAR_VALIDATION_INVALID_WELCOME_FILE;
	public static String WAR_DD_PARSE_LINECOL;
	public static String WAR_DD_PARSE_LINE;
	public static String WAR_DD_PARSE_NOINFO;
	public static String WAR_DD_CANNOT_OPEN_DD;
	public static String MESSAGE_WAR_VALIDATION_INVALID_URL;
	public static String MESSAGE_WAR_VALIDATION_INVALID_SEC_ROLE;
	public static String MESSAGE_WAR_VALIDATION_INVALID_HTTP_CMD;
	public static String MESSAGE_WAR_VALIDATION_INVALID_TRANSPORT;
	public static String MESSAGE_WAR_VALIDATION_INVALID_JSPFILE_REF;
	public static String ERROR_EAR_INVALID_CONTEXT_ROOT;
	public static String MESSAGE_WAR_VALIDATION_INVALID_ERROR_PAGE;
	public static String MESSAGE_WAR_VALIDATION_INVALID_SEC_ROLE_NAME;
	public static String MESSAGE_WAR_VALIDATION_INVALID_AUTH_METHOD;
	public static String MESSAGE_WAR_VALIDATION_IGNORE_FORM_LOGIN;
	public static String MESSAGE_WAR_VALIDATION_RES_AUTH_REQUIRED_22;
	public static String MESSAGE_WAR_VALIDATION_RES_AUTH_REQUIRED_23;
	public static String MESSAGE_WAR_VALIDATION_RES_AUTH_INVALID_22;
	public static String MESSAGE_WAR_VALIDATION_RES_AUTH_INVALID_23;
	public static String MESSAGE_WAR_VALIDATION_INVALID_EXCEPTION_TYPE;
	public static String MESSAGE_WAR_VALIDATION_DUPLICATE_MAPPING;
	public static String MESSAGE_WAR_VALIDATION_DUPLICATE_SERVLET;
	public static String MESSAGE_WAR_VALIDATION_SESSION_TIMEOUT;
	public static String MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY;
	public static String MESSAGE_WAR_VALIDATION_EMPTY_ENTRY;
	public static String MESSAGE_WAR_VALIDATION_DUPLICATE_FILTER;
	public static String MESSAGE_WAR_VALIDATION_DUPLICATE_EJB_REF;
	public static String ERROR_EAR_UNCONTAINED_MODULE_FILE_EXCEPTION;
	public static String MESSAGE_WAR_VALIDATION_INVALID_ERROR_CODE;
	public static String MESSAGE_WAR_VALIDATION_INVALID_RES_SHARING_SCOPE;
	public static String MESSAGE_WAR_VALIDATION_INVALID_ENV_ENTRY;
	public static String MESSAGE_WAR_VALIDATION_MISSING_WLP_PROJECT;
	public static String MESSAGE_WAR_VALIDATION_CONFLICTING_WLP_PROJECT;
	public static String MSG_SERVER_TARGET_MISMATCH;
	public static String MESSAGE_URL_PATTERN_END_WITH_CARRAIGE_RETURN;
	public static String MESSAGE_WAR_VALIDATION_INVALID_EJB_REF_TYPE;
	public static String ERROR_EAR_MISSING_EJB_ROLE;
	public static String of_Type_Security_Role_Name_6;
	public static String Web_Archive_Validator_8;
	public static String of_Type_Role_Name_19;
	public static String of_Type_Parameter_Name_25;
	public static String of_Type_Parameter_Value_26;
	public static String of_Type_Parameter_Name_32;
	public static String of_Type_Parameter_Value_33;
	public static String of_Type_Parameter_Name_39;
	public static String of_Type_Parameter_Value_40;
	public static String of_Type_ejb_ref_name_44;
	public static String of_Type_Error_Location_47;
	public static String of_Type_Error_Location_49;
	public static String of_Type_Mime_Extension_54;
	public static String of_Type_Mime_Type_55;
	public static String of_Type_Mime_Extension___56;
	public static String of_Type_Web_Resource_Collection_64;
	public static String of_Type_Filter_Name_66;
	public static String Invalid_URL_70;
	public static String Invalid_URL_75;
	public static String of_Type_Servlet_Name_77;
	public static String of_Type_Taglib_80;
	public static String of_Type_TagLib___81;
	public static String of_Type_Web_Resource_Name_84;
	public static String of_Type_Welcome_File_Name__87;
	public static String of_Type_Env_Entry_Name___88;

	static {
		NLS.initializeMessages(BUNDLE_NAME, WARValidationResourceHandler.class);
	}
}