/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


public class DeleteMessageDrivenCommand extends MessageDrivenCommand {

	/**
	 * Constructor for DeleteMessageDrivenCommand.
	 * 
	 * @param anEjb
	 * @param anEditModel
	 */
	public DeleteMessageDrivenCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/*
	 * @see AbstractEJBRootCommand#getTaskName()
	 */
	public String getTaskName() {
		return getUpdatingTaskName();
	}

	public boolean isDeleteCommand() {
		return true;
	}

	protected void initializeHelper(EnterpriseBeanHelper aHelper) {
		super.initializeHelper(aHelper);
		aHelper.setDeleteAll(deleteGeneratedClassesOverrideFlag);
	}

	public void dispose() {
	}

	protected void executeForMetadataGeneration() {
		deleteEntireEnterpriseBean();
	}
}