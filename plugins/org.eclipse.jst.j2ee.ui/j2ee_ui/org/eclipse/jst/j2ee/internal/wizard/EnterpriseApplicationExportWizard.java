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

import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationExportDataModel;
import org.eclipse.jst.j2ee.internal.archive.operations.EnterpriseApplicationExportOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IExportWizard;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to export J2EE Enterprise Application structures from the Eclipse Workbench to a
 * deployable Enterprise Application Archive *.ear file.
 * </p>
 */
public final class EnterpriseApplicationExportWizard extends J2EEArtifactExportWizard implements IExportWizard {

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public EnterpriseApplicationExportWizard() {
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
	public EnterpriseApplicationExportWizard(EnterpriseApplicationExportDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link EnterpriseApplicationImportDataModel}.
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new EnterpriseApplicationExportDataModel();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Returns an {@link EnterpriseApplicationExportOperation}using the model either supplied in
	 * the constructor or created from {@link #createDefaultModel()}.
	 * </p>
	 * 
	 * @return Returns the operation to be executed when the Wizard completes.
	 */
	protected WTPOperation createBaseOperation() {
		return new EnterpriseApplicationExportOperation(getSpecificModel());
	}

	/**
	 * <p>
	 * Adds the following pages:
	 * <ul>
	 * <li>{@link EARExportPage}as the main wizard page ({@link #MAIN_PG})
	 * </ul>
	 * </p>
	 */
	public void doAddPages() {
		addPage(new EARExportPage(getSpecificModel(), MAIN_PG, getSelection()));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Sets up the default wizard page image.
	 * </p>
	 */
	protected void doInit() {
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_EXPORT_WIZARD_BANNER));
	}

	private EnterpriseApplicationExportDataModel getSpecificModel() {
		return (EnterpriseApplicationExportDataModel) getModel();
	}
}