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

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.AppClientProjectCreationPage;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to create J2EE Application Client module structures in Eclipse Projects.
 * </p>
 */
public final class AppClientModuleCreationWizard extends J2EEModuleCreationWizard {

	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities
	 * management.
	 * </p>
	 */
	public static final String WIZARD_ID = AppClientModuleCreationWizard.class.getName();

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public AppClientModuleCreationWizard() {
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
	public AppClientModuleCreationWizard(AppClientModuleCreationDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link AppClientProjectCreationDataModel}and defaults the value of
	 * {@link J2EEModuleCreationDataModel#ADD_TO_EAR}to <b>true </b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Application
	 *         Clients
	 */
	protected final WTPOperationDataModel createDefaultModel() {
		AppClientModuleCreationDataModel aModel = new AppClientModuleCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link AppClientProjectCreationOperation}.
	 * </p>
	 * 
	 * @return Returns the specific operation for the creation of J2EE Application Clients
	 */
	protected final WTPOperation createBaseOperation() {
		return new AppClientModuleCreationOperation(getSpecificDataModel());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Sets up the dialog window title and default wizard page image.
	 * </p>
	 * 
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	public void doInit() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds an {@link AppClientProjectCreationPage}as the {@link J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	public void doAddPages() {
		addPage(new AppClientProjectCreationPage(getSpecificDataModel(), MAIN_PG));
		super.doAddPages();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.wst.common.frameworks.ui.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return WIZARD_ID;
	}

	private AppClientModuleCreationDataModel getSpecificDataModel() {
		return (AppClientModuleCreationDataModel) model;
	}

}