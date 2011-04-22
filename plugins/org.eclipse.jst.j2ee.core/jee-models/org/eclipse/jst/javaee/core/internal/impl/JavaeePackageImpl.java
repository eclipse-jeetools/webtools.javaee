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
package org.eclipse.jst.javaee.core.internal.impl;

import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.internal.J2EEInit;

import org.eclipse.jst.javaee.core.AddressingResponsesType;
import org.eclipse.jst.javaee.core.AddressingType;
import org.eclipse.jst.javaee.core.DataSourceType;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EjbRefType;
import org.eclipse.jst.javaee.core.EmptyType;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.GenericBooleanType;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.IsolationLevelType;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.MessageDestinationUsageType;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceContextType;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.PortComponentRef;
import org.eclipse.jst.javaee.core.PropertyType;
import org.eclipse.jst.javaee.core.ResAuthType;
import org.eclipse.jst.javaee.core.ResSharingScopeType;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.RespectBindingType;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.javaee.core.ServiceRefHandler;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChain;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChains;
import org.eclipse.jst.javaee.core.UrlPatternType;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

import org.eclipse.jst.javaee.core.internal.util.JavaeeValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaeePackageImpl extends EPackageImpl implements JavaeePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addressingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataSourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass displayNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbLocalRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ejbRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emptyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass envEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iconEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass injectionTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lifecycleCallbackEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listenerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageDestinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageDestinationRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paramValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass persistenceContextRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass persistenceUnitRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portComponentRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEnvRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass respectBindingTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runAsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityRoleRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceRefHandlerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceRefHandlerChainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceRefHandlerChainsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass urlPatternTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum addressingResponsesTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum ejbRefTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum genericBooleanTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum isolationLevelTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum messageDestinationUsageTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum persistenceContextTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resAuthTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum resSharingScopeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType addressingResponsesTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType deweyVersionTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ejbLinkEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ejbRefNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ejbRefTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType envEntryTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType fullyQualifiedClassTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType genericBooleanTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType homeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType isolationLevelTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType javaIdentifierEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType javaTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType jdbcUrlTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType jndiNameEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType localEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType localHomeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType messageDestinationLinkEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType messageDestinationTypeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType messageDestinationUsageTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType pathTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType persistenceContextTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType protocolBindingListTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType protocolBindingTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType protocolURIAliasTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType qnamePatternEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType remoteEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType resAuthTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType resSharingScopeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType roleNameEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType trueFalseTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType trueFalseTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JavaeePackageImpl() {
		super(eNS_URI, JavaeeFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link JavaeePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JavaeePackage init() {
		if (isInited) return (JavaeePackage)EPackage.Registry.INSTANCE.getEPackage(JavaeePackage.eNS_URI);

		// Obtain or create and register package
		JavaeePackageImpl theJavaeePackage = (JavaeePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JavaeePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JavaeePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLNamespacePackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJavaeePackage.createPackageContents();

		// Initialize created meta-data
		theJavaeePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theJavaeePackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return JavaeeValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theJavaeePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JavaeePackage.eNS_URI, theJavaeePackage);

		J2EEInit.initEMFModels();
		return theJavaeePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddressingType() {
		return addressingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddressingType_Enabled() {
		return (EAttribute)addressingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddressingType_Required() {
		return (EAttribute)addressingTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddressingType_Responses() {
		return (EAttribute)addressingTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataSourceType() {
		return dataSourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataSourceType_Description() {
		return (EReference)dataSourceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_Name() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_ClassName() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_ServerName() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_PortNumber() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_DatabaseName() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_Url() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_User() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_Password() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataSourceType_Property() {
		return (EReference)dataSourceTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_LoginTimeout() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_Transactional() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_IsolationLevel() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_InitialPoolSize() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_MaxPoolSize() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_MinPoolSize() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_MaxIdleTime() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_MaxStatements() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataSourceType_Id() {
		return (EAttribute)dataSourceTypeEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescription() {
		return descriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Value() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Lang() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisplayName() {
		return displayNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayName_Value() {
		return (EAttribute)displayNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDisplayName_Lang() {
		return (EAttribute)displayNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEjbLocalRef() {
		return ejbLocalRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEjbLocalRef_Descriptions() {
		return (EReference)ejbLocalRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_EjbRefName() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_EjbRefType() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_LocalHome() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_Local() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_EjbLink() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_MappedName() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEjbLocalRef_InjectionTargets() {
		return (EReference)ejbLocalRefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_LookupName() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbLocalRef_Id() {
		return (EAttribute)ejbLocalRefEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEjbRef() {
		return ejbRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEjbRef_Descriptions() {
		return (EReference)ejbRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_EjbRefName() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_EjbRefType() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_Home() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_Remote() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_EjbLink() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_MappedName() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEjbRef_InjectionTargets() {
		return (EReference)ejbRefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_LookupName() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEjbRef_Id() {
		return (EAttribute)ejbRefEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmptyType() {
		return emptyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEmptyType_Id() {
		return (EAttribute)emptyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvEntry() {
		return envEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvEntry_Descriptions() {
		return (EReference)envEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnvEntry_EnvEntryName() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnvEntry_EnvEntryType() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnvEntry_EnvEntryValue() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnvEntry_MappedName() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnvEntry_InjectionTargets() {
		return (EReference)envEntryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnvEntry_LookupName() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnvEntry_Id() {
		return (EAttribute)envEntryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIcon() {
		return iconEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIcon_SmallIcon() {
		return (EAttribute)iconEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIcon_LargeIcon() {
		return (EAttribute)iconEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIcon_Id() {
		return (EAttribute)iconEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIcon_Lang() {
		return (EAttribute)iconEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInjectionTarget() {
		return injectionTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInjectionTarget_InjectionTargetClass() {
		return (EAttribute)injectionTargetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInjectionTarget_InjectionTargetName() {
		return (EAttribute)injectionTargetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLifecycleCallback() {
		return lifecycleCallbackEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLifecycleCallback_LifecycleCallbackClass() {
		return (EAttribute)lifecycleCallbackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLifecycleCallback_LifecycleCallbackMethod() {
		return (EAttribute)lifecycleCallbackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListener() {
		return listenerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListener_Descriptions() {
		return (EReference)listenerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListener_DisplayNames() {
		return (EReference)listenerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListener_Icons() {
		return (EReference)listenerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getListener_ListenerClass() {
		return (EAttribute)listenerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getListener_Id() {
		return (EAttribute)listenerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageDestination() {
		return messageDestinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDestination_Descriptions() {
		return (EReference)messageDestinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDestination_DisplayNames() {
		return (EReference)messageDestinationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDestination_Icons() {
		return (EReference)messageDestinationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestination_MessageDestinationName() {
		return (EAttribute)messageDestinationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestination_MappedName() {
		return (EAttribute)messageDestinationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestination_LookupName() {
		return (EAttribute)messageDestinationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestination_Id() {
		return (EAttribute)messageDestinationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageDestinationRef() {
		return messageDestinationRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDestinationRef_Descriptions() {
		return (EReference)messageDestinationRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_MessageDestinationRefName() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_MessageDestinationType() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_MessageDestinationUsage() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_MessageDestinationLink() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_MappedName() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageDestinationRef_InjectionTargets() {
		return (EReference)messageDestinationRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_LookupName() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessageDestinationRef_Id() {
		return (EAttribute)messageDestinationRefEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParamValue() {
		return paramValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParamValue_Descriptions() {
		return (EReference)paramValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamValue_ParamName() {
		return (EAttribute)paramValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamValue_ParamValue() {
		return (EAttribute)paramValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamValue_Id() {
		return (EAttribute)paramValueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersistenceContextRef() {
		return persistenceContextRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPersistenceContextRef_Descriptions() {
		return (EReference)persistenceContextRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceContextRef_PersistenceContextRefName() {
		return (EAttribute)persistenceContextRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceContextRef_PersistenceUnitName() {
		return (EAttribute)persistenceContextRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceContextRef_PersistenceContextType() {
		return (EAttribute)persistenceContextRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPersistenceContextRef_PersistenceProperties() {
		return (EReference)persistenceContextRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceContextRef_MappedName() {
		return (EAttribute)persistenceContextRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPersistenceContextRef_InjectionTargets() {
		return (EReference)persistenceContextRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceContextRef_Id() {
		return (EAttribute)persistenceContextRefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersistenceUnitRef() {
		return persistenceUnitRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPersistenceUnitRef_Descriptions() {
		return (EReference)persistenceUnitRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceUnitRef_PersistenceUnitRefName() {
		return (EAttribute)persistenceUnitRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceUnitRef_PersistenceUnitName() {
		return (EAttribute)persistenceUnitRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceUnitRef_MappedName() {
		return (EAttribute)persistenceUnitRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPersistenceUnitRef_InjectionTargets() {
		return (EReference)persistenceUnitRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPersistenceUnitRef_Id() {
		return (EAttribute)persistenceUnitRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortComponentRef() {
		return portComponentRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponentRef_ServiceEndpointInterface() {
		return (EAttribute)portComponentRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponentRef_EnableMtom() {
		return (EAttribute)portComponentRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponentRef_MtomThreshold() {
		return (EAttribute)portComponentRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortComponentRef_Addressing() {
		return (EReference)portComponentRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortComponentRef_RespectBinding() {
		return (EReference)portComponentRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponentRef_PortComponentLink() {
		return (EAttribute)portComponentRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortComponentRef_Id() {
		return (EAttribute)portComponentRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyType() {
		return propertyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyType_Name() {
		return (EAttribute)propertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyType_Value() {
		return (EAttribute)propertyTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyType_Id() {
		return (EAttribute)propertyTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceEnvRef() {
		return resourceEnvRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceEnvRef_Descriptions() {
		return (EReference)resourceEnvRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceEnvRef_ResourceEnvRefName() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceEnvRef_ResourceEnvRefType() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceEnvRef_MappedName() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceEnvRef_InjectionTargets() {
		return (EReference)resourceEnvRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceEnvRef_LookupName() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceEnvRef_Id() {
		return (EAttribute)resourceEnvRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceRef() {
		return resourceRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceRef_Descriptions() {
		return (EReference)resourceRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_ResRefName() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_ResType() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_ResAuth() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_ResSharingScope() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_MappedName() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceRef_InjectionTargets() {
		return (EReference)resourceRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_LookupName() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceRef_Id() {
		return (EAttribute)resourceRefEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRespectBindingType() {
		return respectBindingTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRespectBindingType_Enabled() {
		return (EAttribute)respectBindingTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRunAs() {
		return runAsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRunAs_Descriptions() {
		return (EReference)runAsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunAs_RoleName() {
		return (EAttribute)runAsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRunAs_Id() {
		return (EAttribute)runAsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityRole() {
		return securityRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityRole_Descriptions() {
		return (EReference)securityRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityRole_RoleName() {
		return (EAttribute)securityRoleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityRole_Id() {
		return (EAttribute)securityRoleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityRoleRef() {
		return securityRoleRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecurityRoleRef_Descriptions() {
		return (EReference)securityRoleRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityRoleRef_RoleName() {
		return (EAttribute)securityRoleRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityRoleRef_RoleLink() {
		return (EAttribute)securityRoleRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityRoleRef_Id() {
		return (EAttribute)securityRoleRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceRef() {
		return serviceRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_Descriptions() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_DisplayNames() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_Icons() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_ServiceRefName() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_ServiceInterface() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_ServiceRefType() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_WsdlFile() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_JaxrpcMappingFile() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_ServiceQname() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_PortComponentRefs() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_Handlers() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_HandlerChains() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_MappedName() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRef_InjectionTargets() {
		return (EReference)serviceRefEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_LookupName() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRef_Id() {
		return (EAttribute)serviceRefEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceRefHandler() {
		return serviceRefHandlerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRefHandler_Descriptions() {
		return (EReference)serviceRefHandlerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRefHandler_DisplayNames() {
		return (EReference)serviceRefHandlerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRefHandler_Icons() {
		return (EReference)serviceRefHandlerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandler_HandlerName() {
		return (EAttribute)serviceRefHandlerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandler_HandlerClass() {
		return (EAttribute)serviceRefHandlerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRefHandler_InitParams() {
		return (EReference)serviceRefHandlerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandler_SoapHeaders() {
		return (EAttribute)serviceRefHandlerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandler_SoapRoles() {
		return (EAttribute)serviceRefHandlerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandler_PortNames() {
		return (EAttribute)serviceRefHandlerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandler_Id() {
		return (EAttribute)serviceRefHandlerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceRefHandlerChain() {
		return serviceRefHandlerChainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandlerChain_ServiceNamePattern() {
		return (EAttribute)serviceRefHandlerChainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandlerChain_PortNamePattern() {
		return (EAttribute)serviceRefHandlerChainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandlerChain_ProtocolBindings() {
		return (EAttribute)serviceRefHandlerChainEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRefHandlerChain_Handlers() {
		return (EReference)serviceRefHandlerChainEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandlerChain_Id() {
		return (EAttribute)serviceRefHandlerChainEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceRefHandlerChains() {
		return serviceRefHandlerChainsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getServiceRefHandlerChains_HandlerChains() {
		return (EReference)serviceRefHandlerChainsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceRefHandlerChains_Id() {
		return (EAttribute)serviceRefHandlerChainsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUrlPatternType() {
		return urlPatternTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUrlPatternType_Value() {
		return (EAttribute)urlPatternTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAddressingResponsesType() {
		return addressingResponsesTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEjbRefType() {
		return ejbRefTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGenericBooleanType() {
		return genericBooleanTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIsolationLevelType() {
		return isolationLevelTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEnvEntryType() {
		return envEntryTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMessageDestinationUsageType() {
		return messageDestinationUsageTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPersistenceContextType() {
		return persistenceContextTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getResAuthType() {
		return resAuthTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getResSharingScopeType() {
		return resSharingScopeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAddressingResponsesTypeObject() {
		return addressingResponsesTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDeweyVersionType() {
		return deweyVersionTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEJBLink() {
		return ejbLinkEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEjbRefNameType() {
		return ejbRefNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEjbRefTypeObject() {
		return ejbRefTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getFullyQualifiedClassType() {
		return fullyQualifiedClassTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGenericBooleanTypeObject() {
		return genericBooleanTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getHome() {
		return homeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIsolationLevelTypeObject() {
		return isolationLevelTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getJavaIdentifier() {
		return javaIdentifierEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getJavaType() {
		return javaTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getJdbcUrlType() {
		return jdbcUrlTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getJNDIName() {
		return jndiNameEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLocal() {
		return localEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getLocalHome() {
		return localHomeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMessageDestinationLink() {
		return messageDestinationLinkEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMessageDestinationTypeType() {
		return messageDestinationTypeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getMessageDestinationUsageTypeObject() {
		return messageDestinationUsageTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPathType() {
		return pathTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPersistenceContextTypeObject() {
		return persistenceContextTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getProtocolBindingListType() {
		return protocolBindingListTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getProtocolBindingType() {
		return protocolBindingTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getProtocolURIAliasType() {
		return protocolURIAliasTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getQnamePattern() {
		return qnamePatternEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRemote() {
		return remoteEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getResAuthTypeObject() {
		return resAuthTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getResSharingScopeTypeObject() {
		return resSharingScopeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRoleName() {
		return roleNameEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTrueFalseType() {
		return trueFalseTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTrueFalseTypeObject() {
		return trueFalseTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeeFactory getJavaeeFactory() {
		return (JavaeeFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

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
		addressingTypeEClass = createEClass(ADDRESSING_TYPE);
		createEAttribute(addressingTypeEClass, ADDRESSING_TYPE__ENABLED);
		createEAttribute(addressingTypeEClass, ADDRESSING_TYPE__REQUIRED);
		createEAttribute(addressingTypeEClass, ADDRESSING_TYPE__RESPONSES);

		dataSourceTypeEClass = createEClass(DATA_SOURCE_TYPE);
		createEReference(dataSourceTypeEClass, DATA_SOURCE_TYPE__DESCRIPTION);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__NAME);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__CLASS_NAME);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__SERVER_NAME);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__PORT_NUMBER);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__DATABASE_NAME);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__URL);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__USER);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__PASSWORD);
		createEReference(dataSourceTypeEClass, DATA_SOURCE_TYPE__PROPERTY);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__LOGIN_TIMEOUT);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__TRANSACTIONAL);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__ISOLATION_LEVEL);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__INITIAL_POOL_SIZE);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__MAX_POOL_SIZE);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__MIN_POOL_SIZE);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__MAX_IDLE_TIME);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__MAX_STATEMENTS);
		createEAttribute(dataSourceTypeEClass, DATA_SOURCE_TYPE__ID);

		descriptionEClass = createEClass(DESCRIPTION);
		createEAttribute(descriptionEClass, DESCRIPTION__VALUE);
		createEAttribute(descriptionEClass, DESCRIPTION__LANG);

		displayNameEClass = createEClass(DISPLAY_NAME);
		createEAttribute(displayNameEClass, DISPLAY_NAME__VALUE);
		createEAttribute(displayNameEClass, DISPLAY_NAME__LANG);

		ejbLocalRefEClass = createEClass(EJB_LOCAL_REF);
		createEReference(ejbLocalRefEClass, EJB_LOCAL_REF__DESCRIPTIONS);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__EJB_REF_NAME);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__EJB_REF_TYPE);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__LOCAL_HOME);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__LOCAL);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__EJB_LINK);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__MAPPED_NAME);
		createEReference(ejbLocalRefEClass, EJB_LOCAL_REF__INJECTION_TARGETS);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__LOOKUP_NAME);
		createEAttribute(ejbLocalRefEClass, EJB_LOCAL_REF__ID);

		ejbRefEClass = createEClass(EJB_REF);
		createEReference(ejbRefEClass, EJB_REF__DESCRIPTIONS);
		createEAttribute(ejbRefEClass, EJB_REF__EJB_REF_NAME);
		createEAttribute(ejbRefEClass, EJB_REF__EJB_REF_TYPE);
		createEAttribute(ejbRefEClass, EJB_REF__HOME);
		createEAttribute(ejbRefEClass, EJB_REF__REMOTE);
		createEAttribute(ejbRefEClass, EJB_REF__EJB_LINK);
		createEAttribute(ejbRefEClass, EJB_REF__MAPPED_NAME);
		createEReference(ejbRefEClass, EJB_REF__INJECTION_TARGETS);
		createEAttribute(ejbRefEClass, EJB_REF__LOOKUP_NAME);
		createEAttribute(ejbRefEClass, EJB_REF__ID);

		emptyTypeEClass = createEClass(EMPTY_TYPE);
		createEAttribute(emptyTypeEClass, EMPTY_TYPE__ID);

		envEntryEClass = createEClass(ENV_ENTRY);
		createEReference(envEntryEClass, ENV_ENTRY__DESCRIPTIONS);
		createEAttribute(envEntryEClass, ENV_ENTRY__ENV_ENTRY_NAME);
		createEAttribute(envEntryEClass, ENV_ENTRY__ENV_ENTRY_TYPE);
		createEAttribute(envEntryEClass, ENV_ENTRY__ENV_ENTRY_VALUE);
		createEAttribute(envEntryEClass, ENV_ENTRY__MAPPED_NAME);
		createEReference(envEntryEClass, ENV_ENTRY__INJECTION_TARGETS);
		createEAttribute(envEntryEClass, ENV_ENTRY__LOOKUP_NAME);
		createEAttribute(envEntryEClass, ENV_ENTRY__ID);

		iconEClass = createEClass(ICON);
		createEAttribute(iconEClass, ICON__SMALL_ICON);
		createEAttribute(iconEClass, ICON__LARGE_ICON);
		createEAttribute(iconEClass, ICON__ID);
		createEAttribute(iconEClass, ICON__LANG);

		injectionTargetEClass = createEClass(INJECTION_TARGET);
		createEAttribute(injectionTargetEClass, INJECTION_TARGET__INJECTION_TARGET_CLASS);
		createEAttribute(injectionTargetEClass, INJECTION_TARGET__INJECTION_TARGET_NAME);

		lifecycleCallbackEClass = createEClass(LIFECYCLE_CALLBACK);
		createEAttribute(lifecycleCallbackEClass, LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_CLASS);
		createEAttribute(lifecycleCallbackEClass, LIFECYCLE_CALLBACK__LIFECYCLE_CALLBACK_METHOD);

		listenerEClass = createEClass(LISTENER);
		createEReference(listenerEClass, LISTENER__DESCRIPTIONS);
		createEReference(listenerEClass, LISTENER__DISPLAY_NAMES);
		createEReference(listenerEClass, LISTENER__ICONS);
		createEAttribute(listenerEClass, LISTENER__LISTENER_CLASS);
		createEAttribute(listenerEClass, LISTENER__ID);

		messageDestinationEClass = createEClass(MESSAGE_DESTINATION);
		createEReference(messageDestinationEClass, MESSAGE_DESTINATION__DESCRIPTIONS);
		createEReference(messageDestinationEClass, MESSAGE_DESTINATION__DISPLAY_NAMES);
		createEReference(messageDestinationEClass, MESSAGE_DESTINATION__ICONS);
		createEAttribute(messageDestinationEClass, MESSAGE_DESTINATION__MESSAGE_DESTINATION_NAME);
		createEAttribute(messageDestinationEClass, MESSAGE_DESTINATION__MAPPED_NAME);
		createEAttribute(messageDestinationEClass, MESSAGE_DESTINATION__LOOKUP_NAME);
		createEAttribute(messageDestinationEClass, MESSAGE_DESTINATION__ID);

		messageDestinationRefEClass = createEClass(MESSAGE_DESTINATION_REF);
		createEReference(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__DESCRIPTIONS);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_REF_NAME);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_TYPE);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_USAGE);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__MESSAGE_DESTINATION_LINK);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__MAPPED_NAME);
		createEReference(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__INJECTION_TARGETS);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__LOOKUP_NAME);
		createEAttribute(messageDestinationRefEClass, MESSAGE_DESTINATION_REF__ID);

		paramValueEClass = createEClass(PARAM_VALUE);
		createEReference(paramValueEClass, PARAM_VALUE__DESCRIPTIONS);
		createEAttribute(paramValueEClass, PARAM_VALUE__PARAM_NAME);
		createEAttribute(paramValueEClass, PARAM_VALUE__PARAM_VALUE);
		createEAttribute(paramValueEClass, PARAM_VALUE__ID);

		persistenceContextRefEClass = createEClass(PERSISTENCE_CONTEXT_REF);
		createEReference(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__DESCRIPTIONS);
		createEAttribute(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_REF_NAME);
		createEAttribute(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__PERSISTENCE_UNIT_NAME);
		createEAttribute(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__PERSISTENCE_CONTEXT_TYPE);
		createEReference(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__PERSISTENCE_PROPERTIES);
		createEAttribute(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__MAPPED_NAME);
		createEReference(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__INJECTION_TARGETS);
		createEAttribute(persistenceContextRefEClass, PERSISTENCE_CONTEXT_REF__ID);

		persistenceUnitRefEClass = createEClass(PERSISTENCE_UNIT_REF);
		createEReference(persistenceUnitRefEClass, PERSISTENCE_UNIT_REF__DESCRIPTIONS);
		createEAttribute(persistenceUnitRefEClass, PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_REF_NAME);
		createEAttribute(persistenceUnitRefEClass, PERSISTENCE_UNIT_REF__PERSISTENCE_UNIT_NAME);
		createEAttribute(persistenceUnitRefEClass, PERSISTENCE_UNIT_REF__MAPPED_NAME);
		createEReference(persistenceUnitRefEClass, PERSISTENCE_UNIT_REF__INJECTION_TARGETS);
		createEAttribute(persistenceUnitRefEClass, PERSISTENCE_UNIT_REF__ID);

		portComponentRefEClass = createEClass(PORT_COMPONENT_REF);
		createEAttribute(portComponentRefEClass, PORT_COMPONENT_REF__SERVICE_ENDPOINT_INTERFACE);
		createEAttribute(portComponentRefEClass, PORT_COMPONENT_REF__ENABLE_MTOM);
		createEAttribute(portComponentRefEClass, PORT_COMPONENT_REF__MTOM_THRESHOLD);
		createEReference(portComponentRefEClass, PORT_COMPONENT_REF__ADDRESSING);
		createEReference(portComponentRefEClass, PORT_COMPONENT_REF__RESPECT_BINDING);
		createEAttribute(portComponentRefEClass, PORT_COMPONENT_REF__PORT_COMPONENT_LINK);
		createEAttribute(portComponentRefEClass, PORT_COMPONENT_REF__ID);

		propertyTypeEClass = createEClass(PROPERTY_TYPE);
		createEAttribute(propertyTypeEClass, PROPERTY_TYPE__NAME);
		createEAttribute(propertyTypeEClass, PROPERTY_TYPE__VALUE);
		createEAttribute(propertyTypeEClass, PROPERTY_TYPE__ID);

		resourceEnvRefEClass = createEClass(RESOURCE_ENV_REF);
		createEReference(resourceEnvRefEClass, RESOURCE_ENV_REF__DESCRIPTIONS);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__RESOURCE_ENV_REF_NAME);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__RESOURCE_ENV_REF_TYPE);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__MAPPED_NAME);
		createEReference(resourceEnvRefEClass, RESOURCE_ENV_REF__INJECTION_TARGETS);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__LOOKUP_NAME);
		createEAttribute(resourceEnvRefEClass, RESOURCE_ENV_REF__ID);

		resourceRefEClass = createEClass(RESOURCE_REF);
		createEReference(resourceRefEClass, RESOURCE_REF__DESCRIPTIONS);
		createEAttribute(resourceRefEClass, RESOURCE_REF__RES_REF_NAME);
		createEAttribute(resourceRefEClass, RESOURCE_REF__RES_TYPE);
		createEAttribute(resourceRefEClass, RESOURCE_REF__RES_AUTH);
		createEAttribute(resourceRefEClass, RESOURCE_REF__RES_SHARING_SCOPE);
		createEAttribute(resourceRefEClass, RESOURCE_REF__MAPPED_NAME);
		createEReference(resourceRefEClass, RESOURCE_REF__INJECTION_TARGETS);
		createEAttribute(resourceRefEClass, RESOURCE_REF__LOOKUP_NAME);
		createEAttribute(resourceRefEClass, RESOURCE_REF__ID);

		respectBindingTypeEClass = createEClass(RESPECT_BINDING_TYPE);
		createEAttribute(respectBindingTypeEClass, RESPECT_BINDING_TYPE__ENABLED);

		runAsEClass = createEClass(RUN_AS);
		createEReference(runAsEClass, RUN_AS__DESCRIPTIONS);
		createEAttribute(runAsEClass, RUN_AS__ROLE_NAME);
		createEAttribute(runAsEClass, RUN_AS__ID);

		securityRoleEClass = createEClass(SECURITY_ROLE);
		createEReference(securityRoleEClass, SECURITY_ROLE__DESCRIPTIONS);
		createEAttribute(securityRoleEClass, SECURITY_ROLE__ROLE_NAME);
		createEAttribute(securityRoleEClass, SECURITY_ROLE__ID);

		securityRoleRefEClass = createEClass(SECURITY_ROLE_REF);
		createEReference(securityRoleRefEClass, SECURITY_ROLE_REF__DESCRIPTIONS);
		createEAttribute(securityRoleRefEClass, SECURITY_ROLE_REF__ROLE_NAME);
		createEAttribute(securityRoleRefEClass, SECURITY_ROLE_REF__ROLE_LINK);
		createEAttribute(securityRoleRefEClass, SECURITY_ROLE_REF__ID);

		serviceRefEClass = createEClass(SERVICE_REF);
		createEReference(serviceRefEClass, SERVICE_REF__DESCRIPTIONS);
		createEReference(serviceRefEClass, SERVICE_REF__DISPLAY_NAMES);
		createEReference(serviceRefEClass, SERVICE_REF__ICONS);
		createEAttribute(serviceRefEClass, SERVICE_REF__SERVICE_REF_NAME);
		createEAttribute(serviceRefEClass, SERVICE_REF__SERVICE_INTERFACE);
		createEAttribute(serviceRefEClass, SERVICE_REF__SERVICE_REF_TYPE);
		createEAttribute(serviceRefEClass, SERVICE_REF__WSDL_FILE);
		createEAttribute(serviceRefEClass, SERVICE_REF__JAXRPC_MAPPING_FILE);
		createEAttribute(serviceRefEClass, SERVICE_REF__SERVICE_QNAME);
		createEReference(serviceRefEClass, SERVICE_REF__PORT_COMPONENT_REFS);
		createEReference(serviceRefEClass, SERVICE_REF__HANDLERS);
		createEReference(serviceRefEClass, SERVICE_REF__HANDLER_CHAINS);
		createEAttribute(serviceRefEClass, SERVICE_REF__MAPPED_NAME);
		createEReference(serviceRefEClass, SERVICE_REF__INJECTION_TARGETS);
		createEAttribute(serviceRefEClass, SERVICE_REF__LOOKUP_NAME);
		createEAttribute(serviceRefEClass, SERVICE_REF__ID);

		serviceRefHandlerEClass = createEClass(SERVICE_REF_HANDLER);
		createEReference(serviceRefHandlerEClass, SERVICE_REF_HANDLER__DESCRIPTIONS);
		createEReference(serviceRefHandlerEClass, SERVICE_REF_HANDLER__DISPLAY_NAMES);
		createEReference(serviceRefHandlerEClass, SERVICE_REF_HANDLER__ICONS);
		createEAttribute(serviceRefHandlerEClass, SERVICE_REF_HANDLER__HANDLER_NAME);
		createEAttribute(serviceRefHandlerEClass, SERVICE_REF_HANDLER__HANDLER_CLASS);
		createEReference(serviceRefHandlerEClass, SERVICE_REF_HANDLER__INIT_PARAMS);
		createEAttribute(serviceRefHandlerEClass, SERVICE_REF_HANDLER__SOAP_HEADERS);
		createEAttribute(serviceRefHandlerEClass, SERVICE_REF_HANDLER__SOAP_ROLES);
		createEAttribute(serviceRefHandlerEClass, SERVICE_REF_HANDLER__PORT_NAMES);
		createEAttribute(serviceRefHandlerEClass, SERVICE_REF_HANDLER__ID);

		serviceRefHandlerChainEClass = createEClass(SERVICE_REF_HANDLER_CHAIN);
		createEAttribute(serviceRefHandlerChainEClass, SERVICE_REF_HANDLER_CHAIN__SERVICE_NAME_PATTERN);
		createEAttribute(serviceRefHandlerChainEClass, SERVICE_REF_HANDLER_CHAIN__PORT_NAME_PATTERN);
		createEAttribute(serviceRefHandlerChainEClass, SERVICE_REF_HANDLER_CHAIN__PROTOCOL_BINDINGS);
		createEReference(serviceRefHandlerChainEClass, SERVICE_REF_HANDLER_CHAIN__HANDLERS);
		createEAttribute(serviceRefHandlerChainEClass, SERVICE_REF_HANDLER_CHAIN__ID);

		serviceRefHandlerChainsEClass = createEClass(SERVICE_REF_HANDLER_CHAINS);
		createEReference(serviceRefHandlerChainsEClass, SERVICE_REF_HANDLER_CHAINS__HANDLER_CHAINS);
		createEAttribute(serviceRefHandlerChainsEClass, SERVICE_REF_HANDLER_CHAINS__ID);

		urlPatternTypeEClass = createEClass(URL_PATTERN_TYPE);
		createEAttribute(urlPatternTypeEClass, URL_PATTERN_TYPE__VALUE);

		// Create enums
		addressingResponsesTypeEEnum = createEEnum(ADDRESSING_RESPONSES_TYPE);
		ejbRefTypeEEnum = createEEnum(EJB_REF_TYPE);
		genericBooleanTypeEEnum = createEEnum(GENERIC_BOOLEAN_TYPE);
		isolationLevelTypeEEnum = createEEnum(ISOLATION_LEVEL_TYPE);
		messageDestinationUsageTypeEEnum = createEEnum(MESSAGE_DESTINATION_USAGE_TYPE);
		persistenceContextTypeEEnum = createEEnum(PERSISTENCE_CONTEXT_TYPE);
		resAuthTypeEEnum = createEEnum(RES_AUTH_TYPE);
		resSharingScopeTypeEEnum = createEEnum(RES_SHARING_SCOPE_TYPE);

		// Create data types
		addressingResponsesTypeObjectEDataType = createEDataType(ADDRESSING_RESPONSES_TYPE_OBJECT);
		deweyVersionTypeEDataType = createEDataType(DEWEY_VERSION_TYPE);
		ejbLinkEDataType = createEDataType(EJB_LINK);
		ejbRefNameTypeEDataType = createEDataType(EJB_REF_NAME_TYPE);
		ejbRefTypeObjectEDataType = createEDataType(EJB_REF_TYPE_OBJECT);
		envEntryTypeEDataType = createEDataType(ENV_ENTRY_TYPE);
		fullyQualifiedClassTypeEDataType = createEDataType(FULLY_QUALIFIED_CLASS_TYPE);
		genericBooleanTypeObjectEDataType = createEDataType(GENERIC_BOOLEAN_TYPE_OBJECT);
		homeEDataType = createEDataType(HOME);
		isolationLevelTypeObjectEDataType = createEDataType(ISOLATION_LEVEL_TYPE_OBJECT);
		javaIdentifierEDataType = createEDataType(JAVA_IDENTIFIER);
		javaTypeEDataType = createEDataType(JAVA_TYPE);
		jdbcUrlTypeEDataType = createEDataType(JDBC_URL_TYPE);
		jndiNameEDataType = createEDataType(JNDI_NAME);
		localEDataType = createEDataType(LOCAL);
		localHomeEDataType = createEDataType(LOCAL_HOME);
		messageDestinationLinkEDataType = createEDataType(MESSAGE_DESTINATION_LINK);
		messageDestinationTypeTypeEDataType = createEDataType(MESSAGE_DESTINATION_TYPE_TYPE);
		messageDestinationUsageTypeObjectEDataType = createEDataType(MESSAGE_DESTINATION_USAGE_TYPE_OBJECT);
		pathTypeEDataType = createEDataType(PATH_TYPE);
		persistenceContextTypeObjectEDataType = createEDataType(PERSISTENCE_CONTEXT_TYPE_OBJECT);
		protocolBindingListTypeEDataType = createEDataType(PROTOCOL_BINDING_LIST_TYPE);
		protocolBindingTypeEDataType = createEDataType(PROTOCOL_BINDING_TYPE);
		protocolURIAliasTypeEDataType = createEDataType(PROTOCOL_URI_ALIAS_TYPE);
		qnamePatternEDataType = createEDataType(QNAME_PATTERN);
		remoteEDataType = createEDataType(REMOTE);
		resAuthTypeObjectEDataType = createEDataType(RES_AUTH_TYPE_OBJECT);
		resSharingScopeTypeObjectEDataType = createEDataType(RES_SHARING_SCOPE_TYPE_OBJECT);
		roleNameEDataType = createEDataType(ROLE_NAME);
		trueFalseTypeEDataType = createEDataType(TRUE_FALSE_TYPE);
		trueFalseTypeObjectEDataType = createEDataType(TRUE_FALSE_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

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
		XMLNamespacePackage theXMLNamespacePackage = (XMLNamespacePackage)EPackage.Registry.INSTANCE.getEPackage(XMLNamespacePackage.eNS_URI);

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
			initEClass(addressingTypeEClass, AddressingType.class, "AddressingType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getAddressingType_Enabled(), this.getTrueFalseType(), "enabled", null, 0, 1, AddressingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getAddressingType_Required(), this.getTrueFalseType(), "required", null, 0, 1, AddressingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getAddressingType_Responses(), this.getAddressingResponsesType(), "responses", null, 0, 1, AddressingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(dataSourceTypeEClass, DataSourceType.class, "DataSourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getDataSourceType_Description(), this.getDescription(), null, "description", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_Name(), this.getJNDIName(), "name", null, 1, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_ClassName(), this.getFullyQualifiedClassType(), "className", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_ServerName(), theXMLTypePackage.getString(), "serverName", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_PortNumber(), theXMLTypePackage.getInteger(), "portNumber", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_DatabaseName(), theXMLTypePackage.getString(), "databaseName", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_Url(), this.getJdbcUrlType(), "url", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_User(), theXMLTypePackage.getString(), "user", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_Password(), theXMLTypePackage.getString(), "password", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getDataSourceType_Property(), this.getPropertyType(), null, "property", null, 0, -1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_LoginTimeout(), theXMLTypePackage.getInteger(), "loginTimeout", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_Transactional(), theXMLTypePackage.getBoolean(), "transactional", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_IsolationLevel(), this.getIsolationLevelType(), "isolationLevel", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_InitialPoolSize(), theXMLTypePackage.getInteger(), "initialPoolSize", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_MaxPoolSize(), theXMLTypePackage.getInteger(), "maxPoolSize", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_MinPoolSize(), theXMLTypePackage.getInteger(), "minPoolSize", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_MaxIdleTime(), theXMLTypePackage.getInteger(), "maxIdleTime", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_MaxStatements(), theXMLTypePackage.getInteger(), "maxStatements", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDataSourceType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DataSourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(descriptionEClass, Description.class, "Description", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getDescription_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDescription_Lang(), theXMLNamespacePackage.getLangType(), "lang", null, 0, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(displayNameEClass, DisplayName.class, "DisplayName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getDisplayName_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, DisplayName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getDisplayName_Lang(), theXMLNamespacePackage.getLangType(), "lang", null, 0, 1, DisplayName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(ejbLocalRefEClass, EjbLocalRef.class, "EjbLocalRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getEjbLocalRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_EjbRefName(), this.getEjbRefNameType(), "ejbRefName", null, 1, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_EjbRefType(), this.getEjbRefType(), "ejbRefType", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_LocalHome(), this.getLocalHome(), "localHome", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_Local(), this.getLocal(), "local", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_EjbLink(), this.getEJBLink(), "ejbLink", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getEjbLocalRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbLocalRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EjbLocalRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(ejbRefEClass, EjbRef.class, "EjbRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getEjbRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_EjbRefName(), this.getEjbRefNameType(), "ejbRefName", null, 1, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_EjbRefType(), this.getEjbRefType(), "ejbRefType", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_Home(), this.getHome(), "home", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_Remote(), this.getRemote(), "remote", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_EjbLink(), this.getEJBLink(), "ejbLink", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getEjbRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEjbRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EjbRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(emptyTypeEClass, EmptyType.class, "EmptyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getEmptyType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EmptyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(envEntryEClass, EnvEntry.class, "EnvEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getEnvEntry_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEnvEntry_EnvEntryName(), this.getJNDIName(), "envEntryName", null, 1, 1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEnvEntry_EnvEntryType(), this.getEnvEntryType(), "envEntryType", null, 0, 1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEnvEntry_EnvEntryValue(), theXMLTypePackage.getString(), "envEntryValue", null, 0, 1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEnvEntry_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getEnvEntry_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEnvEntry_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getEnvEntry_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, EnvEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(iconEClass, Icon.class, "Icon", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getIcon_SmallIcon(), this.getPathType(), "smallIcon", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getIcon_LargeIcon(), this.getPathType(), "largeIcon", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getIcon_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getIcon_Lang(), theXMLNamespacePackage.getLangType(), "lang", null, 0, 1, Icon.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(injectionTargetEClass, InjectionTarget.class, "InjectionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getInjectionTarget_InjectionTargetClass(), this.getFullyQualifiedClassType(), "injectionTargetClass", null, 1, 1, InjectionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getInjectionTarget_InjectionTargetName(), this.getJavaIdentifier(), "injectionTargetName", null, 1, 1, InjectionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(lifecycleCallbackEClass, LifecycleCallback.class, "LifecycleCallback", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getLifecycleCallback_LifecycleCallbackClass(), this.getFullyQualifiedClassType(), "lifecycleCallbackClass", null, 0, 1, LifecycleCallback.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getLifecycleCallback_LifecycleCallbackMethod(), this.getJavaIdentifier(), "lifecycleCallbackMethod", null, 1, 1, LifecycleCallback.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(listenerEClass, Listener.class, "Listener", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getListener_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, Listener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getListener_DisplayNames(), this.getDisplayName(), null, "displayNames", null, 0, -1, Listener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getListener_Icons(), this.getIcon(), null, "icons", null, 0, -1, Listener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getListener_ListenerClass(), this.getFullyQualifiedClassType(), "listenerClass", null, 1, 1, Listener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getListener_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Listener.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(messageDestinationEClass, MessageDestination.class, "MessageDestination", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getMessageDestination_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getMessageDestination_DisplayNames(), this.getDisplayName(), null, "displayNames", null, 0, -1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getMessageDestination_Icons(), this.getIcon(), null, "icons", null, 0, -1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestination_MessageDestinationName(), theXMLTypePackage.getToken(), "messageDestinationName", null, 1, 1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestination_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestination_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestination_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MessageDestination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(messageDestinationRefEClass, MessageDestinationRef.class, "MessageDestinationRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getMessageDestinationRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_MessageDestinationRefName(), this.getJNDIName(), "messageDestinationRefName", null, 1, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_MessageDestinationType(), this.getMessageDestinationTypeType(), "messageDestinationType", null, 0, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_MessageDestinationUsage(), this.getMessageDestinationUsageType(), "messageDestinationUsage", null, 0, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_MessageDestinationLink(), this.getMessageDestinationLink(), "messageDestinationLink", null, 0, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getMessageDestinationRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getMessageDestinationRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MessageDestinationRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(paramValueEClass, ParamValue.class, "ParamValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getParamValue_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, ParamValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getParamValue_ParamName(), theXMLTypePackage.getToken(), "paramName", null, 1, 1, ParamValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getParamValue_ParamValue(), theXMLTypePackage.getString(), "paramValue", null, 1, 1, ParamValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getParamValue_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ParamValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(persistenceContextRefEClass, PersistenceContextRef.class, "PersistenceContextRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getPersistenceContextRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceContextRef_PersistenceContextRefName(), this.getJNDIName(), "persistenceContextRefName", null, 1, 1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceContextRef_PersistenceUnitName(), theXMLTypePackage.getToken(), "persistenceUnitName", null, 0, 1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceContextRef_PersistenceContextType(), this.getPersistenceContextType(), "persistenceContextType", null, 0, 1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPersistenceContextRef_PersistenceProperties(), this.getPropertyType(), null, "persistenceProperties", null, 0, -1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceContextRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPersistenceContextRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceContextRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PersistenceContextRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(persistenceUnitRefEClass, PersistenceUnitRef.class, "PersistenceUnitRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getPersistenceUnitRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, PersistenceUnitRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceUnitRef_PersistenceUnitRefName(), this.getJNDIName(), "persistenceUnitRefName", null, 1, 1, PersistenceUnitRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceUnitRef_PersistenceUnitName(), theXMLTypePackage.getToken(), "persistenceUnitName", null, 0, 1, PersistenceUnitRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceUnitRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, PersistenceUnitRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPersistenceUnitRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, PersistenceUnitRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPersistenceUnitRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PersistenceUnitRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(portComponentRefEClass, PortComponentRef.class, "PortComponentRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getPortComponentRef_ServiceEndpointInterface(), this.getFullyQualifiedClassType(), "serviceEndpointInterface", null, 1, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponentRef_EnableMtom(), this.getTrueFalseType(), "enableMtom", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponentRef_MtomThreshold(), theXMLTypePackage.getNonNegativeInteger(), "mtomThreshold", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponentRef_Addressing(), this.getAddressingType(), null, "addressing", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getPortComponentRef_RespectBinding(), this.getRespectBindingType(), null, "respectBinding", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponentRef_PortComponentLink(), theXMLTypePackage.getToken(), "portComponentLink", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPortComponentRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PortComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(propertyTypeEClass, PropertyType.class, "PropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getPropertyType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPropertyType_Value(), theXMLTypePackage.getString(), "value", null, 1, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getPropertyType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(resourceEnvRefEClass, ResourceEnvRef.class, "ResourceEnvRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getResourceEnvRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceEnvRef_ResourceEnvRefName(), this.getJNDIName(), "resourceEnvRefName", null, 1, 1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceEnvRef_ResourceEnvRefType(), this.getFullyQualifiedClassType(), "resourceEnvRefType", null, 0, 1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceEnvRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getResourceEnvRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceEnvRef_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceEnvRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ResourceEnvRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(resourceRefEClass, ResourceRef.class, "ResourceRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getResourceRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_ResRefName(), this.getJNDIName(), "resRefName", null, 1, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_ResType(), this.getFullyQualifiedClassType(), "resType", null, 0, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_ResAuth(), this.getResAuthType(), "resAuth", null, 0, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_ResSharingScope(), this.getResSharingScopeType(), "resSharingScope", null, 0, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getResourceRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getResourceRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ResourceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(respectBindingTypeEClass, RespectBindingType.class, "RespectBindingType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getRespectBindingType_Enabled(), this.getTrueFalseType(), "enabled", null, 0, 1, RespectBindingType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(runAsEClass, RunAs.class, "RunAs", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getRunAs_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, RunAs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getRunAs_RoleName(), this.getRoleName(), "roleName", null, 1, 1, RunAs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getRunAs_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RunAs.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(securityRoleEClass, SecurityRole.class, "SecurityRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getSecurityRole_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, SecurityRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getSecurityRole_RoleName(), this.getRoleName(), "roleName", null, 1, 1, SecurityRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getSecurityRole_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SecurityRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(securityRoleRefEClass, SecurityRoleRef.class, "SecurityRoleRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getSecurityRoleRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, SecurityRoleRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getSecurityRoleRef_RoleName(), this.getRoleName(), "roleName", null, 1, 1, SecurityRoleRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getSecurityRoleRef_RoleLink(), this.getRoleName(), "roleLink", null, 0, 1, SecurityRoleRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getSecurityRoleRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SecurityRoleRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(serviceRefEClass, ServiceRef.class, "ServiceRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getServiceRef_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_DisplayNames(), this.getDisplayName(), null, "displayNames", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_Icons(), this.getIcon(), null, "icons", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_ServiceRefName(), this.getJNDIName(), "serviceRefName", null, 1, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_ServiceInterface(), this.getFullyQualifiedClassType(), "serviceInterface", null, 1, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_ServiceRefType(), this.getFullyQualifiedClassType(), "serviceRefType", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_WsdlFile(), theXMLTypePackage.getAnyURI(), "wsdlFile", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_JaxrpcMappingFile(), this.getPathType(), "jaxrpcMappingFile", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_ServiceQname(), theXMLTypePackage.getQName(), "serviceQname", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_PortComponentRefs(), this.getPortComponentRef(), null, "portComponentRefs", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_Handlers(), this.getServiceRefHandler(), null, "handlers", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_HandlerChains(), this.getServiceRefHandlerChains(), null, "handlerChains", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_MappedName(), theXMLTypePackage.getString(), "mappedName", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRef_InjectionTargets(), this.getInjectionTarget(), null, "injectionTargets", null, 0, -1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_LookupName(), theXMLTypePackage.getString(), "lookupName", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRef_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ServiceRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(serviceRefHandlerEClass, ServiceRefHandler.class, "ServiceRefHandler", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getServiceRefHandler_Descriptions(), this.getDescription(), null, "descriptions", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRefHandler_DisplayNames(), this.getDisplayName(), null, "displayNames", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRefHandler_Icons(), this.getIcon(), null, "icons", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandler_HandlerName(), theXMLTypePackage.getToken(), "handlerName", null, 1, 1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandler_HandlerClass(), this.getFullyQualifiedClassType(), "handlerClass", null, 1, 1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRefHandler_InitParams(), this.getParamValue(), null, "initParams", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandler_SoapHeaders(), theXMLTypePackage.getQName(), "soapHeaders", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandler_SoapRoles(), theXMLTypePackage.getToken(), "soapRoles", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandler_PortNames(), theXMLTypePackage.getToken(), "portNames", null, 0, -1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandler_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ServiceRefHandler.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(serviceRefHandlerChainEClass, ServiceRefHandlerChain.class, "ServiceRefHandlerChain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getServiceRefHandlerChain_ServiceNamePattern(), this.getQnamePattern(), "serviceNamePattern", null, 0, 1, ServiceRefHandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandlerChain_PortNamePattern(), this.getQnamePattern(), "portNamePattern", null, 0, 1, ServiceRefHandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandlerChain_ProtocolBindings(), this.getProtocolBindingListType(), "protocolBindings", null, 0, 1, ServiceRefHandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEReference(getServiceRefHandlerChain_Handlers(), this.getServiceRefHandler(), null, "handlers", null, 1, -1, ServiceRefHandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandlerChain_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ServiceRefHandlerChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(serviceRefHandlerChainsEClass, ServiceRefHandlerChains.class, "ServiceRefHandlerChains", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEReference(getServiceRefHandlerChains_HandlerChains(), this.getServiceRefHandlerChain(), null, "handlerChains", null, 0, -1, ServiceRefHandlerChains.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
			initEAttribute(getServiceRefHandlerChains_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ServiceRefHandlerChains.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			initEClass(urlPatternTypeEClass, UrlPatternType.class, "UrlPatternType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEAttribute(getUrlPatternType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, UrlPatternType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
	
			// Initialize enums and add enum literals
			initEEnum(addressingResponsesTypeEEnum, AddressingResponsesType.class, "AddressingResponsesType"); //$NON-NLS-1$
			addEEnumLiteral(addressingResponsesTypeEEnum, AddressingResponsesType.ANONYMOUS);
			addEEnumLiteral(addressingResponsesTypeEEnum, AddressingResponsesType.NONANONYMOUS);
			addEEnumLiteral(addressingResponsesTypeEEnum, AddressingResponsesType.ALL);
	
			initEEnum(ejbRefTypeEEnum, EjbRefType.class, "EjbRefType"); //$NON-NLS-1$
			addEEnumLiteral(ejbRefTypeEEnum, EjbRefType.ENTITY_LITERAL);
			addEEnumLiteral(ejbRefTypeEEnum, EjbRefType.SESSION_LITERAL);
	
			initEEnum(genericBooleanTypeEEnum, GenericBooleanType.class, "GenericBooleanType"); //$NON-NLS-1$
			addEEnumLiteral(genericBooleanTypeEEnum, GenericBooleanType.TRUE);
			addEEnumLiteral(genericBooleanTypeEEnum, GenericBooleanType.FALSE);
			addEEnumLiteral(genericBooleanTypeEEnum, GenericBooleanType.YES);
			addEEnumLiteral(genericBooleanTypeEEnum, GenericBooleanType.NO);
	
			initEEnum(isolationLevelTypeEEnum, IsolationLevelType.class, "IsolationLevelType"); //$NON-NLS-1$
			addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.TRANSACTIONREADUNCOMMITTED);
			addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.TRANSACTIONREADCOMMITTED);
			addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.TRANSACTIONREPEATABLEREAD);
			addEEnumLiteral(isolationLevelTypeEEnum, IsolationLevelType.TRANSACTIONSERIALIZABLE);
	
			initEEnum(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.class, "MessageDestinationUsageType"); //$NON-NLS-1$
			addEEnumLiteral(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.CONSUMES_LITERAL);
			addEEnumLiteral(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.PRODUCES_LITERAL);
			addEEnumLiteral(messageDestinationUsageTypeEEnum, MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);
	
			initEEnum(persistenceContextTypeEEnum, PersistenceContextType.class, "PersistenceContextType"); //$NON-NLS-1$
			addEEnumLiteral(persistenceContextTypeEEnum, PersistenceContextType.TRANSACTION_LITERAL);
			addEEnumLiteral(persistenceContextTypeEEnum, PersistenceContextType.EXTENDED_LITERAL);
	
			initEEnum(resAuthTypeEEnum, ResAuthType.class, "ResAuthType"); //$NON-NLS-1$
			addEEnumLiteral(resAuthTypeEEnum, ResAuthType.APPLICATION_LITERAL);
			addEEnumLiteral(resAuthTypeEEnum, ResAuthType.CONTAINER_LITERAL);
	
			initEEnum(resSharingScopeTypeEEnum, ResSharingScopeType.class, "ResSharingScopeType"); //$NON-NLS-1$
			addEEnumLiteral(resSharingScopeTypeEEnum, ResSharingScopeType.SHAREABLE_LITERAL);
			addEEnumLiteral(resSharingScopeTypeEEnum, ResSharingScopeType.UNSHAREABLE_LITERAL);
	
			// Initialize data types
			initEDataType(addressingResponsesTypeObjectEDataType, AddressingResponsesType.class, "AddressingResponsesTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(deweyVersionTypeEDataType, String.class, "DeweyVersionType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(ejbLinkEDataType, String.class, "EJBLink", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(ejbRefNameTypeEDataType, String.class, "EjbRefNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(ejbRefTypeObjectEDataType, EjbRefType.class, "EjbRefTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(envEntryTypeEDataType, String.class, "EnvEntryType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(fullyQualifiedClassTypeEDataType, String.class, "FullyQualifiedClassType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(genericBooleanTypeObjectEDataType, GenericBooleanType.class, "GenericBooleanTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(homeEDataType, String.class, "Home", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(isolationLevelTypeObjectEDataType, IsolationLevelType.class, "IsolationLevelTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(javaIdentifierEDataType, String.class, "JavaIdentifier", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(javaTypeEDataType, String.class, "JavaType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(jdbcUrlTypeEDataType, String.class, "JdbcUrlType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(jndiNameEDataType, String.class, "JNDIName", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(localEDataType, String.class, "Local", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(localHomeEDataType, String.class, "LocalHome", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(messageDestinationLinkEDataType, String.class, "MessageDestinationLink", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(messageDestinationTypeTypeEDataType, String.class, "MessageDestinationTypeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(messageDestinationUsageTypeObjectEDataType, MessageDestinationUsageType.class, "MessageDestinationUsageTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(pathTypeEDataType, String.class, "PathType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(persistenceContextTypeObjectEDataType, PersistenceContextType.class, "PersistenceContextTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(protocolBindingListTypeEDataType, List.class, "ProtocolBindingListType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(protocolBindingTypeEDataType, String.class, "ProtocolBindingType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(protocolURIAliasTypeEDataType, String.class, "ProtocolURIAliasType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(qnamePatternEDataType, String.class, "QnamePattern", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(remoteEDataType, String.class, "Remote", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(resAuthTypeObjectEDataType, ResAuthType.class, "ResAuthTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(resSharingScopeTypeObjectEDataType, ResSharingScopeType.class, "ResSharingScopeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(roleNameEDataType, String.class, "RoleName", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(trueFalseTypeEDataType, boolean.class, "TrueFalseType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
			initEDataType(trueFalseTypeObjectEDataType, Boolean.class, "TrueFalseTypeObject", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
	
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
		  (addressingResponsesTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "addressing-responsesType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (addressingResponsesTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "addressing-responsesType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "addressing-responsesType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (addressingTypeEClass, 
		   source, 
		   new String[] {
			 "name", "addressingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAddressingType_Enabled(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "enabled", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAddressingType_Required(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "required", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getAddressingType_Responses(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "responses", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (dataSourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "data-sourceType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_Description(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_Name(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_ClassName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "class-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_ServerName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "server-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_PortNumber(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "port-number", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_DatabaseName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "database-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_Url(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "url", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_User(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "user", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_Password(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "password", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_Property(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_LoginTimeout(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "login-timeout", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_Transactional(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "transactional", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_IsolationLevel(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "isolation-level", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_InitialPoolSize(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "initial-pool-size", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_MaxPoolSize(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "max-pool-size", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_MinPoolSize(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "min-pool-size", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_MaxIdleTime(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "max-idle-time", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDataSourceType_MaxStatements(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "max-statements", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getDataSourceType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (descriptionEClass, 
		   source, 
		   new String[] {
			 "name", "descriptionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getDescription_Value(), 
		   source, 
		   new String[] {
			 "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDescription_Lang(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (deweyVersionTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "dewey-versionType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "\\.?[0-9]+(\\.[0-9]+)*" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (displayNameEClass, 
		   source, 
		   new String[] {
			 "name", "display-nameType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getDisplayName_Value(), 
		   source, 
		   new String[] {
			 "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getDisplayName_Lang(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbLinkEDataType, 
		   source, 
		   new String[] {
			 "name", "ejb-linkType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbLocalRefEClass, 
		   source, 
		   new String[] {
			 "name", "ejb-local-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_EjbRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_EjbRefType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_LocalHome(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local-home", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_Local(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "local", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_EjbLink(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-link", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEjbLocalRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEjbLocalRef_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbLocalRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbRefEClass, 
		   source, 
		   new String[] {
			 "name", "ejb-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_EjbRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_EjbRefType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-ref-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_Home(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "home", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_Remote(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "remote", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_EjbLink(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "ejb-link", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEjbRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEjbRef_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEjbRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbRefNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "ejb-ref-nameType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (ejbRefTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "ejb-ref-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (ejbRefTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "ejb-ref-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "ejb-ref-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (emptyTypeEClass, 
		   source, 
		   new String[] {
			 "name", "emptyType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEmptyType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (envEntryEClass, 
		   source, 
		   new String[] {
			 "name", "env-entryType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnvEntry_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEnvEntry_EnvEntryName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEnvEntry_EnvEntryType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEnvEntry_EnvEntryValue(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "env-entry-value", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEnvEntry_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnvEntry_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getEnvEntry_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getEnvEntry_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (envEntryTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "env-entry-type-valuesType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "fully-qualified-classType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (fullyQualifiedClassTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "fully-qualified-classType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (genericBooleanTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "generic-booleanType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (genericBooleanTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "generic-booleanType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "generic-booleanType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (homeEDataType, 
		   source, 
		   new String[] {
			 "name", "homeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (iconEClass, 
		   source, 
		   new String[] {
			 "name", "iconType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getIcon_SmallIcon(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "small-icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getIcon_LargeIcon(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "large-icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getIcon_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getIcon_Lang(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (injectionTargetEClass, 
		   source, 
		   new String[] {
			 "name", "injection-targetType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInjectionTarget_InjectionTargetClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getInjectionTarget_InjectionTargetName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (isolationLevelTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "isolation-levelType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (isolationLevelTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "isolation-levelType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "isolation-levelType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (javaIdentifierEDataType, 
		   source, 
		   new String[] {
			 "name", "java-identifierType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "($|_|\\p{L})(\\p{L}|\\p{Nd}|_|$)*" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (javaTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "java-typeType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "[^\\p{Z}]*" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (jdbcUrlTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "jdbc-urlType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "jdbc:(.*):(.*)" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (jndiNameEDataType, 
		   source, 
		   new String[] {
			 "name", "jndi-nameType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (lifecycleCallbackEClass, 
		   source, 
		   new String[] {
			 "name", "lifecycle-callbackType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLifecycleCallback_LifecycleCallbackClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lifecycle-callback-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getLifecycleCallback_LifecycleCallbackMethod(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lifecycle-callback-method", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (listenerEClass, 
		   source, 
		   new String[] {
			 "name", "listenerType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getListener_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getListener_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getListener_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getListener_ListenerClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "listener-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getListener_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (localEDataType, 
		   source, 
		   new String[] {
			 "name", "localType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (localHomeEDataType, 
		   source, 
		   new String[] {
			 "name", "local-homeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageDestinationEClass, 
		   source, 
		   new String[] {
			 "name", "message-destinationType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestination_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestination_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestination_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDestination_MessageDestinationName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDestination_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDestination_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestination_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageDestinationLinkEDataType, 
		   source, 
		   new String[] {
			 "name", "message-destination-linkType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageDestinationRefEClass, 
		   source, 
		   new String[] {
			 "name", "message-destination-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestinationRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDestinationRef_MessageDestinationRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestinationRef_MessageDestinationType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestinationRef_MessageDestinationUsage(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-usage", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestinationRef_MessageDestinationLink(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "message-destination-link", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDestinationRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestinationRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getMessageDestinationRef_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getMessageDestinationRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageDestinationTypeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "message-destination-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (messageDestinationUsageTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "message-destination-usageType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (messageDestinationUsageTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "message-destination-usageType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "message-destination-usageType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (paramValueEClass, 
		   source, 
		   new String[] {
			 "name", "param-valueType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getParamValue_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getParamValue_ParamName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "param-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getParamValue_ParamValue(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "param-value", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getParamValue_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (pathTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "pathType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (persistenceContextRefEClass, 
		   source, 
		   new String[] {
			 "name", "persistence-context-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceContextRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceContextRef_PersistenceContextRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceContextRef_PersistenceUnitName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceContextRef_PersistenceContextType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-context-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceContextRef_PersistenceProperties(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-property", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceContextRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceContextRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceContextRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (persistenceContextTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "persistence-context-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (persistenceContextTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "persistence-context-typeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "persistence-context-typeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (persistenceUnitRefEClass, 
		   source, 
		   new String[] {
			 "name", "persistence-unit-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceUnitRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceUnitRef_PersistenceUnitRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceUnitRef_PersistenceUnitName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "persistence-unit-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPersistenceUnitRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceUnitRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPersistenceUnitRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (portComponentRefEClass, 
		   source, 
		   new String[] {
			 "name", "port-component-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPortComponentRef_ServiceEndpointInterface(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-endpoint-interface", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPortComponentRef_EnableMtom(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "enable-mtom", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPortComponentRef_MtomThreshold(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mtom-threshold", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPortComponentRef_Addressing(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "addressing", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPortComponentRef_RespectBinding(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "respect-binding", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getPortComponentRef_PortComponentLink(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "port-component-link", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPortComponentRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (propertyTypeEClass, 
		   source, 
		   new String[] {
			 "name", "propertyType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPropertyType_Name(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPropertyType_Value(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getPropertyType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (protocolBindingListTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "protocol-bindingListType", //$NON-NLS-1$ //$NON-NLS-2$
			 "itemType", "protocol-bindingType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (protocolBindingTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "protocol-bindingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "memberTypes", "http://www.eclipse.org/emf/2003/XMLType#anyURI protocol-URIAliasType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (protocolURIAliasTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "protocol-URIAliasType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "##.+" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (qnamePatternEDataType, 
		   source, 
		   new String[] {
			 "name", "qname-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#token", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "\\*|([\\i-[:]][\\c-[:]]*:)?[\\i-[:]][\\c-[:]]*\\*?" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (remoteEDataType, 
		   source, 
		   new String[] {
			 "name", "remoteType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (resAuthTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "res-authType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (resAuthTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "res-authType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "res-authType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (resourceEnvRefEClass, 
		   source, 
		   new String[] {
			 "name", "resource-env-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceEnvRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceEnvRef_ResourceEnvRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceEnvRef_ResourceEnvRefType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resource-env-ref-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceEnvRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceEnvRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceEnvRef_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceEnvRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (resourceRefEClass, 
		   source, 
		   new String[] {
			 "name", "resource-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceRef_ResRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "res-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceRef_ResType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "res-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceRef_ResAuth(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "res-auth", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceRef_ResSharingScope(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "res-sharing-scope", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getResourceRef_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getResourceRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (respectBindingTypeEClass, 
		   source, 
		   new String[] {
			 "name", "respect-bindingType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRespectBindingType_Enabled(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "enabled", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (resSharingScopeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "res-sharing-scopeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (resSharingScopeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "res-sharing-scopeType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "res-sharing-scopeType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (roleNameEDataType, 
		   source, 
		   new String[] {
			 "name", "role-nameType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (runAsEClass, 
		   source, 
		   new String[] {
			 "name", "run-asType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRunAs_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRunAs_RoleName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "role-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getRunAs_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (securityRoleEClass, 
		   source, 
		   new String[] {
			 "name", "security-roleType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityRole_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityRole_RoleName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "role-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityRole_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (securityRoleRefEClass, 
		   source, 
		   new String[] {
			 "name", "security-role-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityRoleRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSecurityRoleRef_RoleName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "role-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getSecurityRoleRef_RoleLink(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "role-link", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getSecurityRoleRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (serviceRefEClass, 
		   source, 
		   new String[] {
			 "name", "service-refType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRef_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRef_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRef_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_ServiceRefName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_ServiceInterface(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-interface", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_ServiceRefType(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-ref-type", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_WsdlFile(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "wsdl-file", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_JaxrpcMappingFile(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "jaxrpc-mapping-file", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_ServiceQname(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-qname", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_PortComponentRefs(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "port-component-ref", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_Handlers(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "handler", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_HandlerChains(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "handler-chains", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_MappedName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "mapped-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRef_InjectionTargets(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "injection-target", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRef_LookupName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "lookup-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRef_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (serviceRefHandlerEClass, 
		   source, 
		   new String[] {
			 "name", "handlerType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandler_Descriptions(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandler_DisplayNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandler_Icons(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRefHandler_HandlerName(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "handler-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRefHandler_HandlerClass(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "handler-class", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRefHandler_InitParams(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "init-param", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRefHandler_SoapHeaders(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "soap-header", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRefHandler_SoapRoles(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "soap-role", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (getServiceRefHandler_PortNames(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "port-name", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandler_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (serviceRefHandlerChainEClass, 
		   source, 
		   new String[] {
			 "name", "handler-chainType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChain_ServiceNamePattern(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "service-name-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChain_PortNamePattern(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "port-name-pattern", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChain_ProtocolBindings(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "protocol-bindings", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChain_Handlers(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "handler", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChain_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (serviceRefHandlerChainsEClass, 
		   source, 
		   new String[] {
			 "name", "handler-chainsType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChains_HandlerChains(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "handler-chain", //$NON-NLS-1$ //$NON-NLS-2$
			 "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getServiceRefHandlerChains_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (trueFalseTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "true-falseType", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#boolean", //$NON-NLS-1$ //$NON-NLS-2$
			 "pattern", "(true|false)" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (trueFalseTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "true-falseType:Object", //$NON-NLS-1$ //$NON-NLS-2$
			 "baseType", "true-falseType" //$NON-NLS-1$ //$NON-NLS-2$
		   });			
		addAnnotation
		  (urlPatternTypeEClass, 
		   source, 
		   new String[] {
			 "name", "url-patternType", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getUrlPatternType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
			 "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //JavaeePackageImpl
