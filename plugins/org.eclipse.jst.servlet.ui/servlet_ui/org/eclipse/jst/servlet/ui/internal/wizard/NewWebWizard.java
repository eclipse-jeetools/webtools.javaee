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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.ui.WTPWizard;
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jlanuti
 */
public abstract class NewWebWizard extends WTPWizard implements INewWizard {

	/**
	 * @param model
	 */
	public NewWebWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 * Default constructor
	 */
	public NewWebWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected abstract WTPOperationDataModel createDefaultModel();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createOperation()
	 */
	protected abstract WTPOperation createOperation();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		createDefaultModel();
	}

	protected IProject getDefaultWebProject() {
		IProject project = null;
		IStructuredSelection selection = getCurrentSelection();
		if (selection != null && selection.getFirstElement() != null) {
			project = ProjectUtilities.getProject(selection.getFirstElement());
		}
		if (project == null) {
			IProject[] projects = ProjectUtilities.getAllProjects();
			ArtifactEdit edit = null;
			for (int i = 0; i < projects.length; i++) {
				//TODO this assumes only web projects and one module per project for now
				try {
					edit = ModuleCore.getFirstArtifactEditForRead(projects[i]);
					if (edit != null) {
						project = projects[i];
						break;
					}
				} finally {
					if (edit != null)
						edit.dispose();
				}
			}
		}
		return project;
	}

	protected IStructuredSelection getCurrentSelection() {
		IWorkbenchWindow window = J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				return (IStructuredSelection) selection;
			}
		}
		return null;
	}

}