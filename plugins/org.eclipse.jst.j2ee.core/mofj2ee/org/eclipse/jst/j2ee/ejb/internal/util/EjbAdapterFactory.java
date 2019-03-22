/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.util;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.J2EEEAttribute;
import org.eclipse.jst.j2ee.common.J2EEEObject;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBMethodCategory;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.Session;

public class EjbAdapterFactory extends AdapterFactoryImpl {
	protected static EjbPackage modelPackage;
	public EjbAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = (EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI);
		}
	}
	@Override
	public boolean isFactoryForType(Object type) {
		if (type == modelPackage) {
			return true;
		}
		if (type instanceof EObject) {
			return ((EObject) type).eClass().eContainer() == modelPackage;
		}
		return false;
	}
	protected EjbSwitch sw = new EjbSwitch() {
		@Override
		public Object caseMethodPermission(MethodPermission object) {
			return createMethodPermissionAdapter();
		}
		@Override
		public Object caseAssemblyDescriptor(AssemblyDescriptor object) {
			return createAssemblyDescriptorAdapter();
		}
		@Override
		public Object caseMethodTransaction(MethodTransaction object) {
			return createMethodTransactionAdapter();
		}
		@Override
		public Object caseEnterpriseBean(EnterpriseBean object) {
			return createEnterpriseBeanAdapter();
		}
		@Override
		public Object caseEntity(Entity object) {
			return createEntityAdapter();
		}
		@Override
		public Object caseContainerManagedEntity(ContainerManagedEntity object) {
			return createContainerManagedEntityAdapter();
		}
		@Override
		public Object caseCMPAttribute(CMPAttribute object) {
			return createCMPAttributeAdapter();
		}
		@Override
		public Object caseCMRField(CMRField object) {
			return createCMRFieldAdapter();
		}
		@Override
		public Object caseEJBRelationshipRole(EJBRelationshipRole object) {
			return createEJBRelationshipRoleAdapter();
		}
		@Override
		public Object caseEJBRelation(EJBRelation object) {
			return createEJBRelationAdapter();
		}
		@Override
		public Object caseRelationships(Relationships object) {
			return createRelationshipsAdapter();
		}
		@Override
		public Object caseEJBJar(EJBJar object) {
			return createEJBJarAdapter();
		}
		@Override
		public Object caseRoleSource(RoleSource object) {
			return createRoleSourceAdapter();
		}
		@Override
		public Object caseQuery(Query object) {
			return createQueryAdapter();
		}
		@Override
		public Object caseQueryMethod(QueryMethod object) {
			return createQueryMethodAdapter();
		}
		@Override
		public Object caseMethodElement(MethodElement object) {
			return createMethodElementAdapter();
		}
		@Override
		public Object caseExcludeList(ExcludeList object) {
			return createExcludeListAdapter();
		}
		@Override
		public Object caseSession(Session object) {
			return createSessionAdapter();
		}
		@Override
		public Object caseMessageDriven(MessageDriven object) {
			return createMessageDrivenAdapter();
		}
		@Override
		public Object caseMessageDrivenDestination(MessageDrivenDestination object) {
			return createMessageDrivenDestinationAdapter();
		}
		@Override
		public Object caseActivationConfig(ActivationConfig object) {
			return createActivationConfigAdapter();
		}
		@Override
		public Object caseActivationConfigProperty(ActivationConfigProperty object) {
			return createActivationConfigPropertyAdapter();
		}
		@Override
		public Object caseEJBMethodCategory(EJBMethodCategory object) {
			return createEJBMethodCategoryAdapter();
		}
		@Override
		public Object caseEAttribute(EAttribute object) {
			return createEAttributeAdapter();
		}
		@Override
		public Object caseEStructuralFeature(EStructuralFeature object) {
			return createEStructuralFeatureAdapter();
		}
		@Override
		public Object caseEModelElement(EModelElement object) {
			return createEModelElementAdapter();
		}
		@Override
		public Object caseENamedElement(ENamedElement object) {
			return createENamedElementAdapter();
		}
	};
	@Override
	public Adapter createAdapter(Notifier target) {
		return (Adapter) sw.doSwitch((EObject) target);
	}
	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 */
	public Adapter createMethodPermissionAdapter() {
		return null;
	}
	public Adapter createAssemblyDescriptorAdapter() {
		return null;
	}
	public Adapter createMethodTransactionAdapter() {
		return null;
	}
	public Adapter createEnterpriseBeanAdapter() {
		return null;
	}
	public Adapter createEntityAdapter() {
		return null;
	}
	public Adapter createContainerManagedEntityAdapter() {
		return null;
	}
	public Adapter createCMPAttributeAdapter() {
		return null;
	}
	public Adapter createCMRFieldAdapter() {
		return null;
	}
	public Adapter createEJBRelationshipRoleAdapter() {
		return null;
	}
	public Adapter createEJBRelationAdapter() {
		return null;
	}
	public Adapter createRelationshipsAdapter() {
		return null;
	}
	public Adapter createEJBJarAdapter() {
		return null;
	}
	public Adapter createRoleSourceAdapter() {
		return null;
	}
	public Adapter createQueryAdapter() {
		return null;
	}
	public Adapter createQueryMethodAdapter() {
		return null;
	}
	public Adapter createMethodElementAdapter() {
		return null;
	}
	public Adapter createExcludeListAdapter() {
		return null;
	}
	public Adapter createSessionAdapter() {
		return null;
	}
	public Adapter createMessageDrivenAdapter() {
		return null;
	}
	public Adapter createMessageDrivenDestinationAdapter() {
		return null;
	}
	public Adapter createEJBMethodCategoryAdapter() {
		return null;
	}
	public Adapter createEClassAdapter() {
		return null;
	}
	public Adapter createEAttributeAdapter() {
		return null;
	}
	public Adapter createEStructuralFeatureAdapter() {
		return null;
	}
	public Adapter createEClassifierAdapter() {
		return null;
	}
	public Adapter createEModelElementAdapter() {
		return null;
	}
	public Adapter createENamedElementAdapter() {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEObjectAdapter() {
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
	protected EjbSwitch modelSwitch =
		new EjbSwitch() {
			@Override
			public Object caseContainerManagedEntity(ContainerManagedEntity object) {
				return createContainerManagedEntityAdapter();
			}
			@Override
			public Object caseMethodPermission(MethodPermission object) {
				return createMethodPermissionAdapter();
			}
			@Override
			public Object caseAssemblyDescriptor(AssemblyDescriptor object) {
				return createAssemblyDescriptorAdapter();
			}
			@Override
			public Object caseMethodTransaction(MethodTransaction object) {
				return createMethodTransactionAdapter();
			}
			@Override
			public Object caseSession(Session object) {
				return createSessionAdapter();
			}
			@Override
			public Object caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Object caseEnterpriseBean(EnterpriseBean object) {
				return createEnterpriseBeanAdapter();
			}
			@Override
			public Object caseEJBMethodCategory(EJBMethodCategory object) {
				return createEJBMethodCategoryAdapter();
			}
			@Override
			public Object caseEJBJar(EJBJar object) {
				return createEJBJarAdapter();
			}
			@Override
			public Object caseMethodElement(MethodElement object) {
				return createMethodElementAdapter();
			}
			@Override
			public Object caseCMPAttribute(CMPAttribute object) {
				return createCMPAttributeAdapter();
			}
			@Override
			public Object caseRelationships(Relationships object) {
				return createRelationshipsAdapter();
			}
			@Override
			public Object caseQuery(Query object) {
				return createQueryAdapter();
			}
			@Override
			public Object caseEJBRelation(EJBRelation object) {
				return createEJBRelationAdapter();
			}
			@Override
			public Object caseEJBRelationshipRole(EJBRelationshipRole object) {
				return createEJBRelationshipRoleAdapter();
			}
			@Override
			public Object caseRoleSource(RoleSource object) {
				return createRoleSourceAdapter();
			}
			@Override
			public Object caseCMRField(CMRField object) {
				return createCMRFieldAdapter();
			}
			@Override
			public Object caseMessageDriven(MessageDriven object) {
				return createMessageDrivenAdapter();
			}
			@Override
			public Object caseMessageDrivenDestination(MessageDrivenDestination object) {
				return createMessageDrivenDestinationAdapter();
			}
			@Override
			public Object caseExcludeList(ExcludeList object) {
				return createExcludeListAdapter();
			}
			@Override
			public Object caseQueryMethod(QueryMethod object) {
				return createQueryMethodAdapter();
			}
			@Override
			public Object caseActivationConfigProperty(ActivationConfigProperty object) {
				return createActivationConfigPropertyAdapter();
			}
			@Override
			public Object caseActivationConfig(ActivationConfig object) {
				return createActivationConfigAdapter();
			}
			@Override
			public Object caseJ2EEEObject(J2EEEObject object) {
				return createJ2EEEObjectAdapter();
			}
			@Override
			public Object caseDescriptionGroup(DescriptionGroup object) {
				return createDescriptionGroupAdapter();
			}
			@Override
			public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
				return createCompatibilityDescriptionGroupAdapter();
			}
			@Override
			public Object caseJNDIEnvRefsGroup(JNDIEnvRefsGroup object) {
				return createJNDIEnvRefsGroupAdapter();
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
			public Object caseJ2EEEAttribute(J2EEEAttribute object) {
				return createJ2EEEAttributeAdapter();
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
	 * It's useful to ignore a case when inheritance will catch all the cases
	 * anyway.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createMethodPermissionAdapterGen() {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAssemblyDescriptorAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createMethodTransactionAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEnterpriseBeanAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEntityAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createContainerManagedEntityAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createCMPAttributeAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createCMRFieldAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBRelationshipRoleAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBRelationAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createRelationshipsAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBJarAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createRoleSourceAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createQueryAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createQueryMethodAdapterGen() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.ejb.ActivationConfigProperty <em>Activation Config Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfigProperty
	 * @generated
	 */
	public Adapter createActivationConfigPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.ejb.ActivationConfig <em>Activation Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.ejb.ActivationConfig
	 * @generated
	 */
	public Adapter createActivationConfigAdapter() {
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
	public Adapter createMethodElementAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createExcludeListAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSessionAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createMessageDrivenAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createMessageDrivenDestinationAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBMethodCategoryAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEAttributeAdapterGen() {
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
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEStructuralFeatureAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEModelElementAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createENamedElementAdapterGen() {
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

} //EjbAdapterFactory
