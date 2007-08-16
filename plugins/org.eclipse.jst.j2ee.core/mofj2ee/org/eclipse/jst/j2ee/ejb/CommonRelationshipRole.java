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
 * Insert the type's description here.
 * Creation date: (10/26/2001 9:24:27 AM)
 * @author: Administrator
 * @since 1.0 */
public interface CommonRelationshipRole extends EObject {
/**
 * Return the persistentAttributes from my Entity that are used
 * by this role.
 * @return the attributes of the CommonRelationshipRole
 */

public EList getAttributes();
/**
 * Return our parent relationship as a CommonRelationship
 * @return the CommonRelationship reference
 */

public CommonRelationship getCommonRelationship();


/**

 * Defines a name for a role that is unique within an ejb-relation. Different relationships can use the same name for a role.
 * @return The value of the Name attribute
	 */
	public String getName();
/**
 * Return the actual name of this role.  This is the name defined in the Deployment
 * Descriptor.  You should use <code>getName()</code> for the name to use in code
 * generation.
 * @return the name of the CommonRelationshipRole 
 */
String getRoleName();
/**
 * Return the other role.
 * @return CommonRelationshipRole reference
 */

CommonRelationshipRole getOppositeAsCommonRole() ;
/**
 * @return The Source ejb
 * Compute the ContainerManagedEntity which is the source of a role that participates in a relationship.
 * @return  ContainerManagedEntity reference
 */
public ContainerManagedEntity getSourceEntity();

/**
 * Compute the ContainerManagedEntity which is the type of a role that participates in a relationship.
 * @return The type ejb 
 */
public ContainerManagedEntity getTypeEntity();
/**
* @return The computed value of the isForward attribute
* Specifies whether this role should be mapped to a persistent reference, such as a foreign key.
*/
public boolean isForward();

/**
 * Returns true if this is a key 
 * @return The computed value isKey
 */
public boolean isKey();

/**
 * This method should be used as a hint about the key status of the role.
 * @param aBoolean the boolean value
 */
public void setKey(boolean aBoolean) ;
/**
* @return The computed value isMany
* Returns whether the upper limit of our multiplicity is > 1
*/
public boolean isMany();

/**
* 
* sets the upper limit of our multiplicity
* @param upperBound the int value of the multiplicity
*/
public void setUpper(int upperBound);

/**
* 
* sets the lower limit of our multiplicity
* @param lowerBound the int value if the multiplicity
*/
public void setLower(int lowerBound);

/**
* @return The computed value of the isNavigable attribute
* Specifies that accessors should be generated for this relationship role.
*/
public boolean isNavigable();

/**
 * Gets the attribute name of a given CMPAttribute
 * @param roleAttribute the CMPAttribute
 * @return the name of the targetAttribute
 */
public String getTargetAttributeName(CMPAttribute roleAttribute);

/**
 * Collects the attributes
 *
 */
void reconcileAttributes();
	/**
	 * Method isRequired.
	 * @return boolean value
	 */
	boolean isRequired();
}



