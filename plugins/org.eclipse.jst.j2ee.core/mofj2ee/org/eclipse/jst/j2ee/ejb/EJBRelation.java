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
 * The ejb-relation element describes a relationship between two entity beans with container managed persistence. An ejb-relation element contains a description; an optional ejb-relation-name element; and exactly two relationship role declarations, defined by the ejb-relationship-roles. The name of the relationship, if specified, is unique within the ejb-jar file.
 * 
 * @invariant The name of the relationship, if specified, is unique within the ejb-jar file.
 * @invariant self.relationshipRoles.size == 2
 * @invariant self.relationshipRoles[0].name != self.relationshipRoles[1].name
 * 
 * @migration EJB1.1 Moved from ejbext::EjbRelationshipRole contained under ejbext::EJBJarExtension
 * @migration EJB1.1 added optional attribute, description:String
 * @migration EJB1.1 added optional attribute, name:String (May have been inherited from EObject previously)
 * @since 1.0 */
public interface EJBRelation extends EObject,CommonRelationship{

boolean containsRelationshipRole(EJBRelationshipRole aRole) ;
EJBRelationshipRole getFirstRole() ;
/**
 * Return the other role.
 */

EJBRelationshipRole getOppositeRole(EJBRelationshipRole aRole) ;
EJBRelationshipRole getSecondRole() ;
/**
 * Set the forward role.
 */

void setFoward(EJBRelationshipRole aRole) ;
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description is used by the ejb-jar file producer to provide text describing 
	 * the ejb relation.
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
	 * @return The value of the Name attribute
	 * The name of the relationship. If specified, must be unique within the ejb-jar
	 * file.
	 * @invariant self.name unique within Set<collect(ejbJar.ejbRelations.name)>
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The RelationshipList reference
	 */
	Relationships getRelationshipList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the RelationshipList reference
	 */
	void setRelationshipList(Relationships value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of RelationshipRoles references
	 * A collection containing exactly two relationship roles.
	 * 
	 * @migration EJB1.1 Containment used to be by reference on relation--now by value.

	 */
	EList getRelationshipRoles();

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
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getEJBRelation_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

	/**
	 * Return the EJBRelationshipRole with the same name as <code>aRoleName</code>.
	 */
	EJBRelationshipRole getRelationshipRole(String aRoleName) ;
}






