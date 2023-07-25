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

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Security Role Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The security-role-refType contains the declaration of a
 *         security role reference in a component's or a
 *         Deployment Component's code. The declaration consists of an
 *         optional description, the security role name used in the
 *         code, and an optional link to a security role. If the
 *         security role is not specified, the Deployer must choose an
 *         appropriate security role.
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getRoleName <em>Role Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getRoleLink <em>Role Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getSecurityRoleRef()
 * @extends JavaEEObject
 * @generated
 */
public interface SecurityRoleRef extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getSecurityRoleRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Role Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The value of the role-name element must be the String used
	 *             as the parameter to the 
	 *             EJBContext.isCallerInRole(String roleName) method or the
	 *             HttpServletRequest.isUserInRole(String role) method.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role Name</em>' attribute.
	 * @see #setRoleName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getSecurityRoleRef_RoleName()
	 * @generated
	 */
	String getRoleName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getRoleName <em>Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Name</em>' attribute.
	 * @see #getRoleName()
	 * @generated
	 */
	void setRoleName(String value);

	/**
	 * Returns the value of the '<em><b>Role Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The role-link element is a reference to a defined
	 *             security role. The role-link element must contain
	 *             the name of one of the security roles defined in the
	 *             security-role elements.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role Link</em>' attribute.
	 * @see #setRoleLink(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getSecurityRoleRef_RoleLink()
	 * @generated
	 */
	String getRoleLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getRoleLink <em>Role Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Link</em>' attribute.
	 * @see #getRoleLink()
	 * @generated
	 */
	void setRoleLink(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getSecurityRoleRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.SecurityRoleRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SecurityRoleRef