/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.util;


import org.eclipse.core.resources.IProject;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.widgets.Shell;

public class BinaryProjectUIHelper {

	public static final String DIALOG_TITLE = J2EEUIMessages.getResourceString("BINARY_PROJECT"); //$NON-NLS-1$
	public static final String DIALOG_MESSAGE = J2EEUIMessages.getResourceString("ACTION_CANNOT_BE_PERFORMED_ON_BIN_PROJECT"); //$NON-NLS-1$

	/**
	 * Constructor for BinaryProjectUIHelper.
	 */
	public BinaryProjectUIHelper() {
		super();
	}

	public static void displayError(Shell parent) {
		MessageDialog.openError(parent, DIALOG_TITLE, DIALOG_MESSAGE);
		return;
	}

	public static boolean displayErrorIfBinaryProject(Shell parent, IProject aProject) {
		boolean isBinary = JemProjectUtilities.isBinaryProject(aProject);
		if (isBinary)
			displayError(parent);
		return isBinary;
	}
}

