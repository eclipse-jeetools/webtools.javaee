/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.Field;

/**
 * @generated
 * @since 1.0 */
public interface ContainerManagedEntity extends Entity{

	/**
	 * Denotes 1.x version of the CMP Bean
	 */
	String VERSION_1_X = "1.x"; //$NON-NLS-1$
	
	/**
	 * Denotes 2.x version of the CMP Bean
	 */
	String VERSION_2_X = "2.x"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	String WAS_GENERATED_STRING_KEY = "com.ibm.ws.ejbpersistence.dataaccess.GeneratedStringPK"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	String WAS_GENERATED_STRING_FIELD = "wasGenUnknownkeyValue"; //$NON-NLS-1$
	
	/**
	 * Set to java.lang.Object
	 */
	String UNKNOWN_PRIMARY_KEY = "java.lang.Object"; //$NON-NLS-1$
	
	/**
	 * Set to java.lang.String
	 */
	String JAVA_LANG_STRING = "java.lang.String"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	int DERIVED_FLAG_CHANGE = 301;
	
	/**
	 * Create a new instance of CMPAttribute with aName and add it to the keyAttributes     
	 * of the bean
	 * @param aName the name of the key attribute
	 * @return CMPAttribute
	 */
	public CMPAttribute addKeyAttributeName(String aName);

	/**
	 * Add a persistent attribute with the same name as the name for aField.
	 * @param aField a reference of type Field
	 * @return CMPAttribute
	 */
	public abstract CMPAttribute addPersistentAttribute(Field aField) ;
	
	/**
	 * Create a new instance of CMPAttribute with aName and add it to the persistent attributes
	 * @param aName the name of the persistent attribute
	 * @return CMPAttribute
	 */
	public abstract CMPAttribute addPersistentAttributeName(String aName) ;
	/**
	 * Return the keyAttribute with aName.
	 * Creation date: (8/8/2000 9:25:27 PM)
	 * @return EStructuralFeature
	 * @param aName java.lang.String
	 */
	CMPAttribute getKeyAttribute(String aName) ;
	/**
	 * Return the persistentAttribute with aName.
	 * Creation date: (8/8/2000 9:25:27 PM)
	 * @return EStructuralFeature
	 * @param aName java.lang.String
	 */
	CMPAttribute getPersistentAttribute(String aName) ;
	/**
	 * If a primary key exists, return the persistent attribute; the primary key is defined as one and only one
	 * key attribute, whose type is the same as the key class
	 * @return a reference to the CMPAttribute
	 * @deprecated - Use getPrimKeyField()
	 */
	CMPAttribute getPrimaryKeyAttribute();

	/**
	 * Returns true when invoked on the instance of this class
	 * @return boolean value  
	 */
	public boolean isContainerManagedEntity();
	
	/**
	 * Returns true if the CMP is of version 1.X
	 * @return boolean value
	 */
	public boolean isVersion1_X();
	
	/**
	 * Returns true if the CMP is of version 2.X
	 * @return boolean value
	 */
	public boolean isVersion2_X();
	
	/**
	 * Returns true if anAttribute is a key attribute of the CMP
	 * @param anAttribute a attribute of the CMP of type CMPAttribute
	 * @return boolean value
	 */
	public boolean isKeyAttribute(CMPAttribute anAttribute);
	
	/**
	 * Return whether this bean represents an instance of the "unknown primary key" case
	 * described in the specification.  This is identified by the primary key class
	 * being specified as "java.lang.Object".
	 * In order to deploy an unknown primary key bean, we must substitute a real key,
	 * and add a corresponding field.
	 * @return boolean value
	 */
	public boolean isUnknownPrimaryKey();
	
	/**
	 * Returns the list of Persistent Field Names
	 * @return an String array of names 
	 */
	public String[] listPersistentFieldNames();
	
	/**
	 * Removes the attribute with the name specified in the aField from the list of persistent attributes
	 * @param aField the Field reference
	 * @return CMPAttribute reference
	 */

	CMPAttribute removePersistentAttribute(Field aField) ;
	/**
	 * Remove the persistentAttribute named aName.
	 * Creation date: (8/2/2000 8:59:32 PM)
	 * @param aName java.lang.String
	 * @return CMPAttribute reference
	 */
	CMPAttribute removePersistentAttribute(String aName) ;
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Version attribute
	 * Specifies the version of an entity bean with container-managed persistence.
	 * 
	 * @invariant The cmp-version element must be one of the two following: 1.x , 2.x
	 * @default 2.x

	 */
	String getVersion();

	/**
	* This returns the module version id. Compare with J2EEVersionConstants to determine module level
	* @throws IllegalStateException throws this exception when an error is encountered in getting version of the CMP
	* @return the version of CMP represented in int type 
	*/
	public int getCMPVersionID() throws IllegalStateException ;


	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Version attribute
	 */
	void setVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the AbstractSchemaName attribute
	 * The abstract-schema-name element specifies the name of the abstract schema type 
	 * of an entity bean with cmp-version 2.x. It is used in EJB QL queries.
	 * 
	 * For example, the abstract-schema-name for an entity bean whose local interface
	 * is com.acme.commerce.Order might be Order.
	 * 

	 */
	String getAbstractSchemaName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the AbstractSchemaName attribute
	 */
	void setAbstractSchemaName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of PersistentAttributes references
	 */
	EList getPersistentAttributes();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of KeyAttributes references
	 */
	EList getKeyAttributes();

	/**
	 * Returns the value of the '<em><b>Queries</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.ejb.Query}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.j2ee.ejb.Query#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Queries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4 moved from CME
	 * The optional query elements must be present if the persistence-type is Container and the cmp-version is 2.x and query methods other than findByPrimaryKey have been defined for the entity bean.
	 * 
	 * @invariant see description
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Queries</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getContainerManagedEntity_Queries()
	 * @see org.eclipse.jst.j2ee.ejb.Query#getEntity
	 * @model type="org.eclipse.jst.j2ee.ejb.Query" opposite="entity" containment="true"
	 * @generated
	 */
	EList getQueries();

	/**
	 * Returns the value of the '<em><b>CMP Attribute</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.ejb.CMPAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CMP Attribute</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CMP Attribute</em>' reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getContainerManagedEntity_CMPAttribute()
	 * @model type="org.eclipse.jst.j2ee.ejb.CMPAttribute"
	 * @generated
	 */
	EList getCMPAttribute();

	/**
	 * Returns the value of the '<em><b>Prim Key Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prim Key Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prim Key Field</em>' reference.
	 * @see #setPrimKeyField(CMPAttribute)
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getContainerManagedEntity_PrimKeyField()
	 * @model
	 * @generated
	 */
	CMPAttribute getPrimKeyField();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.ContainerManagedEntity#getPrimKeyField <em>Prim Key Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prim Key Field</em>' reference.
	 * @see #getPrimKeyField()
	 * @generated
	 */
	void setPrimKeyField(CMPAttribute value);

	/**
	 * Return a List of all the available Query methods for the Home and Local Home interfaces.
	 * @return List of Find Query Methods
	 */
	List getAvailableFindQueryMethods() ;
	/**
	 * Return a List of all the available Query methods for the Bean class.
	 * @return List of Select Query Methods
	 */
	List getAvailableSelectQueryMethods();
	/**
	 * Return a List, sorted by signature, of all the available Query methods 
	 * on the remote Home, local Home, or ejbSelect methods on the bean class
	 * @return List of Query methods
	 */
	List getAllAvailableQueryMethods();
	
	/**
	 * Returns the list of EJBExtensionFilter
	 * @param aFilter a reference of type EJBExtensionFilter
	 * @return List of filtered features
	 */
	public List getFilteredFeatures(EJBExtensionFilter aFilter);
	
	/**
	 * Makes the primary Key Field proxy.
	 *
	 */
	public void makePrimKeyFieldProxy();
	/**
	 * Gets the list of CMR Fields
	 * @return The list of all CMR fields for this bean.
	 */
	public List getCMRFields();
	/**
	 * @return The list of all roles where this bean is the source.
	 * @link CommonRelationshipRole
	 */
	public List getRoles();

	/**
	 * Return a specific CommonRelationshipRole which has a named role
	 * that is equal to <code>roleName</code>.
	 * @see CommonRelationshipRole
	 * @param roleName the role name 
	 * @return a reference to CommonRelationshipRole instance
	 */
	public CommonRelationshipRole getRole(String roleName);
	
}






