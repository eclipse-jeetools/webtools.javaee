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
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Encoding Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMapping#getLocale <em>Locale</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMapping#getEncoding <em>Encoding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getLocalEncodingMapping()
 * @model 
 * @generated
 */
public interface LocalEncodingMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getLocalEncodingMapping_Locale()
	 * @model 
	 * @generated
	 */
	String getLocale();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMapping#getLocale <em>Locale</em>}' attribute.
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
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getLocalEncodingMapping_Encoding()
	 * @model 
	 * @generated
	 */
	String getEncoding();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.LocalEncodingMapping#getEncoding <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' attribute.
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(String value);

} // LocalEncodingMapping
