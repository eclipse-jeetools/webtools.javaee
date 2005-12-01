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



import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.rename.RenameOptions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


public class RenameModuleDialog extends J2EERenameDialog implements J2EERenameUIConstants {

	protected RenameModuleComposite renameComposite;
	protected String contextRoot = null;

	public RenameModuleDialog(Shell parentShell, String name, String oldContextRoot) {
		super(parentShell, RENAME_MODULE_OPTIONS, name);
		contextRoot = oldContextRoot;
	}

	protected Control createCustomArea(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, IJ2EEUIContextIds.RENAME_MODULE_DIALOG_1); //$NON-NLS-1$
		renameComposite = new RenameModuleComposite(parent, SWT.NONE);
		renameComposite.setNewName(currentName);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = 10;
		renameComposite.setLayoutData(data);
		renameComposite.setNewContextRoot(contextRoot);
		return renameComposite;
	}

	public void createRenameOptions() {
		renameOptions = new RenameOptions();
		renameOptions.setRenameProjects(renameComposite.shouldRenameProjects());
		renameOptions.setRenameModules(renameComposite.shouldRenameModules());
		renameOptions.setRenameModuleDependencies(renameComposite.shouldRenameModuleDependencies());
		renameOptions.setNewName(renameComposite.getNewName());
		renameOptions.setNewContextRoot(renameComposite.getNewContextRoot());
	}

	/*
	 * (non-Javadoc) Method declared on Dialog.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		Button OKButton = null;
		super.createButtonsForButtonBar(parent);
		OKButton = getButton(0);
		renameComposite.setOKButton(OKButton);
	}
}
