/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jsp;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.UrlPatternType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 		The jsp-property-groupType is used to group a number of
 *         files so they can be given global property information.
 *         All files so described are deemed to be JSP files.  The
 *         following additional properties can be described:
 *         
 *         - Control whether EL is ignored.
 *         - Control whether scripting elements are invalid.
 *         - Indicate pageEncoding information.
 *         - Indicate that a resource is a JSP document (XML).
 *         - Prelude and Coda automatic includes.
 *         - Control whether the character sequence #{ is allowed
 *         when used as a String literal.
 *         - Control whether template text containing only
 *         whitespaces must be removed from the response output.
 *         - Indicate the default contentType information.
 *         - Indicate the default buffering model for JspWriter
 *         - Control whether error should be raised for the use of
 *         undeclared namespaces in a JSP page.
 * 
 * 	@since Java EE 5, JSP 2.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isElIgnored <em>El Ignored</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getPageEncoding <em>Page Encoding</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isIsXml <em>Is Xml</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIncludePreludes <em>Include Preludes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getIncludeCodas <em>Include Codas</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isDeferredSyntaxAllowedAsLiteral <em>Deferred Syntax Allowed As Literal</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isTrimDirectiveWhitespaces <em>Trim Directive Whitespaces</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDefaultContentType <em>Default Content Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getBuffer <em>Buffer</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isErrorOnUndeclaredNamespace <em>Error On Undeclared Namespace</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup()
 * @extends JavaEEObject
 * @generated
 */
public interface JspPropertyGroup extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Url Patterns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.UrlPatternType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url Patterns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url Patterns</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_UrlPatterns()
	 * @generated
	 */
	List<UrlPatternType> getUrlPatterns();

	/**
	 * Returns the value of the '<em><b>El Ignored</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    Can be used to easily set the isELIgnored
	 * 	    property of a group of JSP pages.  By default, the
	 * 	    EL evaluation is enabled for Web Applications using
	 * 	    a Servlet 2.4 or greater web.xml, and disabled
	 * 	    otherwise.
	 * 
	 * 	    @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>El Ignored</em>' attribute.
	 * @see #isSetElIgnored()
	 * @see #unsetElIgnored()
	 * @see #setElIgnored(boolean)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_ElIgnored()
	 * @generated
	 */
	boolean isElIgnored();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isElIgnored <em>El Ignored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>El Ignored</em>' attribute.
	 * @see #isSetElIgnored()
	 * @see #unsetElIgnored()
	 * @see #isElIgnored()
	 * @generated
	 */
	void setElIgnored(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isElIgnored <em>El Ignored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetElIgnored()
	 * @see #isElIgnored()
	 * @see #setElIgnored(boolean)
	 * @generated
	 */
	void unsetElIgnored();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isElIgnored <em>El Ignored</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>El Ignored</em>' attribute is set.
	 * @see #unsetElIgnored()
	 * @see #isElIgnored()
	 * @see #setElIgnored(boolean)
	 * @generated
	 */
	boolean isSetElIgnored();

	/**
	 * Returns the value of the '<em><b>Page Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The valid values of page-encoding are those of the
	 * 	    pageEncoding page directive.  It is a
	 * 	    translation-time error to name different encodings
	 * 	    in the pageEncoding attribute of the page directive
	 * 	    of a JSP page and in a JSP configuration element
	 * 	    matching the page.  It is also a translation-time
	 * 	    error to name different encodings in the prolog
	 * 	    or text declaration of a document in XML syntax and
	 * 	    in a JSP configuration element matching the document.
	 * 	    It is legal to name the same encoding through
	 * 	    mulitple mechanisms.
	 * 
	 * 	    @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Page Encoding</em>' attribute.
	 * @see #setPageEncoding(String)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_PageEncoding()
	 * @generated
	 */
	String getPageEncoding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getPageEncoding <em>Page Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Encoding</em>' attribute.
	 * @see #getPageEncoding()
	 * @generated
	 */
	void setPageEncoding(String value);

	/**
	 * Returns the value of the '<em><b>Scripting Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    Can be used to easily disable scripting in a
	 * 	    group of JSP pages.  By default, scripting is
	 * 	    enabled.
	 * 
	 * 	    @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scripting Invalid</em>' attribute.
	 * @see #isSetScriptingInvalid()
	 * @see #unsetScriptingInvalid()
	 * @see #setScriptingInvalid(boolean)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_ScriptingInvalid()
	 * @generated
	 */
	boolean isScriptingInvalid();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scripting Invalid</em>' attribute.
	 * @see #isSetScriptingInvalid()
	 * @see #unsetScriptingInvalid()
	 * @see #isScriptingInvalid()
	 * @generated
	 */
	void setScriptingInvalid(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetScriptingInvalid()
	 * @see #isScriptingInvalid()
	 * @see #setScriptingInvalid(boolean)
	 * @generated
	 */
	void unsetScriptingInvalid();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Scripting Invalid</em>' attribute is set.
	 * @see #unsetScriptingInvalid()
	 * @see #isScriptingInvalid()
	 * @see #setScriptingInvalid(boolean)
	 * @generated
	 */
	boolean isSetScriptingInvalid();

	/**
	 * Returns the value of the '<em><b>Is Xml</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    If true, denotes that the group of resources
	 * 	    that match the URL pattern are JSP documents,
	 * 	    and thus must be interpreted as XML documents.
	 * 	    If false, the resources are assumed to not
	 * 	    be JSP documents, unless there is another
	 * 	    property group that indicates otherwise.
	 * 
	 * 	    @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Xml</em>' attribute.
	 * @see #isSetIsXml()
	 * @see #unsetIsXml()
	 * @see #setIsXml(boolean)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_IsXml()
	 * @generated
	 */
	boolean isIsXml();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isIsXml <em>Is Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Xml</em>' attribute.
	 * @see #isSetIsXml()
	 * @see #unsetIsXml()
	 * @see #isIsXml()
	 * @generated
	 */
	void setIsXml(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isIsXml <em>Is Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsXml()
	 * @see #isIsXml()
	 * @see #setIsXml(boolean)
	 * @generated
	 */
	void unsetIsXml();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isIsXml <em>Is Xml</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Xml</em>' attribute is set.
	 * @see #unsetIsXml()
	 * @see #isIsXml()
	 * @see #setIsXml(boolean)
	 * @generated
	 */
	boolean isSetIsXml();

	/**
	 * Returns the value of the '<em><b>Include Preludes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The include-prelude element is a context-relative
	 * 	    path that must correspond to an element in the
	 * 	    Web Application.  When the element is present,
	 * 	    the given path will be automatically included (as
	 * 	    in an include directive) at the beginning of each
	 * 	    JSP page in this jsp-property-group.
	 * 
	 * 	    @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Include Preludes</em>' attribute list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_IncludePreludes()
	 * @generated
	 */
	List<String> getIncludePreludes();

	/**
	 * Returns the value of the '<em><b>Include Codas</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The include-coda element is a context-relative
	 * 	    path that must correspond to an element in the
	 * 	    Web Application.  When the element is present,
	 * 	    the given path will be automatically included (as
	 * 	    in an include directive) at the end of each
	 * 	    JSP page in this jsp-property-group.
	 * 
	 * 	    @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Include Codas</em>' attribute list.
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_IncludeCodas()
	 * @generated
	 */
	List<String> getIncludeCodas();

	/**
	 * Returns the value of the '<em><b>Deferred Syntax Allowed As Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *              The character sequence #{ is reserved for EL expressions.
	 *              Consequently, a translation error occurs if the #{
	 *              character sequence is used as a String literal, unless
	 *              this element is enabled (true). Disabled (false) by
	 *              default.
	 * 
	 *              @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deferred Syntax Allowed As Literal</em>' attribute.
	 * @see #isSetDeferredSyntaxAllowedAsLiteral()
	 * @see #unsetDeferredSyntaxAllowedAsLiteral()
	 * @see #setDeferredSyntaxAllowedAsLiteral(boolean)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_DeferredSyntaxAllowedAsLiteral()
	 * @generated
	 */
	boolean isDeferredSyntaxAllowedAsLiteral();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isDeferredSyntaxAllowedAsLiteral <em>Deferred Syntax Allowed As Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deferred Syntax Allowed As Literal</em>' attribute.
	 * @see #isSetDeferredSyntaxAllowedAsLiteral()
	 * @see #unsetDeferredSyntaxAllowedAsLiteral()
	 * @see #isDeferredSyntaxAllowedAsLiteral()
	 * @generated
	 */
	void setDeferredSyntaxAllowedAsLiteral(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isDeferredSyntaxAllowedAsLiteral <em>Deferred Syntax Allowed As Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDeferredSyntaxAllowedAsLiteral()
	 * @see #isDeferredSyntaxAllowedAsLiteral()
	 * @see #setDeferredSyntaxAllowedAsLiteral(boolean)
	 * @generated
	 */
	void unsetDeferredSyntaxAllowedAsLiteral();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isDeferredSyntaxAllowedAsLiteral <em>Deferred Syntax Allowed As Literal</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Deferred Syntax Allowed As Literal</em>' attribute is set.
	 * @see #unsetDeferredSyntaxAllowedAsLiteral()
	 * @see #isDeferredSyntaxAllowedAsLiteral()
	 * @see #setDeferredSyntaxAllowedAsLiteral(boolean)
	 * @generated
	 */
	boolean isSetDeferredSyntaxAllowedAsLiteral();

	/**
	 * Returns the value of the '<em><b>Trim Directive Whitespaces</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *              Indicates that template text containing only whitespaces
	 *              must be removed from the response output. It has no
	 *              effect on JSP documents (XML syntax). Disabled (false)
	 *              by default.
	 * 
	 *              @since Java EE 5, JSP 2.1
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trim Directive Whitespaces</em>' attribute.
	 * @see #isSetTrimDirectiveWhitespaces()
	 * @see #unsetTrimDirectiveWhitespaces()
	 * @see #setTrimDirectiveWhitespaces(boolean)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_TrimDirectiveWhitespaces()
	 * @generated
	 */
	boolean isTrimDirectiveWhitespaces();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isTrimDirectiveWhitespaces <em>Trim Directive Whitespaces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trim Directive Whitespaces</em>' attribute.
	 * @see #isSetTrimDirectiveWhitespaces()
	 * @see #unsetTrimDirectiveWhitespaces()
	 * @see #isTrimDirectiveWhitespaces()
	 * @generated
	 */
	void setTrimDirectiveWhitespaces(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isTrimDirectiveWhitespaces <em>Trim Directive Whitespaces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTrimDirectiveWhitespaces()
	 * @see #isTrimDirectiveWhitespaces()
	 * @see #setTrimDirectiveWhitespaces(boolean)
	 * @generated
	 */
	void unsetTrimDirectiveWhitespaces();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isTrimDirectiveWhitespaces <em>Trim Directive Whitespaces</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Trim Directive Whitespaces</em>' attribute is set.
	 * @see #unsetTrimDirectiveWhitespaces()
	 * @see #isTrimDirectiveWhitespaces()
	 * @see #setTrimDirectiveWhitespaces(boolean)
	 * @generated
	 */
	boolean isSetTrimDirectiveWhitespaces();

	/**
	 * Returns the value of the '<em><b>Default Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The valid values of default-content-type are those of the
	 *             contentType page directive.  It specifies the default
	 *             response contentType if the page directive does not include
	 *             a contentType attribute.
	 * 
	 *             @since Java EE 6, JSP 2.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Content Type</em>' attribute.
	 * @see #setDefaultContentType(String)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_DefaultContentType()
	 * @generated
	 */
	String getDefaultContentType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getDefaultContentType <em>Default Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Content Type</em>' attribute.
	 * @see #getDefaultContentType()
	 * @generated
	 */
	void setDefaultContentType(String value);

	/**
	 * Returns the value of the '<em><b>Buffer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The valid values of buffer are those of the
	 *             buffer page directive.  It specifies if buffering should be
	 *             used for the output to response, and if so, the size of the
	 *             buffer to use.
	 * 
	 *             @since Java EE 6, JSP 2.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Buffer</em>' attribute.
	 * @see #setBuffer(String)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_Buffer()
	 * @generated
	 */
	String getBuffer();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getBuffer <em>Buffer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buffer</em>' attribute.
	 * @see #getBuffer()
	 * @generated
	 */
	void setBuffer(String value);

	/**
	 * Returns the value of the '<em><b>Error On Undeclared Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The default behavior when a tag with unknown namespace is used
	 *             in a JSP page (regular syntax) is to silently ignore it.  If
	 *             set to true, then an error must be raised during the translation
	 *             time when an undeclared tag is used in a JSP page.  Disabled
	 *             (false) by default.
	 * 
	 *             @since Java EE 6, JSP 2.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Error On Undeclared Namespace</em>' attribute.
	 * @see #isSetErrorOnUndeclaredNamespace()
	 * @see #unsetErrorOnUndeclaredNamespace()
	 * @see #setErrorOnUndeclaredNamespace(boolean)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_ErrorOnUndeclaredNamespace()
	 * @generated
	 */
	boolean isErrorOnUndeclaredNamespace();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isErrorOnUndeclaredNamespace <em>Error On Undeclared Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error On Undeclared Namespace</em>' attribute.
	 * @see #isSetErrorOnUndeclaredNamespace()
	 * @see #unsetErrorOnUndeclaredNamespace()
	 * @see #isErrorOnUndeclaredNamespace()
	 * @generated
	 */
	void setErrorOnUndeclaredNamespace(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isErrorOnUndeclaredNamespace <em>Error On Undeclared Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetErrorOnUndeclaredNamespace()
	 * @see #isErrorOnUndeclaredNamespace()
	 * @see #setErrorOnUndeclaredNamespace(boolean)
	 * @generated
	 */
	void unsetErrorOnUndeclaredNamespace();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#isErrorOnUndeclaredNamespace <em>Error On Undeclared Namespace</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Error On Undeclared Namespace</em>' attribute is set.
	 * @see #unsetErrorOnUndeclaredNamespace()
	 * @see #isErrorOnUndeclaredNamespace()
	 * @see #setErrorOnUndeclaredNamespace(boolean)
	 * @generated
	 */
	boolean isSetErrorOnUndeclaredNamespace();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage#getJspPropertyGroup_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jsp.JspPropertyGroup#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // JspPropertyGroup