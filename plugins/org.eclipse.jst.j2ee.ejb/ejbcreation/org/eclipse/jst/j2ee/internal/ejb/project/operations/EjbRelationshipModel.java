/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;


import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

public class EjbRelationshipModel {
	public static final String ZERO_TO_ONE_MULT = "0..1"; //$NON-NLS-1$
	public static final String ONE_TO_ONE_MULT = "1..1"; //$NON-NLS-1$
	public static final String ZERO_TO_MANY_MULT = "0..*"; //$NON-NLS-1$
	public static final String ONE_TO_MANY_MULT = "1..*"; //$NON-NLS-1$

	protected String relationshipName;
	protected String relationshipDescription;
	protected String roleAName;
	protected String roleBName;
	protected EnterpriseBean roleAType;
	protected EnterpriseBean roleBType;
	protected boolean roleANavigable;
	protected boolean roleBNavigable;
	protected String roleAMultiplicityString;
	protected String roleBMultiplicityString;
	protected boolean roleAForward;
	protected boolean roleBForward;


	/**
	 * Constructor for EjbRelationshipModel.
	 */
	public EjbRelationshipModel() {
		super();
	}

	/**
	 * Gets the relationshipName.
	 * 
	 * @return Returns a String
	 */
	public String getRelationshipName() {
		return relationshipName;
	}

	/**
	 * Sets the relationshipName.
	 * 
	 * @param relationshipName
	 *            The relationshipName to set
	 */
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	/**
	 * Gets the roleAMultiplicityString.
	 * 
	 * @return Returns a String
	 */
	public String getRoleAMultiplicityString() {
		return roleAMultiplicityString;
	}

	/**
	 * Sets the roleAMultiplicityString.
	 * 
	 * @param roleAMultiplicityString
	 *            The roleAMultiplicityString to set
	 */
	public void setRoleAMultiplicityString(String roleAMultiplicityString) {
		this.roleAMultiplicityString = roleAMultiplicityString;
	}

	/**
	 * Gets the roleAName.
	 * 
	 * @return Returns a String
	 */
	public String getRoleAName() {
		return roleAName;
	}

	/**
	 * Sets the roleAName.
	 * 
	 * @param roleAName
	 *            The roleAName to set
	 */
	public void setRoleAName(String roleAName) {
		this.roleAName = roleAName;
	}

	/**
	 * Gets the roleANavigable.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isRoleANavigable() {
		return roleANavigable;
	}

	/**
	 * Sets the roleANavigable.
	 * 
	 * @param roleANavigable
	 *            The roleANavigable to set
	 */
	public void setRoleANavigable(boolean roleANavigable) {
		this.roleANavigable = roleANavigable;
	}

	/**
	 * Gets the roleAType.
	 * 
	 * @return Returns a EnterpriseBean
	 */
	public EnterpriseBean getRoleAType() {
		return roleAType;
	}

	/**
	 * Sets the roleAType.
	 * 
	 * @param roleAType
	 *            The roleAType to set
	 */
	public void setRoleAType(EnterpriseBean roleAType) {
		this.roleAType = roleAType;
	}

	/**
	 * Gets the roleBMultiplicityString.
	 * 
	 * @return Returns a String
	 */
	public String getRoleBMultiplicityString() {
		return roleBMultiplicityString;
	}

	/**
	 * Sets the roleBMultiplicityString.
	 * 
	 * @param roleBMultiplicityString
	 *            The roleBMultiplicityString to set
	 */
	public void setRoleBMultiplicityString(String roleBMultiplicityString) {
		this.roleBMultiplicityString = roleBMultiplicityString;
	}

	/**
	 * Gets the roleBName.
	 * 
	 * @return Returns a String
	 */
	public String getRoleBName() {
		return roleBName;
	}

	/**
	 * Sets the roleBName.
	 * 
	 * @param roleBName
	 *            The roleBName to set
	 */
	public void setRoleBName(String roleBName) {
		this.roleBName = roleBName;
	}

	/**
	 * Returns the relationshipDescription.
	 * 
	 * @return String
	 */
	public String getRelationshipDescription() {
		return relationshipDescription;
	}

	/**
	 * Sets the relationshipDescription.
	 * 
	 * @param relationshipDescription
	 *            The relationshipDescription to set
	 */
	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
	}

	/**
	 * Gets the roleBNavigable.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isRoleBNavigable() {
		return roleBNavigable;
	}

	/**
	 * Sets the roleBNavigable.
	 * 
	 * @param roleBNavigable
	 *            The roleBNavigable to set
	 */
	public void setRoleBNavigable(boolean roleBNavigable) {
		this.roleBNavigable = roleBNavigable;
	}

	/**
	 * Gets the roleBType.
	 * 
	 * @return Returns a EnterpriseBean
	 */
	public EnterpriseBean getRoleBType() {
		return roleBType;
	}

	/**
	 * Sets the roleBType.
	 * 
	 * @param roleBType
	 *            The roleBType to set
	 */
	public void setRoleBType(EnterpriseBean roleBType) {
		this.roleBType = roleBType;
	}

	/**
	 * Gets the roleAForward.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isRoleAForward() {
		return roleAForward;
	}

	/**
	 * Sets the roleAForward.
	 * 
	 * @param roleAForward
	 *            The roleAForward to set
	 */
	public void setRoleAForward(boolean roleAForward) {
		this.roleAForward = roleAForward;
	}

	/**
	 * Gets the roleBForward.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isRoleBForward() {
		return roleBForward;
	}

	/**
	 * Sets the roleBForward.
	 * 
	 * @param roleBForward
	 *            The roleBForward to set
	 */
	public void setRoleBForward(boolean roleBForward) {
		this.roleBForward = roleBForward;
	}

	public boolean isEJB20Model() {
		return false;
	}
}