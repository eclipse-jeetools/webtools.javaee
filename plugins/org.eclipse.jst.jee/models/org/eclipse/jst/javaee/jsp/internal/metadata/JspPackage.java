/**
 * <copyright>
 * </copyright>
 *
 * $Id: JspPackage.java,v 1.2 2007/05/15 23:17:37 cbridgha Exp $
 */
package org.eclipse.jst.javaee.jsp.internal.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.javaee.jsp.JspFactory;

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
 *       @(#)jsp_2_1.xsds	1.5 08/11/05
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
 *       This is the XML Schema for the JSP 2.1 deployment descriptor
 *       types.  The JSP 2.1 schema contains all the special
 *       structures and datatypes that are necessary to use JSP files
 *       from a web application.
 * 
 *       The contents of this schema is used by the web-app_2_5.xsd
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
 * @see org.eclipse.jst.javaee.jsp.JspFactory
 * @generated
 */
public interface JspPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jsp"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://java.sun.com/xml/ns/javaee/jsp"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "jsp"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JspPackage eINSTANCE = org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl <em>Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getJspConfig()
	 * @generated
	 */
	int JSP_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Tag Libs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG__TAG_LIBS = 0;

	/**
	 * The feature id for the '<em><b>Jsp Property Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG__JSP_PROPERTY_GROUPS = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG__ID = 2;

	/**
	 * The number of structural features of the '<em>Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl <em>Property Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getJspPropertyGroup()
	 * @generated
	 */
	int JSP_PROPERTY_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DESCRIPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DISPLAY_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__ICONS = 2;

	/**
	 * The feature id for the '<em><b>Url Patterns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__URL_PATTERNS = 3;

	/**
	 * The feature id for the '<em><b>El Ignored</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__EL_IGNORED = 4;

	/**
	 * The feature id for the '<em><b>Page Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__PAGE_ENCODING = 5;

	/**
	 * The feature id for the '<em><b>Scripting Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__SCRIPTING_INVALID = 6;

	/**
	 * The feature id for the '<em><b>Is Xml</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__IS_XML = 7;

	/**
	 * The feature id for the '<em><b>Include Preludes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__INCLUDE_PRELUDES = 8;

	/**
	 * The feature id for the '<em><b>Include Codas</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__INCLUDE_CODAS = 9;

	/**
	 * The feature id for the '<em><b>Deferred Syntax Allowed As Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL = 10;

	/**
	 * The feature id for the '<em><b>Trim Directive Whitespaces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__ID = 12;

	/**
	 * The number of structural features of the '<em>Property Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl <em>Tag Lib</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getTagLib()
	 * @generated
	 */
	int TAG_LIB = 2;

	/**
	 * The feature id for the '<em><b>Taglib Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__TAGLIB_URI = 0;

	/**
	 * The feature id for the '<em><b>Taglib Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__TAGLIB_LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__ID = 2;

	/**
	 * The number of structural features of the '<em>Tag Lib</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '<em>File Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getJspFileType()
	 * @generated
	 */
	int JSP_FILE_TYPE = 3;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jsp.JspConfig <em>Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Config</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspConfig
	 * @generated
	 */
	EClass getJspConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jsp.JspConfig#getTagLibs <em>Tag Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Libs</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspConfig#getTagLibs()
	 * @see #getJspConfig()
	 * @generated
	 */
	EReference getJspConfig_TagLibs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jsp.JspConfig#getJspPropertyGroups <em>Jsp Property Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jsp Property Groups</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspConfig#getJspPropertyGroups()
	 * @see #getJspConfig()
	 * @generated
	 */
	EReference getJspConfig_JspPropertyGroups();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspConfig#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspConfig#getId()
	 * @see #getJspConfig()
	 * @generated
	 */
	EAttribute getJspConfig_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup <em>Property Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Group</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup
	 * @generated
	 */
	EClass getJspPropertyGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDescriptions()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EReference getJspPropertyGroup_Descriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDisplayNames()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EReference getJspPropertyGroup_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIcons()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EReference getJspPropertyGroup_Icons();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getUrlPatterns <em>Url Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Url Patterns</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getUrlPatterns()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EReference getJspPropertyGroup_UrlPatterns();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isElIgnored <em>El Ignored</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>El Ignored</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#isElIgnored()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_ElIgnored();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getPageEncoding <em>Page Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Encoding</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getPageEncoding()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_PageEncoding();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scripting Invalid</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#isScriptingInvalid()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_ScriptingInvalid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isIsXml <em>Is Xml</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Xml</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#isIsXml()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_IsXml();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIncludePreludes <em>Include Preludes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Include Preludes</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIncludePreludes()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_IncludePreludes();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIncludeCodas <em>Include Codas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Include Codas</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIncludeCodas()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_IncludeCodas();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isDeferredSyntaxAllowedAsLiteral <em>Deferred Syntax Allowed As Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deferred Syntax Allowed As Literal</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#isDeferredSyntaxAllowedAsLiteral()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_DeferredSyntaxAllowedAsLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isTrimDirectiveWhitespaces <em>Trim Directive Whitespaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trim Directive Whitespaces</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#isTrimDirectiveWhitespaces()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_TrimDirectiveWhitespaces();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jsp.JspPropertyGroup#getId()
	 * @see #getJspPropertyGroup()
	 * @generated
	 */
	EAttribute getJspPropertyGroup_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.javaee.jsp.TagLib <em>Tag Lib</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Lib</em>'.
	 * @see org.eclipse.jst.javaee.jsp.TagLib
	 * @generated
	 */
	EClass getTagLib();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.TagLib#getTaglibUri <em>Taglib Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Taglib Uri</em>'.
	 * @see org.eclipse.jst.javaee.jsp.TagLib#getTaglibUri()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_TaglibUri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.TagLib#getTaglibLocation <em>Taglib Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Taglib Location</em>'.
	 * @see org.eclipse.jst.javaee.jsp.TagLib#getTaglibLocation()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_TaglibLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.javaee.jsp.TagLib#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.javaee.jsp.TagLib#getId()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_Id();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>File Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>File Type</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getJspFileType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JspFactory getJspFactory();

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
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl <em>Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getJspConfig()
		 * @generated
		 */
		EClass JSP_CONFIG = eINSTANCE.getJspConfig();

		/**
		 * The meta object literal for the '<em><b>Tag Libs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_CONFIG__TAG_LIBS = eINSTANCE.getJspConfig_TagLibs();

		/**
		 * The meta object literal for the '<em><b>Jsp Property Groups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_CONFIG__JSP_PROPERTY_GROUPS = eINSTANCE.getJspConfig_JspPropertyGroups();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_CONFIG__ID = eINSTANCE.getJspConfig_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl <em>Property Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getJspPropertyGroup()
		 * @generated
		 */
		EClass JSP_PROPERTY_GROUP = eINSTANCE.getJspPropertyGroup();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_PROPERTY_GROUP__DESCRIPTIONS = eINSTANCE.getJspPropertyGroup_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_PROPERTY_GROUP__DISPLAY_NAMES = eINSTANCE.getJspPropertyGroup_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_PROPERTY_GROUP__ICONS = eINSTANCE.getJspPropertyGroup_Icons();

		/**
		 * The meta object literal for the '<em><b>Url Patterns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_PROPERTY_GROUP__URL_PATTERNS = eINSTANCE.getJspPropertyGroup_UrlPatterns();

		/**
		 * The meta object literal for the '<em><b>El Ignored</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__EL_IGNORED = eINSTANCE.getJspPropertyGroup_ElIgnored();

		/**
		 * The meta object literal for the '<em><b>Page Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__PAGE_ENCODING = eINSTANCE.getJspPropertyGroup_PageEncoding();

		/**
		 * The meta object literal for the '<em><b>Scripting Invalid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__SCRIPTING_INVALID = eINSTANCE.getJspPropertyGroup_ScriptingInvalid();

		/**
		 * The meta object literal for the '<em><b>Is Xml</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__IS_XML = eINSTANCE.getJspPropertyGroup_IsXml();

		/**
		 * The meta object literal for the '<em><b>Include Preludes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__INCLUDE_PRELUDES = eINSTANCE.getJspPropertyGroup_IncludePreludes();

		/**
		 * The meta object literal for the '<em><b>Include Codas</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__INCLUDE_CODAS = eINSTANCE.getJspPropertyGroup_IncludeCodas();

		/**
		 * The meta object literal for the '<em><b>Deferred Syntax Allowed As Literal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL = eINSTANCE.getJspPropertyGroup_DeferredSyntaxAllowedAsLiteral();

		/**
		 * The meta object literal for the '<em><b>Trim Directive Whitespaces</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES = eINSTANCE.getJspPropertyGroup_TrimDirectiveWhitespaces();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_PROPERTY_GROUP__ID = eINSTANCE.getJspPropertyGroup_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl <em>Tag Lib</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.TagLibImpl
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getTagLib()
		 * @generated
		 */
		EClass TAG_LIB = eINSTANCE.getTagLib();

		/**
		 * The meta object literal for the '<em><b>Taglib Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__TAGLIB_URI = eINSTANCE.getTagLib_TaglibUri();

		/**
		 * The meta object literal for the '<em><b>Taglib Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__TAGLIB_LOCATION = eINSTANCE.getTagLib_TaglibLocation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__ID = eINSTANCE.getTagLib_Id();

		/**
		 * The meta object literal for the '<em>File Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.jst.javaee.jsp.internal.impl.JspPackageImpl#getJspFileType()
		 * @generated
		 */
		EDataType JSP_FILE_TYPE = eINSTANCE.getJspFileType();

	}

} //JspPackage
