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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.javaee.core.*;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage
 * @generated
 */
public class JavaeeAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JavaeePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeeAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = JavaeePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavaeeSwitch<Adapter> modelSwitch =
		new JavaeeSwitch<Adapter>() {
			@Override
			public Adapter caseAddressingType(AddressingType object) {
				return createAddressingTypeAdapter();
			}
			@Override
			public Adapter caseAdministeredObjectType(AdministeredObjectType object) {
				return createAdministeredObjectTypeAdapter();
			}
			@Override
			public Adapter caseConnectionFactoryResourceType(ConnectionFactoryResourceType object) {
				return createConnectionFactoryResourceTypeAdapter();
			}
			@Override
			public Adapter caseDataSourceType(DataSourceType object) {
				return createDataSourceTypeAdapter();
			}
			@Override
			public Adapter caseDescription(Description object) {
				return createDescriptionAdapter();
			}
			@Override
			public Adapter caseDisplayName(DisplayName object) {
				return createDisplayNameAdapter();
			}
			@Override
			public Adapter caseEjbLocalRef(EjbLocalRef object) {
				return createEjbLocalRefAdapter();
			}
			@Override
			public Adapter caseEjbRef(EjbRef object) {
				return createEjbRefAdapter();
			}
			@Override
			public Adapter caseEmptyType(EmptyType object) {
				return createEmptyTypeAdapter();
			}
			@Override
			public Adapter caseEnvEntry(EnvEntry object) {
				return createEnvEntryAdapter();
			}
			@Override
			public Adapter caseIcon(Icon object) {
				return createIconAdapter();
			}
			@Override
			public Adapter caseInjectionTarget(InjectionTarget object) {
				return createInjectionTargetAdapter();
			}
			@Override
			public Adapter caseJmsConnectionFactoryType(JmsConnectionFactoryType object) {
				return createJmsConnectionFactoryTypeAdapter();
			}
			@Override
			public Adapter caseJmsDestinationType(JmsDestinationType object) {
				return createJmsDestinationTypeAdapter();
			}
			@Override
			public Adapter caseLifecycleCallback(LifecycleCallback object) {
				return createLifecycleCallbackAdapter();
			}
			@Override
			public Adapter caseListener(Listener object) {
				return createListenerAdapter();
			}
			@Override
			public Adapter caseMailSessionType(MailSessionType object) {
				return createMailSessionTypeAdapter();
			}
			@Override
			public Adapter caseMessageDestination(MessageDestination object) {
				return createMessageDestinationAdapter();
			}
			@Override
			public Adapter caseMessageDestinationRef(MessageDestinationRef object) {
				return createMessageDestinationRefAdapter();
			}
			@Override
			public Adapter caseParamValue(ParamValue object) {
				return createParamValueAdapter();
			}
			@Override
			public Adapter casePersistenceContextRef(PersistenceContextRef object) {
				return createPersistenceContextRefAdapter();
			}
			@Override
			public Adapter casePersistenceUnitRef(PersistenceUnitRef object) {
				return createPersistenceUnitRefAdapter();
			}
			@Override
			public Adapter casePortComponentRef(PortComponentRef object) {
				return createPortComponentRefAdapter();
			}
			@Override
			public Adapter casePropertyType(PropertyType object) {
				return createPropertyTypeAdapter();
			}
			@Override
			public Adapter caseResourceEnvRef(ResourceEnvRef object) {
				return createResourceEnvRefAdapter();
			}
			@Override
			public Adapter caseResourceRef(ResourceRef object) {
				return createResourceRefAdapter();
			}
			@Override
			public Adapter caseRespectBindingType(RespectBindingType object) {
				return createRespectBindingTypeAdapter();
			}
			@Override
			public Adapter caseRunAs(RunAs object) {
				return createRunAsAdapter();
			}
			@Override
			public Adapter caseSecurityRole(SecurityRole object) {
				return createSecurityRoleAdapter();
			}
			@Override
			public Adapter caseSecurityRoleRef(SecurityRoleRef object) {
				return createSecurityRoleRefAdapter();
			}
			@Override
			public Adapter caseServiceRef(ServiceRef object) {
				return createServiceRefAdapter();
			}
			@Override
			public Adapter caseServiceRefHandler(ServiceRefHandler object) {
				return createServiceRefHandlerAdapter();
			}
			@Override
			public Adapter caseServiceRefHandlerChain(ServiceRefHandlerChain object) {
				return createServiceRefHandlerChainAdapter();
			}
			@Override
			public Adapter caseServiceRefHandlerChains(ServiceRefHandlerChains object) {
				return createServiceRefHandlerChainsAdapter();
			}
			@Override
			public Adapter caseUrlPatternType(UrlPatternType object) {
				return createUrlPatternTypeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.AddressingType <em>Addressing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.AddressingType
	 * @generated
	 */
	public Adapter createAddressingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.AdministeredObjectType <em>Administered Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.AdministeredObjectType
	 * @generated
	 */
	public Adapter createAdministeredObjectTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ConnectionFactoryResourceType <em>Connection Factory Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ConnectionFactoryResourceType
	 * @generated
	 */
	public Adapter createConnectionFactoryResourceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.DataSourceType <em>Data Source Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.DataSourceType
	 * @generated
	 */
	public Adapter createDataSourceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.Description <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.Description
	 * @generated
	 */
	public Adapter createDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.DisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.DisplayName
	 * @generated
	 */
	public Adapter createDisplayNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.EjbLocalRef <em>Ejb Local Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.EjbLocalRef
	 * @generated
	 */
	public Adapter createEjbLocalRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.EjbRef <em>Ejb Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.EjbRef
	 * @generated
	 */
	public Adapter createEjbRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.EmptyType <em>Empty Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.EmptyType
	 * @generated
	 */
	public Adapter createEmptyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.EnvEntry <em>Env Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.EnvEntry
	 * @generated
	 */
	public Adapter createEnvEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.Icon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.Icon
	 * @generated
	 */
	public Adapter createIconAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.InjectionTarget <em>Injection Target</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.InjectionTarget
	 * @generated
	 */
	public Adapter createInjectionTargetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.JmsConnectionFactoryType <em>Jms Connection Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.JmsConnectionFactoryType
	 * @generated
	 */
	public Adapter createJmsConnectionFactoryTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.JmsDestinationType <em>Jms Destination Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.JmsDestinationType
	 * @generated
	 */
	public Adapter createJmsDestinationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.LifecycleCallback <em>Lifecycle Callback</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.LifecycleCallback
	 * @generated
	 */
	public Adapter createLifecycleCallbackAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.Listener <em>Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.Listener
	 * @generated
	 */
	public Adapter createListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.MailSessionType <em>Mail Session Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.MailSessionType
	 * @generated
	 */
	public Adapter createMailSessionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.MessageDestination <em>Message Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.MessageDestination
	 * @generated
	 */
	public Adapter createMessageDestinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.MessageDestinationRef <em>Message Destination Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.MessageDestinationRef
	 * @generated
	 */
	public Adapter createMessageDestinationRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ParamValue <em>Param Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ParamValue
	 * @generated
	 */
	public Adapter createParamValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.PersistenceContextRef <em>Persistence Context Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.PersistenceContextRef
	 * @generated
	 */
	public Adapter createPersistenceContextRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.PersistenceUnitRef <em>Persistence Unit Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.PersistenceUnitRef
	 * @generated
	 */
	public Adapter createPersistenceUnitRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.PortComponentRef <em>Port Component Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.PortComponentRef
	 * @generated
	 */
	public Adapter createPortComponentRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.PropertyType <em>Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.PropertyType
	 * @generated
	 */
	public Adapter createPropertyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ResourceEnvRef <em>Resource Env Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ResourceEnvRef
	 * @generated
	 */
	public Adapter createResourceEnvRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ResourceRef <em>Resource Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ResourceRef
	 * @generated
	 */
	public Adapter createResourceRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.RespectBindingType <em>Respect Binding Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.RespectBindingType
	 * @generated
	 */
	public Adapter createRespectBindingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.RunAs <em>Run As</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.RunAs
	 * @generated
	 */
	public Adapter createRunAsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.SecurityRole <em>Security Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.SecurityRole
	 * @generated
	 */
	public Adapter createSecurityRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.SecurityRoleRef <em>Security Role Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.SecurityRoleRef
	 * @generated
	 */
	public Adapter createSecurityRoleRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ServiceRef <em>Service Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ServiceRef
	 * @generated
	 */
	public Adapter createServiceRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ServiceRefHandler <em>Service Ref Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ServiceRefHandler
	 * @generated
	 */
	public Adapter createServiceRefHandlerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain <em>Service Ref Handler Chain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ServiceRefHandlerChain
	 * @generated
	 */
	public Adapter createServiceRefHandlerChainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChains <em>Service Ref Handler Chains</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.ServiceRefHandlerChains
	 * @generated
	 */
	public Adapter createServiceRefHandlerChainsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.core.UrlPatternType <em>Url Pattern Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.core.UrlPatternType
	 * @generated
	 */
	public Adapter createUrlPatternTypeAdapter() {
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

} //JavaeeAdapterFactory
