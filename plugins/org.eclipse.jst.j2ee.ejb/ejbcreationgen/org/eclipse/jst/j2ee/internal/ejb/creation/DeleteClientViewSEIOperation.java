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
/*
 * Created on May 10, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.creation;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.DeleteClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.RemoveClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientViewSEIDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;

/**
 * @author sagarwal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class DeleteClientViewSEIOperation extends DeleteClientViewOperation {

	ClientViewSEIDataModel clientViewSEIDataModel;

	/**
	 * @param ejbBean
	 * @param aViewModel
	 * @param anEditModel
	 * @param anOperationHandler
	 */
	public DeleteClientViewSEIOperation(EnterpriseBean ejbBean, ClientViewSEIDataModel aViewModel, EJBEditModel anEditModel, IOperationHandler anOperationHandler) {
		super(ejbBean, aViewModel, anEditModel, anOperationHandler);
		clientViewSEIDataModel = aViewModel;
	}

	/**
	 * Creates the commands.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - Command to be created.
	 */
	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		super.createDependentCommands(ejbCommand);
		if (this.shouldCreateSEIClientViewCommands()) {
			this.createSEIClientViewCommand(ejbCommand);
		}

	}// createDependentCommands

	/**
	 * Says if remote clients should be deleted.
	 * 
	 * @return boolean
	 */
	protected boolean shouldCreateSEIClientViewCommands() {
		return clientViewSEIDataModel.shouldDeleteSEI();
	}// shouldCreateSEIClientViewCommands

	/**
	 * Calls the delete or remove command for remote interfaces.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The current command.
	 */
	protected void createSEIClientViewCommand(EnterpriseBeanCommand ejbCommand) {
		if (clientViewSEIDataModel.shouldDelete()) {
			createDeleteCommand(ejbCommand, clientViewSEIDataModel.getSEIExisting(), DeleteClassReferenceCommand.SERVICE_ENDPOINT);
		} else {
			createRemoveCommand(ejbCommand, clientViewSEIDataModel.getSEIExisting(), RemoveClassReferenceCommand.SERVICE_ENDPOINT);
		}// if
	}// createRemoteCommand
}