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
package org.eclipse.jst.j2ee.ejb.internal.operations;



import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddPrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.DeletePrimaryKeyClassCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;


/**
 * Insert the type's description here. Creation date: (8/23/2001 3:10:51 PM)
 * 
 * @author: Administrator
 * @deprecated
 */
public class ChangeKeyClassOperation extends EjbModificationOperation {
	private ChangeKeyClassInfoProvider infoProvider;

	/**
	 * ChangeKeyClassOperation constructor comment.
	 * 
	 * @param anEditModel
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBEditModel
	 * @param anOperationHandler
	 *            org.eclipse.jst.j2ee.internal.internal.operations.IOperationHandler
	 */
	public ChangeKeyClassOperation(EJBEditModel anEditModel, ChangeKeyClassInfoProvider anInfoProvider, IOperationHandler anOperationHandler) {
		super(anEditModel, anOperationHandler);
		setInfoProvider(anInfoProvider);
	}

	/**
	 * createCommand method comment.
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand createCommand() {
		IRootCommand root, merged = null;
		if (shouldDeleteObsoleteKeyClass() && !getInfoProvider().shouldUsePrimitiveKeyAttributeType()) {
			root = createRootCommand();
			if (root != null)
				createDeleteKeyCommand(root);
			merged = root;
		}
		root = createRootCommand();
		if (root != null) {
			createDependentCommands(root);
			if (merged != null)
				merged = merged.append(root);
			else
				merged = root;
		}
		return merged;
	}

	/**
	 * Method shouldDeleteObsoleteKeyClass.
	 * 
	 * @return boolean
	 */
	private boolean shouldDeleteObsoleteKeyClass() {
		EnterpriseBean ejb = getInfoProvider().getEnterpriseBean();
		return getInfoProvider().shouldDeleteObsoleteKeyClass() && (ejb.isBeanManagedEntity() || ((ContainerManagedEntity) ejb).getPrimKeyField() == null);
	}


	protected void createDeleteKeyCommand(IRootCommand root) {
		Entity entity = (Entity) getInfoProvider().getEnterpriseBean();
		new DeletePrimaryKeyClassCommand(root, entity.getPrimaryKey());
	}

	/**
	 * createCommand method comment.
	 */
	protected void createDependentCommands(IRootCommand aRootCommand) {
		if (getInfoProvider().shouldUsePrimitiveKeyAttributeType())
			createKeyClassCommandForPrimitive(aRootCommand);
		else if (getInfoProvider().isExistingKeyClass())
			createKeyClassCommandForExisting(aRootCommand);
		else
			createKeyClassCommandForNew(aRootCommand);
	}

	/**
	 * createCommand method comment.
	 */
	protected void createKeyClassCommandForExisting(IRootCommand aRootCommand) {
		new AddPrimaryKeyClassCommand(aRootCommand, getInfoProvider().getKeyClassName(), getInfoProvider().getKeyClassPackageName());
	}

	/**
	 * createCommand method comment.
	 */
	protected void createKeyClassCommandForNew(IRootCommand aRootCommand) {
		new CreatePrimaryKeyClassCommand(aRootCommand, getInfoProvider().getKeyClassName(), getInfoProvider().getKeyClassPackageName());
	}

	/**
	 * We need to delete the old key class. The command framework will handle the rest.
	 */
	protected void createKeyClassCommandForPrimitive(IRootCommand aRootCommand) {
		EnterpriseBean ejb = getInfoProvider().getEnterpriseBean();
		if (ejb.isContainerManagedEntity()) {
			DeletePrimaryKeyClassCommand command;
			ContainerManagedEntity cmp = (ContainerManagedEntity) ejb;
			command = new DeletePrimaryKeyClassCommand(aRootCommand, cmp.getPrimaryKey());
			if (!getInfoProvider().shouldDeleteObsoleteKeyClass())
				command.setGenerateJava(false);
		}
	}

	/**
	 * createCommand method comment.
	 */
	protected IRootCommand createRootCommand() {
		EnterpriseBean ejb = getInfoProvider().getEnterpriseBean();
		return primCreateRootCommand(ejb);
	}

	protected IRootCommand primCreateRootCommand(EnterpriseBean ejb) {
		if (ejb.isBeanManagedEntity())
			return new UpdateEntityCommand(ejb, getEditModel());
		else if (ejb.isContainerManagedEntity())
			return new UpdateContainerManagedEntityCommand(ejb, getEditModel());
		return null;
	}

	/**
	 * Return a String for error message if the operation fails.
	 */
	protected String errorMessage() {
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (8/23/2001 3:24:09 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.ejb.internal.operations.ChangeKeyClassInfoProvider
	 */
	protected ChangeKeyClassInfoProvider getInfoProvider() {
		return infoProvider;
	}

	/**
	 * Insert the method's description here. Creation date: (8/23/2001 3:24:09 PM)
	 * 
	 * @param newInfoProvider
	 *            org.eclipse.jst.j2ee.internal.ejb.internal.operations.ChangeKeyClassInfoProvider
	 */
	protected void setInfoProvider(ChangeKeyClassInfoProvider newInfoProvider) {
		infoProvider = newInfoProvider;
	}
}