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
 * Insert the type's description here. Creation date: (6/28/2001 10:47:44 PM)
 * 
 * @author: Administrator
 */
public class DeleteSessionCommand extends SessionCommand {
	/**
	 * DeleteSessionCommand constructor comment.
	 * 
	 * @param anEjb
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.EnterpriseBean
	 * @param anEditModel
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBEditModel
	 */
	public DeleteSessionCommand(EnterpriseBean anEjb, org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel anEditModel) {
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
}