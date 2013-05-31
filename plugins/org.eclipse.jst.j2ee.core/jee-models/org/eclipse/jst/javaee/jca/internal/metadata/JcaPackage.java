/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.javaee.jca.JcaFactory;

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
 *       $Id: connector_1_6.xsd,v 1.3 2010/01/14 18:20:11 ccc Exp $
 *       
 *     
 * 
 * [
 *       This is the XML Schema for the Connector 1.7 deployment
 *       descriptor.  The deployment descriptor must be named
 *       "META-INF/ra.xml" in the connector's rar file.  All Connector
 *       deployment descriptors must indicate the connector resource
 *       adapter schema by using the Java EE namespace:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee
 *       
 *       and by indicating the version of the schema by
 *       using the version element as shown below:
 *       
 *       <connector xmlns="http://xmlns.jcp.org/xml/ns/javaee"
 *       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
 *       	 http://xmlns.jcp.org/xml/ns/javaee/connector_1_7.xsd"
 *       version="1.7">
 *       ...
 *       </connector>
 *       
 *       The instance documents may indicate the published version of
 *       the schema using the xsi:schemaLocation attribute for Java EE
 *       namespace with the following location:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee/connector_1_7.xsd
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
 * @see org.eclipse.jst.javaee.jca.JcaFactory
 * @generated
 */
public interface JcaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jca"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = J2EEConstants.CONNECTOR_SCHEMA_LOC_1_6;
	String eNS_URI2 = J2EEConstants.CONNECTOR_SCHEMA_LOC_1_7;

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jca"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JcaPackage eINSTANCE = org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl <em>Activation Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getActivationSpec()
	 * @generated
	 */
	int ACTIVATION_SPEC = 0;

	/**
	 * The feature id for the '<em><b>Activationspec Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Required Config Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Config Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__CONFIG_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC__ID = 3;

	/**
	 * The number of structural features of the '<em>Activation Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVATION_SPEC_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl <em>Admin Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getAdminObject()
	 * @generated
	 */
	int ADMIN_OBJECT = 1;

	/**
	 * The feature id for the '<em><b>Adminobject Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__ADMINOBJECT_INTERFACE = 0;

	/**
	 * The feature id for the '<em><b>Adminobject Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__ADMINOBJECT_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Config Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__CONFIG_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT__ID = 3;

	/**
	 * The number of structural features of the '<em>Admin Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADMIN_OBJECT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl <em>Authentication Mechanism</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getAuthenticationMechanism()
	 * @generated
	 */
	int AUTHENTICATION_MECHANISM = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Authentication Mechanism Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Credential Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM__ID = 3;

	/**
	 * The number of structural features of the '<em>Authentication Mechanism</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_MECHANISM_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl <em>Config Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigProperty()
	 * @generated
	 */
	int CONFIG_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Config Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__CONFIG_PROPERTY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Config Property Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Config Property Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Config Property Ignore</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE = 4;

	/**
	 * The feature id for the '<em><b>Config Property Supports Dynamic Updates</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES = 5;

	/**
	 * The feature id for the '<em><b>Config Property Confidential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY__ID = 7;

	/**
	 * The number of structural features of the '<em>Config Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIG_PROPERTY_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl <em>Connection Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConnectionDefinition()
	 * @generated
	 */
	int CONNECTION_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Managedconnectionfactory Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Config Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONFIG_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Connectionfactory Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Connectionfactory Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Connection Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_INTERFACE = 4;

	/**
	 * The feature id for the '<em><b>Connection Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION__ID = 6;

	/**
	 * The number of structural features of the '<em>Connection Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_DEFINITION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 5;

	/**
	 * The feature id for the '<em><b>Module Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__MODULE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DESCRIPTIONS = 1;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DISPLAY_NAMES = 2;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ICONS = 3;

	/**
	 * The feature id for the '<em><b>Vendor Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__VENDOR_NAME = 4;

	/**
	 * The feature id for the '<em><b>Eis Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__EIS_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Resourceadapter Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__RESOURCEADAPTER_VERSION = 6;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__LICENSE = 7;

	/**
	 * The feature id for the '<em><b>Resourceadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__RESOURCEADAPTER = 8;

	/**
	 * The feature id for the '<em><b>Required Work Context</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__REQUIRED_WORK_CONTEXT = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ID = 10;

	/**
	 * The feature id for the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__METADATA_COMPLETE = 11;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__VERSION = 12;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl <em>Connector Deployment Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConnectorDeploymentDescriptor()
	 * @generated
	 */
	int CONNECTOR_DEPLOYMENT_DESCRIPTOR = 6;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR = 3;

	/**
	 * The number of structural features of the '<em>Connector Deployment Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEPLOYMENT_DESCRIPTOR_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.InboundResourceAdapterImpl <em>Inbound Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.InboundResourceAdapterImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getInboundResourceAdapter()
	 * @generated
	 */
	int INBOUND_RESOURCE_ADAPTER = 7;

	/**
	 * The feature id for the '<em><b>Messageadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOUND_RESOURCE_ADAPTER__ID = 1;

	/**
	 * The number of structural features of the '<em>Inbound Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOUND_RESOURCE_ADAPTER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.LicenseImpl <em>License</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.LicenseImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getLicense()
	 * @generated
	 */
	int LICENSE = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>License Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE__LICENSE_REQUIRED = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE__ID = 2;

	/**
	 * The number of structural features of the '<em>License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.MessageAdapterImpl <em>Message Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.MessageAdapterImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getMessageAdapter()
	 * @generated
	 */
	int MESSAGE_ADAPTER = 9;

	/**
	 * The feature id for the '<em><b>Messagelistener</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_ADAPTER__MESSAGELISTENER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_ADAPTER__ID = 1;

	/**
	 * The number of structural features of the '<em>Message Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_ADAPTER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl <em>Message Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getMessageListener()
	 * @generated
	 */
	int MESSAGE_LISTENER = 10;

	/**
	 * The feature id for the '<em><b>Messagelistener Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER__MESSAGELISTENER_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Activationspec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER__ACTIVATIONSPEC = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER__ID = 2;

	/**
	 * The number of structural features of the '<em>Message Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_LISTENER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl <em>Outbound Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getOutboundResourceAdapter()
	 * @generated
	 */
	int OUTBOUND_RESOURCE_ADAPTER = 11;

	/**
	 * The feature id for the '<em><b>Connection Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Transaction Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT = 1;

	/**
	 * The feature id for the '<em><b>Authentication Mechanism</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM = 2;

	/**
	 * The feature id for the '<em><b>Reauthentication Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER__ID = 4;

	/**
	 * The number of structural features of the '<em>Outbound Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOUND_RESOURCE_ADAPTER_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.RequiredConfigPropertyImpl <em>Required Config Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.RequiredConfigPropertyImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getRequiredConfigProperty()
	 * @generated
	 */
	int REQUIRED_CONFIG_PROPERTY = 12;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Config Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY__CONFIG_PROPERTY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY__ID = 2;

	/**
	 * The number of structural features of the '<em>Required Config Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CONFIG_PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl <em>Resource Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getResourceAdapter()
	 * @generated
	 */
	int RESOURCE_ADAPTER = 13;

	/**
	 * The feature id for the '<em><b>Resourceadapter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Config Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__CONFIG_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Outbound Resourceadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER = 2;

	/**
	 * The feature id for the '<em><b>Inbound Resourceadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER = 3;

	/**
	 * The feature id for the '<em><b>Adminobject</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__ADMINOBJECT = 4;

	/**
	 * The feature id for the '<em><b>Security Permission</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__SECURITY_PERMISSION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER__ID = 6;

	/**
	 * The number of structural features of the '<em>Resource Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ADAPTER_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.internal.impl.SecurityPermissionImpl <em>Security Permission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.internal.impl.SecurityPermissionImpl
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getSecurityPermission()
	 * @generated
	 */
	int SECURITY_PERMISSION = 14;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Security Permission Spec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION__SECURITY_PERMISSION_SPEC = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION__ID = 2;

	/**
	 * The number of structural features of the '<em>Security Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_PERMISSION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.ConfigPropertyTypeType <em>Config Property Type Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigPropertyTypeType()
	 * @generated
	 */
	int CONFIG_PROPERTY_TYPE_TYPE = 15;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jca.CredentialInterfaceType <em>Credential Interface Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getCredentialInterfaceType()
	 * @generated
	 */
	int CREDENTIAL_INTERFACE_TYPE = 16;

	/**
	 * The meta object id for the '<em>Config Property Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigPropertyNameType()
	 * @generated
	 */
	int CONFIG_PROPERTY_NAME_TYPE = 17;

	/**
	 * The meta object id for the '<em>Config Property Type Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigPropertyTypeTypeObject()
	 * @generated
	 */
	int CONFIG_PROPERTY_TYPE_TYPE_OBJECT = 18;

	/**
	 * The meta object id for the '<em>Credential Interface Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
	 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getCredentialInterfaceTypeObject()
	 * @generated
	 */
	int CREDENTIAL_INTERFACE_TYPE_OBJECT = 19;

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.ActivationSpec <em>Activation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activation Spec</em>'.
	 * @see org.eclipse.jst.javaee.jca.ActivationSpec
	 * @generated
	 */
	EClass getActivationSpec();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ActivationSpec#getActivationspecClass <em>Activationspec Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activationspec Class</em>'.
	 * @see org.eclipse.jst.javaee.jca.ActivationSpec#getActivationspecClass()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EAttribute getActivationSpec_ActivationspecClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ActivationSpec#getRequiredConfigProperty <em>Required Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.ActivationSpec#getRequiredConfigProperty()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EReference getActivationSpec_RequiredConfigProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ActivationSpec#getConfigProperty <em>Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.ActivationSpec#getConfigProperty()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EReference getActivationSpec_ConfigProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ActivationSpec#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.ActivationSpec#getId()
	 * @see #getActivationSpec()
	 * @generated
	 */
	EAttribute getActivationSpec_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.AdminObject <em>Admin Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Admin Object</em>'.
	 * @see org.eclipse.jst.javaee.jca.AdminObject
	 * @generated
	 */
	EClass getAdminObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectInterface <em>Adminobject Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adminobject Interface</em>'.
	 * @see org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectInterface()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_AdminobjectInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectClass <em>Adminobject Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adminobject Class</em>'.
	 * @see org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectClass()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_AdminobjectClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.AdminObject#getConfigProperty <em>Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.AdminObject#getConfigProperty()
	 * @see #getAdminObject()
	 * @generated
	 */
	EReference getAdminObject_ConfigProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.AdminObject#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.AdminObject#getId()
	 * @see #getAdminObject()
	 * @generated
	 */
	EAttribute getAdminObject_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism <em>Authentication Mechanism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Authentication Mechanism</em>'.
	 * @see org.eclipse.jst.javaee.jca.AuthenticationMechanism
	 * @generated
	 */
	EClass getAuthenticationMechanism();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.javaee.jca.AuthenticationMechanism#getDescription()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EReference getAuthenticationMechanism_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authentication Mechanism Type</em>'.
	 * @see org.eclipse.jst.javaee.jca.AuthenticationMechanism#getAuthenticationMechanismType()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EAttribute getAuthenticationMechanism_AuthenticationMechanismType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getCredentialInterface <em>Credential Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Credential Interface</em>'.
	 * @see org.eclipse.jst.javaee.jca.AuthenticationMechanism#getCredentialInterface()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EAttribute getAuthenticationMechanism_CredentialInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.AuthenticationMechanism#getId()
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	EAttribute getAuthenticationMechanism_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.ConfigProperty <em>Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty
	 * @generated
	 */
	EClass getConfigProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#getDescription()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EReference getConfigProperty_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyName <em>Config Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Name</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyName()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_ConfigPropertyName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyType <em>Config Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Type</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyType()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_ConfigPropertyType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyValue <em>Config Property Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Value</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyValue()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_ConfigPropertyValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyIgnore <em>Config Property Ignore</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Ignore</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyIgnore()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_ConfigPropertyIgnore();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertySupportsDynamicUpdates <em>Config Property Supports Dynamic Updates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Supports Dynamic Updates</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertySupportsDynamicUpdates()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_ConfigPropertySupportsDynamicUpdates();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyConfidential <em>Config Property Confidential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Confidential</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyConfidential()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_ConfigPropertyConfidential();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigProperty#getId()
	 * @see #getConfigProperty()
	 * @generated
	 */
	EAttribute getConfigProperty_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition <em>Connection Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Definition</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition
	 * @generated
	 */
	EClass getConnectionDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getManagedconnectionfactoryClass <em>Managedconnectionfactory Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Managedconnectionfactory Class</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getManagedconnectionfactoryClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ManagedconnectionfactoryClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConfigProperty <em>Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getConfigProperty()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EReference getConnectionDefinition_ConfigProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryInterface <em>Connectionfactory Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connectionfactory Interface</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryInterface()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionfactoryInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryImplClass <em>Connectionfactory Impl Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connectionfactory Impl Class</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryImplClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionfactoryImplClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Interface</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionInterface()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Impl Class</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionImplClass()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_ConnectionImplClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectionDefinition#getId()
	 * @see #getConnectionDefinition()
	 * @generated
	 */
	EAttribute getConnectionDefinition_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#getModuleName <em>Module Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Module Name</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getModuleName()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_ModuleName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.Connector#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getDescriptions()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.Connector#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getDisplayNames()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.Connector#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getIcons()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Icons();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#getVendorName <em>Vendor Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor Name</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getVendorName()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_VendorName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#getEisType <em>Eis Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eis Type</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getEisType()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_EisType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#getResourceadapterVersion <em>Resourceadapter Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resourceadapter Version</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getResourceadapterVersion()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_ResourceadapterVersion();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.Connector#getLicense <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>License</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getLicense()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_License();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.Connector#getResourceadapter <em>Resourceadapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resourceadapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getResourceadapter()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Resourceadapter();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.jca.Connector#getRequiredWorkContext <em>Required Work Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Required Work Context</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getRequiredWorkContext()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_RequiredWorkContext();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getId()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#isMetadataComplete <em>Metadata Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metadata Complete</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#isMetadataComplete()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_MetadataComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.Connector#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.javaee.jca.Connector#getVersion()
	 * @see #getConnector()
	 * @generated
	 */
	EAttribute getConnector_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor <em>Connector Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Deployment Descriptor</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor
	 * @generated
	 */
	EClass getConnectorDeploymentDescriptor();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getMixed()
	 * @see #getConnectorDeploymentDescriptor()
	 * @generated
	 */
	EAttribute getConnectorDeploymentDescriptor_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getXMLNSPrefixMap()
	 * @see #getConnectorDeploymentDescriptor()
	 * @generated
	 */
	EReference getConnectorDeploymentDescriptor_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getXSISchemaLocation()
	 * @see #getConnectorDeploymentDescriptor()
	 * @generated
	 */
	EReference getConnectorDeploymentDescriptor_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connector</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor#getConnector()
	 * @see #getConnectorDeploymentDescriptor()
	 * @generated
	 */
	EReference getConnectorDeploymentDescriptor_Connector();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter <em>Inbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.InboundResourceAdapter
	 * @generated
	 */
	EClass getInboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter#getMessageadapter <em>Messageadapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Messageadapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.InboundResourceAdapter#getMessageadapter()
	 * @see #getInboundResourceAdapter()
	 * @generated
	 */
	EReference getInboundResourceAdapter_Messageadapter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.InboundResourceAdapter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.InboundResourceAdapter#getId()
	 * @see #getInboundResourceAdapter()
	 * @generated
	 */
	EAttribute getInboundResourceAdapter_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.License <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License</em>'.
	 * @see org.eclipse.jst.javaee.jca.License
	 * @generated
	 */
	EClass getLicense();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.License#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.javaee.jca.License#getDescription()
	 * @see #getLicense()
	 * @generated
	 */
	EReference getLicense_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.License#isLicenseRequired <em>License Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>License Required</em>'.
	 * @see org.eclipse.jst.javaee.jca.License#isLicenseRequired()
	 * @see #getLicense()
	 * @generated
	 */
	EAttribute getLicense_LicenseRequired();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.License#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.License#getId()
	 * @see #getLicense()
	 * @generated
	 */
	EAttribute getLicense_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.MessageAdapter <em>Message Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Adapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageAdapter
	 * @generated
	 */
	EClass getMessageAdapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.MessageAdapter#getMessagelistener <em>Messagelistener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Messagelistener</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageAdapter#getMessagelistener()
	 * @see #getMessageAdapter()
	 * @generated
	 */
	EReference getMessageAdapter_Messagelistener();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.MessageAdapter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageAdapter#getId()
	 * @see #getMessageAdapter()
	 * @generated
	 */
	EAttribute getMessageAdapter_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.MessageListener <em>Message Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Listener</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageListener
	 * @generated
	 */
	EClass getMessageListener();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.MessageListener#getMessagelistenerType <em>Messagelistener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Messagelistener Type</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageListener#getMessagelistenerType()
	 * @see #getMessageListener()
	 * @generated
	 */
	EAttribute getMessageListener_MessagelistenerType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.MessageListener#getActivationspec <em>Activationspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activationspec</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageListener#getActivationspec()
	 * @see #getMessageListener()
	 * @generated
	 */
	EReference getMessageListener_Activationspec();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.MessageListener#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.MessageListener#getId()
	 * @see #getMessageListener()
	 * @generated
	 */
	EAttribute getMessageListener_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter <em>Outbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outbound Resource Adapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.OutboundResourceAdapter
	 * @generated
	 */
	EClass getOutboundResourceAdapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getConnectionDefinition <em>Connection Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Definition</em>'.
	 * @see org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getConnectionDefinition()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EReference getOutboundResourceAdapter_ConnectionDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getTransactionSupport <em>Transaction Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Support</em>'.
	 * @see org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getTransactionSupport()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_TransactionSupport();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getAuthenticationMechanism <em>Authentication Mechanism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Authentication Mechanism</em>'.
	 * @see org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getAuthenticationMechanism()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EReference getOutboundResourceAdapter_AuthenticationMechanism();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#isReauthenticationSupport <em>Reauthentication Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reauthentication Support</em>'.
	 * @see org.eclipse.jst.javaee.jca.OutboundResourceAdapter#isReauthenticationSupport()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_ReauthenticationSupport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.OutboundResourceAdapter#getId()
	 * @see #getOutboundResourceAdapter()
	 * @generated
	 */
	EAttribute getOutboundResourceAdapter_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty <em>Required Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.RequiredConfigProperty
	 * @generated
	 */
	EClass getRequiredConfigProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.javaee.jca.RequiredConfigProperty#getDescription()
	 * @see #getRequiredConfigProperty()
	 * @generated
	 */
	EReference getRequiredConfigProperty_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getConfigPropertyName <em>Config Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Config Property Name</em>'.
	 * @see org.eclipse.jst.javaee.jca.RequiredConfigProperty#getConfigPropertyName()
	 * @see #getRequiredConfigProperty()
	 * @generated
	 */
	EAttribute getRequiredConfigProperty_ConfigPropertyName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.RequiredConfigProperty#getId()
	 * @see #getRequiredConfigProperty()
	 * @generated
	 */
	EAttribute getRequiredConfigProperty_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.ResourceAdapter <em>Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Adapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter
	 * @generated
	 */
	EClass getResourceAdapter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getResourceadapterClass <em>Resourceadapter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resourceadapter Class</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getResourceadapterClass()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EAttribute getResourceAdapter_ResourceadapterClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getConfigProperty <em>Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Config Property</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getConfigProperty()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_ConfigProperty();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getOutboundResourceadapter <em>Outbound Resourceadapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outbound Resourceadapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getOutboundResourceadapter()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_OutboundResourceadapter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getInboundResourceadapter <em>Inbound Resourceadapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inbound Resourceadapter</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getInboundResourceadapter()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_InboundResourceadapter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getAdminobject <em>Adminobject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Adminobject</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getAdminobject()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_Adminobject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getSecurityPermission <em>Security Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Permission</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getSecurityPermission()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EReference getResourceAdapter_SecurityPermission();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.ResourceAdapter#getId()
	 * @see #getResourceAdapter()
	 * @generated
	 */
	EAttribute getResourceAdapter_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jca.SecurityPermission <em>Security Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Permission</em>'.
	 * @see org.eclipse.jst.javaee.jca.SecurityPermission
	 * @generated
	 */
	EClass getSecurityPermission();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jca.SecurityPermission#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.javaee.jca.SecurityPermission#getDescription()
	 * @see #getSecurityPermission()
	 * @generated
	 */
	EReference getSecurityPermission_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.SecurityPermission#getSecurityPermissionSpec <em>Security Permission Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Permission Spec</em>'.
	 * @see org.eclipse.jst.javaee.jca.SecurityPermission#getSecurityPermissionSpec()
	 * @see #getSecurityPermission()
	 * @generated
	 */
	EAttribute getSecurityPermission_SecurityPermissionSpec();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jca.SecurityPermission#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jca.SecurityPermission#getId()
	 * @see #getSecurityPermission()
	 * @generated
	 */
	EAttribute getSecurityPermission_Id();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.jca.ConfigPropertyTypeType <em>Config Property Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Config Property Type Type</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
	 * @generated
	 */
	EEnum getConfigPropertyTypeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.jca.CredentialInterfaceType <em>Credential Interface Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Credential Interface Type</em>'.
	 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
	 * @generated
	 */
	EEnum getCredentialInterfaceType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Config Property Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Config Property Name Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getConfigPropertyNameType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.jca.ConfigPropertyTypeType <em>Config Property Type Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Config Property Type Type Object</em>'.
	 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
	 * @generated
	 */
	EDataType getConfigPropertyTypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.jca.CredentialInterfaceType <em>Credential Interface Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Credential Interface Type Object</em>'.
	 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
	 * @generated
	 */
	EDataType getCredentialInterfaceTypeObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JcaFactory getJcaFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl <em>Activation Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.ActivationSpecImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getActivationSpec()
		 * @generated
		 */
		EClass ACTIVATION_SPEC = eINSTANCE.getActivationSpec();

		/**
		 * The meta object literal for the '<em><b>Activationspec Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVATION_SPEC__ACTIVATIONSPEC_CLASS = eINSTANCE.getActivationSpec_ActivationspecClass();

		/**
		 * The meta object literal for the '<em><b>Required Config Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVATION_SPEC__REQUIRED_CONFIG_PROPERTY = eINSTANCE.getActivationSpec_RequiredConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Config Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVATION_SPEC__CONFIG_PROPERTY = eINSTANCE.getActivationSpec_ConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVATION_SPEC__ID = eINSTANCE.getActivationSpec_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl <em>Admin Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.AdminObjectImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getAdminObject()
		 * @generated
		 */
		EClass ADMIN_OBJECT = eINSTANCE.getAdminObject();

		/**
		 * The meta object literal for the '<em><b>Adminobject Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN_OBJECT__ADMINOBJECT_INTERFACE = eINSTANCE.getAdminObject_AdminobjectInterface();

		/**
		 * The meta object literal for the '<em><b>Adminobject Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN_OBJECT__ADMINOBJECT_CLASS = eINSTANCE.getAdminObject_AdminobjectClass();

		/**
		 * The meta object literal for the '<em><b>Config Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADMIN_OBJECT__CONFIG_PROPERTY = eINSTANCE.getAdminObject_ConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADMIN_OBJECT__ID = eINSTANCE.getAdminObject_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl <em>Authentication Mechanism</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.AuthenticationMechanismImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getAuthenticationMechanism()
		 * @generated
		 */
		EClass AUTHENTICATION_MECHANISM = eINSTANCE.getAuthenticationMechanism();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHENTICATION_MECHANISM__DESCRIPTION = eINSTANCE.getAuthenticationMechanism_Description();

		/**
		 * The meta object literal for the '<em><b>Authentication Mechanism Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE = eINSTANCE.getAuthenticationMechanism_AuthenticationMechanismType();

		/**
		 * The meta object literal for the '<em><b>Credential Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE = eINSTANCE.getAuthenticationMechanism_CredentialInterface();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_MECHANISM__ID = eINSTANCE.getAuthenticationMechanism_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl <em>Config Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.ConfigPropertyImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigProperty()
		 * @generated
		 */
		EClass CONFIG_PROPERTY = eINSTANCE.getConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIG_PROPERTY__DESCRIPTION = eINSTANCE.getConfigProperty_Description();

		/**
		 * The meta object literal for the '<em><b>Config Property Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__CONFIG_PROPERTY_NAME = eINSTANCE.getConfigProperty_ConfigPropertyName();

		/**
		 * The meta object literal for the '<em><b>Config Property Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__CONFIG_PROPERTY_TYPE = eINSTANCE.getConfigProperty_ConfigPropertyType();

		/**
		 * The meta object literal for the '<em><b>Config Property Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__CONFIG_PROPERTY_VALUE = eINSTANCE.getConfigProperty_ConfigPropertyValue();

		/**
		 * The meta object literal for the '<em><b>Config Property Ignore</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__CONFIG_PROPERTY_IGNORE = eINSTANCE.getConfigProperty_ConfigPropertyIgnore();

		/**
		 * The meta object literal for the '<em><b>Config Property Supports Dynamic Updates</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__CONFIG_PROPERTY_SUPPORTS_DYNAMIC_UPDATES = eINSTANCE.getConfigProperty_ConfigPropertySupportsDynamicUpdates();

		/**
		 * The meta object literal for the '<em><b>Config Property Confidential</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__CONFIG_PROPERTY_CONFIDENTIAL = eINSTANCE.getConfigProperty_ConfigPropertyConfidential();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIG_PROPERTY__ID = eINSTANCE.getConfigProperty_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl <em>Connection Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.ConnectionDefinitionImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConnectionDefinition()
		 * @generated
		 */
		EClass CONNECTION_DEFINITION = eINSTANCE.getConnectionDefinition();

		/**
		 * The meta object literal for the '<em><b>Managedconnectionfactory Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__MANAGEDCONNECTIONFACTORY_CLASS = eINSTANCE.getConnectionDefinition_ManagedconnectionfactoryClass();

		/**
		 * The meta object literal for the '<em><b>Config Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_DEFINITION__CONFIG_PROPERTY = eINSTANCE.getConnectionDefinition_ConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Connectionfactory Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTIONFACTORY_INTERFACE = eINSTANCE.getConnectionDefinition_ConnectionfactoryInterface();

		/**
		 * The meta object literal for the '<em><b>Connectionfactory Impl Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTIONFACTORY_IMPL_CLASS = eINSTANCE.getConnectionDefinition_ConnectionfactoryImplClass();

		/**
		 * The meta object literal for the '<em><b>Connection Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTION_INTERFACE = eINSTANCE.getConnectionDefinition_ConnectionInterface();

		/**
		 * The meta object literal for the '<em><b>Connection Impl Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__CONNECTION_IMPL_CLASS = eINSTANCE.getConnectionDefinition_ConnectionImplClass();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_DEFINITION__ID = eINSTANCE.getConnectionDefinition_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.ConnectorImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Module Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__MODULE_NAME = eINSTANCE.getConnector_ModuleName();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__DESCRIPTIONS = eINSTANCE.getConnector_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__DISPLAY_NAMES = eINSTANCE.getConnector_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__ICONS = eINSTANCE.getConnector_Icons();

		/**
		 * The meta object literal for the '<em><b>Vendor Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__VENDOR_NAME = eINSTANCE.getConnector_VendorName();

		/**
		 * The meta object literal for the '<em><b>Eis Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__EIS_TYPE = eINSTANCE.getConnector_EisType();

		/**
		 * The meta object literal for the '<em><b>Resourceadapter Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__RESOURCEADAPTER_VERSION = eINSTANCE.getConnector_ResourceadapterVersion();

		/**
		 * The meta object literal for the '<em><b>License</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__LICENSE = eINSTANCE.getConnector_License();

		/**
		 * The meta object literal for the '<em><b>Resourceadapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__RESOURCEADAPTER = eINSTANCE.getConnector_Resourceadapter();

		/**
		 * The meta object literal for the '<em><b>Required Work Context</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__REQUIRED_WORK_CONTEXT = eINSTANCE.getConnector_RequiredWorkContext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__ID = eINSTANCE.getConnector_Id();

		/**
		 * The meta object literal for the '<em><b>Metadata Complete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__METADATA_COMPLETE = eINSTANCE.getConnector_MetadataComplete();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR__VERSION = eINSTANCE.getConnector_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl <em>Connector Deployment Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.ConnectorDeploymentDescriptorImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConnectorDeploymentDescriptor()
		 * @generated
		 */
		EClass CONNECTOR_DEPLOYMENT_DESCRIPTOR = eINSTANCE.getConnectorDeploymentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_DEPLOYMENT_DESCRIPTOR__MIXED = eINSTANCE.getConnectorDeploymentDescriptor_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = eINSTANCE.getConnectorDeploymentDescriptor_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = eINSTANCE.getConnectorDeploymentDescriptor_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_DEPLOYMENT_DESCRIPTOR__CONNECTOR = eINSTANCE.getConnectorDeploymentDescriptor_Connector();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.InboundResourceAdapterImpl <em>Inbound Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.InboundResourceAdapterImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getInboundResourceAdapter()
		 * @generated
		 */
		EClass INBOUND_RESOURCE_ADAPTER = eINSTANCE.getInboundResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Messageadapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INBOUND_RESOURCE_ADAPTER__MESSAGEADAPTER = eINSTANCE.getInboundResourceAdapter_Messageadapter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INBOUND_RESOURCE_ADAPTER__ID = eINSTANCE.getInboundResourceAdapter_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.LicenseImpl <em>License</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.LicenseImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getLicense()
		 * @generated
		 */
		EClass LICENSE = eINSTANCE.getLicense();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LICENSE__DESCRIPTION = eINSTANCE.getLicense_Description();

		/**
		 * The meta object literal for the '<em><b>License Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE__LICENSE_REQUIRED = eINSTANCE.getLicense_LicenseRequired();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LICENSE__ID = eINSTANCE.getLicense_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.MessageAdapterImpl <em>Message Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.MessageAdapterImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getMessageAdapter()
		 * @generated
		 */
		EClass MESSAGE_ADAPTER = eINSTANCE.getMessageAdapter();

		/**
		 * The meta object literal for the '<em><b>Messagelistener</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_ADAPTER__MESSAGELISTENER = eINSTANCE.getMessageAdapter_Messagelistener();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_ADAPTER__ID = eINSTANCE.getMessageAdapter_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl <em>Message Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.MessageListenerImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getMessageListener()
		 * @generated
		 */
		EClass MESSAGE_LISTENER = eINSTANCE.getMessageListener();

		/**
		 * The meta object literal for the '<em><b>Messagelistener Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_LISTENER__MESSAGELISTENER_TYPE = eINSTANCE.getMessageListener_MessagelistenerType();

		/**
		 * The meta object literal for the '<em><b>Activationspec</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_LISTENER__ACTIVATIONSPEC = eINSTANCE.getMessageListener_Activationspec();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_LISTENER__ID = eINSTANCE.getMessageListener_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl <em>Outbound Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.OutboundResourceAdapterImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getOutboundResourceAdapter()
		 * @generated
		 */
		EClass OUTBOUND_RESOURCE_ADAPTER = eINSTANCE.getOutboundResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Connection Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTBOUND_RESOURCE_ADAPTER__CONNECTION_DEFINITION = eINSTANCE.getOutboundResourceAdapter_ConnectionDefinition();

		/**
		 * The meta object literal for the '<em><b>Transaction Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTBOUND_RESOURCE_ADAPTER__TRANSACTION_SUPPORT = eINSTANCE.getOutboundResourceAdapter_TransactionSupport();

		/**
		 * The meta object literal for the '<em><b>Authentication Mechanism</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTBOUND_RESOURCE_ADAPTER__AUTHENTICATION_MECHANISM = eINSTANCE.getOutboundResourceAdapter_AuthenticationMechanism();

		/**
		 * The meta object literal for the '<em><b>Reauthentication Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTBOUND_RESOURCE_ADAPTER__REAUTHENTICATION_SUPPORT = eINSTANCE.getOutboundResourceAdapter_ReauthenticationSupport();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTBOUND_RESOURCE_ADAPTER__ID = eINSTANCE.getOutboundResourceAdapter_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.RequiredConfigPropertyImpl <em>Required Config Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.RequiredConfigPropertyImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getRequiredConfigProperty()
		 * @generated
		 */
		EClass REQUIRED_CONFIG_PROPERTY = eINSTANCE.getRequiredConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIRED_CONFIG_PROPERTY__DESCRIPTION = eINSTANCE.getRequiredConfigProperty_Description();

		/**
		 * The meta object literal for the '<em><b>Config Property Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_CONFIG_PROPERTY__CONFIG_PROPERTY_NAME = eINSTANCE.getRequiredConfigProperty_ConfigPropertyName();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIRED_CONFIG_PROPERTY__ID = eINSTANCE.getRequiredConfigProperty_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl <em>Resource Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.ResourceAdapterImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getResourceAdapter()
		 * @generated
		 */
		EClass RESOURCE_ADAPTER = eINSTANCE.getResourceAdapter();

		/**
		 * The meta object literal for the '<em><b>Resourceadapter Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__RESOURCEADAPTER_CLASS = eINSTANCE.getResourceAdapter_ResourceadapterClass();

		/**
		 * The meta object literal for the '<em><b>Config Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__CONFIG_PROPERTY = eINSTANCE.getResourceAdapter_ConfigProperty();

		/**
		 * The meta object literal for the '<em><b>Outbound Resourceadapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__OUTBOUND_RESOURCEADAPTER = eINSTANCE.getResourceAdapter_OutboundResourceadapter();

		/**
		 * The meta object literal for the '<em><b>Inbound Resourceadapter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__INBOUND_RESOURCEADAPTER = eINSTANCE.getResourceAdapter_InboundResourceadapter();

		/**
		 * The meta object literal for the '<em><b>Adminobject</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__ADMINOBJECT = eINSTANCE.getResourceAdapter_Adminobject();

		/**
		 * The meta object literal for the '<em><b>Security Permission</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ADAPTER__SECURITY_PERMISSION = eINSTANCE.getResourceAdapter_SecurityPermission();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ADAPTER__ID = eINSTANCE.getResourceAdapter_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.internal.impl.SecurityPermissionImpl <em>Security Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.internal.impl.SecurityPermissionImpl
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getSecurityPermission()
		 * @generated
		 */
		EClass SECURITY_PERMISSION = eINSTANCE.getSecurityPermission();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_PERMISSION__DESCRIPTION = eINSTANCE.getSecurityPermission_Description();

		/**
		 * The meta object literal for the '<em><b>Security Permission Spec</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_PERMISSION__SECURITY_PERMISSION_SPEC = eINSTANCE.getSecurityPermission_SecurityPermissionSpec();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_PERMISSION__ID = eINSTANCE.getSecurityPermission_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.ConfigPropertyTypeType <em>Config Property Type Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigPropertyTypeType()
		 * @generated
		 */
		EEnum CONFIG_PROPERTY_TYPE_TYPE = eINSTANCE.getConfigPropertyTypeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jca.CredentialInterfaceType <em>Credential Interface Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getCredentialInterfaceType()
		 * @generated
		 */
		EEnum CREDENTIAL_INTERFACE_TYPE = eINSTANCE.getCredentialInterfaceType();

		/**
		 * The meta object literal for the '<em>Config Property Name Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigPropertyNameType()
		 * @generated
		 */
		EDataType CONFIG_PROPERTY_NAME_TYPE = eINSTANCE.getConfigPropertyNameType();

		/**
		 * The meta object literal for the '<em>Config Property Type Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getConfigPropertyTypeTypeObject()
		 * @generated
		 */
		EDataType CONFIG_PROPERTY_TYPE_TYPE_OBJECT = eINSTANCE.getConfigPropertyTypeTypeObject();

		/**
		 * The meta object literal for the '<em>Credential Interface Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
		 * @see org.eclipse.jst.javaee.jca.internal.impl.JcaPackageImpl#getCredentialInterfaceTypeObject()
		 * @generated
		 */
		EDataType CREDENTIAL_INTERFACE_TYPE_OBJECT = eINSTANCE.getCredentialInterfaceTypeObject();

	}

} //JcaPackage
