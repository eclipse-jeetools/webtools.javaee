/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationclientPackage.java,v 1.1 2007/05/16 06:42:43 cbridgha Exp $
 */
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
 *       @(#)application-client_5.xsds	1.26 02/17/06
 *     
 * 
 * 
 *       Copyright 2003-2006 Sun Microsystems, Inc.
 *       4150 Network Circle
 *       Santa Clara, California 95054
 *       U.S.A
 *       All rights reserved.
 * 
 *       Sun Microsystems, Inc. has intellectual property rights
 *       relating to technology described in this document. In
 *       particular, and without limitation, these intellectual
 *       property rights may include one or more of the U.S. patents
 *       listed at http://www.sun.com/patents and one or more
 *       additional patents or pending patent applications in the
 *       U.S. and other countries.
 * 
 *       This document and the technology which it describes are
 *       distributed under licenses restricting their use, copying,
 *       distribution, and decompilation. No part of this document
 *       may be reproduced in any form by any means without prior
 *       written authorization of Sun and its licensors, if any.
 * 
 *       Third-party software, including font technology, is
 *       copyrighted and licensed from Sun suppliers.
 * 
 *       Sun, Sun Microsystems, the Sun logo, Solaris, Java, J2EE,
 *       JavaServer Pages, Enterprise JavaBeans and the Java Coffee
 *       Cup logo are trademarks or registered trademarks of Sun
 *       Microsystems, Inc. in the U.S. and other countries.
 * 
 *       Federal Acquisitions: Commercial Software - Government Users
 *       Subject to Standard License Terms and Conditions.
 * 
 *     
 * 
 *       
 * 
 * 	This is the XML Schema for the application client 5
 * 	deployment descriptor.  The deployment descriptor must
 * 	be named "META-INF/application-client.xml" in the
 * 	application client's jar file.  All application client
 * 	deployment descriptors must indicate the application
 * 	client schema by using the Java EE namespace:
 * 
 * 	http://java.sun.com/xml/ns/javaee
 * 
 * 	and indicate the version of the schema by
 * 	using the version element as shown below:
 * 
 * 	    &lt;application-client xmlns="http://java.sun.com/xml/ns/javaee"
 * 	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 * 	      xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
 * 		http://java.sun.com/xml/ns/javaee/application-client_5.xsd"
 * 	      version="5"&gt;
 * 	      ...
 * 	    &lt;/application-client&gt;
 * 
 * 	The instance documents may indicate the published version of
 * 	the schema using the xsi:schemaLocation attribute for Java EE
 * 	namespace with the following location:
 * 
 * 	http://java.sun.com/xml/ns/javaee/application-client_5.xsd
 * 
 * 	
 *     
 * 
 * 
 *       The following conventions apply to all Java EE
 *       deployment descriptor elements unless indicated otherwise.
 * 
 *       - In elements that specify a pathname to a file within the
 * 	same JAR file, relative filenames (i.e., those not
 * 	starting with "/") are considered relative to the root of
 * 	the JAR file's namespace.  Absolute filenames (i.e., those
 * 	starting with "/") also specify names in the root of the
 * 	JAR file's namespace.  In general, relative names are
 * 	preferred.  The exception is .war files where absolute
 * 	names are preferred for consistency with the Servlet API.
 * 
 *     
 * 
 *       @(#)javaee_5.xsds	1.65 06/02/17
 *     
 * 
 * 
 *       Copyright 2003-2006 Sun Microsystems, Inc.
 *       4150 Network Circle
 *       Santa Clara, California 95054
 *       U.S.A
 *       All rights reserved.
 * 
 *       Sun Microsystems, Inc. has intellectual property rights
 *       relating to technology described in this document. In
 *       particular, and without limitation, these intellectual
 *       property rights may include one or more of the U.S. patents
 *       listed at http://www.sun.com/patents and one or more
 *       additional patents or pending patent applications in the
 *       U.S. and other countries.
 * 
 *       This document and the technology which it describes are
 *       distributed under licenses restricting their use, copying,
 *       distribution, and decompilation. No part of this document
 *       may be reproduced in any form by any means without prior
 *       written authorization of Sun and its licensors, if any.
 * 
 *       Third-party software, including font technology, is
 *       copyrighted and licensed from Sun suppliers.
 * 
 *       Sun, Sun Microsystems, the Sun logo, Solaris, Java, J2EE,
 *       JavaServer Pages, Enterprise JavaBeans and the Java Coffee
 *       Cup logo are trademarks or registered trademarks of Sun
 *       Microsystems, Inc. in the U.S. and other countries.
 * 
 *       Federal Acquisitions: Commercial Software - Government Users
 *       Subject to Standard License Terms and Conditions.
 * 
 *     
 * 
 * 
 * The following definitions that appear in the common
 * shareable schema(s) of J2EE deployment descriptors should be
 * interpreted with respect to the context they are included:
 * 
 * Deployment Component may indicate one of the following:
 *     j2ee application;
 *     application client;
 *     web application;
 *     enterprise bean;
 *     resource adapter;
 * 
 * Deployment File may indicate one of the following:
 *     ear file;
 *     war file;
 *     jar file;
 *     rar file;
 * 
 * 
 * 
 *       @(#)javaee_web_services_client_1_2.xsds	1.19 02/13/06
 *     
 * 
 * 
 *       Copyright 2003-2006 Sun Microsystems, Inc.
 *       4150 Network Circle
 *       Santa Clara, California 95054
 *       U.S.A
 *       All rights reserved.
 * 
 *       Sun Microsystems, Inc. has intellectual property rights
 *       relating to technology described in this document. In
 *       particular, and without limitation, these intellectual
 *       property rights may include one or more of the U.S. patents
 *       listed at http://www.sun.com/patents and one or more
 *       additional patents or pending patent applications in the
 *       U.S. and other countries.
 * 
 *       This document and the technology which it describes are
 *       distributed under licenses restricting their use, copying,
 *       distribution, and decompilation. No part of this document
 *       may be reproduced in any form by any means without prior
 *       written authorization of Sun and its licensors, if any.
 * 
 *       Third-party software, including font technology, is
 *       copyrighted and licensed from Sun suppliers.
 * 
 *       Sun, Sun Microsystems, the Sun logo, Solaris, Java, J2EE,
 *       JavaServer Pages, Enterprise JavaBeans and the Java Coffee
 *       Cup logo are trademarks or registered trademarks of Sun
 *       Microsystems, Inc. in the U.S. and other countries.
 * 
 *       Federal Acquisitions: Commercial Software - Government Users
 *       Subject to Standard License Terms and Conditions.
 * 
 *     
 * 
 * 
 *       (C) Copyright International Business Machines Corporation 2002
 * 
 *     
 * 
 *    See http://www.w3.org/XML/1998/namespace.html and
 *    http://www.w3.org/TR/REC-xml for information about this namespace.
 * 
 *     This schema document describes the XML namespace, in a form
 *     suitable for import by other schema documents.  
 * 
 *     Note that local names in this namespace are intended to be defined
 *     only by the World Wide Web Consortium or its subgroups.  The
 *     following names are currently defined in this namespace and should
 *     not be used with conflicting semantics by any Working Group,
 *     specification, or document instance:
 * 
 *     base (as an attribute name): denotes an attribute whose value
 *          provides a URI to be used as the base for interpreting any
 *          relative URIs in the scope of the element on which it
 *          appears; its value is inherited.  This name is reserved
 *          by virtue of its definition in the XML Base specification.
 * 
 *     id   (as an attribute name): denotes an attribute whose value
 *          should be interpreted as if declared to be of type ID.
 *          The xml:id specification is not yet a W3C Recommendation,
 *          but this attribute is included here to facilitate experimentation
 *          with the mechanisms it proposes.  Note that it is _not_ included
 *          in the specialAttrs attribute group.
 * 
 *     lang (as an attribute name): denotes an attribute whose value
 *          is a language code for the natural language of the content of
 *          any element; its value is inherited.  This name is reserved
 *          by virtue of its definition in the XML specification.
 *   
 *     space (as an attribute name): denotes an attribute whose
 *          value is a keyword indicating what whitespace processing
 *          discipline is intended for the content of the element; its
 *          value is inherited.  This name is reserved by virtue of its
 *          definition in the XML specification.
 * 
 *     Father (in any context at all): denotes Jon Bosak, the chair of 
 *          the original XML Working Group.  This name is reserved by 
 *          the following decision of the W3C XML Plenary and 
 *          XML Coordination groups:
 * 
 *              In appreciation for his vision, leadership and dedication
 *              the W3C XML Plenary on this 10th day of February, 2000
 *              reserves for Jon Bosak in perpetuity the XML name
 *              xml:Father
 *   
 * This schema defines attributes and an attribute group
 *         suitable for use by
 *         schemas wishing to allow xml:base, xml:lang, xml:space or xml:id
 *         attributes on elements they define.
 * 
 *         To enable this, such a schema must import this schema
 *         for the XML namespace, e.g. as follows:
 *         &lt;schema . . .&gt;
 *          . . .
 *          &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                     schemaLocation="http://www.w3.org/2001/xml.xsd"/&gt;
 * 
 *         Subsequently, qualified reference to any of the attributes
 *         or the group defined below will have the desired effect, e.g.
 * 
 *         &lt;type . . .&gt;
 *          . . .
 *          &lt;attributeGroup ref="xml:specialAttrs"/&gt;
 *  
 *          will define a type which will schema-validate an instance
 *          element with any of those attributes
 * In keeping with the XML Schema WG's standard versioning
 *    policy, this schema document will persist at
 *    http://www.w3.org/2005/08/xml.xsd.
 *    At the date of issue it can also be found at
 *    http://www.w3.org/2001/xml.xsd.
 *    The schema document at that URI may however change in the future,
 *    in order to remain compatible with the latest version of XML Schema
 *    itself, or with the XML namespace itself.  In other words, if the XML
 *    Schema or XML namespaces change, the version of this document at
 *    http://www.w3.org/2001/xml.xsd will change
 *    accordingly; the version at
 *    http://www.w3.org/2005/08/xml.xsd will not change.
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
	 * <!-- end-user-doc -->
	 */
	String eNS_URI = "http://java.sun.com/xml/ns/javaee/application-client_5.xsd"; //$NON-NLS-1$

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
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DISPLAY_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ICONS = 2;

	/**
	 * The feature id for the '<em><b>Env Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ENV_ENTRIES = 3;

	/**
	 * The feature id for the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__EJB_REFS = 4;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__SERVICE_REFS = 5;

	/**
	 * The feature id for the '<em><b>Resource Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__RESOURCE_REFS = 6;

	/**
	 * The feature id for the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__RESOURCE_ENV_REFS = 7;

	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS = 8;

	/**
	 * The feature id for the '<em><b>Persistence Unit Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__PERSISTENCE_UNIT_REFS = 9;

	/**
	 * The feature id for the '<em><b>Post Constructs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__POST_CONSTRUCTS = 10;

	/**
	 * The feature id for the '<em><b>Pre Destroys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__PRE_DESTROYS = 11;

	/**
	 * The feature id for the '<em><b>Callback Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__CALLBACK_HANDLER = 12;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MESSAGE_DESTINATIONS = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ID = 14;

	/**
	 * The feature id for the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__METADATA_COMPLETE = 15;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__VERSION = 16;

	/**
	 * The number of structural features of the '<em>Application Client</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_FEATURE_COUNT = 17;

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
