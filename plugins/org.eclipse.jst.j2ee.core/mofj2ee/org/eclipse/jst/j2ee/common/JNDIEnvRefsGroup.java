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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JNDI Env Refs Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * This group keeps the usage of the contained JNDI environment
 * reference elements consistent across J2EE deployment descriptors.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getEnvironmentProperties <em>Environment Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.common.JNDIEnvRefsGroup#getServiceRefs <em>Service Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup()
 * @model abstract="true"
 * @generated
 */
public interface JNDIEnvRefsGroup extends CompatibilityDescriptionGroup {
	/**
	 * Returns the value of the '<em><b>Environment Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.EnvEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Properties</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_EnvironmentProperties()
	 * @model type="org.eclipse.jst.j2ee.internal.common.EnvEntry" containment="true"
	 * @generated
	 */
	EList getEnvironmentProperties();

	/**
	 * Returns the value of the '<em><b>Resource Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.ResourceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_ResourceRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.common.ResourceRef" containment="true"
	 * @generated
	 */
	EList getResourceRefs();

	/**
	 * Returns the value of the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.EjbRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_EjbRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.common.EjbRef" containment="true"
	 * @generated
	 */
	EList getEjbRefs();

	/**
	 * Returns the value of the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.ResourceEnvRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Env Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A list of enterprise bean references to an administered object associated with a resource in the enterprise bean's environment. It consists of an optional description, the resource environment reference name, and an indication of the resource environment reference type expected by the enterprise bean code.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Env Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_ResourceEnvRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.common.ResourceEnvRef" containment="true"
	 * @generated
	 */
	EList getResourceEnvRefs();

	/**
	 * Returns the value of the '<em><b>Ejb Local Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.EJBLocalRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Local Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Local Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_EjbLocalRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.common.EJBLocalRef" containment="true"
	 * @generated
	 */
	EList getEjbLocalRefs();

	/**
	 * Returns the value of the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Destination Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_MessageDestinationRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.common.MessageDestinationRef" containment="true"
	 * @generated
	 */
	EList getMessageDestinationRefs();

	/**
	 * Returns the value of the '<em><b>Service Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getJNDIEnvRefsGroup_ServiceRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef" containment="true"
	 * @generated
	 */
	EList getServiceRefs();

} // JNDIEnvRefsGroup
