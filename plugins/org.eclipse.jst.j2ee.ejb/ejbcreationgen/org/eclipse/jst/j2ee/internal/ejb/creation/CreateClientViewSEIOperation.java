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
 * Created on May 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.creation;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddServiceEndpointInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateServiceEndpointInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientViewSEIDataModel;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

/**
 * @author sagarwal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class CreateClientViewSEIOperation extends CreateClientViewOperation {
	ClientViewSEIDataModel clientViewSEIDataModel;

	/**
	 * Constructor for CreateClientViewOperation.
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
	public CreateClientViewSEIOperation(EnterpriseBean ejbBean, ClientViewSEIDataModel aViewModel, EJBEditModel ejbEditModel, IOperationHandler operationHandler) {

		super(ejbBean, aViewModel, ejbEditModel, operationHandler);
		clientViewSEIDataModel = aViewModel;


	} // CreateClientViewOperation

	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		super.createDependentCommands(ejbCommand);
		if (this.shouldCreateSEIClientViewCommand()) {
			this.createSEIClientViewCommand(ejbCommand);
		}// if
	}// createDependentCommands

	/**
	 * Says if Service Endpoint clients should be created.
	 * 
	 * @return boolean
	 */

	private boolean shouldCreateSEIClientViewCommand() {
		boolean shouldCreateSEI;

		if (!clientViewSEIDataModel.shouldCreateSEICommand())
			return false;

		shouldCreateSEI = (((Session) getClientViewModel().getEjbBean()).getServiceEndpoint() == null) || (((Session) getClientViewModel().getEjbBean()).getServiceEndpointName() == null && ((Session) getClientViewModel().getEjbBean()).getServiceEndpointName().length() < 0);

		return shouldCreateSEI;
	}

	/**
	 * Creates the home create command.
	 * 
	 * @param EnterpriseBeanCommand
	 *            ejbCommand - The create command to perform.
	 */
	protected void createSEIClientViewCommand(EnterpriseBeanCommand ejbCommand) {

		JavaClass seiInterface = ((Session) getClientViewModel().getEjbBean()).getServiceEndpoint();

		if (seiInterface != null) {
			new AddServiceEndpointInterfaceCommand(ejbCommand, seiInterface);
		} else {
			String seiName = clientViewSEIDataModel.getSEIExistingName();

			if (seiName != null && seiName.length() > 0) {
				CreateServiceEndpointInterfaceCommand serviceEndpointInterfaceCmd = new CreateServiceEndpointInterfaceCommand(ejbCommand, this.getSimpleName(seiName), this.getPackageName(seiName));

				serviceEndpointInterfaceCmd.setMethodCollection(this.getClientViewModel().getMethodCollection());
			} // if
		} // if
	} // createSEIClientViewCommand
}