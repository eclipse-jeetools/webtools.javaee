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
import org.eclipse.jst.j2ee.internal.delete.DeleteOptions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


public class DeleteModuleDialog extends J2EEDeleteDialog implements J2EEDeleteUIConstants {

	protected DeleteModuleComposite deleteComposite;

	public DeleteModuleDialog(Shell parentShell) {
		super(parentShell, DELETE_MODULE_OPTIONS);
	}

	protected Control createCustomArea(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, IJ2EEUIContextIds.DELEATE_MODULE_DIALOG_1); //$NON-NLS-1$
		deleteComposite = new DeleteModuleComposite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = 10;
		deleteComposite.setLayoutData(data);
		return deleteComposite;
	}

	public void createDeleteOptions() {
		deleteOptions = new DeleteOptions();
		deleteOptions.setDeleteProjects(deleteComposite.shouldDeleteProjects());
		deleteOptions.setDeleteModules(deleteComposite.shouldDeleteModules());
		deleteOptions.setDeleteModuleDependencies(deleteComposite.shouldDeleteModuleDependencies());
	}

}

