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
/*
 * Created on Mar 31, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.wizard.ClassesImportWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author jsholl
 * 
 * To change this generated comment go to Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ImportClassesAction extends WTPBaseAction {

	public static String LABEL = J2EEUIMessages.getResourceString("Import_Classes"); //$NON-NLS-1$

	public ImportClassesAction() {
		super();
		setText(LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.common.actions.BaseAction#primRun(org.eclipse.swt.widgets.Shell)
	 */
	protected void primRun(Shell shell) {
		IProject project = ProjectUtilities.getProject(getSelection().getFirstElement());
		ClassesImportWizard wizard = new ClassesImportWizard(project);


		wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
		wizard.setDialogSettings(J2EEUIPlugin.getDefault().getDialogSettings());

		WizardDialog dialog = new WizardDialog(shell, wizard);

		dialog.create();
		dialog.getShell().setSize(550, 550);
		dialog.open();
	}

}