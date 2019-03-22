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
 * A representation of the model object '<em><b>Persistence Context Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * [
 *         The persistence-context-ref element contains a declaration
 *         of Deployment Component's reference to a persistence context
 *         associated within a Deployment Component's
 *         environment. It consists of:
 *         
 *         - an optional description
 *         - the persistence context reference name
 *         - an optional persistence unit name.  If not specified,
 *         the default persistence unit is assumed.
 *         - an optional specification as to whether
 *         the persistence context type is Transaction or
 *         Extended.  If not specified, Transaction is assumed.
 *         - an optional specification as to whether
 *         the persistence context synchronization with the current
 *         transaction is Synchronized or Unsynchronized. If not
 *         specified, Synchronized is assumed.
 *         - an optional list of persistence properties
 *         - optional injection targets
 *         
 *         Examples:
 *         
 *         <persistence-context-ref>
 *         <persistence-context-ref-name>myPersistenceContext
 *         </persistence-context-ref-name>
 *         </persistence-context-ref>
 *         
 *         <persistence-context-ref>
 *         <persistence-context-ref-name>myPersistenceContext
 *         </persistence-context-ref-name>
 *         <persistence-unit-name>PersistenceUnit1
 *         </persistence-unit-name>
 *         <persistence-context-type>Extended</persistence-context-type>
 *         </persistence-context-ref>
 *         
 * 
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextRefName <em>Persistence Context Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceUnitName <em>Persistence Unit Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextType <em>Persistence Context Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextSynchronization <em>Persistence Context Synchronization</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceProperties <em>Persistence Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef()
 * @extends JavaEEObject
 * @generated
 */
public interface PersistenceContextRef extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Persistence Context Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The persistence-context-ref-name element specifies
	 *             the name of a persistence context reference; its
	 *             value is the environment entry name used in
	 *             Deployment Component code.  The name is a JNDI name
	 *             relative to the java:comp/env context.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Context Ref Name</em>' attribute.
	 * @see #setPersistenceContextRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_PersistenceContextRefName()
	 * @generated
	 */
	String getPersistenceContextRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextRefName <em>Persistence Context Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistence Context Ref Name</em>' attribute.
	 * @see #getPersistenceContextRefName()
	 * @generated
	 */
	void setPersistenceContextRefName(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_PersistenceUnitName()
	 * @generated
	 */
	String getPersistenceUnitName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceUnitName <em>Persistence Unit Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistence Unit Name</em>' attribute.
	 * @see #getPersistenceUnitName()
	 * @generated
	 */
	void setPersistenceUnitName(String value);

	/**
	 * Returns the value of the '<em><b>Persistence Context Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.PersistenceContextType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persistence Context Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persistence Context Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.PersistenceContextType
	 * @see #isSetPersistenceContextType()
	 * @see #unsetPersistenceContextType()
	 * @see #setPersistenceContextType(PersistenceContextType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_PersistenceContextType()
	 * @generated
	 */
	PersistenceContextType getPersistenceContextType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextType <em>Persistence Context Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistence Context Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.PersistenceContextType
	 * @see #isSetPersistenceContextType()
	 * @see #unsetPersistenceContextType()
	 * @see #getPersistenceContextType()
	 * @generated
	 */
	void setPersistenceContextType(PersistenceContextType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextType <em>Persistence Context Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPersistenceContextType()
	 * @see #getPersistenceContextType()
	 * @see #setPersistenceContextType(PersistenceContextType)
	 * @generated
	 */
	void unsetPersistenceContextType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextType <em>Persistence Context Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Persistence Context Type</em>' attribute is set.
	 * @see #unsetPersistenceContextType()
	 * @see #getPersistenceContextType()
	 * @see #setPersistenceContextType(PersistenceContextType)
	 * @generated
	 */
	boolean isSetPersistenceContextType();

	/**
	 * Returns the value of the '<em><b>Persistence Context Synchronization</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.PersistenceContextSynchronizationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 7
	 *          
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Context Synchronization</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.PersistenceContextSynchronizationType
	 * @see #isSetPersistenceContextSynchronization()
	 * @see #unsetPersistenceContextSynchronization()
	 * @see #setPersistenceContextSynchronization(PersistenceContextSynchronizationType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_PersistenceContextSynchronization()
	 * @generated
	 */
	PersistenceContextSynchronizationType getPersistenceContextSynchronization();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextSynchronization <em>Persistence Context Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persistence Context Synchronization</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.PersistenceContextSynchronizationType
	 * @see #isSetPersistenceContextSynchronization()
	 * @see #unsetPersistenceContextSynchronization()
	 * @see #getPersistenceContextSynchronization()
	 * @generated
	 */
	void setPersistenceContextSynchronization(PersistenceContextSynchronizationType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextSynchronization <em>Persistence Context Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPersistenceContextSynchronization()
	 * @see #getPersistenceContextSynchronization()
	 * @see #setPersistenceContextSynchronization(PersistenceContextSynchronizationType)
	 * @generated
	 */
	void unsetPersistenceContextSynchronization();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getPersistenceContextSynchronization <em>Persistence Context Synchronization</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Persistence Context Synchronization</em>' attribute is set.
	 * @see #unsetPersistenceContextSynchronization()
	 * @see #getPersistenceContextSynchronization()
	 * @see #setPersistenceContextSynchronization(PersistenceContextSynchronizationType)
	 * @generated
	 */
	boolean isSetPersistenceContextSynchronization();

	/**
	 * Returns the value of the '<em><b>Persistence Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PropertyType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Used to specify properties for the container or persistence
	 *             provider.  Vendor-specific properties may be included in
	 *             the set of properties.  Properties that are not recognized
	 *             by a vendor must be ignored.  Entries that make use of the 
	 *             namespace javax.persistence and its subnamespaces must not
	 *             be used for vendor-specific properties.  The namespace
	 *             javax.persistence is reserved for use by the specification.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persistence Properties</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_PersistenceProperties()
	 * @generated
	 */
	List<PropertyType> getPersistenceProperties();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getMappedName <em>Mapped Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_InjectionTargets()
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPersistenceContextRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PersistenceContextRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // PersistenceContextRef