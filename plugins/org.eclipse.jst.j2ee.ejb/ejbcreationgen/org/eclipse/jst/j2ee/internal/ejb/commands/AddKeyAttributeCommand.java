/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;



/**
 * Insert the type's description here. Creation date: (9/11/2000 8:50:40 AM)
 * 
 * @author: Administrator
 */

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.common.CMPJavaChangeSynchronizationAdapter;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;


public class AddKeyAttributeCommand extends PersistentAttributeCommand {
	/**
	 * CreateKeyAttributeCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.commands.ICommand
	 * @param aName
	 *            java.lang.String
	 */
	public AddKeyAttributeCommand(IRootCommand parent, String aName) {
		super(parent, aName);
		setKey(true);
	}

	/**
	 * CreateKeyAttributeCommand constructor comment.
	 */
	public AddKeyAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * CreateKeyAttributeCommand constructor comment.
	 */
	public AddKeyAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	/**
	 * Add the persistent attribute to the keyFeatures of the EJB. If persistent attribute does not
	 * exist, one will be created.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		if (getContainerManagedEntity() == null) {
			nullEJBError();
			return;
		}
		if (getAttribute() == null)
			nullFeatureAddKeyError();
		else {
			List keysCache = new ArrayList();
			// hack to outsmart key synch adapter to remove prim key and keep the two key attributes
			List keys = getContainerManagedEntity().getKeyAttributes();
			keys.add(getAttribute());
			keysCache.addAll(keys);
			if (keys.size() == 2 && getContainerManagedEntity().getPrimKeyField() != null) {
				getContainerManagedEntity().setPrimKeyField(null);
				keys.add(keysCache.get(0));
				keys.add(keysCache.get(1));
			}
		}
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
		((AttributeHelper) getInverseHelper()).setUpdateGeneratorName(IEJBGenConstants.ENTITY_ATTRIBUTE_REMOVE_FROM_KEY);
	}

	/**
	 * The undo method for all persistent feature remove commands are going to be the same, so it
	 * has been abstracted to the PersistentFeatureCommand class. It is necessary to call this
	 * method from the undo method for each subclassed Create...Command.
	 */
	protected void undoMetadataGeneration() {
		if (getAttribute() != null && getContainerManagedEntity() != null)
			getContainerManagedEntity().getKeyAttributes().remove(getAttribute());
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