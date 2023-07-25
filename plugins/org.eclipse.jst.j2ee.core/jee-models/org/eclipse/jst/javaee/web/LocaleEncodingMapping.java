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
package org.eclipse.jst.javaee.web;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locale Encoding Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The locale-encoding-mapping contains locale name and
 *         encoding name. The locale name must be either "Language-code",
 *         such as "ja", defined by ISO-639 or "Language-code_Country-code",
 *         such as "ja_JP".  "Country code" is defined by ISO-3166.
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getLocale <em>Locale</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMapping()
 * @extends JavaEEObject
 * @generated
 */
public interface LocaleEncodingMapping extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Locale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locale</em>' attribute.
	 * @see #setLocale(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMapping_Locale()
	 * @generated
	 */
	String getLocale();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getLocale <em>Locale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locale</em>' attribute.
	 * @see #getLocale()
	 * @generated
	 */
	void setLocale(String value);

	/**
	 * Returns the value of the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding</em>' attribute.
	 * @see #setEncoding(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMapping_Encoding()
	 * @generated
	 */
	String getEncoding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getEncoding <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' attribute.
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(String value);

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMapping_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.LocaleEncodingMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // LocaleEncodingMapping