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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEUtilityJarListImportDataModel;
import org.eclipse.ui.IImportWizard;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;


/**
 * <p>
 * Used to import Utility Jars into several Eclipse workbench configurations. These can be extracted
 * as editable projects, binary projects, linked resources in the EAR module or actual resources in
 * the EAR module.
 * </p>
 */
public final class J2EEUtilityJarImportWizard extends J2EEArtifactImportWizard implements IImportWizard {

	/**
	 * <p>
	 * Constant used to identify the key of the page of the Wizard which allows users to define the
	 * type of import they would like to carry out.
	 * </p>
	 */
	private static final String IMPORT_TYPE = "IMPORT_TYPE"; //$NON-NLS-1$

	/**
	 * <p>
	 * Constant used to identify the key of the page of the Wizard that allows users to select jar
	 * files for import
	 * </p>
	 */
	private static final String SELECT_JARS = "SELECT_JARS"; //$NON-NLS-1$

	private IStructuredSelection selection;

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public J2EEUtilityJarImportWizard() {
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
	public J2EEUtilityJarImportWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link J2EEUtilityJarListImportDataModel}.
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new J2EEUtilityJarListImportDataModel();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Returns an {@link J2EEUtilityJarImportWizard}using the model either supplied in the
	 * constructor or created from {@link #createDefaultModel()}.
	 * </p>
	 * 
	 * @return Returns the operation to be executed when the Wizard completes.
	 */
	protected WTPOperation createBaseOperation() {
		return getModel().getDefaultOperation();
	}

	/**
	 * <p>
	 * Adds the following pages:
	 * <ul>
	 * <li>{@link J2EEUtilityJarImportTypePage}as the main wizard page ({@link #IMPORT_TYPE})
	 * <li>{@link J2EEUtilityJarImportPage}as the main wizard page ({@link #SELECT_JARS})
	 * </ul>
	 * </p>
	 */
	public void doAddPages() {
		this.addPage(new J2EEUtilityJarImportTypePage(getUtilityJarImportModel(), IMPORT_TYPE, selection));
		this.addPage(new J2EEUtilityJarImportPage(getUtilityJarImportModel(), SELECT_JARS));
	}

	private J2EEUtilityJarListImportDataModel getUtilityJarImportModel() {
		return (J2EEUtilityJarListImportDataModel) getModel();
	}

}