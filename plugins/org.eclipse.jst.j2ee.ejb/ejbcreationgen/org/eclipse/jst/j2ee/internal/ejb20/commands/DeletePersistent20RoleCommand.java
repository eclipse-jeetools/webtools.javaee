/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;


public class DeletePersistent20RoleCommand extends Persistent20RoleCommand {
	/**
	 * Constructor for DeletePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aRole
	 */
	public DeletePersistent20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole) {
		super(parent, aRole.getName());
		setCommonRole(aRole);
	}

	/**
	 * Constructor for DeletePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 */
	public DeletePersistent20RoleCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * Constructor for DeletePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 */
	public DeletePersistent20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * Constructor for DeletePersistent20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public DeletePersistent20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		//deleteEJBReference();
		removeRoleFromKeyIfNecessary();
		EJBRelationshipRole role = getEJBRelationshipRole();
		if (role == null)
			initializeRoleFromName();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setDelete();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		getInverseHelper().setCreate();
	}

	/**
	 * Undo the the removal of the persistent role as well as the addition to the key features if
	 * necessary.
	 */
	protected void undoMetadataGeneration() {
		//createEJBReferenceIfNecessary();
		addRoleToKeyIfNecessary();
		super.undoMetadataGeneration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.PersistentRoleCommand#isDeleteCommand()
	 */
	public boolean isDeleteCommand() {
		return true;
	}
}