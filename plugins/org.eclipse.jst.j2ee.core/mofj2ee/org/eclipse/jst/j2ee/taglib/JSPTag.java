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
package org.eclipse.jst.j2ee.taglib;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.internal.taglib.TaglibPackage;


/**
 * The tag element defines an action in this tag library. The tag element has one attribute, id.
 * The tag element may have several subelements defining:
 * name -- The unique action name
 * tag-class -- The tag handler class implementing javax.servlet.jsp.tagext.Tag
 * tei-class -- An optional subclass of javax.servlet.jsp.tagext.TagExtraInfo
 * body-content -- The body content type
 * display-name -- A short name that is intended to be displayed by tools
 * small-icon -- Optional small-icon that can be used by tools
 * large-icon -- Optional large-icon that can be used by tools
 * description -- Optional tag-specific information
 * variable -- Optional scripting variable information
 * attribute -- All attributes of this action
 * example -- Optional informal description of an example of a use of this action.

 */
public interface JSPTag extends DescriptionGroup{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the BodyContent attribute
	 * Provides a hint as to the content of the body of this tag. Primarily intended
	 * for use by page composition tools.There are currently three values specified:
	 * 
	 * tagdependent - The body of the tag is interpreted by the tag implementation
	 * itself, and is most likely in a		different "langage", e.g embedded SQL
	 * statements.
	 * 
	 * JSP - The body of the tag contains nested JSP syntax
	 * 
	 * empty - The body must be emptyThe default (if not defined) is JSP#PCDATA ::= 
	 * tagdependent | JSP | empty
	 */
	BodyContentType getBodyContent();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTag#getBodyContent <em>Body Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body Content</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.taglib.BodyContentType
	 * @see #isSetBodyContent()
	 * @see #unsetBodyContent()
	 * @see #getBodyContent()
	 * @generated
	 */
	void setBodyContent(BodyContentType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTag#getBodyContent <em>Body Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBodyContent()
	 * @see #getBodyContent()
	 * @see #setBodyContent(BodyContentType)
	 * @generated
	 */
	void unsetBodyContent();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTag#getBodyContent <em>Body Content</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Body Content</em>' attribute is set.
	 * @see #unsetBodyContent()
	 * @see #getBodyContent()
	 * @see #setBodyContent(BodyContentType)
	 * @generated
	 */
	boolean isSetBodyContent();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Example attribute
	 * The example element provides an informal description of an example of the use
	 * of a tag.
	 */
	String getExample();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Example attribute
	 */
	void setExample(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getJSPTag_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTag#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Dynamic Attributes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Attributes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Attributes</em>' attribute.
	 * @see #setDynamicAttributes(boolean)
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getJSPTag_DynamicAttributes()
	 * @model 
	 * @generated
	 */
	boolean isDynamicAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.taglib.JSPTag#isDynamicAttributes <em>Dynamic Attributes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Attributes</em>' attribute.
	 * @see #isDynamicAttributes()
	 * @generated
	 */
	void setDynamicAttributes(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Attributes references
	 */
	EList getAttributes();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Variables references
	 * The variable element provides information on the scripting variables defined by 
	 * this tag.
	 * It is a (translation time) error for an action that has one or more variable
	 * subelements to have a TagExtraInfo class that returns a non-null object.
	 * The subelements of variable are of the form:
	 * name-given -- The variable name as a constant
	 * name-from-attribute -- The name of an attribute whose (translation time) value
	 * will give the name of the variable. One of name-given or namefrom-attribute is
	 * required.
	 * variable-class -- Name of the class of the variable. java.lang.String is
	 * default.
	 * declare -- Whether the variable is declared or not. True is the default.
	 * scope -- The scope of the scripting variable defined. NESTED is default.

	 */
	EList getVariables();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The TagClass reference
	 * The tag-class element indicates the subclass of javax.serlvet.jsp.tagext.Tag
	 * that implements the request time semantics for this tag. This element is
	 * required.
	 * @regexp fully qualified Java class name

	 */
	JavaClass getTagClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the TagClass reference
	 */
	void setTagClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The TeiClass reference
	 * The tei-class element indicates the subclass of
	 * javax.servlet.jsp.tagext.TagExtraInfo for this tag. The class is instantiated
	 * at translation time. This element is optional.
	 * @regexp fully qualified Java class name
	 */
	JavaClass getTeiClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the TeiClass reference
	 */
	void setTeiClass(JavaClass value);

}














