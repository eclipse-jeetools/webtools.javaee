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

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientModuleImportOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to import J2EE Application Client module structures into the Eclipse Workbench from
 * an existing Application Client *.jar file.
 * </p>
 */
public final class AppClientModuleImportWizard extends J2EEModuleImportWizard {

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public AppClientModuleImportWizard() {
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
	public AppClientModuleImportWizard(AppClientModuleImportDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link AppClientImportDataModel}and defaults the value of
	 * {@link J2EEModuleImportDataModel#ADD_TO_EAR}to <b>true </b>
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		AppClientModuleImportDataModel aModel = new AppClientModuleImportDataModel();
		aModel.setBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Returns an {@link AppClientImportOperation}using the model either supplied in the
	 * constructor or created from {@link #createDefaultModel()}.
	 * </p>
	 * 
	 * @return Returns the operation to be executed when the Wizard completes.
	 */
	protected WTPOperation getImportOperation() {
		return new AppClientModuleImportOperation(getSpecificDataModel());
	}

	/**
	 * <p>
	 * Adds an {@link AppClientImportPage}as the main wizard page ({@link #MAIN_PG}).
	 * </p>
	 */
	public void doAddPages() {
		addPage(new AppClientImportPage(getSpecificDataModel(), MAIN_PG));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Sets up the dialog window title and default wizard page image.
	 * </p>
	 */
	public final void doInit() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.IMPORT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_IMPORT_WIZARD_BANNER));
		updateEARToModelFromSelection(getSpecificDataModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleImportWizard#getModuleValidatorStrings()
	 */
	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.validation.UIApplicationClientValidator"}; //$NON-NLS-1$
	}

	private AppClientModuleImportDataModel getSpecificDataModel() {
		return (AppClientModuleImportDataModel) getModel();
	}
}