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

import org.eclipse.jst.j2ee.applicationclient.internal.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientComponentImportPage extends J2EEModuleImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public AppClientComponentImportPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_IMPORT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_IMPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_IMPORT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFileImportLabel()
	 */
	protected String getFileImportLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_IMPORT_FILE_LABEL);
	}

	protected String getFileNamesStoreID() {
		return IApplicationClientNatureConstants.NATURE_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFilterExpression()
	 */
	protected String[] getFilterExpression() {
		return new String[]{"*.jar"}; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getProjectImportLabel()
	 */
	protected String getProjectImportLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_IMPORT_PROJECT_LABEL);
	}

	// protected J2EEComponentCreationDataModel getNewProjectCreationDataModel() {
	// return getAppClientDataModel().getJ2eeArtifactCreationDataModel();
	// }
	//
	// private AppClientModuleImportDataModel getAppClientDataModel() {
	// return (AppClientModuleImportDataModel) model;
	//	}

	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.IMPORT_APPCLIENT_WIZARD_P1);
		return super.createTopLevelComposite(parent);
	}

}