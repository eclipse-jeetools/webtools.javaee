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


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientViewModel;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

/**
 * Defines operations for removing and adding interfaces to an EJB.
 * 
 * @deprecated
 */
public abstract class AbstractClientViewOperation extends EjbUpdateCommandOperation {

	private ClientViewModel clientViewModel;

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
	public AbstractClientViewOperation(EnterpriseBean ejbBean, ClientViewModel aViewModel, EJBEditModel ejbEditModel, IOperationHandler operationHandler) {

		super(ejbBean, ejbEditModel, operationHandler);
		this.setClientViewModel(aViewModel);
		this.setEjbBean(this.getClientViewModel().getEjbBean());
	}// AbstractClientViewOperation

	/**
	 * Creates the commands.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - Command to be created.
	 */
	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		if (this.shouldCreateRemoteClientViewCommands()) {
			this.createRemoteClientViewCommands(ejbCommand);
		}// if
		if (this.shouldCreateLocalClientViewCommands()) {
			this.createLocalClientViewCommands(ejbCommand);
		}// if
	}// createDependentCommands

	/**
	 * Says if remote clients should be created.
	 * 
	 * @return boolean
	 */
	protected boolean shouldCreateRemoteClientViewCommands() {

		boolean shouldCreateRemoteOne;
		boolean shouldCreateRemoteTwo;


		if (!getClientViewModel().shouldCreateRemoteCommand())
			return false;

		shouldCreateRemoteOne = (this.getClientViewModel().getEjbBean().getHomeInterface() == null) || (this.getClientViewModel().getEjbBean().getHomeInterfaceName() == null && this.getClientViewModel().getEjbBean().getHomeInterfaceName().length() < 0);

		shouldCreateRemoteTwo = (this.getClientViewModel().getEjbBean().getRemoteInterface() == null) || (this.getClientViewModel().getEjbBean().getRemoteInterfaceName() == null && this.getClientViewModel().getEjbBean().getRemoteInterfaceName().length() < 0);

		return shouldCreateRemoteOne && shouldCreateRemoteTwo;
	}// shouldCreateRemoteClientViewCommands

	/**
	 * Says if local clients should be created.
	 * 
	 * @return boolean
	 */
	protected boolean shouldCreateLocalClientViewCommands() {

		boolean shouldCreateLocalOne;
		boolean shouldCreateLocalTwo;

		if (!getClientViewModel().shouldCreateLocalCommand())
			return false;

		shouldCreateLocalOne = (this.getClientViewModel().getEjbBean().getLocalHomeInterface() == null) || (this.getClientViewModel().getEjbBean().getLocalHomeInterfaceName() == null && this.getClientViewModel().getEjbBean().getLocalHomeInterfaceName().length() < 0);

		shouldCreateLocalTwo = (this.getClientViewModel().getEjbBean().getLocalInterface() == null) || (this.getClientViewModel().getEjbBean().getLocalInterfaceName() == null && this.getClientViewModel().getEjbBean().getLocalInterfaceName().length() < 0);

		return shouldCreateLocalOne && shouldCreateLocalTwo;
	}// shouldCreateLocalClientViewCommands

	/**
	 * Creates all the remote commands.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The remote command to perform.
	 */
	protected void createRemoteClientViewCommands(EnterpriseBeanCommand ejbCommand) {
		this.createHomeCommand(ejbCommand);
		this.createRemoteCommand(ejbCommand);
	}// createRemoteClientViewCommands

	/**
	 * Creates all the local commands.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The local command to perform.
	 */
	protected void createLocalClientViewCommands(EnterpriseBeanCommand ejbCommand) {
		this.createLocalHomeCommand(ejbCommand);
		this.createLocalCommand(ejbCommand);
	}// createLocalClientViewCommands

	/**
	 * Gets the simple name of the signature.
	 * 
	 * @param String
	 *            aQualifiedName - Full qualified name.
	 * @return String
	 */
	protected String getSimpleName(String aQualifiedName) {
		return Signature.getSimpleName(aQualifiedName);
	}// getSimpleName

	/**
	 * Gets the simple name of the package.
	 * 
	 * @param String
	 *            aQualifiedName - Full qualified name.
	 * @return String
	 */
	protected String getPackageName(String aQualifiedName) {
		return Signature.getQualifier(aQualifiedName);
	}// getPackageName

	/**
	 * Gets the client view model.
	 * 
	 * @return ClientViewModel
	 */
	public ClientViewModel getClientViewModel() {
		return clientViewModel;
	}// getViewModel

	/**
	 * Sets the client view model.
	 * 
	 * @param ClientViewModel -
	 *            The client view model to set
	 */
	public void setClientViewModel(ClientViewModel viewModel) {
		this.clientViewModel = viewModel;
	}// setViewModel

	/**
	 * Creates the home create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected abstract void createHomeCommand(EnterpriseBeanCommand ejbCommand);

	/**
	 * Creates the remote create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected abstract void createRemoteCommand(EnterpriseBeanCommand ejbCommand);

	/**
	 * Creates the local home create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected abstract void createLocalHomeCommand(EnterpriseBeanCommand ejbCommand);

	/**
	 * Creates the local create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected abstract void createLocalCommand(EnterpriseBeanCommand ejbCommand);

	protected void dispose(IProgressMonitor monitor) {
		try {
			doDispose(monitor);
		} finally {
			saveEditModel(monitor);
		}
	}

	protected void doDispose(IProgressMonitor monitor) {
	}

	private final void saveEditModel(IProgressMonitor monitor) {
		if (null != getEditModel()) {
			getEditModel().saveIfNecessary(monitor, this);
		}
		postSaveEditModel(monitor);
	}

	/**
	 * @param monitor
	 */
	protected void postSaveEditModel(IProgressMonitor monitor) {
		// do nothing by default
	}

}// AbstractClientViewOperation
