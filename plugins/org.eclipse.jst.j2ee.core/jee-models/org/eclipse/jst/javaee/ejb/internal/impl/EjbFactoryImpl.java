/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;
import org.eclipse.jst.javaee.ejb.*;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EjbFactoryImpl extends EFactoryImpl implements EjbFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EjbFactory init() {
		try {
			EjbFactory theEjbFactory = (EjbFactory)EPackage.Registry.INSTANCE.getEFactory("http://xmlns.jcp.org/xml/ns/javaee/ejb"); //$NON-NLS-1$ 
			if (theEjbFactory != null) {
				return theEjbFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EjbFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EjbPackage.ACCESS_TIMEOUT_TYPE: return (EObject)createAccessTimeoutType();
			case EjbPackage.ACTIVATION_CONFIG: return (EObject)createActivationConfig();
			case EjbPackage.ACTIVATION_CONFIG_PROPERTY: return (EObject)createActivationConfigProperty();
			case EjbPackage.APPLICATION_EXCEPTION: return (EObject)createApplicationException();
			case EjbPackage.AROUND_INVOKE_TYPE: return (EObject)createAroundInvokeType();
			case EjbPackage.AROUND_TIMEOUT_TYPE: return (EObject)createAroundTimeoutType();
			case EjbPackage.ASSEMBLY_DESCRIPTOR: return (EObject)createAssemblyDescriptor();
			case EjbPackage.ASYNC_METHOD_TYPE: return (EObject)createAsyncMethodType();
			case EjbPackage.CMP_FIELD: return (EObject)createCMPField();
			case EjbPackage.CMR_FIELD: return (EObject)createCMRField();
			case EjbPackage.CONCURRENT_METHOD_TYPE: return (EObject)createConcurrentMethodType();
			case EjbPackage.CONTAINER_TRANSACTION_TYPE: return (EObject)createContainerTransactionType();
			case EjbPackage.DEPENDS_ON_TYPE: return (EObject)createDependsOnType();
			case EjbPackage.EJB_JAR: return (EObject)createEJBJar();
			case EjbPackage.EJB_JAR_DEPLOYMENT_DESCRIPTOR: return (EObject)createEJBJarDeploymentDescriptor();
			case EjbPackage.EJB_RELATION: return (EObject)createEJBRelation();
			case EjbPackage.EJB_RELATIONSHIP_ROLE: return (EObject)createEJBRelationshipRole();
			case EjbPackage.ENTERPRISE_BEANS: return (EObject)createEnterpriseBeans();
			case EjbPackage.ENTITY_BEAN: return (EObject)createEntityBean();
			case EjbPackage.EXCLUDE_LIST: return (EObject)createExcludeList();
			case EjbPackage.INIT_METHOD_TYPE: return (EObject)createInitMethodType();
			case EjbPackage.INTERCEPTOR_BINDING_TYPE: return (EObject)createInterceptorBindingType();
			case EjbPackage.INTERCEPTOR_ORDER_TYPE: return (EObject)createInterceptorOrderType();
			case EjbPackage.INTERCEPTORS_TYPE: return (EObject)createInterceptorsType();
			case EjbPackage.INTERCEPTOR_TYPE: return (EObject)createInterceptorType();
			case EjbPackage.MESSAGE_DRIVEN_BEAN: return (EObject)createMessageDrivenBean();
			case EjbPackage.METHOD_PARAMS: return (EObject)createMethodParams();
			case EjbPackage.METHOD_PERMISSION: return (EObject)createMethodPermission();
			case EjbPackage.METHOD_TYPE: return (EObject)createMethodType();
			case EjbPackage.NAMED_METHOD_TYPE: return (EObject)createNamedMethodType();
			case EjbPackage.QUERY: return (EObject)createQuery();
			case EjbPackage.QUERY_METHOD: return (EObject)createQueryMethod();
			case EjbPackage.RELATIONSHIP_ROLE_SOURCE_TYPE: return (EObject)createRelationshipRoleSourceType();
			case EjbPackage.RELATIONSHIPS: return (EObject)createRelationships();
			case EjbPackage.REMOVE_METHOD_TYPE: return (EObject)createRemoveMethodType();
			case EjbPackage.SECURITY_IDENTITY_TYPE: return (EObject)createSecurityIdentityType();
			case EjbPackage.SESSION_BEAN: return (EObject)createSessionBean();
			case EjbPackage.STATEFUL_TIMEOUT_TYPE: return (EObject)createStatefulTimeoutType();
			case EjbPackage.TIMER_SCHEDULE_TYPE: return (EObject)createTimerScheduleType();
			case EjbPackage.TIMER_TYPE: return (EObject)createTimerType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case EjbPackage.CMP_VERSION_TYPE:
				return createCmpVersionTypeFromString(eDataType, initialValue);
			case EjbPackage.CMR_FIELD_TYPE:
				return createCMRFieldTypeFromString(eDataType, initialValue);
			case EjbPackage.CONCURRENCY_MANAGEMENT_TYPE_TYPE:
				return createConcurrencyManagementTypeTypeFromString(eDataType, initialValue);
			case EjbPackage.CONCURRENT_LOCK_TYPE_TYPE:
				return createConcurrentLockTypeTypeFromString(eDataType, initialValue);
			case EjbPackage.METHOD_INTERFACE_TYPE:
				return createMethodInterfaceTypeFromString(eDataType, initialValue);
			case EjbPackage.MULTIPLICITY_TYPE:
				return createMultiplicityTypeFromString(eDataType, initialValue);
			case EjbPackage.PERSISTENCE_TYPE:
				return createPersistenceTypeFromString(eDataType, initialValue);
			case EjbPackage.RESULT_TYPE_MAPPING_TYPE:
				return createResultTypeMappingTypeFromString(eDataType, initialValue);
			case EjbPackage.SESSION_TYPE:
				return createSessionTypeFromString(eDataType, initialValue);
			case EjbPackage.TIME_UNIT_TYPE_TYPE:
				return createTimeUnitTypeTypeFromString(eDataType, initialValue);
			case EjbPackage.TRANSACTION_ATTRIBUTE_TYPE:
				return createTransactionAttributeTypeFromString(eDataType, initialValue);
			case EjbPackage.TRANSACTION_TYPE:
				return createTransactionTypeFromString(eDataType, initialValue);
			case EjbPackage.CMP_VERSION_TYPE_OBJECT:
				return createCmpVersionTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.CMR_FIELD_TYPE_OBJECT:
				return createCMRFieldTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.CONCURRENCY_MANAGEMENT_TYPE_TYPE_OBJECT:
				return createConcurrencyManagementTypeTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.CONCURRENT_LOCK_TYPE_TYPE_OBJECT:
				return createConcurrentLockTypeTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.EJB_CLASS_TYPE:
				return createEjbClassTypeFromString(eDataType, initialValue);
			case EjbPackage.EJB_NAME_TYPE:
				return createEjbNameTypeFromString(eDataType, initialValue);
			case EjbPackage.METHOD_INTERFACE_TYPE_OBJECT:
				return createMethodInterfaceTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.METHOD_NAME_TYPE:
				return createMethodNameTypeFromString(eDataType, initialValue);
			case EjbPackage.MULTIPLICITY_TYPE_OBJECT:
				return createMultiplicityTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.PERSISTENCE_TYPE_OBJECT:
				return createPersistenceTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.RESULT_TYPE_MAPPING_TYPE_OBJECT:
				return createResultTypeMappingTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.SESSION_TYPE_OBJECT:
				return createSessionTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.TIME_UNIT_TYPE_TYPE_OBJECT:
				return createTimeUnitTypeTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.TRANSACTION_ATTRIBUTE_TYPE_OBJECT:
				return createTransactionAttributeTypeObjectFromString(eDataType, initialValue);
			case EjbPackage.TRANSACTION_TYPE_OBJECT:
				return createTransactionTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case EjbPackage.CMP_VERSION_TYPE:
				return convertCmpVersionTypeToString(eDataType, instanceValue);
			case EjbPackage.CMR_FIELD_TYPE:
				return convertCMRFieldTypeToString(eDataType, instanceValue);
			case EjbPackage.CONCURRENCY_MANAGEMENT_TYPE_TYPE:
				return convertConcurrencyManagementTypeTypeToString(eDataType, instanceValue);
			case EjbPackage.CONCURRENT_LOCK_TYPE_TYPE:
				return convertConcurrentLockTypeTypeToString(eDataType, instanceValue);
			case EjbPackage.METHOD_INTERFACE_TYPE:
				return convertMethodInterfaceTypeToString(eDataType, instanceValue);
			case EjbPackage.MULTIPLICITY_TYPE:
				return convertMultiplicityTypeToString(eDataType, instanceValue);
			case EjbPackage.PERSISTENCE_TYPE:
				return convertPersistenceTypeToString(eDataType, instanceValue);
			case EjbPackage.RESULT_TYPE_MAPPING_TYPE:
				return convertResultTypeMappingTypeToString(eDataType, instanceValue);
			case EjbPackage.SESSION_TYPE:
				return convertSessionTypeToString(eDataType, instanceValue);
			case EjbPackage.TIME_UNIT_TYPE_TYPE:
				return convertTimeUnitTypeTypeToString(eDataType, instanceValue);
			case EjbPackage.TRANSACTION_ATTRIBUTE_TYPE:
				return convertTransactionAttributeTypeToString(eDataType, instanceValue);
			case EjbPackage.TRANSACTION_TYPE:
				return convertTransactionTypeToString(eDataType, instanceValue);
			case EjbPackage.CMP_VERSION_TYPE_OBJECT:
				return convertCmpVersionTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.CMR_FIELD_TYPE_OBJECT:
				return convertCMRFieldTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.CONCURRENCY_MANAGEMENT_TYPE_TYPE_OBJECT:
				return convertConcurrencyManagementTypeTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.CONCURRENT_LOCK_TYPE_TYPE_OBJECT:
				return convertConcurrentLockTypeTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.EJB_CLASS_TYPE:
				return convertEjbClassTypeToString(eDataType, instanceValue);
			case EjbPackage.EJB_NAME_TYPE:
				return convertEjbNameTypeToString(eDataType, instanceValue);
			case EjbPackage.METHOD_INTERFACE_TYPE_OBJECT:
				return convertMethodInterfaceTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.METHOD_NAME_TYPE:
				return convertMethodNameTypeToString(eDataType, instanceValue);
			case EjbPackage.MULTIPLICITY_TYPE_OBJECT:
				return convertMultiplicityTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.PERSISTENCE_TYPE_OBJECT:
				return convertPersistenceTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.RESULT_TYPE_MAPPING_TYPE_OBJECT:
				return convertResultTypeMappingTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.SESSION_TYPE_OBJECT:
				return convertSessionTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.TIME_UNIT_TYPE_TYPE_OBJECT:
				return convertTimeUnitTypeTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.TRANSACTION_ATTRIBUTE_TYPE_OBJECT:
				return convertTransactionAttributeTypeObjectToString(eDataType, instanceValue);
			case EjbPackage.TRANSACTION_TYPE_OBJECT:
				return convertTransactionTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AccessTimeoutType createAccessTimeoutType() {
		AccessTimeoutTypeImpl accessTimeoutType = new AccessTimeoutTypeImpl();
		return accessTimeoutType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActivationConfig createActivationConfig() {
		ActivationConfigImpl activationConfig = new ActivationConfigImpl();
		return activationConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActivationConfigProperty createActivationConfigProperty() {
		ActivationConfigPropertyImpl activationConfigProperty = new ActivationConfigPropertyImpl();
		return activationConfigProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ApplicationException createApplicationException() {
		ApplicationExceptionImpl applicationException = new ApplicationExceptionImpl();
		return applicationException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AroundInvokeType createAroundInvokeType() {
		AroundInvokeTypeImpl aroundInvokeType = new AroundInvokeTypeImpl();
		return aroundInvokeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AroundTimeoutType createAroundTimeoutType() {
		AroundTimeoutTypeImpl aroundTimeoutType = new AroundTimeoutTypeImpl();
		return aroundTimeoutType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssemblyDescriptor createAssemblyDescriptor() {
		AssemblyDescriptorImpl assemblyDescriptor = new AssemblyDescriptorImpl();
		return assemblyDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AsyncMethodType createAsyncMethodType() {
		AsyncMethodTypeImpl asyncMethodType = new AsyncMethodTypeImpl();
		return asyncMethodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CMPField createCMPField() {
		CMPFieldImpl cmpField = new CMPFieldImpl();
		return cmpField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CMRField createCMRField() {
		CMRFieldImpl cmrField = new CMRFieldImpl();
		return cmrField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConcurrentMethodType createConcurrentMethodType() {
		ConcurrentMethodTypeImpl concurrentMethodType = new ConcurrentMethodTypeImpl();
		return concurrentMethodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerTransactionType createContainerTransactionType() {
		ContainerTransactionTypeImpl containerTransactionType = new ContainerTransactionTypeImpl();
		return containerTransactionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DependsOnType createDependsOnType() {
		DependsOnTypeImpl dependsOnType = new DependsOnTypeImpl();
		return dependsOnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EJBJar createEJBJar() {
		EJBJarImpl ejbJar = new EJBJarImpl();
		return ejbJar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EJBJarDeploymentDescriptor createEJBJarDeploymentDescriptor() {
		EJBJarDeploymentDescriptorImpl ejbJarDeploymentDescriptor = new EJBJarDeploymentDescriptorImpl();
		return ejbJarDeploymentDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EJBRelation createEJBRelation() {
		EJBRelationImpl ejbRelation = new EJBRelationImpl();
		return ejbRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EJBRelationshipRole createEJBRelationshipRole() {
		EJBRelationshipRoleImpl ejbRelationshipRole = new EJBRelationshipRoleImpl();
		return ejbRelationshipRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnterpriseBeans createEnterpriseBeans() {
		EnterpriseBeansImpl enterpriseBeans = new EnterpriseBeansImpl();
		return enterpriseBeans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityBean createEntityBean() {
		EntityBeanImpl entityBean = new EntityBeanImpl();
		return entityBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExcludeList createExcludeList() {
		ExcludeListImpl excludeList = new ExcludeListImpl();
		return excludeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InitMethodType createInitMethodType() {
		InitMethodTypeImpl initMethodType = new InitMethodTypeImpl();
		return initMethodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptorBindingType createInterceptorBindingType() {
		InterceptorBindingTypeImpl interceptorBindingType = new InterceptorBindingTypeImpl();
		return interceptorBindingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptorOrderType createInterceptorOrderType() {
		InterceptorOrderTypeImpl interceptorOrderType = new InterceptorOrderTypeImpl();
		return interceptorOrderType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptorsType createInterceptorsType() {
		InterceptorsTypeImpl interceptorsType = new InterceptorsTypeImpl();
		return interceptorsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptorType createInterceptorType() {
		InterceptorTypeImpl interceptorType = new InterceptorTypeImpl();
		return interceptorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageDrivenBean createMessageDrivenBean() {
		MessageDrivenBeanImpl messageDrivenBean = new MessageDrivenBeanImpl();
		return messageDrivenBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MethodParams createMethodParams() {
		MethodParamsImpl methodParams = new MethodParamsImpl();
		return methodParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MethodPermission createMethodPermission() {
		MethodPermissionImpl methodPermission = new MethodPermissionImpl();
		return methodPermission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MethodType createMethodType() {
		MethodTypeImpl methodType = new MethodTypeImpl();
		return methodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedMethodType createNamedMethodType() {
		NamedMethodTypeImpl namedMethodType = new NamedMethodTypeImpl();
		return namedMethodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Query createQuery() {
		QueryImpl query = new QueryImpl();
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QueryMethod createQueryMethod() {
		QueryMethodImpl queryMethod = new QueryMethodImpl();
		return queryMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelationshipRoleSourceType createRelationshipRoleSourceType() {
		RelationshipRoleSourceTypeImpl relationshipRoleSourceType = new RelationshipRoleSourceTypeImpl();
		return relationshipRoleSourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Relationships createRelationships() {
		RelationshipsImpl relationships = new RelationshipsImpl();
		return relationships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RemoveMethodType createRemoveMethodType() {
		RemoveMethodTypeImpl removeMethodType = new RemoveMethodTypeImpl();
		return removeMethodType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SecurityIdentityType createSecurityIdentityType() {
		SecurityIdentityTypeImpl securityIdentityType = new SecurityIdentityTypeImpl();
		return securityIdentityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SessionBean createSessionBean() {
		SessionBeanImpl sessionBean = new SessionBeanImpl();
		return sessionBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StatefulTimeoutType createStatefulTimeoutType() {
		StatefulTimeoutTypeImpl statefulTimeoutType = new StatefulTimeoutTypeImpl();
		return statefulTimeoutType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimerScheduleType createTimerScheduleType() {
		TimerScheduleTypeImpl timerScheduleType = new TimerScheduleTypeImpl();
		return timerScheduleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimerType createTimerType() {
		TimerTypeImpl timerType = new TimerTypeImpl();
		return timerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CmpVersionType createCmpVersionTypeFromString(EDataType eDataType, String initialValue) {
		CmpVersionType result = CmpVersionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCmpVersionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CMRFieldType createCMRFieldTypeFromString(EDataType eDataType, String initialValue) {
		CMRFieldType result = CMRFieldType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCMRFieldTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConcurrencyManagementTypeType createConcurrencyManagementTypeTypeFromString(EDataType eDataType, String initialValue) {
		ConcurrencyManagementTypeType result = ConcurrencyManagementTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConcurrencyManagementTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConcurrentLockTypeType createConcurrentLockTypeTypeFromString(EDataType eDataType, String initialValue) {
		ConcurrentLockTypeType result = ConcurrentLockTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConcurrentLockTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInterfaceType createMethodInterfaceTypeFromString(EDataType eDataType, String initialValue) {
		MethodInterfaceType result = MethodInterfaceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMethodInterfaceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiplicityType createMultiplicityTypeFromString(EDataType eDataType, String initialValue) {
		MultiplicityType result = MultiplicityType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiplicityTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceType createPersistenceTypeFromString(EDataType eDataType, String initialValue) {
		PersistenceType result = PersistenceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPersistenceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResultTypeMappingType createResultTypeMappingTypeFromString(EDataType eDataType, String initialValue) {
		ResultTypeMappingType result = ResultTypeMappingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResultTypeMappingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SessionType createSessionTypeFromString(EDataType eDataType, String initialValue) {
		SessionType result = SessionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSessionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeUnitTypeType createTimeUnitTypeTypeFromString(EDataType eDataType, String initialValue) {
		TimeUnitTypeType result = TimeUnitTypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimeUnitTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionAttributeType createTransactionAttributeTypeFromString(EDataType eDataType, String initialValue) {
		TransactionAttributeType result = TransactionAttributeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionAttributeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionType createTransactionTypeFromString(EDataType eDataType, String initialValue) {
		TransactionType result = TransactionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CmpVersionType createCmpVersionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createCmpVersionTypeFromString(EjbPackage.Literals.CMP_VERSION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCmpVersionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertCmpVersionTypeToString(EjbPackage.Literals.CMP_VERSION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CMRFieldType createCMRFieldTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createCMRFieldTypeFromString(EjbPackage.Literals.CMR_FIELD_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCMRFieldTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertCMRFieldTypeToString(EjbPackage.Literals.CMR_FIELD_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConcurrencyManagementTypeType createConcurrencyManagementTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createConcurrencyManagementTypeTypeFromString(EjbPackage.Literals.CONCURRENCY_MANAGEMENT_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConcurrencyManagementTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertConcurrencyManagementTypeTypeToString(EjbPackage.Literals.CONCURRENCY_MANAGEMENT_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConcurrentLockTypeType createConcurrentLockTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createConcurrentLockTypeTypeFromString(EjbPackage.Literals.CONCURRENT_LOCK_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConcurrentLockTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertConcurrentLockTypeTypeToString(EjbPackage.Literals.CONCURRENT_LOCK_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEjbClassTypeFromString(EDataType eDataType, String initialValue) {
		return (String)JavaeeFactory.eINSTANCE.createFromString(JavaeePackage.Literals.FULLY_QUALIFIED_CLASS_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEjbClassTypeToString(EDataType eDataType, Object instanceValue) {
		return JavaeeFactory.eINSTANCE.convertToString(JavaeePackage.Literals.FULLY_QUALIFIED_CLASS_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEjbNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NMTOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEjbNameTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NMTOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInterfaceType createMethodInterfaceTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createMethodInterfaceTypeFromString(EjbPackage.Literals.METHOD_INTERFACE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMethodInterfaceTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMethodInterfaceTypeToString(EjbPackage.Literals.METHOD_INTERFACE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMethodNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMethodNameTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiplicityType createMultiplicityTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createMultiplicityTypeFromString(EjbPackage.Literals.MULTIPLICITY_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiplicityTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMultiplicityTypeToString(EjbPackage.Literals.MULTIPLICITY_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceType createPersistenceTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createPersistenceTypeFromString(EjbPackage.Literals.PERSISTENCE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPersistenceTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPersistenceTypeToString(EjbPackage.Literals.PERSISTENCE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResultTypeMappingType createResultTypeMappingTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createResultTypeMappingTypeFromString(EjbPackage.Literals.RESULT_TYPE_MAPPING_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResultTypeMappingTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertResultTypeMappingTypeToString(EjbPackage.Literals.RESULT_TYPE_MAPPING_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SessionType createSessionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createSessionTypeFromString(EjbPackage.Literals.SESSION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSessionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSessionTypeToString(EjbPackage.Literals.SESSION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeUnitTypeType createTimeUnitTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTimeUnitTypeTypeFromString(EjbPackage.Literals.TIME_UNIT_TYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTimeUnitTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTimeUnitTypeTypeToString(EjbPackage.Literals.TIME_UNIT_TYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionAttributeType createTransactionAttributeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTransactionAttributeTypeFromString(EjbPackage.Literals.TRANSACTION_ATTRIBUTE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionAttributeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTransactionAttributeTypeToString(EjbPackage.Literals.TRANSACTION_ATTRIBUTE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionType createTransactionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTransactionTypeFromString(EjbPackage.Literals.TRANSACTION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTransactionTypeToString(EjbPackage.Literals.TRANSACTION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EjbPackage getEjbPackage() {
		return (EjbPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EjbPackage getPackage() {
		return EjbPackage.eINSTANCE;
	}

} //EjbFactoryImpl
