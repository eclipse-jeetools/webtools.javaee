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


import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;

/**
 * Insert the type's description here. Creation date: (8/22/2000 8:21:39 AM)
 * 
 * @author: Administrator
 */
public class CreateSessionCommand extends SessionCommand {
	/**
	 * Constructor method for an CreateSessionCommand.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public CreateSessionCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	public CreateSessionCommand(String aName, EJBEditModel anEditModel, boolean isStatefull, boolean isContainerType) {
		super(aName, anEditModel);
		setIsStateful(isStatefull);
		setIsContainerManaged(isContainerType);
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:20:02 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTaskName() {
		return getCreatingTaskName();
	}

	public boolean isCreateCommand() {
		return true;
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateJava() {
		return shouldGenerateJavaForModify();
	}

	/*
	 * @see EnterpriseBeanCommand#executeForMetadataGeneration()
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		getSession().setSessionType(getSessionType());
		getSession().setTransactionType(getTransactionType());
	}

}