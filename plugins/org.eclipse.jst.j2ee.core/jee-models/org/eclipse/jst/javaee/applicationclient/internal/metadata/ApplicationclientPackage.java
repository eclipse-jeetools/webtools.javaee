/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.applicationclient.internal.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.javaee.applicationclient.ApplicationclientFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *       $Id: application-client_6.xsd,v 1.4 2009/10/02 16:04:08 ccc Exp $
 *       
 *     
 * 
 *       [
 *       This is the XML Schema for the application client 7
 *       deployment descriptor.  The deployment descriptor must
 *       be named "META-INF/application-client.xml" in the
 *       application client's jar file.  All application client
 *       deployment descriptors must indicate the application
 *       client schema by using the Java EE namespace:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee
 *       
 *       and indicate the version of the schema by
 *       using the version element as shown below:
 *       
 *       <application-client xmlns="http://xmlns.jcp.org/xml/ns/javaee"
 *       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
 *       	http://xmlns.jcp.org/xml/ns/javaee/application-client_7.xsd"
 *       version="7">
 *       ...
 *       </application-client>
 *       
 *       The instance documents may indicate the published version of
 *       the schema using the xsi:schemaLocation attribute for Java EE
 *       namespace with the following location:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee/application-client_7.xsd
 *       
 *       
 *     
 * 
 * 
 *       The following conventions apply to all Java EE
 *       deployment descriptor elements unless indicated otherwise.
 *       
 *       - In elements that specify a pathname to a file within the
 *       same JAR file, relative filenames (i.e., those not
 *       starting with "/") are considered relative to the root of
 *       the JAR file's namespace.  Absolute filenames (i.e., those
 *       starting with "/") also specify names in the root of the
 *       JAR file's namespace.  In general, relative names are
 *       preferred.  The exception is .war files where absolute
 *       names are preferred for consistency with the Servlet API.
 *       
 *     
 * 
 * 
 *       $Id: javaee_6.xsd,v 1.4 2010/01/14 18:20:11 ccc Exp $
 *       
 *     
 * 
 * 
 *       The following definitions that appear in the common
 *       shareable schema(s) of Java EE deployment descriptors should be
 *       interpreted with respect to the context they are included:
 *       
 *       Deployment Component may indicate one of the following:
 *       java ee application;
 *       application client;
 *       web application;
 *       enterprise bean;
 *       resource adapter; 
 *       
 *       Deployment File may indicate one of the following:
 *       ear file;
 *       war file;
 *       jar file;
 *       rar file;
 *       
 *     
 * 
 * 
 *       $Id: javaee_web_services_client_1_3.xsd,v 1.2 2009/09/29 20:31:42 ccc Exp $
 *       
 *     
 * 
 * 
 *       (C) Copyright International Business Machines Corporation 2002
 *       
 *     
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml">
 *     <h1>About the XML namespace</h1>
 * 
 *     <div class="bodytext">
 *      <p>
 *       This schema document describes the XML namespace, in a form
 *       suitable for import by other schema documents.
 *      </p>
 *      <p>
 *       See <a href="http://www.w3.org/XML/1998/namespace.html">
 *       http://www.w3.org/XML/1998/namespace.html</a> and
 *       <a href="http://www.w3.org/TR/REC-xml">
 *       http://www.w3.org/TR/REC-xml</a> for information 
 *       about this namespace.
 *      </p>
 *      <p>
 *       Note that local names in this namespace are intended to be
 *       defined only by the World Wide Web Consortium or its subgroups.
 *       The names currently defined in this namespace are listed below.
 *       They should not be used with conflicting semantics by any Working
 *       Group, specification, or document instance.
 *      </p>
 *      <p>   
 *       See further below in this document for more information about <a href="#usage">how to refer to this schema document from your own
 *       XSD schema documents</a> and about <a href="#nsversioning">the
 *       namespace-versioning policy governing this schema document</a>.
 *      </p>
 *     </div>
 *    </div>
 *   
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml">
 *    
 *     <h3>Father (in any context at all)</h3> 
 * 
 *     <div class="bodytext">
 *      <p>
 *       denotes Jon Bosak, the chair of 
 *       the original XML Working Group.  This name is reserved by 
 *       the following decision of the W3C XML Plenary and 
 *       XML Coordination groups:
 *      </p>
 *      <blockquote>
 *        <p>
 * 	In appreciation for his vision, leadership and
 * 	dedication the W3C XML Plenary on this 10th day of
 * 	February, 2000, reserves for Jon Bosak in perpetuity
 * 	the XML name "xml:Father".
 *        </p>
 *      </blockquote>
 *     </div>
 *    </div>
 *   
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml" id="usage" xml:id="usage">
 *     <h2>
 *       <a name="usage">About this schema document</a>
 *     </h2>
 * 
 *     <div class="bodytext">
 *      <p>
 *       This schema defines attributes and an attribute group suitable
 *       for use by schemas wishing to allow <code>xml:base</code>,
 *       <code>xml:lang</code>, <code>xml:space</code> or
 *       <code>xml:id</code> attributes on elements they define.
 *      </p>
 *      <p>
 *       To enable this, such a schema must import this schema for
 *       the XML namespace, e.g. as follows:
 *      </p>
 *      <pre>
 *           &lt;schema . . .&gt;
 *            . . .
 *            &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                       schemaLocation="http://www.w3.org/2001/xml.xsd"/&gt;
 *      </pre>
 *      <p>
 *       or
 *      </p>
 *      <pre>
 *            &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                       schemaLocation="http://www.w3.org/2009/01/xml.xsd"/&gt;
 *      </pre>
 *      <p>
 *       Subsequently, qualified reference to any of the attributes or the
 *       group defined below will have the desired effect, e.g.
 *      </p>
 *      <pre>
 *           &lt;type . . .&gt;
 *            . . .
 *            &lt;attributeGroup ref="xml:specialAttrs"/&gt;
 *      </pre>
 *      <p>
 *       will define a type which will schema-validate an instance element
 *       with any of those attributes.
 *      </p>
 *     </div>
 *    </div>
 *   
 * 
 *    <div xmlns="http://www.w3.org/1999/xhtml" id="nsversioning" xml:id="nsversioning">
 *     <h2>
 *       <a name="nsversioning">Versioning policy for this schema document</a>
 *     </h2>
 *     <div class="bodytext">
 *      <p>
 *       In keeping with the XML Schema WG's standard versioning
 *       policy, this schema document will persist at
 *       <a href="http://www.w3.org/2009/01/xml.xsd">
 *        http://www.w3.org/2009/01/xml.xsd</a>.
 *      </p>
 *      <p>
 *       At the date of issue it can also be found at
 *       <a href="http://www.w3.org/2001/xml.xsd">
 *        http://www.w3.org/2001/xml.xsd</a>.
 *      </p>
 *      <p>
 *       The schema document at that URI may however change in the future,
 *       in order to remain compatible with the latest version of XML
 *       Schema itself, or with the XML namespace itself.  In other words,
 *       if the XML Schema or XML namespaces change, the version of this
 *       document at <a href="http://www.w3.org/2001/xml.xsd">
 *        http://www.w3.org/2001/xml.xsd 
 *       </a> 
 *       will change accordingly; the version at 
 *       <a href="http://www.w3.org/2009/01/xml.xsd">
 *        http://www.w3.org/2009/01/xml.xsd 
 *       </a> 
 *       will not change.
 *      </p>
 *      <p>
 *       Previous dated (and unchanging) versions of this schema 
 *       document are at:
 *      </p>
 *      <ul>
 *       <li>
 *           <a href="http://www.w3.org/2009/01/xml.xsd">
 * 	http://www.w3.org/2009/01/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2007/08/xml.xsd">
 * 	http://www.w3.org/2007/08/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2004/10/xml.xsd">
 * 	http://www.w3.org/2004/10/xml.xsd</a>
 *         </li>
 *       <li>
 *           <a href="http://www.w3.org/2001/03/xml.xsd">
 * 	http://www.w3.org/2001/03/xml.xsd</a>
 *         </li>
 *      </ul>
 *     </div>
 *    </div>
 *   
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.applicationclient.ApplicationclientFactory
 * @generated
 */
public interface ApplicationclientPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "applicationclient"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * The package must be registered with the Java EE 5 and Java EE 6 xsds, since it is used for both
	 * <!-- end-user-doc -->
	 */
	String eNS_URI = "http://xmlns.jcp.org/xml/ns/javaee/application-client_7.xsd"; //$NON-NLS-1$
	String eNS_URI2 = "http://java.sun.com/xml/ns/javaee/application-client_6.xsd"; //$NON-NLS-1$
	String eNS_URI3 = "http://java.sun.com/xml/ns/javaee/application-client_5.xsd"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "applicationclient"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationclientPackage eINSTANCE = org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl <em>Application Client</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl
	 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl#getApplicationClient()
	 * @generated
	 */
	int APPLICATION_CLIENT = 0;

	/**
	 * The feature id for the '<em><b>Module Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MODULE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DESCRIPTIONS = 1;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DISPLAY_NAMES = 2;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ICONS = 3;

	/**
	 * The feature id for the '<em><b>Env Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ENV_ENTRIES = 4;

	/**
	 * The feature id for the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__EJB_REFS = 5;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__SERVICE_REFS = 6;

	/**
	 * The feature id for the '<em><b>Resource Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__RESOURCE_REFS = 7;

	/**
	 * The feature id for the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__RESOURCE_ENV_REFS = 8;

	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS = 9;

	/**
	 * The feature id for the '<em><b>Persistence Unit Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS = 10;

	/**
	 * The feature id for the '<em><b>Post Constructs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__POST_CONSTRUCTS = 11;

	/**
	 * The feature id for the '<em><b>Pre Destroys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__PRE_DESTROYS = 12;

	/**
	 * The feature id for the '<em><b>Callback Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__CALLBACK_HANDLER = 13;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MESSAGE_DESTINATIONS = 14;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DATA_SOURCE = 15;

	/**
	 * The feature id for the '<em><b>Jms Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__JMS_CONNECTION_FACTORY = 16;

	/**
	 * The feature id for the '<em><b>Jms Destination</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__JMS_DESTINATION = 17;

	/**
	 * The feature id for the '<em><b>Mail Session</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MAIL_SESSION = 18;

	/**
	 * The feature id for the '<em><b>Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__CONNECTION_FACTORY = 19;

	/**
	 * The feature id for the '<em><b>Administered Object</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ADMINISTERED_OBJECT = 20;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ID = 21;

	/**
	 * The feature id for the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__METADATA_COMPLETE = 22;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__VERSION = 23;

	/**
	 * The number of structural features of the '<em>Application Client</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_FEATURE_COUNT = 24;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientDeploymentDescriptorImpl <em>Application Client Deployment Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientDeploymentDescriptorImpl
	 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl#getApplicationClientDeploymentDescriptor()
	 * @generated
	 */
	int APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Application Client</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__APPLICATION_CLIENT = 3;

	/**
	 * The number of structural features of the '<em>Application Client Deployment Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient <em>Application Client</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Client</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient
	 * @generated
	 */
	EClass getApplicationClient();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getModuleName <em>Module Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Name</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getModuleName()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EAttribute getApplicationClient_ModuleName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDescriptions()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDisplayNames()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getIcons()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_Icons();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getEnvEntries <em>Env Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Env Entries</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getEnvEntries()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_EnvEntries();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getEjbRefs <em>Ejb Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Refs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getEjbRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_EjbRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getServiceRefs <em>Service Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Refs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getServiceRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_ServiceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getResourceRefs <em>Resource Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Refs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getResourceRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_ResourceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getResourceEnvRefs <em>Resource Env Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Env Refs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getResourceEnvRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_ResourceEnvRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMessageDestinationRefs <em>Message Destination Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination Refs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMessageDestinationRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_MessageDestinationRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Unit Refs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPersistenceUnitRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_PersistenceUnitRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPostConstructs <em>Post Constructs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Post Constructs</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPostConstructs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_PostConstructs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPreDestroys <em>Pre Destroys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pre Destroys</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getPreDestroys()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_PreDestroys();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getCallbackHandler <em>Callback Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Callback Handler</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getCallbackHandler()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EAttribute getApplicationClient_CallbackHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMessageDestinations <em>Message Destinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destinations</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMessageDestinations()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_MessageDestinations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Source</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getDataSource()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_DataSource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getJmsConnectionFactory <em>Jms Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getJmsConnectionFactory()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_JmsConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getJmsDestination <em>Jms Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Destination</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getJmsDestination()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_JmsDestination();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMailSession <em>Mail Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Session</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getMailSession()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_MailSession();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getConnectionFactory <em>Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getConnectionFactory()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_ConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getAdministeredObject <em>Administered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Administered Object</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getAdministeredObject()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_AdministeredObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getId()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EAttribute getApplicationClient_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#isMetadataComplete <em>Metadata Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metadata Complete</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#isMetadataComplete()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EAttribute getApplicationClient_MetadataComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClient#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClient#getVersion()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EAttribute getApplicationClient_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor <em>Application Client Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Client Deployment Descriptor</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor
	 * @generated
	 */
	EClass getApplicationClientDeploymentDescriptor();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getMixed()
	 * @see #getApplicationClientDeploymentDescriptor()
	 * @generated
	 */
	EAttribute getApplicationClientDeploymentDescriptor_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getXMLNSPrefixMap()
	 * @see #getApplicationClientDeploymentDescriptor()
	 * @generated
	 */
	EReference getApplicationClientDeploymentDescriptor_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getXSISchemaLocation()
	 * @see #getApplicationClientDeploymentDescriptor()
	 * @generated
	 */
	EReference getApplicationClientDeploymentDescriptor_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getApplicationClient <em>Application Client</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Application Client</em>'.
	 * @see org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getApplicationClient()
	 * @see #getApplicationClientDeploymentDescriptor()
	 * @generated
	 */
	EReference getApplicationClientDeploymentDescriptor_ApplicationClient();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ApplicationclientFactory getApplicationclientFactory();

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
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl <em>Application Client</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientImpl
		 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl#getApplicationClient()
		 * @generated
		 */
		EClass APPLICATION_CLIENT = eINSTANCE.getApplicationClient();

		/**
		 * The meta object literal for the '<em><b>Module Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_CLIENT__MODULE_NAME = eINSTANCE.getApplicationClient_ModuleName();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__DESCRIPTIONS = eINSTANCE.getApplicationClient_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__DISPLAY_NAMES = eINSTANCE.getApplicationClient_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__ICONS = eINSTANCE.getApplicationClient_Icons();

		/**
		 * The meta object literal for the '<em><b>Env Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__ENV_ENTRIES = eINSTANCE.getApplicationClient_EnvEntries();

		/**
		 * The meta object literal for the '<em><b>Ejb Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__EJB_REFS = eINSTANCE.getApplicationClient_EjbRefs();

		/**
		 * The meta object literal for the '<em><b>Service Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__SERVICE_REFS = eINSTANCE.getApplicationClient_ServiceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__RESOURCE_REFS = eINSTANCE.getApplicationClient_ResourceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Env Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__RESOURCE_ENV_REFS = eINSTANCE.getApplicationClient_ResourceEnvRefs();

		/**
		 * The meta object literal for the '<em><b>Message Destination Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS = eINSTANCE.getApplicationClient_MessageDestinationRefs();

		/**
		 * The meta object literal for the '<em><b>Persistence Unit Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS = eINSTANCE.getApplicationClient_PersistenceUnitRefs();

		/**
		 * The meta object literal for the '<em><b>Post Constructs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__POST_CONSTRUCTS = eINSTANCE.getApplicationClient_PostConstructs();

		/**
		 * The meta object literal for the '<em><b>Pre Destroys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__PRE_DESTROYS = eINSTANCE.getApplicationClient_PreDestroys();

		/**
		 * The meta object literal for the '<em><b>Callback Handler</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_CLIENT__CALLBACK_HANDLER = eINSTANCE.getApplicationClient_CallbackHandler();

		/**
		 * The meta object literal for the '<em><b>Message Destinations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__MESSAGE_DESTINATIONS = eINSTANCE.getApplicationClient_MessageDestinations();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__DATA_SOURCE = eINSTANCE.getApplicationClient_DataSource();

		/**
		 * The meta object literal for the '<em><b>Jms Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__JMS_CONNECTION_FACTORY = eINSTANCE.getApplicationClient_JmsConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Jms Destination</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__JMS_DESTINATION = eINSTANCE.getApplicationClient_JmsDestination();

		/**
		 * The meta object literal for the '<em><b>Mail Session</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__MAIL_SESSION = eINSTANCE.getApplicationClient_MailSession();

		/**
		 * The meta object literal for the '<em><b>Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__CONNECTION_FACTORY = eINSTANCE.getApplicationClient_ConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Administered Object</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT__ADMINISTERED_OBJECT = eINSTANCE.getApplicationClient_AdministeredObject();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_CLIENT__ID = eINSTANCE.getApplicationClient_Id();

		/**
		 * The meta object literal for the '<em><b>Metadata Complete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_CLIENT__METADATA_COMPLETE = eINSTANCE.getApplicationClient_MetadataComplete();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_CLIENT__VERSION = eINSTANCE.getApplicationClient_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientDeploymentDescriptorImpl <em>Application Client Deployment Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationClientDeploymentDescriptorImpl
		 * @see org.eclipse.jst.javaee.applicationclient.internal.impl.ApplicationclientPackageImpl#getApplicationClientDeploymentDescriptor()
		 * @generated
		 */
		EClass APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR = eINSTANCE.getApplicationClientDeploymentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__MIXED = eINSTANCE.getApplicationClientDeploymentDescriptor_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = eINSTANCE.getApplicationClientDeploymentDescriptor_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = eINSTANCE.getApplicationClientDeploymentDescriptor_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Application Client</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_CLIENT_DEPLOYMENT_DESCRIPTOR__APPLICATION_CLIENT = eINSTANCE.getApplicationClientDeploymentDescriptor_ApplicationClient();

	}

} //ApplicationclientPackage
