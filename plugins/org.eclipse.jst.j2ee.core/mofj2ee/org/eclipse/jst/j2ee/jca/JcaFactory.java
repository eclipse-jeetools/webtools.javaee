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
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
/**
 * @generated
 */
public interface JcaFactory extends EFactory{

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JcaFactory eINSTANCE = new org.eclipse.jst.j2ee.internal.jca.impl.JcaFactoryImpl();


	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Connector value
	 */
	Connector createConnector();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return License value
	 */
	License createLicense();

	/**
	 * Returns a new object of class '<em>Inbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inbound Resource Adapter</em>'.
	 * @generated
	 */
	InboundResourceAdapter createInboundResourceAdapter();

	/**
	 * Returns a new object of class '<em>Outbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outbound Resource Adapter</em>'.
	 * @generated
	 */
	OutboundResourceAdapter createOutboundResourceAdapter();

	/**
	 * Returns a new object of class '<em>Message Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Adapter</em>'.
	 * @generated
	 */
	MessageAdapter createMessageAdapter();

	/**
	 * Returns a new object of class '<em>Connection Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Definition</em>'.
	 * @generated
	 */
	ConnectionDefinition createConnectionDefinition();

	/**
	 * Returns a new object of class '<em>Admin Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Admin Object</em>'.
	 * @generated
	 */
	AdminObject createAdminObject();

	/**
	 * Returns a new object of class '<em>Message Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Listener</em>'.
	 * @generated
	 */
	MessageListener createMessageListener();

	/**
	 * Returns a new object of class '<em>Activation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Spec</em>'.
	 * @generated
	 */
	ActivationSpec createActivationSpec();

	/**
	 * Returns a new object of class '<em>Required Config Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Config Property Type</em>'.
	 * @generated
	 */
	RequiredConfigPropertyType createRequiredConfigPropertyType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ResourceAdapter value
	 */
	ResourceAdapter createResourceAdapter();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityPermission value
	 */
	SecurityPermission createSecurityPermission();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AuthenticationMechanism value
	 */
	AuthenticationMechanism createAuthenticationMechanism();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ConfigProperty value
	 */
	ConfigProperty createConfigProperty();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	JcaPackage getJcaPackage();

}



















































































































































