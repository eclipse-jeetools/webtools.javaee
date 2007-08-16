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
package org.eclipse.jst.j2ee.internal.xml;



/**
 * This interface represents a catalog of all the tag names which occur in 
 * an WAR deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */
public interface WarDeploymentDescriptorXmlMapperI extends DeploymentDescriptorXmlMapperI {
	public String AUTH_CONSTRAINT = "auth-constraint";//$NON-NLS-1$
	public String AUTH_METHOD = "auth-method";//$NON-NLS-1$
	public String CONTEXT_PARAM = "context-param";//$NON-NLS-1$
	public String DISTRIBUTABLE = "distributable";//$NON-NLS-1$
	public String ERROR_CODE = "error-code";//$NON-NLS-1$
	public String ERROR_PAGE = "error-page";//$NON-NLS-1$
	public String EXCEPTION_TYPE = "exception-type";//$NON-NLS-1$
	public String EXTENSION = "extension";//$NON-NLS-1$
	public String FILTER = "filter";			//$NON-NLS-1$		// Servlet2.3
	public String FILTER_MAPPING = "filter-mapping";//$NON-NLS-1$	// Servlet2.3
	public String FILTER_NAME = "filter-name";		//$NON-NLS-1$	// Servlet2.3
	public String FILTER_CLASS = "filter-class";//$NON-NLS-1$		// Servlet2.3
	public String FORM_ERROR_PAGE = "form-error-page";//$NON-NLS-1$
	public String FORM_LOGIN_CONFIG = "form-login-config";//$NON-NLS-1$
	public String FORM_LOGIN_PAGE = "form-login-page";//$NON-NLS-1$
	public String HTTP_METHOD = "http-method";//$NON-NLS-1$
	public String INIT_PARAM = "init-param";//$NON-NLS-1$
	public String JSP_FILE = "jsp-file";//$NON-NLS-1$
	public String LISTENER = "listener";	//$NON-NLS-1$			// Servlet2.3
	public String LOAD_ON_STARTUP = "load-on-startup";//$NON-NLS-1$
	public String LOCATION = "location";//$NON-NLS-1$
	public String LOGIN_CONFIG = "login-config";//$NON-NLS-1$
	public String MIME_MAPPING = "mime-mapping";//$NON-NLS-1$
	public String MIME_TYPE = "mime-type";//$NON-NLS-1$
	public String REALM_NAME = "realm-name";//$NON-NLS-1$
	public String SECURITY_CONSTRAINT = "security-constraint";//$NON-NLS-1$
	public String SERVLET = "servlet";//$NON-NLS-1$
	public String SERVLET_CLASS = "servlet-class";//$NON-NLS-1$
	public String SERVLET_MAPPING = "servlet-mapping";//$NON-NLS-1$
	public String SERVLET_NAME = "servlet-name";//$NON-NLS-1$
	public String SESSION_CONFIG = "session-config";//$NON-NLS-1$
	public String SESSION_TIMEOUT = "session-timeout";//$NON-NLS-1$
	public String TAGLIB = "taglib";//$NON-NLS-1$
	public String TAGLIB_LOCATION = "taglib-location";//$NON-NLS-1$
	public String TAGLIB_URI = "taglib-uri";//$NON-NLS-1$
	public String TRANSPORT_GUARANTEE = "transport-guarantee";//$NON-NLS-1$
	public String URL_PATTERN = "url-pattern";//$NON-NLS-1$
	public String USER_DATA_CONSTRAINT = "user-data-constraint";//$NON-NLS-1$
	public String WEB_APP = "web-app";//$NON-NLS-1$
	public String WEB_RESOURCE_COLLECTION = "web-resource-collection";//$NON-NLS-1$
	public String WEB_RESOURCE_NAME = "web-resource-name";//$NON-NLS-1$
	public String WELCOME_FILE = "welcome-file";//$NON-NLS-1$
	public String WELCOME_FILE_LIST = "welcome-file-list";//$NON-NLS-1$
	
	public String DISPATCHER = "dispatcher";//$NON-NLS-1$
	public String LOCALE_ENCODING_MAPPING_LIST = "locale-encoding-mapping-list";//$NON-NLS-1$
	public String LOCALE_ENCODING_MAPPING = "locale-encoding-mapping";//$NON-NLS-1$
	public String LOCALE = "locale";//$NON-NLS-1$
	public String ENCODING = "encoding";//$NON-NLS-1$
	public String JSP_CONFIG = "jsp-config";//$NON-NLS-1$
	public String JSP_PROPERTY_GROUP = "jsp-property-group";//$NON-NLS-1$
	public String EL_IGNORED = "el-ignored";//$NON-NLS-1$
	public String PAGE_ENCODING = "page-encoding";//$NON-NLS-1$
	public String SCRIPTING_INVALID = "scripting-invalid";//$NON-NLS-1$
	public String IS_XML = "is-xml";//$NON-NLS-1$
	public String INCLUDE_PRELUDE = "include-prelude";//$NON-NLS-1$
	public String INCLUDE_CODA = "include-coda";//$NON-NLS-1$
	
}


