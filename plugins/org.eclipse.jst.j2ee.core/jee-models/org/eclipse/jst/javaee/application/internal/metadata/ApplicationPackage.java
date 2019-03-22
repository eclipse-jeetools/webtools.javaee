/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.application.internal.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.javaee.application.ApplicationFactory;

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
 *       $Id: application_6.xsd,v 1.4 2010/01/14 18:20:11 ccc Exp $
 *       
 *     
 * 
 *       [
 *       This is the XML Schema for the application 8 deployment
 *       descriptor.  The deployment descriptor must be named
 *       "META-INF/application.xml" in the application's ear file.
 *       All application deployment descriptors must indicate
 *       the application schema by using the Java EE namespace:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee
 *       
 *       and indicate the version of the schema by
 *       using the version element as shown below:
 *       
 *       <application xmlns="http://xmlns.jcp.org/xml/ns/javaee"
 *       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
 *       	http://xmlns.jcp.org/xml/ns/javaee/application_8.xsd"
 *       version="8">
 *       ...
 *       </application>
 *       
 *       The instance documents may indicate the published version of
 *       the schema using the xsi:schemaLocation attribute for Java EE
 *       namespace with the following location:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee/application_8.xsd
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
 * @see org.eclipse.jst.javaee.application.ApplicationFactory
 * @generated
 */
public interface ApplicationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "application"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * The package must be registered with the Java EE 5 and Java EE 6 xsds, since it is used for both
	 * <!-- end-user-doc -->
	 */
	String eNS_URI = J2EEConstants.APPLICATION_SCHEMA_LOC_5;
	String eNS_URI2 = J2EEConstants.APPLICATION_SCHEMA_LOC_6;
	String eNS_URI3 = J2EEConstants.APPLICATION_SCHEMA_LOC_7;
	String eNS_URI4 = J2EEConstants.APPLICATION_SCHEMA_LOC_8;
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "application"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplicationPackage eINSTANCE = org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl
	 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 0;

	/**
	 * The feature id for the '<em><b>Application Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__APPLICATION_NAME = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DESCRIPTIONS = 1;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DISPLAY_NAMES = 2;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ICONS = 3;

	/**
	 * The feature id for the '<em><b>Initialize In Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__INITIALIZE_IN_ORDER = 4;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__MODULES = 5;

	/**
	 * The feature id for the '<em><b>Security Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__SECURITY_ROLES = 6;

	/**
	 * The feature id for the '<em><b>Library Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__LIBRARY_DIRECTORY = 7;

	/**
	 * The feature id for the '<em><b>Env Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ENV_ENTRY = 8;

	/**
	 * The feature id for the '<em><b>Ejb Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__EJB_REF = 9;

	/**
	 * The feature id for the '<em><b>Ejb Local Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__EJB_LOCAL_REF = 10;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__SERVICE_REFS = 11;

	/**
	 * The feature id for the '<em><b>Resource Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__RESOURCE_REF = 12;

	/**
	 * The feature id for the '<em><b>Resource Env Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__RESOURCE_ENV_REF = 13;

	/**
	 * The feature id for the '<em><b>Message Destination Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__MESSAGE_DESTINATION_REF = 14;

	/**
	 * The feature id for the '<em><b>Persistence Context Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__PERSISTENCE_CONTEXT_REF = 15;

	/**
	 * The feature id for the '<em><b>Persistence Unit Ref</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__PERSISTENCE_UNIT_REF = 16;

	/**
	 * The feature id for the '<em><b>Message Destination</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__MESSAGE_DESTINATION = 17;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DATA_SOURCE = 18;

	/**
	 * The feature id for the '<em><b>Jms Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__JMS_CONNECTION_FACTORY = 19;

	/**
	 * The feature id for the '<em><b>Jms Destination</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__JMS_DESTINATION = 20;

	/**
	 * The feature id for the '<em><b>Mail Session</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__MAIL_SESSION = 21;

	/**
	 * The feature id for the '<em><b>Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__CONNECTION_FACTORY = 22;

	/**
	 * The feature id for the '<em><b>Administered Object</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ADMINISTERED_OBJECT = 23;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ID = 24;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__VERSION = 25;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationDeploymentDescriptorImpl <em>Deployment Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationDeploymentDescriptorImpl
	 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getApplicationDeploymentDescriptor()
	 * @generated
	 */
	int APPLICATION_DEPLOYMENT_DESCRIPTOR = 1;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_DEPLOYMENT_DESCRIPTOR__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_DEPLOYMENT_DESCRIPTOR__APPLICATION = 3;

	/**
	 * The number of structural features of the '<em>Deployment Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_DEPLOYMENT_DESCRIPTOR_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.application.internal.impl.ModuleImpl
	 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getModule()
	 * @generated
	 */
	int MODULE = 2;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__CONNECTOR = 0;

	/**
	 * The feature id for the '<em><b>Ejb</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__EJB = 1;

	/**
	 * The feature id for the '<em><b>Java</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__JAVA = 2;

	/**
	 * The feature id for the '<em><b>Web</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__WEB = 3;

	/**
	 * The feature id for the '<em><b>Alt Dd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__ALT_DD = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__ID = 5;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.application.internal.impl.WebImpl <em>Web</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.application.internal.impl.WebImpl
	 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getWeb()
	 * @generated
	 */
	int WEB = 3;

	/**
	 * The feature id for the '<em><b>Web Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB__WEB_URI = 0;

	/**
	 * The feature id for the '<em><b>Context Root</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB__CONTEXT_ROOT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB__ID = 2;

	/**
	 * The number of structural features of the '<em>Web</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.application.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see org.eclipse.jst.javaee.application.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Application#getApplicationName <em>Application Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Application Name</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getApplicationName()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_ApplicationName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getDescriptions()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getDisplayNames()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getIcons()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Icons();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Application#getInitializeInOrder <em>Initialize In Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initialize In Order</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getInitializeInOrder()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_InitializeInOrder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modules</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getModules()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Modules();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getSecurityRoles <em>Security Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Roles</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getSecurityRoles()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_SecurityRoles();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Application#getLibraryDirectory <em>Library Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Library Directory</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getLibraryDirectory()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_LibraryDirectory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getEnvEntry <em>Env Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Env Entry</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getEnvEntry()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_EnvEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getEjbRef <em>Ejb Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getEjbRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_EjbRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getEjbLocalRef <em>Ejb Local Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Local Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getEjbLocalRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_EjbLocalRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getServiceRefs <em>Service Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Refs</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getServiceRefs()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_ServiceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getResourceRef <em>Resource Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getResourceRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_ResourceRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getResourceEnvRef <em>Resource Env Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Env Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getResourceEnvRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_ResourceEnvRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getMessageDestinationRef <em>Message Destination Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getMessageDestinationRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_MessageDestinationRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getPersistenceContextRef <em>Persistence Context Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Context Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getPersistenceContextRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_PersistenceContextRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getPersistenceUnitRef <em>Persistence Unit Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Unit Ref</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getPersistenceUnitRef()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_PersistenceUnitRef();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getMessageDestination <em>Message Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getMessageDestination()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_MessageDestination();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Source</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getDataSource()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_DataSource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getJmsConnectionFactory <em>Jms Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getJmsConnectionFactory()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_JmsConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getJmsDestination <em>Jms Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Destination</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getJmsDestination()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_JmsDestination();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getMailSession <em>Mail Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Session</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getMailSession()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_MailSession();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getConnectionFactory <em>Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getConnectionFactory()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_ConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.application.Application#getAdministeredObject <em>Administered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Administered Object</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getAdministeredObject()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_AdministeredObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Application#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getId()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.javaee.application.Application#getVersion()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor <em>Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Descriptor</em>'.
	 * @see org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor
	 * @generated
	 */
	EClass getApplicationDeploymentDescriptor();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getMixed()
	 * @see #getApplicationDeploymentDescriptor()
	 * @generated
	 */
	EAttribute getApplicationDeploymentDescriptor_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getXMLNSPrefixMap()
	 * @see #getApplicationDeploymentDescriptor()
	 * @generated
	 */
	EReference getApplicationDeploymentDescriptor_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getXSISchemaLocation()
	 * @see #getApplicationDeploymentDescriptor()
	 * @generated
	 */
	EReference getApplicationDeploymentDescriptor_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Application</em>'.
	 * @see org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor#getApplication()
	 * @see #getApplicationDeploymentDescriptor()
	 * @generated
	 */
	EReference getApplicationDeploymentDescriptor_Application();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.application.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see org.eclipse.jst.javaee.application.Module
	 * @generated
	 */
	EClass getModule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Module#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connector</em>'.
	 * @see org.eclipse.jst.javaee.application.Module#getConnector()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Connector();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Module#getEjb <em>Ejb</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ejb</em>'.
	 * @see org.eclipse.jst.javaee.application.Module#getEjb()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Ejb();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Module#getJava <em>Java</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java</em>'.
	 * @see org.eclipse.jst.javaee.application.Module#getJava()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Java();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.application.Module#getWeb <em>Web</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Web</em>'.
	 * @see org.eclipse.jst.javaee.application.Module#getWeb()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Web();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Module#getAltDd <em>Alt Dd</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alt Dd</em>'.
	 * @see org.eclipse.jst.javaee.application.Module#getAltDd()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_AltDd();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Module#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.application.Module#getId()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.application.Web <em>Web</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Web</em>'.
	 * @see org.eclipse.jst.javaee.application.Web
	 * @generated
	 */
	EClass getWeb();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Web#getWebUri <em>Web Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Web Uri</em>'.
	 * @see org.eclipse.jst.javaee.application.Web#getWebUri()
	 * @see #getWeb()
	 * @generated
	 */
	EAttribute getWeb_WebUri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Web#getContextRoot <em>Context Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Context Root</em>'.
	 * @see org.eclipse.jst.javaee.application.Web#getContextRoot()
	 * @see #getWeb()
	 * @generated
	 */
	EAttribute getWeb_ContextRoot();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.application.Web#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.application.Web#getId()
	 * @see #getWeb()
	 * @generated
	 */
	EAttribute getWeb_Id();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ApplicationFactory getApplicationFactory();

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
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl
		 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '<em><b>Application Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__APPLICATION_NAME = eINSTANCE.getApplication_ApplicationName();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__DESCRIPTIONS = eINSTANCE.getApplication_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__DISPLAY_NAMES = eINSTANCE.getApplication_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__ICONS = eINSTANCE.getApplication_Icons();

		/**
		 * The meta object literal for the '<em><b>Initialize In Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__INITIALIZE_IN_ORDER = eINSTANCE.getApplication_InitializeInOrder();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__MODULES = eINSTANCE.getApplication_Modules();

		/**
		 * The meta object literal for the '<em><b>Security Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__SECURITY_ROLES = eINSTANCE.getApplication_SecurityRoles();

		/**
		 * The meta object literal for the '<em><b>Library Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__LIBRARY_DIRECTORY = eINSTANCE.getApplication_LibraryDirectory();

		/**
		 * The meta object literal for the '<em><b>Env Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__ENV_ENTRY = eINSTANCE.getApplication_EnvEntry();

		/**
		 * The meta object literal for the '<em><b>Ejb Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__EJB_REF = eINSTANCE.getApplication_EjbRef();

		/**
		 * The meta object literal for the '<em><b>Ejb Local Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__EJB_LOCAL_REF = eINSTANCE.getApplication_EjbLocalRef();

		/**
		 * The meta object literal for the '<em><b>Service Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__SERVICE_REFS = eINSTANCE.getApplication_ServiceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__RESOURCE_REF = eINSTANCE.getApplication_ResourceRef();

		/**
		 * The meta object literal for the '<em><b>Resource Env Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__RESOURCE_ENV_REF = eINSTANCE.getApplication_ResourceEnvRef();

		/**
		 * The meta object literal for the '<em><b>Message Destination Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__MESSAGE_DESTINATION_REF = eINSTANCE.getApplication_MessageDestinationRef();

		/**
		 * The meta object literal for the '<em><b>Persistence Context Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__PERSISTENCE_CONTEXT_REF = eINSTANCE.getApplication_PersistenceContextRef();

		/**
		 * The meta object literal for the '<em><b>Persistence Unit Ref</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__PERSISTENCE_UNIT_REF = eINSTANCE.getApplication_PersistenceUnitRef();

		/**
		 * The meta object literal for the '<em><b>Message Destination</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__MESSAGE_DESTINATION = eINSTANCE.getApplication_MessageDestination();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__DATA_SOURCE = eINSTANCE.getApplication_DataSource();

		/**
		 * The meta object literal for the '<em><b>Jms Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__JMS_CONNECTION_FACTORY = eINSTANCE.getApplication_JmsConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Jms Destination</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__JMS_DESTINATION = eINSTANCE.getApplication_JmsDestination();

		/**
		 * The meta object literal for the '<em><b>Mail Session</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__MAIL_SESSION = eINSTANCE.getApplication_MailSession();

		/**
		 * The meta object literal for the '<em><b>Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__CONNECTION_FACTORY = eINSTANCE.getApplication_ConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Administered Object</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__ADMINISTERED_OBJECT = eINSTANCE.getApplication_AdministeredObject();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__ID = eINSTANCE.getApplication_Id();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__VERSION = eINSTANCE.getApplication_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationDeploymentDescriptorImpl <em>Deployment Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationDeploymentDescriptorImpl
		 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getApplicationDeploymentDescriptor()
		 * @generated
		 */
		EClass APPLICATION_DEPLOYMENT_DESCRIPTOR = eINSTANCE.getApplicationDeploymentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_DEPLOYMENT_DESCRIPTOR__MIXED = eINSTANCE.getApplicationDeploymentDescriptor_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = eINSTANCE.getApplicationDeploymentDescriptor_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = eINSTANCE.getApplicationDeploymentDescriptor_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Application</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_DEPLOYMENT_DESCRIPTOR__APPLICATION = eINSTANCE.getApplicationDeploymentDescriptor_Application();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.application.internal.impl.ModuleImpl
		 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getModule()
		 * @generated
		 */
		EClass MODULE = eINSTANCE.getModule();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__CONNECTOR = eINSTANCE.getModule_Connector();

		/**
		 * The meta object literal for the '<em><b>Ejb</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__EJB = eINSTANCE.getModule_Ejb();

		/**
		 * The meta object literal for the '<em><b>Java</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__JAVA = eINSTANCE.getModule_Java();

		/**
		 * The meta object literal for the '<em><b>Web</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__WEB = eINSTANCE.getModule_Web();

		/**
		 * The meta object literal for the '<em><b>Alt Dd</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__ALT_DD = eINSTANCE.getModule_AltDd();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__ID = eINSTANCE.getModule_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.application.internal.impl.WebImpl <em>Web</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.application.internal.impl.WebImpl
		 * @see org.eclipse.jst.javaee.application.internal.impl.ApplicationPackageImpl#getWeb()
		 * @generated
		 */
		EClass WEB = eINSTANCE.getWeb();

		/**
		 * The meta object literal for the '<em><b>Web Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB__WEB_URI = eINSTANCE.getWeb_WebUri();

		/**
		 * The meta object literal for the '<em><b>Context Root</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB__CONTEXT_ROOT = eINSTANCE.getWeb_ContextRoot();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB__ID = eINSTANCE.getWeb_Id();

	}

} //ApplicationPackage
