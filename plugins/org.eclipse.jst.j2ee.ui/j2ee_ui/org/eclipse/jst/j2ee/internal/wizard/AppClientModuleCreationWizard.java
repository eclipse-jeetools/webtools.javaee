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

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to create J2EE Application Client module structures in Eclipse Projects.
 * </p>
 */
public class AppClientModuleCreationWizard extends J2EEModuleCreationWizard {
	
	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID = "org.eclipse.jst.j2ee.AppClientModuleCreationWizard"; //$NON-NLS-1$
	
	/**
	 * {@inheritDoc}
	 */
	public AppClientModuleCreationWizard() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param model used to initialize the wizard and to interface with the operation
	 */
	public AppClientModuleCreationWizard(AppClientProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@see AppClientProjectCreationDataModel} and defaults
	 * the value of {@see J2EEModuleCreationDataModel#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Application Clients 
	 */
	protected final WTPOperationDataModel createDefaultModel() {
		AppClientProjectCreationDataModel aModel = new AppClientProjectCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@see AppClientProjectCreationOperation}. 
	 * </p>
	 * @return Returns the specific operation for the creation of J2EE Application Clients 
	 */
	protected final WTPOperation createBaseOperation() {
		return new AppClientProjectCreationOperation(getAppClientProjectCreationDataModel());
	}

	/**
	 * {@inheritDoc}   
	 * 
	 * <p>
	 * Sets up the dialog window title and default wizard page image. 
	 * </p>
	 * 
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	public void doInit() { 
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds an {@see AppClientProjectCreationPage} as the {@see J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	public void doAddPages() {
		addPage(new AppClientProjectCreationPage(getAppClientProjectCreationDataModel(), MAIN_PG));
		super.doAddPages();
	}

	/**
	 * {@inheritDoc}  
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.extensionui.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return WIZARD_ID;
	} 	
	
	/**
	 * {@inheritDoc}  
	 * 
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactCreationWizard#getPluginId()
	 */
	public String getPluginId() {
		return J2EEUIPlugin.PLUGIN_ID;
	}

	private AppClientProjectCreationDataModel getAppClientProjectCreationDataModel() {
		return (AppClientProjectCreationDataModel) model;
	}

}