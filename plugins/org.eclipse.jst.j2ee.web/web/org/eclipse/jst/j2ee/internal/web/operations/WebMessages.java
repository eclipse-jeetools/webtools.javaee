/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 13, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jst.j2ee.common.J2EECommonMessages;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebMessages extends J2EECommonMessages {
	public static final String ERR_JAR_NAME_EMPTY = "ERR_JAR_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_JAR_NAME_EXIST = "ERR_JAR_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_JAVA_PROJECT_NAME_EMPTY = "ERR_JAVA_PROJECT_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_JAVA_PROJECT_NAME_EXIST = "ERR_JAVA_PROJECT_NAME_EXIST"; //$NON-NLS-1$

	public static final String ERR_DUPLICATED_INIT_PARAMETER = "ERR_DUPLICATED_INIT_PARAMETER"; //$NON-NLS-1$
	public static final String ERR_DUPLICATED_URL_MAPPING = "ERR_DUPLICATED_URL_MAPPING"; //$NON-NLS-1$

	public static final String ERR_DISPLAY_NAME_EMPTY = "ERR_DISPLAY_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_SERVLET_NAME_EXIST = "ERR_SERVLET_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_SERVLET_DISPLAY_NAME_EXIST = "ERR_SERVLET_DISPLAY_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_SERVLET_CLASS_NAME_USED = "ERR_SERVLET_CLASS_NAME_USED"; //$NON-NLS-1$
	public static final String ERR_SERVLET_JAVA_CLASS_NAME_INVALID = "ERR_SERVLET_JAVA_CLASS_NAME_INVALID"; //$NON-NLS-1$
	public static final String ERR_SERVLET_MAPPING_SERVLET_NOT_EXIST = "ERR_SERVLET_MAPPING_SERVLET_NOT_EXIST"; //$NON-NLS-1$
	public static final String ERR_SERVLET_MAPPING_URL_PATTERN_EMPTY = "ERR_SERVLET_MAPPING_URL_PATTERN_EMPTY"; //$NON-NLS-1$
	public static final String ERR_SERVLET_MAPPING_URL_PATTERN_EXIST = "ERR_SERVLET_MAPPING_URL_PATTERN_EXIST"; //$NON-NLS-1$
	public static final String ERR_SERVLET_PARAMETER_NAME_EMPTY = "ERR_SERVLET_PARAMETER_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_SERVLET_PARAMETER_NAME_EXIST = "ERR_SERVLET_PARAMETER_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_SERVLET_PARAMETER_VALUE_EMPTY = "ERR_SERVLET_PARAMETER_VALUE_EMPTY"; //$NON-NLS-1$
	public static final String ERR_SERVLET_PARAMETER_VALUE_EXIST = "ERR_SERVLET_PARAMETER_VALUE_EXIST"; //$NON-NLS-1$


	public static final String ERR_PAGE_ENCODING_EMPTY = "ERR_PAGE_ENCODING_EMPTY"; //$NON-NLS-1$
	public static final String ERR_PAGE_ENCODING_EXIST = "ERR_PAGE_ENCODING_EXIST"; //$NON-NLS-1$
	public static final String ERR_URL_PATTERN_EXIST = "ERR_URL_PATTERN_EXIST"; //$NON-NLS-1$
	public static final String ERR_INCLUDE_PRELUDES_EXIST = "ERR_INCLUDE_PRELUDES_EXIST"; //$NON-NLS-1$
	public static final String ERR_INCLUDE_CODAS_EXIST = "ERR_INCLUDE_CODAS_EXIST"; //$NON-NLS-1$

	public static final String ERR_SECURITY_CONSTRAINT_NAME_EMPTY = "ERR_SECURITY_CONSTRAINT_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_SECURITY_CONSTRAINT_NAME_EXIST = "ERR_SECURITY_CONSTRAINT_NAME_EXIST"; //$NON-NLS-1$

	public static final String ERR_SERVLET_ROLE_REF_NAME_EMPTY = "ERR_SERVLET_ROLE_REF_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_SERVLET_ROLE_REF_NAME_EXIST = "ERR_SERVLET_ROLE_REF_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_SERVLET_ROLE_LINK_EMPTY = "ERR_SERVLET_ROLE_LINK_EMPTY"; //$NON-NLS-1$

	public static final String ERR_CONTEXT_PARAMETER_NAME_EMPTY = "ERR_CONTEXT_PARAMETER_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_CONTEXT_PARAMETER_NAME_EXIST = "ERR_CONTEXT_PARAMETER_NAME_EXIST"; //$NON-NLS-1$

	public static final String ERR_RESOURCE_NAME_EMPTY = "ERR_RESOURCE_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_RESOURCE_NAME_EXIST = "ERR_RESOURCE_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_URL_PATTERNS_EMPTY = "ERR_URL_PATTERNS_EMPTY"; //$NON-NLS-1$

	public static final String ERR_MIME_MAPPING_EXTENSION_EMPTY = "ERR_MIME_MAPPING_EXTENSION_EMPTY"; //$NON-NLS-1$
	public static final String ERR_MIME_MAPPING_EXTENSION_EXIST = "ERR_MIME_MAPPING_EXTENSION_EXIST"; //$NON-NLS-1$

	public static final String ERR_FILTER_DISPLAY_NAME_EXIST = "ERR_FILTER_DISPLAY_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_FILTER_CLASS_NAME_USED = "ERR_FILTER_CLASS_NAME_USED"; //$NON-NLS-1$
	public static final String ERR_FILTER_CLASS_NAME_INVALID = "ERR_FILTER_CLASS_NAME_INVALID"; //$NON-NLS-1$
	public static final String ERR_FILTER_MAPPING_URL_PATTERN_EMPTY = "3"; //$NON-NLS-1$
	public static final String ERR_FILTER_MAPPING_URL_PATTERN_EXIST = "4"; //$NON-NLS-1$
	public static final String ERR_FILTER_MAPPING_SERVLET_EMPTY = "5"; //$NON-NLS-1$
	public static final String ERR_FILTER_MAPPING_SERVLET_EXIST = "ERR_FILTER_MAPPING_SERVLET_EXIST"; //$NON-NLS-1$
	public static final String ERR_FILTER_MAPPING_SERVLET_DISPATCHER_TYPES_EMPTY = "ERR_FILTER_MAPPING_SERVLET_DISPATCHER_TYPES_EMPTY"; //$NON-NLS-1$
	public static final String ERR_FILTER_PARAM_NAME_EMPTY = "6"; //$NON-NLS-1$
	public static final String ERR_FILTER_PARAM_NAME_EXISTS = "ERR_FILTER_PARAMETER_NAME_EXIST"; //$NON-NLS-1$

	public static final String ERR_LISTENER_DISPLAY_NAME_EXIST = "ERR_LISTENER_DISPLAY_NAME_EXIST"; //$NON-NLS-1$
	public static final String ERR_LISTENER_CLASS_NAME_USED = "ERR_LISTENER_CLASS_NAME_USED"; //$NON-NLS-1$
	public static final String ERR_LISTENER_CLASS_NAME_INVALID = "ERR_LISTENER_CLASS_NAME_INVALID"; //$NON-NLS-1$

	public static final String ERR_ENV_ENTRY_NAME_EMPTY = "ERR_ENV_ENTRY_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_ENV_ENTRY_NAME_EXIST = "ERR_ENV_ENTRY_NAME_EXIST"; //$NON-NLS-1$

	public static final String ERR_WELCOME_PAGE_EMPTY = "ERR_WELCOME_PAGE_EMPTY"; //$NON-NLS-1$
	public static final String ERR_WELCOME_PAGE_EXIST = "ERR_WELCOME_PAGE_EXIST"; //$NON-NLS-1$

	public static final String ERR_ERROR_CODE_EMPTY = "ERR_ERROR_CODE_EMPTY"; //$NON-NLS-1$
	public static final String ERR_ERROR_CODE_MUST_BE_3_DIGITS = "ERR_ERROR_CODE_MUST_BE_3_DIGITS"; //$NON-NLS-1$

	public static final String ERR_ERROR_PAGE_LOCATION_EMPTY = "ERR_ERROR_PAGE_LOCATION_EMPTY"; //$NON-NLS-1$
	public static final String ERR_ERROR_PAGE_LOCATION_EXIST = "ERR_ERROR_PAGE_LOCATION_EXIST"; //$NON-NLS-1$
	public static final String ERR_ERROR_PAGE_LOCATION_MUST_START_WITH_SLASH = "ERR_ERROR_PAGE_LOCATION_MUST_START_WITH_SLASH"; //$NON-NLS-1$

	public static final String ERR_EXCEPTION_TYPE_EMPTY = "ERR_EXCEPTION_TYPE_EMPTY"; //$NON-NLS-1$

	public static final String ERR_LOCALE_NAME_EMPTY = "ERR_LOCALE_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_LOCALE_NAME_EXIST = "ERR_LOCALE_NAME_EXIST"; //$NON-NLS-1$

	public static final String ERR_TAGLIBREF_URL_EMPTY = "ERR_TAGLIBREF_URL_EMPTY"; //$NON-NLS-1$
	public static final String ERR_TAGLIBREF_URL_EXIST = "ERR_TAGLIBREF_URL_EXIST"; //$NON-NLS-1$

	public static final String ERR_MESSAGE_DESTINATION_NAME_EMPTY = "ERR_MESSAGE_DESTINATION_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_MESSAGE_DESTINATION_NAME_EXIST = "ERR_MESSAGE_DESTINATION_NAME_EXIST"; //$NON-NLS-1$

	//	Security Identity Wizard, EJB EDITOR
	public static final String ERR_EXCEPTION_SECURITY_ID_EMPTY_ROLE_NAME = "ERR_EXCEPTION_ROLE_NAME_EMPTY"; //$NON-NLS-1$
	public static final String ERR_EXCEPTION_SECURITY_ID_NO_BEANS = "ERR_EXCEPTION_SECURITY_ID_NO_BEANS"; //$NON-NLS-1$

	//Method Permission Wizard, EJB EDITOR
	public static final String ERR_EXCEPTION_SECURITY_ROLES_EMPTY = ERR_EXCEPTION_SECURITY_ID_EMPTY_ROLE_NAME;
	public static final String ERR_EXCEPTION_METHOD_ELEMENTS_EMPTY = "ERR_EXCEPTION_METHOD_ELEMENT_EMPTY"; //$NON-NLS-1$

	//Method Transaction Wizard, EJB EDITOR
	public static final String ERR_EXCEPTION_METHOD_TRANSACTION_EMPTY = "ERR_EXCEPTION_METHOD_TRANSACTION_ATTRIBUTES_EMPTY"; //$NON-NLS-1$

	public static final String ERR_SERVLET_LIST_EMPTY = "ERR_SERVLET_LIST_EMPTY"; //$NON-NLS-1$
	public static final String ERR_FILTER_LIST_EMPTY = "ERR_FILTER_LIST_EMPTY"; //$NON-NLS-1$
	public static final String ERR_LISTENER_LIST_EMPTY = "ERR_LISTENER_LIST_EMPTY"; //$NON-NLS-1$

	private static final WebMessages INSTANCE = new WebMessages();

	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		return INSTANCE.doGetResourceString(key);
	}

	public static String getResourceString(String key, Object[] args) {
		return INSTANCE.doGetResourceString(key, args);
	}

	protected WebMessages() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			this.resourceBundle = ResourceBundle.getBundle("web"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
		}
	}
}