/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Security Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The security-permissionType specifies a security
 *         permission that is required by the resource adapter code.
 *         
 *         The security permission listed in the deployment descriptor
 *         are ones that are different from those required by the
 *         default permission set as specified in the connector
 *         specification. The optional description can mention specific
 *         reason that resource adapter requires a given security
 *         permission.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.SecurityPermission#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.SecurityPermission#getSecurityPermissionSpec <em>Security Permission Spec</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.SecurityPermission#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getSecurityPermission()
 * @extends JavaEEObject
 * @generated
 */
public interface SecurityPermission extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getSecurityPermission_Description()
	 * @generated
	 */
	List<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>Security Permission Spec</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element security-permission-spec specifies a security
	 *             permission based on the Security policy file
	 *             syntax. Refer to the following URL for Sun's
	 *             implementation of the security permission
	 *             specification:
	 *             
	 *             http://docs.oracle.com/javase/6/docs/technotes/guides/security/PolicyFiles.html
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Security Permission Spec</em>' attribute.
	 * @see #setSecurityPermissionSpec(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getSecurityPermission_SecurityPermissionSpec()
	 * @generated
	 */
	String getSecurityPermissionSpec();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.SecurityPermission#getSecurityPermissionSpec <em>Security Permission Spec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Permission Spec</em>' attribute.
	 * @see #getSecurityPermissionSpec()
	 * @generated
	 */
	void setSecurityPermissionSpec(String value);

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getSecurityPermission_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.SecurityPermission#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SecurityPermission
