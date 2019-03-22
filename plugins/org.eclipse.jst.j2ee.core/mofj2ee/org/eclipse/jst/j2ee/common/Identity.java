/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.common.util.EList;
/**
 * Specifies the run-as identity to be used for the execution of the methods of an enterprise bean. It contains an optional description, and the name of a security role.
 * @generated
 * @since 1.0 */
public interface Identity extends J2EEEObject {
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description is used by the ejb-jar file producer to provide text describing 
	 * the run as identity.
	 * 
	 * The description should include any information that the ejb-jar file producer
	 * wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer).
	 * Typically, the tools used by the ejb-jar file consumer will display the
	 * description when processing the list of dependents.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the RoleName attribute
	 * The role-name element contains the name of a security role.
	 * 
	 * The name must conform to the lexical rules for an NMTOKEN.
	 */
	String getRoleName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the RoleName attribute
	 */
	void setRoleName(String value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getIdentity_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





