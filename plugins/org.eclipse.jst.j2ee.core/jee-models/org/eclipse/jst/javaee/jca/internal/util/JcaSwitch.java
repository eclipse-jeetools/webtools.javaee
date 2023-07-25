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
package org.eclipse.jst.javaee.jca.internal.util;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jst.javaee.jca.*;

import org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage
 * @generated
 */
public class JcaSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JcaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JcaSwitch() {
		if (modelPackage == null) {
			modelPackage = JcaPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case JcaPackage.ACTIVATION_SPEC: {
				ActivationSpec activationSpec = (ActivationSpec)theEObject;
				T result = caseActivationSpec(activationSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.ADMIN_OBJECT: {
				AdminObject adminObject = (AdminObject)theEObject;
				T result = caseAdminObject(adminObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.AUTHENTICATION_MECHANISM: {
				AuthenticationMechanism authenticationMechanism = (AuthenticationMechanism)theEObject;
				T result = caseAuthenticationMechanism(authenticationMechanism);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.CONFIG_PROPERTY: {
				ConfigProperty configProperty = (ConfigProperty)theEObject;
				T result = caseConfigProperty(configProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.CONNECTION_DEFINITION: {
				ConnectionDefinition connectionDefinition = (ConnectionDefinition)theEObject;
				T result = caseConnectionDefinition(connectionDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.CONNECTOR: {
				Connector connector = (Connector)theEObject;
				T result = caseConnector(connector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.CONNECTOR_DEPLOYMENT_DESCRIPTOR: {
				ConnectorDeploymentDescriptor connectorDeploymentDescriptor = (ConnectorDeploymentDescriptor)theEObject;
				T result = caseConnectorDeploymentDescriptor(connectorDeploymentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.INBOUND_RESOURCE_ADAPTER: {
				InboundResourceAdapter inboundResourceAdapter = (InboundResourceAdapter)theEObject;
				T result = caseInboundResourceAdapter(inboundResourceAdapter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.LICENSE: {
				License license = (License)theEObject;
				T result = caseLicense(license);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.MESSAGE_ADAPTER: {
				MessageAdapter messageAdapter = (MessageAdapter)theEObject;
				T result = caseMessageAdapter(messageAdapter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.MESSAGE_LISTENER: {
				MessageListener messageListener = (MessageListener)theEObject;
				T result = caseMessageListener(messageListener);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.OUTBOUND_RESOURCE_ADAPTER: {
				OutboundResourceAdapter outboundResourceAdapter = (OutboundResourceAdapter)theEObject;
				T result = caseOutboundResourceAdapter(outboundResourceAdapter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.REQUIRED_CONFIG_PROPERTY: {
				RequiredConfigProperty requiredConfigProperty = (RequiredConfigProperty)theEObject;
				T result = caseRequiredConfigProperty(requiredConfigProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.RESOURCE_ADAPTER: {
				ResourceAdapter resourceAdapter = (ResourceAdapter)theEObject;
				T result = caseResourceAdapter(resourceAdapter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JcaPackage.SECURITY_PERMISSION: {
				SecurityPermission securityPermission = (SecurityPermission)theEObject;
				T result = caseSecurityPermission(securityPermission);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivationSpec(ActivationSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Admin Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Admin Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdminObject(AdminObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Authentication Mechanism</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Authentication Mechanism</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAuthenticationMechanism(AuthenticationMechanism object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Config Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigProperty(ConfigProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionDefinition(ConnectionDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnector(Connector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector Deployment Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectorDeploymentDescriptor(ConnectorDeploymentDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inbound Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInboundResourceAdapter(InboundResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicense(License object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessageAdapter(MessageAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Listener</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessageListener(MessageListener object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outbound Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutboundResourceAdapter(OutboundResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Required Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Required Config Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequiredConfigProperty(RequiredConfigProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceAdapter(ResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurityPermission(SecurityPermission object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //JcaSwitch
