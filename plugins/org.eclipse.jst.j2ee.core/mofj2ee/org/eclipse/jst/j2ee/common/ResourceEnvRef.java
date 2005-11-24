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
import org.eclipse.jem.java.JavaClass;

/**
 * The resource-env-ref element contains a declaration of an enterprise bean's reference to an administered object associated with a resource in the enterprise bean's environment. It consists of an optional description, the resource environment reference name, and an indication of the resource environment reference type expected by the enterprise bean code.
 * 
 * Examples:
 * 
 * <resource-env-ref>
 *     <resource-env-ref-name>jms//StockQueue<//resource-env-ref-name>
 *     <resource-env-ref-type>javax.jms.Queue<//resource-env-ref-type>
 * <//resource-env-ref>
 * @since 1.0 */
public interface ResourceEnvRef extends EObject{

public String getTypeName();
public void setTypeName(String className);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description is used by the ejb-jar file producer to provide text describing 
	 * the resource env ref.
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
	 * @return The value of the Name attribute
	 * Specifies the name of a resource environment reference; its value is the
	 * environment entry name used in the enterprise bean code.
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Type reference
	 * Specifies the type of a resource environment reference.  See the class
	 * description for an example.
	 */
	JavaClass getType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Type reference
	 */
	void setType(JavaClass value);

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
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getResourceEnvRef_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





