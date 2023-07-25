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
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Url Pattern Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The url-patternType contains the url pattern of the mapping.
 *         It must follow the rules specified in Section 11.2 of the
 *         Servlet API Specification. This pattern is assumed to be in
 *         URL-decoded form and must not contain CR(#xD) or LF(#xA).
 *         If it contains those characters, the container must inform
 *         the developer with a descriptive error message.
 *         The container must preserve all characters including whitespaces.
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.UrlPatternType#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getUrlPatternType()
 * @extends JavaEEObject
 * @generated
 */
public interface UrlPatternType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getUrlPatternType_Value()
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.UrlPatternType#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // UrlPatternType