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
package org.eclipse.jst.j2ee.internal.jca.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.AdminObject;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.InboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.MessageAdapter;
import org.eclipse.jst.j2ee.jca.MessageListener;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;



/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage
 * @generated
 */
public class JcaSwitch {
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
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case JcaPackage.CONNECTOR: {
					Connector connector = (Connector)theEObject;
					Object result = caseConnector(connector);
					if (result == null) result = caseCompatibilityDescriptionGroup(connector);
					if (result == null) result = caseDescriptionGroup(connector);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.RESOURCE_ADAPTER: {
					ResourceAdapter resourceAdapter = (ResourceAdapter)theEObject;
					Object result = caseResourceAdapter(resourceAdapter);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.AUTHENTICATION_MECHANISM: {
					AuthenticationMechanism authenticationMechanism = (AuthenticationMechanism)theEObject;
					Object result = caseAuthenticationMechanism(authenticationMechanism);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.CONFIG_PROPERTY: {
					ConfigProperty configProperty = (ConfigProperty)theEObject;
					Object result = caseConfigProperty(configProperty);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.SECURITY_PERMISSION: {
					SecurityPermission securityPermission = (SecurityPermission)theEObject;
					Object result = caseSecurityPermission(securityPermission);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.LICENSE: {
					License license = (License)theEObject;
					Object result = caseLicense(license);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.INBOUND_RESOURCE_ADAPTER: {
					InboundResourceAdapter inboundResourceAdapter = (InboundResourceAdapter)theEObject;
					Object result = caseInboundResourceAdapter(inboundResourceAdapter);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.OUTBOUND_RESOURCE_ADAPTER: {
					OutboundResourceAdapter outboundResourceAdapter = (OutboundResourceAdapter)theEObject;
					Object result = caseOutboundResourceAdapter(outboundResourceAdapter);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.MESSAGE_ADAPTER: {
					MessageAdapter messageAdapter = (MessageAdapter)theEObject;
					Object result = caseMessageAdapter(messageAdapter);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.CONNECTION_DEFINITION: {
					ConnectionDefinition connectionDefinition = (ConnectionDefinition)theEObject;
					Object result = caseConnectionDefinition(connectionDefinition);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.ADMIN_OBJECT: {
					AdminObject adminObject = (AdminObject)theEObject;
					Object result = caseAdminObject(adminObject);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.MESSAGE_LISTENER: {
					MessageListener messageListener = (MessageListener)theEObject;
					Object result = caseMessageListener(messageListener);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.ACTIVATION_SPEC: {
					ActivationSpec activationSpec = (ActivationSpec)theEObject;
					Object result = caseActivationSpec(activationSpec);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case JcaPackage.REQUIRED_CONFIG_PROPERTY_TYPE: {
					RequiredConfigPropertyType requiredConfigPropertyType = (RequiredConfigPropertyType)theEObject;
					Object result = caseRequiredConfigPropertyType(requiredConfigPropertyType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConnector(Connector object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseResourceAdapter(ResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Authentication Mechanism</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Authentication Mechanism</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAuthenticationMechanism(AuthenticationMechanism object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Config Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConfigProperty(ConfigProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityPermission(SecurityPermission object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>License</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>License</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLicense(License object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Inbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Inbound Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInboundResourceAdapter(InboundResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Outbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Outbound Resource Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseOutboundResourceAdapter(OutboundResourceAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Message Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Adapter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageAdapter(MessageAdapter object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Connection Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Connection Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConnectionDefinition(ConnectionDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Admin Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Admin Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAdminObject(AdminObject object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Message Listener</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageListener(MessageListener object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Activation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Activation Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseActivationSpec(ActivationSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Required Config Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Required Config Property Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRequiredConfigPropertyType(RequiredConfigPropertyType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDescriptionGroup(DescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //JcaSwitch
