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

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactCreationWizard;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleCreationWizard;
import org.eclipse.jst.j2ee.jca.ui.internal.plugin.JCAUIPlugin;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.ui.INewWizard;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/** 
 * <p>
 * Wizard used to create J2EE Connector module structures in Eclipse Projects.
 * </p>
 */
public class ConnectorModuleCreationWizard extends J2EEModuleCreationWizard implements IExecutableExtension, INewWizard {

	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID = "org.eclipse.jst.j2ee.ConnectorModuleCreationWizard"; //$NON-NLS-1$
	
	/**
	 * {@inheritDoc}
	 */
	public ConnectorModuleCreationWizard() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param model used to initialize the wizard and to interface with the operation
	 */
	public ConnectorModuleCreationWizard(ConnectorModuleCreationDataModel model) {
		super(model);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@see ConnectorProjectCreationDataModel} and defaults
	 * the value of {@see J2EEModuleCreationDataModel#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Connector modules
	 */
	protected final WTPOperationDataModel createDefaultModel() {
		ConnectorModuleCreationDataModel aModel = new ConnectorModuleCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@see ConnectorProjectCreationOperation}. 
	 * </p>
	 * 
	 * @return Returns the specific operation for the creation of J2EE Connector modules
	 */
	protected final WTPOperation createBaseOperation() {
		return new ConnectorModuleCreationOperation(getConnectorProjectCreationDataModel());
	}
	
	/** 
	 * {@inheritDoc}   
	 * 
	 * <p>
	 * Sets up the dialog window title and default page image. 
	 * </p> 
	 * 
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	protected void doInit() {
		setWindowTitle(JCAUIMessages.getResourceString(JCAUIMessages.JCA_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@see JCAProjectCreationPage} as the {@see J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	public void doAddPages() {
		addPage(new JCAProjectCreationPage(getConnectorProjectCreationDataModel(), MAIN_PG));
		super.doAddPages();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
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
		return JCAUIPlugin.PLUGIN_ID;
	}

	private ConnectorModuleCreationDataModel getConnectorProjectCreationDataModel() {
		return (ConnectorModuleCreationDataModel) model;
	}

}