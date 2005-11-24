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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @generated
 * @since 1.0 */
public interface SecurityRoleRef extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description element is used by the ejb-jar file producer to provide text
	 * describing the parent element.  The description element should include any
	 * information that the ejb-jar file producer wants to provide to the consumer of
	 * the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the
	 * ejb-jar file consumer will display the description when processing the parent
	 * element.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Link attribute
	 * The role-link element is used to link a security role reference to a defined
	 * security role. The role-link element must contain the name of one of the
	 * security roles defined in the security-role elements.
	 */
	String getLink();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Link attribute
	 */
	void setLink(String value);

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
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getSecurityRoleRef_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





