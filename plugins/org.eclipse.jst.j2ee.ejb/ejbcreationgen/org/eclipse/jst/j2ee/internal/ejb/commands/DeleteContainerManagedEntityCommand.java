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



import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;


/**
 * Insert the type's description here. Creation date: (6/28/2001 11:00:34 PM)
 * 
 * @author: Administrator
 */
public class DeleteContainerManagedEntityCommand extends ContainerManagedEntityCommand {
	/**
	 * DeleteContainerManagedEntityCommand constructor comment.
	 * 
	 * @param anEjb
	 *            org.eclipse.jst.j2ee.ejb.EnterpriseBean
	 * @param anEditModel
	 *            org.eclipse.jst.j2ee.ejb.ejbproject.EJBEditModel
	 */
	public DeleteContainerManagedEntityCommand(EnterpriseBean anEjb, org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/**
	 * This calls {@link Command#dispose}for each command in the list.
	 */
	public void dispose() {

	}

	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected void executeForMetadataGeneration() {
		deleteEntireEnterpriseBean();
	}

	/**
	 * Insert the method's description here. Creation date: (6/28/2001 10:47:45 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getTaskName() {
		return getDeletingTaskName();
	}

	/**
	 * Initialize the code gen helper.
	 */
	protected void initializeHelper(EnterpriseBeanHelper aHelper) {
		super.initializeHelper(aHelper);
		aHelper.setDeleteAll(deleteGeneratedClassesOverrideFlag);
	}

	/**
	 * Override to return true in each subclass command that performs a delete.
	 */
	public boolean isDeleteCommand() {
		return true;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.commands.ContainerManagedEntityCommand#updatePrimaryKey()
	 */
	protected void updatePrimaryKey() {
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.commands.ContainerManagedEntityCommand#needToRegenerateSubtypes()
	 */
	protected boolean needToRegenerateSubtypes() {
		return false;
	}

}