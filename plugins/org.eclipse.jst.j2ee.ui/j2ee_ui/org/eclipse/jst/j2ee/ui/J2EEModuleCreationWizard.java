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
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModulesDependencyPage;


/**
 * <p>
 * Serves as a base class for Wizards which create J2EE module structures in Eclipse projects.
 * </p>
 * <p>
 * The EAR will be pre-populated in the Wizard controls if any selection was provided to the Wizard.
 * </p>
 * <p>
 * Refer to {@link org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactCreationWizard}for information
 * on how to extend this class.
 * </p>
 * 
 * @see org.eclipse.jst.j2ee.ui.J2EEArtifactCreationWizard
 */
public abstract class J2EEModuleCreationWizard extends J2EEArtifactCreationWizard {

	/**
	 * <p>
	 * Constant used to identify the key of the main page of the Wizard.
	 * </p>
	 */
	protected static final String MODULE_PG = "module"; //$NON-NLS-1$

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public J2EEModuleCreationWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls and interface with the operation.
	 * </p>
	 * 
	 * @param model
	 *            The model parameter is used to pre-populate wizard controls and interface with the
	 *            operation
	 */
	public J2EEModuleCreationWizard(J2EEModuleCreationDataModel model) {
		super(model);
	}

	/**
	 * <p>
	 * The selection stored in
	 * {@link J2EEArtifactCreationWizard#init(IWorkbench, IStructuredSelection)}is used to
	 * pre-populate the EAR project in the Wizard dialog controls.
	 * </p>
	 * 
	 * @see J2EEArtifactCreationWizard#init(IWorkbench, IStructuredSelection)
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	protected void doInit() {
		preFillSelectedEARProject();
	}

	/**
	 * <p>
	 * Subclasses which override this method should always call super.addModulesPageIfNecessary()
	 * ahead of before their own pages.
	 * </p>
	 *  
	 */
	protected void addModulesPageIfNecessary() {
		if (model.getBooleanProperty(J2EEModuleCreationDataModel.UI_SHOW_EAR_SECTION)) {
			addPage(new J2EEModulesDependencyPage((J2EEModuleCreationDataModel) model, MODULE_PG));
		}
	}

	/**
	 * @inheritDoc
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	protected void doAddPages() {
		addModulesPageIfNecessary();
	}

	/**
	 * <p>
	 * Skips the page identified by the MODULE_PG name if
	 * {@link J2EEModuleCreationWizard#shouldShowModulesPage()}is false.
	 * </p>
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getPreviousPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previous = super.getPreviousPage(page);
		if (previous != null && previous.getName().equals(MODULE_PG)) {
			if (!shouldShowModulesPage()) {
				previous = super.getPreviousPage(previous);
			}
		}
		return previous;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Skips the page identified by the MODULE_PG name if
	 * {@link J2EEModuleProjectCreationWizard#shouldShowModulesPage()}is false.
	 * </p>
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage next = super.getNextPage(page);
		if (next != null && next.getName().equals(MODULE_PG)) {
			if (!shouldShowModulesPage()) {
				next = super.getNextPage(next);
			}
		}
		return next;
	}

	/**
	 * <p>
	 * Uses the model to determine if the module page should be shown. The default is to AND the
	 * values of model.getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR) and
	 * shouldShowModulesPageForEAR().
	 * </p>
	 * 
	 * @return Returns a boolean true if the module page should be shown.
	 */
	protected final boolean shouldShowModulesPage() {
		return model.getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR) && shouldShowModulesPageForEAR();
	}

	/**
	 * <p>
	 * Uses the model to determine if the module page should be shown for the EAR. If no modules are
	 * present in the classpath selection of the model, the method will return false.
	 * </p>
	 * 
	 * @return true only if the
	 * @see J2EEModuleCreationDataModel#getClassPathSelection() is non-empty.
	 */
	protected final boolean shouldShowModulesPageForEAR() {
		IProject earProject = model.getProjectHandle(J2EEModuleCreationDataModel.EAR_PROJECT_NAME);
		if (null != earProject && earProject.exists() && earProject.isAccessible())
			return !((J2EEModuleCreationDataModel) model).getClassPathSelection().getClasspathElements().isEmpty();
		return false;
	}

	private void preFillSelectedEARProject() {
		IProject project = getSelectedEARProject();
		if (project != null && model != null) {
			EARNatureRuntime earNature = EARNatureRuntime.getRuntime(project);
			if (null != earNature) {
				int j2eeVersion = earNature.getJ2EEVersion();
				if (j2eeVersion == J2EEVersionConstants.J2EE_1_2_ID && model instanceof ConnectorModuleCreationDataModel) {
					j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
				}
				model.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
				model.setIntProperty(J2EEModuleCreationDataModel.J2EE_VERSION, j2eeVersion);
				model.setProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME, project.getName());
			}
		}
	}
}