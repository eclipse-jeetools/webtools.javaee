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
import org.eclipse.jst.j2ee.common.SecurityRole;

/**
 * The method-permission element specifies that one or more security roles are allowed to invoke one or more enterprise bean methods. The method-permission element consists of an optional description, a list of security role names, and a list of method elements.  The security roles used in the method-permission element must be defined in the security-role element of the deployment descriptor, and the methods must be methods defined in the enterprise bean's remote and//or home interfaces.
 */
public interface MethodPermission extends EObject{

/**
 * Return a List of MethodElements that point to
 * @anEJB.
 */
List getMethodElements(EnterpriseBean anEJB) ;
/**
 * Return the SecurityRole named @aRoleName.
 */
SecurityRole getSecurityRole(String aRoleName) ;
/**
 * Return true if this permission contains @aSecurityRole
 */
boolean hasSecurityRole(SecurityRole aSecurityRole) ;
/**
 * Return true if this permission contains a SecurityRole
 * named @aRoleName.
 */
boolean hasSecurityRole(String aRoleName) ;
/**
 * Return true if this MethodPermission and @anotherMethodPermission
 * have the same roles.
 */
boolean isEquivalent(MethodPermission anotherMethodPermission) ;
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
	 * @return The value of the Unchecked attribute
	 */
	boolean isUnchecked();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Unchecked attribute
	 */
	void setUnchecked(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodPermission#isUnchecked <em>Unchecked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnchecked()
	 * @see #isUnchecked()
	 * @see #setUnchecked(boolean)
	 * @generated
	 */
	void unsetUnchecked();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.ejb.MethodPermission#isUnchecked <em>Unchecked</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unchecked</em>' attribute is set.
	 * @see #unsetUnchecked()
	 * @see #isUnchecked()
	 * @see #setUnchecked(boolean)
	 * @generated
	 */
	boolean isSetUnchecked();

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
	 * @return The list of Roles references
	 */
	EList getRoles();

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
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getMethodPermission_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





