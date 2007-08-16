/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.listeners;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPartListener;
import org.eclipse.wst.common.internal.emfworkbench.validateedit.ResourceStateValidatorPresenter;


public interface IValidateEditListener extends ResourceStateValidatorPresenter, IPartListener, ShellListener {
	/**
	 * This method should be called by any action that is about to edit any contents of any IFile.
	 */
	public IStatus validateState();

	/**
	 * Return true if there are any read only IFiles that are being managed.
	 * 
	 * @return boolean
	 * @see com.ibm.etools.emf.workbench.ResourceStateInputProvider#getResources()
	 */
	boolean hasReadOnlyFiles();

	/**
	 * This method should be called prior to saving the contents. It returns true if the contents
	 * can be saved.
	 */
	boolean checkSave() throws CoreException;

	/**
	 * Use this method to set the Shell that will be used to prompt to the user.
	 */
	void setShell(Shell aShell);
}