/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage
 * @generated
 */
public interface JavaeeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JavaeeFactory eINSTANCE = org.eclipse.jst.javaee.core.internal.impl.JavaeeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Addressing Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Addressing Type</em>'.
	 * @generated
	 */
	AddressingType createAddressingType();

	/**
	 * Returns a new object of class '<em>Administered Object Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Administered Object Type</em>'.
	 * @generated
	 */
	AdministeredObjectType createAdministeredObjectType();

	/**
	 * Returns a new object of class '<em>Connection Factory Resource Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Factory Resource Type</em>'.
	 * @generated
	 */
	ConnectionFactoryResourceType createConnectionFactoryResourceType();

	/**
	 * Returns a new object of class '<em>Data Source Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Source Type</em>'.
	 * @generated
	 */
	DataSourceType createDataSourceType();

	/**
	 * Returns a new object of class '<em>Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description</em>'.
	 * @generated
	 */
	Description createDescription();

	/**
	 * Returns a new object of class '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Display Name</em>'.
	 * @generated
	 */
	DisplayName createDisplayName();

	/**
	 * Returns a new object of class '<em>Ejb Local Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ejb Local Ref</em>'.
	 * @generated
	 */
	EjbLocalRef createEjbLocalRef();

	/**
	 * Returns a new object of class '<em>Ejb Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ejb Ref</em>'.
	 * @generated
	 */
	EjbRef createEjbRef();

	/**
	 * Returns a new object of class '<em>Empty Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Empty Type</em>'.
	 * @generated
	 */
	EmptyType createEmptyType();

	/**
	 * Returns a new object of class '<em>Env Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Env Entry</em>'.
	 * @generated
	 */
	EnvEntry createEnvEntry();

	/**
	 * Returns a new object of class '<em>Icon</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Icon</em>'.
	 * @generated
	 */
	Icon createIcon();

	/**
	 * Returns a new object of class '<em>Injection Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Injection Target</em>'.
	 * @generated
	 */
	InjectionTarget createInjectionTarget();

	/**
	 * Returns a new object of class '<em>Jms Connection Factory Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Jms Connection Factory Type</em>'.
	 * @generated
	 */
	JmsConnectionFactoryType createJmsConnectionFactoryType();

	/**
	 * Returns a new object of class '<em>Jms Destination Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Jms Destination Type</em>'.
	 * @generated
	 */
	JmsDestinationType createJmsDestinationType();

	/**
	 * Returns a new object of class '<em>Lifecycle Callback</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lifecycle Callback</em>'.
	 * @generated
	 */
	LifecycleCallback createLifecycleCallback();

	/**
	 * Returns a new object of class '<em>Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Listener</em>'.
	 * @generated
	 */
	Listener createListener();

	/**
	 * Returns a new object of class '<em>Mail Session Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mail Session Type</em>'.
	 * @generated
	 */
	MailSessionType createMailSessionType();

	/**
	 * Returns a new object of class '<em>Message Destination</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Destination</em>'.
	 * @generated
	 */
	MessageDestination createMessageDestination();

	/**
	 * Returns a new object of class '<em>Message Destination Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Destination Ref</em>'.
	 * @generated
	 */
	MessageDestinationRef createMessageDestinationRef();

	/**
	 * Returns a new object of class '<em>Param Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Param Value</em>'.
	 * @generated
	 */
	ParamValue createParamValue();

	/**
	 * Returns a new object of class '<em>Persistence Context Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persistence Context Ref</em>'.
	 * @generated
	 */
	PersistenceContextRef createPersistenceContextRef();

	/**
	 * Returns a new object of class '<em>Persistence Unit Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persistence Unit Ref</em>'.
	 * @generated
	 */
	PersistenceUnitRef createPersistenceUnitRef();

	/**
	 * Returns a new object of class '<em>Port Component Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Component Ref</em>'.
	 * @generated
	 */
	PortComponentRef createPortComponentRef();

	/**
	 * Returns a new object of class '<em>Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Type</em>'.
	 * @generated
	 */
	PropertyType createPropertyType();

	/**
	 * Returns a new object of class '<em>Resource Env Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Env Ref</em>'.
	 * @generated
	 */
	ResourceEnvRef createResourceEnvRef();

	/**
	 * Returns a new object of class '<em>Resource Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Ref</em>'.
	 * @generated
	 */
	ResourceRef createResourceRef();

	/**
	 * Returns a new object of class '<em>Respect Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Respect Binding Type</em>'.
	 * @generated
	 */
	RespectBindingType createRespectBindingType();

	/**
	 * Returns a new object of class '<em>Run As</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run As</em>'.
	 * @generated
	 */
	RunAs createRunAs();

	/**
	 * Returns a new object of class '<em>Security Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Role</em>'.
	 * @generated
	 */
	SecurityRole createSecurityRole();

	/**
	 * Returns a new object of class '<em>Security Role Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Role Ref</em>'.
	 * @generated
	 */
	SecurityRoleRef createSecurityRoleRef();

	/**
	 * Returns a new object of class '<em>Service Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref</em>'.
	 * @generated
	 */
	ServiceRef createServiceRef();

	/**
	 * Returns a new object of class '<em>Service Ref Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref Handler</em>'.
	 * @generated
	 */
	ServiceRefHandler createServiceRefHandler();

	/**
	 * Returns a new object of class '<em>Service Ref Handler Chain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref Handler Chain</em>'.
	 * @generated
	 */
	ServiceRefHandlerChain createServiceRefHandlerChain();

	/**
	 * Returns a new object of class '<em>Service Ref Handler Chains</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref Handler Chains</em>'.
	 * @generated
	 */
	ServiceRefHandlerChains createServiceRefHandlerChains();

	/**
	 * Returns a new object of class '<em>Url Pattern Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Url Pattern Type</em>'.
	 * @generated
	 */
	UrlPatternType createUrlPatternType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JavaeePackage getJavaeePackage();

} //JavaeeFactory
