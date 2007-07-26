/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
public class JavaeeSwitch {
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
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case JavaeePackage.DESCRIPTION: {
				Description description = (Description)theEObject;
				Object result = caseDescription(description);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.DISPLAY_NAME: {
				DisplayName displayName = (DisplayName)theEObject;
				Object result = caseDisplayName(displayName);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.EJB_LOCAL_REF: {
				EjbLocalRef ejbLocalRef = (EjbLocalRef)theEObject;
				Object result = caseEjbLocalRef(ejbLocalRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.EJB_REF: {
				EjbRef ejbRef = (EjbRef)theEObject;
				Object result = caseEjbRef(ejbRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.EMPTY_TYPE: {
				EmptyType emptyType = (EmptyType)theEObject;
				Object result = caseEmptyType(emptyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.ENV_ENTRY: {
				EnvEntry envEntry = (EnvEntry)theEObject;
				Object result = caseEnvEntry(envEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.ICON: {
				Icon icon = (Icon)theEObject;
				Object result = caseIcon(icon);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.INJECTION_TARGET: {
				InjectionTarget injectionTarget = (InjectionTarget)theEObject;
				Object result = caseInjectionTarget(injectionTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.LIFECYCLE_CALLBACK: {
				LifecycleCallback lifecycleCallback = (LifecycleCallback)theEObject;
				Object result = caseLifecycleCallback(lifecycleCallback);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.LISTENER: {
				Listener listener = (Listener)theEObject;
				Object result = caseListener(listener);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.MESSAGE_DESTINATION: {
				MessageDestination messageDestination = (MessageDestination)theEObject;
				Object result = caseMessageDestination(messageDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.MESSAGE_DESTINATION_REF: {
				MessageDestinationRef messageDestinationRef = (MessageDestinationRef)theEObject;
				Object result = caseMessageDestinationRef(messageDestinationRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PARAM_VALUE: {
				ParamValue paramValue = (ParamValue)theEObject;
				Object result = caseParamValue(paramValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PERSISTENCE_CONTEXT_REF: {
				PersistenceContextRef persistenceContextRef = (PersistenceContextRef)theEObject;
				Object result = casePersistenceContextRef(persistenceContextRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PERSISTENCE_UNIT_REF: {
				PersistenceUnitRef persistenceUnitRef = (PersistenceUnitRef)theEObject;
				Object result = casePersistenceUnitRef(persistenceUnitRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PORT_COMPONENT_REF: {
				PortComponentRef portComponentRef = (PortComponentRef)theEObject;
				Object result = casePortComponentRef(portComponentRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.PROPERTY_TYPE: {
				PropertyType propertyType = (PropertyType)theEObject;
				Object result = casePropertyType(propertyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RESOURCE_ENV_REF: {
				ResourceEnvRef resourceEnvRef = (ResourceEnvRef)theEObject;
				Object result = caseResourceEnvRef(resourceEnvRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RESOURCE_REF: {
				ResourceRef resourceRef = (ResourceRef)theEObject;
				Object result = caseResourceRef(resourceRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.RUN_AS: {
				RunAs runAs = (RunAs)theEObject;
				Object result = caseRunAs(runAs);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SECURITY_ROLE: {
				SecurityRole securityRole = (SecurityRole)theEObject;
				Object result = caseSecurityRole(securityRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SECURITY_ROLE_REF: {
				SecurityRoleRef securityRoleRef = (SecurityRoleRef)theEObject;
				Object result = caseSecurityRoleRef(securityRoleRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF: {
				ServiceRef serviceRef = (ServiceRef)theEObject;
				Object result = caseServiceRef(serviceRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF_HANDLER: {
				ServiceRefHandler serviceRefHandler = (ServiceRefHandler)theEObject;
				Object result = caseServiceRefHandler(serviceRefHandler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN: {
				ServiceRefHandlerChain serviceRefHandlerChain = (ServiceRefHandlerChain)theEObject;
				Object result = caseServiceRefHandlerChain(serviceRefHandlerChain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS: {
				ServiceRefHandlerChains serviceRefHandlerChains = (ServiceRefHandlerChains)theEObject;
				Object result = caseServiceRefHandlerChains(serviceRefHandlerChains);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case JavaeePackage.URL_PATTERN_TYPE: {
				UrlPatternType urlPatternType = (UrlPatternType)theEObject;
				Object result = caseUrlPatternType(urlPatternType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDescription(Description object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Display Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDisplayName(DisplayName object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Ejb Local Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Ejb Local Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEjbLocalRef(EjbLocalRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Ejb Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Ejb Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEjbRef(EjbRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Empty Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Empty Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEmptyType(EmptyType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Env Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Env Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEnvEntry(EnvEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Icon</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Icon</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIcon(Icon object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Injection Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Injection Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInjectionTarget(InjectionTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Lifecycle Callback</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Lifecycle Callback</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLifecycleCallback(LifecycleCallback object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Listener</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Listener</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseListener(Listener object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Message Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageDestination(MessageDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Message Destination Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Destination Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageDestinationRef(MessageDestinationRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Param Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Param Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseParamValue(ParamValue object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Persistence Context Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Persistence Context Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePersistenceContextRef(PersistenceContextRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Persistence Unit Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Persistence Unit Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePersistenceUnitRef(PersistenceUnitRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Port Component Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Port Component Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePortComponentRef(PortComponentRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Property Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePropertyType(PropertyType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Resource Env Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Resource Env Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseResourceEnvRef(ResourceEnvRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Resource Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Resource Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseResourceRef(ResourceRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Run As</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Run As</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRunAs(RunAs object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityRole(SecurityRole object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Role Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Role Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityRoleRef(SecurityRoleRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Service Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Service Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServiceRef(ServiceRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Service Ref Handler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Service Ref Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServiceRefHandler(ServiceRefHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Service Ref Handler Chain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Service Ref Handler Chain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServiceRefHandlerChain(ServiceRefHandlerChain object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Service Ref Handler Chains</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Service Ref Handler Chains</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseServiceRefHandlerChains(ServiceRefHandlerChains object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Url Pattern Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Url Pattern Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUrlPatternType(UrlPatternType object) {
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

} //JavaeeSwitch
