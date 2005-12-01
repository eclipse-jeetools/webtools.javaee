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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * The ejb-relationship-role element describes a role within a relationship.
 * There are two roles in each relationship. The ejb-relationship-role element contains an optional description; an optional name for the relationship role; a specification of the multiplicity of the role; an optional specification of cascade-delete functionality for the role; the role source; and a declaration of the cmr-field, if any, by means of which the other side of the relationship is accessed from the perspective of the role source. The multiplicity and relationship-role-source element are mandatory. The relationship-role-source element designates an entity-bean by means of an ejb-name element. For bidirectional relationships, both roles of a relationship must declare a relationship-role-source element that specifies a cmr-field in terms of which the relationship is accessed. The lack of a cmr-field element in an ejb-relationship-role specifies that the relationship is unidirectional in navigability and that entity bean that participates in the relationship is "not aware" of the relationship.
 * 
 * @invariant multiplicity != null
 * @invariant roleSource != null
 * @invariant Cascade delete can only be specified in an EJBRelationshipRole element in which the roleSource element specifies a dependent object class. 
 * @invariant Cascade delete can only be specified for an EJBRelationshipRole contained in an EJBrelation in which the other EJBRelationshipRole element specifies a multiplicity of One.
 * 
 * Example:
 * <ejb-relation>
 *   <ejb-relation-name>Product-LineItem<//ejb-relation-name>
 *   <ejb-relationship-role>
 *     <ejb-relationship-role-name>product-has-lineitems<//ejb-relationship-role-name>
 *     <multiplicity>One<//multiplicity>
 *     <relationship-role-source>
 *       <ejb-name>ProductEJB<//ejb-name>
 *     <//relationship-role-source>
 *   <//ejb-relationship-role>
 * ...
 * @since 1.0 */
public interface EJBRelationshipRole extends EObject,CommonRelationshipRole{

/**
 * @return The logical name which can be used for naming this role.
 * Required to be unique within a JAR.
 * If a cmr-field is defined, then a name in the form %source-ejb-name%_%cmr-field-name% is computed.
 * Otherwise a default ID is generated.
 */
public String getLogicalName();
/**
 * Return the other role.
 */

EJBRelationshipRole getOpposite() ;
/**
 * @return java.lang.String the unique name for this role
 * */
public java.lang.String getName();
/**
 * @return The Source ejb
 * Compute the ContainerManagedEntity which is the source of a role that participates in a relationship. 
 */
public ContainerManagedEntity getSourceEntity();
/**
* @return The computed value of the isForward attribute
* Specifies whether this role should be mapped to a persistent reference, such as a foreign key.
*/
public boolean isForward();
/**
* @return The computed value isMany
* Returns whether the upper limit of our multiplicity is > 1
*/
public boolean isMany();
/**
* @return The computed value of the isNavigable attribute
* Specifies that accessors should be generated for this relationship role.
*/
public boolean isNavigable();
/**
 * Set this role to be the forward role.
 */

void setFoward() ;

public void setName(java.lang.String uniqueName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description is used by the ejb-jar file producer to provide text describing 
	 * the ejb relationship role.
	 * 
	 * The description should include any information that the ejb-jar file producer
	 * wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer).
	 * Typically, the tools used by the ejb-jar file consumer will display the
	 * description when processing the list of dependents.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the RoleName attribute
	 * Defines a name for a role that is unique within an ejb-relation. Different
	 * relationships can use the same name for a role.

	 */
	String getRoleName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the RoleName attribute
	 */
	void setRoleName(String value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.j2ee.ejb.MultiplicityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Describes the multiplicity of the role that participates in a relation.
	 * 
	 * The multiplicity element must be one of the two following:
	 *      One
	 *      Many
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see org.eclipse.jst.j2ee.ejb.MultiplicityKind
	 * @see #isSetMultiplicity()
	 * @see #unsetMultiplicity()
	 * @see #setMultiplicity(MultiplicityKind)
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getEJBRelationshipRole_Multiplicity()
	 * @model unsettable="true"
	 * @generated
	 */
	MultiplicityKind getMultiplicity();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' attribute.
	 * @see org.eclipse.jst.j2ee.ejb.MultiplicityKind
	 * @see #isSetMultiplicity()
	 * @see #unsetMultiplicity()
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(MultiplicityKind value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMultiplicity()
	 * @see #getMultiplicity()
	 * @see #setMultiplicity(MultiplicityKind)
	 * @generated
	 */
	void unsetMultiplicity();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#getMultiplicity <em>Multiplicity</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Multiplicity</em>' attribute is set.
	 * @see #unsetMultiplicity()
	 * @see #getMultiplicity()
	 * @see #setMultiplicity(MultiplicityKind)
	 * @generated
	 */
	boolean isSetMultiplicity();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the CascadeDelete attribute
	 * Specifies that, within a particular relationship, the lifetime of one or more
	 * entity beans is dependent upon the lifetime of another entity bean. The
	 * cascade-delete element can Cascade delete can only be specified for an
	 * EJBRelationshipRole contained in an EJBrelation in which the other
	 * EJBRelationshipRole element specifies a multiplicity of One.
	 * 
	 * 

	 */
	boolean isCascadeDelete();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the CascadeDelete attribute
	 */
	void setCascadeDelete(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#isCascadeDelete <em>Cascade Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isSetCascadeDelete()
	 * @see #isCascadeDelete()
	 * @see #setCascadeDelete(boolean)
	 * @generated
	 */
  void unsetCascadeDelete();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole#isCascadeDelete <em>Cascade Delete</em>}' attribute is set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Cascade Delete</em>' attribute is set.
	 * @see #unsetCascadeDelete()
	 * @see #isCascadeDelete()
	 * @see #setCascadeDelete(boolean)
	 * @generated
	 */
  boolean isSetCascadeDelete();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Relationship reference
	 */
	EJBRelation getRelationship();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Relationship reference
	 */
	void setRelationship(EJBRelation value);

	void setRequired(Boolean isRequired);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Source reference
	 * Designates the source of a role that participates in a relationship. A
	 * relationship-role-source element uniquely identifies an entity bean.
	 * @migration EJB1.1: was ibmejbext::EjbRelationshipRole::sourceEJBName, but that
	 * may not be sufficient, as this can now refer to dependent class names or ejb
	 * refs
	 */
	RoleSource getSource();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the Source reference
	 */
	void setSource(RoleSource value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The CmrField reference
	 * A declaration of the CMRField, if any, by means of which the other side of the
	 * relationship is accessed from the perspective of the role source
	 * 
	 * @migration EJB1.1: Used to be handled via ibmejbext::EjbRole::attributes list
	 */
	CMRField getCmrField();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the CmrField reference
	 */
	void setCmrField(CMRField value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getEJBRelationshipRole_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

	/**
	 * Return whether this role is part of the key of the source EJB.
	 */
	boolean isKey();
	
	/**
	 * This allows you to set the Role as being key so it will
	 * not traverse it attributes to determine if it is key.
	 */
	void setKey(boolean aBoolean) ;
	
	void reconcileAttributes() ;
}






