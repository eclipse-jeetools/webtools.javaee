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
package org.eclipse.jst.j2ee.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactExportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.J2EEExportPage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizard;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * <p>
 * Serves as a base class for Wizards which export J2EE artifact 
 * structures from Eclipse projects into a deployable form. 
 * </p>
 * <p> 
 * Subclasses must provide the methods that are required by 
 * {@link org.eclipse.wst.common.frameworks.internal.ui.WTPWizard}.
 * </p> 
 * <p>
 * Optionally, subclasses may also override the following methods:
 * <ul>
 * 	<li>{@link #doInit()()}
 * 	<li>{@link #doDispose()()}
 * </ul>
 * </p>
 * <p>
 * The base class will ensure that the Wizard is not spawned unless
 * all open editors are in a non-dirty state. Additionally, the 
 * selection from the active view which spanwed the wizard will 
 * be made available to subclasses via {@link #getSelection()}.
 * </p>
 */
public abstract class J2EEArtifactExportWizard extends WTPWizard {
	
	/**
	 * <p>
	 * Constant used to identify the key of the main page of the Wizard.
	 * </p>
	 */
	protected static final String MAIN_PG = "main"; //$NON-NLS-1$

	private IStructuredSelection currentSelection;

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public J2EEArtifactExportWizard() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("67"));//$NON-NLS-1$ 
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public J2EEArtifactExportWizard(WTPOperationDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString("67"));//$NON-NLS-1$ 
	}
	
	/**
	 * <p>
	 * Invoked from init(IWorkbench, IStructuredSelection) 
	 * once the workbench and selection have been safely 
	 * stored away.
	 * </p>
	 * <p>
	 * No-op by default.
	 * </p>
	 */
	protected void doInit() {
		
	}
	/**
	 * <p>
	 * Invoked from {@link #dispose()}. Should be used to handle 
	 * any specific Wizard disposal.
	 * </p>
	 */
	private void doDispose() { 		
	}
	
	/**
	 * <p>
	 * The selection is used to pre-populate values in the Wizard dialog controls.
	 * </p>
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 * 
	 * @param workbench
	 *            the current workbench parent of the wizard
	 * @param aSelection
	 *            the selection from the view used to start the wizard (if any)
	 */
	public final void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EXPORT_WIZ_TITLE));
		this.currentSelection = selection;

		if (this.currentSelection.size() > 0) {
			Object element = this.currentSelection.getFirstElement();
			IProject project = ProjectUtilities.getProject(element);
			if (project != null) {
				J2EEArtifactExportDataModel m = getJ2EEExportDataModel();
				Object originalProjectName = m.getProperty(J2EEArtifactExportDataModel.PROJECT_NAME);
				m.setProperty(J2EEArtifactExportDataModel.PROJECT_NAME, project.getName());
				if (!m.validateProperty(J2EEArtifactExportDataModel.PROJECT_NAME).isOK()) {
					m.setProperty(J2EEArtifactExportDataModel.PROJECT_NAME, originalProjectName);
				}
			}
		}
		doInit();
	}
	
	/**
	 * <p>
	 * Calls {@link #doDispose()} and then nulls out fields that 
	 * are no longer needed once the wizard completes.
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.WTPWizard#dispose()
	 */
	public final void dispose() { 
		super.dispose();
		doDispose();
		this.currentSelection = null;		
	}

	protected final boolean prePerformFinish() {
		if (!CommonEditorUtility.promptToSaveAllDirtyEditors()) {
			return false;
		}
		if (CommonEditorUtility.getDirtyEditors().length != 0) { // all checkboxes were not selected
			return false;
		}
		return super.prePerformFinish();
	}
	
	/**
	 * @return Returns the currentSelection.
	 */
	protected final IStructuredSelection getSelection() {
		return currentSelection;
	}	
	
	/**
	 * @return
	 */
	private J2EEArtifactExportDataModel getJ2EEExportDataModel() {
		return (J2EEArtifactExportDataModel) getModel();
	}

	/**
	 * @return
	 */
	protected final J2EEExportPage getMainPage() {
		return (J2EEExportPage) getPage(MAIN_PG);
	}

}