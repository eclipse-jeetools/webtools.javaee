/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;


public class CreatePersistent20RoleCommand extends Persistent20RoleCommand {
	/**
	 * Constructor for CreatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 */
	public CreatePersistent20RoleCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * Constructor for CreatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 */
	public CreatePersistent20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * Constructor for CreatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aRole
	 * @param shouldGenJava
	 */
	public CreatePersistent20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole, boolean shouldGenJava) {
		super(parent, aRole.getName(), shouldGenJava);
		setRoleSource(aRole);
		setRole(aRole);
	}

	/**
	 * Constructor for CreatePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public CreatePersistent20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		ContainerManagedEntity cmp = getContainerManagedEntity();
		if (cmp == null) {
			nullEJBError();
			return;
		}
		EJBRelationshipRole role = getEJBRelationshipRole();
		if (role == null) {
			role = createRelationshipRole();
			role.setRoleName(getName());
			setCommonRole(role);
			if (isCascadeDelete())
				role.setCascadeDelete(true);
			else
				role.unsetCascadeDelete();
			setRoleSource(role);
			setRoleMultiplicity(role);
		}
		addRoleToKeyIfNecessary();
		//createEJBReferenceIfNecessary();
		setCMRFieldInformation(role);
	}

	/**
	 * Method setRoleSource.
	 * 
	 * @param role
	 */
	private void setRoleSource(EJBRelationshipRole role) {
		RoleSource roleSource = getEJBFactory().createRoleSource();
		roleSource.setEntityBean(getContainerManagedEntity());
		role.setSource(roleSource);
	}

	protected void setCMRFieldInformation(EJBRelationshipRole newRole) {
		if (getCmrFieldName() != null) {
			CMRField field = getEJBFactory().createCMRField();
			field.setName(getCmrFieldName());
			if (getCmrFieldCollectionTypeName() != null)
				field.setCollectionTypeName(getCmrFieldCollectionTypeName());
			newRole.setCmrField(field);
		}
	}

	/**
	 * Undo the the addition of the persistent role as well as removal from the key features if
	 * necessary.
	 */
	protected void undoMetadataGeneration() {
		if (getEJBRelationshipRole() != null && getContainerManagedEntity() != null) {
			removeRoleFromKeyIfNecessary();
		}
	}

	protected void setupHelper() {
		super.setupHelper();

	}

	protected void setupInverseHelper() {
		super.setupInverseHelper();
		getInverseHelper().setDelete();
	}
}