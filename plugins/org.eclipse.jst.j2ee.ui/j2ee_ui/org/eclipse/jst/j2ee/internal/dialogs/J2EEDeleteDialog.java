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
import org.eclipse.jst.j2ee.internal.delete.DeleteOptions;
import org.eclipse.swt.widgets.Shell;


public abstract class J2EEDeleteDialog extends MessageDialog implements J2EEDeleteUIConstants {

	protected DeleteOptions deleteOptions;

	/**
	 * Constructor for J2EEDeleteDialog.
	 * 
	 * @param parentShell
	 * @param dialogTitle
	 * @param dialogTitleImage
	 * @param dialogMessage
	 * @param dialogImageType
	 * @param dialogButtonLabels
	 * @param defaultIndex
	 */
	public J2EEDeleteDialog(Shell parentShell, String dialogTitle) {
		super(parentShell, dialogTitle, null, DELETE_DIALOG_MESSAGE, QUESTION, new String[]{IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
	}

	public DeleteOptions getDeleteOptions() {
		return deleteOptions;
	}


	public abstract void createDeleteOptions();

	protected void buttonPressed(int buttonId) {
		if (buttonId == 0)
			createDeleteOptions();
		super.buttonPressed(buttonId);
	}

}