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
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EmptyType;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EJB Relationship Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *         [
 *         The ejb-relationship-roleType describes a role within a
 *         relationship. There are two roles in each relationship.
 *         
 *         The ejb-relationship-roleType contains an optional
 *         description; an optional name for the relationship role; a
 *         specification of the multiplicity of the role; an optional
 *         specification of cascade-delete functionality for the role;
 *         the role source; and a declaration of the cmr-field, if any,
 *         by means of which the other side of the relationship is
 *         accessed from the perspective of the role source.
 *         
 *         The multiplicity and role-source element are mandatory.
 *         
 *         The relationship-role-source element designates an entity
 *         bean by means of an ejb-name element. For bidirectional
 *         relationships, both roles of a relationship must declare a
 *         relationship-role-source element that specifies a cmr-field
 *         in terms of which the relationship is accessed. The lack of
 *         a cmr-field element in an ejb-relationship-role specifies
 *         that the relationship is unidirectional in navigability and
 *         the entity bean that participates in the relationship is
 *         "not aware" of the relationship.
 *         
 *         Example:
 *         
 *         <ejb-relation>
 *         <ejb-relation-name>Product-LineItem</ejb-relation-name>
 *         <ejb-relationship-role>
 *         	  <ejb-relationship-role-name>product-has-lineitems
 *         	  </ejb-relationship-role-name>
 *         	  <multiplicity>One</multiplicity>
 *         	  <relationship-role-source>
 *         	  <ejb-name>ProductEJB</ejb-name>
 *         	  </relationship-role-source>
 *         </ejb-relationship-role>
 *         </ejb-relation>
 *         
 *         Support for entity beans is optional as of EJB 3.2.
 * 
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getEjbRelationshipRoleName <em>Ejb Relationship Role Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getCascadeDelete <em>Cascade Delete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getRelationshipRoleSource <em>Relationship Role Source</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getCmrField <em>Cmr Field</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole()
 * @extends JavaEEObject
 * @generated
 */
public interface EJBRelationshipRole extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Ejb Relationship Role Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The ejb-relationship-role-name element defines a
	 *             name for a role that is unique within an
	 *             ejb-relation. Different relationships can use the
	 *             same name for a role.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb Relationship Role Name</em>' attribute.
	 * @see #setEjbRelationshipRoleName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_EjbRelationshipRoleName()
	 * @generated
	 */
	String getEjbRelationshipRoleName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getEjbRelationshipRoleName <em>Ejb Relationship Role Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Relationship Role Name</em>' attribute.
	 * @see #getEjbRelationshipRoleName()
	 * @generated
	 */
	void setEjbRelationshipRoleName(String value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.MultiplicityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.MultiplicityType
	 * @see #isSetMultiplicity()
	 * @see #unsetMultiplicity()
	 * @see #setMultiplicity(MultiplicityType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_Multiplicity()
	 * @generated
	 */
	MultiplicityType getMultiplicity();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.MultiplicityType
	 * @see #isSetMultiplicity()
	 * @see #unsetMultiplicity()
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(MultiplicityType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMultiplicity()
	 * @see #getMultiplicity()
	 * @see #setMultiplicity(MultiplicityType)
	 * @generated
	 */
	void unsetMultiplicity();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Multiplicity</em>' attribute is set.
	 * @see #unsetMultiplicity()
	 * @see #getMultiplicity()
	 * @see #setMultiplicity(MultiplicityType)
	 * @generated
	 */
	boolean isSetMultiplicity();

	/**
	 * Returns the value of the '<em><b>Cascade Delete</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The cascade-delete element specifies that, within a
	 *             particular relationship, the lifetime of one or more
	 *             entity beans is dependent upon the lifetime of
	 *             another entity bean. The cascade-delete element can
	 *             only be specified for an ejb-relationship-role
	 *             element contained in an ejb-relation element in
	 *             which the other ejb-relationship-role
	 *             element specifies a multiplicity of One.
	 *             
	 *             Support for entity beans is optional as of EJB 3.2.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cascade Delete</em>' containment reference.
	 * @see #setCascadeDelete(EmptyType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_CascadeDelete()
	 * @generated
	 */
	EmptyType getCascadeDelete();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getCascadeDelete <em>Cascade Delete</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cascade Delete</em>' containment reference.
	 * @see #getCascadeDelete()
	 * @generated
	 */
	void setCascadeDelete(EmptyType value);

	/**
	 * Returns the value of the '<em><b>Relationship Role Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship Role Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship Role Source</em>' containment reference.
	 * @see #setRelationshipRoleSource(RelationshipRoleSourceType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_RelationshipRoleSource()
	 * @generated
	 */
	RelationshipRoleSourceType getRelationshipRoleSource();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getRelationshipRoleSource <em>Relationship Role Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relationship Role Source</em>' containment reference.
	 * @see #getRelationshipRoleSource()
	 * @generated
	 */
	void setRelationshipRoleSource(RelationshipRoleSourceType value);

	/**
	 * Returns the value of the '<em><b>Cmr Field</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cmr Field</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cmr Field</em>' containment reference.
	 * @see #setCmrField(CMRField)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_CmrField()
	 * @generated
	 */
	CMRField getCmrField();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getCmrField <em>Cmr Field</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cmr Field</em>' containment reference.
	 * @see #getCmrField()
	 * @generated
	 */
	void setCmrField(CMRField value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBRelationshipRole_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBRelationshipRole#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // EJBRelationshipRole