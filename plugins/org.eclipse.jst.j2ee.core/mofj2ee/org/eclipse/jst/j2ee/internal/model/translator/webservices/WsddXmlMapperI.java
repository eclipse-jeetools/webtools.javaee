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
package org.eclipse.jst.j2ee.internal.model.translator.webservices;

//imports for J2EE core classes
//import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;

/**
 * This interface represents a catalog of all the tag names which occur in 
 * an web service deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */
public interface WsddXmlMapperI extends WebServiceCommonXmlMapperI
{
  public String WEBSERVICES                     = "webservices";//$NON-NLS-1$
  public String WEBSERVICE_DESCRIPTION          = "webservice-description";//$NON-NLS-1$
  public String WEBSERVICE_DESCRIPTION_NAME     = "webservice-description-name";//$NON-NLS-1$
  public String PORT_COMPONENT                  = "port-component";//$NON-NLS-1$
  public String PORT_COMPONENT_NAME             = "port-component-name";//$NON-NLS-1$
  public String WSDL_PORT                       = "wsdl-port";//$NON-NLS-1$
  public String WSDL_SERVICE                    = "wsdl-service";//$NON-NLS-1$
  public String SERVICE_IMPL_BEAN               = "service-impl-bean";//$NON-NLS-1$
  public String SERVLET_LINK                    = "servlet-link";//$NON-NLS-1$
  public String ENABLE_MTOM						= "enable-mtom";//$NON-NLS-1$
  public String PROTOCOL_BINDING				= "protocol-binding";//$NON-NLS-1$
  public String HANDLER_CHAINS					= "handler-chains";//$NON-NLS-1$
  public String HANDLER_CHAIN					= "handler-chain";//$NON-NLS-1$
  public String PROTOCOL_BINDINGS				= "protocol-bindings";//$NON-NLS-1$
  public String SERVICE_NAME_PATTERN			= "service-name-pattern";//$NON-NLS-1$
  public String PORT_NAME_PATTERN				= "port-name-patern";//$NON-NLS-1$
  //EJB_LINK is defined in DeploymentDescriptorXmlMapperI
  //public String EJB_LINK                        = "ejb-link";

}

