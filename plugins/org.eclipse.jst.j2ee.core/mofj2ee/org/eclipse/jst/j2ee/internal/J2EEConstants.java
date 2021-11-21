/*******************************************************************************
 * Copyright (c) 2001, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Stefan Dimov, stefan.dimov@sap.com - bug 207826
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import org.eclipse.emf.common.util.URI;



/**
 * This is a catalog of useful constants for the archive support.  Can be used to
 * store relative paths to specific xml and xmi resources.  
 */
public interface J2EEConstants extends J2EEVersionConstants {
    //Standard Jar info
            /** "META-INF/MANIFEST.MF"                                                                                              */
    String MANIFEST_URI                                     =       "META-INF/MANIFEST.MF"; //$NON-NLS-1$
    URI MANIFEST_URI_OBJ                            =       URI.createURI(MANIFEST_URI);
    String MANIFEST_SHORT_NAME                      =       "MANIFEST.MF"; //$NON-NLS-1$

            /** "META-INF"                                                                                                                  */
    String META_INF                                 =       "META-INF"; //$NON-NLS-1$
    /** "WEB-INF"                                                                                                                   */
    String WEB_INF                                  =       "WEB-INF"; //$NON-NLS-1$
    String WEB_INF_CLASSES                          =       "WEB-INF/classes"; //$NON-NLS-1$
    String WEB_INF_LIB                              =       "WEB-INF/lib"; //$NON-NLS-1$
    /** "ALT-INF"                                                                                                                   */
    String ALT_INF                                  =       "ALT-INF"; //$NON-NLS-1$
    //Application client info
    /** "Application-client_ID"                                                                                     */
    String APP_CLIENT_ID                            =   "Application-client_ID"; //$NON-NLS-1$
    /** "META-INF/application-client.xml"                                                                   */
    String APP_CLIENT_DD_URI                        =       "META-INF/application-client.xml"; //$NON-NLS-1$
    URI APP_CLIENT_DD_URI_OBJ               =       URI.createURI(APP_CLIENT_DD_URI);
    /** "application-client.xml"                                                                                    */
    String APP_CLIENT_DD_SHORT_NAME         =       "application-client.xml"; //$NON-NLS-1$
    /** Doc type for app client deployment descriptors */
    String APP_CLIENT_DOCTYPE                       =       "application-client"; //$NON-NLS-1$
    String APP_CLIENT_PUBLICID_1_2          =   "-//Sun Microsystems, Inc.//DTD J2EE Application Client 1.2//EN"; //$NON-NLS-1$
    String APP_CLIENT_PUBLICID_1_3          =   "-//Sun Microsystems, Inc.//DTD J2EE Application Client 1.3//EN"; //$NON-NLS-1$
    String APP_CLIENT_SYSTEMID_1_2          =       "http://java.sun.com/j2ee/dtds/application-client_1_2.dtd"; //$NON-NLS-1$
    String APP_CLIENT_ALT_SYSTEMID_1_2      =       "http://java.sun.com/j2ee/dtds/application-client_1.2.dtd"; //$NON-NLS-1$
    String APP_CLIENT_SYSTEMID_1_3          =       "http://java.sun.com/dtd/application-client_1_3.dtd"; //$NON-NLS-1$
    String APP_CLIENT_ALT_SYSTEMID_1_3      =       "http://java.sun.com/dtd/application-client_1.3.dtd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_1_4       =   "http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/application-client_1_4.xsd";//$NON-NLS-1$
    String APP_CLIENT_SCHEMA_5         =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application-client_5.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_6         =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application-client_6.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_7         =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/application-client_7.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_8         =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/application-client_8.xsd"; //$NON-NLS-1$
    
    //Application constants
    /** "Application_ID"                                                                                                    */
    String APPL_ID                                          =       "Application_ID"; //$NON-NLS-1$
    /** "META-INF/application.xml"                                                                                  */
    String APPLICATION_DD_URI                       =       "META-INF/application.xml"; //$NON-NLS-1$
    URI APPLICATION_DD_URI_OBJ                      =       URI.createURI(APPLICATION_DD_URI);
    /** "application.xml"                                                                                                   */
    String APPLICATION_DD_SHORT_NAME        =       "application.xml"; //$NON-NLS-1$
 // @deprecated @see org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants.EAR_EXT
    String EAR_FILE_EXT = ".ear"; //$NON-NLS-1$
    /** Doc type for application deployment descriptors */
    String APPLICATION_DOCTYPE                      =       "application"; //$NON-NLS-1$
    String APPLICATION_PUBLICID_1_2         =   "-//Sun Microsystems, Inc.//DTD J2EE Application 1.2//EN"; //$NON-NLS-1$
    String APPLICATION_PUBLICID_1_3         =   "-//Sun Microsystems, Inc.//DTD J2EE Application 1.3//EN"; //$NON-NLS-1$
    String APPLICATION_PUBLICID_1_4         =   "-//Sun Microsystems, Inc.//DTD J2EE Application 1.4//EN"; //$NON-NLS-1$
    String APPLICATION_SYSTEMID_1_2         =       "http://java.sun.com/j2ee/dtds/application_1_2.dtd"; //$NON-NLS-1$
    String APPLICATION_ALT_SYSTEMID_1_2     =       "http://java.sun.com/j2ee/dtds/application_1.2.dtd"; //$NON-NLS-1$
    String APPLICATION_SYSTEMID_1_3         =       "http://java.sun.com/dtd/application_1_3.dtd"; //$NON-NLS-1$
    String APPLICATION_ALT_SYSTEMID_1_3     =       "http://java.sun.com/dtd/application_1.3.dtd"; //$NON-NLS-1$
    String APPLICATION_SYSTEMID_1_4         =       "http://java.sun.com/dtd/application_1_4.dtd"; //$NON-NLS-1$
    String APPLICATION_ALT_SYSTEMID_1_4 =  "http://java.sun.com/dtd/application_1_4.dtd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_1_4       =  "http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/application_1_4.xsd";//$NON-NLS-1$
    String APPLICATION_SCHEMA_5         =  "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application_5.xsd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_6         =  "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application_6.xsd"; //$NON-NLS-1$ 
    String APPLICATION_SCHEMA_7         =  "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/application_7.xsd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_8         =  "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/application_8.xsd"; //$NON-NLS-1$
    
    //EJB Jar Constants
    /** "ejb-jar_ID"                                                                                                                */
    String EJBJAR_ID                                        =       "ejb-jar_ID"; //$NON-NLS-1$
    /** "AssemblyDescriptor_ID"                                                                                             */
    String ASSEMBLYDESCRIPTOR_ID        =   "AssemblyDescriptor_ID"; //$NON-NLS-1$
    /** "ejb-jar.xml"                                                                                                               */
    String EJBJAR_DD_SHORT_NAME         =   "ejb-jar.xml"; //$NON-NLS-1$
    /** "META-INF/ejb-jar.xml"                                                                                              */
    String EJBJAR_DD_URI                =   "META-INF/ejb-jar.xml"; //$NON-NLS-1$
    URI EJBJAR_DD_URI_OBJ               =   URI.createURI(EJBJAR_DD_URI);
    /** "META-INF/ibm-ejb-jar-bnd.xmi"                                                                              */
    String EJBJAR_DOCTYPE               =       "ejb-jar"; //$NON-NLS-1$
    String EJBJAR_PUBLICID_1_1      =   "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 1.1//EN"; //$NON-NLS-1$
    String EJBJAR_PUBLICID_2_0      =   "-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN"; //$NON-NLS-1$
    String EJBJAR_SYSTEMID_1_1      =   "http://java.sun.com/j2ee/dtds/ejb-jar_1_1.dtd"; //$NON-NLS-1$
    String EJBJAR_ALT_SYSTEMID_1_1  =   "http://java.sun.com/j2ee/dtds/ejb-jar_1.1.dtd"; //$NON-NLS-1$
    String EJBJAR_SYSTEMID_2_0      =   "http://java.sun.com/dtd/ejb-jar_2_0.dtd"; //$NON-NLS-1$
    String EJBJAR_ALT_SYSTEMID_2_0  =   "http://java.sun.com/dtd/ejb-jar_2.0.dtd"; //$NON-NLS-1$
    String EJBJAR_SCHEMA_2_1        =   "http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/ejb-jar_2_1.xsd";//$NON-NLS-1$
    String EJBJAR_SCHEMA_2_1_NS     =   "http://java.sun.com/xml/ns/j2ee/ejb-jar_2_1.xsd";//$NON-NLS-1$
    String EJBJAR_SCHEMA_3_0        =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd"; //$NON-NLS-1$ 
    String EJBJAR_SCHEMA_3_0_NS     =   "http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd"; //$NON-NLS-1$
    String EJBJAR_SCHEMA_3_1        =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd"; //$NON-NLS-1$ 
    String EJBJAR_SCHEMA_3_1_NS     =   "http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd"; //$NON-NLS-1$ 
    String EJBJAR_SCHEMA_3_2        =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/ejb-jar_3_2.xsd"; //$NON-NLS-1$ 
    String EJBJAR_SCHEMA_3_2_NS     =   "http://xmlns.jcp.org/xml/ns/javaee/ejb-jar_3_2.xsd"; //$NON-NLS-1$ 
    
    // EAR constants
    
    String EAR_ROOT_DIR				=	"/"; //$NON-NLS-1$
    String EAR_DEFAULT_LIB_DIR		=	"/lib"; //$NON-NLS-1$
    
    // Web Fragments Constants 
    String 	WEBFRAGMENT_DOCTYPE         =       "web-fragment"; //$NON-NLS-1$
    String  WEBFRAGMENT_ID				= 		"WebFragment_ID"; //$NON-NLS-1$
    String 	WEBFRAGMENT_DD_URI			=		"META-INF/web-fragment.xml"; //$NON-NLS-1$
    URI 	WEBFRAGMENT_DD_URI_OBJ      =       URI.createURI(WEBFRAGMENT_DD_URI);
    String 	WEBFRAGMENT_DD_SHORT_NAME   =       "web-fragment.xml"; //$NON-NLS-1$
    String  WEBFRAGMENT_SCHEMA_3_0 		= 		"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd"; //$NON-NLS-1$
    String  WEBFRAGMENT_SCHEMA_3_1 		= 		"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-fragment_3_1.xsd"; //$NON-NLS-1$
    String  WEBFRAGMENT_SCHEMA_4_0 		= 		"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-fragment_4_0.xsd"; //$NON-NLS-1$
    
    //Web app Constants
    /** "WebApp_ID"                                                                                                                 */
    String WEBAPP_ID                                        =       "WebApp_ID"; //$NON-NLS-1$
    /** "WEB-INF/web.xml"                                                                                                   */
    String WEBAPP_DD_URI                            =       "WEB-INF/web.xml"; //$NON-NLS-1$
    URI WEBAPP_DD_URI_OBJ                           =       URI.createURI(WEBAPP_DD_URI);
    /** "web.xml"                                                                                                                   */
    String WEBAPP_DD_SHORT_NAME                     =       "web.xml"; //$NON-NLS-1$
    /** "WEB-INF/ibm-web-bnd.xmi"                                                                                   */
    /** Doc type for web app deployment descriptors */
    String WEBAPP_DOCTYPE                           =       "web-app"; //$NON-NLS-1$
    String CONTEXTROOT                                      = "context-root"; //$NON-NLS-1$
    String WEBAPP_PUBLICID_2_2              =   "-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN"; //$NON-NLS-1$
    String WEBAPP_PUBLICID_2_3              =   "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"; //$NON-NLS-1$
    String WEBAPP_SYSTEMID_2_2              =       "http://java.sun.com/j2ee/dtds/web-app_2_2.dtd"; //$NON-NLS-1$
    String WEBAPP_ALT_SYSTEMID_2_2  =       "http://java.sun.com/j2ee/dtds/web-app_2.2.dtd"; //$NON-NLS-1$
    String WEBAPP_SYSTEMID_2_3              =       "http://java.sun.com/dtd/web-app_2_3.dtd"; //$NON-NLS-1$
    String WEBAPP_ALT_SYSTEMID_2_3  =       "http://java.sun.com/dtd/web-app_2.3.dtd"; //$NON-NLS-1$
    String WEBAPP_SCHEMA_2_4        =   "http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd";//$NON-NLS-1$
    String WEBAPP_SCHEMA_2_5        =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd";//$NON-NLS-1$
    String WEBAPP_SCHEMA_3_0        =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd";//$NON-NLS-1$
    String WEBAPP_SCHEMA_3_1        =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd";//$NON-NLS-1$
    String WEBAPP_SCHEMA_4_0        =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd";//$NON-NLS-1$
    String WEBAPP_SCHEMA_5_0        =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_5_0.xsd";//$NON-NLS-1$
    /** "WEB-INF/ejb-jar.xml" */
    String EJBJAR_IN_WAR_DD_URI                =   "WEB-INF/ejb-jar.xml"; //$NON-NLS-1$
    //J2C Resource Adapter Constants
    /** "J2CResourceAdapter_ID"                                                                                             */
    String RAR_ID                                           =       "J2CResourceAdapter_ID"; //$NON-NLS-1$
    /** "META-INF/ra.xml"                                                                                                   */
    String RAR_DD_URI                                       =       "META-INF/ra.xml"; //$NON-NLS-1$
    URI RAR_DD_URI_OBJ                                      =       URI.createURI(RAR_DD_URI);
    String RAR_SHORT_NAME                           =       "ra.xml"; //$NON-NLS-1$
    //Need connector constant.
    String CONNECTOR_ID                                     =       "Connector_ID"; //$NON-NLS-1$
    /** Doc type for connector deployment descriptors */
    String CONNECTOR_DOCTYPE                        =       "connector"; //$NON-NLS-1$
    String CONNECTOR_PUBLICID_1_0           =   "-//Sun Microsystems, Inc.//DTD Connector 1.0//EN"; //$NON-NLS-1$
    String CONNECTOR_SYSTEMID_1_0           =       "http://java.sun.com/dtd/connector_1_0.dtd"; //$NON-NLS-1$
    String CONNECTOR_ALT_SYSTEMID_1_0       =       "http://java.sun.com/dtd/connector_1.0.dtd"; //$NON-NLS-1$
    String CONNECTOR_SCHEMA_1_5         =   "http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/connector_1_5.xsd";//$NON-NLS-1$
    String CONNECTOR_SCHEMA_1_6         =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/connector_1_6.xsd";//$NON-NLS-1$
    String CONNECTOR_SCHEMA_1_7         =   "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/connector_1_7.xsd";//$NON-NLS-1$
    //Web Services Constants
    /** Doc type for webservices deployment descriptors */
    String WEB_SERVICES_CLIENT_DOCTYPE      = "webservicesclient";  //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_PUBLICID_1_0 = "-//IBM Corporation, Inc.//DTD J2EE Web services client 1.0//EN"; //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_SYSTEMID_1_0 = "http://www.ibm.com/webservices/dtd/j2ee_web_services_client_1_0.dtd"; //$NON-NLS-1$  
    String WEB_SERVICES_CLIENT_LOC_1_1 = "http://www.ibm.com/webservices/xsd/j2ee_web_services_client_1_1.xsd"; //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_SHORTNAME    = "webservicesclient.xml"; //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_DD_URI                       =       "webservicesclient.xml"; //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_META_INF_DD_URI      =   "META-INF/webservicesclient.xml"; //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_WEB_INF_DD_URI       =   "WEB-INF/webservicesclient.xml"; //$NON-NLS-1$
    URI WEB_SERVICES_CLIENT_DD_URI_OBJ                      =       URI.createURI(WEB_SERVICES_CLIENT_DD_URI);
    URI WEB_SERVICES_CLIENT_META_INF_DD_URI_OBJ     =       URI.createURI(WEB_SERVICES_CLIENT_META_INF_DD_URI);
    URI WEB_SERVICES_CLIENT_WEB_INF_DD_URI_OBJ      =       URI.createURI(WEB_SERVICES_CLIENT_WEB_INF_DD_URI);
    
    /** Doc type for webservices deployment descriptors */
    String WEB_SERVICES_DOCTYPE      		= "webservices";  //$NON-NLS-1$
    String WEB_SERVICES_DD_URI                      =       "webservices.xml"; //$NON-NLS-1$
    String WEB_SERVICES_META_INF_DD_URI     =   "META-INF/webservices.xml"; //$NON-NLS-1$
    String WEB_SERVICES_WEB_INF_DD_URI      =   "WEB-INF/webservices.xml"; //$NON-NLS-1$
    URI WEB_SERVICES_DD_URI_OBJ                     =       URI.createURI(WEB_SERVICES_DD_URI);
    URI WEB_SERVICES_META_INF_DD_URI_OBJ    = URI.createURI(WEB_SERVICES_META_INF_DD_URI);
    URI WEB_SERVICES_WEB_INF_DD_URI_OBJ     =       URI.createURI(WEB_SERVICES_WEB_INF_DD_URI);
    String WEBSERVICE_SYSTEMID    = "http://www.ibm.com/webservices/dtd/j2ee_web_services_1_0.dtd"; //$NON-NLS-1$
    String WEBSERVICE_PUBLICID = "-//IBM Corporation, Inc.//DTD J2EE Web services 1.0//EN"; //$NON-NLS-1$  
    String WEBSERVICE_SCHEMA_1_1 = "http://java.sun.com/xml/ns/j2ee http://www.ibm.com/webservices/xsd/j2ee_web_services_1_1.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_LOC_SUN_1_1 = "http://java.sun.com/xml/ns/j2ee/j2ee_web_services_1_1.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_1_2 = "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/javaee_web_services_1_2.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_LOC_SUN_1_2 = "http://java.sun.com/xml/ns/javaee/javaee_web_services_1_2.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_1_3 = "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/javaee_web_services_1_3.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_LOC_SUN_1_3 = "http://java.sun.com/xml/ns/javaee/javaee_web_services_1_3.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_1_4 = "http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/javaee_web_services_1_4.xsd";//$NON-NLS-1$
    String WEBSERVICE_SCHEMA_LOC_1_4 = "http://xmlns.jcp.org/xml/ns/javaee/javaee_web_services_1_4.xsd";//$NON-NLS-1$
        
    //Miscellaneous constants
    /** "UTF-8"                                                                                                                             */
    String DEFAULT_XML_ENCODING             =       "UTF-8"; //$NON-NLS-1$
    /** "1.0"                                                                                                                               */
    String DEFAULT_XML_VERSION                      =       "1.0"; //$NON-NLS-1$
    String JAVA_SUN_COM_URL                         =   "http://java.sun.com"; //$NON-NLS-1$
    String WWW_W3_ORG_URL                           =   "http://www.w3.org"; //$NON-NLS-1$
    String WWW_IBM_COM_URL                          =   "http://www.ibm.com"; //$NON-NLS-1$
    
    String J2EE_NS_URL                      = "http://java.sun.com/xml/ns/j2ee"; //$NON-NLS-1$
    String JAVAEE_NS_URL                    = "http://java.sun.com/xml/ns/javaee"; //$NON-NLS-1$ //EJB3
    String JAVAEE7_NS_URL                   = "http://xmlns.jcp.org/xml/ns/javaee"; //$NON-NLS-1$
    String J2EE_1_4_XSD_SHORT_NAME          = "j2ee_1_4.xsd"; //$NON-NLS-1$
    String JAVAEE_5_XSD_SHORT_NAME          = "javaee_5.xsd"; //$NON-NLS-1$  //EJB3
    String XSI_NS_URL                       = "http://www.w3.org/2001/XMLSchema-instance"; //$NON-NLS-1$ 
    String APPLICATION_SCHEMA_LOC_1_4       = "http://java.sun.com/xml/ns/j2ee/application_1_4.xsd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_LOC_5         = "http://java.sun.com/xml/ns/javaee/application_5.xsd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_LOC_6         = "http://java.sun.com/xml/ns/javaee/application_6.xsd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_LOC_7         = "http://xmlns.jcp.org/xml/ns/javaee/application_7.xsd"; //$NON-NLS-1$
    String APPLICATION_SCHEMA_LOC_8         = "http://xmlns.jcp.org/xml/ns/javaee/application_8.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_LOC_1_4        = "http://java.sun.com/xml/ns/j2ee/application-client_1_4.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_LOC_5          = "http://java.sun.com/xml/ns/javaee/application-client_5.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_LOC_6          = "http://java.sun.com/xml/ns/javaee/application-client_6.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_LOC_7          = "http://xmlns.jcp.org/xml/ns/javaee/application-client_7.xsd"; //$NON-NLS-1$
    String APP_CLIENT_SCHEMA_LOC_8          = "http://xmlns.jcp.org/xml/ns/javaee/application-client_8.xsd"; //$NON-NLS-1$
    String EJB_JAR_SCHEMA_LOC_2_1           = "http://java.sun.com/xml/ns/j2ee/ejb-jar_2_1.xsd"; //$NON-NLS-1$
    String EJB_JAR_SCHEMA_LOC_3_0    		= "http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd"; //$NON-NLS-1$
    String EJB_JAR_SCHEMA_LOC_3_1    		= "http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd"; //$NON-NLS-1$
    String EJB_JAR_SCHEMA_LOC_3_2    		= "http://xmlns.jcp.org/xml/ns/javaee/ejb-jar_3_2.xsd"; //$NON-NLS-1$
    String CONNECTOR_SCHEMA_LOC_1_5         = "http://java.sun.com/xml/ns/j2ee/connector_1_5.xsd"; //$NON-NLS-1$
    String CONNECTOR_SCHEMA_LOC_1_6         = "http://java.sun.com/xml/ns/javaee/connector_1_6.xsd"; //$NON-NLS-1$
    String CONNECTOR_SCHEMA_LOC_1_7         = "http://xmlns.jcp.org/xml/ns/javaee/connector_1_7.xsd"; //$NON-NLS-1$
    String WEB_APP_SCHEMA_LOC_2_4           = "http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"; //$NON-NLS-1$
    String WEB_APP_SCHEMA_LOC_2_5           = "http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"; //$NON-NLS-1$
    String WEB_APP_SCHEMA_LOC_3_0           = "http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"; //$NON-NLS-1$
    String WEB_APP_SCHEMA_LOC_3_1           = "http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"; //$NON-NLS-1$
    String WEB_APP_SCHEMA_LOC_4_0           = "http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"; //$NON-NLS-1$
    String WEB_APP_SCHEMA_LOC_5_0           = "https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"; //$NON-NLS-1$
    String WEB_SERVICES_CLIENT_SCHEMA_LOC_1_1 = "http://www.ibm.com/webservices/xsd/j2ee_web_services_client_1_1.xsd"; //$NON-NLS-1$
    String JSP_SCHEMA_LOC_2_0               =  "http://java.sun.com/xml/ns/j2ee/jsp_2_0.xsd"; //$NON-NLS-1$
    String JSP_SCHEMA_LOC_2_1               =  "http://java.sun.com/xml/ns/javaee/jsp_2_1.xsd"; //$NON-NLS-1$ //EJB3
    String JSP_SCHEMA_LOC_3_0               =  "https://jakarta.ee/xml/ns/jakartaee/jsp_3_0.xsd"; //$NON-NLS-1$ //EJB3
    String WEBFRAGMENT_SCHEMA_LOC_3_0       = "http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd"; //$NON-NLS-1$ //Web Fragment3
    String WEBFRAGMENT_SCHEMA_LOC_3_1       = "http://xmlns.jcp.org/xml/ns/javaee/web-fragment_3_1.xsd"; //$NON-NLS-1$ //Web Fragment3
    String WEBFRAGMENT_SCHEMA_LOC_4_0       = "http://xmlns.jcp.org/xml/ns/javaee/web-fragment_4_0.xsd"; //$NON-NLS-1$ //Web Fragment4
    
    public static final String EAR_MODEL_NAME = "EAR_VALIDATION"; //$NON-NLS-1$
}



