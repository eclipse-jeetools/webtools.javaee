/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.ejb.modulecore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizard;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;

public abstract class NewEjbWizard extends WTPWizard implements INewWizard {

	/**
	 * @param model
	 */
	public NewEjbWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 * Default constructor
	 */
	public NewEjbWizard() {
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
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		createDefaultModel();
	}

	protected IProject getDefaultEjbProject() {
		IProject project = null;
		IStructuredSelection selection = getCurrentSelection();
		if (selection != null && selection.getFirstElement() != null) {
			project = JavaProjectUtilities.getProject(selection
					.getFirstElement());
		}
		if (project == null) {
			IProject[] projects = JavaProjectUtilities.getAllProjects();
			for (int i = 0; i < projects.length; i++) {
				ModuleCore core = null;
				core = ModuleCore.getModuleCoreForRead(projects[i]);
				WorkbenchComponent[] components = core
						.findWorkbenchModuleByType(EJBArtifactEdit.TYPE_ID);
				if (components != null && components.length > 0) {
					project = projects[i];
					if (core != null)
						core.dispose();
					break;
				}
			}
		}
		return project;
	}

	protected IStructuredSelection getCurrentSelection() {
		IWorkbenchWindow window = J2EEUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				return (IStructuredSelection) selection;
			}
		}
		return null;
	}

}