/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
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

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.SecurityRole;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The assembly-descriptorType defines
 *         application-assembly information.
 *         
 *         The application-assembly information consists of the
 *         following parts: the definition of security roles, the
 *         definition of method permissions, the definition of
 *         transaction attributes for enterprise beans with
 *         container-managed transaction demarcation, the definition
 *         of interceptor bindings, a list of
 *         methods to be excluded from being invoked, and a list of
 *         exception types that should be treated as application exceptions.
 *         
 *         All the parts are optional in the sense that they are
 *         omitted if the lists represented by them are empty.
 *         
 *         Providing an assembly-descriptor in the deployment
 *         descriptor is optional for the ejb-jar file or .war file producer.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getMethodPermissions <em>Method Permissions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getContainerTransactions <em>Container Transactions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getInterceptorBindings <em>Interceptor Bindings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getExcludeList <em>Exclude List</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getApplicationExceptions <em>Application Exceptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor()
 * @extends JavaEEObject
 * @generated
 */
public interface AssemblyDescriptor extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Security Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Roles</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_SecurityRoles()
	 * @generated
	 */
	List<SecurityRole> getSecurityRoles();

	/**
	 * Returns the value of the '<em><b>Method Permissions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.MethodPermission}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Permissions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Permissions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_MethodPermissions()
	 * @generated
	 */
	List<MethodPermission> getMethodPermissions();

	/**
	 * Returns the value of the '<em><b>Container Transactions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.ContainerTransactionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Transactions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Transactions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_ContainerTransactions()
	 * @generated
	 */
	List<ContainerTransactionType> getContainerTransactions();

	/**
	 * Returns the value of the '<em><b>Interceptor Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.InterceptorBindingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interceptor Bindings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interceptor Bindings</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_InterceptorBindings()
	 * @generated
	 */
	List<InterceptorBindingType> getInterceptorBindings();

	/**
	 * Returns the value of the '<em><b>Message Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destinations</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_MessageDestinations()
	 * @generated
	 */
	List<MessageDestination> getMessageDestinations();

	/**
	 * Returns the value of the '<em><b>Exclude List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclude List</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude List</em>' containment reference.
	 * @see #setExcludeList(ExcludeList)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_ExcludeList()
	 * @generated
	 */
	ExcludeList getExcludeList();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getExcludeList <em>Exclude List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclude List</em>' containment reference.
	 * @see #getExcludeList()
	 * @generated
	 */
	void setExcludeList(ExcludeList value);

	/**
	 * Returns the value of the '<em><b>Application Exceptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.ApplicationException}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application Exceptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Application Exceptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_ApplicationExceptions()
	 * @generated
	 */
	List<ApplicationException> getApplicationExceptions();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getAssemblyDescriptor_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // AssemblyDescriptor