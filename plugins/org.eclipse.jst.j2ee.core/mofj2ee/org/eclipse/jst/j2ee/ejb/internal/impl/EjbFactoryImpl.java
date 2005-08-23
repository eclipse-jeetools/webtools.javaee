/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBMethodCategory;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.MultiplicityKind;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.ejb.ReturnTypeMapping;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.jst.j2ee.ejb.TransactionAttributeType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.internal.util.CMPKeySynchronizationAdapter;
import org.eclipse.wst.common.internal.emf.utilities.AdapterFactoryDescriptor;
import org.eclipse.wst.common.internal.emf.utilities.AdapterFactoryUtil;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;



/**
 * @generated
 */
public class EjbFactoryImpl extends EFactoryImpl implements EjbFactory{
	private static List CMP_ADAPTER_FACTORIES = new ArrayList(1);
	private static List CMP_ADAPTER_FACTORY_DESCRIPTORS = new ArrayList(1);
	private static List EJB_RELATION_ADAPTER_FACTORIES = new ArrayList(1);
	private static List EJB_RELATION_ADAPTER_FACTORY_DESCRIPTORS = new ArrayList(1);
	private static List RELATIONSHIPS_ADAPTER_FACTORIES = new ArrayList(1);
	private static List RELATIONSHIPS_ADAPTER_FACTORY_DESCRIPTORS = new ArrayList(1);
	public static final String MM_VERSION = "1.1";  //$NON-NLS-1$
	private boolean createdAdapterFactories = false;

	public EjbFactoryImpl() {
		super(); 
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EjbPackage.CONTAINER_MANAGED_ENTITY: return createContainerManagedEntity();
			case EjbPackage.METHOD_PERMISSION: return createMethodPermission();
			case EjbPackage.ASSEMBLY_DESCRIPTOR: return createAssemblyDescriptor();
			case EjbPackage.METHOD_TRANSACTION: return createMethodTransaction();
			case EjbPackage.SESSION: return createSession();
			case EjbPackage.ENTITY: return createEntity();
			case EjbPackage.EJB_METHOD_CATEGORY: return createEJBMethodCategory();
			case EjbPackage.EJB_JAR: return createEJBJar();
			case EjbPackage.METHOD_ELEMENT: return createMethodElement();
			case EjbPackage.CMP_ATTRIBUTE: return createCMPAttribute();
			case EjbPackage.RELATIONSHIPS: return createRelationships();
			case EjbPackage.QUERY: return createQuery();
			case EjbPackage.EJB_RELATION: return createEJBRelation();
			case EjbPackage.EJB_RELATIONSHIP_ROLE: return createEJBRelationshipRole();
			case EjbPackage.ROLE_SOURCE: return createRoleSource();
			case EjbPackage.CMR_FIELD: return createCMRField();
			case EjbPackage.MESSAGE_DRIVEN: return createMessageDriven();
			case EjbPackage.MESSAGE_DRIVEN_DESTINATION: return createMessageDrivenDestination();
			case EjbPackage.EXCLUDE_LIST: return createExcludeList();
			case EjbPackage.QUERY_METHOD: return createQueryMethod();
			case EjbPackage.ACTIVATION_CONFIG_PROPERTY: return createActivationConfigProperty();
			case EjbPackage.ACTIVATION_CONFIG: return createActivationConfig();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case EjbPackage.TRANSACTION_ATTRIBUTE_TYPE: {
				TransactionAttributeType result = TransactionAttributeType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.TRANSACTION_TYPE: {
				TransactionType result = TransactionType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.SESSION_TYPE: {
				SessionType result = SessionType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.METHOD_ELEMENT_KIND: {
				MethodElementKind result = MethodElementKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.ACKNOWLEDGE_MODE: {
				AcknowledgeMode result = AcknowledgeMode.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.SUBSCRIPTION_DURABILITY_KIND: {
				SubscriptionDurabilityKind result = SubscriptionDurabilityKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.RETURN_TYPE_MAPPING: {
				ReturnTypeMapping result = ReturnTypeMapping.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.DESTINATION_TYPE: {
				DestinationType result = DestinationType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case EjbPackage.MULTIPLICITY_KIND: {
				MultiplicityKind result = MultiplicityKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case EjbPackage.TRANSACTION_ATTRIBUTE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.TRANSACTION_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.SESSION_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.METHOD_ELEMENT_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.ACKNOWLEDGE_MODE:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.SUBSCRIPTION_DURABILITY_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.RETURN_TYPE_MAPPING:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.DESTINATION_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case EjbPackage.MULTIPLICITY_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

public MethodElement createMethodElement(String aSignature) {
	MethodElement me = createMethodElement();
	me.initializeFromSignature(aSignature);
	return me;
}
/**
 * Helper method to create a MethodElement from a java method.   Defaults the type to "Unspecified".
 */
public MethodElement createMethodElementFrom(
	Method method) {

	MethodElement element = createMethodElement();
	element.setName(method.getName());
	element.setType(MethodElementKind.UNSPECIFIED_LITERAL);

	JavaParameter[] params = 
		method.listParametersWithoutReturn();
	if (params.length == 0)
		element.applyZeroParams();
	else { 
		for (int i = 0; i < params.length; i++) {
			JavaHelpers helper = (JavaHelpers) params[i].getEType();
			element.addMethodParams(helper.getQualifiedName());
		}
	}
	return element;
} 
/**
 * Helper method to reflect the method and create a MethodElement.  If the parameter is null, creates an element
 * with a name of "*".  Defaults the type to "Unspecified".
 */
public MethodElement createMethodElementFrom(java.lang.reflect.Method method) {

	String methodName = method == null ? "*" : method.getName(); //$NON-NLS-1$
	MethodElement me = createMethodElement();
	me.setName(methodName);
	me.setType(MethodElementKind.UNSPECIFIED_LITERAL);
	if (method != null) {
		Class[] types = method.getParameterTypes();
		for (int i = 0; i < types.length; i++) {
			me.addMethodParams(getTypeName(types[i]));
		}
	}
	return me;
}
public static EjbFactory getActiveFactory() {
	return (EjbFactory) getPackage().getEFactoryInstance();
}
/*
 * Utility routine to paper over array type names
 * Borrowed from a package-visibility helper on java.lang.reflect.Field
 */
static String getTypeName(Class type) {
	if (type.isArray()) {
		try {
			Class cl = type;
			int dimensions = 0;
			while (cl.isArray()) {
				dimensions++;
				cl = cl.getComponentType();
			}
			StringBuffer sb = new StringBuffer();
			sb.append(cl.getName());
			for (int i = 0; i < dimensions; i++) {
				sb.append("[]");//$NON-NLS-1$
			}
			return sb.toString();
		} catch (Throwable e) { /*FALLTHRU*/
		}
	}
	return type.getName();
}


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public MethodPermission createMethodPermission() {
		MethodPermissionImpl methodPermission = new MethodPermissionImpl();
		return methodPermission;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public AssemblyDescriptor createAssemblyDescriptor() {
		AssemblyDescriptorImpl assemblyDescriptor = new AssemblyDescriptorImpl();
		return assemblyDescriptor;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public MethodTransaction createMethodTransaction() {
		MethodTransactionImpl methodTransaction = new MethodTransactionImpl();
		return methodTransaction;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Entity createEntity() {
		EntityImpl entity = new EntityImpl();
		return entity;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ContainerManagedEntity createContainerManagedEntityGen() {
		ContainerManagedEntityImpl containerManagedEntity = new ContainerManagedEntityImpl();
		return containerManagedEntity;
	}

  	public ContainerManagedEntity createContainerManagedEntity() {
  		ContainerManagedEntity cmp = createContainerManagedEntityGen();
  		adaptNew(cmp, CMP_ADAPTER_FACTORIES);
  		addKeySynchronizationAdapter(cmp);
  		return cmp;
  	}
  	
	/**
	 * @param cmp
	 */
	private void addKeySynchronizationAdapter(ContainerManagedEntity cmp) {
		CMPKeySynchronizationAdapter cmpAdapter = new CMPKeySynchronizationAdapter(cmp);
		Adapter loadingAdapter = ExtendedEcoreUtil.createAdapterForLoading(cmpAdapter,cmp);
		cmp.eAdapters().add(loadingAdapter);
	}
	
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public CMPAttribute createCMPAttribute() {
		CMPAttributeImpl cmpAttribute = new CMPAttributeImpl();
		return cmpAttribute;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public CMRField createCMRField() {
		CMRFieldImpl cmrField = new CMRFieldImpl();
		return cmrField;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EJBRelationshipRole createEJBRelationshipRole() {
		EJBRelationshipRoleImpl ejbRelationshipRole = new EJBRelationshipRoleImpl();
		return ejbRelationshipRole;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EJBRelation createEJBRelationGen() {
		EJBRelationImpl ejbRelation = new EJBRelationImpl();
		return ejbRelation;
	}

  	public EJBRelation createEJBRelation() {
  		EJBRelation rel = createEJBRelationGen();
  		adaptNew(rel, EJB_RELATION_ADAPTER_FACTORIES);
  		return rel;
  	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Relationships createRelationshipsGen() {
		RelationshipsImpl relationships = new RelationshipsImpl();
		return relationships;
	}

	public Relationships createRelationships() {
		Relationships rel = createRelationshipsGen();
		adaptNew(rel, RELATIONSHIPS_ADAPTER_FACTORIES);
		return rel;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EJBJar createEJBJar() {
		EJBJarImpl ejbJar = new EJBJarImpl();
		return ejbJar;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public RoleSource createRoleSource() {
		RoleSourceImpl roleSource = new RoleSourceImpl();
		return roleSource;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Query createQuery() {
		QueryImpl query = new QueryImpl();
		return query;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public QueryMethod createQueryMethod() {
		QueryMethodImpl queryMethod = new QueryMethodImpl();
		return queryMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivationConfigProperty createActivationConfigProperty() {
		ActivationConfigPropertyImpl activationConfigProperty = new ActivationConfigPropertyImpl();
		return activationConfigProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivationConfig createActivationConfig() {
		ActivationConfigImpl activationConfig = new ActivationConfigImpl();
		return activationConfig;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public MethodElement createMethodElement() {
		MethodElementImpl methodElement = new MethodElementImpl();
		return methodElement;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ExcludeList createExcludeList() {
		ExcludeListImpl excludeList = new ExcludeListImpl();
		return excludeList;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Session createSession() {
		SessionImpl session = new SessionImpl();
		return session;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public MessageDriven createMessageDriven() {
		MessageDrivenImpl messageDriven = new MessageDrivenImpl();
		return messageDriven;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public MessageDrivenDestination createMessageDrivenDestination() {
		MessageDrivenDestinationImpl messageDrivenDestination = new MessageDrivenDestinationImpl();
		return messageDrivenDestination;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EJBMethodCategory createEJBMethodCategory() {
		EJBMethodCategoryImpl ejbMethodCategory = new EJBMethodCategoryImpl();
		return ejbMethodCategory;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EjbPackage getEjbPackage() {
		return (EjbPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static EjbPackage getPackage() {
		return EjbPackage.eINSTANCE;
	}

	public static void internalRegisterCMPAdapterFactory(AdapterFactoryDescriptor config) {
		CMP_ADAPTER_FACTORY_DESCRIPTORS.add(config);
		
	}
	public static void internalRegisterEJBRelationAdapterFactory(AdapterFactoryDescriptor config) {
		EJB_RELATION_ADAPTER_FACTORY_DESCRIPTORS.add(config);
	}
	public static void internalRegisterRelationshipsAdapterFactory(AdapterFactoryDescriptor config) {
		RELATIONSHIPS_ADAPTER_FACTORY_DESCRIPTORS.add(config);
	}
	
	
	private void createAdapterFactories() {
		createdAdapterFactories = true;
		AdapterFactoryUtil.createAdapterFactories(CMP_ADAPTER_FACTORY_DESCRIPTORS, CMP_ADAPTER_FACTORIES);
		AdapterFactoryUtil.createAdapterFactories(EJB_RELATION_ADAPTER_FACTORY_DESCRIPTORS, EJB_RELATION_ADAPTER_FACTORIES);
		AdapterFactoryUtil.createAdapterFactories(RELATIONSHIPS_ADAPTER_FACTORY_DESCRIPTORS, RELATIONSHIPS_ADAPTER_FACTORIES);
	}
	
	private void adaptNew(Notifier notifier, List factories) {
		if (!createdAdapterFactories)
			createAdapterFactories();
		AdapterFactoryUtil.adaptNew(notifier, factories);
	}

}









