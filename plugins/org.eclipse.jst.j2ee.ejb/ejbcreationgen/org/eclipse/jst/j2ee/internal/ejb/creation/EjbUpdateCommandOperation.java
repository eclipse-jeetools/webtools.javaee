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


import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.internal.operations.EjbModificationOperation;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCommandHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;

/**
 * Does updates on an EJB.
 * 
 * @deprecated
 */
public abstract class EjbUpdateCommandOperation extends EjbModificationOperation {

	protected EnterpriseBean ejbBean;

	/**
	 * Constructor for AbstractClientViewOperation.
	 * 
	 * @param ejbBean -
	 *            The EJB to modify.
	 * @param ejbEditModel -
	 *            The EJB edit model.
	 * @param operationHandler -
	 *            The current operation handler.
	 */
	public EjbUpdateCommandOperation(EnterpriseBean ejbBean, EJBEditModel anEditModel, IOperationHandler anOperationHandler) {
		super(anEditModel, anOperationHandler);
		this.setEjbBean(ejbBean);
	}// EjbUpdateCommandOperation

	/**
	 * Constructs the commands
	 * 
	 * @return IEJBCommand
	 */
	protected IEJBCommand createCommand() {
		EnterpriseBeanCommand ejbCommand = createRootCommand();
		if (ejbCommand != null) {
			createDependentCommands(ejbCommand);
		}// if
		return ejbCommand;
	}// createCommand

	/**
	 * Constructs the root ejb command.
	 * 
	 * @return EnterpriseBeanCommand
	 */
	protected EnterpriseBeanCommand createRootCommand() {
		EnterpriseBeanCommand rootCommand = (EnterpriseBeanCommand) EJBCommandHelper.createEnterpriseBeanUpdateCommand(this.getEjbBean(), getEditModel());
		if (rootCommand != null) {
			rootCommand.setOperationHandler(getOperationHandler());
		}// if
		return rootCommand;
	}// createRooteCommand

	/**
	 * Creates the local create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected abstract void createDependentCommands(EnterpriseBeanCommand ejbCommand);

	/**
	 * Gets the EJB.
	 * 
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEjbBean() {
		return ejbBean;
	}// getEjbBean

	/**
	 * Sets the EJB.
	 * 
	 * @param EnterpriseBean -
	 *            The EJB to set
	 */
	public void setEjbBean(EnterpriseBean ejbBean) {
		this.ejbBean = ejbBean;
	}// setEjbBean

}// EjbUpdateCommandOperation
