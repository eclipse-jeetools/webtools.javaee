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

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportWizardNew;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * <p>
 * Wizard used to import J2EE Web Application module structures into the Eclipse Workbench from an
 * existing Web Application *.war file.
 * </p>
 */
public final class WebModuleImportWizardNew extends J2EEModuleImportWizardNew {

	protected static final String WEB_LIBS_PG = "WEB_LIBS_PG";
	
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public WebModuleImportWizardNew() {
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
	public WebModuleImportWizardNew(IDataModel model) {
		super(model);
	}

	/**
	 * <p>
	 * Adds an {@link WARImportPage} as the main wizard page ({@link #MAIN_PG}).
	 * </p>
	 */
	public void doAddPages() {
		addPage(new WARImportPageNew(getDataModel(), MAIN_PG));
		addPage(new WARImportWebLibsPage(getDataModel(), MAIN_PG));
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
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_IMPORT_WIZARD_BANNER));
		// updateEARToModelFromSelection(getSpecificDataModel());
	}

	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.web.validation.UIWarValidator"}; //$NON-NLS-1$
	}


	// protected String getFinalPerspectiveID() {
	// // J2EEComponentCreationDataModel projCreationModel =
	// // getSpecificDataModel().getJ2EEModuleCreationDataModel();
	// // return projCreationModel.getStringProperty(ComponentCreationDataModel.FINAL_PERSPECTIVE);
	// return getDataModel().getStringProperty(ComponentCreationDataModel.FINAL_PERSPECTIVE);
	// }

	protected IDataModelProvider getDefaultProvider() {
		return new WebModuleImportDataModelProvider();
	}


}