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
package org.eclipse.jst.javaee.core.internal.util;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jst.javaee.core.*;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

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
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage
 * @generated
 */
public class JavaeeSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JavaeePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeeSwitch() {
		if (modelPackage == null) {
			modelPackage = JavaeePackage.eINSTANCE;
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
			case JavaeePackage.ADDRESSING_TYPE: {
				AddressingType addressingType = (AddressingType)theEObject;
				T result = caseAddressingType(addressingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.ADMINISTERED_OBJECT_TYPE: {
				AdministeredObjectType administeredObjectType = (AdministeredObjectType)theEObject;
				T result = caseAdministeredObjectType(administeredObjectType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE: {
				ConnectionFactoryResourceType connectionFactoryResourceType = (ConnectionFactoryResourceType)theEObject;
				T result = caseConnectionFactoryResourceType(connectionFactoryResourceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.DATA_SOURCE_TYPE: {
				DataSourceType dataSourceType = (DataSourceType)theEObject;
				T result = caseDataSourceType(dataSourceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.DESCRIPTION: {
				Description description = (Description)theEObject;
				T result = caseDescription(description);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.DISPLAY_NAME: {
				DisplayName displayName = (DisplayName)theEObject;
				T result = caseDisplayName(displayName);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.EJB_LOCAL_REF: {
				EjbLocalRef ejbLocalRef = (EjbLocalRef)theEObject;
				T result = caseEjbLocalRef(ejbLocalRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.EJB_REF: {
				EjbRef ejbRef = (EjbRef)theEObject;
				T result = caseEjbRef(ejbRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.EMPTY_TYPE: {
				EmptyType emptyType = (EmptyType)theEObject;
				T result = caseEmptyType(emptyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.ENV_ENTRY: {
				EnvEntry envEntry = (EnvEntry)theEObject;
				T result = caseEnvEntry(envEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.ICON: {
				Icon icon = (Icon)theEObject;
				T result = caseIcon(icon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.INJECTION_TARGET: {
				InjectionTarget injectionTarget = (InjectionTarget)theEObject;
				T result = caseInjectionTarget(injectionTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE: {
				JmsConnectionFactoryType jmsConnectionFactoryType = (JmsConnectionFactoryType)theEObject;
				T result = caseJmsConnectionFactoryType(jmsConnectionFactoryType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.JMS_DESTINATION_TYPE: {
				JmsDestinationType jmsDestinationType = (JmsDestinationType)theEObject;
				T result = caseJmsDestinationType(jmsDestinationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.LIFECYCLE_CALLBACK: {
				LifecycleCallback lifecycleCallback = (LifecycleCallback)theEObject;
				T result = caseLifecycleCallback(lifecycleCallback);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.LISTENER: {
				Listener listener = (Listener)theEObject;
				T result = caseListener(listener);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.MAIL_SESSION_TYPE: {
				MailSessionType mailSessionType = (MailSessionType)theEObject;
				T result = caseMailSessionType(mailSessionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.MESSAGE_DESTINATION: {
				MessageDestination messageDestination = (MessageDestination)theEObject;
				T result = caseMessageDestination(messageDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.MESSAGE_DESTINATION_REF: {
				MessageDestinationRef messageDestinationRef = (MessageDestinationRef)theEObject;
				T result = caseMessageDestinationRef(messageDestinationRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PARAM_VALUE: {
				ParamValue paramValue = (ParamValue)theEObject;
				T result = caseParamValue(paramValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PERSISTENCE_CONTEXT_REF: {
				PersistenceContextRef persistenceContextRef = (PersistenceContextRef)theEObject;
				T result = casePersistenceContextRef(persistenceContextRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PERSISTENCE_UNIT_REF: {
				PersistenceUnitRef persistenceUnitRef = (PersistenceUnitRef)theEObject;
				T result = casePersistenceUnitRef(persistenceUnitRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PORT_COMPONENT_REF: {
				PortComponentRef portComponentRef = (PortComponentRef)theEObject;
				T result = casePortComponentRef(portComponentRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PROPERTY_TYPE: {
				PropertyType propertyType = (PropertyType)theEObject;
				T result = casePropertyType(propertyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RESOURCE_ENV_REF: {
				ResourceEnvRef resourceEnvRef = (ResourceEnvRef)theEObject;
				T result = caseResourceEnvRef(resourceEnvRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RESOURCE_REF: {
				ResourceRef resourceRef = (ResourceRef)theEObject;
				T result = caseResourceRef(resourceRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RESPECT_BINDING_TYPE: {
				RespectBindingType respectBindingType = (RespectBindingType)theEObject;
				T result = caseRespectBindingType(respectBindingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RUN_AS: {
				RunAs runAs = (RunAs)theEObject;
				T result = caseRunAs(runAs);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SECURITY_ROLE: {
				SecurityRole securityRole = (SecurityRole)theEObject;
				T result = caseSecurityRole(securityRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SECURITY_ROLE_REF: {
				SecurityRoleRef securityRoleRef = (SecurityRoleRef)theEObject;
				T result = caseSecurityRoleRef(securityRoleRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF: {
				ServiceRef serviceRef = (ServiceRef)theEObject;
				T result = caseServiceRef(serviceRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF_HANDLER: {
				ServiceRefHandler serviceRefHandler = (ServiceRefHandler)theEObject;
				T result = caseServiceRefHandler(serviceRefHandler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN: {
				ServiceRefHandlerChain serviceRefHandlerChain = (ServiceRefHandlerChain)theEObject;
				T result = caseServiceRefHandlerChain(serviceRefHandlerChain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS: {
				ServiceRefHandlerChains serviceRefHandlerChains = (ServiceRefHandlerChains)theEObject;
				T result = caseServiceRefHandlerChains(serviceRefHandlerChains);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.URL_PATTERN_TYPE: {
				UrlPatternType urlPatternType = (UrlPatternType)theEObject;
				T result = caseUrlPatternType(urlPatternType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Addressing Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Addressing Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddressingType(AddressingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Administered Object Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Administered Object Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdministeredObjectType(AdministeredObjectType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Factory Resource Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Factory Resource Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionFactoryResourceType(ConnectionFactoryResourceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Source Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Source Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataSourceType(DataSourceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDescription(Description object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Display Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDisplayName(DisplayName object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ejb Local Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ejb Local Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEjbLocalRef(EjbLocalRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ejb Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ejb Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEjbRef(EjbRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Empty Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Empty Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEmptyType(EmptyType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Env Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Env Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvEntry(EnvEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Icon</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Icon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIcon(Icon object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Injection Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Injection Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInjectionTarget(InjectionTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Jms Connection Factory Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Jms Connection Factory Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJmsConnectionFactoryType(JmsConnectionFactoryType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Jms Destination Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Jms Destination Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJmsDestinationType(JmsDestinationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lifecycle Callback</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lifecycle Callback</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLifecycleCallback(LifecycleCallback object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Listener</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListener(Listener object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mail Session Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mail Session Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMailSessionType(MailSessionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessageDestination(MessageDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Destination Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Destination Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessageDestinationRef(MessageDestinationRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Param Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Param Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParamValue(ParamValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Persistence Context Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persistence Context Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePersistenceContextRef(PersistenceContextRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Persistence Unit Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persistence Unit Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePersistenceUnitRef(PersistenceUnitRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Component Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Component Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortComponentRef(PortComponentRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyType(PropertyType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Env Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Env Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceEnvRef(ResourceEnvRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceRef(ResourceRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Respect Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Respect Binding Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRespectBindingType(RespectBindingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Run As</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run As</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunAs(RunAs object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurityRole(SecurityRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Role Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Role Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurityRoleRef(SecurityRoleRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceRef(ServiceRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Ref Handler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Ref Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceRefHandler(ServiceRefHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Ref Handler Chain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Ref Handler Chain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceRefHandlerChain(ServiceRefHandlerChain object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Ref Handler Chains</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Ref Handler Chains</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceRefHandlerChains(ServiceRefHandlerChains object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Url Pattern Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Url Pattern Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUrlPatternType(UrlPatternType object) {
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

} //JavaeeSwitch
