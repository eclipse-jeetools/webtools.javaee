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
 * This interface represents a catalog of all the tag names which may be shared in 
 * a deployment descriptor for EJBJars, ears, and wars. Interface shared by readers and 
 * writers to have access to common constants
 */

public interface DeploymentDescriptorXmlMapperI {
	String DESCRIPTION = "description";//$NON-NLS-1$
	String DISPLAY_NAME = "display-name";//$NON-NLS-1$
	String EJB_LINK = "ejb-link";//$NON-NLS-1$
	String ENV_ENTRY = "env-entry";//$NON-NLS-1$
	String ENV_ENTRY_NAME = "env-entry-name";//$NON-NLS-1$
	String ENV_ENTRY_TYPE = "env-entry-type";//$NON-NLS-1$
	String ENV_ENTRY_VALUE = "env-entry-value";//$NON-NLS-1$
	String EJB_LOCAL_REF = "ejb-local-ref";	//$NON-NLS-1$				// J2EE1.3
	String EJB_REF = "ejb-ref";//$NON-NLS-1$
	String EJB_REF_NAME = "ejb-ref-name";//$NON-NLS-1$
	String EJB_REF_TYPE = "ejb-ref-type";//$NON-NLS-1$
	String HOME = "home";//$NON-NLS-1$
	String ICON = "icon";//$NON-NLS-1$
	String ID = "id";//$NON-NLS-1$
	String LARGE_ICON = "large-icon";//$NON-NLS-1$
	String LARGE_ICON_PATH = "icon/large-icon";//$NON-NLS-1$
	String LOCAL = "local";//$NON-NLS-1$									// J2EE1.3
	String LOCAL_HOME = "local-home";//$NON-NLS-1$						// J2EE1.3
	String REMOTE = "remote";//$NON-NLS-1$
	String RES_AUTH = "res-auth";//$NON-NLS-1$
	String RES_REF_NAME = "res-ref-name";//$NON-NLS-1$
	String RES_SHARING_SCOPE = "res-sharing-scope";//$NON-NLS-1$ 		// J2EE1.3 
	String RES_TYPE = "res-type";//$NON-NLS-1$
	String RESOURCE_ENV_REF = "resource-env-ref"; //$NON-NLS-1$			// J2EE1.3
	String RESOURCE_ENV_REF_NAME = "resource-env-ref-name";//$NON-NLS-1$ // J2EE1.3
	String RESOURCE_ENV_REF_TYPE = "resource-env-ref-type";//$NON-NLS-1$ // J2EE1.3
	String RESOURCE_REF = "resource-ref";//$NON-NLS-1$
	String ROLE_LINK = "role-link";//$NON-NLS-1$
	String ROLE_NAME = "role-name";//$NON-NLS-1$
	String RUN_AS = "run-as";	//$NON-NLS-1$							// J2EE1.3
	String SECURITY_IDENTITY = "security-identity";	//$NON-NLS-1$		// J2EE1.3
	String SECURITY_ROLE = "security-role";//$NON-NLS-1$
	String SECURITY_ROLE_REF = "security-role-ref";//$NON-NLS-1$
	String SMALL_ICON = "small-icon";//$NON-NLS-1$
	String SMALL_ICON_PATH = "icon/small-icon";//$NON-NLS-1$
	String USE_CALLER_IDENTITY = "use-caller-identity";//$NON-NLS-1$		// J2EE1.3
	
	
	//	J2EE 1.4
	String LANG = "xml:lang";//$NON-NLS-1$
	String DEPLOY_EXT = "deployment-extension";		//$NON-NLS-1$		// J2EE1.4
	String EXT_ELEMENT = "extension-element";	//$NON-NLS-1$			// J2EE1.4
	String MESSAGE_DEST_REF = "message-destination-ref";//$NON-NLS-1$	// J2EE1.4
	String MESSAGE_DEST_REF_NAME = "message-destination-ref-name";//$NON-NLS-1$	// J2EE1.4
	String MESSAGE_DEST_TYPE = "message-destination-type";//$NON-NLS-1$			// J2EE1.4
	String MESSAGE_DEST_LINK = "message-destination-link";	//$NON-NLS-1$		// J2EE1.4
	String MESSAGE_DEST_USAGE = "message-destination-usage";//$NON-NLS-1$		// J2EE1.4
	String MESSAGE_DEST = "message-destination";	//$NON-NLS-1$			// J2EE1.4
	String MESSAGE_DEST_NAME = "message-destination-name";	//$NON-NLS-1$			// J2EE1.4
	String SERVICE_REF = "service-ref";//$NON-NLS-1$
	String SERVICE_REF_NAME = "service-ref-name";//$NON-NLS-1$
	String SERVICE_INTERFACE = "service-interface";//$NON-NLS-1$
	String WSDL_FILE = "wsdl-file";//$NON-NLS-1$
	String JAXRPC_MAPPING_FILE = "jaxrpc-mapping-file";//$NON-NLS-1$
	String SERVICE_QNAME = "service-qname";//$NON-NLS-1$
	String PORT_COMPONENT_REF = "port-component-ref";//$NON-NLS-1$
	String HANDLER = "handler";//$NON-NLS-1$
	String SERVICE_ENPOINT_INTERFACE = "service-endpoint-interface";//$NON-NLS-1$
	String PORT_COMPONENT_LINK = "port-component-link";//$NON-NLS-1$
	String HANDLER_NAME = "handler-name";//$NON-NLS-1$
	String HANDLER_CLASS = "handler-class";//$NON-NLS-1$
	String HANDLER_INIT_PARAM = "init-param";//$NON-NLS-1$
	String SOAP_HEADER = "soap-header";//$NON-NLS-1$
	String SOAP_ROLE = "soap-role";//$NON-NLS-1$
	String PORT_NAME = "port-name";//$NON-NLS-1$
	String VERSION = "version";//$NON-NLS-1$
	String XML_NS = "xmlns";//$NON-NLS-1$
	String XML_NS_XSI = "xmlns:xsi";//$NON-NLS-1$
	String XSI_SCHEMA_LOCATION = "xsi:schemaLocation";//$NON-NLS-1$
	String NAMESPACE = "namespace";//$NON-NLS-1$
	String MUSTUNDERSTAND = "mustUnderstand";//$NON-NLS-1$
	String PARAM_NAME = "param-name";//$NON-NLS-1$
	String PARAM_VALUE = "param-value";//$NON-NLS-1$
	String LISTENER_CLASS = "listener-class";//$NON-NLS-1$	// Servlet2.3
	
	
	
}


