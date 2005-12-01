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

public class RenameEARDialog extends J2EERenameDialog {
	protected RenameEARComposite renameComposite;
	protected Set referencedProjects;

	/**
	 * Constructor for RenameEARDialog.
	 * 
	 * @param parentShell
	 * @param dialogTitle
	 */
	public RenameEARDialog(Shell parentShell, Set referencedProjects, String name) {
		super(parentShell, RENAME_EAR_OPTIONS, name);
		this.referencedProjects = referencedProjects;
	}


	/**
	 * @see MessageDialog#createCustomArea(Composite)
	 */
	protected Control createCustomArea(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, IJ2EEUIContextIds.RENAME_EAR_DIALOG_1); //$NON-NLS-1$
		renameComposite = new RenameEARComposite(parent, SWT.NONE, referencedProjects);
		//		renameComposite.setNewName(currentName);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = 10;
		renameComposite.setLayoutData(data);
		return renameComposite;
	}

	/**
	 * @see J2EERenameDialog#createRenameOptions()
	 */
	public void createRenameOptions() {
		renameOptions = renameComposite.createRenameOptions();
	}

}
