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
package org.eclipse.jst.javaee.core;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Persistence Unit Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * [
 *         The persistence-unit-ref element contains a declaration
 *         of Deployment Component's reference to a persistence unit
 *         associated within a Deployment Component's
 *         environment. It consists of:
 *         
 *         - an optional description
 *         - the persistence unit reference name
 *         - an optional persistence unit name.  If not specified,
 *         the default persistence unit is assumed.
 *         - optional injection targets
 *         
 *         Examples:
 *         
 *         <persistence-unit-ref>
 *         <persistence-unit-ref-name>myPersistenceUnit
 *         </persistence-unit-ref-name>
 *         </persistence-unit-ref>
 *         
 *         <persistence-unit-ref>
 *         <persistence-unit-ref-name>myPersistenceUnit
 *         </persistence-unit-ref-name>
 *         <persistence-unit-name>PersistenceUnit1
 *         </persistence-unit-name>
 *         </persistence-unit-ref>
 *         
 * 
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getPersistenceUnitRefName <em>Persistence Unit Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getPersistenceUnitName <em>Persistence Unit Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef()
 * @extends JavaEEObject
 * @generated
 */
public interface PersistenceUnitRef extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Persistence Unit Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The persistence-unit-ref-name element specifies
	 *             the name of a persistence unit reference; its
	 *             value is the environment entry name used in
	 *             Deployment Component code.  The name is a JNDI name
	 *             relative to the java:comp/env context.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Unit Ref Name</em>' attribute.
	 * @see #setPersistenceUnitRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef_PersistenceUnitRefName()
	 * @generated
	 */
	String getPersistenceUnitRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getPersistenceUnitRefName <em>Persistence Unit Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistence Unit Ref Name</em>' attribute.
	 * @see #getPersistenceUnitRefName()
	 * @generated
	 */
	void setPersistenceUnitRefName(String value);

	/**
	 * Returns the value of the '<em><b>Persistence Unit Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The Application Assembler(or BeanProvider) may use the
	 *             following syntax to avoid the need to rename persistence
	 *             units to have unique names within a Java EE application.
	 *             
	 *             The Application Assembler specifies the pathname of the
	 *             root of the persistence.xml file for the referenced
	 *             persistence unit and appends the name of the persistence
	 *             unit separated from the pathname by #. The pathname is
	 *             relative to the referencing application component jar file. 
	 *             In this manner, multiple persistence units with the same
	 *             persistence unit name may be uniquely identified when the 
	 *             Application Assembler cannot change persistence unit names.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Unit Name</em>' attribute.
	 * @see #setPersistenceUnitName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef_PersistenceUnitName()
	 * @generated
	 */
	String getPersistenceUnitName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getPersistenceUnitName <em>Persistence Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistence Unit Name</em>' attribute.
	 * @see #getPersistenceUnitName()
	 * @generated
	 */
	void setPersistenceUnitName(String value);

	/**
	 * Returns the value of the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             A product specific name that this resource should be
	 *             mapped to.  The name of this resource, as defined by the
	 *             resource's name element or defaulted, is a name that is
	 *             local to the application component using the resource.
	 *             (It's a name in the JNDI java:comp/env namespace.)  Many
	 *             application servers provide a way to map these local
	 *             names to names of resources known to the application
	 *             server.  This mapped name is often a global JNDI name,
	 *             but may be a name of any form.
	 *             
	 *             Application servers are not required to support any
	 *             particular form or type of mapped name, nor the ability
	 *             to use mapped names.  The mapped name is
	 *             product-dependent and often installation-dependent.  No
	 *             use of a mapped name is portable.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mapped Name</em>' attribute.
	 * @see #setMappedName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getMappedName <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped Name</em>' attribute.
	 * @see #getMappedName()
	 * @generated
	 */
	void setMappedName(String value);

	/**
	 * Returns the value of the '<em><b>Injection Targets</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.InjectionTarget}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection Targets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Injection Targets</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef_InjectionTargets()
	 * @generated
	 */
	List<InjectionTarget> getInjectionTargets();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceUnitRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceUnitRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // PersistenceUnitRef