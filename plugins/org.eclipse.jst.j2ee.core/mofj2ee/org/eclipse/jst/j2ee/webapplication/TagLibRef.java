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
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
/**
 * The taglib element is used to describe a JSP tag library.
 * @since since J2EE1.4 deprecated, use org.eclipse.jst.j2ee.internal.jsp.TagLibRef
 */
public interface TagLibRef extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TaglibURI attribute
	 * The taglib-uri element describes a URI, relative to the location of the web.xml 
	 * document, identifying a Tag Library used in the Web Application.
	 */
	String getTaglibURI();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the TaglibURI attribute
	 */
	void setTaglibURI(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TaglibLocation attribute
	 * The taglib-location element contains the location (as a resource relative to
	 * the root of the web application) where to find the Tag Libary Description file
	 * for the tag library.
	 */
	String getTaglibLocation();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the TaglibLocation attribute
	 */
	void setTaglibLocation(String value);

	/**
	 * Returns the value of the '<em><b>Web App</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.j2ee.internal.webapplication.WebApp#getTagLibs <em>Tag Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Web App</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Web App</em>' container reference.
	 * @see #setWebApp(WebApp)
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getTagLibRef_WebApp()
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebApp#getTagLibs
	 * @model opposite="tagLibs"
	 * @generated
	 */
	WebApp getWebApp();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.TagLibRef#getWebApp <em>Web App</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Web App</em>' container reference.
	 * @see #getWebApp()
	 * @generated
	 */
	void setWebApp(WebApp value);

}














