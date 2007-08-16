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
 * an RAR deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */
public interface RarDeploymentDescriptorXmlMapperI extends DeploymentDescriptorXmlMapperI {
	public String AUTH_MECHANISM = "authentication-mechanism";//$NON-NLS-1$
	public String AUTH_MECH_TYPE = "authentication-mechanism-type";//$NON-NLS-1$
	public String CONFIG_PROPERTY = "config-property";//$NON-NLS-1$
	public String CONFIG_PROPERTY_NAME = "config-property-name";//$NON-NLS-1$
	public String CONFIG_PROPERTY_VALUE = "config-property-value";//$NON-NLS-1$
	public String CONFIG_PROPERTY_TYPE = "config-property-type";//$NON-NLS-1$
	public String CONNECTOR = "connector";//$NON-NLS-1$
	public String CONNECTIONFACTORY_INTERFACE = "connectionfactory-interface";//$NON-NLS-1$
	public String CONNECTIONFACTORY_IMPL_CLASS = "connectionfactory-impl-class";//$NON-NLS-1$
	public String CONNECTION_INTERFACE = "connection-interface";//$NON-NLS-1$
	public String CONNECTION_IMPL_CLASS = "connection-impl-class";//$NON-NLS-1$
	public String CREDENTIAL_INTERFACE = "credential-interface";//$NON-NLS-1$
	public String EIS_TYPE = "eis-type";//$NON-NLS-1$
	public String LICENSE = "license";//$NON-NLS-1$
	public String LICENSE_REQUIRED = "license-required";//$NON-NLS-1$
	public String MANAGEDCONNECTIONFACTORY_CLASS = "managedconnectionfactory-class";//$NON-NLS-1$
	public String REAUTHENTICATION_SUPPORT = "reauthentication-support";//$NON-NLS-1$
	public String RESOURCEADAPTER = "resourceadapter";//$NON-NLS-1$
	public String SECURITY_PERMISSION = "security-permission";//$NON-NLS-1$
	public String SECURITY_PERMISSION_SPEC = "security-permission-spec";//$NON-NLS-1$
	public String SPEC_VERSION = "spec-version";//$NON-NLS-1$
	public String TRANSACTION_SUPPORT = "transaction-support";//$NON-NLS-1$
	public String VENDOR_NAME = "vendor-name";//$NON-NLS-1$
	//JCA 1.5
	public String RESOURCEADAPTER_VERSION = "resourceadapter-version";//$NON-NLS-1$
	public String RESOURCEADAPTER_CLASS = "resourceadapter-class";//$NON-NLS-1$
	public String OUTBOUND_RESOURCEADAPTER = "outbound-resourceadapter";//$NON-NLS-1$
	public String INBOUND_RESOURCEADAPTER = "inbound-resourceadapter";//$NON-NLS-1$
	
	public String CONNECTION_DEFINITION = "connection-definition";//$NON-NLS-1$
	public String MESSAGEADAPTER = "messageadapter";//$NON-NLS-1$
	public String MESSAGELISTENER = "messagelistener";//$NON-NLS-1$
	public String MESSAGELISTENER_TYPE = "messagelistener-type";//$NON-NLS-1$
	public String ACTIVATIONSPEC = "activationspec";//$NON-NLS-1$
	public String ACTIVATIONSPEC_CLASS = "activationspec-class";//$NON-NLS-1$
	public String REQUIRED_CONFIG_PROPERTY = "required-config-property";//$NON-NLS-1$
	
	public String ADMINOBJECT = "adminobject";//$NON-NLS-1$
	public String ADMINOBJECT_CLASS = "adminobject-class";//$NON-NLS-1$
	public String ADMINOBJECT_INTERFACE = "adminobject-interface"; //$NON-NLS-1$
}



