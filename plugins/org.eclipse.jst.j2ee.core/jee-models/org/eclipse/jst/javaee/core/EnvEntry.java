/**
 * <copyright>
 * </copyright>
 *
 * $Id: EnvEntry.java,v 1.1 2007/05/16 06:42:29 cbridgha Exp $
 */
package org.eclipse.jst.javaee.core;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Env Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The env-entryType is used to declare an application's
 * 	environment entry. The declaration consists of an optional
 * 	description, the name of the environment entry, a type
 * 	(optional if the value is injected, otherwise required), and
 * 	an optional value.
 * 
 * 	It also includes optional elements to define injection of
 * 	the named resource into fields or JavaBeans properties.
 * 
 * 	If a value is not specified and injection is requested,
 * 	no injection will occur and no entry of the specified name
 * 	will be created.  This allows an initial value to be
 * 	specified in the source code without being incorrectly
 * 	changed when no override has been specified.
 * 
 * 	If a value is not specified and no injection is requested,
 * 	a value must be supplied during deployment.
 * 
 * 	This type is used by env-entry elements.
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryName <em>Env Entry Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryType <em>Env Entry Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryValue <em>Env Entry Value</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.EnvEntry#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry()
 * @extends JavaEEObject
 * @generated
 */
public interface EnvEntry extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_Descriptions()
	 * @generated
	 */
	List getDescriptions();

	/**
	 * Returns the value of the '<em><b>Env Entry Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 	    
	 * 
	 * 	      The env-entry-name element contains the name of a
	 * 	      Deployment Component's environment entry.  The name
	 * 	      is a JNDI name relative to the java:comp/env
	 * 	      context.  The name must be unique within a
	 * 	      Deployment Component. The uniqueness
	 * 	      constraints must be defined within the declared
	 * 	      context.
	 * 
	 * 	      Example:
	 * 
	 * 	      &lt;env-entry-name&gt;minAmount&lt;/env-entry-name&gt;
	 * 
	 * 	      
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Env Entry Name</em>' attribute.
	 * @see #setEnvEntryName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_EnvEntryName()
	 * @generated
	 */
	String getEnvEntryName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryName <em>Env Entry Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Env Entry Name</em>' attribute.
	 * @see #getEnvEntryName()
	 * @generated
	 */
	void setEnvEntryName(String value);

	/**
	 * Returns the value of the '<em><b>Env Entry Type</b></em>' attribute.
	 * The default value is <code>"java.lang.Boolean"</code>.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.EnvEntryType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 	    
	 * 
	 * 	      The env-entry-type element contains the Java language
	 * 	      type of the environment entry.  If an injection target
	 * 	      is specified for the environment entry, the type may
	 * 	      be omitted, or must match the type of the injection
	 * 	      target.  If no injection target is specified, the type
	 * 	      is required.
	 * 
	 * 	      Example:
	 * 
	 * 	      &lt;env-entry-type&gt;java.lang.Integer&lt;/env-entry-type&gt;
	 * 
	 * 	      
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Env Entry Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.EnvEntryType
	 * @see #isSetEnvEntryType()
	 * @see #unsetEnvEntryType()
	 * @see #setEnvEntryType(EnvEntryType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_EnvEntryType()
	 * @generated
	 */
	EnvEntryType getEnvEntryType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryType <em>Env Entry Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Env Entry Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.EnvEntryType
	 * @see #isSetEnvEntryType()
	 * @see #unsetEnvEntryType()
	 * @see #getEnvEntryType()
	 * @generated
	 */
	void setEnvEntryType(EnvEntryType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryType <em>Env Entry Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnvEntryType()
	 * @see #getEnvEntryType()
	 * @see #setEnvEntryType(EnvEntryType)
	 * @generated
	 */
	void unsetEnvEntryType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryType <em>Env Entry Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Env Entry Type</em>' attribute is set.
	 * @see #unsetEnvEntryType()
	 * @see #getEnvEntryType()
	 * @see #setEnvEntryType(EnvEntryType)
	 * @generated
	 */
	boolean isSetEnvEntryType();

	/**
	 * Returns the value of the '<em><b>Env Entry Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 	    
	 * 
	 * 	      The env-entry-value designates the value of a
	 * 	      Deployment Component's environment entry. The value
	 * 	      must be a String that is valid for the
	 * 	      constructor of the specified type that takes a
	 * 	      single String parameter, or for java.lang.Character,
	 * 	      a single character.
	 * 
	 * 	      Example:
	 * 
	 * 	      &lt;env-entry-value&gt;100.00&lt;/env-entry-value&gt;
	 * 
	 * 	      
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Env Entry Value</em>' attribute.
	 * @see #setEnvEntryValue(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_EnvEntryValue()
	 * @generated
	 */
	String getEnvEntryValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getEnvEntryValue <em>Env Entry Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Env Entry Value</em>' attribute.
	 * @see #getEnvEntryValue()
	 * @generated
	 */
	void setEnvEntryValue(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getMappedName <em>Mapped Name</em>}' attribute.
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_InjectionTargets()
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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getEnvEntry_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.EnvEntry#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // EnvEntry