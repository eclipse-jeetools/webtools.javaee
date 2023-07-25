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
 * A representation of the model object '<em><b>Resource Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * [
 *         The resource-refType contains a declaration of a
 *         Deployment Component's reference to an external resource. It
 *         consists of an optional description, the resource manager
 *         connection factory reference name, an optional indication of
 *         the resource manager connection factory type expected by the
 *         Deployment Component code, an optional type of authentication
 *         (Application or Container), and an optional specification of
 *         the shareability of connections obtained from the resource
 *         (Shareable or Unshareable).
 *         
 *         It also includes optional elements to define injection of
 *         the named resource into fields or JavaBeans properties.
 *         
 *         The connection factory type must be supplied unless an
 *         injection target is specified, in which case the type
 *         of the target is used.  If both are specified, the type
 *         must be assignment compatible with the type of the injection
 *         target.
 *         
 *         Example:
 *         
 *         <resource-ref>
 *         <res-ref-name>jdbc/EmployeeAppDB</res-ref-name>
 *         <res-type>javax.sql.DataSource</res-type>
 *         <res-auth>Container</res-auth>
 *         <res-sharing-scope>Shareable</res-sharing-scope>
 *         </resource-ref>
 *         
 * 
 *         @since Java EE 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getResRefName <em>Res Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getResType <em>Res Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getResAuth <em>Res Auth</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef()
 * @extends JavaEEObject
 * @generated
 */
public interface ResourceRef extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Res Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The res-ref-name element specifies the name of a
	 *             resource manager connection factory reference.
	 *             The name is a JNDI name relative to the
	 *             java:comp/env context.  
	 *             The name must be unique within a Deployment File. 
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Res Ref Name</em>' attribute.
	 * @see #setResRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_ResRefName()
	 * @generated
	 */
	String getResRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResRefName <em>Res Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Res Ref Name</em>' attribute.
	 * @see #getResRefName()
	 * @generated
	 */
	void setResRefName(String value);

	/**
	 * Returns the value of the '<em><b>Res Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The res-type element specifies the type of the data
	 *             source. The type is specified by the fully qualified
	 *             Java language class or interface
	 *             expected to be implemented by the data source.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Res Type</em>' attribute.
	 * @see #setResType(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_ResType()
	 * @generated
	 */
	String getResType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResType <em>Res Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Res Type</em>' attribute.
	 * @see #getResType()
	 * @generated
	 */
	void setResType(String value);

	/**
	 * Returns the value of the '<em><b>Res Auth</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.ResAuthType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Res Auth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Res Auth</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.ResAuthType
	 * @see #isSetResAuth()
	 * @see #unsetResAuth()
	 * @see #setResAuth(ResAuthType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_ResAuth()
	 * @generated
	 */
	ResAuthType getResAuth();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResAuth <em>Res Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Res Auth</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.ResAuthType
	 * @see #isSetResAuth()
	 * @see #unsetResAuth()
	 * @see #getResAuth()
	 * @generated
	 */
	void setResAuth(ResAuthType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResAuth <em>Res Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResAuth()
	 * @see #getResAuth()
	 * @see #setResAuth(ResAuthType)
	 * @generated
	 */
	void unsetResAuth();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResAuth <em>Res Auth</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Res Auth</em>' attribute is set.
	 * @see #unsetResAuth()
	 * @see #getResAuth()
	 * @see #setResAuth(ResAuthType)
	 * @generated
	 */
	boolean isSetResAuth();

	/**
	 * Returns the value of the '<em><b>Res Sharing Scope</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.ResSharingScopeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Res Sharing Scope</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Res Sharing Scope</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.ResSharingScopeType
	 * @see #isSetResSharingScope()
	 * @see #unsetResSharingScope()
	 * @see #setResSharingScope(ResSharingScopeType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_ResSharingScope()
	 * @generated
	 */
	ResSharingScopeType getResSharingScope();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Res Sharing Scope</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.ResSharingScopeType
	 * @see #isSetResSharingScope()
	 * @see #unsetResSharingScope()
	 * @see #getResSharingScope()
	 * @generated
	 */
	void setResSharingScope(ResSharingScopeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResSharingScope()
	 * @see #getResSharingScope()
	 * @see #setResSharingScope(ResSharingScopeType)
	 * @generated
	 */
	void unsetResSharingScope();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Res Sharing Scope</em>' attribute is set.
	 * @see #unsetResSharingScope()
	 * @see #getResSharingScope()
	 * @see #setResSharingScope(ResSharingScopeType)
	 * @generated
	 */
	boolean isSetResSharingScope();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getMappedName <em>Mapped Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_InjectionTargets()
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_LookupName()
	 * @generated
	 */
	String getLookupName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getLookupName <em>Lookup Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ResourceRef