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
 * Created on Jan 20, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddServiceEndpointInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateServiceEndpointInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateSessionCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.SessionCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateSessionBeanOperation extends CreateEnterpriseBeanWithClientViewOperation {
	/**
	 * @param dataModel
	 */
	public CreateSessionBeanOperation(CreateSessionBeanDataModel dataModel) {
		super(dataModel);
	}

	/**
	 *  
	 */
	public CreateSessionBeanOperation() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperatoin#createRootCommand()
	 */
	protected EnterpriseBeanCommand createRootCommand(String beanName) {
		return new CreateSessionCommand(beanName, (EJBEditModel) editModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperatoin#createDependentCommands(org.eclipse.jst.j2ee.commands.EnterpriseBeanCommand)
	 */
	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		super.createDependentCommands(ejbCommand);
		createServiceEndpointCommands(ejbCommand);
	}

	/**
	 * @param ejbCommand
	 */
	private void createServiceEndpointCommands(EnterpriseBeanCommand ejbCommand) {
		if (operationDataModel.getBooleanProperty(CreateSessionBeanDataModel.ADD_SERVICE_ENDPOINT)) {
			String serviceEndpointName = operationDataModel.getStringProperty(CreateSessionBeanDataModel.SERVICE_ENDPOINT_INTERFACE);
			JavaClass serviceEndpointClass = reflectJavaClass(serviceEndpointName);
			if (shouldGenerateClass(CreateSessionBeanDataModel.SERVICE_ENDPOINT_INTERFACE))
				new CreateServiceEndpointInterfaceCommand(ejbCommand, serviceEndpointClass);
			else
				new AddServiceEndpointInterfaceCommand(ejbCommand, serviceEndpointClass);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperatoin#initializeRootCommand(org.eclipse.jst.j2ee.commands.EnterpriseBeanCommand)
	 */
	protected void initializeRootCommand(EnterpriseBeanCommand ejbCommand) {
		super.initializeRootCommand(ejbCommand);
		SessionCommand sessionCommand = (SessionCommand) ejbCommand;
		//Set Session Type
		SessionType sessType = (SessionType) operationDataModel.getProperty(CreateSessionBeanDataModel.SESSION_TYPE);
		sessionCommand.setIsStateful(sessType == SessionType.STATEFUL_LITERAL);
		//Set Transaction Type
		TransactionType transType = (TransactionType) operationDataModel.getProperty(CreateSessionBeanDataModel.TRANSACTION_TYPE);
		sessionCommand.setIsContainerManaged(transType == TransactionType.CONTAINER_LITERAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperation#createTemplateModel()
	 */
	protected CreateEnterpriseBeanTemplateModel createTemplateModel() {
		return new CreateSessionBeanTemplateModel((CreateSessionBeanDataModel) operationDataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperation#getTemplateFileName()
	 */
	protected String getTemplateFileName() {
		return "sessionXDoclet.javajet"; //$NON-NLS-1$
	}
}