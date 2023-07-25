/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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
 * A representation of the model object '<em><b>Ejb Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The ejb-refType is used by ejb-ref elements for the
 *         declaration of a reference to an enterprise bean's home or
 *         to the remote business interface of a 3.0 bean.  
 *         The declaration consists of:
 *         
 *         - an optional description
 *         - the EJB reference name used in the code of
 *         the Deployment Component that's referencing the enterprise
 *         bean. 
 *         - the optional expected type of the referenced enterprise bean
 *         - the optional remote interface of the referenced enterprise bean
 *         or the remote business interface of the referenced enterprise 
 *         bean
 *         - the optional expected home interface of the referenced 
 *         enterprise bean.  Not applicable if this ejb-ref
 *         refers to the remote business interface of a 3.0 bean.
 *         - optional ejb-link information, used to specify the
 *         referenced enterprise bean
 *         - optional elements to define injection of the named enterprise
 *         bean into a component field or property
 *         
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getEjbRefName <em>Ejb Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getEjbRefType <em>Ejb Ref Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getHome <em>Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getRemote <em>Remote</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getEjbLink <em>Ejb Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EjbRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef()
 * @extends JavaEEObject
 * @generated
 */
public interface EjbRef extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Ejb Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Ref Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Ref Name</em>' attribute.
	 * @see #setEjbRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_EjbRefName()
	 * @generated
	 */
	String getEjbRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getEjbRefName <em>Ejb Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Ref Name</em>' attribute.
	 * @see #getEjbRefName()
	 * @generated
	 */
	void setEjbRefName(String value);

	/**
	 * Returns the value of the '<em><b>Ejb Ref Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.EjbRefType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Ref Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Ref Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.EjbRefType
	 * @see #isSetEjbRefType()
	 * @see #unsetEjbRefType()
	 * @see #setEjbRefType(EjbRefType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_EjbRefType()
	 * @generated
	 */
	EjbRefType getEjbRefType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getEjbRefType <em>Ejb Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Ref Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.EjbRefType
	 * @see #isSetEjbRefType()
	 * @see #unsetEjbRefType()
	 * @see #getEjbRefType()
	 * @generated
	 */
	void setEjbRefType(EjbRefType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getEjbRefType <em>Ejb Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEjbRefType()
	 * @see #getEjbRefType()
	 * @see #setEjbRefType(EjbRefType)
	 * @generated
	 */
	void unsetEjbRefType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getEjbRefType <em>Ejb Ref Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ejb Ref Type</em>' attribute is set.
	 * @see #unsetEjbRefType()
	 * @see #getEjbRefType()
	 * @see #setEjbRefType(EjbRefType)
	 * @generated
	 */
	boolean isSetEjbRefType();

	/**
	 * Returns the value of the '<em><b>Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Home</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Home</em>' attribute.
	 * @see #setHome(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_Home()
	 * @generated
	 */
	String getHome();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getHome <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Home</em>' attribute.
	 * @see #getHome()
	 * @generated
	 */
	void setHome(String value);

	/**
	 * Returns the value of the '<em><b>Remote</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote</em>' attribute.
	 * @see #setRemote(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_Remote()
	 * @generated
	 */
	String getRemote();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getRemote <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote</em>' attribute.
	 * @see #getRemote()
	 * @generated
	 */
	void setRemote(String value);

	/**
	 * Returns the value of the '<em><b>Ejb Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Link</em>' attribute.
	 * @see #setEjbLink(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_EjbLink()
	 * @generated
	 */
	String getEjbLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getEjbLink <em>Ejb Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Link</em>' attribute.
	 * @see #getEjbLink()
	 * @generated
	 */
	void setEjbLink(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getMappedName <em>Mapped Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_InjectionTargets()
	 * @generated
	 */
	List<InjectionTarget> getInjectionTargets();

	/**
	 * Returns the value of the '<em><b>Lookup Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The JNDI name to be looked up to resolve a resource reference.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lookup Name</em>' attribute.
	 * @see #setLookupName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_LookupName()
	 * @generated
	 */
	String getLookupName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getLookupName <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lookup Name</em>' attribute.
	 * @see #getLookupName()
	 * @generated
	 */
	void setLookupName(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEjbRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EjbRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // EjbRef