/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;


import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;

public class EjbUpdateRelationshipModel extends EjbRelationshipModel {
	protected CommonRelationshipRole roleA;
	protected CommonRelationshipRole roleB;
	protected CommonRelationship relationship;

	/**
	 * Constructor for EjbUpdateRelationshipModel.
	 */
	public EjbUpdateRelationshipModel() {
		super();
	}

	/**
	 * Gets the relationship.
	 * 
	 * @return Returns a CommonRelationship
	 */
	public CommonRelationship getRelationship() {
		return relationship;
	}

	/**
	 * Sets the relationship.
	 * 
	 * @param relationship
	 *            The relationship to set
	 */
	public void setRelationship(CommonRelationship relationship) {
		this.relationship = relationship;
	}

	/**
	 * Gets the roleA.
	 * 
	 * @return Returns a CommonRelationshipRole
	 */
	public CommonRelationshipRole getRoleA() {
		return roleA;
	}

	/**
	 * Sets the roleA.
	 * 
	 * @param roleA
	 *            The roleA to set
	 */
	public void setRoleA(CommonRelationshipRole roleA) {
		this.roleA = roleA;
		if (roleA != null)
			setRoleAType(roleA.getTypeEntity());
	}

	/**
	 * Gets the roleB.
	 * 
	 * @return Returns a CommonRelationshipRole
	 */
	public CommonRelationshipRole getRoleB() {
		return roleB;
	}

	/**
	 * Sets the roleB.
	 * 
	 * @param roleB
	 *            The roleB to set
	 */
	public void setRoleB(CommonRelationshipRole roleB) {
		this.roleB = roleB;
		if (roleB != null)
			setRoleBType(roleB.getTypeEntity());
	}

	public boolean shouldUpdateRoles() {
		return getRoleA() != null && getRoleB() != null;
	}
}