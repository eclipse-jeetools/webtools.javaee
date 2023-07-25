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

import java.util.List;

import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Security Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The security-constraintType is used to associate
 *         security constraints with one or more web resource
 *         collections
 *         
 *         Used in: web-app
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.SecurityConstraint#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SecurityConstraint#getWebResourceCollections <em>Web Resource Collections</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SecurityConstraint#getAuthConstraint <em>Auth Constraint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SecurityConstraint#getUserDataConstraint <em>User Data Constraint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.SecurityConstraint#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSecurityConstraint()
 * @extends JavaEEObject
 * @generated
 */
public interface SecurityConstraint extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSecurityConstraint_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Web Resource Collections</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.WebResourceCollection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Web Resource Collections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Web Resource Collections</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSecurityConstraint_WebResourceCollections()
	 * @generated
	 */
	List<WebResourceCollection> getWebResourceCollections();

	/**
	 * Returns the value of the '<em><b>Auth Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auth Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auth Constraint</em>' containment reference.
	 * @see #setAuthConstraint(AuthConstraint)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSecurityConstraint_AuthConstraint()
	 * @generated
	 */
	AuthConstraint getAuthConstraint();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getAuthConstraint <em>Auth Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auth Constraint</em>' containment reference.
	 * @see #getAuthConstraint()
	 * @generated
	 */
	void setAuthConstraint(AuthConstraint value);

	/**
	 * Returns the value of the '<em><b>User Data Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Data Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Data Constraint</em>' containment reference.
	 * @see #setUserDataConstraint(UserDataConstraint)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSecurityConstraint_UserDataConstraint()
	 * @generated
	 */
	UserDataConstraint getUserDataConstraint();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getUserDataConstraint <em>User Data Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Data Constraint</em>' containment reference.
	 * @see #getUserDataConstraint()
	 * @generated
	 */
	void setUserDataConstraint(UserDataConstraint value);

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getSecurityConstraint_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.SecurityConstraint#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SecurityConstraint