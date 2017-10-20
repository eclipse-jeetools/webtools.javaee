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
package org.eclipse.jst.javaee.ejb.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;
import org.eclipse.jst.javaee.ejb.AccessTimeoutType;
import org.eclipse.jst.javaee.ejb.ActivationConfig;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.ApplicationException;
import org.eclipse.jst.javaee.ejb.AroundInvokeType;
import org.eclipse.jst.javaee.ejb.AroundTimeoutType;
import org.eclipse.jst.javaee.ejb.AssemblyDescriptor;
import org.eclipse.jst.javaee.ejb.AsyncMethodType;
import org.eclipse.jst.javaee.ejb.CMPField;
import org.eclipse.jst.javaee.ejb.CMRField;
import org.eclipse.jst.javaee.ejb.CMRFieldType;
import org.eclipse.jst.javaee.ejb.CmpVersionType;
import org.eclipse.jst.javaee.ejb.ConcurrencyManagementTypeType;
import org.eclipse.jst.javaee.ejb.ConcurrentLockTypeType;
import org.eclipse.jst.javaee.ejb.ConcurrentMethodType;
import org.eclipse.jst.javaee.ejb.ContainerTransactionType;
import org.eclipse.jst.javaee.ejb.DependsOnType;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor;
import org.eclipse.jst.javaee.ejb.EJBRelation;
import org.eclipse.jst.javaee.ejb.EJBRelationshipRole;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.ExcludeList;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.InterceptorBindingType;
import org.eclipse.jst.javaee.ejb.InterceptorOrderType;
import org.eclipse.jst.javaee.ejb.InterceptorType;
import org.eclipse.jst.javaee.ejb.InterceptorsType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.MethodInterfaceType;
import org.eclipse.jst.javaee.ejb.MethodParams;
import org.eclipse.jst.javaee.ejb.MethodPermission;
import org.eclipse.jst.javaee.ejb.MethodType;
import org.eclipse.jst.javaee.ejb.MultiplicityType;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.PersistenceType;
import org.eclipse.jst.javaee.ejb.Query;
import org.eclipse.jst.javaee.ejb.QueryMethod;
import org.eclipse.jst.javaee.ejb.RelationshipRoleSourceType;
import org.eclipse.jst.javaee.ejb.Relationships;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.ResultTypeMappingType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.SessionType;
import org.eclipse.jst.javaee.ejb.StatefulTimeoutType;
import org.eclipse.jst.javaee.ejb.TimeUnitTypeType;
import org.eclipse.jst.javaee.ejb.TimerScheduleType;
import org.eclipse.jst.javaee.ejb.TimerType;
import org.eclipse.jst.javaee.ejb.TransactionAttributeType;
import org.eclipse.jst.javaee.ejb.TransactionType;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EjbPackageImpl extends EPackageImpl implements EjbPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass accessTimeoutTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activationConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activationConfigPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationExceptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aroundInvokeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aroundTimeoutTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asyncMethodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cmpFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cmrFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concurrentMethodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerTransactionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependsOnTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbJarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbJarDeploymentDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbRelationshipRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enterpriseBeansEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityBeanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass excludeListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initMethodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interceptorBindingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interceptorOrderTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interceptorsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interceptorTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageDrivenBeanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodParamsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodPermissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedMethodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationshipRoleSourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationshipsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass removeMethodTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityIdentityTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sessionBeanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statefulTimeoutTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerScheduleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum cmpVersionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum cmrFieldTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum concurrencyManagementTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum concurrentLockTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum methodInterfaceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum multiplicityTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum persistenceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resultTypeMappingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sessionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum timeUnitTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transactionAttributeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum transactionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType cmpVersionTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType cmrFieldTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType concurrencyManagementTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType concurrentLockTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ejbClassTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ejbNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType methodInterfaceTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType methodNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType multiplicityTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType persistenceTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType resultTypeMappingTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sessionTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType timeUnitTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType transactionAttributeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType transactionTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * { @link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EjbPackageImpl() {
		super(eNS_URI, EjbFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link EjbPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EjbPackage init() {
		if (isInited) return (EjbPackage)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI);

		// Obtain or create and register package
		EjbPackageImpl theEjbPackage = (EjbPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EjbPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EjbPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLNamespacePackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEjbPackage.createPackageContents();

		// Initialize created meta-data
		theEjbPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEjbPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EjbPackage.eNS_URI, theEjbPackage);
		EPackage.Registry.INSTANCE.put(EjbPackage.eNS_URI2, theEjbPackage);
		EPackage.Registry.INSTANCE.put(EjbPackage.eNS_URI3, theEjbPackage);

		J2EEInit.initEMFModels();
		return theEjbPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAccessTimeoutType() {
		return accessTimeoutTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAccessTimeoutType_Timeout() {
		return (EAttribute)accessTimeoutTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAccessTimeoutType_Unit() {
		return (EAttribute)accessTimeoutTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAccessTimeoutType_Id() {
		return (EAttribute)accessTimeoutTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivationConfig() {
		return activationConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivationConfig_Descriptions() {
		return (EReference)activationConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivationConfig_ActivationConfigProperties() {
		return (EReference)activationConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivationConfig_Id() {
		return (EAttribute)activationConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivationConfigProperty() {
		return activationConfigPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivationConfigProperty_ActivationConfigPropertyName() {
		return (EAttribute)activationConfigPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivationConfigProperty_ActivationConfigPropertyValue() {
		return (EAttribute)activationConfigPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActivationConfigProperty_Id() {
		return (EAttribute)activationConfigPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationException() {
		return applicationExceptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationException_ExceptionClass() {
		return (EAttribute)applicationExceptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationException_Rollback() {
		return (EAttribute)applicationExceptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationException_Inherited() {
		return (EAttribute)applicationExceptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getApplicationException_Id() {
		return (EAttribute)applicationExceptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAroundInvokeType() {
		return aroundInvokeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAroundInvokeType_Class() {
		return (EAttribute)aroundInvokeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAroundInvokeType_MethodName() {
		return (EAttribute)aroundInvokeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAroundTimeoutType() {
		return aroundTimeoutTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAroundTimeoutType_Class() {
		return (EAttribute)aroundTimeoutTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAroundTimeoutType_MethodName() {
		return (EAttribute)aroundTimeoutTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyDescriptor() {
		return assemblyDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_SecurityRoles() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_MethodPermissions() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_ContainerTransactions() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_InterceptorBindings() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_MessageDestinations() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_ExcludeList() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyDescriptor_ApplicationExceptions() {
		return (EReference)assemblyDescriptorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssemblyDescriptor_Id() {
		return (EAttribute)assemblyDescriptorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAsyncMethodType() {
		return asyncMethodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAsyncMethodType_MethodName() {
		return (EAttribute)asyncMethodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAsyncMethodType_MethodParams() {
		return (EReference)asyncMethodTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAsyncMethodType_Id() {
		return (EAttribute)asyncMethodTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCMPField() {
		return cmpFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCMPField_Descriptions() {
		return (EReference)cmpFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCMPField_FieldName() {
		return (EAttribute)cmpFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCMPField_Id() {
		return (EAttribute)cmpFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCMRField() {
		return cmrFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCMRField_Descriptions() {
		return (EReference)cmrFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCMRField_CmrFieldName() {
		return (EAttribute)cmrFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCMRField_CmrFieldType() {
		return (EAttribute)cmrFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCMRField_Id() {
		return (EAttribute)cmrFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcurrentMethodType() {
		return concurrentMethodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcurrentMethodType_Method() {
		return (EReference)concurrentMethodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConcurrentMethodType_Lock() {
		return (EAttribute)concurrentMethodTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcurrentMethodType_AccessTimeout() {
		return (EReference)concurrentMethodTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConcurrentMethodType_Id() {
		return (EAttribute)concurrentMethodTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainerTransactionType() {
		return containerTransactionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerTransactionType_Descriptions() {
		return (EReference)containerTransactionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerTransactionType_Methods() {
		return (EReference)containerTransactionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContainerTransactionType_TransAttribute() {
		return (EAttribute)containerTransactionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContainerTransactionType_Id() {
		return (EAttribute)containerTransactionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependsOnType() {
		return dependsOnTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDependsOnType_EjbName() {
		return (EAttribute)dependsOnTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDependsOnType_Id() {
		return (EAttribute)dependsOnTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEJBJar() {
		return ejbJarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_Descriptions() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_DisplayNames() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_Icons() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBJar_ModuleName() {
		return (EAttribute)ejbJarEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_EnterpriseBeans() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_Interceptors() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_Relationships() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJar_AssemblyDescriptor() {
		return (EReference)ejbJarEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBJar_EjbClientJar() {
		return (EAttribute)ejbJarEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBJar_Id() {
		return (EAttribute)ejbJarEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBJar_MetadataComplete() {
		return (EAttribute)ejbJarEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBJar_Version() {
		return (EAttribute)ejbJarEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEJBJarDeploymentDescriptor() {
		return ejbJarDeploymentDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBJarDeploymentDescriptor_Mixed() {
		return (EAttribute)ejbJarDeploymentDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJarDeploymentDescriptor_XMLNSPrefixMap() {
		return (EReference)ejbJarDeploymentDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJarDeploymentDescriptor_XSISchemaLocation() {
		return (EReference)ejbJarDeploymentDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBJarDeploymentDescriptor_EjbJar() {
		return (EReference)ejbJarDeploymentDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEJBRelation() {
		return ejbRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBRelation_Descriptions() {
		return (EReference)ejbRelationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBRelation_EjbRelationName() {
		return (EAttribute)ejbRelationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBRelation_EjbRelationshipRoles() {
		return (EReference)ejbRelationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBRelation_Id() {
		return (EAttribute)ejbRelationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEJBRelationshipRole() {
		return ejbRelationshipRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBRelationshipRole_Descriptions() {
		return (EReference)ejbRelationshipRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBRelationshipRole_EjbRelationshipRoleName() {
		return (EAttribute)ejbRelationshipRoleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBRelationshipRole_Multiplicity() {
		return (EAttribute)ejbRelationshipRoleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBRelationshipRole_CascadeDelete() {
		return (EReference)ejbRelationshipRoleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBRelationshipRole_RelationshipRoleSource() {
		return (EReference)ejbRelationshipRoleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEJBRelationshipRole_CmrField() {
		return (EReference)ejbRelationshipRoleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEJBRelationshipRole_Id() {
		return (EAttribute)ejbRelationshipRoleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnterpriseBeans() {
		return enterpriseBeansEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnterpriseBeans_Group() {
		return (EAttribute)enterpriseBeansEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnterpriseBeans_SessionBeans() {
		return (EReference)enterpriseBeansEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnterpriseBeans_EntityBeans() {
		return (EReference)enterpriseBeansEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnterpriseBeans_MessageDrivenBeans() {
		return (EReference)enterpriseBeansEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnterpriseBeans_Id() {
		return (EAttribute)enterpriseBeansEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityBean() {
		return entityBeanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_Descriptions() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_DisplayNames() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_Icons() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_EjbName() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_MappedName() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_Home() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_Remote() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_LocalHome() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_Local() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_EjbClass() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_PersistenceType() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_PrimKeyClass() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_Reentrant() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_CmpVersion() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_AbstractSchemaName() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_CmpFields() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_PrimkeyField() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_EnvEntries() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_EjbRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_EjbLocalRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_ServiceRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_ResourceRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_ResourceEnvRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_MessageDestinationRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_PersistenceContextRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_PersistenceUnitRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_PostConstructs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_PreDestroys() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_DataSource() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_JmsConnectionFactory() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_JmsDestination() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_MailSession() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_ConnectionFactory() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_AdministeredObject() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_SecurityRoleRefs() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_SecurityIdentity() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityBean_Queries() {
		return (EReference)entityBeanEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityBean_Id() {
		return (EAttribute)entityBeanEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExcludeList() {
		return excludeListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExcludeList_Descriptions() {
		return (EReference)excludeListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExcludeList_Methods() {
		return (EReference)excludeListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExcludeList_Id() {
		return (EAttribute)excludeListEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitMethodType() {
		return initMethodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitMethodType_CreateMethod() {
		return (EReference)initMethodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitMethodType_BeanMethod() {
		return (EReference)initMethodTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInitMethodType_Id() {
		return (EAttribute)initMethodTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterceptorBindingType() {
		return interceptorBindingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorBindingType_Descriptions() {
		return (EReference)interceptorBindingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorBindingType_EjbName() {
		return (EAttribute)interceptorBindingTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorBindingType_InterceptorClasses() {
		return (EAttribute)interceptorBindingTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorBindingType_InterceptorOrder() {
		return (EReference)interceptorBindingTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorBindingType_ExcludeDefaultInterceptors() {
		return (EAttribute)interceptorBindingTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorBindingType_ExcludeClassInterceptors() {
		return (EAttribute)interceptorBindingTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorBindingType_Method() {
		return (EReference)interceptorBindingTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorBindingType_Id() {
		return (EAttribute)interceptorBindingTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterceptorOrderType() {
		return interceptorOrderTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorOrderType_InterceptorClasses() {
		return (EAttribute)interceptorOrderTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorOrderType_Id() {
		return (EAttribute)interceptorOrderTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterceptorsType() {
		return interceptorsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorsType_Descriptions() {
		return (EReference)interceptorsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorsType_Interceptors() {
		return (EReference)interceptorsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorsType_Id() {
		return (EAttribute)interceptorsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterceptorType() {
		return interceptorTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_Descriptions() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorType_InterceptorClass() {
		return (EAttribute)interceptorTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_AroundInvokes() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_AroundTimeouts() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_AroundConstruct() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_EnvEntries() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_EjbRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_EjbLocalRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_ServiceRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_ResourceRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_ResourceEnvRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_MessageDestinationRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_PersistenceContextRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_PersistenceUnitRefs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_PostConstructs() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_PreDestroys() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_DataSource() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_JmsConnectionFactory() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_JmsDestination() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_MailSession() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_ConnectionFactory() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_AdministeredObject() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_PostActivates() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInterceptorType_PrePassivates() {
		return (EReference)interceptorTypeEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInterceptorType_Id() {
		return (EAttribute)interceptorTypeEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageDrivenBean() {
		return messageDrivenBeanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_Descriptions() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_DisplayNames() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_Icons() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_EjbName() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_MappedName() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_EjbClass() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_MessagingType() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_TimeoutMethod() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_Timer() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_TransactionType() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_MessageDestinationType() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_MessageDestinationLink() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_ActivationConfig() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_AroundInvokes() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_AroundTimeouts() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_EnvEntries() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_EjbRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_EjbLocalRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_ServiceRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_ResourceRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_ResourceEnvRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_MessageDestinationRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_PersistenceContextRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_PersistenceUnitRefs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_PostConstructs() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_PreDestroys() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_DataSource() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_JmsConnectionFactory() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_JmsDestination() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_MailSession() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_ConnectionFactory() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_AdministeredObject() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_SecurityRoleRef() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDrivenBean_SecurityIdentity() {
		return (EReference)messageDrivenBeanEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDrivenBean_Id() {
		return (EAttribute)messageDrivenBeanEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodParams() {
		return methodParamsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodParams_MethodParams() {
		return (EAttribute)methodParamsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodParams_Id() {
		return (EAttribute)methodParamsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodPermission() {
		return methodPermissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPermission_Descriptions() {
		return (EReference)methodPermissionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPermission_RoleNames() {
		return (EAttribute)methodPermissionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPermission_Unchecked() {
		return (EReference)methodPermissionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodPermission_Methods() {
		return (EReference)methodPermissionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodPermission_Id() {
		return (EAttribute)methodPermissionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodType() {
		return methodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodType_Descriptions() {
		return (EReference)methodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodType_EjbName() {
		return (EAttribute)methodTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodType_MethodIntf() {
		return (EAttribute)methodTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodType_MethodName() {
		return (EAttribute)methodTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodType_MethodParams() {
		return (EReference)methodTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodType_Id() {
		return (EAttribute)methodTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedMethodType() {
		return namedMethodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedMethodType_MethodName() {
		return (EAttribute)namedMethodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedMethodType_MethodParams() {
		return (EReference)namedMethodTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedMethodType_Id() {
		return (EAttribute)namedMethodTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuery() {
		return queryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuery_Description() {
		return (EReference)queryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuery_QueryMethod() {
		return (EReference)queryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuery_ResultTypeMapping() {
		return (EAttribute)queryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuery_EjbQl() {
		return (EAttribute)queryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuery_Id() {
		return (EAttribute)queryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQueryMethod() {
		return queryMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryMethod_MethodName() {
		return (EAttribute)queryMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQueryMethod_MethodParams() {
		return (EReference)queryMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryMethod_Id() {
		return (EAttribute)queryMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationshipRoleSourceType() {
		return relationshipRoleSourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationshipRoleSourceType_Descriptions() {
		return (EReference)relationshipRoleSourceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelationshipRoleSourceType_EjbName() {
		return (EAttribute)relationshipRoleSourceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelationshipRoleSourceType_Id() {
		return (EAttribute)relationshipRoleSourceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationships() {
		return relationshipsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationships_Descriptions() {
		return (EReference)relationshipsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelationships_EjbRelations() {
		return (EReference)relationshipsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRelationships_Id() {
		return (EAttribute)relationshipsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRemoveMethodType() {
		return removeMethodTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRemoveMethodType_BeanMethod() {
		return (EReference)removeMethodTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemoveMethodType_RetainIfException() {
		return (EAttribute)removeMethodTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRemoveMethodType_Id() {
		return (EAttribute)removeMethodTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityIdentityType() {
		return securityIdentityTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityIdentityType_Descriptions() {
		return (EReference)securityIdentityTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityIdentityType_UseCallerIdentity() {
		return (EReference)securityIdentityTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityIdentityType_RunAs() {
		return (EReference)securityIdentityTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityIdentityType_Id() {
		return (EAttribute)securityIdentityTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSessionBean() {
		return sessionBeanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_Descriptions() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_DisplayNames() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_Icons() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_EjbName() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_MappedName() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_Home() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_Remote() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_LocalHome() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_Local() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_BusinessLocals() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_BusinessRemotes() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_LocalBean() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_ServiceEndpoint() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_EjbClass() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_SessionType() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_StatefulTimeout() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_TimeoutMethod() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_Timer() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_InitOnStartup() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_ConcurrencyManagementType() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_ConcurrentMethod() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_DependsOn() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_InitMethods() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_RemoveMethods() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_AsyncMethod() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_TransactionType() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_AfterBeginMethod() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_BeforeCompletionMethod() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_AfterCompletionMethod() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_AroundInvokes() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_AroundTimeouts() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_EnvEntries() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_EjbRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_EjbLocalRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_ServiceRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_ResourceRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_ResourceEnvRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_MessageDestinationRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_PersistenceContextRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_PersistenceUnitRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_PostConstructs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_PreDestroys() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_DataSource() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_JmsConnectionFactory() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_JmsDestination() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_MailSession() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_ConnectionFactory() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_AdministeredObject() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_PostActivates() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_PrePassivates() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_SecurityRoleRefs() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSessionBean_SecurityIdentities() {
		return (EReference)sessionBeanEClass.getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_PassivationCapable() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSessionBean_Id() {
		return (EAttribute)sessionBeanEClass.getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatefulTimeoutType() {
		return statefulTimeoutTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatefulTimeoutType_Timeout() {
		return (EAttribute)statefulTimeoutTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatefulTimeoutType_Unit() {
		return (EAttribute)statefulTimeoutTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatefulTimeoutType_Id() {
		return (EAttribute)statefulTimeoutTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerScheduleType() {
		return timerScheduleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_Second() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_Minute() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_Hour() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_DayOfMonth() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_Month() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_DayOfWeek() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_Year() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerScheduleType_Id() {
		return (EAttribute)timerScheduleTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerType() {
		return timerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_Description() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_Schedule() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Start() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_End() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerType_TimeoutMethod() {
		return (EReference)timerTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Persistent() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Timezone() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Info() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimerType_Id() {
		return (EAttribute)timerTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCmpVersionType() {
		return cmpVersionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCMRFieldType() {
		return cmrFieldTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConcurrencyManagementTypeType() {
		return concurrencyManagementTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConcurrentLockTypeType() {
		return concurrentLockTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMethodInterfaceType() {
		return methodInterfaceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMultiplicityType() {
		return multiplicityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPersistenceType() {
		return persistenceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getResultTypeMappingType() {
		return resultTypeMappingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSessionType() {
		return sessionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTimeUnitTypeType() {
		return timeUnitTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTransactionAttributeType() {
		return transactionAttributeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTransactionType() {
		return transactionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCmpVersionTypeObject() {
		return cmpVersionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCMRFieldTypeObject() {
		return cmrFieldTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getConcurrencyManagementTypeTypeObject() {
		return concurrencyManagementTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getConcurrentLockTypeTypeObject() {
		return concurrentLockTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEjbClassType() {
		return ejbClassTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEjbNameType() {
		return ejbNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMethodInterfaceTypeObject() {
		return methodInterfaceTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMethodNameType() {
		return methodNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMultiplicityTypeObject() {
		return multiplicityTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPersistenceTypeObject() {
		return persistenceTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getResultTypeMappingTypeObject() {
		return resultTypeMappingTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getSessionTypeObject() {
		return sessionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTimeUnitTypeTypeObject() {
		return timeUnitTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTransactionAttributeTypeObject() {
		return transactionAttributeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTransactionTypeObject() {
		return transactionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbFactory getEjbFactory() {
		return (EjbFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		accessTimeoutTypeEClass = createEClass(ACCESS_TIMEOUT_TYPE);
		createEAttribute(accessTimeoutTypeEClass, ACCESS_TIMEOUT_TYPE__TIMEOUT);
		createEAttribute(accessTimeoutTypeEClass, ACCESS_TIMEOUT_TYPE__UNIT);
		createEAttribute(accessTimeoutTypeEClass, ACCESS_TIMEOUT_TYPE__ID);

		activationConfigEClass = createEClass(ACTIVATION_CONFIG);
		createEReference(activationConfigEClass, ACTIVATION_CONFIG__DESCRIPTIONS);
		createEReference(activationConfigEClass, ACTIVATION_CONFIG__ACTIVATION_CONFIG_PROPERTIES);
		createEAttribute(activationConfigEClass, ACTIVATION_CONFIG__ID);

		activationConfigPropertyEClass = createEClass(ACTIVATION_CONFIG_PROPERTY);
		createEAttribute(activationConfigPropertyEClass, ACTIVATION_CONFIG_PROPERTY__ACTIVATION_CONFIG_PROPERTY_NAME);
		createEAttribute(activationConfigPropertyEClass, ACTIVATION_CONFIG_PROPERTY__ACTIVATION_CONFIG_PROPERTY_VALUE);
		createEAttribute(activationConfigPropertyEClass, ACTIVATION_CONFIG_PROPERTY__ID);

		applicationExceptionEClass = createEClass(APPLICATION_EXCEPTION);
		createEAttribute(applicationExceptionEClass, APPLICATION_EXCEPTION__EXCEPTION_CLASS);
		createEAttribute(applicationExceptionEClass, APPLICATION_EXCEPTION__ROLLBACK);
		createEAttribute(applicationExceptionEClass, APPLICATION_EXCEPTION__INHERITED);
		createEAttribute(applicationExceptionEClass, APPLICATION_EXCEPTION__ID);

		aroundInvokeTypeEClass = createEClass(AROUND_INVOKE_TYPE);
		createEAttribute(aroundInvokeTypeEClass, AROUND_INVOKE_TYPE__CLASS);
		createEAttribute(aroundInvokeTypeEClass, AROUND_INVOKE_TYPE__METHOD_NAME);

		aroundTimeoutTypeEClass = createEClass(AROUND_TIMEOUT_TYPE);
		createEAttribute(aroundTimeoutTypeEClass, AROUND_TIMEOUT_TYPE__CLASS);
		createEAttribute(aroundTimeoutTypeEClass, AROUND_TIMEOUT_TYPE__METHOD_NAME);

		assemblyDescriptorEClass = createEClass(ASSEMBLY_DESCRIPTOR);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__SECURITY_ROLES);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST);
		createEReference(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS);
		createEAttribute(assemblyDescriptorEClass, ASSEMBLY_DESCRIPTOR__ID);

		asyncMethodTypeEClass = createEClass(ASYNC_METHOD_TYPE);
		createEAttribute(asyncMethodTypeEClass, ASYNC_METHOD_TYPE__METHOD_NAME);
		createEReference(asyncMethodTypeEClass, ASYNC_METHOD_TYPE__METHOD_PARAMS);
		createEAttribute(asyncMethodTypeEClass, ASYNC_METHOD_TYPE__ID);

		cmpFieldEClass = createEClass(CMP_FIELD);
		createEReference(cmpFieldEClass, CMP_FIELD__DESCRIPTIONS);
		createEAttribute(cmpFieldEClass, CMP_FIELD__FIELD_NAME);
		createEAttribute(cmpFieldEClass, CMP_FIELD__ID);

		cmrFieldEClass = createEClass(CMR_FIELD);
		createEReference(cmrFieldEClass, CMR_FIELD__DESCRIPTIONS);
		createEAttribute(cmrFieldEClass, CMR_FIELD__CMR_FIELD_NAME);
		createEAttribute(cmrFieldEClass, CMR_FIELD__CMR_FIELD_TYPE);
		createEAttribute(cmrFieldEClass, CMR_FIELD__ID);

		concurrentMethodTypeEClass = createEClass(CONCURRENT_METHOD_TYPE);
		createEReference(concurrentMethodTypeEClass, CONCURRENT_METHOD_TYPE__METHOD);
		createEAttribute(concurrentMethodTypeEClass, CONCURRENT_METHOD_TYPE__LOCK);
		createEReference(concurrentMethodTypeEClass, CONCURRENT_METHOD_TYPE__ACCESS_TIMEOUT);
		createEAttribute(concurrentMethodTypeEClass, CONCURRENT_METHOD_TYPE__ID);

		containerTransactionTypeEClass = createEClass(CONTAINER_TRANSACTION_TYPE);
		createEReference(containerTransactionTypeEClass, CONTAINER_TRANSACTION_TYPE__DESCRIPTIONS);
		createEReference(containerTransactionTypeEClass, CONTAINER_TRANSACTION_TYPE__METHODS);
		createEAttribute(containerTransactionTypeEClass, CONTAINER_TRANSACTION_TYPE__TRANS_ATTRIBUTE);
		createEAttribute(containerTransactionTypeEClass, CONTAINER_TRANSACTION_TYPE__ID);

		dependsOnTypeEClass = createEClass(DEPENDS_ON_TYPE);
		createEAttribute(dependsOnTypeEClass, DEPENDS_ON_TYPE__EJB_NAME);
		createEAttribute(dependsOnTypeEClass, DEPENDS_ON_TYPE__ID);

		ejbJarEClass = createEClass(EJB_JAR);
		createEAttribute(ejbJarEClass, EJB_JAR__MODULE_NAME);
		createEReference(ejbJarEClass, EJB_JAR__DESCRIPTIONS);
		createEReference(ejbJarEClass, EJB_JAR__DISPLAY_NAMES);
		createEReference(ejbJarEClass, EJB_JAR__ICONS);
		createEReference(ejbJarEClass, EJB_JAR__ENTERPRISE_BEANS);
		createEReference(ejbJarEClass, EJB_JAR__INTERCEPTORS);
		createEReference(ejbJarEClass, EJB_JAR__RELATIONSHIPS);
		createEReference(ejbJarEClass, EJB_JAR__ASSEMBLY_DESCRIPTOR);
		createEAttribute(ejbJarEClass, EJB_JAR__EJB_CLIENT_JAR);
		createEAttribute(ejbJarEClass, EJB_JAR__ID);
		createEAttribute(ejbJarEClass, EJB_JAR__METADATA_COMPLETE);
		createEAttribute(ejbJarEClass, EJB_JAR__VERSION);

		ejbJarDeploymentDescriptorEClass = createEClass(EJB_JAR_DEPLOYMENT_DESCRIPTOR);
		createEAttribute(ejbJarDeploymentDescriptorEClass, EJB_JAR_DEPLOYMENT_DESCRIPTOR__MIXED);
		createEReference(ejbJarDeploymentDescriptorEClass, EJB_JAR_DEPLOYMENT_DESCRIPTOR__XMLNS_PREFIX_MAP);
		createEReference(ejbJarDeploymentDescriptorEClass, EJB_JAR_DEPLOYMENT_DESCRIPTOR__XSI_SCHEMA_LOCATION);
		createEReference(ejbJarDeploymentDescriptorEClass, EJB_JAR_DEPLOYMENT_DESCRIPTOR__EJB_JAR);

		ejbRelationEClass = createEClass(EJB_RELATION);
		createEReference(ejbRelationEClass, EJB_RELATION__DESCRIPTIONS);
		createEAttribute(ejbRelationEClass, EJB_RELATION__EJB_RELATION_NAME);
		createEReference(ejbRelationEClass, EJB_RELATION__EJB_RELATIONSHIP_ROLES);
		createEAttribute(ejbRelationEClass, EJB_RELATION__ID);

		ejbRelationshipRoleEClass = createEClass(EJB_RELATIONSHIP_ROLE);
		createEReference(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__DESCRIPTIONS);
		createEAttribute(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__EJB_RELATIONSHIP_ROLE_NAME);
		createEAttribute(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__MULTIPLICITY);
		createEReference(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__CASCADE_DELETE);
		createEReference(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__RELATIONSHIP_ROLE_SOURCE);
		createEReference(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__CMR_FIELD);
		createEAttribute(ejbRelationshipRoleEClass, EJB_RELATIONSHIP_ROLE__ID);

		enterpriseBeansEClass = createEClass(ENTERPRISE_BEANS);
		createEAttribute(enterpriseBeansEClass, ENTERPRISE_BEANS__GROUP);
		createEReference(enterpriseBeansEClass, ENTERPRISE_BEANS__SESSION_BEANS);
		createEReference(enterpriseBeansEClass, ENTERPRISE_BEANS__ENTITY_BEANS);
		createEReference(enterpriseBeansEClass, ENTERPRISE_BEANS__MESSAGE_DRIVEN_BEANS);
		createEAttribute(enterpriseBeansEClass, ENTERPRISE_BEANS__ID);

		entityBeanEClass = createEClass(ENTITY_BEAN);
		createEReference(entityBeanEClass, ENTITY_BEAN__DESCRIPTIONS);
		createEReference(entityBeanEClass, ENTITY_BEAN__DISPLAY_NAMES);
		createEReference(entityBeanEClass, ENTITY_BEAN__ICONS);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__EJB_NAME);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__MAPPED_NAME);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__HOME);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__REMOTE);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__LOCAL_HOME);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__LOCAL);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__EJB_CLASS);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__PERSISTENCE_TYPE);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__PRIM_KEY_CLASS);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__REENTRANT);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__CMP_VERSION);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__ABSTRACT_SCHEMA_NAME);
		createEReference(entityBeanEClass, ENTITY_BEAN__CMP_FIELDS);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__PRIMKEY_FIELD);
		createEReference(entityBeanEClass, ENTITY_BEAN__ENV_ENTRIES);
		createEReference(entityBeanEClass, ENTITY_BEAN__EJB_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__EJB_LOCAL_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__SERVICE_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__RESOURCE_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__RESOURCE_ENV_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__MESSAGE_DESTINATION_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__PERSISTENCE_CONTEXT_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__PERSISTENCE_UNIT_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__POST_CONSTRUCTS);
		createEReference(entityBeanEClass, ENTITY_BEAN__PRE_DESTROYS);
		createEReference(entityBeanEClass, ENTITY_BEAN__DATA_SOURCE);
		createEReference(entityBeanEClass, ENTITY_BEAN__JMS_CONNECTION_FACTORY);
		createEReference(entityBeanEClass, ENTITY_BEAN__JMS_DESTINATION);
		createEReference(entityBeanEClass, ENTITY_BEAN__MAIL_SESSION);
		createEReference(entityBeanEClass, ENTITY_BEAN__CONNECTION_FACTORY);
		createEReference(entityBeanEClass, ENTITY_BEAN__ADMINISTERED_OBJECT);
		createEReference(entityBeanEClass, ENTITY_BEAN__SECURITY_ROLE_REFS);
		createEReference(entityBeanEClass, ENTITY_BEAN__SECURITY_IDENTITY);
		createEReference(entityBeanEClass, ENTITY_BEAN__QUERIES);
		createEAttribute(entityBeanEClass, ENTITY_BEAN__ID);

		excludeListEClass = createEClass(EXCLUDE_LIST);
		createEReference(excludeListEClass, EXCLUDE_LIST__DESCRIPTIONS);
		createEReference(excludeListEClass, EXCLUDE_LIST__METHODS);
		createEAttribute(excludeListEClass, EXCLUDE_LIST__ID);

		initMethodTypeEClass = createEClass(INIT_METHOD_TYPE);
		createEReference(initMethodTypeEClass, INIT_METHOD_TYPE__CREATE_METHOD);
		createEReference(initMethodTypeEClass, INIT_METHOD_TYPE__BEAN_METHOD);
		createEAttribute(initMethodTypeEClass, INIT_METHOD_TYPE__ID);

		interceptorBindingTypeEClass = createEClass(INTERCEPTOR_BINDING_TYPE);
		createEReference(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__DESCRIPTIONS);
		createEAttribute(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__EJB_NAME);
		createEAttribute(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_CLASSES);
		createEReference(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__INTERCEPTOR_ORDER);
		createEAttribute(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__EXCLUDE_DEFAULT_INTERCEPTORS);
		createEAttribute(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__EXCLUDE_CLASS_INTERCEPTORS);
		createEReference(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__METHOD);
		createEAttribute(interceptorBindingTypeEClass, INTERCEPTOR_BINDING_TYPE__ID);

		interceptorOrderTypeEClass = createEClass(INTERCEPTOR_ORDER_TYPE);
		createEAttribute(interceptorOrderTypeEClass, INTERCEPTOR_ORDER_TYPE__INTERCEPTOR_CLASSES);
		createEAttribute(interceptorOrderTypeEClass, INTERCEPTOR_ORDER_TYPE__ID);

		interceptorsTypeEClass = createEClass(INTERCEPTORS_TYPE);
		createEReference(interceptorsTypeEClass, INTERCEPTORS_TYPE__DESCRIPTIONS);
		createEReference(interceptorsTypeEClass, INTERCEPTORS_TYPE__INTERCEPTORS);
		createEAttribute(interceptorsTypeEClass, INTERCEPTORS_TYPE__ID);

		interceptorTypeEClass = createEClass(INTERCEPTOR_TYPE);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__DESCRIPTIONS);
		createEAttribute(interceptorTypeEClass, INTERCEPTOR_TYPE__INTERCEPTOR_CLASS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__AROUND_INVOKES);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__AROUND_TIMEOUTS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__AROUND_CONSTRUCT);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__ENV_ENTRIES);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__EJB_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__EJB_LOCAL_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__SERVICE_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__RESOURCE_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__RESOURCE_ENV_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__POST_CONSTRUCTS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__PRE_DESTROYS);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__DATA_SOURCE);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__JMS_CONNECTION_FACTORY);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__JMS_DESTINATION);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__MAIL_SESSION);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__CONNECTION_FACTORY);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__ADMINISTERED_OBJECT);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__POST_ACTIVATES);
		createEReference(interceptorTypeEClass, INTERCEPTOR_TYPE__PRE_PASSIVATES);
		createEAttribute(interceptorTypeEClass, INTERCEPTOR_TYPE__ID);

		messageDrivenBeanEClass = createEClass(MESSAGE_DRIVEN_BEAN);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__DESCRIPTIONS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__DISPLAY_NAMES);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__ICONS);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__EJB_NAME);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__MAPPED_NAME);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__EJB_CLASS);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__MESSAGING_TYPE);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__TIMEOUT_METHOD);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__TIMER);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__TRANSACTION_TYPE);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_TYPE);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_LINK);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__ACTIVATION_CONFIG);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__AROUND_INVOKES);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__AROUND_TIMEOUTS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__ENV_ENTRIES);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__EJB_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__EJB_LOCAL_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__SERVICE_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__RESOURCE_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__RESOURCE_ENV_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__MESSAGE_DESTINATION_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__PERSISTENCE_CONTEXT_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__PERSISTENCE_UNIT_REFS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__POST_CONSTRUCTS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__PRE_DESTROYS);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__DATA_SOURCE);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__JMS_CONNECTION_FACTORY);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__JMS_DESTINATION);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__MAIL_SESSION);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__CONNECTION_FACTORY);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__ADMINISTERED_OBJECT);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__SECURITY_ROLE_REF);
		createEReference(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__SECURITY_IDENTITY);
		createEAttribute(messageDrivenBeanEClass, MESSAGE_DRIVEN_BEAN__ID);

		methodParamsEClass = createEClass(METHOD_PARAMS);
		createEAttribute(methodParamsEClass, METHOD_PARAMS__METHOD_PARAMS);
		createEAttribute(methodParamsEClass, METHOD_PARAMS__ID);

		methodPermissionEClass = createEClass(METHOD_PERMISSION);
		createEReference(methodPermissionEClass, METHOD_PERMISSION__DESCRIPTIONS);
		createEAttribute(methodPermissionEClass, METHOD_PERMISSION__ROLE_NAMES);
		createEReference(methodPermissionEClass, METHOD_PERMISSION__UNCHECKED);
		createEReference(methodPermissionEClass, METHOD_PERMISSION__METHODS);
		createEAttribute(methodPermissionEClass, METHOD_PERMISSION__ID);

		methodTypeEClass = createEClass(METHOD_TYPE);
		createEReference(methodTypeEClass, METHOD_TYPE__DESCRIPTIONS);
		createEAttribute(methodTypeEClass, METHOD_TYPE__EJB_NAME);
		createEAttribute(methodTypeEClass, METHOD_TYPE__METHOD_INTF);
		createEAttribute(methodTypeEClass, METHOD_TYPE__METHOD_NAME);
		createEReference(methodTypeEClass, METHOD_TYPE__METHOD_PARAMS);
		createEAttribute(methodTypeEClass, METHOD_TYPE__ID);

		namedMethodTypeEClass = createEClass(NAMED_METHOD_TYPE);
		createEAttribute(namedMethodTypeEClass, NAMED_METHOD_TYPE__METHOD_NAME);
		createEReference(namedMethodTypeEClass, NAMED_METHOD_TYPE__METHOD_PARAMS);
		createEAttribute(namedMethodTypeEClass, NAMED_METHOD_TYPE__ID);

		queryEClass = createEClass(QUERY);
		createEReference(queryEClass, QUERY__DESCRIPTION);
		createEReference(queryEClass, QUERY__QUERY_METHOD);
		createEAttribute(queryEClass, QUERY__RESULT_TYPE_MAPPING);
		createEAttribute(queryEClass, QUERY__EJB_QL);
		createEAttribute(queryEClass, QUERY__ID);

		queryMethodEClass = createEClass(QUERY_METHOD);
		createEAttribute(queryMethodEClass, QUERY_METHOD__METHOD_NAME);
		createEReference(queryMethodEClass, QUERY_METHOD__METHOD_PARAMS);
		createEAttribute(queryMethodEClass, QUERY_METHOD__ID);

		relationshipRoleSourceTypeEClass = createEClass(RELATIONSHIP_ROLE_SOURCE_TYPE);
		createEReference(relationshipRoleSourceTypeEClass, RELATIONSHIP_ROLE_SOURCE_TYPE__DESCRIPTIONS);
		createEAttribute(relationshipRoleSourceTypeEClass, RELATIONSHIP_ROLE_SOURCE_TYPE__EJB_NAME);
		createEAttribute(relationshipRoleSourceTypeEClass, RELATIONSHIP_ROLE_SOURCE_TYPE__ID);

		relationshipsEClass = createEClass(RELATIONSHIPS);
		createEReference(relationshipsEClass, RELATIONSHIPS__DESCRIPTIONS);
		createEReference(relationshipsEClass, RELATIONSHIPS__EJB_RELATIONS);
		createEAttribute(relationshipsEClass, RELATIONSHIPS__ID);

		removeMethodTypeEClass = createEClass(REMOVE_METHOD_TYPE);
		createEReference(removeMethodTypeEClass, REMOVE_METHOD_TYPE__BEAN_METHOD);
		createEAttribute(removeMethodTypeEClass, REMOVE_METHOD_TYPE__RETAIN_IF_EXCEPTION);
		createEAttribute(removeMethodTypeEClass, REMOVE_METHOD_TYPE__ID);

		securityIdentityTypeEClass = createEClass(SECURITY_IDENTITY_TYPE);
		createEReference(securityIdentityTypeEClass, SECURITY_IDENTITY_TYPE__DESCRIPTIONS);
		createEReference(securityIdentityTypeEClass, SECURITY_IDENTITY_TYPE__USE_CALLER_IDENTITY);
		createEReference(securityIdentityTypeEClass, SECURITY_IDENTITY_TYPE__RUN_AS);
		createEAttribute(securityIdentityTypeEClass, SECURITY_IDENTITY_TYPE__ID);

		sessionBeanEClass = createEClass(SESSION_BEAN);
		createEReference(sessionBeanEClass, SESSION_BEAN__DESCRIPTIONS);
		createEReference(sessionBeanEClass, SESSION_BEAN__DISPLAY_NAMES);
		createEReference(sessionBeanEClass, SESSION_BEAN__ICONS);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__EJB_NAME);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__MAPPED_NAME);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__HOME);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__REMOTE);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__LOCAL_HOME);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__LOCAL);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__BUSINESS_LOCALS);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__BUSINESS_REMOTES);
		createEReference(sessionBeanEClass, SESSION_BEAN__LOCAL_BEAN);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__SERVICE_ENDPOINT);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__EJB_CLASS);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__SESSION_TYPE);
		createEReference(sessionBeanEClass, SESSION_BEAN__STATEFUL_TIMEOUT);
		createEReference(sessionBeanEClass, SESSION_BEAN__TIMEOUT_METHOD);
		createEReference(sessionBeanEClass, SESSION_BEAN__TIMER);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__INIT_ON_STARTUP);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__CONCURRENCY_MANAGEMENT_TYPE);
		createEReference(sessionBeanEClass, SESSION_BEAN__CONCURRENT_METHOD);
		createEReference(sessionBeanEClass, SESSION_BEAN__DEPENDS_ON);
		createEReference(sessionBeanEClass, SESSION_BEAN__INIT_METHODS);
		createEReference(sessionBeanEClass, SESSION_BEAN__REMOVE_METHODS);
		createEReference(sessionBeanEClass, SESSION_BEAN__ASYNC_METHOD);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__TRANSACTION_TYPE);
		createEReference(sessionBeanEClass, SESSION_BEAN__AFTER_BEGIN_METHOD);
		createEReference(sessionBeanEClass, SESSION_BEAN__BEFORE_COMPLETION_METHOD);
		createEReference(sessionBeanEClass, SESSION_BEAN__AFTER_COMPLETION_METHOD);
		createEReference(sessionBeanEClass, SESSION_BEAN__AROUND_INVOKES);
		createEReference(sessionBeanEClass, SESSION_BEAN__AROUND_TIMEOUTS);
		createEReference(sessionBeanEClass, SESSION_BEAN__ENV_ENTRIES);
		createEReference(sessionBeanEClass, SESSION_BEAN__EJB_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__EJB_LOCAL_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__SERVICE_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__RESOURCE_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__RESOURCE_ENV_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__MESSAGE_DESTINATION_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__PERSISTENCE_CONTEXT_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__PERSISTENCE_UNIT_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__POST_CONSTRUCTS);
		createEReference(sessionBeanEClass, SESSION_BEAN__PRE_DESTROYS);
		createEReference(sessionBeanEClass, SESSION_BEAN__DATA_SOURCE);
		createEReference(sessionBeanEClass, SESSION_BEAN__JMS_CONNECTION_FACTORY);
		createEReference(sessionBeanEClass, SESSION_BEAN__JMS_DESTINATION);
		createEReference(sessionBeanEClass, SESSION_BEAN__MAIL_SESSION);
		createEReference(sessionBeanEClass, SESSION_BEAN__CONNECTION_FACTORY);
		createEReference(sessionBeanEClass, SESSION_BEAN__ADMINISTERED_OBJECT);
		createEReference(sessionBeanEClass, SESSION_BEAN__POST_ACTIVATES);
		createEReference(sessionBeanEClass, SESSION_BEAN__PRE_PASSIVATES);
		createEReference(sessionBeanEClass, SESSION_BEAN__SECURITY_ROLE_REFS);
		createEReference(sessionBeanEClass, SESSION_BEAN__SECURITY_IDENTITIES);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__PASSIVATION_CAPABLE);
		createEAttribute(sessionBeanEClass, SESSION_BEAN__ID);

		statefulTimeoutTypeEClass = createEClass(STATEFUL_TIMEOUT_TYPE);
		createEAttribute(statefulTimeoutTypeEClass, STATEFUL_TIMEOUT_TYPE__TIMEOUT);
		createEAttribute(statefulTimeoutTypeEClass, STATEFUL_TIMEOUT_TYPE__UNIT);
		createEAttribute(statefulTimeoutTypeEClass, STATEFUL_TIMEOUT_TYPE__ID);

		timerScheduleTypeEClass = createEClass(TIMER_SCHEDULE_TYPE);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__SECOND);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__MINUTE);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__HOUR);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__DAY_OF_MONTH);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__MONTH);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__DAY_OF_WEEK);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__YEAR);
		createEAttribute(timerScheduleTypeEClass, TIMER_SCHEDULE_TYPE__ID);

		timerTypeEClass = createEClass(TIMER_TYPE);
		createEReference(timerTypeEClass, TIMER_TYPE__DESCRIPTION);
		createEReference(timerTypeEClass, TIMER_TYPE__SCHEDULE);
		createEAttribute(timerTypeEClass, TIMER_TYPE__START);
		createEAttribute(timerTypeEClass, TIMER_TYPE__END);
		createEReference(timerTypeEClass, TIMER_TYPE__TIMEOUT_METHOD);
		createEAttribute(timerTypeEClass, TIMER_TYPE__PERSISTENT);
		createEAttribute(timerTypeEClass, TIMER_TYPE__TIMEZONE);
		createEAttribute(timerTypeEClass, TIMER_TYPE__INFO);
		createEAttribute(timerTypeEClass, TIMER_TYPE__ID);

		// Create enums
		cmpVersionTypeEEnum = createEEnum(CMP_VERSION_TYPE);
		cmrFieldTypeEEnum = createEEnum(CMR_FIELD_TYPE);
		concurrencyManagementTypeTypeEEnum = createEEnum(CONCURRENCY_MANAGEMENT_TYPE_TYPE);
		concurrentLockTypeTypeEEnum = createEEnum(CONCURRENT_LOCK_TYPE_TYPE);
		methodInterfaceTypeEEnum = createEEnum(METHOD_INTERFACE_TYPE);
		multiplicityTypeEEnum = createEEnum(MULTIPLICITY_TYPE);
		persistenceTypeEEnum = createEEnum(PERSISTENCE_TYPE);
		resultTypeMappingTypeEEnum = createEEnum(RESULT_TYPE_MAPPING_TYPE);
		sessionTypeEEnum = createEEnum(SESSION_TYPE);
		timeUnitTypeTypeEEnum = createEEnum(TIME_UNIT_TYPE_TYPE);
		transactionAttributeTypeEEnum = createEEnum(TRANSACTION_ATTRIBUTE_TYPE);
		transactionTypeEEnum = createEEnum(TRANSACTION_TYPE);

		// Create data types
		cmpVersionTypeObjectEDataType = createEDataType(CMP_VERSION_TYPE_OBJECT);
		cmrFieldTypeObjectEDataType = createEDataType(CMR_FIELD_TYPE_OBJECT);
		concurrencyManagementTypeTypeObjectEDataType = createEDataType(CONCURRENCY_MANAGEMENT_TYPE_TYPE_OBJECT);
		concurrentLockTypeTypeObjectEDataType = createEDataType(CONCURRENT_LOCK_TYPE_TYPE_OBJECT);
		ejbClassTypeEDataType = createEDataType(EJB_CLASS_TYPE);
		ejbNameTypeEDataType = createEDataType(EJB_NAME_TYPE);
		methodInterfaceTypeObjectEDataType = createEDataType(METHOD_INTERFACE_TYPE_OBJECT);
		methodNameTypeEDataType = createEDataType(METHOD_NAME_TYPE);
		multiplicityTypeObjectEDataType = createEDataType(MULTIPLICITY_TYPE_OBJECT);
		persistenceTypeObjectEDataType = createEDataType(PERSISTENCE_TYPE_OBJECT);
		resultTypeMappingTypeObjectEDataType = createEDataType(RESULT_TYPE_MAPPING_TYPE_OBJECT);
		sessionTypeObjectEDataType = createEDataType(SESSION_TYPE_OBJECT);
		timeUnitTypeTypeObjectEDataType = createEDataType(TIME_UNIT_TYPE_TYPE_OBJECT);
		transactionAttributeTypeObjectEDataType = createEDataType(TRANSACTION_ATTRIBUTE_TYPE_OBJECT);
		transactionTypeObjectEDataType = createEDataType(TRANSACTION_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private volatile boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		JavaeePackage theJavaeePackage = (JavaeePackage)(EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) instanceof JavaeePackage ? EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI) : JavaeePackage.eINSTANCE);

		// Create type parameters

		// Set bounds for type parameters

		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}		
		
		try{
		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(accessTimeoutTypeEClass, AccessTimeoutType.class, "AccessTimeoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAccessTimeoutType_Timeout(), theXMLTypePackage.getInteger(), "timeout", null, 1, 1, AccessTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAccessTimeoutType_Unit(), this.getTimeUnitTypeType(), "unit", null, 1, 1, AccessTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAccessTimeoutType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AccessTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(activationConfigEClass, ActivationConfig.class, "ActivationConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getActivationConfig_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, ActivationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getActivationConfig_ActivationConfigProperties(), this.getActivationConfigProperty(), null, "activationConfigProperties", null, 1, -1, ActivationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getActivationConfig_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ActivationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(activationConfigPropertyEClass, ActivationConfigProperty.class, "ActivationConfigProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getActivationConfigProperty_ActivationConfigPropertyName(), theXMLTypePackage.getString(), "activationConfigPropertyName", null, 1, 1, ActivationConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getActivationConfigProperty_ActivationConfigPropertyValue(), theXMLTypePackage.getString(), "activationConfigPropertyValue", null, 1, 1, ActivationConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getActivationConfigProperty_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ActivationConfigProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(applicationExceptionEClass, ApplicationException.class, "ApplicationException", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getApplicationException_ExceptionClass(), theJavaeePackage.getFullyQualifiedClassType(), "exceptionClass", null, 1, 1, ApplicationException.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getApplicationException_Rollback(), theJavaeePackage.getTrueFalseType(), "rollback", null, 0, 1, ApplicationException.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getApplicationException_Inherited(), theJavaeePackage.getTrueFalseType(), "inherited", null, 0, 1, ApplicationException.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getApplicationException_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ApplicationException.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(aroundInvokeTypeEClass, AroundInvokeType.class, "AroundInvokeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAroundInvokeType_Class(), theJavaeePackage.getFullyQualifiedClassType(), "class", null, 0, 1, AroundInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAroundInvokeType_MethodName(), theJavaeePackage.getJavaIdentifier(), "methodName", null, 1, 1, AroundInvokeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(aroundTimeoutTypeEClass, AroundTimeoutType.class, "AroundTimeoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAroundTimeoutType_Class(), theJavaeePackage.getFullyQualifiedClassType(), "class", null, 0, 1, AroundTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAroundTimeoutType_MethodName(), theJavaeePackage.getJavaIdentifier(), "methodName", null, 1, 1, AroundTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(assemblyDescriptorEClass, AssemblyDescriptor.class, "AssemblyDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_SecurityRoles(), theJavaeePackage.getSecurityRole(), null, "securityRoles", null, 0, -1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_MethodPermissions(), this.getMethodPermission(), null, "methodPermissions", null, 0, -1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_ContainerTransactions(), this.getContainerTransactionType(), null, "containerTransactions", null, 0, -1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_InterceptorBindings(), this.getInterceptorBindingType(), null, "interceptorBindings", null, 0, -1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_MessageDestinations(), theJavaeePackage.getMessageDestination(), null, "messageDestinations", null, 0, -1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_ExcludeList(), this.getExcludeList(), null, "excludeList", null, 0, 1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAssemblyDescriptor_ApplicationExceptions(), this.getApplicationException(), null, "applicationExceptions", null, 0, -1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAssemblyDescriptor_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AssemblyDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(asyncMethodTypeEClass, AsyncMethodType.class, "AsyncMethodType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAsyncMethodType_MethodName(), theXMLTypePackage.getToken(), "methodName", null, 1, 1, AsyncMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAsyncMethodType_MethodParams(), this.getMethodParams(), null, "methodParams", null, 0, 1, AsyncMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAsyncMethodType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AsyncMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(cmpFieldEClass, CMPField.class, "CMPField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCMPField_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, CMPField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCMPField_FieldName(), theJavaeePackage.getJavaIdentifier(), "fieldName", null, 1, 1, CMPField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCMPField_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, CMPField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(cmrFieldEClass, CMRField.class, "CMRField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCMRField_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, CMRField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCMRField_CmrFieldName(), theXMLTypePackage.getToken(), "cmrFieldName", null, 1, 1, CMRField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCMRField_CmrFieldType(), this.getCMRFieldType(), "cmrFieldType", null, 0, 1, CMRField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCMRField_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, CMRField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(concurrentMethodTypeEClass, ConcurrentMethodType.class, "ConcurrentMethodType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConcurrentMethodType_Method(), this.getNamedMethodType(), null, "method", null, 1, 1, ConcurrentMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConcurrentMethodType_Lock(), this.getConcurrentLockTypeType(), "lock", null, 0, 1, ConcurrentMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConcurrentMethodType_AccessTimeout(), this.getAccessTimeoutType(), null, "accessTimeout", null, 0, 1, ConcurrentMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConcurrentMethodType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConcurrentMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(containerTransactionTypeEClass, ContainerTransactionType.class, "ContainerTransactionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getContainerTransactionType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, ContainerTransactionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getContainerTransactionType_Methods(), this.getMethodType(), null, "methods", null, 1, -1, ContainerTransactionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getContainerTransactionType_TransAttribute(), this.getTransactionAttributeType(), "transAttribute", null, 1, 1, ContainerTransactionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getContainerTransactionType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ContainerTransactionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(dependsOnTypeEClass, DependsOnType.class, "DependsOnType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDependsOnType_EjbName(), theJavaeePackage.getEJBLink(), "ejbName", null, 1, -1, DependsOnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDependsOnType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DependsOnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(ejbJarEClass, EJBJar.class, "EJBJar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEJBJar_ModuleName(), theXMLTypePackage.getToken(), "moduleName", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_EnterpriseBeans(), this.getEnterpriseBeans(), null, "enterpriseBeans", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_Interceptors(), this.getInterceptorsType(), null, "interceptors", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_Relationships(), this.getRelationships(), null, "relationships", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJar_AssemblyDescriptor(), this.getAssemblyDescriptor(), null, "assemblyDescriptor", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBJar_EjbClientJar(), theJavaeePackage.getPathType(), "ejbClientJar", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBJar_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBJar_MetadataComplete(), theXMLTypePackage.getBoolean(), "metadataComplete", null, 0, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBJar_Version(), theJavaeePackage.getDeweyVersionType(), "version", "3.2", 1, 1, EJBJar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(ejbJarDeploymentDescriptorEClass, EJBJarDeploymentDescriptor.class, "EJBJarDeploymentDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEJBJarDeploymentDescriptor_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJarDeploymentDescriptor_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJarDeploymentDescriptor_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBJarDeploymentDescriptor_EjbJar(), this.getEJBJar(), null, "ejbJar", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(ejbRelationEClass, EJBRelation.class, "EJBRelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getEJBRelation_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, EJBRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBRelation_EjbRelationName(), theXMLTypePackage.getToken(), "ejbRelationName", null, 0, 1, EJBRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBRelation_EjbRelationshipRoles(), this.getEJBRelationshipRole(), null, "ejbRelationshipRoles", null, 2, 2, EJBRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBRelation_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EJBRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(ejbRelationshipRoleEClass, EJBRelationshipRole.class, "EJBRelationshipRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getEJBRelationshipRole_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBRelationshipRole_EjbRelationshipRoleName(), theXMLTypePackage.getToken(), "ejbRelationshipRoleName", null, 0, 1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBRelationshipRole_Multiplicity(), this.getMultiplicityType(), "multiplicity", null, 1, 1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBRelationshipRole_CascadeDelete(), theJavaeePackage.getEmptyType(), null, "cascadeDelete", null, 0, 1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBRelationshipRole_RelationshipRoleSource(), this.getRelationshipRoleSourceType(), null, "relationshipRoleSource", null, 1, 1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEJBRelationshipRole_CmrField(), this.getCMRField(), null, "cmrField", null, 0, 1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEJBRelationshipRole_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EJBRelationshipRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(enterpriseBeansEClass, EnterpriseBeans.class, "EnterpriseBeans", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEnterpriseBeans_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, EnterpriseBeans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEnterpriseBeans_SessionBeans(), this.getSessionBean(), null, "sessionBeans", null, 0, -1, EnterpriseBeans.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEnterpriseBeans_EntityBeans(), this.getEntityBean(), null, "entityBeans", null, 0, -1, EnterpriseBeans.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEnterpriseBeans_MessageDrivenBeans(), this.getMessageDrivenBean(), null, "messageDrivenBeans", null, 0, -1, EnterpriseBeans.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEnterpriseBeans_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EnterpriseBeans.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(entityBeanEClass, EntityBean.class, "EntityBean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getEntityBean_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_EjbName(), this.getEjbNameType(), "ejbName", null, 1, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_Home(), theJavaeePackage.getHome(), "home", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_Remote(), theJavaeePackage.getRemote(), "remote", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_LocalHome(), theJavaeePackage.getLocalHome(), "localHome", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_Local(), theJavaeePackage.getLocal(), "local", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_EjbClass(), this.getEjbClassType(), "ejbClass", null, 1, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_PersistenceType(), this.getPersistenceType(), "persistenceType", null, 1, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_PrimKeyClass(), theJavaeePackage.getFullyQualifiedClassType(), "primKeyClass", null, 1, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_Reentrant(), theJavaeePackage.getTrueFalseType(), "reentrant", null, 1, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_CmpVersion(), this.getCmpVersionType(), "cmpVersion", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_AbstractSchemaName(), theJavaeePackage.getJavaIdentifier(), "abstractSchemaName", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_CmpFields(), this.getCMPField(), null, "cmpFields", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_PrimkeyField(), theXMLTypePackage.getToken(), "primkeyField", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_EnvEntries(), theJavaeePackage.getEnvEntry(), null, "envEntries", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_EjbRefs(), theJavaeePackage.getEjbRef(), null, "ejbRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_EjbLocalRefs(), theJavaeePackage.getEjbLocalRef(), null, "ejbLocalRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_ServiceRefs(), theJavaeePackage.getServiceRef(), null, "serviceRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_ResourceRefs(), theJavaeePackage.getResourceRef(), null, "resourceRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_ResourceEnvRefs(), theJavaeePackage.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_MessageDestinationRefs(), theJavaeePackage.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_PersistenceContextRefs(), theJavaeePackage.getPersistenceContextRef(), null, "persistenceContextRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_PersistenceUnitRefs(), theJavaeePackage.getPersistenceUnitRef(), null, "persistenceUnitRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_PostConstructs(), theJavaeePackage.getLifecycleCallback(), null, "postConstructs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_PreDestroys(), theJavaeePackage.getLifecycleCallback(), null, "preDestroys", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_DataSource(), theJavaeePackage.getDataSourceType(), null, "dataSource", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_JmsConnectionFactory(), theJavaeePackage.getJmsConnectionFactoryType(), null, "jmsConnectionFactory", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_JmsDestination(), theJavaeePackage.getJmsDestinationType(), null, "jmsDestination", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_MailSession(), theJavaeePackage.getMailSessionType(), null, "mailSession", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_ConnectionFactory(), theJavaeePackage.getConnectionFactoryResourceType(), null, "connectionFactory", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_AdministeredObject(), theJavaeePackage.getAdministeredObjectType(), null, "administeredObject", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_SecurityRoleRefs(), theJavaeePackage.getSecurityRoleRef(), null, "securityRoleRefs", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_SecurityIdentity(), this.getSecurityIdentityType(), null, "securityIdentity", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEntityBean_Queries(), this.getQuery(), null, "queries", null, 0, -1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEntityBean_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EntityBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(excludeListEClass, ExcludeList.class, "ExcludeList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getExcludeList_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, ExcludeList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getExcludeList_Methods(), this.getMethodType(), null, "methods", null, 1, -1, ExcludeList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getExcludeList_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ExcludeList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(initMethodTypeEClass, InitMethodType.class, "InitMethodType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInitMethodType_CreateMethod(), this.getNamedMethodType(), null, "createMethod", null, 1, 1, InitMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInitMethodType_BeanMethod(), this.getNamedMethodType(), null, "beanMethod", null, 1, 1, InitMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInitMethodType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, InitMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(interceptorBindingTypeEClass, InterceptorBindingType.class, "InterceptorBindingType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInterceptorBindingType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorBindingType_EjbName(), theXMLTypePackage.getToken(), "ejbName", null, 1, 1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorBindingType_InterceptorClasses(), theJavaeePackage.getFullyQualifiedClassType(), "interceptorClasses", null, 0, -1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorBindingType_InterceptorOrder(), this.getInterceptorOrderType(), null, "interceptorOrder", null, 0, 1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorBindingType_ExcludeDefaultInterceptors(), theJavaeePackage.getTrueFalseType(), "excludeDefaultInterceptors", null, 0, 1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorBindingType_ExcludeClassInterceptors(), theJavaeePackage.getTrueFalseType(), "excludeClassInterceptors", null, 0, 1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorBindingType_Method(), this.getNamedMethodType(), null, "method", null, 0, 1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorBindingType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, InterceptorBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(interceptorOrderTypeEClass, InterceptorOrderType.class, "InterceptorOrderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getInterceptorOrderType_InterceptorClasses(), theJavaeePackage.getFullyQualifiedClassType(), "interceptorClasses", null, 1, -1, InterceptorOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorOrderType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, InterceptorOrderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(interceptorsTypeEClass, InterceptorsType.class, "InterceptorsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInterceptorsType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, InterceptorsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorsType_Interceptors(), this.getInterceptorType(), null, "interceptors", null, 1, -1, InterceptorsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorsType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, InterceptorsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(interceptorTypeEClass, InterceptorType.class, "InterceptorType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInterceptorType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorType_InterceptorClass(), theJavaeePackage.getFullyQualifiedClassType(), "interceptorClass", null, 1, 1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_AroundInvokes(), this.getAroundInvokeType(), null, "aroundInvokes", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_AroundTimeouts(), this.getAroundTimeoutType(), null, "aroundTimeouts", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_AroundConstruct(), theJavaeePackage.getLifecycleCallback(), null, "aroundConstruct", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_EnvEntries(), theJavaeePackage.getEnvEntry(), null, "envEntries", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_EjbRefs(), theJavaeePackage.getEjbRef(), null, "ejbRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_EjbLocalRefs(), theJavaeePackage.getEjbLocalRef(), null, "ejbLocalRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_ServiceRefs(), theJavaeePackage.getServiceRef(), null, "serviceRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_ResourceRefs(), theJavaeePackage.getResourceRef(), null, "resourceRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_ResourceEnvRefs(), theJavaeePackage.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_MessageDestinationRefs(), theJavaeePackage.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_PersistenceContextRefs(), theJavaeePackage.getPersistenceContextRef(), null, "persistenceContextRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_PersistenceUnitRefs(), theJavaeePackage.getPersistenceUnitRef(), null, "persistenceUnitRefs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_PostConstructs(), theJavaeePackage.getLifecycleCallback(), null, "postConstructs", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_PreDestroys(), theJavaeePackage.getLifecycleCallback(), null, "preDestroys", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_DataSource(), theJavaeePackage.getDataSourceType(), null, "dataSource", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_JmsConnectionFactory(), theJavaeePackage.getJmsConnectionFactoryType(), null, "jmsConnectionFactory", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_JmsDestination(), theJavaeePackage.getJmsDestinationType(), null, "jmsDestination", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_MailSession(), theJavaeePackage.getMailSessionType(), null, "mailSession", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_ConnectionFactory(), theJavaeePackage.getConnectionFactoryResourceType(), null, "connectionFactory", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_AdministeredObject(), theJavaeePackage.getAdministeredObjectType(), null, "administeredObject", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_PostActivates(), theJavaeePackage.getLifecycleCallback(), null, "postActivates", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptorType_PrePassivates(), theJavaeePackage.getLifecycleCallback(), null, "prePassivates", null, 0, -1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInterceptorType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, InterceptorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(messageDrivenBeanEClass, MessageDrivenBean.class, "MessageDrivenBean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_EjbName(), this.getEjbNameType(), "ejbName", null, 1, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_EjbClass(), this.getEjbClassType(), "ejbClass", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_MessagingType(), theJavaeePackage.getFullyQualifiedClassType(), "messagingType", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_TimeoutMethod(), this.getNamedMethodType(), null, "timeoutMethod", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_Timer(), this.getTimerType(), null, "timer", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_TransactionType(), this.getTransactionType(), "transactionType", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_MessageDestinationType(), theJavaeePackage.getMessageDestinationTypeType(), "messageDestinationType", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_MessageDestinationLink(), theJavaeePackage.getMessageDestinationLink(), "messageDestinationLink", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_ActivationConfig(), this.getActivationConfig(), null, "activationConfig", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_AroundInvokes(), this.getAroundInvokeType(), null, "aroundInvokes", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_AroundTimeouts(), this.getAroundTimeoutType(), null, "aroundTimeouts", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_EnvEntries(), theJavaeePackage.getEnvEntry(), null, "envEntries", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_EjbRefs(), theJavaeePackage.getEjbRef(), null, "ejbRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_EjbLocalRefs(), theJavaeePackage.getEjbLocalRef(), null, "ejbLocalRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_ServiceRefs(), theJavaeePackage.getServiceRef(), null, "serviceRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_ResourceRefs(), theJavaeePackage.getResourceRef(), null, "resourceRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_ResourceEnvRefs(), theJavaeePackage.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_MessageDestinationRefs(), theJavaeePackage.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_PersistenceContextRefs(), theJavaeePackage.getPersistenceContextRef(), null, "persistenceContextRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_PersistenceUnitRefs(), theJavaeePackage.getPersistenceUnitRef(), null, "persistenceUnitRefs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_PostConstructs(), theJavaeePackage.getLifecycleCallback(), null, "postConstructs", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_PreDestroys(), theJavaeePackage.getLifecycleCallback(), null, "preDestroys", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_DataSource(), theJavaeePackage.getDataSourceType(), null, "dataSource", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_JmsConnectionFactory(), theJavaeePackage.getJmsConnectionFactoryType(), null, "jmsConnectionFactory", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_JmsDestination(), theJavaeePackage.getJmsDestinationType(), null, "jmsDestination", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_MailSession(), theJavaeePackage.getMailSessionType(), null, "mailSession", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_ConnectionFactory(), theJavaeePackage.getConnectionFactoryResourceType(), null, "connectionFactory", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_AdministeredObject(), theJavaeePackage.getAdministeredObjectType(), null, "administeredObject", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_SecurityRoleRef(), theJavaeePackage.getSecurityRoleRef(), null, "securityRoleRef", null, 0, -1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMessageDrivenBean_SecurityIdentity(), this.getSecurityIdentityType(), null, "securityIdentity", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMessageDrivenBean_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MessageDrivenBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(methodParamsEClass, MethodParams.class, "MethodParams", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMethodParams_MethodParams(), theJavaeePackage.getJavaType(), "methodParams", null, 0, -1, MethodParams.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodParams_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MethodParams.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(methodPermissionEClass, MethodPermission.class, "MethodPermission", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMethodPermission_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, MethodPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodPermission_RoleNames(), theJavaeePackage.getRoleName(), "roleNames", null, 0, -1, MethodPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMethodPermission_Unchecked(), theJavaeePackage.getEmptyType(), null, "unchecked", null, 0, 1, MethodPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMethodPermission_Methods(), this.getMethodType(), null, "methods", null, 1, -1, MethodPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodPermission_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MethodPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(methodTypeEClass, MethodType.class, "MethodType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getMethodType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, MethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodType_EjbName(), this.getEjbNameType(), "ejbName", null, 1, 1, MethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodType_MethodIntf(), this.getMethodInterfaceType(), "methodIntf", null, 0, 1, MethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodType_MethodName(), this.getMethodNameType(), "methodName", null, 1, 1, MethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getMethodType_MethodParams(), this.getMethodParams(), null, "methodParams", null, 0, 1, MethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getMethodType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(namedMethodTypeEClass, NamedMethodType.class, "NamedMethodType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNamedMethodType_MethodName(), theXMLTypePackage.getToken(), "methodName", null, 1, 1, NamedMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getNamedMethodType_MethodParams(), this.getMethodParams(), null, "methodParams", null, 0, 1, NamedMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getNamedMethodType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, NamedMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getQuery_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getQuery_QueryMethod(), this.getQueryMethod(), null, "queryMethod", null, 1, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getQuery_ResultTypeMapping(), this.getResultTypeMappingType(), "resultTypeMapping", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getQuery_EjbQl(), theXMLTypePackage.getString(), "ejbQl", null, 1, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getQuery_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(queryMethodEClass, QueryMethod.class, "QueryMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getQueryMethod_MethodName(), this.getMethodNameType(), "methodName", null, 1, 1, QueryMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getQueryMethod_MethodParams(), this.getMethodParams(), null, "methodParams", null, 1, 1, QueryMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getQueryMethod_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, QueryMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(relationshipRoleSourceTypeEClass, RelationshipRoleSourceType.class, "RelationshipRoleSourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRelationshipRoleSourceType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, RelationshipRoleSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRelationshipRoleSourceType_EjbName(), this.getEjbNameType(), "ejbName", null, 1, 1, RelationshipRoleSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRelationshipRoleSourceType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RelationshipRoleSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(relationshipsEClass, Relationships.class, "Relationships", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRelationships_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, Relationships.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRelationships_EjbRelations(), this.getEJBRelation(), null, "ejbRelations", null, 1, -1, Relationships.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRelationships_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Relationships.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(removeMethodTypeEClass, RemoveMethodType.class, "RemoveMethodType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRemoveMethodType_BeanMethod(), this.getNamedMethodType(), null, "beanMethod", null, 1, 1, RemoveMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRemoveMethodType_RetainIfException(), theJavaeePackage.getTrueFalseType(), "retainIfException", null, 0, 1, RemoveMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRemoveMethodType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RemoveMethodType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(securityIdentityTypeEClass, SecurityIdentityType.class, "SecurityIdentityType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSecurityIdentityType_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, SecurityIdentityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSecurityIdentityType_UseCallerIdentity(), theJavaeePackage.getEmptyType(), null, "useCallerIdentity", null, 0, 1, SecurityIdentityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSecurityIdentityType_RunAs(), theJavaeePackage.getRunAs(), null, "runAs", null, 0, 1, SecurityIdentityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSecurityIdentityType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SecurityIdentityType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sessionBeanEClass, SessionBean.class, "SessionBean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSessionBean_Descriptions(), theJavaeePackage.getDescription(), null, "descriptions", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_DisplayNames(), theJavaeePackage.getDisplayName(), null, "displayNames", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_Icons(), theJavaeePackage.getIcon(), null, "icons", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_EjbName(), this.getEjbNameType(), "ejbName", null, 1, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_Home(), theJavaeePackage.getHome(), "home", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_Remote(), theJavaeePackage.getRemote(), "remote", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_LocalHome(), theJavaeePackage.getLocalHome(), "localHome", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_Local(), theJavaeePackage.getLocal(), "local", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_BusinessLocals(), theJavaeePackage.getFullyQualifiedClassType(), "businessLocals", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_BusinessRemotes(), theJavaeePackage.getFullyQualifiedClassType(), "businessRemotes", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_LocalBean(), theJavaeePackage.getEmptyType(), null, "localBean", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_ServiceEndpoint(), theJavaeePackage.getFullyQualifiedClassType(), "serviceEndpoint", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_EjbClass(), this.getEjbClassType(), "ejbClass", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_SessionType(), this.getSessionType(), "sessionType", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_StatefulTimeout(), this.getStatefulTimeoutType(), null, "statefulTimeout", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_TimeoutMethod(), this.getNamedMethodType(), null, "timeoutMethod", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_Timer(), this.getTimerType(), null, "timer", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_InitOnStartup(), theJavaeePackage.getTrueFalseType(), "initOnStartup", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_ConcurrencyManagementType(), this.getConcurrencyManagementTypeType(), "concurrencyManagementType", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_ConcurrentMethod(), this.getConcurrentMethodType(), null, "concurrentMethod", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_DependsOn(), this.getDependsOnType(), null, "dependsOn", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_InitMethods(), this.getInitMethodType(), null, "initMethods", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_RemoveMethods(), this.getRemoveMethodType(), null, "removeMethods", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_AsyncMethod(), this.getAsyncMethodType(), null, "asyncMethod", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_TransactionType(), this.getTransactionType(), "transactionType", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_AfterBeginMethod(), this.getNamedMethodType(), null, "afterBeginMethod", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_BeforeCompletionMethod(), this.getNamedMethodType(), null, "beforeCompletionMethod", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_AfterCompletionMethod(), this.getNamedMethodType(), null, "afterCompletionMethod", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_AroundInvokes(), this.getAroundInvokeType(), null, "aroundInvokes", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_AroundTimeouts(), this.getAroundTimeoutType(), null, "aroundTimeouts", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_EnvEntries(), theJavaeePackage.getEnvEntry(), null, "envEntries", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_EjbRefs(), theJavaeePackage.getEjbRef(), null, "ejbRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_EjbLocalRefs(), theJavaeePackage.getEjbLocalRef(), null, "ejbLocalRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_ServiceRefs(), theJavaeePackage.getServiceRef(), null, "serviceRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_ResourceRefs(), theJavaeePackage.getResourceRef(), null, "resourceRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_ResourceEnvRefs(), theJavaeePackage.getResourceEnvRef(), null, "resourceEnvRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_MessageDestinationRefs(), theJavaeePackage.getMessageDestinationRef(), null, "messageDestinationRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_PersistenceContextRefs(), theJavaeePackage.getPersistenceContextRef(), null, "persistenceContextRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_PersistenceUnitRefs(), theJavaeePackage.getPersistenceUnitRef(), null, "persistenceUnitRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_PostConstructs(), theJavaeePackage.getLifecycleCallback(), null, "postConstructs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_PreDestroys(), theJavaeePackage.getLifecycleCallback(), null, "preDestroys", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_DataSource(), theJavaeePackage.getDataSourceType(), null, "dataSource", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_JmsConnectionFactory(), theJavaeePackage.getJmsConnectionFactoryType(), null, "jmsConnectionFactory", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_JmsDestination(), theJavaeePackage.getJmsDestinationType(), null, "jmsDestination", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_MailSession(), theJavaeePackage.getMailSessionType(), null, "mailSession", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_ConnectionFactory(), theJavaeePackage.getConnectionFactoryResourceType(), null, "connectionFactory", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_AdministeredObject(), theJavaeePackage.getAdministeredObjectType(), null, "administeredObject", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_PostActivates(), theJavaeePackage.getLifecycleCallback(), null, "postActivates", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_PrePassivates(), theJavaeePackage.getLifecycleCallback(), null, "prePassivates", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_SecurityRoleRefs(), theJavaeePackage.getSecurityRoleRef(), null, "securityRoleRefs", null, 0, -1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSessionBean_SecurityIdentities(), this.getSecurityIdentityType(), null, "securityIdentities", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSessionBean_PassivationCapable(), theXMLTypePackage.getBoolean(), "passivationCapable", "true", 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSessionBean_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SessionBean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(statefulTimeoutTypeEClass, StatefulTimeoutType.class, "StatefulTimeoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStatefulTimeoutType_Timeout(), theXMLTypePackage.getInteger(), "timeout", null, 1, 1, StatefulTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStatefulTimeoutType_Unit(), this.getTimeUnitTypeType(), "unit", null, 1, 1, StatefulTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStatefulTimeoutType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, StatefulTimeoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(timerScheduleTypeEClass, TimerScheduleType.class, "TimerScheduleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_Second(), theXMLTypePackage.getToken(), "second", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_Minute(), theXMLTypePackage.getToken(), "minute", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_Hour(), theXMLTypePackage.getToken(), "hour", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_DayOfMonth(), theXMLTypePackage.getToken(), "dayOfMonth", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_Month(), theXMLTypePackage.getToken(), "month", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_DayOfWeek(), theXMLTypePackage.getToken(), "dayOfWeek", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_Year(), theXMLTypePackage.getToken(), "year", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerScheduleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, TimerScheduleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(timerTypeEClass, TimerType.class, "TimerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTimerType_Description(), theJavaeePackage.getDescription(), null, "description", null, 0, -1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTimerType_Schedule(), this.getTimerScheduleType(), null, "schedule", null, 1, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerType_Start(), theXMLTypePackage.getDateTime(), "start", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerType_End(), theXMLTypePackage.getDateTime(), "end", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTimerType_TimeoutMethod(), this.getNamedMethodType(), null, "timeoutMethod", null, 1, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerType_Persistent(), theJavaeePackage.getTrueFalseType(), "persistent", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerType_Timezone(), theXMLTypePackage.getToken(), "timezone", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerType_Info(), theXMLTypePackage.getToken(), "info", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimerType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, TimerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(cmpVersionTypeEEnum, CmpVersionType.class, "CmpVersionType"); //$NON-NLS-1$
		addEEnumLiteral(cmpVersionTypeEEnum, CmpVersionType._1X_LITERAL);
		addEEnumLiteral(cmpVersionTypeEEnum, CmpVersionType._2X_LITERAL);

		initEEnum(cmrFieldTypeEEnum, CMRFieldType.class, "CMRFieldType"); //$NON-NLS-1$
		addEEnumLiteral(cmrFieldTypeEEnum, CMRFieldType.JAVA_UTIL_COLLECTION_LITERAL);
		addEEnumLiteral(cmrFieldTypeEEnum, CMRFieldType.JAVA_UTIL_SET_LITERAL);

		initEEnum(concurrencyManagementTypeTypeEEnum, ConcurrencyManagementTypeType.class, "ConcurrencyManagementTypeType"); //$NON-NLS-1$
		addEEnumLiteral(concurrencyManagementTypeTypeEEnum, ConcurrencyManagementTypeType.BEAN);
		addEEnumLiteral(concurrencyManagementTypeTypeEEnum, ConcurrencyManagementTypeType.CONTAINER);

		initEEnum(concurrentLockTypeTypeEEnum, ConcurrentLockTypeType.class, "ConcurrentLockTypeType"); //$NON-NLS-1$
		addEEnumLiteral(concurrentLockTypeTypeEEnum, ConcurrentLockTypeType.READ);
		addEEnumLiteral(concurrentLockTypeTypeEEnum, ConcurrentLockTypeType.WRITE);

		initEEnum(methodInterfaceTypeEEnum, MethodInterfaceType.class, "MethodInterfaceType"); //$NON-NLS-1$
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.HOME_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.REMOTE_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.LOCAL_HOME_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.LOCAL_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.SERVICE_ENDPOINT_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.TIMER_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.MESSAGE_ENDPOINT_LITERAL);
		addEEnumLiteral(methodInterfaceTypeEEnum, MethodInterfaceType.LIFECYCLE_CALLBACK);

		initEEnum(multiplicityTypeEEnum, MultiplicityType.class, "MultiplicityType"); //$NON-NLS-1$
		addEEnumLiteral(multiplicityTypeEEnum, MultiplicityType.ONE_LITERAL);
		addEEnumLiteral(multiplicityTypeEEnum, MultiplicityType.MANY_LITERAL);

		initEEnum(persistenceTypeEEnum, PersistenceType.class, "PersistenceType"); //$NON-NLS-1$
		addEEnumLiteral(persistenceTypeEEnum, PersistenceType.BEAN_LITERAL);
		addEEnumLiteral(persistenceTypeEEnum, PersistenceType.CONTAINER_LITERAL);

		initEEnum(resultTypeMappingTypeEEnum, ResultTypeMappingType.class, "ResultTypeMappingType"); //$NON-NLS-1$
		addEEnumLiteral(resultTypeMappingTypeEEnum, ResultTypeMappingType.LOCAL_LITERAL);
		addEEnumLiteral(resultTypeMappingTypeEEnum, ResultTypeMappingType.REMOTE_LITERAL);

		initEEnum(sessionTypeEEnum, SessionType.class, "SessionType"); //$NON-NLS-1$
		addEEnumLiteral(sessionTypeEEnum, SessionType.SINGLETON_LITERAL);
		addEEnumLiteral(sessionTypeEEnum, SessionType.STATEFUL_LITERAL);
		addEEnumLiteral(sessionTypeEEnum, SessionType.STATELESS_LITERAL);

		initEEnum(timeUnitTypeTypeEEnum, TimeUnitTypeType.class, "TimeUnitTypeType"); //$NON-NLS-1$
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.DAYS);
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.HOURS);
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.MINUTES);
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.SECONDS);
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.MILLISECONDS);
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.MICROSECONDS);
		addEEnumLiteral(timeUnitTypeTypeEEnum, TimeUnitTypeType.NANOSECONDS);

		initEEnum(transactionAttributeTypeEEnum, TransactionAttributeType.class, "TransactionAttributeType"); //$NON-NLS-1$
		addEEnumLiteral(transactionAttributeTypeEEnum, TransactionAttributeType.NOT_SUPPORTED_LITERAL);
		addEEnumLiteral(transactionAttributeTypeEEnum, TransactionAttributeType.SUPPORTS_LITERAL);
		addEEnumLiteral(transactionAttributeTypeEEnum, TransactionAttributeType.REQUIRED_LITERAL);
		addEEnumLiteral(transactionAttributeTypeEEnum, TransactionAttributeType.REQUIRES_NEW_LITERAL);
		addEEnumLiteral(transactionAttributeTypeEEnum, TransactionAttributeType.MANDATORY_LITERAL);
		addEEnumLiteral(transactionAttributeTypeEEnum, TransactionAttributeType.NEVER_LITERAL);

		initEEnum(transactionTypeEEnum, TransactionType.class, "TransactionType"); //$NON-NLS-1$
		addEEnumLiteral(transactionTypeEEnum, TransactionType.BEAN_LITERAL);
		addEEnumLiteral(transactionTypeEEnum, TransactionType.CONTAINER_LITERAL);

		// Initialize data types
		initEDataType(cmpVersionTypeObjectEDataType, CmpVersionType.class, "CmpVersionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(cmrFieldTypeObjectEDataType, CMRFieldType.class, "CMRFieldTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(concurrencyManagementTypeTypeObjectEDataType, ConcurrencyManagementTypeType.class, "ConcurrencyManagementTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(concurrentLockTypeTypeObjectEDataType, ConcurrentLockTypeType.class, "ConcurrentLockTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(ejbClassTypeEDataType, String.class, "EjbClassType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(ejbNameTypeEDataType, String.class, "EjbNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(methodInterfaceTypeObjectEDataType, MethodInterfaceType.class, "MethodInterfaceTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(methodNameTypeEDataType, String.class, "MethodNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(multiplicityTypeObjectEDataType, MultiplicityType.class, "MultiplicityTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(persistenceTypeObjectEDataType, PersistenceType.class, "PersistenceTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(resultTypeMappingTypeObjectEDataType, ResultTypeMappingType.class, "ResultTypeMappingTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(sessionTypeObjectEDataType, SessionType.class, "SessionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(timeUnitTypeTypeObjectEDataType, TimeUnitTypeType.class, "TimeUnitTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(transactionAttributeTypeObjectEDataType, TransactionAttributeType.class, "TransactionAttributeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(transactionTypeObjectEDataType, TransactionType.class, "TransactionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
		}finally{
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$				
		addAnnotation
		  (accessTimeoutTypeEClass, 
		   source, 
		   new String[] {
			 "name", "access-timeoutType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAccessTimeoutType_Timeout(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAccessTimeoutType_Unit(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "unit", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAccessTimeoutType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (activationConfigEClass, 
		   source, 
		   new String[] {
			 "name", "activation-configType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getActivationConfig_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getActivationConfig_ActivationConfigProperties(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "activation-config-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getActivationConfig_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (activationConfigPropertyEClass, 
		   source, 
		   new String[] {
			 "name", "activation-config-propertyType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getActivationConfigProperty_ActivationConfigPropertyName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "activation-config-property-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getActivationConfigProperty_ActivationConfigPropertyValue(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "activation-config-property-value", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getActivationConfigProperty_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (applicationExceptionEClass, 
		   source, 
		   new String[] {
			 "name", "application-exceptionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getApplicationException_ExceptionClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "exception-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getApplicationException_Rollback(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "rollback", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getApplicationException_Inherited(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "inherited", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getApplicationException_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (aroundInvokeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "around-invokeType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAroundInvokeType_Class(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAroundInvokeType_MethodName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (aroundTimeoutTypeEClass, 
		   source, 
		   new String[] {
			 "name", "around-timeoutType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAroundTimeoutType_Class(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAroundTimeoutType_MethodName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (assemblyDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "assembly-descriptorType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_SecurityRoles(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-role", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_MethodPermissions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-permission", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_ContainerTransactions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "container-transaction", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_InterceptorBindings(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptor-binding", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_MessageDestinations(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_ExcludeList(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "exclude-list", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_ApplicationExceptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "application-exception", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAssemblyDescriptor_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (asyncMethodTypeEClass, 
		   source, 
		   new String[] {
			 "name", "async-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAsyncMethodType_MethodName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAsyncMethodType_MethodParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-params", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAsyncMethodType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (cmpFieldEClass, 
		   source, 
		   new String[] {
			 "name", "cmp-fieldType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getCMPField_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getCMPField_FieldName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "field-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getCMPField_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (cmpVersionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "cmp-versionType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (cmpVersionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "cmp-versionType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "cmp-versionType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (cmrFieldEClass, 
		   source, 
		   new String[] {
			 "name", "cmr-fieldType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getCMRField_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getCMRField_CmrFieldName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "cmr-field-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getCMRField_CmrFieldType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "cmr-field-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getCMRField_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (cmrFieldTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "cmr-field-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (cmrFieldTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "cmr-field-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "cmr-field-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (concurrencyManagementTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "concurrency-management-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (concurrencyManagementTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "concurrency-management-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "concurrency-management-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (concurrentLockTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "concurrent-lock-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (concurrentLockTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "concurrent-lock-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "concurrent-lock-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (concurrentMethodTypeEClass, 
		   source, 
		   new String[] {
			 "name", "concurrent-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConcurrentMethodType_Method(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConcurrentMethodType_Lock(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lock", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConcurrentMethodType_AccessTimeout(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "access-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getConcurrentMethodType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (containerTransactionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "container-transactionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getContainerTransactionType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getContainerTransactionType_Methods(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getContainerTransactionType_TransAttribute(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "trans-attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getContainerTransactionType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (dependsOnTypeEClass, 
		   source, 
		   new String[] {
			 "name", "depends-onType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getDependsOnType_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getDependsOnType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbClassTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "ejb-classType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://xmlns.jcp.org/xml/ns/javaee#fully-qualified-classType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbJarEClass, 
		   source, 
		   new String[] {
			 "name", "ejb-jarType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBJar_ModuleName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "module-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_EnterpriseBeans(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "enterprise-beans", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_Interceptors(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptors", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_Relationships(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "relationships", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBJar_AssemblyDescriptor(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "assembly-descriptor", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBJar_EjbClientJar(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-client-jar", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJar_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBJar_MetadataComplete(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "metadata-complete" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBJar_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "version" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (ejbJarDeploymentDescriptorEClass, 
		   source, 
		   new String[] {
			 "name", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJarDeploymentDescriptor_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJarDeploymentDescriptor_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBJarDeploymentDescriptor_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBJarDeploymentDescriptor_EjbJar(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-jar", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "ejb-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#NMTOKEN" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbRelationEClass, 
		   source, 
		   new String[] {
			 "name", "ejb-relationType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelation_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBRelation_EjbRelationName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-relation-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelation_EjbRelationshipRoles(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-relationship-role", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelation_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbRelationshipRoleEClass, 
		   source, 
		   new String[] {
			 "name", "ejb-relationship-roleType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelationshipRole_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBRelationshipRole_EjbRelationshipRoleName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-relationship-role-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelationshipRole_Multiplicity(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "multiplicity", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEJBRelationshipRole_CascadeDelete(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "cascade-delete", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelationshipRole_RelationshipRoleSource(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "relationship-role-source", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelationshipRole_CmrField(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "cmr-field", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEJBRelationshipRole_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (enterpriseBeansEClass, 
		   source, 
		   new String[] {
			 "name", "enterprise-beansType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnterpriseBeans_Group(), 
		   source, 
		   new String[] {
			 "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnterpriseBeans_SessionBeans(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "session", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnterpriseBeans_EntityBeans(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "entity", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnterpriseBeans_MessageDrivenBeans(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-driven", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
			 "group", "#group:0" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnterpriseBeans_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (entityBeanEClass, 
		   source, 
		   new String[] {
			 "name", "entity-beanType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Home(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "home", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Remote(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "remote", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_LocalHome(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local-home", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Local(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_EjbClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_PersistenceType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_PrimKeyClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "prim-key-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_Reentrant(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "reentrant", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_CmpVersion(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "cmp-version", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_AbstractSchemaName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "abstract-schema-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_CmpFields(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "cmp-field", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_PrimkeyField(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "primkey-field", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_EnvEntries(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_EjbRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_EjbLocalRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-local-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_ServiceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_ResourceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_ResourceEnvRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_MessageDestinationRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_PersistenceContextRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_PersistenceUnitRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_PostConstructs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-construct", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_PreDestroys(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-destroy", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_DataSource(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "data-source", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_JmsConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_JmsDestination(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-destination", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_MailSession(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mail-session", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_ConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEntityBean_AdministeredObject(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "administered-object", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_SecurityRoleRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-role-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_SecurityIdentity(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-identity", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Queries(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "query", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEntityBean_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (excludeListEClass, 
		   source, 
		   new String[] {
			 "name", "exclude-listType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getExcludeList_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getExcludeList_Methods(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getExcludeList_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (initMethodTypeEClass, 
		   source, 
		   new String[] {
			 "name", "init-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInitMethodType_CreateMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "create-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInitMethodType_BeanMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "bean-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInitMethodType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (interceptorBindingTypeEClass, 
		   source, 
		   new String[] {
			 "name", "interceptor-bindingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_InterceptorClasses(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptor-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_InterceptorOrder(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptor-order", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_ExcludeDefaultInterceptors(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "exclude-default-interceptors", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_ExcludeClassInterceptors(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "exclude-class-interceptors", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_Method(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorBindingType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (interceptorOrderTypeEClass, 
		   source, 
		   new String[] {
			 "name", "interceptor-orderType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorOrderType_InterceptorClasses(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptor-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorOrderType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (interceptorsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "interceptorsType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorsType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorsType_Interceptors(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptor", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorsType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (interceptorTypeEClass, 
		   source, 
		   new String[] {
			 "name", "interceptorType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_InterceptorClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "interceptor-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_AroundInvokes(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-invoke", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_AroundTimeouts(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_AroundConstruct(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-construct", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_EnvEntries(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_EjbRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_EjbLocalRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-local-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_ServiceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_ResourceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_ResourceEnvRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_MessageDestinationRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_PersistenceContextRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_PersistenceUnitRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_PostConstructs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-construct", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_PreDestroys(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-destroy", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_DataSource(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "data-source", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getInterceptorType_JmsConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getInterceptorType_JmsDestination(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-destination", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getInterceptorType_MailSession(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mail-session", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getInterceptorType_ConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getInterceptorType_AdministeredObject(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "administered-object", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_PostActivates(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-activate", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_PrePassivates(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-passivate", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInterceptorType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageDrivenBeanEClass, 
		   source, 
		   new String[] {
			 "name", "message-driven-beanType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_EjbClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_MessagingType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "messaging-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_TimeoutMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timeout-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timer", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_TransactionType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "transaction-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_MessageDestinationType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_MessageDestinationLink(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-link", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_ActivationConfig(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "activation-config", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_AroundInvokes(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-invoke", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_AroundTimeouts(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_EnvEntries(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_EjbRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_EjbLocalRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-local-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_ServiceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_ResourceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_ResourceEnvRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_MessageDestinationRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_PersistenceContextRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_PersistenceUnitRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_PostConstructs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-construct", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_PreDestroys(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-destroy", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_DataSource(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "data-source", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_JmsConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_JmsDestination(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-destination", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_MailSession(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mail-session", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_ConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_AdministeredObject(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "administered-object", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDrivenBean_SecurityRoleRef(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-role-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_SecurityIdentity(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-identity", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDrivenBean_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (methodInterfaceTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "method-intfType" //$NON-NLS-1$ //$NON-NLS-2$
		   });					
		addAnnotation
		  (methodInterfaceTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "method-intfType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "method-intfType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (methodNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "method-nameType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (methodParamsEClass, 
		   source, 
		   new String[] {
			 "name", "method-paramsType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMethodParams_MethodParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-param", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodParams_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (methodPermissionEClass, 
		   source, 
		   new String[] {
			 "name", "method-permissionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodPermission_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodPermission_RoleNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "role-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMethodPermission_Unchecked(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "unchecked", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodPermission_Methods(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodPermission_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (methodTypeEClass, 
		   source, 
		   new String[] {
			 "name", "methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodType_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodType_MethodIntf(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-intf", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodType_MethodName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodType_MethodParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-params", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMethodType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (multiplicityTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "multiplicityType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (multiplicityTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "multiplicityType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "multiplicityType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (namedMethodTypeEClass, 
		   source, 
		   new String[] {
			 "name", "named-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getNamedMethodType_MethodName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getNamedMethodType_MethodParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-params", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getNamedMethodType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (persistenceTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "persistence-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (persistenceTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "persistence-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "persistence-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (queryEClass, 
		   source, 
		   new String[] {
			 "name", "queryType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQuery_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQuery_QueryMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "query-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQuery_ResultTypeMapping(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "result-type-mapping", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQuery_EjbQl(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ql", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQuery_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (queryMethodEClass, 
		   source, 
		   new String[] {
			 "name", "query-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQueryMethod_MethodName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQueryMethod_MethodParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "method-params", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getQueryMethod_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (relationshipRoleSourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "relationship-role-sourceType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRelationshipRoleSourceType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRelationshipRoleSourceType_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRelationshipRoleSourceType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (relationshipsEClass, 
		   source, 
		   new String[] {
			 "name", "relationshipsType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRelationships_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRelationships_EjbRelations(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-relation", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRelationships_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (removeMethodTypeEClass, 
		   source, 
		   new String[] {
			 "name", "remove-methodType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRemoveMethodType_BeanMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "bean-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRemoveMethodType_RetainIfException(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "retain-if-exception", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRemoveMethodType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (resultTypeMappingTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "result-type-mappingType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (resultTypeMappingTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "result-type-mappingType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "result-type-mappingType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (securityIdentityTypeEClass, 
		   source, 
		   new String[] {
			 "name", "security-identityType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityIdentityType_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSecurityIdentityType_UseCallerIdentity(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "use-caller-identity", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityIdentityType_RunAs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "run-as", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityIdentityType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (sessionBeanEClass, 
		   source, 
		   new String[] {
			 "name", "session-beanType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_EjbName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Home(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "home", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Remote(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "remote", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_LocalHome(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local-home", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Local(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_BusinessLocals(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "business-local", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_BusinessRemotes(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "business-remote", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_LocalBean(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local-bean", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_ServiceEndpoint(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-endpoint", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_EjbClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_SessionType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "session-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_StatefulTimeout(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "stateful-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_TimeoutMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timeout-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Timer(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timer", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_InitOnStartup(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "init-on-startup", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_ConcurrencyManagementType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "concurrency-management-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_ConcurrentMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "concurrent-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_DependsOn(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "depends-on", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_InitMethods(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "init-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_RemoveMethods(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "remove-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_AsyncMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "async-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_TransactionType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "transaction-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_AfterBeginMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "after-begin-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_BeforeCompletionMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "before-completion-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_AfterCompletionMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "after-completion-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_AroundInvokes(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-invoke", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_AroundTimeouts(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "around-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_EnvEntries(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_EjbRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_EjbLocalRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-local-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_ServiceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_ResourceRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_ResourceEnvRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_MessageDestinationRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_PersistenceContextRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_PersistenceUnitRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_PostConstructs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-construct", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_PreDestroys(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-destroy", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_DataSource(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "data-source", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_JmsConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_JmsDestination(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jms-destination", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_MailSession(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mail-session", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_ConnectionFactory(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "connection-factory", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_AdministeredObject(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "administered-object", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://xmlns.jcp.org/xml/ns/javaee" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_PostActivates(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "post-activate", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_PrePassivates(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "pre-passivate", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_SecurityRoleRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-role-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_SecurityIdentities(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "security-identity", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSessionBean_PassivationCapable(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "passivation-capable", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSessionBean_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (sessionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "session-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (sessionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "session-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "session-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (statefulTimeoutTypeEClass, 
		   source, 
		   new String[] {
			 "name", "stateful-timeoutType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getStatefulTimeoutType_Timeout(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getStatefulTimeoutType_Unit(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "unit", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getStatefulTimeoutType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (timerScheduleTypeEClass, 
		   source, 
		   new String[] {
			 "name", "timer-scheduleType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_Second(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "second", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_Minute(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "minute", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_Hour(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "hour", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_DayOfMonth(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "day-of-month", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_Month(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "month", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_DayOfWeek(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "day-of-week", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_Year(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "year", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerScheduleType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (timerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "timerType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Schedule(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "schedule", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Start(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "start", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_End(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "end", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_TimeoutMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timeout-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Persistent(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistent", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Timezone(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "timezone", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Info(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "info", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTimerType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (timeUnitTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "time-unit-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (timeUnitTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "time-unit-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "time-unit-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (transactionAttributeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "trans-attributeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (transactionAttributeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "trans-attributeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "trans-attributeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (transactionTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "transaction-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (transactionTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "transaction-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "transaction-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

	@Override
	public void freeze()
	{
		// since EClassImpl.freeze() does a clear() on all of the subClasses, we need to protect initializePackageContents() against it.
		boolean hasLock = false;
		try {
			hasLock = J2EEInit.aquireInitializePackageContentsLock();
		} catch (InterruptedException e) {
			J2EECorePlugin.logError(e);
		}
		finally {
			super.freeze();
			if( hasLock )
				J2EEInit.releaseInitializePackageContentsLock();
		}
	}

} //EjbPackageImpl
