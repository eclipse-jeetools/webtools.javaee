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
package org.eclipse.jst.j2ee.internal.model.translator.webservices;

//imports for J2EE core classes
import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;

/**
 * This interface represents a catalog of all the tag names which occur in 
 * an web service deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */
public interface WebServiceCommonXmlMapperI extends DeploymentDescriptorXmlMapperI
{
//  public String WSDL_FILE                       = "wsdl-file";
//  public String JAXRPC_MAPPING_FILE             = "jaxrpc-mapping-file";
  public String NAMESPACEURI                    = "namespaceURI";//$NON-NLS-1$
  public String LOCALPART                       = "localpart";//$NON-NLS-1$
//  public String SERVICE_INTERFACE               = "service-interface";
  public String SERVICE_ENDPOINT_INTERFACE      = "service-endpoint-interface";//$NON-NLS-1$
//  public String HANDLER                         = "handler";
//  public String HANDLER_NAME                    = "handler-name";
//  public String HANDLER_CLASS                   = "handler-class";
  public String INIT_PARAM                      = "init-param";//$NON-NLS-1$
//  public String PARAM_NAME                      = "param-name";
//  public String PARAM_VALUE                     = "param-value";
//  public String SOAP_HEADER                     = "soap-header";
//  public String SOAP_ROLE                       = "soap-role";
//  public String PORT_NAME                       = "port-name";

}

