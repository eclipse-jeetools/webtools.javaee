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
package org.eclipse.jst.j2ee.jca.ui;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleImportOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.jca.ui.internal.wizard.RARImportPage;
import org.eclipse.jst.j2ee.ui.J2EEModuleImportWizard;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to import J2EE Connector module 
 * structures into the Eclipse Workbench from an existing
 * Connector *.rar file.  
 * </p>
 */
public final class ConnectorModuleImportWizard extends J2EEModuleImportWizard { 
 
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public ConnectorModuleImportWizard() {
		super();
	}	
	
	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public ConnectorModuleImportWizard(ConnectorModuleImportDataModel model) {
		super(model);
	}

	/**
	 * <p>
	 * Overridden to return an {@link RARImportDataModel} and defaults
	 * the value of {@link J2EEModuleImportDataModel#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 *  
	 * @see org.eclipse.wst.common.frameworks.internal.ui.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		ConnectorModuleImportDataModel aModel = new ConnectorModuleImportDataModel();
		aModel.setBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * <p>
	 * Returns an {@link ConnectorModuleImportOperation} using the model either
	 * supplied in the constructor or created from {@link #createDefaultModel()}.
	 * </p>
	 * @return Returns the operation to be executed when the Wizard completes.
	 */
	protected WTPOperation getImportOperation() {
		return new ConnectorModuleImportOperation(getSpecificDataModel());
	}
	
	/**
	 * <p>
	 * Adds an {@link RARImportPage} as the main wizard page ({@link #MAIN_PG}).
	 * </p>
	 */
	public void addPages() {
		addPage(new RARImportPage(getSpecificDataModel(), MAIN_PG));
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
		updateEARToModelFromSelection(getSpecificDataModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleImportWizard#getModuleValidatorStrings()
	 */
	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.jca.validation.UIConnectorValidator"}; //$NON-NLS-1$
	}
	

	private ConnectorModuleImportDataModel getSpecificDataModel() {
		return (ConnectorModuleImportDataModel) getModel();
	}

}