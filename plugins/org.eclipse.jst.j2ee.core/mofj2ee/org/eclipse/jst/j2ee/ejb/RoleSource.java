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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
/**
 * Designates the source of a role that participates in a relationship. A relationship-role-source element uniquely identifies an entity bean.
 * @invariant The content of each role-source element shall refer to an existing entity bean, entity bean reference.
 */
public interface RoleSource extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Role reference
	 */
	EJBRelationshipRole getRole();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Role reference
	 */
	void setRole(EJBRelationshipRole value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The EntityBean reference
	 */
	ContainerManagedEntity getEntityBean();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.RoleSource#getEntityBean <em>Entity Bean</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity Bean</em>' reference.
	 * @see #getEntityBean()
	 * @generated
	 */
	void setEntityBean(ContainerManagedEntity value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getRoleSource_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





