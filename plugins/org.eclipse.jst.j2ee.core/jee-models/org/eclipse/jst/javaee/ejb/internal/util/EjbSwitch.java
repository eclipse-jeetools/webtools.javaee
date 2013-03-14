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
package org.eclipse.jst.javaee.ejb.internal.util;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jst.javaee.ejb.*;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

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
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage
 * @generated
 */
public class EjbSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EjbPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbSwitch() {
		if (modelPackage == null) {
			modelPackage = EjbPackage.eINSTANCE;
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
			case EjbPackage.ACCESS_TIMEOUT_TYPE: {
				AccessTimeoutType accessTimeoutType = (AccessTimeoutType)theEObject;
				T result = caseAccessTimeoutType(accessTimeoutType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ACTIVATION_CONFIG: {
				ActivationConfig activationConfig = (ActivationConfig)theEObject;
				T result = caseActivationConfig(activationConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ACTIVATION_CONFIG_PROPERTY: {
				ActivationConfigProperty activationConfigProperty = (ActivationConfigProperty)theEObject;
				T result = caseActivationConfigProperty(activationConfigProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.APPLICATION_EXCEPTION: {
				ApplicationException applicationException = (ApplicationException)theEObject;
				T result = caseApplicationException(applicationException);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.AROUND_INVOKE_TYPE: {
				AroundInvokeType aroundInvokeType = (AroundInvokeType)theEObject;
				T result = caseAroundInvokeType(aroundInvokeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.AROUND_TIMEOUT_TYPE: {
				AroundTimeoutType aroundTimeoutType = (AroundTimeoutType)theEObject;
				T result = caseAroundTimeoutType(aroundTimeoutType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ASSEMBLY_DESCRIPTOR: {
				AssemblyDescriptor assemblyDescriptor = (AssemblyDescriptor)theEObject;
				T result = caseAssemblyDescriptor(assemblyDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ASYNC_METHOD_TYPE: {
				AsyncMethodType asyncMethodType = (AsyncMethodType)theEObject;
				T result = caseAsyncMethodType(asyncMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CMP_FIELD: {
				CMPField cmpField = (CMPField)theEObject;
				T result = caseCMPField(cmpField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CMR_FIELD: {
				CMRField cmrField = (CMRField)theEObject;
				T result = caseCMRField(cmrField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CONCURRENT_METHOD_TYPE: {
				ConcurrentMethodType concurrentMethodType = (ConcurrentMethodType)theEObject;
				T result = caseConcurrentMethodType(concurrentMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CONTAINER_TRANSACTION_TYPE: {
				ContainerTransactionType containerTransactionType = (ContainerTransactionType)theEObject;
				T result = caseContainerTransactionType(containerTransactionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.DEPENDS_ON_TYPE: {
				DependsOnType dependsOnType = (DependsOnType)theEObject;
				T result = caseDependsOnType(dependsOnType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_JAR: {
				EJBJar ejbJar = (EJBJar)theEObject;
				T result = caseEJBJar(ejbJar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR: {
				EJBJarDeploymentDescriptor ejbJarDeploymentDescriptor = (EJBJarDeploymentDescriptor)theEObject;
				T result = caseEJBJarDeploymentDescriptor(ejbJarDeploymentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_RELATION: {
				EJBRelation ejbRelation = (EJBRelation)theEObject;
				T result = caseEJBRelation(ejbRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_RELATIONSHIP_ROLE: {
				EJBRelationshipRole ejbRelationshipRole = (EJBRelationshipRole)theEObject;
				T result = caseEJBRelationshipRole(ejbRelationshipRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ENTERPRISE_BEANS: {
				EnterpriseBeans enterpriseBeans = (EnterpriseBeans)theEObject;
				T result = caseEnterpriseBeans(enterpriseBeans);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ENTITY_BEAN: {
				EntityBean entityBean = (EntityBean)theEObject;
				T result = caseEntityBean(entityBean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EXCLUDE_LIST: {
				ExcludeList excludeList = (ExcludeList)theEObject;
				T result = caseExcludeList(excludeList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INIT_METHOD_TYPE: {
				InitMethodType initMethodType = (InitMethodType)theEObject;
				T result = caseInitMethodType(initMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTOR_BINDING_TYPE: {
				InterceptorBindingType interceptorBindingType = (InterceptorBindingType)theEObject;
				T result = caseInterceptorBindingType(interceptorBindingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTOR_ORDER_TYPE: {
				InterceptorOrderType interceptorOrderType = (InterceptorOrderType)theEObject;
				T result = caseInterceptorOrderType(interceptorOrderType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTORS_TYPE: {
				InterceptorsType interceptorsType = (InterceptorsType)theEObject;
				T result = caseInterceptorsType(interceptorsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTOR_TYPE: {
				InterceptorType interceptorType = (InterceptorType)theEObject;
				T result = caseInterceptorType(interceptorType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.MESSAGE_DRIVEN_BEAN: {
				MessageDrivenBean messageDrivenBean = (MessageDrivenBean)theEObject;
				T result = caseMessageDrivenBean(messageDrivenBean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.METHOD_PARAMS: {
				MethodParams methodParams = (MethodParams)theEObject;
				T result = caseMethodParams(methodParams);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.METHOD_PERMISSION: {
				MethodPermission methodPermission = (MethodPermission)theEObject;
				T result = caseMethodPermission(methodPermission);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.METHOD_TYPE: {
				MethodType methodType = (MethodType)theEObject;
				T result = caseMethodType(methodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.NAMED_METHOD_TYPE: {
				NamedMethodType namedMethodType = (NamedMethodType)theEObject;
				T result = caseNamedMethodType(namedMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.QUERY: {
				Query query = (Query)theEObject;
				T result = caseQuery(query);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.QUERY_METHOD: {
				QueryMethod queryMethod = (QueryMethod)theEObject;
				T result = caseQueryMethod(queryMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.RELATIONSHIP_ROLE_SOURCE_TYPE: {
				RelationshipRoleSourceType relationshipRoleSourceType = (RelationshipRoleSourceType)theEObject;
				T result = caseRelationshipRoleSourceType(relationshipRoleSourceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.RELATIONSHIPS: {
				Relationships relationships = (Relationships)theEObject;
				T result = caseRelationships(relationships);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.REMOVE_METHOD_TYPE: {
				RemoveMethodType removeMethodType = (RemoveMethodType)theEObject;
				T result = caseRemoveMethodType(removeMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.SECURITY_IDENTITY_TYPE: {
				SecurityIdentityType securityIdentityType = (SecurityIdentityType)theEObject;
				T result = caseSecurityIdentityType(securityIdentityType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.SESSION_BEAN: {
				SessionBean sessionBean = (SessionBean)theEObject;
				T result = caseSessionBean(sessionBean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.STATEFUL_TIMEOUT_TYPE: {
				StatefulTimeoutType statefulTimeoutType = (StatefulTimeoutType)theEObject;
				T result = caseStatefulTimeoutType(statefulTimeoutType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.TIMER_SCHEDULE_TYPE: {
				TimerScheduleType timerScheduleType = (TimerScheduleType)theEObject;
				T result = caseTimerScheduleType(timerScheduleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.TIMER_TYPE: {
				TimerType timerType = (TimerType)theEObject;
				T result = caseTimerType(timerType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Access Timeout Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Access Timeout Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccessTimeoutType(AccessTimeoutType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivationConfig(ActivationConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activation Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activation Config Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivationConfigProperty(ActivationConfigProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Exception</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Exception</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationException(ApplicationException object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Around Invoke Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Around Invoke Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAroundInvokeType(AroundInvokeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Around Timeout Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Around Timeout Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAroundTimeoutType(AroundTimeoutType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyDescriptor(AssemblyDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Async Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Async Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAsyncMethodType(AsyncMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CMP Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CMP Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCMPField(CMPField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CMR Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CMR Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCMRField(CMRField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concurrent Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concurrent Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcurrentMethodType(ConcurrentMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Transaction Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Transaction Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerTransactionType(ContainerTransactionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Depends On Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Depends On Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependsOnType(DependsOnType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EJB Jar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EJB Jar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEJBJar(EJBJar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EJB Jar Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EJB Jar Deployment Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEJBJarDeploymentDescriptor(EJBJarDeploymentDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EJB Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EJB Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEJBRelation(EJBRelation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EJB Relationship Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EJB Relationship Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEJBRelationshipRole(EJBRelationshipRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enterprise Beans</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enterprise Beans</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnterpriseBeans(EnterpriseBeans object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityBean(EntityBean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exclude List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exclude List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExcludeList(ExcludeList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Init Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Init Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInitMethodType(InitMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interceptor Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interceptor Binding Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterceptorBindingType(InterceptorBindingType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interceptor Order Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interceptor Order Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterceptorOrderType(InterceptorOrderType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interceptors Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interceptors Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterceptorsType(InterceptorsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interceptor Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interceptor Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterceptorType(InterceptorType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Driven Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Driven Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessageDrivenBean(MessageDrivenBean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Params</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Params</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodParams(MethodParams object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodPermission(MethodPermission object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodType(MethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedMethodType(NamedMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuery(Query object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQueryMethod(QueryMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationship Role Source Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationship Role Source Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationshipRoleSourceType(RelationshipRoleSourceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relationships</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relationships</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationships(Relationships object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remove Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remove Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveMethodType(RemoveMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Identity Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Identity Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurityIdentityType(SecurityIdentityType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Session Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Session Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSessionBean(SessionBean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stateful Timeout Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stateful Timeout Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStatefulTimeoutType(StatefulTimeoutType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer Schedule Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer Schedule Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimerScheduleType(TimerScheduleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimerType(TimerType object) {
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

} //EjbSwitch
