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
package org.eclipse.jst.j2ee.ejb;
 
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
/**
 * @generated
 */
public interface EjbFactory extends EFactory{

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EjbFactory eINSTANCE = new org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl();

MethodElement createMethodElement(String aSignature) ;
MethodElement createMethodElementFrom(Method method) ;
MethodElement createMethodElementFrom(java.lang.reflect.Method method) ;
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodPermission value
	 */
	MethodPermission createMethodPermission();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return AssemblyDescriptor value
	 */
	AssemblyDescriptor createAssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodTransaction value
	 */
	MethodTransaction createMethodTransaction();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Entity value
	 */
	Entity createEntity();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ContainerManagedEntity value
	 */
	ContainerManagedEntity createContainerManagedEntity();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return CMPAttribute value
	 */
	CMPAttribute createCMPAttribute();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return CMRField value
	 */
	CMRField createCMRField();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBRelationshipRole value
	 */
	EJBRelationshipRole createEJBRelationshipRole();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBRelation value
	 */
	EJBRelation createEJBRelation();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Relationships value
	 */
	Relationships createRelationships();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBJar value
	 */
	EJBJar createEJBJar();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return RoleSource value
	 */
	RoleSource createRoleSource();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Query value
	 */
	Query createQuery();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return QueryMethod value
	 */
	QueryMethod createQueryMethod();

	/**
	 * Returns a new object of class '<em>Activation Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Config Property</em>'.
	 * @generated
	 */
	ActivationConfigProperty createActivationConfigProperty();

	/**
	 * Returns a new object of class '<em>Activation Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Config</em>'.
	 * @generated
	 */
	ActivationConfig createActivationConfig();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MethodElement value
	 */
	MethodElement createMethodElement();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ExcludeList value
	 */
	ExcludeList createExcludeList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Session value
	 */
	Session createSession();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MessageDriven value
	 */
	MessageDriven createMessageDriven();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return MessageDrivenDestination value
	 */
	MessageDrivenDestination createMessageDrivenDestination();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBMethodCategory value
	 */
	EJBMethodCategory createEJBMethodCategory();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EjbPackage getEjbPackage();

}








