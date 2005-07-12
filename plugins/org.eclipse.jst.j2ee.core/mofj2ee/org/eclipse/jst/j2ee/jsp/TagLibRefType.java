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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Lib Ref Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * A taglib can be used to provide information on a tag
 * library that is used by a JSP page within the Web
 * Application.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.TagLibRefType#getTaglibURI <em>Taglib URI</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jsp.TagLibRefType#getTaglibLocation <em>Taglib Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getTagLibRefType()
 * @model 
 * @generated
 */
public interface TagLibRefType extends EObject{
	/**
	 * Returns the value of the '<em><b>Taglib URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Taglib URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  A taglib-uri element describes a URI identifying a
	 *         tag library used in the web application.  The body
	 *         of the taglib-uri element may be either an
	 *         absolute URI specification, or a relative URI.
	 *         There should be no entries in web.xml with the
	 *         same taglib-uri value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Taglib URI</em>' attribute.
	 * @see #setTaglibURI(String)
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getTagLibRefType_TaglibURI()
	 * @model 
	 * @generated
	 */
	String getTaglibURI();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.TagLibRefType#getTaglibURI <em>Taglib URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Taglib URI</em>' attribute.
	 * @see #getTaglibURI()
	 * @generated
	 */
	void setTaglibURI(String value);

	/**
	 * Returns the value of the '<em><b>Taglib Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Taglib Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * the taglib-location element contains the location
	 *         (as a resource relative to the root of the web
	 *         application) where to find the Tag Library
	 *         Description file for the tag library.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Taglib Location</em>' attribute.
	 * @see #setTaglibLocation(String)
	 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage#getTagLibRefType_TaglibLocation()
	 * @model 
	 * @generated
	 */
	String getTaglibLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jsp.TagLibRefType#getTaglibLocation <em>Taglib Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Taglib Location</em>' attribute.
	 * @see #getTaglibLocation()
	 * @generated
	 */
	void setTaglibLocation(String value);

} // TagLibRefType
