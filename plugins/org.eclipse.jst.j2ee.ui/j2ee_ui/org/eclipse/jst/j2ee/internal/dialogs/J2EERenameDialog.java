/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;


import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.rename.RenameOptions;
import org.eclipse.swt.widgets.Shell;


public abstract class J2EERenameDialog extends MessageDialog implements J2EERenameUIConstants {

	protected RenameOptions renameOptions;
	protected String currentName = null;

	/**
	 * Constructor for J2EERenameDialog.
	 * 
	 * @param parentShell
	 * @param dialogTitle
	 * @param dialogTitleImage
	 * @param dialogMessage
	 * @param dialogImageType
	 * @param dialogButtonLabels
	 * @param defaultIndex
	 */
	public J2EERenameDialog(Shell parentShell, String dialogTitle, String name) {
		super(parentShell, dialogTitle, null, RENAME_DIALOG_MESSAGE, QUESTION, new String[]{IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
		currentName = name;
	}

	public RenameOptions getRenameOptions() {
		return renameOptions;
	}


	public abstract void createRenameOptions();

	protected void buttonPressed(int buttonId) {
		if (buttonId == 0)
			createRenameOptions();
		super.buttonPressed(buttonId);
	}
}