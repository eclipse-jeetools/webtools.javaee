/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentImportWizard;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * <p>
 * Wizard used to import J2EE Connector module structures into the Eclipse Workbench from an
 * existing Connector *.rar file.
 * </p>
 */
public final class ConnectorComponentImportWizard extends J2EEComponentImportWizard {

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public ConnectorComponentImportWizard() {
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
	public ConnectorComponentImportWizard(IDataModel model) {
		super(model);
	}

	/**
	 * <p>
	 * Adds an {@link RARImportPage} as the main wizard page ({@link #MAIN_PG}).
	 * </p>
	 */
	public void doAddPages() {
		addPage(new ConnectorComponentImportPage(getDataModel(), MAIN_PG));
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
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_IMPORT_WIZARD_BANNER));
		// updateEARToModelFromSelection(getSpecificDataModel());
	}

	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.jca.validation.UIConnectorValidator"}; //$NON-NLS-1$
	}

	protected IDataModelProvider getDefaultProvider() {
		return new ConnectorComponentImportDataModelProvider();
	}

}
