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
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (9/6/2000 9:56:15 AM)
 * 
 * @author: Administrator
 */
public class CreateEntityCommand extends EntityCommand {
	/**
	 * Constructor method for an CreateEntityCommand.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public CreateEntityCommand(String aName, EJBEditModel anEditModel) {
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
}