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



import org.eclipse.jst.j2ee.common.CMPJavaChangeSynchronizationAdapter;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;


/**
 * Insert the type's description here. Creation date: (9/11/2000 1:41:21 PM)
 * 
 * @author: Administrator
 */
public class RemoveKeyAttributeCommand extends PersistentAttributeCommand {
	/**
	 * RemoveKeyAttributeCommand constructor comment.
	 * 
	 * @param parent
	 *            org.eclipse.jst.j2ee.commands.IEJBCommand
	 * @param aName
	 *            java.lang.String
	 */
	public RemoveKeyAttributeCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public RemoveKeyAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public RemoveKeyAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	/**
	 * Remove the key feature from the list of key features with the entity.
	 */
	protected void executeForMetadataGeneration() {
		super.executeForMetadataGeneration();
		if (getContainerManagedEntity() == null) {
			nullEJBError();
			return;
		}
		if (getAttribute() == null)
			nullFeatureRemoveKeyError();
		else if (isKey()) {
			if (getAttribute().isPrimKeyField()) {
				getContainerManagedEntity().setPrimKeyField(null);
			}
			getContainerManagedEntity().getKeyAttributes().remove(getAttribute());
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
		((AttributeHelper) getHelper()).setUpdateGeneratorName(IEJBGenConstants.ENTITY_ATTRIBUTE_REMOVE_FROM_KEY);
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
		if (getAttribute() != null && getContainerManagedEntity() != null)
			getContainerManagedEntity().getKeyAttributes().add(getAttribute());
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