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
package org.eclipse.jst.javaee.web.internal.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.javaee.web.WebFactory;

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
 *       $Id: web-common_3_0.xsd,v 1.6 2010/01/14 18:20:11 ccc Exp $
 *       
 *     
 * 
 * [
 *       This is the common XML Schema for the Servlet 3.1 deployment descriptor.
 *       This file is in turn used by web.xml and web-fragment.xml
 *       web application's war file.  All Servlet deployment descriptors
 *       must indicate the web common schema by using the Java EE
 *       namespace:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee 
 *       
 *       and by indicating the version of the schema by 
 *       using the version element as shown below: 
 *       
 *       <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
 *       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *       xsi:schemaLocation="..."
 *       version="4.0">
 *       ...
 *       </web-app>
 *       
 *       The instance documents may indicate the published version of
 *       the schema using the xsi:schemaLocation attribute for Java EE
 *       namespace with the following location:
 *       
 *       http://xmlns.jcp.org/xml/ns/javaee/web-common_4_0.xsd
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
 *    <div id="usage" xml:id="usage" xmlns="http://www.w3.org/1999/xhtml">
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
 *    <div id="nsversioning" xml:id="nsversioning" xmlns="http://www.w3.org/1999/xhtml">
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
 * 
 *       @(#)jsp_2_2.xsds	02/26/09
 *     
 * 
 * 
 *       This is the XML Schema for the JSP 2.3 deployment descriptor
 *       types.  The JSP 2.3 schema contains all the special
 *       structures and datatypes that are necessary to use JSP files
 *       from a web application. 
 *       
 *       The contents of this schema is used by the web-common_3_1.xsd 
 *       file to define JSP specific content. 
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
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.web.WebFactory
 * @generated
 */
public interface WebPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "web"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * The package must be registered with the Java EE 5 and Java EE 6 xsds, since it is used for both 
	 * <!-- end-user-doc -->
	 */
	String eNS_URI = J2EEConstants.WEB_APP_SCHEMA_LOC_2_5;
	String eNS_URI2 = J2EEConstants.WEB_APP_SCHEMA_LOC_3_0;
	String eNS_URI3 = J2EEConstants.WEB_APP_SCHEMA_LOC_3_1;
	String eNS_URI4 = J2EEConstants.WEB_APP_SCHEMA_LOC_4_0;

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "web"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebPackage eINSTANCE = org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl <em>Absolute Ordering Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getAbsoluteOrderingType()
	 * @generated
	 */
	int ABSOLUTE_ORDERING_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Others</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE__OTHERS = 2;

	/**
	 * The number of structural features of the '<em>Absolute Ordering Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.AuthConstraintImpl <em>Auth Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.AuthConstraintImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getAuthConstraint()
	 * @generated
	 */
	int AUTH_CONSTRAINT = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTH_CONSTRAINT__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Role Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTH_CONSTRAINT__ROLE_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTH_CONSTRAINT__ID = 2;

	/**
	 * The number of structural features of the '<em>Auth Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTH_CONSTRAINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieCommentTypeImpl <em>Cookie Comment Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.CookieCommentTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieCommentType()
	 * @generated
	 */
	int COOKIE_COMMENT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_COMMENT_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Cookie Comment Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_COMMENT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl <em>Cookie Config Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieConfigType()
	 * @generated
	 */
	int COOKIE_CONFIG_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Domain</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__DOMAIN = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__PATH = 2;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__COMMENT = 3;

	/**
	 * The feature id for the '<em><b>Http Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__HTTP_ONLY = 4;

	/**
	 * The feature id for the '<em><b>Secure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__SECURE = 5;

	/**
	 * The feature id for the '<em><b>Max Age</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__MAX_AGE = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE__ID = 7;

	/**
	 * The number of structural features of the '<em>Cookie Config Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_CONFIG_TYPE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieDomainTypeImpl <em>Cookie Domain Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.CookieDomainTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieDomainType()
	 * @generated
	 */
	int COOKIE_DOMAIN_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_DOMAIN_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Cookie Domain Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_DOMAIN_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieNameTypeImpl <em>Cookie Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.CookieNameTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieNameType()
	 * @generated
	 */
	int COOKIE_NAME_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_NAME_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Cookie Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_NAME_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookiePathTypeImpl <em>Cookie Path Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.CookiePathTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookiePathType()
	 * @generated
	 */
	int COOKIE_PATH_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_PATH_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Cookie Path Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COOKIE_PATH_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.ErrorPageImpl <em>Error Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.ErrorPageImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getErrorPage()
	 * @generated
	 */
	int ERROR_PAGE = 7;

	/**
	 * The feature id for the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PAGE__ERROR_CODE = 0;

	/**
	 * The feature id for the '<em><b>Exception Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PAGE__EXCEPTION_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PAGE__LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PAGE__ID = 3;

	/**
	 * The number of structural features of the '<em>Error Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PAGE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl <em>Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.FilterImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFilter()
	 * @generated
	 */
	int FILTER = 8;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__DISPLAY_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ICONS = 2;

	/**
	 * The feature id for the '<em><b>Filter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__FILTER_NAME = 3;

	/**
	 * The feature id for the '<em><b>Filter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__FILTER_CLASS = 4;

	/**
	 * The feature id for the '<em><b>Async Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ASYNC_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Init Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__INIT_PARAMS = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ID = 7;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl <em>Filter Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFilterMapping()
	 * @generated
	 */
	int FILTER_MAPPING = 9;

	/**
	 * The feature id for the '<em><b>Filter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__FILTER_NAME = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Url Patterns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__URL_PATTERNS = 2;

	/**
	 * The feature id for the '<em><b>Servlet Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__SERVLET_NAMES = 3;

	/**
	 * The feature id for the '<em><b>Dispatchers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__DISPATCHERS = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING__ID = 5;

	/**
	 * The number of structural features of the '<em>Filter Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_MAPPING_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl <em>Form Login Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFormLoginConfig()
	 * @generated
	 */
	int FORM_LOGIN_CONFIG = 10;

	/**
	 * The feature id for the '<em><b>Form Login Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE = 0;

	/**
	 * The feature id for the '<em><b>Form Error Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_LOGIN_CONFIG__FORM_ERROR_PAGE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_LOGIN_CONFIG__ID = 2;

	/**
	 * The number of structural features of the '<em>Form Login Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_LOGIN_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingImpl <em>Locale Encoding Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLocaleEncodingMapping()
	 * @generated
	 */
	int LOCALE_ENCODING_MAPPING = 11;

	/**
	 * The feature id for the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING__LOCALE = 0;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING__ENCODING = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING__ID = 2;

	/**
	 * The number of structural features of the '<em>Locale Encoding Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingListImpl <em>Locale Encoding Mapping List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingListImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLocaleEncodingMappingList()
	 * @generated
	 */
	int LOCALE_ENCODING_MAPPING_LIST = 12;

	/**
	 * The feature id for the '<em><b>Local Encoding Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING_LIST__ID = 1;

	/**
	 * The number of structural features of the '<em>Locale Encoding Mapping List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_ENCODING_MAPPING_LIST_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.LoginConfigImpl <em>Login Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.LoginConfigImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLoginConfig()
	 * @generated
	 */
	int LOGIN_CONFIG = 13;

	/**
	 * The feature id for the '<em><b>Auth Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG__AUTH_METHOD = 0;

	/**
	 * The feature id for the '<em><b>Realm Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG__REALM_NAME = 1;

	/**
	 * The feature id for the '<em><b>Form Login Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG__FORM_LOGIN_CONFIG = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG__ID = 3;

	/**
	 * The number of structural features of the '<em>Login Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGIN_CONFIG_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.MimeMappingImpl <em>Mime Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.MimeMappingImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getMimeMapping()
	 * @generated
	 */
	int MIME_MAPPING = 14;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MIME_MAPPING__EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Mime Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MIME_MAPPING__MIME_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MIME_MAPPING__ID = 2;

	/**
	 * The number of structural features of the '<em>Mime Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MIME_MAPPING_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl <em>Multipart Config Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getMultipartConfigType()
	 * @generated
	 */
	int MULTIPART_CONFIG_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPART_CONFIG_TYPE__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Max File Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE = 1;

	/**
	 * The feature id for the '<em><b>Max Request Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE = 2;

	/**
	 * The feature id for the '<em><b>File Size Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD = 3;

	/**
	 * The number of structural features of the '<em>Multipart Config Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPART_CONFIG_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.OrderingOrderingTypeImpl <em>Ordering Ordering Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.OrderingOrderingTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getOrderingOrderingType()
	 * @generated
	 */
	int ORDERING_ORDERING_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_ORDERING_TYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_ORDERING_TYPE__OTHERS = 1;

	/**
	 * The number of structural features of the '<em>Ordering Ordering Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_ORDERING_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.OrderingOthersTypeImpl <em>Ordering Others Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.OrderingOthersTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getOrderingOthersType()
	 * @generated
	 */
	int ORDERING_OTHERS_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_OTHERS_TYPE__ID = 0;

	/**
	 * The number of structural features of the '<em>Ordering Others Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_OTHERS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.OrderingTypeImpl <em>Ordering Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.OrderingTypeImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getOrderingType()
	 * @generated
	 */
	int ORDERING_TYPE = 18;

	/**
	 * The feature id for the '<em><b>After</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_TYPE__AFTER = 0;

	/**
	 * The feature id for the '<em><b>Before</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_TYPE__BEFORE = 1;

	/**
	 * The number of structural features of the '<em>Ordering Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl <em>Security Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getSecurityConstraint()
	 * @generated
	 */
	int SECURITY_CONSTRAINT = 19;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__DISPLAY_NAMES = 0;

	/**
	 * The feature id for the '<em><b>Web Resource Collections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS = 1;

	/**
	 * The feature id for the '<em><b>Auth Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__AUTH_CONSTRAINT = 2;

	/**
	 * The feature id for the '<em><b>User Data Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__ID = 4;

	/**
	 * The number of structural features of the '<em>Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl <em>Servlet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.ServletImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getServlet()
	 * @generated
	 */
	int SERVLET = 20;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__DISPLAY_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__ICONS = 2;

	/**
	 * The feature id for the '<em><b>Servlet Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__SERVLET_NAME = 3;

	/**
	 * The feature id for the '<em><b>Servlet Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__SERVLET_CLASS = 4;

	/**
	 * The feature id for the '<em><b>Jsp File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__JSP_FILE = 5;

	/**
	 * The feature id for the '<em><b>Init Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__INIT_PARAMS = 6;

	/**
	 * The feature id for the '<em><b>Load On Startup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__LOAD_ON_STARTUP = 7;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__ENABLED = 8;

	/**
	 * The feature id for the '<em><b>Async Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__ASYNC_SUPPORTED = 9;

	/**
	 * The feature id for the '<em><b>Run As</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__RUN_AS = 10;

	/**
	 * The feature id for the '<em><b>Security Role Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__SECURITY_ROLE_REFS = 11;

	/**
	 * The feature id for the '<em><b>Multipart Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__MULTIPART_CONFIG = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET__ID = 13;

	/**
	 * The number of structural features of the '<em>Servlet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl <em>Servlet Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getServletMapping()
	 * @generated
	 */
	int SERVLET_MAPPING = 21;

	/**
	 * The feature id for the '<em><b>Servlet Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_MAPPING__SERVLET_NAME = 0;

	/**
	 * The feature id for the '<em><b>Url Patterns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_MAPPING__URL_PATTERNS = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_MAPPING__ID = 2;

	/**
	 * The number of structural features of the '<em>Servlet Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVLET_MAPPING_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl <em>Session Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getSessionConfig()
	 * @generated
	 */
	int SESSION_CONFIG = 22;

	/**
	 * The feature id for the '<em><b>Session Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_CONFIG__SESSION_TIMEOUT = 0;

	/**
	 * The feature id for the '<em><b>Cookie Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_CONFIG__COOKIE_CONFIG = 1;

	/**
	 * The feature id for the '<em><b>Tracking Mode</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_CONFIG__TRACKING_MODE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_CONFIG__ID = 3;

	/**
	 * The number of structural features of the '<em>Session Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_CONFIG_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl <em>User Data Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getUserDataConstraint()
	 * @generated
	 */
	int USER_DATA_CONSTRAINT = 23;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_CONSTRAINT__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Transport Guarantee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_CONSTRAINT__ID = 2;

	/**
	 * The number of structural features of the '<em>User Data Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_CONSTRAINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl <em>App</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebAppImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebApp()
	 * @generated
	 */
	int WEB_APP = 24;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Module Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MODULE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DESCRIPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DISPLAY_NAMES = 3;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ICONS = 4;

	/**
	 * The feature id for the '<em><b>Distributables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DISTRIBUTABLES = 5;

	/**
	 * The feature id for the '<em><b>Context Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__CONTEXT_PARAMS = 6;

	/**
	 * The feature id for the '<em><b>Filters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__FILTERS = 7;

	/**
	 * The feature id for the '<em><b>Filter Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__FILTER_MAPPINGS = 8;

	/**
	 * The feature id for the '<em><b>Listeners</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__LISTENERS = 9;

	/**
	 * The feature id for the '<em><b>Servlets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SERVLETS = 10;

	/**
	 * The feature id for the '<em><b>Servlet Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SERVLET_MAPPINGS = 11;

	/**
	 * The feature id for the '<em><b>Session Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SESSION_CONFIGS = 12;

	/**
	 * The feature id for the '<em><b>Mime Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MIME_MAPPINGS = 13;

	/**
	 * The feature id for the '<em><b>Welcome File Lists</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__WELCOME_FILE_LISTS = 14;

	/**
	 * The feature id for the '<em><b>Error Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ERROR_PAGES = 15;

	/**
	 * The feature id for the '<em><b>Jsp Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__JSP_CONFIGS = 16;

	/**
	 * The feature id for the '<em><b>Security Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SECURITY_CONSTRAINTS = 17;

	/**
	 * The feature id for the '<em><b>Login Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__LOGIN_CONFIGS = 18;

	/**
	 * The feature id for the '<em><b>Security Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SECURITY_ROLES = 19;

	/**
	 * The feature id for the '<em><b>Env Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ENV_ENTRIES = 20;

	/**
	 * The feature id for the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__EJB_REFS = 21;

	/**
	 * The feature id for the '<em><b>Ejb Local Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__EJB_LOCAL_REFS = 22;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__SERVICE_REFS = 23;

	/**
	 * The feature id for the '<em><b>Resource Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__RESOURCE_REFS = 24;

	/**
	 * The feature id for the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__RESOURCE_ENV_REFS = 25;

	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MESSAGE_DESTINATION_REFS = 26;

	/**
	 * The feature id for the '<em><b>Persistence Context Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__PERSISTENCE_CONTEXT_REFS = 27;

	/**
	 * The feature id for the '<em><b>Persistence Unit Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__PERSISTENCE_UNIT_REFS = 28;

	/**
	 * The feature id for the '<em><b>Post Constructs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__POST_CONSTRUCTS = 29;

	/**
	 * The feature id for the '<em><b>Pre Destroys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__PRE_DESTROYS = 30;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DATA_SOURCE = 31;

	/**
	 * The feature id for the '<em><b>Jms Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__JMS_CONNECTION_FACTORY = 32;

	/**
	 * The feature id for the '<em><b>Jms Destination</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__JMS_DESTINATION = 33;

	/**
	 * The feature id for the '<em><b>Mail Session</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MAIL_SESSION = 34;

	/**
	 * The feature id for the '<em><b>Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__CONNECTION_FACTORY = 35;

	/**
	 * The feature id for the '<em><b>Administered Object</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ADMINISTERED_OBJECT = 36;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__MESSAGE_DESTINATIONS = 37;

	/**
	 * The feature id for the '<em><b>Local Encoding Mappings Lists</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS = 38;

	/**
	 * The feature id for the '<em><b>Deny Uncovered Http Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__DENY_UNCOVERED_HTTP_METHODS = 39;

	/**
	 * The feature id for the '<em><b>Absolute Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ABSOLUTE_ORDERING = 40;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__ID = 41;

	/**
	 * The feature id for the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__METADATA_COMPLETE = 42;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP__VERSION = 43;

	/**
	 * The number of structural features of the '<em>App</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_FEATURE_COUNT = 44;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebAppDeploymentDescriptorImpl <em>App Deployment Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebAppDeploymentDescriptorImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebAppDeploymentDescriptor()
	 * @generated
	 */
	int WEB_APP_DEPLOYMENT_DESCRIPTOR = 25;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Web App</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_APP = 3;

	/**
	 * The number of structural features of the '<em>App Deployment Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_APP_DEPLOYMENT_DESCRIPTOR_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebFragmentImpl <em>Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebFragmentImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebFragment()
	 * @generated
	 */
	int WEB_FRAGMENT = 26;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__DESCRIPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__DISPLAY_NAMES = 3;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__ICONS = 4;

	/**
	 * The feature id for the '<em><b>Distributables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__DISTRIBUTABLES = 5;

	/**
	 * The feature id for the '<em><b>Context Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__CONTEXT_PARAMS = 6;

	/**
	 * The feature id for the '<em><b>Filters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__FILTERS = 7;

	/**
	 * The feature id for the '<em><b>Filter Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__FILTER_MAPPINGS = 8;

	/**
	 * The feature id for the '<em><b>Listeners</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__LISTENERS = 9;

	/**
	 * The feature id for the '<em><b>Servlets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__SERVLETS = 10;

	/**
	 * The feature id for the '<em><b>Servlet Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__SERVLET_MAPPINGS = 11;

	/**
	 * The feature id for the '<em><b>Session Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__SESSION_CONFIGS = 12;

	/**
	 * The feature id for the '<em><b>Mime Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__MIME_MAPPINGS = 13;

	/**
	 * The feature id for the '<em><b>Welcome File Lists</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__WELCOME_FILE_LISTS = 14;

	/**
	 * The feature id for the '<em><b>Error Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__ERROR_PAGES = 15;

	/**
	 * The feature id for the '<em><b>Jsp Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__JSP_CONFIGS = 16;

	/**
	 * The feature id for the '<em><b>Security Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__SECURITY_CONSTRAINTS = 17;

	/**
	 * The feature id for the '<em><b>Login Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__LOGIN_CONFIGS = 18;

	/**
	 * The feature id for the '<em><b>Security Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__SECURITY_ROLES = 19;

	/**
	 * The feature id for the '<em><b>Env Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__ENV_ENTRIES = 20;

	/**
	 * The feature id for the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__EJB_REFS = 21;

	/**
	 * The feature id for the '<em><b>Ejb Local Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__EJB_LOCAL_REFS = 22;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__SERVICE_REFS = 23;

	/**
	 * The feature id for the '<em><b>Resource Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__RESOURCE_REFS = 24;

	/**
	 * The feature id for the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__RESOURCE_ENV_REFS = 25;

	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__MESSAGE_DESTINATION_REFS = 26;

	/**
	 * The feature id for the '<em><b>Persistence Context Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__PERSISTENCE_CONTEXT_REFS = 27;

	/**
	 * The feature id for the '<em><b>Persistence Unit Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__PERSISTENCE_UNIT_REFS = 28;

	/**
	 * The feature id for the '<em><b>Post Constructs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__POST_CONSTRUCTS = 29;

	/**
	 * The feature id for the '<em><b>Pre Destroys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__PRE_DESTROYS = 30;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__DATA_SOURCE = 31;

	/**
	 * The feature id for the '<em><b>Jms Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__JMS_CONNECTION_FACTORY = 32;

	/**
	 * The feature id for the '<em><b>Jms Destination</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__JMS_DESTINATION = 33;

	/**
	 * The feature id for the '<em><b>Mail Session</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__MAIL_SESSION = 34;

	/**
	 * The feature id for the '<em><b>Connection Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__CONNECTION_FACTORY = 35;

	/**
	 * The feature id for the '<em><b>Administered Object</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__ADMINISTERED_OBJECT = 36;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__MESSAGE_DESTINATIONS = 37;

	/**
	 * The feature id for the '<em><b>Local Encoding Mappings Lists</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__LOCAL_ENCODING_MAPPINGS_LISTS = 38;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__ORDERING = 39;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__ID = 40;

	/**
	 * The feature id for the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__METADATA_COMPLETE = 41;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT__VERSION = 42;

	/**
	 * The number of structural features of the '<em>Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_FRAGMENT_FEATURE_COUNT = 43;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl <em>Resource Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebResourceCollection()
	 * @generated
	 */
	int WEB_RESOURCE_COLLECTION = 27;

	/**
	 * The feature id for the '<em><b>Web Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__DESCRIPTIONS = 1;

	/**
	 * The feature id for the '<em><b>Url Patterns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__URL_PATTERNS = 2;

	/**
	 * The feature id for the '<em><b>Http Methods</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__HTTP_METHODS = 3;

	/**
	 * The feature id for the '<em><b>Http Method Omission</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION__ID = 5;

	/**
	 * The number of structural features of the '<em>Resource Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_RESOURCE_COLLECTION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.internal.impl.WelcomeFileListImpl <em>Welcome File List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.internal.impl.WelcomeFileListImpl
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWelcomeFileList()
	 * @generated
	 */
	int WELCOME_FILE_LIST = 28;

	/**
	 * The feature id for the '<em><b>Welcome Files</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WELCOME_FILE_LIST__WELCOME_FILES = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WELCOME_FILE_LIST__ID = 1;

	/**
	 * The number of structural features of the '<em>Welcome File List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WELCOME_FILE_LIST_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.DispatcherType <em>Dispatcher Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.DispatcherType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getDispatcherType()
	 * @generated
	 */
	int DISPATCHER_TYPE = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.NullCharType <em>Null Char Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.NullCharType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getNullCharType()
	 * @generated
	 */
	int NULL_CHAR_TYPE = 30;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.TrackingModeType <em>Tracking Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.TrackingModeType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTrackingModeType()
	 * @generated
	 */
	int TRACKING_MODE_TYPE = 31;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.TransportGuaranteeType <em>Transport Guarantee Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTransportGuaranteeType()
	 * @generated
	 */
	int TRANSPORT_GUARANTEE_TYPE = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.web.WebAppVersionType <em>App Version Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.WebAppVersionType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebAppVersionType()
	 * @generated
	 */
	int WEB_APP_VERSION_TYPE = 33;

	/**
	 * The meta object id for the '<em>Auth Method Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getAuthMethodType()
	 * @generated
	 */
	int AUTH_METHOD_TYPE = 34;

	/**
	 * The meta object id for the '<em>Dispatcher Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.DispatcherType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getDispatcherTypeObject()
	 * @generated
	 */
	int DISPATCHER_TYPE_OBJECT = 35;

	/**
	 * The meta object id for the '<em>Encoding Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getEncodingType()
	 * @generated
	 */
	int ENCODING_TYPE = 36;

	/**
	 * The meta object id for the '<em>Error Code Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.math.BigInteger
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getErrorCodeType()
	 * @generated
	 */
	int ERROR_CODE_TYPE = 37;

	/**
	 * The meta object id for the '<em>Filter Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFilterNameType()
	 * @generated
	 */
	int FILTER_NAME_TYPE = 38;

	/**
	 * The meta object id for the '<em>Http Method Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getHttpMethodType()
	 * @generated
	 */
	int HTTP_METHOD_TYPE = 39;

	/**
	 * The meta object id for the '<em>Load On Startup Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLoadOnStartupType()
	 * @generated
	 */
	int LOAD_ON_STARTUP_TYPE = 40;

	/**
	 * The meta object id for the '<em>Locale Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLocaleType()
	 * @generated
	 */
	int LOCALE_TYPE = 41;

	/**
	 * The meta object id for the '<em>Mime Type Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getMimeTypeType()
	 * @generated
	 */
	int MIME_TYPE_TYPE = 42;

	/**
	 * The meta object id for the '<em>Non Empty String Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getNonEmptyStringType()
	 * @generated
	 */
	int NON_EMPTY_STRING_TYPE = 43;

	/**
	 * The meta object id for the '<em>Null Char Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.NullCharType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getNullCharTypeObject()
	 * @generated
	 */
	int NULL_CHAR_TYPE_OBJECT = 44;

	/**
	 * The meta object id for the '<em>Servlet Name Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getServletNameType()
	 * @generated
	 */
	int SERVLET_NAME_TYPE = 45;

	/**
	 * The meta object id for the '<em>Tracking Mode Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.TrackingModeType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTrackingModeTypeObject()
	 * @generated
	 */
	int TRACKING_MODE_TYPE_OBJECT = 46;

	/**
	 * The meta object id for the '<em>Transport Guarantee Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTransportGuaranteeTypeObject()
	 * @generated
	 */
	int TRANSPORT_GUARANTEE_TYPE_OBJECT = 47;

	/**
	 * The meta object id for the '<em>War Path Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWarPathType()
	 * @generated
	 */
	int WAR_PATH_TYPE = 48;

	/**
	 * The meta object id for the '<em>App Version Type Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.web.WebAppVersionType
	 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebAppVersionTypeObject()
	 * @generated
	 */
	int WEB_APP_VERSION_TYPE_OBJECT = 49;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType <em>Absolute Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Absolute Ordering Type</em>'.
	 * @see org.eclipse.jst.javaee.web.AbsoluteOrderingType
	 * @generated
	 */
	EClass getAbsoluteOrderingType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.jst.javaee.web.AbsoluteOrderingType#getGroup()
	 * @see #getAbsoluteOrderingType()
	 * @generated
	 */
	EAttribute getAbsoluteOrderingType_Group();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Name</em>'.
	 * @see org.eclipse.jst.javaee.web.AbsoluteOrderingType#getName()
	 * @see #getAbsoluteOrderingType()
	 * @generated
	 */
	EAttribute getAbsoluteOrderingType_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType#getOthers <em>Others</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Others</em>'.
	 * @see org.eclipse.jst.javaee.web.AbsoluteOrderingType#getOthers()
	 * @see #getAbsoluteOrderingType()
	 * @generated
	 */
	EReference getAbsoluteOrderingType_Others();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.AuthConstraint <em>Auth Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Auth Constraint</em>'.
	 * @see org.eclipse.jst.javaee.web.AuthConstraint
	 * @generated
	 */
	EClass getAuthConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.AuthConstraint#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.AuthConstraint#getDescriptions()
	 * @see #getAuthConstraint()
	 * @generated
	 */
	EReference getAuthConstraint_Descriptions();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.AuthConstraint#getRoleNames <em>Role Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Role Names</em>'.
	 * @see org.eclipse.jst.javaee.web.AuthConstraint#getRoleNames()
	 * @see #getAuthConstraint()
	 * @generated
	 */
	EAttribute getAuthConstraint_RoleNames();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.AuthConstraint#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.AuthConstraint#getId()
	 * @see #getAuthConstraint()
	 * @generated
	 */
	EAttribute getAuthConstraint_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.CookieCommentType <em>Cookie Comment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cookie Comment Type</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieCommentType
	 * @generated
	 */
	EClass getCookieCommentType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieCommentType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieCommentType#getValue()
	 * @see #getCookieCommentType()
	 * @generated
	 */
	EAttribute getCookieCommentType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.CookieConfigType <em>Cookie Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cookie Config Type</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType
	 * @generated
	 */
	EClass getCookieConfigType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.CookieConfigType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#getName()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EReference getCookieConfigType_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.CookieConfigType#getDomain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Domain</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#getDomain()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EReference getCookieConfigType_Domain();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.CookieConfigType#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Path</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#getPath()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EReference getCookieConfigType_Path();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.CookieConfigType#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Comment</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#getComment()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EReference getCookieConfigType_Comment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieConfigType#isHttpOnly <em>Http Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Only</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#isHttpOnly()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EAttribute getCookieConfigType_HttpOnly();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieConfigType#isSecure <em>Secure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secure</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#isSecure()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EAttribute getCookieConfigType_Secure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieConfigType#getMaxAge <em>Max Age</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Age</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#getMaxAge()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EAttribute getCookieConfigType_MaxAge();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieConfigType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieConfigType#getId()
	 * @see #getCookieConfigType()
	 * @generated
	 */
	EAttribute getCookieConfigType_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.CookieDomainType <em>Cookie Domain Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cookie Domain Type</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieDomainType
	 * @generated
	 */
	EClass getCookieDomainType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieDomainType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieDomainType#getValue()
	 * @see #getCookieDomainType()
	 * @generated
	 */
	EAttribute getCookieDomainType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.CookieNameType <em>Cookie Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cookie Name Type</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieNameType
	 * @generated
	 */
	EClass getCookieNameType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookieNameType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.javaee.web.CookieNameType#getValue()
	 * @see #getCookieNameType()
	 * @generated
	 */
	EAttribute getCookieNameType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.CookiePathType <em>Cookie Path Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cookie Path Type</em>'.
	 * @see org.eclipse.jst.javaee.web.CookiePathType
	 * @generated
	 */
	EClass getCookiePathType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.CookiePathType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.javaee.web.CookiePathType#getValue()
	 * @see #getCookiePathType()
	 * @generated
	 */
	EAttribute getCookiePathType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.ErrorPage <em>Error Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error Page</em>'.
	 * @see org.eclipse.jst.javaee.web.ErrorPage
	 * @generated
	 */
	EClass getErrorPage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.ErrorPage#getErrorCode <em>Error Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Code</em>'.
	 * @see org.eclipse.jst.javaee.web.ErrorPage#getErrorCode()
	 * @see #getErrorPage()
	 * @generated
	 */
	EAttribute getErrorPage_ErrorCode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.ErrorPage#getExceptionType <em>Exception Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exception Type</em>'.
	 * @see org.eclipse.jst.javaee.web.ErrorPage#getExceptionType()
	 * @see #getErrorPage()
	 * @generated
	 */
	EAttribute getErrorPage_ExceptionType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.ErrorPage#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.jst.javaee.web.ErrorPage#getLocation()
	 * @see #getErrorPage()
	 * @generated
	 */
	EAttribute getErrorPage_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.ErrorPage#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.ErrorPage#getId()
	 * @see #getErrorPage()
	 * @generated
	 */
	EAttribute getErrorPage_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter
	 * @generated
	 */
	EClass getFilter();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Filter#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getDescriptions()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Filter#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getDisplayNames()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Filter#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getIcons()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_Icons();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Filter#getFilterName <em>Filter Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Name</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getFilterName()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_FilterName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Filter#getFilterClass <em>Filter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Class</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getFilterClass()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_FilterClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Filter#isAsyncSupported <em>Async Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async Supported</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#isAsyncSupported()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_AsyncSupported();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Filter#getInitParams <em>Init Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Init Params</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getInitParams()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_InitParams();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Filter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.Filter#getId()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.FilterMapping <em>Filter Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Mapping</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping
	 * @generated
	 */
	EClass getFilterMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.FilterMapping#getFilterName <em>Filter Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filter Name</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping#getFilterName()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EAttribute getFilterMapping_FilterName();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.FilterMapping#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping#getGroup()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EAttribute getFilterMapping_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.FilterMapping#getUrlPatterns <em>Url Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Url Patterns</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping#getUrlPatterns()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EReference getFilterMapping_UrlPatterns();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.FilterMapping#getServletNames <em>Servlet Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Servlet Names</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping#getServletNames()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EAttribute getFilterMapping_ServletNames();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.FilterMapping#getDispatchers <em>Dispatchers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Dispatchers</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping#getDispatchers()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EAttribute getFilterMapping_Dispatchers();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.FilterMapping#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.FilterMapping#getId()
	 * @see #getFilterMapping()
	 * @generated
	 */
	EAttribute getFilterMapping_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.FormLoginConfig <em>Form Login Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Login Config</em>'.
	 * @see org.eclipse.jst.javaee.web.FormLoginConfig
	 * @generated
	 */
	EClass getFormLoginConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.FormLoginConfig#getFormLoginPage <em>Form Login Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Form Login Page</em>'.
	 * @see org.eclipse.jst.javaee.web.FormLoginConfig#getFormLoginPage()
	 * @see #getFormLoginConfig()
	 * @generated
	 */
	EAttribute getFormLoginConfig_FormLoginPage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.FormLoginConfig#getFormErrorPage <em>Form Error Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Form Error Page</em>'.
	 * @see org.eclipse.jst.javaee.web.FormLoginConfig#getFormErrorPage()
	 * @see #getFormLoginConfig()
	 * @generated
	 */
	EAttribute getFormLoginConfig_FormErrorPage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.FormLoginConfig#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.FormLoginConfig#getId()
	 * @see #getFormLoginConfig()
	 * @generated
	 */
	EAttribute getFormLoginConfig_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping <em>Locale Encoding Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locale Encoding Mapping</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMapping
	 * @generated
	 */
	EClass getLocaleEncodingMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getLocale <em>Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Locale</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMapping#getLocale()
	 * @see #getLocaleEncodingMapping()
	 * @generated
	 */
	EAttribute getLocaleEncodingMapping_Locale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMapping#getEncoding()
	 * @see #getLocaleEncodingMapping()
	 * @generated
	 */
	EAttribute getLocaleEncodingMapping_Encoding();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMapping#getId()
	 * @see #getLocaleEncodingMapping()
	 * @generated
	 */
	EAttribute getLocaleEncodingMapping_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList <em>Locale Encoding Mapping List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locale Encoding Mapping List</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMappingList
	 * @generated
	 */
	EClass getLocaleEncodingMappingList();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getLocalEncodingMappings <em>Local Encoding Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Local Encoding Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getLocalEncodingMappings()
	 * @see #getLocaleEncodingMappingList()
	 * @generated
	 */
	EReference getLocaleEncodingMappingList_LocalEncodingMappings();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getId()
	 * @see #getLocaleEncodingMappingList()
	 * @generated
	 */
	EAttribute getLocaleEncodingMappingList_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.LoginConfig <em>Login Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Login Config</em>'.
	 * @see org.eclipse.jst.javaee.web.LoginConfig
	 * @generated
	 */
	EClass getLoginConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LoginConfig#getAuthMethod <em>Auth Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auth Method</em>'.
	 * @see org.eclipse.jst.javaee.web.LoginConfig#getAuthMethod()
	 * @see #getLoginConfig()
	 * @generated
	 */
	EAttribute getLoginConfig_AuthMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LoginConfig#getRealmName <em>Realm Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Realm Name</em>'.
	 * @see org.eclipse.jst.javaee.web.LoginConfig#getRealmName()
	 * @see #getLoginConfig()
	 * @generated
	 */
	EAttribute getLoginConfig_RealmName();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.LoginConfig#getFormLoginConfig <em>Form Login Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Form Login Config</em>'.
	 * @see org.eclipse.jst.javaee.web.LoginConfig#getFormLoginConfig()
	 * @see #getLoginConfig()
	 * @generated
	 */
	EReference getLoginConfig_FormLoginConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.LoginConfig#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.LoginConfig#getId()
	 * @see #getLoginConfig()
	 * @generated
	 */
	EAttribute getLoginConfig_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.MimeMapping <em>Mime Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mime Mapping</em>'.
	 * @see org.eclipse.jst.javaee.web.MimeMapping
	 * @generated
	 */
	EClass getMimeMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MimeMapping#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension</em>'.
	 * @see org.eclipse.jst.javaee.web.MimeMapping#getExtension()
	 * @see #getMimeMapping()
	 * @generated
	 */
	EAttribute getMimeMapping_Extension();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MimeMapping#getMimeType <em>Mime Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mime Type</em>'.
	 * @see org.eclipse.jst.javaee.web.MimeMapping#getMimeType()
	 * @see #getMimeMapping()
	 * @generated
	 */
	EAttribute getMimeMapping_MimeType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MimeMapping#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.MimeMapping#getId()
	 * @see #getMimeMapping()
	 * @generated
	 */
	EAttribute getMimeMapping_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.MultipartConfigType <em>Multipart Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multipart Config Type</em>'.
	 * @see org.eclipse.jst.javaee.web.MultipartConfigType
	 * @generated
	 */
	EClass getMultipartConfigType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.jst.javaee.web.MultipartConfigType#getLocation()
	 * @see #getMultipartConfigType()
	 * @generated
	 */
	EAttribute getMultipartConfigType_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxFileSize <em>Max File Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max File Size</em>'.
	 * @see org.eclipse.jst.javaee.web.MultipartConfigType#getMaxFileSize()
	 * @see #getMultipartConfigType()
	 * @generated
	 */
	EAttribute getMultipartConfigType_MaxFileSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxRequestSize <em>Max Request Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Request Size</em>'.
	 * @see org.eclipse.jst.javaee.web.MultipartConfigType#getMaxRequestSize()
	 * @see #getMultipartConfigType()
	 * @generated
	 */
	EAttribute getMultipartConfigType_MaxRequestSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getFileSizeThreshold <em>File Size Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Size Threshold</em>'.
	 * @see org.eclipse.jst.javaee.web.MultipartConfigType#getFileSizeThreshold()
	 * @see #getMultipartConfigType()
	 * @generated
	 */
	EAttribute getMultipartConfigType_FileSizeThreshold();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.OrderingOrderingType <em>Ordering Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordering Ordering Type</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingOrderingType
	 * @generated
	 */
	EClass getOrderingOrderingType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.OrderingOrderingType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Name</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingOrderingType#getName()
	 * @see #getOrderingOrderingType()
	 * @generated
	 */
	EAttribute getOrderingOrderingType_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.OrderingOrderingType#getOthers <em>Others</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Others</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingOrderingType#getOthers()
	 * @see #getOrderingOrderingType()
	 * @generated
	 */
	EReference getOrderingOrderingType_Others();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.OrderingOthersType <em>Ordering Others Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordering Others Type</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingOthersType
	 * @generated
	 */
	EClass getOrderingOthersType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.OrderingOthersType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingOthersType#getId()
	 * @see #getOrderingOthersType()
	 * @generated
	 */
	EAttribute getOrderingOthersType_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.OrderingType <em>Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordering Type</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingType
	 * @generated
	 */
	EClass getOrderingType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.OrderingType#getAfter <em>After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>After</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingType#getAfter()
	 * @see #getOrderingType()
	 * @generated
	 */
	EReference getOrderingType_After();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.OrderingType#getBefore <em>Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Before</em>'.
	 * @see org.eclipse.jst.javaee.web.OrderingType#getBefore()
	 * @see #getOrderingType()
	 * @generated
	 */
	EReference getOrderingType_Before();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.SecurityConstraint <em>Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Constraint</em>'.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint
	 * @generated
	 */
	EClass getSecurityConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint#getDisplayNames()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EReference getSecurityConstraint_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getWebResourceCollections <em>Web Resource Collections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Web Resource Collections</em>'.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint#getWebResourceCollections()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EReference getSecurityConstraint_WebResourceCollections();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getAuthConstraint <em>Auth Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Auth Constraint</em>'.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint#getAuthConstraint()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EReference getSecurityConstraint_AuthConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getUserDataConstraint <em>User Data Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>User Data Constraint</em>'.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint#getUserDataConstraint()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EReference getSecurityConstraint_UserDataConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.SecurityConstraint#getId()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EAttribute getSecurityConstraint_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.Servlet <em>Servlet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Servlet</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet
	 * @generated
	 */
	EClass getServlet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Servlet#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getDescriptions()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Servlet#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getDisplayNames()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Servlet#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getIcons()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_Icons();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#getServletName <em>Servlet Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Servlet Name</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getServletName()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_ServletName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#getServletClass <em>Servlet Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Servlet Class</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getServletClass()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_ServletClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#getJspFile <em>Jsp File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jsp File</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getJspFile()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_JspFile();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Servlet#getInitParams <em>Init Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Init Params</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getInitParams()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_InitParams();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#getLoadOnStartup <em>Load On Startup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load On Startup</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getLoadOnStartup()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_LoadOnStartup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#isEnabled()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#isAsyncSupported <em>Async Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Async Supported</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#isAsyncSupported()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_AsyncSupported();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.Servlet#getRunAs <em>Run As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Run As</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getRunAs()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_RunAs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.Servlet#getSecurityRoleRefs <em>Security Role Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Role Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getSecurityRoleRefs()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_SecurityRoleRefs();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.Servlet#getMultipartConfig <em>Multipart Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Multipart Config</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getMultipartConfig()
	 * @see #getServlet()
	 * @generated
	 */
	EReference getServlet_MultipartConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.Servlet#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.Servlet#getId()
	 * @see #getServlet()
	 * @generated
	 */
	EAttribute getServlet_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.ServletMapping <em>Servlet Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Servlet Mapping</em>'.
	 * @see org.eclipse.jst.javaee.web.ServletMapping
	 * @generated
	 */
	EClass getServletMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.ServletMapping#getServletName <em>Servlet Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Servlet Name</em>'.
	 * @see org.eclipse.jst.javaee.web.ServletMapping#getServletName()
	 * @see #getServletMapping()
	 * @generated
	 */
	EAttribute getServletMapping_ServletName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.ServletMapping#getUrlPatterns <em>Url Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Url Patterns</em>'.
	 * @see org.eclipse.jst.javaee.web.ServletMapping#getUrlPatterns()
	 * @see #getServletMapping()
	 * @generated
	 */
	EReference getServletMapping_UrlPatterns();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.ServletMapping#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.ServletMapping#getId()
	 * @see #getServletMapping()
	 * @generated
	 */
	EAttribute getServletMapping_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.SessionConfig <em>Session Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session Config</em>'.
	 * @see org.eclipse.jst.javaee.web.SessionConfig
	 * @generated
	 */
	EClass getSessionConfig();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.SessionConfig#getSessionTimeout <em>Session Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Session Timeout</em>'.
	 * @see org.eclipse.jst.javaee.web.SessionConfig#getSessionTimeout()
	 * @see #getSessionConfig()
	 * @generated
	 */
	EAttribute getSessionConfig_SessionTimeout();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.SessionConfig#getCookieConfig <em>Cookie Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cookie Config</em>'.
	 * @see org.eclipse.jst.javaee.web.SessionConfig#getCookieConfig()
	 * @see #getSessionConfig()
	 * @generated
	 */
	EReference getSessionConfig_CookieConfig();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.SessionConfig#getTrackingMode <em>Tracking Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tracking Mode</em>'.
	 * @see org.eclipse.jst.javaee.web.SessionConfig#getTrackingMode()
	 * @see #getSessionConfig()
	 * @generated
	 */
	EAttribute getSessionConfig_TrackingMode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.SessionConfig#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.SessionConfig#getId()
	 * @see #getSessionConfig()
	 * @generated
	 */
	EAttribute getSessionConfig_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.UserDataConstraint <em>User Data Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Data Constraint</em>'.
	 * @see org.eclipse.jst.javaee.web.UserDataConstraint
	 * @generated
	 */
	EClass getUserDataConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.UserDataConstraint#getDescriptions()
	 * @see #getUserDataConstraint()
	 * @generated
	 */
	EReference getUserDataConstraint_Descriptions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transport Guarantee</em>'.
	 * @see org.eclipse.jst.javaee.web.UserDataConstraint#getTransportGuarantee()
	 * @see #getUserDataConstraint()
	 * @generated
	 */
	EAttribute getUserDataConstraint_TransportGuarantee();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.UserDataConstraint#getId()
	 * @see #getUserDataConstraint()
	 * @generated
	 */
	EAttribute getUserDataConstraint_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.WebApp <em>App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>App</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp
	 * @generated
	 */
	EClass getWebApp();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebApp#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getGroup()
	 * @see #getWebApp()
	 * @generated
	 */
	EAttribute getWebApp_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getDescriptions()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getDisplayNames()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getIcons()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_Icons();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getDistributables <em>Distributables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Distributables</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getDistributables()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_Distributables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getContextParams <em>Context Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context Params</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getContextParams()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ContextParams();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getFilters <em>Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filters</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getFilters()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_Filters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getFilterMappings <em>Filter Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filter Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getFilterMappings()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_FilterMappings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getListeners <em>Listeners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Listeners</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getListeners()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_Listeners();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getServlets <em>Servlets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Servlets</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getServlets()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_Servlets();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getServletMappings <em>Servlet Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Servlet Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getServletMappings()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ServletMappings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getSessionConfigs <em>Session Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Session Configs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getSessionConfigs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_SessionConfigs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getMimeMappings <em>Mime Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mime Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getMimeMappings()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_MimeMappings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getWelcomeFileLists <em>Welcome File Lists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Welcome File Lists</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getWelcomeFileLists()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_WelcomeFileLists();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getErrorPages <em>Error Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Error Pages</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getErrorPages()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ErrorPages();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getJspConfigs <em>Jsp Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jsp Configs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getJspConfigs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_JspConfigs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getSecurityConstraints <em>Security Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Constraints</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getSecurityConstraints()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_SecurityConstraints();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getLoginConfigs <em>Login Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Login Configs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getLoginConfigs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_LoginConfigs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getSecurityRoles <em>Security Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Roles</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getSecurityRoles()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_SecurityRoles();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getEnvEntries <em>Env Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Env Entries</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getEnvEntries()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_EnvEntries();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getEjbRefs <em>Ejb Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getEjbRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_EjbRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getEjbLocalRefs <em>Ejb Local Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Local Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getEjbLocalRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_EjbLocalRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getServiceRefs <em>Service Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getServiceRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ServiceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getResourceRefs <em>Resource Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getResourceRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ResourceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getResourceEnvRefs <em>Resource Env Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Env Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getResourceEnvRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ResourceEnvRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getMessageDestinationRefs <em>Message Destination Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getMessageDestinationRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_MessageDestinationRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getPersistenceContextRefs <em>Persistence Context Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Context Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getPersistenceContextRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_PersistenceContextRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Unit Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getPersistenceUnitRefs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_PersistenceUnitRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getPostConstructs <em>Post Constructs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Post Constructs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getPostConstructs()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_PostConstructs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getPreDestroys <em>Pre Destroys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pre Destroys</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getPreDestroys()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_PreDestroys();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Source</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getDataSource()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_DataSource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getJmsConnectionFactory <em>Jms Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getJmsConnectionFactory()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_JmsConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getJmsDestination <em>Jms Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Destination</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getJmsDestination()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_JmsDestination();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getMailSession <em>Mail Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Session</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getMailSession()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_MailSession();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getConnectionFactory <em>Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getConnectionFactory()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_ConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getAdministeredObject <em>Administered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Administered Object</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getAdministeredObject()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_AdministeredObject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getMessageDestinations <em>Message Destinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destinations</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getMessageDestinations()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_MessageDestinations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getLocalEncodingMappingsLists <em>Local Encoding Mappings Lists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Local Encoding Mappings Lists</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getLocalEncodingMappingsLists()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_LocalEncodingMappingsLists();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getDenyUncoveredHttpMethods <em>Deny Uncovered Http Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deny Uncovered Http Methods</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getDenyUncoveredHttpMethods()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_DenyUncoveredHttpMethods();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebApp#getModuleName <em>Module Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Module Name</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getModuleName()
	 * @see #getWebApp()
	 * @generated
	 */
	EAttribute getWebApp_ModuleName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebApp#getAbsoluteOrdering <em>Absolute Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Absolute Ordering</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getAbsoluteOrdering()
	 * @see #getWebApp()
	 * @generated
	 */
	EReference getWebApp_AbsoluteOrdering();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebApp#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getId()
	 * @see #getWebApp()
	 * @generated
	 */
	EAttribute getWebApp_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebApp#isMetadataComplete <em>Metadata Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metadata Complete</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#isMetadataComplete()
	 * @see #getWebApp()
	 * @generated
	 */
	EAttribute getWebApp_MetadataComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebApp#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.javaee.web.WebApp#getVersion()
	 * @see #getWebApp()
	 * @generated
	 */
	EAttribute getWebApp_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor <em>App Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>App Deployment Descriptor</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor
	 * @generated
	 */
	EClass getWebAppDeploymentDescriptor();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getMixed()
	 * @see #getWebAppDeploymentDescriptor()
	 * @generated
	 */
	EAttribute getWebAppDeploymentDescriptor_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getXMLNSPrefixMap()
	 * @see #getWebAppDeploymentDescriptor()
	 * @generated
	 */
	EReference getWebAppDeploymentDescriptor_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getXSISchemaLocation()
	 * @see #getWebAppDeploymentDescriptor()
	 * @generated
	 */
	EReference getWebAppDeploymentDescriptor_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getWebApp <em>Web App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Web App</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor#getWebApp()
	 * @see #getWebAppDeploymentDescriptor()
	 * @generated
	 */
	EReference getWebAppDeploymentDescriptor_WebApp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.WebFragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment
	 * @generated
	 */
	EClass getWebFragment();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebFragment#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getGroup()
	 * @see #getWebFragment()
	 * @generated
	 */
	EAttribute getWebFragment_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getDescriptions()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getDisplayNames()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getIcons()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Icons();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebFragment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Name</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getName()
	 * @see #getWebFragment()
	 * @generated
	 */
	EAttribute getWebFragment_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getDistributables <em>Distributables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Distributables</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getDistributables()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Distributables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getContextParams <em>Context Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context Params</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getContextParams()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ContextParams();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getFilters <em>Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filters</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getFilters()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Filters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getFilterMappings <em>Filter Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filter Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getFilterMappings()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_FilterMappings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getListeners <em>Listeners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Listeners</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getListeners()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Listeners();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getServlets <em>Servlets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Servlets</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getServlets()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Servlets();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getServletMappings <em>Servlet Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Servlet Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getServletMappings()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ServletMappings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getSessionConfigs <em>Session Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Session Configs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getSessionConfigs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_SessionConfigs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getMimeMappings <em>Mime Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mime Mappings</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getMimeMappings()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_MimeMappings();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getWelcomeFileLists <em>Welcome File Lists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Welcome File Lists</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getWelcomeFileLists()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_WelcomeFileLists();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getErrorPages <em>Error Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Error Pages</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getErrorPages()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ErrorPages();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getJspConfigs <em>Jsp Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jsp Configs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getJspConfigs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_JspConfigs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getSecurityConstraints <em>Security Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Constraints</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getSecurityConstraints()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_SecurityConstraints();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getLoginConfigs <em>Login Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Login Configs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getLoginConfigs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_LoginConfigs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getSecurityRoles <em>Security Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Security Roles</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getSecurityRoles()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_SecurityRoles();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getEnvEntries <em>Env Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Env Entries</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getEnvEntries()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_EnvEntries();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getEjbRefs <em>Ejb Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getEjbRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_EjbRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getEjbLocalRefs <em>Ejb Local Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Local Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getEjbLocalRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_EjbLocalRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getServiceRefs <em>Service Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getServiceRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ServiceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getResourceRefs <em>Resource Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getResourceRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ResourceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getResourceEnvRefs <em>Resource Env Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Env Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getResourceEnvRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ResourceEnvRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getMessageDestinationRefs <em>Message Destination Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getMessageDestinationRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_MessageDestinationRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getPersistenceContextRefs <em>Persistence Context Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Context Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getPersistenceContextRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_PersistenceContextRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persistence Unit Refs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getPersistenceUnitRefs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_PersistenceUnitRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getPostConstructs <em>Post Constructs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Post Constructs</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getPostConstructs()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_PostConstructs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getPreDestroys <em>Pre Destroys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pre Destroys</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getPreDestroys()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_PreDestroys();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Source</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getDataSource()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_DataSource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getJmsConnectionFactory <em>Jms Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getJmsConnectionFactory()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_JmsConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getJmsDestination <em>Jms Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jms Destination</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getJmsDestination()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_JmsDestination();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getMailSession <em>Mail Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mail Session</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getMailSession()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_MailSession();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getConnectionFactory <em>Connection Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Factory</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getConnectionFactory()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_ConnectionFactory();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getAdministeredObject <em>Administered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Administered Object</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getAdministeredObject()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_AdministeredObject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getMessageDestinations <em>Message Destinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destinations</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getMessageDestinations()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_MessageDestinations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getLocalEncodingMappingsLists <em>Local Encoding Mappings Lists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Local Encoding Mappings Lists</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getLocalEncodingMappingsLists()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_LocalEncodingMappingsLists();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebFragment#getOrdering <em>Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ordering</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getOrdering()
	 * @see #getWebFragment()
	 * @generated
	 */
	EReference getWebFragment_Ordering();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebFragment#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getId()
	 * @see #getWebFragment()
	 * @generated
	 */
	EAttribute getWebFragment_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebFragment#isMetadataComplete <em>Metadata Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metadata Complete</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#isMetadataComplete()
	 * @see #getWebFragment()
	 * @generated
	 */
	EAttribute getWebFragment_MetadataComplete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebFragment#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.javaee.web.WebFragment#getVersion()
	 * @see #getWebFragment()
	 * @generated
	 */
	EAttribute getWebFragment_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.WebResourceCollection <em>Resource Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Collection</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection
	 * @generated
	 */
	EClass getWebResourceCollection();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getWebResourceName <em>Web Resource Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Web Resource Name</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection#getWebResourceName()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EAttribute getWebResourceCollection_WebResourceName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection#getDescriptions()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EReference getWebResourceCollection_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getUrlPatterns <em>Url Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Url Patterns</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection#getUrlPatterns()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EReference getWebResourceCollection_UrlPatterns();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getHttpMethods <em>Http Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Http Methods</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection#getHttpMethods()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EAttribute getWebResourceCollection_HttpMethods();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getHttpMethodOmission <em>Http Method Omission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Http Method Omission</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection#getHttpMethodOmission()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EAttribute getWebResourceCollection_HttpMethodOmission();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.WebResourceCollection#getId()
	 * @see #getWebResourceCollection()
	 * @generated
	 */
	EAttribute getWebResourceCollection_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.web.WelcomeFileList <em>Welcome File List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Welcome File List</em>'.
	 * @see org.eclipse.jst.javaee.web.WelcomeFileList
	 * @generated
	 */
	EClass getWelcomeFileList();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.web.WelcomeFileList#getWelcomeFiles <em>Welcome Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Welcome Files</em>'.
	 * @see org.eclipse.jst.javaee.web.WelcomeFileList#getWelcomeFiles()
	 * @see #getWelcomeFileList()
	 * @generated
	 */
	EAttribute getWelcomeFileList_WelcomeFiles();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.web.WelcomeFileList#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.web.WelcomeFileList#getId()
	 * @see #getWelcomeFileList()
	 * @generated
	 */
	EAttribute getWelcomeFileList_Id();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.web.DispatcherType <em>Dispatcher Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Dispatcher Type</em>'.
	 * @see org.eclipse.jst.javaee.web.DispatcherType
	 * @generated
	 */
	EEnum getDispatcherType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.web.NullCharType <em>Null Char Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Null Char Type</em>'.
	 * @see org.eclipse.jst.javaee.web.NullCharType
	 * @generated
	 */
	EEnum getNullCharType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.web.TrackingModeType <em>Tracking Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tracking Mode Type</em>'.
	 * @see org.eclipse.jst.javaee.web.TrackingModeType
	 * @generated
	 */
	EEnum getTrackingModeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.web.TransportGuaranteeType <em>Transport Guarantee Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transport Guarantee Type</em>'.
	 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
	 * @generated
	 */
	EEnum getTransportGuaranteeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.javaee.web.WebAppVersionType <em>App Version Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>App Version Type</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppVersionType
	 * @generated
	 */
	EEnum getWebAppVersionType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Auth Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Auth Method Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getAuthMethodType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.web.DispatcherType <em>Dispatcher Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Dispatcher Type Object</em>'.
	 * @see org.eclipse.jst.javaee.web.DispatcherType
	 * @generated
	 */
	EDataType getDispatcherTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Encoding Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Encoding Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getEncodingType();

	/**
	 * Returns the meta object for data type '{@link java.math.BigInteger <em>Error Code Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Error Code Type</em>'.
	 * @see java.math.BigInteger
	 * @generated
	 */
	EDataType getErrorCodeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Filter Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Filter Name Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getFilterNameType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Http Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Http Method Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getHttpMethodType();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Load On Startup Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Load On Startup Type</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	EDataType getLoadOnStartupType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Locale Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Locale Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getLocaleType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Mime Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Mime Type Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getMimeTypeType();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Non Empty String Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Non Empty String Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getNonEmptyStringType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.web.NullCharType <em>Null Char Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Null Char Type Object</em>'.
	 * @see org.eclipse.jst.javaee.web.NullCharType
	 * @generated
	 */
	EDataType getNullCharTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>Servlet Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Servlet Name Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getServletNameType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.web.TrackingModeType <em>Tracking Mode Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tracking Mode Type Object</em>'.
	 * @see org.eclipse.jst.javaee.web.TrackingModeType
	 * @generated
	 */
	EDataType getTrackingModeTypeObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.web.TransportGuaranteeType <em>Transport Guarantee Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Transport Guarantee Type Object</em>'.
	 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
	 * @generated
	 */
	EDataType getTransportGuaranteeTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>War Path Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>War Path Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getWarPathType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.javaee.web.WebAppVersionType <em>App Version Type Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>App Version Type Object</em>'.
	 * @see org.eclipse.jst.javaee.web.WebAppVersionType
	 * @generated
	 */
	EDataType getWebAppVersionTypeObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WebFactory getWebFactory();

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
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl <em>Absolute Ordering Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.AbsoluteOrderingTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getAbsoluteOrderingType()
		 * @generated
		 */
		EClass ABSOLUTE_ORDERING_TYPE = eINSTANCE.getAbsoluteOrderingType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSOLUTE_ORDERING_TYPE__GROUP = eINSTANCE.getAbsoluteOrderingType_Group();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSOLUTE_ORDERING_TYPE__NAME = eINSTANCE.getAbsoluteOrderingType_Name();

		/**
		 * The meta object literal for the '<em><b>Others</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSOLUTE_ORDERING_TYPE__OTHERS = eINSTANCE.getAbsoluteOrderingType_Others();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.AuthConstraintImpl <em>Auth Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.AuthConstraintImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getAuthConstraint()
		 * @generated
		 */
		EClass AUTH_CONSTRAINT = eINSTANCE.getAuthConstraint();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTH_CONSTRAINT__DESCRIPTIONS = eINSTANCE.getAuthConstraint_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Role Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTH_CONSTRAINT__ROLE_NAMES = eINSTANCE.getAuthConstraint_RoleNames();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTH_CONSTRAINT__ID = eINSTANCE.getAuthConstraint_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieCommentTypeImpl <em>Cookie Comment Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.CookieCommentTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieCommentType()
		 * @generated
		 */
		EClass COOKIE_COMMENT_TYPE = eINSTANCE.getCookieCommentType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_COMMENT_TYPE__VALUE = eINSTANCE.getCookieCommentType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl <em>Cookie Config Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieConfigType()
		 * @generated
		 */
		EClass COOKIE_CONFIG_TYPE = eINSTANCE.getCookieConfigType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COOKIE_CONFIG_TYPE__NAME = eINSTANCE.getCookieConfigType_Name();

		/**
		 * The meta object literal for the '<em><b>Domain</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COOKIE_CONFIG_TYPE__DOMAIN = eINSTANCE.getCookieConfigType_Domain();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COOKIE_CONFIG_TYPE__PATH = eINSTANCE.getCookieConfigType_Path();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COOKIE_CONFIG_TYPE__COMMENT = eINSTANCE.getCookieConfigType_Comment();

		/**
		 * The meta object literal for the '<em><b>Http Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_CONFIG_TYPE__HTTP_ONLY = eINSTANCE.getCookieConfigType_HttpOnly();

		/**
		 * The meta object literal for the '<em><b>Secure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_CONFIG_TYPE__SECURE = eINSTANCE.getCookieConfigType_Secure();

		/**
		 * The meta object literal for the '<em><b>Max Age</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_CONFIG_TYPE__MAX_AGE = eINSTANCE.getCookieConfigType_MaxAge();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_CONFIG_TYPE__ID = eINSTANCE.getCookieConfigType_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieDomainTypeImpl <em>Cookie Domain Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.CookieDomainTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieDomainType()
		 * @generated
		 */
		EClass COOKIE_DOMAIN_TYPE = eINSTANCE.getCookieDomainType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_DOMAIN_TYPE__VALUE = eINSTANCE.getCookieDomainType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookieNameTypeImpl <em>Cookie Name Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.CookieNameTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookieNameType()
		 * @generated
		 */
		EClass COOKIE_NAME_TYPE = eINSTANCE.getCookieNameType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_NAME_TYPE__VALUE = eINSTANCE.getCookieNameType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.CookiePathTypeImpl <em>Cookie Path Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.CookiePathTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getCookiePathType()
		 * @generated
		 */
		EClass COOKIE_PATH_TYPE = eINSTANCE.getCookiePathType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COOKIE_PATH_TYPE__VALUE = eINSTANCE.getCookiePathType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.ErrorPageImpl <em>Error Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.ErrorPageImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getErrorPage()
		 * @generated
		 */
		EClass ERROR_PAGE = eINSTANCE.getErrorPage();

		/**
		 * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PAGE__ERROR_CODE = eINSTANCE.getErrorPage_ErrorCode();

		/**
		 * The meta object literal for the '<em><b>Exception Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PAGE__EXCEPTION_TYPE = eINSTANCE.getErrorPage_ExceptionType();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PAGE__LOCATION = eINSTANCE.getErrorPage_Location();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PAGE__ID = eINSTANCE.getErrorPage_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.FilterImpl <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.FilterImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFilter()
		 * @generated
		 */
		EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__DESCRIPTIONS = eINSTANCE.getFilter_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__DISPLAY_NAMES = eINSTANCE.getFilter_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__ICONS = eINSTANCE.getFilter_Icons();

		/**
		 * The meta object literal for the '<em><b>Filter Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__FILTER_NAME = eINSTANCE.getFilter_FilterName();

		/**
		 * The meta object literal for the '<em><b>Filter Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__FILTER_CLASS = eINSTANCE.getFilter_FilterClass();

		/**
		 * The meta object literal for the '<em><b>Async Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__ASYNC_SUPPORTED = eINSTANCE.getFilter_AsyncSupported();

		/**
		 * The meta object literal for the '<em><b>Init Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__INIT_PARAMS = eINSTANCE.getFilter_InitParams();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__ID = eINSTANCE.getFilter_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl <em>Filter Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.FilterMappingImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFilterMapping()
		 * @generated
		 */
		EClass FILTER_MAPPING = eINSTANCE.getFilterMapping();

		/**
		 * The meta object literal for the '<em><b>Filter Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__FILTER_NAME = eINSTANCE.getFilterMapping_FilterName();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__GROUP = eINSTANCE.getFilterMapping_Group();

		/**
		 * The meta object literal for the '<em><b>Url Patterns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_MAPPING__URL_PATTERNS = eINSTANCE.getFilterMapping_UrlPatterns();

		/**
		 * The meta object literal for the '<em><b>Servlet Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__SERVLET_NAMES = eINSTANCE.getFilterMapping_ServletNames();

		/**
		 * The meta object literal for the '<em><b>Dispatchers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__DISPATCHERS = eINSTANCE.getFilterMapping_Dispatchers();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_MAPPING__ID = eINSTANCE.getFilterMapping_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl <em>Form Login Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.FormLoginConfigImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFormLoginConfig()
		 * @generated
		 */
		EClass FORM_LOGIN_CONFIG = eINSTANCE.getFormLoginConfig();

		/**
		 * The meta object literal for the '<em><b>Form Login Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_LOGIN_CONFIG__FORM_LOGIN_PAGE = eINSTANCE.getFormLoginConfig_FormLoginPage();

		/**
		 * The meta object literal for the '<em><b>Form Error Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_LOGIN_CONFIG__FORM_ERROR_PAGE = eINSTANCE.getFormLoginConfig_FormErrorPage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORM_LOGIN_CONFIG__ID = eINSTANCE.getFormLoginConfig_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingImpl <em>Locale Encoding Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLocaleEncodingMapping()
		 * @generated
		 */
		EClass LOCALE_ENCODING_MAPPING = eINSTANCE.getLocaleEncodingMapping();

		/**
		 * The meta object literal for the '<em><b>Locale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCALE_ENCODING_MAPPING__LOCALE = eINSTANCE.getLocaleEncodingMapping_Locale();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCALE_ENCODING_MAPPING__ENCODING = eINSTANCE.getLocaleEncodingMapping_Encoding();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCALE_ENCODING_MAPPING__ID = eINSTANCE.getLocaleEncodingMapping_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingListImpl <em>Locale Encoding Mapping List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.LocaleEncodingMappingListImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLocaleEncodingMappingList()
		 * @generated
		 */
		EClass LOCALE_ENCODING_MAPPING_LIST = eINSTANCE.getLocaleEncodingMappingList();

		/**
		 * The meta object literal for the '<em><b>Local Encoding Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCALE_ENCODING_MAPPING_LIST__LOCAL_ENCODING_MAPPINGS = eINSTANCE.getLocaleEncodingMappingList_LocalEncodingMappings();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCALE_ENCODING_MAPPING_LIST__ID = eINSTANCE.getLocaleEncodingMappingList_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.LoginConfigImpl <em>Login Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.LoginConfigImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLoginConfig()
		 * @generated
		 */
		EClass LOGIN_CONFIG = eINSTANCE.getLoginConfig();

		/**
		 * The meta object literal for the '<em><b>Auth Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_CONFIG__AUTH_METHOD = eINSTANCE.getLoginConfig_AuthMethod();

		/**
		 * The meta object literal for the '<em><b>Realm Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_CONFIG__REALM_NAME = eINSTANCE.getLoginConfig_RealmName();

		/**
		 * The meta object literal for the '<em><b>Form Login Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGIN_CONFIG__FORM_LOGIN_CONFIG = eINSTANCE.getLoginConfig_FormLoginConfig();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGIN_CONFIG__ID = eINSTANCE.getLoginConfig_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.MimeMappingImpl <em>Mime Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.MimeMappingImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getMimeMapping()
		 * @generated
		 */
		EClass MIME_MAPPING = eINSTANCE.getMimeMapping();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MIME_MAPPING__EXTENSION = eINSTANCE.getMimeMapping_Extension();

		/**
		 * The meta object literal for the '<em><b>Mime Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MIME_MAPPING__MIME_TYPE = eINSTANCE.getMimeMapping_MimeType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MIME_MAPPING__ID = eINSTANCE.getMimeMapping_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl <em>Multipart Config Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.MultipartConfigTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getMultipartConfigType()
		 * @generated
		 */
		EClass MULTIPART_CONFIG_TYPE = eINSTANCE.getMultipartConfigType();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MULTIPART_CONFIG_TYPE__LOCATION = eINSTANCE.getMultipartConfigType_Location();

		/**
		 * The meta object literal for the '<em><b>Max File Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MULTIPART_CONFIG_TYPE__MAX_FILE_SIZE = eINSTANCE.getMultipartConfigType_MaxFileSize();

		/**
		 * The meta object literal for the '<em><b>Max Request Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MULTIPART_CONFIG_TYPE__MAX_REQUEST_SIZE = eINSTANCE.getMultipartConfigType_MaxRequestSize();

		/**
		 * The meta object literal for the '<em><b>File Size Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MULTIPART_CONFIG_TYPE__FILE_SIZE_THRESHOLD = eINSTANCE.getMultipartConfigType_FileSizeThreshold();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.OrderingOrderingTypeImpl <em>Ordering Ordering Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.OrderingOrderingTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getOrderingOrderingType()
		 * @generated
		 */
		EClass ORDERING_ORDERING_TYPE = eINSTANCE.getOrderingOrderingType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDERING_ORDERING_TYPE__NAME = eINSTANCE.getOrderingOrderingType_Name();

		/**
		 * The meta object literal for the '<em><b>Others</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_ORDERING_TYPE__OTHERS = eINSTANCE.getOrderingOrderingType_Others();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.OrderingOthersTypeImpl <em>Ordering Others Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.OrderingOthersTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getOrderingOthersType()
		 * @generated
		 */
		EClass ORDERING_OTHERS_TYPE = eINSTANCE.getOrderingOthersType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDERING_OTHERS_TYPE__ID = eINSTANCE.getOrderingOthersType_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.OrderingTypeImpl <em>Ordering Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.OrderingTypeImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getOrderingType()
		 * @generated
		 */
		EClass ORDERING_TYPE = eINSTANCE.getOrderingType();

		/**
		 * The meta object literal for the '<em><b>After</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_TYPE__AFTER = eINSTANCE.getOrderingType_After();

		/**
		 * The meta object literal for the '<em><b>Before</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_TYPE__BEFORE = eINSTANCE.getOrderingType_Before();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl <em>Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getSecurityConstraint()
		 * @generated
		 */
		EClass SECURITY_CONSTRAINT = eINSTANCE.getSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__DISPLAY_NAMES = eINSTANCE.getSecurityConstraint_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Web Resource Collections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS = eINSTANCE.getSecurityConstraint_WebResourceCollections();

		/**
		 * The meta object literal for the '<em><b>Auth Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__AUTH_CONSTRAINT = eINSTANCE.getSecurityConstraint_AuthConstraint();

		/**
		 * The meta object literal for the '<em><b>User Data Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT = eINSTANCE.getSecurityConstraint_UserDataConstraint();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_CONSTRAINT__ID = eINSTANCE.getSecurityConstraint_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.ServletImpl <em>Servlet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.ServletImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getServlet()
		 * @generated
		 */
		EClass SERVLET = eINSTANCE.getServlet();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__DESCRIPTIONS = eINSTANCE.getServlet_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__DISPLAY_NAMES = eINSTANCE.getServlet_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__ICONS = eINSTANCE.getServlet_Icons();

		/**
		 * The meta object literal for the '<em><b>Servlet Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__SERVLET_NAME = eINSTANCE.getServlet_ServletName();

		/**
		 * The meta object literal for the '<em><b>Servlet Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__SERVLET_CLASS = eINSTANCE.getServlet_ServletClass();

		/**
		 * The meta object literal for the '<em><b>Jsp File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__JSP_FILE = eINSTANCE.getServlet_JspFile();

		/**
		 * The meta object literal for the '<em><b>Init Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__INIT_PARAMS = eINSTANCE.getServlet_InitParams();

		/**
		 * The meta object literal for the '<em><b>Load On Startup</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__LOAD_ON_STARTUP = eINSTANCE.getServlet_LoadOnStartup();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__ENABLED = eINSTANCE.getServlet_Enabled();

		/**
		 * The meta object literal for the '<em><b>Async Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__ASYNC_SUPPORTED = eINSTANCE.getServlet_AsyncSupported();

		/**
		 * The meta object literal for the '<em><b>Run As</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__RUN_AS = eINSTANCE.getServlet_RunAs();

		/**
		 * The meta object literal for the '<em><b>Security Role Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__SECURITY_ROLE_REFS = eINSTANCE.getServlet_SecurityRoleRefs();

		/**
		 * The meta object literal for the '<em><b>Multipart Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET__MULTIPART_CONFIG = eINSTANCE.getServlet_MultipartConfig();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET__ID = eINSTANCE.getServlet_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl <em>Servlet Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.ServletMappingImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getServletMapping()
		 * @generated
		 */
		EClass SERVLET_MAPPING = eINSTANCE.getServletMapping();

		/**
		 * The meta object literal for the '<em><b>Servlet Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET_MAPPING__SERVLET_NAME = eINSTANCE.getServletMapping_ServletName();

		/**
		 * The meta object literal for the '<em><b>Url Patterns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVLET_MAPPING__URL_PATTERNS = eINSTANCE.getServletMapping_UrlPatterns();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVLET_MAPPING__ID = eINSTANCE.getServletMapping_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl <em>Session Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.SessionConfigImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getSessionConfig()
		 * @generated
		 */
		EClass SESSION_CONFIG = eINSTANCE.getSessionConfig();

		/**
		 * The meta object literal for the '<em><b>Session Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION_CONFIG__SESSION_TIMEOUT = eINSTANCE.getSessionConfig_SessionTimeout();

		/**
		 * The meta object literal for the '<em><b>Cookie Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION_CONFIG__COOKIE_CONFIG = eINSTANCE.getSessionConfig_CookieConfig();

		/**
		 * The meta object literal for the '<em><b>Tracking Mode</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION_CONFIG__TRACKING_MODE = eINSTANCE.getSessionConfig_TrackingMode();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION_CONFIG__ID = eINSTANCE.getSessionConfig_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl <em>User Data Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.UserDataConstraintImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getUserDataConstraint()
		 * @generated
		 */
		EClass USER_DATA_CONSTRAINT = eINSTANCE.getUserDataConstraint();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DATA_CONSTRAINT__DESCRIPTIONS = eINSTANCE.getUserDataConstraint_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Transport Guarantee</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA_CONSTRAINT__TRANSPORT_GUARANTEE = eINSTANCE.getUserDataConstraint_TransportGuarantee();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA_CONSTRAINT__ID = eINSTANCE.getUserDataConstraint_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebAppImpl <em>App</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebAppImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebApp()
		 * @generated
		 */
		EClass WEB_APP = eINSTANCE.getWebApp();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__GROUP = eINSTANCE.getWebApp_Group();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__DESCRIPTIONS = eINSTANCE.getWebApp_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__DISPLAY_NAMES = eINSTANCE.getWebApp_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__ICONS = eINSTANCE.getWebApp_Icons();

		/**
		 * The meta object literal for the '<em><b>Distributables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__DISTRIBUTABLES = eINSTANCE.getWebApp_Distributables();

		/**
		 * The meta object literal for the '<em><b>Context Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__CONTEXT_PARAMS = eINSTANCE.getWebApp_ContextParams();

		/**
		 * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__FILTERS = eINSTANCE.getWebApp_Filters();

		/**
		 * The meta object literal for the '<em><b>Filter Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__FILTER_MAPPINGS = eINSTANCE.getWebApp_FilterMappings();

		/**
		 * The meta object literal for the '<em><b>Listeners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__LISTENERS = eINSTANCE.getWebApp_Listeners();

		/**
		 * The meta object literal for the '<em><b>Servlets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SERVLETS = eINSTANCE.getWebApp_Servlets();

		/**
		 * The meta object literal for the '<em><b>Servlet Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SERVLET_MAPPINGS = eINSTANCE.getWebApp_ServletMappings();

		/**
		 * The meta object literal for the '<em><b>Session Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SESSION_CONFIGS = eINSTANCE.getWebApp_SessionConfigs();

		/**
		 * The meta object literal for the '<em><b>Mime Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__MIME_MAPPINGS = eINSTANCE.getWebApp_MimeMappings();

		/**
		 * The meta object literal for the '<em><b>Welcome File Lists</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__WELCOME_FILE_LISTS = eINSTANCE.getWebApp_WelcomeFileLists();

		/**
		 * The meta object literal for the '<em><b>Error Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__ERROR_PAGES = eINSTANCE.getWebApp_ErrorPages();

		/**
		 * The meta object literal for the '<em><b>Jsp Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__JSP_CONFIGS = eINSTANCE.getWebApp_JspConfigs();

		/**
		 * The meta object literal for the '<em><b>Security Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SECURITY_CONSTRAINTS = eINSTANCE.getWebApp_SecurityConstraints();

		/**
		 * The meta object literal for the '<em><b>Login Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__LOGIN_CONFIGS = eINSTANCE.getWebApp_LoginConfigs();

		/**
		 * The meta object literal for the '<em><b>Security Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SECURITY_ROLES = eINSTANCE.getWebApp_SecurityRoles();

		/**
		 * The meta object literal for the '<em><b>Env Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__ENV_ENTRIES = eINSTANCE.getWebApp_EnvEntries();

		/**
		 * The meta object literal for the '<em><b>Ejb Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__EJB_REFS = eINSTANCE.getWebApp_EjbRefs();

		/**
		 * The meta object literal for the '<em><b>Ejb Local Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__EJB_LOCAL_REFS = eINSTANCE.getWebApp_EjbLocalRefs();

		/**
		 * The meta object literal for the '<em><b>Service Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__SERVICE_REFS = eINSTANCE.getWebApp_ServiceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__RESOURCE_REFS = eINSTANCE.getWebApp_ResourceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Env Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__RESOURCE_ENV_REFS = eINSTANCE.getWebApp_ResourceEnvRefs();

		/**
		 * The meta object literal for the '<em><b>Message Destination Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__MESSAGE_DESTINATION_REFS = eINSTANCE.getWebApp_MessageDestinationRefs();

		/**
		 * The meta object literal for the '<em><b>Persistence Context Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__PERSISTENCE_CONTEXT_REFS = eINSTANCE.getWebApp_PersistenceContextRefs();

		/**
		 * The meta object literal for the '<em><b>Persistence Unit Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__PERSISTENCE_UNIT_REFS = eINSTANCE.getWebApp_PersistenceUnitRefs();

		/**
		 * The meta object literal for the '<em><b>Post Constructs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__POST_CONSTRUCTS = eINSTANCE.getWebApp_PostConstructs();

		/**
		 * The meta object literal for the '<em><b>Pre Destroys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__PRE_DESTROYS = eINSTANCE.getWebApp_PreDestroys();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__DATA_SOURCE = eINSTANCE.getWebApp_DataSource();

		/**
		 * The meta object literal for the '<em><b>Jms Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__JMS_CONNECTION_FACTORY = eINSTANCE.getWebApp_JmsConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Jms Destination</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__JMS_DESTINATION = eINSTANCE.getWebApp_JmsDestination();

		/**
		 * The meta object literal for the '<em><b>Mail Session</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__MAIL_SESSION = eINSTANCE.getWebApp_MailSession();

		/**
		 * The meta object literal for the '<em><b>Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__CONNECTION_FACTORY = eINSTANCE.getWebApp_ConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Administered Object</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__ADMINISTERED_OBJECT = eINSTANCE.getWebApp_AdministeredObject();

		/**
		 * The meta object literal for the '<em><b>Message Destinations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__MESSAGE_DESTINATIONS = eINSTANCE.getWebApp_MessageDestinations();

		/**
		 * The meta object literal for the '<em><b>Local Encoding Mappings Lists</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__LOCAL_ENCODING_MAPPINGS_LISTS = eINSTANCE.getWebApp_LocalEncodingMappingsLists();

		/**
		 * The meta object literal for the '<em><b>Deny Uncovered Http Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__DENY_UNCOVERED_HTTP_METHODS = eINSTANCE.getWebApp_DenyUncoveredHttpMethods();

		/**
		 * The meta object literal for the '<em><b>Module Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__MODULE_NAME = eINSTANCE.getWebApp_ModuleName();

		/**
		 * The meta object literal for the '<em><b>Absolute Ordering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP__ABSOLUTE_ORDERING = eINSTANCE.getWebApp_AbsoluteOrdering();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__ID = eINSTANCE.getWebApp_Id();

		/**
		 * The meta object literal for the '<em><b>Metadata Complete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__METADATA_COMPLETE = eINSTANCE.getWebApp_MetadataComplete();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP__VERSION = eINSTANCE.getWebApp_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebAppDeploymentDescriptorImpl <em>App Deployment Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebAppDeploymentDescriptorImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebAppDeploymentDescriptor()
		 * @generated
		 */
		EClass WEB_APP_DEPLOYMENT_DESCRIPTOR = eINSTANCE.getWebAppDeploymentDescriptor();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_APP_DEPLOYMENT_DESCRIPTOR__MIXED = eINSTANCE.getWebAppDeploymentDescriptor_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP = eINSTANCE.getWebAppDeploymentDescriptor_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION = eINSTANCE.getWebAppDeploymentDescriptor_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Web App</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_APP_DEPLOYMENT_DESCRIPTOR__WEB_APP = eINSTANCE.getWebAppDeploymentDescriptor_WebApp();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebFragmentImpl <em>Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebFragmentImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebFragment()
		 * @generated
		 */
		EClass WEB_FRAGMENT = eINSTANCE.getWebFragment();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_FRAGMENT__GROUP = eINSTANCE.getWebFragment_Group();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__DESCRIPTIONS = eINSTANCE.getWebFragment_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__DISPLAY_NAMES = eINSTANCE.getWebFragment_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__ICONS = eINSTANCE.getWebFragment_Icons();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_FRAGMENT__NAME = eINSTANCE.getWebFragment_Name();

		/**
		 * The meta object literal for the '<em><b>Distributables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__DISTRIBUTABLES = eINSTANCE.getWebFragment_Distributables();

		/**
		 * The meta object literal for the '<em><b>Context Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__CONTEXT_PARAMS = eINSTANCE.getWebFragment_ContextParams();

		/**
		 * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__FILTERS = eINSTANCE.getWebFragment_Filters();

		/**
		 * The meta object literal for the '<em><b>Filter Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__FILTER_MAPPINGS = eINSTANCE.getWebFragment_FilterMappings();

		/**
		 * The meta object literal for the '<em><b>Listeners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__LISTENERS = eINSTANCE.getWebFragment_Listeners();

		/**
		 * The meta object literal for the '<em><b>Servlets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__SERVLETS = eINSTANCE.getWebFragment_Servlets();

		/**
		 * The meta object literal for the '<em><b>Servlet Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__SERVLET_MAPPINGS = eINSTANCE.getWebFragment_ServletMappings();

		/**
		 * The meta object literal for the '<em><b>Session Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__SESSION_CONFIGS = eINSTANCE.getWebFragment_SessionConfigs();

		/**
		 * The meta object literal for the '<em><b>Mime Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__MIME_MAPPINGS = eINSTANCE.getWebFragment_MimeMappings();

		/**
		 * The meta object literal for the '<em><b>Welcome File Lists</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__WELCOME_FILE_LISTS = eINSTANCE.getWebFragment_WelcomeFileLists();

		/**
		 * The meta object literal for the '<em><b>Error Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__ERROR_PAGES = eINSTANCE.getWebFragment_ErrorPages();

		/**
		 * The meta object literal for the '<em><b>Jsp Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__JSP_CONFIGS = eINSTANCE.getWebFragment_JspConfigs();

		/**
		 * The meta object literal for the '<em><b>Security Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__SECURITY_CONSTRAINTS = eINSTANCE.getWebFragment_SecurityConstraints();

		/**
		 * The meta object literal for the '<em><b>Login Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__LOGIN_CONFIGS = eINSTANCE.getWebFragment_LoginConfigs();

		/**
		 * The meta object literal for the '<em><b>Security Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__SECURITY_ROLES = eINSTANCE.getWebFragment_SecurityRoles();

		/**
		 * The meta object literal for the '<em><b>Env Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__ENV_ENTRIES = eINSTANCE.getWebFragment_EnvEntries();

		/**
		 * The meta object literal for the '<em><b>Ejb Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__EJB_REFS = eINSTANCE.getWebFragment_EjbRefs();

		/**
		 * The meta object literal for the '<em><b>Ejb Local Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__EJB_LOCAL_REFS = eINSTANCE.getWebFragment_EjbLocalRefs();

		/**
		 * The meta object literal for the '<em><b>Service Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__SERVICE_REFS = eINSTANCE.getWebFragment_ServiceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__RESOURCE_REFS = eINSTANCE.getWebFragment_ResourceRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Env Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__RESOURCE_ENV_REFS = eINSTANCE.getWebFragment_ResourceEnvRefs();

		/**
		 * The meta object literal for the '<em><b>Message Destination Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__MESSAGE_DESTINATION_REFS = eINSTANCE.getWebFragment_MessageDestinationRefs();

		/**
		 * The meta object literal for the '<em><b>Persistence Context Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__PERSISTENCE_CONTEXT_REFS = eINSTANCE.getWebFragment_PersistenceContextRefs();

		/**
		 * The meta object literal for the '<em><b>Persistence Unit Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__PERSISTENCE_UNIT_REFS = eINSTANCE.getWebFragment_PersistenceUnitRefs();

		/**
		 * The meta object literal for the '<em><b>Post Constructs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__POST_CONSTRUCTS = eINSTANCE.getWebFragment_PostConstructs();

		/**
		 * The meta object literal for the '<em><b>Pre Destroys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__PRE_DESTROYS = eINSTANCE.getWebFragment_PreDestroys();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__DATA_SOURCE = eINSTANCE.getWebFragment_DataSource();

		/**
		 * The meta object literal for the '<em><b>Jms Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__JMS_CONNECTION_FACTORY = eINSTANCE.getWebFragment_JmsConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Jms Destination</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__JMS_DESTINATION = eINSTANCE.getWebFragment_JmsDestination();

		/**
		 * The meta object literal for the '<em><b>Mail Session</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__MAIL_SESSION = eINSTANCE.getWebFragment_MailSession();

		/**
		 * The meta object literal for the '<em><b>Connection Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__CONNECTION_FACTORY = eINSTANCE.getWebFragment_ConnectionFactory();

		/**
		 * The meta object literal for the '<em><b>Administered Object</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__ADMINISTERED_OBJECT = eINSTANCE.getWebFragment_AdministeredObject();

		/**
		 * The meta object literal for the '<em><b>Message Destinations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__MESSAGE_DESTINATIONS = eINSTANCE.getWebFragment_MessageDestinations();

		/**
		 * The meta object literal for the '<em><b>Local Encoding Mappings Lists</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__LOCAL_ENCODING_MAPPINGS_LISTS = eINSTANCE.getWebFragment_LocalEncodingMappingsLists();

		/**
		 * The meta object literal for the '<em><b>Ordering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_FRAGMENT__ORDERING = eINSTANCE.getWebFragment_Ordering();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_FRAGMENT__ID = eINSTANCE.getWebFragment_Id();

		/**
		 * The meta object literal for the '<em><b>Metadata Complete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_FRAGMENT__METADATA_COMPLETE = eINSTANCE.getWebFragment_MetadataComplete();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_FRAGMENT__VERSION = eINSTANCE.getWebFragment_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl <em>Resource Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebResourceCollectionImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebResourceCollection()
		 * @generated
		 */
		EClass WEB_RESOURCE_COLLECTION = eINSTANCE.getWebResourceCollection();

		/**
		 * The meta object literal for the '<em><b>Web Resource Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME = eINSTANCE.getWebResourceCollection_WebResourceName();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_RESOURCE_COLLECTION__DESCRIPTIONS = eINSTANCE.getWebResourceCollection_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Url Patterns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_RESOURCE_COLLECTION__URL_PATTERNS = eINSTANCE.getWebResourceCollection_UrlPatterns();

		/**
		 * The meta object literal for the '<em><b>Http Methods</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__HTTP_METHODS = eINSTANCE.getWebResourceCollection_HttpMethods();

		/**
		 * The meta object literal for the '<em><b>Http Method Omission</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__HTTP_METHOD_OMISSION = eINSTANCE.getWebResourceCollection_HttpMethodOmission();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_RESOURCE_COLLECTION__ID = eINSTANCE.getWebResourceCollection_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.internal.impl.WelcomeFileListImpl <em>Welcome File List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.internal.impl.WelcomeFileListImpl
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWelcomeFileList()
		 * @generated
		 */
		EClass WELCOME_FILE_LIST = eINSTANCE.getWelcomeFileList();

		/**
		 * The meta object literal for the '<em><b>Welcome Files</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WELCOME_FILE_LIST__WELCOME_FILES = eINSTANCE.getWelcomeFileList_WelcomeFiles();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WELCOME_FILE_LIST__ID = eINSTANCE.getWelcomeFileList_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.DispatcherType <em>Dispatcher Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.DispatcherType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getDispatcherType()
		 * @generated
		 */
		EEnum DISPATCHER_TYPE = eINSTANCE.getDispatcherType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.NullCharType <em>Null Char Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.NullCharType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getNullCharType()
		 * @generated
		 */
		EEnum NULL_CHAR_TYPE = eINSTANCE.getNullCharType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.TrackingModeType <em>Tracking Mode Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.TrackingModeType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTrackingModeType()
		 * @generated
		 */
		EEnum TRACKING_MODE_TYPE = eINSTANCE.getTrackingModeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.TransportGuaranteeType <em>Transport Guarantee Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTransportGuaranteeType()
		 * @generated
		 */
		EEnum TRANSPORT_GUARANTEE_TYPE = eINSTANCE.getTransportGuaranteeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.web.WebAppVersionType <em>App Version Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.WebAppVersionType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebAppVersionType()
		 * @generated
		 */
		EEnum WEB_APP_VERSION_TYPE = eINSTANCE.getWebAppVersionType();

		/**
		 * The meta object literal for the '<em>Auth Method Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getAuthMethodType()
		 * @generated
		 */
		EDataType AUTH_METHOD_TYPE = eINSTANCE.getAuthMethodType();

		/**
		 * The meta object literal for the '<em>Dispatcher Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.DispatcherType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getDispatcherTypeObject()
		 * @generated
		 */
		EDataType DISPATCHER_TYPE_OBJECT = eINSTANCE.getDispatcherTypeObject();

		/**
		 * The meta object literal for the '<em>Encoding Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getEncodingType()
		 * @generated
		 */
		EDataType ENCODING_TYPE = eINSTANCE.getEncodingType();

		/**
		 * The meta object literal for the '<em>Error Code Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.math.BigInteger
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getErrorCodeType()
		 * @generated
		 */
		EDataType ERROR_CODE_TYPE = eINSTANCE.getErrorCodeType();

		/**
		 * The meta object literal for the '<em>Filter Name Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getFilterNameType()
		 * @generated
		 */
		EDataType FILTER_NAME_TYPE = eINSTANCE.getFilterNameType();

		/**
		 * The meta object literal for the '<em>Http Method Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getHttpMethodType()
		 * @generated
		 */
		EDataType HTTP_METHOD_TYPE = eINSTANCE.getHttpMethodType();

		/**
		 * The meta object literal for the '<em>Load On Startup Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLoadOnStartupType()
		 * @generated
		 */
		EDataType LOAD_ON_STARTUP_TYPE = eINSTANCE.getLoadOnStartupType();

		/**
		 * The meta object literal for the '<em>Locale Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getLocaleType()
		 * @generated
		 */
		EDataType LOCALE_TYPE = eINSTANCE.getLocaleType();

		/**
		 * The meta object literal for the '<em>Mime Type Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getMimeTypeType()
		 * @generated
		 */
		EDataType MIME_TYPE_TYPE = eINSTANCE.getMimeTypeType();

		/**
		 * The meta object literal for the '<em>Non Empty String Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getNonEmptyStringType()
		 * @generated
		 */
		EDataType NON_EMPTY_STRING_TYPE = eINSTANCE.getNonEmptyStringType();

		/**
		 * The meta object literal for the '<em>Null Char Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.NullCharType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getNullCharTypeObject()
		 * @generated
		 */
		EDataType NULL_CHAR_TYPE_OBJECT = eINSTANCE.getNullCharTypeObject();

		/**
		 * The meta object literal for the '<em>Servlet Name Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getServletNameType()
		 * @generated
		 */
		EDataType SERVLET_NAME_TYPE = eINSTANCE.getServletNameType();

		/**
		 * The meta object literal for the '<em>Tracking Mode Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.TrackingModeType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTrackingModeTypeObject()
		 * @generated
		 */
		EDataType TRACKING_MODE_TYPE_OBJECT = eINSTANCE.getTrackingModeTypeObject();

		/**
		 * The meta object literal for the '<em>Transport Guarantee Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getTransportGuaranteeTypeObject()
		 * @generated
		 */
		EDataType TRANSPORT_GUARANTEE_TYPE_OBJECT = eINSTANCE.getTransportGuaranteeTypeObject();

		/**
		 * The meta object literal for the '<em>War Path Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWarPathType()
		 * @generated
		 */
		EDataType WAR_PATH_TYPE = eINSTANCE.getWarPathType();

		/**
		 * The meta object literal for the '<em>App Version Type Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.web.WebAppVersionType
		 * @see org.eclipse.jst.javaee.web.internal.impl.WebPackageImpl#getWebAppVersionTypeObject()
		 * @generated
		 */
		EDataType WEB_APP_VERSION_TYPE_OBJECT = eINSTANCE.getWebAppVersionTypeObject();

	}

} //WebPackage
