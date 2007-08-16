/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;


import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.RenameResourceAction;

public class J2EERenameResourceAction extends RenameResourceAction {
	String newName = null;

	/**
	 * Creates a new action. Using this constructor directly will rename using a dialog (if
	 * necessary) rather than the inline editor of a ResourceNavigator. Note that a rename happens
	 * on one and only one resource at a time.
	 * 
	 * @param shell
	 *            the shell for any dialogs
	 */
	public J2EERenameResourceAction(Shell shell) {
		super(shell);
	}

	/**
	 * Return the new name to be given to the target resource.
	 * 
	 * @return java.lang.String
	 */
	protected String queryNewResourceName(final IResource resource) {
		String retVal = null;

		if (newName == null || newName.length() < 1) {
			retVal = super.queryNewResourceName(resource);
		} else {
			retVal = newName;
		}

		return retVal;
	}

	/**
	 * Gets the newName.
	 * 
	 * @return Returns a String
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * Sets the newName.
	 * 
	 * @param newName
	 *            The newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}

}