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
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.common.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.actions.AbstractActionDelegate;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.ui.util.BinaryProjectUIHelper;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.WorkbenchPage;

/**
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MigrateJ2EEActionDelgate extends AbstractActionDelegate implements IJ2EEMigrationConstants {

	private int j2eeVersion = 12;
	private boolean validJ2EEVersion = true;

	/**
	 * Constructor for MigrateJ2EEActionDelgate.
	 */
	public MigrateJ2EEActionDelgate() {
		super();
		setAllowsMultiSelect(true);
	}

	/**
	 * @see org.eclipse.jst.j2ee.common.actions.AbstractActionDelegate#isSupportedAction(Object)
	 */
	protected boolean isSupportedAction(Object element) {
		return CommonUtil.isDeploymentDescriptorRoot(element, true);
	}

	protected void primRun(Shell shell) {
		List projects = getProjects();
		if (!ensureSingleSelectionNotBinary(shell, projects))
			return;
		if (!checkNoEditorsOpen(shell))
			return;

		if (!validateSelection(shell, projects))
			return;
		openWizard(shell, projects);
	}


	private boolean ensureSingleSelectionNotBinary(Shell shell, List projects) {
		return projects.size() != 1 || !BinaryProjectUIHelper.displayErrorIfBinaryProject(shell, (IProject) projects.get(0));
	}


	private boolean validateSelection(Shell shell, List projects) {
		boolean validSelection = true;
		validJ2EEVersion = true;
		if (!isValidSelection(projects)) {
			informInvalidSelection(shell);
			validSelection = false;
		} else if (!validJ2EEVersion) {
			informInvalidVersionSelection(shell);
			validSelection = false;
		}
		return validSelection;
	}

	/**
	 * @param shell
	 */
	private void informInvalidVersionSelection(Shell shell) {
		MessageDialog.openInformation(shell, INVALID_SELECTION_TITLE, INFORM_INVALID_J2EE_SELECTION);

	}

	//	/**
	//	 * @return
	//	 */
	//	private boolean j2eeVersionHighestJ2EEPreferenceVersion() {
	//		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
	//		return (j2eeVersion >= highestJ2EEPref);
	//	}

	private boolean isValidSelection(List projects) {
		if (projects.isEmpty())
			return false;
		int size = projects.size();
		for (int i = 0; i < projects.size(); i++) {
			J2EENature nature = J2EENature.getRegisteredRuntime((IProject) projects.get(i));
			if (nature == null)
				return false;
			j2eeVersion = nature.getJ2EEVersion();
			if (j2eeVersion == 14)
				validJ2EEVersion = false;
			switch (nature.getDeploymentDescriptorType()) {
				case XMLResource.APPLICATION_TYPE :
					return size == 1;
				case XMLResource.RAR_TYPE :
					return j2eeVersion >= 13;
				default :
					continue;
			}
		}
		return true;
	}

	/**
	 * Method informInvalidSelection.
	 */
	private void informInvalidSelection(Shell shell) {
		MessageDialog.openInformation(shell, INVALID_SELECTION_TITLE, INFORM_PROPER_SELECTION);
	}

	protected void openWizard(Shell shell, List projects) {
		J2EEMigrationWizard wizard = new J2EEMigrationWizard();
		J2EEUIPlugin plugin = J2EEUIPlugin.getDefault();
		wizard.init(plugin.getWorkbench(), (IStructuredSelection) selection);
		wizard.setProjects(projects);
		wizard.setDialogSettings(plugin.getDialogSettings());
		wizard.setJ2eeVersion(j2eeVersion);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.getShell().setSize(530, 600);
		dialog.open();
	}

	private boolean checkNoEditorsOpen(Shell shell) {
		if (isEditorOpen()) {
			boolean answer = MessageDialog.openQuestion(shell, OPEN_EDITORS_TITLE, INFORM_OPEN_EDITORS);
			if (answer) {
				IWorkbenchWindow[] windows = getWorkbenchWindows();
				for (int i = 0; i < windows.length; i++) {
					IWorkbenchPage[] pages = windows[i].getPages();
					for (int x = 0; x < pages.length; x++) {
						IEditorReference[] editorRefs = pages[x].getEditorReferences();
						for (int j = 0; j < editorRefs.length; j++) {
							IEditorReference reference = editorRefs[j];
							((WorkbenchPage) pages[x]).closeEditor(reference, true);
						}
					}
				}
			} else
				return false;
		}
		return true;
	}

	protected boolean isEditorOpen() {
		IWorkbenchWindow[] windows = getWorkbenchWindows();
		for (int i = 0; i < windows.length; i++) {
			IWorkbenchPage[] pages = windows[i].getPages();
			for (int x = 0; x < pages.length; x++) {
				IEditorReference[] refs = pages[x].getEditorReferences();
				if (refs != null && refs.length > 0)
					return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	private IWorkbenchWindow[] getWorkbenchWindows() {
		IWorkbench workbench = J2EEUIPlugin.getDefault().getWorkbench();
		IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
		return windows;
	}
}