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
 * The assembly-descriptor element contains application-assembly information.  The application-assembly information consists of the following parts: the definition of security roles, the definition of method permissions, and the definition of transaction attributes for enterprise beans with container-managed transaction demarcation. All the parts are optional in the sense that they are omitted if the lists represented by them are empty. Providing an assembly-descriptor in the deployment descriptor is optional for the ejb-jar file producer.
 * @since 1.0 */
public interface AssemblyDescriptor extends EObject{

/**
 * Return the first method permission that contains all the roles in securityRoles and
 * is the same size
 */
MethodPermission getMethodPermission(List securityRoles);
/**
 * Return a List of MethodElements for @anEJB going
 * through the MethodPermissions.
 */
List getMethodPermissionMethodElements(EnterpriseBean anEJB) ;
/**
 * Return a List of MethodElements for @anEJB going
 * through the MethodTransactions.
 */
List getMethodTransactionMethodElements(EnterpriseBean anEJB) ;
	public SecurityRole getSecurityRoleNamed(String roleName);

/**
 * Rename the security role, if it exists
 */
public void renameSecurityRole(String existingRoleName, String newRoleName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of MethodPermissions references
	 */
	EList getMethodPermissions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of MethodTransactions references
	 * Specifies how the container must manage transaction scopes for the enterprise
	 * bean's method invocations.  The element consists of an optional description, a
	 * list of method elements, and a transaction attribute.The transaction attribute
	 * is to be applied to all the specified methods.
	 */
	EList getMethodTransactions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The EjbJar reference
	 */
	EJBJar getEjbJar();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the EjbJar reference
	 */
	void setEjbJar(EJBJar value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of SecurityRoles references
	 */
	EList getSecurityRoles();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ExcludeList reference
	 */
	ExcludeList getExcludeList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the ExcludeList reference
	 */
	void setExcludeList(ExcludeList value);

	/**
	 * Returns the value of the '<em><b>Message Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destinations</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getAssemblyDescriptor_MessageDestinations()
	 * @model type="org.eclipse.jst.j2ee.common.MessageDestination" containment="true"
	 * @generated
	 */
	EList getMessageDestinations();

	/**
	 * Remove the MethodElements that are referencing @anEJB.
	 */
	void removeData(EnterpriseBean anEJB) ;

}





