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


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (8/22/2000 8:22:01 AM)
 * 
 * @author: Administrator
 */
public class CreateContainerManagedEntityCommand extends ContainerManagedEntityCommand {
	protected static final String DEFAULT_DATASOURCE_JNDI = "jdbc/Default"; //$NON-NLS-1$

	/**
	 * Constructor method for an CreateContainerManagedEntityCommand.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public CreateContainerManagedEntityCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	/**
	 * Return the copy for aRefObject from the <code>copyUtility</code>.
	 */
	public EObject getCopy(EObject aRefObject) {
		return null;
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


	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected EnterpriseBean createEJB() {
		ContainerManagedEntity cmp = (ContainerManagedEntity) super.createEJB();
		cmp.setVersion(ContainerManagedEntity.VERSION_1_X);
		return cmp;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.ContainerManagedEntityCommand#postExecuteChildren()
	 */
	protected void postExecuteChildren() {
		super.postExecuteChildren();
		//  if (addDefaultDatasourceBinding)
		//  	createDefaultDatasourceBinding();
	}



}