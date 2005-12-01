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
package org.eclipse.jst.j2ee.internal.dialogs;


import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class DeleteEARDialog extends J2EEDeleteDialog {
	protected DeleteEARComposite deleteComposite;
	protected Set referencedProjects;

	/**
	 * Constructor for DeleteEARDialog.
	 * 
	 * @param parentShell
	 * @param dialogTitle
	 */
	public DeleteEARDialog(Shell parentShell, Set referencedProjects) {
		super(parentShell, DELETE_EAR_OPTIONS);
		this.referencedProjects = referencedProjects;
	}


	/**
	 * @see MessageDialog#createCustomArea(Composite)
	 */
	protected Control createCustomArea(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, IJ2EEUIContextIds.DELEATE_EAR_DIALOG_1); //$NON-NLS-1$
		deleteComposite = new DeleteEARComposite(parent, this, SWT.NONE, referencedProjects);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = 10;
		deleteComposite.setLayoutData(data);
		return deleteComposite;
	}

	/**
	 * @see J2EEDeleteDialog#createDeleteOptions()
	 */
	public void createDeleteOptions() {
		deleteOptions = deleteComposite.createDeleteOptions();
	}

	/**
	 * @see org.eclipse.jface.window.Window#getContents()
	 */
	public Control getContents() {
		return super.getContents();
	}

}
