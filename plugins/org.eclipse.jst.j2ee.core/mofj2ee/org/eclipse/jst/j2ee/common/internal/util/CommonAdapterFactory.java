/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;

import org.eclipse.jst.j2ee.common.*;

import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.jst.j2ee.common.J2EEEObject;
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



public class CommonAdapterFactory extends AdapterFactoryImpl {
	protected static CommonPackage modelPackage;

	public CommonAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
		}
	}
	@Override
	public boolean isFactoryForType(Object type) {
		if (type == modelPackage) {
			return true;
		}
		if (type instanceof EObject) {
			return ((EObject)type).eClass().eContainer() == modelPackage;
		}
		return false;
	}

	@Override
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	public Adapter createSecurityRoleAdapter() {
		return null;
	}

	public Adapter createResourceRefAdapter() {
		return null;
	}

	public Adapter createEjbRefAdapter() {
		return null;
	}

	public Adapter createEJBLocalRefAdapter() {
		return null;
	}

	public Adapter createEnvEntryAdapter() {
		return null;
	}

	public Adapter createSecurityRoleRefAdapter() {
		return null;
	}

	public Adapter createRunAsSpecifiedIdentityAdapter() {
		return null;
	}

	public Adapter createSecurityIdentityAdapter() {
		return null;
	}

	public Adapter createUseCallerIdentityAdapter() {
		return null;
	}

	public Adapter createIdentityAdapter() {
		return null;
	}

	public Adapter createResourceEnvRefAdapter() {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForTypeGen(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommonSwitch modelSwitch =
		new CommonSwitch() {
			@Override
			public Object caseEjbRef(EjbRef object) {
				return createEjbRefAdapter();
			}
			@Override
			public Object caseEnvEntry(EnvEntry object) {
				return createEnvEntryAdapter();
			}
			@Override
			public Object caseResourceRef(ResourceRef object) {
				return createResourceRefAdapter();
			}
			@Override
			public Object caseSecurityRoleRef(SecurityRoleRef object) {
				return createSecurityRoleRefAdapter();
			}
			@Override
			public Object caseSecurityRole(SecurityRole object) {
				return createSecurityRoleAdapter();
			}
			@Override
			public Object caseResourceEnvRef(ResourceEnvRef object) {
				return createResourceEnvRefAdapter();
			}
			@Override
			public Object caseEJBLocalRef(EJBLocalRef object) {
				return createEJBLocalRefAdapter();
			}
			@Override
			public Object caseRunAsSpecifiedIdentity(RunAsSpecifiedIdentity object) {
				return createRunAsSpecifiedIdentityAdapter();
			}
			@Override
			public Object caseIdentity(Identity object) {
				return createIdentityAdapter();
			}
			@Override
			public Object caseIconType(IconType object) {
				return createIconTypeAdapter();
			}
			@Override
			public Object caseDisplayName(DisplayName object) {
				return createDisplayNameAdapter();
			}
			@Override
			public Object caseMessageDestinationRef(MessageDestinationRef object) {
				return createMessageDestinationRefAdapter();
			}
			@Override
			public Object caseMessageDestination(MessageDestination object) {
				return createMessageDestinationAdapter();
			}
			@Override
			public Object caseParamValue(ParamValue object) {
				return createParamValueAdapter();
			}
			@Override
			public Object caseDescriptionGroup(DescriptionGroup object) {
				return createDescriptionGroupAdapter();
			}
			@Override
			public Object caseJNDIEnvRefsGroup(JNDIEnvRefsGroup object) {
				return createJNDIEnvRefsGroupAdapter();
			}
			@Override
			public Object caseSecurityIdentity(SecurityIdentity object) {
				return createSecurityIdentityAdapter();
			}
			@Override
			public Object caseUseCallerIdentity(UseCallerIdentity object) {
				return createUseCallerIdentityAdapter();
			}
			@Override
			public Object caseDescription(Description object) {
				return createDescriptionAdapter();
			}
			@Override
			public Object caseQName(QName object) {
				return createQNameAdapter();
			}
			@Override
			public Object caseListener(Listener object) {
				return createListenerAdapter();
			}
			@Override
			public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
				return createCompatibilityDescriptionGroupAdapter();
			}
			@Override
			public Object caseJ2EEEObject(J2EEEObject object) {
				return createJ2EEEObjectAdapter();
			}
			@Override
			public Object caseJ2EEEAttribute(J2EEEAttribute object) {
				return createJ2EEEAttributeAdapter();
			}
			@Override
			public Object caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			@Override
			public Object caseENamedElement(ENamedElement object) {
				return createENamedElementAdapter();
			}
			@Override
			public Object caseETypedElement(ETypedElement object) {
				return createETypedElementAdapter();
			}
			@Override
			public Object caseEStructuralFeature(EStructuralFeature object) {
				return createEStructuralFeatureAdapter();
			}
			@Override
			public Object caseEAttribute(EAttribute object) {
				return createEAttributeAdapter();
			}
			@Override
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapterGen(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSecurityRoleAdapterGen() {

		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createResourceRefAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEjbRefAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBLocalRefAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEnvEntryAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSecurityRoleRefAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createRunAsSpecifiedIdentityAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSecurityIdentityAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createUseCallerIdentityAdapterGen() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.Description <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.Description
	 * @generated
	 */
	public Adapter createDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.QName <em>QName</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.QName
	 * @generated
	 */
	public Adapter createQNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.Listener <em>Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.Listener
	 * @generated
	 */
	public Adapter createListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup <em>Compatibility Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup
	 * @generated
	 */
	public Adapter createCompatibilityDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.J2EEEObject <em>J2EEE Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.J2EEEObject
	 * @generated
	 */
	public Adapter createJ2EEEObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.J2EEEAttribute <em>J2EEE Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.J2EEEAttribute
	 * @generated
	 */
	public Adapter createJ2EEEAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @generated
	 */
	public Adapter createENamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ETypedElement <em>ETyped Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ETypedElement
	 * @generated
	 */
	public Adapter createETypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EStructuralFeature
	 * @generated
	 */
	public Adapter createEStructuralFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EAttribute <em>EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EAttribute
	 * @generated
	 */
	public Adapter createEAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createIdentityAdapterGen() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.IconType <em>Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.IconType
	 * @generated
	 */
	public Adapter createIconTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.DisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.DisplayName
	 * @generated
	 */
	public Adapter createDisplayNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef <em>Message Destination Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef
	 * @generated
	 */
	public Adapter createMessageDestinationRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.MessageDestination <em>Message Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.MessageDestination
	 * @generated
	 */
	public Adapter createMessageDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.ParamValue <em>Param Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.ParamValue
	 * @generated
	 */
	public Adapter createParamValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.DescriptionGroup <em>Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.DescriptionGroup
	 * @generated
	 */
	public Adapter createDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup <em>JNDI Env Refs Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup
	 * @generated
	 */
	public Adapter createJNDIEnvRefsGroupAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createResourceEnvRefAdapterGen() {
		return null;
	}

} //CommonAdapterFactory


