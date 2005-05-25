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

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.application.internal.operations.EnterpriseApplicationImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IImportWizard;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * <p>
 * Wizard used to import J2EE Application structures into the Eclipse Workbench from an existing
 * Enterprise Application Archive *.ear file.
 * </p>
 */
public final class EnterpriseApplicationImportWizardNew extends J2EEArtifactImportWizardNew implements IExecutableExtension, IImportWizard {

	/**
	 * <p>
	 * Constant used to identify the key of the Projects page of the Wizard.
	 * </p>
	 */
	protected static final String PROJECT_PG = "projects"; //$NON-NLS-1$

	/**
	 * <p>
	 * Constant used to identify the key of the Options page of the Wizard.
	 * </p>
	 */
	protected static final String OPTIONS_PG = "options"; //$NON-NLS-1$	

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public EnterpriseApplicationImportWizardNew() {
		super();
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
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
	public EnterpriseApplicationImportWizardNew(IDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString("38")); //$NON-NLS-1$
	}

	/**
	 * <p>
	 * Adds the following pages:
	 * <ul>
	 * <li>{@link EARImportPageNew}as the main wizard page ({@link #MAIN_PG})
	 * <li>{@link EARImportOptionsPageNew}as the options wizard page ({@link #OPTIONS_PG})
	 * <li>{@link EARImportProjectsPageNew}as the project wizard page ({@link #PROJECT_PG})
	 * </ul>
	 * 
	 * </p>
	 */
	public void doAddPages() {
		addPage(new EARImportPageNew(getDataModel(), MAIN_PG));
		addPage(new EARImportOptionsPageNew(getDataModel(), OPTIONS_PG));
		addPage(new EARImportProjectsPageNew(getDataModel(), PROJECT_PG));
	}



	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Sets up the dialog window title and default wizard page image.
	 * </p>
	 */
	protected void doInit() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.IMPORT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));
	}

	protected IDataModelProvider getDefaultProvider() {
		return new EnterpriseApplicationImportDataModelProvider();
	}

}