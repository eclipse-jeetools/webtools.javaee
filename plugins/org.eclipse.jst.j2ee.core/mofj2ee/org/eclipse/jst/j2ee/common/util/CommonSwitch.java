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
package org.eclipse.jst.j2ee.common.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.UseCallerIdentity;



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
 * @see org.eclipse.jst.j2ee.common.CommonPackage
 * @generated
 */
public class CommonSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CommonPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommonSwitch() {
		if (modelPackage == null) {
			modelPackage = CommonPackage.eINSTANCE;
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
				case CommonPackage.EJB_REF: {
					EjbRef ejbRef = (EjbRef)theEObject;
					Object result = caseEjbRef(ejbRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.ENV_ENTRY: {
					EnvEntry envEntry = (EnvEntry)theEObject;
					Object result = caseEnvEntry(envEntry);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.RESOURCE_REF: {
					ResourceRef resourceRef = (ResourceRef)theEObject;
					Object result = caseResourceRef(resourceRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.SECURITY_ROLE_REF: {
					SecurityRoleRef securityRoleRef = (SecurityRoleRef)theEObject;
					Object result = caseSecurityRoleRef(securityRoleRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.SECURITY_ROLE: {
					SecurityRole securityRole = (SecurityRole)theEObject;
					Object result = caseSecurityRole(securityRole);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.RESOURCE_ENV_REF: {
					ResourceEnvRef resourceEnvRef = (ResourceEnvRef)theEObject;
					Object result = caseResourceEnvRef(resourceEnvRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.EJB_LOCAL_REF: {
					EJBLocalRef ejbLocalRef = (EJBLocalRef)theEObject;
					Object result = caseEJBLocalRef(ejbLocalRef);
					if (result == null) result = caseEjbRef(ejbLocalRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.RUN_AS_SPECIFIED_IDENTITY: {
					RunAsSpecifiedIdentity runAsSpecifiedIdentity = (RunAsSpecifiedIdentity)theEObject;
					Object result = caseRunAsSpecifiedIdentity(runAsSpecifiedIdentity);
					if (result == null) result = caseSecurityIdentity(runAsSpecifiedIdentity);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.IDENTITY: {
					Identity identity = (Identity)theEObject;
					Object result = caseIdentity(identity);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.ICON_TYPE: {
					IconType iconType = (IconType)theEObject;
					Object result = caseIconType(iconType);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.DISPLAY_NAME: {
					DisplayName displayName = (DisplayName)theEObject;
					Object result = caseDisplayName(displayName);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.MESSAGE_DESTINATION_REF: {
					MessageDestinationRef messageDestinationRef = (MessageDestinationRef)theEObject;
					Object result = caseMessageDestinationRef(messageDestinationRef);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.MESSAGE_DESTINATION: {
					MessageDestination messageDestination = (MessageDestination)theEObject;
					Object result = caseMessageDestination(messageDestination);
					if (result == null) result = caseCompatibilityDescriptionGroup(messageDestination);
					if (result == null) result = caseDescriptionGroup(messageDestination);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.PARAM_VALUE: {
					ParamValue paramValue = (ParamValue)theEObject;
					Object result = caseParamValue(paramValue);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.DESCRIPTION_GROUP: {
					DescriptionGroup descriptionGroup = (DescriptionGroup)theEObject;
					Object result = caseDescriptionGroup(descriptionGroup);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.SECURITY_IDENTITY: {
					SecurityIdentity securityIdentity = (SecurityIdentity)theEObject;
					Object result = caseSecurityIdentity(securityIdentity);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.USE_CALLER_IDENTITY: {
					UseCallerIdentity useCallerIdentity = (UseCallerIdentity)theEObject;
					Object result = caseUseCallerIdentity(useCallerIdentity);
					if (result == null) result = caseSecurityIdentity(useCallerIdentity);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.DESCRIPTION: {
					Description description = (Description)theEObject;
					Object result = caseDescription(description);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.QNAME: {
					QName qName = (QName)theEObject;
					Object result = caseQName(qName);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.LISTENER: {
					Listener listener = (Listener)theEObject;
					Object result = caseListener(listener);
					if (result == null) result = caseCompatibilityDescriptionGroup(listener);
					if (result == null) result = caseDescriptionGroup(listener);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP: {
					CompatibilityDescriptionGroup compatibilityDescriptionGroup = (CompatibilityDescriptionGroup)theEObject;
					Object result = caseCompatibilityDescriptionGroup(compatibilityDescriptionGroup);
					if (result == null) result = caseDescriptionGroup(compatibilityDescriptionGroup);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
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
	 * Returns the result of interpretting the object as an instance of '<em>EJB Local Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Local Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEJBLocalRef(EJBLocalRef object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Run As Specified Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Run As Specified Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRunAsSpecifiedIdentity(RunAsSpecifiedIdentity object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityIdentity(SecurityIdentity object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIdentity(Identity object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Icon Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Icon Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIconType(IconType object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>JNDI Env Refs Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>JNDI Env Refs Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJNDIEnvRefsGroup(JNDIEnvRefsGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Use Caller Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Use Caller Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUseCallerIdentity(UseCallerIdentity object) {
		return null;
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
	 * Returns the result of interpretting the object as an instance of '<em>QName</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>QName</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseQName(QName object) {
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

} //CommonSwitch
