/**
 * <copyright>
 * </copyright>
 *
 * $Id: ApplicationPackage.java,v 1.3 2007/04/26 17:11:46 jsholl Exp $
 */
package org.eclipse.jst.javaee.application.internal.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 *       @(#)application_5.xsds	1.17 08/05/05
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
 * 	This is the XML Schema for the application 5 deployment
 * 	descriptor.  The deployment descriptor must be named
 * 	"META-INF/application.xml" in the application's ear file.
 * 	All application deployment descriptors must indicate
 * 	the application schema by using the Java EE namespace:
 * 
 * 	http://java.sun.com/xml/ns/javaee
 * 
 * 	and indicate the version of the schema by
 * 	using the version element as shown below:
 * 
 * 	    &lt;application xmlns="http://java.sun.com/xml/ns/javaee"
 * 	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 * 	      xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
 * 		http://java.sun.com/xml/ns/javaee/application_5.xsd"
 * 	      version="5"&gt;
 * 	      ...
 * 	    &lt;/application&gt;
 * 
 * 	The instance documents may indicate the published version of
 * 	the schema using the xsi:schemaLocation attribute for Java EE
 * 	namespace with the following location:
 * 
 * 	http://java.sun.com/xml/ns/javaee/application_5.xsd
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
 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationFactory
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
	 * <!-- end-user-doc -->
	 */
	String eNS_URI = "http://java.sun.com/xml/ns/javaee/application_5.xsd"; //$NON-NLS-1$

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
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__DISPLAY_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ICONS = 2;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__MODULES = 3;

	/**
	 * The feature id for the '<em><b>Security Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__SECURITY_ROLES = 4;

	/**
	 * The feature id for the '<em><b>Library Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__LIBRARY_DIRECTORY = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__ID = 6;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__VERSION = 7;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = 8;

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
