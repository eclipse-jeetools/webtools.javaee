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
package org.eclipse.jst.javaee.ejb.internal.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.javaee.ejb.*;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage
 * @generated
 */
public class EjbAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EjbPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = EjbPackage.eINSTANCE;
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
	protected EjbSwitch<Adapter> modelSwitch =
		new EjbSwitch<Adapter>() {
			@Override
			public Adapter caseAccessTimeoutType(AccessTimeoutType object) {
				return createAccessTimeoutTypeAdapter();
			}
			@Override
			public Adapter caseActivationConfig(ActivationConfig object) {
				return createActivationConfigAdapter();
			}
			@Override
			public Adapter caseActivationConfigProperty(ActivationConfigProperty object) {
				return createActivationConfigPropertyAdapter();
			}
			@Override
			public Adapter caseApplicationException(ApplicationException object) {
				return createApplicationExceptionAdapter();
			}
			@Override
			public Adapter caseAroundInvokeType(AroundInvokeType object) {
				return createAroundInvokeTypeAdapter();
			}
			@Override
			public Adapter caseAroundTimeoutType(AroundTimeoutType object) {
				return createAroundTimeoutTypeAdapter();
			}
			@Override
			public Adapter caseAssemblyDescriptor(AssemblyDescriptor object) {
				return createAssemblyDescriptorAdapter();
			}
			@Override
			public Adapter caseAsyncMethodType(AsyncMethodType object) {
				return createAsyncMethodTypeAdapter();
			}
			@Override
			public Adapter caseCMPField(CMPField object) {
				return createCMPFieldAdapter();
			}
			@Override
			public Adapter caseCMRField(CMRField object) {
				return createCMRFieldAdapter();
			}
			@Override
			public Adapter caseConcurrentMethodType(ConcurrentMethodType object) {
				return createConcurrentMethodTypeAdapter();
			}
			@Override
			public Adapter caseContainerTransactionType(ContainerTransactionType object) {
				return createContainerTransactionTypeAdapter();
			}
			@Override
			public Adapter caseDependsOnType(DependsOnType object) {
				return createDependsOnTypeAdapter();
			}
			@Override
			public Adapter caseEJBJar(EJBJar object) {
				return createEJBJarAdapter();
			}
			@Override
			public Adapter caseEJBJarDeploymentDescriptor(EJBJarDeploymentDescriptor object) {
				return createEJBJarDeploymentDescriptorAdapter();
			}
			@Override
			public Adapter caseEJBRelation(EJBRelation object) {
				return createEJBRelationAdapter();
			}
			@Override
			public Adapter caseEJBRelationshipRole(EJBRelationshipRole object) {
				return createEJBRelationshipRoleAdapter();
			}
			@Override
			public Adapter caseEnterpriseBeans(EnterpriseBeans object) {
				return createEnterpriseBeansAdapter();
			}
			@Override
			public Adapter caseEntityBean(EntityBean object) {
				return createEntityBeanAdapter();
			}
			@Override
			public Adapter caseExcludeList(ExcludeList object) {
				return createExcludeListAdapter();
			}
			@Override
			public Adapter caseInitMethodType(InitMethodType object) {
				return createInitMethodTypeAdapter();
			}
			@Override
			public Adapter caseInterceptorBindingType(InterceptorBindingType object) {
				return createInterceptorBindingTypeAdapter();
			}
			@Override
			public Adapter caseInterceptorOrderType(InterceptorOrderType object) {
				return createInterceptorOrderTypeAdapter();
			}
			@Override
			public Adapter caseInterceptorsType(InterceptorsType object) {
				return createInterceptorsTypeAdapter();
			}
			@Override
			public Adapter caseInterceptorType(InterceptorType object) {
				return createInterceptorTypeAdapter();
			}
			@Override
			public Adapter caseMessageDrivenBean(MessageDrivenBean object) {
				return createMessageDrivenBeanAdapter();
			}
			@Override
			public Adapter caseMethodParams(MethodParams object) {
				return createMethodParamsAdapter();
			}
			@Override
			public Adapter caseMethodPermission(MethodPermission object) {
				return createMethodPermissionAdapter();
			}
			@Override
			public Adapter caseMethodType(MethodType object) {
				return createMethodTypeAdapter();
			}
			@Override
			public Adapter caseNamedMethodType(NamedMethodType object) {
				return createNamedMethodTypeAdapter();
			}
			@Override
			public Adapter caseQuery(Query object) {
				return createQueryAdapter();
			}
			@Override
			public Adapter caseQueryMethod(QueryMethod object) {
				return createQueryMethodAdapter();
			}
			@Override
			public Adapter caseRelationshipRoleSourceType(RelationshipRoleSourceType object) {
				return createRelationshipRoleSourceTypeAdapter();
			}
			@Override
			public Adapter caseRelationships(Relationships object) {
				return createRelationshipsAdapter();
			}
			@Override
			public Adapter caseRemoveMethodType(RemoveMethodType object) {
				return createRemoveMethodTypeAdapter();
			}
			@Override
			public Adapter caseSecurityIdentityType(SecurityIdentityType object) {
				return createSecurityIdentityTypeAdapter();
			}
			@Override
			public Adapter caseSessionBean(SessionBean object) {
				return createSessionBeanAdapter();
			}
			@Override
			public Adapter caseStatefulTimeoutType(StatefulTimeoutType object) {
				return createStatefulTimeoutTypeAdapter();
			}
			@Override
			public Adapter caseTimerScheduleType(TimerScheduleType object) {
				return createTimerScheduleTypeAdapter();
			}
			@Override
			public Adapter caseTimerType(TimerType object) {
				return createTimerTypeAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.AccessTimeoutType <em>Access Timeout Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.AccessTimeoutType
	 * @generated
	 */
	public Adapter createAccessTimeoutTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.ActivationConfig <em>Activation Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.ActivationConfig
	 * @generated
	 */
	public Adapter createActivationConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty <em>Activation Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.ActivationConfigProperty
	 * @generated
	 */
	public Adapter createActivationConfigPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.ApplicationException <em>Application Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.ApplicationException
	 * @generated
	 */
	public Adapter createApplicationExceptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.AroundInvokeType <em>Around Invoke Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.AroundInvokeType
	 * @generated
	 */
	public Adapter createAroundInvokeTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.AroundTimeoutType <em>Around Timeout Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.AroundTimeoutType
	 * @generated
	 */
	public Adapter createAroundTimeoutTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.AssemblyDescriptor <em>Assembly Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.AssemblyDescriptor
	 * @generated
	 */
	public Adapter createAssemblyDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.AsyncMethodType <em>Async Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.AsyncMethodType
	 * @generated
	 */
	public Adapter createAsyncMethodTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.CMPField <em>CMP Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.CMPField
	 * @generated
	 */
	public Adapter createCMPFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.CMRField <em>CMR Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.CMRField
	 * @generated
	 */
	public Adapter createCMRFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.ConcurrentMethodType <em>Concurrent Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.ConcurrentMethodType
	 * @generated
	 */
	public Adapter createConcurrentMethodTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.ContainerTransactionType <em>Container Transaction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.ContainerTransactionType
	 * @generated
	 */
	public Adapter createContainerTransactionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.DependsOnType <em>Depends On Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.DependsOnType
	 * @generated
	 */
	public Adapter createDependsOnTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.EJBJar <em>EJB Jar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.EJBJar
	 * @generated
	 */
	public Adapter createEJBJarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor <em>EJB Jar Deployment Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor
	 * @generated
	 */
	public Adapter createEJBJarDeploymentDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.EJBRelation <em>EJB Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.EJBRelation
	 * @generated
	 */
	public Adapter createEJBRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole <em>EJB Relationship Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.EJBRelationshipRole
	 * @generated
	 */
	public Adapter createEJBRelationshipRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans <em>Enterprise Beans</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.EnterpriseBeans
	 * @generated
	 */
	public Adapter createEnterpriseBeansAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.EntityBean <em>Entity Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.EntityBean
	 * @generated
	 */
	public Adapter createEntityBeanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.ExcludeList <em>Exclude List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.ExcludeList
	 * @generated
	 */
	public Adapter createExcludeListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.InitMethodType <em>Init Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.InitMethodType
	 * @generated
	 */
	public Adapter createInitMethodTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.InterceptorBindingType <em>Interceptor Binding Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.InterceptorBindingType
	 * @generated
	 */
	public Adapter createInterceptorBindingTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.InterceptorOrderType <em>Interceptor Order Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.InterceptorOrderType
	 * @generated
	 */
	public Adapter createInterceptorOrderTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.InterceptorsType <em>Interceptors Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.InterceptorsType
	 * @generated
	 */
	public Adapter createInterceptorsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.InterceptorType <em>Interceptor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.InterceptorType
	 * @generated
	 */
	public Adapter createInterceptorTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.MessageDrivenBean <em>Message Driven Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.MessageDrivenBean
	 * @generated
	 */
	public Adapter createMessageDrivenBeanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.MethodParams <em>Method Params</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.MethodParams
	 * @generated
	 */
	public Adapter createMethodParamsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.MethodPermission <em>Method Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.MethodPermission
	 * @generated
	 */
	public Adapter createMethodPermissionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.MethodType <em>Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.MethodType
	 * @generated
	 */
	public Adapter createMethodTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.NamedMethodType <em>Named Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.NamedMethodType
	 * @generated
	 */
	public Adapter createNamedMethodTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.Query
	 * @generated
	 */
	public Adapter createQueryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.QueryMethod <em>Query Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.QueryMethod
	 * @generated
	 */
	public Adapter createQueryMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.RelationshipRoleSourceType <em>Relationship Role Source Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.RelationshipRoleSourceType
	 * @generated
	 */
	public Adapter createRelationshipRoleSourceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.Relationships <em>Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.Relationships
	 * @generated
	 */
	public Adapter createRelationshipsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.RemoveMethodType <em>Remove Method Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.RemoveMethodType
	 * @generated
	 */
	public Adapter createRemoveMethodTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.SecurityIdentityType <em>Security Identity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.SecurityIdentityType
	 * @generated
	 */
	public Adapter createSecurityIdentityTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.SessionBean <em>Session Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.SessionBean
	 * @generated
	 */
	public Adapter createSessionBeanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType <em>Stateful Timeout Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.StatefulTimeoutType
	 * @generated
	 */
	public Adapter createStatefulTimeoutTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.TimerScheduleType <em>Timer Schedule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.TimerScheduleType
	 * @generated
	 */
	public Adapter createTimerScheduleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.javaee.ejb.TimerType <em>Timer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.javaee.ejb.TimerType
	 * @generated
	 */
	public Adapter createTimerTypeAdapter() {
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

} //EjbAdapterFactory
