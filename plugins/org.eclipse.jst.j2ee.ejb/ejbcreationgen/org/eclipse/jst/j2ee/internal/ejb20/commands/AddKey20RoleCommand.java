/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.common.CMPJavaChangeSynchronizationAdapter;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;


public class AddKey20RoleCommand extends Persistent20RoleCommand {
	/**
	 * Constructor for AddKey20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 */
	public AddKey20RoleCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	public AddKey20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole) {
		super(parent, aRole.getName());
		setRole(aRole);
	}

	/**
	 * Constructor for AddKey20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 */
	public AddKey20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * Constructor for AddKey20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public AddKey20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	/**
	 * Add the persistent role to the keyFeatures of the EJB. If persistent role does not exist, one
	 * will be created.
	 */
	protected void executeForMetadataGeneration() {
		getCommonRole().reconcileAttributes();
		if (getCommonRole().getAttributes().isEmpty()) {
			setGenerateJava(false); //we are in an invalid state
			return;
		}
		super.executeForMetadataGeneration();
		setRoleAttributesDerived(false);
		addRoleToKey();
	}

	/**
	 * Initialize the key attribute field.
	 */
	protected void initializeKey() {
		setKey(true);
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		getHelper().setUpdate();
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		getInverseHelper().setUpdate();
	}

	/**
	 * Undo the the addition of the persistent key feature. If the persistent role was created,
	 * remove the persistent attribute as well.
	 */
	protected void undoMetadataGeneration() {
		removeRoleFromKey();
		super.undoMetadataGeneration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		//Disable notifications to key synch
		CMPJavaChangeSynchronizationAdapter.disable((ContainerManagedEntity) getEjb());
		super.execute();
	}
}