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
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.RARImportDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.RARImportOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 *  
 */
public class ConnectorModuleImportWizard extends J2EEModuleImportWizard {
	private static final String MAIN_PG = "main"; //$NON-NLS-1$
 
	/**
	 *  
	 */
	public ConnectorModuleImportWizard() {
		super();
	}	
	
	/**
	 * @param model
	 */
	public ConnectorModuleImportWizard(RARImportDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		RARImportDataModel aModel = new RARImportDataModel();
		aModel.setBooleanProperty(J2EEModuleImportDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation getImportOperation() {
		return new RARImportOperation(getJCAImportDataModel());
	}

	public void addPages() {
		addPage(new RARImportPage(getJCAImportDataModel(), MAIN_PG));
	}

	/**
	 *  
	 */
	private RARImportDataModel getJCAImportDataModel() {
		return (RARImportDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.IMPORT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_IMPORT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleImportWizard#getModuleValidatorStrings()
	 */
	protected String[] getModuleValidatorStrings() {
		return new String[]{"org.eclipse.jst.j2ee.internal.jca.validation.UIConnectorValidator"}; //$NON-NLS-1$
	}

}