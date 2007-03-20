/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResourceEnvRef.java,v 1.1 2007/03/20 18:04:35 jsholl Exp $
 */
package org.eclipse.jst.javaee.core;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Env Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 	
 * 
 * 	  The resource-env-refType is used to define
 * 	  resource-env-type elements.  It contains a declaration of a
 * 	  Deployment Component's reference to an administered object
 * 	  associated with a resource in the Deployment Component's
 * 	  environment.  It consists of an optional description, the
 * 	  resource environment reference name, and an optional
 * 	  indication of the resource environment reference type
 * 	  expected by the Deployment Component code.
 * 
 * 	  It also includes optional elements to define injection of
 * 	  the named resource into fields or JavaBeans properties.
 * 
 * 	  The resource environment type must be supplied unless an
 * 	  injection target is specified, in which case the type
 * 	  of the target is used.  If both are specified, the type
 * 	  must be assignment compatible with the type of the injection
 * 	  target.
 * 
 * 	  Example:
 * 
 * 	  &lt;resource-env-ref&gt;
 * 	      &lt;resource-env-ref-name&gt;jms/StockQueue
 * 	      &lt;/resource-env-ref-name&gt;
 * 	      &lt;resource-env-ref-type&gt;javax.jms.Queue
 * 	      &lt;/resource-env-ref-type&gt;
 * 	  &lt;/resource-env-ref&gt;
 * 
 * 	  
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getResourceEnvRefName <em>Resource Env Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getResourceEnvRefType <em>Resource Env Ref Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef()
 * @extends JavaEEObject
 * @generated
 */
public interface ResourceEnvRef extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef_Descriptions()
	 * @generated
	 */
	List getDescriptions();

	/**
	 * Returns the value of the '<em><b>Resource Env Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The resource-env-ref-name element specifies the name
	 * 	    of a resource environment reference; its value is
	 * 	    the environment entry name used in
	 * 	    the Deployment Component code.  The name is a JNDI
	 * 	    name relative to the java:comp/env context and must
	 * 	    be unique within a Deployment Component.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Env Ref Name</em>' attribute.
	 * @see #setResourceEnvRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef_ResourceEnvRefName()
	 * @generated
	 */
	String getResourceEnvRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getResourceEnvRefName <em>Resource Env Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Env Ref Name</em>' attribute.
	 * @see #getResourceEnvRefName()
	 * @generated
	 */
	void setResourceEnvRefName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Env Ref Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The resource-env-ref-type element specifies the type
	 * 	    of a resource environment reference.  It is the
	 * 	    fully qualified name of a Java language class or
	 * 	    interface.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Env Ref Type</em>' attribute.
	 * @see #setResourceEnvRefType(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef_ResourceEnvRefType()
	 * @generated
	 */
	String getResourceEnvRefType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getResourceEnvRefType <em>Resource Env Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Env Ref Type</em>' attribute.
	 * @see #getResourceEnvRefType()
	 * @generated
	 */
	void setResourceEnvRefType(String value);

	/**
	 * Returns the value of the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 	    
	 * 
	 * 	      A product specific name that this resource should be
	 * 	      mapped to.  The name of this resource, as defined by the
	 * 	      resource's name element or defaulted, is a name that is
	 * 	      local to the application component using the resource.
	 * 	      (It's a name in the JNDI java:comp/env namespace.)  Many
	 * 	      application servers provide a way to map these local
	 * 	      names to names of resources known to the application
	 * 	      server.  This mapped name is often a global JNDI name,
	 * 	      but may be a name of any form.
	 * 
	 * 	      Application servers are not required to support any
	 * 	      particular form or type of mapped name, nor the ability
	 * 	      to use mapped names.  The mapped name is
	 * 	      product-dependent and often installation-dependent.  No
	 * 	      use of a mapped name is portable.
	 * 
	 * 	      
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mapped Name</em>' attribute.
	 * @see #setMappedName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getMappedName <em>Mapped Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef_InjectionTargets()
	 * @generated
	 */
	List getInjectionTargets();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResourceEnvRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ResourceEnvRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ResourceEnvRef