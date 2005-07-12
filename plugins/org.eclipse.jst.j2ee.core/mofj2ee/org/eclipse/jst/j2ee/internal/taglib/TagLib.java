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
package org.eclipse.jst.j2ee.internal.taglib;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;

/**
 * The taglib tag is the document root.

 */
public interface TagLib extends CompatibilityDescriptionGroup{
	/**
	 * These values are used in the deployment descriptor, use getVersionID() to determine module versions
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TagLibVersion attribute
	 * Describes this version number of the tag library (dewey decimal).
	 * @regexp [0-9]*{ "."[0-9] }0..3
	 */
	String getTagLibVersion();

	/**
	* This returns the module version id. Compare with J2EEVersionConstants to determine module level
	*/
	public int getVersionID() throws IllegalStateException ;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the TagLibVersion attribute
	 */
	void setTagLibVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the JspVersion attribute
	 * Describes the JSP version (number) this tag library requires in order to
	 * function (dewey decimal).  Default is 1.2
	 * @regexp [0-9]*{ "."[0-9] }0..3
	 */
	String getJspVersion();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the JspVersion attribute
	 */
	void setJspVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ShortName attribute
	 * The value of the short-name element is a name that could be used by a JSP
	 * authoring tool to create names with a mnemonic value; for example, it may be
	 * used as the prefered prefix value in taglib directives. Do not use white space, 
	 * and do not start with digits or underscore.
	 */
	String getShortName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ShortName attribute
	 */
	void setShortName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Uri attribute
	 * The value of the uri element is a public URI that uniquely identifies the exact 
	 * semantics of this taglibrary.
	 */
	String getUri();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Uri attribute
	 */
	void setUri(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Tags references
	 */
	EList getTags();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Validator reference
	 * The validator element provides information on an optional validator that can be 
	 * used to validate the conformance of a JSP page to using this tag library.

	 */
	Validator getValidator();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Validator reference
	 */
	void setValidator(Validator value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of Listeners references
	 */
	EList getListeners();

	/**
	 * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.taglib.Function}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Functions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getTagLib_Functions()
	 * @model type="org.eclipse.jst.j2ee.internal.taglib.Function" containment="true"
	 * @generated
	 */
	EList getFunctions();

	/**
	 * Returns the value of the '<em><b>Taglib Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.taglib.TldExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Taglib Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Taglib Extensions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.taglib.TaglibPackage#getTagLib_TaglibExtensions()
	 * @model type="org.eclipse.jst.j2ee.internal.taglib.TldExtension" containment="true"
	 * @generated
	 */
	EList getTaglibExtensions();

}














