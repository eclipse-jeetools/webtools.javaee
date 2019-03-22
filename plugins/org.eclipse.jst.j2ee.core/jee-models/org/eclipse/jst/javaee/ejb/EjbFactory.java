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
package org.eclipse.jst.javaee.ejb;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage
 * @generated
 */
public interface EjbFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EjbFactory eINSTANCE = org.eclipse.jst.javaee.ejb.internal.impl.EjbFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Access Timeout Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Access Timeout Type</em>'.
	 * @generated
	 */
	AccessTimeoutType createAccessTimeoutType();

	/**
	 * Returns a new object of class '<em>Activation Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Config</em>'.
	 * @generated
	 */
	ActivationConfig createActivationConfig();

	/**
	 * Returns a new object of class '<em>Activation Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Config Property</em>'.
	 * @generated
	 */
	ActivationConfigProperty createActivationConfigProperty();

	/**
	 * Returns a new object of class '<em>Application Exception</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Application Exception</em>'.
	 * @generated
	 */
	ApplicationException createApplicationException();

	/**
	 * Returns a new object of class '<em>Around Invoke Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Around Invoke Type</em>'.
	 * @generated
	 */
	AroundInvokeType createAroundInvokeType();

	/**
	 * Returns a new object of class '<em>Around Timeout Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Around Timeout Type</em>'.
	 * @generated
	 */
	AroundTimeoutType createAroundTimeoutType();

	/**
	 * Returns a new object of class '<em>Assembly Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assembly Descriptor</em>'.
	 * @generated
	 */
	AssemblyDescriptor createAssemblyDescriptor();

	/**
	 * Returns a new object of class '<em>Async Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Async Method Type</em>'.
	 * @generated
	 */
	AsyncMethodType createAsyncMethodType();

	/**
	 * Returns a new object of class '<em>CMP Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CMP Field</em>'.
	 * @generated
	 */
	CMPField createCMPField();

	/**
	 * Returns a new object of class '<em>CMR Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CMR Field</em>'.
	 * @generated
	 */
	CMRField createCMRField();

	/**
	 * Returns a new object of class '<em>Concurrent Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concurrent Method Type</em>'.
	 * @generated
	 */
	ConcurrentMethodType createConcurrentMethodType();

	/**
	 * Returns a new object of class '<em>Container Transaction Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Transaction Type</em>'.
	 * @generated
	 */
	ContainerTransactionType createContainerTransactionType();

	/**
	 * Returns a new object of class '<em>Depends On Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Depends On Type</em>'.
	 * @generated
	 */
	DependsOnType createDependsOnType();

	/**
	 * Returns a new object of class '<em>EJB Jar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EJB Jar</em>'.
	 * @generated
	 */
	EJBJar createEJBJar();

	/**
	 * Returns a new object of class '<em>EJB Jar Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EJB Jar Deployment Descriptor</em>'.
	 * @generated
	 */
	EJBJarDeploymentDescriptor createEJBJarDeploymentDescriptor();

	/**
	 * Returns a new object of class '<em>EJB Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EJB Relation</em>'.
	 * @generated
	 */
	EJBRelation createEJBRelation();

	/**
	 * Returns a new object of class '<em>EJB Relationship Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EJB Relationship Role</em>'.
	 * @generated
	 */
	EJBRelationshipRole createEJBRelationshipRole();

	/**
	 * Returns a new object of class '<em>Enterprise Beans</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enterprise Beans</em>'.
	 * @generated
	 */
	EnterpriseBeans createEnterpriseBeans();

	/**
	 * Returns a new object of class '<em>Entity Bean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Bean</em>'.
	 * @generated
	 */
	EntityBean createEntityBean();

	/**
	 * Returns a new object of class '<em>Exclude List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exclude List</em>'.
	 * @generated
	 */
	ExcludeList createExcludeList();

	/**
	 * Returns a new object of class '<em>Init Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Init Method Type</em>'.
	 * @generated
	 */
	InitMethodType createInitMethodType();

	/**
	 * Returns a new object of class '<em>Interceptor Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interceptor Binding Type</em>'.
	 * @generated
	 */
	InterceptorBindingType createInterceptorBindingType();

	/**
	 * Returns a new object of class '<em>Interceptor Order Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interceptor Order Type</em>'.
	 * @generated
	 */
	InterceptorOrderType createInterceptorOrderType();

	/**
	 * Returns a new object of class '<em>Interceptors Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interceptors Type</em>'.
	 * @generated
	 */
	InterceptorsType createInterceptorsType();

	/**
	 * Returns a new object of class '<em>Interceptor Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interceptor Type</em>'.
	 * @generated
	 */
	InterceptorType createInterceptorType();

	/**
	 * Returns a new object of class '<em>Message Driven Bean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Driven Bean</em>'.
	 * @generated
	 */
	MessageDrivenBean createMessageDrivenBean();

	/**
	 * Returns a new object of class '<em>Method Params</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Params</em>'.
	 * @generated
	 */
	MethodParams createMethodParams();

	/**
	 * Returns a new object of class '<em>Method Permission</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Permission</em>'.
	 * @generated
	 */
	MethodPermission createMethodPermission();

	/**
	 * Returns a new object of class '<em>Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Type</em>'.
	 * @generated
	 */
	MethodType createMethodType();

	/**
	 * Returns a new object of class '<em>Named Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Method Type</em>'.
	 * @generated
	 */
	NamedMethodType createNamedMethodType();

	/**
	 * Returns a new object of class '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query</em>'.
	 * @generated
	 */
	Query createQuery();

	/**
	 * Returns a new object of class '<em>Query Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Method</em>'.
	 * @generated
	 */
	QueryMethod createQueryMethod();

	/**
	 * Returns a new object of class '<em>Relationship Role Source Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relationship Role Source Type</em>'.
	 * @generated
	 */
	RelationshipRoleSourceType createRelationshipRoleSourceType();

	/**
	 * Returns a new object of class '<em>Relationships</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relationships</em>'.
	 * @generated
	 */
	Relationships createRelationships();

	/**
	 * Returns a new object of class '<em>Remove Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remove Method Type</em>'.
	 * @generated
	 */
	RemoveMethodType createRemoveMethodType();

	/**
	 * Returns a new object of class '<em>Security Identity Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Identity Type</em>'.
	 * @generated
	 */
	SecurityIdentityType createSecurityIdentityType();

	/**
	 * Returns a new object of class '<em>Session Bean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Session Bean</em>'.
	 * @generated
	 */
	SessionBean createSessionBean();

	/**
	 * Returns a new object of class '<em>Stateful Timeout Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stateful Timeout Type</em>'.
	 * @generated
	 */
	StatefulTimeoutType createStatefulTimeoutType();

	/**
	 * Returns a new object of class '<em>Timer Schedule Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer Schedule Type</em>'.
	 * @generated
	 */
	TimerScheduleType createTimerScheduleType();

	/**
	 * Returns a new object of class '<em>Timer Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer Type</em>'.
	 * @generated
	 */
	TimerType createTimerType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EjbPackage getEjbPackage();

} //EjbFactory
