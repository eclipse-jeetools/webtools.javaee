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
package org.eclipse.jst.j2ee.internal.ejb.creation;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.DeleteClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.RemoveClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientViewModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;

/**
 * Creates the delete operation for the client views.
 * 
 * @deprecated
 */
public class DeleteClientViewOperation extends AbstractClientViewOperation {

	/**
	 * Constructor for AbstractClientViewOperation.
	 * 
	 * @param ejbBean -
	 *            The EJB to modify.
	 * @param aViewModel -
	 *            The ClientViewModel.
	 * @param ejbEditModel -
	 *            The EJB edit model.
	 * @param operationHandler -
	 *            The current operation handler.
	 */
	public DeleteClientViewOperation(EnterpriseBean ejbBean, ClientViewModel aViewModel, EJBEditModel anEditModel, IOperationHandler anOperationHandler) {
		super(ejbBean, aViewModel, anEditModel, anOperationHandler);
	}// DeleteClientViewOperation

	/**
	 * Performs a delete command on passed in class.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The current command.
	 * @param JavaClass
	 *            aJavaClass - The class to delete.
	 * @param EStructuralFeature
	 *            aType - The the type of class to delete.
	 * @return DeleteClassReferenceCommand
	 */
	protected DeleteClassReferenceCommand createDeleteCommand(EnterpriseBeanCommand ejbCommand, JavaClass aJavaClass, EStructuralFeature aType) {
		return new DeleteClassReferenceCommand(ejbCommand, aJavaClass, aType);
	}// createDeleteCommand


	/**
	 * Performs a remove command on passed in class.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The current command.
	 * @param JavaClass
	 *            aJavaClass - The class to remove.
	 * @param EStructuralFeature
	 *            aType - The the type of class to remove.
	 * @return RemoveClassReferenceCommand
	 */
	protected RemoveClassReferenceCommand createRemoveCommand(EnterpriseBeanCommand ejbCommand, JavaClass aJavaClass, EStructuralFeature aType) {
		return new RemoveClassReferenceCommand(ejbCommand, aJavaClass, aType);
	}// createRemoveCommand


	/**
	 * Creates the commands.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - Command to be created.
	 */
	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		if (this.shouldCreateRemoteClientViewCommands()) {
			this.createRemoteClientViewCommands(ejbCommand);
		}
		if (this.shouldCreateLocalClientViewCommands()) {
			this.createLocalClientViewCommands(ejbCommand);
		}// if
	}// createDependentCommands

	/**
	 * Calls the delete or remove command for home interfaces.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The current command.
	 */
	protected void createHomeCommand(EnterpriseBeanCommand ejbCommand) {
		if (this.getClientViewModel().shouldDelete()) {
			createDeleteCommand(ejbCommand, this.getClientViewModel().getHomeInterfaceExisting(), DeleteClassReferenceCommand.HOME);
		} else {
			createRemoveCommand(ejbCommand, this.getClientViewModel().getHomeInterfaceExisting(), RemoveClassReferenceCommand.HOME);
		}// if
	}// createHomeCommand

	/**
	 * Says if remote clients should be deleted.
	 * 
	 * @return boolean
	 */
	protected boolean shouldCreateRemoteClientViewCommands() {
		return this.getClientViewModel().shouldDeleteRemote();
	}// shouldCreateRemoteClientViewCommands

	/**
	 * Says if local clients should be deleted.
	 * 
	 * @return boolean
	 */
	protected boolean shouldCreateLocalClientViewCommands() {
		return this.getClientViewModel().shouldDeleteLocal();
	}// shouldCreateLocalClientViewCommands

	/**
	 * Calls the delete or remove command for remote interfaces.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The current command.
	 */
	protected void createRemoteCommand(EnterpriseBeanCommand ejbCommand) {
		if (this.getClientViewModel().shouldDelete()) {
			createDeleteCommand(ejbCommand, this.getClientViewModel().getRemoteInterfaceExisting(), DeleteClassReferenceCommand.REMOTE);
		} else {
			createRemoveCommand(ejbCommand, this.getClientViewModel().getRemoteInterfaceExisting(), RemoveClassReferenceCommand.REMOTE);
		}// if
	}// createRemoteCommand

	/**
	 * Creates the local home delete command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createLocalHomeCommand(EnterpriseBeanCommand ejbCommand) {
		if (this.getClientViewModel().shouldDelete()) {
			createDeleteCommand(ejbCommand, this.getClientViewModel().getLocalHomeInterfaceExisting(), DeleteClassReferenceCommand.LOCAL_HOME);
		} else {
			createRemoveCommand(ejbCommand, this.getClientViewModel().getLocalHomeInterfaceExisting(), RemoveClassReferenceCommand.LOCAL_HOME);
		}// if
	}// createLocalHomeCommand

	/**
	 * Creates the local delete command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createLocalCommand(EnterpriseBeanCommand ejbCommand) {
		if (this.getClientViewModel().shouldDelete()) {
			createDeleteCommand(ejbCommand, this.getClientViewModel().getLocalInterfaceExisting(), DeleteClassReferenceCommand.LOCAL);
		} else {
			createRemoveCommand(ejbCommand, this.getClientViewModel().getLocalInterfaceExisting(), RemoveClassReferenceCommand.LOCAL);
		}// if
	}// createLocalCommand

	/**
	 * Gets an error message.
	 * 
	 * @return String
	 */
	protected String errorMessage() {
		return null;
	}// errorMessage

}// DeleteClientViewOperation
