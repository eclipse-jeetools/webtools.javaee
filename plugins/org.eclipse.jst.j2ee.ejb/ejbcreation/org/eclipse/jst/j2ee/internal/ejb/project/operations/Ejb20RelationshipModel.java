/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;


public class Ejb20RelationshipModel extends EjbUpdateRelationshipModel {
	protected boolean roleACascadeDelete;
	protected boolean roleBCascadeDelete;
	protected String roleACmrFieldName;
	protected String roleBCmrFieldName;
	protected String roleACmrFieldCollectionType;
	protected String roleBCmrFieldCollectionType;

	/**
	 * Constructor for Ejb20RelationshipModel.
	 */
	public Ejb20RelationshipModel() {
		super();
	}

	/**
	 * Gets the roleACascadeDelete.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isRoleACascadeDelete() {
		return roleACascadeDelete;
	}

	/**
	 * Sets the roleACascadeDelete.
	 * 
	 * @param roleACascadeDelete
	 *            The roleACascadeDelete to set
	 */
	public void setRoleACascadeDelete(boolean roleACascadeDelete) {
		this.roleACascadeDelete = roleACascadeDelete;
	}

	/**
	 * Gets the roleACmrFieldCollectionType.
	 * 
	 * @return Returns a String
	 */
	public String getRoleACmrFieldCollectionType() {
		return roleACmrFieldCollectionType;
	}

	/**
	 * Sets the roleACmrFieldCollectionType.
	 * 
	 * @param roleACmrFieldCollectionType
	 *            The roleACmrFieldCollectionType to set
	 */
	public void setRoleACmrFieldCollectionType(String roleACmrFieldCollectionType) {
		this.roleACmrFieldCollectionType = roleACmrFieldCollectionType;
	}

	/**
	 * Gets the roleACmrFieldName.
	 * 
	 * @return Returns a String
	 */
	public String getRoleACmrFieldName() {
		return roleACmrFieldName;
	}

	/**
	 * Sets the roleACmrFieldName.
	 * 
	 * @param roleACmrFieldName
	 *            The roleACmrFieldName to set
	 */
	public void setRoleACmrFieldName(String roleACmrFieldName) {
		this.roleACmrFieldName = roleACmrFieldName;
	}

	/**
	 * Gets the roleBCascadeDelete.
	 * 
	 * @return Returns a boolean
	 */
	public boolean isRoleBCascadeDelete() {
		return roleBCascadeDelete;
	}

	/**
	 * Sets the roleBCascadeDelete.
	 * 
	 * @param roleBCascadeDelete
	 *            The roleBCascadeDelete to set
	 */
	public void setRoleBCascadeDelete(boolean roleBCascadeDelete) {
		this.roleBCascadeDelete = roleBCascadeDelete;
	}

	/**
	 * Gets the roleBCmrFieldCollectionType.
	 * 
	 * @return Returns a String
	 */
	public String getRoleBCmrFieldCollectionType() {
		return roleBCmrFieldCollectionType;
	}

	/**
	 * Sets the roleBCmrFieldCollectionType.
	 * 
	 * @param roleBCmrFieldCollectionType
	 *            The roleBCmrFieldCollectionType to set
	 */
	public void setRoleBCmrFieldCollectionType(String roleBCmrFieldCollectionType) {
		this.roleBCmrFieldCollectionType = roleBCmrFieldCollectionType;
	}

	/**
	 * Gets the roleBCmrFieldName.
	 * 
	 * @return Returns a String
	 */
	public String getRoleBCmrFieldName() {
		return roleBCmrFieldName;
	}

	/**
	 * Sets the roleBCmrFieldName.
	 * 
	 * @param roleBCmrFieldName
	 *            The roleBCmrFieldName to set
	 */
	public void setRoleBCmrFieldName(String roleBCmrFieldName) {
		this.roleBCmrFieldName = roleBCmrFieldName;
	}

	public boolean isEJB20Model() {
		return true;
	}

	public boolean isRoleAMany() {
		return getRoleAMultiplicityString().equals("Many"); //$NON-NLS-1$
	}

	public boolean isRoleBMany() {
		return getRoleBMultiplicityString().equals("Many"); //$NON-NLS-1$
	}
}