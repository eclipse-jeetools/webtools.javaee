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
package org.eclipse.jst.j2ee.internal.ejb.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
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
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage
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
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case EjbPackage.CONTAINER_MANAGED_ENTITY: {
					ContainerManagedEntity containerManagedEntity = (ContainerManagedEntity)theEObject;
					Object result = caseContainerManagedEntity(containerManagedEntity);
					if (result == null) result = caseEntity(containerManagedEntity);
					if (result == null) result = caseEnterpriseBean(containerManagedEntity);
					if (result == null) result = caseJNDIEnvRefsGroup(containerManagedEntity);
					if (result == null) result = caseCompatibilityDescriptionGroup(containerManagedEntity);
					if (result == null) result = caseDescriptionGroup(containerManagedEntity);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.METHOD_PERMISSION: {
					MethodPermission methodPermission = (MethodPermission)theEObject;
					Object result = caseMethodPermission(methodPermission);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.ASSEMBLY_DESCRIPTOR: {
					AssemblyDescriptor assemblyDescriptor = (AssemblyDescriptor)theEObject;
					Object result = caseAssemblyDescriptor(assemblyDescriptor);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.METHOD_TRANSACTION: {
					MethodTransaction methodTransaction = (MethodTransaction)theEObject;
					Object result = caseMethodTransaction(methodTransaction);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.SESSION: {
					Session session = (Session)theEObject;
					Object result = caseSession(session);
					if (result == null) result = caseEnterpriseBean(session);
					if (result == null) result = caseJNDIEnvRefsGroup(session);
					if (result == null) result = caseCompatibilityDescriptionGroup(session);
					if (result == null) result = caseDescriptionGroup(session);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.ENTITY: {
					Entity entity = (Entity)theEObject;
					Object result = caseEntity(entity);
					if (result == null) result = caseEnterpriseBean(entity);
					if (result == null) result = caseJNDIEnvRefsGroup(entity);
					if (result == null) result = caseCompatibilityDescriptionGroup(entity);
					if (result == null) result = caseDescriptionGroup(entity);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.EJB_METHOD_CATEGORY: {
					EJBMethodCategory ejbMethodCategory = (EJBMethodCategory)theEObject;
					Object result = caseEJBMethodCategory(ejbMethodCategory);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.EJB_JAR: {
					EJBJar ejbJar = (EJBJar)theEObject;
					Object result = caseEJBJar(ejbJar);
					if (result == null) result = caseCompatibilityDescriptionGroup(ejbJar);
					if (result == null) result = caseDescriptionGroup(ejbJar);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.METHOD_ELEMENT: {
					MethodElement methodElement = (MethodElement)theEObject;
					Object result = caseMethodElement(methodElement);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.CMP_ATTRIBUTE: {
					CMPAttribute cmpAttribute = (CMPAttribute)theEObject;
					Object result = caseCMPAttribute(cmpAttribute);
					if (result == null) result = caseEAttribute(cmpAttribute);
					if (result == null) result = caseEStructuralFeature(cmpAttribute);
					if (result == null) result = caseETypedElement(cmpAttribute);
					if (result == null) result = caseENamedElement(cmpAttribute);
					if (result == null) result = caseEModelElement(cmpAttribute);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.RELATIONSHIPS: {
					Relationships relationships = (Relationships)theEObject;
					Object result = caseRelationships(relationships);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.QUERY: {
					Query query = (Query)theEObject;
					Object result = caseQuery(query);
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
				case EjbPackage.ROLE_SOURCE: {
					RoleSource roleSource = (RoleSource)theEObject;
					Object result = caseRoleSource(roleSource);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.CMR_FIELD: {
					CMRField cmrField = (CMRField)theEObject;
					Object result = caseCMRField(cmrField);
					if (result == null) result = caseCMPAttribute(cmrField);
					if (result == null) result = caseEAttribute(cmrField);
					if (result == null) result = caseEStructuralFeature(cmrField);
					if (result == null) result = caseETypedElement(cmrField);
					if (result == null) result = caseENamedElement(cmrField);
					if (result == null) result = caseEModelElement(cmrField);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.MESSAGE_DRIVEN: {
					MessageDriven messageDriven = (MessageDriven)theEObject;
					Object result = caseMessageDriven(messageDriven);
					if (result == null) result = caseEnterpriseBean(messageDriven);
					if (result == null) result = caseJNDIEnvRefsGroup(messageDriven);
					if (result == null) result = caseCompatibilityDescriptionGroup(messageDriven);
					if (result == null) result = caseDescriptionGroup(messageDriven);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.MESSAGE_DRIVEN_DESTINATION: {
					MessageDrivenDestination messageDrivenDestination = (MessageDrivenDestination)theEObject;
					Object result = caseMessageDrivenDestination(messageDrivenDestination);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.EXCLUDE_LIST: {
					ExcludeList excludeList = (ExcludeList)theEObject;
					Object result = caseExcludeList(excludeList);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.QUERY_METHOD: {
					QueryMethod queryMethod = (QueryMethod)theEObject;
					Object result = caseQueryMethod(queryMethod);
					if (result == null) result = caseMethodElement(queryMethod);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.ACTIVATION_CONFIG_PROPERTY: {
					ActivationConfigProperty activationConfigProperty = (ActivationConfigProperty)theEObject;
					Object result = caseActivationConfigProperty(activationConfigProperty);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case EjbPackage.ACTIVATION_CONFIG: {
					ActivationConfig activationConfig = (ActivationConfig)theEObject;
					Object result = caseActivationConfig(activationConfig);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Container Managed Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Container Managed Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseContainerManagedEntity(ContainerManagedEntity object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>Method Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Method Transaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethodTransaction(MethodTransaction object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Session</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSession(Session object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Enterprise Bean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Enterprise Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEnterpriseBean(EnterpriseBean object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EJB Method Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Method Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEJBMethodCategory(EJBMethodCategory object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>Method Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Method Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethodElement(MethodElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>CMP Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>CMP Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCMPAttribute(CMPAttribute object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>Role Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Role Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRoleSource(RoleSource object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>Message Driven</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Driven</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageDriven(MessageDriven object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Message Driven Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Message Driven Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMessageDrivenDestination(MessageDrivenDestination object) {
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
	 * Returns the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDescriptionGroup(DescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>JNDI Env Refs Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>JNDI Env Refs Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseJNDIEnvRefsGroup(JNDIEnvRefsGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>ENamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>ENamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseENamedElement(ENamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>ETyped Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>ETyped Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseETypedElement(ETypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EStructural Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EStructural Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEStructuralFeature(EStructuralFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EAttribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EAttribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEAttribute(EAttribute object) {
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
