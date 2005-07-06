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
package org.eclipse.jst.j2ee.jsp;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JSP Property Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @plannedfor J2EE1.4
 * The jsp-property-groupType is used to group a number of
 * files so they can be given global property information.
 * All files so described are deemed to be JSP files. The
 * following additional properties can be described:
 * 
 * - Control enabling of EL evaluation. - Control enabling
 * of Scripting elements. - Indicate pageEncoding
 * information. - Indicating that a resource is a JSP
 * document - Prelude and Coda automatic includes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#getUrlPattern <em>Url Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isElIgnored <em>El Ignored</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#getPageEncoding <em>Page Encoding</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isIsXML <em>Is XML</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#getIncludePreludes <em>Include Preludes</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#getIncludeCodas <em>Include Codas</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup()
 * @model 
 * @generated
 */
public interface JSPPropertyGroup extends CompatibilityDescriptionGroup {
	/**
	 * Returns the value of the '<em><b>Url Pattern</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url Pattern</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url Pattern</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_UrlPattern()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getUrlPattern();

	/**
	 * Returns the value of the '<em><b>El Ignored</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>El Ignored</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Can be used to easily set the isELIgnored
	 * 	property of a group of JSP pages. By default,
	 * 	the EL evaluation is enabled for Web
	 * 	Applications using a Servlet 2.4 or greater
	 * 	web.xml, and disabled otherwise.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>El Ignored</em>' attribute.
	 * @see #isSetElIgnored()
	 * @see #unsetElIgnored()
	 * @see #setElIgnored(boolean)
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_ElIgnored()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isElIgnored();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isElIgnored <em>El Ignored</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isElIgnored <em>El Ignored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetElIgnored()
	 * @see #isElIgnored()
	 * @see #setElIgnored(boolean)
	 * @generated
	 */
	void unsetElIgnored();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isElIgnored <em>El Ignored</em>}' attribute is set.
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
	 * <p>
	 * If the meaning of the '<em>Page Encoding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The valid values of page-encoding are those of
	 * 	the pageEncoding page directive. It is a
	 * 	translation-time error to name different
	 * 	encodings in the pageEncoding attribute of the
	 * 	page directive of a JSP page and in a JSP
	 * 	configuration element matching the page. It is
	 * 	also a translation-time error to name different
	 * 	encodings in the prolog of a document in XML
	 * 	syntax and in a JSP configuration element
	 * 	matching the document. It is legal to name the
	 * 	same encoding through mulitple mechanisms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Page Encoding</em>' attribute.
	 * @see #setPageEncoding(String)
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_PageEncoding()
	 * @model 
	 * @generated
	 */
	String getPageEncoding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#getPageEncoding <em>Page Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Encoding</em>' attribute.
	 * @see #getPageEncoding()
	 * @generated
	 */
	void setPageEncoding(String value);

	/**
	 * Returns the value of the '<em><b>Scripting Invalid</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scripting Invalid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Can be used to easily disable scripting in a
	 * 	group of JSP pages. By default, scripting is
	 * 	enabled.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scripting Invalid</em>' attribute.
	 * @see #isSetScriptingInvalid()
	 * @see #unsetScriptingInvalid()
	 * @see #setScriptingInvalid(boolean)
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_ScriptingInvalid()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isScriptingInvalid();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetScriptingInvalid()
	 * @see #isScriptingInvalid()
	 * @see #setScriptingInvalid(boolean)
	 * @generated
	 */
	void unsetScriptingInvalid();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Is XML</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is XML</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, denotes that the group of resources
	 * 	that match the URL pattern are JSP documents,
	 * 	and thus must be interpreted as XML documents.
	 * 	If false, the resources are assumed to not be
	 * 	JSP documents, unless there is another property
	 * 	group that indicates otherwise.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is XML</em>' attribute.
	 * @see #isSetIsXML()
	 * @see #unsetIsXML()
	 * @see #setIsXML(boolean)
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_IsXML()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isIsXML();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isIsXML <em>Is XML</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is XML</em>' attribute.
	 * @see #isSetIsXML()
	 * @see #unsetIsXML()
	 * @see #isIsXML()
	 * @generated
	 */
	void setIsXML(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isIsXML <em>Is XML</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsXML()
	 * @see #isIsXML()
	 * @see #setIsXML(boolean)
	 * @generated
	 */
	void unsetIsXML();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.JSPPropertyGroup#isIsXML <em>Is XML</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is XML</em>' attribute is set.
	 * @see #unsetIsXML()
	 * @see #isIsXML()
	 * @see #setIsXML(boolean)
	 * @generated
	 */
	boolean isSetIsXML();

	/**
	 * Returns the value of the '<em><b>Include Preludes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Preludes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The include-prelude element is a
	 * 	context-relative path that must correspond to
	 * 	an element in the Web Application. When the
	 * 	element is present, the given path will be
	 * 	automatically included (as in an include
	 * 	directive) at the beginning of each JSP page in
	 * 	this jsp-property-group.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Include Preludes</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_IncludePreludes()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getIncludePreludes();

	/**
	 * Returns the value of the '<em><b>Include Codas</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Codas</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The include-coda element is a context-relative
	 * 	path that must correspond to an element in the
	 * 	Web Application. When the element is present,
	 * 	the given path will be automatically included
	 * 	(as in an include directive) at the end of each
	 * 	JSP page in this jsp-property-group.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Include Codas</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getJSPPropertyGroup_IncludeCodas()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getIncludeCodas();

} // JSPPropertyGroup
