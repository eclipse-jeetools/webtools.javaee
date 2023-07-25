/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage
 * @generated
 */
public interface JcaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JcaFactory eINSTANCE = org.eclipse.jst.javaee.jca.internal.impl.JcaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Activation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Spec</em>'.
	 * @generated
	 */
	ActivationSpec createActivationSpec();

	/**
	 * Returns a new object of class '<em>Admin Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Admin Object</em>'.
	 * @generated
	 */
	AdminObject createAdminObject();

	/**
	 * Returns a new object of class '<em>Authentication Mechanism</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Authentication Mechanism</em>'.
	 * @generated
	 */
	AuthenticationMechanism createAuthenticationMechanism();

	/**
	 * Returns a new object of class '<em>Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Config Property</em>'.
	 * @generated
	 */
	ConfigProperty createConfigProperty();

	/**
	 * Returns a new object of class '<em>Connection Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Definition</em>'.
	 * @generated
	 */
	ConnectionDefinition createConnectionDefinition();

	/**
	 * Returns a new object of class '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector</em>'.
	 * @generated
	 */
	Connector createConnector();

	/**
	 * Returns a new object of class '<em>Connector Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Deployment Descriptor</em>'.
	 * @generated
	 */
	ConnectorDeploymentDescriptor createConnectorDeploymentDescriptor();

	/**
	 * Returns a new object of class '<em>Inbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inbound Resource Adapter</em>'.
	 * @generated
	 */
	InboundResourceAdapter createInboundResourceAdapter();

	/**
	 * Returns a new object of class '<em>License</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>License</em>'.
	 * @generated
	 */
	License createLicense();

	/**
	 * Returns a new object of class '<em>Message Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Adapter</em>'.
	 * @generated
	 */
	MessageAdapter createMessageAdapter();

	/**
	 * Returns a new object of class '<em>Message Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Listener</em>'.
	 * @generated
	 */
	MessageListener createMessageListener();

	/**
	 * Returns a new object of class '<em>Outbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outbound Resource Adapter</em>'.
	 * @generated
	 */
	OutboundResourceAdapter createOutboundResourceAdapter();

	/**
	 * Returns a new object of class '<em>Required Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Config Property</em>'.
	 * @generated
	 */
	RequiredConfigProperty createRequiredConfigProperty();

	/**
	 * Returns a new object of class '<em>Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Adapter</em>'.
	 * @generated
	 */
	ResourceAdapter createResourceAdapter();

	/**
	 * Returns a new object of class '<em>Security Permission</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Permission</em>'.
	 * @generated
	 */
	SecurityPermission createSecurityPermission();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JcaPackage getJcaPackage();

} //JcaFactory
