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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.Method;
/**
 * Specifies how the container must manage transaction scopes for the enterprise bean's method invocations.  It consists of an optional description, a list of method elements, and a transaction attribute.The transaction attribute is to be applied to all the specified methods.
 */
public interface MethodTransaction extends EObject{

/**
 * Return a List of MethodElements that point to
 * @anEJB.
 */
List getMethodElements(EnterpriseBean anEJB) ;
MethodElement getMostSpecificMethodElement(Method aMethod) ;
/**
 * Return true if the transaction attributes are the same.
 */
boolean isEquivalent(MethodTransaction anotherMethodTransaction) ;
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TransactionAttribute attribute
	 * The trans-attribute element specifies how the container must manage the
	 * transaction boundaries when delegating a method invocation to an enterprise
	 * bean's business method.

	 */
	TransactionAttributeType getTransactionAttribute();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodTransaction#getTransactionAttribute <em>Transaction Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Attribute</em>' attribute.
	 * @see org.eclipse.jst.j2ee.ejb.TransactionAttributeType
	 * @see #isSetTransactionAttribute()
	 * @see #unsetTransactionAttribute()
	 * @see #getTransactionAttribute()
	 * @generated
	 */
	void setTransactionAttribute(TransactionAttributeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodTransaction#getTransactionAttribute <em>Transaction Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionAttribute()
	 * @see #getTransactionAttribute()
	 * @see #setTransactionAttribute(TransactionAttributeType)
	 * @generated
	 */
	void unsetTransactionAttribute();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodTransaction#getTransactionAttribute <em>Transaction Attribute</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transaction Attribute</em>' attribute is set.
	 * @see #unsetTransactionAttribute()
	 * @see #getTransactionAttribute()
	 * @see #setTransactionAttribute(TransactionAttributeType)
	 * @generated
	 */
	boolean isSetTransactionAttribute();

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
	 * @return The AssemblyDescriptor reference
	 */
	AssemblyDescriptor getAssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the AssemblyDescriptor reference
	 */
	void setAssemblyDescriptor(AssemblyDescriptor value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of MethodElements references
	 */
	EList getMethodElements();

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
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getMethodTransaction_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





