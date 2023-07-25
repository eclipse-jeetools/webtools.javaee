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
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EmptyType;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.RunAs;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Security Identity Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The security-identityType specifies whether the caller's
 *         security identity is to be used for the execution of the
 *         methods of the enterprise bean or whether a specific run-as
 *         identity is to be used. It contains an optional description
 *         and a specification of the security identity to be used.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getUseCallerIdentity <em>Use Caller Identity</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getRunAs <em>Run As</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSecurityIdentityType()
 * @extends JavaEEObject
 * @generated
 */
public interface SecurityIdentityType extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSecurityIdentityType_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Use Caller Identity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               The use-caller-identity element specifies that
	 *               the caller's security identity be used as the
	 *               security identity for the execution of the
	 *               enterprise bean's methods.
	 *               
	 *               @since Java EE 5, EJB 3.0
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Use Caller Identity</em>' containment reference.
	 * @see #setUseCallerIdentity(EmptyType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSecurityIdentityType_UseCallerIdentity()
	 * @generated
	 */
	EmptyType getUseCallerIdentity();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getUseCallerIdentity <em>Use Caller Identity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Caller Identity</em>' containment reference.
	 * @see #getUseCallerIdentity()
	 * @generated
	 */
	void setUseCallerIdentity(EmptyType value);

	/**
	 * Returns the value of the '<em><b>Run As</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Run As</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Run As</em>' containment reference.
	 * @see #setRunAs(RunAs)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSecurityIdentityType_RunAs()
	 * @generated
	 */
	RunAs getRunAs();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getRunAs <em>Run As</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Run As</em>' containment reference.
	 * @see #getRunAs()
	 * @generated
	 */
	void setRunAs(RunAs value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSecurityIdentityType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SecurityIdentityType