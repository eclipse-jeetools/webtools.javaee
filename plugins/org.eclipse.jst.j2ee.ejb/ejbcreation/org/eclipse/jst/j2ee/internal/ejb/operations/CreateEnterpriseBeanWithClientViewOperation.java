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
import org.eclipse.jst.j2ee.internal.ejb.commands.AddHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.AddRemoteInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreateRemoteInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBClassReferenceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.AddLocalHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.AddLocalInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateLocalHomeInterfaceCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateLocalInterfaceCommand;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class CreateEnterpriseBeanWithClientViewOperation extends CreateEnterpriseBeanOperation {
	/**
	 * @param dataModel
	 */
	public CreateEnterpriseBeanWithClientViewOperation(CreateEnterpriseBeanWithClientViewDataModel dataModel) {
		super(dataModel);
	}

	/**
	 *  
	 */
	public CreateEnterpriseBeanWithClientViewOperation() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanOperatoin#createDependentCommands(org.eclipse.jst.j2ee.commands.EnterpriseBeanCommand)
	 */
	protected void createDependentCommands(EnterpriseBeanCommand ejbCommand) {
		super.createDependentCommands(ejbCommand);
		createRemoteCommands(ejbCommand);
		createLocalCommands(ejbCommand);
	}

	/**
	 * @param ejbCommand
	 */
	private void createLocalCommands(EnterpriseBeanCommand ejbCommand) {
		if (operationDataModel.getBooleanProperty(CreateEnterpriseBeanWithClientViewDataModel.ADD_LOCAL)) {
			createLocalHomeCommand(ejbCommand);
			createLocalCommand(ejbCommand);
		}
	}

	/**
	 * @param ejbCommand
	 */
	private void createLocalHomeCommand(EnterpriseBeanCommand ejbCommand) {
		String homeName = operationDataModel.getStringProperty(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_HOME);
		JavaClass homeClass = reflectJavaClass(homeName);
		if (shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_HOME))
			new CreateLocalHomeInterfaceCommand(ejbCommand, homeClass);
		else
			new AddLocalHomeInterfaceCommand(ejbCommand, homeClass);
	}

	/**
	 * @param ejbCommand
	 */
	private void createLocalCommand(EnterpriseBeanCommand ejbCommand) {
		String localName = operationDataModel.getStringProperty(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_INTERFACE);
		JavaClass localClass = reflectJavaClass(localName);
		if (shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_INTERFACE)) {
			IEJBClassReferenceCommand command = new CreateLocalInterfaceCommand(ejbCommand, localClass);
			addSuperInterfaces(command, CreateEnterpriseBeanWithClientViewDataModel.LOCAL_INTERFACE_EXTENSIONS);
		} else
			new AddLocalInterfaceCommand(ejbCommand, localClass);
	}

	/**
	 * @param ejbCommand
	 */
	private void createRemoteCommands(EnterpriseBeanCommand ejbCommand) {
		if (operationDataModel.getBooleanProperty(CreateEnterpriseBeanWithClientViewDataModel.ADD_REMOTE)) {
			createHomeCommand(ejbCommand);
			createRemoteCommand(ejbCommand);
		}
	}

	/**
	 *  
	 */
	private void createHomeCommand(EnterpriseBeanCommand ejbCommand) {
		String homeName = operationDataModel.getStringProperty(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_HOME);
		JavaClass homeClass = reflectJavaClass(homeName);
		if (shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_HOME))
			new CreateHomeInterfaceCommand(ejbCommand, homeClass);
		else
			new AddHomeInterfaceCommand(ejbCommand, homeClass);
	}

	/**
	 *  
	 */
	private void createRemoteCommand(EnterpriseBeanCommand ejbCommand) {
		String remoteName = operationDataModel.getStringProperty(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_INTERFACE);
		JavaClass remoteClass = reflectJavaClass(remoteName);
		if (shouldGenerateClass(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_INTERFACE)) {
			IEJBClassReferenceCommand command = new CreateRemoteInterfaceCommand(ejbCommand, remoteClass);
			addSuperInterfaces(command, CreateEnterpriseBeanWithClientViewDataModel.REMOTE_INTERFACE_EXTENSIONS);
		} else
			new AddRemoteInterfaceCommand(ejbCommand, remoteClass);
	}

	protected void addSuperInterfaces(IEJBClassReferenceCommand interfaceCommand, String propertyName) {
		if (interfaceCommand == null)
			return;
		String[] interfaces = (String[]) operationDataModel.getProperty(propertyName);
		if (interfaces == null)
			return;
		for (int i = 0; i < interfaces.length; i++)
			interfaceCommand.addSuperInterfaceName(interfaces[i]);
	}
}