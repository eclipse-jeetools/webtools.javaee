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
//import org.eclipse.jst.j2ee.xml.DeploymentDescriptorXmlMapperI;

/**
 * This interface represents a catalog of all the tag names which occur in 
 * an web service deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */
public interface JaxrpcmapXmlMapperI extends WebServiceCommonXmlMapperI
{
  public String JAVA_WSDL_MAPPING                       ="java-wsdl-mapping"; //$NON-NLS-1$
  public String CLASS_TYPE                              ="class-type";//$NON-NLS-1$
  public String JAVA_TYPE                                ="java-type";  //$NON-NLS-1$
  public String CONSTRUCTOR_PARAMETER_ORDER             ="constructor-parameter-order";//$NON-NLS-1$
  public String DATA_MEMBER                             ="data-member";//$NON-NLS-1$
  public String ELEMENT_NAME                            ="element-name";//$NON-NLS-1$
  public String EXCEPTION_MAPPING                       ="exception-mapping";//$NON-NLS-1$
  public String EXCEPTION_TYPE                          ="exception-type";//$NON-NLS-1$
  public String JAVA_METHOD_NAME                        ="java-method-name";//$NON-NLS-1$
  public String JAVA_PORT_NAME                          ="java-port-name";//$NON-NLS-1$
  public String JAVA_VARIABLE_NAME                      ="java-variable-name";//$NON-NLS-1$
  public String JAVA_XML_TYPE_MAPPING                   ="java-xml-type-mapping";//$NON-NLS-1$
  public String METHOD_PARAM_PARTS_MAPPING              ="method-param-parts-mapping";//$NON-NLS-1$
  public String METHOD_RETURN_VALUE                     ="method-return-value";//$NON-NLS-1$
  public String PACKAGE_MAPPING                         ="package-mapping";//$NON-NLS-1$
  public String PACKAGE_TYPE                            ="package-type";//$NON-NLS-1$
  public String PARAMETER_MODE                          ="parameter-mode";//$NON-NLS-1$
  public String PARAM_POSITION                          ="param-position";//$NON-NLS-1$
  public String PARAM_TYPE                              ="param-type";//$NON-NLS-1$
  public String PORT_MAPPING                            ="port-mapping";//$NON-NLS-1$
  public String QNAME_SCOPE                             ="qname-scope";//$NON-NLS-1$
  public String ANONYMOUS_TYPE_QNAME             = "anonymous-type-qname";//$NON-NLS-1$
  public String ROOT_TYPE_QNAME                         ="root-type-qname";//$NON-NLS-1$
  //public String SERVICE_ENDPOINT_INTERFACE              ="service-endpoint-interface";
  public String SERVICE_ENDPOINT_INTERFACE_MAPPING      ="service-endpoint-interface-mapping";//$NON-NLS-1$
  public String SERVICE_ENDPOINT_METHOD_MAPPING         ="service-endpoint-method-mapping";//$NON-NLS-1$
  //public String SERVICE_INTERFACE                       ="service-interface";
  public String SERVICE_INTERFACE_MAPPING               ="service-interface-mapping";//$NON-NLS-1$
  //public String SOAP_HEADER                             ="soap_header";
  public String VARIABLE_MAPPING                        ="variable-mapping";//$NON-NLS-1$
  public String WRAPPED_ELEMENT                         ="wrapped-element";//$NON-NLS-1$
  public String WSDL_BINDING                            ="wsdl-binding";//$NON-NLS-1$
  public String WSDL_MESSAGE                            ="wsdl-message";//$NON-NLS-1$
  public String WSDL_MESSAGE_MAPPING                    ="wsdl-message-mapping";//$NON-NLS-1$
  public String WSDL_MESSAGE_PART_NAME                  ="wsdl-message-part-name";//$NON-NLS-1$
  public String WSDL_OPERATION                          ="wsdl-operation";//$NON-NLS-1$
  public String WSDL_PORT_TYPE                          ="wsdl-port-type";//$NON-NLS-1$
  public String WSDL_RETURN_VALUE_MAPPING               ="wsdl-return-value-mapping";//$NON-NLS-1$
  public String WSDL_SERVICE_NAME                       ="wsdl-service-name";//$NON-NLS-1$
  public String XML_ATTRIBUTE_NAME                        ="xml-attribute-name";//$NON-NLS-1$
  public String XML_ELEMENT_NAME                        ="xml-element-name";//$NON-NLS-1$
  public String XML_WILDCARD                                ="xml-wildcard";//$NON-NLS-1$
}

