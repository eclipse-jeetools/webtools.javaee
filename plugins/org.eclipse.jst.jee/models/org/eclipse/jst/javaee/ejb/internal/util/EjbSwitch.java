/**
 * <copyright>
 * </copyright>
 *
 * $Id: EjbSwitch.java,v 1.2 2007/03/26 21:04:16 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
public class EjbSwitch {
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
			case EjbPackage.ACTIVATION_CONFIG: {
				ActivationConfig activationConfig = (ActivationConfig)theEObject;
				Object result = caseActivationConfig(activationConfig);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ACTIVATION_CONFIG_PROPERTY: {
				ActivationConfigProperty activationConfigProperty = (ActivationConfigProperty)theEObject;
				Object result = caseActivationConfigProperty(activationConfigProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.APPLICATION_EXCEPTION: {
				ApplicationException applicationException = (ApplicationException)theEObject;
				Object result = caseApplicationException(applicationException);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.AROUND_INVOKE_TYPE: {
				AroundInvokeType aroundInvokeType = (AroundInvokeType)theEObject;
				Object result = caseAroundInvokeType(aroundInvokeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ASSEMBLY_DESCRIPTOR: {
				AssemblyDescriptor assemblyDescriptor = (AssemblyDescriptor)theEObject;
				Object result = caseAssemblyDescriptor(assemblyDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CMP_FIELD: {
				CMPField cmpField = (CMPField)theEObject;
				Object result = caseCMPField(cmpField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CMR_FIELD: {
				CMRField cmrField = (CMRField)theEObject;
				Object result = caseCMRField(cmrField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.CONTAINER_TRANSACTION_TYPE: {
				ContainerTransactionType containerTransactionType = (ContainerTransactionType)theEObject;
				Object result = caseContainerTransactionType(containerTransactionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_JAR: {
				EJBJar ejbJar = (EJBJar)theEObject;
				Object result = caseEJBJar(ejbJar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR: {
				EJBJarDeploymentDescriptor ejbJarDeploymentDescriptor = (EJBJarDeploymentDescriptor)theEObject;
				Object result = caseEJBJarDeploymentDescriptor(ejbJarDeploymentDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_RELATION: {
				EJBRelation ejbRelation = (EJBRelation)theEObject;
				Object result = caseEJBRelation(ejbRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EJB_RELATIONSHIP_ROLE: {
				EJBRelationshipRole ejbRelationshipRole = (EJBRelationshipRole)theEObject;
				Object result = caseEJBRelationshipRole(ejbRelationshipRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ENTERPRISE_BEANS: {
				EnterpriseBeans enterpriseBeans = (EnterpriseBeans)theEObject;
				Object result = caseEnterpriseBeans(enterpriseBeans);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.ENTITY_BEAN: {
				EntityBean entityBean = (EntityBean)theEObject;
				Object result = caseEntityBean(entityBean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.EXCLUDE_LIST: {
				ExcludeList excludeList = (ExcludeList)theEObject;
				Object result = caseExcludeList(excludeList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INIT_METHOD_TYPE: {
				InitMethodType initMethodType = (InitMethodType)theEObject;
				Object result = caseInitMethodType(initMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTOR_BINDING_TYPE: {
				InterceptorBindingType interceptorBindingType = (InterceptorBindingType)theEObject;
				Object result = caseInterceptorBindingType(interceptorBindingType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTOR_ORDER_TYPE: {
				InterceptorOrderType interceptorOrderType = (InterceptorOrderType)theEObject;
				Object result = caseInterceptorOrderType(interceptorOrderType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTORS_TYPE: {
				InterceptorsType interceptorsType = (InterceptorsType)theEObject;
				Object result = caseInterceptorsType(interceptorsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.INTERCEPTOR_TYPE: {
				InterceptorType interceptorType = (InterceptorType)theEObject;
				Object result = caseInterceptorType(interceptorType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.MESSAGE_DRIVEN_BEAN: {
				MessageDrivenBean messageDrivenBean = (MessageDrivenBean)theEObject;
				Object result = caseMessageDrivenBean(messageDrivenBean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.METHOD_PARAMS: {
				MethodParams methodParams = (MethodParams)theEObject;
				Object result = caseMethodParams(methodParams);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.METHOD_PERMISSION: {
				MethodPermission methodPermission = (MethodPermission)theEObject;
				Object result = caseMethodPermission(methodPermission);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.METHOD_TYPE: {
				MethodType methodType = (MethodType)theEObject;
				Object result = caseMethodType(methodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.NAMED_METHOD_TYPE: {
				NamedMethodType namedMethodType = (NamedMethodType)theEObject;
				Object result = caseNamedMethodType(namedMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.QUERY: {
				Query query = (Query)theEObject;
				Object result = caseQuery(query);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.QUERY_METHOD: {
				QueryMethod queryMethod = (QueryMethod)theEObject;
				Object result = caseQueryMethod(queryMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.RELATIONSHIP_ROLE_SOURCE_TYPE: {
				RelationshipRoleSourceType relationshipRoleSourceType = (RelationshipRoleSourceType)theEObject;
				Object result = caseRelationshipRoleSourceType(relationshipRoleSourceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.RELATIONSHIPS: {
				Relationships relationships = (Relationships)theEObject;
				Object result = caseRelationships(relationships);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.REMOVE_METHOD_TYPE: {
				RemoveMethodType removeMethodType = (RemoveMethodType)theEObject;
				Object result = caseRemoveMethodType(removeMethodType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.SECURITY_IDENTITY_TYPE: {
				SecurityIdentityType securityIdentityType = (SecurityIdentityType)theEObject;
				Object result = caseSecurityIdentityType(securityIdentityType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EjbPackage.SESSION_BEAN: {
				SessionBean sessionBean = (SessionBean)theEObject;
				Object result = caseSessionBean(sessionBean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Activation Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Activation Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseActivationConfig(ActivationConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Activation Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Activation Config Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseActivationConfigProperty(ActivationConfigProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Application Exception</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Application Exception</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseApplicationException(ApplicationException object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Around Invoke Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Around Invoke Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAroundInvokeType(AroundInvokeType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Assembly Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Assembly Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAssemblyDescriptor(AssemblyDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>CMP Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>CMP Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCMPField(CMPField object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>CMR Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>CMR Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCMRField(CMRField object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Container Transaction Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Container Transaction Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseContainerTransactionType(ContainerTransactionType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EJB Jar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Jar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEJBJar(EJBJar object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EJB Jar Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Jar Deployment Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEJBJarDeploymentDescriptor(EJBJarDeploymentDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EJB Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEJBRelation(EJBRelation object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EJB Relationship Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Relationship Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEJBRelationshipRole(EJBRelationshipRole object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Enterprise Beans</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Enterprise Beans</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEnterpriseBeans(EnterpriseBeans object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Entity Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Entity Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEntityBean(EntityBean object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Exclude List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Exclude List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExcludeList(ExcludeList object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Init Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Init Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInitMethodType(InitMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Interceptor Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Interceptor Binding Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInterceptorBindingType(InterceptorBindingType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Interceptor Order Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Interceptor Order Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInterceptorOrderType(InterceptorOrderType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Interceptors Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Interceptors Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInterceptorsType(InterceptorsType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Interceptor Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Interceptor Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInterceptorType(InterceptorType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Message Driven Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Driven Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageDrivenBean(MessageDrivenBean object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Method Params</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Method Params</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethodParams(MethodParams object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Method Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Method Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethodPermission(MethodPermission object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethodType(MethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Named Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Named Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNamedMethodType(NamedMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseQuery(Query object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Query Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Query Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseQueryMethod(QueryMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Relationship Role Source Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Relationship Role Source Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRelationshipRoleSourceType(RelationshipRoleSourceType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Relationships</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Relationships</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRelationships(Relationships object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Remove Method Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Remove Method Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRemoveMethodType(RemoveMethodType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Identity Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Identity Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityIdentityType(SecurityIdentityType object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Session Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Session Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSessionBean(SessionBean object) {
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

} //EjbSwitch
