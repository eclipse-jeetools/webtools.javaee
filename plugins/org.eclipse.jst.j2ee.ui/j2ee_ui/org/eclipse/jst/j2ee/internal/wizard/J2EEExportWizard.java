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
 * Created on Dec 17, 2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEExportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizard;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class J2EEExportWizard extends WTPWizard {

	private IStructuredSelection currentSelection;

	protected static final String MAIN_PG = "main"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public J2EEExportWizard(WTPOperationDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString("67"));//$NON-NLS-1$ 
	}

	/**
	 *  
	 */
	public J2EEExportWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("67"));//$NON-NLS-1$ 
	}

	/**
	 * @return
	 */
	protected J2EEExportDataModel getJ2EEExportDataModel() {
		return (J2EEExportDataModel) getModel();
	}

	/**
	 * @return
	 */
	protected J2EEExportPage getMainPage() {
		return (J2EEExportPage) getPage(MAIN_PG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected abstract WTPOperationDataModel createDefaultModel();

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EXPORT_WIZ_TITLE));
		this.currentSelection = selection;

		if (this.currentSelection.size() > 0) {
			Object element = this.currentSelection.getFirstElement();
			IProject project = ProjectUtilities.getProject(element);
			if (project != null) {
				J2EEExportDataModel m = getJ2EEExportDataModel();
				Object originalProjectName = m.getProperty(J2EEExportDataModel.PROJECT_NAME);
				m.setProperty(J2EEExportDataModel.PROJECT_NAME, project.getName());
				if (!m.validateProperty(J2EEExportDataModel.PROJECT_NAME).isOK()) {
					m.setProperty(J2EEExportDataModel.PROJECT_NAME, originalProjectName);
				}
			}
		}
	}

	protected abstract void doInit();

	/**
	 * @return Returns the currentSelection.
	 */
	protected IStructuredSelection getCurrentSelection() {
		return currentSelection;
	}

	protected boolean prePerformFinish() {
		if (!CommonEditorUtility.promptToSaveAllDirtyEditors()) {
			return false;
		}
		if (CommonEditorUtility.getDirtyEditors().length != 0) { // all checkboxes were not selected
			return false;
		}
		return super.prePerformFinish();
	}
}