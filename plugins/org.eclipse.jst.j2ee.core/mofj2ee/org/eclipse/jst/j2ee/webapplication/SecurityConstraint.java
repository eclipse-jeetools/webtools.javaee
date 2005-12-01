/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
/**
 * The security-constraint element is used to associate security constraints with one or more web resource collections
 */
public interface SecurityConstraint extends EObject{
	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4 deprecated, becomes displayNames
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getSecurityConstraint_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webapplication.SecurityConstraint#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The WebApp reference
	 */
	WebApp getWebApp();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the WebApp reference
	 */
	void setWebApp(WebApp value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of WebResourceCollections references
	 */
	EList getWebResourceCollections();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The AuthConstraint reference
	 */
	AuthConstraint getAuthConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the AuthConstraint reference
	 */
	void setAuthConstraint(AuthConstraint value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The UserDataConstraint reference
	 */
	UserDataConstraint getUserDataConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the UserDataConstraint reference
	 */
	void setUserDataConstraint(UserDataConstraint value);

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getSecurityConstraint_DisplayNames()
	 * @model type="org.eclipse.jst.j2ee.common.DisplayName" containment="true"
	 * @generated
	 */
	EList getDisplayNames();

}














