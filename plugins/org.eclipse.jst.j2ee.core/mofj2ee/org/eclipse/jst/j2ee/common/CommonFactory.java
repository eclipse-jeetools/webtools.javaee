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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.common.CommonPackage
 * @generated
 */
public interface CommonFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommonFactory eINSTANCE = new org.eclipse.jst.j2ee.common.impl.CommonFactoryImpl();

	/**
	 * Returns a new object of class '<em>Ejb Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ejb Ref</em>'.
	 * @generated
	 */
	EjbRef createEjbRef();

	/**
	 * Returns a new object of class '<em>Env Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Env Entry</em>'.
	 * @generated
	 */
	EnvEntry createEnvEntry();

	/**
	 * Returns a new object of class '<em>Resource Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Ref</em>'.
	 * @generated
	 */
	ResourceRef createResourceRef();

	/**
	 * Returns a new object of class '<em>Security Role Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Role Ref</em>'.
	 * @generated
	 */
	SecurityRoleRef createSecurityRoleRef();

	/**
	 * Returns a new object of class '<em>Security Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Role</em>'.
	 * @generated
	 */
	SecurityRole createSecurityRole();

	/**
	 * Returns a new object of class '<em>Resource Env Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Env Ref</em>'.
	 * @generated
	 */
	ResourceEnvRef createResourceEnvRef();

	/**
	 * Returns a new object of class '<em>EJB Local Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EJB Local Ref</em>'.
	 * @generated
	 */
	EJBLocalRef createEJBLocalRef();

	/**
	 * Returns a new object of class '<em>Run As Specified Identity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run As Specified Identity</em>'.
	 * @generated
	 */
	RunAsSpecifiedIdentity createRunAsSpecifiedIdentity();

	/**
	 * Returns a new object of class '<em>Identity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Identity</em>'.
	 * @generated
	 */
	Identity createIdentity();

	/**
	 * Returns a new object of class '<em>Icon Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Icon Type</em>'.
	 * @generated
	 */
	IconType createIconType();

	/**
	 * Returns a new object of class '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Display Name</em>'.
	 * @generated
	 */
	DisplayName createDisplayName();

	/**
	 * Returns a new object of class '<em>Message Destination Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Destination Ref</em>'.
	 * @generated
	 */
	MessageDestinationRef createMessageDestinationRef();

	/**
	 * Returns a new object of class '<em>Message Destination</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Destination</em>'.
	 * @generated
	 */
	MessageDestination createMessageDestination();

	/**
	 * Returns a new object of class '<em>Param Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Param Value</em>'.
	 * @generated
	 */
	ParamValue createParamValue();

	/**
	 * Returns a new object of class '<em>Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description Group</em>'.
	 * @generated
	 */
	DescriptionGroup createDescriptionGroup();

	/**
	 * Returns a new object of class '<em>Security Identity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Identity</em>'.
	 * @generated
	 */
	SecurityIdentity createSecurityIdentity();

	/**
	 * Returns a new object of class '<em>Use Caller Identity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Caller Identity</em>'.
	 * @generated
	 */
	UseCallerIdentity createUseCallerIdentity();

	/**
	 * Returns a new object of class '<em>Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description</em>'.
	 * @generated
	 */
	Description createDescription();

	/**
	 * Returns a new object of class '<em>QName</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>QName</em>'.
	 * @generated
	 */
	QName createQName();

	/**
	 * Returns a new object of class '<em>Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Listener</em>'.
	 * @generated
	 */
	Listener createListener();

	/**
	 * Returns a new object of class '<em>Compatibility Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Compatibility Description Group</em>'.
	 * @generated
	 */
	CompatibilityDescriptionGroup createCompatibilityDescriptionGroup();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CommonPackage getCommonPackage();

} //CommonFactory
