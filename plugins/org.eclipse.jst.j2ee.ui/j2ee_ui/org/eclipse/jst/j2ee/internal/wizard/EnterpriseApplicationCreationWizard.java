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
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

/**
 * <p>
 * Provides a user interface for the creation of J2EE Enterprise Application Projects.
 * </p>
 */
public final class EnterpriseApplicationCreationWizard extends J2EEArtifactCreationWizard {

	/**
	 * <p>
	 * Constant used to identify the key of the Modules page of the Wizard.
	 * </p>
	 */
	private static final String MODULES_PG = "modules"; //$NON-NLS-1$

	public static final String WIZARD_ID = EnterpriseApplicationCreationWizard.class.getName();

	/**
	 * <p>
	 * Creates a default instance of the wizard with no configuration data, no selection, and no
	 * operation data model.
	 * </p>
	 */
	public EnterpriseApplicationCreationWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate wizard controls and to collect data from the user. The model
	 * will eventually be used to run the operation, if the user does not cancel the Wizard.
	 * </p>
	 * 
	 * @param model
	 *            used to collect information and interface with the WTP Operation
	 */
	public EnterpriseApplicationCreationWizard(EnterpriseApplicationCreationDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	protected void doAddPages() {
		addPage(new ApplicationProjectCreationPage(getSpecificDataModel(), MAIN_PG));
		if (!model.getBooleanProperty(EnterpriseApplicationCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY)) {
			addPage(new ApplicationModuleProjectsPage(getSpecificDataModel(), MODULES_PG));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Creates a new {@link EARProjectCreationDataModel}
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new EnterpriseApplicationCreationDataModel();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new EnterpriseApplicationCreationOperation(getSpecificDataModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	protected void doInit() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
		setHelpAvailable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return WIZARD_ID;
	}

	private EnterpriseApplicationCreationDataModel getSpecificDataModel() {
		return (EnterpriseApplicationCreationDataModel) model;
	}
}