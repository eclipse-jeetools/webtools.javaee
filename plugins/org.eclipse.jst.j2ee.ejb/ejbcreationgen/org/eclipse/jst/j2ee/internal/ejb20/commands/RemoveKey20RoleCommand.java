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


public class RemoveKey20RoleCommand extends Persistent20RoleCommand {
	/**
	 * Constructor for RemoveKey20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 */
	public RemoveKey20RoleCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	public RemoveKey20RoleCommand(IRootCommand parent, EJBRelationshipRole aRole) {
		super(parent, aRole.getName());
		setRole(aRole);
		setRoleAttributesDerived(true);
	}

	/**
	 * Constructor for RemoveKey20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 */
	public RemoveKey20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * Constructor for RemoveKey20RoleCommand.
	 * 
	 * @param parent
	 * @param aName
	 * @param shouldGenJava
	 * @param shouldGenMetadata
	 */
	public RemoveKey20RoleCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	/**
	 * Remove the key feature from the list of key features with the entity.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		//removeREADintents() ;
		removeRoleFromKey();
		//addREADintents() ;
	}

	/**
	 * Initialize the key attribute field.
	 */
	protected void initializeKey() {
		setKey(false);
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
	 * Add the feature as a key feature with the entity.
	 */
	protected void undoMetadataGeneration() {
		addRoleToKey();
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