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
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


/**
 * The ejb-ref element is used for the declaration of a reference to an enterprise bean's home. The declaration consists of an optional description; the EJB reference name used in the code of the referencing application client; the expected type of the referenced enterprise bean; the expected home and remote interfaces of the referenced enterprise bean; and an optional ejb-link information. The optional  ejb-link element is used to specify the referenced enterprise bean.
 */
public interface EjbRef extends EObject{

/**
 * Return an enterprise bean referenced by the EjbRef, if one exists, within the scope of the parameter.
 * The ejb-link value of the ref must equate to a named enterprise bean contained in the jar; otherwise 
 * return null
 */
public EnterpriseBean getLinkedEjb(EJBJar ejbJar);/**
 * Return an enterprise bean referenced by the EjbRef, if one exists, within the scope of the parameter.
 * The ejb-link value of the ref must equate to a named enterprise bean contained in the jar; otherwise 
 * return null
 */  
/**
 * Return true if the name, home, remote, and link are equivalent.
 */
boolean isEquivalent(EjbRef anEjbRef) ;
/**
 * Return true if this reference is physically linked to
 * @anEJB.
 */
boolean isLinked(EnterpriseBean anEJB) ;
/**
 * Return true if the ejbref is for a local ejb interface.
 */
boolean isLocal();
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 * The ejb-ref-name element contains the name of an EJB reference. This is the
	 * JNDI name that the servlet code uses to get a reference to the enterprise bean.
	 * Example: <ejb-ref-name>ejb//Payroll<//ejb-ref-name>
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Type attribute
	 * The ejb-ref-type element contains the expected type of the referenced
	 * enterprise bean. The ejb-ref-type element must be one of the following:
	 * <ejb-ref-type>Entity<//ejb-ref-type>
	 * <ejb-ref-type>Session<//ejb-ref-type>
	 */
	EjbRefType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.EjbRef#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRefType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(EjbRefType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.common.EjbRef#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(EjbRefType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.common.EjbRef#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(EjbRefType)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Home attribute
	 * The home element contains the fully-qualified name of the enterprise bean's
	 * home interface.
	 * Example: <home>com.aardvark.payroll.PayrollHome<//home>
	 */
	String getHome();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Home attribute
	 */
	void setHome(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Remote attribute
	 * The remote element contains the fully-qualified name of the enterprise bean's
	 * remote interface.
	 */
	String getRemote();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Remote attribute
	 */
	void setRemote(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Link attribute
	 * The ejb-link element is used in the ejb-ref element to specify that an EJB
	 * reference is linked to an enterprise bean in the encompassing J2EE Application
	 * package. The value of the ejb-link element must be the ejb-name of an
	 * enterprise bean in the same J2EE Application package. 
	 * Example: <ejb-link>EmployeeRecord<//ejb-link>
	 */
	String getLink();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Link attribute
	 */
	void setLink(String value);

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
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @plannedfor J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getEjbRef_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





