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
/*
 * Created on Dec 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportPage;
import org.eclipse.jst.j2ee.jca.ui.ConnectorModuleCreationWizard;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizard;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class RARImportPage extends J2EEModuleImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public RARImportPage(J2EEModuleImportDataModel model, String pageName) {
		super(model, pageName);
		setTitle(JCAUIMessages.getResourceString(JCAUIMessages.JCA_IMPORT_MAIN_PG_TITLE));
		setDescription(JCAUIMessages.getResourceString(JCAUIMessages.JCA_IMPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_IMPORT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFileImportLabel()
	 */
	protected String getFileImportLabel() {
		return JCAUIMessages.getResourceString(JCAUIMessages.JCA_FILE_LBL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFilterExpression()
	 */
	protected String[] getFilterExpression() {
		return new String[]{"*.rar"}; //$NON-NLS-1$
	}

	protected J2EEArtifactCreationDataModel getNewProjectCreationDataModel() {
		return getJCADataModel().getJ2eeArtifactCreationDataModel();
	}

	protected WTPWizard getNewProjectWizard(J2EEArtifactCreationDataModel aModel) {
		return new ConnectorModuleCreationWizard((ConnectorModuleCreationDataModel) aModel);
	}

	protected String getFileNamesStoreID() {
		return IConnectorNatureConstants.NATURE_ID;
	}

	/**
	 *  
	 */
	private ConnectorModuleImportDataModel getJCADataModel() {
		return (ConnectorModuleImportDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getProjectImportLabel()
	 */
	protected String getProjectImportLabel() {
		return JCAUIMessages.getResourceString(JCAUIMessages.JCA_PROJECT_LBL);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		return super.createTopLevelComposite(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#getInfopopID()
	 */
	protected String getInfopopID() {
		return IJ2EEUIContextIds.IMPORT_RAR_WIZARD_PAGE;
	}
}